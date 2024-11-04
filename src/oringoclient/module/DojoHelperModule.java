package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import map.Class352;
import map.Class362;
import map.Class376;
import map.Class500;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.ReplyCommand;
import oringo.event.Class16;
import oringo.event.Class189;
import oringo.event.Class194;
import oringo.event.Class210;
import oringo.event.Class217;
import oringo.event.Class419;
import oringo.event.Class438;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class DojoHelperModule extends Module {
   public static final DoubleSetting field0 = (DoubleSetting)(new DoubleSetting("Bow charge", 0.6D, 0.1D, 1.0D, 0.1D, DojoHelperModule::lambda$static$2)).method2("Minimum bow charge before aimbot shoots");
   public static final DoubleSetting s_ = (DoubleSetting)(new DoubleSetting("Time", 0.3D, 0.1D, 5.0D, 0.05D, DojoHelperModule::lambda$static$1)).method2("Time at which aimbot shoots");
   public float t_;
   public static final HashMap field3 = new HashMap();
   public BlockPos field4;
   public double field5;
   public static final BooleanSetting field6 = new BooleanSetting("Tenacity float", true);
   public DojoHelperModule.Class0 field7;
   public Entity field8;
   public static final BooleanSetting field9 = (BooleanSetting)(new BooleanSetting("Hide bad zombies", true)).method2("Hides wrong zombies for the force challenge");
   public static final BooleanSetting field10 = (BooleanSetting)(new BooleanSetting("Auto sword swap", true)).method2("Swaps swords for the discipline challenge");
   public BlockPos u_;
   public static final BooleanSetting field12 = new BooleanSetting("Mastery aimbot", true);
   public static final EnumSetting field13 = (EnumSetting)(new EnumSetting("Color", DojoHelperModule::lambda$static$3, "Yellow", new String[]{"Red", "Yellow", "Green"})).method2("Aimbot targets wool of the specified color");
   public static final BooleanSetting field14 = new BooleanSetting("Control", true);
   public static final BooleanSetting field15 = new BooleanSetting("Stamina", true);
   public boolean db_;
   public EntitySkeleton field17;
   public static final BooleanSetting field18 = new BooleanSetting("Swiftness", true);

   @SubscribeEvent
   public void method0(Class217 var1) {
      if (this.u_ != null && this.field7 == DojoHelperModule.Class0.field4 && field18.method1() && !field58.thePlayer.isInLava()) {
         BlockPos var2 = (new BlockPos(field58.thePlayer)).down();
         if (field58.thePlayer.onGround) {
            Vec3 var3 = null;
            boolean var4 = false;
            BlockPos var9;
            if (!var2.equals(this.u_)) {
               EnumFacing[] var5 = EnumFacing.HORIZONTALS;
               int var6 = var5.length;

               for(int var7 = 0; var7 < var6; ++var7) {
                  EnumFacing var8 = var5[var7];
                  var9 = var2.offset(var8);
                  if (var9.equals(this.u_)) {
                     var3 = (new Vec3(var9)).addVector(0.5D, 0.0D, 0.5D);
                     break;
                  }
               }
            }

            if (var3 == null) {
               var4 = !this.u_.equals(var2);
               var3 = (new Vec3(this.u_)).addVector(0.5D, 0.0D, 0.5D);
            }

            this.t_ = ReplyCommand.method0(var3).method5();
            double var10 = Math.min(0.2873D, field58.thePlayer.getDistance(var3.xCoord, field58.thePlayer.posY, var3.zCoord));
            double var11 = Math.toRadians((double)this.t_);
            var1.method0(var10 * -Math.sin(var11), 0.0D, var10 * Math.cos(var11));
            if (var4 && var2.distanceSq(this.u_) <= 16.0D) {
               var9 = (new BlockPos(field58.thePlayer.getPositionVector().addVector(var1.w_(), 0.0D, var1.method2()))).down();
               if (!var9.equals(var2) || field58.theWorld.getBlockState(var9).getBlock() != Blocks.wool || var2.distanceSq(this.u_) < 16.0D) {
                  var1.method2(var1.w_() + 0.20000000298023224D * -Math.sin(var11)).method1(var1.method2() + 0.20000000298023224D * Math.cos(var11)).method0(0.41999998688697815D);
               }
            }

            this.field4 = this.u_;
         } else if (this.field4 != null && var2.getX() == this.field4.getX() && var2.getZ() == this.field4.getZ()) {
            var1.method2(0.0D).method1(0.0D);
         }

         field58.thePlayer.setVelocity(var1.w_(), var1.method6(), var1.method2());
      } else {
         this.u_ = null;
      }
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      BlockPos var2 = var1.cB_;
      IBlockState var3 = var1.field2;
      if (field58.thePlayer != null) {
         if ((var2.getY() == 99 || var2.getY() == 12) && field58.thePlayer.getDistanceSq(var2) <= 225.0D) {
            if (var3.getBlock() == Blocks.wool && var3.getValue(BlockColored.COLOR) == EnumDyeColor.LIME) {
               this.u_ = var2;
            }

         }
      }
   }

   public static boolean lambda$onMotionPre$Mastery$4(Entry var0) {
      return System.currentTimeMillis() - (Long)var0.getValue() > 5000L;
   }

   @SubscribeEvent
   public void method0(Class419 var1) {
      if (this.u_ != null && this.field7 == DojoHelperModule.Class0.field4 && field18.method1()) {
         if (!field58.thePlayer.onGround) {
            var1.method0(this.t_).method2(0.98F).method1(0.0F);
         }

      }
   }

   public static double lambda$onMotionPre$Mastery$6(EntityArmorStand var0) {
      return BacteNotificationsModule.h_(ChatFormatting.stripFormatting(var0.getName()));
   }

   @SubscribeEvent
   public void onMotion$control(Class210.Class1 var1) {
      if (this.field7 == DojoHelperModule.Class0.field3 && field14.method1()) {
         double var2;
         double var6;
         if (this.field17 == null || field58.theWorld.getEntityByID(this.field17.getEntityId()) == null || !this.field17.canEntityBeSeen(field58.thePlayer)) {
            var2 = 999.0D;
            this.field17 = null;
            Iterator var4 = field58.theWorld.getEntities(EntitySkeleton.class, DojoHelperModule::lambda$onMotion$control$7).iterator();

            while(var4.hasNext()) {
               EntitySkeleton var5 = (EntitySkeleton)var4.next();
               var6 = (double)field58.thePlayer.getDistanceToEntity(var5);
               if (var6 < var2) {
                  var2 = var6;
                  this.field17 = var5;
               }
            }
         }

         if (this.field17 != null) {
            var2 = (double)Class362.field7.field8 / 30.0D;
            double var10 = this.field17.posX + (this.field17.posX - this.field17.prevPosX) * var2;
            var6 = this.field17.posY + (this.field17.posY - this.field17.prevPosY) * var2 / 2.0D;
            double var8 = this.field17.posZ + (this.field17.posZ - this.field17.prevPosZ) * var2;
            var1.method0(Class352.method0(var10, var6 + (double)this.field17.getEyeHeight(), var8));
         }

      }
   }

   public static boolean lambda$left$11(ItemStack var0) {
      return var0.getItem() == Items.iron_sword;
   }

   public String lambda$new$0() {
      return DojoHelperModule.Class0.access$000(this.field7).replaceAll("Challenge: ", "");
   }

   public static boolean method2() {
      return Class362.field50.method44() && Class362.field50.cg_.method1();
   }

   public static boolean method3() {
      return AntiNukekubiModule.method0(0.0D, 0.0D);
   }

   public static boolean lambda$left$10(ItemStack var0) {
      return var0.getItem() == Items.diamond_sword;
   }

   public static boolean lambda$onMotion$control$7(EntitySkeleton var0) {
      return var0.getSkeletonType() == 1 && var0.getHealth() == 20.0F && !var0.isInvisible() && var0.canEntityBeSeen(field58.thePlayer);
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.u_ != null && this.field7 == DojoHelperModule.Class0.field4 && field18.method1()) {
         var1.field0 = true;
         var1.method0(false);
      }
   }

   public static boolean lambda$left$8(ItemStack var0) {
      return var0.getItem() == Items.wooden_sword;
   }

   public static boolean lambda$onMotionPre$Mastery$5(Pattern var0, EntityArmorStand var1) {
      return PacketLoggerModule.f_(var1.getName()) && !field3.containsKey(var1) && var0.matcher(var1.getName()).find();
   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      if ((field6.method1() && this.field7 == DojoHelperModule.Class0.field2 || field15.method1() && this.field7 == DojoHelperModule.Class0.field0) && this.db_ && var1.field0.getY() <= 105) {
         var1.method9();
      }
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook) {
         this.db_ = true;
      }

   }

   public static Boolean lambda$static$2() {
      return !field12.method1();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field58.theWorld.getSkylightSubtracted() == 11) {
         DojoHelperModule.Class0[] var2 = DojoHelperModule.Class0.values();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            DojoHelperModule.Class0 var5 = var2[var4];
            if (Class376.method0(DojoHelperModule.Class0.access$000(var5))) {
               if (this.field7 != var5) {
                  this.field7 = var5;
                  this.db_ = false;
               }
               break;
            }
         }

         if (field9.method1() && this.field7 == DojoHelperModule.Class0.field5) {
            Iterator var6 = field58.theWorld.loadedEntityList.iterator();

            while(true) {
               while(var6.hasNext()) {
                  Entity var7 = (Entity)var6.next();
                  if (var7 instanceof EntityZombie && ((EntityZombie)var7).getCurrentArmor(3) != null && ((EntityZombie)var7).getCurrentArmor(3).getItem() == Items.leather_helmet) {
                     var7.posY = -100.0D;
                     var7.lastTickPosY = -100.0D;
                  } else if (var7 instanceof EntityArmorStand && var7.getDisplayName().getFormattedText().startsWith("§c-")) {
                     var7.posY = -100.0D;
                     var7.lastTickPosY = -100.0D;
                  }
               }

               return;
            }
         }
      }
   }

   public static Boolean lambda$static$3() {
      return !field12.method1();
   }

   @SubscribeEvent
   public void method0(Class438 var1) {
      if (this.field7 == DojoHelperModule.Class0.field1 && field10.method1()) {
         DelaysModule.method0(var1.field0);
      }
   }

   public static boolean lambda$left$9(ItemStack var0) {
      return var0.getItem() == Items.golden_sword;
   }

   @SubscribeEvent
   public void onMotion$tenacity(Class217 var1) {
      if ((field6.method1() && this.field7 == DojoHelperModule.Class0.field2 || field15.method1() && this.field7 == DojoHelperModule.Class0.field0) && this.db_ && field58.thePlayer.posY <= 105.0D) {
         if (!field58.thePlayer.isInLava()) {
            if (this.field7 == DojoHelperModule.Class0.field2) {
               var1.method0(-1.0D, 0.0D, 0.0D);
            } else {
               var1.method0(0.0D, 0.0D, 1.0D);
            }

         }
      }
   }

   public DojoHelperModule() {
      super("Dojo Helper", Category.skyblock, SubCategory.skills, "Helps you do dojo challenges");
      this.field7 = DojoHelperModule.Class0.field6;
      this.method0((Setting[])(new Setting[]{field9, field10, field6, field15, field12, s_, field0, field13, field14, field18}));
      this.method0((Class500)(this::lambda$new$0));
   }

   @SubscribeEvent
   public void onMotionPost$Mastery(Class75 var1) {
      if (this.field7 == DojoHelperModule.Class0.field7 && field12.method1() && this.field8 != null) {
         if (field58.thePlayer.isUsingItem() && field58.thePlayer.getItemInUse().getItem() == Items.bow) {
            ItemBow var2 = (ItemBow)field58.thePlayer.getItemInUse().getItem();
            int var3 = var2.getMaxItemUseDuration(field58.thePlayer.getItemInUse()) - field58.thePlayer.getItemInUseCount();
            float var4 = (float)var3 / 20.0F;
            var4 = (var4 * var4 + var4 * 2.0F) / 3.0F;
            if ((double)var4 >= field0.method0() && this.field5 <= s_.method0()) {
               KeyBinding.setKeyBindState(field58.gameSettings.keyBindUseItem.getKeyCode(), false);
               field3.put(this.field8, System.currentTimeMillis());
               this.field8 = null;
            }
         }

      }
   }

   public static Boolean lambda$static$1() {
      return !field12.method1();
   }

   @SubscribeEvent
   public void onMotionPre$Mastery(Class210.Class1 var1) {
      this.field8 = null;
      if (this.field7 == DojoHelperModule.Class0.field7 && field12.method1()) {
         KeyBinding.setKeyBindState(field58.gameSettings.keyBindUseItem.getKeyCode(), true);
         field3.entrySet().removeIf(DojoHelperModule::lambda$onMotionPre$Mastery$4);
         Pattern var2 = Pattern.compile("\\d:\\d\\d\\d");
         List var3 = field58.theWorld.getEntities(EntityArmorStand.class, DojoHelperModule::lambda$onMotionPre$Mastery$5);
         var3.sort(Comparator.comparingDouble(DojoHelperModule::lambda$onMotionPre$Mastery$6));
         if (!var3.isEmpty()) {
            this.field8 = (Entity)var3.get(0);
            this.field5 = BacteNotificationsModule.h_(ChatFormatting.stripFormatting(this.field8.getName()));
            var1.method0(ReplyCommand.method0(this.field8.getPositionVector().addVector(0.0D, 4.0D, 0.0D)));
         }

      } else {
         field3.clear();
      }
   }

   public static enum Class0 {
      field0("Challenge: Stamina"),
      field1("Challenge: Discipline");

      private static final DojoHelperModule.Class0[] field8 = new DojoHelperModule.Class0[]{field7, field2, field3, field5, field1, field0, field4, field6};
      field2("Challenge: Tenacity"),
      field3("Challenge: Control"),
      field4("Challenge: Swiftness"),
      field5("Challenge: Force"),
      field6(""),
      field7("Challenge: Mastery");

      private final String field9;

      private Class0(String var3) {
         this.field9 = var3;
      }

      static String access$000(DojoHelperModule.Class0 var0) {
         return var0.field9;
      }
   }
}
