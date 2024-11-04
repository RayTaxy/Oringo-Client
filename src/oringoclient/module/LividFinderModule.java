package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import map.Class208;
import map.Class25;
import map.Class496;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class125;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class LividFinderModule extends Module {
   public static Entity field0 = null;
   public Color dh_ = null;
   public static final BooleanSetting field2 = new BooleanSetting("Target the livid", true);
   public static final BooleanSetting field3 = new BooleanSetting("Hide fake livids", true);

   @SubscribeEvent
   public void method0(Class125 var1) {
      if (var1.field4.equals(field0)) {
         MinigameAimbotModule.method0(var1, this.dh_ == null ? Color.green : this.dh_);
      }

   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
      if (Class496.field13) {
         if (field0 == null) {
            Iterator var2 = field58.theWorld.loadedEntityList.iterator();

            while(var2.hasNext()) {
               Entity var3 = (Entity)var2.next();
               if (var3.getDisplayName().getFormattedText().startsWith("§rLivid")) {
                  field0 = var3;
               }
            }
         } else if (this.dh_ == null) {
            Entity var5 = field58.theWorld.getEntityByID(field0.getEntityId() + 10);
            if (var5 instanceof EntityArmorStand && !var5.getDisplayName().getFormattedText().startsWith("§e﴾ §c§l")) {
               method2("The correct livid is: " + var5.getDisplayName().getFormattedText());
               this.dh_ = ScaffoldModule.j_(var5.getDisplayName().getFormattedText().substring(0, 2));
            }

            if (field3.method1()) {
               Iterator var6 = field58.theWorld.loadedEntityList.iterator();

               while(var6.hasNext()) {
                  Entity var4 = (Entity)var6.next();
                  if (field0 != var4 && var4.getDisplayName().getFormattedText().startsWith("§rLivid")) {
                     field58.theWorld.removeEntity(var4);
                  }
               }
            }
         }
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      field0 = null;
      this.dh_ = null;
   }

   public LividFinderModule() {
      super("Livid Finder", Category.dungeon, SubCategory.main, "Shows you the correct livid");
      this.method0((Setting[])(new Setting[]{field2, field3}));
   }

   public static ServerRotationsModule method5() {
      return ServerRotationsModule.field0;
   }

   public static Class208 method0(int var0, int var1) {
      return Class25.method0((var0 - -185) / 32, (var1 - -185) / 32);
   }

   public static void method0(boolean var0, List var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         BlockPos var4 = (BlockPos)var3.next();
         var2.add(new AxisAlignedBB(var4, var4.add(1, 1, 1)));
      }

      DungeonMapModule.method0(var0, var2);
   }
}
