package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class291;
import map.Class361;
import map.Class447;
import map.Class496;
import map.Class510;
import map.Class94;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.command.MessageCommand;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class468;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class KuudraHelperModule extends Module {
   public EntityArmorStand field0;
   public final BooleanSetting by_ = new BooleanSetting("Auto stun", true);
   public final DoubleSetting field2 = new DoubleSetting("Stun distance", 5.0D, 1.0D, 6.0D, 0.1D, this::lambda$new$3);
   public final BooleanSetting field3 = (BooleanSetting)(new BooleanSetting("Rotate", false, this::lambda$new$0)).method2("Makes auto build rotate");
   public final Pattern field4 = Pattern.compile("§dPod Condition: §.(\\d+)%");
   public final Class447 field5 = new Class447();
   public final DoubleSetting bz_ = new DoubleSetting("Build distance", 5.0D, 2.0D, 6.0D, 0.1D, this::lambda$new$1);
   public final BooleanSetting field7 = new BooleanSetting("Auto build", true);
   public int field8;
   public final DoubleSetting field9 = new DoubleSetting("Stop percent", 20.0D, 0.0D, 100.0D, 1.0D, this::lambda$new$4);
   public BlockPos field6;
   public BlockPos field11;
   public final DoubleSetting field12 = new DoubleSetting("Build CPS", 5.0D, 1.0D, 20.0D, 1.0D, this::lambda$new$2);

   public Boolean lambda$new$3() {
      return !this.by_.method1();
   }

   public boolean method0(IBlockState var1) {
      if (var1.getBlock() != Blocks.stained_hardened_clay && var1.getBlock() != Blocks.glass_pane) {
         return false;
      } else {
         return var1.getValue(BlockColored.COLOR) == EnumDyeColor.MAGENTA;
      }
   }

   public static void method0(int var0) {
      GL11.glColorMask(true, true, true, true);
      GL11.glStencilFunc(514, var0, 1);
      GL11.glStencilOp(7680, 7680, 7680);
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (field58.theWorld != null && this.field6 != null && Class496.field18 && this.by_.method1()) {
         if (var1.field0 instanceof S25PacketBlockBreakAnim) {
            S25PacketBlockBreakAnim var2 = (S25PacketBlockBreakAnim)var1.field0;
            if (var2.getPosition().equals(this.field6)) {
               if (var2.getProgress() == 10 && this.field8 != 10) {
                  this.field11 = this.field6;
                  this.field6 = null;
               }

               this.field8 = var2.getProgress();
            }

         }
      }
   }

   public Boolean lambda$new$2() {
      return !this.field7.method1();
   }

   public boolean lambda$onMotion$6(EntityArmorStand var1) {
      return var1.getDistanceSqToEntity(field58.thePlayer) < this.bz_.method0() * this.bz_.method0() && var1.getName().equals("§f§lSNEAK§f§l + §f§lPUNCH");
   }

   public Boolean lambda$new$1() {
      return !this.field7.method1();
   }

   public static boolean lambda$onMotion$5(EntityArmorStand var0) {
      return var0.getName().startsWith("§dPod Condition: ");
   }

   public Boolean lambda$new$0() {
      return !this.field7.method1();
   }

   public Boolean lambda$new$4() {
      return !this.by_.method1();
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field6 != null) {
         AutoMaskModule.method0(this.field6, Color.WHITE);
      }

   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (Class496.field18 && this.by_.method1()) {
         if (this.field6 != null && this.method0(this.field6) && this.method0(field58.theWorld.getBlockState(this.field6))) {
            Class361.method0((Class94)(new Class510(this.field6, EnumFacing.DOWN)));
         } else {
            this.field6 = null;
            List var2 = field58.theWorld.getEntities(EntityArmorStand.class, KuudraHelperModule::lambda$onMotion$5);
            if (!var2.isEmpty()) {
               IBlockState var3 = null;
               EntityArmorStand var4 = null;
               BlockPos var5 = (new BlockPos(field58.thePlayer)).up();
               Iterator var6 = BlockPos.getAllInBox(var5.add(7, 7, 7), var5.add(-7, -7, -7)).iterator();

               while(var6.hasNext()) {
                  BlockPos var7 = (BlockPos)var6.next();
                  if (this.method0(var7)) {
                     var3 = field58.theWorld.getBlockState(var7);
                     if (this.method0(var3)) {
                        var4 = null;
                        double var8 = Double.MAX_VALUE;
                        Iterator var10 = var2.iterator();

                        while(var10.hasNext()) {
                           EntityArmorStand var11 = (EntityArmorStand)var10.next();
                           double var12 = var7.distanceSq(new BlockPos(var11));
                           if (var12 < var8) {
                              var8 = var12;
                              var4 = var11;
                           }
                        }

                        this.field6 = var7;
                        if (!this.field6.equals(this.field11)) {
                           break;
                        }
                     }
                  }
               }

               if (this.field6 != null && var3 != null && var4 != null) {
                  Matcher var14 = this.field4.matcher(var4.getName());
                  if (!var14.find()) {
                     return;
                  }

                  int var15 = Integer.parseInt(var14.group(1));
                  if (var15 <= this.field9.method3()) {
                     this.field6 = null;
                  }
               }

            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Class468 var1) {
      if (this.field7.method1() && Class496.field18) {
         if (this.field0 != null) {
            var1.method0(true);
         }

      }
   }

   public KuudraHelperModule() {
      super("Kuudra Helper", Category.skyblock, SubCategory.slayer);
      this.method0((Setting[])(new Setting[]{this.field7, this.bz_, this.field12, this.field3, this.by_, this.field2, this.field9}));
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      this.field0 = null;
      if (this.field7.method1() && Class496.field18) {
         List var2 = field58.theWorld.getEntities(EntityArmorStand.class, this::lambda$onMotion$6);
         double var3 = 100.0D;
         Iterator var5 = var2.iterator();

         while(var5.hasNext()) {
            EntityArmorStand var6 = (EntityArmorStand)var5.next();
            double var7 = field58.thePlayer.getDistanceSqToEntity(var6);
            if (var7 < var3) {
               var3 = var7;
               this.field0 = var6;
            }
         }

         if (this.field0 != null) {
            if (!Class361.method0((Class94)(new Class291(this.field0)))) {
               return;
            }

            if (this.field3.method1()) {
               var1.method0(MessageCommand.method0((Entity)this.field0));
            }
         }

      }
   }

   public boolean method0(BlockPos var1) {
      return field58.thePlayer.getDistanceSq((double)var1.getX(), (double)((float)var1.getY() - field58.thePlayer.eyeHeight), (double)var1.getZ()) < this.field2.method0() * this.field2.method0();
   }
}
