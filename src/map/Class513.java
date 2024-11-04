package map;

import com.mojang.authlib.GameProfile;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.client.Minecraft;
import oringo.module.AntiNickerModule;
import oringo.module.KillInsultsModule;

public class Class513 {
   public static final Minecraft field0 = Minecraft.getMinecraft();

   public static void method0(String var0, String var1) {
      if (KillInsultsModule.cZ_.a_(500L) && !KillInsultsModule.field2.isEmpty()) {
         ++KillInsultsModule.field0;
         KillInsultsModule.field0 %= KillInsultsModule.field2.size();
         String var2 = (String)KillInsultsModule.field2.get(KillInsultsModule.field0);
         if (var2.contains("%s")) {
            var2 = var2.replaceAll("%s", var0);
         }

         KillInsultsModule.field58.thePlayer.sendChatMessage(var1 + var2);
         KillInsultsModule.cZ_.o_();
      }

   }

   public static String method0(GameProfile var0) {
      AtomicReference var1 = new AtomicReference("");
      var0.getProperties().entries().forEach(AntiNickerModule::lambda$getRealName$0);
      return (String)var1.get();
   }

   public static InputStream method0(HttpURLConnection var0) {
      return var0.getInputStream();
   }
}
