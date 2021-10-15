import java.util.concurrent.ExecutionException;

public interface Producer<KEY, VALUE> {
  void send(Topic topic, KEY key, VALUE value) throws ExecutionException, InterruptedException;
}
