/*     */ package com.atlas.common.fabric.battletower.data;
/*     */ 
/*     */ import com.atlas.common.fabric.AtlasMod;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Stream;
/*     */ import net.fabricmc.api.EnvType;
/*     */ import net.fabricmc.api.Environment;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ 
/*     */ 
/*     */ @Environment(EnvType.CLIENT)
/*     */ public class SkinManager
/*     */ {
/*  21 */   private static final List<String> SKIN_NAMES = new ArrayList<>();
/*  22 */   private static final Random RANDOM = new Random();
/*     */   
/*     */   private static Path skinsFolder;
/*     */   
/*     */   private static boolean initialized = false;
/*  27 */   private static final String[] DEFAULT_SKINS = new String[] { "trainer_1", "trainer_2", "trainer_3", "trainer_4", "trainer_5", "trainer_6", "trainer_7", "trainer_8", "trainer_9" };
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
/*     */   public static void loadSkins() {
/*  41 */     if (initialized)
/*     */       return; 
/*  43 */     SKIN_NAMES.clear();
/*     */ 
/*     */     
/*  46 */     for (String defaultSkin : DEFAULT_SKINS) {
/*  47 */       SKIN_NAMES.add("default/" + defaultSkin);
/*     */     }
/*  49 */     AtlasMod.LOGGER.info("Loaded {} default trainer skins from mod resources", Integer.valueOf(DEFAULT_SKINS.length));
/*     */ 
/*     */     
/*     */     try {
/*  53 */       Path gameDir = (class_310.method_1551()).field_1697.toPath();
/*  54 */       skinsFolder = gameDir.resolve("config").resolve("cobblemon_battle_tower").resolve("skins");
/*     */       
/*  56 */       if (!Files.exists(skinsFolder, new java.nio.file.LinkOption[0])) {
/*  57 */         Files.createDirectories(skinsFolder, (FileAttribute<?>[])new FileAttribute[0]);
/*  58 */         AtlasMod.LOGGER.info("Created skins folder at: {}", skinsFolder);
/*  59 */         writeReadme(skinsFolder);
/*     */       } 
/*     */       
/*  62 */       int customCount = loadCustomSkins();
/*  63 */       if (customCount > 0) {
/*  64 */         AtlasMod.LOGGER.info("Loaded {} custom trainer skins from client", Integer.valueOf(customCount));
/*     */       }
/*     */       
/*  67 */       AtlasMod.LOGGER.info("Total trainer skins available: {}", Integer.valueOf(SKIN_NAMES.size()));
/*  68 */       initialized = true;
/*     */     }
/*  70 */     catch (IOException e) {
/*  71 */       AtlasMod.LOGGER.error("Failed to load custom skins: {}", e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getRandomSkin() {
/*  77 */     if (!initialized) loadSkins(); 
/*  78 */     return SKIN_NAMES.isEmpty() ? null : SKIN_NAMES.get(RANDOM.nextInt(SKIN_NAMES.size()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static class_2960 getSkinLocation(String skinName) {
/*  83 */     if (skinName == null || skinName.isEmpty()) return null; 
/*  84 */     return class_2960.method_60655("cobblemon_battle_tower", "skins/" + skinName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Path getSkinPath(String skinName) {
/*  92 */     if (!initialized) loadSkins(); 
/*  93 */     if (skinName == null) return null;
/*     */ 
/*     */     
/*  96 */     if (skinName.startsWith("default/")) return null;
/*     */     
/*  98 */     if (skinName.startsWith("custom/") && skinsFolder != null) {
/*  99 */       String fileName = skinName.substring("custom/".length());
/* 100 */       return skinsFolder.resolve(fileName + ".png");
/*     */     } 
/*     */     
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean hasCustomSkins() {
/* 107 */     if (!initialized) loadSkins(); 
/* 108 */     return !SKIN_NAMES.isEmpty();
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
/*     */   private static int loadCustomSkins() throws IOException {
/* 120 */     int beforeCount = SKIN_NAMES.size();
/*     */     
/* 122 */     Stream<Path> paths = Files.list(skinsFolder); 
/* 123 */     try { paths
/* 124 */         .filter(path -> path.toString().toLowerCase().endsWith(".png"))
/* 125 */         .forEach(path -> {
/*     */             String fileName = path.getFileName().toString();
/*     */             String skinName = fileName.substring(0, fileName.length() - ".png".length());
/*     */             SKIN_NAMES.add("custom/" + skinName);
/*     */           });
/* 130 */       if (paths != null) paths.close();  } catch (Throwable throwable) { if (paths != null)
/*     */         try { paths.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/* 132 */      return SKIN_NAMES.size() - beforeCount;
/*     */   }
/*     */   
/*     */   private static void writeReadme(Path folder) throws IOException {
/* 136 */     Path readme = folder.resolve("README.txt");
/* 137 */     Files.writeString(readme, "Place 64x64 player skin PNG files in this folder.\nSkins will be randomly selected for Battle Tower trainers.\n\nThe mod comes with %d default skins.\nAny skins you add here will be added to the pool alongside the defaults.\n\nSupported formats:\n- Standard Minecraft player skins (64x64 pixels)\n- Both slim and classic models supported\n- Any filename ending in .png will work\n\nNote: These skins are loaded from YOUR client.\nOther players will see trainers with their own custom skins.\n"
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
/* 151 */         .formatted(new Object[] { Integer.valueOf(DEFAULT_SKINS.length) }, ), new java.nio.file.OpenOption[0]);
/*     */   }
/*     */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\battletower\data\SkinManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */