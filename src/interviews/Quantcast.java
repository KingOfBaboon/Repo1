package interviews;

import dataStructure.DSU;
import java.util.*;

public class Quantcast {

    //    1. Python indentation validation
    //    rules:
    //    1.1 initial indentation is 0
    //    1.2 indentation increase after control statement
    //    1.3 indentation can only fall back to previous values
    //    1.4 skip comment
    //    1.5 should not end with control statement
    public boolean identification(String[] strs) {
        boolean lastLineIsControl = false;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(0);
        for (String s : strs) {
            if (isComment(s)) {
                continue;
            }
            if (!stack.isEmpty() && lastLineIsControl) {
                Integer peek = stack.peek();
                if (getIndentation(s) <= peek) {
                    return false;
                } else {
                    stack.offer(getIndentation(s));
                }
            } else {
                while (!stack.isEmpty() && !getIndentation(s).equals(stack.peek())) {
                    stack.pop();
                }
                if (stack.isEmpty() || !getIndentation(s).equals(stack.peek())) {
                    return false;
                }
            }
            lastLineIsControl = isControl(s);
        }
        return !lastLineIsControl;
    }

    private boolean isControl(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        return s.charAt(s.length() - 1) == ':';
    }

    private boolean isComment(String s) {
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            return c == '#';
        }
        return false;
    }

    private Integer getIndentation(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }


    //    2. Disjoint sets
    //    Given the following data structure called Node:
    //    Node
    //    Id1 (positive integer)
    //    Id2 (positive integer)
    //    Construct a method that will group a given list of nodes. The grouping rule is that nodes
    //    sharing an id belong in the same group.
    //
    //    As an example, given Nodes defined as N(id1, id2) :
    //    N(1,2), N(2,3), N(3,4), N(5,6), N(6,7), N(8,9)
    //    They will be grouped as such:
    //    Group 1: N(1,2) N(2,3) N(3,4)
    //    Group 2: N(5,6) N(6,7)
    //    Group 3: N(8,9)
    //
    //    Although in the example the ids are ordered, no ordering is guaranteed
    //    Nodes may have duplication in the given list, merge the duplicated nodes if they have the
    //    same id1 and the same id2

    static class Node {
        int id1;
        int id2;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node node = (Node) obj;
                return node.id1 == id1 && node.id2 == id2;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return id1 + id2;
        }

        public Node(int id1, int id2) {
            this.id1 = id1;
            this.id2 = id2;
        }
    }

    //    2.1 DFS traverse
    public String disjointSets(List<Node> nodes){
        nodes = new ArrayList<>(new HashSet<>(nodes));
        boolean[] visited = new boolean[nodes.size()];
        List<List<Node>> nodeGroups = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (!visited[i]) {
                List<Node> newGroup = new ArrayList<>();
                nodeGroups.add(dfsTraverse(nodes, i, visited, newGroup));
            }
        }
        for (List<Node> group : nodeGroups) {
            group.sort((o1, o2) -> o1.id1 - o2.id1);
        }
        nodeGroups.sort((o1, o2) -> o1.get(0).id1 - o2.get(0).id1);

        StringBuilder sb = new StringBuilder();
        for (List<Node> group : nodeGroups) {
            for (Node node : group) {
                sb.append(node.id1).append(",").append(node.id2).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<Node> dfsTraverse(List<Node> nodes, int index, boolean[] visited, List<Node> newGroup) {
        visited[index] = true;
        newGroup.add(nodes.get(index));
        for (int i = 0; i < nodes.size(); i++) {
            if (isConnected(nodes.get(index), nodes.get(i)) && !visited[i]) {
                dfsTraverse(nodes, i, visited, newGroup);
            }
        }
        return newGroup;
    }

    private boolean isConnected(Node node1, Node node2) {
        return node1.id1 == node2.id1
                || node1.id1 == node2.id2
                || node1.id2 == node2.id1
                || node1.id2 == node2.id2;
    }

    // 2.2 disjoint set union
    public String disjointSetsDSU(List<Node> nodes){
        nodes = new ArrayList<>(new HashSet<>(nodes));
        DSU dsu = new DSU(nodes.size());
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                if (isConnected(nodes.get(i), nodes.get(j))) {
                    dsu.union(i, j);
                }
            }
        }
        Map<Integer, List<Node>> map = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) {
            int setRoot = dsu.find(i);
            if (map.get(setRoot) == null) {
                map.put(setRoot, new ArrayList<>());
            }
            map.get(setRoot).add(nodes.get(i));
        }
        List<List<Node>> nodeGroups = new ArrayList<>(map.values());

        for (List<Node> group : nodeGroups) {
            group.sort((o1, o2) -> o1.id1 - o2.id1);
        }
        nodeGroups.sort((o1, o2) -> o1.get(0).id1 - o2.get(0).id1);

        StringBuilder sb = new StringBuilder();
        for (List<Node> group : nodeGroups) {
            for (Node node : group) {
                sb.append(node.id1).append(",").append(node.id2).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
