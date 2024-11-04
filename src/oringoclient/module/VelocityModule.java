package oringo.module;

import map.Class144;
import map.Class198;
import map.Class208;
import map.Class34;
import map.Class357;
import map.Class424;
import map.Class432;
import map.Class447;
import map.Class469;
import map.Class496;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class194;
import oringo.mixin.S12PacketEntityVelocityAccessor;
import oringo.mixin.S27PacketExplosionAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class VelocityModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("SB vertical", 1.0D, -2.0D, 2.0D, 0.05D, VelocityModule::lambda$new$1);
   public final DoubleSetting cf_ = new DoubleSetting("SB horizontal", 1.0D, -2.0D, 2.0D, 0.05D, VelocityModule::lambda$new$0);
   public static final String[] field2 = new String[]{"Jerry-chine Gun", "Bonzo's Staff", "Grappling Hook", "Leaping sword", "Silk-Edge Sword", "Spring Boots", "Spider Boots", "Tarantula Boots"};
   public static final BooleanSetting field3 = new BooleanSetting("Skyblock kb", true);
   public final DoubleSetting field4 = new DoubleSetting("Horizontal", 0.0D, -2.0D, 2.0D, 0.05D);
   public static final BooleanSetting field5 = new BooleanSetting("Only on skyblock", false);
   public final Class447 field6 = new Class447();
   public static final BooleanSetting field7 = (BooleanSetting)(new BooleanSetting("Control", false, VelocityModule::lambda$static$3)).method2("Redirects velocity in the direction you are going to");
   public static final BooleanSetting field8 = new BooleanSetting("Vertical Jerry-chine", false, VelocityModule::lambda$static$2);
   public static final BooleanSetting field9 = new BooleanSetting("Only in dungeon", false, VelocityModule::lambda$static$4);
   public static final BooleanSetting field10 = new BooleanSetting("Near slime blocks", true);
   public final DoubleSetting field11 = new DoubleSetting("Vertical", 0.0D, -2.0D, 2.0D, 0.05D);
   public final Class447 field12 = new Class447();
   public static final BooleanSetting field13 = new BooleanSetting("Kuudra lava kb", false);

   public static MovingObjectPosition method0(Class34 var0) {
      return AnimationsModule.method0(var0.method5(), var0.method2());
   }

   public static void method5() {
      int var1 = 0;

      for(int var2 = 0; var2 < Class198.field1.length; ++var2) {
         int var0 = 0;
         Class208[] var3 = Class198.field1[var2];

         for(int var4 = 0; var4 < var3.length; ++var4) {
            Class208 var5 = var3[var4];
            if (var5 != null) {
               Class469.method0((float)var0, (float)var1, (float)(var0 + 18), (float)(var1 + 18), var5.method17().getRGB());
               if (var5.field6.method0() != null) {
                  if (var5.field6 == Class432.field0) {
                     GlStateManager.color(0.29411766F, 0.9607843F, 0.25882354F, 1.0F);
                  }

                  SumoFencesModule.method0(var5.field6.method0(), (float)var0, (float)var1, 18.0F, 18.0F);
               }

               if (var5.field7 && !Class198.field0.method13() && var5.field6 != Class432.field0) {
                  SumoFencesModule.method0(Class144.field2, (float)var0, (float)var1, 9.0F, 9.0F);
               }

               boolean var6 = false;
               boolean var7 = false;
               if (var5.field4 != null && var5.field4.field0 != Class424.field5) {
                  Class469.method0((float)(var0 + 18), (float)var1 + 6.0F, (float)(var0 + 22), (float)var1 + 12.0F, var5.field4.method0(var5.field6 != Class432.field2).getRGB());
               } else if (AnimationsModule.method0(var5, var4 + 1, var2)) {
                  Class469.method0((float)(var0 + 18), (float)var1, (float)(var0 + 22), (float)(var1 + 18), var5.method17().getRGB());
                  var6 = true;
               }

               if (var5.field11 != null && var5.field11.field0 != Class424.field5) {
                  Class469.method0((float)var0 + 6.0F, (float)(var1 + 18), (float)var0 + 12.0F, (float)(var1 + 22), var5.field11.method0(var5.field6 != Class432.field2).getRGB());
               } else if (AnimationsModule.method0(var5, var4, var2 + 1)) {
                  Class469.method0((float)var0, (float)(var1 + 18), (float)(var0 + 18), (float)(var1 + 22), var5.method17().getRGB());
                  var7 = true;
               }

               if (var7 && var6 && AnimationsModule.method0(var5, var4 + 1, var2 + 1)) {
                  Class469.method0((float)(var0 + 18), (float)(var1 + 18), (float)(var0 + 22), (float)(var1 + 22), var5.method17().getRGB());
               }
            }

            var0 += 22;
         }

         var1 += 22;
      }

   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      if (var1.field2 == Blocks.slime_block) {
         this.field12.o_();
      }

   }

   public static Boolean lambda$static$3() {
      return !field3.method1();
   }

   public static Boolean lambda$static$2() {
      return !field3.method1();
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void field0(Class189 var1) {
      if (this.method44() && field58.thePlayer != null && (!field5.method1() || Class496.field7) && (!field9.method1() || !field5.method1() || Class496.field5)) {
         if (this.field12.a_(250L) || field10.method1()) {
            double[] var3;
            if (var1.field0 instanceof S27PacketExplosion) {
               S27PacketExplosion var2 = (S27PacketExplosion)var1.field0;
               if (this.method12()) {
                  ((S27PacketExplosionAccessor)var2).setMotionX(var2.func_149149_c() * this.cf_.method1());
                  ((S27PacketExplosionAccessor)var2).setMotionY(var2.func_149144_d() * this.field0.method1());
                  ((S27PacketExplosionAccessor)var2).setMotionZ(var2.func_149147_e() * this.cf_.method1());
                  if (field8.method1() && field58.thePlayer.getHeldItem() != null && field58.thePlayer.getHeldItem().getDisplayName().contains("Jerry-chine Gun")) {
                     ((S27PacketExplosionAccessor)var2).setMotionX(0.0F);
                     ((S27PacketExplosionAccessor)var2).setMotionZ(0.0F);
                  } else if (field7.method1()) {
                     var3 = AntiSpectateModule.method0(AntiNickerModule.method0(var2.getX(), var2.getZ()));
                     ((S27PacketExplosionAccessor)var2).setMotionX((float)var3[0]);
                     ((S27PacketExplosionAccessor)var2).setMotionZ((float)var3[1]);
                  }
               } else {
                  ((S27PacketExplosionAccessor)var2).setMotionX(var2.func_149149_c() * this.field4.method1());
                  ((S27PacketExplosionAccessor)var2).setMotionY(var2.func_149144_d() * this.field11.method1());
                  ((S27PacketExplosionAccessor)var2).setMotionZ(var2.func_149147_e() * this.field4.method1());
               }
            } else if (var1.field0 instanceof S12PacketEntityVelocity && ((S12PacketEntityVelocity)var1.field0).getEntityID() == field58.thePlayer.getEntityId()) {
               S12PacketEntityVelocity var4 = (S12PacketEntityVelocity)var1.field0;
               if (this.method12()) {
                  ((S12PacketEntityVelocityAccessor)var4).setMotionX((int)((double)var4.getMotionX() * this.cf_.method0()));
                  ((S12PacketEntityVelocityAccessor)var4).setMotionY((int)((float)var4.getMotionY() * this.field0.method1()));
                  ((S12PacketEntityVelocityAccessor)var4).setMotionZ((int)((float)var4.getMotionZ() * this.cf_.method1()));
                  if (field8.method1() && field58.thePlayer.getHeldItem() != null && field58.thePlayer.getHeldItem().getDisplayName().contains("Jerry-chine Gun")) {
                     ((S12PacketEntityVelocityAccessor)var4).setMotionX((int)(field58.thePlayer.motionX * 8000.0D));
                     ((S12PacketEntityVelocityAccessor)var4).setMotionZ((int)(field58.thePlayer.motionZ * 8000.0D));
                  } else if (field7.method1()) {
                     var3 = AntiSpectateModule.method0(AntiNickerModule.method0((double)var4.getMotionX(), (double)var4.getMotionZ()));
                     ((S12PacketEntityVelocityAccessor)var4).setMotionX((int)var3[0]);
                     ((S12PacketEntityVelocityAccessor)var4).setMotionZ((int)var3[1]);
                  }
               } else {
                  ((S12PacketEntityVelocityAccessor)var4).setMotionX(this.field4.method0() == 0.0D ? (int)(field58.thePlayer.motionX * 8000.0D) : (int)((double)var4.getMotionX() * this.field4.method0()));
                  ((S12PacketEntityVelocityAccessor)var4).setMotionY(this.field11.method0() == 0.0D ? (int)(field58.thePlayer.motionY * 8000.0D) : (int)((double)var4.getMotionY() * this.field11.method0()));
                  ((S12PacketEntityVelocityAccessor)var4).setMotionZ(this.field4.method0() == 0.0D ? (int)(field58.thePlayer.motionZ * 8000.0D) : (int)((double)var4.getMotionZ() * this.field4.method0()));
               }
            }

         }
      }
   }

   public VelocityModule() {
      super("Velocity", 0, Category.player, SubCategory.player, "Modify the knockback you take");
      this.method0((Setting[])(new Setting[]{this.field4, this.field11, field3, this.cf_, this.field0, field7, field8, field5, field9, field13, field10}));
   }

   public static Boolean lambda$new$1() {
      return !field3.method1();
   }

   public static boolean method9() {
      return (!Class357.field2 || !Class357.field1.equals(Class357.field3)) && Class496.field1 && Minecraft.getMinecraft().theWorld != null;
   }

   public static Boolean lambda$new$0() {
      return !field3.method1();
   }

   public static Boolean lambda$static$4() {
      return !field5.method1();
   }

   public boolean method12() {
      if (field13.method1() && Class496.field18) {
         if (field58.thePlayer.isInLava()) {
            this.field6.o_();
            return true;
         }

         if (!this.field6.a_(500L)) {
            return true;
         }
      }

      if (!field3.method1()) {
         return false;
      } else if (field58.thePlayer.isInLava() && Class496.field5) {
         return true;
      } else {
         String[] var1 = field2;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            String var4 = var1[var3];
            if (field58.thePlayer.getHeldItem() != null && field58.thePlayer.getHeldItem().getDisplayName().contains(var4) || field58.thePlayer.getCurrentArmor(0) != null && field58.thePlayer.getCurrentArmor(0).getDisplayName().contains(var4)) {
               return true;
            }
         }

         return false;
      }
   }
}
