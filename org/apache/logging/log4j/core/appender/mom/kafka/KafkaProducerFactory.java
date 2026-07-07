package org.apache.logging.log4j.core.appender.mom.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;

public interface KafkaProducerFactory {
  Producer<byte[], byte[]> newKafkaProducer(Properties paramProperties);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\appender\mom\kafka\KafkaProducerFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */