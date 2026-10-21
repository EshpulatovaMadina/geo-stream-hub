package uz.learn.geostreamhub.stream;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import uz.learn.geostreamhub.model.LocationPoint;

@Component
public class LocationStreamProcessor {

    private final Sinks.Many<LocationPoint> sink = Sinks.many().multicast().onBackpressureBuffer();

    public void emitLocation(LocationPoint locationPoint) {
        sink.tryEmitNext(locationPoint);
    }

    public Flux<LocationPoint> getStream() {
        return sink.asFlux();
    }
}
