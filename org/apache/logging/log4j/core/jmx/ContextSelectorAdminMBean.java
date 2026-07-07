package org.apache.logging.log4j.core.jmx;

public interface ContextSelectorAdminMBean {
  public static final String PATTERN = "org.apache.logging.log4j2:type=%s,component=ContextSelector";
  
  String getImplementationClassName();
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\org\apache\logging\log4j\core\jmx\ContextSelectorAdminMBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */