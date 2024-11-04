package oringo.module;

import java.awt.Color;
import map.Class351;
import map.Class362;
import map.Class388;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;

public class LightningDetectModule extends Module {
   public LightningDetectModule() {
      super("Lightning Detect", Category.other, SubCategory.other, "Detects lightning");
   }

   public static void method0(float var0, float var1, float var2, float var3, float var4, float var5) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableAlpha();
      Class388.field0.method5();
      Class362.field7.field0(Class388.field0);
      Class351.field0(Class388.field0, var2, var3, var4);
      BossBarModule.method0(var0, var1, var2, var3);
      Class388.field0.method2();
      NoCarpetModule.method0(var0 + var5, var1 + var5, var2 - var5 * 2.0F, var3 - var5 * 2.0F, var4 - var5);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.method44() && var1.field0 instanceof S29PacketSoundEffect) {
         S29PacketSoundEffect var2 = (S29PacketSoundEffect)var1.field0;
         if (var2.getSoundName().equals("ambient.weather.thunder")) {
            int var3 = (int)field58.thePlayer.getDistance(var2.getX(), field58.thePlayer.posY, var2.getZ());
            ChatComponentText var4 = new ChatComponentText("OringoClient §7» §fLightning found! X:" + (int)var2.getX() + " Y:" + (int)var2.getY() + " Z:" + (int)var2.getZ() + String.format(" (%sm away)", var3));
            ChatStyle var5 = new ChatStyle();
            var5.setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, String.format("%suhctp %s %s %s", AutoRabbitModule.method1(), var2.getX(), 100, var2.getZ())));
            var4.setChatStyle(var5);
            field58.thePlayer.addChatMessage(var4);
         }
      }

   }

   public static void method0(EntityLivingBase var0, float var1, float var2, float var3, float var4, float var5, float var6, ModelBase var7, Color var8) {
      Minecraft var9 = Minecraft.getMinecraft();
      boolean var10 = var9.gameSettings.fancyGraphics;
      float var11 = var9.gameSettings.gammaSetting;
      var9.gameSettings.fancyGraphics = false;
      var9.gameSettings.gammaSetting = 100000.0F;
      GlStateManager.resetColor();
      Class351.method0(var8);
      ModHiderModule.method0(2.0F);
      var7.render(var0, var1, var2, var3, var4, var5, var6);
      Class351.method0(var8);
      MurderMysteryModule.method1();
      var7.render(var0, var1, var2, var3, var4, var5, var6);
      Class351.method0(var8);
      NamesOnlyModule.method3();
      var7.render(var0, var1, var2, var3, var4, var5, var6);
      Class351.method0(var8);
      NBTCopyModule.method0(var8);
      var7.render(var0, var1, var2, var3, var4, var5, var6);
      Class351.method0(var8);
      NoCarpetModule.method5();
      Class351.method0(Color.WHITE);
      GlStateManager.disableBlend();
      GlStateManager.disableLighting();
      var9.gameSettings.fancyGraphics = var10;
      var9.gameSettings.gammaSetting = var11;
   }
}
