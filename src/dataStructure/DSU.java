package dataStructure;

public class DSU {
    int[] parent;
    int[] rank;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
        rank = new int[size];
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public boolean union(int i, int j) {
        int iRoot = find(i);
        int jRoot = find(j);
        if (iRoot == jRoot) {
            return false;
        }
        if (rank[iRoot] > rank[jRoot]) {
            parent[jRoot] = iRoot;
        } else if (rank[iRoot] < rank[jRoot]) {
            parent[iRoot] = jRoot;
        } else {
            parent[iRoot] = jRoot;
            rank[jRoot]++;
        }
        return true;
    }
}

