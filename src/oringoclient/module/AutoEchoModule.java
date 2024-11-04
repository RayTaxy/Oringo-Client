package oringo.module;

import map.Class302;
import map.Class350;
import map.Class361;
import map.Class94;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class75;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoEchoModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Health", 5.0D, 1.0D, 20.0D, 0.5D);
   public long cC_ = System.currentTimeMillis();

   @SubscribeEvent
   public void method0(Class75 var1) {
      if ((double)field58.thePlayer.getHealth() < this.field0.method0() && this.cC_ + 40000L < System.currentTimeMillis() && this.method44()) {
         int var2 = TrailModule.method0(AutoEchoModule::lambda$onUpdate$0);
         if (var2 == -1) {
            return;
         }

         if (!Class361.method0((Class94)(new Class350(var2)))) {
            return;
         }

         SecretHitboxesModule.method0("Echo used!", 2500);
         this.cC_ = System.currentTimeMillis();
      }

   }

   public AutoEchoModule() {
      super("Auto Echo", Category.player, SubCategory.player);
      this.method0((Setting)this.field0);
   }

   public static void method0(String... var0) {
      if (var0.length == 4) {
         IChatComponent[] var1 = new IChatComponent[4];

         for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = new ChatComponentText(var0[var2]);
         }

         BlockPos var4 = ThystHiderModule.method3();
         BlockPos var3 = new BlockPos(var4.getX(), 0, var4.getZ());
         Class302.method2(new C12PacketUpdateSign(var3, var1));
      }
   }

   public static boolean lambda$onUpdate$0(ItemStack var0) {
      return var0.getDisplayName().contains("Echo");
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (var1.message.getUnformattedText().equals("You used your Echo ability!")) {
         this.cC_ = System.currentTimeMillis();
      }

      if (var1.message.getUnformattedText().trim().equals("Gather resources and equipment on your")) {
         this.cC_ = System.currentTimeMillis() - 30000L;
      }

   }
}
