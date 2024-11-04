package map;

import com.google.common.collect.Maps;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class210;
import oringo.module.SneakModule;
import oringo.module.StepModule;

public class Class196 {
   public static boolean field0;
   public static final HashMap field1 = Maps.newHashMap();
   public static final Minecraft field2 = Minecraft.getMinecraft();
   public static boolean field3;
   public static final Class354 field4 = new Class354("PreGlow.frag", "Vertex.vert");
   public static final Class354 field5 = new Class354("Glow.frag", "Vertex.vert");
   public static Framebuffer field6 = new Framebuffer(1, 1, false);

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(RenderWorldLastEvent var1) {
      if (StepModule.C_() && !field1.isEmpty()) {
         field6 = Class90.method0(field6);
         field6.framebufferClear();
         field6.bindFramebuffer(false);
         field4.method5();
         field3 = true;
         field1.forEach(Class196::lambda$onRenderWorld$0);
         field3 = false;
         field4.method2();
         field1.clear();
         field6.unbindFramebuffer();
         field2.getFramebuffer().bindFramebuffer(false);
         GlStateManager.disableLighting();
      }
   }

   public static void lambda$onRenderWorld$0(RenderWorldLastEvent var0, EntityLivingBase var1, Color var2) {
      if (Class210.method0((Entity)var1)) {
         field0 = true;
         field4.method0("color", (float)var2.getRed() / 255.0F, (float)var2.getGreen() / 255.0F, (float)var2.getBlue() / 255.0F);
         ((Class488)field2.getRenderManager()).method0(var1, var0.partialTicks);
      }
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void method0(Pre var1) {
      if (var1.type == ElementType.HELMET && field0) {
         field0 = false;
         SneakModule.method1();
      }
   }
}
