package oringo.module;

import com.google.gson.JsonObject;
import java.util.Iterator;
import map.Class305;
import map.Class357;
import map.Class362;
import map.Class447;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ScreenShotHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.mixin.PlayerControllerMPAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class FastBreakModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Less packets", true, "Lets you break more blocks per second without getting kicked");
   public final DoubleSetting field1 = new DoubleSetting("Speed", 1.0D, 1.0D, 2.0D, 0.1D);
   public final DoubleSetting field2 = new DoubleSetting("Additional blocks", 0.0D, 0.0D, 4.0D, 0.5D);
   public final Class447 field3 = new Class447();

   public FastBreakModule() {
      super("Fast Break", 0, Category.player, SubCategory.player, "Allows you to break blocks faster");
      this.method0(new Setting[]{this.field1, this.field2, this.field0});
   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
      if (this.field2.method0() == 0.0D) {
         ((PlayerControllerMPAccessor)field58.playerController).setBlockHitDelay(0);
      }

      if ((double)((PlayerControllerMPAccessor)field58.playerController).getCurBlockDamageMP() >= 2.0D - this.field1.method0()) {
         ((PlayerControllerMPAccessor)field58.playerController).setCurBlockDamageMP(1.0F);
      }

   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (this.field0.method1()) {
         if (var1.field0 instanceof C07PacketPlayerDigging) {
            BlockPos var2 = ((C07PacketPlayerDigging)var1.field0).getPosition();
            Block var3 = field58.theWorld.getBlockState(var2).getBlock();
            ItemStack var4 = field58.thePlayer.getHeldItem();
            if (var4 == null) {
               return;
            }

            float var5 = var3.getPlayerRelativeBlockHardness(field58.thePlayer, field58.theWorld, var2);
            if (var3 != Blocks.air && var5 >= 1.0F) {
               this.field3.o_();
            }

            if (GardenHelperModule.method1(var2) && ((C07PacketPlayerDigging)var1.field0).getStatus() == Action.ABORT_DESTROY_BLOCK) {
               var1.method9();
            }

            if (this.field3.a_(1000L)) {
               return;
            }

            if (var3 == Blocks.air) {
               var1.method9();
            }

            if (var5 < 1.0F) {
               var1.method9();
            }
         }

      }
   }

   public static void method5() {
      JsonObject var0 = new JsonObject();
      if (DilloFinderModule.cB_ != null && Class362.field49.field5.method1()) {
         var0.addProperty("Dillo", DilloFinderModule.cB_.toLong());
      }

      Iterator var1 = NucleusHelperModule.field0.iterator();

      while(var1.hasNext()) {
         NucleusHelperModule.Class0 var2 = (NucleusHelperModule.Class0)var1.next();
         if (!var2.field7.isEmpty()) {
            var0.addProperty(var2.field1, ((BlockPos)var2.field7.iterator().next()).toLong());
         }
      }

      if (!Class357.field4.equals(var0.toString())) {
         Class357.field4 = var0.toString();
         Oringo.field4.method0(new Class305(var0));
      }
   }

   public static void method6() {
      if (ScreenShotModule.field8) {
         ScreenShotHelper.saveScreenshot(ScreenShotModule.field1, ScreenShotModule.field4, ScreenShotModule.field2, ScreenShotModule.cX_, ScreenShotModule.field9);
         Class362.field32.method1(ScreenShotModule.field6);
         NickHiderModule.field1 = false;
         ScreenShotModule.field8 = false;
      }

   }

   public static int method0(int var0, boolean var1) {
      int var2 = (var0 >> 3 & 1) * 85;
      int var3 = (var0 >> 2 & 1) * 170 + var2;
      int var4 = (var0 >> 1 & 1) * 170 + var2;
      int var5 = (var0 & 1) * 170 + var2;
      if (var0 == 6) {
         var3 += 85;
      }

      if (var0 >= 16) {
         var3 /= 4;
         var4 /= 4;
         var5 /= 4;
      }

      return (var1 ? -16777216 : 0) | (var3 & 255) << 16 | (var4 & 255) << 8 | var5 & 255;
   }
}
