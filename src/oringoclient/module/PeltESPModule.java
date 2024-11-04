package oringo.module;

import com.google.gson.JsonObject;
import java.awt.Color;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import map.Class28;
import map.Class447;
import map.Class496;
import map.Class518;
import map.Class528;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.event.ClickEvent;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.io.FileUtils;
import oringo.Oringo;
import oringo.event.Class125;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class PeltESPModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Warp to trapper on kill", true);
   public final HashSet field1 = new HashSet();
   public final BooleanSetting field2 = new BooleanSetting("Tracers", true);
   public final Class447 field3 = new Class447();
   public final BooleanSetting field4 = new BooleanSetting("Auto Accept", true);
   public final EnumSetting field5 = new EnumSetting("Mode", "ESP", new String[]{"ESP", "Outline"});
   public final String[] field6 = new String[]{"100:§f", "500:§a", "1000:§9", "5000:§5", "10000:§6"};
   public final BooleanSetting field7 = (BooleanSetting)(new BooleanSetting("See all animals", false)).method2("Shows other players' trapper mobs");
   public boolean field8;

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (this.field4.method1() && Class496.field8) {
         String var2 = var1.message.getUnformattedText();
         if (var2.startsWith("\nAccept the trapper")) {
            Iterator var3 = var1.message.getSiblings().iterator();

            while(var3.hasNext()) {
               IChatComponent var4 = (IChatComponent)var3.next();
               ClickEvent var5 = var4.getChatStyle().getChatClickEvent();
               if (var5 != null && var5.getValue().endsWith("YES")) {
                  field58.thePlayer.sendChatMessage(var5.getValue());
               }
            }
         } else if (var2.startsWith("Return to the Trapper soon to get a new animal to hunt!") && this.field0.method1()) {
            this.field3.o_();
            this.field8 = true;
         }

      }
   }

   @SubscribeEvent
   public void method0(Class125 var1) {
      if (Class496.field8 && this.field5.method0(1) && (Class496.field20 || this.field7.method1())) {
         if (var1.field4.ticksExisted > 30 && var1.field4 instanceof EntityAnimal && !(var1.field4 instanceof EntityWolf) && !var1.field4.isDead) {
            Optional var2 = Arrays.stream(this.field6).filter(PeltESPModule::lambda$onRender$0).findFirst();
            if (!var2.isPresent()) {
               return;
            }

            MinigameAimbotModule.method0(var1, ScaffoldModule.j_(((String)var2.get()).split(":")[1]));
         }

      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (Class496.field20 || this.field7.method1() && Class496.field8) {
         Iterator var2 = field58.theWorld.getEntities(EntityAnimal.class, PeltESPModule::lambda$onRender$1).iterator();

         while(var2.hasNext()) {
            EntityAnimal var3 = (EntityAnimal)var2.next();
            Optional var4 = Arrays.stream(this.field6).filter(PeltESPModule::lambda$onRender$2).findFirst();
            if (var4.isPresent()) {
               Color var5 = ScaffoldModule.j_(((String)var4.get()).split(":")[1]);
               if (this.field2.method1()) {
                  Class528.method0(var3, var1.partialTicks, 1.0F, var5);
               }

               if (this.field5.method0(0) && Class210.method0((Entity)var3)) {
                  AutoIceFillModule.method0(var3, var1.partialTicks, var5);
               }
            }
         }
      }

   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.field7.method1() && Class496.field8) {
         if (var1.field0 instanceof S13PacketDestroyEntities) {
            int[] var2 = ((S13PacketDestroyEntities)var1.field0).getEntityIDs();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               int var5 = var2[var4];
               Entity var6 = field58.theWorld.getEntityByID(var5);
               if (var6 instanceof EntityAnimal) {
                  float var7 = Class518.method0((EntityLivingBase)var6);
                  if (Arrays.stream(this.field6).anyMatch(PeltESPModule::lambda$onPacket$3)) {
                     this.field1.add(var5);
                     var1.method9();
                  }
               }
            }
         }

      }
   }

   public static boolean lambda$onRender$1(EntityAnimal var0) {
      return var0.ticksExisted > 30 && !(var0 instanceof EntityWolf);
   }

   public static void A_(String var0) {
      HostageESPModule.method0(var0, MessageType.WARNING);
   }

   public void b_() {
      Iterator var1 = this.field1.iterator();

      while(var1.hasNext()) {
         Integer var2 = (Integer)var1.next();
         field58.theWorld.removeEntityFromWorld(var2);
      }

      this.field1.clear();
   }

   public static boolean lambda$onPacket$3(float var0, Entity var1, String var2) {
      return var2.split(":")[0].equals(String.valueOf((int)var0 / (var1 instanceof EntityHorse ? 2 : 1)));
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field1.clear();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field58.thePlayer != null && this.field3.a_(1500L)) {
         if (this.field8) {
            this.field8 = false;
            field58.thePlayer.sendChatMessage("/warp trapper");
         }

      }
   }

   public static boolean lambda$onRender$0(Class125 var0, String var1) {
      return String.valueOf((int)Class518.method0(var0.field4) / (var0.field4 instanceof EntityHorse ? 2 : 1)).equals(var1.split(":")[0]);
   }

   public static boolean lambda$onRender$2(EntityAnimal var0, String var1) {
      return String.valueOf((int)Class518.method0(var0) / (var0 instanceof EntityHorse ? 2 : 1)).equals(var1.split(":")[0]);
   }

   public static void method5() {
      Class28.field3.clear();
      Class28.field1.clear();
      Class28.field2.clear();
      File var0 = new File(Class28.field4);
      if (var0.exists()) {
         try {
            FileReader var1 = new FileReader(var0);
            Throwable var2 = null;

            try {
               JsonObject var3 = (JsonObject)Class28.field0.fromJson(var1, JsonObject.class);
               var3.getAsJsonArray("rooms").forEach(Class28::lambda$load$1);
               var3.getAsJsonArray("floors").forEach(Class28::lambda$load$3);
            } catch (Throwable var30) {
               var2 = var30;
               throw var30;
            } finally {
               if (var1 != null) {
                  if (var2 != null) {
                     try {
                        var1.close();
                     } catch (Throwable var29) {
                        var2.addSuppressed(var29);
                     }
                  } else {
                     var1.close();
                  }
               }

            }
         } catch (Exception var34) {
            var34.printStackTrace();
         }
      } else {
         File var35 = new File(Oringo.field5 + "brush/brushes.json");
         if (var35.exists()) {
            try {
               FileReader var36 = new FileReader(var35);
               Throwable var37 = null;

               try {
                  JsonObject var4 = (JsonObject)Class28.field0.fromJson(var36, JsonObject.class);
                  var4.getAsJsonArray("rooms").forEach(Class28::lambda$load$5);
                  var4.getAsJsonObject("floors").entrySet().forEach(Class28::lambda$load$7);
               } catch (Throwable var28) {
                  var37 = var28;
                  throw var28;
               } finally {
                  if (var36 != null) {
                     if (var37 != null) {
                        try {
                           var36.close();
                        } catch (Throwable var27) {
                           var37.addSuppressed(var27);
                        }
                     } else {
                        var36.close();
                     }
                  }

               }
            } catch (IOException var32) {
               var32.printStackTrace();
            }

            FileUtils.deleteQuietly(var35);
            PestESPModule.n_();
         }
      }

      Class28.field3.forEach(Class28::lambda$load$8);
   }

   public PeltESPModule() {
      super("Pelt ESP", Category.skyblock, SubCategory.visual);
      this.method0((Setting[])(new Setting[]{this.field5, this.field2, this.field4, this.field0, this.field7}));
   }
}
