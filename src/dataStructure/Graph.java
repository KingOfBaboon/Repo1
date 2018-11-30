package dataStructure;

import java.util.*;

public class Graph {



    // 0 -1 - 2
    //    |   |
    //    3-5-6-7
    //    |     |
    //    4-----8


    // bfs traverse
    public List<Integer> bfsTraverse (List<List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }

    // dfs traverse
    public List<Integer> dfsTraverse(List<List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        dfs(graph, start, result, visited);
        return result;
    }

    private void dfs(List<List<Integer>> graph, int start, List<Integer> result, boolean[] visited) {
        if (!visited[start]) {
            visited[start] = true;
            result.add(start);
            for (int neighbor : graph.get(start)) {
                dfs(graph, neighbor, result, visited);
            }
        }
    }

    // detect dag circle

    //    207. Course Schedule
    //    https://leetcode.com/problems/course-schedule/description/

    // 207.1 dfs grey color
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfsHaveCircle(i, graph, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfsHaveCircle(int i, List<List<Integer>> graph, int[] visited) {
        if (visited[i] == 2) {
            return false;
        }
        if (visited[i] == 1) {
            return true;
        }
        visited[i] = 1;
        for (int child : graph.get(i)) {
            if (dfsHaveCircle(child, graph, visited)) {
                return true;
            }
        }
        visited[i] = 2;
        return false;
    }

    // bfs grep color : can't use bfs grey color to detect circle in dag

    // 207.2 we can use kahn's algorithm(topologic sort)
    public boolean canFinishKahn(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }
        return !kahnHaveCircle(graph);
    }

