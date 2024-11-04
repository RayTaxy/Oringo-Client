package map;

import com.google.gson.annotations.SerializedName;
import oringo.Oringo;
import oringo.mixin.EntityPlayerSPAccessor;

public class Class175 implements Class23 {
   @SerializedName("message")
   public String field0;
   @SerializedName("name")
   public String cW_;

   public static Class34 method0() {
      return new Class34(((EntityPlayerSPAccessor)Oringo.field9.thePlayer).getLastReportedYaw(), ((EntityPlayerSPAccessor)Oringo.field9.thePlayer).getLastReportedPitch());
   }

   public Class175(String var1, String var2) {
      this.cW_ = var1;
      this.field0 = var2;
   }

   public void method0(Class173 var1) {
   }
}
