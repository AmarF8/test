package org.apache.logging.log4j.core.config.plugins.validation;

public interface ConstraintValidator<A extends java.lang.annotation.Annotation> {
  void initialize(A paramA);
  
  boolean isValid(String paramString, Object paramObject);
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\config\plugins\validation\ConstraintValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */