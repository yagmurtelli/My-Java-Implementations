import java.util.*;

//Bu sınıf şirketteki her bir çalışanı temsil eder.
class Node {
    String name; //çalışanın adı
    int salary; //maaşı
    Node firstChild; //ilk alt çalışan, bir node’un ilk çocuğunu gösterir
    Node nextSibling; //aynı seviyedeki diğer çalışan, aynı parent’a sahip diğer çocukları bağlar

    public Node(String name, int salary) {
        this.name = name;
        this.salary = salary;
        this.firstChild = null;
        this.nextSibling = null;
    }
}

//Bu kod ne yapıyor: verilen isimde bir çalışanı tree içinde bulmaya çalışıyor
class GeneralTree {
    Node root;

    public Node find(Node root, String name) {
        if (root == null) return null; //Ağaç boşsa veya dal bitmişse aramayı durdurur

        // current node kontrolü
        if (root.name.equals(name)) return root; //Eğer aranan kişi şu anki node ise direkt döndürür

        // first child üzerinden aşağı in
        Node child = root.firstChild; //İlk çocuktan başlar

        while (child != null) {  //tüm siblingleri gezeriz
            Node result = find(child, name);  //recursive olarak aşağı ineriz
            if (result != null) return result;
            child = child.nextSibling;
        }

        return null;
    }

    public void add(String parentName, String childName, int salary) {
       
    	 // parent node'u tree içinde buluruz
    	Node parent = find(root, parentName);

        // parent yoksa ekleme yapılamaz
        if (parent == null) {
            System.out.println("Parent not found!");
            return;
        }

        // Bu kod ne yapıyor: tree’ye eklenecek yeni çalışan yaratır
        Node newNode = new Node(childName, salary);

     // eğer parent'ın hiç çocuğu yoksa
        if (parent.firstChild == null) {
            parent.firstChild = newNode;
        } 
     // varsa sibling listesine ekleriz
        else {
            Node temp = parent.firstChild;
           
         // en son sibling'e kadar gideriz
            while (temp.nextSibling != null) {
                temp = temp.nextSibling;
            }
            // sona ekleriz
            temp.nextSibling = newNode;
        }
    }
    
     //Bu kod ne yapıyor: verilen çalışan tree içinde aranır (DFS).
    public void printSubordinates(String name) {
        Node node = find(root, name);

        // çalışan bulunamazsa
        if (node == null) {
            System.out.println("Employee not found.");
            return;
        }

        // alt çalışan yoksa
        if (node.firstChild == null) {
            System.out.println(name + " has no subordinates.");
            return;
        }

     // tüm child'ları yazdırırız
        Node child = node.firstChild;
        while (child != null) {
            System.out.print(child.name + " ");
            child = child.nextSibling;
        }
        System.out.println();
    }

    //Bu kod ne yapıyor: bir node’un kendisi ve tüm alt çalışanlarının maaş toplamını hesaplar
    public int totalSalary(Node root) {
        if (root == null) return 0;

        int sum = root.salary; //Önce kendisini toplama ekleriz

        Node child = root.firstChild; //ilk alt çalışan
        while (child != null) {  //tüm sibling’leri gezeriz
            sum += totalSalary(child);  //her alt ağaç kendi toplamını döndürür
            child = child.nextSibling;  // sonra aynı seviyedeki diğer çalışanlara geçeriz
        }

        return sum;
    }

    //Bu kod ne yapıyor: bir node’a ulaşmak için root’tan o node’a giden yolu buluyoruz
    public boolean findPath(Node root, String name, List<String> path) {
        if (root == null) return false;

        // current node'u path'e ekleriz
        path.add(root.name);

     // hedef bulunduysa dururuz
        if (root.name.equals(name)) return true;

        // çocuklarda ararız
        Node child = root.firstChild;
        while (child != null) {
            if (findPath(child, name, path)) return true;
            child = child.nextSibling;
        }

        // eğer bulunamadıysa geri alırız
        path.remove(path.size() - 1);
        return false;
    }

    //Bu kod ne yapıyor: iki node’un en yakın ortak atasını, manager'ı buluyoruz
    public Node LCA(String a, String b) {
        List<String> path1 = new ArrayList<>();
        List<String> path2 = new ArrayList<>();

        findPath(root, a, path1); //root > a yolu
        findPath(root, b, path2); //root > b yolu

        Node lca = null;

        for (int i = 0; i < Math.min(path1.size(), path2.size()); i++) {
           
        	if (path1.get(i).equals(path2.get(i))) { //aynı seviyedeki node’ları karşılaştırıyoruz
                lca = find(root, path1.get(i));
            } else {
                break;
            }
        }

        return lca;
    }

    public boolean isManager(String A, String B) {
        List<String> path = new ArrayList<>();
        
       // B node'una giden yolu buluyoruz
        findPath(root, B, path);
       // A, B'nin path'inde varsa A, B'nin manager'ıdır
        return path.contains(A);
    }
}

public class Q2 {
    public static void main(String[] args) {

  Scanner sc = new Scanner(System.in);
        GeneralTree tree = new GeneralTree();

        // root oluşturma
        tree.root = new Node("GeneralManager", 50000);

        // örnek veri 
        tree.add("GeneralManager", "Alice", 20000);
        tree.add("GeneralManager", "Bob", 25000);
        tree.add("Alice", "Charlie", 15000);
        tree.add("Alice", "David", 12000);

        while (true) {

    System.out.println("Menu:");
    System.out.println("1. Print Subordinates");
    System.out.println("2. Find Common Manager");
    System.out.println("3. Calculate Total Salary");
    System.out.println("4. Check Manager");
    System.out.println("5. Find Path");
    System.out.println("6. Quit");

    System.out.print("Enter your choice: ");

    String line = sc.nextLine().trim();

    int choice = Integer.parseInt(line);
 
            if (choice == 1) {
                String name = sc.next();
                tree.printSubordinates(name);
            }

            else if (choice == 2) {
                String a = sc.next();
                String b = sc.next();
                Node lca = tree.LCA(a, b);
                System.out.println(lca != null ? lca.name : "Not found");
            }

            else if (choice == 3) {
                String name = sc.next();
                Node node = tree.find(tree.root, name);
                System.out.println(tree.totalSalary(node));
            }

            else if (choice == 4) {
                String a = sc.next();
                String b = sc.next();
                System.out.println(tree.isManager(a, b));
            }

            else if (choice == 5) {
                String name = sc.next();
                List<String> path = new ArrayList<>();
                tree.findPath(tree.root, name, path);
                System.out.println(String.join(" -> ", path));
            }

            else if (choice == 6) {
                break;
            }
        }

        sc.close();
    }
}
