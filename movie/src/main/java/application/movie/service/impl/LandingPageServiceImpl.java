package application.movie.service.impl;

import application.movie.data.Mall;
import application.movie.data.dto.BookingPayload;
import application.movie.data.dto.BookingResponse;
import application.movie.data.dto.MoviePayload;
import application.movie.data.dto.MovieResponse;
import application.movie.exception.*;
import application.movie.model.Booking;
import application.movie.model.Movie;
import application.movie.repository.BookingRepo;
import application.movie.repository.MovieRepo;
import application.movie.service.LandingPageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LandingPageServiceImpl implements LandingPageService {

    private final Mall mall;
    private final Mall.TheatreMovies theatreMovies;
    private final Mall.OfferCodes codes;
    private final MovieRepo repo;
    private final BookingRepo bookingRepo;

    @Override
    public MovieResponse chooseMovie(MoviePayload payload) { //customerName, theatreName, movieName, seats, offerCode

        // get movieTheatre

        String movieTheatre = payload.getMovieTheatre().toLowerCase();

        // check if theatre exists
        boolean theatreExists = false;

        for(String m : mall.getMall()){
            if(m.equalsIgnoreCase(movieTheatre)) {
                // if(NexusMall.contains("Alexis");
                // if(TrinityMall.contains("Alexis");
                // if(OrionMall.contains("Alexis");
                theatreExists = true;
                break;
            }
        }
        if(!theatreExists){
            String errorMessage = "Invalid Theatre Name: " + movieTheatre;
            log.error(errorMessage);
            throw new InvalidTheatreNameException(errorMessage);
        }

//        // check if movies exists in the theatre
//        boolean isMovieValid = false;
//
//        for(String nexusMovies : theatreMovies.getNexusMallMovies().getMovie()){
//            if(payload.getMovieName().equalsIgnoreCase(nexusMovies)){
//                isMovieValid = true;
//                break;
//            }
//        }
//        for(String orionMovies : theatreMovies.getOrionMallMovies().getMovie()){
//            if(payload.getMovieName().equalsIgnoreCase(orionMovies)){
//                isMovieValid = true;
//                break;
//            }
//        }
//        for(String trinityMovies : theatreMovies.getTrinityMallMovies().getMovie()){
//            if(payload.getMovieName().equalsIgnoreCase(trinityMovies)){
//               isMovieValid = true;
//               break;
//            }
//        }

        // Determine which mall's movie list to use based on the theatre name
        List<String> movieList = null;

        switch (movieTheatre) {
            case "nexusmall":
                movieList = theatreMovies.getNexusMallMovies().getMovie();
                break;
            case "orionmall":
                movieList = theatreMovies.getOrionMallMovies().getMovie();
                break;
            case "trinitymall":
                movieList = theatreMovies.getTrinityMallMovies().getMovie();
                break;
            default:
                String errorMessage = "Invalid Theatre Name: " + movieTheatre;
                log.error(errorMessage);
                throw new InvalidTheatreNameException(errorMessage);
        }

        // Validate the movie within the chosen theatre's list
        boolean isMovieValid = false;
        for(String movie : movieList){
         if(payload.getMovieName().equalsIgnoreCase(movie)){
             isMovieValid = true;
             break;
         }
        }

        if(!isMovieValid){
            String errorMessage = "Invalid Movie Name" + payload.getMovieName();
            log.error(errorMessage);
            throw new InvalidMovieNameException(errorMessage);
        }


        // set the price of the movie
        double price = 0.0;

        List<String> movie;
        LinkedHashMap<String,Double> moviePrices;
        switch (payload.getMovieTheatre().toLowerCase()) {
            case "nexusmall":
                movie = theatreMovies.getNexusMallMovies().getMovie();
                moviePrices = theatreMovies.getNexusMallMovies().getMoviePrices();
                for(String movieName : movie){
                    if(moviePrices.containsKey(payload.getMovieName())){
                        price = moviePrices.get(movieName) * payload.getSeats();
                        break;
                    }
                }
                break;
            case "orionmall":
                movie = theatreMovies.getOrionMallMovies().getMovie();
                moviePrices = theatreMovies.getOrionMallMovies().getMoviePrices();
                for(String movieName : movie){
                    if(moviePrices.containsKey(payload.getMovieName())){
                        price = moviePrices.get(movieName) * payload.getSeats();
                        break;
                    }
                }
                break;
            case "trinitymall":
                movie = theatreMovies.getTrinityMallMovies().getMovie();
                moviePrices = theatreMovies.getTrinityMallMovies().getMoviePrices();
                for(String movieName : movie){
                    if(moviePrices.containsKey(payload.getMovieName())){
                        price = moviePrices.get(movieName) * payload.getSeats();
                        break;
                    }
                }
                break;
            default:
                break;
        }

        MovieResponse response = new MovieResponse();
        response.setMovieTheatre(movieTheatre);
        response.setMovieName(payload.getMovieName());
        response.setSeats(payload.getSeats());
        response.setCustomerName(payload.getCustomerName());
        response.setPrice(price);

        // generate unique reference id
        String reference = UUID.randomUUID().toString();

        response.setMovieReferenceId(reference);

        Movie saveMovie = new Movie();
        saveMovie.setName(response.getMovieName());
        saveMovie.setCustomerName(response.getCustomerName());
        saveMovie.setMovieReferenceId(response.getMovieReferenceId());
        saveMovie.setPurchase(response.getPrice());
        saveMovie.setNoOfSeats(response.getSeats());
        saveMovie.setTheatre(response.getMovieTheatre());

        repo.save(saveMovie);

        return response;

    }

    @Override
    public BookingResponse doBooking(BookingPayload payload) {
        double totalAmount = 0.0;
        Movie movieInfo;
        List<Integer> movieIds = new ArrayList<>();
        Booking booking = new Booking();
        // extract movie info for the referenceIds in a loop (one by one) and add the sum into totalAmount
           for(String referenceId : payload.getMovieReferenceIds()){
                   movieInfo = repo.findByMovieReferenceId(referenceId);
                   if(!movieInfo.getMovieReferenceId().equals(referenceId)){
                       String errorMessage = "Not valid MovieReferenceId " + referenceId;
                       log.error(errorMessage);
                       throw new InvalidMovieReferenceException(errorMessage);
                   }
                   totalAmount = totalAmount + movieInfo.getPurchase();
                   movieIds.add(movieInfo.getId());
               }

        double finalPrice = getOffer(payload.getOfferCode(),totalAmount);


        BookingResponse response = new BookingResponse();
        response.setFinalPrice(finalPrice);
        response.setMessage("Thank you for your patronage");

        booking.setFinalBookingAmount(response.getFinalPrice());
        booking.setOfferCode(payload.getOfferCode());
        booking.setMovieReferenceId(payload.getMovieReferenceIds());
        booking.setMovieId(movieIds);
        bookingRepo.save(booking);
        return response;
    }

    public Double getOffer(String offerCode,double totalAmount){
        // check if the offerCode provided is valid
        List<String> offerCodes = Arrays.asList(codes.getCode1(),codes.getCode2(),codes.getCode3());
        double finalPrice = 0.0;
        if(offerCodes.contains(offerCode)){
            // if offerCode is valid perform below piggyback logic
            switch(offerCode){
                case "BMS30":
                    finalPrice = totalAmount * (1-0.30);
                    break;
                case "BMS40":
                    finalPrice = totalAmount * (1-0.40);
                    break;
                case "BMS50":
                    finalPrice = totalAmount * (1-0.50);
                    break;
                default:
                    String errorMessage = "No Such Coupon " + offerCode;
                    log.error(errorMessage);
                    throw new NoSuchCouponException(errorMessage);
            }
        } else {
            String errorMessage = "Invalid Offer Code" + offerCode;
            log.error(errorMessage);
            throw new InvalidOfferCodeException(errorMessage);
        }
        return finalPrice;
    }
}
