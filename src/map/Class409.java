package map;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import net.minecraft.util.EnumChatFormatting;
import oringo.Oringo;
import oringo.command.PetCommand;
import oringo.command.PlusCommand;
import oringo.setting.EnumSetting;

public class Class409 extends EnumSetting {
   public static final HashMap field5 = new HashMap();

   static {
      EnumChatFormatting[] var0 = EnumChatFormatting.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         EnumChatFormatting var3 = var0[var2];
         if (var3.getColorIndex() != -1) {
            field5.put(PlusCommand.method0(var3), var3);
         }
      }

   }

   public EnumChatFormatting method5() {
      return (EnumChatFormatting)field5.get(this.method4());
   }

   public Class409(String var1, EnumChatFormatting var2) {
      super(var1, PlusCommand.method0(var2), PetCommand.method2());
   }

   public static boolean method0(File var0) {
      if (!var0.exists()) {
         return false;
      } else {
         try {
            BufferedReader var1 = Files.newBufferedReader(var0.toPath(), StandardCharsets.UTF_8);
            Throwable var2 = null;

            try {
               Class362.method5();
               JsonArray var3 = (new JsonParser()).parse(var1).getAsJsonArray();
               var3.forEach(Class376::lambda$loadConfig$0);
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
         } catch (Exception var14) {
            if (Oringo.cV_) {
               throw new IllegalStateException("Exception thrown while loading config!", var14);
            }

            Class376.field3.error("Exception thrown while loading config {}!", new Object[]{var0.getAbsolutePath(), var14});
            return false;
         }

         Class376.field3.info("Config loaded successfully!");
         return true;
      }
   }

   public static void method0(Class507 var0) {
      Class28.field1.remove(var0.field2);
      Class28.field3.remove(var0);
   }
}
