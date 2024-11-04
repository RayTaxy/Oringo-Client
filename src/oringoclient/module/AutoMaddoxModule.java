package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import map.Class239;
import map.Class350;
import map.Class361;
import map.Class408;
import map.Class447;
import map.Class528;
import map.Class94;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class156;
import oringo.event.Class189;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class AutoMaddoxModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Mute", true);
   public final Queue av_ = new LinkedList();
   public boolean field2 = false;
   public boolean field3 = false;
   public final EnumSetting field4 = new EnumSetting("Call on", "Spawn", new String[]{"Spawn", "Kill"});
   public AutoMaddoxModule.Class0 field5 = null;
   public ContainerChest field6 = null;
   public final Class447 field7 = new Class447();
   public String field8 = null;

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.field0.method1() && !this.field7.a_(3000L)) {
         if (var1.field0 instanceof S29PacketSoundEffect) {
            if ((int)(((S29PacketSoundEffect)var1.field0).getPitch() * 63.0F) != 105) {
               return;
            }

            if (((S29PacketSoundEffect)var1.field0).getVolume() * 100.0F != 69.0F) {
               return;
            }

            if (!((S29PacketSoundEffect)var1.field0).getSoundName().equals("note.pling")) {
               return;
            }

            var1.method9();
         }

      }
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      String var2 = var1.message.getUnformattedText();
      if (!var2.isEmpty() && var2.charAt(0) == 9990 && this.field0.method1() && !this.field7.a_(3000L)) {
         var1.setCanceled(true);
      }

      if (var2.equals("  NICE! SLAYER BOSS SLAIN!")) {
         int var3 = Math.max(PinglessHardstoneModule.z_("AATROX_BATPHONE"), PinglessHardstoneModule.z_("AATROX_BADPHONE"));
         if (var3 != -1 && Class361.method0((Class94)(new Class350(var3)))) {
            this.field7.o_();
         }
      }

   }

   public static int method0(Class var0) {
      return TrailModule.method0(Class239::lambda$getHotbar$3);
   }

   public AutoMaddoxModule() {
      super("Auto Maddox", Category.skyblock, SubCategory.slayer, "Automatically calls maddox and starts ");
      this.method0((Setting[])(new Setting[]{this.field4, this.field0}));
   }

   @SubscribeEvent
   public void field0(Class156 var1) {
      ContainerChest var2 = Class528.method1();
      if (var2 != null && this.field6 != var2 && !this.field7.a_(10000L)) {
         if (var2.getLowerChestInventory().getName().equals("Slayer")) {
            ItemStack var3 = var2.getSlot(12).getStack();
            if (var3 == null) {
               return;
            }

            for(int var4 = 10; var4 < 17; ++var4) {
               ItemStack var5 = var2.getSlot(var4).getStack();
               if (var5 != null) {
                  if (var5.getDisplayName().equals("§aSlayer Quest Complete!")) {
                     List var6 = Class408.method1(var5);
                     if (var6.size() == 12) {
                        this.field5 = new AutoMaddoxModule.Class0(((String)var6.get(4)).substring(8), var5.getItem());
                        field58.playerController.windowClick(var2.windowId, var4, 0, 0, field58.thePlayer);
                     }
                  }

                  if (this.field5 != null && var5.getItem() == AutoMaddoxModule.Class0.access$000(this.field5)) {
                     field58.playerController.windowClick(var2.windowId, var4, 0, 0, field58.thePlayer);
                  }
               }
            }
         }

         if (this.field5 != null && var2.getLowerChestInventory().getName().equals(AutoMaddoxModule.Class0.access$100(this.field5))) {
            for(int var7 = 11; var7 < 16; ++var7) {
               ItemStack var8 = var2.getSlot(var7).getStack();
               if (var8 != null && var8.getDisplayName().equals(AutoMaddoxModule.Class0.access$200(this.field5))) {
                  field58.playerController.windowClick(var2.windowId, var7, 0, 0, field58.thePlayer);
                  this.field5 = null;
                  break;
               }
            }
         }

         if (var2.getLowerChestInventory().getName().equals("Confirm")) {
            field58.playerController.windowClick(var2.windowId, 11, 0, 0, field58.thePlayer);
         }
      }

      if (this.field6 != var2 && !this.av_.isEmpty() && var2 != null) {
         field58.playerController.windowClick(var2.windowId, (Integer)this.av_.poll(), 0, 0, field58.thePlayer);
      }

      this.field6 = Class528.method1();
   }

   private static class Class0 {
      private final Item field0;
      private final String field1;
      private final String field2;

      static Item access$000(AutoMaddoxModule.Class0 var0) {
         return var0.field0;
      }

      static String access$100(AutoMaddoxModule.Class0 var0) {
         return var0.field1;
      }

      static String access$200(AutoMaddoxModule.Class0 var0) {
         return var0.field2;
      }

      public Class0(String var1, Item var2) {
         this.field2 = var1;
         this.field0 = var2;
         this.field1 = ChatFormatting.stripFormatting(var1.substring(0, var1.lastIndexOf(32)));
      }
   }
}
