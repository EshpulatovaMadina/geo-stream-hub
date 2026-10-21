package uz.learn.geostreamhub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("couriers")
public record Courier(
    @Id
    Long id,
    String name,
    String phone,
    String status // MASALAN: AVAILABLE, BUSY, OFFLINE
) {
}