package oringo.module;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.opengl.GL11;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class HideSummonsModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Hide near boss", true);
   public final BooleanSetting bg_ = new BooleanSetting("Hide wither cloak", true);

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase != Phase.START) {
         if (this.method44() && field58.theWorld != null) {
            Iterator var2 = field58.theWorld.getLoadedEntityList().iterator();

            while(true) {
               Entity var3;
               String var4;
               label43:
               do {
                  while(var2.hasNext()) {
                     var3 = (Entity)var2.next();
                     if (var3 instanceof EntityArmorStand && this.field0.method1()) {
                        var4 = var3.getDisplayName().getFormattedText();
                        continue label43;
                     }

                     if (var3 instanceof EntityCreeper && var3.isInvisible() && ((EntityCreeper)var3).getHealth() == 20.0F && this.bg_.method1()) {
                        field58.theWorld.removeEntity(var3);
                     }
                  }

                  return;
               } while(!var4.contains("Voidgloom Seraph") && !var4.contains("Endstone Protector"));

               Iterator var5 = field58.theWorld.getEntitiesInAABBexcluding(var3, var3.getEntityBoundingBox().expand(5.0D, 5.0D, 5.0D), HideSummonsModule::lambda$onWorldRender$0).iterator();

               while(var5.hasNext()) {
                  Entity var6 = (Entity)var5.next();
                  var6.setPosition(2137.0D, 2137.0D, 2137.0D);
               }
            }
         }
      }
   }

   public static void method3() {
      GL11.glDisable(2960);
   }

   public static boolean method0(EnumDyeColor var0) {
      // $FF: Couldn't be decompiled
   }

   public static boolean lambda$onWorldRender$0(Entity var0) {
      return var0 != field58.thePlayer && !(var0 instanceof EntityArmorStand) && !(var0 instanceof EntityEnderman) && !(var0 instanceof EntityGuardian) && !(var0 instanceof EntityFallingBlock);
   }

   public HideSummonsModule() {
      super("Hide Summons", Category.skyblock, SubCategory.slayer, "Removes mobs near bosses");
      this.method0((Setting[])(new Setting[]{this.field0, this.bg_}));
   }
}
