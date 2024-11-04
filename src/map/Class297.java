package map;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.EnumDyeColor;
import oringo.module.GemstoneESPModule;

public class Class297 implements Class23 {
   @SerializedName("hover_string")
   public String cm_;
   @SerializedName("message")
   public String field0;
   @SerializedName("copy_string")
   public String field2;
   @SerializedName("force")
   public boolean field3;

   public void method0(Class173 var1) {
      var1.field0(this);
   }

   public String B_() {
      return this.cm_;
   }

   public static void o_() {
      GemstoneESPModule.field0.clear();
      EnumDyeColor[] var0 = EnumDyeColor.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         EnumDyeColor var3 = var0[var2];
         GemstoneESPModule.field0.put(var3, new Class266());
      }

   }

   public String method0() {
      return this.field0;
   }

   public String method3() {
      return this.field2;
   }

   public boolean method4() {
      return this.field3;
   }
}
