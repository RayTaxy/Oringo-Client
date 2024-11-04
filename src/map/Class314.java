package map;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Class314 implements Class23 {
   @SerializedName("remove")
   public Map field0;
   @SerializedName("add")
   public Map field1;

   public Class314(Map var1, Map var2) {
      this.field1 = var1;
      this.field0 = var2;
   }

   public void method0(Class173 var1) {
      var1.method0(this);
   }

   public Map method0() {
      return this.field0;
   }

   public Map method1() {
      return this.field1;
   }
}
