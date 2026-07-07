package org.apache.logging.log4j.core.config.builder.api;

public interface LoggableComponentBuilder<T extends ComponentBuilder<T>> extends FilterableComponentBuilder<T> {
  T add(AppenderRefComponentBuilder paramAppenderRefComponentBuilder);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\config\builder\api\LoggableComponentBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */