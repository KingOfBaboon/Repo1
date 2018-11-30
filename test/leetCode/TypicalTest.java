package leetCode;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypicalTest {
    Typical main = new Typical();

    @Test
    public void quickSort() {
        int[] array1 = new int[]{2, 1};
        main.quickSort(array1, 0, 1);

        int[] array2 = new int[]{3, 2, 1,3,3};
        main.quickSort(array2, 0, array2.length - 1);

        return;
    }

    @Test
    public void twoSum() {
    }

    @Test
    public void qsort3way() {
        main.qsort3way(new int[]{6, 7, 1, 2,6,6,6,6,6, 3, 6, 7, 4}, 0, 7);
    }

    @Test
    public void findKthLargest() {
        main.findKthLargest(new int[]{3, 2, 1, 5, 6, 4, 2}, 2);
    }

    @Test
    public void topKFrequent() {
    }

    @Test
    public void sortColors() {
        main.sortColors(new int[]{2, 0, 1});
    }

    @Test
    public void eraseOverlapIntervals() {
    }

    @Test
    public void binarySearch() {
        main.binarySearch(new int[]{0,1}, 1);
    }

    @Test
    public void mySqrt() {
        main.mySqrt(8);
    }

    @Test
    public void permute() {
    }

    @Test
    public void canPartition() {
        main.canPartition(new int[]{3, 3, 3, 4, 5});
    }
}