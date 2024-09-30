package application.movie.controller;


import application.movie.data.dto.BookingPayload;
import application.movie.data.dto.BookingResponse;
import application.movie.data.dto.MoviePayload;
import application.movie.data.dto.MovieResponse;
import application.movie.service.LandingPageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookMyShow")
@AllArgsConstructor
public class LandingPageController {

    private LandingPageService service;

    @PostMapping("/movie")
    public MovieResponse addMovie(@RequestBody MoviePayload payload){
        return service.chooseMovie(payload);
    }

    @PostMapping("/booking")
    public BookingResponse bookMovie(@RequestBody BookingPayload payload){
        return service.doBooking(payload);
    }

}
