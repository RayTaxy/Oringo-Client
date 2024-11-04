package oringo.module;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import map.Class361;
import map.Class395;
import map.Class447;
import map.Class94;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.event.Class75;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class AutoAlignModule extends Module {
   public static final List field0;
   public static final DoubleSetting W_ = new DoubleSetting("Scan delay", 1500.0D, 250.0D, 2500.0D, 50.0D);
   public static final Class447 field2;
   public static final DoubleSetting field3 = new DoubleSetting("Distance", 6.0D, 1.0D, 6.0D, 0.1D, AutoAlignModule::lambda$static$0);
   public static final ArrayList field4;
   public static final DoubleSetting field5 = new DoubleSetting("Delay", 150.0D, 0.0D, 500.0D, 10.0D);
   public static boolean field6;
   public static final List field7;
   public static final Class447 field8;
   public static final EnumSetting field9 = (EnumSetting)(new EnumSetting("Mode", "Legit", new String[]{"Aura", "Legit"})).method2("Legit mode requires you to look at the item frame");

   public void method4() {
      field4.clear();
   }

   public static boolean lambda$onUpdate$7() {
      return !field58.thePlayer.isUsingItem() && !field58.playerController.getIsHittingBlock();
   }

   static {
      ArrayList var0 = new ArrayList();
      BlockPos.getAllInBox(new BlockPos(-2, 125, 79), new BlockPos(-2, 121, 75)).forEach(var0::add);
      field0 = var0;
      field4 = new ArrayList();
      field8 = new Class447();
      field2 = new Class447();
      field7 = new ArrayList();
   }

   public static void method2() {
      GlStateManager.enableDepth();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static boolean method0(ItemStack var0) {
      return "RABBIT_HAT".equals(BlockHitModule.method0(var0));
   }

   public static boolean lambda$onUpdate$3(EntityItemFrame var0) {
      return var0.getDisplayedItem().getMetadata() == 5;
   }

   public static boolean lambda$onUpdate$4(EntityItemFrame var0) {
      return var0.getDisplayedItem().getMetadata() == 14;
   }

   public static void access$200(Packet var0) {
      method3(var0);
   }

   public static boolean lambda$onUpdate$1(EntityItemFrame var0) {
      return field0.contains(var0.getPosition()) && var0.getDisplayedItem() != null && (var0.getDisplayedItem().getItem() == Item.getItemFromBlock(Blocks.wool) || var0.getDisplayedItem().getItem() == Items.arrow);
   }

   public static void access$100(Packet var0) {
      method3(var0);
   }

   public static Minecraft access$300() {
      return field58;
   }

   @SubscribeEvent
   public void method0(Load var1) {
      field7.clear();
      field4.clear();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field58.thePlayer.getDistanceSq(new BlockPos(-2, 125, 79)) <= 625.0D) {
         List var2;
         if (field8.method0(W_.method0(), true) && field2.a_((long)W_.method3()) || field4.isEmpty()) {
            field4.clear();
            field7.clear();
            var2 = field58.theWorld.getEntities(EntityItemFrame.class, AutoAlignModule::lambda$onUpdate$1);
            List var3 = (List)var2.stream().filter(AutoAlignModule::lambda$onUpdate$2).collect(Collectors.toList());
            field6 = var3.stream().filter(AutoAlignModule::lambda$onUpdate$3).count() == 2L && var3.stream().filter(AutoAlignModule::lambda$onUpdate$4).count() == 1L;
            var2.sort(Comparator.comparingDouble(AutoAlignModule::lambda$onUpdate$5));
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
               EntityItemFrame var5 = (EntityItemFrame)var4.next();
               if (var5.getDisplayedItem().getMetadata() == (field6 ? 14 : 5)) {
                  BlinkModule.method0(var5, 8, (EnumFacing)null, var2);
               }
            }
         }

         if (!AutoFishNewModule.method7()) {
            if (!field9.method0(0)) {
               if (field2.a_((long)field5.method3()) && field58.pointedEntity instanceof EntityItemFrame) {
                  Iterator var6 = field4.iterator();

                  AutoAlignModule.Class0 var7;
                  do {
                     if (!var6.hasNext()) {
                        return;
                     }

                     var7 = (AutoAlignModule.Class0)var6.next();
                  } while(!var7.method3() || AutoAlignModule.Class0.field0(var7) != field58.pointedEntity);

                  Class361.method0((Class94)(new Class395(field5.method3() == 0 ? var7::method1 : var7::method5, true, IRCModule::method2)));
               }
            } else {
               var2 = (List)field4.stream().filter(AutoAlignModule.Class0::method3).collect(Collectors.toList());
               if (!var2.isEmpty() && field2.method0(field5.method0() == 0.0D && var2.size() == 1 ? 500.0D : field5.method0())) {
                  Class361.method0((Class94)(new Class395(AutoAlignModule::lambda$onUpdate$6, true, AutoAlignModule::lambda$onUpdate$7)));
               }

            }
         }
      }
   }

   public static double lambda$onUpdate$5(EntityItemFrame var0) {
      return -var0.posY;
   }

   public void b_() {
      field7.clear();
      field4.clear();
   }

   public static void lambda$onUpdate$6(List var0) {
      do {
         AutoAlignModule.Class0 var1 = (AutoAlignModule.Class0)var0.remove(0);
         if (var1.method2()) {
            var1.method1();
            field2.o_();
         }
      } while(!var0.isEmpty() && (field5.method0() != 0.0D || var0.size() > 1) && field2.method0(field5.method0()));

   }

   public static boolean lambda$rotate$8(BlockPos var0, EntityItemFrame var1) {
      return var1.getPosition().equals(var0);
   }

   public static Boolean lambda$static$0() {
      return !field9.method0(0);
   }

   public static int method6() {
      double var0 = (double)System.currentTimeMillis() / 1000.0D;
      double var2 = Math.floor(var0 - 1.560276E9D);
      return (int)(var2 / 446400.0D + 1.0D);
   }

   public static boolean field0(EntityItemFrame var0, AutoAlignModule.Class0 var1) {
      return AutoAlignModule.Class0.field0(var1).getEntityId() == var0.getEntityId();
   }

   public static boolean lambda$onUpdate$2(EntityItemFrame var0) {
      return var0.getDisplayedItem().getItem() == Item.getItemFromBlock(Blocks.wool);
   }

   public AutoAlignModule() {
      super("Auto Align", Category.dungeon, SubCategory.floor7, "Does the f7 p3 s3 device");
      this.method0((Setting[])(new Setting[]{field9, field3, field5, W_}));
   }

   public static class Class0 {
      final int field0;
      private int field2;
      private final EntityItemFrame field3;
      boolean field1;

      public void method5() {
         this.field2 = (this.field2 + 1) % 8;
         if (this.field2 == this.field0) {
            this.field1 = true;
         }

         AutoAlignModule.access$100(new C02PacketUseEntity(this.field3, new Vec3(0.0D, 0.0D, 0.0D)));
         AutoAlignModule.access$200(new C02PacketUseEntity(this.field3, Action.INTERACT));
      }

      public void method1() {
         while(this.field2 != this.field0) {
            this.method5();
         }

         this.field1 = true;
      }

      public boolean method2() {
         return (double)AutoAlignModule.access$300().thePlayer.getDistanceToEntity(this.field3) < AutoAlignModule.field3.method0();
      }

      public Class0(EntityItemFrame var1, int var2) {
         this.field0 = var2;
         this.field3 = var1;
         this.field2 = var1.getRotation();
      }

      public boolean method3() {
         return this.field2 != this.field0 && !this.field1;
      }

      static EntityItemFrame field0(AutoAlignModule.Class0 var0) {
         return var0.field3;
      }
   }
}
