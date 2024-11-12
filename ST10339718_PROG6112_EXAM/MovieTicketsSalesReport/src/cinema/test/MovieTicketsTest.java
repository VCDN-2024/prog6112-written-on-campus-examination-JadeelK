package cinema.test;

import cinema.MovieTickets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTicketsTest {

    @Test
    public void CalculateTotalSales_ReturnsExpectedTotalSales() {
        MovieTickets movieTickets = new MovieTickets();
        int[] napoleonSales = {3000, 1500, 1700};
        int expectedTotal = 6200;
        int actualTotal = movieTickets.TotalMovieSales(napoleonSales);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void TopMovieSales_ReturnsTopMovie() {
        MovieTickets movieTickets = new MovieTickets();
        String[] movies = {"Napoleon", "Oppenheimer"};
        int[] totalSales = {6200, 6300};
        String expectedTopMovie = "Oppenheimer";
        String actualTopMovie = movieTickets.TopMovie(movies, totalSales);
        assertEquals(expectedTopMovie, actualTopMovie);
    }
}
