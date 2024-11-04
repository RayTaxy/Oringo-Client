package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class198;
import map.Class395;
import map.Class447;
import map.Class453;
import map.Class496;
import map.Class513;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class290;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class KillInsultsModule extends Module {
   public static final Class447 cZ_ = new Class447();
   public String D_ = null;
   public static final List field2 = new ArrayList();
   public static int field0;
   public static final BooleanSetting field4 = new BooleanSetting("Only >=300 -> <300", false, KillInsultsModule::lambda$static$0);
   public static final ButtonSetting field5 = new ButtonSetting("Open", KillInsultsModule::lambda$static$1);
   public static final ButtonSetting field6 = new ButtonSetting("Reload", Class453::method9);
   public final Class447 field7 = new Class447();
   public static final EnumSetting field8 = new EnumSetting("Mode", "Dungeon", new String[]{"Dungeon", "Normal"});
   public static final Pattern field9 = Pattern.compile("^ ☠ .+ and became a ghost\\.$");

   public static Boolean lambda$static$0() {
      return !field8.method0(0);
   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
      if (Class198.field0.method3() < 300 && !this.field7.a_(2000L) && this.D_ != null) {
         this.field7.method0(-3000L);
         if (Class496.field5) {
            Class513.method0(this.D_, "/pc ");
         }
      }

   }

   public KillInsultsModule() {
      super("Kill Insults", Category.other, SubCategory.other, (String)null);
      this.method0((Setting[])(new Setting[]{field8, field4, field5, field6}));
      Class453.method9();
   }

   public static void lambda$static$1() {
      try {
         Desktop.getDesktop().edit(new File(Oringo.field5 + "insults.txt"));
      } catch (Exception var1) {
      }

   }

   @SubscribeEvent
   public void method0(Class290 var1) {
      if (this.method44() && field8.method0(1)) {
         Class513.method0(ChatFormatting.stripFormatting(var1.field0.getName()), "/ac ");
      }

   }

   public static boolean method7() {
      return !Class395.field0.thePlayer.isUsingItem() && !Class395.field0.playerController.getIsHittingBlock();
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void method0(ClientChatReceivedEvent var1) {
      if (this.method44() && field8.method0(0) && Class496.field5) {
         String var2 = var1.message.getUnformattedText();
         Matcher var3 = field9.matcher(var2);
         if (var3.find()) {
            String[] var4 = var2.split(" ");
            if (var4.length > 2) {
               String var5 = var4[2];
               if (!var5.equals("You") && !var5.equals(field58.thePlayer.getName())) {
                  if (field4.method1()) {
                     if (Class198.field0.method3() < 300) {
                        return;
                     }

                     this.D_ = var5;
                     this.field7.o_();
                     return;
                  }

                  Class513.method0(var5, "/pc ");
               }
            }
         }
      }

   }
}
