package org.apache.logging.log4j.core.time;

import org.apache.logging.log4j.util.StringBuilderFormattable;

public interface Instant extends StringBuilderFormattable {
  long getEpochSecond();
  
  int getNanoOfSecond();
  
  long getEpochMillisecond();
  
  int getNanoOfMillisecond();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\time\Instant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */