package application.movie.model;

import application.movie.config.ListToJsonConvertor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Convert(converter = ListToJsonConvertor.class)
    private List<Integer> movieId;

    @Convert(converter = ListToJsonConvertor.class)
    private List<String> movieReferenceId;

    private Double finalBookingAmount;

    private String offerCode;


}
