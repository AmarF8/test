package org.apache.logging.log4j.core.config.builder.api;

public interface FilterableComponentBuilder<T extends ComponentBuilder<T>> extends ComponentBuilder<T> {
  T add(FilterComponentBuilder paramFilterComponentBuilder);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\config\builder\api\FilterableComponentBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */