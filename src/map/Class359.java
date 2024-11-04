package map;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import oringo.module.GemstoneESPModule;
import oringo.module.ThystHiderModule;

public class Class359 implements Class23 {
   @SerializedName("message")
   public String field0 = "Forced Oringo Client crash";

   public String method0() {
      return this.field0;
   }

   public static double method0(double var0, double var2, double var4) {
      return Math.max(var0, Math.min(var2, var4));
   }

   public void method0(Class173 var1) {
      var1.method0(this);
   }

   public static ArrayList method0(EnumDyeColor var0, List var1) {
      if (!GemstoneESPModule.field5.a_(1000L) && GemstoneESPModule.field10.containsKey(var0)) {
         return (ArrayList)GemstoneESPModule.field10.get(var0);
      } else {
         BlockPos var2 = ThystHiderModule.method3();
         ArrayList var3 = new ArrayList(1000);
         Iterator var4 = var1.iterator();

         while(var4.hasNext()) {
            Vec3 var5 = (Vec3)var4.next();
            if (var5.squareDistanceTo(new Vec3(var2)) <= GemstoneESPModule.field4.method0() * GemstoneESPModule.field4.method0()) {
               var3.add(var5);
            }
         }

         GemstoneESPModule.field10.put(var0, var3);
         return var3;
      }
   }
}
