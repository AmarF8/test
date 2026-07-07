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
/*    */ public class Conditions
/*    */   implements DataRegistry {
/* 29 */   private static final class_2960 ID = class_2960.method_60655("gg", "showdown/conditions");
/* 30 */   private static final SimpleObservable<Conditions> OBSERVABLE = new SimpleObservable();
/* 31 */   public static final Conditions INSTANCE = new Conditions();
/* 32 */   private final Map<String, String> conditionScripts = new HashMap<>();
/*    */   
/*    */   private Conditions() {
/* 35 */     OBSERVABLE.subscribe(Priority.NORMAL, this::conditionsLoad);
/*    */   }
/*    */   
/*    */   public void conditionsLoad(Conditions condition) {
/* 39 */     Cobblemon.INSTANCE.getShowdownThread().queue(showdownService -> {
/*    */           if (showdownService instanceof GraalShowdownService) {
/*    */             GraalShowdownService service = (GraalShowdownService)showdownService;
/*    */             Value receiveConditionDataFn = service.context.getBindings("js").getMember("receiveConditionData");
/*    */             for (Map.Entry<String, String> entry : INSTANCE.getConditionScripts().entrySet()) {
/*    */               String conditionId = entry.getKey();
/*    */               String js = ((String)entry.getValue()).replace("\n", " ");
/*    */               receiveConditionDataFn.execute(new Object[] { conditionId, js });
/*    */             } 
/*    */           } 
/*    */           return Unit.INSTANCE;
/*    */         });
/*    */   }
/*    */   public Map<String, String> getConditionScripts() {
/* 53 */     return this.conditionScripts;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_2960 getId() {
/* 59 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_3264 getType() {
/* 65 */     return class_3264.field_14190;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SimpleObservable<? extends DataRegistry> getObservable() {
/* 71 */     return (SimpleObservable)OBSERVABLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reload(@NotNull class_3300 resourceManager) {
/* 76 */     this.conditionScripts.clear();
/* 77 */     resourceManager.method_14488("showdown/conditions", path -> path.method_12832().endsWith(".js")).forEach((id, resource) -> { try { BufferedReader reader = new BufferedReader(new InputStreamReader(resource.method_14482(), StandardCharsets.UTF_8)); try { String js = reader.lines().collect(Collectors.joining("\n")); String conditionId = (new File(id.method_12832())).getName().replace(".js", ""); this.conditionScripts.put(conditionId, js); reader.close(); }
/* 78 */             catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1)
/*    */               { throwable.addSuppressed(throwable1); }
/*    */                throw throwable; }
/*    */              }
/* 82 */           catch (IOException e)
/*    */           { LogManager.getLogger().error("Failed to load condition script: {} {}", id, e); }
/*    */         
/*    */         });
/*    */     
/* 87 */     OBSERVABLE.emit((Object[])new Conditions[] { this });
/*    */   }
/*    */   
/*    */   public void sync(@NotNull class_3222 serverPlayer) {}
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mega\Conditions.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */