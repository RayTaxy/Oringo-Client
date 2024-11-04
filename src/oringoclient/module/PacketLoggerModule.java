package oringo.module;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import map.Class253;
import net.minecraft.entity.Entity;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import oringo.Oringo;
import oringo.command.MessageCommand;
import oringo.setting.ButtonSetting;

public class PacketLoggerModule extends Module {
   public static OutputStream field0 = null;
   public static final List aw_;
   public final ButtonSetting field2 = new ButtonSetting("Write", PacketLoggerModule::lambda$new$0);

   public static void lambda$new$0() {
      try {
         GhostBlocksModule.method3();
      } catch (Exception var1) {
         throw new RuntimeException(var1);
      }
   }

   public static boolean method0(IChatComponent var0) {
      ChatStyle var1 = var0.getChatStyle();
      if (var1 != null && var1.getChatClickEvent() != null) {
         String var2 = var1.getChatClickEvent().getValue();
         String var3 = var2.toLowerCase();
         if (var3.startsWith("/viewauction ")) {
            ScoreboardModule.v_(var2.split(" ")[1]);
            return true;
         } else if (var3.startsWith("/cofl openauctiongui ")) {
            Class253.field1 = System.currentTimeMillis() + 2000L;
            ScoreboardModule.v_(var2.split(" ")[2]);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void method4() {
      if (field58.theWorld == null) {
         this.method1(false);
      }

      try {
         field0 = new GZIPOutputStream(Files.newOutputStream((new File("packetLog.log")).toPath()));
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      Runtime.getRuntime().addShutdownHook(new Thread(this::b_));
   }

   public void b_() {
      if (field0 != null) {
         try {
            field0.close();
         } catch (IOException var3) {
            throw new RuntimeException(var3);
         }
      }

      field0 = null;

      try {
         GhostBlocksModule.method3();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public PacketLoggerModule() {
      super("Packet Logger", Category.other, SubCategory.other);
      this.method0(this.field2);
      BacteNotificationsModule.field3 = this;
   }

   public static float method5() {
      float var0 = KillAuraModule.field24 != null && KillAuraModule.field12.method1() ? MessageCommand.method0((Entity)KillAuraModule.field24).method5() : Oringo.field9.thePlayer.rotationYaw;
      if (Oringo.field9.thePlayer.moveForward < 0.0F) {
         var0 += 180.0F;
      }

      float var1 = 1.0F;
      if (Oringo.field9.thePlayer.moveForward < 0.0F) {
         var1 = -0.5F;
      } else if (Oringo.field9.thePlayer.moveForward > 0.0F) {
         var1 = 0.5F;
      }

      if (Oringo.field9.thePlayer.moveStrafing > 0.0F) {
         var0 -= 90.0F * var1;
      }

      if (Oringo.field9.thePlayer.moveStrafing < 0.0F) {
         var0 += 90.0F * var1;
      }

      return var0;
   }

   static {
      aw_ = Arrays.asList(EnumConnectionState.PLAY.getPacketId(EnumPacketDirection.CLIENTBOUND, new S3FPacketCustomPayload()), EnumConnectionState.PLAY.getPacketId(EnumPacketDirection.CLIENTBOUND, new S21PacketChunkData()), EnumConnectionState.PLAY.getPacketId(EnumPacketDirection.CLIENTBOUND, new S26PacketMapChunkBulk()));
   }

   public static boolean f_(String var0) {
      if (DojoHelperModule.field13.method0(0)) {
         return var0.startsWith("§c§l");
      } else if (DojoHelperModule.field13.method0(2)) {
         return var0.startsWith("§a§l");
      } else {
         return DojoHelperModule.field13.method0(1) ? var0.startsWith("§e§l") : false;
      }
   }
}
