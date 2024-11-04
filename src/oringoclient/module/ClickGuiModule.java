package oringo.module;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import map.Class213;
import map.Class252;
import map.Class283;
import map.Class306;
import map.Class311;
import map.Class338;
import map.Class354;
import map.Class362;
import map.Class447;
import map.Class7;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C16PacketClientStatus.EnumState;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.opengl.GL20;
import oringo.Oringo;
import oringo.event.Class177;
import oringo.event.Class189;
import oringo.setting.BooleanSetting;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class ClickGuiModule extends Module {
   public static final ColorSetting field0 = new ColorSetting("Prefix color", -14167829, false);
   public static final BooleanSetting field1 = new BooleanSetting("Send notifications in chat", false);
   public static final EnumSetting field2 = new EnumSetting("Gui style", "Compact", new String[]{"Compact", "Dropdown"});
   public final ColorSetting field3 = new ColorSetting("Color 2", new Color(164, 128, 255), false, ClickGuiModule::lambda$new$4);
   public static final BooleanSetting field4 = new BooleanSetting("TPS counter", false);
   public final DoubleSetting field5 = new DoubleSetting("x", 0.00625D, 0.0D, 1.0D, 0.0D, ClickGuiModule::lambda$new$7);
   public static final BooleanSetting field6 = new BooleanSetting("BPS counter", false);
   public static final BooleanSetting field7 = new BooleanSetting("Outro", true);
   public long field8;
   public final DoubleSetting field9 = new DoubleSetting("Shift Speed ", 1.0D, 0.1D, 5.0D, 0.1D, ClickGuiModule::lambda$new$5);
   public static final StringSetting field10 = new StringSetting("Title", "Oringo Client ($ign)");
   public static final BooleanSetting field11 = new BooleanSetting("Search bar", true, ClickGuiModule::lambda$static$10);
   public static final EnumSetting field12 = new ClickGuiModule$1("Font", "Rubik", new String[]{"Quicksand", "Roboto", "Rubik"});
   public final DoubleSetting field13 = new DoubleSetting("Rainbow Speed", 2.5D, 0.1D, 5.0D, 0.1D, ClickGuiModule::lambda$new$6);
   public final ColorSetting field14 = new ColorSetting("Color", new Color(119, 255, 230), false, ClickGuiModule::lambda$new$3);
   public static final BooleanSetting field15 = new BooleanSetting("HSV Shift", true, ClickGuiModule::lambda$static$9);
   public static final DoubleSetting field16 = new DoubleSetting("GUI_Y", 0.5D, 0.0D, 1.0D, 0.0D, ClickGuiModule::lambda$static$2);
   public static final BooleanSetting field17 = new BooleanSetting("FPS counter", false);
   public final CopyOnWriteArrayList field18 = new CopyOnWriteArrayList();
   public static final EnumSetting field19 = new EnumSetting("Mode", "Color shift", new String[]{"Rainbow", "Color shift", "Pulse", "Custom"});
   public static final ColorSetting field20 = new ColorSetting("Gui color", new Color(109, 255, 242), false);
   public static final BooleanSetting field21 = new BooleanSetting("Ping Counter", false);
   public static final DoubleSetting field22 = new DoubleSetting("GUI_X", 0.5D, 0.0D, 1.0D, 0.0D, ClickGuiModule::lambda$static$1);
   public static final BooleanSetting field23 = new BooleanSetting("Toggle notifications", true);
   public Class7 field24 = null;
   public static final StringSetting field25 = new StringSetting("CMD Prefix", 1);
   public static final ArrayList field26 = new ArrayList();
   public Class306 field27 = null;
   public short field28 = 0;
   public final DoubleSetting field29 = new DoubleSetting("y", 0.975D, 0.0D, 1.0D, 0.0D, ClickGuiModule::lambda$new$8);
   public static final BooleanSetting field30 = new BooleanSetting("Toggle buttons", false);
   public static final BooleanSetting field31 = new BooleanSetting("Scale gui", false);
   public static final StringSetting field32 = new StringSetting("previous_version", "", ClickGuiModule::lambda$static$0);
   public final Class447 field33 = new Class447();

   public static boolean lambda$onRender$11(Long var0) {
      return System.currentTimeMillis() - var0 > 2050L;
   }

   public GuiScreen method5() {
      if (this.field24 == null) {
         this.field24 = new Class7();
         this.field27 = new Class306();
      }

      return (GuiScreen)(field2.method0(1) ? this.field27 : this.field24);
   }

   public static Boolean lambda$new$5() {
      return !field19.method0(1) && !field19.method0(2) && !field19.method0(-1);
   }

   public static Boolean lambda$new$7() {
      return true;
   }

   public static Boolean lambda$static$10() {
      return !field2.method0(1);
   }

   public static Boolean lambda$new$6() {
      return !field19.method0(0);
   }

   public ClickGuiModule() {
      super("Click Gui", 54, Category.render, SubCategory.ui);
      this.method48();
      this.method0((Setting[])(new Setting[]{field32, field7, this.field5, this.field29, field22, field16, field2, field20, field0, field10, field12, field19, this.field13, this.field9, this.field14, this.field3, field15, field25, field23, field1, field31, field21, field6, field4, field17, field30, field11}));
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (this.method44()) {
         if (this.field60.a_(200L)) {
            this.method40();
         }

         GuiScreen var2 = this.method5();
         if (field58.currentScreen != var2) {
            field58.displayGuiScreen((GuiScreen)null);
            field58.displayGuiScreen(var2);
         }
      }

   }

   public static Boolean lambda$static$0() {
      return true;
   }

   public void field0(Class354 var1) {
      float var2 = 0.0F;
      String var3 = field19.method4();
      byte var4 = -1;
      switch(var3.hashCode()) {
      case -1656737386:
         if (var3.equals("Rainbow")) {
            var4 = 1;
         }
         break;
      case 77474681:
         if (var3.equals("Pulse")) {
            var4 = 2;
         }
         break;
      case 1188757861:
         if (var3.equals("Color shift")) {
            var4 = 0;
         }
      }

      switch(var4) {
      case 0:
         var2 = (float)((double)System.currentTimeMillis() * this.field9.method0() / 6283.185307179586D % 1.0D);
         if (field15.method1()) {
            var1.method0("color", new Color(this.field14.method5(), this.field14.method0(), this.field14.method1(), (float)this.field14.method4() / 255.0F));
            var1.method0("color1", new Color(this.field3.method5(), this.field3.method0(), this.field3.method1(), (float)this.field3.method4() / 255.0F));
         } else {
            var1.method0("color", this.field14.method17());
            var1.method0("color1", this.field3.method17());
         }
         break;
      case 1:
         var2 = (float)((double)System.currentTimeMillis() * this.field13.method0() / 5000.0D % 1.0D);
         var1.method0("color", new Color(0.0F, 0.65F, 1.0F, (float)this.field14.method4() / 255.0F));
         var1.method0("color1", new Color(1.0F, 0.65F, 1.0F, (float)this.field14.method4() / 255.0F));
         break;
      case 2:
         Color var5 = this.field14.method17();
         var2 = (float)((double)System.currentTimeMillis() * this.field9.method0() / 6283.185307179586D % 1.0D);
         var1.method0("color", var5);
         var1.method0("color1", var5.darker().darker());
         break;
      default:
         var1.method0("color", this.field14.method17());
         var1.method0("color1", this.field14.method17());
      }

      var1.method0("hsv", (!field19.method0(1) || !field15.method1()) && !field19.method0(0) ? 0 : 1);
      var1.method0("angle", var2 * 360.0F + 180.0F);
   }

   public Color method0(float var1) {
      String var2 = field19.method4();
      byte var3 = -1;
      switch(var2.hashCode()) {
      case -1656737386:
         if (var2.equals("Rainbow")) {
            var3 = 1;
         }
         break;
      case 77474681:
         if (var2.equals("Pulse")) {
            var3 = 2;
         }
         break;
      case 961091784:
         if (var2.equals("Astolfo")) {
            var3 = 3;
         }
         break;
      case 1188757861:
         if (var2.equals("Color shift")) {
            var3 = 0;
         }
      }

      switch(var3) {
      case 0:
         float var4 = (float)((Math.cos(((double)var1 * 450.0D + (double)System.currentTimeMillis() * this.field9.method0()) / 1000.0D) + 1.0D) * 0.5D);
         if (field15.method1()) {
            return Color.getHSBColor(Class213.method0(this.field14.method5(), this.field3.method5(), var4), Class213.method0(this.field14.method0(), this.field3.method0(), var4), Class213.method0(this.field14.method1(), this.field3.method1(), var4));
         }

         return new Color(Class213.method0((float)this.field14.method7() / 255.0F, (float)this.field3.method7() / 255.0F, var4), Class213.method0((float)this.field14.method2() / 255.0F, (float)this.field3.method2() / 255.0F, var4), Class213.method0((float)this.field14.method3() / 255.0F, (float)this.field3.method3() / 255.0F, var4));
      case 1:
         return Color.getHSBColor((float)(((double)var1 * 100.0D + (double)System.currentTimeMillis() * this.field13.method0()) / 5000.0D % 1.0D), 0.65F, 1.0F);
      case 2:
         Color var5 = this.field14.method17();
         return BridgeHelperModule.method0(var5, var5.darker().darker(), (float)((Math.sin(((double)var1 * 450.0D + (double)System.currentTimeMillis() * this.field9.method0()) / 1000.0D) + 1.0D) * 0.5D));
      case 3:
         float var6 = (float)((Math.cos(((double)var1 * 450.0D + (double)System.currentTimeMillis() * this.field9.method0()) / 1000.0D) + 1.0D) * 0.5D);
         return Color.getHSBColor(0.5F + 0.4F * var6, 0.6F, 1.0F);
      default:
         return this.field14.method17();
      }
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.type.equals(ElementType.TEXT) && field58.thePlayer != null) {
         Iterator var2 = field26.iterator();

         while(var2.hasNext()) {
            Class283 var3 = (Class283)var2.next();
            if (var3.method1()) {
               var3.method2();
            }
         }

         try {
            if (this.field18.size() > 0) {
               this.field18.removeIf(ClickGuiModule::lambda$onRender$11);
            }
         } catch (Exception var6) {
         }

         Class252 var7 = Class252.field2;
         float var8 = var7.k_();
         float var4 = var7.method15();
         Class311.field12.method5();
         if (field6.method1()) {
            var8 = Class311.field12.method3(this.method15() + "  ", Class311.field12.method3("BPS: ", var8, var4, Class362.field7.method17().getRGB()), var4, Color.white.getRGB());
         }

         if (field4.method1()) {
            float var5 = Class311.field12.method3(Math.min(20, this.field18.size() / 2) + "  ", Class311.field12.method3("TPS: ", var8, var4, Class362.field7.method17().getRGB()), var4, Color.white.getRGB());
            if (this.field18.size() >= 20) {
               var8 += 43.0F;
            } else {
               var8 = var5;
            }
         }

         if (field17.method1()) {
            var8 = Class311.field12.method3(Minecraft.getDebugFPS() + "  ", Class311.field12.method3("FPS: ", var8, var4, Class362.field7.method17().getRGB()), var4, Color.white.getRGB());
         }

         if (field21.method1()) {
            var8 = Class311.field12.method3((this.field8 <= 1000L && !field58.isSingleplayer() ? this.field8 : 0L) + "  ", Class311.field12.method3("Ping: ", var8, var4, Class362.field7.method17().getRGB()), var4, Color.white.getRGB());
         }

         Class311.field12.method6();
         var7.method1(var8 - var7.k_() - 3.0F, Class311.field12.method7());
         if (field58.currentScreen instanceof GuiChat && var7.j_()) {
            AutoSimonModule.method0(var7.k_() - 2.0F, var4 - 2.0F, var8 - var7.k_() - 1.0F, Class311.field12.method0() + 4.0F, 5.0F, 2.5F, -1);
         }
      }

   }

   public int method11() {
      return MathHelper.clamp_int(this.field18.size() / 2, 0, 20);
   }

   public static Boolean lambda$new$3() {
      return field19.method0(-1) || field19.method0(0);
   }

   @SubscribeEvent
   public void method0(Class177 var1) {
      Iterator var2 = field26.iterator();

      while(true) {
         while(true) {
            Class283 var3;
            do {
               if (!var2.hasNext()) {
                  return;
               }

               var3 = (Class283)var2.next();
            } while(!var3.method1());

            if (var1 instanceof Class177.Class3) {
               if (var3.method0(var1.field3, var1.field1)) {
                  var3.method16();
               }
            } else if (var1 instanceof Class177.Class4 || var1 instanceof Class177.Class0) {
               var3.method21();
            }
         }
      }
   }

   public static Boolean lambda$new$4() {
      return !field19.method0(1);
   }

   public static void method0(Class283 var0) {
      field26.add(var0);
   }

   public static Boolean lambda$static$9() {
      return !field19.method0(1);
   }

   public String method15() {
      double var1 = Math.hypot(field58.thePlayer.posX - field58.thePlayer.prevPosX, field58.thePlayer.posZ - field58.thePlayer.prevPosZ) * (double)FrozenTreasureESPModule.method5().timerSpeed * 20.0D;
      String var3 = String.valueOf(var1).replace('.', ',');
      int var4 = var3.indexOf(",");
      return var3.substring(0, var4 + 2);
   }

   public static Boolean lambda$static$2() {
      return true;
   }

   public Color method17() {
      return this.method0(0.0F);
   }

   public static Boolean lambda$new$8() {
      return true;
   }

   public static int method0(String var0, int var1) {
      String var2;
      InputStream var3;
      Throwable var4;
      if ((new File("C:\\Users\\Dadoodze\\Desktop\\Projects\\OringoClient\\aaa\\src\\main\\resources\\assets\\oringoclient\\shader\\" + var0)).exists()) {
         try {
            var3 = Files.newInputStream(Paths.get("C:\\Users\\Dadoodze\\Desktop\\Projects\\OringoClient\\aaa\\src\\main\\resources\\assets\\oringoclient\\shader\\" + var0));
            var4 = null;

            try {
               var2 = Class338.method0(var3);
            } catch (Throwable var32) {
               var4 = var32;
               throw var32;
            } finally {
               if (var3 != null) {
                  if (var4 != null) {
                     try {
                        var3.close();
                     } catch (Throwable var29) {
                        var4.addSuppressed(var29);
                     }
                  } else {
                     var3.close();
                  }
               }

            }
         } catch (NullPointerException | IOException var34) {
            throw new IllegalStateException("Unable to find shader " + var0 + "!", var34);
         }
      } else {
         try {
            var3 = Oringo.field9.getResourceManager().getResource(new ResourceLocation("oringoclient", "shader/" + var0)).getInputStream();
            var4 = null;

            try {
               var2 = Class338.method0(var3);
            } catch (Throwable var31) {
               var4 = var31;
               throw var31;
            } finally {
               if (var3 != null) {
                  if (var4 != null) {
                     try {
                        var3.close();
                     } catch (Throwable var30) {
                        var4.addSuppressed(var30);
                     }
                  } else {
                     var3.close();
                  }
               }

            }
         } catch (NullPointerException | IOException var36) {
            throw new IllegalStateException("Unable to find shader " + var0 + "!", var36);
         }
      }

      int var37 = GL20.glCreateShader(var1);
      GL20.glShaderSource(var37, var2);
      GL20.glCompileShader(var37);
      if (GL20.glGetShaderi(var37, 35713) == 0) {
         throw new IllegalStateException("Failed to compile shader! " + GL20.glGetShaderInfoLog(var37, 4096));
      } else {
         return var37;
      }
   }

   public static Boolean lambda$static$1() {
      return true;
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S32PacketConfirmTransaction) {
         short var2 = ((S32PacketConfirmTransaction)var1.field0).getActionNumber();
         if (var2 >= 0) {
            return;
         }

         if (this.field28 == var2) {
            this.field18.add(System.currentTimeMillis());
            if (this.field33.a_(1000L)) {
               this.method20();
            }
         }

         this.field28 = (short)(var2 - 1);
      } else if (var1.field0 instanceof S37PacketStatistics) {
         this.field8 = this.field33.method0();
      }

   }

   public void method20() {
      this.field33.o_();
      method2(new C16PacketClientStatus(EnumState.REQUEST_STATS));
   }
}
