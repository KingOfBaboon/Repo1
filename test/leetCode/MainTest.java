package leetCode;

import dataStructure.ListNode;
import dataStructure.RandomListNode;
import dataStructure.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void findKthLargest() {
        int[] nums1 = {3,3,3,3,3};
        int k1 = 1;
        assertEquals(3, main.findKthLargest(nums1, k1));
    }

    @Test
    public void frequencySort() {
        String input1 = "tree";
        String output = main.frequencySort(input1);
        boolean b = output.equals("eert") || output.equals("eetr");
        assertEquals(true, b);

    }

    @Test
    public void topKFrequent() {
    }

    @Test
    public void sortColors() {
        int[] nums = {2, 0, 1};
        main.sortColors(nums);
        int[] expected = {0, 1, 2};
        assertTrue(Arrays.equals(expected, nums));
    }

    @Test
    public void findContentChildren() {
    }

    @Test
    public void eraseOverlapIntervals() {
    }

    @Test
    public void reconstructQueue() {
        int[][] a = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        main.reconstructQueue(a);
    }

    @Test
    public void partitionLabels() {
    }

    @Test
    public void canPlaceFlowers() {
    }

    @Test
    public void isSubsequence() {
    }

    @Test
    public void checkPossibility() {
        int[] input = {2,3,2,4};
        assertEquals(true, main.checkPossibility(input));
    }

    @Test
    public void maxProfit() {
    }

    @Test
    public void mySqrt() {
        main.mySqrt(4);
    }

    @Test
    public void nextGreatestLetter() {
        System.out.println('a' - 'z');
    }

    @Test
    public void singleNonDuplicate() {
        main.singleNonDuplicate(new int[]{1, 1, 2});
    }

    @Test
    public void firstBadVersion() {
    }

    @Test
    public void findMin() {
        main.findMin(new int[]{1,2});
    }

    @Test
    public void diffWaysToCompute() {
        main.diffWaysToCompute("2-1-1");
    }

    @Test
    public void numSquares_bfs() {
    }

    @Test
    public void numSquares_dp() {
    }

    @Test
    public void ladderLength() {
        int a = main.ladderLength("hit", "cog", new LinkedList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(a);
    }

    @Test
    public void maxAreaOfIsland() {
    }

    @Test
    public void numIslands() {
        main.numIslands(new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}});
    }

    @Test
    public void letterCombinations() {
    }

    @Test
    public void restoreIpAddresses() {
        System.out.println(main.restoreIpAddresses("11111111111111111111111111111111111111"));
    }

    @Test
    public void climbStairs() {
        main.climbStairs(4);
    }

    @Test
    public void rob() {
    }

    @Test
    public void countPrimes() {
        System.out.println(main.countPrimes(499979));
    }

    @Test
    public void getIntersectionNode() {
    }

    @Test
    public void reverseList_tieration() {
    }

    @Test
    public void reverseList_recursive() {
    }

    @Test
    public void maxDepth() {
    }

    @Test
    public void isBalanced() {
    }

    @Test
    public void diameterOfBinaryTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        main.diameterOfBinaryTree(node1);
    }

    @Test
    public void isValid() {
        main.isValid("()");
    }

    @Test
    public void dailyTemperatures() {
        main.dailyTemperatures(new int[]{5, 4, 3, 6});
    }

    @Test
    public void twoSum_no1() {
    }

    @Test
    public void findLHS() {
    }

    @Test
    public void longestConsecutive() {
    }

    @Test
    public void isAnagram() {
    }

    @Test
    public void longestPalindrome() {
    }

    @Test
    public void isIsomorphic() {
        System.out.println((int) 'a');
        System.out.println((int) 'A');
        System.out.println((int) 'z');
        System.out.println((int) 'Z');
        main.isIsomorphic("ab", "aa");
    }

    @Test
    public void countSubstrings() {
    }

    @Test
    public void countBinarySubstrings() {
    }

    @Test
    public void moveZeroes() {
    }

    @Test
    public void matrixReshape() {
    }

    @Test
    public void findMaxConsecutiveOnes() {
    }

    @Test
    public void searchMatrix() {
    }

    @Test
    public void isBipartite() {
    }

    @Test
    public void canFinish() {
    }

    @Test
    public void findOrder() {
        main.findOrder(2, new int[][]{{1, 0}});
    }

    @Test
    public void findCircleNum() {
        int[][] a =new int[1][1];
        a[0][0] = 1;
        main.findCircleNum(a);
    }

    @Test
    public void solve() {
    }

    @Test
    public void pacificAtlantic() {
    }

    @Test
    public void exist() {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        main.exist(board, word);
    }

    @Test
    public void binaryTreePaths() {
    }

    @Test
    public void permute() {
    }

    @Test
    public void permuteBacktrack() {
    }

    @Test
    public void permuteUnique() {
    }

    @Test
    public void combine() {
        main.combine(4, 2);
    }

    @Test
    public void combinationSum() {
        main.combinationSum(new int[]{2, 3, 6, 7}, 7);
    }

    @Test
    public void combinationSum2() {
    }

    @Test
    public void combinationSum3() {
    }

    @Test
    public void subsets() {
    }

    @Test
    public void subsetsWithDup() {
    }

    @Test
    public void partition() {
    }

    @Test
    public void solveSudoku() {
        char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        main.solveSudoku(board);
    }


    @Test
    public void solveNQueens() {
        main.solveNQueens(6);
    }

    @Test
    public void rob2() {
        main.rob2(new int[]{1});
    }

    @Test
    public void minPathSum() {
    }

    @Test
    public void uniquePaths() {
    }

    @Test
    public void numberOfArithmeticSlices() {
    }

    @Test
    public void integerBreak() {
    }

    @Test
    public void numDecodings() {
        assertEquals(0, main.numDecodings("0"));
        assertEquals(0, main.numDecodings("0000"));
        assertEquals(0, main.numDecodings("001"));
        assertEquals(0, main.numDecodings("010"));
        assertEquals(2, main.numDecodings("12"));
        assertEquals(0, main.numDecodings("100"));
        assertEquals(1, main.numDecodings("101"));
    }

    @Test
    public void lengthOfLIS_DP() {
    }

    @Test
    public void lengthOfLIS_Tail() {
        main.lengthOfLIS_Tail(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
    }

    @Test
    public void findLongestChain() {
    }

    @Test
    public void findLongestChain_Greedy() {
    }

    @Test
    public void justify() {
        assertEquals("The  quick  brown  fox  jumps  over  the  lazy  dog.", main.justify("The quick brown fox jumps over the lazy dog.", 52));
        assertEquals("foo   bar  bar  foo.", main.justify("foo bar bar foo.", 20));
    }

    @Test
    public void fullJustify() {
        List<String> result = main.fullJustify(new String[]{"Here", "is", "an", "example", "of", "text", "justification."}, 14);
        System.out.println(result);
    }

    @Test
    public void wiggleMaxLength() {
        main.wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8});
    }

    @Test
    public void knapsack() {
        int a = main.knapsack(5, 3, new int[]{1, 2, 3}, new int[]{6, 10, 12});
        System.out.println(a);
    }

    @Test
    public void knapsack2() {
        int a = main.knapsack2(5, 3, new int[]{1, 2, 3}, new int[]{6, 10, 12});
        System.out.println(a);
    }

    @Test
    public void canPartition() {
        System.out.println(main.canPartition(new int[]{1, 1}));
    }

    @Test
    public void canPartitionBack() {
        boolean b = main.canPartitionBackpack(new int[]{1, 2, 5});
        System.out.println(b);
    }

    @Test
    public void canPartitionBack2() {
    }

    @Test
    public void findTargetSumWays() {
        int a = main.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(a);
    }

    @Test
    public void findTargetSumWaysDFS() {
    }

    @Test
    public void wordBreak() {
        main.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
    }

    @Test
    public void findMaxForm() {
    }

    @Test
    public void coinChange() {
    }

    @Test
    public void maxProfit1() {
    }

    @Test
    public void maxProfit2() {
    }

    @Test
    public void maxProfit3() {
        main.maxProfit3(new int[]{3, 2, 6, 5, 0, 3});

    }

    @Test
    public void combinationSum4() {
    }

    @Test
    public void maxProfit3SpaceOptimize() {
        main.maxProfit3SpaceOptimize(new int[]{3, 2, 6, 5, 0, 3});
    }

    @Test
    public void maxProfit4SpaceOptimize() {
    }

    @Test
    public void maxProfit5() {
    }

    @Test
    public void maxProfit6() {
    }

    @Test
    public void minDistance() {
    }

    @Test
    public void minDistance72() {
        int a = main.minDistance72("horse", "ros");
        System.out.println(a);
    }

    @Test
    public void minSteps() {
        int a = Integer.valueOf("1");
        int b = (int) "1".charAt(0);
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void minStepsDP() {
        main.minStepsDP(6);
    }

    @Test
    public void convertToBase7() {
        char a = (char) 45345345;
        System.out.println(a);
        System.out.println(main.convertToBase7(100));
        System.out.println(main.convertToBase7(-7));
        System.out.println(main.convertToBase7(-0));
    }

    @Test
    public void convertToTitle() {
    }

    @Test
    public void trailingZeroes() {
    }

    @Test
    public void addBinary() {
        System.out.println(main.addBinary("11", "1"));
        System.out.println();
    }

    @Test
    public void addStrings() {
    }

    @Test
    public void minMoves2() {
    }

    @Test
    public void majorityElement() {
    }

    @Test
    public void majorityElementSort() {
    }

    @Test
    public void isPerfectSquare() {
        main.isPerfectSquare(2147483647);
    }

    @Test
    public void isPowerOfThree() {
    }

    @Test
    public void productExceptSelf() {
    }

    @Test
    public void maximumProduct() {
    }

    @Test
    public void mergeTwoLists() {
    }

    @Test
    public void deleteDuplicates() {
        ListNode a = new ListNode(new int[]{1, 1, 2, 3, 3});
        a.printListNodes();
        main.deleteDuplicates(a);
        a.printListNodes();
    }

    @Test
    public void removeNthFromEnd() {
        ListNode a = new ListNode(new int[]{1, 2,3,4,5});
        ListNode b = main.removeNthFromEnd(a, 3);
        b.printListNodes();
    }

    @Test
    public void swapPairs() {
        ListNode a = new ListNode(new int[]{1, 2, 3, 4});
        ListNode b = main.swapPairs(a);
        b.printListNodes();
    }

    @Test
    public void addTwoNumbers() {
        main.addTwoNumbers(new ListNode(new int[]{7, 2, 4, 3}), new ListNode(new int[]{5, 6, 4})).printListNodes();
    }

    @Test
    public void isPalindrome2() {
        int a = 5;
        while (a-- > 0) {
            System.out.println(a);
        }
    }

    @Test
    public void splitListToParts() {
        ListNode a = new ListNode(new int[]{1, 2, 3, 4});
        main.splitListToParts(a, 5);
    }

    @Test
    public void oddEvenList() {
    }

    @Test
    public void invertTree() {
    }

    @Test
    public void mergeTrees() {
    }

    @Test
    public void hasPathSum() {
    }

    @Test
    public void pathSum113() {
    }

    @Test
    public void pathSum() {
    }

    @Test
    public void isSubtree() {
    }

    @Test
    public void isSymmetric() {
    }

    @Test
    public void minDepth() {
    }

    @Test
    public void sumOfLeftLeaves() {
    }

    @Test
    public void longestUnivaluePath() {
    }

    @Test
    public void findSecondMinimumValue() {
    }

    @Test
    public void averageOfLevels() {
        TreeNode a = new TreeNode(3);
        main.averageOfLevels(a);
    }

    @Test
    public void findBottomLeftValue() {
    }

    @Test
    public void preorderTraversal() {
    }

    @Test
    public void postorderTraversal() {
    }

    @Test
    public void inorderTraversal() {
    }

    @Test
    public void trimBST() {
    }

    @Test
    public void kthSmallest() {
        TreeNode a = new TreeNode(Arrays.asList(3, 1, 4, null, 2));
        main.kthSmallest(a, 1);
    }

    @Test
    public void convertBST() {
    }

    @Test
    public void lowestCommonAncestor() {
    }

    @Test
    public void lowestCommonAncestor236() {
    }

    @Test
    public void sortedArrayToBST() {
    }

    @Test
    public void sortedListToBST() {
    }

    @Test
    public void findTarget() {
    }

    @Test
    public void getMinimumDifference() {
    }

    @Test
    public void findMode() {
        TreeNode a = new TreeNode(Arrays.asList(1, null, 2, 2));
        main.findMode(a);
    }

    @Test
    public void trie() {
        Main.Trie trie = new Main.Trie();
        trie.insert("apple");
        trie.search("apple");   // returns true
        trie.search("app");     // returns false
        trie.startsWith("app"); // returns true
        trie.insert("app");
        trie.search("app");     // returns true
    }

    @Test
    public void mapSum() {
        Main.MapSum mapSum = new Main.MapSum();
        mapSum.insert("apple", 3);
        mapSum.sum("ap");
    }

    @Test
    public void minStack() {
        Main.MinStack minStack = new Main.MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
    }

    @Test
    public void nextGreaterElements() {
    }

    @Test
    public void containsDuplicate() {
    }

    @Test
    public void kthSmallestMatrix() {
        int[][] a = new int[5][5];
        a[0] = new int[]{1, 4, 7, 11, 15};
        a[1] = new int[]{2, 5, 8, 12, 19};
        a[2] = new int[]{3, 6, 9, 16, 22};
        a[3] = new int[]{10, 13, 14, 17, 24};
        a[4] = new int[]{18, 21, 23, 26, 30};
        main.kthSmallest(a, 5);
    }

    @Test
    public void findDuplicate() {
        main.findDuplicate(new int[]{3, 1, 3, 4, 2});
    }

    @Test
    public void findErrorNums() {
        main.findErrorNums(new int[]{4,2,4,3,1,6,8,5});
    }

    @Test
    public void findDisappearedNumbers() {
    }

    @Test
    public void findDuplicates() {
    }

    @Test
    public void constructArray() {
        main.constructArray(10, 2);
        main.constructArray(10, 3);
        main.constructArray(10, 6);
    }

    @Test
    public void findShortestSubArray() {
        main.findShortestSubArray(new int[]{2, 1, 1, 2, 1, 3, 3, 3, 1, 3, 1, 3, 2});
    }

    @Test
    public void isToeplitzMatrix() {
    }

    @Test
    public void arrayNesting() {
        int a = main.arrayNesting(new int[]{5, 4, 0, 3, 1, 6, 2});
        System.out.println(a);
    }

    @Test
    public void maxChunksToSorted() {
        main.maxChunksToSorted(new int[]{4, 1, 0, 2, 3, 5, 6});
    }


    @Test
    public void canPartitionBackpack() {
    }

    @Test
    public void canPartitionBackpack2() {
    }

    @Test
    public void sortList() {
        ListNode a = new ListNode(new int[]{5, 4, 6, 9, 1});
        a = main.sortList(a);
        a.printListNodes();
    }

    @Test
    public void sortListRecursive() {
        ListNode a = new ListNode(new int[]{5, 4, 6, 9, 1});
        main.sortListRecursive(a);
    }

    @Test
    public void findRedundantConnection() {
        System.out.println("shit".hashCode());
        System.out.println("shit".hashCode());
        String s = "shit";
        System.out.println(s.hashCode());
        main.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}});
    }

    @Test
    public void countNodes() {
        TreeNode node = new TreeNode(Arrays.asList(1, 2, 3, 4, 5, 6, null));
        int a = main.countNodes(node);
        assertEquals(6, a);
    }

    @Test
    public void countNodes2() {
    }

    @Test
    public void addTwoNumbers2() {
    }

    @Test
    public void lengthOfLongestSubstring() {
        int a = main.lengthOfLongestSubstring("pwwkew");
        System.out.println(a);
    }

    @Test
    public void isMatch() {
    }

    @Test
    public void generateParenthesis() {
        main.generateParenthesis(3);
    }

    @Test
    public void mergeKLists() {
    }

    @Test
    public void nextPermutation() {
        main.nextPermutation(new int[]{1, 3, 2});

    }

    @Test
    public void longestValidParentheses() {
    }

    @Test
    public void longestValidParentheses2() {
    }

    @Test
    public void longestValidParenthesesStack() {
    }

    @Test
    public void trap() {
        int a = main.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(a);
    }

    @Test
    public void rotate() {
        main.rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

    @Test
    public void groupAnagrams() {
    }

    @Test
    public void myAtoi() {
    }

    @Test
    public void firstMissingPositive() {
    }

    @Test
    public void isMatch2() {
        System.out.println(System.currentTimeMillis());
        boolean a = main.isMatch2(
                "aa",
                "*");
        System.out.println(a);
        System.out.println(System.currentTimeMillis());

    }

    @Test
    public void myPow() {
    }

    @Test
    public void spiralOrder() {
        List<Integer> result = main.spiralOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
        System.out.println(result);
    }

    @Test
    public void mergeIntervals() {
        List<Main.Interval> intervals = new ArrayList<>();
        intervals.add(new Main.Interval(1, 3));
        intervals.add(new Main.Interval(2, 6));
        intervals.add(new Main.Interval(8, 10));
        intervals.add(new Main.Interval(15, 18));
        List<Main.Interval> result = main.mergeIntervals(intervals);

    }

    @Test
    public void setZeroes() {
    }

    @Test
    public void minWindow() {
        String a = main.minWindow("cabwefgewcwaefgcf",
                "cae");
        System.out.println(a);
    }

    @Test
    public void largestRectangleArea() {
        main.largestRectangleArea(new int[]{3,2,1});
    }

    @Test
    public void largestRectangleArea2() {
    }

    @Test
    public void buildTree() {
    }

    @Test
    public void connect() {
    }

    @Test
    public void copyRandomList() {
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        RandomListNode node5 = new RandomListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node1.random = node3;
        node2.random = node4;
        node4.random = node1;
        node5.random = node1;

        main.copyRandomList(node1);
    }

    @Test
    public void canFinishDFS() {
    }

    @Test
    public void canFinishKahn() {
    }

    @Test
    public void findOrderKahn() {
    }

    @Test
    public void cloneGraph() {
    }

    @Test
    public void findMinHeightTrees() {
    }

    @Test
    public void findItinerary() {
        main.findItinerary(new String[][]{
                {"jfk", "a"},
                {"jfk", "d"},
                {"a", "c"},
                {"c", "d"},
                {"c", "jfk"},
                {"b", "c"},
                {"d", "b"},
                {"d", "a"}
        });
    }
}


