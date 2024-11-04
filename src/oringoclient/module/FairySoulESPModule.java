package oringo.module;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import map.Class266;
import map.Class28;
import map.Class496;
import map.Class98;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.BrushCommand;
import oringo.command.PlayerCommand;
import oringo.event.Class270;
import oringo.event.Class274;
import oringo.event.Class307;
import oringo.event.Class439;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;

public class FairySoulESPModule extends Module {
   public FairySoulESPModule.Class0 field0 = new FairySoulESPModule.Class0((FairySoulESPModule$1)null);
   public final BooleanSetting field1 = new BooleanSetting("Aura", true);
   public static final String field2 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk2OTIzYWQyNDczMTAwMDdmNmFlNWQzMjZkODQ3YWQ1Mzg2NGNmMTZjMzU2NWExODFkYzhlNmIyMGJlMjM4NyJ9fX0=";
   public final ArrayList field3 = new ArrayList();
   public final Class266 field4 = new Class266();
   public final ArrayList field5 = new ArrayList();

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!this.field5.isEmpty()) {
         this.field3.clear();
         Iterator var2 = this.field5.iterator();

         while(var2.hasNext()) {
            EntityArmorStand var3 = (EntityArmorStand)var2.next();
            if (var3.isDead) {
               var2.remove();
               return;
            }

            if (!this.field0.method0(var3)) {
               this.field3.add(var3);
            }
         }

         if (!this.field3.isEmpty()) {
            this.field4.method0((List)this.field3.stream().map(FairySoulESPModule::lambda$onUpdate$0).collect(Collectors.toList()));
         }
      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field5.clear();
      this.field3.clear();
   }

   public void method1(JsonObject var1) {
      var1.add("clicked", (new Gson()).toJsonTree(this.field0));
   }

   @SubscribeEvent
   public void method0(Class274 var1) {
      if (field58.objectMouseOver != null) {
         this.method0(field58.objectMouseOver.entityHit);
      }
   }

   public static Minecraft access$100() {
      return field58;
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (!this.field3.isEmpty()) {
         AutoBlazeModule.method0(PlayerCommand.method0(Color.MAGENTA, 160));
         GlStateManager.blendFunc(770, 771);
         GlStateManager.enableBlend();
         GlStateManager.disableTexture2D();
         GlStateManager.disableDepth();
         GlStateManager.disableAlpha();
         GlStateManager.depthMask(false);
         GlStateManager.disableLighting();
         GlStateManager.enableCull();
         BrushCommand.method3();
         this.field4.method2();
         Class28.method1();
         GlStateManager.enableAlpha();
         GlStateManager.enableTexture2D();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         GlStateManager.disableBlend();
         GlStateManager.enableLighting();
         AutoCloseModule.method5();
      }
   }

   public void method0(Entity var1) {
      if (this.field5.contains(var1)) {
         this.field0.method0(var1);
         this.field5.remove(var1);
         this.field3.remove(var1);
      }
   }

   @SubscribeEvent
   public void method0(Class439 var1) {
      if (var1.field0 instanceof EntityArmorStand) {
         this.method0((EntityArmorStand)var1.field0);
      }
   }

   public void method0(EntityArmorStand var1) {
      ItemStack var2 = var1.getCurrentArmor(3);
      if (var2 != null && "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk2OTIzYWQyNDczMTAwMDdmNmFlNWQzMjZkODQ3YWQ1Mzg2NGNmMTZjMzU2NWExODFkYzhlNmIyMGJlMjM4NyJ9fX0=".equals(Class98.method0(var2))) {
         this.field5.add(var1);
      }

   }

   public FairySoulESPModule() {
      super("Fairy Soul ESP", Category.render, SubCategory.visual);
   }

   public static Vec3 lambda$onUpdate$0(EntityArmorStand var0) {
      return var0.getPositionEyes(1.0F).addVector(-0.5D, -0.3D, -0.5D);
   }

   public void a_(JsonObject var1) {
      if (var1.has("clicked")) {
         this.field0 = (FairySoulESPModule.Class0)(new Gson()).fromJson(var1.getAsJsonObject("clicked"), FairySoulESPModule.Class0.class);
      }
   }

   public static void method6() {
      EnigmaSoulESPModule.method0(1.0F);
   }

   public void method4() {
      if (field58.theWorld != null) {
         this.field5.clear();
         Iterator var1 = field58.theWorld.loadedEntityList.iterator();

         while(var1.hasNext()) {
            Entity var2 = (Entity)var1.next();
            if (var2 instanceof EntityArmorStand) {
               this.method0((EntityArmorStand)var2);
            }
         }

      }
   }

   public static Minecraft access$200() {
      return field58;
   }

   @SubscribeEvent
   public void method0(Class307 var1) {
      if (field58.objectMouseOver != null) {
         this.method0(field58.objectMouseOver.entityHit);
      }
   }

   private static class Class0 {
      @SerializedName("clicked")
      public final HashMap field0;

      private static List lambda$click$0(String var0) {
         return new ArrayList();
      }

      public boolean method0(EntityArmorStand var1) {
         if (Class496.field2 == null) {
            return true;
         } else {
            List var2 = (List)this.field0.get(Class496.field2 + FairySoulESPModule.access$100().getSession().getPlayerID());
            return var2 == null ? false : var2.contains((new BlockPos(var1)).toLong());
         }
      }

      public void method0(Entity var1) {
         if (Class496.field2 != null) {
            String var2 = Class496.field2 + FairySoulESPModule.access$200().getSession().getPlayerID();
            List var3 = (List)this.field0.computeIfAbsent(var2, FairySoulESPModule.Class0::lambda$click$0);
            var3.add((new BlockPos(var1)).toLong());
         }
      }

      Class0(FairySoulESPModule$1 var1) {
         this();
      }

      private Class0() {
         this.field0 = new HashMap();
      }
   }
}
