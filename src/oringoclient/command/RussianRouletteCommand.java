package oringo.command;

import java.awt.Color;
import java.util.Random;
import map.Class469;
import map.Class514;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.input.Mouse;
import oringo.module.FrozenTreasureESPModule;
import oringo.module.NoRenderModule2;
import oringo.module.PopupAnimationModule;
import oringo.notification.Notifications;

public class RussianRouletteCommand extends Command {
   public boolean field0 = false;
   public static final Random field1 = new Random();

   public RussianRouletteCommand() {
      super("russianroulette");
   }

   public String method1() {
      return null;
   }

   public static void method2() {
      if (NoRenderModule2.field58.skipRenderWorld && NoRenderModule2.field58.currentScreen != null) {
         NoRenderModule2.field58.setIngameNotInFocus();
         if (NoRenderModule2.field58.theWorld == null) {
            GlStateManager.viewport(0, 0, NoRenderModule2.field58.displayWidth, NoRenderModule2.field58.displayHeight);
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.loadIdentity();
            NoRenderModule2.field58.entityRenderer.setupOverlayRendering();
         } else {
            GlStateManager.alphaFunc(516, 0.1F);
            NoRenderModule2.field58.entityRenderer.setupOverlayRendering();
         }

         ScaledResolution var0 = new ScaledResolution(NoRenderModule2.field58);
         int var1 = var0.getScaledWidth();
         int var2 = var0.getScaledHeight();
         int var3 = Mouse.getX() * var1 / NoRenderModule2.field58.displayWidth;
         int var4 = var2 - Mouse.getY() * var2 / NoRenderModule2.field58.displayHeight - 1;
         GlStateManager.clear(256);
         Class469.method0(0.0F, 0.0F, (float)var1, (float)var2, Color.black.getRGB());

         try {
            ForgeHooksClient.drawScreen(NoRenderModule2.field58.currentScreen, var3, var4, FrozenTreasureESPModule.method5().renderPartialTicks);
         } catch (Throwable var8) {
            CrashReport var6 = CrashReport.makeCrashReport(var8, "Rendering screen");
            CrashReportCategory var7 = var6.makeCategory("Screen render details");
            var7.addCrashSectionCallable("Screen name", NoRenderModule2::lambda$drawGui$0);
            var7.addCrashSectionCallable("Mouse location", NoRenderModule2::lambda$drawGui$1);
            var7.addCrashSectionCallable("Screen size", NoRenderModule2::lambda$drawGui$2);
            throw new ReportedException(var6);
         }
      }

   }

   public void method0(String[] var1) {
      if (!this.field0) {
         PopupAnimationModule.method2("This command is §cNOT §fa fake ban command! If you use it again it will have 1/6 chance to ban you!");
         this.field0 = true;
      } else {
         if (field1.nextInt(6) == 0) {
            Class514.method0("Boom!", 10000, Notifications.Class1.field2);
            SelfBanCommand.field1 = true;

            for(int var2 = 0; var2 < 10; ++var2) {
               field9.getNetHandler().getNetworkManager().sendPacket(new C08PacketPlayerBlockPlacement(new BlockPos((new Random()).nextInt(), (new Random()).nextInt(), (new Random()).nextInt()), 1, field9.thePlayer.inventory.getCurrentItem(), 0.0F, 0.0F, 0.0F));
            }
         }

      }
   }
}
