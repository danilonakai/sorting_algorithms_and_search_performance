import java.util.Arrays;
import java.util.Random;

public class Main {
    /**
     * The swap method swaps the contents of two elements in an int array.
     *
     * @param array where elements are to be swapped.
     * @param a The subscript of the first element.
     * @param b The subscript of the second element.
     */
    private static void swap(int[] array, int a, int b) {
        int temp;
        temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * Validate that an array is sorted,
     *
     * @param array array that might or might not be sorted
     * @return a 6 digit checksum if sorted, -1 if not sorted.
     */
    public static int ckSumSorted(int[] array) {
        int sum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return -1;
            }
            sum += array[i] * i;
        }
        return Math.abs(sum % 1000_000);
    }

    /**
     * Randomly re-arrange any array, if sorted will unsort the array, or shuffle it
     *
     * @param array array that might or might not be sorted
     */
    public static void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int j = rand.nextInt(array.length);
            swap(array, i, j);
        }
    }

    /**
     * ---------------------------- a Sort ---------------------------------------
     */
    public static long aSort(int[] array) {
        int startScan;   // Starting position of the scan
        int index;       // To hold a subscript value
        int minIndex;    // Element with smallest value in the scan
        int minValue;    // The smallest value found in the scan
        long compares = 0; // Initialize compares

        // The outer loop iterates once for each element in the
        // array. The startScan variable marks the position where
        // the scan should begin.
        for (startScan = 0; startScan < (array.length - 1); startScan++) {
            // Assume the first element in the scannable area
            // is the smallest value.
            minIndex = startScan;
            minValue = array[startScan];

            // Scan the array, starting at the 2nd element in
            // the scannable area. We are looking for the smallest
            // value in the scannable area.
            for (index = startScan + 1; index < array.length; index++) {
                compares++; // Increment compares
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
            }

            // Swap the element with the smallest value
            // with the first element in the scannable area.
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }

        return compares;
    }

    /**
     * ---------------------------- b Sort ---------------------------------------
     */
    public static long bSort(int array[]) {
        int count = 0;
        long compares = 0; // Initialize compare

        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min)
                min = array[i];
            else if (array[i] > max)
                max = array[i];

            compares++; // Increment compare
        }
        int b[] = new int[max - min + 1];
        for (int i = 0; i < array.length; i++)
            b[array[i] - min]++;

        for (int i = 0; i < b.length; i++)
            for (int j = 0; j < b[i]; j++) {
                array[count++] = i + min;
            }

        return compares;
    }

    /**
     * ---------------------------- c Sort ---------------------------------------
     */
    public static long cSort(int inputArray[]) {
        int length = inputArray.length;
        // Create array only once for merging
        int[] workingArray = new int[inputArray.length];
        long count = 0;
        count = doCSort(inputArray, workingArray, 0, length - 1, count);

        long compares = count; // Set compares value

        return compares;
    }

    private static long doCSort(int[] inputArray, int[] workingArray, int lowerIndex, int higherIndex, long count) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            count = doCSort(inputArray, workingArray, lowerIndex, middle, count);
            // Below step sorts the right side of the array
            count = doCSort(inputArray, workingArray, middle + 1, higherIndex, count);
            // Now merge both sides
            long mergedIncrementCount = part2(inputArray, workingArray, lowerIndex, middle, higherIndex);

            count = count + mergedIncrementCount;  // Increment count
        }
        return count;
    }

    private static long part2(int[] inputArray, int[] workingArray, int lowerIndex, int middle, int higherIndex) {

        long count = 0;
        for (int i = lowerIndex; i <= higherIndex; i++) {
            workingArray[i] = inputArray[i];
        }
        int i1 = lowerIndex;
        int i2 = middle + 1;
        int newIndex = lowerIndex;
        while (i1 <= middle && i2 <= higherIndex) {
            count++; // Increment count for every comparison

            if (workingArray[i1] <= workingArray[i2]) {
                inputArray[newIndex] = workingArray[i1];
                i1++;
            } else {
                inputArray[newIndex] = workingArray[i2];
                i2++;
            }
            newIndex++;
        }
        while (i1 <= middle) {
            inputArray[newIndex] = workingArray[i1];
            newIndex++;
            i1++;
        }
        return count;
    }

    /**
     * ---------------------------- d Sort ---------------------------------------
     */
    public static long dSort(int[] array) {
        int unsortedValue;  // The first unsorted value
        int scan;           // Used to scan the array
        long compares = 0; // Set compares value

        // The outer loop steps the index variable through
        // each subscript in the array, starting at 1. The portion of
        // the array containing element 0  by itself is already sorted.
        for (int index = 1; index < array.length; index++) {
            // The first element outside the sorted portion is
            // array[index]. Store the value of this element
            // in unsortedValue.
            unsortedValue = array[index];

            // Start scan at the subscript of the first element
            // outside the sorted part.
            scan = index;

            // Move the first element in the still unsorted part
            // into its proper position within the sorted part.
            while (scan > 0 && array[scan - 1] > unsortedValue) {
                array[scan] = array[scan - 1];
                scan--;
                compares++; // Increment compares
            }

            compares++; // Increment compares for the last failed comparison in the while condition

            // Insert the unsorted value in its proper position
            // within the sorted subset.
            array[scan] = unsortedValue;
        }

        return compares;
    }

    /**
     * ---------------------------- e Sort ---------------------------------------
     */
    public static long eSort(int array[]) {
        long compares = doESort(array, 0, array.length - 1, 0);

        return compares;
    }

    /**
     *
     * @param array The array to sort.
     * @param start The starting subscript of the list to sort
     * @param end The ending subscript of the list to sort
     */
    private static long doESort(int array[], int start, int end, long numberOfCompares) {
        int pivotPoint;

        if (start < end) {
            // Get the pivot point.
            pivotPoint = part1(array, start, end);
            // Note - only one +/=
            numberOfCompares += (end - start);
            // Sort the first sub list.
            numberOfCompares = doESort(array, start, pivotPoint - 1, numberOfCompares);

            // Sort the second sub list.
            numberOfCompares = doESort(array, pivotPoint + 1, end, numberOfCompares);
        }
        return numberOfCompares;
    }

    /**
     * The partition method selects a pivot value in an array and arranges the
     * array into two sub lists. All the values less than the pivot will be
     * stored in the left sub list and all the values greater than or equal to
     * the pivot will be stored in the right sub list.
     *
     * @param array The array to partition.
     * @param start The starting subscript of the area to partition.
     * @param end The ending subscript of the area to partition.
     * @return The subscript of the pivot value.
     */
    private static int part1(int array[], int start, int end) {
        int pivotValue;    // To hold the pivot value
        int endOfLeftList; // Last element in the left sub list.
        int mid;           // To hold the mid-point subscript

        // Find the subscript of the middle element.
        // This will be our pivot value.
        mid = (start + end) / 2;

        // Swap the middle element with the first element.
        // This moves the pivot value to the start of
        // the list.
        swap(array, start, mid);

        // Save the pivot value for comparisons.
        pivotValue = array[start];

        // For now, the end of the left sub list is
        // the first element.
        endOfLeftList = start;

        // Scan the entire list and move any values that
        // are less than the pivot value to the left
        // sub list.
        for (int scan = start + 1; scan <= end; scan++) {
            if (array[scan] < pivotValue) {
                endOfLeftList++;
                swap(array, endOfLeftList, scan);
            }
        }

        // Move the pivot value to end of the
        // left sub list.
        swap(array, start, endOfLeftList);

        // Return the subscript of the pivot value.
        return endOfLeftList;
    }

    /**
     * ---------------------------- f Sort ---------------------------------------
     */
    public static long fSort(int[] array) {
        int lastPos;     // Position of last element to compare
        int index;       // Index of an element to compare
        long compares = 0; // Initialize compares

        // The outer loop positions lastPos at the last element
        // to compare during each pass through the array. Initially
        // lastPos is the index of the last element in the array.
        // During each iteration, it is decreased by one.
        for (lastPos = array.length - 1; lastPos >= 0; lastPos--) {
            // The inner loop steps through the array, comparing
            // each element with its neighbor. All of the elements
            // from index 0 thrugh lastPos are involved in the
            // comparison. If two elements are out of order, they
            // are swapped.
            for (index = 0; index <= lastPos - 1; index++) {
                compares++; // Increment compares

                // Compare an element with its neighbor.
                if (array[index] > array[index + 1]) {
                    // Swap the two elements.
                    swap(array, index, index + 1);
                }
            }
        }

        return compares;
    }

    /**
     * A demonstration of recursive counting in a Binary Search
     * @param array - array to search
     * @param low - the low index - set to 0 to search whiole array
     * @param high - set to length of array to search whole array
     * @param value - item to search for
     * @param count - Used in recursion to accumulate the number of comparisons made (set to 0 on initial call)
     *
     * @return
     */
    private static int[] binarySearchR(int[] array, int low, int high, int value, int count)
    {
        int middle;     // Mid point of search

        // Test for the base case where the value is not found.
        if (low > high)
            return new int[] {-1,count};

        // Calculate the middle position.
        middle = (low + high) / 2;

        // Search for the value.
        if (array[middle] == value)
            // Found match return the index
            return new int[] {middle, count};

        else if (value > array[middle])
            // Recursive method call here (Upper half of remaining data)
            return binarySearchR(array, middle + 1, high, value, count+1);
        else
            // Recursive method call here (Lower half of remaining data)
            return binarySearchR(array, low, middle - 1, value, count+1);
    }





    // PART 1
    /**
     * Generates a header for the sort comparison report
     *
     * @param arraySize      the size of the array being sorted
     * @param unsortedArray  the array that contains the unsorted data
     */
    public static void generateHeader(int arraySize, int[] unsortedArray){
        int total_runs = (1000_000 / arraySize);
        System.out.printf("\nComparison of sorts, Array size = %,d total runs = %,d\n", arraySize, total_runs);
        System.out.println("==============================================================");
        System.out.println("Algorithm      Run time     # of compares         ms / compares    checksum");

        measureSortTime("aSort", unsortedArray, total_runs);
        measureSortTime("bSort", unsortedArray, total_runs);
        measureSortTime("cSort", unsortedArray, total_runs);
        measureSortTime("dSort", unsortedArray, total_runs);
        measureSortTime("eSort", unsortedArray, total_runs);
        measureSortTime("fSort", unsortedArray, total_runs);
    }

    /**
     * Generates an array of integers using a random number
     *
     * @param arraySize  the size of the array to be generated
     * @return           an array of randomly generated integers
     */
    public static int[] generateArrayUsingRandomNumber(int arraySize){
        Random rand = new Random();
        int[] array = new int[arraySize];

        for(int i = 0; i < array.length; i++){
            array[i] = rand.nextInt(1, arraySize);
        }

        return array;
    }

    /**
     * Generates a formatted report of sorting performance
     *
     * @param sortName        the name of the sorting algorithm
     * @param execTimeMs      the execution time in milliseconds
     * @param compares        the number of comparisons made during sorting
     * @param msPerCompare    the average time per comparison in milliseconds
     * @param checksum        the checksum of the sorted array
     */
    public static void generateReport(String sortName, double execTimeMs, long compares, double msPerCompare, int checksum){
        System.out.printf("%-10s", sortName);
        System.out.printf("%, 10.7f ms", execTimeMs);
        System.out.printf("%, 14d ops", compares);
        System.out.printf("%, 14.7f ms / op", msPerCompare);
        System.out.printf("%, 12d \n", checksum);
    }

    /**
     * Measures the execution time and number of comparisons for a specified sorting algorithm
     *
     * @param sortName       the name of the sorting algorithm to be measured
     * @param unsortedArray  the array to be sorted
     * @param totalRuns      the number of times to run the sorting algorithm for averaging
     */
    public static void measureSortTime(String sortName, int[] unsortedArray, int totalRuns) {
        long totalTime = 0;
        long compares = 0;
        int[] sortedArray = unsortedArray.clone();

        if(ckSumSorted(sortedArray) == -1){
            for (int i = 0; i < totalRuns; i++) {
                long startTime = System.currentTimeMillis();
                compares = switch (sortName) {
                    case "aSort" -> aSort(sortedArray);
                    case "bSort" -> bSort(sortedArray);
                    case "cSort" -> cSort(sortedArray);
                    case "dSort" -> dSort(sortedArray);
                    case "eSort" -> eSort(sortedArray);
                    case "fSort" -> fSort(sortedArray);
                    default -> 0;
                };

                long endTime = System.currentTimeMillis();
                totalTime += (endTime - startTime);
            }

            double execTimeMs = (double) totalTime / totalRuns;
            double msPerCompare = execTimeMs / compares;

            if(ckSumSorted(sortedArray) != -1){
                generateReport(sortName, execTimeMs, compares, msPerCompare, ckSumSorted(sortedArray));
            }
        }
    }


    // PART 2
    /**
     * Performs a linear search on the given array for the specified value
     *
     * @param array the array to search through
     * @param value the value to search for in the array
     * @return an int array of size 2:
     *         - index: the index of the found element, or -1 if not found
     *         - count: the number of comparisons made during the search
     */
    public static int[] linearSearch(int[] array, int value) {
        int count = 0;
        int index = -1;

        for (int i = 0; i < array.length; i++) {
            count++;
            if (array[i] == value) {
                index = i;
                break;
            }
        }

        return new int[] {index, count};
    }

    /**
     * Compares the performance of linear search and sort + binary search
     * It calculates the average time and number of comparisons for both methods
     *
     * @param arraySize     the size of the array to be tested
     * @param unsortedArray the array to perform the searches on
     */
    public static void searchComparison(int arraySize, int[] unsortedArray) {
        int total_runs = (1_000_000 / arraySize); // I'm using the same approach of the part 1 to repeat the test

        // Sort the array
        int[] sortedArray = unsortedArray.clone();
        System.out.println("\n\n\nPart2. Calculating the time required to sort 100,000 elements (bSort - Radix Sort)");
        System.out.println("==============================================================");
        System.out.println("Algorithm      Run time     # of compares         ms / compares    checksum");
        measureSortTime("bSort", sortedArray, total_runs); // Using bSort that was the fastest option for arrays with many elements




        System.out.println("\nComparison of Linear Search vs Sort + Binary Search:");

        long totalLinearTime = 0;
        int totalLinearComparisons = 0;

        // Run linear search for the specified number of total runs
        for (int i = 0; i < total_runs; i++) {
            long startTime = System.currentTimeMillis();
            int[] linearSearchResult = linearSearch(unsortedArray, -1);
            long linearSearchTime = System.currentTimeMillis() - startTime;

            totalLinearTime += linearSearchTime;
            totalLinearComparisons += linearSearchResult[1];
        }

        // Calculate average time for linear search in milliseconds
        double avgLinearTime = (double) totalLinearTime / total_runs;
        int avgLinearComparisons = totalLinearComparisons / total_runs;

        System.out.printf("Linear Search: Avg Time = %.2f ms, Total Comparisons = %,d\n", avgLinearTime, avgLinearComparisons);




        long totalBinaryTime = 0;
        int totalBinaryComparisons = 0;

        // Run binary search for the specified number of total runs
        for (int i = 0; i < total_runs; i++) {
            long startTime = System.nanoTime();
            int[] binarySearchResult = binarySearchR(sortedArray, 0, sortedArray.length - 1, -1, 0);
            long binarySearchTime = System.nanoTime() - startTime; // Calculating elapsed time in nanoseconds because it's too fast for ms

            totalBinaryTime += binarySearchTime;
            totalBinaryComparisons += binarySearchResult[1];
        }

        // Calculate average time for binary search in milliseconds(converting from nanoseconds)
        double avgBinaryTime = (double) totalBinaryTime / total_runs / 1_000_000;
        int avgBinaryComparisons = totalBinaryComparisons / total_runs;

        System.out.printf("Binary Search: Avg Time = %.6f ms, Total Comparisons = %,d\n", avgBinaryTime, avgBinaryComparisons);

        System.out.println("To justify sorting the data first, it would have to do about 5 linear searches to take the same amount of effective time as sorting and then doing a binary search.");
    }

    /**
     * The main method will run through all of the Sorts for the prescribed sizes and produce output for parts A and B
     *
     * Part C should be answered at the VERY TOP of the code in a comment
     *
     */
    public static void main(String[] args)
    {
        // PART 1
        int arraySize = 20;
        int[] unsortedArray = generateArrayUsingRandomNumber(arraySize);
        generateHeader(arraySize, unsortedArray);

        arraySize = 400;
        unsortedArray = generateArrayUsingRandomNumber(arraySize);
        generateHeader(arraySize, unsortedArray);


        arraySize = 8_000;
        unsortedArray = generateArrayUsingRandomNumber(arraySize);
        generateHeader(arraySize, unsortedArray);


        // PART 2
        arraySize = 100000;
        unsortedArray = generateArrayUsingRandomNumber(arraySize);
        searchComparison(arraySize, unsortedArray);
    }
}
