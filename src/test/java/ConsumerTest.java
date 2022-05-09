import org.junit.jupiter.api.Test;

public class ConsumerTest {
  @Test
  public void pollTest() {
    KafkaExerciseConsumer consumer = new KafkaExerciseConsumer();
    consumer.subscribe(Topic.KUCKJWI);
    consumer.consume();
  }
}
