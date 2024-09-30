package application.movie.service;

import application.movie.data.dto.BookingPayload;
import application.movie.data.dto.BookingResponse;
import application.movie.data.dto.MoviePayload;
import application.movie.data.dto.MovieResponse;

public interface LandingPageService {
    MovieResponse chooseMovie(MoviePayload payload);

    BookingResponse doBooking(BookingPayload payload);
}