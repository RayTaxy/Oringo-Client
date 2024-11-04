package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import map.Class296;
import map.Class376;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class270;
import oringo.event.Class439;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class DragonHitboxesModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Only M7", false);
   public final List field1 = new ArrayList();
   public final HashMap field2 = new HashMap();

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.b_();
   }

   @SubscribeEvent
   public void method0(Class439 var1) {
      if (!this.field0.method1() || Class376.method0("(M7)")) {
         if (var1.field0 instanceof EntityDragon && !var1.field0.isInvisible()) {
            this.field1.add((EntityDragon)var1.field0);
         }

      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      Iterator var2 = this.field2.keySet().iterator();

      while(var2.hasNext()) {
         Entity var3 = (Entity)var2.next();
         double[] var4 = (double[])this.field2.get(var3);
         double var5 = (var3.posX - var4[0]) * (double)(1.0F - var1.partialTicks);
         double var7 = (var3.posY - var4[1]) * (double)(1.0F - var1.partialTicks);
         double var9 = (var3.posZ - var4[2]) * (double)(1.0F - var1.partialTicks);
         AutoIceSprayModule.method0(var3.getEntityBoundingBox().offset(-var5, -var7, -var9), Color.WHITE);
      }

   }

   public DragonHitboxesModule() {
      super("Dragon Hitboxes", Category.render, SubCategory.visual, "Renders real dragon hitboxes");
      this.method0((Setting[])(new Setting[]{this.field0}));
   }

   public void method4() {
      if (field58.theWorld != null && (!this.field0.method1() || Class376.method0("(M7)"))) {
         Iterator var1 = field58.theWorld.loadedEntityList.iterator();

         while(var1.hasNext()) {
            Entity var2 = (Entity)var1.next();
            if (var2 instanceof EntityDragon && !var2.isInvisible()) {
               this.field1.add((EntityDragon)var2);
            }
         }

      }
   }

   public static void method5() {
      ++Class296.field1;
   }

   public static boolean method6() {
      return !SpeedModule.field3.a_(2000L);
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!this.field1.isEmpty()) {
         this.field2.clear();
         if (this.field0.method1() && !Class376.method0("(M7)")) {
            this.field1.clear();
         } else {
            ArrayList var2 = new ArrayList();
            Iterator var3 = this.field1.iterator();

            while(true) {
               while(var3.hasNext()) {
                  EntityDragon var4 = (EntityDragon)var3.next();
                  if (!var4.isDead && var4.deathTicks == 0 && !var4.isInvisible()) {
                     Entity[] var5 = var4.getParts();
                     int var6 = var5.length;

                     for(int var7 = 0; var7 < var6; ++var7) {
                        Entity var8 = var5[var7];
                        this.field2.put(var8, new double[]{var8.posX, var8.posY, var8.posZ});
                     }
                  } else {
                     var2.add(var4);
                  }
               }

               this.field1.removeAll(var2);
               return;
            }
         }
      }
   }

   public void b_() {
      this.field1.clear();
      this.field2.clear();
   }
}
