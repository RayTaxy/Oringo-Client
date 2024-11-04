package oringo.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import map.Class374;
import map.Class528;
import map.Class528$1;
import oringo.Oringo;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class GlintModule extends Module {
   public final EnumSetting field0 = new EnumSetting("Color Mode", "RGBA", new String[]{"RGBA", "Rainbow", "Theme"});
   public final ColorSetting bW_ = new ColorSetting("Color", new Color(0, 80, 255), true, this::lambda$new$0);
   public final DoubleSetting field2 = new DoubleSetting("Rainbow speed", 5.0D, 0.1D, 10.0D, 0.1D, this::lambda$new$1);
   public final DoubleSetting field3 = new DoubleSetting("Speed", 100.0D, 0.0D, 200.0D, 1.0D);

   public Boolean lambda$new$0() {
      return !this.field0.method0(0);
   }

   public Boolean lambda$new$1() {
      return !this.field0.method0(1);
   }

   public GlintModule() {
      super("Glint", Category.render, SubCategory.ui);
      this.method0(new Setting[]{this.field3, this.field0, this.bW_, this.field2});
   }

   public static void method7() {
      Class528.field0.clear();

      try {
         InputStreamReader var0 = new InputStreamReader((InputStream)Objects.requireNonNull(Class528.class.getResourceAsStream("/assets/oringoclient/rooms.json")));
         Throwable var1 = null;

         try {
            Gson var2 = (new GsonBuilder()).setPrettyPrinting().create();
            List var3 = (List)var2.fromJson(var0, (new Class528$1()).getType());
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
               Class374 var5 = (Class374)var4.next();
               int[] var6 = var5.field0;
               int var7 = var6.length;

               for(int var8 = 0; var8 < var7; ++var8) {
                  int var9 = var6[var8];
                  Class374 var10 = (Class374)Class528.field0.put(var9, var5);
                  if (var10 != null) {
                     Class528.field1.warn("Duplicate room info {}!", new Object[]{var9});
                  }
               }
            }
         } catch (Throwable var19) {
            var1 = var19;
            throw var19;
         } finally {
            if (var0 != null) {
               if (var1 != null) {
                  try {
                     var0.close();
                  } catch (Throwable var18) {
                     var1.addSuppressed(var18);
                  }
               } else {
                  var0.close();
               }
            }

         }
      } catch (NullPointerException | IOException var21) {
         if (Oringo.cV_) {
            var21.printStackTrace();
         }
      }

   }
}
