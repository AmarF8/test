package com.atlas.client.b;

import com.atlas.common.fabric.emoji.data.EmojiData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_5250;

public class a {
  private static final int a = 8;
  
  private static final int b = 14;
  
  private static final int c = 160;
  
  private static final int d = -586149864;
  
  private static final int e = -14009800;
  
  private static final int f = -15061462;
  
  private static final int g = -1;
  
  private static final int h = -7820664;
  
  private static final Pattern i = Pattern.compile(":([a-z_]+):");
  
  private List<EmojiData.EmojiEntry> j = Collections.emptyList();
  
  private int k = 0;
  
  private boolean l = false;
  
  private String m = "";
  
  private boolean n = false;
  
  private int o = -1;
  
  private a p;
  
  public void a(a parama) {
    this.p = parama;
  }
  
  public boolean a() {
    return (this.l && !this.j.isEmpty());
  }
  
  public boolean b() {
    return this.n;
  }
  
  public void a(String paramString, int paramInt) {
    if (this.n)
      return; 
    if (paramString.isEmpty() || paramInt <= 0) {
      c();
      return;
    } 
    if (paramString.startsWith("/")) {
      c();
      return;
    } 
    String str1 = paramString.substring(0, paramInt);
    int i = str1.lastIndexOf(':');
    if (i < 0) {
      c();
      return;
    } 
    String str2 = str1.substring(i + 1);
    if (str2.contains(":") || str2.contains(" ")) {
      c();
      return;
    } 
    if (!str2.isEmpty() && !str2.matches("[a-zA-Z_]+")) {
      c();
      return;
    } 
    this.m = str2;
    this.j = EmojiData.getInstance().findByPrefix(this.m, 2147483647);
    this.k = 0;
    this.l = !this.j.isEmpty();
  }
  
  public void c() {
    this.l = false;
    this.j = Collections.emptyList();
    this.k = 0;
    this.m = "";
    this.n = false;
    this.o = -1;
  }
  
  public boolean a(int paramInt) {
    EmojiData.EmojiEntry emojiEntry;
    if (!a())
      return false; 
    switch (paramInt) {
      case 265:
        if (this.n)
          break; 
        this.k = Math.max(0, this.k - 1);
        return true;
      case 264:
        if (this.n)
          break; 
        this.k = Math.min(this.j.size() - 1, this.k + 1);
        return true;
      case 258:
        if (this.p == null)
          return false; 
        if (!this.n) {
          this.n = true;
        } else {
          this.k = (this.k + 1) % this.j.size();
        } 
        emojiEntry = this.j.get(this.k);
        this.p.complete(this.o, emojiEntry.name());
        return true;
      case 257:
        return false;
      case 256:
        c();
        return true;
    } 
    return false;
  }
  
  public void b(int paramInt) {
    this.o = paramInt;
  }
  
  public int d() {
    return this.o;
  }
  
  public EmojiData.EmojiEntry e() {
    return (!a() || this.k >= this.j.size()) ? null : this.j.get(this.k);
  }
  
  public String f() {
    return this.m;
  }
  
  public void a(class_332 paramclass_332, class_327 paramclass_327, int paramInt) {
    if (!a())
      return; 
    int i = Math.min(this.j.size(), 8);
    int j = i * 14 + 4;
    byte b1 = 4;
    int k = paramInt - j - 2;
    int m = 0;
    if (this.j.size() > 8) {
      m = this.k - 4;
      m = Math.max(0, Math.min(m, this.j.size() - 8));
    } 
    paramclass_332.method_51448().method_22903();
    paramclass_332.method_51448().method_46416(0.0F, 0.0F, 200.0F);
    paramclass_332.method_25294(b1, k, b1 + 160, k + j, -586149864);
    paramclass_332.method_49601(b1, k, 160, j, -14009800);
    if (m > 0)
      paramclass_332.method_51433(paramclass_327, "▲", b1 + 160 - 12, k + 2, -7820664, false); 
    if (m + 8 < this.j.size())
      paramclass_332.method_51433(paramclass_327, "▼", b1 + 160 - 12, k + j - 10, -7820664, false); 
    for (byte b2 = 0; b2 < i; b2++) {
      int n = b2 + m;
      EmojiData.EmojiEntry emojiEntry = this.j.get(n);
      int i1 = k + 2 + b2 * 14;
      if (n == this.k)
        paramclass_332.method_25294(b1 + 1, i1, b1 + 160 - 1, i1 + 14, -15061462); 
      class_5250 class_5250 = class_2561.method_43471(emojiEntry.key()).method_27694(paramclass_2583 -> paramclass_2583.method_27704(class_2960.method_60654(paramEmojiEntry.font())));
      paramclass_332.method_51439(paramclass_327, (class_2561)class_5250, b1 + 4, i1 + 3, -1, false);
      String str = ":" + emojiEntry.name() + ":";
      boolean bool = (n == this.k) ? true : true;
      paramclass_332.method_51433(paramclass_327, str, b1 + 18, i1 + 3, bool, true);
    } 
    paramclass_332.method_51448().method_22909();
  }
  
  public int g() {
    return Math.min(this.j.size(), 8);
  }
  
  public void a(class_332 paramclass_332, class_327 paramclass_327, String paramString, int paramInt) {
    if (paramString.isEmpty() || !paramString.contains(":"))
      return; 
    Matcher matcher = i.matcher(paramString);
    ArrayList<EmojiData.EmojiEntry> arrayList = new ArrayList();
    while (matcher.find()) {
      EmojiData.EmojiEntry emojiEntry = EmojiData.getInstance().findByName(matcher.group(1));
      if (emojiEntry != null)
        arrayList.add(emojiEntry); 
    } 
    if (arrayList.isEmpty())
      return; 
    int i = paramInt - (a() ? (g() * 14 + 8) : 0) - 16;
    byte b1 = 4;
    paramclass_332.method_51448().method_22903();
    paramclass_332.method_51448().method_46416(0.0F, 0.0F, 200.0F);
    int j = arrayList.size() * 20 + 4;
    paramclass_332.method_25294(b1, i, b1 + j, i + 14, -586149864);
    paramclass_332.method_49601(b1, i, j, 14, -14009800);
    for (byte b2 = 0; b2 < arrayList.size(); b2++) {
      EmojiData.EmojiEntry emojiEntry = arrayList.get(b2);
      class_5250 class_5250 = class_2561.method_43471(emojiEntry.key()).method_27694(paramclass_2583 -> paramclass_2583.method_27704(class_2960.method_60654(paramEmojiEntry.font())));
      paramclass_332.method_51439(paramclass_327, (class_2561)class_5250, b1 + 4 + b2 * 20, i + 3, -1, false);
    } 
    paramclass_332.method_51448().method_22909();
  }
  
  @FunctionalInterface
  public static interface a {
    void complete(int param1Int, String param1String);
  }
}


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-client-1.2.0.jar!\com\atlas\client\b\a.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */