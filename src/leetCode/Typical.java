package leetCode;

import java.util.*;

public class Typical {

    // two pointers
    //    167. Two Sum II - Input array is sorted
    //    https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                return new int[]{start + 1, end + 1};
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return null;
    }

    // quick sort
    public void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int p = partition(array, start, end);
            quickSort(array, start, p - 1);
            quickSort(array, p + 1, end);
        }
    }

    private int partition(int[] array, int start, int end) {
        int i = start;
        int j = end + 1;
        int p = array[start];
        while (true) {
            while (array[++i] < p && i < end);
            while (array[--j] > p && j > start) ;
            if (i < j) {
                swap(array, i, j);
            } else {
                break;
            }
        }
        swap(array, start, j);
        return j;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //    215. Kth Largest Element in an Array
    //    https://leetcode.com/problems/kth-largest-element-in-an-array/description/
    public int findKthLargest(int[] nums, int k) {
        int targetIndex = nums.length - k;
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            int p = partition(nums, l, h);
            if (p == targetIndex) {
                break;
            } else if (p < targetIndex) {
                l = p + 1;
            } else {
                h = p - 1;
            }
        }
        return nums[targetIndex];
    }

    // bucket sort
    //    347. Top K Frequent Elements
    //    https://leetcode.com/problems/top-k-frequent-elements/description/
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numToFrequency = new HashMap<>();
        for (int i : nums) {
            numToFrequency.put(i, numToFrequency.getOrDefault(i, 0) + 1);
        }
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int num : numToFrequency.keySet()) {
            int frequency = numToFrequency.get(num);
            buckets.get(frequency).add(num);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.size() - 1; i >= 0 && k > 0; i--) {
            if (buckets.get(i).size() > 0) {
                result.addAll(buckets.get(i));
                k -= buckets.get(i).size();
            }
        }
        return result;
    }


    // 3 way quick sort
    public void qsort3way (int[] array, int lo,int hi ) {
        if (lo >= hi) {
            return;  //单个元素或者没有元素的情况}
        }
        int lt = lo;
        int i = lo + 1;  //第一个元素是切分元素，所以指针i可以从lo+1开始
        int gt = hi;
        int v = array[lo];
        while (i <= gt) {
            if (array[i] < v)  //小于切分元素的放在lt左边，因此指针lt和指针i整体右移
                swap(array, lt++, i++);
            else if (array[i] > v)  //大于切分元素的放在gt右边，因此指针gt需要左移
                swap(array, i, gt--);
            else
                i++;
        }
        //lt-gt的元素已经排定，只需对it左边和gt右边的元素进行递归求解
        qsort3way(array, lo, lt - 1);
        qsort3way(array, gt + 1, hi);
    }

    //    75. Sort Colors
    //    https://leetcode.com/problems/sort-colors/description/
    public void sortColors(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        int i = 0;
        while (i <= h) {
            if (nums[i] == 0) {
                swap(nums, i, l);
                i++;
                l++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, h);
                h--;
            }
        }
    }

    // greedy
    //    435. Non-overlapping Intervals
    //    https://leetcode.com/problems/non-overlapping-intervals/description/
    public int eraseOverlapIntervals(Main.Interval[] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1.end - i2.end);
        int minCount = 0;
        int currentEnd = Integer.MIN_VALUE;
        for (Main.Interval interval : intervals) {
            if (interval.start >= currentEnd) {
                minCount++;
                currentEnd = interval.end;
            }
        }
        return intervals.length - minCount;
    }

    // binary search
    public int binarySearch(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] == key) {
                return m;
            } else if (nums[m] > key) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

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
            } else if (x / m< m) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    // backtrace
    //    46. Permutations
    //    https://leetcode.com/problems/permutations/description/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permuteBacktrace(nums, result, path, visited);
        return result;
    }

    private void permuteBacktrace(int[] nums, List<List<Integer>> result, List<Integer> path, boolean[] visited) {
        if (path.size() == visited.length) {
            result.add(new ArrayList<>(path));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    path.add(nums[i]);
                    permuteBacktrace(nums, result, path, visited);
                    visited[i] = false;
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    // 01 backpack
    //    416. Partition Equal Subset Sum
    //    https://leetcode.com/problems/partition-equal-subset-sum/description/
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j] || (j - nums[i - 1] >= 0 && dp[i - 1][j - nums[i - 1]]);
            }
        }
        return dp[nums.length][target];
    }


}
