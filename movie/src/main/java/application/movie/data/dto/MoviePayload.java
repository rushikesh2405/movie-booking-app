package application.movie.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
public class MoviePayload {
    private String customerName;
    private String movieTheatre; // = "NexusMall"
    private String movieName;
    private int seats;
    private String offerCode;
}
