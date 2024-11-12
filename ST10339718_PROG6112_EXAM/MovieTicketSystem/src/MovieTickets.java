public class MovieTickets implements IMovieTickets {

    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        double totalPrice = numberOfTickets * ticketPrice;
        double vatAmount = totalPrice * 0.14;  // 14% VAT
        return totalPrice + vatAmount;
    }

    public boolean ValidateData(MovieTicketData movieTicketData) {
        // Validation checks
        if (movieTicketData.getMovieName().isEmpty()) {
            return false; // Movie name cannot be empty
        }
        if (movieTicketData.getTicketPrice() <= 0) {
            return false; // Ticket price must be greater than zero
        }
        if (movieTicketData.getNumberOfTickets() <= 0) {
            return false; // Number of tickets must be greater than zero
        }
        return true;
    }
}