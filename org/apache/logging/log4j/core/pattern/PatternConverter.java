package org.apache.logging.log4j.core.pattern;

public interface PatternConverter {
  public static final String CATEGORY = "Converter";
  
  void format(Object paramObject, StringBuilder paramStringBuilder);
  
  String getName();
  
  String getStyleClass(Object paramObject);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\pattern\PatternConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */