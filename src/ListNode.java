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

}
