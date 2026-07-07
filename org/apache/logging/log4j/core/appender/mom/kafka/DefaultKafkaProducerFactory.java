/*    */ package org.apache.logging.log4j.core.appender.mom.kafka;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.kafka.clients.producer.KafkaProducer;
/*    */ import org.apache.kafka.clients.producer.Producer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultKafkaProducerFactory
/*    */   implements KafkaProducerFactory
/*    */ {
/*    */   public Producer<byte[], byte[]> newKafkaProducer(Properties config) {
/* 40 */     return (Producer<byte[], byte[]>)new KafkaProducer(config);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\appender\mom\kafka\DefaultKafkaProducerFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */