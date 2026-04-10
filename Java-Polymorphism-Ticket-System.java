import java.util.Scanner;

class Ticket {
   
    public void bookTicket() {
       
    }
}

class StandardTicket extends Ticket {
    
    @Override
    public void bookTicket() {
        System.out.println("Standard Ticket booked: $8");
    }
}

class VIPTicket extends Ticket {
  
    @Override
    public void bookTicket() {
        System.out.println("VIP Ticket booked: $20");
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Ticket ticket = null; 

        try {
         
            System.out.print("Choose a ticket type (1: Standard, 2: VIP): ");
            int choice = sc.nextInt(); 

            if (choice == 2) {
                ticket = new VIPTicket(); 
            } else if (choice == 1) {
                ticket = new StandardTicket(); 
            } else {
                System.out.println("Invalid choice, defaulting to Standard Ticket.");
                ticket = new StandardTicket(); 
            }
        } catch (Exception e) {
            System.out.println("Invalid input, defaulting to Standard Ticket.");
            ticket = new StandardTicket(); 
        } finally {
            
            if (ticket != null) {
                ticket.bookTicket();
            }
            sc.close(); 
        }
    }
}
	
	
	
	


