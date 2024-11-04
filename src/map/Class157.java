package map;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import java.lang.reflect.Field;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Session;
import org.lwjgl.LWJGLUtil;
import oringo.setting.StringSetting;

public class Class157 extends Authenticator {
   public final Class35 field0;

   public static void method0(Session var0, String var1) {
      try {
         Field var2 = Minecraft.class.getDeclaredField(var1);
         var2.setAccessible(true);
         var2.set(Minecraft.getMinecraft(), var0);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public PasswordAuthentication getPasswordAuthentication() {
      if (!"SOCKS authentication".equals(this.getRequestingPrompt())) {
         return null;
      } else {
         String var1 = this.getRequestingHost();
         if (var1 != null && var1.equals(this.field0.m_()) && this.getRequestingPort() == this.field0.method0()) {
            String var2 = this.field0.method2();
            String var3 = this.field0.method3();
            return var2.isEmpty() ? null : new PasswordAuthentication(var2, var3.toCharArray());
         } else {
            return null;
         }
      }
   }

   public static void method0() {
      if (LWJGLUtil.getPlatform() == 3) {
         HWND var0 = Class339.method6();
         if (var0 != null) {
            User32.INSTANCE.ShowWindow(var0, 2);
            User32.INSTANCE.ShowWindow(var0, 3);
            User32.INSTANCE.SetForegroundWindow(var0);
         }
      }
   }

   public Class157(Class35 var1) {
      this.field0 = var1;
   }

   public static Class1 method0(BlockPos var0, Class12 var1, Class208 var2) {
      if (var2 != null && var2.field3 != null) {
         ConcurrentHashMap var5 = var2.field3.field1;
         BlockPos var6 = var2.method0(var0.add(-var1.method1(), 0, -var1.method0()), true);
         return (Class1)var5.get(var6);
      } else {
         int var3 = Class198.field0.method5();
         Map var4 = StringSetting.method0(var3);
         return (Class1)var4.get(var0);
      }
   }
}
