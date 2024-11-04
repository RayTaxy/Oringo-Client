package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import map.Class118;
import map.Class237;
import map.Class28;
import map.Class357;
import map.Class362;
import map.Class483;
import map.Class496;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.BrushCommand;
import oringo.event.Class464;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.ColorSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class ChestESPModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Only Closed", false);
   public final BooleanSetting field1 = new BooleanSetting("Cursed", false);
   public double field2 = 0.0D;
   public final EnumSetting field3 = new EnumSetting("Mode", "Chams", new String[]{"Chams", "Box"});
   public final Class483 field4 = new Class118();
   public final BooleanSetting field5 = new BooleanSetting("Tracer", true);
   public final BooleanSetting field6 = new BooleanSetting("Only Dungeon", false);
   public final ColorSetting field7 = new ColorSetting("Color", new Color(0, 255, 0, 90), true, this::lambda$new$0);

   public static void method0(float var0, float var1, float var2, float var3) {
      float var4 = var0 / (float)Oringo.field9.displayWidth;
      float var5 = ((float)Oringo.field9.displayHeight - var1) / (float)Oringo.field9.displayHeight;
      float var6 = (var0 + var2) / (float)Oringo.field9.displayWidth;
      float var7 = ((float)Oringo.field9.displayHeight - var1 - var3) / (float)Oringo.field9.displayHeight;
      GL11.glBegin(7);
      GL11.glTexCoord2f(var4, var5);
      GL11.glVertex2f(var0, var1);
      GL11.glTexCoord2f(var4, var7);
      GL11.glVertex2f(var0, var1 + var3);
      GL11.glTexCoord2f(var6, var7);
      GL11.glVertex2f(var0 + var2, var1 + var3);
      GL11.glTexCoord2f(var6, var5);
      GL11.glVertex2f(var0 + var2, var1);
      GL11.glEnd();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field58.theWorld != null) {
         ArrayList var2 = new ArrayList();
         Iterator var3 = field58.theWorld.loadedTileEntityList.iterator();

         while(true) {
            TileEntity var4;
            do {
               do {
                  if (!var3.hasNext()) {
                     if (!this.field3.method0(0)) {
                        this.field4.method0((List)var2);
                     }

                     return;
                  }

                  var4 = (TileEntity)var3.next();
               } while(!(var4 instanceof TileEntityChest));
            } while(this.field0.method1() && ((TileEntityChest)var4).lidAngle != 0.0F);

            var2.add(new Vec3(var4.getPos()));
         }
      }
   }

   public ChestESPModule() {
      super("ChestESP", Category.render, SubCategory.world, "Shows you chests");
      this.method0((Setting[])(new Setting[]{this.field3, this.field7, this.field5, this.field1, this.field6, this.field0}));
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (!this.field4.F_() && this.field3.method0(1)) {
         GlStateManager.disableTexture2D();
         GlStateManager.disableDepth();
         GlStateManager.depthMask(false);
         GlStateManager.disableLighting();
         GL11.glDisable(2848);
         GL11.glLineWidth(2.0F);
         GlStateManager.color((float)this.field7.method7() / 255.0F, (float)this.field7.method2() / 255.0F, (float)this.field7.method3() / 255.0F, (float)this.field7.method4() / 255.0F);
         GlStateManager.enableBlend();
         BrushCommand.method3();
         long var2 = System.nanoTime();
         this.field4.method3();
         MithrilMacroModule2.method5().method0("Chest render", (System.nanoTime() - var2) * 10L);
         Class28.method1();
         AutoCloseModule.method5();
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         GlStateManager.enableLighting();
      }

      this.field2 = (double)(System.currentTimeMillis() / 2L % 3600L);
      if (this.field5.method1() && (!this.field6.method1() || Class496.field5)) {
         Iterator var4 = ((List)field58.theWorld.loadedTileEntityList.stream().filter(ChestESPModule::lambda$onRender$1).collect(Collectors.toList())).iterator();

         while(true) {
            TileEntity var3;
            do {
               if (!var4.hasNext()) {
                  return;
               }

               var3 = (TileEntity)var4.next();
            } while(this.field0.method1() && ((TileEntityChest)var3).lidAngle != 0.0F);

            AutoArrowModule.method0((double)var3.getPos().getX() + 0.5D, (double)var3.getPos().getY() + 0.5D, (double)var3.getPos().getZ() + 0.5D, Class362.field7.method17());
         }
      }
   }

   public Boolean lambda$new$0() {
      return !this.field3.method0(1);
   }

   @SubscribeEvent
   public void method0(Class464.Class1 var1) {
      if (this.field1.method1() || this.field3.method0(0)) {
         if (field58.theWorld.loadedTileEntityList.contains(var1.method1()) && (!this.field6.method1() || Class496.field5)) {
            if (this.field1.method1()) {
               Class237.method0((double)var1.method1().getPos().getX() + 0.5D, (double)var1.method1().getPos().getY() + 0.5D, (double)var1.method1().getPos().getZ() + 0.5D);
               GL11.glRotated(this.field2, 0.1D, 1.0D, 0.2D);
               Class357.method0((double)var1.method1().getPos().getX() + 0.5D, (double)var1.method1().getPos().getY() + 0.5D, (double)var1.method1().getPos().getZ() + 0.5D);
            }

            if (this.field3.method0(0)) {
               KillAuraModule.method12();
            }
         }

      }
   }

   public static boolean lambda$onRender$1(TileEntity var0) {
      return var0 instanceof TileEntityChest;
   }

   @SubscribeEvent
   public void field0(Class464.Class0 var1) {
      if (this.field1.method1() || this.field3.method0(0)) {
         if (field58.theWorld.loadedTileEntityList.contains(var1.method1()) && (!this.field6.method1() || Class496.field5)) {
            if (this.field0.method1() && var1.method1().lidAngle != 0.0F) {
               return;
            }

            if (this.field3.method0(0)) {
               AutoSalvageModule.method14();
            }

            if (this.field1.method1()) {
               Class237.method0((double)var1.method1().getPos().getX() + 0.5D, (double)var1.method1().getPos().getY() + 0.5D, (double)var1.method1().getPos().getZ() + 0.5D);
               GL11.glRotated(-this.field2, 0.1D, 1.0D, 0.2D);
               Class357.method0((double)var1.method1().getPos().getX() + 0.5D, (double)var1.method1().getPos().getY() + 0.5D, (double)var1.method1().getPos().getZ() + 0.5D);
            }
         }

      }
   }
}
