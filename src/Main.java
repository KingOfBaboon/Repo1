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
            while (currentSize-- > 0) {
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
            while (size-- > 0) {
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
            return;
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
        return (List) stringList;
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
        if (a == '(' && b == ')'
                || a == ')' && b == '('
                || a == '[' && b == ']'
                || a == ']' && b == '['
                || a == '{' && b == '}'
                || a == '}' && b == '{') {
            return true;
        }
        return false;
    }

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

    //    arrays
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
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisity : prerequisites) {
            int from = prerequisity[0];
            int to = prerequisity[1];
            graph.get(from).add(to);
        }
        boolean[] permanentMarked = new boolean[graph.size()];
        boolean[] temperaryMarked = new boolean[graph.size()];
        for (int i = 0; i < numCourses; i++) {
            if (hasCircle(i, graph, permanentMarked, temperaryMarked)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCircle(int node, List<List<Integer>> graph, boolean[] permanentMarked, boolean[] temperaryMarked) {
        if (!permanentMarked[node]) {
            if (temperaryMarked[node]) {
                return true;
            } else {
                temperaryMarked[node] = true;
            }
            for (int connectedNode : graph.get(node)) {
                if (hasCircle(connectedNode, graph, permanentMarked, temperaryMarked)) {
                    return true;
                }
            }
            permanentMarked[node] = true;
        }
        return false;
    }

    //    210. Course Schedule II
    //    https://leetcode.com/problems/course-schedule-ii/description/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graphic = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graphic[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            graphic[pre[0]].add(pre[1]);
        }
        Stack<Integer> postOrder = new Stack<>();
        boolean[] globalMarked = new boolean[numCourses];
        boolean[] localMarked = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(globalMarked, localMarked, graphic, i, postOrder)) {
                return new int[0];
            }
        }
        int[] orders = new int[numCourses];
        for (int i = numCourses - 1; i >= 0; i--) {
            orders[i] = postOrder.pop();
        }
        return orders;
    }

    private boolean hasCycle(boolean[] globalMarked, boolean[] localMarked, List<Integer>[] graphic,
                             int curNode, Stack<Integer> postOrder) {

        if (localMarked[curNode]) {
            return true;
        }
        if (globalMarked[curNode]) {
            return false;
        }
        globalMarked[curNode] = true;
        localMarked[curNode] = true;
        for (int nextNode : graphic[curNode]) {
            if (hasCycle(globalMarked, localMarked, graphic, nextNode, postOrder)) {
                return true;
            }
        }
        localMarked[curNode] = false;
        postOrder.push(curNode);
        return false;
    }


}







