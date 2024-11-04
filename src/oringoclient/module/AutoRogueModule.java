package oringo.module;

import map.Class361;
import map.Class402;
import map.Class426;
import map.Class447;
import map.Class496;
import map.Class94;
import map.Class95;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoRogueModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Only dungeon", false);
   public final BooleanSetting field1 = new BooleanSetting("From inv", false);
   public final DoubleSetting field2 = (new DoubleSetting("Delay", 31.0D, 0.0D, 100.0D, 1.0D)).method0("s");
   public final Class447 field3 = new Class447();

   public static void method0(double var0, double var2, double var4, double var6, double var8, double var10, int var12) {
      AutoSimonModule.method0((float)var0, (float)var2, (float)var4, (float)var6, (float)var8, (float)var10, var12);
   }

   public AutoRogueModule() {
      super("Auto Rogue", Category.dungeon, SubCategory.main, "Clicks rogue sword automatically");
      this.method0(new Setting[]{this.field2, this.field0, this.field1});
   }

   public static boolean lambda$onTick$0(ItemStack var0) {
      return "ROGUE_SWORD".equals(BlockHitModule.method0(var0));
   }

   @SubscribeEvent
   public void a_(Class270 var1) {
      this.field3.method0(30000L);
   }

   public static void lambda$onTick$1(ItemStack var0) {
      if (var0 != null) {
         Class426.method10();
         method3(new C08PacketPlayerBlockPlacement(var0));
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (Class496.field5 || !this.field0.method1()) {
         if (field58.thePlayer.fishEntity == null && !field58.thePlayer.isUsingItem()) {
            if (this.field3.a_((long)this.field2.method3() * 1000L)) {
               int var2 = Class95.method0(AutoRogueModule::lambda$onTick$0);
               if (var2 < 36 && !this.field1.method1()) {
                  var2 = -1;
               }

               if (var2 != -1) {
                  if (Class361.method0((Class94)(new Class402(var2, AutoRogueModule::lambda$onTick$1)))) {
                     this.field3.o_();
                  }
               }
            }
         }
      }
   }
}
