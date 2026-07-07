package org.apache.logging.log4j.core.async;

import org.apache.logging.log4j.Level;

public interface AsyncQueueFullPolicy {
  EventRoute getRoute(long paramLong, Level paramLevel);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\async\AsyncQueueFullPolicy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */