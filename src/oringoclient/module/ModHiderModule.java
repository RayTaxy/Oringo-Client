package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import map.Class239;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import org.lwjgl.opengl.GL11;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class ModHiderModule extends Module {
   public BooleanSetting x_(String var1) {
      Iterator var2 = this.method47().iterator();

      while(var2.hasNext()) {
         Setting var3 = (Setting)var2.next();
         if (var3.cW_.equals("Show: " + var1)) {
            return (BooleanSetting)var3;
         }
      }

      return new BooleanSetting("", var1.equals("mcp") || var1.equals("FML") || var1.equals("Forge"));
   }

   public static void method0(float var0) {
      PrinterModule.method2();
      GL11.glPushAttrib(1048575);
      GlStateManager.disableAlpha();
      GlStateManager.disableTexture2D();
      GlStateManager.disableLighting();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GL11.glLineWidth(var0);
      GL11.glEnable(2848);
      GL11.glEnable(2960);
      GL11.glClear(1024);
      GL11.glClearStencil(15);
      GL11.glStencilFunc(512, 1, 15);
      GL11.glStencilOp(7681, 7681, 7681);
      GL11.glPolygonMode(1032, 6913);
   }

   public ModHiderModule() {
      super("Mod Hider", Category.other, SubCategory.other, "Prevents the server from seeing what mods you are using");
      Iterator var1 = Loader.instance().getModList().iterator();

      while(var1.hasNext()) {
         ModContainer var2 = (ModContainer)var1.next();
         String var3 = var2.getModId();
         boolean var4 = var3.equals("mcp") || var3.equals("FML") || var3.equals("Forge");
         if (!var4) {
            this.method0(new BooleanSetting("Show: " + var3, false));
         }
      }

      this.method1(true);
   }

   public boolean method5() {
      return Class239.method0(4).equals("net.minecraftforge.fml.common.network.handshake.FMLHandshakeClientState$2");
   }

   public static void method0(float var0, float var1, float var2, float var3, float var4, float var5, Color var6, Color var7) {
      NBTCopyModule.method0(var0, var1, var2, var3, var4, var7);
      NBTCopyModule.method0(var0 + var5, var1 + var5, var2 - var5 * 2.0F, var3 - var5 * 2.0F, var4 - var5, var6);
   }
}
