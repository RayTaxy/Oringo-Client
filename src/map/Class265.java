package map;

import com.google.common.collect.Maps;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import net.minecraftforge.client.event.RenderPlayerEvent.Post;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;

public class Class265 {
   public static final File field0;
   public static final Map field1 = Maps.newConcurrentMap();

   public static boolean method0(Predicate var0) {
      ArrayList var1 = Class82.field2;
      Iterator var2 = var1.iterator();

      String var3;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         var3 = (String)var2.next();
      } while(!var0.test(var3));

      return true;
   }

   public static void method0() {
      Oringo.field9.getFramebuffer().bindFramebuffer(true);
   }

   @SubscribeEvent
   public void method0(Post var1) {
      Class262 var2 = ((Class480)var1.entityPlayer).method17();
      if (var2 != null && Class496.field7) {
         GL11.glTranslated(var1.x, var1.y, var1.z);
         GL11.glScaled(1.0D / var2.method4(), 1.0D / var2.method0(), 1.0D / var2.method4());
         GL11.glTranslated(-var1.x, -var1.y, -var1.z);
      }

   }

   public static boolean method0(char var0) {
      return var0 != 167 && var0 >= ' ' && var0 != 127 || var0 == 167;
   }

   static {
      field0 = new File(Oringo.field5 + "capes/assets/capes");
   }

   @SubscribeEvent
   public void method0(Pre var1) {
      Class262 var2 = ((Class480)var1.entityPlayer).method17();
      if (var2 != null && Class496.field7) {
         GL11.glTranslated(var1.x, var1.y, var1.z);
         GL11.glScaled(var2.method4(), var2.method0(), var2.method4());
         GL11.glTranslated(-var1.x, -var1.y, -var1.z);
      }

   }
}
