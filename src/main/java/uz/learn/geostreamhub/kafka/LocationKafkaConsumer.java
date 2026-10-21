package uz.learn.geostreamhub.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import uz.learn.geostreamhub.model.LocationPoint;
import uz.learn.geostreamhub.stream.LocationStreamProcessor;

@Component
public class LocationKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(LocationKafkaConsumer.class);

    private final LocationStreamProcessor streamProcessor;
    private final ObjectMapper objectMapper;

    public LocationKafkaConsumer(LocationStreamProcessor streamProcessor, ObjectMapper objectMapper) {
        this.streamProcessor = streamProcessor;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "courier-locations", groupId = "geo-stream-group")
    public void consume(String messageJson) {
        try {
            log.info("Kafka'dan xom xabar keldi: {}", messageJson);
            LocationPoint message = objectMapper.readValue(messageJson, LocationPoint.class);

            log.info("Muvaffaqiyatli o'qildi: courierId = {}, lat = {}, lon = {}",
                    message.courierId(), message.latitude(), message.longitude());

            streamProcessor.emitLocation(message);
        } catch (Exception e) {
            log.error("Kafka xabarini o'qishda xatolik yuz berdi: {}", messageJson, e);
        }
    }
}