package org.apache.logging.log4j.core.appender.rolling;

public interface DirectFileRolloverStrategy {
  String getCurrentFileName(RollingFileManager paramRollingFileManager);
  
  void clearCurrentFileName();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\appender\rolling\DirectFileRolloverStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */