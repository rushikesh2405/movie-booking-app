package application.movie.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class BookingPayload {

    private List<String> movieReferenceIds;

    private String offerCode;

}
