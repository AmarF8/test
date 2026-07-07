package org.apache.logging.log4j.core.async;

import java.util.concurrent.BlockingQueue;

public interface BlockingQueueFactory<E> {
  public static final String ELEMENT_TYPE = "BlockingQueueFactory";
  
  BlockingQueue<E> create(int paramInt);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\async\BlockingQueueFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */