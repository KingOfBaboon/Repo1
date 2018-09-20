import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {
    Main main;

    @Before
    public void setMain() {
        this.main = new Main();
    }

    @Test
    public void main() {
    }

    @Test
    public void twoSum() {
        int[] numbers1 = new int[]{3, 6, 8, 32, 44};
        int target1 = 40;
        int[] numbers2 = new int[]{1, 6, 9, 12, 22};
        int target2 = 15;
        assertArrayEquals(new int[]{3,4}, main.twoSum(numbers1, target1));
        assertArrayEquals(new int[]{2,3}, main.twoSum(numbers2, target2));
    }

    @Test
    public void judgeSquareSum() {
        assertFalse(main.judgeSquareSum(1));
        assertFalse(main.judgeSquareSum(2));
        assertFalse(main.judgeSquareSum(3));
        assertTrue(main.judgeSquareSum(5));
        assertTrue(main.judgeSquareSum(25));
    }

    @Test
    public void reverseVowels() {
        assertEquals("holle", main.reverseVowels("hello"));
        assertEquals("leotcede", main.reverseVowels("leetcode"));
        assertEquals("aioeu", main.reverseVowels("ueoia"));
    }

    @Test
    public void validPalindrome() {
        assertEquals(true, main.validPalindrome("aba"));
        assertEquals(true, main.validPalindrome("abca"));
        assertEquals(false, main.validPalindrome("aakds"));
    }

    @Test
    public void isPalindrome() {
        assertTrue(main.isPalindrome("abbbba"));
        assertTrue(main.isPalindrome("abbccdccbba"));
        assertTrue(main.isPalindrome("a"));
        assertFalse(main.isPalindrome("ab"));
        assertFalse(main.isPalindrome("abcab"));
    }

    @Test
    public void merge() {
        main.merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
    }

    @Test
    public void hasCycle() {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode list2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        list2.next.next.next.next.next = list2.next;

        assertEquals(false, main.hasCycle(list1));
        assertEquals(true, main.hasCycle(list2));
    }

    @Test
    public void findLongestWord() {
        String s1 = "abpcplea";
        List<String> d1 = Arrays.asList("ale","apple","monkey","plea");
        String s2 = "abpcplea";
        List<String> d2 = Arrays.asList("a","b","c");
        assertEquals("apple", main.findLongestWord(s1, d1));
        assertEquals("a", main.findLongestWord(s2, d2));



    }
}