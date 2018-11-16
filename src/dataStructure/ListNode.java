package dataStructure;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    public ListNode(int[] array) {
        ListNode dummy = new ListNode(array[0]);
        ListNode node = dummy;
        for (int i : array) {
            node.next = new ListNode(i);
            node = node.next;
        }
        this.val = dummy.next.val;
        this.next = dummy.next.next;
    }

    public String printListNodes() {
        ListNode node = this;
        StringBuilder stringBuilder = new StringBuilder();
        while (node != null) {
            stringBuilder.append(node.val).append("->");
            node = node.next;
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

}