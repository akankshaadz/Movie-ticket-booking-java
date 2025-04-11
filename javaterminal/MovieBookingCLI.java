import java.util.*;

public class MovieBookingCLI {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, List<String>> bookedSeats = new HashMap<>();
    private static final String[] movies = {
        "Avengers: Endgame", "Inception", "Interstellar", "Joker", "The Dark Knight",
        "Titanic", "Avatar", "Spider-Man: No Way Home", "Dune", "The Matrix",
        "Black Panther", "Doctor Strange", "John Wick", "Iron Man", "Toy Story"
    };
    
    public static void main(String[] args) {
        System.out.println("Welcome to Movie Ticket Booking System!");
        login();
    }
    
    private static void login() {
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();
        
        if (username.equals("admin") && password.equals("password")) {
            System.out.println("Login successful!");
            selectMovie();
        } else {
            System.out.println("Invalid credentials. Try again.");
            login();
        }
    }
    
    private static void selectMovie() {
        System.out.println("Select a movie:");
        for (int i = 0; i < movies.length; i++) {
            System.out.println((i + 1) + ". " + movies[i]);
        }
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        
        if (choice > 0 && choice <= movies.length) {
            String selectedMovie = movies[choice - 1];
            System.out.println("You selected: " + selectedMovie);
            bookSeats(selectedMovie);
        } else {
            System.out.println("Invalid choice. Try again.");
            selectMovie();
        }
    }
    
    private static void bookSeats(String movie) {
        System.out.println("Available seats: A1, A2, A3, B1, B2, B3, C1, C2, C3");
        System.out.print("Enter seat numbers separated by commas: ");
        scanner.nextLine(); // Consume newline
        String seatsInput = scanner.nextLine();
        List<String> selectedSeats = Arrays.asList(seatsInput.split(","));
        
        bookedSeats.put(movie, selectedSeats);
        System.out.println("Seats booked successfully!");
        proceedToPayment(movie, selectedSeats);
    }
    
    private static void proceedToPayment(String movie, List<String> seats) {
        int amount = seats.size() * 500;
        System.out.println("Total amount: Rs" + amount);
        System.out.print("Confirm payment (yes/no): ");
        String confirm = scanner.next();
        
        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println("Payment Successful!");
            System.out.println("Movie: " + movie);
            System.out.println("Seats: " + String.join(", ", seats));
            System.out.println("Thank you for booking!");
        } else {
            System.out.println("Payment canceled. Restarting booking.");
            selectMovie();
        }
    }
}
