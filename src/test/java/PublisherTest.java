import org.junit.jupiter.api.Test;

public class PublisherTest {
  @Test
  public void publishTest() throws Exception {
    KafkaExerciseProducer producer = new KafkaExerciseProducer();
    producer.send(Topic.KUCKJWI, 1, "kuckjwi");
    producer.send(Topic.KUCKJWI, 1, "isis");
    producer.send(Topic.KUCKJWI, -1, "exit");
  }
}
