package cinema;

public class MovieTickets implements IMovieTickets {
    private final String[] movies = {"Napoleon", "Oppenheimer"};
    private final int[][] ticketSales = {
        {3000, 1500, 1700}, // Napoleon's ticket sales for Jan, Feb, Mar
        {3500, 1200, 1600}  // Oppenheimer's ticket sales for Jan, Feb, Mar
    };
    
    @Override
    public int TotalMovieSales(int[] movieTicketSales) {
        int total = 0;
        for (int sales : movieTicketSales) {
            total += sales;
        }
        return total;
    }
    
    @Override
    public String TopMovie(String[] movies, int[] totalSales) {
        int topIndex = 0;
        for (int i = 1; i < totalSales.length; i++) {
            if (totalSales[i] > totalSales[topIndex]) {
                topIndex = i;
            }
        }
        return movies[topIndex];
    }
    
    public void displayReport() {
        int[] totalSales = new int[movies.length];
        
        System.out.println("MOVIE TICKET SALES REPORT - 2024\n");
        
        String[] months = {"JAN", "FEB", "MAR"};
        for (int i = 0; i < months.length; i++) {
            System.out.println(months[i]);
            for (int j = 0; j < movies.length; j++) {
                System.out.println(movies[j] + ": " + ticketSales[j][i]);
                totalSales[j] += ticketSales[j][i];
            }
            System.out.println();
        }
        
        for (int i = 0; i < movies.length; i++) {
            System.out.println("Total movie ticket sales for " + movies[i] + ": " + totalSales[i]);
        }
        
        System.out.println("\nTop performing movie: " + TopMovie(movies, totalSales));
    }
}
