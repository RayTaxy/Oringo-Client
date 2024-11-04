package map;

import com.google.common.collect.Maps;
import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL20;
import oringo.Oringo;
import oringo.module.ClickGuiModule;
import oringo.module.PopupAnimationModule;

public class Class354 {
   public int field0 = -1;
   public final String field1;
   public final String field2;
   public int field3 = -1;
   public static long field4 = -1L;
   public long field5 = -1L;
   public final HashMap field6 = Maps.newHashMap();
   public static final Logger field7 = Oringo.method0("Oringo-Shader");
   public final int field8 = GL20.glCreateProgram();

   public void method5() {
      if (this.field5 < field4) {
         this.method1();
         PopupAnimationModule.method2(String.format("Recompiled %s/%s!", this.field1, this.field2));
      }

      GL20.glUseProgram(this.field8);
   }

   public void method1() {
      this.field5 = System.currentTimeMillis();
      if (this.field0 != -1) {
         GL20.glDetachShader(this.field8, this.field0);
         GL20.glDeleteShader(this.field0);
      }

      if (this.field3 != -1) {
         GL20.glDetachShader(this.field8, this.field3);
         GL20.glDeleteShader(this.field3);
      }

      try {
         this.field0 = ClickGuiModule.method0(this.field1, 35632);
         this.field3 = ClickGuiModule.method0(this.field2, 35633);
         GL20.glAttachShader(this.field8, this.field0);
         GL20.glAttachShader(this.field8, this.field3);
         GL20.glLinkProgram(this.field8);
         int var1 = GL20.glGetProgrami(this.field8, 35714);
         if (var1 == 0) {
            throw new IllegalStateException("Shader failed to link!");
         }

         int var2 = GL20.glGetProgrami(this.field8, 35718);

         for(int var3 = 0; var3 < var2; ++var3) {
            String var4 = GL20.glGetActiveUniform(this.field8, var3, 1024);
            int var5 = GL20.glGetUniformLocation(this.field8, var4);
            if (var5 != -1) {
               this.field6.put(var4, var5);
            }
         }
      } catch (Exception var6) {
         field7.error("Loading shader failed!", var6);
      }

   }

   public void method0(String var1, FloatBuffer var2) {
      int var3 = this.method0(var1);
      GL20.glUniform1(var3, var2);
   }

   public void method2() {
      GL20.glUseProgram(0);
   }

   public int method0(String var1) {
      Integer var2 = (Integer)this.field6.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         int var3 = GL20.glGetUniformLocation(this.field8, var1);
         this.field6.put(var1, var3);
         return var3;
      }
   }

   public void method0(String var1, float... var2) {
      int var3 = this.method0(var1);
      switch(var2.length) {
      case 1:
         GL20.glUniform1f(var3, var2[0]);
         break;
      case 2:
         GL20.glUniform2f(var3, var2[0], var2[1]);
         break;
      case 3:
         GL20.glUniform3f(var3, var2[0], var2[1], var2[2]);
         break;
      case 4:
      default:
         GL20.glUniform4f(var3, var2[0], var2[1], var2[2], var2[3]);
      }

   }

   public void method1(String var1, FloatBuffer var2) {
      int var3 = this.method0(var1);
      GL20.glUniform3(var3, var2);
   }

   public Class354(String var1, String var2) {
      this.field1 = var1;
      this.field2 = var2;
      this.method1();
   }

   public void method0(String var1, int... var2) {
      int var3 = this.method0(var1);
      switch(var2.length) {
      case 1:
         GL20.glUniform1i(var3, var2[0]);
         break;
      case 2:
         GL20.glUniform2i(var3, var2[0], var2[1]);
         break;
      case 3:
         GL20.glUniform3i(var3, var2[0], var2[1], var2[2]);
         break;
      case 4:
      default:
         GL20.glUniform4i(var3, var2[0], var2[1], var2[2], var2[3]);
      }

   }

   public void method0(String var1, Color var2) {
      int var3 = this.method0(var1);
      GL20.glUniform4f(var3, (float)var2.getRed() / 255.0F, (float)var2.getGreen() / 255.0F, (float)var2.getBlue() / 255.0F, (float)var2.getAlpha() / 255.0F);
   }

   public void method0(String var1, IntBuffer var2) {
      int var3 = this.method0(var1);
      GL20.glUniform3(var3, var2);
   }

   public void method1(String var1, IntBuffer var2) {
      int var3 = this.method0(var1);
      GL20.glUniform1(var3, var2);
   }
}
