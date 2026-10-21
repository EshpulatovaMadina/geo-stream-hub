package uz.learn.geostreamhub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("delivery_orders")
public record DeliveryOrder(
    @Id
    Long id,
    String customerName,
    String deliveryAddress,
    Long courierId, // Qaysi kurer biriktirilgani
    String status,  // MASALAN: CREATED, IN_PROGRESS, COMPLETED
    Instant createdAt
) {
}