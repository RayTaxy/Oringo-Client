package oringo.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import map.Class144;
import map.Class28;
import map.Class283;
import map.Class46;
import map.Class469;
import map.Class479;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import oringo.command.BrushCommand;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class DungeonMapModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Translate to player", false);
   public final EnumSetting field1 = new EnumSetting("Background", "Clean", new String[]{"Clean", "Classic", "None"});
   public final DoubleSetting field2 = new DoubleSetting("Y offset", 2.0D, 1.0D, 3.0D, 0.1D, this::lambda$new$3);
   public final BooleanSetting field3 = new BooleanSetting("Rotate map", true, this::lambda$new$2);
   public final BooleanSetting field4 = new BooleanSetting("Draw normal room names", true);
   public final ColorSetting field5 = new ColorSetting("Color", 0, false);
   public final DoubleSetting field6 = new DoubleSetting("x", 0.05D, 0.0D, 0.0D, 0.0D, DungeonMapModule::lambda$new$4);
   public final BooleanSetting field7 = new BooleanSetting("Blur", true);
   public final BooleanSetting field8 = new BooleanSetting("Run info", true);
   public final DoubleSetting field9 = new DoubleSetting("Font size", 7.0D, 1.0D, 20.0D, 0.1D);
   public final BooleanSetting field10 = new BooleanSetting("Disable in boss", true);
   public final DoubleSetting field11 = new DoubleSetting("Scale", 1.0D, 0.1D, 2.0D, 0.01D);
   public final DoubleSetting field12 = new DoubleSetting("y", 0.15D, 0.0D, 0.0D, 0.0D, DungeonMapModule::lambda$new$5);
   public final BooleanSetting field13 = new BooleanSetting("Hide cleared room names", true);
   public final BooleanSetting field14 = new BooleanSetting("Draw secret count", true);
   public final DoubleSetting field15 = new DoubleSetting("Player size", 8.0D, 4.0D, 20.0D, 2.0D);
   public final EnumSetting field16 = new EnumSetting("Border", this::lambda$new$1, "Theme", new String[]{"Theme", "Chroma", "Custom"});
   public final BooleanSetting field17 = new BooleanSetting("Show minibosses", false);

   public Boolean lambda$new$1() {
      return !this.field1.method0(1);
   }

   public static Boolean lambda$new$5() {
      return true;
   }

   public static TargetHUDModule method7() {
      return TargetHUDModule.field4;
   }

   public Boolean lambda$new$2() {
      return !this.field0.method1();
   }

   public static Boolean lambda$new$4() {
      return true;
   }

   public static boolean method0(int var0, int var1) {
      for(int var2 = 50; var2 < 100; ++var2) {
         if (Class469.field0.theWorld.getBlockState(new BlockPos(var0, var2, var1)).getBlock() != Blocks.air) {
            return false;
         }
      }

      return true;
   }

   public static void method0(boolean var0, List var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3;
      AxisAlignedBB var4;
      if (var0) {
         var3 = var1.iterator();

         while(var3.hasNext()) {
            var4 = (AxisAlignedBB)var3.next();
            var2.add(new BlockPos(var4.minX, var4.minY, var4.minZ));
         }
      }

      Class479.field3.begin(7, DefaultVertexFormats.POSITION);
      var3 = var1.iterator();

      while(true) {
         BlockPos var5;
         do {
            if (!var3.hasNext()) {
               GlStateManager.blendFunc(770, 771);
               GlStateManager.enableBlend();
               GlStateManager.disableTexture2D();
               GlStateManager.disableDepth();
               GlStateManager.depthMask(false);
               GlStateManager.disableLighting();
               BrushCommand.method3();
               Class479.field1.draw();
               Class28.method1();
               GlStateManager.enableTexture2D();
               GlStateManager.enableDepth();
               GlStateManager.depthMask(true);
               GlStateManager.disableBlend();
               return;
            }

            var4 = (AxisAlignedBB)var3.next();
            var5 = new BlockPos(var4.minX, var4.minY, var4.minZ);
            if (!var0 || !var2.contains(var5.add(0, -1, 0))) {
               Class479.field3.pos(var4.minX, var4.minY, var4.minZ).endVertex();
               Class479.field3.pos(var4.maxX, var4.minY, var4.minZ).endVertex();
               Class479.field3.pos(var4.maxX, var4.minY, var4.maxZ).endVertex();
               Class479.field3.pos(var4.minX, var4.minY, var4.maxZ).endVertex();
            }

            if (!var0 || !var2.contains(var5.add(0, 1, 0))) {
               Class479.field3.pos(var4.minX, var4.maxY, var4.minZ).endVertex();
               Class479.field3.pos(var4.minX, var4.maxY, var4.maxZ).endVertex();
               Class479.field3.pos(var4.maxX, var4.maxY, var4.maxZ).endVertex();
               Class479.field3.pos(var4.maxX, var4.maxY, var4.minZ).endVertex();
            }

            if (!var0 || !var2.contains(var5.add(-1, 0, 0))) {
               Class479.field3.pos(var4.minX, var4.minY, var4.maxZ).endVertex();
               Class479.field3.pos(var4.minX, var4.maxY, var4.maxZ).endVertex();
               Class479.field3.pos(var4.minX, var4.maxY, var4.minZ).endVertex();
               Class479.field3.pos(var4.minX, var4.minY, var4.minZ).endVertex();
            }

            if (!var0 || !var2.contains(var5.add(1, 0, 0))) {
               Class479.field3.pos(var4.maxX, var4.minY, var4.minZ).endVertex();
               Class479.field3.pos(var4.maxX, var4.maxY, var4.minZ).endVertex();
               Class479.field3.pos(var4.maxX, var4.maxY, var4.maxZ).endVertex();
               Class479.field3.pos(var4.maxX, var4.minY, var4.maxZ).endVertex();
            }

            if (!var0 || !var2.contains(var5.add(0, 0, -1))) {
               Class479.field3.pos(var4.minX, var4.minY, var4.minZ).endVertex();
               Class479.field3.pos(var4.minX, var4.maxY, var4.minZ).endVertex();
               Class479.field3.pos(var4.maxX, var4.maxY, var4.minZ).endVertex();
               Class479.field3.pos(var4.maxX, var4.minY, var4.minZ).endVertex();
            }
         } while(var0 && var2.contains(var5.add(0, 0, 1)));

         Class479.field3.pos(var4.maxX, var4.minY, var4.maxZ).endVertex();
         Class479.field3.pos(var4.maxX, var4.maxY, var4.maxZ).endVertex();
         Class479.field3.pos(var4.minX, var4.maxY, var4.maxZ).endVertex();
         Class479.field3.pos(var4.minX, var4.minY, var4.maxZ).endVertex();
      }
   }

   public DungeonMapModule() {
      super("Dungeon Map", Category.render, SubCategory.main, "Drag in chat");
      Class144 var1 = new Class144(this);
      ClickGuiModule.method0((Class283)var1);
      this.method0(new Setting[]{this.field1, this.field16, this.field5, this.field7, this.field10, this.field4, this.field13, this.field14, this.field8, this.field15, this.field0, this.field3, this.field2, this.field11, this.field9, this.field17, this.field6, this.field12, new ButtonSetting("Reset position", this::lambda$new$0)});
      Class46[] var2 = Class46.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Class46 var5 = var2[var4];
         this.method0(var5.field2);
      }

   }

   public void lambda$new$0() {
      this.field6.method4(0.05D);
      this.field12.method4(0.15D);
      this.field11.method4(1.0D);
   }

   public Boolean lambda$new$3() {
      return !this.field0.method1();
   }
}
