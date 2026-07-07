package com.atlas.client.c;

import com.atlas.client.a.b;
import com.atlas.client.a.c;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

public final class a {
  private volatile boolean a = false;
  
  private static byte[] f() {
    int i = c.a();
    int j = com.atlas.client.a.a.a();
    int k = b.a();
    int m = com.atlas.client.e.a.a.a();
    int n = com.atlas.client.stations.a.a();
    byte[] arrayOfByte1 = { 
        (byte)(i >>> 24), (byte)(i >>> 16), (byte)(i >>> 8), (byte)i, (byte)(j >>> 24), (byte)(j >>> 16), (byte)(j >>> 8), (byte)j, (byte)(k >>> 24), (byte)(k >>> 16), 
        (byte)(k >>> 8), (byte)k, (byte)(m >>> 24), (byte)(m >>> 16), (byte)(m >>> 8), (byte)m };
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length];
    for (byte b = 0; b < arrayOfByte2.length; b++)
      arrayOfByte2[b] = (byte)(arrayOfByte1[b] ^ n + b); 
    return arrayOfByte2;
  }
  
  public boolean a() {
    return this.a;
  }
  
  public void b() {
    this.a = true;
  }
  
  public void c() {
    this.a = false;
  }
  
  @NotNull
  public String d() {
    try {
      Collection collection = FabricLoader.getInstance().getAllMods();
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      List list = collection.stream().sorted(Comparator.comparing(paramModContainer -> paramModContainer.getMetadata().getId())).toList();
      Path path = FabricLoader.getInstance().getGameDir().resolve("mods").toRealPath(new java.nio.file.LinkOption[0]);
      byte[] arrayOfByte = new byte[8192];
      for (ModContainer modContainer : list) {
        Path path1 = null;
        try {
          List<Path> list1 = modContainer.getOrigin().getPaths();
          if (!list1.isEmpty())
            path1 = list1.get(0); 
        } catch (UnsupportedOperationException unsupportedOperationException) {}
        if (path1 == null || !Files.isRegularFile(path1, new java.nio.file.LinkOption[0]) || !path1.toRealPath(new java.nio.file.LinkOption[0]).startsWith(path))
          continue; 
        InputStream inputStream = Files.newInputStream(path1, new java.nio.file.OpenOption[0]);
        try {
          int i;
          while ((i = inputStream.read(arrayOfByte)) != -1)
            messageDigest.update(arrayOfByte, 0, i); 
          if (inputStream != null)
            inputStream.close(); 
        } catch (Throwable throwable) {
          if (inputStream != null)
            try {
              inputStream.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            }  
          throw throwable;
        } 
      } 
      return DigestUtils.sha256Hex(messageDigest.digest());
    } catch (Exception exception) {
      throw new IllegalStateException("Failed to get the hash.", exception);
    } 
  }
  
  @NotNull
  public String a(@NotNull String paramString, @NotNull byte[] paramArrayOfbyte, boolean paramBoolean) {
    try {
      String str = d();
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(new SecretKeySpec(paramArrayOfbyte, "HmacSHA256"));
      mac.update((str + str).getBytes(StandardCharsets.UTF_8));
      mac.update(f());
      if (paramBoolean)
        mac.update(g()); 
      byte[] arrayOfByte = mac.doFinal();
      StringBuilder stringBuilder = new StringBuilder(arrayOfByte.length * 2);
      for (byte b : arrayOfByte) {
        stringBuilder.append(String.format("%02x", new Object[] { Integer.valueOf(b & 0xFF) }));
      } 
      return stringBuilder.toString();
    } catch (Exception exception) {
      throw new IllegalStateException("Failed to get the challenge hash.", exception);
    } 
  }
  
  @NotNull
  public String e() {
    byte[] arrayOfByte = g();
    if (arrayOfByte.length == 0)
      return ""; 
    StringBuilder stringBuilder = new StringBuilder(arrayOfByte.length * 2);
    for (byte b : arrayOfByte) {
      stringBuilder.append(String.format("%02x", new Object[] { Integer.valueOf(b & 0xFF) }));
    } 
    return stringBuilder.toString();
  }
  
  private static byte[] g() {
    try {
      String str = "/" + a.class.getName().replace('.', '/') + ".class";
      InputStream inputStream = a.class.getResourceAsStream(str);
      try {
        if (inputStream == null) {
          byte[] arrayOfByte = new byte[0];
          if (inputStream != null)
            inputStream.close(); 
          return arrayOfByte;
        } 
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] arrayOfByte1 = new byte[4096];
        int i;
        while ((i = inputStream.read(arrayOfByte1)) != -1)
          messageDigest.update(arrayOfByte1, 0, i); 
        byte[] arrayOfByte2 = messageDigest.digest();
        if (inputStream != null)
          inputStream.close(); 
        return arrayOfByte2;
      } catch (Throwable throwable) {
        if (inputStream != null)
          try {
            inputStream.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          }  
        throw throwable;
      } 
    } catch (Exception exception) {
      return new byte[0];
    } 
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\c\a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */