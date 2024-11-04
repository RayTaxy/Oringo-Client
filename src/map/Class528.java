package map;

import com.google.common.reflect.TypeToken;
import java.awt.Color;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.ContainerChest;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.module.AutoBlazeModule;
import oringo.module.AutoCloseModule;
import oringo.module.AutoFarmModule;

public class Class528 {
   public static final HashMap field0 = new HashMap();
   public static final Logger field1 = Oringo.method0("Room-Loader");

   public static boolean method0() {
      float var0 = Class229.method1().field36.moveStrafe;
      if (var0 == 0.0F) {
         return false;
      } else {
         AutoFarmModule.field58.gameSettings.thirdPersonView = var0 == -1.0F ? 1 : 2;
         return true;
      }
   }

   public static void method0(Entity var0, float var1, float var2, Color var3) {
      double var4 = var0.prevPosX + (var0.posX - var0.prevPosX) * (double)var1 - Oringo.field9.getRenderManager().viewerPosX;
      double var6 = var0.prevPosY + (var0.posY - var0.prevPosY) * (double)var1 + (double)(var0.height / 2.0F) - Oringo.field9.getRenderManager().viewerPosY;
      double var8 = var0.prevPosZ + (var0.posZ - var0.prevPosZ) * (double)var1 - Oringo.field9.getRenderManager().viewerPosZ;
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableTexture2D();
      GL11.glLineWidth(var2);
      GlStateManager.depthMask(false);
      AutoBlazeModule.method0(var3);
      GlStateManager.disableLighting();
      Class228 var10 = (new Class228(Oringo.field9.thePlayer.getLookVec())).method0().method1(0.1D);
      GL11.glBegin(2);
      GL11.glVertex3d(var10.field2, (double)Oringo.field9.thePlayer.getEyeHeight() + var10.field1, var10.field0);
      GL11.glVertex3d(var4, var6, var8);
      GL11.glEnd();
      AutoCloseModule.method5();
      GlStateManager.enableTexture2D();
      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
   }

   public static boolean method0(Class var0) {
      Set var1 = TypeToken.of(var0).getTypes().rawTypes();
      Method[] var2 = var0.getMethods();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Method var5 = var2[var4];

         try {
            Iterator var6 = var1.iterator();

            while(var6.hasNext()) {
               Class var7 = (Class)var6.next();
               Method var8 = var7.getDeclaredMethod(var5.getName(), var5.getParameterTypes());
               if (var8.isAnnotationPresent(SubscribeEvent.class)) {
                  return true;
               }
            }
         } catch (NoSuchMethodException var9) {
         }
      }

      return false;
   }

   public static ContainerChest method1() {
      return Class496.field26.currentScreen instanceof GuiContainer && ((GuiContainer)Class496.field26.currentScreen).inventorySlots instanceof ContainerChest ? (ContainerChest)((GuiContainer)Class496.field26.currentScreen).inventorySlots : null;
   }
}
