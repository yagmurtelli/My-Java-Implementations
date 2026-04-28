import java.util.Scanner;

public class Main {

    public static void insertionSortByPrice(Product[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            Product key = arr[i];
            int j = i + 1;

            while (j < arr.length && key.compareTo(arr[j]) > 0) {
                arr[j - 1] = arr[j];
                j++;
            }
            arr[j - 1] = key;
        }
    }

    public static void mergeSortBySales(Product[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortBySales(arr, left, mid);
            mergeSortBySales(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Product[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Product[] L = new Product[n1];
        Product[] R = new Product[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].salesCount >= R[j].salesCount) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    public static void printProducts(Product[] arr, boolean showSales) {
        for (Product p : arr) {
            if (showSales) {
                System.out.println(p.productName + " - " + p.category + " (Sales: " + p.salesCount + ")");
            } else {
                System.out.printf("%s - %s ($%.2f)%n", p.productName, p.category, p.price);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        Product[] products = new Product[n];

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().split(",");

            String name = data[0].trim();
            String category = data[1].trim();
            double price = Double.parseDouble(data[2].trim());
            int stock = Integer.parseInt(data[3].trim());
            int sales = Integer.parseInt(data[4].trim());

            products[i] = new Product(name, category, price, stock, sales);
        }

        int sortType = Integer.parseInt(sc.nextLine());

        if (sortType == 1) {
            insertionSortByPrice(products);
            System.out.println("Insertion Sort by Price (Ascending):");
            printProducts(products, false);
        } else if (sortType == 2) {
            mergeSortBySales(products, 0, products.length - 1);
            System.out.println("Merge Sort by Sales Count (Descending):");
            printProducts(products, true);
        }

        sc.close();
    }
}

