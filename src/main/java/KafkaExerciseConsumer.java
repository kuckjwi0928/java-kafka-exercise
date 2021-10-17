import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class KafkaExerciseConsumer implements AutoCloseable, Consumer {
  private KafkaConsumer<Integer, String> consumer;

  private KafkaConsumerProperties kafkaConsumerProperties;

  public KafkaExerciseConsumer() {
    this(
            List.of("localhost:50001", "localhost:50002", "localhost:50003"),
            "org.apache.kafka.common.serialization.IntegerDeserializer",
            "org.apache.kafka.common.serialization.StringDeserializer",
            false,
            "consumer-group-1"
    );
  }

  public KafkaExerciseConsumer(List<String> bootStrapServers, String keySerializer,
                               String valueSerializer, boolean autoCommit, String groupId) {
    kafkaConsumerProperties = new KafkaConsumerProperties(bootStrapServers, keySerializer, valueSerializer, autoCommit, groupId);
    consumer = new KafkaConsumer<>(kafkaConsumerProperties.toProperties());
  }

  public KafkaConsumerProperties getKafkaConsumerProperties() {
    return kafkaConsumerProperties;
  }

  @Override
  public void close() throws Exception {
    consumer.close();
  }

  @Override
  public void subscribe(Topic topic) {
    consumer.subscribe(Collections.singletonList(topic.getName()));
  }

  @Override
  public void consume() {
    String lastValue = "";
    do {
      ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(1L));
      for (ConsumerRecord<Integer, String> record : records) {
        System.out.printf("partition: %s, key: %s, value: %s%n", record.partition(), record.key(), record.value());
        lastValue = record.value();
      }
      consumer.commitSync();
    } while(!lastValue.equalsIgnoreCase("exit"));
    System.out.println("Consumer ended.");
  }
}
