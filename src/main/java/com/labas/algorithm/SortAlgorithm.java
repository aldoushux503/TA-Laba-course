package main.java.com.labas.algorithm;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortAlgorithm {

    public static void main(String[] args) {
        int size = 20;

        int[] randomArray = generateRandomArray(size, 0, 100);

        System.out.println("Before sorting: " + Arrays.toString(randomArray));
        quickSort(randomArray, 0, size - 1);
        System.out.println("After sorting: " + Arrays.toString(randomArray));
    }

    // Generates an array of random integers within a specified range
    private static int[] generateRandomArray(int size, int min, int max) {
        return ThreadLocalRandom.current()
                .ints(size, min, max)
                .toArray();
    }

    // QuickSort algorithm implementation
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Partitions the array and returns the pivot index
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    // Swaps two elements in the array
    private static void swap(int[] array, int i, int j) {
        if (i != j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }
}
