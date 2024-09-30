package application.movie.repository;

import application.movie.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {

     @Query("select b.movieReferenceId from Booking b where b.movieReferenceId = :movieReferenceId ")
     String findByMovieReferenceId(@Param("movieReferenceId") String movieReferenceId);
}
