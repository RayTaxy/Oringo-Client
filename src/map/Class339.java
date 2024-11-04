package map;

import com.google.gson.annotations.SerializedName;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Proxy.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Session;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;
import oringo.module.BrushModule;
import oringo.setting.StringSetting;

public class Class339 {
   @SerializedName("username")
   public String bK_;
   @SerializedName("enabled")
   public boolean field0 = false;
   @SerializedName("ip")
   public String field2;
   @SerializedName("port")
   public int field3;
   @SerializedName("type")
   public Type field4;
   @SerializedName("password")
   public String field5;

   public void method0(String var1) {
      this.bK_ = var1;
   }

   public int method0() {
      return this.field3;
   }

   public void method0(boolean var1) {
      this.field0 = var1;
   }

   public void method0(int var1) {
      this.field3 = var1;
   }

   public void method0(Type var1) {
      this.field4 = var1;
   }

   public String m_() {
      return this.field2;
   }

   public String method2() {
      return this.bK_;
   }

   public static void method0(BlockPos var0, Class12 var1, Class208 var2) {
      BrushModule.method7().method0(var0);
      if (var2 != null && var2.field3 != null) {
         ConcurrentHashMap var5 = var2.field3.field1;
         BlockPos var6 = var2.method0(var0.add(-var1.method1(), 0, -var1.method0()), true);
         var5.remove(var6);
      } else {
         int var3 = Class198.field0.method5();
         Map var4 = StringSetting.method0(var3);
         var4.remove(var0);
      }
   }

   public void method1(String var1) {
      this.field5 = var1;
   }

   public Class339() {
      this.field4 = Type.SOCKS;
      this.field3 = 0;
      this.field2 = "";
      this.bK_ = "";
      this.field5 = "";
   }

   public void method2(String var1) {
      this.field2 = var1;
   }

   public String method3() {
      return this.field5;
   }

   public Type method4() {
      return this.field4;
   }

   public boolean method1() {
      return this.field0;
   }

   public static HWND method6() {
      if (LWJGLUtil.getPlatform() != 3) {
         return null;
      } else {
         try {
            Object var0 = null;
            Method[] var1 = Display.class.getDeclaredMethods();
            Method[] var2 = var1;
            int var3 = var1.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               Method var5 = var2[var4];
               if (var5.getName().equals("getImplementation")) {
                  var5.setAccessible(true);
                  var0 = var5.invoke((Object)null);
                  break;
               }
            }

            if (var0 == null) {
               return null;
            } else {
               long var10 = -1L;
               Field[] var11 = var0.getClass().getDeclaredFields();
               Field[] var12 = var11;
               int var6 = var11.length;

               for(int var7 = 0; var7 < var6; ++var7) {
                  Field var8 = var12[var7];
                  if (var8.getName().equals("hwnd")) {
                     var8.setAccessible(true);
                     var10 = (Long)var8.get(var0);
                  }
               }

               if (var10 == -1L) {
                  return null;
               } else {
                  return new HWND(new Pointer(var10));
               }
            }
         } catch (InvocationTargetException | IllegalAccessException var9) {
            var9.printStackTrace();
            return null;
         }
      }
   }

   public static Session method0(Minecraft var0) {
      return var0.getSession();
   }
}
