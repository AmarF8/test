package org.apache.logging.log4j.core.config.builder.api;

public interface ScriptFileComponentBuilder extends ComponentBuilder<ScriptFileComponentBuilder> {
  ScriptFileComponentBuilder addLanguage(String paramString);
  
  ScriptFileComponentBuilder addIsWatched(boolean paramBoolean);
  
  ScriptFileComponentBuilder addIsWatched(String paramString);
  
  ScriptFileComponentBuilder addCharset(String paramString);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\config\builder\api\ScriptFileComponentBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */