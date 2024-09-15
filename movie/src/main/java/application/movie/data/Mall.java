package application.movie.data;

import lombok.Data;

import java.util.Arrays;
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
    }

    @Data
    public static class OrionMallMovies{
        private List<String> movie = Arrays.asList("Bad Boys", "Deadpool", "Captain America");
    }

    @Data
    public static class TrinityMallMovies{
        private List<String> movie = Arrays.asList("Stree2", "BadNewz", "Kill");
    }
}
