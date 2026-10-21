package uz.learn.geostreamhub.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("location_points")
public record LocationPoint(
        @Id
        Long id,
        String courierId,
        Double latitude,
        Double longitude,
        Instant timestamp
) {
    @JsonCreator
    public LocationPoint(
            @JsonProperty("id") Long id,
            @JsonProperty("courierId") String courierId,
            @JsonProperty("latitude") Double latitude,
            @JsonProperty("longitude") Double longitude,
            @JsonProperty("timestamp") Instant timestamp
    ) {
        this.id = id;
        this.courierId = courierId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }
}