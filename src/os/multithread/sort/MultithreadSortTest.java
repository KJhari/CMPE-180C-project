package os.multithread.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import os.multithread.sort.MultithreadSort.MergeSort;
import os.multithread.sort.MultithreadSort.QuickSort;



public class MultithreadSortTest {
	

	ForkJoinPool testPool = new ForkJoinPool();
	MultithreadSort multithreadedtest;
	
	//creating an array of random size
	private static Integer[] createTestArray(final int size) {
		Integer[] array = new Integer[size];
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			array[i] = rand.nextInt(1000);
		}
		return array;
	}
	
	//Create Mixed Integer Array//
	private static Integer[] createMixTestArray(final int size, final int low, final int high) {
			Integer[] array = new Integer[size];
			Random rand = new Random();
			for (int i = 0; i < size; i++) {
				array[i] = (int)((Math.random() * (high-low)) + low);
			}
			return array;
	}
	
    //Printing of an array
	/*private void printForMe(Integer[] arr) {
		for(int n:arr){	
			System.out.print(n);
			System.out.print(" ");
		}
		System.out.print("\n");
	}*/
	
	
	///////////////////////////.....................Quick Sort testing................////////////////////////
	

	//1-Testing quick sort with an empty array
	@Test
	public void quick_sort_empty_array() {
		Integer[] randomTestArray = { };
		Integer[] sampleArr = randomTestArray.clone();
			
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
			
		
		Arrays.sort(sampleArr);
			
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
		
	}
		

	//2-Testing Quick sort on an array of random size even	
	@Test
	public void quick_sort_random_even_array() {
		Integer[] randomTestArray = createTestArray(500);
		Integer[] sampleArr = randomTestArray.clone();
		
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
		
		Arrays.sort(sampleArr);
		
		Assert.assertArrayEquals(sampleArr, randomTestArray);
	}
	
		
	//3-Testing quick sort on an array of random size odd
	@Test
	public void quick_sort_random_odd_array() {
		Integer[] randomTestArray = createTestArray(499);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
			
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
			
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
			
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr, randomTestArray);
		
	}
		
			
	//4-Testing quick sort on an array of negative integers of random size even
	@Test
	public void quick_sort_negative_even_array() {
		Integer[] randomTestArray = createMixTestArray(5000, -100000, -8);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
		
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
		
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
		
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
	
	}
	
	
	//5-Testing quick sort on an array of negative integers of random size odd
	@Test
	public void quick_sort_negative_odd_array() {
		Integer[] randomTestArray = createMixTestArray(501, -100000, -8);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
			
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
			
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
			
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
		
	}
	
		
	//6-Testing quick sort with an array containing both positive and negative numbers.
	@Test
	public void quick_sort_mixed_array() {
		Integer[] randomTestArray = createMixTestArray(100, -300, 150);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
		
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
		
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
		
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
	
	}
	
	
	//7- Testing quick sort with an array already sorted in descending order with mixed integers
	@Test
	public void quick_sort_descending_order_array() {
		Integer[] randomTestArray = createMixTestArray(500, -300, 150);
		Arrays.sort(randomTestArray, Collections.reverseOrder());
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(sampleArr);
		
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
		
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
		
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
	
	}
	
	
	//8- Testing quick sort with an array already sorted in ascending order with mixed integers
	@Test
	public void quick_sort_ascending_order_array() {
		Integer[] testArray = createMixTestArray(500, -5000, 9500);
		Arrays.sort(testArray);
		Integer[] randomTestArray = testArray.clone();
			
			
		//Arrays.sort(randomTestArray, Collections.reverseOrder());
		//Integer[] sampleArr = randomTestArray.clone();
		//printForMe(nonsorted);
			
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
			
		//printForMe(randomTestArray);
		//Arrays.sort(sampleArr);
			
		String sortedArray = Arrays.toString(testArray);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
		
	}
		
	
	 //////////////////////////////...........Testing Merge Sort...............////////////////////////////////////
	
	
	//1-Testing Merge sort with an empty array
	@Test
	public void merge_sort_empty_array() {
		Integer[] randomTestArray = { };
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
				
	}
		
	//2-Testing Merge sort on an array of random size even	
	@Test
	public void merge_sort_random_even_array() {
		Integer[] randomTestArray = createTestArray(500);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
			
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
			
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
			
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr, randomTestArray);
		
	}
		
		
	//3-Testing Merge Sort on an array of random size odd
	@Test
	public void merge_sort_random_odd_array() {
		Integer[] randomTestArray = createTestArray(1001);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr, randomTestArray);
				
	}
		
	
	//4-Testing Merge sort on an array of negative integers of random size even
	@Test
	public void merge_sort_negative_even_array() {
		Integer[] randomTestArray = createMixTestArray(9990, -100000, -8);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
				
	}
	
	
	//5-Testing Merge sort on an array of negative integers of random size odd
	@Test
	public void merge_sort_negative_odd_array() {
		Integer[] randomTestArray = createMixTestArray(999, -100000, -8);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
				
	}
				
	
	//6-Testing merge sort with an array containing both positive and negative numbers.
	@Test
	public void merge_sort_mixed_array() {
		Integer[] randomTestArray = createMixTestArray(6580, -300, 150);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
				
	}
	
	
	//7- Testing merge sort with an array already sorted in descending order with mixed integers
	@Test
	public void merge_sort_descending_order_array() {
		Integer[] randomTestArray = createMixTestArray(599, -300, 150);
		Arrays.sort(randomTestArray, Collections.reverseOrder());
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(sampleArr);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
				
	}
				
	//8- Testing merge sort with an array already sorted in ascending order with mixed integers
	@Test
	public void merge_sort_ascending_order_array() {
		Integer[] testArray = createMixTestArray(799, -5000, 9500);
		Arrays.sort(testArray);
		Integer[] randomTestArray = testArray.clone();
					
					
		//Arrays.sort(randomTestArray, Collections.reverseOrder());
		//Integer[] sampleArr = randomTestArray.clone();
		//printForMe(nonsorted);
					
		 MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		//Arrays.sort(sampleArr);
					
		String sortedArray = Arrays.toString(testArray);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
				
	}
	
	////...................Testing Create array module............////////////
	
	@Test
	public void test_create_array_function() {
		new MultithreadSort();
		Integer[] testArray = MultithreadSort.Array_generate(1000);
		Assert.assertEquals(1000, Array.getLength(testArray));
	}
	
	
	////////..................Exceptions............../////////////
	
	//Testing out of memory exception
	 /*@Before
	    public void init() {
	        multithreadSort = new MultithreadSort();
	    }
	 @After
	    public void tearDown() {
	        multithreadSort = null;
	    }
	 @Test(expected = Error.class)
	    public void test_Stack_overflow_Error() {
		 Integer[] testArray = createMixTestArray(100000000, -5000, 9500);
		 QuickSort quickSortSTU = new QuickSort(testArray, 0, testArray.length - 1);
		 testPool.invoke(quickSortSTU);
	    }*/
	 
	 

}
		
	



