/*    */ package net.kyori.adventure.text.serializer.plain;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import net.kyori.adventure.builder.AbstractBuilder;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import net.kyori.adventure.text.TextComponent;
/*    */ import net.kyori.adventure.text.flattener.ComponentFlattener;
/*    */ import net.kyori.adventure.text.serializer.ComponentSerializer;
/*    */ import net.kyori.adventure.util.Buildable;
/*    */ import net.kyori.adventure.util.PlatformAPI;
/*    */ import org.jetbrains.annotations.ApiStatus.Internal;
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
/*    */ public interface PlainTextComponentSerializer
/*    */   extends ComponentSerializer<Component, TextComponent, String>, Buildable<PlainTextComponentSerializer, PlainTextComponentSerializer.Builder>
/*    */ {
/*    */   @NotNull
/*    */   static PlainTextComponentSerializer plainText() {
/* 55 */     return PlainTextComponentSerializerImpl.Instances.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static Builder builder() {
/* 65 */     return new PlainTextComponentSerializerImpl.BuilderImpl();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   default TextComponent deserialize(@NotNull String input) {
/* 70 */     return Component.text(input);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   default String serialize(@NotNull Component component) {
/* 75 */     StringBuilder sb = new StringBuilder();
/* 76 */     serialize(sb, component);
/* 77 */     return sb.toString();
/*    */   }
/*    */   
/*    */   void serialize(@NotNull StringBuilder paramStringBuilder, @NotNull Component paramComponent);
/*    */   
/*    */   @PlatformAPI
/*    */   @Internal
/*    */   public static interface Provider {
/*    */     @PlatformAPI
/*    */     @Internal
/*    */     @NotNull
/*    */     PlainTextComponentSerializer plainTextSimple();
/*    */     
/*    */     @PlatformAPI
/*    */     @Internal
/*    */     @NotNull
/*    */     Consumer<PlainTextComponentSerializer.Builder> plainText();
/*    */   }
/*    */   
/*    */   public static interface Builder extends AbstractBuilder<PlainTextComponentSerializer>, Buildable.Builder<PlainTextComponentSerializer> {
/*    */     @NotNull
/*    */     Builder flattener(@NotNull ComponentFlattener param1ComponentFlattener);
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\serializer\plain\PlainTextComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */