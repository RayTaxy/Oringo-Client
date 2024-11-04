package map;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovementInput;

public enum Class179 {
   private final int field4;
   field0(-1, 0),
   field1(0, -1);

   private final int field5;
   private static final Class179[] field6 = new Class179[]{field2, field0, field3, field1};
   field2(1, 0),
   field3(0, 1);

   public static Class179 method0(MovementInput var0) {
      Class179[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Class179 var4 = var1[var3];
         if ((float)var4.field5 == var0.moveStrafe && (float)var4.field4 == var0.moveForward) {
            return var4;
         }
      }

      return null;
   }

   private Class179(int var3, int var4) {
      this.field4 = var3;
      this.field5 = var4;
   }

   public MovementInput method0() {
      MovementInput var1 = new MovementInput();
      var1.moveForward = (float)this.field4;
      var1.moveStrafe = (float)this.field5;
      return var1;
   }

   public EnumFacing method0(EnumFacing var1) {
      // $FF: Couldn't be decompiled
   }
}
