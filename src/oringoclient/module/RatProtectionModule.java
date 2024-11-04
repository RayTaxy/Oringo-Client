package oringo.module;

import com.google.gson.Gson;
import com.mojang.authlib.HttpAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.request.JoinMinecraftServerRequest;
import com.mojang.util.UUIDTypeAdapter;
import java.io.IOException;
import java.net.Proxy;
import java.util.Random;
import java.util.UUID;
import map.Class447;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.setting.BooleanSetting;

public class RatProtectionModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Only when connected", true, "Will only block when you are connected to a server");
   public final Class447 bZ_ = new Class447();

   public RatProtectionModule() {
      super("Rat Protection", Category.other, SubCategory.other, "Blocks people from joining minecraft servers on your account\n\nWARNING: This does not kick people already connected to servers on your account!");
      this.method0(this.field0);
   }

   public void lambda$onTick$0() {
      String var1 = String.valueOf((new Random()).nextInt());

      try {
         String var2 = this.method0(field58.getSession().getToken(), UUIDTypeAdapter.fromString(field58.getSession().getPlayerID()), var1);
         if (!var2.isEmpty()) {
            if (var2.startsWith("<")) {
               this.bZ_.method0(-5000L);
            }

            return;
         }

         this.bZ_.method0(300L);
      } catch (Exception var3) {
      }

   }

   public String method0(String var1, UUID var2, String var3) throws IOException {
      YggdrasilAuthenticationService var4 = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
      JoinMinecraftServerRequest var5 = new JoinMinecraftServerRequest();
      var5.accessToken = var1;
      var5.selectedProfile = var2;
      var5.serverId = var3;
      return var4.performPostRequest(HttpAuthenticationService.constantURL("https://sessionserver.mojang.com/session/minecraft/join"), (new Gson()).toJson(var5), "application/json");
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (field58.theWorld != null || !this.field0.method1()) {
         if (this.bZ_.method0(1000L, true)) {
            (new Thread(this::lambda$onTick$0)).start();
         }

      }
   }
}
