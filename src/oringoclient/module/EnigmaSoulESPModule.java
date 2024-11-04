package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedHashSet;
import map.Class98;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class270;
import oringo.event.Class439;

public class EnigmaSoulESPModule extends Module {
   public static final String field0 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTMwZmU3NzFmY2MzZWNjMDUzMGVlOTU0NWFiMDc3OTc0MzdmOTVlMDlhMGVhYTliNTEyNDk3ZmU4OTJmNTJmYiJ9fX0=";
   public static final LinkedHashSet bb_ = new LinkedHashSet();

   public static void method0(float var0) {
      FrozenTreasureESPModule.method5().timerSpeed = var0;
   }

   public static boolean lambda$onEntity$0(BlockPos var0) {
      return field58.theWorld == null || !field58.theWorld.getChunkFromBlockCoords(var0).isLoaded();
   }

   public EnigmaSoulESPModule() {
      super("Enigma Soul ESP", Category.render, SubCategory.visual, "ESP for Enigma Souls in The Rift");
   }

   public void method4() {
      super.method4();
      if (field58.theWorld != null) {
         Iterator var1 = field58.theWorld.loadedEntityList.iterator();

         while(var1.hasNext()) {
            Entity var2 = (Entity)var1.next();
            if (var2 instanceof EntityArmorStand && this.method0((EntityArmorStand)var2)) {
               bb_.add(new BlockPos(var2.getPositionEyes(1.0F)));
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(Class439 var1) {
      if (var1.field0 instanceof EntityArmorStand && this.method0((EntityArmorStand)var1.field0)) {
         bb_.add(new BlockPos(var1.field0.getPositionEyes(1.0F)));
         bb_.removeIf(EnigmaSoulESPModule::lambda$onEntity$0);
      }

   }

   public boolean method0(EntityArmorStand var1) {
      if (var1.getEquipmentInSlot(4) == null) {
         return false;
      } else {
         ItemStack var2 = var1.getEquipmentInSlot(4);
         return !(var2.getItem() instanceof ItemSkull) ? false : "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTMwZmU3NzFmY2MzZWNjMDUzMGVlOTU0NWFiMDc3OTc0MzdmOTVlMDlhMGVhYTliNTEyNDk3ZmU4OTJmNTJmYiJ9fX0=".equals(Class98.method0(var2));
      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (!bb_.isEmpty()) {
         Iterator var2 = bb_.iterator();

         while(var2.hasNext()) {
            BlockPos var3 = (BlockPos)var2.next();
            AutoMaskModule.method0(var3, Color.MAGENTA.darker());
         }

      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      bb_.clear();
   }
}
