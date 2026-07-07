/*    */ package com.atlas.common.fabric.mega;
/*    */ 
/*    */ import com.cobblemon.mod.common.Cobblemon;
/*    */ import com.cobblemon.mod.common.api.Priority;
/*    */ import com.cobblemon.mod.common.api.abilities.AbilityTemplate;
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
/*    */ 
/*    */ public class Abilities
/*    */   implements DataRegistry
/*    */ {
/* 32 */   private static final class_2960 ID = class_2960.method_60655("gg", "showdown/ability");
/* 33 */   private static final SimpleObservable<Abilities> OBSERVABLE = new SimpleObservable();
/* 34 */   public static final Abilities INSTANCE = new Abilities();
/* 35 */   private final Map<String, String> abilityScripts = new HashMap<>();
/*    */   
/*    */   private Abilities() {
/* 38 */     OBSERVABLE.subscribe(Priority.NORMAL, this::registerAbilities);
/*    */   }
/*    */   
/*    */   public void registerAbilities(Abilities ability) {
/* 42 */     Cobblemon.INSTANCE.getShowdownThread().queue(showdownService -> {
/*    */           if (showdownService instanceof GraalShowdownService) {
/*    */             GraalShowdownService service = (GraalShowdownService)showdownService;
/*    */             Value receiveAbilityDataFn = service.context.getBindings("js").getMember("receiveAbilityData");
/*    */             for (Map.Entry<String, String> entry : INSTANCE.getAbilityScripts().entrySet()) {
/*    */               String abilityId = entry.getKey();
/*    */               String js = ((String)entry.getValue()).replace("\n", " ");
/*    */               receiveAbilityDataFn.execute(new Object[] { abilityId, js });
/*    */               AbilityTemplate newAbility = NewAbility.getAbility(abilityId);
/*    */               com.cobblemon.mod.common.api.abilities.Abilities.register(newAbility);
/*    */             } 
/*    */           } 
/*    */           return Unit.INSTANCE;
/*    */         });
/*    */   }
/*    */   
/*    */   public Map<String, String> getAbilityScripts() {
/* 59 */     return this.abilityScripts;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_2960 getId() {
/* 65 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public class_3264 getType() {
/* 71 */     return class_3264.field_14190;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SimpleObservable<? extends DataRegistry> getObservable() {
/* 77 */     return (SimpleObservable)OBSERVABLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reload(@NotNull class_3300 resourceManager) {
/* 82 */     this.abilityScripts.clear();
/* 83 */     resourceManager.method_14488("showdown/abilities", path -> path.method_12832().endsWith(".js")).forEach((id, resource) -> { try { BufferedReader reader = new BufferedReader(new InputStreamReader(resource.method_14482(), StandardCharsets.UTF_8)); try { String js = reader.lines().collect(Collectors.joining("\n")); String abilityId = (new File(id.method_12832())).getName().replace(".js", ""); this.abilityScripts.put(abilityId, js); reader.close(); }
/* 84 */             catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1)
/*    */               { throwable.addSuppressed(throwable1); }
/*    */                throw throwable; }
/*    */              }
/* 88 */           catch (IOException e)
/*    */           { LogManager.getLogger().error("Failed to load ability script: {} {}", id, e); }
/*    */         
/*    */         });
/*    */     
/* 93 */     OBSERVABLE.emit((Object[])new Abilities[] { this });
/*    */   }
/*    */   
/*    */   public void sync(@NotNull class_3222 serverPlayer) {}
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mega\Abilities.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */