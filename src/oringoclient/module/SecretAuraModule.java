package oringo.module;

import com.google.common.collect.Lists;
import com.mojang.authlib.properties.Property;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import map.Class208;
import map.Class256;
import map.Class34;
import map.Class352;
import map.Class361;
import map.Class362;
import map.Class376;
import map.Class402;
import map.Class417;
import map.Class426;
import map.Class428;
import map.Class447;
import map.Class462;
import map.Class469;
import map.Class496;
import map.Class94;
import map.Class95;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockLever;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.ArrayUtils;
import oringo.command.ReplyCommand;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class274;
import oringo.event.Class290;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class SecretAuraModule extends Module {
   public final StringSetting field0 = (StringSetting)(new StringSetting("Item")).method2("The item that secret aura will swap to before clicking");
   public final BooleanSetting field1 = new BooleanSetting("Disable in boss", false);
   public final DoubleSetting field2 = new DoubleSetting("Click delay", 400.0D, 0.0D, 1000.0D, 10.0D);
   public final DoubleSetting field3 = new DoubleSetting("Pitch", 0.5D, 0.0D, 5.0D, 0.05D, this::lambda$new$4);
   public final DoubleSetting field4 = new DoubleSetting("Volume", 1.0D, 0.0D, 5.0D, 0.05D, this::lambda$new$5);
   public static final HashMap field5 = new HashMap();
   public final DoubleSetting field6 = new DoubleSetting("Fov", 360.0D, 1.0D, 360.0D, 1.0D, this::lambda$new$1);
   public final StringSetting field7 = (StringSetting)(new StringSetting("Sound", "random.orb", this::lambda$new$3)).method2("Plays a sound on secret pickup");
   public final BooleanSetting field8 = (BooleanSetting)(new BooleanSetting("Rotation", false, this::lambda$new$2)).method2("Makes aura rotate before picking up the secret");
   public final DoubleSetting field9 = new DoubleSetting("Essence reach", 5.0D, 2.0D, 6.0D, 0.1D);
   public boolean field10;
   public final ArrayList field11 = new ArrayList();
   public final BooleanSetting field12 = new BooleanSetting("Trigger bot", false, this::lambda$new$0);
   public final BooleanSetting field13 = new BooleanSetting("Secret ESP", false);
   public final BooleanSetting field14 = new BooleanSetting("Highlight Clicked", false);
   public final BooleanSetting field15 = new BooleanSetting("Play sound", false);
   public final DoubleSetting field16 = new DoubleSetting("Reach", 5.0D, 2.0D, 6.0D, 0.1D);
   public final BooleanSetting field17 = new BooleanSetting("Redstone key", false);
   public boolean field18;
   public final BooleanSetting field19 = new BooleanSetting("Disable in gui", false);
   public final BooleanSetting field20 = (BooleanSetting)(new BooleanSetting("Close chests", true)).method2("Makes aura close secret chests");
   public final HashMap field21 = new HashMap();
   public final BooleanSetting field22 = new BooleanSetting("Click Doors", true);
   public final EnumSetting field23 = (EnumSetting)(new EnumSetting("Mode", "Aura", new String[]{"Aura", "Stonkless"})).method2("Changes how aura works\nAura - Clicks all blocks in the selected distance\nStonkless - requires you to hover over the secret and right click");
   public final BooleanSetting field24 = (BooleanSetting)(new BooleanSetting("From inventory", false)).method2("Uses the item from inventory");
   public final BooleanSetting field25 = new BooleanSetting("Render Range", false);
   public boolean field26;

   public boolean method0(BlockPos var1) {
      return this.field22.method1() && !Class496.field0 && var1.getY() == 70 && (var1.getZ() % 32 == -25 || var1.getZ() % 32 == -9) && (var1.getX() % 32 == -25 || var1.getX() % 32 == -9);
   }

   public static Class34[] method0(int var0, int var1, int var2, float var3, Class34 var4) {
      Class34[] var5 = SecretHitboxesModule.method0(var0, (double)(1.0F + var3), Class256.method1(), var4);
      Class34 var6 = var5[var5.length - 1];
      Class34[] var7 = new Class34[var1];
      Arrays.fill(var7, var6);
      Class34[] var8 = SecretHitboxesModule.method0(var2, 1.0D, var6, var4);
      return (Class34[])ArrayUtils.addAll(ArrayUtils.addAll(var5, var7), var8);
   }

   public Boolean lambda$new$1() {
      return !this.field23.method0(0);
   }

   public SecretAuraModule() {
      super("Secret Aura", 0, Category.dungeon, SubCategory.main, "Picks up secrets automatically");
      this.method0((Setting[])(new Setting[]{this.field23, this.field12, this.field6, this.field8, this.field16, this.field9, this.field0, this.field24, this.field2, this.field17, this.field22, this.field20, this.field1, this.field19, this.field13, this.field25, this.field14, this.field15, this.field7, this.field3, this.field4}));
   }

   public Boolean lambda$new$4() {
      return !this.field15.method1();
   }

   public Boolean lambda$new$0() {
      return !this.field23.method0(1);
   }

   public void method1(BlockPos var1) {
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableBlend();
      GlStateManager.enableCull();
      GlStateManager.disableTexture2D();
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.disableLighting();
      IceFillHelperModule.method0((new AxisAlignedBB((double)var1.getX(), (double)var1.getY(), (double)var1.getZ(), (double)(var1.getX() + 1), (double)(var1.getY() + 1), (double)(var1.getZ() + 1))).offset(-field58.getRenderManager().viewerPosX, -field58.getRenderManager().viewerPosY, -field58.getRenderManager().viewerPosZ));
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
   }

   public void b_() {
      this.method5();
   }

   public boolean method0(BlockPos var1, EnumFacing var2, Vec3 var3, boolean var4) {
      int var5;
      if (var4) {
         var5 = Class95.method0(SecretAuraModule::lambda$interactWithBlock$6);
         return var5 == -1 ? false : Class361.method0((Class94)(new Class402(var5, SecretAuraModule::lambda$interactWithBlock$7)));
      } else {
         var5 = this.field0.F_() ? 36 : Class95.method0(this::lambda$interactWithBlock$8);
         return var5 != -1 && (var5 >= 36 || this.field24.method1()) ? Class361.method0((Class94)(new Class402(var5, SecretAuraModule::lambda$interactWithBlock$9))) : false;
      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.method10()) {
         Iterator var2 = this.field11.iterator();

         while(var2.hasNext()) {
            TileEntity var3 = (TileEntity)var2.next();
            BlockPos var4 = var3.getPos();
            if (this.field25.method1()) {
               double var5 = Math.min(this.field16.method0(), Math.abs(field58.thePlayer.getPositionEyes(var1.partialTicks).yCoord - (double)var4.getY()));
               Class462.method0(new Vec3(var4), Math.sqrt(this.field16.method0() * this.field16.method0() - var5 * var5), Color.GREEN);
            }

            Color var9 = field5.containsKey(var4) ? Color.RED : Color.green;
            GlStateManager.color((float)var9.getRed() / 255.0F, (float)var9.getGreen() / 255.0F, (float)var9.getBlue() / 255.0F, 0.2F);
            this.method1(var4);
            AutoCloseModule.method5();
         }

         if (!this.field21.isEmpty()) {
            if (this.field14.method1()) {
               var2 = this.field21.keySet().iterator();

               while(var2.hasNext()) {
                  BlockPos var7 = (BlockPos)var2.next();
                  Color var8 = Class362.field7.method17();
                  AutoBlazeModule.method0(new Color(var8.getRed(), var8.getGreen(), var8.getBlue(), 80));
                  this.method1(var7);
                  AutoCloseModule.method5();
               }
            }

            this.field21.entrySet().removeIf(SecretAuraModule::lambda$onRender$10);
         }
      }
   }

   public Boolean lambda$new$2() {
      return !this.field23.method0(0);
   }

   public boolean method2(BlockPos var1) {
      IBlockState var2 = field58.theWorld.getBlockState(var1);
      Block var3 = var2.getBlock();
      if (this.method0(var1, var3 == Blocks.skull ? this.field9.method0() : this.field16.method0()) && this.method0(var2, var1)) {
         if (this.field23.method0(1)) {
            return true;
         } else {
            SecretAuraModule.Class0 var4 = (SecretAuraModule.Class0)field5.get(var1);
            return var4 == null || var4.a_((long)this.field2.method3()) && !var4.field1;
         }
      } else {
         return false;
      }
   }

   public static void lambda$interactWithBlock$7(BlockPos var0, ItemStack var1) {
      Class426.method10();
      if (field58.playerController.onPlayerRightClick(field58.thePlayer, field58.theWorld, var1, var0, EnumFacing.UP, (new Vec3(var0)).addVector(0.5D, 1.0D, 0.5D))) {
         field58.thePlayer.swingItem();
      } else if (var1 != null) {
         AutoCraftModule.method3(new C08PacketPlayerBlockPlacement(var1));
      }

   }

   public static boolean w_(String var0) {
      Class208 var1 = IceFillHelperModule.method0((int)Class469.field0.thePlayer.posX, (int)Class469.field0.thePlayer.posZ);
      return var1 != null && var1.method45().equalsIgnoreCase(var0);
   }

   public MovingObjectPosition method3(BlockPos var1) {
      if (this.field23.method0(1)) {
         Vec3 var7 = field58.thePlayer.getPositionEyes(1.0F);
         Vec3 var3 = field58.thePlayer.getLook(1.0F);
         Vec3 var4 = var7.addVector(var3.xCoord * 7.0D, var3.yCoord * 7.0D, var3.zCoord * 7.0D);
         AxisAlignedBB var5 = new AxisAlignedBB(var1, var1.add(1, 1, 1));
         MovingObjectPosition var6 = var5.calculateIntercept(var7, var4);
         if (var6 == null) {
            return null;
         } else {
            var6 = new MovingObjectPosition(var6.hitVec, var6.sideHit, var1);
            return var6;
         }
      } else {
         if (this.field6.method0() != 360.0D) {
            Class34 var2 = ReplyCommand.method0((new Vec3(var1)).addVector(0.5D, 0.5D, 0.5D));
            if (!Class417.method0(var2, this.field6.method1())) {
               return null;
            }
         }

         return AutoCloakModule.method0(var1);
      }
   }

   public boolean method0(BlockPos var1, EnumFacing var2, Vec3 var3) {
      if (this.field14.method1()) {
         this.field21.put(var1, new Class447());
      }

      IBlockState var4 = field58.theWorld.getBlockState(var1);
      if (!this.method0(var1, var2, var3, var4.getBlock() == Blocks.redstone_block)) {
         return false;
      } else {
         if (this.field15.method1()) {
            field58.thePlayer.playSound(this.field7.method1(), this.field4.method1(), this.field3.method1());
         }

         BrushModule.field1 = true;
         SecretAuraModule.Class0 var5 = (SecretAuraModule.Class0)field5.get(var1);
         if (var5 == null) {
            SecretAuraModule.Class0 var6 = new SecretAuraModule.Class0((SecretAuraModule$1)null);
            field5.put(var1, var6);
            if (var4.getBlock() == Blocks.skull) {
               var6.field0.method0(-2000L);
            }
         } else {
            var5.o_();
         }

         return true;
      }
   }

   public void method5() {
      field5.clear();
      this.field10 = false;
      this.field11.clear();
      this.field21.clear();
   }

   public boolean method10() {
      boolean var1 = this.field23.method0(1);
      return Class496.field5 && (!var1 || !Class496.field13) && (!Class496.field13 || !this.field1.method1()) && !w_("Weirdos") && (!this.field19.method1() || field58.currentScreen == null);
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (Class496.field5) {
         if (var1.field0 instanceof S2DPacketOpenWindow) {
            if (!this.field20.method1()) {
               return;
            }

            S2DPacketOpenWindow var2 = (S2DPacketOpenWindow)var1.field0;
            String var3 = var2.getWindowTitle().getUnformattedText();
            if (var3.equals("Chest") || var3.equals("Large Chest")) {
               var1.method9();
               if (field58.currentScreen instanceof GuiContainer) {
                  field58.thePlayer.closeScreen();
               }

               method3(new C0DPacketCloseWindow(var2.getWindowId()));
            }
         } else if (var1.field0 instanceof S29PacketSoundEffect) {
            S29PacketSoundEffect var4 = (S29PacketSoundEffect)var1.field0;
            if ("random.click".equals(var4.getSoundName()) || "random.chestopen".equals(var4.getSoundName())) {
               SecretAuraModule.Class0 var5 = (SecretAuraModule.Class0)field5.get(new BlockPos(var4.getX(), var4.getY(), var4.getZ()));
               if (var5 != null) {
                  var5.field1 = true;
               }
            }
         }

      }
   }

   public boolean method0(BlockPos var1, double var2) {
      return field58.thePlayer.getDistanceSq((double)var1.getX(), (double)((float)var1.getY() - field58.thePlayer.eyeHeight), (double)var1.getZ()) < var2 * var2;
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.method10()) {
         this.field11.clear();
         if (this.field13.method1()) {
            Iterator var2 = field58.theWorld.loadedTileEntityList.iterator();

            while(var2.hasNext()) {
               TileEntity var3 = (TileEntity)var2.next();
               if (var3 instanceof TileEntityChest && ((TileEntityChest)var3).lidAngle == 0.0F && Class290.method0().isBoundingBoxInFrustum(var3.getRenderBoundingBox())) {
                  this.field11.add(var3);
               }
            }
         }

      }
   }

   public List method11() {
      ArrayList var1 = Lists.newArrayList();
      BlockPos var2 = AutoConversationModule.method5();
      Iterator var3 = Class428.method0(var2).iterator();

      while(var3.hasNext()) {
         BlockPos var4 = (BlockPos)var3.next();
         if (this.method2(var4)) {
            var1.add(var4);
         }
      }

      return var1;
   }

   public boolean lambda$interactWithBlock$8(ItemStack var1) {
      return var1.getDisplayName().toLowerCase().contains(this.field0.method1().toLowerCase());
   }

   public static boolean lambda$interactWithBlock$6(ItemStack var0) {
      return var0.getDisplayName().toLowerCase().contains("redstone key");
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Class210.Class1 var1) {
      this.field26 = Class376.method0("■ 1x");
      this.field18 = Class376.method0("■ ✓");
      if (this.method10()) {
         if (!this.field23.method0(1) || this.field10 || this.field12.method1()) {
            Iterator var2 = this.method11().iterator();

            while(true) {
               if (var2.hasNext()) {
                  BlockPos var3 = (BlockPos)var2.next();
                  MovingObjectPosition var4 = this.method3(var3);
                  if (var4 == null) {
                     continue;
                  }

                  double var5 = var4.hitVec.xCoord - (double)var4.getBlockPos().getX();
                  double var7 = var4.hitVec.yCoord - (double)var4.getBlockPos().getY();
                  double var9 = var4.hitVec.zCoord - (double)var4.getBlockPos().getZ();
                  if (var5 > 1.0D || var5 < 0.0D || var7 > 1.0D || var7 < 0.0D || var9 > 1.0D || var9 < 0.0D) {
                     throw new IllegalStateException("Invalid click values!");
                  }

                  if (this.method0(var4.getBlockPos(), var4.sideHit, var4.hitVec)) {
                     this.field10 = false;
                     if (this.field8.method1() && this.field23.method0(0)) {
                        Class34 var11 = Class352.method0((double)var3.getX() + 0.5D, (double)var3.getY() + 0.5D, (double)var3.getZ() + 0.5D);
                        var1.method0(var11);
                     }
                  }
               }

               return;
            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Class274 var1) {
      this.field10 = this.field23.method0(1);
   }

   public Boolean lambda$new$3() {
      return !this.field15.method1();
   }

   public boolean method0(TileEntitySkull var1) {
      if (var1.getSkullType() == 3 && var1.getPlayerProfile() != null && var1.getPlayerProfile().getProperties() != null) {
         Property var2 = (Property)WTapModule.method0((Iterable)var1.getPlayerProfile().getProperties().get("textures"));
         return var2 != null && (var2.getValue().equals("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkYjRhZGZhOWJmNDhmZjVkNDE3MDdhZTM0ZWE3OGJkMjM3MTY1OWZjZDhjZDg5MzQ3NDlhZjRjY2U5YiJ9fX0=") || this.field17.method1() && var2.getValue().equals("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I4NTJiYTE1ODRkYTllNTcxNDg1OTk5NTQ1MWU0Yjk0NzQ4YzRkZDYzYWU0NTQzYzE1ZjlmOGFlYzY1YzgifX19") && SecretHitboxesModule.method0("redstone key", "Golden Oasis"));
      } else {
         return false;
      }
   }

   public boolean method4(BlockPos var1) {
      TileEntity var2 = field58.theWorld.getTileEntity(var1);
      return var2 instanceof TileEntityChest && ((TileEntityChest)var2).lidAngle != 0.0F;
   }

   public boolean method0(IBlockState var1, BlockPos var2) {
      Block var3 = var1.getBlock();
      if (var3 == Blocks.chest) {
         return !this.method4(var2) && (field58.thePlayer.posY >= 78.0D || !w_("Shroom"));
      } else if (var3 == Blocks.trapped_chest) {
         return !this.method4(var2) && !w_("Dueces");
      } else if (var3 == Blocks.skull) {
         return this.method0((TileEntitySkull)field58.theWorld.getTileEntity(var2));
      } else if (var3 == Blocks.lever) {
         return !ShortbowTriggerbotModule.method0(var2, "Board") && (!Class496.field13 || !(Boolean)var1.getValue(BlockLever.POWERED));
      } else if (var3 == Blocks.redstone_block) {
         return this.field17.method1() && ShortbowTriggerbotModule.method0(var2, "redstone key", "Golden Oasis");
      } else if (var3 == Blocks.coal_block) {
         return this.field26 && this.method0(var2);
      } else if (var3 != Blocks.stained_hardened_clay) {
         return false;
      } else {
         return var1.getValue(BlockColored.COLOR) == EnumDyeColor.RED && this.field18 && this.method0(var2);
      }
   }

   public static boolean lambda$onRender$10(Entry var0) {
      return ((Class447)var0.getValue()).a_(1500L);
   }

   public Boolean lambda$new$5() {
      return !this.field15.method1();
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.method5();
   }

   public static void lambda$interactWithBlock$9(BlockPos var0, EnumFacing var1, Vec3 var2, ItemStack var3) {
      Class426.method10();
      if (field58.playerController.onPlayerRightClick(field58.thePlayer, field58.theWorld, var3, var0, var1, var2)) {
         field58.thePlayer.swingItem();
      } else if (var3 != null) {
         AutoCraftModule.method3(new C08PacketPlayerBlockPlacement(var3));
      }

   }

   private static class Class0 {
      final Class447 field0;
      boolean field1;

      Class0(SecretAuraModule$1 var1) {
         this();
      }

      public boolean a_(long var1) {
         return this.field0.a_(var1);
      }

      private Class0() {
         this.field0 = new Class447();
      }

      public void o_() {
         this.field0.o_();
      }
   }
}
