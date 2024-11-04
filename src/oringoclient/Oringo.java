package oringo;

import java.io.File;
import java.net.URISyntaxException;
import map.Class101;
import map.Class190;
import map.Class198;
import map.Class237;
import map.Class245;
import map.Class25;
import map.Class252;
import map.Class253;
import map.Class265;
import map.Class283;
import map.Class311;
import map.Class32;
import map.Class349;
import map.Class35;
import map.Class357;
import map.Class362;
import map.Class371;
import map.Class418;
import map.Class496;
import map.Class527;
import map.Class532;
import map.Class56;
import map.Class90;
import map.Class91;
import map.Class98;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import oringo.command.UseCommand;
import oringo.module.AnimationsModule;
import oringo.module.AutoMaskModule;
import oringo.module.ClickGuiModule;
import oringo.module.EagleModule;
import oringo.module.GlintModule;
import oringo.module.MushroomESPModule;
import oringo.module.PeltESPModule;
import oringo.module.ScoreboardModule;
import oringo.module.ShinyBlocksModule;
import oringo.notification.Notifications;
import oringo.setting.BooleanSetting;

public class Oringo {
   public static final Class35 field0;
   public static final String field1 = "oringoclient";
   public static final String field2 = "OringoClient §7» §f";
   public static String field3 = "examplemod";
   public static IRC field4;
   public static final String field5;
   public static boolean cV_;
   private static final Logger field10;
   public static boolean field6;
   public static final String field8 = "2.8.0";
   public static final Minecraft field9 = Minecraft.getMinecraft();

   public void method0(FMLPostInitializationEvent var1) {
      Class311.method0();
      ClickGuiModule.method0((Class283)Class527.df_);
      ClickGuiModule.method0((Class283)Class252.field2);
      ClickGuiModule.method0((Class283)(new Class32()));
      ClickGuiModule.method0(((ScoreboardModule)Class362.method0(ScoreboardModule.class)).method5());
      ClickGuiModule.method0((Class283)(new Class532()));
      Class362.field7.method5();
      AnimationsModule.method5();
      MushroomESPModule.z_();
   }

   public void method0(FMLPreInitializationEvent var1) {
      try {
         field4 = new IRC();
      } catch (URISyntaxException var3) {
         throw new IllegalStateException("Invalid IRC URI!", var3);
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

      Class371.method0();
      Class362.z_();
      ShinyBlocksModule.z_();
      AutoMaskModule.z_();
      PeltESPModule.method5();
      MinecraftForge.EVENT_BUS.register(new Notifications());
      MinecraftForge.EVENT_BUS.register(this);
      MinecraftForge.EVENT_BUS.register(Class190.field1);
      MinecraftForge.EVENT_BUS.register(new Class349());
      MinecraftForge.EVENT_BUS.register(new Class496());
      MinecraftForge.EVENT_BUS.register(new Class237());
      MinecraftForge.EVENT_BUS.register(new Class56());
      MinecraftForge.EVENT_BUS.register(new Class253());
      MinecraftForge.EVENT_BUS.register(new UseCommand());
      MinecraftForge.EVENT_BUS.register(new Class418());
      MinecraftForge.EVENT_BUS.register(new Class357());
      MinecraftForge.EVENT_BUS.register(new Class90());
      MinecraftForge.EVENT_BUS.register(new Class265());
      MinecraftForge.EVENT_BUS.register(new Class25());
      MinecraftForge.EVENT_BUS.register(new Class101());
      MinecraftForge.EVENT_BUS.register(new Class91());
      MinecraftForge.EVENT_BUS.register(new Class245());
      EagleModule.method42();
      GlintModule.method7();
      MinecraftForge.EVENT_BUS.register(new Class198());
      if (cV_) {
         MinecraftForge.EVENT_BUS.register(new Class98());
      }

      if (!ClickGuiModule.field10.F_()) {
         Display.setTitle(ClickGuiModule.field10.method1());
      }

      Runtime.getRuntime().addShutdownHook(new Thread(BooleanSetting::method0));
   }

   static {
      field5 = (new File(field9.mcDataDir + "/config/OringoClient/")).getAbsolutePath() + "/";
      field6 = false;
      cV_ = false;
      field4 = null;
      field0 = new Class35(new File(field5, "proxy.json"));
      field10 = LogManager.getLogger("Oringo-Client");
   }

   public static Logger method0(String var0) {
      return LogManager.getLogger(var0);
   }
}
