package oringo.module;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import map.Class142;
import map.Class229;
import map.Class24;
import map.Class361;
import map.Class496;
import map.Class94;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.command.Command;
import oringo.event.Class75;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoQuizModule extends Module {
   public static String[] field0;
   public static final HashMap b_ = new AutoQuizModule$1();
   public static final DoubleSetting field2 = new DoubleSetting("Distance", 64.0D, 1.0D, 64.0D, 0.1D);

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void method0(ClientChatReceivedEvent var1) {
      if (var1.type != 2 && Class496.field5) {
         String var2 = var1.message.getUnformattedText().trim();
         if (var2.startsWith("[STATUE] Oruo the Omniscient: ") && var2.endsWith("correctly!")) {
            field0 = null;
         } else if (var2.startsWith("What Skyblock year is it?")) {
            field0 = new String[]{"Year " + AutoAlignModule.method6()};
         } else {
            String[] var3 = (String[])b_.get(var2);
            if (var3 != null) {
               field0 = var3;
            }
         }

      }
   }

   public static void f_() {
      try {
         Method var0;
         try {
            var0 = Minecraft.class.getDeclaredMethod("func_147121_ag");
         } catch (NoSuchMethodException var2) {
            var0 = Minecraft.class.getDeclaredMethod("rightClickMouse");
         }

         var0.setAccessible(true);
         var0.invoke(Minecraft.getMinecraft());
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public AutoQuizModule() {
      super("Auto Quiz", Category.dungeon, SubCategory.puzzle, (String)null);
      this.method0((Setting[])(new Setting[]{field2}));
   }

   public static void method0(float var0, float var1, float var2) {
      GL11.glTranslatef(var0, var1, 0.0F);
      GL11.glScalef(var2, var2, var2);
      GL11.glTranslatef(-var0, -var1, 0.0F);
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field58.thePlayer.ticksExisted % 5 == 0 && field0 != null) {
         Iterator var2 = field58.theWorld.getEntities(EntityArmorStand.class, Class229::method0).iterator();

         while(var2.hasNext()) {
            EntityArmorStand var3 = (EntityArmorStand)var2.next();
            if (var3.hasMarker()) {
               method2("Error 534, please report this");
            } else {
               Class361.method0((Class94)(new Class142(var3)));
            }
         }
      }

   }

   public static void method0(Command var0) {
      String[] var1 = var0.method5();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         String var4 = var1[var3];
         Class24.field0.remove(var4.toLowerCase());
      }

   }
}
