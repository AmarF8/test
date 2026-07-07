/*     */ package net.kyori.adventure.text;
/*     */ 
/*     */ import java.util.stream.Stream;
/*     */ import net.kyori.examination.ExaminableProperty;
/*     */ import org.jetbrains.annotations.Contract;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface NBTComponent<C extends NBTComponent<C, B>, B extends NBTComponentBuilder<C, B>>
/*     */   extends BuildableComponent<C, B>
/*     */ {
/*     */   @NotNull
/*     */   String nbtPath();
/*     */   
/*     */   @Contract(pure = true)
/*     */   @NotNull
/*     */   C nbtPath(@NotNull String paramString);
/*     */   
/*     */   boolean interpret();
/*     */   
/*     */   @Contract(pure = true)
/*     */   @NotNull
/*     */   C interpret(boolean paramBoolean);
/*     */   
/*     */   @Nullable
/*     */   Component separator();
/*     */   
/*     */   @NotNull
/*     */   C separator(@Nullable ComponentLike paramComponentLike);
/*     */   
/*     */   @NotNull
/*     */   default Stream<? extends ExaminableProperty> examinableProperties() {
/* 110 */     return Stream.concat(
/* 111 */         Stream.of(new ExaminableProperty[] {
/* 112 */             ExaminableProperty.of("nbtPath", nbtPath()), 
/* 113 */             ExaminableProperty.of("interpret", interpret()), 
/* 114 */             ExaminableProperty.of("separator", separator())
/*     */           
/* 116 */           }), super.examinableProperties());
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\NBTComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */