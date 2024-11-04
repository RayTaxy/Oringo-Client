package oringo.module;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import map.Class265;
import map.Class305;
import map.Class34;
import map.Class349;
import map.Class376;
import map.Class392;
import map.Class417;
import map.Class92;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import oringo.command.ReplyCommand;
import oringo.mixin.MinecraftAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class AutoSumoModule extends Module {
   public static EntityPlayer field0 = null;
   public static Thread aX_ = null;
   public static int field2 = -1;
   public BooleanSetting field3 = new BooleanSetting("Skip no loses", true);
   public static ArrayList field4 = new ArrayList();
   public final StringSetting field5 = new StringSetting("Webhook");

   public static boolean lambda$null$1(EntityPlayer var0) {
      return !var0.isInvisible();
   }

   public void method4() {
      if (aX_ != null) {
         aX_.stop();
         aX_ = null;
         ShortbowTriggerbotModule.method0("Oringo Client", "AutoSumo has been disabled!", 1000);
      } else {
         if (this.field5.method1().length() < 5) {
            ShortbowTriggerbotModule.method0("Oringo Client", "You need to set a webhook", 2500);
            this.method40();
            return;
         }

         (aX_ = new Thread(AutoSumoModule::lambda$onEnable$2)).start();
         ShortbowTriggerbotModule.method0("Oringo Client", "AutoSumo has been enabled!", 1000);
      }

   }

   public static void lambda$onEnable$2() {
      if (!Class376.method0("Mode Winstreak:") && !Class376.method0("Players: 2/2")) {
         Minecraft.getMinecraft().thePlayer.sendChatMessage("/play duels_sumo_duel");
      }

      int var0 = 0;
      boolean var1 = false;
      boolean var2 = false;

      while(true) {
         while(true) {
            while(true) {
               while(true) {
                  while(true) {
                     try {
                        Thread.sleep(50L);
                        ++var0;
                        KeyBinding.setKeyBindState(31, false);
                        if (Class376.method0("Players: 2/2") || Class376.method0("Waiting..") && !Class376.method0("Starting in 2s") && !Class376.method0("Starting in 1s")) {
                           field0 = null;
                           KeyBinding.setKeyBindState(17, true);
                           KeyBinding.setKeyBindState(57, true);
                           Minecraft.getMinecraft().thePlayer.rotationYaw = 90.0F + 30.0F * (float)Math.sin(Math.toRadians((double)((int)(System.currentTimeMillis() % 1800L) / 5)));
                           Minecraft.getMinecraft().thePlayer.rotationPitch = 12.3F;
                           field2 = -1;
                           if ((new Random()).nextInt(10) == 0) {
                              Class349.field1 = true;
                           }

                           var2 = true;
                        }

                        if (Class376.method0("Starting in 2s") || Class376.method0("Starting in 1s")) {
                           KeyBinding.setKeyBindState(field58.gameSettings.keyBindForward.getKeyCode(), false);
                           KeyBinding.setKeyBindState(field58.gameSettings.keyBindJump.getKeyCode(), false);
                        }

                        if (field2 > 0) {
                           --field2;
                        }

                        if (!Class376.method0("Mode Winstreak:")) {
                           if (!var2) {
                              KeyBinding.setKeyBindState(field58.gameSettings.keyBindForward.getKeyCode(), false);
                           }

                           KeyBinding.setKeyBindState(field58.gameSettings.keyBindLeft.getKeyCode(), false);
                           KeyBinding.setKeyBindState(field58.gameSettings.keyBindRight.getKeyCode(), false);

                           for(int var8 = 0; var8 < 100 && !Class376.method0("Mode Winstreak:") && !Class376.method0("Players: 2/2"); ++var8) {
                              Thread.sleep(50L);
                           }

                           if (!Class376.method0("Mode Winstreak:") && !Class376.method0("Players: 2/2")) {
                              Minecraft.getMinecraft().thePlayer.sendChatMessage("/play duels_sumo_duel");
                           }
                        } else {
                           long var3 = System.currentTimeMillis();
                           if (var2) {
                              var2 = false;
                              KeyBinding.setKeyBindState(57, false);
                           }

                           if (!Minecraft.getMinecraft().thePlayer.capabilities.isFlying) {
                              KeyBinding.setKeyBindState(17, true);
                              if ((new Random()).nextInt(7) == 1) {
                                 int var5 = Class92.method0((Entity)field0);
                                 if (var5 != -1) {
                                    int var6 = var5 == 0 ? 30 : 32;
                                    KeyBinding.setKeyBindState(var6, true);
                                    (new Thread(AutoSumoModule::lambda$null$0)).start();
                                 }
                              }

                              if (Minecraft.getMinecraft().theWorld.playerEntities.stream().filter(AutoSumoModule::lambda$null$1).count() == 2L) {
                                 Iterator var9 = Minecraft.getMinecraft().theWorld.playerEntities.iterator();

                                 while(true) {
                                    if (var9.hasNext()) {
                                       EntityPlayer var11 = (EntityPlayer)var9.next();
                                       if (Math.abs(var11.posY - Minecraft.getMinecraft().thePlayer.posY) >= 2.0D || var11.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) >= 12.0F || var11.isInvisible() || var11.equals(Minecraft.getMinecraft().thePlayer)) {
                                          continue;
                                       }

                                       field0 = var11;
                                    }

                                    if (field0 == null) {
                                       break;
                                    }

                                    if (var0 % 8 <= 1 && Class305.method0()) {
                                       KeyBinding.setKeyBindState(17, false);
                                    }

                                    if (Minecraft.getMinecraft().thePlayer.getDistance(field0.posX, Minecraft.getMinecraft().thePlayer.posY, field0.posZ) < 1.0D) {
                                       KeyBinding.setKeyBindState(17, false);
                                    }

                                    if (Class417.method0(field0.posX, field0.posY, field0.posZ)) {
                                       if (field2 == -1) {
                                          field2 = 40;
                                       }

                                       KeyBinding.setKeyBindState(field58.gameSettings.keyBindLeft.getKeyCode(), false);
                                       KeyBinding.setKeyBindState(field58.gameSettings.keyBindRight.getKeyCode(), false);
                                       if (field0.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) < 5.0F) {
                                          KeyBinding.setKeyBindState(field58.gameSettings.keyBindForward.getKeyCode(), false);
                                          if (field2 != 0) {
                                             KeyBinding.setKeyBindState(field58.gameSettings.keyBindBack.getKeyCode(), true);
                                          }
                                       }
                                    }

                                    if (!Minecraft.getMinecraft().theWorld.playerEntities.contains(field0)) {
                                       break;
                                    }

                                    if (var0++ % 4 != 0 && (new Random()).nextInt(5) != 0 || !var1 && Class305.method0()) {
                                       Class349.field1 = true;
                                    }

                                    var1 = Class305.method0();
                                    if ((double)Minecraft.getMinecraft().thePlayer.getDistanceToEntity(field0) > 0.4D && !Class417.method0(field0.posX, field0.posY, field0.posZ)) {
                                       Class34 var10 = ReplyCommand.method0((new Vec3(field0.posX - 0.33D, Math.max(Math.min(field0.posY, Minecraft.getMinecraft().thePlayer.posY), field0.posY - (double)field0.getEyeHeight()), field0.posZ - 0.33D)).add(new Vec3((new Random()).nextDouble() * 0.1D - 0.05D, (new Random()).nextDouble() * 0.1D - 0.05D + (double)field0.getEyeHeight() - 0.4D, (new Random()).nextDouble() * 0.1D - 0.05D)));
                                       field58.thePlayer.rotationYaw = var10.method5();
                                       field58.thePlayer.rotationPitch = var10.method2();
                                    }

                                    if (var3 - System.currentTimeMillis() > 3L) {
                                       PopupAnimationModule.method2("Lag! " + (var3 - System.currentTimeMillis()));
                                    }
                                    break;
                                 }
                              }
                           }
                        }
                     } catch (Exception var7) {
                        var7.printStackTrace();
                     }
                  }
               }
            }
         }
      }
   }

   public static void lambda$null$0(int var0) {
      try {
         Thread.sleep((long)(500 + (new Random()).nextInt(300)));
         KeyBinding.setKeyBindState(var0, false);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

   }

   public static void method5() {
      Class265.field0.mkdirs();
      File[] var0 = Class265.field0.listFiles();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         File var3 = var0[var2];
         String var4 = var3.getName();
         int var5 = var4.lastIndexOf(46);
         if (var5 != -1) {
            var4 = var4.substring(0, var5);
         }

         Class392.field4.add(var4);
      }

      try {
         Files.write((new File(Class392.field3, "pack.mcmeta")).toPath(), "{\n \"pack\": {\n   \"description\": \"\",\n   \"pack_format\": 1\n }\n}".getBytes(StandardCharsets.UTF_8), new OpenOption[]{StandardOpenOption.CREATE});
      } catch (IOException var6) {
         var6.printStackTrace();
      }

      FolderResourcePack var7 = new FolderResourcePack(Class392.field3);
      ((MinecraftAccessor)Minecraft.getMinecraft()).getDefaultResourcePacks().add(var7);
   }

   public void b_() {
      if (aX_ != null) {
         aX_.stop();
         aX_ = null;
         ShortbowTriggerbotModule.method0("Oringo Client", "AutoSumo has been disabled!", 1000);
      }

   }

   public AutoSumoModule() {
      super("Auto Sumo", Category.other, SubCategory.other, (String)null);
      this.method0(new Setting[]{this.field5});
   }
}
