/*    */ package net.kyori.adventure.translation;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import net.kyori.adventure.text.renderer.TranslatableComponentRenderer;
/*    */ import net.kyori.examination.Examinable;
/*    */ import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface GlobalTranslator
/*    */   extends Translator, Examinable
/*    */ {
/*    */   @NotNull
/*    */   static GlobalTranslator translator() {
/* 52 */     return GlobalTranslatorImpl.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   @ScheduledForRemoval(inVersion = "5.0.0")
/*    */   @NotNull
/*    */   static GlobalTranslator get() {
/* 65 */     return GlobalTranslatorImpl.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   static TranslatableComponentRenderer<Locale> renderer() {
/* 75 */     return GlobalTranslatorImpl.INSTANCE.renderer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   static Component render(@NotNull Component component, @NotNull Locale locale) {
/* 87 */     return renderer().render(component, locale);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   Iterable<? extends Translator> sources();
/*    */   
/*    */   boolean addSource(@NotNull Translator paramTranslator);
/*    */   
/*    */   boolean removeSource(@NotNull Translator paramTranslator);
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\translation\GlobalTranslator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */