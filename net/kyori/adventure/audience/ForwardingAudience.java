/*     */ package net.kyori.adventure.audience;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.kyori.adventure.bossbar.BossBar;
/*     */ import net.kyori.adventure.chat.ChatType;
/*     */ import net.kyori.adventure.chat.SignedMessage;
/*     */ import net.kyori.adventure.identity.Identified;
/*     */ import net.kyori.adventure.identity.Identity;
/*     */ import net.kyori.adventure.inventory.Book;
/*     */ import net.kyori.adventure.pointer.Pointer;
/*     */ import net.kyori.adventure.pointer.Pointers;
/*     */ import net.kyori.adventure.resource.ResourcePackRequest;
/*     */ import net.kyori.adventure.sound.Sound;
/*     */ import net.kyori.adventure.sound.SoundStop;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.title.TitlePart;
/*     */ import org.jetbrains.annotations.ApiStatus.OverrideOnly;
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
/*     */ @FunctionalInterface
/*     */ public interface ForwardingAudience
/*     */   extends Audience
/*     */ {
/*     */   @OverrideOnly
/*     */   @NotNull
/*     */   Iterable<? extends Audience> audiences();
/*     */   
/*     */   @NotNull
/*     */   default Pointers pointers() {
/*  75 */     return Pointers.empty();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   default Audience filterAudience(@NotNull Predicate<? super Audience> filter) {
/*  80 */     List<Audience> audiences = null;
/*  81 */     for (Audience audience : audiences()) {
/*  82 */       if (filter.test(audience)) {
/*  83 */         Audience filtered = audience.filterAudience(filter);
/*  84 */         if (filtered != Audience.empty()) {
/*  85 */           if (audiences == null) {
/*  86 */             audiences = new ArrayList<>();
/*     */           }
/*  88 */           audiences.add(filtered);
/*     */         } 
/*     */       } 
/*     */     } 
/*  92 */     return (audiences != null) ? 
/*  93 */       Audience.audience(audiences) : 
/*  94 */       Audience.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   default void forEachAudience(@NotNull Consumer<? super Audience> action) {
/*  99 */     for (Audience audience : audiences()) audience.forEachAudience(action);
/*     */   
/*     */   }
/*     */   
/*     */   default void sendMessage(@NotNull Component message) {
/* 104 */     for (Audience audience : audiences()) audience.sendMessage(message);
/*     */   
/*     */   }
/*     */   
/*     */   default void sendMessage(@NotNull Component message, ChatType.Bound boundChatType) {
/* 109 */     for (Audience audience : audiences()) audience.sendMessage(message, boundChatType);
/*     */   
/*     */   }
/*     */   
/*     */   default void sendMessage(@NotNull SignedMessage signedMessage, ChatType.Bound boundChatType) {
/* 114 */     for (Audience audience : audiences()) audience.sendMessage(signedMessage, boundChatType);
/*     */   
/*     */   }
/*     */   
/*     */   default void deleteMessage(SignedMessage.Signature signature) {
/* 119 */     for (Audience audience : audiences()) audience.deleteMessage(signature);
/*     */   
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   default void sendMessage(@NotNull Identified source, @NotNull Component message, @NotNull MessageType type) {
/* 125 */     for (Audience audience : audiences()) audience.sendMessage(source, message, type);
/*     */   
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   default void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
/* 131 */     for (Audience audience : audiences()) audience.sendMessage(source, message, type);
/*     */   
/*     */   }
/*     */   
/*     */   default void sendActionBar(@NotNull Component message) {
/* 136 */     for (Audience audience : audiences()) audience.sendActionBar(message);
/*     */   
/*     */   }
/*     */   
/*     */   default void sendPlayerListHeader(@NotNull Component header) {
/* 141 */     for (Audience audience : audiences()) audience.sendPlayerListHeader(header);
/*     */   
/*     */   }
/*     */   
/*     */   default void sendPlayerListFooter(@NotNull Component footer) {
/* 146 */     for (Audience audience : audiences()) audience.sendPlayerListFooter(footer);
/*     */   
/*     */   }
/*     */   
/*     */   default void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer) {
/* 151 */     for (Audience audience : audiences()) audience.sendPlayerListHeaderAndFooter(header, footer);
/*     */   
/*     */   }
/*     */   
/*     */   default <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
/* 156 */     for (Audience audience : audiences()) audience.sendTitlePart(part, value);
/*     */   
/*     */   }
/*     */   
/*     */   default void clearTitle() {
/* 161 */     for (Audience audience : audiences()) audience.clearTitle();
/*     */   
/*     */   }
/*     */   
/*     */   default void resetTitle() {
/* 166 */     for (Audience audience : audiences()) audience.resetTitle();
/*     */   
/*     */   }
/*     */   
/*     */   default void showBossBar(@NotNull BossBar bar) {
/* 171 */     for (Audience audience : audiences()) audience.showBossBar(bar);
/*     */   
/*     */   }
/*     */   
/*     */   default void hideBossBar(@NotNull BossBar bar) {
/* 176 */     for (Audience audience : audiences()) audience.hideBossBar(bar);
/*     */   
/*     */   }
/*     */   
/*     */   default void playSound(@NotNull Sound sound) {
/* 181 */     for (Audience audience : audiences()) audience.playSound(sound);
/*     */   
/*     */   }
/*     */   
/*     */   default void playSound(@NotNull Sound sound, double x, double y, double z) {
/* 186 */     for (Audience audience : audiences()) audience.playSound(sound, x, y, z);
/*     */   
/*     */   }
/*     */   
/*     */   default void playSound(@NotNull Sound sound, Sound.Emitter emitter) {
/* 191 */     for (Audience audience : audiences()) audience.playSound(sound, emitter);
/*     */   
/*     */   }
/*     */   
/*     */   default void stopSound(@NotNull SoundStop stop) {
/* 196 */     for (Audience audience : audiences()) audience.stopSound(stop);
/*     */   
/*     */   }
/*     */   
/*     */   default void openBook(@NotNull Book book) {
/* 201 */     for (Audience audience : audiences()) audience.openBook(book);
/*     */   
/*     */   }
/*     */   
/*     */   default void sendResourcePacks(@NotNull ResourcePackRequest request) {
/* 206 */     for (Audience audience : audiences()) audience.sendResourcePacks(request);
/*     */   
/*     */   }
/*     */   
/*     */   default void removeResourcePacks(@NotNull Iterable<UUID> ids) {
/* 211 */     for (Audience audience : audiences()) audience.removeResourcePacks(ids);
/*     */   
/*     */   }
/*     */   
/*     */   void removeResourcePacks(@NotNull UUID id, @NotNull UUID... others) {
/* 216 */     for (Audience audience : audiences()) audience.removeResourcePacks(id, others);
/*     */   
/*     */   }
/*     */   
/*     */   default void clearResourcePacks() {
/* 221 */     for (Audience audience : audiences()) audience.clearResourcePacks();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Single
/*     */     extends ForwardingAudience
/*     */   {
/*     */     @OverrideOnly
/*     */     @NotNull
/*     */     Audience audience();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     @NotNull
/*     */     default Iterable<? extends Audience> audiences() {
/* 248 */       return Collections.singleton(audience());
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     default <T> Optional<T> get(@NotNull Pointer<T> pointer) {
/* 253 */       return audience().get(pointer);
/*     */     }
/*     */     
/*     */     @Contract("_, null -> null; _, !null -> !null")
/*     */     @Nullable
/*     */     default <T> T getOrDefault(@NotNull Pointer<T> pointer, @Nullable T defaultValue) {
/* 259 */       return (T)audience().getOrDefault(pointer, defaultValue);
/*     */     }
/*     */ 
/*     */     
/*     */     default <T> T getOrDefaultFrom(@NotNull Pointer<T> pointer, @NotNull Supplier<? extends T> defaultValue) {
/* 264 */       return (T)audience().getOrDefaultFrom(pointer, defaultValue);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     default Audience filterAudience(@NotNull Predicate<? super Audience> filter) {
/* 269 */       Audience audience = audience();
/* 270 */       return filter.test(audience) ? 
/* 271 */         this : 
/* 272 */         Audience.empty();
/*     */     }
/*     */ 
/*     */     
/*     */     default void forEachAudience(@NotNull Consumer<? super Audience> action) {
/* 277 */       audience().forEachAudience(action);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     default Pointers pointers() {
/* 282 */       return audience().pointers();
/*     */     }
/*     */ 
/*     */     
/*     */     default void sendMessage(@NotNull Component message) {
/* 287 */       audience().sendMessage(message);
/*     */     }
/*     */ 
/*     */     
/*     */     default void sendMessage(@NotNull Component message, ChatType.Bound boundChatType) {
/* 292 */       audience().sendMessage(message, boundChatType);
/*     */     }
/*     */ 
/*     */     
/*     */     default void sendMessage(@NotNull SignedMessage signedMessage, ChatType.Bound boundChatType) {
/* 297 */       audience().sendMessage(signedMessage, boundChatType);
/*     */     }
/*     */ 
/*     */     
/*     */     default void deleteMessage(SignedMessage.Signature signature) {
/* 302 */       audience().deleteMessage(signature);
/*     */     }
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     default void sendMessage(@NotNull Identified source, @NotNull Component message, @NotNull MessageType type) {
/* 308 */       audience().sendMessage(source, message, type);
/*     */     }
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     default void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
/* 314 */       audience().sendMessage(source, message, type);
/*     */     }
/*     */ 
/*     */     
/*     */     default void sendActionBar(@NotNull Component message) {
/* 319 */       audience().sendActionBar(message);
/*     */     }
/*     */ 
/*     */     
/*     */     default void sendPlayerListHeader(@NotNull Component header) {
/* 324 */       audience().sendPlayerListHeader(header);
/*     */     }
/*     */ 
/*     */     
/*     */     default void sendPlayerListFooter(@NotNull Component footer) {
/* 329 */       audience().sendPlayerListFooter(footer);
/*     */     }
/*     */ 
/*     */     
/*     */     default void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer) {
/* 334 */       audience().sendPlayerListHeaderAndFooter(header, footer);
/*     */     }
/*     */ 
/*     */     
/*     */     default <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
/* 339 */       audience().sendTitlePart(part, value);
/*     */     }
/*     */ 
/*     */     
/*     */     default void clearTitle() {
/* 344 */       audience().clearTitle();
/*     */     }
/*     */ 
/*     */     
/*     */     default void resetTitle() {
/* 349 */       audience().resetTitle();
/*     */     }
/*     */ 
/*     */     
/*     */     default void showBossBar(@NotNull BossBar bar) {
/* 354 */       audience().showBossBar(bar);
/*     */     }
/*     */ 
/*     */     
/*     */     default void hideBossBar(@NotNull BossBar bar) {
/* 359 */       audience().hideBossBar(bar);
/*     */     }
/*     */ 
/*     */     
/*     */     default void playSound(@NotNull Sound sound) {
/* 364 */       audience().playSound(sound);
/*     */     }
/*     */ 
/*     */     
/*     */     default void playSound(@NotNull Sound sound, double x, double y, double z) {
/* 369 */       audience().playSound(sound, x, y, z);
/*     */     }
/*     */ 
/*     */     
/*     */     default void playSound(@NotNull Sound sound, Sound.Emitter emitter) {
/* 374 */       audience().playSound(sound, emitter);
/*     */     }
/*     */ 
/*     */     
/*     */     default void stopSound(@NotNull SoundStop stop) {
/* 379 */       audience().stopSound(stop);
/*     */     }
/*     */ 
/*     */     
/*     */     default void openBook(@NotNull Book book) {
/* 384 */       audience().openBook(book);
/*     */     }
/*     */ 
/*     */     
/*     */     default void sendResourcePacks(@NotNull ResourcePackRequest request) {
/* 389 */       audience().sendResourcePacks(request.callback(Audiences.unwrapCallback(this, audience(), request.callback())));
/*     */     }
/*     */ 
/*     */     
/*     */     default void removeResourcePacks(@NotNull Iterable<UUID> ids) {
/* 394 */       audience().removeResourcePacks(ids);
/*     */     }
/*     */ 
/*     */     
/*     */     void removeResourcePacks(@NotNull UUID id, @NotNull UUID... others) {
/* 399 */       audience().removeResourcePacks(id, others);
/*     */     }
/*     */ 
/*     */     
/*     */     default void clearResourcePacks() {
/* 404 */       audience().clearResourcePacks();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\audience\ForwardingAudience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */