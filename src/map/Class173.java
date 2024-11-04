package map;

import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent.Action;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.apache.commons.codec.digest.DigestUtils;
import oringo.IRC;
import oringo.Oringo;
import oringo.module.CookieClickerModule;
import oringo.module.IRCModule;
import oringo.module.InventoryManagerModule;
import oringo.module.RenderChunkBoundsModule;

public class Class173 {
   public static final Minecraft field0 = Minecraft.getMinecraft();
   public final IRC field1;

   public static void lambda$onPlusData$0(Map var0, String var1, Class262 var2) {
      var0.remove(var1);
   }

   public void method0(Class314 var1) {
      Map var2 = Class265.field1;
      var2.putAll(var1.method1());
      var1.method0().forEach(Class173::lambda$onPlusData$0);
      if (field0.thePlayer != null) {
         Class262 var3 = (Class262)var2.get(DigestUtils.sha256Hex(field0.thePlayer.getUniqueID().toString().replaceAll("-", "")));
         if (var3 != null) {
            ((Class480)field0.thePlayer).method0(var3);
         }
      }

   }

   public static Class34 method0(Vec3 var0) {
      double var1 = Oringo.field9.thePlayer.getPositionEyes(1.0F).distanceTo(var0);
      double var3 = var0.xCoord - Oringo.field9.thePlayer.posX;
      double var5 = var0.zCoord - Oringo.field9.thePlayer.posZ;
      double var7 = Oringo.field9.thePlayer.posY + (double)Oringo.field9.thePlayer.getEyeHeight() - var0.yCoord;
      float var9 = (float)Math.toDegrees(Math.atan2(var5, var3)) - 90.0F;
      double var10 = (double)MathHelper.sqrt_double(var3 * var3 + var5 * var5);
      float var12 = (float)(-(Math.atan2(var7, var10) * 180.0D / 3.141592653589793D)) + (float)var1 * 0.11F;
      return new Class34(var9, Class163.method0(-var12, 90.0F, -90.0F));
   }

   public void method0(Class359 var1) {
      field0.crashed(new CrashReport(var1.method0(), new Throwable()));
   }

   public void method0(Class337 var1) {
      InventoryManagerModule.method0(var1.method0());
   }

   public void field0(Class297 var1) {
      if (field0.thePlayer != null) {
         if (Class362.field55.method44() || var1.method4()) {
            String var2 = var1.method0();
            Class253.Class0 var3 = new Class253.Class0(RenderChunkBoundsModule.method0(var2.replaceAll("§\\{nc}", IRCModule.field2.method5().toString()).replaceAll("§\\{mc}", IRCModule.az_.method5().toString()), IRCModule.az_.method5()), Class428.method17().getRGB());
            ChatStyle var4 = var3.getChatStyle();
            if (var1.B_() != null) {
               var4.setChatHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ChatComponentText(var1.B_())));
            }

            if (var1.method3() != null) {
               var4.setChatClickEvent(new ClickEvent(net.minecraft.event.ClickEvent.Action.SUGGEST_COMMAND, var1.method3()));
            }

            var3.setChatStyle(var4);
            CookieClickerModule.method0((Packet)(new S02PacketChat(var3)));
         }
      }
   }

   public Class173(IRC var1) {
      this.field1 = var1;
   }

   public void method0(Class328 var1) {
      if (field0.thePlayer != null) {
         field0.thePlayer.playSound(var1.method1(), var1.method2(), var1.x_());
      }
   }
}
