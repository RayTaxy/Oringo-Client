package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import oringo.Oringo;
import oringo.module.KillInsultsModule;
import oringo.module.Module;
import oringo.module.SubCategory;
import oringo.setting.Setting;

public class Class453 extends Class457 {
   public final SubCategory field0;

   public static void method9() {
      KillInsultsModule.field2.clear();

      try {
         File var0 = new File(Oringo.field5 + "insults.txt");
         if (!var0.exists()) {
            var0.createNewFile();
            return;
         }

         BufferedReader var1 = new BufferedReader(new FileReader(var0));

         String var2;
         while((var2 = var1.readLine()) != null) {
            KillInsultsModule.field2.add(var2);
         }

         var1.close();
      } catch (Exception var3) {
         KillInsultsModule.method2("Unable to load insults");
      }

   }

   public static double field0(Module var0) {
      return (double)(-Class311.field11.method0(var0.d_()));
   }

   public static IBlockState method0(BlockPos var0, byte[] var1) {
      int var2 = (var0.getY() << 8 | var0.getZ() << 4 | var0.getX()) * 2;
      return var1.length <= var2 ? Blocks.air.getDefaultState() : (IBlockState)Block.BLOCK_STATE_IDS.getByValue(var1[var2]);
   }

   public boolean field1(Module var1) {
      if (this.field3.getText().isEmpty()) {
         return var1.method39() == this.field0;
      } else {
         String var2 = this.field3.getText().toLowerCase().replaceAll(" ", "");
         if (var1.d_().replaceAll(" ", "").toLowerCase(Locale.ENGLISH).contains(var2)) {
            return true;
         } else {
            Iterator var3 = var1.field56.iterator();
            if (var3.hasNext()) {
               Setting var4 = (Setting)var3.next();
               String var5 = var4.cW_.toLowerCase(Locale.ENGLISH).replaceAll(" ", "");
               return var5.contains(var2);
            } else {
               return false;
            }
         }
      }
   }

   public static void method0(HttpURLConnection var0, boolean var1) {
      var0.setDoOutput(var1);
   }

   public List method0() {
      List var1 = Class362.method0(this::field1, this.field0 == SubCategory.keybinds);
      var1.sort(Comparator.comparingDouble(Class453::field0));
      return var1;
   }

   public Class453(SubCategory var1) {
      super(var1.method0(), (ResourceLocation)null, var1.u_());
      this.field0 = var1;
   }
}
