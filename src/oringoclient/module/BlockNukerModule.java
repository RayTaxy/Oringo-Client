package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Consumer;
import map.Class296;
import map.Class361;
import map.Class428;
import map.Class510;
import map.Class94;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class148;
import oringo.event.Class75;
import oringo.mixin.PlayerControllerMPAccessor;
import oringo.mixin.RenderGlobalAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class BlockNukerModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("No delay", true);
   public final BooleanSetting aZ_ = new BooleanSetting("Scroll click to whitelist", true);
   public final Class296 field2 = new Class296();
   public BlockPos field3 = null;
   public final HashMap field4 = new HashMap();
   public final HashSet field5 = new HashSet();
   public String field6 = "";
   public final DoubleSetting field7 = new DoubleSetting("Max break time", 60.0D, 0.0D, 200.0D, 1.0D);
   public final DoubleSetting field8 = new DoubleSetting("Range", 5.5D, 3.0D, 6.0D, 0.1D);
   public final DoubleSetting field9 = (new DoubleSetting("Skip on", 90.0D, 0.0D, 100.0D, 10.0D)).method0("%");
   public final BooleanSetting field10 = new BooleanSetting("Break block under you", false);
   public final StringSetting field11 = new StringSetting("Allowed Blocks", "");
   public BlockPos ba_ = null;

   public void method0(IBlockState var1) {
      ArrayList var2 = new ArrayList();
      String[] var3 = this.field11.method1().split(";");
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String var6 = var3[var5];
         if (!var6.isEmpty()) {
            var2.add(var6);
         }
      }

      String var7 = var1.getBlock().getRegistryName().replaceFirst("minecraft:", "");
      method2(String.format("Added §r%s §fto the block nuker whitelist", var7));
      var2.add(var7);
      this.field11.method1(String.join(";", var2));
   }

   public boolean method1() {
      ItemStack var1 = field58.thePlayer.getHeldItem();
      return CreateNewkeybindModule.method0(var1, "Mining Speed:", true);
   }

   public void method0(float var1) {
      if (!field58.thePlayer.isPotionActive(Potion.digSlowdown)) {
         float var2 = ((PlayerControllerMPAccessor)field58.playerController).getCurBlockDamageMP();
         if (var2 != 0.0F) {
            float var3 = field58.theWorld.getBlockState(this.ba_).getBlock().getPlayerRelativeBlockHardness(field58.thePlayer, field58.thePlayer.worldObj, this.ba_);
            if (var3 == 0.0F) {
               return;
            }

            int var4 = (int)Math.ceil((double)((1.0F - var2) / var3));
            AutoWeirdosModule.method0(String.format("%.2f", (double)var4 / 20.0D) + "s", (double)this.ba_.getX() + 0.5D, (double)this.ba_.getY() + 1.5D, (double)this.ba_.getZ() + 0.5D, (double)(var2 + var3 * var1), Color.GREEN);
         }

      }
   }

   public boolean method0(BlockPos var1) {
      double var2 = this.field8.method0();
      var2 *= var2;
      return AutoHarpModule.method5().squareDistanceTo(new Vec3((double)var1.getX() + 0.5D, (double)(var1.getY() - 1), (double)var1.getZ() + 0.5D)) < var2;
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.ba_ != null) {
         this.method0(var1.partialTicks);
         this.method6();
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!this.field4.isEmpty()) {
         this.field4.values().removeIf(BlockNukerModule::lambda$onUpdate$0);
      }

      this.method7();
      if (this.ba_ != null) {
         MovingObjectPosition var2 = AutoCloakModule.method0(this.ba_);
         if (var2 != null) {
            if (this.field0.method1()) {
               ((PlayerControllerMPAccessor)field58.playerController).setBlockHitDelay(0);
            }

            Class361.method0((Class94)(new Class510(this.ba_, var2.sideHit)));
         }
      }
   }

   public static boolean lambda$onUpdate$0(Class296 var0) {
      return var0.a_(5L);
   }

   public void method5() {
      this.ba_ = null;
      BlockPos var1 = AutoConversationModule.method5();
      ArrayList var2 = new ArrayList();
      Iterator var3 = Class428.method0(var1).iterator();

      while(true) {
         BlockPos var4;
         IBlockState var5;
         do {
            if (!var3.hasNext()) {
               if (var2.isEmpty()) {
                  return;
               }

               this.field2.o_();
               this.ba_ = (BlockPos)var2.get(0);
               this.field4.put(this.ba_, new Class296());
               return;
            }

            var4 = (BlockPos)var3.next();
            var5 = field58.theWorld.getBlockState(var4);
         } while(!this.field10.method1() && var5.getBlock().isCollidable() && var4.equals(ThystHiderModule.method3()));

         if (this.method1(var5) && !var4.equals(this.field3) && !this.field4.containsKey(var4) && this.method0(var4)) {
            var2.add(var4);
         }
      }
   }

   public boolean method1(IBlockState var1) {
      if (!this.field6.equals(this.field11.method1())) {
         this.field5.clear();
         String[] var2 = this.field11.method1().split(";");
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            if (!var5.isEmpty()) {
               this.field5.add(Block.getBlockFromName(var5));
            }
         }

         this.field6 = this.field11.method1();
      }

      return this.field5.contains(var1.getBlock());
   }

   public BlockNukerModule() {
      super("Block Nuker", Category.skyblock, SubCategory.mining, "Nukes blocks near you");
      this.method0((Setting[])(new Setting[]{this.field8, this.field9, this.field7, this.field0, this.field10, this.aZ_, this.field11}));
   }

   @SubscribeEvent
   public void method0(Class148 var1) {
      if (this.aZ_.method1()) {
         MovingObjectPosition var2 = field58.objectMouseOver;
         if (var2 != null && var2.typeOfHit == MovingObjectType.BLOCK) {
            IBlockState var3 = field58.theWorld.getBlockState(var2.getBlockPos());
            if (var3.getBlock() != Blocks.air) {
               if (this.method1(var3)) {
                  this.method2(var3);
               } else {
                  this.method0(var3);
               }

            }
         }
      }
   }

   public void method6() {
      AutoMaskModule.method0(this.ba_, new Color(200, 200, 200, 100));
   }

   public void method2(IBlockState var1) {
      ArrayList var2 = new ArrayList();
      String[] var3 = this.field11.method1().split(";");
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String var6 = var3[var5];
         if (!var6.isEmpty()) {
            if (Block.getBlockFromName(var6) == var1.getBlock()) {
               method2(String.format("Removed §r%s §ffrom the block nuker whitelist", var6));
            } else {
               var2.add(var6);
            }
         }
      }

      this.field11.method1(String.join(";", var2));
   }

   public void method7() {
      this.field3 = this.ba_;
      if (this.field7.method3() != 0 && this.field2.a_((long)this.field7.method3())) {
         this.ba_ = null;
      }

      if (field58.thePlayer.isPotionActive(Potion.digSlowdown)) {
         if (this.ba_ != null) {
            Iterator var1 = ((RenderGlobalAccessor)field58.renderGlobal).getDamagedBlocks().values().iterator();

            while(var1.hasNext()) {
               DestroyBlockProgress var2 = (DestroyBlockProgress)var1.next();
               if (var2.getPosition().equals(this.ba_) && var2.getPartialBlockDamage() >= this.field9.method3() / 10) {
                  this.ba_ = null;
                  break;
               }
            }
         }

         if (!this.method1()) {
            this.ba_ = null;
            return;
         }
      }

      if (this.ba_ == null) {
         this.method5();
      } else {
         IBlockState var3 = field58.theWorld.getBlockState(this.ba_);
         if (!this.method1(var3) || !this.method0(this.ba_)) {
            this.method5();
         }
      }

   }

   public static void method0(int var0, Consumer var1, boolean var2) {
      if (var0 != -1 && !Oringo.field9.playerController.getIsHittingBlock()) {
         if (var0 - 36 == Oringo.field9.thePlayer.inventory.currentItem) {
            var1.accept(Oringo.field9.thePlayer.getHeldItem());
         } else if (var0 >= 36 && !var2) {
            AutoJoeyModule.method0(var0 - 36);
            var1.accept(Oringo.field9.thePlayer.inventory.getStackInSlot(var0 - 36));
         } else if (RenderChunkBoundsModule.method5()) {
            ItemStack var3 = Oringo.field9.thePlayer.inventoryContainer.getSlot(var0).getStack();
            Oringo.field9.playerController.windowClick(Oringo.field9.thePlayer.inventoryContainer.windowId, var0, Oringo.field9.thePlayer.inventory.currentItem, 2, Oringo.field9.thePlayer);
            var1.accept(var3);
            Oringo.field9.playerController.windowClick(Oringo.field9.thePlayer.inventoryContainer.windowId, var0, Oringo.field9.thePlayer.inventory.currentItem, 2, Oringo.field9.thePlayer);
         }

      }
   }
}
