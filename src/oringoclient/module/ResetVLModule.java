package oringo.module;

import net.minecraft.util.Vec4b;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class210;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class ResetVLModule extends Module {
   public static final DoubleSetting field0 = new DoubleSetting("Timer", 1.0D, 0.1D, 3.0D, 0.05D);
   public static final DoubleSetting field1 = new DoubleSetting("Jumps", 10.0D, 1.0D, 25.0D, 1.0D);
   public int field2;

   public static float method0(Vec4b var0) {
      return (float)var0.func_176112_b() / 2.0F + 64.0F;
   }

   public void method4() {
      this.field2 = 0;
      if (field58.thePlayer == null || !field58.thePlayer.onGround) {
         this.method40();
      }

   }

   public void b_() {
      EnigmaSoulESPModule.method0(1.0F);
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.method44()) {
         if ((double)this.field2 <= field1.method0()) {
            if (field58.thePlayer.onGround) {
               ++this.field2;
               field58.thePlayer.motionY = 0.11D;
            }

            EnigmaSoulESPModule.method0((float)field0.method0());
         } else {
            this.method40();
         }
      }

   }

   public ResetVLModule() {
      super("Reset VL", Category.other, SubCategory.player, "Resets violation level");
      this.method0((Setting[])(new Setting[]{field1, field0}));
   }
}
