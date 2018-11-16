package dataStructure;

import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
      val = x;
    }

    public TreeNode(List<Integer> array) {
        TreeNode[] nodes = new TreeNode[array.size() * 2 + 1];
        for (int i = array.size() - 1; i >= 0; i--) {
            if (array.get(i) != null) {
                nodes[i] = new TreeNode(array.get(i));
                nodes[i].left = nodes[2 * i + 1];
                nodes[i].right = nodes[2 * i + 2];
            }
        }
        val = nodes[0].val;
        left = nodes[0].left;
        right = nodes[0].right;
    }

    public static boolean isSameTree(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) {
            return n1 == null && n2 == null;
        }
        if (n1.val != n2.val) {
            return false;
        }
        return isSameTree(n1.left, n1.left) && isSameTree(n1.right, n2.right);
    }

    public void displayTree()
    {
        Stack<TreeNode> globalStack = new Stack<>();
        globalStack.push(this);
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while(!isRowEmpty)
        {

            Stack<TreeNode> localStack = new Stack<>();
            isRowEmpty = true;
            for(int j=0; j<emptyLeaf; j++)
                System.out.print(' ');
            while(!globalStack.isEmpty())
            {
                TreeNode temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.val);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if(temp.left != null ||temp.right != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<emptyLeaf*2-2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            emptyLeaf /= 2;
            while(!localStack.isEmpty())
                globalStack.push( localStack.pop() );
        }
        System.out.println("****......................................................****");
    }
}


