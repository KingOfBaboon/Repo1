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
            while (!vowels.contains(sArray[i]) && i < j) {
                i++;
            }
            while (!vowels.contains(sArray[j]) && i < j) {
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
                int i = 0, j = 0;
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


    //sorting
    //    215. Kth Largest Element in an Array
    //    https://leetcode.com/problems/kth-largest-element-in-an-array/description/
    public int findKthLargest(int[] nums, int k) {
        int targetIndex = nums.length - k;
        int l = 0;
        int h = nums.length - 1;
        int result = targetIndex;
        while (l < h) {
            int indexForFirst = partition(nums, l, h);
            if (indexForFirst == targetIndex) {
                result = indexForFirst;
                break;
            } else if (indexForFirst < targetIndex) {
                l = indexForFirst + 1;
            } else if (indexForFirst > targetIndex) {
                h = indexForFirst - 1;
            }
        }
        return nums[result];
    }

    private int partition(int[] nums, int l, int h) {
        int i = l + 1;
        int j = h;
        int p = nums[l];
        while (i < j) {
            while (i < h && nums[i] < p) {
                i++;
            }
            while (j > l && nums[j] > p) {
                j--;
            }
            if (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        if (nums[l] > nums[j]) {
            swap(nums, l, j);
        }
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //    347. Top K Frequent Elements
    //    https://leetcode.com/problems/top-k-frequent-elements/description/
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i : nums) {
            if (map.get(i) == null) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        TreeMap<Integer, List<Integer>> frequencyToNum = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (frequencyToNum.get(entry.getValue()) == null) {
                List<Integer> num = new ArrayList<>();
                num.add(entry.getKey());
                frequencyToNum.put(entry.getValue(), num);
            } else {
                frequencyToNum.get(entry.getValue()).add(entry.getKey());
            }
        }
        List<Integer> result = new ArrayList<>();
        while (k > 0) {
            List<Integer> list = frequencyToNum.pollLastEntry().getValue();
            result.addAll(list);
            k -= list.size();
        }
        return result;
    }

    //    451. Sort Characters By Frequency
    //    https://leetcode.com/problems/sort-characters-by-frequency/description/
    public String frequencySort(String s) {
        HashMap<Character, Integer> charToCout = new HashMap<>();
        for (char c : s.toCharArray()) {
            charToCout.put(c, charToCout.getOrDefault(c, 0) + 1);
        }
        TreeMap<Integer, List<Character>> countToChars = new TreeMap<>();
        for (Map.Entry<Character, Integer> entry : charToCout.entrySet()) {
            List<Character> list = countToChars.getOrDefault(entry.getValue(), new ArrayList<>());
            list.add(entry.getKey());
            countToChars.put(entry.getValue(), list);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!countToChars.isEmpty()) {
            Map.Entry<Integer, List<Character>> entry = countToChars.pollLastEntry();
            for (Character c : entry.getValue()) {
                for (int i = 0; i < entry.getKey(); i++) {
                    stringBuilder.append(c);
                }
            }
        }
        return stringBuilder.toString();
    }

    //    75. Sort Colors
    //    https://leetcode.com/problems/sort-colors/description/
    public void sortColors(int[] nums) {
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        while (j <= k) {
            switch (nums[j]) {
                case 0:
                    swap(nums, i++, j++);
                    break;
                case 1:
                    j++;
                    break;
                case 2:
                    swap(nums, j, k--);
                    break;
            }
        }
    }

    //    greedy
    //    455. Assign Cookies
    //    https://leetcode.com/problems/assign-cookies/description/
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i;
    }

    //    435. Non-overlapping Intervals
    //    https://leetcode.com/problems/non-overlapping-intervals/description/
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        int end = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < intervals.length; i++) {
            Interval interval = intervals[i];
            if (interval.start >= end) {
                count++;
                end = interval.end;
            }
        }
        return intervals.length - count;
    }

    //    406. Queue Reconstruction by Height
    //    https://leetcode.com/problems/queue-reconstruction-by-height/description/
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
        }
        return queue.toArray(new int[queue.size()][]);
    }

    //    763. Partition Labels
    //    https://leetcode.com/problems/partition-labels/description/
    public List<Integer> partitionLabels(String S) {
        Map<Character, Integer> charToLast = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            charToLast.put(S.charAt(i), i);
        }
        List<Integer> result = new ArrayList<>();
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        for (int i = 0; i < S.length(); i++) {
            start = Math.min(start, i);
            end = Math.max(end, charToLast.get(S.charAt(i)));
            if (i == end) {
                result.add(end - start + 1);
                start = Integer.MAX_VALUE;
                end = Integer.MIN_VALUE;
            }
        }
        return result;
    }

    //    605. Can Place Flowers
    //    https://leetcode.com/problems/can-place-flowers/description/
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            if (flowerbed[i] == 0) {
                boolean leftEmpty = (i - 1 < 0) || flowerbed[i - 1] == 0;
                boolean rightEmpty = (i + 1 >= flowerbed.length) || flowerbed[i + 1] == 0;
                if (leftEmpty && rightEmpty) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        return n <= 0;
    }

    //    392. Is Subsequence
    //    https://leetcode.com/problems/is-subsequence/description/
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    //    665. Non-decreasing Array
    //    https://leetcode.com/problems/non-decreasing-array/description/
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i - 1 >= 0 && nums[i - 1] > nums[i]) {
                count++;
                if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i - 1] = nums[i];
                }
            }
        }
        return count < 2;
    }

    //    122. Best Time to Buy and Sell Stock II
    //    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(prices[i] - prices[i - 1], 0);
        }
        return profit;
    }

    //binary search
    //    69. Sqrt(x)
    //    https://leetcode.com/problems/sqrtx/description/
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int l = 1;
        int h = x;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (x / m >= m && x / (m + 1) < m + 1) {
                return m;
            } else if (x / m < m) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    //    744. Find Smallest Letter Greater Than Target
    //    https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, h = n - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (letters[m] <= target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l < n ? letters[l] : letters[0];
    }

    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (m % 2 == 0) {
                if (nums[m] != nums[m - 1] && nums[m] != nums[m + 1]) {
                    return nums[m];
                } else if (nums[m] == nums[m - 1]) {
                    h = m;
                } else {
                    l = m + 1;
                }
            } else {
                if (nums[m] != nums[m - 1] && nums[m] != nums[m + 1]) {
                    return nums[m];
                } else if (nums[m] == nums[m + 1]) {
                    h = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }
        return nums[l];
    }

    //    278. First Bad Version
    //    https://leetcode.com/problems/first-bad-version/description/
    public int firstBadVersion(int n) {
        int l = 0;
        int h = n;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (isBadVersion(m) && !isBadVersion(m - 1)) {
                return m;
            } else if (isBadVersion(m) && isBadVersion(m - 1)) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private boolean isBadVersion(int version) {
        return true;
    }

    //    153. Find Minimum in Rotated Sorted Array
    //    https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
    public int findMin(int[] nums) {
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[l] < nums[m]) {
                l = m;
            } else {
                h = m;
            }
        }
        return nums[l + 1];
    }




}

