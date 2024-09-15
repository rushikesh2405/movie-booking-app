package application.movie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="movie")
public class Movie {

    @Id
    private int id;
    private String name;
    private int noOfSeats;
    private String customerName;
    private double purchase;
    private String movieReferenceId;
}