    private boolean kahnHaveCircle(List<List<Integer>> graph) {
        int[] indegree = new int[graph.size()];
        for (List<Integer> list : graph) {
            for (int node : list) {
                indegree[node]++;
            }
        }
        Queue<Integer> zeroIndegreeNodes = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                zeroIndegreeNodes.add(i);
            }
        }
        int removedCount = 0;
        while (!zeroIndegreeNodes.isEmpty()) {
            int removed = zeroIndegreeNodes.poll();
            removedCount++;
            for (int child : graph.get(removed)) {
                indegree[child]--;
                if (indegree[child] == 0) {
                    zeroIndegreeNodes.add(child);
                }
            }
        }
        return removedCount != graph.size();
    }

    // topologic sort

    //    210. Course Schedule II
    //    https://leetcode.com/problems/course-schedule-ii/description/

    // 210.1 dfs grey color stack
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }
        int[] visited = new int[numCourses];
        List<Integer> postOrder = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (dfsFindOrderHasCircle(i, graph, visited, postOrder)) {
                return new int[0];
            }
        }
        int[] result = new int[numCourses];
        int index = 0;
        while (!postOrder.isEmpty()) {
            result[index++] = postOrder.remove(0);
        }
        return result;
    }

    private boolean dfsFindOrderHasCircle(int i, List<List<Integer>> graph, int[] visited, List<Integer> postOrder) {
        if (visited[i] == 2) {
            return false;
        }
        if (visited[i] == 1) {
            return true;
        }
        visited[i] = 1;
        for (int child : graph.get(i)) {
            if (dfsFindOrderHasCircle(child, graph, visited, postOrder)) {
                return true;
            }
        }
        visited[i] = 2;
        postOrder.add(i);
        return false;
    }


    // 210.2 kahn's algorithm
    public int[] findOrderKahn(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            indegree[edge[1]]++;
        }
        Queue<Integer> zeroIndegree = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                zeroIndegree.add(i);
            }
        }
        int[] result = new int[numCourses];
        int index = numCourses - 1;
        while (!zeroIndegree.isEmpty()) {
            int current = zeroIndegree.poll();
            result[index--] = current;
            for (int child : graph.get(current)) {
                indegree[child]--;
                if (indegree[child] == 0) {
                    zeroIndegree.add(child);
                }
            }
        }
        if (index == -1) {
            return result;
        } else {
            return new int[0];
        }
    }


    // Shortest path problem
    // Dijkstra's algorithm
    // 0 -1 - 2
    //    |   |
    //    3-5-6-7
    //    |     |
    //    4-----8
    int[] dijkstra(int graph[][], int start) {
        int size = graph.length;
        int distance[] = new int[size];
        for (int i = 0; i < size; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        Set<Integer> visited = new HashSet<>();
        distance[start] = 0;
        visited.add(start);
        while (visited.size() < size) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < size; i++) {
                if (distance[i] < minDistance) {
                    minDistance = distance[i];
                    minIndex = i;
                }
            }
            visited.add(minIndex);
            for (int i = 0; i < size; i++) {
                if (!visited.contains(i) && graph[minIndex][i] != 0 &&
                        distance[minIndex] + graph[minIndex][i] < distance[i]) {
                    distance[i] = distance[minIndex] + graph[minIndex][i];
                }
            }
        }
        return distance;
    }

    // Minimum spanning tree
    // Prim algorithm

    //   (1)      (2)
    // 0-----1---------2
    //       |(9)      |(1)
    //       |         |
    //       3----5----6----7
    //    (3)|(2)  (1)  (1) |(1)
    //       |              |
    //       4--------------8
    //            (8)

    public List<List<Integer>> primSpanningTree(List<List<Integer>> graph, Map<List<Integer>, Integer> edgeDistances) {
        List<List<Integer>> edges = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> unVisited = new HashSet<>();
        visited.add(0);
        for (int i = 1; i < graph.size(); i++) {
            unVisited.add(i);
        }
        while (visited.size() < graph.size()) {
            int minEdgeDistance = Integer.MAX_VALUE;
            int fromNode = -1;
            int toNode = -1;
            for (int node : visited) {
                for (int child : graph.get(node)) {
                    if (unVisited.contains(child) && edgeDistances.get(Arrays.asList(node, child)) < minEdgeDistance) {
                        minEdgeDistance = edgeDistances.get(Arrays.asList(node, child));
                        fromNode = node;
                        toNode = child;
                    }
                }
            }
            edges.add(Arrays.asList(fromNode, toNode));
            visited.add(toNode);
            unVisited.remove(toNode);
        }
        return edges;
    }

    // Kruskal with union find
    public List<List<Integer>> kruskalSpanningTree(List<List<Integer>> graph, Map<List<Integer>, Integer> edgeDistances) {
        class UnionFind {
            int[] parents;
            int[] rank;

            UnionFind(int n){
                this.parents = new int[n];
                for (int i = 0; i < parents.length; i++) {
                    parents[i] = i;
                }
                this.rank = new int[n];
            }

            int find(int n) {
                if (parents[n] != n) {
                    parents[n] = find(parents[n]);
                }
                return parents[n];
            }

            boolean union(int i, int j) {
                int ri = find(i);
                int rj = find(j);
                if (ri == rj) {
                    return false;
                }
                if (rank[ri] > rank[rj]) {
                    parents[rj] = ri;
                } else if (rank[ri] < rank[rj]) {
                    parents[ri] = rj;
                } else {
                    parents[rj] = ri;
                    rank[ri]++;
                }
                return true;
            }
        }

        class Edge{
            int from;
            int to;
            int distance;

            public Edge(int from, int to, int distance) {
                this.from = from;
                this.to = to;
                this.distance = distance;
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<Edge> edgesWithDistance = new PriorityQueue<>((e1, e2) -> e1.distance - e2.distance);
        for (Map.Entry<List<Integer>, Integer> entry : edgeDistances.entrySet()) {
            int from = entry.getKey().get(0);
            int to = entry.getKey().get(1);
            int distance = entry.getValue();
            edgesWithDistance.add(new Edge(from, to, distance));
        }

        UnionFind uf = new UnionFind(graph.size());
        for (Edge edge : edgesWithDistance) {
            if (uf.find(edge.from) != uf.find(edge.to)) {
                result.add(Arrays.asList(edge.from, edge.to));
                uf.union(edge.from, edge.to);
            }
        }
        return result;
    }
}
