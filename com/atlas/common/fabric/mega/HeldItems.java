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
/*    */ public class HeldItems implements DataRegistry {
/* 28 */   private static final class_2960 ID = class_2960.method_60655("gg", "showdown/held_items");
/* 29 */   private static final SimpleObservable<HeldItems> OBSERVABLE = new SimpleObservable();
/* 30 */   private final Map<String, String> heldItemsScripts = new HashMap<>();
/* 31 */   public static final HeldItems INSTANCE = new HeldItems();
/*    */   
/*    */   private HeldItems() {
/* 34 */     OBSERVABLE.subscribe(Priority.NORMAL, this::heldItemsLoad);
/*    */   }
/*    */   
/*    */   public void heldItemsLoad(HeldItems heldItem) {
/* 38 */     Cobblemon.INSTANCE.getShowdownThread().queue(showdownService -> {
/*    */           if (showdownService instanceof GraalShowdownService) {
/*    */             GraalShowdownService service = (GraalShowdownService)showdownService;
/*    */             Value receiveHeldItemDataFn = service.context.getBindings("js").getMember("receiveHeldItemData");
/*    */             for (Map.Entry<String, String> entry : INSTANCE.getHeldItemsScripts().entrySet()) {
/*    */               String itemId = entry.getKey();
/*    */               String js = ((String)entry.getValue()).replace("\n", " ");
/*    */               receiveHeldItemDataFn.execute(new Object[] { itemId, js });
/*    */             } 
/*    */           } 
/*    */           return Unit.INSTANCE;
/*    */         });
/*    */   }
/*    */   public Map<String, String> getHeldItemsScripts() {
/* 52 */     return this.heldItemsScripts;
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
/* 75 */     this.heldItemsScripts.clear();
/* 76 */     resourceManager.method_14488("showdown/held_items", path -> path.method_12832().endsWith(".js")).forEach((id, resource) -> { try { BufferedReader reader = new BufferedReader(new InputStreamReader(resource.method_14482(), StandardCharsets.UTF_8)); try { String js = reader.lines().collect(Collectors.joining("\n")); String itemId = (new File(id.method_12832())).getName().replace(".js", ""); this.heldItemsScripts.put(itemId, js); reader.close(); }
/* 77 */             catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1)
/*    */               { throwable.addSuppressed(throwable1); }
/*    */                throw throwable; }
/*    */              }
/* 81 */           catch (IOException e)
/*    */           { LogManager.getLogger().error("Failed to load held item script: {} {}", id, e); }
/*    */         
/*    */         });
/*    */     
/* 86 */     OBSERVABLE.emit((Object[])new HeldItems[] { this });
/*    */   }
/*    */   
/*    */   public void sync(@NotNull class_3222 serverPlayer) {}
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mega\HeldItems.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */