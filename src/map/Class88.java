package map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import oringo.module.BridgeHelperModule;
import oringo.module.HitboxesModule;

public class Class88 implements Class138 {
   public final Minecraft field0 = Minecraft.getMinecraft();

   public long method0(long var1, long var3) {
      if (Class304.field0.method0()) {
         var1 += var3;
      }

      return (long)((double)var1 * 0.2D * 1.08D);
   }

   public boolean method0() {
      return this.field0.thePlayer == null ? false : "bountiful".equals(BridgeHelperModule.method0(this.field0.thePlayer.getHeldItem()));
   }

   public long method0(Class532 var1, long var2, long var4) {
      long var6 = this.method0(var2, var4);
      var1.method0("Bountiful coins/d: §6" + var1.method0(var6));
      return var6;
   }

   public String method1(long var1, long var3) {
      long var5 = this.method0(var1, var3);
      return "Bountiful/d: §6" + HitboxesModule.method0(var5);
   }

   public static JsonElement method0(JsonObject var0, String var1) {
      return var0.get(var1);
   }
}
