package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import map.Class374;
import map.Class392;
import map.Class447;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoCombineModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Combine Shards", false);
   public final DoubleSetting ak_ = new DoubleSetting("Max Level", 10.0D, 2.0D, 10.0D, 1.0D);
   public final DoubleSetting field2 = new DoubleSetting("Pickup Delay", 150.0D, 0.0D, 300.0D, 1.0D);
   public AutoCombineModule.Class0 field3;
   public final Class447 field4 = new Class447();
   public boolean field5 = false;
   public static final boolean field6 = !AutoCombineModule.class.desiredAssertionStatus();
   public boolean field7;
   public final BooleanSetting field8 = new BooleanSetting("Claim from bz", false, this::lambda$new$0);
   public final DoubleSetting field9 = new DoubleSetting("Action Interval", 50.0D, 40.0D, 200.0D, 1.0D);
   public AutoCombineModule.Class1 field10;

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.END) {
         this.field5 = true;
      }

   }

   public AutoCombineModule() {
      super("Auto Combine", Category.skyblock, SubCategory.qol, "Combines all of your books into one");
      this.field3 = AutoCombineModule.Class0.field4;
      this.field10 = null;
      this.field7 = false;
      this.method0((Setting[])(new Setting[]{this.field0, this.field9, this.field2, this.ak_, this.field8}));
   }

   public Boolean lambda$new$0() {
      return this.field0.method1();
   }

   public static boolean lambda$onRender$1(String var0) {
      return ChatFormatting.stripFormatting(var0).equals("Click to claim!");
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.method1(false);
   }

   public static boolean method1(String var0) {
      if (Class392.field4.contains(var0)) {
         return true;
      } else if (Class392.field2.contains(var0)) {
         return false;
      } else {
         Class392.field2.add(var0);
         Class392.field1.submit(Class392::lambda$isCapeReady$0);
         return false;
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      // $FF: Couldn't be decompiled
   }

   public void method4() {
      this.field3 = AutoCombineModule.Class0.field4;
      this.field7 = false;
   }

   private static class Class1 {
      public int al_;
      public int field1;
      public String field2;

      public Class1(int var1, int var2, String var3) {
         this.al_ = var1;
         this.field1 = var2;
         this.field2 = var3;
      }
   }

   private static enum Class0 {
      field0,
      field1,
      field2;

      private static final AutoCombineModule.Class0[] field6 = new AutoCombineModule.Class0[]{field4, field1, field0, field5, field3, field2};
      field3,
      field4,
      field5;
   }
}
