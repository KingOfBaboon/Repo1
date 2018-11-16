package interviews;


import dataStructure.TreeNode;

import java.util.*;

public class Niantic {

    //    1. serialize and deserialize tree

    // method 1: using array and index relationship, node with index i, left child is 2 * i + 1. right child is 2 * 2 + 2
    // BFS
    public String serializeTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        serializeTreeDFS(root, list, 0);
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }

    private void serializeTreeDFS(TreeNode root, ArrayList<Integer> list, int index) {
        if (root != null) {
            while (index > list.size() - 1) {
                list.add(null);
            }
            list.set(index, root.val);
            serializeTreeDFS(root.left, list, index * 2 + 1);
            serializeTreeDFS(root.right, list, index * 2 + 2);
        }
    }

    public TreeNode deserializeTree(String s) {
        String[] numberStrings = s.split(" ");
        ArrayList<TreeNode> nodes = new ArrayList<>();
        for (String string : numberStrings) {
            if (string.equals("null")) {
                nodes.add(null);
            } else {
                nodes.add(new TreeNode(Integer.valueOf(string)));
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode node = nodes.get(i);
            if (node != null) {
                if (2 * i + 1 < nodes.size()) {
                    node.left = nodes.get(2 * i + 1);
                }
                if (2 * i + 2 < nodes.size()) {
                    node.right = nodes.get(2 * i + 2);
                }
            }
        }
        return nodes.get(0);
    }

    //    method 2: recursive
    //    DFS
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    private String rserialize(TreeNode root, String str) {
        // Recursive serialization.
        if (root == null) {
            str += "null ";
        } else {
            str += String.valueOf(root.val) + " ";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public TreeNode deserialize(String data) {
        String[] data_array = data.split(" ");
        List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }

    private TreeNode rdeserialize(List<String> l) {
        // Recursive deserialization.
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);
        return root;
    }


    //    2. schedule concurrent jobs with start and end, get the min concurrent nodes
    static class Job {
        int start;
        int end;

        public Job(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 2.1 traverse all time points
    // O(n * t)
    public int getMinNodes(List<Job> jobs) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Job job : jobs) {
            min = Math.min(min, job.start);
            max = Math.max(max, job.end);
        }
        List<Integer> timePoints = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            int count = 0;
            for (Job job : jobs) {
                if (i >= job.start && i <= job.end) {
                    count++;
                }
            }
            timePoints.add(count);
        }
        int minNodes = Integer.MIN_VALUE;
        for (int i : timePoints) {
            minNodes = Math.max(minNodes, i);
        }
        return minNodes;
    }

    // 2.2
    // O(n log n) n jobs
    public int getMinNodes2(List<Job> jobs) {
        class Node {
            int end;
            public Node(int end) {
                this.end = end;
            }
        }

        PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
        for (Job job : jobs) {
            if (!nodes.isEmpty() && nodes.peek().end <= job.start) {
                Node out = nodes.poll();
                out.end = job.end;
                nodes.offer(out);
            } else {
                nodes.offer(new Node(job.end));
            }
        }
        return nodes.size();
    }





    }
