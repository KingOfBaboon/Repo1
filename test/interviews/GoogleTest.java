package interviews;

import static org.junit.Assert.*;

import dataStructure.TreeNode;
import org.junit.Test;

public class GoogleTest {
    Google main = new Google();

    @Test
    public void getRedundantEdge() {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;
        b.left = c;
        Google.Edge edge = main.getRedundantEdge(a);
        assertEquals(edge.from, a);
        assertEquals(edge.to, c);

        b.left = a;
        edge = main.getRedundantEdge(a);
        assertEquals(edge.from, b);
        assertEquals(edge.to, a);

        TreeNode d = new TreeNode(4);
        b.left = d;
        c.left = d;
        edge = main.getRedundantEdge(a);
        assertEquals(edge.from, c);
        assertEquals(edge.to, d);
    }

    @Test
    public void getRedundantEdgeDSU() {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;
        b.left = c;
        Google.Edge edge = main.getRedundantEdgeDSU(a);
        assertEquals(edge.from, a);
        assertEquals(edge.to, c);

        b.left = a;
        edge = main.getRedundantEdgeDSU(a);
        assertEquals(edge.from, b);
        assertEquals(edge.to, a);

        TreeNode d = new TreeNode(4);
        b.left = d;
        c.left = d;
        edge = main.getRedundantEdgeDSU(a);
        assertEquals(edge.from, c);
        assertEquals(edge.to, d);
    }
}