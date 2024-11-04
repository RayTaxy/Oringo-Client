package map;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.DataWatcher.WatchableObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Rotations;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.Oringo;
import oringo.command.DebugCommand;
import oringo.command.XRayCommand;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.event.Class332;
import oringo.event.Class439;
import oringo.mixin.S3BPacketScoreboardObjectiveAccessor;
import oringo.module.AntibotModule;
import oringo.module.AutoBlazeModule;
import oringo.module.AutoToolModule;
import oringo.module.DragonHitboxesModule;
import oringo.module.NoRenderModule2;
import oringo.module.PopupAnimationModule;

public class Class496 {
   public static boolean field0;
   public static boolean field1;
   public static String field2;
   public static boolean field3;
   public static boolean field4;
   public static boolean field5;
   public static boolean field6;
   public static boolean field7;
   public static boolean field8;
   public static boolean field9;
   public static final ArrayList field10;
   public static boolean field11;
   public static int field12;
   public static boolean field13;
   public static boolean field14;
   public static String field15;
   public static boolean field16;
   public static boolean field17;
   public static boolean field18;
   public static RenderGameOverlayEvent field19;
   public static boolean field20;
   public static boolean field21;
   public static final ArrayList field22;
   public static boolean field23;
   public static final List field24;
   public static boolean field25;
   public static final Minecraft field26;
   public static boolean field27;

   public static boolean field0(Class189 var0, String var1) {
      return var0.field0.getClass().getSimpleName().toLowerCase().contains(var1.toLowerCase());
   }

   @SubscribeEvent
   public void method0(Pre var1) {
      if (var1.type == ElementType.ALL) {
         field19 = var1;
      }

   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      String var2 = var1.message.getUnformattedText();
      if (var2.startsWith("Profile ID: ")) {
         field2 = var2.split(" ")[2];
      }

   }

   public static boolean lambda$anyTab$4(String var0, NetworkPlayerInfo var1) {
      return var1.getDisplayName() != null && var1.getDisplayName().getUnformattedText().toLowerCase().contains(var0.toLowerCase(Locale.ROOT));
   }

   public static void lambda$static$0() {
      try {
         HttpsURLConnection var0 = (HttpsURLConnection)(new URL("https://api.hypixel.net/resources/skyblock/election")).openConnection();
         JsonObject var1 = (new JsonParser()).parse(new InputStreamReader(var0.getInputStream())).getAsJsonObject();
         field17 = var1.getAsJsonObject("mayor").getAsJsonPrimitive("name").getAsString().equals("Paul");
      } catch (Exception var2) {
         if (Oringo.cV_) {
            var2.printStackTrace();
         }
      }

   }

   public static boolean lambda$onPacketDev$2(Class332 var0, String var1) {
      return var0.field0.getClass().getSimpleName().toLowerCase().contains(var1.toLowerCase());
   }

   public static void lambda$onTick$3(String var0) {
      NoRenderModule2.method0("§cDebugger §3» §f" + var0);
   }

   public static void method0(PacketBuffer var0, WatchableObject var1) {
      int var2 = (var1.getObjectType() << 5 | var1.getDataValueId() & 31) & 255;
      var0.writeByte(var2);
      switch(var1.getObjectType()) {
      case 0:
         var0.writeByte((Byte)var1.getObject());
         break;
      case 1:
         var0.writeShort((Short)var1.getObject());
         break;
      case 2:
         var0.writeInt((Integer)var1.getObject());
         break;
      case 3:
         var0.writeFloat((Float)var1.getObject());
         break;
      case 4:
         var0.writeString((String)var1.getObject());
         break;
      case 5:
         ItemStack var3 = (ItemStack)var1.getObject();
         var0.writeItemStackToBuffer(var3);
         break;
      case 6:
         BlockPos var4 = (BlockPos)var1.getObject();
         var0.writeInt(var4.getX());
         var0.writeInt(var4.getY());
         var0.writeInt(var4.getZ());
         break;
      case 7:
         Rotations var5 = (Rotations)var1.getObject();
         var0.writeFloat(var5.getX());
         var0.writeFloat(var5.getY());
         var0.writeFloat(var5.getZ());
      }

   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (field26.theWorld != null && var1.phase == Phase.END) {
         DragonHitboxesModule.method5();
         if (Oringo.cV_ && !field10.isEmpty()) {
            (new ArrayList(field10)).forEach(Class496::lambda$onTick$3);
            field10.clear();
         }

         Iterator var2 = field26.theWorld.loadedEntityList.iterator();

         while(var2.hasNext()) {
            Entity var3 = (Entity)var2.next();
            if (var3.ticksExisted == 0) {
               (new Class439(var3)).method7();
            }
         }

         Class262.method1();
         String var8 = XRayCommand.method2();
         var8 = var8 == null ? "" : var8.trim();
         field7 = AutoBlazeModule.method1();
         field5 = field7 && Class376.method0("The Catacombs") || (field26.isSingleplayer() || Class376.method0("Anticheat:")) && Oringo.cV_;
         field18 = field7 && Class376.method0("Kuudra's Hollow");
         field21 = field7 && Class376.method0("Rift Dimension");
         field23 = Class376.method0("Map");
         field20 = false;
         field15 = "UNKNOWN";
         Iterator var9 = field26.getNetHandler().getPlayerInfoMap().iterator();

         while(var9.hasNext()) {
            NetworkPlayerInfo var4 = (NetworkPlayerInfo)var9.next();
            if (var4 != null) {
               IChatComponent var5 = var4.getDisplayName();
               if (var5 != null && var4.getGameProfile().getName().length() == 4 && var4.getGameProfile().getName().charAt(0) == '!') {
                  List var6 = var5.getSiblings();
                  if (var6.size() == 3 && ((IChatComponent)var6.get(1)).getUnformattedTextForChat().contains("Time Left:")) {
                     field20 = true;
                  }

                  if (var6.size() == 2) {
                     String var7 = ((IChatComponent)var6.get(0)).getUnformattedTextForChat();
                     if (var7.startsWith("Area")) {
                        field15 = ((IChatComponent)var6.get(1)).getUnformattedTextForChat();
                     }

                     if (var7.startsWith(" Server")) {
                        Class357.field3 = ((IChatComponent)var6.get(1)).getUnformattedTextForChat();
                     }
                  }
               }
            }
         }

         field1 = field15.equals("Crystal Hollows");
         field8 = field15.equals("The Farming Islands");
         field4 = var8.equals("BED WARS") && !Class376.method0("Coins: ");
         field25 = var8.equals("DUELS");
         field9 = var8.equals("SKYWARS") && Class376.method0("Mode: ");
         field6 = field15.equals("Garden");
         field27 = field15.equals("Private Island");
      }

   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S2DPacketOpenWindow) {
         field12 = ((S2DPacketOpenWindow)var1.field0).getWindowId();
      }

