package oringo.event;

import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraftforge.common.MinecraftForge;
import oringo.module.RenderBarriersModule;

public class Event extends net.minecraftforge.fml.common.eventhandler.Event {
   public static final Minecraft field10 = Minecraft.getMinecraft();
   public boolean field11;

   public boolean method7() {
      if (this.field11) {
         throw new IllegalStateException("Attempted to post an event twice");
      } else {
         try {
            this.field11 = true;
            return MinecraftForge.EVENT_BUS.post(this);
         } catch (Exception var2) {
            RenderBarriersModule.method1("An error occurred!", new ClickEvent(Action.OPEN_FILE, (new File(field10.mcDataDir, "logs/latest.log")).getAbsolutePath()));
            var2.printStackTrace();
            return false;
         }
      }
   }

   public boolean method8() {
      return true;
   }

   public void method9() {
      this.setCanceled(true);
   }
}
