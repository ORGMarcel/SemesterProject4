package dk.sdu.mmmi.cbse.aisystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commonmap.Map;
import dk.sdu.mmmi.cbse.commonmapenemy.MapEnemy;
import dk.sdu.mmmi.cbse.commonmapobject.CommonMapObject;
import dk.sdu.mmmi.cbse.commonmapplayer.MapPlayer;
import dk.sdu.mmmi.cbse.commonobstacle.Obstacle;
import dk.sdu.mmmi.cbse.commonpath.CommonPath;


import java.util.*;




public class AiControlSystem implements IEntityProcessingService {

    @Override
//    public void process(GameData gameData, World world) {
//        for (Entity xMap : world.getEntities(Map.class)) {
//            Map map = (Map) xMap;
////            System.out.println(map.getMap());
//            CommonMapObject[][] mapArray = map.getMap();
//
//            for (int i = 0; i < mapArray.length; i++) {
//                for (int j = 0; j < mapArray[i].length; j++) {
//                    if(mapArray[i][j] instanceof MapPlayer){
//                        System.out.println("x is: "+mapArray[i][j].getX());
//                        System.out.println("y is: "+mapArray[i][j].getY());
//                    }
//                }
//            }
//
//        }
//
//    }
    public void process(GameData gameData, World world) {
//        // Check if it has run
//        if (hasRun) {
//            return;
//        }

        for (Entity xMap : world.getEntities(Map.class)) {
            Map map = (Map) xMap;
            CommonMapObject[][] mapArray = map.getMap();
//            System.out.println("Entered Map");

            Node[][] nodes = new Node[mapArray.length][mapArray[0].length];
            Node start = null, end = null;

            //TODO: Fix this. This only works when there is only one enemy
            Enemy enemy = new Enemy();

            for (Entity enemy1 : world.getEntities(Enemy.class)){
                enemy = (Enemy) enemy1;
            }

            for (int i = 0; i < mapArray.length; i++) {
                for (int j = 0; j < mapArray[i].length; j++) {
                    nodes[i][j] = new Node(i, j, mapArray[i][j] instanceof Obstacle);
                    if (mapArray[i][j] instanceof MapEnemy) {
                        start = nodes[i][j];
                    } else if (mapArray[i][j] instanceof MapPlayer) {
                        end = nodes[i][j];
                    }
                }
            }

            if (start != null && end != null) {
//                System.out.println("Just before AStar");
                AStar aStar = new AStar();
//                System.out.printf("Before setNodes");
                aStar.setNodes(nodes); // Pass the nodes array to the AStar instance
//                System.out.println("Just after AStar");
                Node[] path = aStar.aStar(mapArray, start, end);
                // Do something with the path
//                System.out.println("First path is: " + path[0].i + ", " + path[0].j);

                if(path != null){
                    int[][] pathArray = new int[path.length][2];
                    for (int i = 0; i < path.length; i++) {
                        pathArray[i][0] = path[i].i;
                        pathArray[i][1] = path[i].j;
//                        System.out.println();
                        System.out.println("i is: " + path[i].i + " j is: " + path[i].j);
                        System.out.println("new");
                    }
                    enemy.setPath(new CommonPath(pathArray));

                }

//                System.out.println("Last path is: " + path[path.length - 1].i + ", " + path[path.length - 1].j);
//                System.out.println("Path is: " + path);
            }
        }
//        hasRun = true;
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
//        System.out.println("Start Node: " + start.i + ", " + start.j);
//        System.out.println("End Node: " + end.i + ", " + end.j);
        open = new PriorityQueue<>((Node n1, Node n2) -> n1.f - n2.f);
        closed = new boolean[map.length][map[0].length];

        start.g = 0;
        start.h = heuristic(start, end);
        start.f = start.g + start.h;

        open.add(start);

        while (!open.isEmpty()) {
            Node current = open.poll();
//            System.out.println("Current Node: " + current.i + ", " + current.j);

            closed[current.i][current.j] = true;

            if (isEndNode(current, end)) {
                return constructPath(current, start);
            } else {
                for (Node neighbor : getNeighbors(current)) {
//                    System.out.println("Neighbor: " + neighbor.i + ", " + neighbor.j);

                    if (!closed[neighbor.i][neighbor.j] && !neighbor.isObstacle) {
                        boolean isInOpen = false;
                        for (Node node : open) {
                            if (node.i == neighbor.i && node.j == neighbor.j) {
                                isInOpen = true;
                                break;
                            }
                        }
                        if (!isInOpen) {
//                            System.out.println("Adding node to open list: " + neighbor.i + ", " + neighbor.j);

                            neighbor.parent = current; // Update the parent pointer when the node is first added to the open list
                            neighbor.g = current.g + 1;
                            neighbor.h = heuristic(neighbor, end);
                            neighbor.f = neighbor.g + neighbor.h;
                            open.add(neighbor);
                        } else {
                            if (neighbor.g > current.g + 1) {
//                                System.out.println("Updating node: " + neighbor.i + ", " + neighbor.j);

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
//        System.out.println("Constructing path");
        LinkedList<Node> path = new LinkedList<>();
        while (node.parent != null) {
            path.addFirst(node);
//            System.out.println("Added node to path: " + node.i + ", " + node.j);
            node = node.parent;
            if (node.equals(start)) {
                break;
            }
        }
        return path.toArray(new Node[path.size()]);
    }
}