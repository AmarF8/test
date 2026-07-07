package com.atlas.client.packet;

import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_9141;
import org.jetbrains.annotations.NotNull;

public class VerifyResponsePacket implements class_8710 {
  @NotNull
  public static final class_8710.class_9154<VerifyResponsePacket> PACKET_ID = new class_8710.class_9154(class_2960.method_60655("atlas", "client_verify_response"));
  
  public static final class_9139<class_2540, VerifyResponsePacket> CODEC = class_9139.method_56438(VerifyResponsePacket::write, VerifyResponsePacket::new);
  
  private final boolean verified;
  
  public VerifyResponsePacket(boolean paramBoolean) {
    this.verified = paramBoolean;
  }
  
  public VerifyResponsePacket(class_2540 paramclass_2540) {
    this.verified = paramclass_2540.readBoolean();
  }
  
  public boolean isVerified() {
    return this.verified;
  }
  
  public class_8710.class_9154<? extends class_8710> method_56479() {
    return (class_8710.class_9154)PACKET_ID;
  }
  
  public void write(class_2540 paramclass_2540) {
    paramclass_2540.method_52964(this.verified);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\packet\VerifyResponsePacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */