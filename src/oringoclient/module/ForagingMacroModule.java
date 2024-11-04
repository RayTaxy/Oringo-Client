package oringo.module;

import java.awt.Toolkit;
import map.Class157;
import map.Class263;
import map.Class447;
import map.Class496;
import map.Class514;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.LWJGLUtil;
import oringo.event.Class189;
import oringo.event.Class468;
import oringo.notification.Notifications;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class ForagingMacroModule extends Module {
   public final Class447 field0 = new Class447();
   public final BooleanSetting field1 = new BooleanSetting("Notifications", true);
   public final BooleanSetting field2 = new BooleanSetting("Auto rejoin", true);
   public ForagingMacroModule.Class0 field3;
   public final Class447 field4 = new Class447();
   public boolean cN_;
   public BlockPos field6;
   public final BooleanSetting field7 = new BooleanSetting("Maximize window", true, ForagingMacroModule::lambda$new$0);
   public final BlockPos[] field8 = new BlockPos[4];
   public int field9;
   public final Class447 field10 = new Class447();
   public boolean db_;

   public static Boolean lambda$new$0() {
      return LWJGLUtil.getPlatform() == 3;
   }

   public ForagingMacroModule() {
      super("Foraging Macro", Category.skyblock, SubCategory.skills, "Automatic foraging");
      this.method0((Setting[])(new Setting[]{this.field2, this.field1, this.field7}));
   }

   public BlockPos method6() {
      for(int var1 = 0; var1 < 2; ++var1) {
         BlockPos[] var2 = this.field8;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            BlockPos var5 = var2[var4];
            BlockPos var6 = var5.up().up(var1);
            if (field58.theWorld.getBlockState(var6).getBlock() instanceof BlockLog) {
               return var6;
            }
         }
      }

      return null;
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (Class496.field27) {
         if (var1.field0 instanceof S08PacketPlayerPosLook) {
            this.db_ = true;
         }

      }
   }

   public static int method0(int var0, int var1) {
      return (int)((float)var0 / ((float)(1000 - var1) / 1000.0F));
   }

   public ForagingMacroModule.Class0 method7() {
      BlockPos[] var1 = this.field8;
      int var2 = var1.length;

      int var3;
      BlockPos var4;
      IBlockState var5;
      for(var3 = 0; var3 < var2; ++var3) {
         var4 = var1[var3];
         var5 = field58.theWorld.getBlockState(var4);
         if (var5.getBlock() != Blocks.grass && var5.getBlock() != Blocks.dirt) {
            return ForagingMacroModule.Class0.field2;
         }
      }

      var1 = this.field8;
      var2 = var1.length;

      for(var3 = 0; var3 < var2; ++var3) {
         var4 = var1[var3];
         var5 = field58.theWorld.getBlockState(var4.up());
         IBlockState var6 = field58.theWorld.getBlockState(var4.up(2));
         if (var5.getBlock() instanceof BlockLog || var6.getBlock() instanceof BlockLog) {
            return ForagingMacroModule.Class0.field1;
         }
      }

      var1 = this.field8;
      var2 = var1.length;

      for(var3 = 0; var3 < var2; ++var3) {
         var4 = var1[var3];
         var5 = field58.theWorld.getBlockState(var4.up());
         if (var5.getBlock() == Blocks.air) {
            return ForagingMacroModule.Class0.field0;
         }
      }

      var1 = this.field8;
      var2 = var1.length;
      byte var7 = 0;
      if (var7 < var2) {
         var4 = var1[var7];
         var5 = field58.theWorld.getBlockState(var4.up());
         if (var5.getBlock() instanceof BlockSapling) {
            return ForagingMacroModule.Class0.field3;
         }
      }

      return null;
   }

   public void method4() {
      this.db_ = false;
      if (field58.thePlayer == null) {
         this.method1(false);
      } else {
         this.field6 = new BlockPos(field58.thePlayer);
         if (!this.method9()) {
            method2("Unable to find a spot to plant!");
            this.method1(false);
         }
      }
   }

   @SubscribeEvent
   public void method0(Class468 var1) {
      // $FF: Couldn't be decompiled
   }

   public static void o_() {
      Class263.field0 = null;
   }

   public void A_(String var1) {
      Class514.method0(var1, 5000, Notifications.Class1.field2);
      if (this.field1.method1()) {
         PeltESPModule.A_(var1);
         if (this.field7.method1()) {
            Class157.method0();
         }

         Toolkit.getDefaultToolkit().beep();
      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START) {
         if (field58.thePlayer == null || !Class496.field27) {
            if (!this.field2.method1()) {
               this.A_("Not on private island!");
               this.method1(false);
            } else if (this.field4.a_(7000L)) {
               this.field4.o_();
               if (field58.thePlayer == null) {
                  field58.displayGuiScreen(new GuiConnecting(field58.currentScreen, field58, new ServerData("Hypixel", "play.hypixel.net", false)));
               } else {
                  field58.thePlayer.sendChatMessage(Class496.field7 ? "/is" : (this.cN_ ? "/lobby" : "/skyblock"));
                  this.cN_ = !this.cN_;
               }
            }
         }
      }
   }

   public boolean method9() {
      BlockPos var1 = new BlockPos(field58.thePlayer);
      EnumFacing[] var2 = EnumFacing.HORIZONTALS;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         EnumFacing var5 = var2[var4];
         BlockPos var6 = var1.offset(var5);
         IBlockState var7 = field58.theWorld.getBlockState(var6);
         if (var7.getBlock() == Blocks.dirt || var7.getBlock() == Blocks.grass) {
            this.field8[3] = var6;
            IBlockState var8 = field58.theWorld.getBlockState(var6.offset(var5));
            if (var8.getBlock() == Blocks.dirt || var8.getBlock() == Blocks.grass) {
               this.field8[0] = var6.offset(var5);

               for(int var9 = -1; var9 <= 1; var9 += 2) {
                  BlockPos var10 = var6.offset(var5.rotateY(), var9);
                  IBlockState var11 = field58.theWorld.getBlockState(var10);
                  IBlockState var12 = field58.theWorld.getBlockState(var10.offset(var5));
                  if (var11.getBlock() == Blocks.grass || var11.getBlock() == Blocks.dirt || var12.getBlock() == Blocks.grass || var12.getBlock() == Blocks.dirt) {
                     this.field8[1] = var10.offset(var5);
                     this.field8[2] = var10;
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public static Minecraft access$000() {
      return field58;
   }

   private static enum Class0 {
      field0("SAPLING:3");

      private static final ForagingMacroModule.Class0[] field4 = new ForagingMacroModule.Class0[]{field0, field3, field1, field2};
      field1("TREECAPITATOR_AXE");

      private final String field5;
      field2((String)null),
      field3("ENCHANTED_BONE_MEAL");

      private boolean lambda$findItem$0(ItemStack var1) {
         return this.field5.equals(BlockHitModule.method0(var1));
      }

      private Class0(String var3) {
         this.field5 = var3;
      }

      public int method0() {
         return TrailModule.method0(this::lambda$findItem$0);
      }

      public boolean method1() {
         return this.field5.equals(BlockHitModule.method0(ForagingMacroModule.access$000().thePlayer.getHeldItem()));
      }
   }
}
