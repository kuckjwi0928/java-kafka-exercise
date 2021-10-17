public class KafkaExerciseConsumerApplication {
  public static void main(String[] args) {
    try (
      KafkaExerciseConsumer consumer = new KafkaExerciseConsumer()
    ) {
      consumer.subscribe(Topic.KUCKJWI);
      consumer.consume();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
