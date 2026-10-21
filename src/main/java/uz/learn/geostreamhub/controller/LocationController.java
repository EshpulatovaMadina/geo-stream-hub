package uz.learn.geostreamhub.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.learn.geostreamhub.model.LocationPoint;
import uz.learn.geostreamhub.service.LocationService;
import uz.learn.geostreamhub.stream.LocationStreamProcessor;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;
    private final LocationStreamProcessor streamProcessor;

    public LocationController(LocationService locationService,
                              LocationStreamProcessor streamProcessor) {
        this.locationService = locationService;
        this.streamProcessor = streamProcessor;
    }

    // 1. Kurer GPS koordinatasini yuboradi (POST)
    @PostMapping
    public Mono<LocationPoint> updateLocation(@RequestBody LocationPoint point) {
        return locationService.processLocation(point);
    }

    // 2. Mijozlar/Xarita kurer lokatsiyasini real vaqtda kuzatib boradi (SSE oqimi)
    @GetMapping(value = "/stream/{courierId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<LocationPoint> streamLocation(@PathVariable String courierId) {
        return locationService.getStreamForCourier(courierId);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<LocationPoint> streamLocations() {
        return streamProcessor.getStream();
    }
}