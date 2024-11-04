package oringo.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class NamesOnlyModule extends Module {
   public static final List field0 = new ArrayList();
   public static final EnumSetting bs_ = new EnumSetting("Mode", "Friends", new String[]{"Friends", "Enemies"});
   public boolean field2;
   public static NamesOnlyModule field3;
   public static final BooleanSetting field4 = new BooleanSetting("Middle Click", false);

   public static void method3() {
      GL11.glStencilFunc(514, 1, 15);
      GL11.glStencilOp(7680, 7680, 7680);
      GL11.glPolygonMode(1032, 6913);
   }

   public void a_(JsonObject var1) {
      field0.clear();
      if (var1.has("names")) {
         JsonArray var2 = var1.getAsJsonArray("names");
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            JsonElement var4 = (JsonElement)var3.next();
            field0.add(var4.getAsString());
         }

      }
   }

   public NamesOnlyModule() {
      super("Names Only", Category.other, SubCategory.other, "Mob filter for auras\nUse .names to add");
      this.method0(new Setting[]{bs_, field4});
      this.method48();
      field3 = this;
   }

   public void method1(JsonObject var1) {
      JsonArray var2 = new JsonArray();
      Iterator var3 = field0.iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         var2.add(new JsonPrimitive(var4));
      }

      var1.add("names", var2);
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (field58.thePlayer != null && field58.theWorld != null && field4.method1()) {
         if (Mouse.isButtonDown(2) && field58.currentScreen == null) {
            if (field58.pointedEntity != null && !this.field2 && !(field58.pointedEntity instanceof EntityArmorStand) && field58.pointedEntity instanceof EntityLivingBase) {
               String var2 = ChatFormatting.stripFormatting(field58.pointedEntity.getName().toLowerCase());
               if (!field0.contains(var2)) {
                  field0.add(var2);
                  ShortbowTriggerbotModule.method0("Oringo Client", "Added " + ChatFormatting.AQUA + var2 + ChatFormatting.RESET + " to name sorting", 2000);
               } else {
                  field0.remove(var2);
                  ShortbowTriggerbotModule.method0("Oringo Client", "Removed " + ChatFormatting.AQUA + var2 + ChatFormatting.RESET + " from name sorting", 2000);
               }
            }

            this.field2 = true;
         } else {
            this.field2 = false;
         }

      }
   }

   public static void method0(double var0, double var2, double var4, double var6, double var8, Color var10) {
      NBTCopyModule.method0((float)var0, (float)var2, (float)var4, (float)var6, (float)var8, var10);
   }
}
