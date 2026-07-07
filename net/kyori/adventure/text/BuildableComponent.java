package net.kyori.adventure.text;

import net.kyori.adventure.util.Buildable;
import org.jetbrains.annotations.NotNull;

public interface BuildableComponent<C extends BuildableComponent<C, B>, B extends ComponentBuilder<C, B>> extends Buildable<C, B>, Component {
  @NotNull
  B toBuilder();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\BuildableComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */