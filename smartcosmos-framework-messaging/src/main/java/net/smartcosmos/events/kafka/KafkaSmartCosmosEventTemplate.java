package net.smartcosmos.events.kafka;

import lombok.extern.slf4j.Slf4j;
import net.smartcosmos.events.AbstractSmartCosmosEventTemplate;
import net.smartcosmos.events.SmartCosmosEvent;
import net.smartcosmos.events.SmartCosmosEventException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author voor
 */
@Slf4j
public class KafkaSmartCosmosEventTemplate extends AbstractSmartCosmosEventTemplate {

    private final KafkaTemplate kafkaTemplate;

    public KafkaSmartCosmosEventTemplate(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void convertAndSend(SmartCosmosEvent<Object> message) throws SmartCosmosEventException {
        MessageBuilder builder = MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC, message.getEventType());

        if (StringUtils.hasText(message.getEventUrn())) {
            builder.setHeader(KafkaHeaders.MESSAGE_KEY,message.getEventUrn());
        }

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(builder.build());
        future.addCallback(result -> log.info("Event Successfully sent to Kafka topic {}, partition {}", result.getRecordMetadata().topic(), result.getRecordMetadata().partition()),
            ex -> log.error("Failed to send event to Kafka", ex));
    }
}
