package uz.learn.geostreamhub.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import uz.learn.geostreamhub.kafka.LocationKafkaProducer;
import uz.learn.geostreamhub.model.LocationPoint;
import uz.learn.geostreamhub.repository.LocationPointRepository;

@Service
public class LocationService {

    private final LocationPointRepository locationPointRepository;
    private final LocationKafkaProducer kafkaProducer;
    private final Sinks.Many<LocationPoint> locationSink;

    public LocationService(LocationPointRepository locationPointRepository,
                           LocationKafkaProducer kafkaProducer) {
        this.locationPointRepository = locationPointRepository;
        this.kafkaProducer = kafkaProducer;
        this.locationSink = Sinks.many().multicast().directBestEffort();
    }

    public Mono<LocationPoint> processLocation(LocationPoint point) {
        return locationPointRepository.save(point)
                .flatMap(savedPoint ->
                        kafkaProducer.sendLocationEvent(savedPoint)
                                .thenReturn(savedPoint)
                )
                .doOnNext(savedPoint -> locationSink.tryEmitNext(savedPoint));
    }

    public Flux<LocationPoint> getStreamForCourier(String courierId) {
        return locationSink.asFlux()
                .filter(point -> point.courierId().equals(courierId));
    }
}