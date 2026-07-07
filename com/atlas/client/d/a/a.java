package com.atlas.client.d.a;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_268;

public final class a {
  private static final String a = "NPC";
  
  private static final int b = 16;
  
  public static boolean a(class_1297 paramclass_1297) {
    return (paramclass_1297.method_5864() == class_1299.field_42457 || paramclass_1297.method_5864() == class_1299.field_42623);
  }
  
  public static boolean b(class_1297 paramclass_1297) {
    if (!(paramclass_1297 instanceof net.minecraft.class_1657))
      return false; 
    class_268 class_268 = paramclass_1297.method_5781();
    return (class_268 != null && "NPC".equals(class_268.method_1197())) ? true : a(paramclass_1297.method_5477().getString());
  }
  
  private static boolean a(String paramString) {
    if (paramString.length() != 16)
      return false; 
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      if (c >= 'A' && c <= 'Z') {
        bool1 = true;
      } else if (c >= 'a' && c <= 'z') {
        bool2 = true;
      } else if (c >= '0' && c <= '9') {
        bool3 = true;
      } else {
        return false;
      } 
    } 
    return (bool1 && bool2 && bool3);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\d\a\a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */