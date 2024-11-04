package oringo.module;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import map.Class119;
import map.Class196;
import map.Class362;
import map.Class447;
import map.Class500;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class217;
import oringo.event.Class468;
import oringo.mixin.MinecraftAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class SpeedModule extends Module {
   public int Y_;
   public final DoubleSetting bL_ = new DoubleSetting("Timer", 1.0D, 1.0D, 3.0D, 0.01D);
   public final DoubleSetting field2 = new DoubleSetting("Sneak timer speed", 1.0D, 1.0D, 3.0D, 0.05D, this::lambda$new$1);
   public static final Class447 field3 = new Class447();
   public final BooleanSetting field4 = new BooleanSetting("Disable on flag", true);
   public final EnumSetting field5 = new EnumSetting("Mode", "Vanilla", new String[]{"Vanilla"});
   public final DoubleSetting field6 = new DoubleSetting("Speed", 1.0D, 0.1D, 5.0D, 0.01D, this::lambda$new$2);
   public final BooleanSetting field7 = new BooleanSetting("Stop on disable", true);
   public final BooleanSetting field8 = new BooleanSetting("Sneak timer", true);

   public static void method0(PrintStream var0, String var1) {
      var0.println(var1);
   }

   public static List method3() {
      if (Oringo.field9.getNetHandler() != null && Oringo.field9.getNetHandler().getPlayerInfoMap() != null) {
         ArrayList var0 = new ArrayList();
         Iterator var1 = Oringo.field9.getNetHandler().getPlayerInfoMap().iterator();

         while(var1.hasNext()) {
            NetworkPlayerInfo var2 = (NetworkPlayerInfo)var1.next();
            if (var2.getDisplayName() != null) {
               String var3 = var2.getDisplayName().getUnformattedText().trim();
               Matcher var4 = Class119.field0.matcher(var3);
               if (var4.find() && !var4.group(1).startsWith(Oringo.field9.thePlayer.getName()) && !var4.group(2).equals("DEAD")) {
                  var0.add(var2);
               }
            }
         }

         return var0;
      } else {
         return null;
      }
   }

   public SpeedModule() {
      super("Speed", Category.movement, SubCategory.movement, "Makes you go faster");
      this.method0((Setting[])(new Setting[]{this.field5, this.field6, this.field7, this.field4, this.bL_, this.field8, this.field2}));
      this.method0((Class500)(this::lambda$new$0));
   }

   @SubscribeEvent
   public void method0(Class468 var1) {
      if (this.method44() && !DragonHitboxesModule.method6()) {
         var1.method0(false);
      }

   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
   }

   public void b_() {
      if (FrozenTreasureESPModule.method5() != null) {
         ((MinecraftAccessor)field58).getTimer().timerSpeed = 1.0F;
      }

      if (field58.thePlayer != null && this.field7.method1()) {
         field58.thePlayer.motionX = 0.0D;
         field58.thePlayer.motionZ = 0.0D;
      }

   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.HIGH
   )
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook && this.field4.method1()) {
         if (!DragonHitboxesModule.method6() && this.method44()) {
            ShortbowTriggerbotModule.method0("Oringo Client", "Disabled speed due to a flag", 1500);
            this.b_();
         }

         field3.o_();
      }

   }

   public static void method0(float var0, float var1) {
      Class196.field5.method5();
      Class196.field5.method0("size", (float)Class196.field2.displayWidth, (float)Class196.field2.displayHeight);
      Class196.field5.method0("glow_size", var0);
      Class196.field5.method0("glow_intensity", var1);
   }

   public String lambda$new$0() {
      return this.field5.method4();
   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      if (this.method44() && !DragonHitboxesModule.method6()) {
         String var2 = this.field5.method4();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -1248403467:
            if (var2.equals("Hypixel")) {
               var3 = 0;
            }
            break;
         case 1897755483:
            if (var2.equals("Vanilla")) {
               var3 = 1;
            }
         }

         switch(var3) {
         case 0:
         default:
            break;
         case 1:
            if (field58.thePlayer.onGround && AutoReconnectModule.method1()) {
               field58.thePlayer.jump();
               var1.method0(field58.thePlayer.motionY);
            }

            Dadudze2Module.method0(var1, this.field6.method0());
         }
      }

   }

   public Boolean lambda$new$2() {
      return !this.field5.method0(0);
   }

   public void method4() {
      this.Y_ = 100;
      if (this.field5.method0(-1) && !((DisablerModule)Class362.method0(DisablerModule.class)).method44()) {
         method2("Enable disabler");
      }

   }

   public Boolean lambda$new$1() {
      return !this.field8.method1();
   }
}
