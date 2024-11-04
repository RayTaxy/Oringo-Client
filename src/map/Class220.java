package map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import oringo.module.AutoFarmModule;
import oringo.module.AutoRabbitModule;

public abstract class Class220 {
   public EnumFacing field3;
   public static boolean field4;
   public final Minecraft field5 = Minecraft.getMinecraft();

   public void method16() {
   }

   public static void method0(Entity var0) {
      ChatComponentText var1 = new ChatComponentText("Name: " + var0.getDisplayName().getFormattedText());
      ChatStyle var2 = new ChatStyle();
      var2.setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, String.format("%sarmorstands %s", AutoRabbitModule.method1(), var0.getEntityId())));
      var1.setChatStyle(var2);
      Minecraft.getMinecraft().thePlayer.addChatMessage(var1);
   }

   public void method3() {
      if (this.field3 != null) {
         AutoFarmModule var1 = Class229.method1();
         int var2 = 0;

         for(int var3 = 0; var3 < 5; ++var3) {
            this.field5.entityRenderer.getMouseOver(1.0F);
            MovingObjectPosition var4 = this.field5.objectMouseOver;
            if (var4 == null || var4.typeOfHit != MovingObjectType.BLOCK) {
               break;
            }

            IBlockState var5 = this.field5.theWorld.getBlockState(var4.getBlockPos());
            if (var5.getBlock() == Blocks.air || !var1.method31() && !var1.method13().method0(var5.getBlock())) {
               break;
            }

            ++Class229.method1().dc_;
            ++Class229.method1().field48;
            if (var1.method19()) {
               this.field5.thePlayer.swingItem();
               this.field5.playerController.clickBlock(var4.getBlockPos(), var4.sideHit);
            } else if (this.field5.playerController.onPlayerDamageBlock(var4.getBlockPos(), var4.sideHit)) {
               this.field5.thePlayer.swingItem();
            }

            field4 = true;
            ++var2;
            if (var2 >= var1.method15() || !var1.method19()) {
               break;
            }
         }

      }
   }

   public abstract MovementInput method0(MovementInput var1);

   public EnumFacing method30() {
      return this.field3;
   }

   public void o_() {
      this.field3 = null;
   }
}
