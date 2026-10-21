package uz.learn.geostreamhub.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uz.learn.geostreamhub.model.LocationPoint;
import reactor.core.publisher.Mono;

@Service
public class LocationKafkaProducer {

    private final KafkaTemplate<String, LocationPoint> kafkaTemplate;
    private static final String TOPIC = "courier-locations";

    public LocationKafkaProducer(KafkaTemplate<String, LocationPoint> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // GPS nuqtani Kafka topic'ga yuborish (Non-blocking tarzda)
    public Mono<Void> sendLocationEvent(LocationPoint point) {
        return Mono.fromFuture(kafkaTemplate.send(TOPIC, point.courierId(), point).toCompletableFuture())
                .then();
    }
}