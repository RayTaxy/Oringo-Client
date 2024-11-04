package map;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.Oringo;
import oringo.mixin.MinecraftAccessor;
import oringo.mixin.PlayerControllerMPAccessor;
import oringo.module.BlockHitModule;
import oringo.module.Category;
import oringo.module.SubCategory;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class Class426 extends Class408 {
   public final Class447 field0 = new Class447();
   public final DoubleSetting field1 = new DoubleSetting("CPS", 10.0D, 1.0D, 20.0D, 1.0D);

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (field58.thePlayer != null && this.method1() && this.method5()) {
         if (this.field0.a_((long)(1000 / this.field1.method3()))) {
            KeyBinding.onTick(field58.gameSettings.keyBindAttack.getKeyCode());
            ((MinecraftAccessor)field58).setClickDelay(0);
            this.field0.o_();
         }
      }
   }

   public boolean method5() {
      return "TERMINATOR".equals(BlockHitModule.method0(field58.thePlayer.getHeldItem()));
   }

   public static void method10() {
      int var0 = Oringo.field9.thePlayer.inventory.currentItem;
      if (var0 != ((PlayerControllerMPAccessor)Oringo.field9.playerController).getCurrentPlayerItem()) {
         ((PlayerControllerMPAccessor)Oringo.field9.playerController).setCurrentPlayerItem(var0);
         Oringo.field9.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(var0));
      }

   }

   public Class426() {
      super("Terminator Clicker", Category.skyblock, SubCategory.qol, "Clicks terminator while the keybind is pressed");
      this.method0(new Setting[]{this.field1});
   }
}
