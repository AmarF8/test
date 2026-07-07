/*    */ package net.kyori.adventure.text.event;
/*    */ 
/*    */ import net.kyori.adventure.nbt.api.BinaryTagHolder;
/*    */ import net.kyori.examination.Examinable;
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
/*    */ 
/*    */ public interface DataComponentValue
/*    */   extends Examinable
/*    */ {
/*    */   static Removed removed() {
/* 49 */     return RemovedDataComponentValueImpl.REMOVED;
/*    */   }
/*    */   
/*    */   public static interface Removed extends DataComponentValue {}
/*    */   
/*    */   public static interface TagSerializable extends DataComponentValue {
/*    */     @NotNull
/*    */     BinaryTagHolder asBinaryTag();
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\text\event\DataComponentValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */