package net.kyori.adventure.key;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.intellij.lang.annotations.Pattern;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
@Pattern("(?:([a-z0-9_\\-.]+:)?|:)[a-z0-9_\\-./]+")
public @interface KeyPattern {
  @Documented
  @Retention(RetentionPolicy.CLASS)
  @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
  @Pattern("[a-z0-9_\\-./]+")
  public static @interface Value {}
  
  @Documented
  @Retention(RetentionPolicy.CLASS)
  @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
  @Pattern("[a-z0-9_\\-.]+")
  public static @interface Namespace {}
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\key\KeyPattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */