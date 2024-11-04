package oringo.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import map.Class122;
import map.Class362;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class MacroHelperModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("- Walking", false, this::lambda$new$2);
   public final BooleanSetting A_ = new BooleanSetting("Anti AFK", false);
   public final BooleanSetting field2 = new BooleanSetting("- Rotations", false, this::lambda$new$1);
   public final BooleanSetting field3 = new BooleanSetting("- Disable modules", true);
   public float field4 = 0.0F;
   public final BooleanSetting field5 = new BooleanSetting("Player Detection", false);
   public float field6 = 0.0F;
   public final DoubleSetting field7 = new DoubleSetting("- Range", 10.0D, 2.0D, 100.0D, 0.5D, this::lambda$new$0);
   public int field20 = 0;

   public Boolean lambda$new$1() {
      return !this.A_.method1();
   }

   public Boolean lambda$new$2() {
      return !this.A_.method1();
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.field5.method1()) {
         List var2 = field58.theWorld.getEntities(EntityOtherPlayerMP.class, this::lambda$onMotionUpdate$3);
         if (!var2.isEmpty()) {
            PopupAnimationModule.method2(String.format("Player %s §fdetected!", ((EntityOtherPlayerMP)var2.get(0)).getDisplayName().getFormattedText()));
            field58.thePlayer.sendChatMessage("/is");
            if (this.field3.method1()) {
               Class362.field25.method1(false);
               Class362.field10.method1(false);
               Class362.field52.method1(false);
            }

            this.method1(false);
            return;
         }
      }

      if (this.A_.method1()) {
         if (this.field2.method1() && this.field20 >= 270) {
            if (this.field20 == 270) {
               this.field6 = ((new Random()).nextFloat() - 0.5F) * 8.0F;
               this.field4 = ((new Random()).nextFloat() - 0.5F) * 8.0F;
            }

            float var3 = (float)(this.field20 - 270) / 30.0F;
            var1.bF_ = Math.max(Math.min(var1.bF_ + this.field6 * var3, 90.0F), -90.0F);
            var1.t_ = Math.max(Math.min(var1.t_ + this.field4 * var3, 180.0F), -180.0F);
         }

         if (this.field0.method1() && this.field20 <= 11) {
            KeyBinding.setKeyBindState(field58.gameSettings.keyBindSneak.getKeyCode(), this.field20 <= 10);
            KeyBinding.setKeyBindState(field58.gameSettings.keyBindBack.getKeyCode(), false);
            KeyBinding.setKeyBindState(field58.gameSettings.keyBindForward.getKeyCode(), false);
            if (this.field20 < 5) {
               KeyBinding.setKeyBindState(field58.gameSettings.keyBindForward.getKeyCode(), true);
            } else if (this.field20 < 10) {
               KeyBinding.setKeyBindState(field58.gameSettings.keyBindBack.getKeyCode(), true);
            }
         }

         ++this.field20;
         if (this.field20 == 300) {
            this.field20 = 0;
         }
      }

   }

   public MacroHelperModule() {
      super("Macro Helper", Category.skyblock, SubCategory.qol, "Helps you, when using not failsafe macros");
      this.method0((Setting[])(new Setting[]{this.field5, this.field7, this.field3, this.A_, this.field2, this.field0}));
   }

   public boolean lambda$onMotionUpdate$3(EntityOtherPlayerMP var1) {
      return (double)field58.thePlayer.getDistanceToEntity(var1) <= this.field7.method0() && (!var1.isInvisible() || !Arrays.equals(var1.inventory.armorInventory, new ItemStack[4])) && var1.riddenByEntity == null && !Class122.method0(var1) && var1.ticksExisted > 20;
   }

   public Boolean lambda$new$0() {
      return !this.field5.method1();
   }

   public static int method0(Class var0) {
      ArrayList var1 = new ArrayList(Oringo.field9.thePlayer.inventoryContainer.inventorySlots);
      Collections.reverse(var1);
      Iterator var2 = var1.iterator();

      Slot var3;
      do {
         if (!var2.hasNext()) {
            return -1;
         }

         var3 = (Slot)var2.next();
      } while(!var3.getHasStack() || !var0.isAssignableFrom(var3.getStack().getItem().getClass()));

      return var3.slotNumber;
   }
}
