package map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;
import net.minecraft.inventory.Slot;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.module.Category;
import oringo.module.CommandBindingModule;
import oringo.module.FreeCamModule;
import oringo.module.KillAuraModule;
import oringo.module.SubCategory;
import oringo.module.TrailModule;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class Class95 extends Class408 {
   public boolean field0;
   public final BooleanSetting ar_ = new BooleanSetting("Render only in dungeon", true, this::lambda$new$0);
   public int field2;
   public final DoubleSetting field3 = new DoubleSetting("Interval", 250.0D, 50.0D, 1000.0D, 1.0D);
   public final BooleanSetting field4 = (BooleanSetting)(new BooleanSetting("Third person", false)).method2("Changes your perspective");
   public final Class447 field5 = new Class447();
   public final BooleanSetting field6 = (BooleanSetting)(new BooleanSetting("Render Range", false)).method2("Shows you the range of the ability");
   public final BooleanSetting field7 = new BooleanSetting("Only held", false);

   public boolean method1() {
      return super.method1() && (!this.field7.method1() || field58.thePlayer != null && field58.thePlayer.getHeldItem() != null && CommandBindingModule.method0(field58.thePlayer.getHeldItem()));
   }

   public Boolean lambda$new$0() {
      return !this.field6.method1();
   }

   public static boolean method3() {
      FreeCamModule var0 = Class362.field43;
      return var0 != null && var0.method44() && var0.field3.method1() && Class496.field1;
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.method1() && KillAuraModule.field24 == null) {
         if (this.field4.method1()) {
            field58.gameSettings.thirdPersonView = 1;
            this.field0 = true;
         }

         if (!Class496.field5 || !Class496.field13 || Class198.field0.method5() != 7) {
            int var2 = TrailModule.method0(CommandBindingModule::method0);
            if (var2 != -1) {
               method0((String)(var2 + " " + CommandBindingModule.method0(field58.thePlayer.inventory.getStackInSlot(var2))));
               if (Class361.method0((Class94)(new Class350(var2)))) {
                  var1.bF_ = 90.0F;
               }

            }
         }
      } else {
         if (this.field0) {
            field58.gameSettings.thirdPersonView = this.field2;
            this.field0 = false;
         }

         this.field2 = field58.gameSettings.thirdPersonView;
      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field6.method1() && (Class496.field5 || !this.ar_.method1())) {
         Class462.method0(field58.thePlayer.getPositionEyes(var1.partialTicks).subtract(0.0D, (double)field58.thePlayer.getEyeHeight(), 0.0D), 6.0D, Color.GREEN);
      }

   }

   public Class95() {
      super("Impact Spammer", Category.skyblock, SubCategory.qol, "Spams wither impact at the ground");
      this.method0((Setting[])(new Setting[]{this.field3, this.field6, this.ar_, this.field7, this.field4}));
   }

   public static int method0(Predicate var0) {
      ArrayList var1 = new ArrayList(Oringo.field9.thePlayer.inventoryContainer.inventorySlots);
      Collections.reverse(var1);
      Iterator var2 = var1.iterator();

      Slot var3;
      do {
         if (!var2.hasNext()) {
            return -1;
         }

         var3 = (Slot)var2.next();
      } while(!var3.getHasStack() || !var0.test(var3.getStack()));

      return var3.slotNumber;
   }
}
