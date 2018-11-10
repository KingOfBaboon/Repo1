import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(ListNode next) {
        this.next = next;
    }

    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    ListNode(int[] array) {
        ListNode dummy = new ListNode(array[0]);
        ListNode node = dummy;
        for (int i : array) {
            ListNode newNode = new ListNode(i);
            node.next = newNode;
            node = node.next;
        }
        this.val = dummy.next.val;
        this.next = dummy.next.next;
    }

    public void printListNodes() {
        ListNode node = this;
        StringBuilder stringBuilder = new StringBuilder();
        while (node != null) {
            stringBuilder.append(node.val + "->");
            node = node.next;
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        System.out.println(stringBuilder.toString());
    }

}