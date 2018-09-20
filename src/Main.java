import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }


    // two pointers

    //    167. Two Sum II - Input array is sorted
    //    https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        String a = "s";
        a.hashCode();
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }




    //    633. Sum of Square Numbers
    //    https://leetcode.com/problems/sum-of-square-numbers/description/
    public boolean judgeSquareSum(int c) {
        int i = 1;
        int j = (int) Math.sqrt(c);
        while (i < j) {
            int sum = i * i + j * j;
            if (sum == c) {
                return true;
            } else if (sum < c) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }



    //    345. Reverse Vowels of a String
    //    https://leetcode.com/problems/reverse-vowels-of-a-string/description/
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        char[] sArray = s.toCharArray();
        int i = 0;
        int j = sArray.length - 1;
        while (i < j) {
            while (!vowels.contains(sArray[i]) && i < j)
            {
                i++;
            }
            while (!vowels.contains(sArray[j]) && i < j)
            {
                j--;
            }
            char temp = sArray[i];
            sArray[i] = sArray[j];
            sArray[j] = temp;
            i++;
            j--;
        }
        return new String(sArray);
    }

    //    680. Valid Palindrome II
    //    https://leetcode.com/problems/valid-palindrome-ii/description/
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        if (i >= j) {
            return true;
        } else {
            return isPalindrome(s.substring(i + 1, j)) || isPalindrome(s.substring(i, j - 1));
        }
    }

    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }


    //    88. Merge Sorted Array
    //    https://leetcode.com/problems/merge-sorted-array/description/
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int merge = m + n - 1;
        while (i >= 0 || j >= 0) {
            if (i < 0) {
                nums1[merge--] = nums2[j--];
            } else if (j < 0) {
                nums1[merge--] = nums1[i--];
            } else if (nums1[i] > nums2[j]) {
                nums1[merge--] = nums1[i--];
            } else {
                nums1[merge--] = nums2[j--];
            }
        }
    }

    //    141. Linked List Cycle
    //    https://leetcode.com/problems/linked-list-cycle/description/
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    //    524. Longest Word in Dictionary through Deleting
    //    https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/
    public String findLongestWord(String s, List<String> d) {
        String result = "";
        for (String target : d) {
                if (target.length() > result.length()) {
                    int i =0, j = 0;
                    while (i < s.length() && j < target.length()) {
                        if (s.charAt(i) == target.charAt(j)) {
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                    if (j == target.length() && target.length() > result.length()) {
                        result = target;
                    }
                }
        }
        return result;
    }

    // sorting
}

