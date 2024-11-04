package oringo.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import map.Class28;
import map.Class496;
import map.Class507;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityBat;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.setting.ColorSetting;
import oringo.setting.Setting;

public class PestESPModule extends Module {
   public final ColorSetting field0;
   public final ColorSetting field1;

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (Class496.field6) {
         Iterator var2 = field58.theWorld.loadedEntityList.iterator();

         while(var2.hasNext()) {
            Entity var3 = (Entity)var2.next();
            if (var3 instanceof EntitySilverfish) {
               AutoIceSprayModule.method0(var3.getEntityBoundingBox(), this.field0.method17());
            } else if (var3 instanceof EntityBat) {
               AutoIceSprayModule.method0(var3.getEntityBoundingBox(), this.field1.method17());
            }
         }

      }
   }

   public PestESPModule() {
      super("Pest ESP", Category.render, SubCategory.visual, "Reveals pests in garden");
      this.field0 = new ColorSetting("Rat Color", Color.RED.getRGB(), false);
      this.field1 = new ColorSetting("Mosquito Color", Color.YELLOW.getRGB(), false);
      this.method0(new Setting[]{this.field0, this.field1});
   }

   public static void n_() {
      JsonObject var0 = new JsonObject();
      ShinyBlocksModule.method6();
      JsonArray var1 = new JsonArray();
      Iterator var2 = Class28.field3.iterator();

      JsonArray var5;
      while(var2.hasNext()) {
         Class507 var3 = (Class507)var2.next();
         JsonObject var4 = new JsonObject();
         var4.addProperty("core", var3.field2);
         var4.addProperty("offset", var3.field0);
         var4.addProperty("block_count", var3.field3);
         var5 = new JsonArray();
         var3.field1.forEach(Class28::lambda$save$9);
         var4.add("blocks", var5);
         var1.add(var4);
      }

      var0.add("rooms", var1);
      JsonArray var7 = new JsonArray();
      Iterator var8 = Class28.field2.iterator();

      while(var8.hasNext()) {
         ConcurrentHashMap var10 = (ConcurrentHashMap)var8.next();
         var5 = new JsonArray();
         var10.forEach(Class28::lambda$save$10);
         var7.add(var5);
      }

      var0.add("floors", var7);

      try {
         File var9 = new File(Class28.field4);
         var9.getParentFile().mkdirs();
         Files.write(var9.toPath(), var0.toString().getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }
}
