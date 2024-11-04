package oringo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.authlib.exceptions.AuthenticationException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import map.Class173;
import map.Class208;
import map.Class23;
import map.Class374;
import map.Class465;
import me.oringo.Key;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class IRC extends WebSocketClient {
   private static final Logger field0 = Oringo.method0("Oringo-IRC");
   private final Thread field1 = new Thread(this::lambda$new$0);
   private final Class173 field2 = new Class173(this);
   private static final Minecraft field3 = Minecraft.getMinecraft();
   private static final Gson field4 = (new GsonBuilder()).create();

   private void lambda$new$1() {
      this.field1.interrupt();
      this.close();
   }

   public void method0(Class23 var1) {
      if (this.isOpen()) {
         try {
            String var2 = field4.toJson(var1);
            this.send((char)Class208.method0(var1.getClass(), Class465.Class0.field0) + var2);
         } catch (Exception var3) {
            field0.error("Error while serializing packet {}!", new Object[]{Class208.method0(var1.getClass(), Class465.Class0.field0), var3});
            this.close(1011, "Error while serializing packet C" + Class208.method0(var1.getClass(), Class465.Class0.field0) + "!");
         }

      }
   }

   public void onError(Exception var1) {
      field0.error("Exception thrown", var1);
   }

   public void onClose(int var1, String var2, boolean var3) {
      field0.info("IRC connection closed with code {} due to {}!", new Object[]{var1, var2});
   }

   public void connect() {
      this.addHeader("name", field3.getSession().getUsername());
      String var1 = Long.toString((new Random()).nextLong());
      this.addHeader("server", var1);

      try {
         field3.getSessionService().joinServer(field3.getSession().getProfile(), field3.getSession().getToken(), var1);
      } catch (AuthenticationException var3) {
      }

      super.connect();
   }

   public void onOpen(ServerHandshake var1) {
      field0.info("Successfully connected!");
   }

   private void lambda$new$0() {
      while(true) {
         try {
            if (this.isClosed()) {
               this.reconnectBlocking();
            }

            Thread.sleep(5000L);
         } catch (InterruptedException var2) {
         }
      }
   }

   public IRC() throws URISyntaxException, InterruptedException {
      super(new URI("ws://shiyu.moe:2052/"));
      this.addHeader("key", Key.getKey());
      this.addHeader("build_time", String.valueOf(1700673600755L));
      this.connectBlocking();
      this.field1.setDaemon(true);
      this.field1.start();
      Runtime.getRuntime().addShutdownHook(new Thread(this::lambda$new$1));
   }

   public void onMessage(String var1) {
      if (!var1.isEmpty()) {
         char var2 = var1.charAt(0);
         String var3 = var1.substring(1);

         try {
            Class var4 = Class374.method0(var2, Class465.Class0.field1);
            Class23 var5 = (Class23)field4.fromJson(var3, var4);
            var5.method0(this.field2);
         } catch (Exception var6) {
            field0.error("Error while deserializing packet {}!", new Object[]{Integer.valueOf(var2), var6});
            this.close(1011, String.format("Error while deserializing packet S%d", Integer.valueOf(var2)));
         }

      }
   }
}
