import java.util.Arrays;

public class Question6 {

    // ================= LINEAR SEARCH =================
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Linear: threshold=" + target + " found (" + comparisons + " comparisons)");
        } else {
            System.out.println("Linear: threshold=" + target + " → not found (" + comparisons + " comparisons)");
        }
    }

    // ================= BINARY FLOOR =================
    public static int floor(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid] <= target) {
                result = arr[mid]; // possible floor
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // ================= BINARY CEILING =================
    public static int ceiling(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                result = arr[mid]; // possible ceiling
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    // ================= INSERTION POINT =================
    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }

        return low;
    }

    // Counter class
    static class Counter {
        int count = 0;
    }

    public static void main(String[] args) {

        // Input
        int[] risks = {10, 25, 50, 100};
        int target = 30;

        // ===== Linear Search =====
        linearSearch(risks, target);

        // ===== Binary Search Operations =====
        Counter counter = new Counter();

        int floor = floor(risks, target, counter);
        int ceiling = ceiling(risks, target, counter);
        int insertPos = insertionPoint(risks, target);

        System.out.println("\nSorted risks: " + Arrays.toString(risks));
        System.out.println("Binary floor(" + target + "): " + floor +
                ", ceiling: " + ceiling +
                " (" + counter.count + " comparisons)");
        System.out.println("Insertion position for " + target + ": index " + insertPos);
    }
}