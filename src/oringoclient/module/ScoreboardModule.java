package oringo.module;

import map.Class209;
import map.Class228;
import map.Class283;
import map.Class430;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.Oringo;
import oringo.command.BuyAuctionCommand;
import oringo.event.Class533;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class ScoreboardModule extends Module {
   public Class228 field0;
   public final BooleanSetting field1 = new BooleanSetting("Custom font", true);
   public final EnumSetting field2 = (EnumSetting)(new EnumSetting("Expand to", "Right", new String[]{"Left", "Right"})).method2("Changes how the scoreboard handles different text lengths");
   public final BooleanSetting field3 = new BooleanSetting("Blur", true);
   public final BooleanSetting field4 = new BooleanSetting("Hide lobby", true);
   public final DoubleSetting field5 = new DoubleSetting("x", 0.995D, 0.0D, 1.0D, 0.0D, ScoreboardModule::lambda$new$0);
   public final EnumSetting field6 = new EnumSetting("Outline Mode", this::lambda$new$2, "Gui Color", new String[]{"Static Color", "Gui Color"});
   public final BooleanSetting field7 = new BooleanSetting("Outline", false);
   public final Class209 field8;
   public final ButtonSetting field9 = new ButtonSetting("Reset position", this::lambda$new$4);
   public final BooleanSetting field10 = new BooleanSetting("Shadow", true);
   public final BooleanSetting field11 = new BooleanSetting("Mix colors", false);
   public final BooleanSetting field12 = new BooleanSetting("Vanilla position", false);
   public final DoubleSetting field13 = new DoubleSetting("y", 0.35D, 0.0D, 1.0D, 0.0D, ScoreboardModule::lambda$new$1);
   public final ColorSetting field14 = new ColorSetting("Outline Color", -1, true, this::lambda$new$3);
   public Class228 field15;
   public final BooleanSetting field16 = new BooleanSetting("DVD", false);

   public static void method0(int var0, int var1, int var2) {
      Oringo.field9.playerController.windowClick(Oringo.field9.thePlayer.inventoryContainer.windowId, var0, var1, var2, Oringo.field9.thePlayer);
   }

   public Class283 method5() {
      return this.field8;
   }

   public static Boolean lambda$new$1() {
      return true;
   }

   public Boolean lambda$new$2() {
      return !this.field7.method1();
   }

   public static void v_(String var0) {
      BuyAuctionCommand.field9.thePlayer.sendChatMessage("/viewauction " + var0);
      BuyAuctionCommand.field0.o_();
   }

   public ScoreboardModule() {
      super("Scoreboard", Category.render, SubCategory.ui, "Changes the scoreboard");
      this.field8 = new Class209(this.field5, this.field13, this.field1, this.field3, this.field10, this.field12, this.field4, this.field7, this.field14, this.field6, this.field11, this.field2);
      this.field15 = (new Class228(1.25D, 1.0D, 0.0D)).method0().method1(5.0D);
      this.field0 = null;
      this.method0((Setting[])(new Setting[]{this.field5, this.field13, this.field1, this.field3, this.field10, this.field4, this.field2, this.field12, this.field7, this.field6, this.field11, this.field16, this.field14, this.field9}));
      this.method1(true);
   }

   @SubscribeEvent
   public void method0(Class533 var1) {
      if (this.field16.method1()) {
         this.field12.method0(false);
         if (this.field0 == null) {
            this.field0 = new Class228(this.field5.method0(), this.field13.method0(), 0.0D);
         }

         float var2 = FrozenTreasureESPModule.method5().renderPartialTicks;
         Class228 var3 = this.field0.method4(this.field15.method1((double)var2));
         this.field8.method0((float)var3.field2, (float)var3.field1);
      }

      var1.setCanceled(true);
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.END && this.field16.method1()) {
         if (this.field0 != null) {
            this.field0 = this.field0.method4(this.field15);
            ScaledResolution var2 = new ScaledResolution(field58);
            boolean var3 = this.field2.method0(0);
            if (this.field0.field2 < (double)(var3 ? 0.0F : this.field8.method7())) {
               this.field0 = this.field0.method4(var3 ? 0.0D : (double)this.field8.method7());
               this.field15 = this.field15.method4((double)Class430.method0(5.0F, 4.0F));
            }

            if (this.field0.field2 + (double)(var3 ? this.field8.method7() : 0.0F) > (double)var2.getScaledWidth()) {
               this.field0 = this.field0.method4(var3 ? (double)((float)var2.getScaledWidth() - this.field8.method7()) : (double)var2.getScaledWidth());
               this.field15 = this.field15.method4((double)(-Class430.method0(5.0F, 4.0F)));
            }

            if (this.field0.field1 < 0.0D) {
               this.field0 = this.field0.method3(0.0D);
               this.field15 = this.field15.method3(4.0D);
            }

            if (this.field0.field1 > (double)((float)var2.getScaledHeight() - this.field8.method13())) {
               this.field0 = this.field0.method3((double)((float)var2.getScaledHeight() - this.field8.method13()));
               this.field15 = this.field15.method3(-4.0D);
            }

         }
      }
   }

   public static Boolean lambda$new$0() {
      return true;
   }

   public Boolean lambda$new$3() {
      return !this.field7.method1() || this.field6.method0(1);
   }

   public void lambda$new$4() {
      this.field5.method4(0.995D);
      this.field13.method4(0.35D);
   }
}
