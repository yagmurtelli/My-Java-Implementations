import java.util.Scanner;

public class Q {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Engine type (L/H): ");
        String engineType = scanner.nextLine();

        
        if (!engineType.equals("L") && !engineType.equals("H")) {
            System.out.println("Error: Invalid engine type. Please enter 'L' or 'H'.");
            return;
        }

       
        System.out.print("Age: ");
        int carAge = scanner.nextInt();

        
        System.out.print("Distance (km): ");
        int distance = scanner.nextInt();

       
        scanner.close();

        
        double fuelConsumptionRate;
        double fuelCostPerLiter;

        if (engineType.equals("L")) {
            fuelConsumptionRate = 3.3;   // LPG engine spends 3.3 lt. / 100 km
            fuelCostPerLiter = 1.6;      // LPG fuel is 1.6 TL / 1 lt.
        } else {
            fuelConsumptionRate = 4.2;   // Hybrid engine spends 4.2 lt. / 100 km
            fuelCostPerLiter = 2.1;      // Hybrid fuel is 2.1 TL / 1 lt.
        }

        double totalCost = calculateTotalCost(carAge, distance, fuelConsumptionRate, fuelCostPerLiter);

       
        totalCost = Math.round(totalCost * 100.0) / 100.0;
        System.out.println("Total cost of fuel = " + totalCost + " TL");
    }

    private static double calculateTotalCost(int carAge, int distance, double fuelConsumptionRate, double fuelCostPerLiter) {
       
        double totalFuelConsumed = (fuelConsumptionRate / 100) * distance;

       
        double totalCost = totalFuelConsumed * fuelCostPerLiter;

        
        if (carAge > 7 && carAge <= 12) {
            totalCost *= 1.03; 
        } else if (carAge > 12) {
            totalCost *= 1.07; 
        }

        return totalCost;
    }
}






