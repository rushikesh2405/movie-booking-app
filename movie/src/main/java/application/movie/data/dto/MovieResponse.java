package application.movie.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private String customerName;
    private String movieTheatre; // = "NexusMall"
    private String movieName;
    private int seats;
    private double price;
    private String movieReferenceId;
}
