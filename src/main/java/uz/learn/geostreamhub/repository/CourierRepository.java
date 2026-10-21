package uz.learn.geostreamhub.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import uz.learn.geostreamhub.model.Courier;
import reactor.core.publisher.Flux;

public interface CourierRepository extends ReactiveCrudRepository<Courier, Long> {

    Flux<Courier> findByStatus(String status);
}