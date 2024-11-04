package oringo.command;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import oringo.Oringo;
import oringo.module.HitboxesModule;
import oringo.module.PopupAnimationModule;

public class BindCommand extends Command {
   public static final File field0;
   public static ArrayList field1;

   public void method0(String[] var1) {
      if (var1.length == 0) {
         PopupAnimationModule.method2("Correct usage: .bind [command, command...] [click type]");
         PopupAnimationModule.method2("Binds a command to your current item");
         PopupAnimationModule.method2("Click types:");
         PopupAnimationModule.method2("0, 1 - Left click, 1 blocks the click");
         PopupAnimationModule.method2("2, 3 - Right click, 3 blocks the click");
         PopupAnimationModule.method2(".bind reset - Resets your item's binded commands");
      } else {
         UUID var2 = HitboxesModule.method0(field9.thePlayer.getHeldItem());
         if (var2 == null) {
            PopupAnimationModule.method2("You must hold a valid item!");
            return;
         }

         if (var1.length == 1) {
            if (var1[0].equalsIgnoreCase("reset")) {
               if (field1.removeIf(BindCommand::lambda$execute$0)) {
                  PopupAnimationModule.method2("Removed all command bind to this item!");
               } else {
                  PopupAnimationModule.method2("There are no commands bind to this item!");
               }

               this.n_();
            }
         } else {
            try {
               int var3 = Integer.parseInt(var1[var1.length - 1]);
               if (var3 < 0 || var3 > 3) {
                  throw new NumberFormatException();
               }

               int var4 = 0;
               StringBuilder var5 = new StringBuilder();
               String[] var6 = var1;
               int var7 = var1.length;

               for(int var8 = 0; var8 < var7; ++var8) {
                  String var9 = var6[var8];
                  ++var4;
                  if (var4 != var1.length) {
                     var5.append(var9).append(" ");
                  }
               }

               field1.add(new BindCommand.Class0(var2, var5.toString(), var3));
               PopupAnimationModule.method2("Command successfully binded!");
               this.n_();
            } catch (NumberFormatException var10) {
               PopupAnimationModule.method2("Invalid click type. Correct: [0-3]");
            }
         }
      }

   }

   public void n_() {
      try {
         FileWriter var1 = new FileWriter(field0);
         var1.write((new Gson()).toJson(field1));
         var1.close();
      } catch (IOException var2) {
         System.out.println("Error, while saving binds!");
      }

   }

   public String method1() {
      return null;
   }

   public static int method0(BlockPos var0, HashSet var1, BlockPos var2) {
      if (var1.size() > 15) {
         return -1;
      } else {
         var1.add(var0);
         int var3 = -1;
         EnumFacing[] var4 = EnumFacing.HORIZONTALS;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            EnumFacing var7 = var4[var6];
            BlockPos var8 = BloodSkipCommand.method0(var0, var7, var1);
            if (var8 != null) {
               if (var8.equals(var2)) {
                  return var1.size();
               }

               int var9 = method0(var8, new HashSet(var1), var2);
               if (var9 != -1 && (var9 < var3 || var3 == -1)) {
                  var3 = var9;
               }
            }
         }

         return var3;
      }
   }

   public boolean a_() {
      return true;
   }

   public static boolean lambda$execute$0(UUID var0, BindCommand.Class0 var1) {
      return var1.field0.equals(var0);
   }

   static {
      field0 = new File(Oringo.field5 + "binds.json");
      field1 = new ArrayList();
   }

   public BindCommand() {
      super("bind");
      if (field0.exists()) {
         try {
            Iterator var1 = (new JsonParser()).parse(new FileReader(field0)).getAsJsonArray().iterator();

            while(var1.hasNext()) {
               JsonElement var2 = (JsonElement)var1.next();
               field1.add((new Gson()).fromJson(var2, BindCommand.Class0.class));
            }
         } catch (FileNotFoundException var3) {
            System.out.println("Error, while loading binds!");
         }
      }

   }

   public static class Class0 {
      public UUID field0;
      public int field1;
      public String field2;

      public Class0(UUID var1, String var2, int var3) {
         this.field0 = var1;
         this.field2 = var2;
         this.field1 = var3;
      }
   }
}
