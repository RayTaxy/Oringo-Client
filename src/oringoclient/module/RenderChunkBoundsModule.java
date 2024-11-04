package oringo.module;

import java.awt.Color;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.regex.Matcher;
import map.Class253;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.PlayerCommand;
import oringo.mixin.ChunkProviderClientAccessor;

public class RenderChunkBoundsModule extends Module {
   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      AutoBlazeModule.method0(PlayerCommand.method0(Color.GREEN, 60));
      if (field58.theWorld.getChunkProvider() instanceof ChunkProviderClient) {
         Iterator var2 = ((ChunkProviderClientAccessor)field58.theWorld.getChunkProvider()).getChunkListing().iterator();

         label21:
         while(true) {
            Chunk var3;
            do {
               if (!var2.hasNext()) {
                  break label21;
               }

               var3 = (Chunk)var2.next();
            } while(field58.thePlayer.chunkCoordX == var3.xPosition && field58.thePlayer.chunkCoordZ == var3.zPosition);

            int var4 = var3.xPosition * 16;
            int var5 = var3.zPosition * 16;
            GhostBlocksModule.method0((new AxisAlignedBB((double)var4, 0.0D, (double)var5, (double)(var4 + 16), 256.0D, (double)(var5 + 16))).offset(-field58.getRenderManager().viewerPosX, -field58.getRenderManager().viewerPosY, -field58.getRenderManager().viewerPosZ), true);
         }
      }

      AutoCloseModule.method5();
   }

   public RenderChunkBoundsModule() {
      super("Render Chunk Bounds", Category.render, SubCategory.world, "Renders boundaries of chunks");
   }

   public static IChatComponent method0(String var0, EnumChatFormatting var1) {
      ChatComponentText var2 = null;
      Matcher var3 = Class253.field0.matcher(var0);
      int var4 = 0;

      while(true) {
         String var8;
         String var9;
         while(true) {
            if (!var3.find()) {
               String var13 = var0.substring(var4);
               if (var2 == null) {
                  var2 = new ChatComponentText(var0);
               } else if (!var13.isEmpty()) {
                  var2.appendText(var13);
               }

               return var2;
            }

            int var5 = var3.start(2);
            int var6 = var3.end(2);
            String var7 = var0.substring(var4, var5);
            if (!var7.isEmpty()) {
               if (var2 == null) {
                  var2 = new ChatComponentText(var7);
                  var2.getChatStyle().setColor(EnumChatFormatting.RESET);
               } else {
                  var2.appendText(var7);
               }
            }

            var4 = var6;
            var8 = var0.substring(var5, var6);
            var9 = var8;

            try {
               if ((new URI(var8)).getScheme() == null) {
                  var9 = "https://" + var8;
               }
               break;
            } catch (URISyntaxException var12) {
               if (var2 == null) {
                  var2 = new ChatComponentText(var8);
                  var2.getChatStyle().setColor(var1);
               } else {
                  var2.appendText(var8);
               }
            }
         }

         ChatComponentText var10 = new ChatComponentText(var8);
         ClickEvent var11 = new ClickEvent(Action.OPEN_URL, var9);
         var10.getChatStyle().setChatClickEvent(var11).setColor(EnumChatFormatting.RESET);
         if (var2 == null) {
            var2 = new ChatComponentText("");
            var2.getChatStyle().setColor(var1);
            var2.appendSibling(var10);
         } else {
            var2.appendSibling(var10);
         }
      }
   }

   public static boolean method5() {
      return Oringo.field9.thePlayer.openContainer.windowId == Oringo.field9.thePlayer.inventoryContainer.windowId;
   }
}
