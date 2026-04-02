import java.util.Arrays;

public class Question5 {

    // ================= LINEAR SEARCH =================
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;

            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        if (first != -1) {
            System.out.println("Linear first " + target + ": index " + first +
                    " (" + comparisons + " comparisons)");
            System.out.println("Linear last " + target + ": index " + last);
        } else {
            System.out.println("Linear: " + target + " not found (" + comparisons + " comparisons)");
        }
    }

    // ================= BINARY SEARCH =================
    public static int binarySearch(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    // Find first occurrence using binary search
    public static int findFirst(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid].compareTo(target) >= 0) {
                if (arr[mid].equals(target)) result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    // Find last occurrence using binary search
    public static int findLast(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid].compareTo(target) <= 0) {
                if (arr[mid].equals(target)) result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // Helper class for counting comparisons
    static class Counter {
        int count = 0;
    }

    public static void main(String[] args) {

        // Input logs
        String[] logs = {"accB", "accA", "accB", "accC"};

        // ===== Linear Search =====
        linearSearch(logs, "accB");

        // ===== Sort for Binary Search =====
        Arrays.sort(logs);
        System.out.println("\nSorted logs: " + Arrays.toString(logs));

        // ===== Binary Search =====
        Counter counter = new Counter();

        int index = binarySearch(logs, "accB", counter);
        int first = findFirst(logs, "accB", counter);
        int last = findLast(logs, "accB", counter);

        int count = (first == -1) ? 0 : (last - first + 1);

        if (index != -1) {
            System.out.println("Binary accB: index " + index +
                    " (" + counter.count + " comparisons), count=" + count);
        } else {
            System.out.println("Binary: accB not found (" + counter.count + " comparisons)");
        }
    }
}