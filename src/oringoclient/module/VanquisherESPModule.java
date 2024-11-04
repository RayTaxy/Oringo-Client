package oringo.module;

import java.util.Iterator;
import java.util.List;
import map.Class28;
import map.Class362;
import map.Class376;
import map.Class409;
import map.Class507;
import map.Class518;
import map.Class528;
import net.minecraft.entity.boss.EntityWither;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class VanquisherESPModule extends Module {
   public final EnumSetting field0 = new EnumSetting("Mode", "Box", new String[]{"Box"});
   public final BooleanSetting field1 = new BooleanSetting("Tracers", true);
   public int field2 = 0;
   public final BooleanSetting field3 = new BooleanSetting("Notify", true);

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (field58.theWorld.getSkylightSubtracted() == 11) {
         if (this.field3.method1()) {
            List var2 = field58.theWorld.getEntities(EntityWither.class, VanquisherESPModule::lambda$onRenderWorld$0);
            if (this.field2 < var2.size()) {
               ShortbowTriggerbotModule.method0("Vanquisher ESP", "New Vanquisher found!", 4000);
            }

            this.field2 = var2.size();
            Iterator var3 = var2.iterator();

            EntityWither var4;
            while(var3.hasNext()) {
               var4 = (EntityWither)var3.next();
               AutoIceFillModule.method0(var4, var1.partialTicks, Class362.field7.method17());
            }

            if (this.field1.method1()) {
               var3 = var2.iterator();

               while(var3.hasNext()) {
                  var4 = (EntityWither)var3.next();
                  Class528.method0(var4, var1.partialTicks, 2.0F, Class362.field7.method17());
               }
            }
         } else {
            this.field2 = 0;
         }

      }
   }

   public static void method5() {
      Class409.method0(Class376.field0);
   }

   public static boolean lambda$onRenderWorld$0(EntityWither var0) {
      return (double)Class518.method0(var0) == 5000000.0D || (double)Class518.method0(var0) == 1.5E7D;
   }

   public static Class507 method0(int var0) {
      return (Class507)Class28.field1.get(var0);
   }

   public VanquisherESPModule() {
      super("Vanquisher ESP", Category.skyblock, SubCategory.visual, "Helps you find vanquishers");
      this.method0(new Setting[]{this.field0, this.field3, this.field1});
   }
}
