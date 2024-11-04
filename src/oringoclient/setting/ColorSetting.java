package oringo.setting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.awt.Color;
import java.util.Iterator;
import map.Class1;
import map.Class376;
import map.Class500;
import net.minecraft.util.BlockPos;
import oringo.Oringo;
import oringo.module.Module;
import oringo.module.PestESPModule;

public class ColorSetting extends Setting {
   public float field0;
   @Expose
   @SerializedName("value")
   public int br_;
   public float field2;
   public final boolean field3;
   public float field4;
   public final int field5;
   public int field6;

   public ColorSetting(String var1, int var2, boolean var3, Class500 var4) {
      super(var1, var4);
      this.field6 = 255;
      Color var5 = new Color(var2, var3);
      this.field3 = var3;
      float[] var6 = Color.RGBtoHSB(var5.getRed(), var5.getGreen(), var5.getBlue(), (float[])null);
      this.method0(var6[0], var6[1], var6[2], var5.getAlpha());
      this.field5 = var2;
   }

   public float method0() {
      return this.field4;
   }

   public void method0(int var1) {
      Color var2 = new Color(var1);
      float[] var3 = new float[3];
      Color.RGBtoHSB(var2.getRed(), var2.getGreen(), var2.getBlue(), var3);
      this.method0(var3[0], var3[1], var3[2], var2.getAlpha());
   }

   public static void method0(BlockPos var0, String var1) {
      Class1 var2 = Setting.method1(var0);
      var2.field2 = var1;
      PestESPModule.n_();
   }

   public float method1() {
      return this.field2;
   }

   public int method2() {
      return this.method8() >> 8 & 255;
   }

   public int method3() {
      return this.method8() & 255;
   }

   public int method4() {
      return this.field6;
   }

   public float method5() {
      return this.field0;
   }

   public ColorSetting(String var1, Color var2, boolean var3) {
      this(var1, var2, var3, (Class500)null);
   }

   public void method0(float var1, float var2, float var3) {
      this.method0(var1, var2, var3, this.field6);
   }

   public void method0(float var1, float var2, float var3, int var4) {
      this.br_ = Color.HSBtoRGB(var1, var2, var3);
      this.field0 = var1;
      this.field4 = var2;
      this.field2 = var3;
      if (this.field3) {
         this.field6 = var4;
         this.br_ &= 16777215;
         this.br_ |= (var4 & 255) << 24;
      }

   }

   public ColorSetting(String var1, Color var2, boolean var3, Class500 var4) {
      this(var1, var2.getRGB(), var3, var4);
   }

   public Color method17() {
      return new Color(this.br_, this.field3);
   }

   public int method7() {
      return this.method8() >> 16 & 255;
   }

   public ColorSetting(String var1, int var2, boolean var3) {
      this(var1, var2, var3, (Class500)null);
   }

   public int method8() {
      return this.br_;
   }

   public boolean method9() {
      return this.field3;
   }

   public static void field0(Module var0, Class376.Class1 var1) {
      try {
         var0.method1(Class376.Class1.field0(var1));
      } catch (Exception var11) {
         if (Oringo.cV_) {
            throw var11;
         }

         Class376.field3.error("Exception thrown while enabling module {}!", new Object[]{var0.method45(), var11});
      }

      var0.method3(var1.field57);
      Iterator var2 = var0.field56.iterator();

      while(var2.hasNext()) {
         Setting var3 = (Setting)var2.next();
         Class376.Class0[] var4 = var1.field2;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            Class376.Class0 var7 = var4[var6];
            if (var3.cW_.equals(var7.cW_)) {
               try {
                  if (var3 instanceof BooleanSetting) {
                     ((BooleanSetting)var3).method0((Boolean)var7.field1);
                  } else if (var3 instanceof EnumSetting) {
                     ((EnumSetting)var3).d_((String)var7.field1);
                  } else if (var3 instanceof DoubleSetting) {
                     ((DoubleSetting)var3).method4((Double)var7.field1);
                  } else if (var3 instanceof StringSetting) {
                     ((StringSetting)var3).method1((String)var7.field1);
                  } else if (var3 instanceof ColorSetting) {
                     Color var8 = new Color(((Double)var7.field1).intValue());
                     float[] var9 = Color.RGBtoHSB(var8.getRed(), var8.getGreen(), var8.getBlue(), (float[])null);
                     ((ColorSetting)var3).method0(var9[0], var9[1], var9[2], var8.getAlpha());
                  }
               } catch (Exception var10) {
                  if (Oringo.cV_) {
                     throw var10;
                  }

                  Class376.field3.error("Exception thrown while loading setting {} in {}!", new Object[]{var3.cW_, var0.method45(), var10});
               }
            }
         }
      }

   }
}
