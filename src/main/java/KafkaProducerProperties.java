import java.util.List;
import java.util.Properties;

public record KafkaProducerProperties(List<String> bootStrapServers, String keySerializer, String valueSerializer) {
  private final static String BOOTSTRAP_SERVERS = "bootstrap.servers";

  private final static String KEY_SERIALIZER = "key.serializer";

  private final static String VALUE_SERIALIZER = "value.serializer";

  public Properties toProperties() {
    Properties properties = new Properties();
    properties.setProperty(BOOTSTRAP_SERVERS, String.join(",", bootStrapServers)); // broker host and port
    // kafka에서는 모든 메시지가 직렬화된 상태로 전송이 됨 따라서 그에 맞는 시리얼라이저 클래스를 지정해야함
    properties.setProperty(KEY_SERIALIZER, keySerializer); // key serializer
    properties.setProperty(VALUE_SERIALIZER, valueSerializer); // value serializer
    return properties;
  }
}
