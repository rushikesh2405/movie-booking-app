package application.movie.repository;


import application.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

    Movie findByMovieReferenceId(String movieReferenceId);
}
