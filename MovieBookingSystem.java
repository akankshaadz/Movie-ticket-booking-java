import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MovieBookingSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Movie Ticket Booking - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        gbc.gridx = 0; gbc.gridy = 0;
        add(userLabel, gbc);
        gbc.gridx = 1;
        add(userField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        add(passLabel, gbc);
        gbc.gridx = 1;
        add(passField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        add(loginButton, gbc);
        gbc.gridx = 1;
        add(registerButton, gbc);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (authenticate(username, password)) {
                new MovieSelectionFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("password");
    }
}

class MovieSelectionFrame extends JFrame {
    public MovieSelectionFrame() {
        setTitle("Select a Movie");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 4, 10, 10));
        getContentPane().setBackground(Color.BLACK);

        String[] movies = {"Avengers: Endgame", "Inception", "Interstellar", "Joker", "The Dark Knight", "Titanic", "Avatar", "Spider-Man: No Way Home", "Dune", "The Matrix", "Black Panther", "Doctor Strange", "John Wick", "Iron Man", "Toy Story"};
        
        for (String movie : movies) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JLabel imageLabel = new JLabel(new ImageIcon("placeholder.jpg"));
            JButton movieButton = new JButton(movie);
            
            movieButton.addActionListener(e -> {
                new SeatBookingFrame(movie);
                dispose();
            });
            
            panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(movieButton, BorderLayout.SOUTH);
            add(panel);
        }

        setVisible(true);
    }
}

class SeatBookingFrame extends JFrame {
    private List<String> selectedSeats = new ArrayList<>();
    
    public SeatBookingFrame(String movie) {
        setTitle("Select Seats - " + movie);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 6, 5, 5));
        getContentPane().setBackground(Color.DARK_GRAY);

        JButton[][] seats = new JButton[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String seatName = (char) ('A' + i) + String.valueOf(j + 1);
                seats[i][j] = new JButton(seatName);
                seats[i][j].setBackground(Color.LIGHT_GRAY);
                add(seats[i][j]);
                
                int row = i, col = j;
                seats[i][j].addActionListener(e -> {
                    if (selectedSeats.contains(seatName)) {
                        selectedSeats.remove(seatName);
                        seats[row][col].setBackground(Color.LIGHT_GRAY);
                    } else {
                        selectedSeats.add(seatName);
                        seats[row][col].setBackground(Color.GREEN);
                    }
                });
            }
        }
        
        JButton payButton = new JButton("Proceed to Payment");
        add(payButton);

        payButton.addActionListener(e -> {
            new PaymentFrame(movie, selectedSeats);
            dispose();
        });

        setVisible(true);
    }
}

class PaymentFrame extends JFrame {
    public PaymentFrame(String movie, List<String> seats) {
        setTitle("Payment - " + movie);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel amountLabel = new JLabel("Amount: ₹" + (seats.size() * 500), SwingConstants.CENTER);
        amountLabel.setForeground(Color.WHITE);
        JLabel seatLabel = new JLabel("Seats: " + String.join(", ", seats), SwingConstants.CENTER);
        seatLabel.setForeground(Color.WHITE);
        JButton payButton = new JButton("Pay Now");

        gbc.gridx = 0; gbc.gridy = 0;
        add(amountLabel, gbc);
        gbc.gridy = 1;
        add(seatLabel, gbc);
        gbc.gridy = 2;
        add(payButton, gbc);

        payButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Payment Successful! Ticket Booked for " + movie + "\nSeats: " + String.join(", ", seats), "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        setVisible(true);
    }
}
