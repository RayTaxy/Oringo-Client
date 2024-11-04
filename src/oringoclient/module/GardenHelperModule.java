package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class361;
import map.Class362;
import map.Class374;
import map.Class376;
import map.Class395;
import map.Class408;
import map.Class426;
import map.Class447;
import map.Class528;
import map.Class94;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockStone.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class332;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class GardenHelperModule extends Module {
   public boolean field0 = false;
   public final BooleanSetting cg_ = new BooleanSetting("Full hitboxes", false);
   public final BooleanSetting field2 = (BooleanSetting)(new BooleanSetting("Pingless Cactus", true)).method2("Makes you break cactus with the Cactus Knife instantly");
   public final BooleanSetting field3 = new BooleanSetting("Auto cleanup", true);
   public final BooleanSetting field4 = new BooleanSetting("Break stone", false, this::lambda$new$1);
   public boolean field5 = true;
   public BlockPos field6;
   public final DoubleSetting field7 = new DoubleSetting("Blocks per tick", 1.0D, 1.0D, 3.0D, 1.0D, this::lambda$new$2);
   public final BooleanSetting field8 = (BooleanSetting)(new BooleanSetting("Auto Composter", true)).method2("Refills the composter automatically");
   public final BooleanSetting field9 = (BooleanSetting)(new BooleanSetting("Swap item", true, this::lambda$new$0)).method2("Swaps the held item to the best one");
   public BlockPos field10 = null;
   public final Class447 field11 = new Class447();

   public void method5() {
      String var1 = Class374.method0();
      if ("Composter".equals(var1)) {
         Slot var2 = Class528.method1().getSlot(46);
         if (!var2.getHasStack()) {
            return;
         }

         Slot var3 = Class528.method1().getSlot(52);
         if (!var3.getHasStack()) {
            return;
         }

         Pattern var4 = Pattern.compile(" ([\\d.]+)/([\\d.]+)");
         String var5 = ChatFormatting.stripFormatting((String)Class408.method1(var2.getStack()).get(0)).replaceAll(",", "").replaceAll("k", "000");
         Matcher var6 = var4.matcher(var5);
         double var7;
         double var9;
         if (var6.find()) {
            var7 = Double.parseDouble(var6.group(1));
            var9 = Double.parseDouble(var6.group(2));
            if (var7 <= var9 / 2.0D) {
               this.field0 = false;
               field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, 48, this.field5 ? 0 : 1, 0, field58.thePlayer);
               return;
            }
         }

         var5 = ChatFormatting.stripFormatting((String)Class408.method1(var3.getStack()).get(0)).replaceAll(",", "").replaceAll("k", "000");
         var6 = var4.matcher(var5);
         if (var6.find()) {
            var7 = Double.parseDouble(var6.group(1));
            var9 = Double.parseDouble(var6.group(2));
            if (var7 <= var9 / 2.0D) {
               this.field0 = true;
               field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, 50, 0, 0, field58.thePlayer);
               return;
            }
         }
      } else {
         this.field5 = true;
      }

   }

   public BlockPos method6() {
      BlockPos var1 = new BlockPos(field58.thePlayer);
      Iterator var2 = BlockPos.getAllInBox(new BlockPos(-7, -7, -7), new BlockPos(7, 7, 7)).iterator();

      BlockPos var4;
      IBlockState var5;
      Block var6;
      do {
         do {
            do {
               if (!var2.hasNext()) {
                  return null;
               }

               BlockPos var3 = (BlockPos)var2.next();
               var4 = var3.add(var1);
            } while(!this.method0(var4));
         } while(var4.equals(var1.down()));

         var5 = field58.theWorld.getBlockState(var4);
         var6 = var5.getBlock();
      } while(!(var6 instanceof BlockFlower) && !(var6 instanceof BlockTallGrass) && !(var6 instanceof BlockDoublePlant) && !(var6 instanceof BlockLeaves) && !(var6 instanceof BlockLog) && (var6 != Blocks.stone || var5.getProperties().get(BlockStone.VARIANT) != EnumType.STONE || !this.field4.method1()));

      return var4;
   }

   public GardenHelperModule() {
      super("Garden Helper", Category.skyblock, SubCategory.skills, "Useful QOL for garden");
      this.method0((Setting[])(new Setting[]{this.field3, this.field7, this.field4, this.field9, this.field8, this.field2, this.cg_}));
   }

   public Boolean lambda$new$0() {
      return !this.field3.method1();
   }

   public boolean method0(BlockPos var1) {
      return field58.thePlayer.getDistanceSq((double)var1.getX() + 0.5D, (double)var1.getY() + 0.5D - 1.5D, (double)var1.getZ() + 0.5D) < 30.25D;
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.field11.method0(1000L, true) && this.field8.method1()) {
         this.method5();
      }

      if (this.field3.method1() && Class376.method0("Cleanup")) {
         Class361.method0((Class94)(new Class395(this::lambda$onMotionUpdate$4, false, GardenHelperModule::lambda$onMotionUpdate$5)));
      }
   }

   public static boolean lambda$null$3(Block var0, ItemStack var1) {
      String var2 = BlockHitModule.method0(var1);
      if (var0 instanceof BlockLog) {
         return "TREECAPITATOR_AXE".equals(var2);
      } else if (var0 instanceof BlockStone) {
         return false;
      } else {
         return "GARDEN_SCYTHE".equals(var2) || "SAM_SCYTHE".equals(var2);
      }
   }

   public Boolean lambda$new$2() {
      return !this.field3.method1();
   }

   public boolean method9() {
      String var1 = BlockHitModule.method0(field58.thePlayer.getHeldItem());
      return "CACTUS_KNIFE".equals(var1);
   }

   public Boolean lambda$new$1() {
      return !this.field3.method1();
   }

   public static boolean method1(BlockPos var0) {
      GardenHelperModule var1 = Class362.field50;
      boolean var2 = var1.method44() && var1.field2.method1() && var1.method9();
      if (var0 == null) {
         return var2;
      } else {
         return var2 && (field58.theWorld.getBlockState(var0).getBlock() == Blocks.cactus || var0.equals(var1.field10));
      }
   }

   public static boolean method0(float var0, double var1, double var3) {
      BlockPos var5 = new BlockPos(Oringo.field9.thePlayer.posX, Oringo.field9.thePlayer.posY, Oringo.field9.thePlayer.posZ);
      if (!Oringo.field9.theWorld.isBlockLoaded(var5)) {
         return false;
      } else {
         AxisAlignedBB var6 = Oringo.field9.thePlayer.getEntityBoundingBox().offset(var1, 0.0D, var3);
         return Oringo.field9.theWorld.getCollidingBoundingBoxes(Oringo.field9.thePlayer, new AxisAlignedBB(var6.minX, var6.minY - (double)var0, var6.minZ, var6.maxX, var6.maxY, var6.maxZ)).isEmpty();
      }
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (var1.message.getUnformattedText().equals("No items in your inventory to insert!")) {
         if (this.field0) {
            method2("Out of items for Auto Composter");
            field58.thePlayer.closeScreen();
            return;
         }

         this.field5 = false;
      }

      if (var1.message.getUnformattedText().equals("No items in your sacks to insert!")) {
         method2("Out of items for Auto Composter");
         field58.thePlayer.closeScreen();
      }

   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (this.field2.method1() && field58.theWorld != null && field58.thePlayer != null) {
         if (this.method9()) {
            if (var1.field0 instanceof C07PacketPlayerDigging) {
               if (((C07PacketPlayerDigging)var1.field0).getStatus() != Action.START_DESTROY_BLOCK) {
                  return;
               }

               BlockPos var2 = ((C07PacketPlayerDigging)var1.field0).getPosition();
               Block var3 = field58.theWorld.getBlockState(var2).getBlock();
               if (var3 != Blocks.cactus) {
                  return;
               }

               field58.theWorld.setBlockToAir(var2);
               this.field10 = var2;
            }

         }
      }
   }

   public void lambda$onMotionUpdate$4() {
      int var1 = 0;

      while(true) {
         if (var1 < this.field7.method3()) {
            if (this.field6 == null || field58.theWorld.getBlockState(this.field6).getBlock() == Blocks.air || !this.method0(this.field6)) {
               this.field6 = this.method6();
            }

            if (this.field6 == null) {
               return;
            }

            int var2 = field58.thePlayer.inventory.currentItem;
            if (this.field9.method1()) {
               Block var3 = field58.theWorld.getBlockState(this.field6).getBlock();
               var2 = TrailModule.method0(GardenHelperModule::lambda$null$3);
               if (var2 == -1) {
                  var2 = AutoJaxRangeModule.method0(this.field6);
               }
            }

            float var4 = field58.theWorld.getBlockState(this.field6).getBlock().getPlayerRelativeBlockHardness(field58.thePlayer, field58.thePlayer.worldObj, this.field6);
            field58.thePlayer.inventory.currentItem = var2;
            Class426.method10();
            if (!field58.playerController.getIsHittingBlock()) {
               field58.thePlayer.swingItem();
               field58.playerController.clickBlock(this.field6, EnumFacing.DOWN);
            }

            if (field58.playerController.getIsHittingBlock() && field58.playerController.onPlayerDamageBlock(this.field6, EnumFacing.DOWN)) {
               field58.thePlayer.swingItem();
            }

            if (var4 >= 1.0F) {
               ++var1;
               continue;
            }
         }

         return;
      }
   }

   public static boolean lambda$onMotionUpdate$5() {
      return !field58.thePlayer.isUsingItem();
   }
}
