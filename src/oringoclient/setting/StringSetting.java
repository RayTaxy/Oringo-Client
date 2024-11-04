package oringo.setting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import map.Class262;
import map.Class28;
import map.Class500;

public class StringSetting extends Setting {
   public final String field0;
   @Expose
   @SerializedName("value")
   public String field1;
   public final int field2;

   public StringSetting(String var1, String var2) {
      this(var1, var2, -1);
   }

   public boolean method0(String var1) {
      return this.field1.equals(var1);
   }

   public boolean F_() {
      return this.field1.isEmpty();
   }

   public void method1(String var1) {
      var1 = Class262.method0(var1);
      this.field1 = var1;
      if (this.field1.length() > this.field2 && this.field2 > 0) {
         this.field1 = this.field1.substring(0, this.field2 - 1);
      }

   }

   public StringSetting(String var1, String var2, int var3, Class500 var4) {
      super(var1, var4);
      this.field1 = var2;
      this.field0 = var2;
      this.field2 = var3;
   }

   public StringSetting(String var1, int var2) {
      this(var1, "", var2);
   }

   public StringSetting(String var1, String var2, int var3) {
      super(var1);
      this.field1 = var2;
      this.field0 = var2;
      this.field2 = var3;
   }

   public static Map method0(int var0) {
      while(var0 >= Class28.field2.size()) {
         Class28.field2.add(new ConcurrentHashMap());
      }

      return (Map)Class28.field2.get(var0);
   }

   public StringSetting(String var1, String var2, Class500 var3) {
      this(var1, var2, -1, var3);
   }

   public String method1() {
      return this.field1;
   }

   public StringSetting(String var1) {
      this(var1, "", -1);
   }

   public String method2() {
      return this.field1.toLowerCase(Locale.ENGLISH);
   }

   public String method3() {
      return this.field1.toUpperCase(Locale.ENGLISH);
   }
}
