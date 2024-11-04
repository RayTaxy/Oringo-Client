package oringo.module;

import java.awt.Color;
import map.Class362;
import map.Class447;
import map.Class496;
import map.Class500;
import map.Class506;
import map.Class92;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class332;
import oringo.mixin.EntityPlayerSPAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class CriticalsModule extends Module {
   public static final DoubleSetting field0 = new DoubleSetting("Delay", 500.0D, 0.0D, 2000.0D, 50.0D);
   public static final BooleanSetting bR_ = new BooleanSetting("Non players", false);
   public static boolean field2;
   public static final double[] field3 = new double[]{0.0625D, 0.0625D};
   public static final DoubleSetting field4 = new DoubleSetting("Hurt time", 10.0D, 0.0D, 10.0D, 1.0D);
   public static final EnumSetting field5 = new EnumSetting("Mode", "Hypixel", new String[]{"Hypixel"});
   public static final Class447 field6 = new Class447();

   public CriticalsModule() {
      super("Criticals", Category.combat, SubCategory.combat, "Makes you crit");
      this.method0((Setting[])(new Setting[]{field5, field0, field4, bR_}));
      EnumSetting var10001 = field5;
      this.method0((Class500)(var10001::method4));
   }

   public static Color method0(Color var0, int var1) {
      return new Color(var0.getRed(), var0.getGreen(), var0.getBlue(), var1);
   }

   public static int method0(ItemStack var0) {
      if (var0 == null) {
         return -1;
      } else {
         NBTTagCompound var1 = TargetStrafeModule.method0(var0);
         return var1 != null && var1.hasKey("farmed_cultivating") ? var1.getInteger("farmed_cultivating") : -1;
      }
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C03PacketPlayer) {
         field2 = ((C03PacketPlayer)var1.field0).isOnGround();
      }

   }

   @SubscribeEvent
   public void method1(Class332 var1) {
      if (!Class496.field7) {
         if (this.method44() && var1.field0 instanceof C0APacketAnimation) {
            Object var2 = KillAuraModule.field24 != null ? KillAuraModule.field24 : (field58.objectMouseOver != null ? field58.objectMouseOver.entityHit : null);
            if (var2 == null || !Class506.method0((Entity)var2) || !field58.thePlayer.onGround || !field2 || field58.thePlayer.isInWater() || field58.thePlayer.isInLava() || field58.thePlayer.isOnLadder() || Class362.field20.method44()) {
               return;
            }

            if (Class92.method1((Entity)var2)) {
               field6.o_();
               if (field5.method4().equals("Hypixel")) {
                  double[] var3 = field3;
                  int var4 = var3.length;

                  for(int var5 = 0; var5 < var4; ++var5) {
                     double var6 = var3[var5];
                     field58.getNetHandler().getNetworkManager().sendPacket(new C04PacketPlayerPosition(((EntityPlayerSPAccessor)field58.thePlayer).getLastReportedPosX(), ((EntityPlayerSPAccessor)field58.thePlayer).getLastReportedPosY() + var6, ((EntityPlayerSPAccessor)field58.thePlayer).getLastReportedPosZ(), false));
                  }

                  ((EntityPlayerSPAccessor)field58.thePlayer).setLastReportedPosY(((EntityPlayerSPAccessor)field58.thePlayer).getLastReportedPosY() + field3[field3.length - 1]);
                  method3("Crit");
               }

               ((EntityLivingBase)var2).hurtTime = -1;
            }
         }

      }
   }

   public static String method0(double var0) {
      if (var0 >= 1.0E9D) {
         return (var0 >= 1.0E10D ? (double)((long)(var0 / 1.0E9D)) : (double)((long)(var0 / 1.0E8D)) / 10.0D) + "B";
      } else if (var0 >= 1000000.0D) {
         return var0 >= 1.0E7D ? (long)(var0 / 1000000.0D) + "M" : (double)((long)(var0 / 100000.0D)) / 10.0D + "M";
      } else if (var0 >= 1000.0D) {
         return var0 >= 10000.0D ? (long)(var0 / 1000.0D) + "k" : (double)((long)(var0 / 100.0D)) / 10.0D + "k";
      } else {
         return String.valueOf(var0);
      }
   }
}
