package map;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import oringo.event.Class307;

public abstract class Class348 {
   private final ResourceLocation field0;
   private final Class442 field1;
   public float field10;
   public float field11;
   public float field12;
   private final String field2;
   protected static final Minecraft field13 = Minecraft.getMinecraft();
   public float field14;

   public abstract void method0(float var1);

   public Class442 method5() {
      return this.field1;
   }

   public abstract void q_();

   public ResourceLocation method4() {
      return this.field0;
   }

   protected static int method6() {
      return Mouse.getX() * Class7.field2.getScaledWidth() / field13.displayWidth;
   }

   public abstract void method0(int var1);

   public abstract void method1();

   public abstract void method1(int var1);

   public abstract boolean method0(char var1, int var2);

   protected static boolean method0(double var0, double var2, double var4, double var6) {
      int var8 = method6();
      int var9 = t_();
      return (double)var8 > var0 && (double)var8 < var0 + var4 && (double)var9 > var2 && (double)var9 < var2 + var6;
   }

   public abstract void method3();

   public Color method2() {
      return Class307.method0(Color.WHITE, Color.LIGHT_GRAY, this.field12);
   }

   public String method45() {
      return this.field2;
   }

   protected Class348(String var1, ResourceLocation var2, Class442 var3) {
      this.field2 = var1;
      this.field0 = var2;
      this.field1 = var3;
   }

   protected static int t_() {
      int var0 = Class7.field2.getScaledHeight();
      return var0 - Mouse.getY() * var0 / field13.displayHeight - 1;
   }
}
