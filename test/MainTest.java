import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
        boolean b = main.canPartitionBack(new int[]{1, 2, 5});
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
        String s = "12";
        s = s.substring(2);

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
    public void maxProfitDpCompact2() {
        main.maxProfitDpCompact2(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
    }

    @Test
    public void maxProfit3SpaceOptimize() {
        main.maxProfit3SpaceOptimize(new int[]{3, 2, 6, 5, 0, 3});
    }

    @Test
    public void maxProfit4SpaceOptimize() {
    }
}