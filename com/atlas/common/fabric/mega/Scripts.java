/*    */ package com.atlas.common.fabric.mega;
/*    */ 
/*    */ import com.cobblemon.mod.common.Cobblemon;
/*    */ import com.cobblemon.mod.common.api.Priority;
/*    */ import com.cobblemon.mod.common.api.data.DataRegistry;
/*    */ import com.cobblemon.mod.common.api.reactive.SimpleObservable;
/*    */ import com.cobblemon.mod.common.battles.runner.ShowdownService;
/*    */ import com.cobblemon.mod.common.battles.runner.graal.GraalShowdownService;
/*    */ import com.cobblemon.mod.relocations.graalvm.polyglot.Value;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import kotlin.Unit;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_3222;
/*    */ import net.minecraft.class_3264;
/*    */ import net.minecraft.class_3298;
/*    */ import net.minecraft.class_3300;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class Scripts implements DataRegistry {
/* 28 */   private static final class_2960 ID = class_2960.method_60655("gg", "showdown/scripts");
/* 29 */   private static final SimpleObservable<Scripts> OBSERVABLE = new SimpleObservable();
/* 30 */   private final Map<String, String> scripts = new HashMap<>();
/* 31 */   public static final Scripts INSTANCE = new Scripts();
/*    */   
/*    */   private Scripts() {
/* 34 */     OBSERVABLE.subscribe(Priority.NORMAL, this::scriptsLoad);
/*    */   }
/*    */   
/*    */   public void scriptsLoad(Scripts scripts) {
/* 38 */     Cobblemon.INSTANCE.getShowdownThread().queue(showdownService -> {
/*    */           if (showdownService instanceof GraalShowdownService) {
/*    */             GraalShowdownService service = (GraalShowdownService)showdownService;
/*    */             Value receiveScriptDataFn = service.context.getBindings("js").getMember("receiveScriptData");
/*    */             for (Map.Entry<String, String> entry : INSTANCE.getScripts().entrySet()) {
/*    */               String scriptId = entry.getKey();
/*    */               String js = ((String)entry.getValue()).replace("\n", " ");
/*    */               receiveScriptDataFn.execute(new Object[] { scriptId, js });
/*    */             } 
/*    */           } 
/*    */           return Unit.INSTANCE;
/*    */         });
/*    */   }
/*    */   public Map<String, String> getScripts() {
/* 52 */     return this.scripts;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_2960 getId() {
/* 58 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_3264 getType() {
/* 64 */     return class_3264.field_14190;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SimpleObservable<? extends DataRegistry> getObservable() {
/* 70 */     return (SimpleObservable)OBSERVABLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reload(@NotNull class_3300 resourceManager) {
/* 75 */     this.scripts.clear();
/* 76 */     resourceManager.method_14488("showdown/scripts", path -> path.method_12832().endsWith(".js")).forEach((id, resource) -> { try { BufferedReader reader = new BufferedReader(new InputStreamReader(resource.method_14482(), StandardCharsets.UTF_8)); try { String js = reader.lines().collect(Collectors.joining("\n")); String scriptId = (new File(id.method_12832())).getName().replace(".js", ""); this.scripts.put(scriptId, js); reader.close(); }
/* 77 */             catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1)
/*    */               { throwable.addSuppressed(throwable1); }
/*    */                throw throwable; }
/*    */              }
/* 81 */           catch (IOException e)
/*    */           { LogManager.getLogger().error("Failed to load script: {} {}", id, e); }
/*    */         
/*    */         });
/*    */     
/* 86 */     OBSERVABLE.emit((Object[])new Scripts[] { this });
/*    */   }
/*    */   
/*    */   public void sync(@NotNull class_3222 serverPlayer) {}
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mega\Scripts.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */