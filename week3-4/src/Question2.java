import java.util.Arrays;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }

    public String detailed() {
        return name + "(" + riskScore + ")";
    }
}

public class Question2 {

    // Bubble Sort (Ascending by riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("Bubble (asc): " + Arrays.toString(arr) + " // Swaps: " + swaps);
    }

    // Insertion Sort (Descending by riskScore, then accountBalance)
    public static void insertionSort(Client[] arr) {

        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && (
                    arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance)
            )) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.print("Insertion (desc): [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Top N highest risk clients
    public static void printTopRisks(Client[] arr, int topN) {
        System.out.print("Top " + topN + " risks: ");

        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.print(arr[i].detailed());
            if (i != topN - 1 && i != arr.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Input
        Client[] clients = {
                new Client("clientC", 80, 1000),
                new Client("clientA", 20, 500),
                new Client("clientB", 50, 700)
        };

        // Copy arrays
        Client[] bubbleArr = Arrays.copyOf(clients, clients.length);
        Client[] insertionArr = Arrays.copyOf(clients, clients.length);

        // Bubble Sort
        bubbleSort(bubbleArr);

        // Insertion Sort
        insertionSort(insertionArr);

        // Top risks (after descending sort)
        printTopRisks(insertionArr, 3);
    }
}