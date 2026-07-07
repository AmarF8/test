package org.apache.logging.log4j.core.appender.rolling;

public interface RolloverListener {
  void rolloverTriggered(String paramString);
  
  void rolloverComplete(String paramString);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\appender\rolling\RolloverListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */