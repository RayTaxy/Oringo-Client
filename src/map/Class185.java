package map;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import oringo.Oringo;
import oringo.module.ClickGuiModule;

public class Class185 extends Class348 {
   public float field0;
   public static final double field1 = 8.271999999999998D;
   public float field2;
   public float field3;
   public final ResourceLocation field4 = new ResourceLocation("oringoclient", "icons/changelog_notif.png");
   public static final SimpleDateFormat field5;
   public final List field6 = Lists.newArrayList();
   public float field7;
   public boolean field8;
   public static final double field9 = 6.345D;

   static {
      field5 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
   }

   public void q_() {
      this.field8 = false;
      this.field0 = this.field3 = this.field2 = 0.0F;
   }

   public static long lambda$new$0(Class185.Class0 var0) {
      return -var0.field2;
   }

   public static String method0(StringBuilder var0) {
      return var0.toString();
   }

   public Class185() {
      super("Changelog", new ResourceLocation("oringoclient", "icons/changelog.png"), Class442.field3);
      this.field8 = !ClickGuiModule.field32.method0("2.8.0");
      ClickGuiModule.field32.method1("2.8.0");
      Gson var1 = (new GsonBuilder()).setPrettyPrinting().create();
      Type var2 = (new Class185$1(this)).getType();

      try {
         InputStreamReader var3 = new InputStreamReader((InputStream)Objects.requireNonNull(Class185.class.getResourceAsStream("/assets/oringoclient/changelog.json")));
         Throwable var4 = null;

         try {
            List var5 = (List)var1.fromJson(var3, var2);
            this.field6.addAll(var5);
         } catch (Throwable var14) {
            var4 = var14;
            throw var14;
         } finally {
            if (var3 != null) {
               if (var4 != null) {
                  try {
                     var3.close();
                  } catch (Throwable var13) {
                     var4.addSuppressed(var13);
                  }
               } else {
                  var3.close();
               }
            }

         }
      } catch (NullPointerException | IOException var16) {
         if (Oringo.cV_) {
            var16.printStackTrace();
         }
      }

      this.field6.sort(Comparator.comparingLong(Class185::lambda$new$0));
      if (Oringo.cV_) {
         System.out.println(var1.toJson(this.field6, var2));
      }

   }

   public void method1() {
   }

   public void method0(int var1) {
   }

   public boolean method0(char var1, int var2) {
      return false;
   }

   public Color method2() {
      return this.field8 ? Color.RED : super.method2();
   }

   public void method0(float var1) {
      new ScaledResolution(field13);
      double var3 = Class7.i_ + 37.599999999999994D + 8.271999999999998D + (double)this.field3 + (double)((this.field0 - this.field3) * var1);
      double var5 = Class7.j_ + 94.0D + 6.345D;
      this.field7 = 0.0F;

      for(Iterator var7 = this.field6.iterator(); var7.hasNext(); this.field7 -= Class311.field11.method3()) {
         Class185.Class0 var8 = (Class185.Class0)var7.next();
         if (var3 > Class7.i_) {
            Class311.field8.method1(var8.field1 + " - " + field5.format(new Date(var8.field2)), (float)var5, (float)var3, -1);
         }

         var3 += (double)Class311.field8.method3();
         this.field7 -= Class311.field8.method3();
         String[] var9 = var8.field0;
         int var10 = var9.length;

         for(int var11 = 0; var11 < var10; ++var11) {
            String var12 = var9[var11];

            Class195 var15;
            for(Iterator var13 = Class311.field11.method0(var12, 363.31F).iterator(); var13.hasNext(); this.field7 -= var15.method3()) {
               String var14 = (String)var13.next();
               var15 = var14.isEmpty() ? Class311.field7 : (var14.matches("\\*\\*.*\\*\\*") ? Class311.field8 : Class311.field11);
               if (var3 > Class7.i_) {
                  var15.method1(var14.replaceAll("\\*\\*", "").replaceFirst("- ", "   "), (float)(var5 + 6.345D), (float)var3, -1);
               }

               var3 += (double)var15.method3();
            }
         }

         var3 += (double)Class311.field11.method3();
      }

      this.field7 = (float)((double)this.field7 + 259.1893333333333D);
      if (this.field7 > 0.0F) {
         this.field7 = 0.0F;
      }

      Class178.method0(Class7.j_, Class7.i_, Class7.j_ + 470.0D, Class7.i_ + 37.599999999999994D, Class7.field6.getRGB());
      Class311.field18.method1("ORINGO", (float)(Class7.j_ + 9.4D), (float)(Class7.i_ + (37.599999999999994D - (double)Class311.field8.method0()) / 2.0D - (double)Class311.field7.method0() / 2.0D - 1.0D), -1);
      Class311.field7.method1("v2.8.0", (float)(Class7.j_ + 9.4D), (float)(Class7.i_ + (37.599999999999994D - (double)Class311.field8.method0()) / 2.0D - (double)Class311.field7.method0() / 2.0D + 11.0D), -2236963);
      Class178.method0(Class7.j_, Class7.i_ + 37.599999999999994D - 1.0D, Class7.j_ + 470.0D, Class7.i_ + 37.599999999999994D, Color.DARK_GRAY.darker().getRGB());
   }

   public void method3() {
      double var1 = (double)Mouse.getDWheel() / 10.0D;
      if (method0(Class7.j_ + 94.0D, Class7.i_ + 37.599999999999994D, 376.0D, 275.7333333333333D)) {
         this.field2 = (float)((double)this.field2 + var1);
      }

      if ((double)this.field2 <= 0.0D && this.field7 <= 0.0F) {
         if (this.field2 < this.field7) {
            this.field2 = this.field7;
         }
      } else {
         this.field2 = 0.0F;
      }

      this.field3 = this.field0;
      this.field0 += (this.field2 - this.field0) / 2.0F;
   }

   public ResourceLocation method4() {
      return this.field8 ? this.field4 : super.method4();
   }

   public void method1(int var1) {
   }

   public static class Class0 {
      @SerializedName("lines")
      final String[] field0;
      @SerializedName("version")
      final String field1;
      @SerializedName("timestamp")
      final long field2;

      public Class0(String var1, String[] var2, long var3) {
         this.field1 = var1;
         this.field0 = var2;
         this.field2 = var3;
      }
   }
}
