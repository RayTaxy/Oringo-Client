package map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import oringo.module.AutoQuizModule;
import oringo.setting.DoubleSetting;

public abstract class Class72 {
   private DoubleSetting field2;
   private float di_;
   protected static final Minecraft field0 = Minecraft.getMinecraft();
   private float dj_;
   private float field5 = 1.0F;
   private DoubleSetting field6;
   public static final int field1 = 30;

   protected static ScaledResolution i_() {
      return new ScaledResolution(field0);
   }

   public Class72 method0(float var1, float var2) {
      this.method1(var1).method0(var2);
      return this;
   }

   public void method4() {
      AutoQuizModule.method0(this.k_(), this.method15(), this.field5);
   }

   public DoubleSetting method5() {
      return this.field2;
   }

   public void method3() {
   }

   public float method7() {
      return this.dj_;
   }

   public Class72 method0(float var1) {
      this.field2.method4((double)Class163.method0(var1 / (float)i_().getScaledHeight(), 1.0F, 0.0F));
      return this;
   }

   public boolean j_() {
      return this.method0(method6(), t_());
   }

   protected abstract void method2();

   public boolean method0(int var1, int var2) {
      return (float)var1 > this.k_() && (float)var1 < this.k_() + this.method7() && (float)var2 > this.method15() && (float)var2 < this.method15() + this.method13();
   }

   public boolean method1() {
      return true;
   }

   public float k_() {
      return this.field6.method1() * (float)i_().getScaledWidth();
   }

   public Class72(DoubleSetting var1, DoubleSetting var2) {
      this.field6 = var1;
      this.field2 = var2;
   }

   public float method10() {
      return this.field5;
   }

   protected static int method6() {
      return Mouse.getX() * i_().getScaledWidth() / field0.displayWidth;
   }

   public DoubleSetting method12() {
      return this.field6;
   }

   public float method13() {
      return this.di_;
   }

   public Class72 method1(float var1) {
      this.field6.method4((double)Class163.method0(var1 / (float)i_().getScaledWidth(), 1.0F, 0.0F));
      return this;
   }

   public Class72 method1(float var1, float var2) {
      this.dj_ = var1;
      this.di_ = var2;
      return this;
   }

   protected static int t_() {
      int var0 = i_().getScaledHeight();
      return var0 - Mouse.getY() * var0 / field0.displayHeight - 1;
   }

   public float method15() {
      return this.field2.method1() * (float)i_().getScaledHeight();
   }

   public Class72 method2(float var1) {
      this.field5 = var1;
      return this;
   }
}
