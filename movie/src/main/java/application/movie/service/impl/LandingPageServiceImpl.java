package application.movie.service.impl;

import application.movie.data.Mall;
import application.movie.data.dto.MoviePayload;
import application.movie.data.dto.MovieResponse;
import application.movie.service.LandingPageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LandingPageServiceImpl implements LandingPageService {

    public Mall mall = new Mall();
    public Mall.TheatreMovies theatreMovies = new Mall.TheatreMovies();
    public MovieResponse response = new MovieResponse();

    @Override
    public MovieResponse chooseMovie(MoviePayload payload) { //customerName, theatreName, movieName, seats, offerCode
        boolean theatreExists = false;

        for(String m : mall.getMall()){
            if(m.equalsIgnoreCase(payload.getMovieTheatre())) {
                // if(NexusMall.contains("Alexis");
                // if(TrinityMall.contains("Alexis");
                // if(OrionMall.contains("Alexis");
                theatreExists = true;
                break;
            }
        }
        if(!theatreExists){
               throw new RuntimeException("Invalid Theatre Name");
        }

//        for(String nexusMovies : theatreMovies.getNexusMallMovies().getMovie()){
//            if(!payload.getMovieName().equalsIgnoreCase(nexusMovies)){
//                log.info("Invalid Movie Name", payload.getMovieName());
//            }
//        }
//        for(String orionMovies : theatreMovies.getOrionMallMovies().getMovie()){
//            if(!payload.getMovieName().equalsIgnoreCase(orionMovies)){
//                log.info("Invalid Movie Name", payload.getMovieName());
//            }
//        }
//        for(String trinityMovies : theatreMovies.getTrinityMallMovies().getMovie()){
//            if(!payload.getMovieName().equalsIgnoreCase(trinityMovies)){
//                log.info("Invalid Movie Name", payload.getMovieName());
//            }
//        }
//        return null;

        response.setMovieTheatre(payload.getMovieTheatre());
        response.setMovieName(payload.getMovieName());
        response.setSeats(payload.getSeats());
        response.setOfferCode(payload.getOfferCode());
        response.setCustomerName(payload.getCustomerName());
        response.setPrice(0.0);

        return response;

    }
}
