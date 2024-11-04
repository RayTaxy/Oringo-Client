package map;

import net.minecraft.util.ResourceLocation;

public enum Class432 {
   field0(new ResourceLocation("oringoclient", "checkmark.png"));

   private static final Class432[] field6 = new Class432[]{field2, field4, field5, field0, field1};
   field1(new ResourceLocation("oringoclient", "cross.png")),
   field2;

   ResourceLocation field3;
   field4,
   field5(new ResourceLocation("oringoclient", "checkmark.png"));

   private Class432() {
   }

   public ResourceLocation method0() {
      return this.field3;
   }

   private Class432(ResourceLocation var3) {
      this.field3 = var3;
   }
}
