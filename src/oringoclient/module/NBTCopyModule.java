package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import map.Class168;
import map.Class351;
import map.Class388;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class NBTCopyModule extends Module {
   public boolean field0 = false;

   public static void method0(float var0, float var1, float var2, float var3, float var4, Color var5) {
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.blendFunc(770, 771);
      Class388.field1.method5();
      PrinterModule.method0(var0, var1, var2, var3, var4, false, var5);
      BossBarModule.method0(var0, var1, var2, var3);
      Class388.field1.method2();
      GlStateManager.enableAlpha();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
   }

   public static void method0(Color var0) {
      Class351.method0(var0);
      GlStateManager.depthMask(false);
      GlStateManager.disableDepth();
      GlStateManager.enablePolygonOffset();
      GlStateManager.doPolygonOffset(1.0F, -2000000.0F);
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
   }

   public boolean method5() {
      boolean var1 = Keyboard.isKeyDown(157);
      boolean var2 = var1 && !this.field0;
      this.field0 = var1;
      return var2;
   }

   @SubscribeEvent
   public void method0(ItemTooltipEvent var1) {
      if (this.method5()) {
         AutoIceSprayModule.b_(Class168.method0(var1.itemStack.serializeNBT()));
         method2("Item NBT tag has been copied to the clipboard!");
      }

   }

   public NBTCopyModule() {
      super("NBT Copy", Category.other, SubCategory.other, "Don't use this features if you don't know, what it does");
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (field58.currentScreen == null) {
         if (this.method5()) {
            StringBuilder var2 = new StringBuilder();
            Iterator var3 = field58.theWorld.loadedEntityList.iterator();

            while(var3.hasNext()) {
               Entity var4 = (Entity)var3.next();
               if (var4.getDistanceSqToEntity(field58.thePlayer) <= 25.0D) {
                  if (var2.length() != 0) {
                     var2.append('\n').append('\n');
                  }

                  NBTTagCompound var5 = new NBTTagCompound();
                  var4.writeToNBT(var5);
                  if (var4 == field58.thePlayer) {
                     var5.removeTag("Inventory");
                  }

                  var2.append("Class: ").append(var4.getClass().getSimpleName()).append('\n');
                  var2.append("Name: ").append(var4.getName()).append('\n');
                  var2.append("Entity ID: ").append(var4.getEntityId()).append('\n');
                  var2.append("NBT: ").append(Class168.method0(var5));
               }
            }

            AutoIceSprayModule.b_(var2.toString());
            method2("Entity NBT tag has been copied to the clipboard!");
         }

      }
   }
}
