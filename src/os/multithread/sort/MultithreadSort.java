package os.multithread.sort;

import java.util.*;
import java.util.Random; // import random generator lib
import java.util.concurrent.*;

@SuppressWarnings("rawtypes")
public class MultithreadSort {

  /** threshold to apply insertion sort */
  private static final int threshold = 100; // final is used for constant, static is used for direct access without object and make it it in global space
  private static Random randomGen = new Random(); // random variable object of class Random
  private static final int ArrayLength = randomGen.nextInt(900080); // generates a random value out of 900080

  //	private static final int ArrayLength = 10000;

  @SuppressWarnings("serial")
  //nested class

  /********************************* QUICK SORT CODE BEGIN ********************************************************/
  static class QuickSort extends RecursiveAction //with the array being divided into small manageable chunks and each part being sorted on a separate core. //For example, sorting a large array can be easily implemented with RecursiveAction,
  {

    private Comparable[] Quick_array; // Comparable - is an interface
    private int low, high;

    public QuickSort(Comparable[] array, int low, int high) { // constructor
      if (array == null) {
        System.out.println("Empty Array!\n");
        return;
      }
      this.Quick_array = array; // Copying the array 'array' to Quick_array for comparison
      this.low = low; // Copying a variable Low indicating the first position of array
      this.high = high; // Copying a variable Higher indicating the last position of array
    }

    // the method where Multithreading will occur
    @Override
    protected void compute() {
      // In case the length of the array is more than the higher limit, then insertionsort will be implemented.
      if (low + threshold > high) insertionsort();
      //Use QuickSort to sort the elements
      else {
        if (low < high) {
          int partitionIndex = partition(); // Defining the partition index

          QuickSort leftSort = new QuickSort(
            Quick_array,
            low,
            partitionIndex - 1
          );
          QuickSort rightSort = new QuickSort(
            Quick_array,
            partitionIndex + 1,
            high
          );
          invokeAll(leftSort, rightSort);
        }
      }
    }

    private int partition() {
      Comparable P = Quick_array[high]; //Defining P as the pivot

      int m = (low - 1);

      for (int n = low; n < high; n++) {
        if (Quick_array[n].compareTo(P) <= 0) { // comparing the nth element of the array with the pivot
          // If nth element is less than pivot element than swapping is done.
          m++;

          Comparable Temp = Quick_array[m];
          Quick_array[m] = Quick_array[n];
          Quick_array[n] = Temp;
        }
      }
      // Putting the pivot element in place and return the partitioning position
      Comparable Temp = Quick_array[m + 1];
      Quick_array[m + 1] = Quick_array[high];
      Quick_array[high] = Temp;

      return m + 1;
    }

    private void insertionsort() {
      for (int k = low + 1; k <= high; k++) {
        Comparable tmp = Quick_array[k];
        int n;

        for (
          n = k;
          n > low && tmp.compareTo(Quick_array[n - 1]) < 0;
          n--
        ) Quick_array[n] = Quick_array[n - 1];
        Quick_array[n] = tmp;
      }
    }
  }

  /********************************* QUICK SORT CODE END ********************************************************/

  /********************************* MERGE SORT CODE BEGIN ********************************************************/
  //Time Complexity  = O(nlogn)
  //logn + logn + n
  // to divide right and left logn, combine - n
  @SuppressWarnings("serial")
  static class MergeSort extends RecursiveAction {

    private Comparable[] Merge_array;
    private int low, high;

    public MergeSort(Comparable[] array, int low, int high) { // constructor
      if (array == null) {
        System.out.println("Empty Array!\n");
        return;
      }
      this.Merge_array = array;
      this.low = low;
      this.high = high;
    }

    // the method where multi-threaded computing will occur
    @Override
    protected void compute() {
      if (low + threshold > high) insertionsort(low, high); else {
        if (low < high) {
          // find middle
          int middle = low + (high - low) / 2;

          // sort left half : O(logn)
          MergeSort leftSort = new MergeSort(Merge_array, low, middle);

          // sort right half : O(logn)
          MergeSort rightSort = new MergeSort(Merge_array, middle + 1, high);

          invokeAll(leftSort, rightSort);

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

    //insertion sort
    private void insertionsort(int low, int high) {
      for (int i = low + 1; i <= high; i++) {
        Comparable temp = Merge_array[i];
        int j;

        for (
          j = i;
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

  public static void main(String[] args) throws InterruptedException {
    // create a pool of threads
    ForkJoinPool threadPool = new ForkJoinPool();

    long LogStart;
    long LogEnd;

    // two arrays are decalred, one is created with array_length and then copied into other with random elements
    Integer[] array1, array2;

    try {
      array1 = Array_generate(ArrayLength);
      array2 = new Integer[ArrayLength];
      for (int i = 0; i < ArrayLength; i++) {
        array2[i] = array1[i];
      }
    } catch (OutOfMemoryError e) {
      System.out.println("Memory Error!\n");
      return;
    }

    MergeSort mergeSort = new MergeSort(array1, 0, array1.length - 1);

    // Get the current time before sorting
    LogStart = System.currentTimeMillis();

    try {
      threadPool.invoke(mergeSort);
    } catch (StackOverflowError e) {
      System.out.println("Stack overflow!\n");
      //e.printStackTrace();
      return;
    } catch (OutOfMemoryError e) {
      System.out.println("Memory Error!\n");
      return;
    }

    // Get the current time after sorting
    LogEnd = System.currentTimeMillis();

    System.out.println(
      "MergeSort Time elapsed: " + (LogEnd - LogStart) + " ms"
    );

    QuickSort quickSort = new QuickSort(array2, 0, array2.length - 1);

    // Get the current time before sorting
    LogStart = System.currentTimeMillis();

    try {
      threadPool.invoke(quickSort);
    } catch (StackOverflowError e) {
      System.out.println("Stack overflow!\n");

      return;
    } catch (OutOfMemoryError e) {
      System.out.println("Memory error!\n");

      return;
    }

    // Get the current time after sorting
    LogEnd = System.currentTimeMillis();

    System.out.println(
      "QuickSort Time elapsed: " + (LogEnd - LogStart) + " ms"
    );
  }
}
