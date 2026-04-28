import java.util.Scanner;
///////////////////////
// Node Class
// Aile ağacındaki tek bir kişiyi temsil eder.
///////////////////////
class Node {
    String name;   // Person's name
    int age;       // Person's age
    Node left;     // Left child
    Node right;    // Right child
    // Constructor
    Node(String name, int age) {
        this.name = name;
        this.age = age;
        this.left = null;
        this.right = null;
    }
}
///////////////////////
// FamilyTree Class
// Handles all tree operations
///////////////////////

class FamilyTree {
    Node root; // Root of the tree
   
    ///////////////////////
    // Method: insert
    // Summary: Yaşa göre BST'ye yeni bir düğüm ekler.
    ///////////////////////
    
    public Node insert(Node root, String name, int age) {
        if (root == null) {
            return new Node(name, age);
        }

        //Yeni kişi mevcut node'dan küçük mü büyük mü oba bakıyoruz.
        if (age < root.age) {
            root.left = insert(root.left, name, age);
        } else {
            root.right = insert(root.right, name, age);
        }

        return root;
    }

    ///////////////////////
    // Method: find
    // Summary: İsimle node bulur.
    ///////////////////////
    
    public Node find(Node root, String name) {
        if (root == null) return null;

        //Bu isimde kişi ağaçta nerede ona bakıyoruz.
        
        if (root.name.equals(name)) return root;  //Eğer aradığın kişi buysa, direkt geri dön, aramayı bitir.
 
        Node leftResult = find(root.left, name); //Sol subtree’de arıyoruz
        if (leftResult != null) return leftResult; //Eğer solda bulduysak sağa gitmeye gerek yok

        return find(root.right, name); //Solda yoksa, sağ subtree’de arıyoruz.
    }

    ///////////////////////
    // Method: findParent
    // Summary: Verilen node'un parentını bulur.
    ///////////////////////
    
    public Node findParent(Node root, String name) {
        if (root == null) return null;

        // Eğer root'un çocuklarından biri aranan kişi ise:
        if ((root.left != null && root.left.name.equals(name)) ||
            (root.right != null && root.right.name.equals(name))) {
            return root;
        }

        // Solda arıyoruz
        Node leftResult = findParent(root.left, name);
        if (leftResult != null) return leftResult;

        // Sağda arıyoruz
        return findParent(root.right, name);
    }

    ///////////////////////
    // Method: marry
    // Summary: Adds spouse(eş) to the tree
    // Rules:
    // - Spouse is added as sibling (same level)
    // - Inserted under parent of the person
    ///////////////////////
    
    public void marry(String name, String spouseName, int age) {
        Node person = find(root, name);

        if (person == null) {
            System.out.println("Person not found!");
            return;
        }

        ///////////////////////
        if (person == root) { //Evlenen kişi root ise
        	
        	//Ödevde istenen; eğer kişi root ise, eşi root’un sağ çocuğu olarak eklenir.
        	
            if (root.right == null) { //Root’un sağ tarafı boşsa: buraya eşi koyabiliriz
            	
                root.right = new Node(spouseName, age); //Yeni node oluştururuz ve sağa bağlarız
            } else {
                System.out.println("Insertion failed!"); //Sağ taraf doluysa: başka yer yok, ekleme başarısız
            }
            return;
        }

        Node parent = findParent(root, name);

        if (parent.left == null) {
            parent.left = new Node(spouseName, age);
        } else if (parent.right == null) {
            parent.right = new Node(spouseName, age);
        } else {
            System.out.println("Insertion failed!");
        }
    }

    ///////////////////////
    // Method: inorder
    // Summary: Prints tree using in-order traversal
    ///////////////////////
    
    //Bu Fonksiyon Ne Yapıyor = Ağacı in-order (LNR) şeklinde geziyor: Left > Node > Right
    public void inorder(Node root) {
        if (root == null) return; //base case

        inorder(root.left); //Önce sol tarafa git, en küçük yaşlar burada
        
        System.out.print(root.name + " ");
      
        inorder(root.right); //daha büyük yaşlar
       
    }
}

///////////////////////
// Main Class
// Handles input and execution
///////////////////////
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        FamilyTree tree = new FamilyTree(); //Boş bir aile ağacı başlatılır

        int n = scanner.nextInt(); // Kaç işlem yapılacak (kaç satır komut var)

        for (int i = 0; i < n; i++) {
            String command = scanner.next(); //Bu ya: "BIRTH" ya da "MARRIED"

            if (command.equals("BIRTH")) {
                String name = scanner.next();
                int age = scanner.nextInt();

                tree.root = tree.insert(tree.root, name, age); //Kişi BST’ye eklenir
            }
            else if (command.equals("MARRIED")) {
                String name = scanner.next(); // mevcut kişi
                String spouseName = scanner.next(); //// yeni kişi
                int age = scanner.nextInt();

                tree.marry(name, spouseName, age); //Evlilik kuralına göre ekleme yapılır
            }
        }

        // Print final tree
        tree.inorder(tree.root);
    }
}
