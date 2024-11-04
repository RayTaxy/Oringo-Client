package map;

import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;
import oringo.Oringo;
import oringo.module.Category;
import oringo.module.Module;

public class Class124 extends Class457 {
   public final Category field0;

   public boolean field1(Module var1) {
      return this.field3.getText().equals("+") ? var1.method44() : var1.d_().replaceAll(" ", "").toLowerCase().contains(this.field3.getText().replaceAll(" ", "").toLowerCase()) && var1.method37() == this.field0;
   }

   public Class124(Category var1) {
      super(var1.name, var1.method0(), Class442.field0);
      this.field0 = var1;
   }

   public List method0() {
      List var1 = Class362.method0(this::field1, this.field0 == Category.keybinds);
      var1.sort(Comparator.comparingDouble(Class124::field0));
      return var1;
   }

   public static Entity method0(EntityArmorStand var0) {
      return Oringo.field9.theWorld.getEntityByID(var0.getEntityId() - 1);
   }

   public static StringBuilder method0(StringBuilder var0, String var1) {
      return var0.append(var1);
   }

   public static double field0(Module var0) {
      return (double)(-Class311.field11.method0(var0.d_()));
   }

   public static Vector4f method0(Vector4f var0, Matrix4f var1) {
      return new Vector4f(var0.x * var1.m00 + var0.y * var1.m10 + var0.z * var1.m20 + var0.w * var1.m30, var0.x * var1.m01 + var0.y * var1.m11 + var0.z * var1.m21 + var0.w * var1.m31, var0.x * var1.m02 + var0.y * var1.m12 + var0.z * var1.m22 + var0.w * var1.m32, var0.x * var1.m03 + var0.y * var1.m13 + var0.z * var1.m23 + var0.w * var1.m33);
   }
}
