package com.atlas.client.packet;

import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_9141;
import org.jetbrains.annotations.NotNull;

public class VerifyRequestPacket implements class_8710 {
  @NotNull
  public static final class_8710.class_9154<VerifyRequestPacket> PACKET_ID = new class_8710.class_9154(class_2960.method_60655("atlas", "client_verify_request"));
  
  public static final class_9139<class_2540, VerifyRequestPacket> CODEC = class_9139.method_56438(VerifyRequestPacket::write, VerifyRequestPacket::new);
  
  private final String hash;
  
  private final String integrityHex;
  
  private final String baseHash;
  
  public VerifyRequestPacket(String paramString1, String paramString2, String paramString3) {
    this.hash = paramString1;
    this.integrityHex = paramString2;
    this.baseHash = paramString3;
  }
  
  public VerifyRequestPacket(class_2540 paramclass_2540) {
    this.hash = paramclass_2540.method_19772();
    this.integrityHex = paramclass_2540.method_19772();
    this.baseHash = paramclass_2540.method_19772();
  }
  
  public String getHash() {
    return this.hash;
  }
  
  public String getIntegrityHex() {
    return this.integrityHex;
  }
  
  public String getBaseHash() {
    return this.baseHash;
  }
  
  public class_8710.class_9154<? extends class_8710> method_56479() {
    return (class_8710.class_9154)PACKET_ID;
  }
  
  public void write(class_2540 paramclass_2540) {
    paramclass_2540.method_10814(this.hash);
    paramclass_2540.method_10814(this.integrityHex);
    paramclass_2540.method_10814(this.baseHash);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\packet\VerifyRequestPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */