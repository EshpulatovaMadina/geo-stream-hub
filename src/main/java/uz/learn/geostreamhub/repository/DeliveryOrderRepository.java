package uz.learn.geostreamhub.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import uz.learn.geostreamhub.model.DeliveryOrder;
import reactor.core.publisher.Flux;

public interface DeliveryOrderRepository extends ReactiveCrudRepository<DeliveryOrder, Long> {

    Flux<DeliveryOrder> findByCourierId(Long courierId);
}