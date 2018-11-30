package dataStructure;

import org.junit.Test;

import java.util.*;

public class GraphTest {
    Graph main = new Graph();


    //   (1)      (2)
    // 0-----1---------2
    //       |(9)      |(1)
    //       |         |
    //       3----5----6----7
    //    (3)|(2)  (1)  (1) |(1)
    //       |              |
    //       4--------------8
    //            (8)
    List<List<Integer>> initialGraph() {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).addAll(Arrays.asList(1));
        graph.get(1).addAll(Arrays.asList(0, 2, 3));
        graph.get(2).addAll(Arrays.asList(1, 6));
        graph.get(3).addAll(Arrays.asList(1, 4, 5));
        graph.get(4).addAll(Arrays.asList(3, 8));
        graph.get(5).addAll(Arrays.asList(3, 6));
        graph.get(6).addAll(Arrays.asList(2, 5, 7));
        graph.get(7).addAll(Arrays.asList(6, 8));
        graph.get(8).addAll(Arrays.asList(4, 7));
        return graph;
    }

    Map<List<Integer>, Integer> initialEdges() {
        Map<List<Integer>, Integer> edgeDistance = new HashMap<>();
        edgeDistance.put(Arrays.asList(0, 1), 1);
        edgeDistance.put(Arrays.asList(1, 0), 1);

        edgeDistance.put(Arrays.asList(1, 2), 2);
        edgeDistance.put(Arrays.asList(2, 1), 2);

        edgeDistance.put(Arrays.asList(1, 3), 9);
        edgeDistance.put(Arrays.asList(3, 1), 9);

        edgeDistance.put(Arrays.asList(2, 6), 1);
        edgeDistance.put(Arrays.asList(6, 2), 1);

        edgeDistance.put(Arrays.asList(3, 5), 2);
        edgeDistance.put(Arrays.asList(5, 3), 2);

        edgeDistance.put(Arrays.asList(5, 6), 1);
        edgeDistance.put(Arrays.asList(6, 5), 1);

        edgeDistance.put(Arrays.asList(6, 7), 1);
        edgeDistance.put(Arrays.asList(7, 6), 1);

        edgeDistance.put(Arrays.asList(3, 4), 3);
        edgeDistance.put(Arrays.asList(4, 3), 3);

        edgeDistance.put(Arrays.asList(7, 8), 1);
        edgeDistance.put(Arrays.asList(8, 7), 1);

        edgeDistance.put(Arrays.asList(4, 8), 8);
        edgeDistance.put(Arrays.asList(8, 4), 8);
        return edgeDistance;
    }


    @Test
    public void bfsTraverse() {
        System.out.println(main.bfsTraverse(initialGraph(), 0));

    }

    @Test
    public void dfsTraverse() {
        System.out.println(main.dfsTraverse(initialGraph(), 8));

    }

    @Test
    public void canFinish() {
        boolean a = main.canFinishDFS(2, new int[][]{{1, 0}});
        System.out.println(a);
    }

    @Test
    public void canFinishDFS() {
    }

    @Test
    public void canFinishBFS() {
    }

    @Test
    public void canFinishKahn() {
    }

    @Test
    public void findOrder() {
    }

    @Test
    public void findOrderKahn() {
    }

    // 0 -1 - 2
    //    |   |
    //    3-5-6-7
    //    |     |
    //    4-----8

    @Test
    public void getShortestPath() {

    }

    @Test
    public void dijkstra() {
    }

    @Test
    public void primSpanningTree() {
        main.primSpanningTree(initialGraph(), initialEdges());
    }

    @Test
    public void kruskalSpanningTree() {
        main.kruskalSpanningTree(initialGraph(), initialEdges());
    }
}