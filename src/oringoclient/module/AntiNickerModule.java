package oringo.module;

import com.google.gson.JsonParser;
import com.mojang.authlib.properties.Property;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.io.PrintStream;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import map.Class12;
import map.Class198;
import map.Class496;
import map.Class513;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.event.Class270;

public class AntiNickerModule extends Module {
   public static final Set field0 = new HashSet();

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (this.method44() && field58.theWorld != null && var1.phase != Phase.END && (Class496.field7 || Class496.field23)) {
         Iterator var2 = ((List)field58.theWorld.playerEntities.stream().filter(AntiNickerModule::lambda$onTick$1).collect(Collectors.toList())).iterator();

         while(true) {
            String var4;
            String var5;
            do {
               do {
                  do {
                     if (!var2.hasNext()) {
                        return;
                     }

                     EntityPlayer var3 = (EntityPlayer)var2.next();
                     var4 = Class513.method0(var3.getGameProfile());
                     var5 = var3.getName();
                     field0.add(var3.getUniqueID());
                  } while(var4.isEmpty());
               } while(var4.equals(var5));
            } while(var5.contains(" "));

            PopupAnimationModule.method2(var5 + ChatFormatting.RESET + ChatFormatting.GRAY + " is nicked!" + (!var4.equals("Tactful") && !var4.equals("Pickguard") && !var4.equals("Goldapfel") ? " Their real name is " + var4 + "!" : ""));
         }
      }
   }

   public static void lambda$getRealName$0(AtomicReference var0, Entry var1) {
      if (((String)var1.getKey()).equals("textures")) {
         try {
            JsonParser var2 = new JsonParser();
            var0.set(var2.parse(new String(Base64.getDecoder().decode(((Property)var1.getValue()).getValue()))).getAsJsonObject().get("profileName").getAsString());
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   public AntiNickerModule() {
      super("Anti Nicker", Category.other, SubCategory.other, "Reveals nicked players");
   }

   public static void method0(PrintStream var0, String var1) {
      var0.println(var1);
   }

   public static double method0(double var0, double var2) {
      return Math.sqrt(var0 * var0 + var2 * var2);
   }

   @SubscribeEvent
   public void a_(Class270 var1) {
      field0.clear();
   }

   public static boolean lambda$onTick$1(EntityPlayer var0) {
      return var0 instanceof EntityOtherPlayerMP && !AutoMiddleModule.method0((Entity)var0) && field58.getNetHandler().getPlayerInfo(var0.getUniqueID()) != null && !field0.contains(var0.getUniqueID()) && (var0.ticksExisted > 100 || !var0.getDisplayName().getFormattedText().contains("§c"));
   }

   public static Class12 method2() {
      MapData var0 = Class198.method0();
      if (var0 == null) {
         return null;
      } else {
         byte[] var1 = var0.colors;
         int var2 = 0;

         for(int var3 = 0; var3 < var1.length; ++var3) {
            int var4 = var1[var3] / 4;
            if (var4 == 7) {
               ++var2;
            } else if (var4 != 0) {
               var2 = 0;
            } else if (var2 > 0) {
               return new Class12(var3 - var2, var2);
            }
         }

         return null;
      }
   }

   public void b_() {
      field0.clear();
   }
}
