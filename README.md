# Sorting Algorithms and Search Performance

This project contains a collection of sorting algorithms and a comparison of their performance with a linear search versus a sorted array with binary search. The goal is to measure the efficiency of various sorting algorithms through comparison counts, execution times, and the checksum of sorted arrays.

The project is divided into two main parts:
- **Part 1**: Sorting algorithms comparison.
- **Part 2**: Linear Search vs. Sort + Binary Search performance comparison.

## Sorting Algorithms Implemented
- **aSort**: Selection Sort
- **bSort**: Radix Sort
- **cSort**: Merge Sort
- **dSort**: Insertion Sort
- **eSort**: Quick Sort (recursive)
- **fSort**: Bubble Sort

## Performance Metrics
For each sorting algorithm:
- **Execution Time (ms)**: The total time taken to execute the sorting algorithm.
- **Comparisons**: The number of comparisons made during the sorting process.
- **ms / Comparison**: The average time per comparison in milliseconds.
- **Checksum**: A checksum of the sorted array to verify correctness.

### Part 1: Sorting Algorithms Comparison
The program generates a set of random arrays with varying sizes and runs each sorting algorithm multiple times, measuring the execution time, number of comparisons, and the checksum. The results are outputted in a formatted table.

### Part 2: Search Performance Comparison
This part compares the performance of:
1. **Linear Search**: Search through the unsorted array.
2. **Binary Search**: Search after sorting the array.

### Example Output
![image](https://github.com/user-attachments/assets/6247c836-e795-4436-91c3-b22c21c00198)

### Situations where sorting first is more effective
To justify sorting the data first, it would have to do about 5 linear searches to take the same amount of effective time as sorting and then doing a binary search.

## Analysis of Sorting Algorithms

### Speed of Sorting Algorithms with 20 Elements
For smaller arrays containing 20 elements, the following sorting algorithms were ranked from fastest to slowest based on execution time:

1. **dSort** (Insertion Sort) – 0.0000800 ms
2. **aSort** (Selection Sort) – 0.0001600 ms
3. **eSort** (Quick Sort) – 0.0001800 ms
4. **bSort** (Radix Sort) – 0.0002200 ms
5. **cSort** (Merge Sort) and **fSort** (Bubble Sort) – Both had the same runtime of 0.0002800 ms. Despite **fSort** having a lower ms/compare value, the overall runtime was identical for both.

### Speed of Sorting Algorithms with 8,000 Elements
When the array size increased to 8,000 elements, the ranking of sorting algorithms changed. The algorithms were ranked as follows:

1. **bSort** (Radix Sort) – 0.0560000 ms
2. **dSort** (Insertion Sort) – 0.0800000 ms
3. **eSort** (Quick Sort) – 0.1040000 ms
4. **cSort** (Merge Sort) – 0.1200000 ms
5. **aSort** (Selection Sort) – 6.0000000 ms
6. **fSort** (Bubble Sort) – 22.8560000 ms

### Lowest Basic Instruction Set Time at 8,000 Elements
At the array size of 8,000 elements, **bSort** (Radix Sort) exhibited the lowest basic instruction set time. However, it is important to note that there is no single "best" sorting algorithm. The ideal choice depends on the size of the data. For example, **dSort** (Insertion Sort) performed better with smaller arrays (20 elements), while **bSort** (Radix Sort) outperformed others with larger arrays (8,000 elements). This demonstrates that the choice of sorting algorithm should be tailored to the problem's specific needs.

## Big O Complexity of Sorting Algorithms
The following table summarizes the time and space complexity of each sorting algorithm implemented:

| Sort Algorithm | Sort Algorithm Name | Big O (Time)   | Big O (Space)  |
|----------------|---------------------|----------------|----------------|
| aSort          | Selection Sort       | Θ(n²)          | O(1)           |
| bSort          | Radix Sort        | Θ(nk)          | O(n + k)       |
| cSort          | Merge Sort           | Θ(n log(n))    | O(n)           |
| dSort          | Insertion Sort       | Θ(n²)          | O(1)           |
| eSort          | Quick Sort           | Θ(n log(n))    | O(log(n))      |
| fSort          | Bubble Sort          | Θ(n²)          | O(1)           |

### Reference:
[Big O Cheat Sheet](https://www.bigocheatsheet.com/)

## Conclusion
The project demonstrates the differences in efficiency between various sorting algorithms. It also highlights the performance advantage of sorting an array before using binary search over performing a linear search directly on an unsorted array.

## Development Environment
- **Java Version**: 15 (Liberica JDK 15) or higher
- **IDE**: IntelliJ IDEA (Community Edition)

## How to Run
1. Clone the repository to your local machine.
2. Open the project in IntelliJ IDEA (Community Edition).
3. Ensure that you are using Java 15 (Liberica JDK 15) or higher.
4. Run the `Main.java` file to test the project in your machine.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
