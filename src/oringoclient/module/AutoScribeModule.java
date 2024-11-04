package oringo.module;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.LinkedHashSet;
import map.Class376;
import map.Class496;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.ReplyCommand;
import oringo.event.Class16;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.mixin.PlayerControllerMPAccessor;

public class AutoScribeModule extends Module {
   public final LinkedHashSet field0 = Sets.newLinkedHashSet();

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (Class496.field21 && !this.field0.isEmpty()) {
         Iterator var2 = this.field0.iterator();

         while(var2.hasNext()) {
            BlockPos var3 = (BlockPos)var2.next();
            Vec3 var4 = (new Vec3(var3)).addVector(0.5D, 1.0D, 0.5D);
            if (field58.theWorld.rayTraceBlocks(new Vec3(field58.thePlayer.posX, field58.thePlayer.posY + (double)field58.thePlayer.getEyeHeight(), field58.thePlayer.posZ), var4) == null && field58.theWorld.getBlockState(var3).getBlock() == Blocks.coal_block) {
               var2.remove();
               var1.method0(ReplyCommand.method0(var4)).method0(false);
               break;
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (Class496.field21) {
         if (Class376.method0("Village Plaza")) {
            if (field58.thePlayer.getDistanceSq(var1.cB_) < 225.0D && var1.field0.getBlock() != Blocks.air && var1.field2.getBlock() == Blocks.coal_block) {
               this.field0.add(var1.cB_);
            }

         }
      }
   }

   public AutoScribeModule() {
      super("Auto Scribe", Category.skyblock, SubCategory.rift, "Looks at coal");
   }

   public static void method5() {
      int var0 = Oringo.field9.thePlayer.inventory.currentItem;
      int var1 = ((PlayerControllerMPAccessor)Oringo.field9.playerController).getCurrentPlayerItem();
      ItemStack var2 = Oringo.field9.thePlayer.inventory.getStackInSlot(var1);
      if (var2 != null) {
         Oringo.field9.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(var2));
         Oringo.field9.thePlayer.inventory.currentItem = var1;
         int var3 = var2.stackSize;
         ItemStack var4 = var2.useItemRightClick(Oringo.field9.theWorld, Oringo.field9.thePlayer);
         if (var4 != var2 || var4.stackSize != var3) {
            Oringo.field9.thePlayer.inventory.mainInventory[Oringo.field9.thePlayer.inventory.currentItem] = var4;
            if (var4.stackSize <= 0) {
               Oringo.field9.thePlayer.inventory.mainInventory[Oringo.field9.thePlayer.inventory.currentItem] = null;
               ForgeEventFactory.onPlayerDestroyItem(Oringo.field9.thePlayer, var4);
            }
         }

         Oringo.field9.thePlayer.inventory.currentItem = var0;
      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field0.clear();
   }
}
