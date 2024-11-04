package map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.module.AutoSalvageModule;
import oringo.module.BarPhaseModule;

public class Class434 {
   public static int field0 = -1;
   public static Framebuffer ap_ = new Framebuffer(1, 1, false);
   public static boolean field2;
   public static final Class354 field3 = new Class354("KawaseDown.frag", "Vertex.vert");
   public static final int field4 = 2;
   public static final float field5 = 5.0F;
   public static final Minecraft field6 = Minecraft.getMinecraft();
   public static final Class354 field7 = new Class354("KawaseUp.frag", "Vertex.vert");
   public static Framebuffer field8 = new Framebuffer(1, 1, false);

   static {
      MinecraftForge.EVENT_BUS.register(new Class434());
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void method0(Post var1) {
      if (var1.type == ElementType.TEXT && field2) {
         field2 = false;
         if (BarPhaseModule.method3()) {
            AutoSalvageModule.method6();
         }
      }
   }
}
