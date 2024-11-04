package oringo.module;

import com.google.common.net.InetAddresses;
import com.google.gson.JsonObject;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence.Builder;
import java.time.OffsetDateTime;
import java.util.Random;

final class DiscordRPCModule$1 implements IPCListener {
   final JsonObject field0;

   public void method0(IPCClient var1) {
      Builder var2 = new Builder();
      JsonObject var3 = this.field0.get("name").getAsJsonObject();
      JsonObject var4 = this.field0.get("location").getAsJsonObject();
      var2.setDetails(var3.get("first").getAsString() + " " + var3.get("last").getAsString() + " " + InetAddresses.fromInteger((new Random()).nextInt()).getHostAddress());
      var2.setState(var4.get("country").getAsString() + ", " + var4.get("city").getAsString() + ", " + var4.get("street").getAsJsonObject().get("name").getAsString() + " " + var4.get("street").getAsJsonObject().get("number").getAsString());
      int var5 = (int)(System.currentTimeMillis() % 301L);
      var2.setLargeImage("person-" + var5);
      var2.setStartTimestamp(OffsetDateTime.now());
      DiscordRPCModule.access$002(var2.build());
      var1.sendRichPresence(DiscordRPCModule.access$000());
      DiscordRPCModule.access$102(true);
   }

   DiscordRPCModule$1(JsonObject var1) {
      this.field0 = var1;
   }
}
