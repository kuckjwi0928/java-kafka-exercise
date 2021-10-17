import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaExerciseProducer implements AutoCloseable, Producer<Integer, String> {
  private final KafkaProducer<Integer, String> producer;

  private final KafkaProducerProperties kafkaProducerProperties;

  public KafkaExerciseProducer() {
    this(
      List.of("localhost:50001", "localhost:50002", "localhost:50003"),
      "org.apache.kafka.common.serialization.IntegerSerializer",
      "org.apache.kafka.common.serialization.StringSerializer"
    );
  }

  public KafkaExerciseProducer(List<String> bootStrapServers, String keySerializer, String valueSerializer) {
    kafkaProducerProperties = new KafkaProducerProperties(bootStrapServers, keySerializer, valueSerializer);
    producer = new KafkaProducer<>(kafkaProducerProperties.toProperties());
  }

  public KafkaProducerProperties getKafkaProducerProperties() {
    return kafkaProducerProperties;
  }

  @Override
  public void send(Topic topic, Integer key, String value) throws ExecutionException, InterruptedException {
    ProducerRecord<Integer, String> record = new ProducerRecord<>(topic.getName(), key, value);
    Future<RecordMetadata> recordMetadataFuture = producer.send(record);
    RecordMetadata metadata = recordMetadataFuture.get();
    System.out.printf("Success topic: %s, partition: %d, offset: %d%n", topic.getName(), metadata.partition(), metadata.offset());
  }

  @Override
  public void close() {
    producer.close();
  }
}
