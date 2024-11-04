package oringo.module;

import java.util.HashMap;
import java.util.Map.Entry;
import map.Class447;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class AutoHarpModule extends Module {
   public static final EnumSetting field0 = new EnumSetting("Mode", "Left", new String[]{"Left", "Middle"});
   public static final DoubleSetting field1 = new DoubleSetting("Delay", 10.0D, 0.0D, 300.0D, 1.0D);
   public int field2 = -1;
   public final Class447 field3 = new Class447();
   public static final BooleanSetting field4 = new BooleanSetting("Close on fail", true);
   public static final DoubleSetting field5 = new DoubleSetting("Delay on double", 150.0D, 0.0D, 500.0D, 1.0D);
   public final HashMap field6 = new HashMap();
   public final Class447 field7 = new Class447();

   @SubscribeEvent
   public void method0(Class75 var1) {
      this.field6.entrySet().removeIf(AutoHarpModule::lambda$onUpdate$0);
   }

   public static boolean lambda$onUpdate$0(Entry var0) {
      if (((Class447)var0.getKey()).a_(0L)) {
         Oringo.field9.getNetHandler().addToSendQueue(new C0EPacketClickWindow(field58.thePlayer.openContainer.windowId, (Integer)var0.getValue(), field0.method0(0) ? 0 : 2, field0.method0(0) ? 0 : 3, field0.method0(0) ? field58.thePlayer.openContainer.getSlot((Integer)var0.getValue()).getStack() : null, DiscordRPCModule.method6()));
      }

      return ((Class447)var0.getKey()).a_(0L);
   }

   public AutoHarpModule() {
      super("Auto Harp", Category.skyblock, SubCategory.qol, "Does the harp for you");
      this.method0(new Setting[]{field0, field1, field5, field4});
   }

   public static Vec3 method5() {
      EntityPlayerSP var0 = Oringo.field9.thePlayer;
      return new Vec3(var0.lastTickPosX, var0.lastTickPosY, var0.lastTickPosZ);
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (field58.currentScreen != null && var1.field0 instanceof S29PacketSoundEffect && TargetStrafeModule.method5() && ((S29PacketSoundEffect)var1.field0).getSoundName().equals("note.bassattack") && field4.method1()) {
         PopupAnimationModule.method2("Song failed!" + (!this.field3.a_(1000L) ? " Due to server lag" : ""));
      }

      if (field58.currentScreen != null && var1.field0 instanceof S2FPacketSetSlot && TargetStrafeModule.method5() && ((S2FPacketSetSlot)var1.field0).func_149174_e() != null) {
         int var2 = ((S2FPacketSetSlot)var1.field0).func_149173_d();
         boolean var3 = var2 >= 28 && var2 <= 34 && var2 == this.field2 + 9;
         if (var2 <= this.field2 || var3) {
            if (!this.field7.a_(10L)) {
               return;
            }

            if (this.field7.method0() >= 400L || this.field7.method0() <= 200L) {
               this.field3.o_();
            }

            this.field7.o_();

            for(int var4 = 28; var4 < 35; ++var4) {
               if (field58.thePlayer.openContainer.inventorySlots.size() <= var4) {
                  return;
               }

               ItemStack var5 = field58.thePlayer.openContainer.getSlot(var4).getStack();
               if (var5 != null && var5.getItem() == Item.getItemFromBlock(Blocks.wool)) {
                  int var6 = var4 + 9;
                  if (var3) {
                     this.field6.put(new Class447(), var6);
                  }

                  this.field6.put(new Class447(System.currentTimeMillis() - (long)(var3 ? field5.method3() : field1.method3())), var6);
                  method3("Clicking " + EnumDyeColor.byMetadata(var5.getMetadata()).getName());
               }
            }
         }

         if (var2 < 35) {
            this.field2 = var2;
         }
      }

   }
}
