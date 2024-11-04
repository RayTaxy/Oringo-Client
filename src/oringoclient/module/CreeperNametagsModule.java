package oringo.module;

import java.awt.Color;
import map.Class376;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class CreeperNametagsModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Sneaky creeper ESP", true);
   public boolean l_;
   public final BooleanSetting field2 = new BooleanSetting("Make visible", true);
   public boolean field3;

   public CreeperNametagsModule() {
      super("Creeper Nametags", Category.render, SubCategory.world, "Renders invisible creepers' nametags");
      this.method0((Setting[])(new Setting[]{this.field2, this.field0}));
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      this.l_ = Class376.method0("Gunpowder Mines");
      this.field3 = Class376.method0("The Mist");
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.entity instanceof EntityCreeper) {
         EntityCreeper var2 = (EntityCreeper)var1.entity;
         this.method0(var1, var2);
         this.method1(var1, var2);
      }
   }

   public void method0(Post var1, EntityCreeper var2) {
      if (this.field3 && var2.getPowered()) {
         String var3 = AutoArrowModule.method0(var2, "Ghost", 250, 1000000);
         AutoCrystalModule.method0(var2, var3, var1.x, var1.y, var1.z, 48);
         if (this.field2.method1()) {
            var2.setInvisible(false);
         }

      }
   }

   public void method1(Post var1, EntityCreeper var2) {
      if (this.l_ && var2.getCreeperState() != 1) {
         String var3 = AutoArrowModule.method0(var2, "Sneaky Creeper", 3, 120);
         AutoCrystalModule.method0(var2, var3, var1.x, var1.y, var1.z, 48);
         if (this.field2.method1()) {
            var2.setInvisible(false);
         }

         if (this.field0.method1()) {
            AutoIceFillModule.method0(var2, FrozenTreasureESPModule.method5().renderPartialTicks, Color.GREEN);
         }

      }
   }
}
