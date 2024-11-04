package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class142;
import map.Class291;
import map.Class350;
import map.Class361;
import map.Class376;
import map.Class447;
import map.Class496;
import map.Class501;
import map.Class510;
import map.Class518;
import map.Class94;
import map.Class98;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.S45PacketTitle.Type;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class344;
import oringo.event.Class438;
import oringo.event.Class468;
import oringo.event.Class75;
import oringo.mixin.EntityLivingBaseAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class VampireHelperModule extends Module {
   public final Class447 field0 = new Class447();
   public final BooleanSetting cG_ = (BooleanSetting)(new BooleanSetting("Hide Impel", false, this::lambda$new$1)).method2("Hides impel messages");
   public final Class447 field2 = new Class447();
   public VampireHelperModule.Class0 field3;
   public final Class447 field4 = new Class447();
   public final DoubleSetting field5 = new DoubleSetting("Melon health", 50.0D, 0.0D, 100.0D, 1.0D, this::lambda$new$5);
   public static final String field6 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzdmN2E3YmM4YWM4NmYyM2NhN2JmOThhZmViNzY5NjAyMjdlMTgzMmZlMjA5YTMwMjZmNmNlYjhiZGU3NGY1NCJ9fX0=";
   public Entity field7 = null;
   public final EnumSetting field8 = new EnumSetting("Auto Tuba", "Vampire Island", new String[]{"Vampire Island", "Boss", "Always", "Disabled"});
   public boolean field9 = false;
   public int field10 = -1;
   public final BooleanSetting field11 = new BooleanSetting("Auto Steak Stake", true);
   public static final Pattern field12 = Pattern.compile("Impel: (.+?) 1\\.9s");
   public boolean field13;
   public final BooleanSetting field14 = new BooleanSetting("Auto Holy Ice", true);
   public final BooleanSetting field15 = (BooleanSetting)(new BooleanSetting("Rotation Impel", true, this::lambda$new$0)).method2("Toggle for click up/down impel");
   public int field16 = 0;
   public final BooleanSetting field17 = new BooleanSetting("Auto Break Totem", true);
   public static final Pattern field18 = Pattern.compile("TWINCLAWS (\\d(?>\\.\\d)?)s");
   public final DoubleSetting field19 = new DoubleSetting("Auto Ice Time", 0.5D, 0.1D, 1.5D, 0.1D);
   public final Class447 field20 = new Class447();
   public final BooleanSetting field21 = new BooleanSetting("Auto Healing Melon", true);
   public int Y_;
   public final BooleanSetting field23 = new BooleanSetting("Auto Impel", true);

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.field23.method1() && Class496.field21 && var1.field0 instanceof S45PacketTitle) {
         S45PacketTitle var2 = (S45PacketTitle)var1.field0;
         if (var2.getType() == Type.SUBTITLE && var2.getMessage() != null) {
            String var3 = ChatFormatting.stripFormatting(var2.getMessage().getUnformattedText());
            Matcher var4 = field12.matcher(var3);
            if (var4.find() && this.field20.a_(300L)) {
               String var5 = var4.group(1);
               VampireHelperModule.Class0 var6 = VampireHelperModule.Class0.method0(var5);
               if (var6 == null) {
                  method2("Unknown impel " + var5);
                  return;
               }

               this.field3 = var6;
               this.field13 = false;
               this.Y_ = 0;
               if (this.cG_.method1() && (this.field15.method1() || var6 != VampireHelperModule.Class0.field1 && var6 != VampireHelperModule.Class0.field0)) {
                  var1.method9();
               }

               this.field20.o_();
            }

         }
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.field10 != -1) {
         Class361.method0((Class94)(new Class350(this.field10)));
         this.field10 = -1;
      } else if (Class496.field21 && field58.currentScreen == null && this.method5()) {
         int var2 = TrailModule.method0(VampireHelperModule::lambda$onMotionPostTuba$7);
         short var3 = 29500;
         if (var2 == -1) {
            var2 = TrailModule.method0(VampireHelperModule::lambda$onMotionPostTuba$8);
            var3 = 20500;
         }

         if (var2 != -1 && this.field4.method0((long)var3, true)) {
            Class361.method0((Class94)(new Class350(var2)));
         }
      }
   }

   public static boolean lambda$onMotionPre$6(ItemStack var0) {
      String var1 = BlockHitModule.method0(var0);
      if (var1 == null) {
         return false;
      } else {
         return "HEALING_MELON".equals(var1) || "JUICY_HEALING_MELON".equals(var1) || "LUSCIOUS_HEALING_MELON".equals(var1);
      }
   }

   public static boolean lambda$onLeftClick$4(ItemStack var0) {
      return "STEAK_STAKE".equals(BlockHitModule.method0(var0));
   }

   @SubscribeEvent
   public void method0(Class438 var1) {
      if (this.field11.method1() && Class496.field21) {
         if (field58.objectMouseOver != null && field58.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
            Entity var2 = field58.objectMouseOver.entityHit;
            if (var2 instanceof EntityOtherPlayerMP && "Bloodfiend ".equals(var2.getName())) {
               EntityOtherPlayerMP var3 = (EntityOtherPlayerMP)var2;
               float var4 = var3.getHealth() / Class518.method0(var3);
               if (var4 > 0.2F) {
                  return;
               }

               int var5 = TrailModule.method0(VampireHelperModule::lambda$onLeftClick$4);
               if (var5 == -1) {
                  return;
               }

               field58.thePlayer.inventory.currentItem = var5;
            }

         }
      }
   }

   @SubscribeEvent
   public void method0(Class344 var1) {
      if (Class496.field21 && Class376.method0("Slay the boss!") && Class376.method0("Riftstalker Bloodfiend")) {
         if (var1.aM_ instanceof EntityBat || var1.aM_ instanceof EntityFallingBlock) {
            var1.method9();
         }

      }
   }

   @SubscribeEvent
   public void a_(Class210.Class1 var1) {
      if (Class496.field21 && this.field14.method1() && Class376.method0("Slay the boss!") && Class376.method0("Riftstalker Bloodfiend")) {
         List var2 = field58.theWorld.getEntities(EntityArmorStand.class, VampireHelperModule::lambda$onMovePre$2);
         if (var2.size() == 1) {
            EntityArmorStand var3 = (EntityArmorStand)var2.get(0);
            Iterator var4 = field58.theWorld.loadedEntityList.iterator();

            while(var4.hasNext()) {
               Entity var5 = (Entity)var4.next();
               if (var5 instanceof EntityArmorStand && var5.getDistanceSqToEntity(field58.thePlayer) <= 225.0D && var3.getDistanceSqToEntity(var5) <= 9.0D) {
                  EntityArmorStand var6 = (EntityArmorStand)var5;
                  Matcher var7 = field18.matcher(ChatFormatting.stripFormatting(var6.getName()));
                  if (var7.find()) {
                     double var8 = Double.parseDouble(var7.group(1));
                     if (var8 <= this.field19.method0()) {
                        if (this.field16 != var5.getEntityId()) {
                           this.field10 = TrailModule.method0(VampireHelperModule::lambda$onMovePre$3);
                        }

                        this.field16 = var5.getEntityId();
                        return;
                     }
                  }
               }
            }

            this.field16 = 0;
         }
      } else {
         this.field16 = 0;
      }
   }

   @SubscribeEvent
   public void b_(Class210.Class1 var1) {
      if (this.field21.method1() && Class496.field21 && this.field2.a_(500L)) {
         float var2 = field58.thePlayer.getHealth() / field58.thePlayer.getMaxHealth();
         if (var2 <= this.field5.method1() / 100.0F) {
            int var3 = TrailModule.method0(VampireHelperModule::lambda$onMotionPre$6);
            if (var3 != -1) {
               this.field10 = var3;
               this.field2.o_();
            }
         }
      }
   }

   public VampireHelperModule() {
      super("Vampire Helper", Category.skyblock, SubCategory.slayer, "Useful features for the vampire slayer");
      this.method0((Setting[])(new Setting[]{this.field23, this.field15, this.cG_, this.field8, this.field17, this.field14, this.field19, this.field11, this.field21, this.field5}));
   }

   public static ArrayList method0(EnumDyeColor var0, List var1) {
      if (!GemstoneESPOldModule.field11.a_(1000L) && GemstoneESPOldModule.field5.containsKey(var0)) {
         return (ArrayList)GemstoneESPOldModule.field5.get(var0);
      } else {
         BlockPos var2 = ThystHiderModule.method3();
         ArrayList var3 = new ArrayList(1000);
         Iterator var4 = var1.iterator();

         while(var4.hasNext()) {
            Vec3 var5 = (Vec3)var4.next();
            if (var5.squareDistanceTo(new Vec3(var2)) <= GemstoneESPOldModule.field4.method0() * GemstoneESPOldModule.field4.method0()) {
               var3.add(var5);
            }
         }

         GemstoneESPOldModule.field5.put(var0, var3);
         return var3;
      }
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.field23.method1() && this.field15.method1() && Class496.field21 && this.field3 != null && !this.field13) {
         ++this.Y_;
         if (this.field3 == VampireHelperModule.Class0.field0) {
            var1.bF_ = -90.0F;
            if (this.Y_ > 2 && Class361.method0((Class94)(new Class501()))) {
               this.field13 = true;
            }
         } else if (this.field3 == VampireHelperModule.Class0.field1) {
            var1.bF_ = 90.0F;
            if (this.Y_ > 2 && Class361.method0((Class94)(new Class510(XRayModule.method0(new BlockPos(field58.thePlayer)), EnumFacing.UP)))) {
               this.field13 = true;
            }
         }

      }
   }

   public boolean method5() {
      String var1 = this.field8.method4();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -14113113:
         if (var1.equals("Vampire Island")) {
            var2 = 0;
         }
         break;
      case 2076557:
         if (var1.equals("Boss")) {
            var2 = 1;
         }
         break;
      case 1964277295:
         if (var1.equals("Always")) {
            var2 = 2;
         }
      }

      switch(var2) {
      case 0:
         return Class376.method0("Stillgore");
      case 1:
         return Class376.method0("Slay the boss!");
      case 2:
         return true;
      default:
         return false;
      }
   }

   public static boolean lambda$onMotionPostTuba$7(ItemStack var0) {
      return "WEIRDER_TUBA".equals(BlockHitModule.method0(var0));
   }

   public static boolean lambda$onMovePre$3(ItemStack var0) {
      return "HOLY_ICE".equals(BlockHitModule.method0(var0));
   }

   @SubscribeEvent
   public void method1(Class75 var1) {
      if (this.field17.method1() && Class496.field21 && this.field0.method0(98L, true)) {
         if (!field58.playerController.getIsHittingBlock()) {
            if (this.field7 != null && field58.thePlayer.getDistanceSqToEntity(this.field7) <= 16.0D && field58.theWorld.getEntityByID(this.field7.getEntityId()) != null) {
               this.field9 = !this.field9;
               if (this.field9) {
                  Class361.method0((Class94)(new Class291(this.field7)));
               } else {
                  Class361.method0((Class94)(new Class142(this.field7)));
               }
            } else {
               this.field7 = null;
               this.method9();
            }

         }
      }
   }

   public Boolean lambda$new$5() {
      return !this.field21.method1();
   }

   @SubscribeEvent
   public void method0(Class468 var1) {
      if (this.field23.method1() && Class496.field21 && this.field3 != null && !this.field13) {
         if (this.field3 == VampireHelperModule.Class0.field2) {
            if (field58.thePlayer.onGround) {
               ((EntityLivingBaseAccessor)field58.thePlayer).setJumpTicks(0);
               var1.method1(true);
               this.field13 = true;
            }
         } else if (this.field3 == VampireHelperModule.Class0.field3) {
            var1.method0(true);
            this.field13 = true;
         }

      }
   }

   public Boolean lambda$new$1() {
      return !this.field23.method1();
   }

   public static boolean lambda$onMotionPostTuba$8(ItemStack var0) {
      return "WEIRD_TUBA".equals(BlockHitModule.method0(var0));
   }

   public static boolean lambda$onMovePre$2(EntityArmorStand var0) {
      return ChatFormatting.stripFormatting(var0.getDisplayName().getUnformattedText()).equals("Spawned by: " + field58.thePlayer.getName());
   }

   public Boolean lambda$new$0() {
      return !this.field23.method1();
   }

   public void method9() {
      Iterator var1 = field58.theWorld.loadedEntityList.iterator();

      while(var1.hasNext()) {
         Entity var2 = (Entity)var1.next();
         if (var2 instanceof EntityArmorStand && field58.thePlayer.getDistanceSqToEntity(var2) <= 16.0D) {
            EntityArmorStand var3 = (EntityArmorStand)var2;
            ItemStack var4 = var3.getEquipmentInSlot(4);
            if (var4 != null && var4.getItem() instanceof ItemSkull && "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzdmN2E3YmM4YWM4NmYyM2NhN2JmOThhZmViNzY5NjAyMjdlMTgzMmZlMjA5YTMwMjZmNmNlYjhiZGU3NGY1NCJ9fX0=".equals(Class98.method0(var4))) {
               this.field7 = var3;
            }
         }
      }

   }

   public static enum Class0 {
      private final String field4;
      field0("CLICK UP"),
      field1("CLICK DOWN"),
      field2("JUMP"),
      field3("SNEAK");

      private static final VampireHelperModule.Class0[] field5 = new VampireHelperModule.Class0[]{field3, field2, field0, field1};

      private Class0(String var3) {
         this.field4 = var3;
      }

      public static VampireHelperModule.Class0 method0(String var0) {
         VampireHelperModule.Class0[] var1 = values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            VampireHelperModule.Class0 var4 = var1[var3];
            if (var4.field4.equals(var0)) {
               return var4;
            }
         }

         return null;
      }
   }
}
