package application.movie.controller;


import application.movie.data.dto.MoviePayload;
import application.movie.data.dto.MovieResponse;
import application.movie.service.LandingPageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class LandingPageController {

    private LandingPageService service;

    @PostMapping
    public MovieResponse getPrice(@RequestBody MoviePayload payload){
        return service.chooseMovie(payload);
    }
}
