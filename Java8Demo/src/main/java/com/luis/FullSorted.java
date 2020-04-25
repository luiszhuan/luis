package com.luis;

public class FullSorted {
    private static int count = 0;

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5, 6};
        new FullSorted().solver(input, 0);

    }

    private void solver(int[] array, int n) {
        if (n == array.length) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + ",");
            }
            System.out.println(count++);
            return;
        }
        for (int i = n; i < array.length; i++) {
            swap(array, n, i);
            solver(array, n + 1);
            swap(array, n, i);
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
