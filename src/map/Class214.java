package map;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;

public class Class214 {
   public boolean bJ_;
   public final List field1 = new ArrayList();
   public String field2;
   public String field3;
   public String bK_;
   public final String field0;

   public void l_(String var1) {
      this.field3 = var1;
   }

   public Class214(String var1) {
      this.field0 = var1;
   }

   public void method1(String var1) {
      this.field2 = var1;
   }

   public void method0(String var1) {
      this.bK_ = var1;
   }

   public void method0(Class214.Class1 var1) {
      this.field1.add(var1);
   }

   public void method0(boolean var1) {
      this.bJ_ = var1;
   }

   public void method0() throws IOException {
      if (this.field3 == null && this.field1.isEmpty()) {
         throw new IllegalArgumentException("Set content or add at least one EmbedObject");
      } else {
         Class214.Class0 var1 = new Class214.Class0((Class214$1)null);
         var1.method0("content", this.field3);
         var1.method0("username", this.bK_);
         var1.method0("avatar_url", this.field2);
         var1.method0("tts", this.bJ_);
         if (!this.field1.isEmpty()) {
            ArrayList var2 = new ArrayList();
            Iterator var3 = this.field1.iterator();

            while(var3.hasNext()) {
               Class214.Class1 var4 = (Class214.Class1)var3.next();
               Class214.Class0 var5 = new Class214.Class0((Class214$1)null);
               var5.method0("title", var4.method5());
               var5.method0("description", var4.method4());
               var5.method0("url", var4.method1());
               if (var4.method0() != null) {
                  Color var6 = var4.method0();
                  int var7 = var6.getRed();
                  var7 = (var7 << 8) + var6.getGreen();
                  var7 = (var7 << 8) + var6.getBlue();
                  var5.method0("color", var7);
               }

               Class214.Class1.Class3 var18 = var4.v_();
               Class214.Class1.Class1 var19 = var4.method3();
               Class214.Class1.Class0 var8 = var4.method6();
               Class214.Class1.Class4 var9 = var4.method2();
               List var10 = var4.method7();
               Class214.Class0 var11;
               if (var18 != null) {
                  var11 = new Class214.Class0((Class214$1)null);
                  var11.method0("text", Class214.Class1.Class3.access$100(var18));
                  var11.method0("icon_url", Class214.Class1.Class3.access$200(var18));
                  var5.method0("footer", var11);
               }

               if (var19 != null) {
                  var11 = new Class214.Class0((Class214$1)null);
                  var11.method0("url", Class214.Class1.Class1.field0(var19));
                  var5.method0("image", var11);
               }

               if (var8 != null) {
                  var11 = new Class214.Class0((Class214$1)null);
                  var11.method0("url", Class214.Class1.Class0.access$400(var8));
                  var5.method0("thumbnail", var11);
               }

               if (var9 != null) {
                  var11 = new Class214.Class0((Class214$1)null);
                  var11.method0("name", Class214.Class1.Class4.access$500(var9));
                  var11.method0("url", Class214.Class1.Class4.access$600(var9));
                  var11.method0("icon_url", Class214.Class1.Class4.access$700(var9));
                  var5.method0("author", var11);
               }

               ArrayList var20 = new ArrayList();
               Iterator var12 = var10.iterator();

               while(var12.hasNext()) {
                  Class214.Class1.Class2 var13 = (Class214.Class1.Class2)var12.next();
                  Class214.Class0 var14 = new Class214.Class0((Class214$1)null);
                  var14.method0("name", Class214.Class1.Class2.access$800(var13));
                  var14.method0("value", Class214.Class1.Class2.access$900(var13));
                  var14.method0("inline", Class214.Class1.Class2.access$1000(var13));
                  var20.add(var14);
               }

               var5.method0("fields", var20.toArray());
               var2.add(var5);
            }

            var1.method0("embeds", var2.toArray());
         }

         URL var15 = new URL(this.field0);
         HttpsURLConnection var16 = (HttpsURLConnection)var15.openConnection();
         var16.setConnectTimeout(150000);
         var16.addRequestProperty("Content-Type", "application/json");
         var16.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
         var16.setDoOutput(true);
         var16.setRequestMethod("POST");
         OutputStream var17 = var16.getOutputStream();
         var17.write(var1.toString().getBytes());
         var17.flush();
         var17.close();
         var16.getInputStream().close();
         var16.disconnect();
      }
   }

   private static class Class0 {
      private final HashMap field0;

      Class0(Class214$1 var1) {
         this();
      }

      private Class0() {
         this.field0 = new HashMap();
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Set var2 = this.field0.entrySet();
         var1.append("{");
         int var3 = 0;
         Iterator var4 = var2.iterator();

         while(var4.hasNext()) {
            Entry var5 = (Entry)var4.next();
            Object var6 = var5.getValue();
            var1.append(this.method0((String)var5.getKey())).append(":");
            if (var6 instanceof String) {
               var1.append(this.method0(String.valueOf(var6)));
            } else if (var6 instanceof Integer) {
               var1.append(Integer.valueOf(String.valueOf(var6)));
            } else if (var6 instanceof Boolean) {
               var1.append(var6);
            } else if (var6 instanceof Class214.Class0) {
               var1.append(var6);
            } else if (var6.getClass().isArray()) {
               var1.append("[");
               int var7 = Array.getLength(var6);

               for(int var8 = 0; var8 < var7; ++var8) {
                  var1.append(Array.get(var6, var8).toString()).append(var8 != var7 - 1 ? "," : "");
               }

               var1.append("]");
            }

            ++var3;
            var1.append(var3 == var2.size() ? "}" : ",");
         }

         return var1.toString();
      }

