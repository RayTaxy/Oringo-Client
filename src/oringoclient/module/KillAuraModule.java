package oringo.module;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import map.Class14;
import map.Class163;
import map.Class175;
import map.Class25;
import map.Class29;
import map.Class32;
import map.Class34;
import map.Class361;
import map.Class395;
import map.Class417;
import map.Class427;
import map.Class430;
import map.Class447;
import map.Class490;
import map.Class500;
import map.Class506;
import map.Class6;
import map.Class94;
import map.Class95;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.LogsCommand;
import oringo.command.MessageCommand;
import oringo.command.MoveItemCommand;
import oringo.command.ReplyCommand;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class419;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class KillAuraModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Always spade swap", false, KillAuraModule::lambda$static$0, "Always spade swap on the Pit");
   public static final BooleanSetting field1 = new BooleanSetting("Disable on join", true, "Disable when world is changed");
   public static final DoubleSetting field2 = (DoubleSetting)(new KillAuraModule$1("Range", 4.2D, 2.0D, 6.0D, 0.1D)).method2("The range at aura will hit");
   public static final BooleanSetting field3 = new BooleanSetting("Click only", false, "Only attack when attack button is held");
   public static final DoubleSetting field4 = new KillAuraModule$5("Max CPS", 13.0D, 1.0D, 20.0D, 1.0D);
   public double field5 = 10.0D;
   public static final DoubleSetting field6 = (DoubleSetting)(new DoubleSetting("Fov", 360.0D, 30.0D, 360.0D, 1.0D)).method2("Aura will only attack entities wihin the fov");
   public static final BooleanSetting field7 = new BooleanSetting("No containers", true, "Prevents attacking in inventories");
   public static final DoubleSetting field8 = (DoubleSetting)(new KillAuraModule$4("Min rotation", 30.0D, 5.0D, 180.0D, 0.1D)).method2("Minimum degrees that aura can rotate per tick");
   public static final EnumSetting field9 = (EnumSetting)(new EnumSetting("Target point", "Closest", new String[]{"Closest", "Middle"})).method2("The point of opponent's hitbox killaura should look at");
   public static final BooleanSetting field10 = new BooleanSetting("Through walls", true, "Attack entities through walls");
   public static final DoubleSetting field11 = new DoubleSetting("Smoothing", 12.0D, 1.0D, 20.0D, 0.1D, KillAuraModule::lambda$static$1);
   public static final BooleanSetting field12 = new BooleanSetting("Movement fix", false);
   public static final DoubleSetting field13 = (DoubleSetting)(new KillAuraModule$3("Max rotation", 60.0D, 5.0D, 180.0D, 0.1D)).method2("Maximum degrees that aura can rotate per tick");
   public static final EnumSetting field14 = (EnumSetting)(new EnumSetting("Mode", "Single", new String[]{"Single", "Switch"})).method2("Should aura find a new target after a hit");
   public static final BooleanSetting field15 = new BooleanSetting("Teams", true, "Check if entity is on your team");
   public static final EnumSetting field16 = (EnumSetting)(new EnumSetting("Sorting", "Distance", new String[]{"Distance", "Health", "Hurt", "Hp reverse"})).method2("Which entities should be prioritized");
   public static final DoubleSetting field17 = (DoubleSetting)(new DoubleSetting("Switch delay", 150.0D, 0.0D, 250.0D, 1.0D, KillAuraModule::lambda$static$2)).method2("Delay between switching targets");
   public int field18;
   public static final BooleanSetting field19 = new BooleanSetting("Swing on rotation", false, "Swing when entity is in the rotation distance");
   public static final EnumSetting field20 = (EnumSetting)(new EnumSetting("Rotation mode", "Simple", new String[]{"Simple", "Smooth", "Derp", "None"})).method2("How aura should look at the target");
   public static final BooleanSetting field21 = new BooleanSetting("Mobs", true, "Target non-player entities");
   public static final BooleanSetting field22 = new BooleanSetting("Invisibles", false, "Attack invisible entities");
   public static final DoubleSetting field23 = new KillAuraModule$6("Min CPS", 11.0D, 1.0D, 20.0D, 1.0D);
   public static EntityLivingBase field24;
   public final Class447 field25 = new Class447();
   public static final EnumSetting field26 = (EnumSetting)(new EnumSetting("Autoblock", "None", new String[]{"Vanilla", "Hypixel", "Fake", "None"})).method2("How aura should block");
   public final Class447 field27 = new Class447();
   public static final BooleanSetting field28 = new BooleanSetting("Spade swap", false, "Swap to spade on the Pit");
   public static final BooleanSetting field29 = new BooleanSetting("Players", false, "Target player entities");
   public static float t_;
   public static final DoubleSetting field31 = (DoubleSetting)(new KillAuraModule$2("Rotation Range", 6.0D, 2.0D, 12.0D, 0.1D)).method2("The range at aura will rotate");
   public static final BooleanSetting field32 = new BooleanSetting("Only swords", false, "Only attack while holding a sword");
   public static final BooleanSetting field33 = new BooleanSetting("Draw range", false, "Graphical representation of distance and fov");

   @SubscribeEvent
   public void method0(Class419 var1) {
      if (field24 != null && field12.method1()) {
         var1.method0(MessageCommand.method0((Entity)field24).method5());
      }

   }

   public Vec3 method0(Entity var1, float var2) {
      return new Vec3(var1.posX + (double)Class430.method0(var2, -var2), var1.posY + (double)var1.getEyeHeight() / 2.0D + (double)Class430.method0(var2, -var2), var1.posZ + (double)Class430.method0(var2, -var2));
   }

   public Vec3 method0(Entity var1) {
      return this.method0(var1, 0.0F);
   }

   public boolean method0(EntityLivingBase var1) {
      for(int var2 = 1; var2 < 5; ++var2) {
         if (var1.getEquipmentInSlot(var2) != null && var1.getEquipmentInSlot(var2).getItem() instanceof ItemArmor && ((ItemArmor)var1.getEquipmentInSlot(var2).getItem()).getArmorMaterial() == ArmorMaterial.DIAMOND) {
            return true;
         }
      }

      return false;
   }

   public boolean a_(EntityLivingBase var1) {
      if (this.method1(var1) > (field24 != null && field24 != var1 ? field2.method0() : Math.max(field31.method0(), field2.method0()))) {
         return false;
      } else if (LividFinderModule.field2.method1() && LividFinderModule.field0 != null) {
         return LividFinderModule.field0.equals(var1);
      } else if (var1 != field58.thePlayer && !Class490.method0((Entity)var1) && Class506.method0((Entity)var1) && (field22.method1() || !var1.isInvisible()) && !(var1 instanceof EntityArmorStand) && (field58.thePlayer.canEntityBeSeen(var1) || field10.method1()) && var1.getHealth() > 0.0F && var1.getDistanceToEntity(field58.thePlayer) <= 15.0F && Class417.method0(MessageCommand.method0((Entity)var1), (float)field6.method0())) {
         if (LogsCommand.method2().method44()) {
            boolean var2 = MoveItemCommand.method0((Entity)var1);
            if (NamesOnlyModule.bs_.method0(1) || var2) {
               return NamesOnlyModule.bs_.method0(1) && var2;
            }
         }

         if ((var1 instanceof EntityMob || var1 instanceof EntityVillager || var1 instanceof EntitySnowman || var1 instanceof EntityAmbientCreature || var1 instanceof EntityWaterMob || var1 instanceof EntityAnimal || var1 instanceof EntitySlime) && !field21.method1()) {
            return false;
         } else {
            return !(var1 instanceof EntityPlayer) || (!Class6.method0(var1) || !field15.method1()) && field29.method1();
         }
      } else {
         return false;
      }
   }

   public boolean method0(Class34 var1, Entity var2) {
      Vec3 var3 = field58.thePlayer.getPositionEyes(1.0F);
      Vec3 var4 = AutoFrozilleModule.method0(var1.method5(), var1.method2());
      Vec3 var5 = var3.addVector(var4.xCoord * field2.method0() + 0.5D, var4.yCoord * field2.method0() + 0.5D, var4.zCoord * field2.method0() + 0.5D);
      float var6 = var2.getCollisionBorderSize();
      AxisAlignedBB var7 = var2.getEntityBoundingBox().expand((double)var6, (double)var6, (double)var6);
      return var7.isVecInside(var3) || var7.calculateIntercept(var3, var5) != null;
   }

   public static Boolean lambda$static$2() {
      return !field14.method0(1);
   }

   public void method2(EntityLivingBase var1) {
      if (this.method1(var1) >= field2.method0()) {
         if (field19.method1()) {
            field58.thePlayer.swingItem();
         }

      } else {
         boolean var2 = field20.method0(3) || field20.method0(2) || this.method0(Class175.method0(), var1);
         int var3 = -1;
         if (field28.method1() && (field0.method1() || this.method0(var1)) && RenderChunkBoundsModule.method5()) {
            var3 = Class95.method0(KillAuraModule::lambda$doAttack$5);
            if (var3 != -1) {
               field58.playerController.windowClick(0, var3, field58.thePlayer.inventory.currentItem, 2, field58.thePlayer);
            }
         }

         field58.thePlayer.swingItem();
         if (var2) {
            field58.playerController.attackEntity(field58.thePlayer, var1);
            if (var3 != -1) {
               field58.playerController.windowClick(0, var3, field58.thePlayer.inventory.currentItem, 2, field58.thePlayer);
            }

            if (this.field25.a_((long)field17.method0())) {
               ++this.field18;
               this.field25.o_();
            }

         }
      }
   }

   public static boolean lambda$doAttack$5(ItemStack var0) {
      return var0.getDisplayName().contains("Combat Spade");
   }

   public KillAuraModule() {
      super("Kill Aura", Category.combat, SubCategory.combat, "Attacks enemies\nUse the Names Only module to filter mobs by their name");
      this.method0((Setting[])(new Setting[]{field14, field17, field2, field31, field23, field4, field16, field9, field20, field11, field13, field8, field6, field26, field29, field21, field22, field15, field19, field12, field3, field10, field7, field1, field32, field33, field28, field0}));
      EnumSetting var10001 = field14;
      this.method0((Class500)(var10001::method4));
   }

   public boolean method6() {
      if (this.field27.a_(Math.round(1000.0D / this.field5))) {
         this.field27.o_();
         this.field5 = Class427.method1(field4.method0(), field23.method0());
         return true;
      } else {
         return false;
      }
   }

   public static boolean lambda$onMovePre$4() {
      return !field58.playerController.getIsHittingBlock();
   }

   public static Integer lambda$getTargets$7(EntityLivingBase var0) {
      return var0.hurtTime;
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (field33.method1()) {
         Class25.method0(field2.method0(), (float)field6.method0(), var1.partialTicks);
      }

   }

   public void b_() {
      field24 = null;
   }

   public static Boolean lambda$static$1() {
      return !field20.method0(1);
   }

   public static double lambda$getTargets$8(EntityLivingBase var0) {
      return (double)(-var0.getHealth());
   }

   @SubscribeEvent(
      priority = EventPriority.LOW
   )
   public void a_(Class210.Class1 var1) {
      field24 = this.method13();
      if (field24 != null) {
         if (!(field58.currentScreen instanceof GuiContainer) || !field7.method1()) {
            if (!Class32.method0()) {
               if (!field32.method1() || field58.thePlayer.getHeldItem() != null && field58.thePlayer.getHeldItem().getItem() instanceof ItemSword) {
                  if (!field3.method1() || field58.gameSettings.keyBindAttack.isKeyDown()) {
                     Class34 var2 = ReplyCommand.method0(field9.method0(1) ? this.method0((Entity)field24) : BlazeSwapperModule.method0(field24.getEntityBoundingBox()));
                     String var3 = field20.method4();
                     byte var4 = -1;
                     switch(var3.hashCode()) {
                     case -1818419758:
                        if (var3.equals("Simple")) {
                           var4 = 3;
                        }
                        break;
                     case -1814666802:
                        if (var3.equals("Smooth")) {
                           var4 = 2;
                        }
                        break;
                     case 2126495:
                        if (var3.equals("Derp")) {
                           var4 = 1;
                        }
                        break;
                     case 2433880:
                        if (var3.equals("None")) {
                           var4 = 0;
                        }
                     }

                     switch(var4) {
                     case 0:
                     default:
                        break;
                     case 1:
                        var1.t_ = MathHelper.wrapAngleTo180_float(t_ += 30.0F);
                        var1.bF_ = 90.0F;
                        break;
                     case 2:
                        var1.method0(Class14.method0(Class175.method0(), var2, (float)field11.method0()).method4());
                        break;
                     case 3:
                        var1.method0(Class29.method0(Class175.method0(), var2, (float)(field8.method0() + Math.abs(field13.method0() - field8.method0()) * (double)(new Random()).nextFloat())).method4());
                     }

                     var1.method0(Class163.method0(var1.bF_, 90.0F, -90.0F));
                     if (this.method6()) {
                        EntityLivingBase var5 = field24;
                        Class361.method0((Class94)(new Class395(this::lambda$onMovePre$3, true, KillAuraModule::lambda$onMovePre$4)));
                     }

                  }
               }
            }
         }
      }
   }

   public void method9() {
      if (field58.thePlayer.getHeldItem() != null && !field58.thePlayer.isUsingItem() && field58.thePlayer.getHeldItem().getItemUseAction() == EnumAction.BLOCK) {
         field58.playerController.sendUseItem(field58.thePlayer, field58.theWorld, field58.thePlayer.getHeldItem());
      }
   }

   public boolean lambda$getTarget$9(EntityLivingBase var1) {
      return this.method1(var1) < field2.method0();
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      if (field1.method1()) {
         this.method1(false);
      }

   }

   public List method10() {
      List var1 = field58.theWorld.getEntities(EntityLivingBase.class, this::a_);
      var1.sort(Comparator.comparingDouble(KillAuraModule::lambda$getTargets$6));
      String var2 = field16.method4();
      byte var3 = -1;
      switch(var2.hashCode()) {
      case -2137395588:
         if (var2.equals("Health")) {
            var3 = 0;
         }
         break;
      case -800305174:
         if (var2.equals("Hp reverse")) {
            var3 = 2;
         }
         break;
      case 2261039:
         if (var2.equals("Hurt")) {
            var3 = 1;
         }
      }

      switch(var3) {
      case 0:
         var1.sort(Comparator.comparingDouble(EntityLivingBase::func_110143_aJ));
         break;
      case 1:
         var1.sort(Comparator.comparing(KillAuraModule::lambda$getTargets$7));
         break;
      case 2:
         var1.sort(Comparator.comparingDouble(KillAuraModule::lambda$getTargets$8));
      }

      return var1;
   }

   public static Boolean lambda$static$0() {
      return !field28.method1();
   }

   public static void method12() {
      GlStateManager.enablePolygonOffset();
      GlStateManager.doPolygonOffset(1.0F, -1000000.0F);
   }

   public void lambda$onMovePre$3(EntityLivingBase var1) {
      this.method2(var1);
      if (field26.method0("Hypixel", "Vanilla")) {
         this.method9();
      }

   }

   public EntityLivingBase method13() {
      List var1 = this.method10();
      if (!var1.isEmpty()) {
         if ((long)this.field18 >= var1.stream().filter(this::lambda$getTarget$9).count()) {
            this.field18 = 0;
         }

         String var2 = field14.method4();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -1818398616:
            if (var2.equals("Single")) {
               var3 = 1;
            }
            break;
         case -1805606060:
            if (var2.equals("Switch")) {
               var3 = 0;
            }
         }

         switch(var3) {
         case 0:
            return (EntityLivingBase)var1.get(this.field18);
         case 1:
            return (EntityLivingBase)var1.get(0);
         }
      }

      return null;
   }

   public static double lambda$getTargets$6(EntityLivingBase var0) {
      return (double)var0.getDistanceToEntity(field58.thePlayer);
   }

   public double method1(Entity var1) {
      AxisAlignedBB var2 = var1.getEntityBoundingBox();
      if (var2 == null) {
         return 100.0D;
      } else {
         Class34 var3 = ReplyCommand.method0(field9.method0(1) ? this.method0(var1) : BlazeSwapperModule.method0(var1.getEntityBoundingBox()));
         Vec3 var4 = field58.thePlayer.getPositionEyes(1.0F);
         Vec3 var5 = AutoFrozilleModule.method0(var3.method5(), var3.method2());
         Vec3 var6 = var4.addVector(var5.xCoord * 13.0D, var5.yCoord * 13.0D, var5.zCoord * 13.0D);
         MovingObjectPosition var7 = var2.calculateIntercept(var4, var6);
         return var7 == null ? 100.0D : var7.hitVec.distanceTo(field58.thePlayer.getPositionEyes(1.0F));
      }
   }
}
