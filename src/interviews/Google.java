package interviews;

import dataStructure.DSU;
import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Google {
    //    remove redundant edge in a tree
    class Edge {
        TreeNode from;
        TreeNode to;

        public Edge(TreeNode node, TreeNode child) {
            this.from = node;
            this.to = child;
        }
    }

    // DFS
    public Edge getRedundantEdge(TreeNode node) {
        Set<TreeNode> visitedSet = new HashSet<>();
        return dfsTraverse(node, visitedSet);
    }

    private Edge dfsTraverse(TreeNode node, Set<TreeNode> set) {
        set.add(node);
        for (TreeNode child : new TreeNode[]{node.left, node.right}) {
            if (child != null) {
                if (set.contains(child)) {
                    return new Edge(node, child);
                } else {
                    Edge edge = dfsTraverse(child, set);
                    if (edge != null) {
                        return edge;
                    }
                }
            }
        }
        return null;
    }

    // DSU
    public Edge getRedundantEdgeDSU(TreeNode node) {
        Set<TreeNode> allNodesSet = new HashSet<>();
        dfsTraverseDSU(node, allNodesSet);

        Set<TreeNode> visitedSet = new HashSet<>();
        List<Edge> allEdges = new ArrayList<>();
        dfsGetAllEdges(node, visitedSet, allEdges);

        List<TreeNode> allNodesList = new ArrayList<>(allNodesSet);
        DSU dsu = new DSU(allNodesSet.size());
        for (Edge edge : allEdges) {
            if (!dsu.union(allNodesList.indexOf(edge.from), allNodesList.indexOf(edge.to))) {
                return edge;
            }
        }
        return null;
    }

    private void dfsGetAllEdges(TreeNode node, Set<TreeNode> visitedSet, List<Edge> allEdges) {
        if (node != null && !visitedSet.contains(node)) {
            visitedSet.add(node);
            for (TreeNode child : new TreeNode[]{node.left, node.right}) {
                if (child != null) {
                    allEdges.add(new Edge(node, child));
                    dfsGetAllEdges(child, visitedSet, allEdges);
                }
            }
        }
    }

    private void dfsTraverseDSU(TreeNode node, Set<TreeNode> visitedSet) {
        if (node != null && !visitedSet.contains(node)) {
            visitedSet.add(node);
            dfsTraverseDSU(node.left, visitedSet);
            dfsTraverseDSU(node.right, visitedSet);
        }
    }

}
