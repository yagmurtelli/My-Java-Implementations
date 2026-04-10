import java.util.Scanner;

class Node {
 Object data; // Veri
 Node next;   // Bir sonraki düğüme giden yol, pointer.(Bir sonraki düğüme olan bağlantı yolu)
 //recursive
 
 Node(Object data) { //Constructor
     this.data = data; //Bu düğümün veri kısmına, dışarıdan gönderilen data bilgisini kaydetmek için yazdık
     this.next = null; //Yeni bir düğüm ilk oluşturulduğunda henüz başka bir düğüme bağlı değildir bu yüzden bir sonraki düğümü gösteren next işaretçisini null olarak ayarladık
 }
}
//Entry point
//Dummy Node kullacağız

public class Question1 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (!sc.hasNextInt()) return; //bunun sayesinde, kullanıcı ilk satıra sayı yerine yanlışlıkla başka bir şey girerse veya girdi dosyası boşsa, programın hata verip kapanması yerine güvenli bir şekilde çalışmayı durduruz.
        int N = sc.nextInt();  //Listenin kaç elemandan oluşacağını okur(düğüm sayısını)
        
        Node dummy = new Node(0); //Listenin gerçek başlangıcından önce sahte bir node oluştururuz böylece ilk elemanı eklerken liste boş mu kontrolü yapmamıza gerek kalmaz.
        Node tail = dummy; //listenin sonunu taip eden bir pointer yeni elemanlar eklendikçe tail ileri kaydırılır.
        
        for (int i = 0; i < N; i++) {
        	int value = sc.nextInt(); //her adımda sıradaki sayıyı okur
        	tail.next = new Node(value); //okunan sayı ile yeni bir düğüm oluşturulur ve bunu mevcut listenin sonuna bağlar. tail.next yapısı yani.
        	tail = tail.next; //bunun sayesinde bir sonraki sayı yine okunabilir 
        }    
    	
	   //solve(....) bu metodu kullanıcaz bunun içindeki çift sayıları bulup ters çeviren algoritma döner
       Node resultHead = solve(dummy.next); //yeni başlangıç düğümü(Node resultHead),düzeltilmiş liste//dummy sahte bir node olduğu için gerçek verilerin başladığı ilk düğüm dummy.next ve listemizi buradan itibaren işleme sokuyoruz.
       printList(resultHead); //Node'daki her veriyi ekrana basarız
	}
    
	public static Node solve(Node head) {
        if (head == null || head.next == null) return head; //Base Case, liste boşsa yapılacak başka bir işlem olmadığı için node'u olduğu gibi geri döndürüyoruz
       
        if ((int)head.data % 2 == 0) { //Node'un hem çift sayı olup olmadığını kontrol ediyoruz hem de Node sınıfında veri Object tipinde tutulduğu için onu integer veri tipine dönüştürüyoruz
            Node temporaryHead = reverseEvenPart(head); //Eğer Node çift ise bloğu ters çeviriyoruz ve ters dönmüş head Node'unu temporaryHead değişkenine atıyoruz
            head.next = solve(head.next); //liste sonuna kadar gidiyoruz
            return temporaryHead; //işlemi tamamlanan kısmın yeni başlangıç noktasını ilettik.
       
        } else { //eğer çift değilse direkt burdan devam ederiz
        	head.next = solve(head.next);
            return head;
        }
    }
	
	private static Node reverseEvenPart(Node current) {
        Node previous = null; //ters çevirme yaparken node'da next pointerı kendisine bağlamak için bir önceki node'u hafızada tutmamız gerekir, başlangıçta başka bir şey olmadığı için null olarak ayarladık 
        Node first = current; //çift sayı bloğunun ilk node'unu saklarız çünkü blok ters çevrildiğinde bu node artık bloğun sonuncusu olacak
        
        while (current != null && (int)current.data % 2 == 0) {
        	//önce current != null 'le güvenlik kontrolu yaparız eğer sonuna geldiysek bakıcak bir şey kalmamış demektir.
        	//(int)current.data % 2 == 0 bu node çift sayı mı diye kontrol ederiz çünkü soru bizden sadece ardışık çift sayıları ters çevirmemizi istiyor araya herhangi bir tek sayı girerse döngüyü durduracağız.
          
        	//pointers: previous, current, nextTemp
        	Node nextTemp = current.next; //Current node'unun önündeki node'la olan bağını koparacağız o yüzden onu yedekliyoruz
            current.next = previous; //asıl işi yapan satırımız bu normalde current node'u ileri bakıyordu bu sayede previous node'una yani bir önceki node'a bakmasını sağladık yani zincirin yönünü tersine çevirdik
            previous = current; // artık burda current node'unu previous node'u yapmış olduk
            current = nextTemp; //önceden yedeklediğimiz gelecekteki node'u şimdi current yapıyoruz böylece döngü başa döndüğünde bir sonraki vagon için aynı işlemleri yapmaya hazır oluyoruz
        }
        
        first.next = current; 
        return previous; 
    }
	
	public static void printList(Node head) {
        while (head != null) { //bu döngü listenin başından başlar yani head ve son node'a kadar tüm vagonları gezer, yani next değeri null olana kadar devam eder
            System.out.print(head.data + (head.next != null ? " " : "")); //Eğer bu vagon son vagon değilse, sayının yanına bir boşluk karakteri ekledik, eğer son vagonsa boşluk eklemedik
            head = head.next; //bir sonraki node'a geçmemizi sağlar, bu satırı yazmazsak döngü hep aynı node'da kalır
        }
        System.out.println();
    }
}



       
       
       
       
