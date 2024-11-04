package oringo.setting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import map.Class28;
import map.Class376;
import map.Class500;
import map.Class507;

public class BooleanSetting extends Setting {
   @Expose
   @SerializedName("value")
   public boolean field0;
   public final boolean field1;

   public static void method0(Class507 var0) {
      if (!Class28.field3.contains(var0)) {
         Class28.field3.add(var0);
         Class28.field1.put(var0.field2, var0);
      }
   }

   public BooleanSetting(String var1, boolean var2, Class500 var3, String var4) {
      super(var1, var3);
      this.field0 = var2;
      this.field61 = var4;
      this.field1 = var2;
   }

   public BooleanSetting(String var1, boolean var2) {
      this(var1, var2, (String)null);
   }

   public static void method0() {
      KeyBindSetting.method0(Class376.field0);
   }

   public boolean J_() {
      this.method0(!this.method1());
      return this.field0;
   }

   public BooleanSetting(String var1, boolean var2, String var3) {
      super(var1);
      this.field61 = var3;
      this.field0 = var2;
      this.field1 = var2;
   }

   public boolean method1() {
      return this.field0;
   }

   public BooleanSetting(String var1, boolean var2, Class500 var3) {
      this(var1, var2, var3, (String)null);
   }

   public void method0(boolean var1) {
      this.field0 = var1;
   }
}
