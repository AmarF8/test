package org.apache.logging.log4j.core.parser;

import org.apache.logging.log4j.core.LogEvent;

public interface TextLogEventParser extends LogEventParser {
  LogEvent parseFrom(String paramString) throws ParseException;
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\parser\TextLogEventParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */