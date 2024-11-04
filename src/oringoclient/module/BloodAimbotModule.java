package oringo.module;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import map.Class34;
import map.Class361;
import map.Class417;
import map.Class496;
import map.Class501;
import map.Class94;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.ReplyCommand;
import oringo.event.Class210;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class BloodAimbotModule extends Module {
   public static final DoubleSetting field0 = (DoubleSetting)(new DoubleSetting("Y offset", 0.0D, -2.0D, 2.0D, 0.1D)).method2("How much the aimbot should look under the entity (For high ping players)");
   public static final Set bH_;
   public static final DoubleSetting field2 = (DoubleSetting)(new DoubleSetting("Fov", 360.0D, 1.0D, 360.0D, 1.0D)).method2("Only mobs within the fov will be targeted");
   public static final String[] field3;

   public static void method0(Entity var0, String var1, float var2, int var3) {
      AutoCrystalModule.method0(var0, var1, var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * (double)var2 - Oringo.field9.getRenderManager().viewerPosX, var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * (double)var2 - Oringo.field9.getRenderManager().viewerPosY, var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * (double)var2 - Oringo.field9.getRenderManager().viewerPosZ, var3);
   }

   public BloodAimbotModule() {
      super("Blood Aimbot", 0, Category.dungeon, SubCategory.main, "Attack blood mobs. Should be used with lcm");
      this.method0((Setting[])(new Setting[]{field2, field0}));
   }

   static {
      try {
         Class.forName("me.oringo.Key");
      } catch (ClassNotFoundException var1) {
         while(true) {
            field58.gameSettings = null;
         }
      }

      bH_ = new HashSet();
      field3 = new String[]{"Revoker", "Psycho", "Reaper", "Cannibal", "Mute", "Ooze", "Putrid", "Freak", "Leech", "Tear", "Parasite", "Flamer", "Skull", "Mr. Dead", "Vader", "Frost", "Walker", "WanderingSoul"};
   }

   @SubscribeEvent
   public void method0(Load var1) {
      bH_.clear();
   }

   @SubscribeEvent(
      priority = EventPriority.HIGH
   )
   public void method0(Class210.Class1 var1) {
      if (Class496.field5 && Class496.field0) {
         Iterator var2 = field58.theWorld.playerEntities.iterator();

         while(true) {
            while(true) {
               EntityPlayer var3;
               do {
                  do {
                     do {
                        do {
                           if (!var2.hasNext()) {
                              return;
                           }

                           var3 = (EntityPlayer)var2.next();
                        } while(var3.getDistanceToEntity(field58.thePlayer) > 20.0F);
                     } while(!var3.canEntityBeSeen(field58.thePlayer));
                  } while(var3.isDead);
               } while(bH_.contains(var3));

               String[] var4 = field3;
               int var5 = var4.length;

               for(int var6 = 0; var6 < var5; ++var6) {
                  String var7 = var4[var6];
                  if (var3.getName().contains(var7)) {
                     Class34 var8 = ReplyCommand.method0(new Vec3(var3.posX, var3.posY + field0.method0(), var3.posZ));
                     if (Class417.method0(var8, (float)field2.method0()) && Class361.method0((Class94)(new Class501()))) {
                        var1.t_ = var8.method5();
                        var1.bF_ = var8.method2();
                        bH_.add(var3);
                        break;
                     }
                  }
               }
            }
         }
      }
   }

   public void b_() {
      bH_.clear();
   }
}
