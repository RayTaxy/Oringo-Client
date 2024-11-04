package oringo.module;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import map.Class361;
import map.Class376;
import map.Class510;
import map.Class94;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Timer;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class307;
import oringo.event.Class75;
import oringo.mixin.MinecraftAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class FrozenTreasureESPModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Nuker", false);
   public final BooleanSetting field1 = new BooleanSetting("Tracers", true);
   public final HashSet field2 = new HashSet();
   public final HashSet field3 = new HashSet();
   public final EnumSetting field4 = new EnumSetting("Mode", "Box", new String[]{"Box", "Chams", "Scaled Chams"});
   public boolean field5;

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field5) {
         Iterator var2 = this.field3.iterator();

         BlockPos var3;
         while(var2.hasNext()) {
            var3 = (BlockPos)var2.next();
            if (this.field4.method0(0) && Class307.method0(var3)) {
               AutoMaskModule.method0(var3, Color.CYAN);
            }

            if (this.field1.method1()) {
               AutoArrowModule.method0((double)var3.getX() + 0.5D, (double)var3.getY() + 0.5D, (double)var3.getZ() + 0.5D, Color.CYAN);
            }
         }

         var2 = this.field2.iterator();

         while(var2.hasNext()) {
            var3 = (BlockPos)var2.next();
            if (this.field4.method0(0) && Class307.method0(var3)) {
               AutoMaskModule.method0(var3, Color.ORANGE);
            }

            if (this.field1.method1()) {
               AutoArrowModule.method0((double)var3.getX() + 0.5D, (double)var3.getY() + 0.5D, (double)var3.getZ() + 0.5D, Color.ORANGE);
            }
         }

      }
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Pre var1) {
      if (this.field5) {
         BlockPos var2 = (new BlockPos(var1.entity.posX, var1.entity.posY, var1.entity.posZ)).up(2);
         if (this.field4.method4().endsWith("Chams") && var1.entity instanceof EntityArmorStand && (this.field2.contains(var2) || this.field3.contains(var2))) {
            KillAuraModule.method12();
            if (this.field4.method0(2)) {
               GL11.glTranslated(var1.x, var1.y + 1.7D, var1.z);
               float var3 = (float)Math.sqrt((double)field58.thePlayer.getDistanceToEntity(var1.entity));
               GL11.glScalef(var3, var3, var3);
               GL11.glTranslated(-var1.x, -var1.y - 1.7D, -var1.z);
            }
         }

      }
   }

   public static Timer method5() {
      return ((MinecraftAccessor)Oringo.field9).getTimer();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      this.field2.clear();
      this.field3.clear();
      if (this.field5 = Class376.method0("Glacial Cave")) {
         Iterator var2 = field58.theWorld.getEntities(EntityArmorStand.class, FrozenTreasureESPModule::lambda$onTick$0).iterator();

         while(var2.hasNext()) {
            EntityArmorStand var3 = (EntityArmorStand)var2.next();
            BlockPos var4 = new BlockPos(var3.posX, var3.posY + 1.8D, var3.posZ);
            if (field58.theWorld.getBlockState(var4).getBlock() == Blocks.ice) {
               String var5 = BlockHitModule.method0(var3.getCurrentArmor(3));
               byte var6 = -1;
               switch(var5.hashCode()) {
               case -1756170589:
                  if (var5.equals("GLACIAL_TALISMAN")) {
                     var6 = 2;
                  }
                  break;
               case -1192976853:
                  if (var5.equals("ENCHANTED_PACKED_ICE")) {
                     var6 = 0;
                  }
                  break;
               case 26475582:
                  if (var5.equals("RED_GIFT")) {
                     var6 = 1;
                  }
               }

               switch(var6) {
               case 0:
               case 1:
               case 2:
                  this.field2.add(var4);
                  break;
               default:
                  this.field3.add(var4);
               }
            }
         }

         if (this.field0.method1() && (this.field3.size() > 0 || this.field2.size() > 0) && field58.thePlayer.getHeldItem() != null) {
            if (TrailModule.method0(FrozenTreasureESPModule::lambda$onTick$1) == -1) {
               return;
            }

            var2 = this.field2.iterator();

            BlockPos var7;
            while(var2.hasNext()) {
               var7 = (BlockPos)var2.next();
               if (field58.thePlayer.getPositionEyes(1.0F).squareDistanceTo(new Vec3(var7)) < 36.0D) {
                  Class361.method0((Class94)(new Class510(var7, EnumFacing.UP)));
                  return;
               }
            }

            var2 = this.field3.iterator();

            while(var2.hasNext()) {
               var7 = (BlockPos)var2.next();
               if (field58.thePlayer.getPositionEyes(1.0F).squareDistanceTo(new Vec3(var7)) < 30.0D) {
                  Class361.method0((Class94)(new Class510(var7, EnumFacing.UP)));
                  return;
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (this.field5) {
         BlockPos var2 = (new BlockPos(var1.entity.posX, var1.entity.posY, var1.entity.posZ)).up(2);
         if (this.field4.method4().endsWith("Chams") && var1.entity instanceof EntityArmorStand && (this.field2.contains(var2) || this.field3.contains(var2))) {
            AutoSalvageModule.method14();
            if (this.field4.method0(2)) {
               GL11.glTranslated(var1.x, var1.y + 1.7D, var1.z);
               float var3 = 1.0F / (float)Math.sqrt((double)field58.thePlayer.getDistanceToEntity(var1.entity));
               GL11.glScalef(var3, var3, var3);
               GL11.glTranslated(-var1.x, -var1.y - 1.7D, -var1.z);
            }
         }

      }
   }

   public static boolean lambda$onTick$0(EntityArmorStand var0) {
      return var0.isInvisible() && BlockHitModule.method0(var0.getCurrentArmor(3)) != null && var0.hasMarker();
   }

   public static boolean lambda$onTick$1(ItemStack var0) {
      return var0.getItem().getRegistryName().endsWith("pickaxe");
   }

   public FrozenTreasureESPModule() {
      super("Frozen Treasure ESP", Category.skyblock, SubCategory.visual, "Frozen treasure ESP and Nuker");
      this.method0((Setting[])(new Setting[]{this.field4, this.field1, this.field0}));
   }
}
