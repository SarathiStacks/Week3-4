import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee;
    }

    public String detailed() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class Question1 {

    // Bubble Sort (by fee)
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("BubbleSort (fees): " + list +
                " // " + passes + " passes, " + swaps + " swaps");
    }

    // Insertion Sort (by fee + timestamp)
    public static void insertionSort(List<Transaction> list) {

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // sort by fee first, then timestamp
            while (j >= 0 && (
                    list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.compareTo(key.timestamp) > 0)
            )) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.print("InsertionSort (fee+ts): [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).detailed());
            if (i != list.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // High-fee outliers
    public static void detectOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        if (outliers.isEmpty()) {
            System.out.println("High-fee outliers: none");
        } else {
            System.out.print("High-fee outliers: ");
            for (Transaction t : outliers) {
                System.out.print(t.detailed() + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Input transactions
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Copy lists (to preserve original)
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        List<Transaction> insertionList = new ArrayList<>(transactions);

        // Apply Bubble Sort
        bubbleSort(bubbleList);

        // Apply Insertion Sort
        insertionSort(insertionList);

        // Detect outliers
        detectOutliers(transactions);
    }
}