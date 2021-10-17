public class KafkaExerciseProducerApplication {
  public static void main(String[] args) {
    try (
      KafkaExerciseProducer producer = new KafkaExerciseProducer();
    ) {
      producer.send(Topic.KUCKJWI, 1, "kuckjwi");
      producer.send(Topic.KUCKJWI, 1, "isis");
      producer.send(Topic.KUCKJWI, -1, "exit");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
