package oringo.setting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import map.Class1;
import map.Class12;
import map.Class208;
import map.Class267;
import map.Class362;
import map.Class376;
import map.Class500;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import oringo.module.AutoRelayModule;
import oringo.module.FragHelperModule;
import oringo.module.LividFinderModule;
import oringo.module.Module;
import oringo.module.PestESPModule;

public class EnumSetting extends Setting {
   public List ah_;
   @Expose
   @SerializedName("value")
   public String ai_;
   public final String field2;
   public int field0;
   public final String field4;

   public boolean method0(int var1) {
      return var1 == this.field0;
   }

   public static byte[] method0() {
      JsonArray var0 = new JsonArray();
      Iterator var1 = Class362.method0().iterator();

      while(var1.hasNext()) {
         Module var2 = (Module)var1.next();
         JsonObject var3 = Class376.field1.toJsonTree(new Class376.Class1(var2)).getAsJsonObject();
         var2.method1(var3);
         var0.add(var3);
      }

      return Class376.field1.toJson(var0).getBytes(StandardCharsets.UTF_8);
   }

   public static boolean method1() {
      return AutoRelayModule.field58.thePlayer != null && AutoRelayModule.field58.thePlayer.openContainer instanceof ContainerChest && ((ContainerChest)AutoRelayModule.field58.thePlayer.openContainer).getLowerChestInventory().getName().endsWith("Network Relay");
   }

   public void method1(int var1) {
      switch(var1) {
      case 0:
         if (this.field0 < this.ah_.size() - 1) {
            ++this.field0;
            this.ai_ = (String)this.ah_.get(this.field0);
         } else if (this.field0 >= this.ah_.size() - 1) {
            this.field0 = 0;
            this.ai_ = (String)this.ah_.get(0);
         }
         break;
      case 1:
         if (this.field0 > 0) {
            --this.field0;
         } else {
            this.field0 = this.ah_.size() - 1;
         }

         this.ai_ = (String)this.ah_.get(this.field0);
         break;
      default:
         this.field0 = this.ah_.indexOf(this.field4);
         this.ai_ = (String)this.ah_.get(this.field0);
      }

   }

   public int method2() {
      return this.field0;
   }

   public EnumSetting(String var1, Class500 var2, String var3, String... var4) {
      super(var1, var2);
      this.field4 = var3;
      this.ah_ = new ArrayList(Arrays.asList(var4));
      this.field0 = this.ah_.indexOf(var3);
      if (this.field0 != -1) {
         this.ai_ = (String)this.ah_.get(this.field0);
      }

      this.field2 = var3;
   }

   public void method0(List var1) {
      this.ah_ = var1;
   }

   public List method3() {
      return this.ah_;
   }

   public void d_(String var1) {
      int var2 = this.ah_.indexOf(var1);
      if (var2 != -1) {
         this.ai_ = var1;
         this.field0 = var2;
      }

   }

   public boolean method0(String var1) {
      return var1.equalsIgnoreCase(this.ai_);
   }

   public EnumSetting(String var1, String var2, String... var3) {
      super(var1);
      this.field4 = var2;
      this.ah_ = new ArrayList(Arrays.asList(var3));
      this.field0 = this.ah_.indexOf(var2);
      if (this.field0 != -1) {
         this.ai_ = (String)this.ah_.get(this.field0);
      }

      this.field2 = var2;
   }

   public static void method0(BlockPos var0, Vec3 var1) {
      Class12 var2 = FragHelperModule.method0(var0.getX(), var0.getZ());
      Class208 var3 = LividFinderModule.method0(var2.method1(), var2.method0());
      Class1 var4 = Class267.method0(var0, var2, var3);
      if (var1 == null) {
         var4.field1 = null;
      } else {
         if (var3 != null) {
            var4.field1 = var3.method0(var1.subtract(new Vec3((double)var2.method1() + 0.5D, 0.0D, (double)var2.method0() + 0.5D)), true);
         } else {
            var4.field1 = var1;
         }

         PestESPModule.n_();
      }
   }

   public String method4() {
      return this.ai_;
   }

   public void method2(int var1) {
      this.field0 = var1;
      this.ai_ = (String)this.ah_.get(var1);
   }

   public boolean method0(String... var1) {
      String[] var2 = var1;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         if (this.method0(var5)) {
            return true;
         }
      }

      return false;
   }
}
