import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MovieTicketApp {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Movie Ticket System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // Create 'File' menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0)); // Exit action
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        
        // Create 'Tools' menu
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(e -> processTicket(frame));
        toolsMenu.add(processItem);
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> clearFields(frame));
        toolsMenu.add(clearItem);
        menuBar.add(toolsMenu);
        
        frame.setJMenuBar(menuBar);

        // Create components for movie ticket system
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel movieLabel = new JLabel("Select Movie:");
        JComboBox<String> movieComboBox = new JComboBox<>(new String[]{"Napoleon", "Oppenheimer", "Damsel"});
        
        JLabel ticketPriceLabel = new JLabel("Ticket Price (R):");
        JTextField ticketPriceField = new JTextField();
        
        JLabel numberTicketsLabel = new JLabel("Number of Tickets:");
        JTextField numberTicketsField = new JTextField();
        
        JTextArea reportArea = new JTextArea(10, 30);
        reportArea.setEditable(false);
        
        panel.add(movieLabel);
        panel.add(movieComboBox);
        panel.add(ticketPriceLabel);
        panel.add(ticketPriceField);
        panel.add(numberTicketsLabel);
        panel.add(numberTicketsField);
        
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(reportArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void processTicket(JFrame frame) {
        // Get input data
        JTextField ticketPriceField = (JTextField) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(3);
        JTextField numberTicketsField = (JTextField) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(5);
        JComboBox<String> movieComboBox = (JComboBox<String>) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        JTextArea reportArea = (JTextArea) ((JScrollPane) frame.getContentPane().getComponent(1)).getViewport().getView();

        String movieName = (String) movieComboBox.getSelectedItem();
        double ticketPrice = Double.parseDouble(ticketPriceField.getText());
        int numberOfTickets = Integer.parseInt(numberTicketsField.getText());

        MovieTicketData movieTicketData = new MovieTicketData();
        movieTicketData.setMovieName(movieName);
        movieTicketData.setTicketPrice(ticketPrice);
        movieTicketData.setNumberOfTickets(numberOfTickets);

        MovieTickets movieTickets = new MovieTickets();
        if (movieTickets.ValidateData(movieTicketData)) {
            double totalAmount = movieTickets.CalculateTotalTicketPrice(numberOfTickets, ticketPrice);
            String report = "MOVIE TICKET REPORT\n*\n" +
                    "MOVIE NAME: " + movieName + "\n" +
                    "MOVIE TICKET PRICE: R " + ticketPrice + "\n" +
                    "NUMBER OF TICKETS: " + numberOfTickets + "\n" +
                    "TOTAL TICKET PRICE: R " + totalAmount;

            reportArea.setText(report);

            // Save report to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
                writer.write(report);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid data entered!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void clearFields(JFrame frame) {
        // Clear all fields
        JComboBox<String> movieComboBox = (JComboBox<String>) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(1);
        JTextField ticketPriceField = (JTextField) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(3);
        JTextField numberTicketsField = (JTextField) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(5);
        JTextArea reportArea = (JTextArea) ((JScrollPane) frame.getContentPane().getComponent(1)).getViewport().getView();

        movieComboBox.setSelectedIndex(0);
        ticketPriceField.setText("");
        numberTicketsField.setText("");
        reportArea.setText("");
    }
}
