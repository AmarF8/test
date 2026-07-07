/*     */ package net.kyori.adventure.audience;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.kyori.adventure.chat.ChatType;
/*     */ import net.kyori.adventure.chat.SignedMessage;
/*     */ import net.kyori.adventure.identity.Identified;
/*     */ import net.kyori.adventure.identity.Identity;
/*     */ import net.kyori.adventure.inventory.Book;
/*     */ import net.kyori.adventure.pointer.Pointer;
/*     */ import net.kyori.adventure.resource.ResourcePackInfoLike;
/*     */ import net.kyori.adventure.resource.ResourcePackRequest;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.ComponentLike;
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
/*     */ final class EmptyAudience
/*     */   implements Audience
/*     */ {
/*  46 */   static final EmptyAudience INSTANCE = new EmptyAudience();
/*     */   
/*     */   @NotNull
/*     */   public <T> Optional<T> get(@NotNull Pointer<T> pointer) {
/*  50 */     return Optional.empty();
/*     */   }
/*     */   
/*     */   @Contract("_, null -> null; _, !null -> !null")
/*     */   @Nullable
/*     */   public <T> T getOrDefault(@NotNull Pointer<T> pointer, @Nullable T defaultValue) {
/*  56 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T getOrDefaultFrom(@NotNull Pointer<T> pointer, @NotNull Supplier<? extends T> defaultValue) {
/*  61 */     return defaultValue.get();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Audience filterAudience(@NotNull Predicate<? super Audience> filter) {
/*  66 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void forEachAudience(@NotNull Consumer<? super Audience> action) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(@NotNull ComponentLike message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(@NotNull Component message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void sendMessage(@NotNull Identified source, @NotNull Component message, @NotNull MessageType type) {}
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(@NotNull Component message, ChatType.Bound boundChatType) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(@NotNull SignedMessage signedMessage, ChatType.Bound boundChatType) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteMessage(SignedMessage.Signature signature) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendActionBar(@NotNull ComponentLike message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPlayerListHeader(@NotNull ComponentLike header) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPlayerListFooter(@NotNull ComponentLike footer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPlayerListHeaderAndFooter(@NotNull ComponentLike header, @NotNull ComponentLike footer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void openBook(Book.Builder book) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendResourcePacks(@NotNull ResourcePackInfoLike request, @NotNull ResourcePackInfoLike... others) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeResourcePacks(@NotNull ResourcePackRequest request) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeResourcePacks(@NotNull ResourcePackInfoLike request, @NotNull ResourcePackInfoLike... others) {}
/*     */ 
/*     */   
/*     */   public boolean equals(Object that) {
/* 137 */     return (this == that);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 142 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 147 */     return "EmptyAudience";
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\audience\EmptyAudience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */