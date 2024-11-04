package oringo.setting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import map.Class1;
import map.Class12;
import map.Class208;
import map.Class267;
import map.Class500;
import net.minecraft.util.BlockPos;
import oringo.module.FragHelperModule;
import oringo.module.LividFinderModule;

public class Setting {
   public Class500 field8;
   public String field61;
   @Expose
   @SerializedName("name")
   public String cW_;

   public Setting method0(Class500 var1) {
      this.field8 = var1;
      return this;
   }

   public static Class1 method1(BlockPos var0) {
      Class12 var1 = FragHelperModule.method0(var0.getX(), var0.getZ());
      Class208 var2 = LividFinderModule.method0(var1.method1(), var1.method0());
      return Class267.method0(var0, var1, var2);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return var1 != null && this.getClass() == var1.getClass() ? this.cW_.equals(((Setting)var1).cW_) : false;
      }
   }

   public Setting(String var1, Class500 var2) {
      this.cW_ = var1;
      this.field8 = var2;
   }

   public Setting method2(String var1) {
      this.field61 = var1;
      return this;
   }

   public Setting(String var1) {
      this(var1, (Class500)null);
   }

   public boolean g_() {
      return this.field8 != null && (Boolean)this.field8.get();
   }
}
