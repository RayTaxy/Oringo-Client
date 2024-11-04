package oringo.module;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import map.Class119;
import map.Class163;
import map.Class175;
import map.Class196;
import map.Class198;
import map.Class208;
import map.Class29;
import map.Class34;
import map.Class362;
import map.Class432;
import map.Class46;
import map.Class83;
import map.Class96;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class210;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class ScaffoldModule extends Module {
   public static final BooleanSetting bl_;
   public static final DoubleSetting bm_;
   public static final DoubleSetting field2;
   public int field3;
   public static final DoubleSetting field4;
   public static final EnumSetting field5;
   public static final BooleanSetting field6;
   public int field7;
   public boolean field0;
   public static final List field9;
   public static final BooleanSetting field10;
   public static final DoubleSetting field11;
   public int field12;
   public static final EnumSetting field13;
   public static final DoubleSetting field14;
   public ScaffoldModule.Class0 field15;
   public static final DoubleSetting field16;
   public static final EnumSetting field17;

   public boolean method3() {
      return !DragonHitboxesModule.method6() && Class362.field20.method44() && !field58.gameSettings.keyBindJump.isKeyDown() && this.field12 != -1;
   }

   static {
      field9 = Arrays.asList(Blocks.enchanting_table, Blocks.chest, Blocks.ender_chest, Blocks.trapped_chest, Blocks.anvil, Blocks.sand, Blocks.web, Blocks.torch, Blocks.crafting_table, Blocks.furnace, Blocks.waterlily, Blocks.dispenser, Blocks.stone_pressure_plate, Blocks.wooden_pressure_plate, Blocks.noteblock, Blocks.dropper, Blocks.tnt, Blocks.standing_banner, Blocks.wall_banner, Blocks.redstone_torch, Blocks.gravel, Blocks.cactus, Blocks.bed, Blocks.lever, Blocks.standing_sign, Blocks.wall_sign, Blocks.jukebox, Blocks.oak_fence, Blocks.spruce_fence, Blocks.birch_fence, Blocks.jungle_fence, Blocks.dark_oak_fence, Blocks.oak_fence_gate, Blocks.spruce_fence_gate, Blocks.birch_fence_gate, Blocks.jungle_fence_gate, Blocks.dark_oak_fence_gate, Blocks.nether_brick_fence, Blocks.cake, Blocks.trapdoor, Blocks.melon_block, Blocks.brewing_stand, Blocks.cauldron, Blocks.skull, Blocks.hopper, Blocks.carpet, Blocks.redstone_wire, Blocks.light_weighted_pressure_plate, Blocks.heavy_weighted_pressure_plate, Blocks.daylight_detector);
      field16 = new DoubleSetting("Range", 4.5D, 1.0D, 4.5D, 0.1D);
      bm_ = new ScaffoldModule$1("Min Rotation", 50.0D, 10.0D, 360.0D, 1.0D);
      field4 = new ScaffoldModule$2("Max Rotation", 70.0D, 10.0D, 360.0D, 1.0D);
      field2 = new DoubleSetting("Timer", 1.0D, 1.0D, 3.0D, 0.05D);
      field11 = new DoubleSetting("Tower timer", 1.0D, 1.0D, 3.0D, 0.05D);
      field14 = new DoubleSetting("Place delay", 0.0D, 0.0D, 5.0D, 1.0D);
      field10 = new BooleanSetting("Safe walk", true);
      field6 = new BooleanSetting("Disable aura", true);
      bl_ = new BooleanSetting("Tower move", false);
      field5 = new EnumSetting("Tower", "None", new String[]{"None", "Hypixel"});
      field13 = new EnumSetting("Sprint", "None", new String[]{"None", "Sprint"});
      field17 = new EnumSetting("Rotations", "Back", new String[]{"Back", "Simple"});
   }

   public static String method0(Class var0) {
      return var0.getSimpleName();
   }

   public static void method0(EntityLivingBase var0, Color var1) {
      Class196.field1.put(var0, var1);
   }

   public Class34 method0(float var1) {
      for(float var2 = var1 - 180.0F; var2 <= var1 + 180.0F; var2 += 45.0F) {
         for(float var3 = -10.0F; var3 <= 10.0F; ++var3) {
            for(float var4 = 90.0F; var4 > 30.0F; --var4) {
               if (NoRotateModule.method0(AnimationsModule.method0(var2 + var3, var4), this.field15)) {
                  return new Class34(var2 + var3, var4);
               }
            }
         }
      }

      return null;
   }

   public static Color j_(String var0) {
      char var1 = var0.charAt(var0.length() - 1);
      return new Color(Class96.field0["0123456789abcdef".indexOf(var1)]);
   }

   public int method5() {
      int var1 = 0;
      int var2 = -1;

      for(int var3 = 0; var3 < 9; ++var3) {
         ItemStack var4 = field58.thePlayer.inventory.getStackInSlot(var3);
         if (var4 != null && var4.getItem() instanceof ItemBlock && ((ItemBlock)var4.getItem()).getBlock().isFullCube() && var1 < var4.stackSize) {
            var1 = var4.stackSize;
            var2 = var3;
         }
      }

      return var2;
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      float var2 = field58.gameSettings.keyBindJump.isKeyDown() ? field11.method1() : field2.method1();
      if (var2 != 1.0F) {
         EnigmaSoulESPModule.method0(var2);
      }

      if (this.field7 > field14.method3()) {
         this.method8();
      }

      if (field58.theWorld.getBlockState((new BlockPos(field58.thePlayer)).down()).getBlock() instanceof BlockAir) {
         ++this.field7;
      } else {
         this.field7 = 0;
      }

      int var3 = (int)field58.thePlayer.posY - 1;
      if (field58.thePlayer.onGround || this.field12 > var3) {
         this.field12 = var3;
      }

      this.field15 = NoDebuffModule.method3();
      var1.method0(this.method10());
      this.field0 = false;
   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
   }

   public void b_() {
      if (field58.thePlayer != null) {
         method3(new C09PacketHeldItemChange(field58.thePlayer.inventory.currentItem));
      }

   }

   public ScaffoldModule() {
      super("Scaffold", Category.movement, SubCategory.movement, "Bridges for you");
      bl_.method0(ScaffoldModule::lambda$new$0);
      this.method0((Setting[])(new Setting[]{field17, field16, field14, field4, bm_, field2, field11, field5, bl_, field13, field10, field6}));
   }

   public static void method6() {
      StepModule.method7();
      MapData var0 = Class198.method0();
      if (var0 != null) {
         int var1 = Class119.field7 + Class119.field4 / 2;
         int var2 = Class119.field3 + Class119.field4 / 2;
         int var3 = Class119.field4 + 4;

         for(int var4 = 0; var4 < Class198.field1.length; ++var4) {
            for(int var5 = 0; var5 < Class198.field1.length; ++var5) {
               Class208 var6 = Class198.field1[var5][var4];
               int var7 = var1 + var4 * var3;
               int var8 = var2 + var5 * var3;
               if (var6 != null && var8 < 128 && var7 < 128) {
                  switch(var0.colors[var8 * 128 + var7]) {
                  case 0:
                  case 85:
                  case 119:
                     var6.field6 = Class432.field2;
                     break;
                  case 18:
                     var6.field6 = var6.method5() == Class46.field4 ? Class432.field4 : Class432.field1;
                     break;
                  case 30:
                     var6.field6 = var6.method5() == Class46.field6 ? Class432.field4 : Class432.field0;
                     break;
                  case 34:
                     var6.field6 = Class432.field5;
                     break;
                  default:
                     var6.field6 = Class432.field4;
                  }
               }
            }
         }

      }
   }

   public static Boolean lambda$new$0() {
      return field5.method0(0);
   }

   public boolean method8() {
      if (this.field15 == null) {
         return false;
      } else {
         int var1 = this.method5();
         if (var1 == -1) {
            return false;
         } else {
            method2(new C09PacketHeldItemChange(var1));
            ItemStack var2 = field58.thePlayer.inventory.getStackInSlot(var1);
            Class34 var3 = Class175.method0();
            MovingObjectPosition var4 = VelocityModule.method0(var3);
            if (NoRotateModule.method0(var4, this.field15) && ((ItemBlock)var2.getItem()).canPlaceBlockOnSide(field58.theWorld, var4.getBlockPos(), var4.sideHit, field58.thePlayer, var2)) {
               boolean var5 = this.method3();
               if (var5 && this.field15.method0().getY() > this.field12) {
                  return false;
               } else if (field58.playerController.onPlayerRightClick(field58.thePlayer, field58.theWorld, var2, var4.getBlockPos(), var4.sideHit, var4.hitVec)) {
                  field58.thePlayer.swingItem();
                  this.field3 = 0;
                  return true;
               } else {
                  AutoScribeModule.method5();
                  return false;
               }
            } else {
               return false;
            }
         }
      }
   }

   public boolean method9() {
      String var1 = field13.method4();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1811812806:
         if (var1.equals("Sprint")) {
            var2 = 1;
         }
         break;
      case -1248403467:
         if (var1.equals("Hypixel")) {
            var2 = 0;
         }
      }

      switch(var2) {
      case 0:
         return this.field3++ > 2 && !field58.gameSettings.keyBindJump.isKeyDown() && !field58.thePlayer.isPotionActive(Potion.moveSpeed);
      case 1:
         return true;
      default:
         return false;
      }
   }

   public void method4() {
      this.field15 = null;
      this.field7 = 0;
      this.field12 = -1;
      this.field0 = true;
      this.field3 = 0;
   }

   public Class34 method10() {
      if (this.field15 != null && this.field7 != 0) {
         if (this.method3() && this.field15.field0.offset(this.field15.field3).getY() > this.field12) {
            return Class175.method0();
         } else {
            float var1 = 45.0F * (float)Math.round(PacketLoggerModule.method5() / 45.0F);
            Class34 var2 = this.method0(var1);
            if (var2 == null) {
               return Class175.method0();
            } else {
               String var3 = field17.method4();
               byte var4 = -1;
               switch(var3.hashCode()) {
               case -1818419758:
                  if (var3.equals("Simple")) {
                     var4 = 0;
                  }
                  break;
               case 2062599:
                  if (var3.equals("Back")) {
                     var4 = 1;
                  }
               }

               switch(var4) {
               case 1:
                  float var5 = PacketLoggerModule.method5() - 180.0F;
                  var2.method3(Class163.method0(var2.method5(), var5 + 10.0F, var5 - 10.0F));
               case 0:
               default:
                  var2 = Class29.method0(Class175.method0(), var2, this.field0 ? 360.0F : (float)Class83.method0(field4.method0(), bm_.method0())).method4();
                  this.field0 = false;
                  return var2;
               }
            }
         }
      } else {
         return Class175.method0();
      }
   }

   private static class Class0 {
      public final BlockPos field0;
      public final EnumFacing field3;

      public BlockPos method0() {
         return this.field0.offset(this.field3);
      }

      public Class0(BlockPos var1, EnumFacing var2) {
         this.field0 = var1;
         this.field3 = var2;
      }
   }
}
