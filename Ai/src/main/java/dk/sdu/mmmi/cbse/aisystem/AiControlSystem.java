package dk.sdu.mmmi.cbse.aisystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commoninvisibleobject.InvisibleObject;
import dk.sdu.mmmi.cbse.commonmap.Map;
import dk.sdu.mmmi.cbse.commonmapenemy.MapEnemy;
import dk.sdu.mmmi.cbse.commonmapobject.CommonMapObject;
import dk.sdu.mmmi.cbse.commonmapplayer.MapPlayer;
import dk.sdu.mmmi.cbse.commonobstacle.Obstacle;
import dk.sdu.mmmi.cbse.commonpath.CommonPath;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import dk.sdu.mmmi.cbse.commonweaponcoin.WeaponCoin;


import java.util.*;


public class AiControlSystem implements IEntityProcessingService {

    public void process(GameData gameData, World world) {
        // Create a new 20x20 2D array to represent the game world
        CommonMapObject[][] mapArray = new CommonMapObject[20][20];


        Node[][] nodes = new Node[mapArray.length][mapArray[0].length];
        Node start = null, end = null;

        //TODO: Fix this. This only works when there is only one enemy
        Enemy enemy = new Enemy();

        for (Entity enemy1 : world.getEntities(Enemy.class)) {
            // TODO: Calculate the path for each enemy inside here and set it to the enemy
            enemy = (Enemy) enemy1;
            // Initialize the gameWorld array with InvisibleObject
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    mapArray[i][j] = new InvisibleObject();
                }
            }

            // Iterate over all the entities
            for (Entity entity : world.getEntities()) {
                int x = (int) entity.getX() / (gameData.getDisplayWidth() / 20);
                int y = (int) entity.getY() / (gameData.getDisplayHeight() / 20);

                // Check the type of each entity and update the gameWorld array accordingly
                if (entity instanceof Obstacle) {
                    mapArray[x][y] = new Obstacle();
                } else if (entity instanceof Player) {
                    mapArray[x][y] = new MapPlayer(entity.getX(), entity.getY());
                } else if (entity instanceof Enemy) {
                    mapArray[x][y] = new MapEnemy(entity.getX(), entity.getY());
                }
            }


            for (int i = 0; i < mapArray.length; i++) {
                for (int j = 0; j < mapArray[i].length; j++) {
                    nodes[i][j] = new Node(i, j, mapArray[i][j] instanceof Obstacle);
                    if (mapArray[i][j] instanceof MapEnemy) {
                        MapEnemy mapEnemy = (MapEnemy) mapArray[i][j];
                        double threshold = 10.0;
                        if (Math.abs(mapEnemy.getX() - enemy.getX()) <= threshold && Math.abs(mapEnemy.getY() - enemy.getY()) <= threshold) {
                            start = nodes[i][j];
                        }
                    } else if (mapArray[i][j] instanceof MapPlayer) {
                        end = nodes[i][j];
                    }
                }
            }

            if (start != null && end != null) {
                AStar aStar = new AStar();
                aStar.setNodes(nodes); // Pass the nodes array to the AStar instance
                Node[] path = aStar.aStar(mapArray, start, end);
                // Do something with the path

                if (path != null) {
                    int[][] pathArray = new int[path.length][2];
                    for (int i = 0; i < path.length; i++) {
                        pathArray[i][0] = path[i].i;
                        pathArray[i][1] = path[i].j;
                    }
                    enemy.setPath(new CommonPath(pathArray));

                }

            }

        }
    }
}


class Node {
    int i;
    int j;
    boolean isObstacle;
    Node parent;
    int g;
    int h;
    int f;

    public Node(int i, int j, boolean isObstacle) {
        this.i = i;
        this.j = j;
        this.isObstacle = isObstacle;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node node = (Node) obj;
        return i == node.i && j == node.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }


}

class AStar {
    private PriorityQueue<Node> open;
    private boolean[][] closed;
    private Node[][] nodes;

    public void setNodes(Node[][] nodes) {
        this.nodes = nodes;
    }


    public Node[] aStar(CommonMapObject[][] map, Node start, Node end) {
        open = new PriorityQueue<>((Node n1, Node n2) -> n1.f - n2.f);
        closed = new boolean[map.length][map[0].length];

        start.g = 0;
        start.h = heuristic(start, end);
        start.f = start.g + start.h;

        open.add(start);

        while (!open.isEmpty()) {
            Node current = open.poll();

            closed[current.i][current.j] = true;

            if (isEndNode(current, end)) {
                return constructPath(current, start);
            } else {
                for (Node neighbor : getNeighbors(current)) {

                    if (!closed[neighbor.i][neighbor.j] && !neighbor.isObstacle) {
                        boolean isInOpen = false;
                        for (Node node : open) {
                            if (node.i == neighbor.i && node.j == neighbor.j) {
                                isInOpen = true;
                                break;
                            }
                        }
                        if (!isInOpen) {
                            neighbor.parent = current; // Update the parent pointer when the node is first added to the open list
                            neighbor.g = current.g + 1;
                            neighbor.h = heuristic(neighbor, end);
                            neighbor.f = neighbor.g + neighbor.h;
                            open.add(neighbor);
                        } else {
                            if (neighbor.g > current.g + 1) {
                                neighbor.g = current.g + 1;
                                neighbor.f = neighbor.g + neighbor.h;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        for (int k = 0; k < 4; k++) {
            int newI = node.i + di[k], newJ = node.j + dj[k];

            if (isValidLocation(newI, newJ) && !nodes[newI][newJ].isObstacle) {
                Node neighbor = nodes[newI][newJ];
                if (neighbor.parent == null) { // Only update the parent pointer if the node has not been visited
                    neighbor.parent = node;
                }
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private boolean isValidLocation(int i, int j) {
        return i >= 0 && j >= 0 && i < nodes.length && j < nodes[0].length;
    }

    private boolean isEndNode(Node current, Node end) {
        return current.equals(end);
    }

    private int heuristic(Node current, Node end) {
        return Math.abs(current.i - end.i) + Math.abs(current.j - end.j);
    }

    private Node[] constructPath(Node node, Node start) {
        LinkedList<Node> path = new LinkedList<>();
        while (node.parent != null) {
            path.addFirst(node);
            node = node.parent;
            if (node.equals(start)) {
                break;
            }
        }
        return path.toArray(new Node[path.size()]);
    }
}