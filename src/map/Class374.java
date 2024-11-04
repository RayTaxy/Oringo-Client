package map;

import com.google.common.collect.BiMap;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jagrosh.discordipc.entities.DiscordBuild;
import java.awt.Color;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import oringo.Oringo;
import oringo.module.AutoFarmModule;
import oringo.module.DiscordRPCModule;
import oringo.module.DiscordRPCModule$1;

public class Class374 {
   public final int[] field0;
   public final int field1;
   public final String field2;
   public final int field3;
   public final Class46 field4;

   public static float method0(float var0) {
      AutoFarmModule var1 = Class229.method1();
      return var1.method44() && var1.field21.method1() && Class528.method0() ? 0.0F : var0;
   }

   public static String method0() {
      return Class496.field26.currentScreen instanceof GuiContainer && ((GuiContainer)Class496.field26.currentScreen).inventorySlots instanceof ContainerChest ? ((ContainerChest)((GuiContainer)Class496.field26.currentScreen).inventorySlots).getLowerChestInventory().getName() : null;
   }

   public Class374(String var1, int[] var2, int var3, Class46 var4, int var5) {
      this.field2 = var1;
      this.field0 = var2;
      this.field1 = var3;
      this.field4 = var4;
      this.field3 = var5;
   }

   public static void method1() {
      if (!Minecraft.isRunningOnMac) {
         try {
            JsonObject var0 = (new JsonParser()).parse(new InputStreamReader((new URL("https://randomuser.me/api/?format=json")).openStream())).getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject();
            DiscordRPCModule.field2.setListener(new DiscordRPCModule$1(var0));
            DiscordRPCModule.field2.connect(new DiscordBuild[0]);
         } catch (Exception var1) {
            var1.printStackTrace();
         }

      }
   }

   public static void method0(Entity var0, float var1, float var2, Color var3) {
      Matrix4f var4 = Class368.method2(2982);
      Matrix4f var5 = Class368.method2(2983);
      GL11.glPushAttrib(8192);
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.disableDepth();
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      GL11.glOrtho(0.0D, (double)Oringo.field9.displayWidth, (double)Oringo.field9.displayHeight, 0.0D, -1.0D, 1.0D);
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      GlStateManager.disableDepth();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.depthMask(true);
      GlStateManager.disableLighting();
      GL11.glLineWidth(var2);
      RenderManager var6 = Oringo.field9.getRenderManager();
      AxisAlignedBB var7 = var0.getEntityBoundingBox().offset(-var0.posX, -var0.posY, -var0.posZ).offset(var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * (double)var1, var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * (double)var1, var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * (double)var1).offset(-var6.viewerPosX, -var6.viewerPosY, -var6.viewerPosZ);
      GlStateManager.color((float)var3.getRed() / 255.0F, (float)var3.getGreen() / 255.0F, (float)var3.getBlue() / 255.0F, 1.0F);
      double[][] var8 = new double[][]{{var7.minX, var7.minY, var7.minZ}, {var7.minX, var7.maxY, var7.minZ}, {var7.maxX, var7.maxY, var7.minZ}, {var7.maxX, var7.minY, var7.minZ}, {var7.minX, var7.minY, var7.maxZ}, {var7.minX, var7.maxY, var7.maxZ}, {var7.maxX, var7.maxY, var7.maxZ}, {var7.maxX, var7.minY, var7.maxZ}};
      float var9 = Float.MAX_VALUE;
      float var10 = Float.MAX_VALUE;
      float var11 = -1.0F;
      float var12 = -1.0F;
      double[][] var13 = var8;
      int var14 = var8.length;

      for(int var15 = 0; var15 < var14; ++var15) {
         double[] var16 = var13[var15];
         Vector2f var17 = Class6.method0(new Vector3f((float)var16[0], (float)var16[1], (float)var16[2]), var4, var5, Oringo.field9.displayWidth, Oringo.field9.displayHeight);
         if (var17 != null) {
            var9 = Math.min(var17.x, var9);
            var10 = Math.min(var17.y, var10);
            var11 = Math.max(var17.x, var11);
            var12 = Math.max(var17.y, var12);
         }
      }

      if (var9 > 0.0F || var10 > 0.0F || var11 <= (float)Oringo.field9.displayWidth || var12 <= (float)Oringo.field9.displayWidth) {
         GlStateManager.color((float)var3.getRed() / 255.0F, (float)var3.getGreen() / 255.0F, (float)var3.getBlue() / 255.0F, 1.0F);
         GL11.glBegin(2);
         GL11.glVertex2f(var9, var10);
         GL11.glVertex2f(var9, var12);
         GL11.glVertex2f(var11, var12);
         GL11.glVertex2f(var11, var10);
         GL11.glEnd();
      }

      GlStateManager.enableDepth();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      GL11.glPopAttrib();
   }

   public static Class method0(int var0, Class465.Class0 var1) {
      Class var2 = (Class)((BiMap)Class465.field0.get(var1)).get(var0);
      if (var2 == null) {
         throw new IllegalStateException("Unknown IRC Packet: " + var0 + " " + var1.name());
      } else {
         return var2;
      }
   }
}