      if (var1.field0 instanceof S3EPacketTeams && ((S3EPacketTeams)var1.field0).getName().startsWith("team")) {
         Class82.field8 = true;
      }

      String var2;
      if (var1.field0 instanceof S3BPacketScoreboardObjective) {
         var2 = ((S3BPacketScoreboardObjective)var1.field0).func_149337_d();
         if (var2 == null) {
            return;
         }

         if (ChatFormatting.stripFormatting(var2).contains("SKIBLOCK")) {
            ((S3BPacketScoreboardObjectiveAccessor)var1.field0).setDisplayName(var2.replaceFirst("I", "Y"));
         }
      }

      if (var1.field0 instanceof S02PacketChat) {
         var2 = ((S02PacketChat)var1.field0).getChatComponent().getUnformattedText();
         if (var2.equals("Creeper Veil Activated!")) {
            field3 = true;
         }

         if (var2.equals("Creeper Veil De-activated!") || var2.equals("Not enough mana! Creeper Veil De-activated!")) {
            field3 = false;
         }

         if (var2.startsWith("[BOSS] The Watcher: You have proven yourself. You may pass.")) {
            field16 = true;
         }

         if (var2.startsWith("[BOSS] The Watcher: ") && !field0) {
            field0 = true;
         }

         if (var2.startsWith("[BOSS] Goldor: Who dares trespass into my domain?")) {
            field11 = true;
         }

         if (field24.contains(var2)) {
            field13 = true;
         }

         if (var2.startsWith("[BOSS] Necron:")) {
            field14 = true;
         }
      }

      if (var1.field0 instanceof S02PacketLoginSuccess && Oringo.field3.hashCode() != 2062794) {
         var1.method9();
      }

      if (Oringo.cV_) {
         if (DebugCommand.field1.length != 0 && (DebugCommand.field1.length == 1 && "*".equals(DebugCommand.field1[0]) && AntibotModule.method0(var1.field0) || Arrays.stream(DebugCommand.field1).anyMatch(Class496::field0))) {
            System.out.println(AutoToolModule.method0(var1.field0));
         }

         if (DebugCommand.field3 && var1.field0 instanceof S29PacketSoundEffect && !((S29PacketSoundEffect)var1.field0).getSoundName().equals("step.stone") && !((S29PacketSoundEffect)var1.field0).getSoundName().equals("game.player.hurt") && !((S29PacketSoundEffect)var1.field0).getSoundName().equals("mob.enderman.hit")) {
            PopupAnimationModule.method2(String.format("Sound: %s Pitch: %s Vol: %s", ((S29PacketSoundEffect)var1.field0).getSoundName(), ((S29PacketSoundEffect)var1.field0).getPitch(), ((S29PacketSoundEffect)var1.field0).getVolume()));
            PopupAnimationModule.method2(String.format("X: %s Y: %s Z: %s", ((S29PacketSoundEffect)var1.field0).getX(), ((S29PacketSoundEffect)var1.field0).getY(), ((S29PacketSoundEffect)var1.field0).getZ()));
         }
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      field2 = null;
      field3 = false;
      field0 = false;
      field11 = false;
      field13 = false;
      field16 = false;
   }

   static {
      (new Thread(Class496::lambda$static$0)).start();
      field26 = Minecraft.getMinecraft();
      field2 = null;
      field24 = Lists.newArrayList(new String[]{"[BOSS] Bonzo: Gratz for making it this far, but I’m basically unbeatable.", "[BOSS] Scarf: This is where the journey ends for you, Adventurers.", "[BOSS] The Professor: I was burdened with terrible news recently...", "[BOSS] Thorn: Welcome Adventurers! I am Thorn, the Spirit! And host of the Vegan Trials!", "[BOSS] Livid: Welcome, you arrive right on time. I am Livid, the Master of Shadows.", "[BOSS] Sadan: So you made it all the way here... Now you wish to defy me? Sadan?!", "[BOSS] Maxor: WELL! WELL! WELL! LOOK WHO'S HERE!"});
      field19 = null;
      field10 = new ArrayList();
      field22 = new ArrayList();
   }
}
