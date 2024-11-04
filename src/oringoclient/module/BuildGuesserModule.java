package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import map.Class348;
import map.Class7;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class BuildGuesserModule extends Module {
   public final ArrayList field0 = new ArrayList();
   public final BooleanSetting as_ = new BooleanSetting("AFK Mode", false);
   public Thread field2 = null;
   public final int field3 = 3200;
   public long field4 = 0L;
   public int field5 = 0;

   public void lambda$onChat$1(ArrayList var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         this.field4 = System.currentTimeMillis();
         Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac " + var3.toLowerCase());
         ShortbowTriggerbotModule.method0("Oringo Client", "Trying: §f" + var3, 2000);

         try {
            Thread.sleep(3200L);
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

   }

   public static void lambda$onGuiOpen$2(GuiOpenEvent var0) {
      try {
         Thread.sleep(1000L);
         if (var0.gui instanceof GuiChest) {
            Minecraft var1 = Minecraft.getMinecraft();
            ScoreObjective var2 = var1.thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(0);
            if (ChatFormatting.stripFormatting((var2 == null ? var1.thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(1) : var2).getDisplayName()).contains("GUESS THE BUILD")) {
               var1.playerController.windowClick(((GuiChest)var0.gui).inventorySlots.windowId, 15, 0, 0, var1.thePlayer);
               Thread.sleep(2000L);
               KeyBinding.setKeyBindState(var1.gameSettings.keyBindSneak.getKeyCode(), true);
               Thread.sleep(2000L);
               KeyBinding.setKeyBindState(var1.gameSettings.keyBindSneak.getKeyCode(), false);
               var1.thePlayer.inventory.currentItem = 1;
               var1.thePlayer.rotationPitch = 40.0F;
               Thread.sleep(500L);
               AutoQuizModule.f_();
            }
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public void lambda$onChat$0(ArrayList var1) {
      try {
         Thread.sleep(3200L - (System.currentTimeMillis() - this.field4));
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac " + ((String)var1.get(0)).toLowerCase());
   }

   public static void method0(int var0) {
      if (!Class7.field5.isEmpty()) {
         HClipModule.method0((Class348)Class7.field5.get(var0));
      }
   }

   public ArrayList e_(String var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.field0.iterator();

      while(true) {
         String var4;
         do {
            if (!var3.hasNext()) {
               return var2;
            }

            var4 = (String)var3.next();
         } while(var4.length() != var1.length());

         boolean var5 = true;

         for(int var6 = 0; var6 < var4.length(); ++var6) {
            if (var1.charAt(var6) == '_') {
               if (var4.charAt(var6) != ' ') {
                  continue;
               }

               var5 = false;
            }

            if (var1.charAt(var6) != var4.charAt(var6)) {
               var5 = false;
            }

            if (!var5) {
               break;
            }
         }

         if (var5) {
            var2.add(var4);
         }
      }
   }

   public void method2() {
      try {
         BufferedReader var1 = new BufferedReader(new InputStreamReader(Oringo.class.getClassLoader().getResourceAsStream("words.txt")));

         String var2;
         while((var2 = var1.readLine()) != null) {
            this.field0.add(var2);
         }

         var1.close();
      } catch (Exception var3) {
         var3.printStackTrace();
         PopupAnimationModule.method2("Couldn't load word list!");
      }

   }

   @SubscribeEvent
   public void method1(GuiOpenEvent var1) {
      if (this.method44() && this.as_.method1()) {
         (new Thread(BuildGuesserModule::lambda$onGuiOpen$2)).start();
      }
   }

   public int method1(String var1) {
      return var1.replaceAll(" ", "").replaceAll("_", "").length();
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (this.method44()) {
         try {
            ScoreObjective var2 = Minecraft.getMinecraft().thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(0);
            if (ChatFormatting.stripFormatting((var2 == null ? Minecraft.getMinecraft().thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(1) : var2).getDisplayName()).contains("GUESS THE BUILD") && var1.message.getUnformattedText().startsWith("This game has been recorded")) {
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/play build_battle_guess_the_build");
            }
         } catch (Exception var5) {
         }

         if (var1.message.getFormattedText().startsWith("§eThe theme was") && this.field2 != null) {
            this.field2.stop();
            this.field2 = null;
         }

         if (var1.message.getUnformattedText().startsWith(Minecraft.getMinecraft().thePlayer.getName() + " correctly guessed the theme!") && this.field2 != null) {
            this.field2.stop();
            this.field2 = null;
         }

         if (var1.type == 2) {
            if (var1.message.getFormattedText().contains("The theme is") && var1.message.getFormattedText().contains("_")) {
               if (this.field0.isEmpty()) {
                  this.method2();
               }

               int var6 = this.method1(var1.message.getFormattedText());
               if (var6 != this.field5) {
                  this.field5 = var6;
                  String var3 = var1.message.getUnformattedText().replaceFirst("The theme is ", "");
                  ArrayList var4 = this.e_(var3);
                  if (var4.size() == 1) {
                     ShortbowTriggerbotModule.method0("Oringo Client", "Found 1 matching word! Sending: §f" + (String)var4.get(0), 2000);
                     if (this.field2 != null) {
                        this.field2.stop();
                        this.field2 = null;
                        (new Thread(this::lambda$onChat$0)).start();
                        return;
                     }

                     Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac " + ((String)var4.get(0)).toLowerCase());
                     return;
                  }

                  ShortbowTriggerbotModule.method0("Oringo Client", String.format("Found %s matching words!", var4.size()), 1500);
                  if (var4.size() <= 15) {
                     Collections.shuffle(var4);
                     if (this.field2 != null) {
                        this.field2.stop();
                     }

                     (this.field2 = new Thread(this::lambda$onChat$1)).start();
                  }
               }
            } else {
               this.field5 = 0;
            }
         }

      }
   }

   public BuildGuesserModule() {
      super("Build Guesser", Category.other, SubCategory.other, (String)null);
      this.method0(new Setting[]{this.as_});
   }
}
