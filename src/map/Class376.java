package map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockWall;
import net.minecraft.init.Blocks;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.module.Module;
import oringo.setting.BooleanSetting;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class Class376 {
   public static final File field0;
   public static final Gson field1;
   public static final String field2;
   public static final Logger field3;

   public static void lambda$loadConfig$0(JsonElement var0) {
      JsonObject var1 = var0.getAsJsonObject();
      Class376.Class1 var2 = (Class376.Class1)field1.fromJson(var1, Class376.Class1.class);
      Iterator var3 = Class362.method0().iterator();

      Module var4;
      do {
         if (!var3.hasNext()) {
            if (var2.cW_.startsWith("Keybind ")) {
               Class486 var5 = new Class486(var2.cW_);
               ColorSetting.field0(var5, var2);
               var5.a_(var1);
               Class362.method0(var5);
            }

            return;
         }

         var4 = (Module)var3.next();
      } while(!var2.cW_.equals(var4.cW_));

      ColorSetting.field0(var4, var2);
      var4.a_(var1);
   }

   public static boolean method0(Block var0) {
      return var0 instanceof BlockStairs || var0 instanceof BlockFence || var0 instanceof BlockFenceGate || var0 instanceof BlockWall || var0 == Blocks.hopper || var0 instanceof BlockSkull;
   }

   public static double method0(double var0, double var2, double var4) {
      var0 = Math.max(var2, var0);
      var0 = Math.min(var4, var0);
      return var0;
   }

   public static boolean method0(String var0) {
      Boolean var1 = (Boolean)Class82.field3.get(var0);
      if (var1 != null) {
         return var1;
      } else if (var0.isEmpty()) {
         return true;
      } else {
         String var2 = var0.toLowerCase(Locale.ENGLISH);
         Iterator var3 = Class82.field2.iterator();

         String var4;
         do {
            if (!var3.hasNext()) {
               Class82.field3.put(var0, false);
               return false;
            }

            var4 = (String)var3.next();
         } while(!var4.contains(var2));

         Class82.field3.put(var0, true);
         return true;
      }
   }

   public static void method0() {
      Class91.ap_.bindFramebuffer(true);
      if (Class91.field3 == -1) {
         GL11.glClear(16640);
      }

      Class91.field3 = Oringo.field9.getFramebuffer().depthBuffer;
   }

   static {
      field2 = Oringo.field5 + "configs/";
      field0 = new File(Oringo.field5 + "OringoClient.json");
      field1 = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
      field3 = Oringo.method0("Oringo-Config");
   }

   private static class Class0 {
      @SerializedName("name")
      public String cW_;
      @SerializedName("value")
      public Object field1;

      public Class0(String var1, Object var2) {
         this.cW_ = var1;
         this.field1 = var2;
      }
   }

   private static class Class1 {
      @SerializedName("name")
      public String cW_;
      @SerializedName("toggled")
      private boolean field0;
      @SerializedName("keyCode")
      protected int field57;
      @SerializedName("settings")
      public Class376.Class0[] field2;

      static boolean field0(Class376.Class1 var0) {
         return var0.field0;
      }

      public Class1(Module var1) {
         this.cW_ = var1.cW_;
         this.field0 = var1.method44();
         this.field57 = var1.method46();
         ArrayList var2 = new ArrayList();

         Class376.Class0 var5;
         for(Iterator var3 = var1.field56.iterator(); var3.hasNext(); var2.add(var5)) {
            Setting var4 = (Setting)var3.next();
            if (var4 == null) {
               throw new IllegalStateException(String.format("Setting in %s is null!", var1.method45()));
            }

            var5 = new Class376.Class0(var4.cW_, (Object)null);
            if (var4 instanceof BooleanSetting) {
               var5.field1 = ((BooleanSetting)var4).method1();
            } else if (var4 instanceof EnumSetting) {
               var5.field1 = ((EnumSetting)var4).method4();
            } else if (var4 instanceof DoubleSetting) {
               var5.field1 = ((DoubleSetting)var4).method0();
            } else if (var4 instanceof StringSetting) {
               var5.field1 = ((StringSetting)var4).method1();
            } else if (var4 instanceof ColorSetting) {
               var5.field1 = ((ColorSetting)var4).method8();
            }
         }

         this.field2 = (Class376.Class0[])var2.toArray(new Class376.Class0[0]);
      }
   }
}
