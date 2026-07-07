package com.atlas.client.packet;

import java.util.Base64;
import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_9141;
import org.jetbrains.annotations.NotNull;

public class VerifyChallengePacket implements class_8710 {
  @NotNull
  public static final class_8710.class_9154<VerifyChallengePacket> PACKET_ID = new class_8710.class_9154(class_2960.method_60655("atlas", "client_verify_challenge"));
  
  public static final class_9139<class_2540, VerifyChallengePacket> CODEC = class_9139.method_56438(VerifyChallengePacket::write, VerifyChallengePacket::new);
  
  private final String nonce;
  
  private final byte[] hmacKey;
  
  private final boolean integrityEnabled;
  
  public VerifyChallengePacket(String paramString, byte[] paramArrayOfbyte, boolean paramBoolean) {
    this.nonce = paramString;
    this.hmacKey = paramArrayOfbyte;
    this.integrityEnabled = paramBoolean;
  }
  
  public VerifyChallengePacket(class_2540 paramclass_2540) {
    this.nonce = paramclass_2540.method_19772();
    this.hmacKey = Base64.getDecoder().decode(paramclass_2540.method_19772());
    this.integrityEnabled = paramclass_2540.method_19772().equals("1");
  }
  
  public String getNonce() {
    return this.nonce;
  }
  
  public byte[] getHmacKey() {
    return this.hmacKey;
  }
  
  public boolean isIntegrityEnabled() {
    return this.integrityEnabled;
  }
  
  public class_8710.class_9154<? extends class_8710> method_56479() {
    return (class_8710.class_9154)PACKET_ID;
  }
  
  public void write(class_2540 paramclass_2540) {
    paramclass_2540.method_10814(this.nonce);
    paramclass_2540.method_10814(Base64.getEncoder().encodeToString(this.hmacKey));
    paramclass_2540.method_10814(this.integrityEnabled ? "1" : "0");
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\packet\VerifyChallengePacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */