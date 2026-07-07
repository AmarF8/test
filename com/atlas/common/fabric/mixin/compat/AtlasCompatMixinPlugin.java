/*    */ package com.atlas.common.fabric.mixin.compat;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import net.fabricmc.loader.api.FabricLoader;
/*    */ import org.objectweb.asm.tree.ClassNode;
/*    */ import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
/*    */ import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class AtlasCompatMixinPlugin
/*    */   implements IMixinConfigPlugin
/*    */ {
/*    */   public void onLoad(String mixinPackage) {}
/*    */   
/*    */   public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
/* 21 */     if (mixinClassName.contains(".jade.")) {
/* 22 */       return FabricLoader.getInstance().isModLoaded("jade");
/*    */     }
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public String getRefMapperConfig() {
/* 28 */     return null;
/*    */   } public List<String> getMixins() {
/* 30 */     return null;
/*    */   }
/*    */   
/*    */   public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}
/*    */   
/*    */   public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
/*    */   
/*    */   public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mixin\compat\AtlasCompatMixinPlugin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */