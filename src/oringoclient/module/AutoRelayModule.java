package oringo.module;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import map.Class447;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class156;
import oringo.event.Class189;
import oringo.mixin.PlayerControllerMPAccessor;
import oringo.setting.EnumSetting;

public class AutoRelayModule extends Module {
   public int al_ = 0;
   public final Class447 cr_ = new Class447();
   public int field2 = 3;
   public boolean field3 = false;
   public boolean cs_ = true;
   public String field5 = "";
   public final HashMap field6 = new HashMap();

   @SubscribeEvent
   public void field0(Class156 var1) {
      if (EnumSetting.method1()) {
         if (this.cr_.method0(250L, true)) {
            if (this.cs_ && this.al_ != 4) {
               Oringo.field9.getNetHandler().addToSendQueue(new C0EPacketClickWindow(field58.thePlayer.openContainer.windowId, 10 + this.al_++ * 9 + this.field2 * 2, 1, 0, (ItemStack)null, DiscordRPCModule.method6()));
            }

            if (this.field6.size() == 4 || this.field3) {
               this.cs_ = false;
               this.field3 = true;
               Integer var2 = (Integer)((Entry)this.field6.entrySet().stream().min(Comparator.comparingDouble(Entry::getValue)).get()).getKey();
               Oringo.field9.getNetHandler().addToSendQueue(new C0EPacketClickWindow(field58.thePlayer.openContainer.windowId, 10 + var2 * 9 + this.field2 * 2, 0, 0, (ItemStack)null, DiscordRPCModule.method6()));
               this.field6.remove(var2);
               if (this.field6.size() == 0) {
                  this.field3 = false;
                  ++this.field2;
                  this.al_ = 0;
                  if (this.field2 != 5) {
                     this.cs_ = true;
                  }
               }
            }
         }
      } else {
         this.field6.clear();
         this.cs_ = true;
         this.al_ = 0;
         this.field2 = 3;
         this.field3 = false;
         this.cr_.o_();
      }

   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (field58.currentScreen != null && var1.field0 instanceof S29PacketSoundEffect && EnumSetting.method1() && (double)((S29PacketSoundEffect)var1.field0).getVolume() == 1.0D && this.cs_ && this.field6.size() != 4) {
         if (!this.field5.equals(((S29PacketSoundEffect)var1.field0).getSoundName())) {
            this.field6.clear();
            this.field5 = ((S29PacketSoundEffect)var1.field0).getSoundName();
         }

         this.field6.put(this.field6.size(), ((S29PacketSoundEffect)var1.field0).getPitch());
      }

   }

   public static void method0(BlockPos var0, EnumFacing var1, Vec3 var2) {
      int var3 = Oringo.field9.thePlayer.inventory.currentItem;
      int var4 = ((PlayerControllerMPAccessor)Oringo.field9.playerController).getCurrentPlayerItem();
      ItemStack var5 = Oringo.field9.thePlayer.inventory.getStackInSlot(var4);
      Oringo.field9.thePlayer.inventory.currentItem = var4;
      if (Oringo.field9.playerController.onPlayerRightClick(Oringo.field9.thePlayer, Oringo.field9.theWorld, var5, var0, var1, var2)) {
         Oringo.field9.getNetHandler().addToSendQueue(new C0APacketAnimation());
         Oringo.field9.thePlayer.inventory.currentItem = var3;
      } else {
         Oringo.field9.thePlayer.inventory.currentItem = var3;
         AutoScribeModule.method5();
      }
   }

   public AutoRelayModule() {
      super("Auto Relay", Category.skyblock, SubCategory.rift, "Auto relay solving");
   }
}
