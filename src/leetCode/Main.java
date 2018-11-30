package leetCode;


import dataStructure.*;

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
    public boolean judgeSquareSum ( int c){
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
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
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
            if (target.length() > result.length() || (target.length() == result.length() && target.compareTo(result) < 0) ) {
                int i = 0, j = 0;
                while (i < s.length() && j < target.length()) {
                    if (s.charAt(i) == target.charAt(j)) {
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                if (j == target.length()) {
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
        while (l < h) {
            int indexForFirst = partition(nums, l, h);
            if (indexForFirst == targetIndex) {
                break;
            } else if (indexForFirst < targetIndex) {
                l = indexForFirst + 1;
            } else {
                h = indexForFirst - 1;
            }
        }
        return nums[targetIndex];
    }

    private int partition(int[] nums, int l, int h) {
        int i = l;
        int j = h + 1;
        while (true) {
            while (++i < h && nums[i] < nums[l]);
            while (--j > l && nums[j] > nums[l]);
            if (i < j) {
                swap(nums, i, j);
            } else {
                break;
            }
        }
        swap(nums, l, j);
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
        HashMap<Integer, Integer> map = new HashMap<>();
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
    public static class Interval {
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
        Arrays.sort(intervals, (o1, o2) -> o1.end - o2.end);
        int end = Integer.MIN_VALUE;
        int count = 0;
        for (Interval interval : intervals) {
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
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
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

    //    392. Is SubSequence
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

    //    540. Single Element in a Sorted Array
    //    https://leetcode.com/problems/single-element-in-a-sorted-array/description/
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
        return version == 1;
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

    //divide and conquer
    //    241. Different Ways to Add Parentheses
    //    https://leetcode.com/problems/different-ways-to-add-parentheses/description/
    Map<String, List<Integer>> diffWaysToComputeCache = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        if (diffWaysToComputeCache.containsKey(input)) {
            return diffWaysToComputeCache.get(input);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == '+'
                    || currentChar == '-'
                    || currentChar == '*') {
                List<Integer> part1 = diffWaysToCompute(input.substring(0, i));
                List<Integer> part2 = diffWaysToCompute(input.substring(i + 1));
                for (Integer i1 : part1) {
                    for (Integer i2 : part2) {
                        switch (currentChar) {
                            case '+':
                                result.add(i1 + i2);
                                break;
                            case '-':
                                result.add(i1 - i2);
                                break;
                            case '*':
                                result.add(i1 * i2);
                                break;
                        }
                    }
                }
            }
        }
        if (result.size() == 0) {
            result.add(Integer.valueOf(input));
        }
        diffWaysToComputeCache.put(input, result);
        return result;
    }

    //BFS
    //    279. Perfect Squares;
    //    https://leetcode.com/problems/perfect-squares/description/
    public int numSquares_bfs(int n) {
        int level = 0;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        while (!q.isEmpty()) {
            level++;
            int currentSize = q.size();
            while (currentSize-- > 0 && !q.isEmpty()) {
                int currentInt = q.poll();
                if (!visited[currentInt]) {
                    visited[currentInt] = true;
                    for (int j = (int) Math.sqrt(currentInt); j > 0; j--) {
                        if (currentInt - j * j == 0) {
                            return level;
                        } else {
                            q.add(currentInt - j * j);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public int numSquares_dp(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    //    127. Word Ladder
    //    https://leetcode.com/problems/word-ladder/description/
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int level = 1;
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        while (!q.isEmpty()) {
            level++;
            int size = q.size();
            while (size-- > 0 && !q.isEmpty()) {
                String current = q.poll();
                Iterator<String> iterator = wordList.iterator();
                while (iterator.hasNext()) {
                    String word = iterator.next();
                    if (canTransfer(current, word)) {
                        if (word.equals(endWord)) {
                            return level;
                        } else {
                            q.add(word);
                            iterator.remove();
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean canTransfer(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < a.length() && count < 2; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }

    // DFS
    //    695. Max Area of Island
    //    https://leetcode.com/problems/max-area-of-island/description/
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                max = Math.max(max, connectedArea(grid, visited, x, y));
            }
        }
        return max;
    }

    private int connectedArea(int[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] == 0) {
            return 0;
        } else {
            visited[x][y] = true;
            return 1
                    + connectedArea(grid, visited, x - 1, y)
                    + connectedArea(grid, visited, x + 1, y)
                    + connectedArea(grid, visited, x, y - 1)
                    + connectedArea(grid, visited, x, y + 1);
        }
    }

    //    200. Number of Islands
    //    https://leetcode.com/problems/number-of-islands/description/
    public int numIslands(char[][] grid) {
        int num = 0;
        if (grid.length < 1 || grid[0].length < 1) {
            return num;
        }
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                num += visitIsland(grid, visited, x, y);
            }
        }
        return num;
    }

    private int visitIsland(char[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] == '0') {
            return 0;
        } else {
            visited[x][y] = true;
            visitIsland(grid, visited, x - 1, y);
            visitIsland(grid, visited, x + 1, y);
            visitIsland(grid, visited, x, y - 1);
            visitIsland(grid, visited, x, y + 1);
            return 1;
        }
    }

    //    547. Friend Circles
    //    https://leetcode.com/problems/friend-circles/
    public int findCircleNum(int[][] M) {
        int result = 0;
        int num = M.length;
        boolean[] visited = new boolean[num];
        for (int i = 0; i < num; i++) {
            if (!visited[i]) {
                result++;
                findCircleNumDFS(M, visited, i);
            }
        }
        return result;
    }

    private void findCircleNumDFS(int[][] M, boolean[] visited, int student) {
        visited[student] = true;
        int[] relation = M[student];
        for (int i = 0; i < relation.length; i++) {
            if (relation[i] == 1 && !visited[i]) {
                findCircleNumDFS(M, visited, i);
            }
        }
    }

    //    130. Surrounded Regions
    //    https://leetcode.com/problems/surrounded-regions/
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int rows = board.length;
        int columns = board[0].length;
        boolean[][] visited = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || i == rows - 1 || j == 0 || j == columns - 1 && board[i][j] == 'O') {
                    solveDFS(board, visited, i, j, rows, columns);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void solveDFS(char[][] board, boolean[][] visited, int i, int j, int rows, int columns) {
        if (i < 0 || i >= rows || j < 0 || j >= columns || visited[i][j] || board[i][j] == 'X') {
        } else {
            visited[i][j] = true;
            solveDFS(board, visited, i - 1, j, rows, columns);
            solveDFS(board, visited, i + 1, j, rows, columns);
            solveDFS(board, visited, i, j - 1, rows, columns);
            solveDFS(board, visited, i, j + 1, rows, columns);

        }
    }

    //    417. Pacific Atlantic Water Flow
    //    https://leetcode.com/problems/pacific-atlantic-water-flow/
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        List<int[]> result = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        boolean[][] reachPacific = new boolean[rows][columns];
        boolean[][] reachAtlantic = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || j == 0) {
                    pacificAtlanticDFS(matrix, i, j, reachPacific, 0, rows, columns);
                }
                if (i == rows - 1 || j == columns - 1) {
                    pacificAtlanticDFS(matrix, i, j, reachAtlantic, 0, rows, columns);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (reachPacific[i][j] && reachAtlantic[i][j]) {
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }

    private void pacificAtlanticDFS(int[][] matrix, int i, int j, boolean[][] reachable, int targetHeight, int rows, int columns) {
        if (i < 0 || j < 0 || i >= rows || j >= columns || reachable[i][j] || matrix[i][j] < targetHeight) {
            return;
        }
        reachable[i][j] = true;
        pacificAtlanticDFS(matrix, i - 1, j, reachable, matrix[i][j], rows, columns);
        pacificAtlanticDFS(matrix, i + 1, j, reachable, matrix[i][j], rows, columns);
        pacificAtlanticDFS(matrix, i, j - 1, reachable, matrix[i][j], rows, columns);
        pacificAtlanticDFS(matrix, i, j + 1, reachable, matrix[i][j], rows, columns);
    }

    //    Backtracking
    //    17. Letter Combinations of a Phone Number
    //    https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        Map<Integer, char[]> map = new HashMap<>();
        map.put(2, new char[]{'a', 'b', 'c'});
        map.put(3, new char[]{'d', 'e', 'f'});
        map.put(4, new char[]{'g', 'h', 'i'});
        map.put(5, new char[]{'j', 'k', 'l'});
        map.put(6, new char[]{'m', 'n', 'o'});
        map.put(7, new char[]{'p', 'q', 'r', 's'});
        map.put(8, new char[]{'t', 'u', 'v'});
        map.put(9, new char[]{'w', 'x', 'y', 'z'});

        Queue<String> stringList = new LinkedList<>();
        stringList.add("");
        for (char c : digits.toCharArray()) {
            int i = Character.getNumericValue(c);

            int size = stringList.size();
            while (size-- > 0) {
                String s = stringList.poll();
                for (char singleChar : map.get(i)) {
                    stringList.add(s + singleChar);
                }
            }
        }
        return new ArrayList<>(stringList);
    }

    //    93. Restore IP Addresses
    //    https://leetcode.com/problems/restore-ip-addresses/description/
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        List<String> tempParts = new ArrayList<>();
        restore(s, result, tempParts, 0);
        return result;
    }

    private void restore(String s, List<String> result, List<String> currentParts, int start) {
        if (currentParts.size() >= 4 || start >= s.length()) {
            if (currentParts.size() == 4 && start == s.length()) {
                result.add(String.join(".", currentParts));
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            int end = start + i;
            if (end <= s.length()) {
                String part = s.substring(start, end);
                if (isValidPart(part)) {
                    currentParts.add(part);
                    restore(s, result, currentParts, end);
                    currentParts.remove(currentParts.size() - 1);
                }
            }
        }
    }

    private boolean isValidPart(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        if (s.charAt(0) == '0') {
            return s.equals("0");
        }
        int i = Integer.valueOf(s);
        return i > 0 && i < 256;
    }

    //    79. Word Search
    //    https://leetcode.com/problems/word-search/
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0 || word.length() == 0) {
            return false;
        }
        int rows = board.length;
        int columns = board[0].length;
        boolean[][] visited = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (existDFS(visited, board, word, 0, i, j, rows, columns)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existDFS(boolean[][] visited, char[][] baord, String word, int index, int i, int j, int rows, int columns) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= rows || j >= columns || visited[i][j] || baord[i][j] != word.charAt(index)) {
            return false;
        }
        visited[i][j] = true;

        boolean exist = existDFS(visited, baord, word, index + 1, i - 1, j, rows, columns)
                || existDFS(visited, baord, word, index + 1, i + 1, j, rows, columns)
                || existDFS(visited, baord, word, index + 1, i, j - 1, rows, columns)
                || existDFS(visited, baord, word, index + 1, i, j + 1, rows, columns);
        if (exist) {
            return true;
        } else {
            visited[i][j] = false;
            return false;
        }
    }

    //    257. Binary Tree Paths
    //    https://leetcode.com/problems/binary-tree-paths/
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        String currentPath = "";
        binaryTreePathsDFS(root, result, currentPath);
        return result;
    }

    private void binaryTreePathsDFS(TreeNode node, List<String> result, String currentPath) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                currentPath += node.val;
                result.add(currentPath);
            } else {
                binaryTreePathsDFS(node.left, result, currentPath + node.val + "->");
                binaryTreePathsDFS(node.right, result, currentPath + node.val + "->");
            }
        }
    }

    //    46. Permutations
    //    https://leetcode.com/problems/permutations/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        } else if (nums.length == 1) {
            result.add(Arrays.asList(nums[0]));
        } else {
            List<List<Integer>> restPermte = permute(Arrays.copyOfRange(nums, 1, nums.length));
            for (List<Integer> list : restPermte) {
                for (int i = 0; i <= list.size(); i++) {
                    List<Integer> listPlus = new ArrayList<>(list);
                    listPlus.add(i, nums[0]);
                    result.add(listPlus);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> permuteBacktrack(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        List<Integer> currentPath = new ArrayList<>();
        permuteBacktrackDFS(nums, result, visited, currentPath);
        return result;
    }

    private void permuteBacktrackDFS(int[] nums, List<List<Integer>> result, boolean[] visited, List<Integer> currentPath) {
        if (currentPath.size() == nums.length) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            currentPath.add(nums[i]);
            permuteBacktrackDFS(nums, result, visited, currentPath);
            currentPath.remove(currentPath.size() - 1);
            visited[i] = false;
        }
    }

    //    47. Permutations II
    //    https://leetcode.com/problems/permutations-ii/
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        List<Integer> currentPath = new ArrayList<>();
        Arrays.sort(nums);
        permuteUniqueBacktrackDFS(nums, result, visited, currentPath);
        return result;
    }

    private void permuteUniqueBacktrackDFS(int[] nums, List<List<Integer>> result, boolean[] visited, List<Integer> currentPath) {
        if (currentPath.size() == nums.length) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            visited[i] = true;
            currentPath.add(nums[i]);
            permuteUniqueBacktrackDFS(nums, result, visited, currentPath);
            currentPath.remove(currentPath.size() - 1);
            visited[i] = false;
        }
    }

    //    77. Combinations
    //    https://leetcode.com/problems/combinations/description/
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        combineBacktracing(result, currentPath, n, k, 1);
        return result;
    }

    private void combineBacktracing(List<List<Integer>> result, List<Integer> currentPath, int n, int k, int start) {
        if (currentPath.size() == k) {
            result.add(new ArrayList<>(currentPath));
        }
        for (int i = start; i <= n; i++) {
            currentPath.add(i);
            combineBacktracing(result, currentPath, n, k, i + 1);
            currentPath.remove(currentPath.size() - 1);
        }
    }

    //    39. Combination Sum
    //    https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumBacktracing(candidates, result, currentPath, 0, target);
        return result;
    }

    private void combinationSumBacktracing(int[] candidates, List<List<Integer>> result, List<Integer> currentPath, int start, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                currentPath.add(candidates[i]);
                combinationSumBacktracing(candidates, result, currentPath, i, target - candidates[i]);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    //    40. Combination Sum II
    //    https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        combinationSum2BackTracing(candidates, result, currentPath, target, 0);
        return result;
    }

    private void combinationSum2BackTracing(int[] candidates, List<List<Integer>> result, List<Integer> currentPath, int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                currentPath.add(candidates[i]);
                combinationSum2BackTracing(candidates, result, currentPath, target - candidates[i], i + 1);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    //    216. Combination Sum III
    //    https://leetcode.com/problems/combination-sum-iii/
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        combinationSum3Backtracing(k, n, result, currentPath, 1);
        return result;
    }

    private void combinationSum3Backtracing(int k, int n, List<List<Integer>> result, List<Integer> currentPath, int start) {
        if (currentPath.size() == k && n == 0) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        if (currentPath.size() < k && n > 0) {
            for (int i = start; i <= 9; i++) {
                currentPath.add(i);
                combinationSum3Backtracing(k, n - i, result, currentPath, i + 1);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    //    78. Subsets
    //    https://leetcode.com/problems/subsets/
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        subsetsBacktracing(nums, result, currentPath, 0);
        return result;
    }

    private void subsetsBacktracing(int[] nums, List<List<Integer>> result, List<Integer> currentPath, int start) {
        result.add(new ArrayList<>(currentPath));
        for (int i = start; i < nums.length; i++) {
            currentPath.add(nums[i]);
            subsetsBacktracing(nums, result, currentPath, i + 1);
            currentPath.remove(currentPath.size() - 1);
        }
    }

    //    90. Subsets II
    //    https://leetcode.com/problems/subsets-ii/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        subsetsWithDupBacktracing(nums, result, currentPath, 0);
        return result;
    }

    private void subsetsWithDupBacktracing(int[] nums, List<List<Integer>> result, List<Integer> currentPath, int start) {
        result.add(new ArrayList<>(currentPath));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            currentPath.add(nums[i]);
            subsetsWithDupBacktracing(nums, result, currentPath, i + 1);
            currentPath.remove(currentPath.size() - 1);
        }
    }

    //    131. Palindrome Partitioning
    //    https://leetcode.com/problems/palindrome-partitioning/
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        partitionBacktracing(s, result, currentPath);
        return result;
    }

    private void partitionBacktracing(String s, List<List<String>> result, List<String> currentPath) {
        if (s.length() == 0) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s.substring(0, i + 1))) {
                currentPath.add(s.substring(0, i + 1));
                partitionBacktracing(s.substring(i + 1), result, currentPath);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    //    37. Sudoku Solver
    //    https://leetcode.com/problems/sudoku-solver/
    public void solveSudoku(char[][] board) {
        int blank = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    blank++;
                }
            }
        }
        solveSudokuBacktracing(board, blank);
    }

    private boolean solveSudokuBacktracing(char[][] board, int blank) {
        if (blank == 0) {
            return true;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (int n = 1; n <= 9; n++) {
                        if (suitSudoku(board, i, j, n)) {
                            board[i][j] = Integer.toString(n).charAt(0);
                            blank--;
                            if (solveSudokuBacktracing(board, blank)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                                blank++;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    private boolean suitSudoku(char[][] board, int i, int j, int n) {
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == Integer.toString(n).charAt(0) || board[k][j] == Integer.toString(n).charAt(0)) {
                return false;
            }
        }
        for (int p = i / 3 * 3; p < i / 3 * 3 + 3; p++) {
            for (int q = j / 3 * 3; q < j / 3 * 3 + 3; q++) {
                if (board[p][q] == Integer.toString(n).charAt(0)) {
                    return false;
                }
            }
        }
        return true;
    }

    //    51. N-Queens
    //    https://leetcode.com/problems/n-queens/
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        int[][] occupied = new int[n][n];
        int queenCount = 0;
        solveNQueensBacktracing(n, queenCount, result, board, occupied);
        System.out.println(result.size());
        return result;
    }

    private void solveNQueensBacktracing(int n, int queenCount, List<List<String>> result, char[][] board, int[][] occupied) {
        if (queenCount == n) {
            List<String> singleSolution = new ArrayList<>();
            for (char[] line : board) {
                singleSolution.add(new String(line));
            }
            result.add(singleSolution);
        }
        if (queenCount < n) {
            for (int j = 0; j < n; j++) {
                if (occupied[queenCount][j] == 0) {
                    board[queenCount][j] = 'Q';
                    solveNQueensModifyOccupied(occupied, queenCount, j, n, 1);
                    solveNQueensBacktracing(n, queenCount + 1, result, board, occupied);
                    board[queenCount][j] = '.';
                    solveNQueensModifyOccupied(occupied, queenCount, j, n, -1);
                }
            }
        }
    }

    private void solveNQueensModifyOccupied(int[][] occupied, int i, int j, int n, int c) {
        for (int k = 0; k < n; k++) {
            occupied[i][k] += c;
            occupied[k][j] += c;
            if (i - k >= 0 && i - k < n && j - k >= 0 && j - k < n) {
                occupied[i - k][j - k] += c;
            }
            if (i + k >= 0 && i + k < n && j + k >= 0 && j + k < n) {
                occupied[i + k][j + k] += c;
            }
            if (i + k >= 0 && i + k < n && j - k >= 0 && j - k < n) {
                occupied[i + k][j - k] += c;
            }
            if (i - k >= 0 && i - k < n && j + k >= 0 && j + k < n) {
                occupied[i - k][j + k] += c;
            }
        }
    }


    //    DP
    //    70. Climbing Stairs
    //    https://leetcode.com/problems/climbing-stairs/description/
    public int climbStairs(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //    198. House Robber
    //    https://leetcode.com/problems/house-robber/description/
    public int rob(int[] nums) {
        switch (nums.length) {
            case 0:
                return 0;
            case 1:
                return nums[0];
            case 2:
                return Math.max(nums[0], nums[1]);
        }

        int[] max = new int[nums.length];
        max[0] = nums[0];
        max[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            max[i] = Math.max(max[i - 2] + nums[i], max[i - 1]);
        }
        return max[nums.length - 1];
    }

    //    213. House Robber II
    //    https://leetcode.com/problems/house-robber-ii/
    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] nums1 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int[] nums2 = Arrays.copyOfRange(nums, 1, nums.length);
        return Math.max(rob(nums1), rob(nums2));
    }

    //    64. Minimum Path Sum
    //    https://leetcode.com/problems/minimum-path-sum/
    public int minPathSum(int[][] grid) {
        int[][] min = new int[grid.length][grid[0].length];
        min[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int up = i == 0 ? Integer.MAX_VALUE : min[i - 1][j];
                int left = j == 0 ? Integer.MAX_VALUE : min[i][j - 1];
                min[i][j] = Math.min(up, left) + grid[i][j];
            }
        }
        return min[grid.length - 1][grid[0].length - 1];
    }

    //    62. Unique Paths
    //    https://leetcode.com/problems/unique-paths/
    public int uniquePaths(int m, int n) {
        int[][] unique = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    unique[i][j] = 1;
                    continue;
                }
                int up = i == 0 ? 0 : unique[i - 1][j];
                int left = j == 0 ? 0 : unique[i][j - 1];
                unique[i][j] = up + left;
            }
        }
        return unique[m - 1][n - 1];
    }

    //    303. Range Sum Query - Immutable
    //    https://leetcode.com/problems/range-sum-query-immutable/
    class NumArray {

        private int[] sum;
        private int[] nums;

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                this.nums = nums;
                sum = new int[nums.length];
                sum[0] = nums[0];
                for (int i  = 1; i < nums.length; i++) {
                    sum[i] = sum[i - 1] + nums[i];
                }
            }
        }

        public int sumRange(int i, int j) {
            return sum[j] - sum[i] + nums[i];
        }
    }

    //    413. Arithmetic Slices
    //    https://leetcode.com/problems/arithmetic-slices/
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int[] dp = new int[A.length];
        dp[0] = dp[1] = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int result = 0;
        for (int i : dp) {
            result += i;
        }
        return result;
    }

    //    343. Integer Break
    //    https://leetcode.com/problems/integer-break/
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        return dp[n];
    }

    //    91. Decode Ways
    //    https://leetcode.com/problems/decode-ways/
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            String oneDigit = String.valueOf(s.charAt(i));
            String twoDigit = s.substring(i - 1, i + 1);
            if (validDigits(oneDigit)) {
                dp[i] += dp[i - 1];
            }
            if (validDigits(twoDigit)) {
                dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
            }

        }
        return dp[dp.length - 1];
    }

    private boolean validDigits(String s) {
        if (s.charAt(0) == '0') {
            return false;
        }
        int i = Integer.valueOf(s);
        return i > 0 && i <= 26;
    }

    //    300. Longest Increasing Subsequence
    //    https://leetcode.com/problems/longest-increasing-subsequence/
    public int lengthOfLIS_DP(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    public int lengthOfLIS_Tail(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for (int num : nums) {
            int index = lengthOfLIS_Tail_BS(tails, len, num);
            tails[index] = num;
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    private int lengthOfLIS_Tail_BS(int[] tails, int len, int key) {
        int l = 0, h = len;
        while (l < h) {
            int mid = l + (h - l) / 2;
            if (tails[mid] == key) {
                return mid;
            } else if (tails[mid] > key) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    //    646. Maximum Length of Pair Chain
    //    https://leetcode.com/problems/maximum-length-of-pair-chain/
    public int findLongestChain(int[][] pairs) {
        if (pairs.length <= 1) {
            return pairs.length;
        }
        Arrays.sort(pairs, (o1, o2) -> o1[0] - o2[0]);
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    public int findLongestChain_Greedy(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[1] - o2[1]);
        int currentTail = Integer.MIN_VALUE;
        int lenth = 0;
        for (int[] pair : pairs) {
            if (currentTail < pair[0]) {
                lenth++;
                currentTail = pair[1];
            }
        }
        return lenth;
    }

    //    68. Text Justification
    //    https://leetcode.com/problems/text-justification/

    public String justify(String line, int length) {
        String result = "";
        if (line == null || line.length() == 0 || length == 0 || line.length() > length) {
            return result;
        }
        String[] words = line.split(" ");
        int extraSpace = length - (line.length());
        int index = 0;
        while (extraSpace > 0) {
            words[index] += " ";
            index++;
            if (index == words.length - 1) {
                index = 0;
            }
            extraSpace--;
        }
        return String.join(" ", words);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int start = 0;
        int end;
        int currentLen;
        while (start < words.length) {
            currentLen = words[start].length();
            end = start + 1;
            while (end < words.length && currentLen + words[end].length() + 1 <= maxWidth) {
                currentLen += words[end].length() + 1;
                end++;
            }
            justifyLine(result, words, maxWidth, start, end, currentLen, end == words.length);
            start = end;
        }
        return result;
    }

    private void justifyLine(List<String> result, String[] words, int maxWidth, int start, int end, int currentLen, boolean lastLine) {
        if (lastLine) {
            StringBuilder s = new StringBuilder(String.join(" ", Arrays.copyOfRange(words, start, end)));
            while (s.length() < maxWidth) {
                s.append(" ");
            }
            result.add(s.toString());
        } else {
            int extraSpace = maxWidth - currentLen;
            int index = start;
            while (extraSpace > 0) {
                words[index] += " ";
                index++;
                if (index >= end - 1) {
                    index = start;
                }
                extraSpace--;
            }
            result.add(String.join(" ", Arrays.copyOfRange(words, start, end)));
        }
    }

    //    376. Wiggle Subsequence
    //    https://leetcode.com/problems/wiggle-subsequence/
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int result = 1;
        String lastChange = "";
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 0 && !lastChange.equals("up")) {
                result++;
                lastChange = "up";
            }
            if (nums[i] - nums[i - 1] < 0 && !lastChange.equals("down")) {
                result++;
                lastChange = "down";
            }
        }
        return result;
    }

    public int knapsack(int W, int N, int[] weights, int[] values) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = 1; j <= W; j++) {
                if (j >= w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][W];
    }

    public int knapsack2(int W, int N, int[] weights, int[] values) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = W; j >= 1; j--) {
                if (j >= w) {
                    dp[j] = Math.max(dp[j], dp[j - w] + v);
                }
            }
        }
        return dp[W];
    }

    //    416. Partition Equal Subset Sum
    //    https://leetcode.com/problems/partition-equal-subset-sum/
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        Set<Integer> subsetSums = new HashSet<>();
        subsetSums.add(0);
        for (int num : nums) {
            Set<Integer> currentSet = new HashSet<>(subsetSums);
            for (int j : currentSet) {
                if (j + num == target) {
                    return true;
                }
                subsetSums.add(j + num);
            }
        }
        return false;
    }

    public boolean canPartitionBackpack(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[][] subsetSum = new boolean[nums.length + 1][target + 1];
        for (boolean[] set : subsetSum) {
            set[0] = true;
        }
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                 if (nums[i - 1] > j) {
                    subsetSum[i][j] = subsetSum[i - 1][j];
                } else {
                    subsetSum[i][j] = subsetSum[i - 1][j] || subsetSum[i - 1][j - nums[i - 1]];
                }
                if (j == target && subsetSum[i][j]) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean canPartitionBackpack2(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] subsetSum = new boolean[target + 1];
        subsetSum[0] = true;
        for (int num : nums) {
            for (int j = target; j > 0; j--) {
                if (num <= j) {
                    subsetSum[j] = subsetSum[j - num];
                }
                if (j == target && subsetSum[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    //    494. Target Sum
    //    https://leetcode.com/problems/target-sum/
    public int findTargetSumWays(int[] nums, int S) {
        int max = 0;
        for (int i : nums) {
            max += Math.abs(i);
        }
        if (S > max || S < -max) {
            return 0;
        }
        int[][] dp = new int[nums.length + 1][2 * max + 1];
        dp[0][max] = 1;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= 2 * max; j++) {
                if (j + nums[i - 1] <= 2 * max) {
                    dp[i][j] += dp[i - 1][j + nums[i - 1]];
                }
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][max + S];
    }

    public int findTargetSumWaysDFS(int[] nums, int S) {
        return findTargetSumWaysDFS(nums, 0, S);
    }

    private int findTargetSumWaysDFS(int[] nums, int start, int S) {
        if (start == nums.length) {
            return S == 0 ? 1 : 0;
        }
        return findTargetSumWaysDFS(nums, start + 1, S + nums[start])
                + findTargetSumWaysDFS(nums, start + 1, S - nums[start]);
    }

    //    139. Word Break
    //    https://leetcode.com/problems/word-break/
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (len <= i && word.equals(s.substring(i - len, i)) && dp[i - len]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        System.out.println(dp[n]);
        return dp[n];
    }

    //   474. Ones and Zeroes
    //   https://leetcode.com/problems/ones-and-zeroes/
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int count0 = 0;
            int count1 = 0;
            for (char c : s.toCharArray()) {
                count0 += c == '0' ? 1 : 0;
                count1 += c == '1' ? 1 : 0;
            }
            for (int i = m; i >= count0; i--) {
                for (int j = n; j >= count1; j--) {
                    dp[i][j] = Math.max(dp[i - count0][j - count1] + 1, dp[i][j]);
                }
            }
        }
        return dp[m][n];
    }

    //    322. Coin Change
    //    https://leetcode.com/problems/coin-change/
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    //    377. Combination Sum IV
    //    https://leetcode.com/problems/combination-sum-iv/
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }


    //    121. Best Time to Buy and Sell Stock
    //    https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit1(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            profit = Math.max(profit, price - minPrice);
        }
        return profit;
    }

    //    122. Best Time to Buy and Sell Stock II
    //    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
    public int maxProfit2(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(prices[i] - prices[i - 1], 0);
        }
        return profit;
    }

    //    123. Best Time to Buy and Sell Stock III
    //    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
    public int maxProfit3(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[3][prices.length + 1];
        for (int i = 1; i < 3; i++) {
            int min = prices[0];
            for (int j = 1; j <= prices.length; j++) {
                min = Math.min(min, prices[j - 1] - dp[i - 1][j - 1]);
                dp[i][j] = Math.max(dp[i][j - 1], prices[j - 1] - min);
            }
        }
        return dp[2][prices.length];
    }

    public int maxProfit3SpaceOptimize(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int[] dp = new int[prices.length + 1];
        for (int i = 1; i < 3; i++) {
            int min = prices[0];
            int[] previousDP = dp.clone();
            for (int j = 1; j <= prices.length; j++) {
                min = Math.min(min, prices[j - 1] - previousDP[j - 1]);
                dp[j] = Math.max(dp[j - 1], prices[j - 1] - min);
            }
        }
        return dp[prices.length];
    }

    //    188. Best Time to Buy and Sell Stock IV
    //    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
    public int maxProfit4SpaceOptimize(int k, int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        if (k > prices.length / 2) {
            k = prices.length / 2;
        }
        int[] dp = new int[prices.length + 1];
        for (int i = 1; i < k + 1; i++) {
            int min = prices[0];
            int[] previousDP = dp.clone();
            for (int j = 1; j <= prices.length; j++) {
                min = Math.min(min, prices[j - 1] - previousDP[j - 1]);
                dp[j] = Math.max(dp[j - 1], prices[j - 1] - min);
            }
        }
        return dp[prices.length];
    }

    //    309. Best Time to Buy and Sell Stock with Cooldown
    //    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
    public int maxProfit5(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int[] buy = new int[prices.length + 1];
        int[] sell = new int[prices.length + 1];
        buy[1] = -prices[0];
        for (int i = 2; i <= prices.length; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i - 1]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
        }
        return sell[prices.length];
    }

    //    714. Best Time to Buy and Sell Stock with Transaction Fee
    //    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
    public int maxProfit6(int[] prices, int fee) {
        if (prices.length < 2) {
            return 0;
        }
        int[] buy = new int[prices.length + 1];
        int[] sell = new int[prices.length + 1];
        buy[1] = -prices[0];
        for (int i = 2; i <= prices.length; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i - 1]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1] - fee);
        }
        return sell[prices.length];
    }


    // Strings

    //    583. Delete Operation for Two Strings
    //    https://leetcode.com/problems/delete-operation-for-two-strings/
    public int minDistance(String word1, String word2) {
        if (word1.length()  == 0 || word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return word1.length() + word2.length() - (2 * dp[word1.length()][word2.length()]);
    }

    //    72. Edit Distance
    //    https://leetcode.com/problems/edit-distance/description/
    public int minDistance72(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int add = dp[i - 1][j] + 1;
                    int sub = dp[i][j - 1] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(add, sub), replace);
                }

            }
        }
        return dp[word1.length()][word2.length()];
    }

    //    650. 2 Keys Keyboard
    //    https://leetcode.com/problems/2-keys-keyboard/
    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return minSteps(n / i) + i;
            }
        }
        return n;
    }

    public int minStepsDP(int n) {
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                if (i % j == 0) {
                    dp[i] = dp[i / j] + j;
                    break;
                }
            }
        }
        return dp[n];
    }



























    //    math
    //    204. Count Primes
    //    https://leetcode.com/problems/count-primes/description/
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }

    //    504. Base 7
    //    https://leetcode.com/problems/base-7/description/
    public String convertToBase7(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        while (num / 7 != 0) {
            stringBuilder.insert(0, Math.abs(num % 7));
            num = num / 7;
        }
        stringBuilder.insert(0, num);
        return stringBuilder.toString();
    }

    //    168. Excel Sheet Column Title
    //    https://leetcode.com/problems/excel-sheet-column-title/
    public String convertToTitle(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        while (n > 0) {
            n--;
            stringBuilder.insert(0, (char) (n % 26 + 'A'));
            n = n / 26;
        }
        return stringBuilder.toString();
    }

    //factorial

    //    172. Factorial Trailing Zeroes
    //    https://leetcode.com/problems/factorial-trailing-zeroes/
    public int trailingZeroes(int n) {
        int result = 0;
        while (n > 0) {
            result += n / 5;
            n  = n / 5;
        }
        return result;
    }

    // string add sub
    //    67. Add Binary
    //    https://leetcode.com/problems/add-binary/
    public String addBinary(String a, String b) {
        if (a.length() == 0 || b.length() == 0) {
            return a + b;
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            int ai = i >= 0 ? Character.getNumericValue(a.charAt(i)) : 0;
            int bj = j >= 0 ? Character.getNumericValue(b.charAt(j)) : 0;
            int v = ai + bj + carry;

            sb.insert(0, v % 2);
            carry = v / 2;
            i--;
            j--;
        }
        if (carry > 0) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

    //    415. Add Strings
    //    https://leetcode.com/problems/add-strings/
    public String addStrings(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            int ai = i >= 0 ? Character.getNumericValue(a.charAt(i)) : 0;
            int bj = j >= 0 ? Character.getNumericValue(b.charAt(j)) : 0;
            int v = ai + bj + carry;
            sb.insert(0, v % 10);
            carry = v / 10;
            i--;
            j--;
        }
        if (carry > 0) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

    //    462. Minimum Moves to Equal Array Elements II
    //    https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int result = 0;
        for (int i : nums) {
            result += Math.abs(i - median);
        }
        return result;
    }

    //    169 .Majority Element
    //    https://leetcode.com/problems/majority-element/
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if (map.get(i) > nums.length / 2) {
                return i;
            }
        }
        return 0;
    }

    public int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // others
    //    367. Valid Perfect Square
    //    https://leetcode.com/problems/valid-perfect-square/
    public boolean isPerfectSquare(int num) {
        long l = 1;
        long h = num;
        while (l <= h) {
            long m = l + (h - l) / 2;
            if (m * m == num) {
                return true;
            } else if (m * m > num) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    //    326. Power of Three
    //    https://leetcode.com/problems/power-of-three/
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    //    238. Product of Array Except Self
    //    https://leetcode.com/problems/product-of-array-except-self/
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, 1);
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            temp *= nums[i - 1];
            result[i] *= temp;
        }
        temp = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            temp *= nums[i + 1];
            result[i] *= temp;
        }
        return result;
    }

    //    628. Maximum Product of Three Numbers
    //    https://leetcode.com/problems/maximum-product-of-three-numbers/
    public int maximumProduct(int[] nums) {
        PriorityQueue<Integer> max3 = new PriorityQueue<>();
        PriorityQueue<Integer> min2 = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : nums) {
            if (max3.size() < 3) {
                max3.offer(i);
            } else if (i > max3.peek()) {
                max3.poll();
                max3.offer(i);
            }
            if (min2.size() < 2) {
                min2.offer(i);
            } else if (i < min2.peek()) {
                min2.poll();
                min2.offer(i);
            }
        }
        return Math.max(max3.poll() * max3.poll() * max3.peek(), min2.poll() * min2.poll() * max3.peek());

    }




    //    linked list
    //    160. Intersection of Two Linked Lists
    //    https://leetcode.com/problems/intersection-of-two-linked-lists/description/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    //    206. Reverse Linked List
    //    https://leetcode.com/problems/reverse-linked-list/description/
    public ListNode reverseList_tieration(ListNode head) {
        ListNode reverted = null;
        ListNode rest = head;
        while (rest != null) {
            ListNode next = rest.next;
            rest.next = reverted;
            reverted = rest;
            rest = next;
        }
        return reverted;
    }

    public ListNode reverseList_recursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverseList_recursive(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    //    21. Merge Two Sorted Lists
    //    https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 == null) {
            tail.next = l2;
        } else {
            tail.next = l1;
        }
        return dummy.next;
    }

    //    83. Remove Duplicates from Sorted List
    //    https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    public ListNode deleteDuplicates(ListNode head) {
        ListNode result = head;
        while (head != null && head.next != null) {
            while (head.next != null && head.val == head.next.val) {
                head.next = head.next.next;
            }
            head = head.next;
        }
        return result;
    }

    //    19. Remove Nth Node From End of List
    //    https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        ListNode target = dummy;
        while (node.next != null) {
            if (n > 0) {
                n--;
                node = node.next;
            } else {
                node = node.next;
                target = target.next;
            }
        }
        target.next = target.next.next;
        return dummy.next;
    }

    //    24. Swap Nodes in Pairs
    //    https://leetcode.com/problems/swap-nodes-in-pairs/
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            ListNode next = head.next.next;
            pre.next = head.next;
            head.next.next = head;
            pre = head;
            head.next = next;
            head = head.next;
        }
        return dummy.next;
    }

    //    445. Add Two Numbers II
    //    https://leetcode.com/problems/add-two-numbers-ii/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode dummy = new ListNode(0);
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry == 1) {
            int n1 = stack1.isEmpty() ? 0 : stack1.pop();
            int n2 = stack2.isEmpty() ? 0 : stack2.pop();
            int v = n1 + n2 + carry;
            ListNode head = dummy.next;
            dummy.next = new ListNode(v % 10);
            dummy.next.next = head;
            carry = v / 10;
        }
        return dummy.next;
    }

    //    234. Palindrome Linked List
    //    https://leetcode.com/problems/palindrome-linked-list/
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverseList_recursive(slow);
        while (slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;
    }

    //    725. Split Linked List in Parts
    //    https://leetcode.com/problems/split-linked-list-in-parts/
    public ListNode[] splitListToParts(ListNode root, int k) {
        int length = 0;
        ListNode node = root;
        while (node != null) {
            length++;
            node = node.next;
        }
        int[] partsLength = new int[k];
        Arrays.fill(partsLength, length / k);
        int rest = length % k;
        ListNode[] result = new ListNode[k];
        node = root;
        for (int i = 0; i < k; i++) {
            if (rest > 0) {
                partsLength[i]++;
                rest--;
            }
            while (partsLength[i]-- > 0) {
                result[i] = result[i] == null ? node : result[i];
                if (partsLength[i] == 0) {
                    ListNode pre = node;
                    node = node.next;
                    pre.next = null;
                } else {
                    node = node.next;
                }
            }
        }
        return result;
    }

    //    328. Odd Even Linked List
    //    https://leetcode.com/problems/odd-even-linked-list/
    public ListNode oddEvenList(ListNode head) {
        int index = 0;
        ListNode oddHead = new ListNode(0);
        ListNode oddTail = oddHead;
        ListNode evenHead = new ListNode(0);
        ListNode evenTail = evenHead;
        while (head != null) {
            boolean isOdd = ++index % 2 == 1;
            if (isOdd) {
                oddTail.next = head;
                oddTail = oddTail.next;
            } else {
                evenTail.next = head;
                evenTail = evenTail.next;
            }
            head = head.next;
        }
        evenTail.next = null;
        oddTail.next = evenHead.next;
        return oddHead.next;
    }



    // tree

    //    104. Maximum Depth of Binary Tree
    //    https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //    110. Balanced Binary Tree
    //    https://leetcode.com/problems/balanced-binary-tree/description/
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }


    //    543. Diameter of Binary Tree
    //    https://leetcode.com/problems/diameter-of-binary-tree/description/
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int diameterWithRoot = maxDepth(root.left) + maxDepth(root.right);
        return Math.max(Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)), diameterWithRoot);
    }

    //    226. Invert Binary Tree
    //    https://leetcode.com/problems/invert-binary-tree/
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //    617. Merge Two Binary Trees
    //    https://leetcode.com/problems/merge-two-binary-trees/
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }

    //    112. Path Sum
    //    https://leetcode.com/problems/path-sum/
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    //    113. Path Sum II
    //    https://leetcode.com/problems/path-sum-ii/
    public List<List<Integer>> pathSum113(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSum113BackTrace(root, sum, result, path);
        return result;
    }

    private void pathSum113BackTrace(TreeNode root, int sum, List<List<Integer>> result, List<Integer> path) {
        if (root != null) {
            path.add(root.val);
            sum -= root.val;
            if (sum == 0 && root.left == null && root.right == null) {
                result.add(new ArrayList<>(path));
            }
            pathSum113BackTrace(root.left, sum, result, path);
            pathSum113BackTrace(root.right, sum, result, path);
            path.remove(path.size() - 1);
        }
    }

    //    437. Path Sum III
    //    https://leetcode.com/problems/path-sum-iii/
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumWithRoot(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumWithRoot(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int result = 0;
        if (root.val == sum) {
            result++;
        }
        result += pathSumWithRoot(root.left, sum - root.val) + pathSumWithRoot(root.right, sum - root.val);
        return result;
    }

    //    572. Subtree of Another Tree
    //    https://leetcode.com/problems/subtree-of-another-tree/
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == null && t == null;
        }
        return s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    //    101. Symmetric Tree
    //    https://leetcode.com/problems/symmetric-tree/
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isSymmetricSubtrees(root.left, root.right);
    }

    private boolean isSymmetricSubtrees(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == null && right == null;
        }
        return left.val == right.val && isSymmetricSubtrees(left.left, right.right) && isSymmetricSubtrees(left.right, right.left);
    }

    //    111. Minimum Depth of Binary Tree
    //    https://leetcode.com/problems/minimum-depth-of-binary-tree/
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    //    404. Sum of Left Leaves
    //    https://leetcode.com/problems/sum-of-left-leaves/
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        } else {
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }

    //    687. Longest Univalue Path
    //    https://leetcode.com/problems/longest-univalue-path/
    public int longestUnivaluePath(TreeNode root) {
        int[] max = new int[1];
        dfs(root, max);
        return max[0];
    }

    private int dfs(TreeNode root, int[] max){
        if (root == null) return 0;
        int left = dfs(root.left, max);
        int right = dfs(root.right, max);
        int leftPath = root.left != null && root.left.val == root.val ? left + 1 : 0;
        int rightPath = root.right != null && root.right.val == root.val ? right + 1 : 0;
        max[0] = Math.max(max[0], leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }

    //    337. House Robber III
    //    https://leetcode.com/problems/house-robber-iii/description/
    public int rob(TreeNode root) {
        return robCache(root, new HashMap<>());
    }

    private int robCache(TreeNode root, Map<TreeNode, Integer> cache) {
        if (root == null) {
            return 0;
        }
        if (cache.containsKey(root)) {
            return cache.get(root);
        }
        int withRoot = root.val;
        if (root.left != null) {
            withRoot += robCache(root.left.left, cache) + robCache(root.left.right, cache);
        }
        if (root.right != null) {
            withRoot += robCache(root.right.left, cache) + robCache(root.right.right, cache);
        }
        int withOutRoot = 0;
        withOutRoot += robCache(root.left, cache) + robCache(root.right, cache);
        cache.put(root, Math.max(withRoot, withOutRoot));
        return Math.max(withRoot, withOutRoot);
    }

    //    671. Second Minimum Node In a Binary Tree
    //    https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
    public int findSecondMinimumValue(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        findSecondMinimumValueDFS(root, set);
        int result = Integer.MAX_VALUE;
        for (int v : set) {
            if (root.val < v && v < result) {
                result = v;
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private void findSecondMinimumValueDFS(TreeNode root, Set<Integer> uniques) {
        if (root != null) {
            uniques.add(root.val);
            findSecondMinimumValueDFS(root.left, uniques);
            findSecondMinimumValueDFS(root.right, uniques);
        }
    }


    // traverse

    //    637. Average of Levels in Binary Tree
    //    https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = size;
            double sum = 0;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }

    //    513. Find Bottom Left Tree Value
    //    https://leetcode.com/problems/find-bottom-left-tree-value/description/
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        Queue<TreeNode> last = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            last = new ArrayDeque<>();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                last.offer(node);
            }
        }
        return last.poll().val;
    }

    //    144. Binary Tree Preorder Traversal
    //    https://leetcode.com/problems/binary-tree-preorder-traversal/description/
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    //    145. Binary Tree Postorder Traversal
    //    https://leetcode.com/problems/binary-tree-postorder-traversal/description/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }

    //    94. Binary Tree Inorder Traversal
    //    https://leetcode.com/problems/binary-tree-inorder-traversal/description/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode node = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            result.add(node.val);
            node = node.right;
        }
        return result;
    }

    //    669. Trim a Binary Search Tree
    //    https://leetcode.com/problems/trim-a-binary-search-tree/description/
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }


    //    230. Kth Smallest Element in a BST
    //    https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
    public int kthSmallest(TreeNode root, int k) {
        int[] count = new int[1];
        count[0] = k;
        return kthSmallestDFS(root, count);

    }

    private int kthSmallestDFS(TreeNode node, int[] count) {
        if (node != null) {
            int left = kthSmallestDFS(node.left, count);
            if (left >= 0) {
                return left;
            }
            if (--count[0] == 0) {
                return node.val;
            }
            int right = kthSmallestDFS(node.right, count);
            if (right >= 0) {
                return right;
            }
        }
        return -1;
    }

    //    538. Convert BST to Greater Tree
    //    https://leetcode.com/problems/convert-bst-to-greater-tree/description/
    int convertBSTSum = 0;

    public TreeNode convertBST(TreeNode root) {
        convertBSTDFS(root);
        return root;
    }

    private void convertBSTDFS(TreeNode root) {
        if (root != null) {
            convertBSTDFS(root.right);
            convertBSTSum += root.val;
            root.val = convertBSTSum;
            convertBSTDFS(root.left);

        }
    }

    //    235. Lowest Common Ancestor of a Binary Search Tree
    //    https://superuser.com/questions/385972/how-to-select-chrome-url-address-bar-by-using-shortcuts
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    //    236. Lowest Common Ancestor of a Binary Tree
    //    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
    public TreeNode lowestCommonAncestor236(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor236(root.left, p, q);
        TreeNode right = lowestCommonAncestor236(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    //    108. Convert Sorted Array to Binary Search Tree
    //    https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int mid = nums.length / 2;
        TreeNode node = new TreeNode(nums[mid]);
        if (0 < mid) {
            node.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        }
        if (mid + 1 < nums.length) {
            node.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));
        }
        return node;
    }

    //    109. Convert Sorted List to Binary Search Tree
    //    https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return sortedArrayToBST(array);
    }

    //    653. Two Sum IV - Input is a BST
    //    https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = inorderTraversal(root);
        int l = 0;
        int h = list.size() - 1;
        while (l < h) {
            int v = list.get(l) + list.get(h);
            if (v == k) {
                return true;
            } else if (v < k) {
                l++;
            } else {
                h--;
            }
        }
        return false;
    }

    //    530. Minimum Absolute Difference in BST
    //    https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getMinimumDifferenceInorder(root, list);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            min = Math.min(min, list.get(i) - list.get(i - 1));
        }
        return min;
    }


    private void getMinimumDifferenceInorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            getMinimumDifferenceInorder(root.left, list);
            list.add(root.val);
            getMinimumDifferenceInorder(root.right, list);
        }
    }

    //    501. Find Mode in Binary Search Tree
    //    https://leetcode.com/problems/find-mode-in-binary-search-tree/description/

    int findModeCurrentCount = 0;
    int findModeMaxCount = 0;
    Integer findModePre = null;
    List<Integer> findModeResult = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        findModeInorder(root);
        int[] result = new int[findModeResult.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = findModeResult.get(i);
        }
        return result;
    }

    private void findModeInorder(TreeNode root) {
        if (root != null) {
            findModeInorder(root.left);
            if (findModePre != null && root.val == findModePre) {
                findModeCurrentCount++;
            } else {
                findModeCurrentCount = 1;
            }
            if (findModeCurrentCount > findModeMaxCount) {
                findModeResult.clear();
                findModeResult.add(root.val);
                findModeMaxCount = findModeCurrentCount;
            } else if (findModeCurrentCount == findModeMaxCount) {
                findModeResult.add(root.val);
            }
            findModePre = root.val;
            findModeInorder(root.right);
        }
    }

    //    208. Implement Trie (Prefix Tree)
    //    https://leetcode.com/problems/implement-trie-prefix-tree

    static class Trie {

        private class TrieNode {
            Map<Character, TrieNode> childs = new HashMap<>();
            private boolean isWord;
        }

        private TrieNode root;


        /** Initialize your data structure here. */
        public Trie() {
            this.root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if (word != null && word.length() > 0) {
                TrieNode current = root;
                for (int i = 0; i < word.length(); i++) {
                    current.childs.put(word.charAt(i), current.childs.getOrDefault(word.charAt(i), new TrieNode()));
                    current = current.childs.get(word.charAt(i));
                }
                current.isWord = true;
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.childs.get(word.charAt(i)) == null) {
                    return false;
                }
                node = node.childs.get(word.charAt(i));
            }
            return node.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                if (node.childs.get(prefix.charAt(i)) == null) {
                    return false;
                }
                node = node.childs.get(prefix.charAt(i));
            }
            return startsWithNode(node);
        }

        private boolean startsWithNode(TrieNode node) {
            if (node == null) {
                return false;
            }
            if (node.isWord) {
                return true;
            }
            for (TrieNode child : node.childs.values()) {
                if (startsWithNode(child)) {
                    return true;
                }
            }
            return false;
        }
    }

    //    677. Map Sum Pairs
    //    https://leetcode.com/problems/map-sum-pairs/description/
    static class MapSum {

        private class MapSumNode {
            Map<Character, MapSumNode> childs = new HashMap<>();
            int value = 0;
        }

        private MapSumNode root;

        /** Initialize your data structure here. */
        public MapSum() {
            this.root = new MapSumNode();
        }

        public void insert(String key, int val) {
            if (key != null && key.length() > 0) {
                MapSumNode node = root;
                for (int i = 0; i < key.length(); i++) {
                    node.childs.put(key.charAt(i), node.childs.getOrDefault(key.charAt(i), new MapSumNode()));
                    node = node.childs.get(key.charAt(i));
                }
                node.value = val;
            }
        }

        public int sum(String prefix) {
            if (prefix != null && prefix.length() > 0) {
                MapSumNode node = root;
                for (int i = 0; i < prefix.length(); i++) {
                    node = node.childs.get(prefix.charAt(i));
                    if (node == null) {
                        return 0;
                    }
                }
                return sumWithRoot(node);
            }
            return 0;
        }

        private int sumWithRoot(MapSumNode node) {
            int result = 0;
            for (MapSumNode child : node.childs.values()) {
                result += sumWithRoot(child);
            }
            result += node.value;
            return result;
        }
    }

    //    232. Implement Queue using Stacks
    //    https://leetcode.com/problems/implement-queue-using-stacks/description/
    class MyQueue {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();

        /** Initialize your data structure here. */
        public MyQueue() {
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stack2.isEmpty()) {
                transfer();
            }
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (stack2.isEmpty()) {
                transfer();
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        private void transfer() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    //    225. Implement Stack using Queues
    //    https://leetcode.com/problems/implement-stack-using-queues/description/
    class MyStack {
        Queue<Integer> stack = new ArrayDeque<>();
        /** Initialize your data structure here. */
        public MyStack() {
        }

        /** Push element x onto stack. */
        public void push(int x) {
            Queue<Integer> temp = new ArrayDeque<>();
            while (!stack.isEmpty()) {
                temp.offer(stack.poll());
            }
            stack.offer(x);
            while (!temp.isEmpty()) {
                stack.offer(temp.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return stack.poll();
        }

        /** Get the top element. */
        public int top() {
            return stack.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }

    //    155. Min Stack
    //    https://leetcode.com/problems/min-stack/description/
    static class MinStack {
        Deque<Integer> dataStack = new ArrayDeque<>();
        Deque<Integer> minStack = new ArrayDeque<>();

        /** initialize your data structure here. */
        public MinStack() {
        }

        public void push(int x) {
            dataStack.push(x);
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else if (minStack.peek() > x) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    //     20. Valid Parentheses
    //     https://leetcode.com/problems/valid-parentheses/description/
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if (pairs(c, stack.peek())) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.size() == 0;
    }

    private boolean pairs(Character a, Character b) {
        if (a == null || b == null) {
            return false;
        }
        return a == '(' && b == ')'
                || a == ')' && b == '('
                || a == '[' && b == ']'
                || a == ']' && b == '['
                || a == '{' && b == '}'
                || a == '}' && b == '{';
    }

    //    739. Daily Temperatures
    //    https://leetcode.com/problems/daily-temperatures/description/
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> indexStack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!indexStack.isEmpty() && temperatures[i] > temperatures[indexStack.peek()]) {
                result[indexStack.peek()] = i - indexStack.peek();
                indexStack.pop();
            }
            indexStack.push(i);
        }
        return result;
    }

    //    503. Next Greater Element II
    //    https://leetcode.com/problems/next-greater-element-ii/description/
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Deque<Integer> indexStack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!indexStack.isEmpty() && nums[i] > nums[indexStack.peek()]) {
                result[indexStack.pop()] = nums[i];
            }
            indexStack.push(i);
        }
        for (int num : nums) {
            while (!indexStack.isEmpty() && num > nums[indexStack.peek()]) {
                result[indexStack.pop()] = num;
            }
        }
        return result;
    }



    //    hash table
    //    1. Two Sum
    //    https://leetcode.com/problems/two-sum/description/
    public int[] twoSum_no1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    //    217. Contains Duplicate
    //    https://leetcode.com/problems/contains-duplicate/description/
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return true;
            } else {
                set.add(i);
            }
        }
        return false;
    }

    //    594. Longest Harmonious Subsequence
    //    https://leetcode.com/problems/longest-harmonious-subsequence/description/
    public int findLHS(int[] nums) {
        int max = 0;
        HashMap<Integer, Integer> numToCount = new HashMap<>();
        for (int i : nums) {
            numToCount.put(i, numToCount.getOrDefault(i, 0) + 1);
        }
        for (int i : numToCount.keySet()) {
            if (numToCount.containsKey(i + 1)) {
                max = Math.max(max, numToCount.get(i) + numToCount.get(i + 1));
            }
        }
        return max;
    }

    //    128. Longest Consecutive Sequence
    //    https://leetcode.com/problems/longest-consecutive-sequence/description/
    public int longestConsecutive(int[] nums) {
        int max = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        for (int i : set) {
            if (!set.contains(i - 1)) {
                int currentLength = 1;
                while (set.contains(++i)) {
                    currentLength++;
                }
                max = Math.max(max, currentLength);
            }
        }
        return max;
    }

    //    242. Valid Anagram
    //    https://leetcode.com/problems/valid-anagram/description/
    public boolean isAnagram(String s, String t) {
        int[] sFrequency = new int[26];
        int[] tFrequency = new int[26];
        for (char c : s.toCharArray()) {
            sFrequency[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            tFrequency[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sFrequency[i] != tFrequency[i]) {
                return false;
            }
        }
        return true;
    }

    //    409. Longest Palindrome
    //    https://leetcode.com/problems/longest-palindrome/description/
    public int longestPalindrome(String s) {
        int max = 0;
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
                max += 2;
            } else {
                set.add(c);
            }
        }
        return max + (set.isEmpty() ? 0 : 1);
    }

    //    205. Isomorphic Strings
    //    https://leetcode.com/problems/isomorphic-strings/description/
    public boolean isIsomorphic(String s, String t) {
        int[] lastIndexS = new int[256];
        int[] lastIndexT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (lastIndexS[sc] != lastIndexT[tc]) {
                return false;
            }
            lastIndexS[sc] = i + 1;
            lastIndexT[tc] = i + 1;
        }
        return true;
    }

    //    647. Palindromic Substrings
    //    https://leetcode.com/problems/palindromic-substrings/description/
    private int cnt = 0;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            extendSubstrings(s, i, i);     // 奇数长度
            extendSubstrings(s, i, i + 1); // 偶数长度
        }
        return cnt;
    }

    private void extendSubstrings(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
            cnt++;
        }
    }

    //    9. Palindrome Number
    //    https://leetcode.com/problems/palindrome-number/solution/
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int reverted = 0;
        while (x > reverted) {
            reverted = reverted * 10 + x % 10;
            x = x / 10;
        }
        return x == reverted || x == reverted / 10;
    }

    //    696. Count Binary Substrings
    //    https://leetcode.com/problems/count-binary-substrings/description/
    public int countBinarySubstrings(String s) {
        int preLen = 0, curLen = 1, count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curLen++;
            } else {
                preLen = curLen;
                curLen = 1;
            }

            if (preLen >= curLen) {
                count++;
            }
        }
        return count;
    }

    //    array and matrix
    //    283. Move Zeroes
    //    https://leetcode.com/problems/move-zeroes/description/
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i : nums) {
            if (i != 0) {
                nums[index++] = i;
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


    //    566. Reshape the Matrix
    //    https://leetcode.com/problems/reshape-the-matrix/description/
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length == 0 || nums.length * nums[0].length != r * c) {
            return nums;
        }
        int[][] result = new int[r][c];
        for (int i = 0; i < r * c; i++) {
            int r1 = i / nums[0].length;
            int c1 = i % nums[0].length;
            int r2 = i / c;
            int c2 = i % c;
            result[r2][c2] = nums[r1][c1];
        }
        return result;
    }

    //    485. Max Consecutive Ones
    //    https://leetcode.com/problems/max-consecutive-ones/description/
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int current = 0;
        for (int i : nums) {
            if (i == 1) {
                current++;
                max = Math.max(max, current);
            } else {
                current = 0;
            }
        }
        return max;
    }

    //    240. Search a 2D Matrix II
    //    https://leetcode.com/problems/search-a-2d-matrix-ii/description/
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int x = 0;
        int y = matrix[0].length - 1;
        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }

    //    378. Kth Smallest Element in a Sorted Matrix
    //    https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
    public int kthSmallest(int[][] matrix, int k) {
        class Node {
            int value;
            int i;
            int j;

            Node(int value, int i, int j) {
                this.value = value;
                this.i = i;
                this.j = j;
            }
        }

        PriorityQueue<Node> minHeap = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
        for (int j = 0; j < matrix[0].length; j++) {
            minHeap.add(new Node(matrix[0][j], 0, j));
        }
        while (k-- > 1) {
            Node node = minHeap.poll();
            if (node.i == matrix.length - 1) {
                continue;
            }
            minHeap.offer(new Node(matrix[node.i + 1][node.j], node.i + 1, node.j));
        }
        return minHeap.poll().value;
    }

    //    645. Set Mismatch
    //    https://leetcode.com/problems/set-mismatch
    public int[] findErrorNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }

    //    448. Find All Numbers Disappeared in an Array
    //    https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        return result;
    }

    //    442. Find All Duplicates in an Array
    //    https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
    public List<Integer> findDuplicates(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }
        return result;
    }


    //    287. Find the Duplicate Number
    //    https://leetcode.com/problems/find-the-duplicate-number/description/
    public int findDuplicate(int[] nums) {
        int l = 1, h = nums.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) cnt++;
            }
            if (cnt > mid) h = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    //    667. Beautiful Arrangement II
    //    https://leetcode.com/problems/beautiful-arrangement-ii/description/
    public int[] constructArray(int n, int k) {
        int[] ret = new int[n];
        ret[0] = 1;
        for (int i = 1, interval = k; i <= k; i++, interval--) {
            ret[i] = i % 2 == 1 ? ret[i - 1] + interval : ret[i - 1] - interval;
        }
        for (int i = k + 1; i < n; i++) {
            ret[i] = i + 1;
        }
        return ret;
    }

    //    697. Degree of an Array
    //    https://leetcode.com/problems/degree-of-an-array/description/
    public int findShortestSubArray(int[] nums) {
        class Record {
            int value;
            int firstIndex;
            int lastIndex;
            int count;

            public Record(int value, int firstIndex, int lastIndex, int count) {
                this.value = value;
                this.firstIndex = firstIndex;
                this.lastIndex = lastIndex;
                this.count = count;
            }
        }

        Map<Integer, Record> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new Record(nums[i], i, i, 1));
            } else {
                Record record = map.get(nums[i]);
                record.lastIndex = i;
                record.count++;
            }
        }
        List<Record> list = new ArrayList<>(map.values());
        list.sort((o1, o2) -> {
            if (o2.count - o1.count != 0) {
                return o2.count - o1.count;
            } else {
                return (o1.lastIndex - o1.firstIndex) - (o2.lastIndex - o2.firstIndex);
            }
        });
        return list.get(0).lastIndex - list.get(0).firstIndex + 1;
    }

    //    766. Toeplitz Matrix
    //    https://leetcode.com/problems/toeplitz-matrix/description/
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i > 0 && j > 0 && matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    //    565. Array Nesting
    //    https://leetcode.com/problems/array-nesting/description/
    public int arrayNesting(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int size = 0;
            while (i >= 0 && nums[i] >= 0) {
                size++;
                int iValue = nums[i];
                nums[i] = -1;
                i = iValue;
            }
            max = Math.max(max, size);
        }
        return max;
    }

    //    769. Max Chunks To Make Sorted
    //    https://leetcode.com/problems/max-chunks-to-make-sorted/description/
    public int maxChunksToSorted(int[] arr) {
        if (arr == null) return 0;
        int ret = 0;
        int right = arr[0];
        for (int i = 0; i < arr.length; i++) {
            right = Math.max(right, arr[i]);
            if (right == i) ret++;
        }
        return ret;
    }

    //    785. Is Graph Bipartite?
    //    https://leetcode.com/problems/is-graph-bipartite/
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                if (!isBipartiteAfterColored(i, 1, color, graph)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBipartiteAfterColored(int index, int targetColor, int[] color, int[][] graph) {
        if (color[index] == 0) {
            color[index] = targetColor;
            for (int connected : graph[index]) {
                if (color[index] == color[connected]) {
                    return false;
                } else {
                    if (!isBipartiteAfterColored(connected, targetColor * -1, color, graph)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //    207. Course Schedule
    //    https://leetcode.com/problems/course-schedule/description/

    // 207.1 dfs grey color
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfsHaveCircle(i, graph, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfsHaveCircle(int i, List<List<Integer>> graph, int[] visited) {
        if (visited[i] == 2) {
            return false;
        }
        if (visited[i] == 1) {
            return true;
        }
        visited[i] = 1;
        for (int child : graph.get(i)) {
            if (dfsHaveCircle(child, graph, visited)) {
                return true;
            }
        }
        visited[i] = 2;
        return false;
    }

    // bfs grep color : can't use bfs grey color to detect circle in dag

    // 207.2 we can use kahn's algorithm(topologic sort)
    public boolean canFinishKahn(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }
        return !kahnHaveCircle(graph);
    }

    private boolean kahnHaveCircle(List<List<Integer>> graph) {
        int[] indegree = new int[graph.size()];
        for (List<Integer> list : graph) {
            for (int node : list) {
                indegree[node]++;
            }
        }
        Queue<Integer> zeroIndegreeNodes = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                zeroIndegreeNodes.add(i);
            }
        }
        int removedCount = 0;
        while (!zeroIndegreeNodes.isEmpty()) {
            int removed = zeroIndegreeNodes.poll();
            removedCount++;
            for (int child : graph.get(removed)) {
                indegree[child]--;
                if (indegree[child] == 0) {
                    zeroIndegreeNodes.add(child);
                }
            }
        }
        return removedCount != graph.size();
    }

    //    210. Course Schedule II
    //    https://leetcode.com/problems/course-schedule-ii/description/

    // 210.1 dfs grey color stack
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }
        int[] visited = new int[numCourses];
        List<Integer> postOrder = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (dfsFindOrderHasCircle(i, graph, visited, postOrder)) {
                return new int[0];
            }
        }
        int[] result = new int[numCourses];
        int index = 0;
        while (!postOrder.isEmpty()) {
            result[index++] = postOrder.remove(0);
        }
        return result;
    }

    private boolean dfsFindOrderHasCircle(int i, List<List<Integer>> graph, int[] visited, List<Integer> postOrder) {
        if (visited[i] == 2) {
            return false;
        }
        if (visited[i] == 1) {
            return true;
        }
        visited[i] = 1;
        for (int child : graph.get(i)) {
            if (dfsFindOrderHasCircle(child, graph, visited, postOrder)) {
                return true;
            }
        }
        visited[i] = 2;
        postOrder.add(i);
        return false;
    }


    // 210.2 kahn's algorithm
    public int[] findOrderKahn(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            indegree[edge[1]]++;
        }
        Queue<Integer> zeroIndegree = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                zeroIndegree.add(i);
            }
        }
        int[] result = new int[numCourses];
        int index = numCourses - 1;
        while (!zeroIndegree.isEmpty()) {
            int current = zeroIndegree.poll();
            result[index--] = current;
            for (int child : graph.get(current)) {
                indegree[child]--;
                if (indegree[child] == 0) {
                    zeroIndegree.add(child);
                }
            }
        }
        if (index == -1) {
            return result;
        } else {
            return new int[0];
        }
    }

    //    148. Sort List
    //    https://leetcode.com/problems/sort-list
    public ListNode sortList(ListNode head) {
        int listLength = 0;
        ListNode node = head;
        while (node != null) {
            listLength++;
            node = node.next;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (int i = 1; i < listLength; i *= 2) {
            mergeBySize(dummy, i);
        }
        return dummy.next;
    }

    private void mergeBySize(ListNode dummy, int i) {
        ListNode pre = dummy;
        while (pre != null && pre.next != null) {
            ListNode[] headAndTail = mergeTwoSubList(pre.next, i);
            pre.next = headAndTail[0];
            pre = headAndTail[1];
        }
    }

    private ListNode[] mergeTwoSubList(ListNode head, int i) {
        ListNode head1 = head;
        ListNode tail1 = getTail(head, i);
        if (tail1 != null && tail1.next != null) {
            ListNode head2 = tail1.next;
            tail1.next = null;
            ListNode tail2 = getTail(head2, i);
            ListNode rest = tail2.next;
            tail2.next = null;
            ListNode dummy = new ListNode(0);
            ListNode node = dummy;
            while (head1 != null && head2 != null) {
                if (head1.val < head2.val) {
                    node.next = head1;
                    node = node.next;
                    head1 = head1.next;
                } else {
                    node.next = head2;
                    node = node.next;
                    head2 = head2.next;
                }
            }
            if (head1 != null) {
                node.next = head1;
            }
            if (head2 != null) {
                node.next = head2;
            }
            tail2 = getTail(dummy, i * 2 + 1);
            tail2.next = rest;
            return new ListNode[]{dummy.next, tail2};
        } else {
            return new ListNode[]{head1, tail1};
        }
    }

    private ListNode getTail(ListNode head, int length) {
        while (head.next != null && length > 1) {
            head = head.next;
            length--;
        }
        return head;
    }

    public ListNode sortListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        head = sortListRecursive(head);
        slow = sortListRecursive(slow);
        head = sortListRecursiveMerge(head, slow);
        return head;
    }

    private ListNode sortListRecursiveMerge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                node.next = head1;
                head1 = head1.next;
            } else {
                node.next = head2;
                head2 = head2.next;
            }
            node = node.next;

        }
        if (head1 != null) {
            node.next = head1;
        }
        if (head2 != null) {
            node.next = head2;
        }
        return dummy.next;
    }

    //    684. Redundant Connection
    //    https://leetcode.com/problems/redundant-connection/description/
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length + 1);
        for (int[] edge: edges) {
            if (!dsu.union(edge[0], edge[1])) return edge;
        }
        return null;
    }

    //    222. Count Complete Tree Nodes
    //    https://leetcode.com/problems/count-complete-tree-nodes/
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height = getTreeHeight(root);
        if (height == 0) {
            return 1;
        }
        if (getTreeHeight(root.left) == getTreeHeight(root.right)) {
            int left = (int) Math.pow(2, height) - 1;
            int right = countNodes(root.right);
            return  left + right + 1;
        } else {
            int left = countNodes(root.left);
            int right = (int) Math.pow(2, height - 1) - 1;
            return left + right + 1;
        }
    }

    private int getTreeHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return 1 + getTreeHeight(root.left);
    }

    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes2(root.left) + countNodes2(root.right);
    }

    // Top 100 Liked Questions
    // https://leetcode.com/problemset/top-100-liked-questions/

    //    2. Add Two Numbers
    //    https://leetcode.com/problems/add-two-numbers/
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int value = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            current.next = new ListNode(value % 10);
            current = current.next;
            carry = value / 10;
        }
        return dummy.next;
    }

    //    3. Longest Substring Without Repeating Characters
    //    https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        int start = 0;
        int end = 0;
        Set<Character> set = new HashSet<>();
        while (start <= end && end < s.length()) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                max = Math.max(max, end - start + 1);
                end++;
            } else {
                set.remove(s.charAt(start));
                start++;
            }
        }
        return max;
    }
    //    10. Regular Expression Matching
    //    https://leetcode.com/problems/regular-expression-matching/
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = !text.isEmpty() && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');
        if (pattern.length() > 1 && pattern.charAt(1) == '*') {
            return isMatch(text, pattern.substring(2)) || (firstMatch && isMatch(text.substring(1), pattern));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    //    22. Generate Parentheses
    //    https://leetcode.com/problems/generate-parentheses/
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesisBacktracking(result, "", 0, 0, n);
        return result;
    }

    private void generateParenthesisBacktracking(List<String> result, String path, int open, int close, int n) {
        if (path.length() == n * 2) {
            result.add(path);
        } else if (path.length() < n * 2) {
            if (open < n) {
                generateParenthesisBacktracking(result, path + "(", open + 1, close, n);
            }
            if (open > close) {
                generateParenthesisBacktracking(result, path + ")", open, close + 1, n);
            }
        }
    }

    //    23. Merge k Sorted Lists
    //    https://leetcode.com/problems/merge-k-sorted-lists/
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.poll();
            current.next = min;
            current = current.next;
            min = min.next;
            if (min != null) {
                minHeap.add(min);
            }
        }
        return dummy.next;
    }

    //    31. Next Permutation
    //    https://leetcode.com/problems/next-permutation/
    public void nextPermutation(int[] nums) {
        Integer first = null;
        Integer second = null;
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                first = i;
                break;
            }
        }
        if (first != null) {
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] > nums[first]) {
                    second = i;
                    break;
                }
            }
            swap(nums, first, second);
            reverse(nums, first + 1);
        } else {
            reverse(nums, 0);
        }
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    //    32. Longest Valid Parentheses
    //    https://leetcode.com/problems/longest-valid-parentheses/

    // n^2
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            max = Math.max(max, longestValidParenthesesStartWith(i, s));
        }
        return max;
    }

    private int longestValidParenthesesStartWith(int i, String s) {
        int open = 0;
        int close = 0;
        int max = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                max = Math.max(max, open + close);
            } else if (open < close) {
                return max;
            }
        }
        return max;
    }

    // o(n) using stack
    public int longestValidParenthesesStack(String s) {
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    max = Math.max(max, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        return max;
    }

    //    42. Trapping Rain Water
    //    https://leetcode.com/problems/trapping-rain-water/
    public int trap(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int[] lastMax = new int[height.length];
        int[] nextMax = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            if (i == 0) {
                lastMax[i] = height[i];
            } else {
                lastMax[i] = Math.max(lastMax[i - 1], height[i]);
            }
        }
        for (int i = height.length - 1; i >= 0; i--) {
            if (i == height.length - 1) {
                nextMax[i] = height[i];
            } else {
                nextMax[i] = Math.max(nextMax[i + 1], height[i]);
            }
        }
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            result += Math.min(lastMax[i], nextMax[i]) - height[i];
        }
        return result;
    }

    //    48. Rotate Image
    //    https://leetcode.com/problems/rotate-image/
    public void rotate(int[][] matrix) {
        int size = matrix.length;
        boolean[][] visited = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotate4(matrix, visited, i, j, size);
            }
        }
    }

    private void rotate4(int[][] matrix, boolean[][] visited, int i, int j, int size) {
        if (!visited[i][j]) {
            int i1 = i;
            int j1 = j;
            int i2 = j1;
            int j2 = size - i1 - 1;
            int i3 = j2;
            int j3 = size - i2 - 1;
            int i4 = j3;
            int j4 = size - i3 - 1;
            int temp = matrix[i1][j1];
            matrix[i1][j1] = matrix[i4][j4];
            matrix[i4][j4] = matrix[i3][j3];
            matrix[i3][j3] = matrix[i2][j2];
            matrix[i2][j2] = temp;
            visited[i1][j1] = true;
            visited[i2][j2] = true;
            visited[i3][j3] = true;
            visited[i4][j4] = true;
        }
    }

    //    49. Group Anagrams
    //    https://leetcode.com/problems/group-anagrams/
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {

            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(s);
        }
        return new ArrayList<>(map.values());
    }

    //    8. String to Integer (atoi)
    //    https://leetcode.com/problems/string-to-integer-atoi/
    public int myAtoi(String str) {
        int index = 0, sign = 1;
        if(str.length() == 0) return 0;

        while(index < str.length() && str.charAt(index) == ' ')
            index++;

        if(index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        if(index < str.length() && !Character.isDigit(str.charAt(index))) return 0;

        int result = 0;
        while(index < str.length()) {
            if(!Character.isDigit(str.charAt(index))) break;
            char current = str.charAt(index++);
            int previous = result;
            result *= 10;
            if(previous != result/10) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result += (current - '0');
            if(result < 0) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        return result * sign;
    }

    //    41. First Missing Positive
    //    https://leetcode.com/problems/first-missing-positive/
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    //    44. Wildcard Matching
    //    https://leetcode.com/problems/wildcard-matching/
    public boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        int i = 1;
        while (i - 1 < p.length() && p.charAt(i - 1) == '*') {
            dp[0][i] = true;
            i++;
        }

        for (int m = 1; m < dp.length; m++) {
            for (int n = 1; n < dp[0].length; n++) {
                char str = s.charAt(m - 1);
                char pattn = p.charAt(n - 1);
                if (str == pattn || pattn == '?') {
                    dp[m][n] = dp[m - 1][n - 1];
                } else if (pattn == '*') {
                    dp[m][n] = dp[m][n - 1] || dp[m - 1][n];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //    50. Pow(x, n)
    //    https://leetcode.com/problems/powx-n/
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n<0){
            n = -n;
            x = 1/x;
        }
        return (n%2 == 0) ? myPow(x*x, n/2) : x*myPow(x*x, n/2);
    }

    //    54. Spiral Matrix
    //    https://leetcode.com/problems/spiral-matrix/
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int[] direction = new int[]{0, 1};
        int[] current = new int[]{0, -1};
        while (true) {
            if (tryNextStep(current, direction, matrix, visited, result)) {
            } else {
                direction = changeDirection(current, matrix, visited);
                if (direction == null) {
                    break;
                }
            }
        }
        return result;
    }

    private int[] changeDirection(int[] current, int[][] matrix, boolean[][] visited) {
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            if (isValidStep(current, direction, matrix, visited)) {
                return direction;
            }
        }
        return null;
    }

    private boolean isValidStep(int[] current, int[] direction, int[][] matrix, boolean[][] visited) {
        int i = current[0] + direction[0];
        int j = current[1] + direction[1];
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length && !visited[i][j];
    }

    private boolean tryNextStep(int[] current, int[] direction, int[][] matrix, boolean[][] visited, List<Integer> result) {
        int i = current[0] + direction[0];
        int j = current[1] + direction[1];
        if (isValidStep(current, direction, matrix, visited)) {
            result.add(matrix[i][j]);
            visited[i][j] = true;
            current[0] = i;
            current[1] = j;
            return true;
        } else {
            return false;
        }
    }

    //    56. Merge Intervals
    //    https://leetcode.com/problems/merge-intervals/
    public List<Interval> mergeIntervals(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<>();
        }
        Collections.sort(intervals, (o1, o2) -> o1.start - o2.start);
        for (int i = 1; i < intervals.size(); i++) {
            Interval pre = intervals.get(i - 1);
            Interval current = intervals.get(i);
            if (isOverlapping(pre, current)) {
                mergeInterval(pre, current);
            }
        }
        Iterator<Interval> iterator = intervals.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().start == -1) {
                iterator.remove();
            }
        }
        return intervals;
    }

    private void mergeInterval(Interval pre, Interval current) {
        current.start = Math.min(pre.start, current.start);
        current.end = Math.max(pre.end, current.end);
        pre.start = -1;
    }

    private boolean isOverlapping(Interval pre, Interval current) {
        return !(pre.end < current.start || current.end < pre.start);
    }

    //    73. Set Matrix Zeroes
    //    https://leetcode.com/problems/set-matrix-zeroes/
    public void setZeroes(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    setRowAndColumn(matrix, i, j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 5555) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private void setRowAndColumn(int[][] matrix, int i, int j) {
        for (int k = 0; k < matrix.length; k++) {
            if (matrix[k][j] != 0) {
                matrix[k][j] = 5555;
            }
        }
        for (int k = 0; k < matrix[0].length; k++) {
            if (matrix[i][k] != 0) {
                matrix[i][k] = 5555;
            }
        }
    }

    //    76. Minimum Window Substring
    //    https://leetcode.com/problems/minimum-window-substring/
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        String minWindows = null;
        Map<Character, Integer> targetCharCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCharCount.put(c, targetCharCount.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> windowCharCount = new HashMap<>();
        windowCharCount.put(s.charAt(0), 1);
        while (start < s.length() && end < s.length()) {
            if (containsString(targetCharCount, windowCharCount)) {
                if (minWindows == null || end - start + 1 < minWindows.length()) {
                    minWindows = s.substring(start, end + 1);
                }
                windowCharCount.put(s.charAt(start), windowCharCount.get(s.charAt(start)) - 1);
                if (windowCharCount.get(s.charAt(start)) == 0) {
                    windowCharCount.remove(s.charAt(start));
                }
                start++;
            } else {
                end++;
                if (end < s.length()) {
                    windowCharCount.put(s.charAt(end), windowCharCount.getOrDefault(s.charAt(end), 0) + 1);
                }
            }
        }
        return minWindows == null ? "" : minWindows;
    }

    private boolean containsString(Map<Character, Integer> targetCharCount, Map<Character, Integer> windowCharCount) {
        if (targetCharCount.size() > windowCharCount.size()) {
            return false;
        }
        for (Character c : targetCharCount.keySet()) {
            if (windowCharCount.get(c) == null || windowCharCount.get(c) < targetCharCount.get(c)) {
                return false;
            }
        }
        return true;
    }

    //    84. Largest Rectangle in Histogram
    //    https://leetcode.com/problems/largest-rectangle-in-histogram/
    public int largestRectangleArea2(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }

    public static int largestRectangleArea(int height[]) {
        Deque<Integer> s = new ArrayDeque<>();
        int maxArea = 0;
        int tp;
        int i = 0;
        while (i < height.length) {
            if (s.isEmpty() || height[s.peek()] <= height[i])
                s.push(i++);
            else {
                tp = s.peek();
                s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - s.peek() - 1));
            }
        }
        while (!s.isEmpty()) {
            tp = s.peek();
            s.pop();
            maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - s.peek() - 1));

        }
        return maxArea;
    }

    //    105. Construct Binary Tree from Preorder and Inorder Traversal
    //    https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        int inRootIndex = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                inRootIndex = i;
            }
        }
        int leftChildLength = inRootIndex;
        int rightChildLength = inorder.length - leftChildLength - 1;
        if (leftChildLength > 0) {
            root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1 + leftChildLength), Arrays.copyOfRange(inorder, 0, inRootIndex));
        }
        if (rightChildLength > 0) {
            root.right = buildTree(Arrays.copyOfRange(preorder, 1 + leftChildLength, preorder.length), Arrays.copyOfRange(inorder, inRootIndex + 1, inorder.length));
        }
        return root;
    }

    //    116. Populating Next Right Pointers in Each Node
    //    https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    public void connect(TreeLinkNode root) {
        if (root != null) {
            Queue<TreeLinkNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size > 0) {
                    TreeLinkNode out = queue.poll();
                    if (out.left != null) {
                        queue.add(out.left);
                    }
                    if (out.right != null) {
                        queue.add(out.right);
                    }
                    size--;
                    if (size == 0) {
                        out.next = null;
                    } else {
                        out.next = queue.peek();
                    }
                }
            }
        }
    }

    //    138. Copy List with Random Pointer
    //    https://leetcode.com/problems/copy-list-with-random-pointer/
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        Map<RandomListNode, Integer> map = new HashMap<>();
        int index = 0;
        RandomListNode node = head;
        while (node != null) {
            map.put(node, index);
            index++;
            node = node.next;
        }
        RandomListNode[] array = new RandomListNode[map.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = new RandomListNode(0);
        }
        node = head;
        index = 0;
        while (node != null) {
            array[index].label = node.label;
            if (index != map.size() - 1) {
                array[index].next = array[index + 1];
            }
            if (node.random != null) {
                int i = map.get(node.random);
                array[index].random = array[i];
            }
            index++;
            node = node.next;
        }
        return array[0];
    }

    //    133. Clone Graph
    //    https://leetcode.com/problems/clone-graph/
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Set<UndirectedGraphNode> allNodes = new HashSet<>();
        undirectedGraphNodeDFS(node, allNodes);
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        for (UndirectedGraphNode eachNode : allNodes) {
            map.put(eachNode, new UndirectedGraphNode(eachNode.label));
        }
        for (UndirectedGraphNode original : allNodes) {
            UndirectedGraphNode copy = map.get(original);
            copy.neighbors = new ArrayList<>();
            for (UndirectedGraphNode originalNeighbor : original.neighbors) {
                copy.neighbors.add(map.get(originalNeighbor));
            }
        }
        return map.get(node);
    }

    private void undirectedGraphNodeDFS(UndirectedGraphNode node, Set<UndirectedGraphNode> allNodes) {
        if (!allNodes.contains(node)) {
            allNodes.add(node);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                undirectedGraphNodeDFS(neighbor, allNodes);
            }
        }
    }

    //    310. Minimum Height Trees
    //    https://leetcode.com/problems/minimum-height-trees/
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Arrays.asList(0);
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        List<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        int leftCount = n;
        while (leftCount > 2) {
            leftCount -= leaves.size();
            List<Integer> newLeaves = new LinkedList<>();
            for (int leaf : leaves) {
                int neighbor = graph.get(leaf).get(0);
                graph.get(neighbor).remove(Integer.valueOf(leaf));
                if (graph.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }

            }
            leaves = newLeaves;
        }
        return leaves;
    }

    //    332. Reconstruct Itinerary
    //    https://leetcode.com/problems/reconstruct-itinerary/

    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            if (!graph.containsKey(ticket[0])) {
                graph.put(ticket[0], new ArrayList<>());
            }
            graph.get(ticket[0]).add(ticket[1]);
        }
        for (List<String> destinations : graph.values()) {
            Collections.sort(destinations);
        }
        List<String> path = new ArrayList<>();
        path.add("jfk");
        int ticketsLeft = tickets.length;
        path = findItineraryBacktrace(graph, path, ticketsLeft);
        return path;

    }

    private List<String> findItineraryBacktrace(Map<String, List<String>> graph, List<String> path, int ticketsLeft) {
        if (ticketsLeft == 0) {
            return path;
        }
        String curretCity = path.get(path.size() - 1);
        if (graph.get(curretCity) != null && graph.get(curretCity).size() > 0) {
            int destinationSize = graph.get(curretCity).size();
            for (int i = 0; i < destinationSize; i++) {
                String destination = graph.get(curretCity).get(i);
                path.add(destination);
                graph.get(curretCity).remove(i);
                List<String> result = findItineraryBacktrace(graph, path, ticketsLeft - 1);
                if (result != null) {
                    return result;
                }
                path.remove(path.size() - 1);
                graph.get(curretCity).add(i, destination);
            }
        }
        return null;
    }


}










