package oringo.module;

import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import map.Class208;
import map.Class25;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class AnimationsModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Reset swing", 2.0D, 0.1D, 10.0D, 0.1D);
   public final BooleanSetting aV_ = new BooleanSetting("Ignore haste", true);
   public final BooleanSetting field2 = new BooleanSetting("Smooth swing", false);
   public final BooleanSetting field3 = new BooleanSetting("No equip reset", true);
   public final DoubleSetting field4 = new DoubleSetting("Size", 1.0D, 0.1D, 3.0D, 0.1D);
   public final DoubleSetting field5 = new DoubleSetting("Z Rotation", 0.0D, 0.0D, 360.0D, 1.0D);
   public final ButtonSetting field6 = new ButtonSetting("Reset", this::lambda$new$0);
   public final DoubleSetting field7 = new DoubleSetting("Y Rotation", 0.0D, 0.0D, 360.0D, 1.0D);
   public final DoubleSetting field8 = new DoubleSetting("Speed", 6.0D, 1.0D, 50.0D, 1.0D);
   public final BooleanSetting field9 = new BooleanSetting("Hide hand", false);
   public final DoubleSetting field10 = new DoubleSetting("X Rotation", 0.0D, 0.0D, 360.0D, 1.0D);
   public final DoubleSetting field11 = new DoubleSetting("Z", 0.0D, -3.0D, 3.0D, 0.01D);
   public final BooleanSetting field12 = new BooleanSetting("No item change animation", false);
   public final DoubleSetting field13 = new DoubleSetting("Y", 0.0D, -3.0D, 3.0D, 0.01D);
   public final EnumSetting field14 = new EnumSetting("Mode", "1.8", new String[]{"1.7", "1.8", "None", "Chill", "Push", "Exhibition", "Reverse", "Z axis", "Stab", "Sigma", "Penis"});
   public final DoubleSetting field15 = new DoubleSetting("X", 0.0D, -3.0D, 3.0D, 0.01D);

   public void lambda$new$0() {
      this.field15.method0(0.0D);
      this.field13.method0(0.0D);
      this.field11.method0(0.0D);
      this.field4.method0(1.0D);
      this.field8.method0(6.0D);
      this.field0.method0(2.0D);
      this.field7.method0(0.0D);
      this.field10.method0(0.0D);
      this.field5.method0(0.0D);
      this.field2.method0(false);
      this.field3.method0(true);
      this.aV_.method0(true);
      this.field5.method0(0.0D);
      this.field14.d_("1.8");
      this.field9.method0(false);
   }

   public static boolean method0(Class208 var0, int var1, int var2) {
      Class208 var3 = Class25.method0(var1, var2);
      return var0 != null && var3 != null && var0.method45().equals(var3.method45());
   }

   public static void method5() {
      if (CustomHubMap.field5 != null) {
         CustomHubMap.field5.method0();
      }

      CustomHubMap.Class0[] var0 = CustomHubMap.aD_;
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         CustomHubMap.Class0 var3 = var0[var2];
         var3.method0();
      }

      try {
         CustomHubMap.field5 = new CustomHubMap.Class0(new ResourceLocation("oringoclient", "logo_bg.png"), 0);
         if (!CustomHubMap.field0.F_()) {
            String var5 = CustomHubMap.field0.method1();
            if (var5.toLowerCase().endsWith(".gif")) {
               NoDebuffModule.method0(new FileInputStream(var5));
               CustomHubMap.field5 = null;
               return;
            }

            CustomHubMap.field5 = new CustomHubMap.Class0(CustomHubMap.field58.getTextureManager().getDynamicTextureLocation("customHubMap", new DynamicTexture(ImageIO.read(new File(var5)))), 0);
         }
      } catch (Exception var4) {
         if (var4 instanceof ArrayIndexOutOfBoundsException) {
            CustomHubMap.aD_ = new CustomHubMap.Class0[0];
         }

         var4.printStackTrace();
         CustomHubMap.method2("An exception occured while loading the image! " + var4.getClass().getSimpleName());
      }

   }

   public AnimationsModule() {
      super("Animations", Category.render, SubCategory.ui, "Changes the position of the held item");
      this.method0(new Setting[]{this.field15, this.field13, this.field11, this.field4, this.field8, this.field0, this.field14, this.field2, this.field3, this.field12, this.aV_, this.field10, this.field7, this.field5, this.field9, this.field6});
   }

   public static MovingObjectPosition method0(float var0, float var1) {
      Vec3 var2 = ScaffoldModule.field58.thePlayer.getPositionEyes(1.0F);
      Vec3 var3 = AutoFrozilleModule.method0(var0, var1);
      Vec3 var4 = var2.addVector(var3.xCoord * (double)ScaffoldModule.field58.playerController.getBlockReachDistance(), var3.yCoord * (double)ScaffoldModule.field58.playerController.getBlockReachDistance(), var3.zCoord * (double)ScaffoldModule.field58.playerController.getBlockReachDistance());
      return ScaffoldModule.field58.theWorld.rayTraceBlocks(var2, var4);
   }
}
