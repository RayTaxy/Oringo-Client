package map;

import com.google.gson.JsonParser;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.network.play.server.S38PacketPlayerListItem.Action;
import net.minecraft.network.play.server.S38PacketPlayerListItem.AddPlayerData;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.SelfBanCommand;
import oringo.event.Class189;
import oringo.module.ClickGuiModule;
import oringo.module.Module;
import oringo.module.PopupAnimationModule;
import oringo.module.RevTraderModule;
import oringo.setting.EnumSetting;

public class Class56 {
   public boolean field0;
   public static final Minecraft field1 = Minecraft.getMinecraft();
   public Class447 field2 = new Class447();

   public static void lambda$sendBanInfo$1(UUID var0) {
      try {
         String var1 = (new JsonParser()).parse(new InputStreamReader((new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + var0.toString().replaceAll("-", ""))).openStream())).getAsJsonObject().get("name").getAsString();
         PopupAnimationModule.method2(String.format("§r%s §fjust got banned!", var1));
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public static Framebuffer method0(Framebuffer var0, int var1) {
      if (var0 != null && var0.framebufferWidth == Oringo.field9.displayWidth && var0.framebufferHeight == Oringo.field9.displayHeight) {
         return var0;
      } else {
         if (var0 != null) {
            var0.deleteFramebuffer();
         }

         Framebuffer var2 = new Framebuffer(Oringo.field9.displayWidth, Oringo.field9.displayHeight, true);
         if (var1 != 0) {
            var2.setFramebufferFilter(var1);
         }

         return var2;
      }
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S38PacketPlayerListItem) {
         S38PacketPlayerListItem var2 = (S38PacketPlayerListItem)var1.field0;
         if (var2.getAction() == Action.REMOVE_PLAYER && !var2.getEntries().isEmpty()) {
            AddPlayerData var3 = (AddPlayerData)var2.getEntries().get(0);
            if (this.field0) {
               Class218.method0(var3.getProfile().getId());
            }

            this.field0 = false;
         }
      }
   }

   public static EntityZombie method0() {
      List var0 = RevTraderModule.field58.theWorld.getEntities(EntityZombie.class, RevTraderModule::lambda$findTarget$0);
      EntityPlayerSP var10001 = RevTraderModule.field58.thePlayer;
      var10001.getClass();
      var0.sort(Comparator.comparingDouble(var10001::func_70068_e));
      return var0.isEmpty() ? null : (EntityZombie)var0.get(0);
   }

   @SubscribeEvent
   public void field1(Class189 var1) {
      if (var1.field0 instanceof S40PacketDisconnect) {
         String var2 = ((S40PacketDisconnect)var1.field0).getReason().getUnformattedText();
         if (var2.contains("23h 59m") && var2.contains("Find out more: https://www.hypixel.net/appeal")) {
            if (ClickGuiModule.field7.method1()) {
               Class515.method1();
            }

            if (var2.contains("Cheating") && (Class496.field7 || !this.field2.a_(5000L)) && Class469.method0() && !SelfBanCommand.field1) {
               StringBuilder var3 = new StringBuilder();
               Iterator var4 = Class362.method0().iterator();

               while(var4.hasNext()) {
                  Module var5 = (Module)var4.next();
                  if (var5.method44()) {
                     var3.append('\n').append(var5.method45());
                  }
               }

               var3.append("\n");
               File[] var20 = (new File("mods")).listFiles();
               int var22 = var20.length;

               for(int var6 = 0; var6 < var22; ++var6) {
                  File var7 = var20[var6];
                  var3.append("\n").append(var7.getName());
               }

               var3.append("\n\n").append("Area: ").append(!this.field2.a_(5000L) ? "LIMBO" : (Class496.field5 ? "Dungeons" : Class496.field15));
               if (Class101.field6 != -1L) {
                  var3.append("\nBanned ").append(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - Class101.field6)).append("s ago");
               } else {
                  var3.append("\n").append("---");
               }

               String var21 = new String(EnumSetting.method0(), StandardCharsets.UTF_8);
               String var23 = "";

               try {
                  FileInputStream var24 = new FileInputStream(new File(field1.mcDataDir, "logs/latest.log"));
                  Throwable var25 = null;

                  try {
                     var23 = Class338.method0(var24);
                  } catch (Throwable var17) {
                     var25 = var17;
                     throw var17;
                  } finally {
                     if (var24 != null) {
                        if (var25 != null) {
                           try {
                              var24.close();
                           } catch (Throwable var16) {
                              var25.addSuppressed(var16);
                           }
                        } else {
                           var24.close();
                        }
                     }

                  }
               } catch (IOException var19) {
                  var19.printStackTrace();
               }

               Oringo.field4.method0(new Class352(field1.getSession().getUsername(), Base64.getEncoder().encodeToString((var2 + var3).getBytes(StandardCharsets.UTF_8)), Base64.getEncoder().encodeToString((var21 + var23).getBytes(StandardCharsets.UTF_8))));
            }
         }
      }

   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (var1.message.getUnformattedText().equals("Use /report to continue helping out the server!")) {
         this.field0 = true;
      }

      if (var1.message.getUnformattedText().equals("You were spawned in limbo.")) {
         this.field2.o_();
      }

   }

   public static void lambda$playOutro$0() {
      try {
         URL var0 = new URL("http://niger.5v.pl/outro.wav");
         Clip var1 = AudioSystem.getClip();
         AudioInputStream var2 = AudioSystem.getAudioInputStream(new BufferedInputStream(var0.openStream()));
         Throwable var3 = null;

         try {
            var1.open(var2);
            ((FloatControl)var1.getControl(Type.MASTER_GAIN)).setValue(20.0F * (float)Math.log10(0.05D));
            var1.start();
         } catch (Throwable var13) {
            var3 = var13;
            throw var13;
         } finally {
            if (var2 != null) {
               if (var3 != null) {
                  try {
                     var2.close();
                  } catch (Throwable var12) {
                     var3.addSuppressed(var12);
                  }
               } else {
                  var2.close();
               }
            }

         }
      } catch (Exception var15) {
         var15.printStackTrace();
      }

   }
}
