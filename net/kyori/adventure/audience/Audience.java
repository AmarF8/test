/*     */ package net.kyori.adventure.audience;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collector;
/*     */ import net.kyori.adventure.bossbar.BossBar;
/*     */ import net.kyori.adventure.chat.ChatType;
/*     */ import net.kyori.adventure.chat.SignedMessage;
/*     */ import net.kyori.adventure.identity.Identified;
/*     */ import net.kyori.adventure.identity.Identity;
/*     */ import net.kyori.adventure.inventory.Book;
/*     */ import net.kyori.adventure.pointer.Pointered;
/*     */ import net.kyori.adventure.resource.ResourcePackInfo;
/*     */ import net.kyori.adventure.resource.ResourcePackInfoLike;
/*     */ import net.kyori.adventure.resource.ResourcePackRequest;
/*     */ import net.kyori.adventure.resource.ResourcePackRequestLike;
/*     */ import net.kyori.adventure.sound.Sound;
/*     */ import net.kyori.adventure.sound.SoundStop;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.ComponentLike;
/*     */ import net.kyori.adventure.title.Title;
/*     */ import net.kyori.adventure.title.TitlePart;
/*     */ import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public interface Audience
/*     */   extends Pointered
/*     */ {
/*     */   @NotNull
/*     */   static Audience empty() {
/* 112 */     return EmptyAudience.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   static Audience audience(@NotNull Audience... audiences) {
/* 124 */     int length = audiences.length;
/* 125 */     if (length == 0)
/* 126 */       return empty(); 
/* 127 */     if (length == 1) {
/* 128 */       return audiences[0];
/*     */     }
/* 130 */     return audience(Arrays.asList(audiences));
/*     */   }
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
/*     */   @NotNull
/*     */   static ForwardingAudience audience(@NotNull Iterable<? extends Audience> audiences) {
/* 145 */     return () -> audiences;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   static Collector<? super Audience, ?, ForwardingAudience> toAudience() {
/* 157 */     return Audiences.COLLECTOR;
/*     */   }
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
/*     */   @NotNull
/*     */   default Audience filterAudience(@NotNull Predicate<? super Audience> filter) {
/* 174 */     return filter.test(this) ? 
/* 175 */       this : 
/* 176 */       empty();
/*     */   }
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
/*     */   default void forEachAudience(@NotNull Consumer<? super Audience> action) {
/* 193 */     action.accept(this);
/*     */   }
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
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendMessage(@NotNull ComponentLike message) {
/* 208 */     sendMessage(message.asComponent());
/*     */   }
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
/*     */   default void sendMessage(@NotNull Component message) {
/* 222 */     sendMessage(message, MessageType.SYSTEM);
/*     */   }
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
/*     */   @Deprecated
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   default void sendMessage(@NotNull ComponentLike message, @NotNull MessageType type) {
/* 240 */     sendMessage(message.asComponent(), type);
/*     */   }
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
/*     */   @Deprecated
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   default void sendMessage(@NotNull Component message, @NotNull MessageType type) {
/* 258 */     sendMessage(Identity.nil(), message, type);
/*     */   }
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
/*     */   @Deprecated
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendMessage(@NotNull Identified source, @NotNull ComponentLike message) {
/* 275 */     sendMessage(source, message.asComponent());
/*     */   }
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
/*     */   @Deprecated
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendMessage(@NotNull Identity source, @NotNull ComponentLike message) {
/* 290 */     sendMessage(source, message.asComponent());
/*     */   }
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
/*     */   @Deprecated
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendMessage(@NotNull Identified source, @NotNull Component message) {
/* 305 */     sendMessage(source, message, MessageType.CHAT);
/*     */   }
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
/*     */   @Deprecated
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendMessage(@NotNull Identity source, @NotNull Component message) {
/* 320 */     sendMessage(source, message, MessageType.CHAT);
/*     */   }
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
/*     */   @Deprecated
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   default void sendMessage(@NotNull Identified source, @NotNull ComponentLike message, @NotNull MessageType type) {
/* 337 */     sendMessage(source, message.asComponent(), type);
/*     */   }
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
/*     */   @Deprecated
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   default void sendMessage(@NotNull Identity source, @NotNull ComponentLike message, @NotNull MessageType type) {
/* 354 */     sendMessage(source, message.asComponent(), type);
/*     */   }
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
/*     */   @Deprecated
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   default void sendMessage(@NotNull Identified source, @NotNull Component message, @NotNull MessageType type) {
/* 370 */     sendMessage(source.identity(), message, type);
/*     */   }
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
/*     */   @Deprecated
/*     */   @ScheduledForRemoval(inVersion = "5.0.0")
/*     */   default void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {}
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
/*     */   default void sendMessage(@NotNull Component message, ChatType.Bound boundChatType) {
/* 401 */     sendMessage(message, MessageType.CHAT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendMessage(@NotNull ComponentLike message, ChatType.Bound boundChatType) {
/* 414 */     sendMessage(message.asComponent(), boundChatType);
/*     */   }
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
/*     */   default void sendMessage(@NotNull SignedMessage signedMessage, ChatType.Bound boundChatType) {
/* 429 */     Component content = (signedMessage.unsignedContent() != null) ? signedMessage.unsignedContent() : (Component)Component.text(signedMessage.message());
/* 430 */     if (signedMessage.isSystem()) {
/* 431 */       sendMessage(content);
/*     */     } else {
/* 433 */       sendMessage(signedMessage.identity(), content, MessageType.CHAT);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void deleteMessage(@NotNull SignedMessage signedMessage) {
/* 447 */     if (signedMessage.canDelete()) {
/* 448 */       deleteMessage(Objects.<SignedMessage.Signature>requireNonNull(signedMessage.signature()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void deleteMessage(SignedMessage.Signature signature) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendActionBar(@NotNull ComponentLike message) {
/* 472 */     sendActionBar(message.asComponent());
/*     */   }
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
/*     */   default void sendActionBar(@NotNull Component message) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendPlayerListHeader(@NotNull ComponentLike header) {
/* 496 */     sendPlayerListHeader(header.asComponent());
/*     */   }
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
/*     */   default void sendPlayerListHeader(@NotNull Component header) {
/* 509 */     sendPlayerListHeaderAndFooter(header, (Component)Component.empty());
/*     */   }
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
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendPlayerListFooter(@NotNull ComponentLike footer) {
/* 523 */     sendPlayerListFooter(footer.asComponent());
/*     */   }
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
/*     */   default void sendPlayerListFooter(@NotNull Component footer) {
/* 536 */     sendPlayerListHeaderAndFooter((Component)Component.empty(), footer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendPlayerListHeaderAndFooter(@NotNull ComponentLike header, @NotNull ComponentLike footer) {
/* 548 */     sendPlayerListHeaderAndFooter(header.asComponent(), footer.asComponent());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void showTitle(@NotNull Title title) {
/* 570 */     Title.Times times = title.times();
/* 571 */     if (times != null) sendTitlePart(TitlePart.TIMES, times);
/*     */     
/* 573 */     sendTitlePart(TitlePart.SUBTITLE, title.subtitle());
/* 574 */     sendTitlePart(TitlePart.TITLE, title.title());
/*     */   }
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
/*     */   default <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {}
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
/*     */   default void clearTitle() {}
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
/*     */   default void resetTitle() {}
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
/*     */   default void showBossBar(@NotNull BossBar bar) {}
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
/*     */   default void hideBossBar(@NotNull BossBar bar) {}
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
/*     */   default void playSound(@NotNull Sound sound) {}
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
/*     */   default void playSound(@NotNull Sound sound, double x, double y, double z) {}
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
/*     */   default void playSound(@NotNull Sound sound, Sound.Emitter emitter) {}
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
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void stopSound(@NotNull Sound sound) {
/* 679 */     stopSound(((Sound)Objects.<Sound>requireNonNull(sound, "sound")).asStop());
/*     */   }
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
/*     */   default void stopSound(@NotNull SoundStop stop) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void openBook(Book.Builder book) {
/* 703 */     openBook(book.build());
/*     */   }
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
/*     */   default void openBook(@NotNull Book book) {}
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
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   void sendResourcePacks(@NotNull ResourcePackInfoLike first, @NotNull ResourcePackInfoLike... others) {
/* 734 */     sendResourcePacks(ResourcePackRequest.addingRequest(first, others));
/*     */   }
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
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void sendResourcePacks(@NotNull ResourcePackRequestLike request) {
/* 748 */     sendResourcePacks(request.asResourcePackRequest());
/*     */   }
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
/*     */   default void sendResourcePacks(@NotNull ResourcePackRequest request) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void removeResourcePacks(@NotNull ResourcePackRequestLike request) {
/* 772 */     removeResourcePacks(request.asResourcePackRequest());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   default void removeResourcePacks(@NotNull ResourcePackRequest request) {
/* 784 */     List<ResourcePackInfo> infos = request.packs();
/* 785 */     if (infos.size() == 1) {
/* 786 */       removeResourcePacks(((ResourcePackInfo)infos.get(0)).id(), new UUID[0]);
/* 787 */     } else if (infos.isEmpty()) {
/*     */       return;
/*     */     } 
/*     */     
/* 791 */     UUID[] otherReqs = new UUID[infos.size() - 1];
/* 792 */     for (int i = 0; i < otherReqs.length; i++) {
/* 793 */       otherReqs[i] = ((ResourcePackInfo)infos.get(i + 1)).id();
/*     */     }
/* 795 */     removeResourcePacks(((ResourcePackInfo)infos.get(0)).id(), otherReqs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ForwardingAudienceOverrideNotRequired
/*     */   void removeResourcePacks(@NotNull ResourcePackInfoLike request, @NotNull ResourcePackInfoLike... others) {
/* 808 */     UUID[] otherReqs = new UUID[others.length];
/* 809 */     for (int i = 0; i < others.length; i++) {
/* 810 */       otherReqs[i] = others[i].asResourcePackInfo().id();
/*     */     }
/* 812 */     removeResourcePacks(request.asResourcePackInfo().id(), otherReqs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void removeResourcePacks(@NotNull Iterable<UUID> ids) {
/*     */     UUID[] others;
/* 824 */     Iterator<UUID> it = ids.iterator();
/* 825 */     if (!it.hasNext())
/*     */       return; 
/* 827 */     UUID id = it.next();
/*     */     
/* 829 */     if (!it.hasNext()) {
/* 830 */       others = new UUID[0];
/* 831 */     } else if (ids instanceof Collection) {
/* 832 */       others = new UUID[((Collection)ids).size() - 1];
/* 833 */       for (int i = 0; i < others.length; i++) {
/* 834 */         others[i] = it.next();
/*     */       }
/*     */     } else {
/* 837 */       List<UUID> othersList = new ArrayList<>();
/* 838 */       while (it.hasNext()) {
/* 839 */         othersList.add(it.next());
/*     */       }
/* 841 */       others = othersList.<UUID>toArray(new UUID[0]);
/*     */     } 
/*     */     
/* 844 */     removeResourcePacks(id, others);
/*     */   }
/*     */   
/*     */   void removeResourcePacks(@NotNull UUID id, @NotNull UUID... others) {}
/*     */   
/*     */   default void clearResourcePacks() {}
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\net\kyori\adventure\audience\Audience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */