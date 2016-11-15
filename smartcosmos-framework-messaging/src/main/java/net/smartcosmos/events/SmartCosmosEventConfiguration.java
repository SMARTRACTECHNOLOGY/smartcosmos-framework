package net.smartcosmos.events;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import net.smartcosmos.events.kafka.KafkaSmartCosmosEventTemplate;

/**
 * Configures the libraries based on current support mechanisms present.
 */
@Configuration
public class SmartCosmosEventConfiguration {

    @Configuration
    @EnableKafka
    @ConditionalOnMissingBean(SmartCosmosEventTemplate.class)
    @ConditionalOnClass(KafkaTemplate.class)
    protected static class KafkaSmartCosmosEventTemplateConfiguration {

        @Value("${smartcosmos.service.kafka.address}")
        private String brokerAddress;

        @Value("${smartcosmos.service.zookeeper.address}")
        private String zookeeperConnect;

        /**
         * Application Name defines the CONSUMER GROUP, which is vital to Pub/Sub so that only a single instance of each service will receive an event.
         */
        @Value("${spring.application.name}")
        private String springApplicationName;

        @Bean
        @Primary
        ConcurrentKafkaListenerContainerFactory<UUID, String> kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<UUID, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
        }

        @Bean
        public ConsumerFactory<UUID, String> consumerFactory() {
            return new DefaultKafkaConsumerFactory<>(consumerConfigs());
        }

        @Bean
        public Map<String, Object> consumerConfigs() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, this.springApplicationName);
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
            props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SmartCosmosEventJsonDeserializer.class);
            return props;
        }

        @Bean
        public ProducerFactory<UUID, String> producerFactory() {
            return new DefaultKafkaProducerFactory<>(producerConfigs());
        }

        @Bean
        public Map<String, Object> producerConfigs() {
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
            props.put(ProducerConfig.RETRIES_CONFIG, 0);
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
            props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return props;
        }

        @Bean
        public KafkaTemplate<UUID, String> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }

        @Bean
        public SmartCosmosEventTemplate smartCosmosEventTemplate(KafkaTemplate<UUID, String> kafkaTemplate) {
            return new KafkaSmartCosmosEventTemplate(kafkaTemplate);
        }
    }

}
