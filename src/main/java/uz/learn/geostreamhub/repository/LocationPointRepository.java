package uz.learn.geostreamhub.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import uz.learn.geostreamhub.model.LocationPoint;
import reactor.core.publisher.Flux;

public interface LocationPointRepository extends ReactiveCrudRepository<LocationPoint, Long> {

    Flux<LocationPoint> findByCourierId(String courierId);
}