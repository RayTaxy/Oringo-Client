package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.List;
import map.Class24;
import map.Class328;
import map.Class350;
import map.Class361;
import map.Class447;
import map.Class94;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;

public class BlazeSwapperModule extends Module {
   public static final String[] field0 = new String[]{"Firedust", "Kindlebane", "Pyrochaos"};
   public final Class447 cP_ = new Class447();
   public static final String[] field2 = new String[]{"Mawdredge", "Twilight", "Deathripper"};

   public static Vec3 method0(AxisAlignedBB var0) {
      Vec3 var1 = Oringo.field9.thePlayer.getPositionEyes(1.0F);
      double var2 = Math.max(var0.minX, Math.min(var0.maxX, var1.xCoord));
      double var4 = Math.max(var0.minY, Math.min(var0.maxY, var1.yCoord));
      double var6 = Math.max(var0.minZ, Math.min(var0.maxZ, var1.zCoord));
      return new Vec3(var2, var4, var6);
   }

   public static boolean field0(BlazeSwapperModule.Class0 var0, ItemStack var1) {
      boolean var2 = false;
      String[] var3 = var0 != BlazeSwapperModule.Class0.field4 && var0 != BlazeSwapperModule.Class0.field2 ? field2 : field0;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String var6 = var3[var5];
         if (var1.getDisplayName().contains(var6)) {
            var2 = true;
            break;
         }
      }

      return var1.getItem() instanceof ItemSword && var2;
   }

   public BlazeSwapperModule() {
      super("Blaze Swapper", Category.skyblock, SubCategory.slayer, "Swaps blaze daggers");
   }

   public static void method0(Framebuffer var0) {
      var0.bindFramebuffer(false);
      AntiTentacleModule.method0(var0);
      GL11.glClear(1024);
      GL11.glEnable(2960);
   }

   public BlazeSwapperModule.Class0 u_(String var1) {
      BlazeSwapperModule.Class0[] var2 = BlazeSwapperModule.Class0.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         BlazeSwapperModule.Class0 var5 = var2[var4];
         if (var1.toLowerCase().contains(var5.toString().toLowerCase())) {
            return var5;
         }
      }

      return BlazeSwapperModule.Class0.field1;
   }

   @SubscribeEvent
   public void method0(AttackEntityEvent var1) {
      if (var1.entityPlayer == field58.thePlayer) {
         if (var1.target instanceof EntitySkeleton && ((EntitySkeleton)var1.target).getSkeletonType() == 1 || var1.target instanceof EntityBlaze || var1.target instanceof EntityPigZombie) {
            List var2 = field58.theWorld.getEntitiesWithinAABB(EntityArmorStand.class, var1.target.getEntityBoundingBox().expand(0.1D, 2.0D, 0.1D), BlazeSwapperModule::lambda$onAttack$0);
            if (!var2.isEmpty()) {
               EntityArmorStand var3 = (EntityArmorStand)var2.get(0);
               BlazeSwapperModule.Class0 var4 = this.u_(ChatFormatting.stripFormatting(var3.getDisplayName().getUnformattedText()));
               if (var4 != BlazeSwapperModule.Class0.field1) {
                  int var5 = Class24.field0(var4);
                  if (var5 != -1) {
                     Class328.method0(var5);
                     if (this.cP_.a_(500L) && field58.thePlayer.inventory.getStackInSlot(var5).getItem() instanceof ItemSword && !((ItemSword)field58.thePlayer.inventory.getStackInSlot(var5).getItem()).getToolMaterialName().equals(var4.field5)) {
                        Class361.method0((Class94)(new Class350(var5)));
                        this.cP_.o_();
                     }
                  }
               }
            }
         }

      }
   }

   public static boolean lambda$onAttack$0(EntityArmorStand var0) {
      String var1 = ChatFormatting.stripFormatting(var0.getDisplayName().getUnformattedText()).toLowerCase();
      return var1.contains("spirit") || var1.contains("ashen") || var1.contains("auric") || var1.contains("crystal");
   }

   public static enum Class0 {
      field0("IRON"),
      field1("NONE"),
      field2("GOLD"),
      field3("EMERALD");

      private static final BlazeSwapperModule.Class0[] field6 = new BlazeSwapperModule.Class0[]{field4, field2, field3, field0, field1};
      field4("STONE");

      public final String field5;

      private Class0(String var3) {
         this.field5 = var3;
      }
   }
}
