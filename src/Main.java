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
        Arrays.sort(intervals, (o1, o2) -> o1.end - o2.end);
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
            int i = queenCount;
            for (int j = 0; j < n; j++) {
                if (occupied[i][j] == 0) {
                    board[i][j] = 'Q';
                    solveNQueensModifyOccupied(occupied, i, j, n, 1);
                    solveNQueensBacktracing(n, queenCount + 1, result, board, occupied);
                    board[i][j] = '.';
                    solveNQueensModifyOccupied(occupied, i, j, n, -1);
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
        if (i > 0 && i <= 26) {
            return true;
        } else {
            return false;
        }
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
            String s = String.join(" ", Arrays.copyOfRange(words, start, end));
            while (s.length() < maxWidth) {
                s += " ";
            }
            result.add(s);
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
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> curretSet = new HashSet<>(subsetSums);
            for (int j : curretSet) {
                if (j + nums[i] == target) {
                    return true;
                }
                subsetSums.add(j + nums[i]);
            }
        }
        return false;
    }

    public boolean canPartitionBack(int[] nums) {
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

    public boolean canPartitionBack2(int[] nums) {
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
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j > 0; j--) {
                if (nums[i] <= j) {
                    subsetSum[j] = subsetSum[j - nums[i]];
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
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            profit = Math.max(profit, prices[i] - minPrice);
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
            return root;
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
        Set<Integer> set = new HashSet<Integer>();
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
            int size = queue.size();
            int count = size;
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







