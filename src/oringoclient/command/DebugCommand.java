package oringo.command;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;
import map.Class354;
import map.Class362;
import map.Class496;
import map.Class80;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import oringo.module.AutoCraftModule;
import oringo.module.DadudzeModule;
import oringo.module.Module;
import oringo.module.PopupAnimationModule;
import oringo.module.ThystHiderModule;

public class DebugCommand extends Command {
   public static boolean field0;
   public static String[] field1 = new String[0];
   public static boolean field2 = false;
   public static boolean field3 = false;

   public void method0(String[] var1) {
      String var2;
      byte var3;
      if (var1.length == 2) {
         var2 = var1[0].toLowerCase();
         var3 = -1;
         switch(var2.hashCode()) {
         case -995865464:
            if (var2.equals("packet")) {
               var3 = 0;
            }
         default:
            switch(var3) {
            case 0:
               if (var1[1].equalsIgnoreCase("off")) {
                  field1 = new String[0];
                  PopupAnimationModule.method2("Packet debugging disabled");
               } else {
                  field1 = var1[1].split("/");
                  PopupAnimationModule.method2("Packet debugging enabled");
               }
            }
         }
      }

      if (var1.length > 1 && var1[0].equals("debugger")) {
         Class496.field22.add(String.join(" ", var1));
      }

      if (var1.length >= 1 && (var1[0].equalsIgnoreCase("reboot") || var1[0].equalsIgnoreCase("restart"))) {
         Class80.method0((String[])Arrays.copyOfRange(var1, 1, var1.length));
      }

      if (var1.length == 1) {
         var2 = var1[0].toLowerCase();
         var3 = -1;
         switch(var2.hashCode()) {
         case -1357714453:
            if (var2.equals("clicks")) {
               var3 = 10;
            }
            break;
         case -1291329255:
            if (var2.equals("events")) {
               var3 = 7;
            }
            break;
         case -903579675:
            if (var2.equals("shader")) {
               var3 = 1;
            }
            break;
         case -896509628:
            if (var2.equals("sounds")) {
               var3 = 9;
            }
            break;
         case -633402244:
            if (var2.equals("removeentities!")) {
               var3 = 6;
            }
            break;
         case 111188:
            if (var2.equals("pos")) {
               var3 = 8;
            }
            break;
         case 3046099:
            if (var2.equals("cape")) {
               var3 = 3;
            }
            break;
         case 94921639:
            if (var2.equals("crash")) {
               var3 = 2;
            }
            break;
         case 110363525:
            if (var2.equals("tiles")) {
               var3 = 11;
            }
            break;
         case 111441755:
            if (var2.equals("unreg")) {
               var3 = 12;
            }
            break;
         case 533756997:
            if (var2.equals("removeentities")) {
               var3 = 4;
            }
            break;
         case 1185864508:
            if (var2.equals("removeentitiesall")) {
               var3 = 5;
            }
            break;
         case 1586494356:
            if (var2.equals("scoreboard")) {
               var3 = 13;
            }
            break;
         case 2053801262:
            if (var2.equals("shaders")) {
               var3 = 0;
            }
         }

         StringBuilder var10;
         Iterator var25;
         Entity var27;
         switch(var3) {
         case 0:
         case 1:
            Class354.field4 = System.currentTimeMillis();
            method2("Forcing shader reload!");
            break;
         case 2:
            ChatComponentText var4 = new ChatComponentText("&m&n");
            ChatComponentText var5 = var4;

            for(int var23 = 0; var23 < 500; ++var23) {
               ChatComponentText var24 = new ChatComponentText("&k");
               var4.appendSibling(var24);
               var4 = var24;
            }

            var4.appendText("llllllllllllllllllllllllllllllllllllllll");
            AutoCraftModule.method3(new C12PacketUpdateSign(new BlockPos(ThystHiderModule.method3()), new IChatComponent[]{var5, var5, var5, var5}));
            break;
         case 3:
            String var6 = field9.getSession().getUsername();
            String var7 = field9.getSession().getPlayerID();
            BigInteger var8 = new BigInteger(128, new Random());

            try {
               field9.getSessionService().joinServer(field9.getSession().getProfile(), field9.getSession().getToken(), var8.toString(16));
               Desktop.getDesktop().browse(new URI("https://optifine.net/capeChange?u=" + var7 + "&n=" + var6 + "&s=" + var8.toString(16)));
            } catch (Exception var22) {
               var22.printStackTrace();
            }
            break;
         case 4:
            var25 = field9.theWorld.loadedEntityList.iterator();

            while(var25.hasNext()) {
               var27 = (Entity)var25.next();
               if (!var27.equals(field9.thePlayer) && var27 instanceof EntityArmorStand) {
                  field9.theWorld.removeEntity(var27);
               }
            }

            return;
         case 5:
            var25 = field9.theWorld.loadedEntityList.iterator();

            while(var25.hasNext()) {
               var27 = (Entity)var25.next();
               if (!var27.equals(field9.thePlayer)) {
                  System.out.println(var27.getClass().getSimpleName());
                  field9.theWorld.removeEntity(var27);
               }
            }

            return;
         case 6:
            var25 = field9.theWorld.loadedEntityList.iterator();

            while(var25.hasNext()) {
               var27 = (Entity)var25.next();
               if (!var27.equals(field9.thePlayer) && !(var27 instanceof EntityPlayer) && !(var27 instanceof EntityArmorStand)) {
                  System.out.println(var27.getClass().getSimpleName());
                  field9.theWorld.removeEntity(var27);
               }
            }

            return;
         case 7:
            field0 = !field0;
            break;
         case 8:
            BlockPos var9 = ThystHiderModule.method3();
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(String.format("new BlockPos(%d, %d, %d)", var9.getX(), var9.getY(), var9.getZ())), (ClipboardOwner)null);
            method2("Current BlockPos has been copied!");
            break;
         case 9:
            PopupAnimationModule.method2("Sound debugging is now: " + ((field3 = !field3) ? "enabled!" : "disabled!"));
            break;
         case 10:
            PopupAnimationModule.method2("Click debugging is now: " + ((field2 = !field2) ? "enabled!" : "disabled!"));
            break;
         case 11:
            var10 = new StringBuilder();
            Iterator var29 = field9.theWorld.loadedTileEntityList.iterator();

            while(var29.hasNext()) {
               TileEntity var30 = (TileEntity)var29.next();
               if (field9.thePlayer.getDistanceSq(var30.getPos()) < 9.0D) {
                  var10.append(var30.serializeNBT()).append("\n");
               }
            }

            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(var10.toString()), (ClipboardOwner)null);
            PopupAnimationModule.method2("Data has been copied!");
            break;
         case 12:
            Iterator var26 = Class362.method0().iterator();

            while(var26.hasNext()) {
               Module var28 = (Module)var26.next();
               if (!(var28 instanceof DadudzeModule)) {
                  MinecraftForge.EVENT_BUS.unregister(var28);
               }
            }

            method2("Unregistered all modules!");
            break;
         case 13:
            var10 = new StringBuilder();
            Scoreboard var11 = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
            ArrayList var12 = new ArrayList(var11.getSortedScores(var11.getObjectiveInDisplaySlot(1)));
            Iterator var13 = var12.iterator();

            while(true) {
               ScorePlayerTeam var15;
               do {
                  if (!var13.hasNext()) {
                     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(var10.toString()), (ClipboardOwner)null);
                     return;
                  }

                  Score var14 = (Score)var13.next();
                  var15 = var11.getPlayersTeam(var14.getPlayerName());
               } while(var15 == null);

               String var16 = var15.getColorPrefix() + var15.getColorSuffix();
               StringBuilder var17 = new StringBuilder();
               char[] var18 = var16.toCharArray();
               int var19 = var18.length;

               for(int var20 = 0; var20 < var19; ++var20) {
                  char var21 = var18[var20];
                  var17.append(var21);
               }

               var10.append(var17).append("\n");
            }
         }
      }

   }

   public String method1() {
      return "";
   }

   public static boolean method0(Predicate var0) {
      String var1 = XRayCommand.method2();
      return var1 != null && var0.test(var1);
   }

   public boolean a_() {
      return true;
   }

   public DebugCommand() {
      super("debug");
   }
}