      private String method0(String var1) {
         return "\"" + var1 + "\"";
      }

      void method0(String var1, Object var2) {
         if (var2 != null) {
            this.field0.put(var1, var2);
         }

      }
   }

   public static class Class1 {
      private String field0;
      private final List field1 = new ArrayList();
      private Class214.Class1.Class0 field2;
      private Class214.Class1.Class1 field3;
      private Color field4;
      private Class214.Class1.Class4 field5;
      private String field6;
      private Class214.Class1.Class3 field7;
      private String field8;

      public Class214.Class1 method0(String var1, String var2, String var3) {
         this.field5 = new Class214.Class1.Class4(var1, var2, var3, (Class214$1)null);
         return this;
      }

      public Class214.Class1 method0(String var1) {
         this.field6 = var1;
         return this;
      }

      public Class214.Class1 method0(String var1, String var2, boolean var3) {
         this.field1.add(new Class214.Class1.Class2(var1, var2, var3, (Class214$1)null));
         return this;
      }

      public Class214.Class1.Class3 v_() {
         return this.field7;
      }

      public Class214.Class1 method1(String var1) {
         this.field8 = var1;
         return this;
      }

      public String method1() {
         return this.field6;
      }

      public Class214.Class1 method0(String var1, String var2) {
         this.field7 = new Class214.Class1.Class3(var1, var2, (Class214$1)null);
         return this;
      }

      public Class214.Class1.Class4 method2() {
         return this.field5;
      }

      public Class214.Class1 method2(String var1) {
         this.field3 = new Class214.Class1.Class1(var1, (Class214$1)null);
         return this;
      }

      public Class214.Class1.Class1 method3() {
         return this.field3;
      }

      public Class214.Class1 method0(Color var1) {
         this.field4 = var1;
         return this;
      }

      public Class214.Class1 method3(String var1) {
         this.field0 = var1;
         return this;
      }

      public Class214.Class1 method4(String var1) {
         this.field2 = new Class214.Class1.Class0(var1, (Class214$1)null);
         return this;
      }

      public String method4() {
         return this.field0;
      }

      public String method5() {
         return this.field8;
      }

      public Class214.Class1.Class0 method6() {
         return this.field2;
      }

      public List method7() {
         return this.field1;
      }

      public Color method0() {
         return this.field4;
      }

      private static class Class2 {
         private final boolean field0;
         private final String dk_;
         private final String field1;

         static String access$800(Class214.Class1.Class2 var0) {
            return var0.method45();
         }

         private String method1() {
            return this.field1;
         }

         static boolean access$1000(Class214.Class1.Class2 var0) {
            return var0.method2();
         }

         static String access$900(Class214.Class1.Class2 var0) {
            return var0.method1();
         }

         Class2(String var1, String var2, boolean var3, Class214$1 var4) {
            this(var1, var2, var3);
         }

         private String method45() {
            return this.dk_;
         }

         private Class2(String var1, String var2, boolean var3) {
            this.dk_ = var1;
            this.field1 = var2;
            this.field0 = var3;
         }

         private boolean method2() {
            return this.field0;
         }
      }

      private static class Class4 {
         private final String field0;
         private final String field1;
         private final String field2;

         static String access$500(Class214.Class1.Class4 var0) {
            return var0.method45();
         }

         Class4(String var1, String var2, String var3, Class214$1 var4) {
            this(var1, var2, var3);
         }

         private Class4(String var1, String var2, String var3) {
            this.field1 = var1;
            this.field2 = var2;
            this.field0 = var3;
         }

         static String access$600(Class214.Class1.Class4 var0) {
            return var0.method2();
         }

         private String method0() {
            return this.field0;
         }

         private String method45() {
            return this.field1;
         }

         private String method2() {
            return this.field2;
         }

         static String access$700(Class214.Class1.Class4 var0) {
            return var0.method0();
         }
      }

      private static class Class1 {
         private final String field0;

         private String method2() {
            return this.field0;
         }

         Class1(String var1, Class214$1 var2) {
            this(var1);
         }

         private Class1(String var1) {
            this.field0 = var1;
         }

         static String field0(Class214.Class1.Class1 var0) {
            return var0.method2();
         }
      }

      private static class Class0 {
         private String field0;

         static String access$400(Class214.Class1.Class0 var0) {
            return var0.method2();
         }

         private Class0(String var1) {
            this.field0 = var1;
         }

         Class0(String var1, Class214$1 var2) {
            this(var1);
         }

         private String method2() {
            return this.field0;
         }
      }

      private static class Class3 {
         private final String field0;
         private final String cy_;

         private Class3(String var1, String var2) {
            this.cy_ = var1;
            this.field0 = var2;
         }

         static String access$100(Class214.Class1.Class3 var0) {
            return var0.method1();
         }

         static String access$200(Class214.Class1.Class3 var0) {
            return var0.method0();
         }

         Class3(String var1, String var2, Class214$1 var3) {
            this(var1, var2);
         }

         private String method0() {
            return this.field0;
         }

         private String method1() {
            return this.cy_;
         }
      }
   }
}
