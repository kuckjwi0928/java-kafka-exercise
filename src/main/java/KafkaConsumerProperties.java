import java.util.List;
import java.util.Properties;

public record KafkaConsumerProperties(
        List<String> bootStrapServers, String keyDeserializer, String valueDeserializer,
        boolean autoCommit, String groupId
) {
  private final static String BOOTSTRAP_SERVERS = "bootstrap.servers";

  private final static String KEY_DESERIALIZER = "key.deserializer";

  private final static String VALUE_DESERIALIZER = "value.deserializer";

  private final static String AUTO_COMMIT = "enable.auto.commit";

  private final static String GROUP_ID = "group.id";

  public Properties toProperties() {
    Properties properties = new Properties();
    properties.setProperty(BOOTSTRAP_SERVERS, String.join(",", bootStrapServers)); // broker host and port
    properties.setProperty(KEY_DESERIALIZER, keyDeserializer); // key deserializer
    properties.setProperty(VALUE_DESERIALIZER, valueDeserializer); // value deserializer
    properties.setProperty(AUTO_COMMIT, Boolean.toString(autoCommit)); // auto commit
    properties.setProperty(GROUP_ID, groupId); // group id
    return properties;
  }
}
