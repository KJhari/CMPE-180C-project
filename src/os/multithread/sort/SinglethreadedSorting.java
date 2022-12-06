package os.multithread.sort;

import java.util.Random;
@SuppressWarnings("rawtypes")
public class SinglethreadedSorting {

  /** Threshold to apply insertion sort */
  private static final int threshold = 100;
  private static final int ArrayLength = 100;

  /********************************* QUICK SORT CODE BEGIN ********************************************************/

  static class QuickSort {

    private Comparable[] quick_array;

    public QuickSort(Comparable[] array) {
      quick_array = array;
    }

    public void sort_quick() {
      quicksort(0, quick_array.length - 1);
    }

    private void quicksort(int low, int high) {
      if (low + threshold > high) insertionsort(low, high); else {
        if (low < high) {
          int pI = partition(low, high);

          quicksort(low, pI - 1);
          quicksort(pI + 1, high);
        }
      }
    }

    private int partition(int low, int high) {
      Comparable P = quick_array[high];

      int i = (low - 1);

      for (int j = low; j < high; j++) {
        if (quick_array[j].compareTo(P) <= 0) {
          i++;

          Comparable Temp = quick_array[i];
          quick_array[i] = quick_array[j];
          quick_array[j] = Temp;
        }
      }

      Comparable Temp = quick_array[i + 1];
      quick_array[i + 1] = quick_array[high];
      quick_array[high] = Temp;

      return i + 1;
    }

    private void insertionsort(int low, int high) {
      for (int p = low + 1; p <= high; p++) {
        Comparable temp = quick_array[p];
        int j;

        for (
          j = p;
          j > low && temp.compareTo(quick_array[j - 1]) < 0;
          j--
        ) quick_array[j] = quick_array[j - 1];
        quick_array[j] = temp;
      }
    }
  }

  /********************************* QUICK SORT CODE END ********************************************************/

  /********************************* MERGE SORT CODE BEGIN ********************************************************/

  static class MergeSort {

    private Comparable[] Merge_array;

    public MergeSort(Comparable[] array) { // constructor
      Merge_array = array;
    }

    public void sort_merge() {
      mergesort(0, Merge_array.length - 1);
    }

    private void mergesort(int low, int high) {
      if (low + threshold > high) insertionsort(low, high); else {
        if (low < high) {
          // find middle
          int middle = low + (high - low) / 2;

          // sort left half : O(logn)
          mergesort(low, middle);
          // sort right half : O(logn)
          mergesort(middle + 1, high); // sort right half

          // merge the sorted halves : O(n)
          merge(low, middle, high);
        }
      }
    }

    // Merges two subarrays : array A(low->mid) and array B(mid+1->high)
    private void merge(int low, int mid, int high) {
      int i = 0, j = 0;
      int k = low;

      // Find sizes of two Array A(1) and B(2) to be merged
      int array1_size = mid - low + 1;
      int array2_size = high - mid;

      // Create temporary arrays  of comparable type
      Comparable[] left_array = new Comparable[array1_size];
      Comparable[] right_array = new Comparable[array2_size];

      // temporary arrays are created to copy data
      for (int l = 0; l < array1_size; ++l) {
        left_array[l] = Merge_array[low + l];
      }

      for (int m = 0; m < array2_size; ++m) {
        right_array[m] = Merge_array[mid + 1 + m];
      }

      // merge arrays while comparing elements
      while (i < array1_size && j < array2_size) {
        //check left array element with right array element
        //https://www.geeksforgeeks.org/java-integer-compareto-method/
        if (left_array[i].compareTo(right_array[j]) <= 0) {
          //left array element in greater
          Merge_array[k] = left_array[i];
          i++;
        } else {
          //right array element in greater
          Merge_array[k] = right_array[j];
          j++;
        }
        k++;
      }

      // remaining elements of array A is copied
      while (i < array1_size) {
        Merge_array[k] = left_array[i];
        i++;
        k++;
      }

      // remaining elements of array B is copied
      while (j < array2_size) {
        Merge_array[k] = right_array[j];
        j++;
        k++;
      }
    }

    private void insertionsort(int low, int high) {
      for (int p = low + 1; p <= high; p++) {
        Comparable temp = Merge_array[p];
        int j;

        for (
          j = p;
          j > low && temp.compareTo(Merge_array[j - 1]) < 0;
          j--
        ) Merge_array[j] = Merge_array[j - 1];
        Merge_array[j] = temp;
      }
    }
  }

  /********************************* MERGE SORT CODE END ********************************************************/

  static Integer[] Array_generate(final int size) {
    Integer[] array = new Integer[size];
    Random rand = new Random();
    for (int i = 0; i < size; i++) {
      array[i] = rand.nextInt(1000);
    }
    return array;
  }

  public static void main(String[] args) {
    long LogStart;
    long LogEnd;

    // Create two arrays of size ArrayLength, with the same randomly generated elements
    Integer[] array1 = Array_generate(ArrayLength);
    Integer[] array2 = new Integer[ArrayLength];
    for (int i = 0; i < ArrayLength; i++) {
      array2[i] = array1[i];
    }

    MergeSort mergeSort = new MergeSort(array1);

    //start Timelog
    LogStart = System.currentTimeMillis();

    mergeSort.sort_merge();

    //end Timelog
    LogEnd = System.currentTimeMillis();

    System.out.println(
      "Time taken with MergeSort: " + (LogEnd - LogStart) + " ms"
    );

    QuickSort quickSort = new QuickSort(array2);

    //start Timelog
    LogStart = System.currentTimeMillis();

    quickSort.sort_quick();

    //end Timelog
    LogEnd = System.currentTimeMillis();

    System.out.println(
      "Time taken with QuickSort: " + (LogEnd - LogStart) + " millis"
    );
  }
}
