package oringo.module;

import net.minecraft.util.ResourceLocation;

public enum Category {
   other("Other", "icons/other.png"),
   keybinds("Keybinds", "icons/keybinds.png");

   public final String name;
   player("Player", "icons/player.png");

   private ResourceLocation location;
   movement("Movement", "icons/movement.png"),
   combat("Combat", "icons/combat.png"),
   skyblock("Skyblock", "icons/skyblock2.png"),
   dungeon("Dungeon", "icons/dungeons.png"),
   render("Render", "icons/render.png");

   private static final Category[] field10 = new Category[]{combat, skyblock, dungeon, render, movement, player, other, keybinds};

   public ResourceLocation method0() {
      return this.location;
   }

   private Category(String var3, String var4) {
      this.name = var3;
      this.location = new ResourceLocation("oringoclient", var4);
   }

   public void method0(ResourceLocation var1) {
      this.location = var1;
   }
}
