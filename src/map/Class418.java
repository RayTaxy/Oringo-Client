package map;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.util.CryptManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.PlusCommand;
import oringo.command.ReplyCommand;
import oringo.event.Class189;
import oringo.mixin.C01PacketEncryptionResponseAccessor;
import oringo.mixin.GuiConnectingAccessor;

public class Class418 {
   public static final Minecraft da_ = Minecraft.getMinecraft();
   public static Class398 field1 = null;
   public static String field0 = null;

   public static void lambda$onPacket$0(NetworkManager var0, Future var1) throws Exception {
      PlusCommand.method0(var0);
   }

   public static void method0(Object var0, int var1, Object var2) {
      try {
         Field var3 = var0.getClass().getDeclaredFields()[var1];
         var3.setAccessible(true);
         var3.set(var0, var2);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S01PacketEncryptionRequest) {
         String var2 = da_.getSession().getToken();
         if (!"2137".equals(var2)) {
            return;
         }

         S01PacketEncryptionRequest var3 = (S01PacketEncryptionRequest)var1.field0;
         byte[] var4 = var3.getPublicKey().getEncoded();
         field1 = ReplyCommand.method0(field0, var3.getServerId(), var4);
         if (field1 == null) {
            return;
         }

         C01PacketEncryptionResponse var5 = new C01PacketEncryptionResponse(CryptManager.createNewSharedKey(), var3.getPublicKey(), var3.getVerifyToken());
         ((C01PacketEncryptionResponseAccessor)var5).setSecretKeyEncrypted(field1.method0());
         if (da_.currentScreen instanceof GuiConnecting) {
            NetworkManager var6 = ((GuiConnectingAccessor)da_.currentScreen).getNetworkManager();
            var6.sendPacket(var5, Class418::lambda$onPacket$0, new GenericFutureListener[0]);
            var1.method9();
         }
      }

   }
}
