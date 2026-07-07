package net.kyori.adventure.text;

import org.jetbrains.annotations.NotNull;

public interface VirtualComponent extends TextComponent {
  @NotNull
  Class<?> contextType();
  
  @NotNull
  VirtualComponentRenderer<?> renderer();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\VirtualComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */