package oringo.event;

import com.google.gson.JsonArray;

public class Class410 extends Event {
   public final JsonArray field0;

   public JsonArray method0() {
      return this.field0;
   }

   public Class410(JsonArray var1) {
      this.field0 = var1;
   }

   public static class Class1 extends Class410 {
      public Class1(JsonArray var1) {
         super(var1);
      }
   }

   public static class Class0 extends Class410 {
      public Class0(JsonArray var1) {
         super(var1);
      }
   }
}
