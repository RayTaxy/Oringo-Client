package oringo.module;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import map.Class108;
import map.Class354;
import map.Class434;
import map.Class447;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class156;
import oringo.event.Class189;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class AutoTerminalsModule extends Module {
   public static final Pattern field0;
   public final Class447 cb_ = new Class447();
   public final DoubleSetting field2 = new DoubleSetting("Rescan delay", 600.0D, 100.0D, 1000.0D, 50.0D);
   public int field3 = 100;
   public final EnumSetting field4 = new EnumSetting("Mode", "Hovered", new String[]{"Hovered", "Vanilla"});
   public boolean field5;
   public final BooleanSetting field6 = (BooleanSetting)(new BooleanSetting("Announce throttle", false)).method2("Announces if terminal has been throttled in chat");
   public final Class447 field7 = new Class447();
   public final BooleanSetting field8 = (BooleanSetting)(new BooleanSetting("Terminal stats", true)).method2("Shows you how long terminal took to complete");
   public boolean field9;
   public final BooleanSetting field10 = new BooleanSetting("Melody Terminal", true);
   public final DoubleSetting field11 = new DoubleSetting("Click Delay", 200.0D, 0.0D, 500.0D, 5.0D);
   public final StringSetting field12 = new StringSetting("Throttle message", "/pc Menu throttled!", this::lambda$new$0);
   public static AutoTerminalsModule.Class1 field13;
   public static final List field14;
   public final Class447 field15 = new Class447();

   static {
      field13 = AutoTerminalsModule.Class1.field3;
      field0 = Pattern.compile("^What starts with: ['\"](.+)['\"]\\?$");
      field14 = Lists.newArrayList(new EnumDyeColor[]{EnumDyeColor.RED, EnumDyeColor.ORANGE, EnumDyeColor.YELLOW, EnumDyeColor.GREEN, EnumDyeColor.BLUE});
   }

   public static Minecraft access$100() {
      return field58;
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S2DPacketOpenWindow) {
         this.field15.o_();
         this.cb_.o_();
         this.field9 = false;
      }

   }

   public static boolean lambda$null$1(EnumDyeColor var0, Slot var1) {
      return var1.getStack().getItemDamage() == var0.getMetadata();
   }

   @SubscribeEvent
   public void field0(Class156 var1) {
      if (((GuiChest)field58.currentScreen).inventorySlots != field58.thePlayer.openContainer) {
         method2("Not equal");
      }

      if (field58.thePlayer != null) {
         if (this.method1(var1.field0)) {
            this.field7.o_();
            this.cb_.o_();
            this.field15.o_();
            this.field5 = false;
         }

         if (field13 != AutoTerminalsModule.Class1.field3 && (field13 != AutoTerminalsModule.Class1.field0 || this.field10.method1())) {
            ContainerChest var2 = var1.field0;
            int var3 = var2.getLowerChestInventory().getSizeInventory();

            for(int var4 = 0; var4 < var3; ++var4) {
               if (!var2.getSlot(var4).getHasStack()) {
                  return;
               }
            }

            if (this.field15.a_(this.field4.method0(1) ? (long)Math.max(200, this.field3) : (long)this.field3) && (!this.field9 || this.cb_.a_((long)this.field2.method3()))) {
               Map var9 = this.method2(var1.field0);
               if (!var9.isEmpty()) {
                  String var6 = this.field4.method4();
                  byte var7 = -1;
                  switch(var6.hashCode()) {
                  case -1529278789:
                     if (var6.equals("Hovered")) {
                        var7 = 1;
                     }
                     break;
                  case 1897755483:
                     if (var6.equals("Vanilla")) {
                        var7 = 0;
                     }
                  }

                  AutoTerminalsModule.Class0 var5;
                  switch(var7) {
                  case 0:
                     var5 = (AutoTerminalsModule.Class0)var9.values().iterator().next();
                     if (AutoTerminalsModule.Class0.access$000(var5).slotNumber < var3 && AutoTerminalsModule.Class0.access$000(var5).slotNumber >= 0) {
                        var5.method0(var1.field0);
                        this.field9 = true;
                        this.cb_.o_();
                        this.field15.o_();
                        this.field3 = this.field11.method3();
                     }
                     break;
                  case 1:
                     Slot var8 = var1.field1;
                     if (var8 != null) {
                        var5 = (AutoTerminalsModule.Class0)var9.get(var8.slotNumber);
                        if (var5 != null && AutoTerminalsModule.Class0.access$000(var5).slotNumber < var3 && AutoTerminalsModule.Class0.access$000(var5).slotNumber >= 0) {
                           var5.method0(var1.field0);
                           this.field9 = true;
                           this.cb_.o_();
                           this.field15.o_();
                           this.field3 = this.field11.method3();
                        }
                     }
                  }

               }
            }
         }
      }
   }

   public AutoTerminalsModule.Class1 method0(ContainerChest var1) {
      String var2 = var1.getLowerChestInventory().getDisplayName().getUnformattedText();
      AutoTerminalsModule.Class1[] var3 = AutoTerminalsModule.Class1.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         AutoTerminalsModule.Class1 var6 = var3[var5];
         if (var6.method0(var2)) {
            return var6;
         }
      }

      return AutoTerminalsModule.Class1.field3;
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      String var2 = var1.message.getUnformattedText().trim();
      if (var2.equals("This menu has been throttled! Please slow down...")) {
         if (!this.field6.method1() || this.field5 || field13 == AutoTerminalsModule.Class1.field3) {
            return;
         }

         this.field5 = true;
         System.out.println("Throttle");
         field58.thePlayer.sendChatMessage(this.field12.method1());
      } else if (this.field8.method1() && var2.startsWith(field58.thePlayer.getName() + " activated a terminal! (")) {
         method2(String.format("Terminal took %.2fs", (double)this.field7.method0() / 1000.0D));
      }

   }

   public boolean method1(ContainerChest var1) {
      AutoTerminalsModule.Class1 var2 = this.method0(var1);
      if (field13 != var2) {
         field13 = var2;
         this.field7.o_();
         return true;
      } else {
         return false;
      }
   }

   public static int field0(Framebuffer var0, int var1, Class354 var2) {
      var0.framebufferClear();
      var0.bindFramebuffer(false);
      var2.method5();
      var2.method0("size", (float)Class434.field6.displayWidth, (float)Class434.field6.displayHeight);
      var2.method0("str", 5.0F);
      GlStateManager.bindTexture(var1);
      CameraModule.method5();
      var2.method2();
      var0.unbindFramebuffer();
      return var0.framebufferTexture;
   }

   public Boolean lambda$new$0() {
      return !this.field6.method1();
   }

   public static int lambda$getNextClick$2(List var0, EnumDyeColor var1) {
      return (int)var0.stream().filter(AutoTerminalsModule::lambda$null$1).count();
   }

   public static Minecraft access$200() {
      return field58;
   }

   public static int lambda$getNextClick$3(Slot var0) {
      return var0.getStack().stackSize;
   }

   public static void method8() {
      Class108.method0();
   }

   public AutoTerminalsModule() {
      super("Auto Terminals", Category.dungeon, SubCategory.floor7, "Completes terminals");
      this.method0((Setting[])(new Setting[]{this.field4, this.field11, this.field2, this.field10, this.field6, this.field12, this.field8}));
   }

   public Map method2(ContainerChest var1) {
      // $FF: Couldn't be decompiled
   }

   public static enum Class1 {
      field0("Click the button on time!"),
      field1("Click in order!"),
      field2("Select all the"),
      field3(""),
      field4("What starts with:"),
      field5("Change all to same color!");

      final String field6;
      private static final AutoTerminalsModule.Class1[] field8 = new AutoTerminalsModule.Class1[]{field2, field1, field4, field7, field0, field5, field3};
      field7("Correct all the panes!");

      private Class1(String var3) {
         this.field6 = var3;
      }

      boolean method0(String var1) {
         return var1.contains(this.field6);
      }
   }

   public static class Class0 {
      private final Slot field0;
      private final int field1;
      private final int field2;

      static Slot access$000(AutoTerminalsModule.Class0 var0) {
         return var0.field0;
      }

      public Class0(Slot var1, int var2, int var3) {
         this.field0 = var1;
         this.field1 = var2;
         this.field2 = var2 == 3 ? 2 : var3;
      }

      public void method0(ContainerChest var1) {
         if (var1.inventorySlots.contains(this.field0)) {
            AutoTerminalsModule.access$200().playerController.windowClick(var1.windowId, this.field0.slotNumber, this.field2, this.field1, AutoTerminalsModule.access$100().thePlayer);
         }
      }

      public Class0(Slot var1) {
         this(var1, 0, 0);
      }
   }
}
