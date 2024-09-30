package application.movie.data;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Data
public class Mall {
    private List<String> mall = Arrays.asList("NexusMall","TrinityMall","OrionMall");

    @Data
    public static class TheatreMovies {
        private NexusMallMovies nexusMallMovies;
        private OrionMallMovies orionMallMovies;
        private TrinityMallMovies trinityMallMovies;
    }

    @Data
    public static class NexusMallMovies{
        private List<String> movie = Arrays.asList("Hasi Toh Phasi","Good News","Mr and Mrs Mahi");
        private LinkedHashMap<String,Double> moviePrices = new LinkedHashMap<>();

        public NexusMallMovies(){
            moviePrices.put("Hasi Toh Phasi",200.00);
            moviePrices.put("Good News",300.00);
            moviePrices.put("Mr and Mrs Mahi",400.00);
        }
    }

    @Data
    public static class OrionMallMovies{
        private List<String> movie = Arrays.asList("Bad Boys", "Deadpool", "Captain America");
        private LinkedHashMap<String,Double> moviePrices = new LinkedHashMap<>();

        public OrionMallMovies(){
            moviePrices.put("Bad Boys",200.00);
            moviePrices.put("Deadpool",400.00);
            moviePrices.put("Captain America",500.00);
        }
    }

    @Data
    public static class TrinityMallMovies{
        private List<String> movie = Arrays.asList("Stree2", "BadNewz", "Kill");
        private LinkedHashMap<String,Double> moviePrices = new LinkedHashMap<>();

        public TrinityMallMovies(){
            moviePrices.put("Stree2",250.00);
            moviePrices.put("BadNewz",300.00);
            moviePrices.put("Kill",450.00);
        }
    }


    @Data
    @Component
    public static class OfferCodes{
        private String code1 = "BMS30";
        private String code2 = "BMS40";
        private String code3 = "BMS50";
    }
}
