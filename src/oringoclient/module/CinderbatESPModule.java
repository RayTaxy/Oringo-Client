package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import map.Class361;
import map.Class461;
import map.Class518;
import map.Class528;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class CinderbatESPModule extends Module {
   public final ArrayList field0 = new ArrayList();
   public final BooleanSetting aA_ = new BooleanSetting("Tracers", true);

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field58.theWorld.getSkylightSubtracted() == 11) {
         this.field0.clear();
         Iterator var2 = field58.theWorld.loadedEntityList.iterator();

         while(var2.hasNext()) {
            Entity var3 = (Entity)var2.next();
            if (var3 instanceof EntityBat && !var3.isDead && ((EntityBat)var3).deathTime == 0 && (double)Class518.method0((EntityLivingBase)var3) == 5000000.0D) {
               this.field0.add((EntityBat)var3);
               var3.setInvisible(false);
            }
         }

      }
   }

   public CinderbatESPModule() {
      super("Cinderbat ESP", Category.render, SubCategory.visual, "Renders ESP on cinderbats");
      this.method0((Setting)this.aA_);
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (field58.theWorld.getSkylightSubtracted() == 11) {
         if (!this.field0.isEmpty()) {
            Iterator var2 = this.field0.iterator();

            while(var2.hasNext()) {
               EntityBat var3 = (EntityBat)var2.next();
               String var4 = AutoArrowModule.method0(var3, "Cinderbat", 90, 5000000);
               BloodAimbotModule.method0(var3, var4, var1.partialTicks, 48);
               float var5 = FrozenTreasureESPModule.method5().renderPartialTicks;
               AutoIceFillModule.method0(var3, var5, Color.GREEN);
               if (this.aA_.method1()) {
                  Class528.method0(var3, var5, 1.0F, Color.GREEN);
               }
            }

         }
      }
   }

   public static double method5() {
      return Math.max(CustomESPModule.method5() * 0.75D, Class361.method1(SpeedModule.field58.thePlayer.motionX, SpeedModule.field58.thePlayer.motionZ));
   }

   public static void o_() {
      GemstoneESPOldModule.field8.clear();
      EnumDyeColor[] var0 = EnumDyeColor.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         EnumDyeColor var3 = var0[var2];
         GemstoneESPOldModule.field8.put(var3, new Class461(true));
      }

   }
}
