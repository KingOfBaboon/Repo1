import java.util.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

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

    public void printTree(){
        Queue<TreeNode> level  = new LinkedList<>();
        level.add(this);
        while(!level.isEmpty()){
            TreeNode node = level.poll();
            System.out.print(node.val + " ");
            if(node.left!= null)
                level.add(node.left);
            if(node.right!= null)
                level.add(node.right);
        }
    }

    public void displayTree()
    {
        Stack<TreeNode> globalStack = new Stack();
        globalStack.push(this);
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while(isRowEmpty==false)
        {

            Stack<TreeNode> localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<emptyLeaf; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false)
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
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
        System.out.println("****......................................................****");
    }
}

class TreeNodeTest {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(Arrays.asList(1, 2, 3));
        node.displayTree();
        return;
    }
}
