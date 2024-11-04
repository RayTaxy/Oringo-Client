package oringo.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class225;
import map.Class374;
import map.Class447;
import map.Class528;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class148;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class439;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class CustomESPModule extends Module {
   public final Class447 field0 = new Class447();
   public static final Map field1 = new HashMap();
   public final BooleanSetting field2 = new BooleanSetting("Glow", true);
   public final BooleanSetting field3 = new BooleanSetting("Middle click to copy name", true);
   public final BooleanSetting field4 = new BooleanSetting("tracers", false);
   public static final Pattern field5 = Pattern.compile("\\[.+] (.+) \\d+/\\d+.+");
   public final BooleanSetting field6 = new BooleanSetting("2D", false);
   public static final ArrayList field7 = new ArrayList();
   public final BooleanSetting field8 = new BooleanSetting("Box", false);

   public static double method5() {
      double var0 = SpeedModule.field58.thePlayer.isSprinting() ? 0.2873D : 0.221D;
      if (SpeedModule.field58.thePlayer.isPotionActive(Potion.moveSpeed)) {
         var0 *= 1.0D + 0.2D * (double)(SpeedModule.field58.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1);
      }

      if (SpeedModule.field58.thePlayer.isPotionActive(Potion.moveSlowdown)) {
         var0 *= 1.0D - 0.15D * (double)(SpeedModule.field58.thePlayer.getActivePotionEffect(Potion.moveSlowdown).getAmplifier() + 1);
      }

      return var0;
   }

   public static void lambda$onLoad$0(JsonElement var0) {
      JsonObject var1 = var0.getAsJsonObject();
      field1.put(var1.get("name").getAsString(), new Color(var1.get("color").getAsInt()));
   }

   public static void lambda$onSave$1(JsonArray var0, String var1, Color var2) {
      JsonObject var3 = new JsonObject();
      var3.addProperty("name", var1);
      var3.addProperty("color", var2.getRGB());
      var0.add(var3);
   }

   @SubscribeEvent
   public void method0(Class439 var1) {
      if (!field1.isEmpty()) {
         this.method0(var1.field0);
      }
   }

   public void method0(Entity var1) {
      if (this.field0.method0(1000L, true)) {
         field7.removeIf(CustomESPModule::lambda$testEntity$2);
      }

      if (var1.getDistanceSqToEntity(field58.thePlayer) <= 10000.0D) {
         Iterator var2 = field1.entrySet().iterator();

         while(var2.hasNext()) {
            Entry var3 = (Entry)var2.next();
            String var4 = (String)var3.getKey();
            if (var4.startsWith("*")) {
               var4 = var4.substring(1);
               String var5 = EntityList.getEntityString(var1);
               if (var5 != null && var5.toLowerCase().contains(var4)) {
                  field7.add(new Class225(var1, var3.getValue()));
               }
            }
         }

         if (var1 instanceof EntityArmorStand && var1.hasCustomName()) {
            EntityArmorStand var9 = (EntityArmorStand)var1;
            Iterator var10 = field1.entrySet().iterator();

            Entry var11;
            do {
               if (!var10.hasNext()) {
                  return;
               }

               var11 = (Entry)var10.next();
            } while(!var9.getName().toLowerCase().contains((CharSequence)var11.getKey()));

            Entity var12 = field58.theWorld.getEntityByID(var9.getEntityId() - 1);
            if (var12 instanceof EntityArmorStand && ((EntityArmorStand)var12).hasMarker()) {
               var12 = field58.theWorld.getEntityByID(var9.getEntityId() - 2);
            }

            if (var12 != null) {
               Color var6 = (Color)var11.getValue();
               Iterator var7 = field7.iterator();

               while(var7.hasNext()) {
                  Class225 var8 = (Class225)var7.next();
                  if (((Entity)var8.field1).getEntityId() == var12.getEntityId()) {
                     return;
                  }
               }

               field7.add(new Class225(var12, var6));
            }

         }
      }
   }

   public CustomESPModule() {
      super("Custom ESP", Category.render, SubCategory.visual, "Shows you entities specified with the esp command");
      this.method0((Setting[])(new Setting[]{this.field2, this.field6, this.field8, this.field4, this.field3}));
   }

   public void a_(JsonObject var1) {
      field1.clear();
      if (var1.has("esp_data")) {
         var1.getAsJsonArray("esp_data").forEach(CustomESPModule::lambda$onLoad$0);
      }
   }

   public static boolean lambda$testEntity$2(Class225 var0) {
      return ((Entity)var0.field1).getDistanceSqToEntity(field58.thePlayer) > 10000.0D || ((Entity)var0.field1).isDead;
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      field7.clear();
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field6.method1() || this.field8.method1() || this.field2.method1() || this.field4.method1()) {
         Iterator var2 = field7.iterator();

         while(true) {
            Color var4;
            Entity var5;
            do {
               do {
                  if (!var2.hasNext()) {
                     return;
                  }

                  Class225 var3 = (Class225)var2.next();
                  var4 = (Color)var3.field0;
                  var5 = (Entity)var3.field1;
               } while(!(var5 instanceof EntityLivingBase));

               if (this.field4.method1()) {
                  Class528.method0(var5, var1.partialTicks, 1.0F, var4);
               }

               if (this.field2.method1()) {
                  ScaffoldModule.method0((EntityLivingBase)var5, var4);
               }
            } while(!this.field8.method1() && !this.field6.method1());

            if (Class210.method0(var5)) {
               if (this.field6.method1()) {
                  Class374.method0(var5, var1.partialTicks, 1.0F, var4);
               }

               if (this.field8.method1()) {
                  AutoIceFillModule.method0(var5, var1.partialTicks, var4);
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Class148 var1) {
      if (field58.theWorld != null && field58.pointedEntity != null && this.field3.method1()) {
         Entity var2 = field58.theWorld.getEntityByID(field58.pointedEntity.getEntityId() + 1);
         if (var2 instanceof EntityArmorStand && !var2.hasCustomName() && field58.pointedEntity.isInvisible()) {
            var2 = field58.theWorld.getEntityByID(field58.pointedEntity.getEntityId() + 2);
         }

         if (var2 instanceof EntityArmorStand) {
            Matcher var3 = field5.matcher(ChatFormatting.stripFormatting(var2.getName()));
            if (var3.find()) {
               method2("Copied name to clipboard!");
               Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(var3.group(1)), (ClipboardOwner)null);
            }

         }
      }
   }

   public void method1(JsonObject var1) {
      JsonArray var2 = new JsonArray();
      field1.forEach(CustomESPModule::lambda$onSave$1);
      var1.add("esp_data", var2);
   }

   public void method4() {
      SecretHitboxesModule.method0("Use the esp command to add mobs", 5000);
      this.method6();
   }

   public void method6() {
      field7.clear();
      if (field58.theWorld != null) {
         Iterator var1 = field58.theWorld.loadedEntityList.iterator();

         while(var1.hasNext()) {
            Entity var2 = (Entity)var1.next();
            this.method0(var2);
         }

      }
   }
}
