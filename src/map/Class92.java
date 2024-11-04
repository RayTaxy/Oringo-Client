package map;

import com.google.gson.annotations.SerializedName;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import oringo.module.CriticalsModule;
import oringo.module.PopupAnimationModule;

public class Class92 implements Class23 {
   @SerializedName("name")
   public String cW_;

   public Class92(String var1) {
      this.cW_ = var1;
   }

   public void method0(Class173 var1) {
   }

   public static float method0(float var0, float var1) {
      return ((var0 - var1) % 360.0F + 540.0F) % 360.0F - 180.0F;
   }

   public static int method0(Entity var0) {
      Minecraft var1 = Minecraft.getMinecraft();
      if (var0 != null) {
         for(int var2 = 0; var2 < 100; ++var2) {
            byte var3 = 60;
            if (Class417.method0(var1.thePlayer.posX - Math.sin(Math.toRadians((double)(var0.rotationYaw - (float)var3)) * 0.13D * (double)var2), var1.thePlayer.posY, var1.thePlayer.posZ + Math.cos(Math.toRadians((double)(var0.rotationYaw - (float)var3))) * 0.13D * (double)var2)) {
               if (Class417.method0(var1.thePlayer.posX - Math.sin(Math.toRadians((double)(var0.rotationYaw + (float)var3)) * 0.13D * (double)var2), var1.thePlayer.posY, var1.thePlayer.posZ + Math.cos(Math.toRadians((double)(var0.rotationYaw + (float)var3))) * 0.13D * (double)var2)) {
                  return -1;
               }

               PopupAnimationModule.method2("Smart: A");
               return 0;
            }

            if (Class417.method0(var1.thePlayer.posX - Math.sin(Math.toRadians((double)(var0.rotationYaw + (float)var3)) * 0.13D * (double)var2), var1.thePlayer.posY, var1.thePlayer.posZ + Math.cos(Math.toRadians((double)(var0.rotationYaw + (float)var3))) * 0.13D * (double)var2)) {
               PopupAnimationModule.method2("Smart: D");
               return 1;
            }
         }

         if (Class417.method0(var0.posX - Math.sin(Math.toRadians((double)var1.thePlayer.rotationYaw) * 3.0D), var0.posY, var0.posZ + Math.cos(Math.toRadians((double)var1.thePlayer.rotationYaw)) * 3.0D)) {
            PopupAnimationModule.method2("Smart: No strafe");
            return -1;
         }
      }

      return (new Random()).nextInt(2);
   }

   public static boolean method1(Entity var0) {
      return CriticalsModule.field58.thePlayer.onGround && CriticalsModule.field2 && !Class362.field20.method44() && var0 instanceof EntityLivingBase && (var0 instanceof EntityOtherPlayerMP || CriticalsModule.bR_.method1()) && (double)((EntityLivingBase)var0).hurtTime <= CriticalsModule.field4.method0() && ((EntityLivingBase)var0).hurtTime != -1 && CriticalsModule.field6.a_((long)CriticalsModule.field0.method0());
   }

   public String method0() {
      return this.cW_;
   }
}
