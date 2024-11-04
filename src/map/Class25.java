package map;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class270;
import oringo.event.Class533;

public class Class25 {
   public static final Minecraft field0 = Minecraft.getMinecraft();
   public static final ArrayList field1 = new ArrayList();

   @SubscribeEvent
   public void method0(Class270 var1) {
      field1.clear();
   }

   public static Class208 method0(int var0, int var1) {
      return var0 >= 0 && var0 < Class198.field1.length && var1 >= 0 && var1 < Class198.field1.length ? Class198.field1[var1][var0] : null;
   }

   public static void method0(double var0, float var2, float var3) {
      double var4 = (double)Oringo.field9.thePlayer.rotationYaw;
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GlStateManager.disableTexture2D();
      GlStateManager.disableCull();
      GlStateManager.disableLighting();
      GL11.glTranslated(Oringo.field9.thePlayer.lastTickPosX + (Oringo.field9.thePlayer.posX - Oringo.field9.thePlayer.lastTickPosX) * (double)var3 - Oringo.field9.getRenderManager().viewerPosX, Oringo.field9.thePlayer.lastTickPosY + (Oringo.field9.thePlayer.posY - Oringo.field9.thePlayer.lastTickPosY) * (double)var3 - Oringo.field9.getRenderManager().viewerPosY + (double)Oringo.field9.thePlayer.height / 2.0D, Oringo.field9.thePlayer.lastTickPosZ + (Oringo.field9.thePlayer.posZ - Oringo.field9.thePlayer.lastTickPosZ) * (double)var3 - Oringo.field9.getRenderManager().viewerPosZ);
      GL11.glLineWidth(2.5F);
      Color var6 = Color.white;
      GlStateManager.color((float)var6.getRed() / 255.0F, (float)var6.getGreen() / 255.0F, (float)var6.getBlue() / 255.0F, 1.0F);
      GL11.glBegin(2);
      byte var7 = 90;
      if (var2 != 360.0F) {
         GL11.glVertex3d(0.0D, 0.0D, 0.0D);
      }

      for(int var8 = 0; var8 <= var7; ++var8) {
         double var9 = (double)var8 / (double)var7 * (double)var2;
         double var11 = Math.toRadians(var9 - (double)var2 / 2.0D + var4 + 90.0D);
         GL11.glVertex3d(Math.cos(var11) * var0, 0.0D, Math.sin(var11) * var0);
      }

      if (var2 != 360.0F) {
         GL11.glVertex3d(0.0D, 0.0D, 0.0D);
      }

      GL11.glEnd();
      GL11.glPopMatrix();
      GlStateManager.enableCull();
      GlStateManager.resetColor();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GL11.glDisable(2848);
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase != Phase.START || field0.theWorld == null || !Class533.method2()) {
         ;
      }
   }

   public static Class351 method0(File var0) throws IOException {
      ArrayList var1 = new ArrayList();
      DataInputStream var2 = new DataInputStream(new GZIPInputStream(Files.newInputStream(var0.toPath())));
      int var3 = var2.readInt();

      for(int var4 = 0; var4 < var3; ++var4) {
         int var5 = var2.readInt();
         int var6 = var2.readInt();
         int var7 = var2.readInt();
         int var8 = var2.readInt();
         int var9 = var2.readInt();
         int var10 = var2.readInt();
         var1.add(new Class351.Class0(new BlockPos(var8, var9, var10), (ItemBlock)Item.getItemById(var5), var6, EnumFacing.values()[var7]));
      }

      var2.close();
      return new Class351(var1);
   }
}
