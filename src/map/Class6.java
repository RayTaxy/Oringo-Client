package map;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import oringo.Oringo;
import oringo.module.FrozenTreasureESPModule;

public class Class6 {
   public float f_;
   public float field1;
   public double g_;
   public float field0;
   public double field4;
   public boolean field5;
   public float field2;
   public double field7;

   public float method0() {
      return this.field1 + (this.field1 - this.f_) * FrozenTreasureESPModule.method5().renderPartialTicks;
   }

   public static boolean method0(Entity var0) {
      if (var0 instanceof EntityPlayer && ChatFormatting.stripFormatting(var0.getDisplayName().getUnformattedText()).length() >= 4) {
         if (Class496.field7) {
            return Class81.method0((EntityPlayer)var0);
         } else if (Oringo.field9.thePlayer.getDisplayName().getFormattedText().charAt(2) == 167 && var0.getDisplayName().getFormattedText().charAt(2) == 167) {
            return Oringo.field9.thePlayer.getDisplayName().getFormattedText().charAt(3) == var0.getDisplayName().getFormattedText().charAt(3);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static StringBuilder method0(StringBuilder var0, String var1) {
      return var0.append(var1);
   }

   public static Vector2f method0(Vector3f var0, Matrix4f var1, Matrix4f var2, int var3, int var4) {
      Vector4f var5 = Class124.method0(Class124.method0(new Vector4f(var0.x, var0.y, var0.z, 1.0F), var1), var2);
      Vector3f var6 = new Vector3f(var5.x / var5.w, var5.y / var5.w, var5.z / var5.w);
      float var7 = (var6.x + 1.0F) / 2.0F * (float)var3;
      float var8 = (1.0F - var6.y) / 2.0F * (float)var4;
      return (double)var6.z >= -1.0D && (double)var6.z <= 1.0D ? new Vector2f(var7, var8) : null;
   }

   public double method1() {
      return this.field7 + (this.g_ - this.field7) * (double)FrozenTreasureESPModule.method5().renderPartialTicks;
   }

   public float method2() {
      return this.field2 + (this.field0 - this.field2) * FrozenTreasureESPModule.method5().renderPartialTicks;
   }
}
