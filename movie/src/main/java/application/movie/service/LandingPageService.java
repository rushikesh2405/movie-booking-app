package application.movie.service;

import application.movie.data.dto.MoviePayload;
import application.movie.data.dto.MovieResponse;

public interface LandingPageService {
    public MovieResponse chooseMovie(MoviePayload payload);
}