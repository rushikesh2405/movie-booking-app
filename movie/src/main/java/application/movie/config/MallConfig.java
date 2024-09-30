package application.movie.config;

import application.movie.data.Mall;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MallConfig {

    @Bean
    public Mall mall(){
        Mall mall = new Mall();
        return mall;
    }

    @Bean
    public Mall.TheatreMovies theatreMovies(){
        Mall.NexusMallMovies nexusMallMovies = new Mall.NexusMallMovies();
        Mall.OrionMallMovies orionMallMovies = new Mall.OrionMallMovies();
        Mall.TrinityMallMovies trinityMallMovies = new Mall.TrinityMallMovies();

        Mall.TheatreMovies theatreMovies = new Mall.TheatreMovies();
        theatreMovies.setNexusMallMovies(nexusMallMovies);
        theatreMovies.setOrionMallMovies(orionMallMovies);
        theatreMovies.setTrinityMallMovies(trinityMallMovies);
        return theatreMovies;
    }

}
