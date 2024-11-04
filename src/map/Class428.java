package map;

import java.awt.Color;
import java.net.HttpURLConnection;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import oringo.module.IRCModule;

public class Class428 extends Class348 {
   public void method1() {
   }

   public void method0(int var1) {
   }

   public void method1(int var1) {
   }

   public Class428() {
      super("Configs", (ResourceLocation)null, Class442.field3);
   }

   public boolean method0(char var1, int var2) {
      return false;
   }

   public void method0(float var1) {
   }

   public static Color method17() {
      return IRCModule.field0.method17();
   }

   public static void method0(HttpURLConnection var0, String var1, String var2) {
      var0.setRequestProperty(var1, var2);
   }

   public void q_() {
   }

   public void method3() {
   }

   public static Iterable method0(BlockPos var0) {
      return Class226::lambda$getNearbyBlocks$1;
   }
}
