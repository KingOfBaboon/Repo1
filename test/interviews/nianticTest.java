package interviews;

import dataStructure.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class nianticTest {

    Niantic main = new Niantic();


    // java tree serialize
    /*
     *               5
     *      3                    7
     *  n        4           6       n
     * n n      n n         n  9
     *
     *
     *
     *
     *
     * */

    @Test
    public void serializeTree() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 7, null, 4, 6, null, null, null, null, null, null, 9));
        TreeNode node = new TreeNode(list);
        String s = main.serializeTree(node);
        assertEquals(s, "5 3 7 null 4 6 null null null null null null 9 ");
    }

    @Test
    public void deserializeTree() {
        TreeNode node1 = main.deserializeTree("5 3 7 null 4 6 null null null null null null 9 ");
        TreeNode node2 = new TreeNode(new ArrayList<>(Arrays.asList(5, 3, 7, null, 4, 6, null, null, null, null, null, null, 9)));
        assertEquals(true, TreeNode.isSameTree(node1, node2));
    }

    @Test
    public void serialize() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 7, null, 4, 6, null, null, null, null, null, null, 9));
        TreeNode node = new TreeNode(list);
        String s = main.serialize(node);
        assertEquals(s, "5 3 null 4 null null 7 6 null 9 null null null ");
    }

    @Test
    public void deserialize() {
        TreeNode node1 = main.deserialize("5 3 null 4 null null 7 6 null 9 null null null ");
        TreeNode node2 = new TreeNode(new ArrayList<>(Arrays.asList(5, 3, 7, null, 4, 6, null, null, null, null, null, null, 9)));
        assertEquals(true, TreeNode.isSameTree(node1, node2));
    }

    @Test
    public void testBFSandDFS() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n2.left = n3;
        n3.left = n4;
        n4.left = n5;
        System.out.println(main.serializeTree(n1));
        System.out.println(main.serialize(n1));

    }



    //    1        5
    //     2     4
    //       3          7
    //                       8 9
    @Test
    public void getMinNodes() {
        List<Niantic.Job> jobs = new ArrayList<>(Arrays.asList(
                new Niantic.Job(1, 5),
                new Niantic.Job(2, 4),
                new Niantic.Job(3, 7),
                new Niantic.Job(8, 9)
        ));
        assertEquals(3, main.getMinNodes(jobs));
    }

    @Test
    public void getMinNodes2() {
        List<Niantic.Job> jobs = new ArrayList<>(Arrays.asList(
                new Niantic.Job(1, 5),
                new Niantic.Job(2, 4),
                new Niantic.Job(3, 7),
                new Niantic.Job(8, 9)
        ));
        assertEquals(3, main.getMinNodes2(jobs));
    }
}