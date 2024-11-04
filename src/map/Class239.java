package map;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.module.BlockHitModule;
import sun.misc.SharedSecrets;

public class Class239 {
   public static boolean lambda$getHotbar$3(Class var0, ItemStack var1) {
      return var0.isAssignableFrom(var1.getItem().getClass());
   }

   public static void method0(Class322.Class0 var0) {
      String var1 = var0.cy_;
      FontRenderer var2 = Class322.field3.fontRendererObj;
      float var3 = 1.6F;
      float var4 = 0.016666668F * var3;
      GL11.glLoadMatrix(var0.field1);
      var2.drawString(var1, -var0.field2 / 2, 0, -1);
   }

   public static boolean lambda$getHotbar$1(String var0, ItemStack var1) {
      return var1.getDisplayName().toLowerCase().contains(var0.toLowerCase());
   }

   public static String method0(int var0) {
      StackTraceElement var1 = SharedSecrets.getJavaLangAccess().getStackTraceElement(new Exception(), var0);
      return var1.getClassName();
   }

   public static boolean lambda$getHotbarByID$2(String var0, ItemStack var1) {
      return var0.equals(BlockHitModule.method0(var1));
   }

   public static void lambda$doFromInv$0(boolean var0, ItemStack var1) {
      Oringo.field9.getNetHandler().addToSendQueue((Packet)(var0 ? new C0APacketAnimation() : new C08PacketPlayerBlockPlacement(var1)));
   }
}
