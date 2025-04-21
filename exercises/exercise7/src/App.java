import java.util.*;
import java.util.function.Predicate;

public class App {

    // (a) Count elements in a collection that match a specific property
    public static <T> int countIf(Collection<T> collection, Predicate<T> predicate) {
        int count = 0;
        for (T element : collection) {
            if (predicate.test(element)) {
                count++;
            }
        }
        return count;
    }

    // (b) Exchange the positions of two different elements in an array
    public static <T> void swap(T[] array, int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 >= array.length || index2 >= array.length) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    // (c) Find the maximal element in the range [begin, end) of a list
    public static <T extends Comparable<T>> T maxInRange(List<T> list, int begin, int end) {
        if (begin < 0 || end > list.size() || begin >= end) {
            throw new IllegalArgumentException("Invalid range.");
        }

        T max = list.get(begin);
        for (int i = begin + 1; i < end; i++) {
            if (list.get(i).compareTo(max) > 0) {
                max = list.get(i);
            }
        }
        return max;
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        // (a) Count example: count odd numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int countOdd = countIf(numbers, n -> n % 2 != 0);
        System.out.println("Number of odd integers: " + countOdd);

        // (b) Swap example: swap elements in an array
        String[] names = {"Karen", "Amy", "Ben"};
        swap(names, 0, 2);
        System.out.println("After swap: " + Arrays.toString(names));

        // (c) Max in range example
        List<Integer> values = Arrays.asList(3, 7, 2, 9, 5,10,9);
        int max = maxInRange(values, 1, 4); // checks elements at index 1 to 3
        System.out.println("Max in range [1, 4): " + max);
    }
}
