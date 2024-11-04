package map;

import com.google.gson.annotations.SerializedName;
import oringo.Oringo;

public class Class256 implements Class23 {
   @SerializedName("server_id")
   public String field0;
   @SerializedName("world_time")
   public long field1;

   public Class256(String var1, long var2) {
      this.field0 = var1;
      this.field1 = var2;
   }

   public void method0(Class173 var1) {
   }

   public String method0() {
      return this.field0;
   }

   public static Class34 method1() {
      return new Class34(Oringo.field9.thePlayer.rotationYaw, Oringo.field9.thePlayer.rotationPitch);
   }

   public long method2() {
      return this.field1;
   }

   public Class256() {
   }
}
