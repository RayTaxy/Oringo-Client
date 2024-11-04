package map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.net.Proxy.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.apache.logging.log4j.Logger;
import oringo.Oringo;

public class Class35 {
   public Class339 field0;
   public final File field1;
   public static final Gson field2 = (new GsonBuilder()).setPrettyPrinting().enableComplexMapKeySerialization().create();
   public static final Logger field3 = Oringo.method0("Oringo-Proxy");
   public HashMap field4 = new HashMap();

   public void method2(String var1) {
      this.method8().method2(var1);
      this.n_();
   }

   public String method2() {
      return this.method8().method2();
   }

   public Proxy l_() {
      if (this.method1() && !this.m_().isEmpty()) {
         try {
            return new Proxy(this.method4(), new InetSocketAddress(InetAddress.getByName(this.m_()), this.method0()));
         } catch (UnknownHostException var2) {
            field3.error("Exception thrown while creating proxy!", var2);
            return null;
         }
      } else {
         return null;
      }
   }

   public String m_() {
      return this.method8().m_();
   }

   public void n_() {
      try {
         Files.write(this.field1.toPath(), field2.toJson(this.field4).getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
      } catch (Throwable var2) {
         field3.error("Exception thrown while saving proxy!", var2);
      }

   }

   public void method0(int var1) {
      this.method8().method0(var1);
      this.n_();
   }

   public String method3() {
      return this.method8().method3();
   }

   public void method1(String var1) {
      this.method8().method1(var1);
      this.n_();
   }

   public boolean method1() {
      return this.method8().method1();
   }

   public Class35(File var1) {
      this.field1 = var1;
      this.method5();
      Authenticator.setDefault(new Class157(this));
   }

   public Type method4() {
      return this.method8().method4();
   }

   public void method0(Type var1) {
      this.method8().method0(var1);
      this.n_();
   }

   public static String method0(Session var0) {
      return var0.getToken();
   }

   public int method0() {
      return this.method8().method0();
   }

   public Class339 method8() {
      this.method5();
      return this.field0;
   }

   public void method5() {
      if (!this.field1.exists()) {
         this.field0 = new Class339();
         this.field4.put(Minecraft.getMinecraft().getSession().getPlayerID(), this.field0);
      } else {
         try {
            FileReader var1 = new FileReader(this.field1);
            Throwable var2 = null;

            try {
               this.field4 = (HashMap)field2.fromJson(var1, (new Class35$1(this)).getType());
            } catch (Throwable var12) {
               var2 = var12;
               throw var12;
            } finally {
               if (var1 != null) {
                  if (var2 != null) {
                     try {
                        var1.close();
                     } catch (Throwable var11) {
                        var2.addSuppressed(var11);
                     }
                  } else {
                     var1.close();
                  }
               }

            }
         } catch (Throwable var14) {
            field3.error("Exception thrown while loading proxy!", var14);
         }

         Class339 var15 = (Class339)this.field4.get(Minecraft.getMinecraft().getSession().getPlayerID());
         if (var15 != null) {
            this.field0 = var15;
         } else {
            this.field0 = new Class339();
            this.field4.put(Minecraft.getMinecraft().getSession().getPlayerID(), this.field0);
         }
      }
   }

   public void method0(String var1) {
      this.method8().method0(var1);
      this.n_();
   }

   public void method0(boolean var1) {
      this.method8().method0(var1);
      this.n_();
   }
}
