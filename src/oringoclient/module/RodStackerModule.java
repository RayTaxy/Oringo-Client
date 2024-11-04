package oringo.module;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class RodStackerModule extends Module {
   public boolean field0 = false;
   public final BooleanSetting aq_ = new BooleanSetting("After hit", true);

   public static boolean lambda$onPacket$0(C08PacketPlayerBlockPlacement var0, ItemStack var1) {
      return !var1.getDisplayName().equals(var0.getStack().getDisplayName()) && var1.getItem() == Items.fishing_rod;
   }

   public static boolean method0(float var0, double var1, double var3) {
      BlockPos var5 = new BlockPos(Oringo.field9.thePlayer.posX, Oringo.field9.thePlayer.posY, Oringo.field9.thePlayer.posZ);
      if (!Oringo.field9.theWorld.isBlockLoaded(var5)) {
         return false;
      } else {
         AxisAlignedBB var6 = Oringo.field9.thePlayer.getEntityBoundingBox().offset(var1, 0.0D, var3);
         return Oringo.field9.theWorld.isAnyLiquid(new AxisAlignedBB(var6.minX, var6.minY - (double)var0, var6.minZ, var6.maxX, var6.maxY, var6.maxZ));
      }
   }

   public void method0(int var1) {
      if (var1 != -1) {
         field58.playerController.windowClick(field58.thePlayer.inventoryContainer.windowId, 1, var1, 2, field58.thePlayer);
         field58.playerController.windowClick(field58.thePlayer.inventoryContainer.windowId, 1, field58.thePlayer.inventory.currentItem, 2, field58.thePlayer);
         field58.playerController.windowClick(field58.thePlayer.inventoryContainer.windowId, 1, var1, 0, field58.thePlayer);
      }

   }

   @SubscribeEvent
   public void field0(Class332.Class0 var1) {
      if (!this.aq_.method1()) {
         if (var1.field0 instanceof C08PacketPlayerBlockPlacement) {
            C08PacketPlayerBlockPlacement var2 = (C08PacketPlayerBlockPlacement)var1.field0;
            if (var2.getStack() != null && var2.getStack().getItem() == Items.fishing_rod && field58.thePlayer.openContainer.windowId == field58.thePlayer.inventoryContainer.windowId) {
               int var3 = TrailModule.method0(RodStackerModule::lambda$onPacket$0);
               this.method0(var3);
            }
         }

      }
   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
      if (this.aq_.method1()) {
         boolean var2 = field58.thePlayer.fishEntity != null && (field58.thePlayer.fishEntity.isInLava() || field58.thePlayer.fishEntity.isInWater());
         if (var2 != this.field0 && field58.thePlayer.openContainer.windowId == field58.thePlayer.inventoryContainer.windowId) {
            int var3 = TrailModule.method0(RodStackerModule::lambda$onPacket$1);
            this.method0(var3);
         }

         this.field0 = var2;
      }
   }

   public RodStackerModule() {
      super("Rod Stacker", Category.skyblock, SubCategory.skills, "Stacks lava rod ability");
      this.method0(new Setting[]{this.aq_});
   }

   public static boolean lambda$onPacket$1(ItemStack var0) {
      return var0 != field58.thePlayer.getHeldItem() && var0.getItem() == Items.fishing_rod;
   }
}
