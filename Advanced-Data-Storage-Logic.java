import java.util.Scanner;

public class Data {
 
    public abstract static class Store {
        protected String dataname;
        protected int storagearea;

        public Store(String dataname, int storagearea) {
            this.dataname = dataname;
            this.storagearea = storagearea;
        }

        public abstract void setStatustext(String statustext);
        public abstract String Status();

        @Override
        public String toString() {
            return "Data: " + dataname + " Size on disk: " + storagearea;
        }
    }

    public static class Datataker extends Store {
        protected String statustext;

        public Datataker(String dataname, int storagearea) {
            super(dataname, storagearea);
        }

        @Override
        public void setStatustext(String statustext) {
            this.statustext = statustext;
        }

        @Override
        public String Status() {
            if (dataname.equals("data3") || dataname.equals("data6")) {
                return "Status of data: " + statustext;
            }
            return "";
        }

        @Override
        public String toString() {
            String statusMessage = Status();
            if (!statusMessage.isEmpty()) {
                return super.toString() + " " + statusMessage;
            }
            return super.toString();
        }
    }

    // Ana Data sınıfı
    Store[] Storage;

    public Data() {
        Scanner scanner = new Scanner(System.in);

        // Kaç veri saklanacağı soruluyor
        System.out.println("How many data do you store?");
        int number = scanner.nextInt();
        scanner.nextLine(); // newline karakterini alıyoruz
        Storage = new Store[number];

        // Verilerin ismi ve boyutları alınıyor
        for (int i = 0; i < number; i++) {
            System.out.println("Enter the name of " + i + ". data:");
            String name = scanner.nextLine();

            System.out.println("Enter size of " + i + ". data:");
            int size = scanner.nextInt();
            scanner.nextLine(); // newline karakterini alıyoruz

            Storage[i] = new Datataker(name, size);

            // Eğer veri sırası 3 veya 6 ise, statü metni alıyoruz
            if (i == 3 || i == 6) {
                System.out.println("Enter status of " + i + ". data:");
                String statusText = scanner.nextLine();
                ((Datataker) Storage[i]).setStatustext(statusText); // Statü metnini set ediyoruz
            }
        }
    }

    // Verilerin listelendiği metod
    public void inventory() {
        System.out.println("Data values");
        System.out.println("--------------------------------------------");
        for (Store item : Storage) {
            System.out.println(item);
        }
    }

    // Main metodunda programı başlatıyoruz
    public static void main(String[] args) {
        Data output = new Data();
        output.inventory();
    }
}

//////////////////
/////////////////

import java.util.Scanner;

public abstract class Store {
	
    protected String dataName;
    protected int storageArea;

    public Store(String dataName, int storageArea) {
        this.dataName = dataName;
        this.storageArea = storageArea;
    }

    @Override
    public String toString() {
        return "Data: " + dataName + " Size on disk: " + storageArea;
    }
}

class Datataker extends Store {

    public Datataker(String dataName, int storageArea) {
        super(dataName, storageArea);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class Data {
    Store[] Storage;

    public Data() {
    	
        Scanner sc = new Scanner(System.in);

        System.out.println("How many data do you store?");
        int number = sc.nextInt();
        sc.nextLine();
        Storage = new Store[number];

        for (int i = 0; i < number; i++) {
            System.out.println("Enter the name of " + i + ". data:");
            String name = sc.nextLine();

            System.out.println("Enter size of " + i + ". data:");
            int size = sc.nextInt();
            sc.nextLine(); 

            Storage[i] = new Datataker(name, size);
        }
    }

    public void inventory() {
        System.out.println("Data values");
        System.out.println("--------------------------------------------");
        for (Store item : Storage) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Data output = new Data();
        output.inventory();
    }
}

