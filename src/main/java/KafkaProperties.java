import java.util.List;
import java.util.Properties;

public record KafkaProperties(List<String> bootStrapServers, String keySerializer, String valueSerializer) {
  public Properties toProperties() {
    Properties properties = new Properties();
    properties.setProperty("bootstrap.servers", String.join(",", bootStrapServers));
    properties.setProperty("key.serializer", keySerializer);
    properties.setProperty("value.serializer", valueSerializer);
    return properties;
  }
}
