package org.apache.logging.log4j.core.lookup;

import org.apache.logging.log4j.core.LogEvent;

public interface StrLookup {
  public static final String CATEGORY = "Lookup";
  
  String lookup(String paramString);
  
  String lookup(LogEvent paramLogEvent, String paramString);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\lookup\StrLookup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */