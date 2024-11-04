package oringo.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import map.Class122;
import map.Class124;
import map.Class216;
import map.Class259;
import map.Class362;
import map.Class411;
import map.Class447;
import map.Class81;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S08PacketPlayerPosLook.EnumFlags;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.codec.digest.DigestUtils;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoFishModule extends Module {
   public Vec3 field0 = new Vec3(0.0D, 0.0D, 0.0D);
   public boolean field1;
   public static boolean field2;
   public int field3 = 0;
   public final DoubleSetting dd_ = new DoubleSetting("Impact Interval", 105.0D, 50.0D, 500.0D, 1.0D, this::lambda$new$0);
   public final DoubleSetting field5 = new DoubleSetting("Detection Range", 15.0D, 5.0D, 32.0D, 1.0D, this::lambda$new$3);
   public final BooleanSetting field6 = new BooleanSetting("Anti AFK Rotations", true);
   public static AutoFishModule.Class0 field7;
   public final Class447 field8 = new Class447();
   public final Class447 field9 = new Class447();
   public final BooleanSetting field10 = new BooleanSetting("Player Detection", false);
   public final Class447 field11 = new Class447();
   public final ButtonSetting field12 = new ButtonSetting("Enable No Rotate", AutoFishModule::lambda$new$1, this::lambda$new$2);
   public final Class447 field13 = new Class447();
   public final Class447 field14 = new Class447();
   public final BooleanSetting field15 = new BooleanSetting("Only Assist", false);
   public final BooleanSetting field16 = new BooleanSetting("Auto Totem", false);
   public final BooleanSetting field17 = new BooleanSetting("Anti AFK Movement", true);
   public final BooleanSetting field18 = new BooleanSetting("Slugfish (20s+)", false);
   public float field4 = 100.0F;
   public int field20 = 0;
   public final BooleanSetting field21 = new BooleanSetting("Auto Impact", true);

   public static boolean lambda$getEntitiesToKill$8(EntityArmorStand var0) {
      return var0.getDisplayName().getUnformattedText().endsWith("❤") && var0.getDistanceToEntity(field58.thePlayer) <= 5.0F && Class124.method0(var0) instanceof EntityLivingBase && ((EntityLivingBase)Class124.method0(var0)).deathTime == 0;
   }

   public void b_() {
      KeyBinding.setKeyBindState(field58.gameSettings.keyBindSneak.getKeyCode(), false);
      KeyBinding.setKeyBindState(field58.gameSettings.keyBindForward.getKeyCode(), false);
      KeyBinding.setKeyBindState(field58.gameSettings.keyBindBack.getKeyCode(), false);
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (!(field58.currentScreen instanceof GuiContainer)) {
         if (!this.field15.method1()) {
            if (KillAuraModule.field24 != null) {
               field58.thePlayer.inventory.currentItem = 0;
            } else if (TerminatorAuraModule.field24 == null) {
               if (field7 == AutoFishModule.Class0.field0) {
                  var1.bF_ = 88.0F + (new Random()).nextFloat() * 2.0F;
               }

               if (field7 == AutoFishModule.Class0.field2) {
                  var1.bF_ = (float)((double)var1.bF_ - (double)(new Random()).nextFloat() * 0.2D);
                  var1.t_ = (float)((double)var1.t_ + ((double)(new Random()).nextFloat() * 0.4D - 0.2D));
               }

            }
         }
      }
   }

   public Boolean lambda$new$2() {
      return !this.field21.method1() || Class362.field59.method44();
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.method44()) {
         if (var1.field0 instanceof S12PacketEntityVelocity && field58.thePlayer.fishEntity != null && ((S12PacketEntityVelocity)var1.field0).getEntityID() == field58.thePlayer.fishEntity.getEntityId() && !this.field1 && ((S12PacketEntityVelocity)var1.field0).getMotionY() >= -20 && ((S12PacketEntityVelocity)var1.field0).getMotionY() <= 0) {
            this.field1 = true;
         }

         if (var1.field0 instanceof S08PacketPlayerPosLook && ((S08PacketPlayerPosLook)var1.field0).getPitch() == 0.0F && !((S08PacketPlayerPosLook)var1.field0).func_179834_f().contains(EnumFlags.X_ROT)) {
            this.method1(false);
         }

      }
   }

   public static boolean lambda$onTick$5(ItemStack var0) {
      return var0.getItem() == Items.fishing_rod;
   }

   public static boolean lambda$onTick$6(ItemStack var0) {
      return var0.getDisplayName().toLowerCase().contains("totem of corruption");
   }

   public void method4() {
      field7 = AutoFishModule.Class0.field2;
      this.method8();
      this.field13.o_();
      this.field4 = 100.0F;
      super.method4();
   }

   public static String method0(File var0) {
      if (!var0.exists()) {
         return "0";
      } else {
         try {
            FileInputStream var1 = new FileInputStream(var0);
            Throwable var2 = null;

            String var3;
            try {
               var3 = DigestUtils.sha256Hex(var1);
            } catch (Throwable var13) {
               var2 = var13;
               throw var13;
            } finally {
               if (var1 != null) {
                  if (var2 != null) {
                     try {
                        var1.close();
                     } catch (Throwable var12) {
                        var2.addSuppressed(var12);
                     }
                  } else {
                     var1.close();
                  }
               }

            }

            return var3;
         } catch (IOException var15) {
            return "0";
         }
      }
   }

   public String d_() {
      return "Legacy Auto Fish";
   }

   public static boolean lambda$onTick$7(ItemStack var0) {
      return var0 != null && Class81.method0(var0);
   }

   public Boolean lambda$new$3() {
      return !this.field10.method1();
   }

   public boolean lambda$onTick$4(EntityOtherPlayerMP var1) {
      return (double)field58.thePlayer.getDistanceToEntity(var1) <= this.field5.method0() && (!var1.isInvisible() || !Arrays.equals(var1.inventory.armorInventory, new ItemStack[4])) && var1.riddenByEntity == null && !Class122.method0(var1) && var1.ticksExisted > 20;
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      // $FF: Couldn't be decompiled
   }

   public Boolean lambda$new$0() {
      return !this.field21.method1();
   }

   @SubscribeEvent
   public void method0(EntityJoinWorldEvent var1) {
      if (!this.field15.method1()) {
         if (var1.entity.equals(field58.thePlayer)) {
            this.method1(false);
         } else {
            if (var1.entity.getDistance(this.field0.xCoord, var1.entity.posY, this.field0.zCoord) < 0.8D && !this.field11.a_(250L) && var1.entity instanceof EntityArmorStand && var1.entity.getDisplayName().getUnformattedText().startsWith("[")) {
               field7 = AutoFishModule.Class0.field0;
            }

         }
      }
   }

   @SubscribeEvent
   public void field1(Class189 var1) {
      if (!(field58.currentScreen instanceof GuiContainer)) {
         if (var1.field0 instanceof S2APacketParticles) {
            if (field58.thePlayer.fishEntity == null) {
               return;
            }

            if (this.field18.method1() && !this.field8.a_(20000L)) {
               return;
            }

            S2APacketParticles var2 = (S2APacketParticles)var1.field0;
            if (field58.thePlayer.fishEntity.getDistance(var2.getXCoordinate(), field58.thePlayer.fishEntity.posY, var2.getZCoordinate()) > 0.5D) {
               return;
            }

            if (var2.getParticleType() != EnumParticleTypes.WATER_BUBBLE && var2.getParticleType() != EnumParticleTypes.DRIP_LAVA) {
               return;
            }

            if (var2.getYOffset() != 0.0F) {
               return;
            }

            if (var2.getParticleCount() != 6) {
               return;
            }

            this.field13.o_();
            this.field0 = new Vec3(field58.thePlayer.fishEntity.posX, field58.thePlayer.fishEntity.posY, field58.thePlayer.fishEntity.posZ);
            this.field11.o_();
            Class216.f_();
            this.method8();
         }

      }
   }

   public void method8() {
      this.field1 = false;
   }

   public static void lambda$new$1() {
      Class362.field59.method1(true);
   }

   static {
      field7 = AutoFishModule.Class0.field2;
      field2 = false;
   }

   public AutoFishModule() {
      super("Auto Fish", Category.other, SubCategory.skills, (String)null);
      this.method0((Setting[])(new Setting[]{this.field15, this.field17, this.field6, this.field21, this.dd_, this.field12, this.field10, this.field5, this.field18, this.field16}));
   }

   public static enum Class0 {
      field0,
      field1,
      field2;

      private static final AutoFishModule.Class0[] field3 = new AutoFishModule.Class0[]{field2, field0, field1};
   }
}
