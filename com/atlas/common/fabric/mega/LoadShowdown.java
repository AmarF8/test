/*    */ package com.atlas.common.fabric.mega;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.file.CopyOption;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.StandardCopyOption;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ 
/*    */ public class LoadShowdown
/*    */ {
/*    */   public void load() {
/* 15 */     Path showdown_sim = Path.of("./showdown/sim", new String[0]);
/* 16 */     Path showdown_data = Path.of("./showdown/data", new String[0]);
/* 17 */     Path showdown = Path.of("./showdown", new String[0]);
/* 18 */     Path showdown_mod_data = Path.of("./showdown/data/mods/cobblemon", new String[0]);
/*    */     
/*    */     try {
/* 21 */       Files.createDirectories(showdown_sim, (FileAttribute<?>[])new FileAttribute[0]);
/* 22 */       Files.createDirectories(showdown_data, (FileAttribute<?>[])new FileAttribute[0]);
/*    */       
/* 24 */       yoink("/assets/mega_showdown/showdown/moves.js", showdown_data.resolve("moves.js"));
/* 25 */       yoink("/assets/mega_showdown/showdown/battle-actions.js", showdown_sim.resolve("battle-actions.js"));
/* 26 */       yoink("/assets/mega_showdown/showdown/pokemon.js", showdown_sim.resolve("pokemons.js"));
/* 27 */       yoink("/assets/mega_showdown/showdown/abilities.js", showdown_data.resolve("abilities.js"));
/* 28 */       yoink("/assets/mega_showdown/showdown/side.js", showdown_sim.resolve("side.js"));
/* 29 */       yoink("/assets/mega_showdown/showdown/conditions.js", showdown_data.resolve("conditions.js"));
/* 30 */       yoink("/assets/mega_showdown/showdown/index.js", showdown.resolve("index.js"));
/*    */       
/* 32 */       if (!Files.exists(showdown_mod_data.resolve("items.js"), new java.nio.file.LinkOption[0])) {
/* 33 */         yoink("/assets/mega_showdown/showdown/mods/items.js", showdown_mod_data.resolve("items.js"));
/*    */       }
/* 35 */       if (!Files.exists(showdown_mod_data.resolve("abilities.js"), new java.nio.file.LinkOption[0])) {
/* 36 */         yoink("/assets/mega_showdown/showdown/mods/abilities.js", showdown_mod_data.resolve("abilities.js"));
/*    */       }
/* 38 */       if (!Files.exists(showdown_mod_data.resolve("moves.js"), new java.nio.file.LinkOption[0])) {
/* 39 */         yoink("/assets/mega_showdown/showdown/mods/moves.js", showdown_mod_data.resolve("moves.js"));
/*    */       }
/* 41 */       if (!Files.exists(showdown_mod_data.resolve("conditions.js"), new java.nio.file.LinkOption[0])) {
/* 42 */         yoink("/assets/mega_showdown/showdown/mods/conditions.js", showdown_mod_data.resolve("conditions.js"));
/*    */       }
/* 44 */       if (!Files.exists(showdown_mod_data.resolve("typechart.js"), new java.nio.file.LinkOption[0])) {
/* 45 */         yoink("/assets/mega_showdown/showdown/mods/typechart.js", showdown_mod_data.resolve("typechart.js"));
/*    */       }
/*    */       
/* 48 */       LogManager.getLogger().info("All files are ready!");
/* 49 */     } catch (IOException e) {
/* 50 */       LogManager.getLogger().error("Failed to prepare required files: {}", e.getMessage());
/*    */     } 
/*    */   }
/*    */   
/*    */   private void yoink(String resourcePath, Path targetPath) {
/*    */     
/* 56 */     try { InputStream inputStream = getClass().getResourceAsStream(resourcePath); 
/* 57 */       try { if (inputStream == null)
/* 58 */         { LogManager.getLogger().error("Fallback file not found: {}", resourcePath);
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 63 */           if (inputStream != null) inputStream.close();  return; }  Files.copy(inputStream, targetPath, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING }); LogManager.getLogger().info("Loaded fallback file: {}", targetPath); if (inputStream != null) inputStream.close();  } catch (Throwable throwable) { if (inputStream != null) try { inputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 64 */     { LogManager.getLogger().error("Failed to copy fallback file {}: {}", resourcePath, e.getMessage()); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\amarf\AppData\Roaming\ModrinthApp\profiles\Cobblemon.gg - Ultimate Pokemon Experience\mods\atlas-common-fabric-1.2.0.jar!\com\atlas\common\fabric\mega\LoadShowdown.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */