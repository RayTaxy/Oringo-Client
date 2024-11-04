package map;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.Oringo;
import oringo.command.BrushCommand;
import oringo.event.Class270;
import oringo.module.BrushModule;

public class Class198 {
   public static final Class462 field0 = new Class462();
   public static Class208[][] field1 = new Class208[6][6];
   public static final Minecraft field2 = Minecraft.getMinecraft();
   public static final int field3 = -185;
   public static boolean field4;
   public static final int field5 = 32;
   public static final int field6 = -185;

   public static void field0(Class208 var0, int var1, int var2, BlockPos var3, Class1 var4) {
      BrushModule.method7().field0(var0.method0(var3, false).add(var1, 0, var2), var4);
   }

   public static double method0(Vec3 var0) {
      return var0.distanceTo(new Vec3(Oringo.field9.getRenderManager().viewerPosX, Oringo.field9.getRenderManager().viewerPosY, Oringo.field9.getRenderManager().viewerPosZ));
   }

   public static MapData method0() {
      if (Class469.field0.thePlayer == null) {
         return null;
      } else {
         ItemStack var0 = Class469.field0.thePlayer.inventory.getStackInSlot(8);
         return var0 != null && var0.getItem() instanceof ItemMap && var0.getDisplayName().contains("Magical Map") ? ((ItemMap)var0.getItem()).getMapData(var0, Class469.field0.theWorld) : null;
      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase != Phase.END) {
         BrushCommand.method2();
      }
   }

   @SubscribeEvent
   public void a_(Class270 var1) {
      Class1.o_();
   }

   public static float method0(ItemStack var0) {
      float var1 = 0.0F;
      if (var0.getItem() instanceof ItemArmor) {
         ItemArmor var2 = (ItemArmor)var0.getItem();
         var1 = (float)((double)var1 + (double)var2.damageReduceAmount + (double)((100 - var2.damageReduceAmount) * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, var0)) * 0.0075D);
         var1 = (float)((double)var1 + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.effectId, var0) / 100.0D);
         var1 = (float)((double)var1 + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.effectId, var0) / 100.0D);
         var1 = (float)((double)var1 + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, var0) / 100.0D);
         var1 = (float)((double)var1 + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, var0) / 50.0D);
         var1 = (float)((double)var1 + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.projectileProtection.effectId, var0) / 100.0D);
         var1 = (float)((double)var1 + (double)var0.getMaxDamage() / 1000.0D);
      }

      return var1;
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      Class1.o_();
   }
}
