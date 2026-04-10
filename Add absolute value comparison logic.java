import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter integer k: ");
        int k = scanner.nextInt();

        System.out.print("Enter integer l: ");
        int l = scanner.nextInt();

        System.out.print("Enter integer m: ");
        int m = scanner.nextInt();

        System.out.print("Enter integer n: ");
        int n = scanner.nextInt();

       
        scanner.close();

        
        int minAbsValue = findMinAbsoluteValue(k, l, m, n);

       
        System.out.println("The integer with minimum absolute value is: " + minAbsValue);
    }

    
    private static int findMinAbsoluteValue(int... numbers) {
        int minAbsValue = Integer.MAX_VALUE;

        for (int number : numbers) {
            int absValue = Math.abs(number);
            if (absValue < minAbsValue) {
                minAbsValue = absValue;
            }
        }

        return minAbsValue;
    }
}

