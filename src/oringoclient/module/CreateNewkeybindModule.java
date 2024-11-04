package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import map.Class362;
import map.Class408;
import map.Class486;
import net.minecraft.item.ItemStack;

public class CreateNewkeybindModule extends Module {
   public void method4() {
      Class486 var1 = new Class486("Keybind " + System.currentTimeMillis());
      var1.method1(true);
      Class362.method0(var1);
      this.method1(false);
   }

   public CreateNewkeybindModule() {
      super("Create new keybind", Category.keybinds, SubCategory.keybinds);
   }

   public static boolean method0(ItemStack var0, String var1, boolean var2) {
      if (var0 == null) {
         return false;
      } else {
         Iterator var3 = Class408.method1(var0).iterator();

         String var5;
         do {
            if (!var3.hasNext()) {
               return false;
            }

            String var4 = (String)var3.next();
            var5 = var2 ? ChatFormatting.stripFormatting(var4) : var4;
         } while(!var5.contains(var1));

         return true;
      }
   }
}
