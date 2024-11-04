package oringo.module;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import map.Class208;
import map.Class350;
import map.Class361;
import map.Class447;
import map.Class47;
import map.Class496;
import map.Class501;
import map.Class506;
import map.Class514;
import map.Class6;
import map.Class83;
import map.Class94;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.LogsCommand;
import oringo.command.MoveItemCommand;
import oringo.event.Class75;
import oringo.notification.Notifications;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class ShortbowTriggerbotModule extends Module {
   public double Z_ = 10.0D;
   public final DoubleSetting aa_ = new ShortbowTriggerbotModule$2(this, "Min CPS", 8.0D, 1.0D, 20.0D, 1.0D);
   public final Class447 field2 = new Class447();
   public Entity field3;
   public final Class447 field4 = new Class447();
   public final BooleanSetting field5 = (BooleanSetting)(new BooleanSetting("Only dungeon", false)).method2("Makes the triggerbot only work in dungeons");
   public final DoubleSetting field6 = (DoubleSetting)(new ShortbowTriggerbotModule$3(this, "Min shot delay", 150.0D, 0.0D, 500.0D, 25.0D)).method2("Delay between hovering over an entity and shooting");
   public final DoubleSetting field7 = new ShortbowTriggerbotModule$4(this, "Max shot delay", 100.0D, 0.0D, 500.0D, 25.0D);
   public final EnumSetting field8 = new EnumSetting("Click", "Right", new String[]{"Right", "Left"});
   public final BooleanSetting field9 = (BooleanSetting)(new BooleanSetting("Hit blazes", false)).method2("Allows the triggerbot to hit blazes");
   public int field10 = 0;
   public final DoubleSetting field11 = new ShortbowTriggerbotModule$1(this, "Max CPS", 12.0D, 1.0D, 20.0D, 1.0D);

   public static boolean lambda$isValid$0(EntityArmorStand var0) {
      return var0.getDisplayName().getFormattedText().contains("█");
   }

   public boolean a_(EntityLivingBase var1) {
      if (LividFinderModule.field2.method1() && LividFinderModule.field0 != null) {
         return LividFinderModule.field0.equals(var1);
      } else if (var1 != field58.thePlayer && !(var1 instanceof EntityArmorStand)) {
         if ((var1 instanceof EntityPlayer || var1 instanceof EntityBat || var1 instanceof EntityZombie || var1 instanceof EntityGiantZombie || var1 instanceof EntityEnderman || var1 instanceof EntityWither) && var1.isInvisible()) {
            return false;
         } else if (var1.getHealth() < 0.0F) {
            return false;
         } else if (var1 instanceof EntityBlaze && !this.field9.method1()) {
            return false;
         } else if (Class506.method0((Entity)var1) && !Class6.method0(var1) && !var1.getName().equals("Dummy") && !var1.getName().startsWith("Decoy")) {
            if (var1 instanceof EntityMagmaCube && !field58.theWorld.getEntitiesWithinAABB(EntityArmorStand.class, var1.getEntityBoundingBox().expand(0.3D, 2.0D, 0.3D), ShortbowTriggerbotModule::lambda$isValid$0).isEmpty()) {
               return false;
            } else {
               if (LogsCommand.method2().method44()) {
                  boolean var2 = MoveItemCommand.method0((Entity)var1);
                  if (NamesOnlyModule.bs_.method0(1) || var2) {
                     return NamesOnlyModule.bs_.method0(1) && var2;
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean method0(BlockPos var0, String... var1) {
      Class208 var2 = IceFillHelperModule.method0(var0.getX(), var0.getZ());
      if (var2 != null) {
         String[] var3 = var1;
         int var4 = var1.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            if (var6.equalsIgnoreCase(var2.method45())) {
               return true;
            }
         }
      }

      return false;
   }

   public static DoubleSetting access$200(ShortbowTriggerbotModule var0) {
      return var0.field7;
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      ItemStack var2 = field58.thePlayer.getHeldItem();
      if ((Class496.field5 || !this.field5.method1()) && Class47.method0(var2)) {
         Entity var3 = this.method0(field58.thePlayer.posX, field58.thePlayer.posY, field58.thePlayer.posZ, field58.thePlayer.rotationYaw, field58.thePlayer.rotationPitch);
         if (var3 instanceof EntityLivingBase && this.a_((EntityLivingBase)var3)) {
            if (var3 != this.field3) {
               this.field3 = var3;
               this.field2.o_();
               this.field10 = (int)Class83.method0(this.field7.method0(), this.field6.method0());
            }

            if (this.field2.a_((long)this.field10) && this.field4.method0(1000.0D / this.Z_, true)) {
               this.Z_ = Class83.method0(this.field11.method0(), this.aa_.method0());
               if (this.field8.method0(1)) {
                  Class361.method0((Class94)(new Class501()));
               } else {
                  Class361.method0((Class94)(new Class350()));
               }

            }
         }
      }
   }

   public Entity method0(double var1, double var3, double var5, float var7, float var8) {
      float var9 = 0.99F;
      float var10 = 0.05F;
      float var11 = 0.3F;
      float var12 = 3.0F;
      double var13 = var1 - (double)(MathHelper.cos(var7 / 180.0F * 3.1415927F) * 0.16F);
      double var15 = var3 + (double)field58.thePlayer.getEyeHeight() - 0.10000000149011612D;
      double var17 = var5 - (double)(MathHelper.sin(var7 / 180.0F * 3.1415927F) * 0.16F);
      double var19 = (double)(-MathHelper.sin(var7 / 180.0F * 3.1415927F) * MathHelper.cos(var8 / 180.0F * 3.1415927F));
      double var21 = (double)(-MathHelper.sin(var8 / 180.0F * 3.1415927F));
      double var23 = (double)(MathHelper.cos(var7 / 180.0F * 3.1415927F) * MathHelper.cos(var8 / 180.0F * 3.1415927F));
      double var25 = (double)MathHelper.sqrt_double(var19 * var19 + var21 * var21 + var23 * var23);
      var19 /= var25;
      var21 /= var25;
      var23 /= var25;
      var19 *= (double)var12;
      var21 *= (double)var12;
      var23 *= (double)var12;

      for(MovingObjectPosition var27 = null; var15 > 0.0D; var21 -= (double)var10) {
         Vec3 var28 = new Vec3(var13, var15, var17);
         Vec3 var29 = new Vec3(var13 + var19, var15 + var21, var17 + var23);
         var27 = field58.theWorld.rayTraceBlocks(var28, var29, false, true, false);
         if (var27 != null) {
            return null;
         }

         AxisAlignedBB var30 = (new AxisAlignedBB(var13 - (double)var11, var15 - (double)var11, var17 - (double)var11, var13 + (double)var11, var15 + (double)var11, var17 + (double)var11)).addCoord(var19, var21, var23).expand(1.0D, 1.0D, 1.0D);
         double var31 = (double)MathHelper.floor_double((var30.minX - 2.0D) / 16.0D);
         double var33 = (double)MathHelper.floor_double((var30.maxX + 2.0D) / 16.0D);
         double var35 = (double)MathHelper.floor_double((var30.minZ - 2.0D) / 16.0D);
         double var37 = (double)MathHelper.floor_double((var30.maxZ + 2.0D) / 16.0D);
         ArrayList var39 = Lists.newArrayList();

         for(int var40 = (int)var31; (double)var40 <= var33; ++var40) {
            for(int var41 = (int)var35; (double)var41 <= var37; ++var41) {
               field58.theWorld.getChunkFromChunkCoords(var40, var41).getEntitiesWithinAABBForEntity(field58.thePlayer, var30, var39, (Predicate)null);
            }
         }

         Iterator var44 = var39.iterator();

         while(var44.hasNext()) {
            Entity var45 = (Entity)var44.next();
            if (var45.canBeCollidedWith() && var45 != field58.thePlayer) {
               AxisAlignedBB var42 = var45.getEntityBoundingBox().expand((double)var11, (double)var11, (double)var11);
               MovingObjectPosition var43 = var42.calculateIntercept(var28, var29);
               if (var43 != null) {
                  return var45;
               }
            }
         }

         var13 += var19;
         var15 += var21;
         var17 += var23;
         if (field58.theWorld.getBlockState(new BlockPos(var13, var15, var17)).getBlock().getMaterial() == Material.water) {
            var19 *= 0.6D;
            var21 *= 0.6D;
            var23 *= 0.6D;
         } else {
            var19 *= (double)var9;
            var21 *= (double)var9;
            var23 *= (double)var9;
         }
      }

      return null;
   }

   public static float method0(int var0, int var1) {
      return 0.3F + 0.7F * (float)(var1 - var0) / (float)var1 * 2.0F;
   }

   public ShortbowTriggerbotModule() {
      super("Shortbow Triggerbot", Category.dungeon, SubCategory.qol, "Shoots a shortbow when targeting an entity");
      this.method0(new Setting[]{this.field8, this.field11, this.aa_, this.field7, this.field6, this.field9});
   }

   public static void method0(String var0, String var1, int var2) {
      Class514.method0(var1, var2, Notifications.Class1.field1);
   }

   public static DoubleSetting access$000(ShortbowTriggerbotModule var0) {
      return var0.aa_;
   }

   public static DoubleSetting access$100(ShortbowTriggerbotModule var0) {
      return var0.field11;
   }

   public static DoubleSetting access$300(ShortbowTriggerbotModule var0) {
      return var0.field6;
   }
}
