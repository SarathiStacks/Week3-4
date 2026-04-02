import java.util.Arrays;
import java.util.Random;

class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class Question4 {

    // ================= MERGE SORT (ASC, STABLE) =================
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Stable: <= ensures original order preserved for ties
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        // Copy back
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    // ================= QUICK SORT (DESC return + ASC volatility) =================
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {

            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Custom comparison:
    // DESC returnRate, if tie → ASC volatility
    private static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(b.returnRate, a.returnRate); // DESC
        }
        return Double.compare(a.volatility, b.volatility); // ASC
    }

    // ================= PIVOT SELECTION =================
    private static int randomPivot(int low, int high) {
        return low + new Random().nextInt(high - low + 1);
    }

    private static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        Asset a = arr[low];
        Asset b = arr[mid];
        Asset c = arr[high];

        if (compare(a, b) < 0) {
            if (compare(b, c) < 0) return mid;
            else if (compare(a, c) < 0) return high;
            else return low;
        } else {
            if (compare(a, c) < 0) return low;
            else if (compare(b, c) < 0) return high;
            else return mid;
        }
    }

    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        // Sample Input
        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        // Copy arrays
        Asset[] mergeArr = Arrays.copyOf(assets, assets.length);
        Asset[] quickArr = Arrays.copyOf(assets, assets.length);

        // Merge Sort (ASC)
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge: " + Arrays.toString(mergeArr));

        // Quick Sort (DESC + volatility ASC)
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick (desc): " + Arrays.toString(quickArr));
    }
}