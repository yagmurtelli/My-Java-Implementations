import java.util.Scanner;

class GiftNode {
    int value;
    int minimumSoFar; //Bu node eklendiğindeki minimum değer
    GiftNode next;

    GiftNode(int value, int minSoFar, GiftNode next) {
        this.value = value;
        this.minimumSoFar = minSoFar;
        this.next = next;
    }
}
public class Question2 {
	private static GiftNode top = null; //Stack'in en üstünü gösterdik

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int n = sc.nextInt(); //Operasyon sayısısını gösterdik
        
        for (int i = 0; i < n; i++) {
            String operation = sc.next();
            
            if (operation.equals("ADD")) {
                int A = sc.nextInt();
                add(A); //yeni bir hediye ekler, eğer çanta boşsa minimumSoFar direkt A olur
            } else if (operation.equals("DEL")) {
                del(); //en üstekki değeri silmemizi sağlar eğer liste boşssa empty yazdırır
            } else if (operation.equals("MIN")) {
                min(); //O anki en üstteki Node'un içinde kayıtlı olan minimumSoFar değerini yazdırırız
            }
        }
        sc.close();
    }

    // Stack'e yeni bir hediye ekleriz ve o anki minimumu güncelleriz
    
    public static void add(int A) {
        int currentMinimum = (top == null) ? A : Math.min(A, top.minimumSoFar);  //Precondition
        top = new GiftNode(A, currentMinimum, top);   // Postcondition: Yeni düğüm yığının tepesine ekleriz.
    }
    //Stack'in en üstündeki elemanı çıkartırız eğer boşsa "Empty" yazdırırız
   
    public static void del() {
        if (top == null) {
            System.out.println("Empty");
        } else {
            top = top.next;
        }
    }
    // Son olarak stack'teki en küçük eğlence değerini yazdırırız
    
    public static void min() {
        if (top == null) {
            System.out.println("Empty");
        } else {
            System.out.println(top.minimumSoFar);
        }
    }

}
