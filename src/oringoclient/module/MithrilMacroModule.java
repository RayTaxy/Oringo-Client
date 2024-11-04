package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import map.Class122;
import map.Class263;
import map.Class34;
import map.Class362;
import map.Class6;
import map.Class94;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStone.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.objectweb.asm.Type;
import oringo.command.BloodSkipCommand;
import oringo.command.ReplyCommand;
import oringo.event.Class189;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class MithrilMacroModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Max break time", 160.0D, 40.0D, 400.0D, 1.0D);
   public int cd_ = 0;
   public final DoubleSetting field2 = new DoubleSetting("Walking ticks", 5.0D, 0.0D, 60.0D, 1.0D);
   public int field3 = 0;
   public final DoubleSetting field4 = new DoubleSetting("Head movements", 5.0D, 0.0D, 50.0D, 1.0D);
   public final DoubleSetting field5 = new DoubleSetting("Auto leave", 100.0D, 0.0D, 200.0D, 1.0D);
   public int field6 = -1;
   public int Y_ = 0;
   public BlockPos ba_ = null;
   public final DoubleSetting field9 = new DoubleSetting("Block skip progress", 0.9D, 0.0D, 1.0D, 0.1D);
   public BlockPos field10 = null;
   public int field11 = 0;
   public final ArrayList field12 = new ArrayList();
   public Vec3 field13 = null;
   public final EnumSetting field14 = new EnumSetting("Target", "Clay", new String[]{"Clay", "Prismarine", "Wool", "Blue", "Gold"});
   public final BooleanSetting field15 = new BooleanSetting("Drill Refuel", false);
   public final DoubleSetting field16 = new DoubleSetting("Rotations", 10.0D, 1.0D, 20.0D, 1.0D);
   public final DoubleSetting field17 = new DoubleSetting("Walking %", 0.1D, 0.0D, 5.0D, 0.1D);
   public final BooleanSetting field18 = new BooleanSetting("Auto ability", true);
   public Vec3 field19 = null;
   public final ArrayList field20 = new ArrayList();
   public final BooleanSetting field21 = new BooleanSetting("Prioritize titanium", true);
   public int field22 = 0;
   public int field23 = -1;
   public boolean field24 = false;
   public final BooleanSetting field25 = new BooleanSetting("Sneak", false);
   public final BooleanSetting field26 = new BooleanSetting("Mine under", false);
   public int field27 = 0;
   public EntityArmorStand field28;
   public final DoubleSetting field29 = new DoubleSetting("Accuracy", 5.0D, 3.0D, 10.0D, 1.0D);

   public void lambda$onTick$8() {
      this.method1(true);
   }

   public static boolean lambda$matchesAny$20(Entry var0) {
      return var0.toString().contains("gray");
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (this.method44()) {
         String var2 = var1.message.getFormattedText();
         if (this.field15.method1() && ChatFormatting.stripFormatting(var2).startsWith("Your") && ChatFormatting.stripFormatting(var2).endsWith("Refuel it by talking to a Drill Mechanic!") && this.field28 != null) {
            this.method1(false);
            (new Thread(this::lambda$onChat$3)).start();
         }

         if (var1.message.getUnformattedText().equals("Mining Speed Boost is now available!") && this.field18.method1() && field58.thePlayer.getHeldItem() != null) {
            PopupAnimationModule.method2("Auto ability");
            field58.playerController.sendUseItem(field58.thePlayer, field58.theWorld, field58.thePlayer.getHeldItem());
         }

         if (var1.message.getUnformattedText().equals("Oh no! Your Pickonimbus 2000 broke!")) {
            (new Thread(MithrilMacroModule::lambda$onChat$4)).start();
         }

      }
   }

   public boolean lambda$findTarget$13(BlockPos var1) {
      return !var1.equals(this.ba_);
   }

   public boolean lambda$onTick$11(DestroyBlockProgress var1) {
      return var1.getPosition().equals(this.ba_);
   }

   public boolean lambda$findTarget$16(BlockPos var1) {
      return !var1.equals(this.ba_);
   }

   public static boolean lambda$onTick$5(Entity var0) {
      return var0 instanceof EntityArmorStand;
   }

   public boolean lambda$onTick$12(DestroyBlockProgress var1) {
      return var1.getPosition().equals(this.ba_) && var1.getPartialBlockDamage() == (int)(this.field9.method0() * 10.0D);
   }

   public static boolean lambda$matchesAny$19(Entry var0) {
      return var0.toString().contains("lightBlue");
   }

   public boolean method2(BlockPos var1) {
      return this.method3(var1) != null;
   }

   public void lambda$onChat$3() {
      try {
         int[] var1 = new int[]{field58.gameSettings.keyBindForward.getKeyCode(), field58.gameSettings.keyBindLeft.getKeyCode(), field58.gameSettings.keyBindBack.getKeyCode(), field58.gameSettings.keyBindRight.getKeyCode(), field58.gameSettings.keyBindSneak.getKeyCode(), field58.gameSettings.keyBindAttack.getKeyCode()};
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            int var4 = var1[var3];
            KeyBinding.setKeyBindState(var4, false);
         }

         Thread.sleep(500L);
         if (!field58.playerController.isPlayerRightClickingOnEntity(field58.thePlayer, this.field28, new MovingObjectPosition(this.field28))) {
            field58.playerController.interactWithEntitySendPacket(field58.thePlayer, this.field28);
         }

         Thread.sleep(2500L);
         if (field58.thePlayer.openContainer instanceof ContainerChest && ((ContainerChest)field58.thePlayer.openContainer).getLowerChestInventory().getDisplayName().getUnformattedText().contains("Drill Anvil")) {
            int var6;
            Slot var7;
            for(var6 = 0; var6 < field58.thePlayer.openContainer.inventorySlots.size(); ++var6) {
               var7 = field58.thePlayer.openContainer.getSlot(var6);
               if (var7.getHasStack() && var7.getStack().getDisplayName().contains("Drill") && var7.getStack().getItem() == Items.prismarine_shard) {
                  field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, var7.slotNumber, 0, 1, field58.thePlayer);
                  break;
               }
            }

            Thread.sleep(500L);

            for(var6 = 0; var6 < field58.thePlayer.openContainer.inventorySlots.size(); ++var6) {
               var7 = field58.thePlayer.openContainer.getSlot(var6);
               if (var7.getHasStack() && (var7.getStack().getDisplayName().contains("Volta") || var7.getStack().getDisplayName().contains("Oil Barrel") || var7.getStack().getDisplayName().contains("Biofuel"))) {
                  field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, var7.slotNumber, 0, 1, field58.thePlayer);
                  break;
               }
            }

            Thread.sleep(500L);
            field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, 22, 0, 0, field58.thePlayer);
            Thread.sleep(250L);
            field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, 13, 0, 1, field58.thePlayer);
            Thread.sleep(250L);
            field58.thePlayer.closeScreen();
         }

         Thread.sleep(2500L);
         this.method1(true);
         KeyBinding.setKeyBindState(field58.gameSettings.keyBindAttack.getKeyCode(), true);
         field58.displayGuiScreen(new GuiChat());
      } catch (InterruptedException var5) {
         var5.printStackTrace();
      }

   }

   @SubscribeEvent
   public void a_(ClientTickEvent var1) {
      if (field58.currentScreen instanceof GuiDisconnected && this.field23 < 0 && this.method44()) {
         this.field23 = 250;
         this.method1(false);
      }

      if (this.field23-- == 0) {
         field58.displayGuiScreen(new GuiConnecting(new GuiMainMenu(), field58, new ServerData("Hypixel", "play.Hypixel.net", false)));
         (new Thread(this::lambda$reconnect$2)).start();
      }

   }

   public boolean method0(ItemStack var1) {
      return var1 != null && (var1.getDisplayName().contains("Pickaxe") || var1.getItem() == Items.prismarine_shard || var1.getDisplayName().contains("Gauntlet"));
   }

   public static String n_(String var0) {
      if (var0.lastIndexOf(64) == -1) {
         return var0;
      } else {
         String var1 = var0.substring(5).replaceFirst(" ASYNC", "");
         var1 = var1.replaceFirst("@\\w+", "");
         String var2 = BloodSkipCommand.method0(var1, Pattern.compile("\\(.+?\\)V"), 0);
         String var3 = Type.getArgumentTypes(var2)[0].getClassName();
         int var4 = var3.lastIndexOf(46) + 1;
         var1 = var1.replace(var2, String.format("(§c%s§f)", var3.substring(var4)));
         return var1;
      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      --this.field27;
      if (this.method44() && !(field58.currentScreen instanceof GuiContainer) && !(field58.currentScreen instanceof GuiEditSign) && this.field27 < 1) {
         ++this.Y_;
         if (field58.thePlayer != null && field58.thePlayer.getHeldItem() != null && field58.thePlayer.getHeldItem().getItem() instanceof ItemMap) {
            this.method1(false);
            field58.thePlayer.sendChatMessage("/l");
         }

         if (field58.theWorld != null) {
            if (this.field28 == null && this.field15.method1()) {
               Iterator var8 = ((List)field58.theWorld.getLoadedEntityList().stream().filter(MithrilMacroModule::lambda$onTick$5).collect(Collectors.toList())).iterator();

               Entity var9;
               do {
                  if (!var8.hasNext()) {
                     this.method1(false);
                     Class362.field51.method0(this::lambda$onTick$6, false);
                     return;
                  }

                  var9 = (Entity)var8.next();
               } while(!var9.getDisplayName().getFormattedText().contains("§e§lDRILL MECHANIC§r"));

               Class362.field54.field28 = (EntityArmorStand)var9;
               PopupAnimationModule.method2("Mechanic");
               return;
            }

            if (!this.method0(field58.thePlayer.getHeldItem())) {
               for(int var2 = 0; var2 < 9; ++var2) {
                  if (this.method0(field58.thePlayer.inventory.getStackInSlot(var2))) {
                     RenderBarriersModule.method0(var2);
                  }
               }
            }

            if (this.cd_-- <= 0) {
               int[] var4 = new int[]{field58.gameSettings.keyBindForward.getKeyCode(), field58.gameSettings.keyBindLeft.getKeyCode(), field58.gameSettings.keyBindBack.getKeyCode(), field58.gameSettings.keyBindRight.getKeyCode(), field58.gameSettings.keyBindLeft.getKeyCode(), field58.gameSettings.keyBindBack.getKeyCode(), field58.gameSettings.keyBindRight.getKeyCode(), field58.gameSettings.keyBindBack.getKeyCode(), field58.gameSettings.keyBindBack.getKeyCode()};
               if (this.field6 != -1) {
                  KeyBinding.setKeyBindState(this.field6, false);
                  KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), this.field25.method1());
               }

               if ((double)(new Random()).nextFloat() < this.field17.method0() / 100.0D) {
                  this.field6 = var4[(new Random()).nextInt(var4.length)];
                  KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), true);
                  KeyBinding.setKeyBindState(this.field6, true);
                  this.cd_ = (int)this.field2.method0();
               }
            } else {
               KeyBinding.setKeyBindState(this.field6, true);
               KeyBinding.setKeyBindState(field58.gameSettings.keyBindSneak.getKeyCode(), this.field25.method1());
            }

            if (field58.objectMouseOver != null && field58.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
               Entity var5 = field58.objectMouseOver.entityHit;
               if (var5 instanceof EntityPlayer && !Class6.method0(var5)) {
                  AutoIcePathModule.method5();
                  this.field27 = 5;
                  return;
               }
            }

            if (field58.theWorld.playerEntities.stream().anyMatch(MithrilMacroModule::lambda$onTick$7)) {
               ++this.field3;
            } else {
               this.field3 = 0;
            }

            boolean var6 = AutoMaskModule.g_("Dwarven Mines") || AutoMaskModule.g_("Crystal Hollows");
            if (this.field5.method0() <= (double)this.field3 && this.field5.method0() != 0.0D || !var6) {
               this.method1(false);
               if (Class362.field51.method44()) {
                  Class362.field51.method0(this::lambda$onTick$8, false);
               }

               this.field3 = 0;
               PopupAnimationModule.method2(!var6 ? "Not in dwarven" : "You have been seen by " + ((EntityPlayer)field58.theWorld.playerEntities.stream().filter(MithrilMacroModule::lambda$onTick$9).findFirst().get()).getName());
               return;
            }

            if (this.ba_ == null) {
               if (!this.method14()) {
                  PopupAnimationModule.method2("No possible target found");
               }

               return;
            }

            if (field58.objectMouseOver != null && field58.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
               if (this.field11++ == 40) {
                  this.method1(false);
                  if (Class362.field51.method44()) {
                     Class362.field51.method0(this::lambda$onTick$10, false);
                  }

                  return;
               }
            } else {
               this.field11 = 0;
            }

            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
            if (this.field25.method1() || this.cd_ != 0) {
               KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), true);
            }

            if (field58.objectMouseOver.typeOfHit == MovingObjectType.BLOCK && field58.currentScreen != null && !(field58.currentScreen instanceof GuiContainer) && this.Y_ % 2 == 0) {
               AutoIcePathModule.method5();
            }

            if (!this.field20.isEmpty() && (this.field24 || !this.method6(this.ba_))) {
               field58.thePlayer.rotationYaw = (Float)this.field20.get(0);
               field58.thePlayer.rotationPitch = (Float)this.field12.get(0);
               this.field20.remove(0);
               this.field12.remove(0);
               if (this.field20.isEmpty() && this.method2(this.ba_) && this.field4.method0() != 0.0D) {
                  this.field24 = false;
                  Vec3 var7 = this.field13;
                  this.field13 = this.method3(this.ba_);
                  this.field19 = this.field13;
                  this.method0(false);
                  this.field13 = var7;
                  return;
               }

               if (this.field4.method0() == 0.0D) {
                  this.field19 = null;
               }

               if (this.field24) {
                  return;
               }
            }

            if (field58.theWorld.getBlockState(this.ba_).getBlock().equals(Blocks.bedrock)) {
               if (!this.method14()) {
               }

               return;
            }

            if (field58.objectMouseOver.typeOfHit != MovingObjectType.BLOCK) {
               if (!this.method14()) {
               }

               return;
            }

            BlockPos var3 = field58.objectMouseOver.getBlockPos();
            if (!var3.equals(this.ba_)) {
               if (!this.method14()) {
               }

               return;
            }

            if (this.field9.method0() != 0.0D && !this.method6(this.ba_) && AutoJaxRangeModule.method1().values().stream().anyMatch(this::lambda$onTick$11) && AutoJaxRangeModule.method1().values().stream().anyMatch(this::lambda$onTick$12)) {
               this.method14();
            }

            if ((double)(this.field22++) == this.field0.method0()) {
               PopupAnimationModule.method2("Mining one block took too long");
               this.method14();
            }
         }
      }

   }

   @SubscribeEvent
   public void method0(Load var1) {
      this.field28 = null;
      if (this.method44()) {
         this.method1(false);
         if (Class362.field51.method44()) {
            Class362.field51.method0(this::lambda$onLoad$0, false);
         }
      }

   }

   public Vec3 method3(BlockPos var1) {
      ArrayList var2 = new ArrayList();

      for(int var3 = 0; (double)var3 < this.field29.method0(); ++var3) {
         for(int var4 = 0; (double)var4 < this.field29.method0(); ++var4) {
            for(int var5 = 0; (double)var5 < this.field29.method0(); ++var5) {
               Vec3 var6 = new Vec3((double)var1.getX() + (double)var3 / this.field29.method0(), (double)var1.getY() + (double)var4 / this.field29.method0(), (double)var1.getZ() + (double)var5 / this.field29.method0());
               this.field10 = new BlockPos(var6.xCoord, var6.yCoord, var6.zCoord);
               MovingObjectPosition var7 = field58.theWorld.rayTraceBlocks(field58.thePlayer.getPositionEyes(0.0F), var6, true, false, true);
               if (var7 != null) {
                  BlockPos var8 = var7.getBlockPos();
                  if (var8.equals(this.field10) && field58.thePlayer.getDistance(var6.xCoord, var6.yCoord - (double)field58.thePlayer.getEyeHeight(), var6.zCoord) < 4.5D && (this.field26.method1() || Math.abs(field58.thePlayer.posY - var6.yCoord) > 1.3D)) {
                     var2.add(var6);
                  }
               }
            }
         }
      }

      return var2.isEmpty() ? null : (Vec3)var2.get((new Random()).nextInt(var2.size()));
   }

   public MithrilMacroModule() {
      super("Mithril Macro", Category.other, SubCategory.skills, (String)null);
      this.method0((Setting[])(new Setting[]{this.field16, this.field15, this.field29, this.field21, this.field25, this.field9, this.field0, this.field18, this.field26, this.field5, this.field4, this.field17, this.field2, this.field14}));
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook && this.method44()) {
         this.field27 = 200;
         this.ba_ = null;
         KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), false);
         KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), false);
         int[] var2 = new int[]{field58.gameSettings.keyBindForward.getKeyCode(), field58.gameSettings.keyBindLeft.getKeyCode(), field58.gameSettings.keyBindBack.getKeyCode(), field58.gameSettings.keyBindRight.getKeyCode()};
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            int var5 = var2[var4];
            KeyBinding.setKeyBindState(var5, false);
         }
      }

   }

   public void lambda$onTick$6() {
      this.method1(true);
   }

   public static void lambda$onChat$4() {
      try {
         Thread.sleep(1000L);
      } catch (InterruptedException var1) {
         var1.printStackTrace();
      }

      for(int var0 = 0; var0 < 9; ++var0) {
         if (field58.thePlayer.inventory.getStackInSlot(var0) != null && field58.thePlayer.inventory.getStackInSlot(var0).getDisplayName().contains("Pickonimbus")) {
            field58.thePlayer.inventory.currentItem = var0;
            break;
         }
      }

   }

   public double lambda$findTarget$15(BlockPos var1, BlockPos var2) {
      return this.method6(var2) && this.field21.method1() ? 0.0D : this.method0(var2, var1, 0.6D);
   }

   public static boolean lambda$onTick$9(EntityPlayer var0) {
      return !var0.equals(field58.thePlayer) && var0.getDistanceToEntity(field58.thePlayer) < 10.0F && Class6.method0(var0);
   }

   public double lambda$findTarget$18(BlockPos var1, BlockPos var2) {
      return this.method6(var2) && this.field21.method1() ? 0.0D : this.method0(var2, var1, 0.6D);
   }

   public void method0(boolean var1) {
      Vec3 var2 = field58.thePlayer.getLookVec().add(field58.thePlayer.getPositionEyes(0.0F));
      if (!this.field20.isEmpty()) {
         this.field20.clear();
         this.field12.clear();
      }

      double var3 = (this.field16.method0() + 1.0D) * (var1 ? 1.0D : this.field4.method0());

      for(int var5 = 0; (double)var5 < var3; ++var5) {
         Vec3 var6 = new Vec3(var2.xCoord + (this.field13.xCoord - var2.xCoord) / var3 * (double)var5, var2.yCoord + (this.field13.yCoord - var2.yCoord) / var3 * (double)var5, var2.zCoord + (this.field13.zCoord - var2.zCoord) / var3 * (double)var5);
         Class34 var7 = ReplyCommand.method0(var6);
         this.field20.add(var7.method5());
         this.field12.add(var7.method2());
      }

      this.field24 = var1;
   }

   public void b_() {
      KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), false);
      KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), false);
   }

   public void lambda$onTick$10() {
      this.method1(true);
   }

   public double method0(BlockPos var1, BlockPos var2, double var3) {
      double var5 = (double)(var1.getX() - var2.getX());
      double var7 = (double)(var1.getY() - var2.getY()) * var3;
      double var9 = (double)(var1.getZ() - var2.getZ());
      return Math.sqrt(var5 * var5 + var7 * var7 + var9 * var9);
   }

   public void lambda$reconnect$2() {
      try {
         Thread.sleep(15000L);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      if (field58.thePlayer != null && Class362.field51.method44()) {
         Class362.field51.method0(this::lambda$null$1, false);
      }

   }

   public static boolean lambda$onTick$7(EntityPlayer var0) {
      return !var0.equals(field58.thePlayer) && var0.riddenByEntity == null && !Class122.method0((EntityOtherPlayerMP)var0) && var0.getDistanceToEntity(field58.thePlayer) < 10.0F && Class6.method0(var0) && (!var0.isInvisible() || var0.posY - field58.thePlayer.posY <= 5.0D);
   }

   public String d_() {
      return "Legacy Mithril Macro";
   }

   public boolean method4(BlockPos var1) {
      IBlockState var2 = field58.theWorld.getBlockState(var1);
      if (this.method6(var1)) {
         return true;
      } else {
         String var3 = this.field14.method4();
         byte var4 = -1;
         switch(var3.hashCode()) {
         case 2073722:
            if (var3.equals("Blue")) {
               var4 = 3;
            }
            break;
         case 2102913:
            if (var3.equals("Clay")) {
               var4 = 0;
            }
            break;
         case 2225280:
            if (var3.equals("Gold")) {
               var4 = 4;
            }
            break;
         case 2702037:
            if (var3.equals("Wool")) {
               var4 = 2;
            }
            break;
         case 2076269454:
            if (var3.equals("Prismarine")) {
               var4 = 1;
            }
         }

         switch(var4) {
         case 0:
            return var2.getBlock().equals(Blocks.stained_hardened_clay) || var2.getBlock().equals(Blocks.wool) && ((EnumDyeColor)var2.getValue(BlockColored.COLOR)).equals(EnumDyeColor.GRAY);
         case 1:
            return var2.getBlock().equals(Blocks.prismarine);
         case 2:
            return var2.getBlock().equals(Blocks.wool) && ((EnumDyeColor)var2.getValue(BlockColored.COLOR)).equals(EnumDyeColor.LIGHT_BLUE);
         case 3:
            return var2.getBlock().equals(Blocks.wool) && ((EnumDyeColor)var2.getValue(BlockColored.COLOR)).equals(EnumDyeColor.LIGHT_BLUE) || var2.getBlock().equals(Blocks.prismarine);
         case 4:
            return var2.getBlock().equals(Blocks.gold_block);
         default:
            return false;
         }
      }
   }

   public void lambda$onLoad$0() {
      this.method1(true);
   }

   public static boolean lambda$findTarget$14(BlockPos var0) {
      return field58.thePlayer.getDistance((double)var0.getX(), (double)((float)var0.getY() - field58.thePlayer.getEyeHeight()), (double)var0.getZ()) < 5.5D;
   }

   public static void method12() {
      if (Class263.field0 != null) {
         Class94 var0 = Class263.field0;
         Class263.field0 = null;
         Class263.bq_ = Minecraft.getMinecraft().thePlayer.inventory.currentItem;
         var0.method4();
         var0.method0();
         var0.method1();
         if (var0.method3()) {
            Minecraft.getMinecraft().playerController.resetBlockRemoving();
         }

      }
   }

   public void method4() {
      this.field3 = 0;
      this.field22 = 0;
      this.field11 = 0;
      if (this.field18.method1() && field58.thePlayer.getHeldItem() != null) {
         field58.playerController.sendUseItem(field58.thePlayer, field58.theWorld, field58.thePlayer.getHeldItem());
      }

   }

   public boolean method6(BlockPos var1) {
      IBlockState var2 = field58.theWorld.getBlockState(var1);
      return var2.getBlock() == Blocks.stone && ((EnumType)var2.getValue(BlockStone.VARIANT)).equals(EnumType.DIORITE_SMOOTH);
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.method44()) {
         if (this.ba_ != null) {
            AutoMaskModule.method0(this.ba_, Color.CYAN);
         }

         if (this.field13 != null) {
            AutoReadyModule.method0(this.field13, Color.GREEN);
         }

         if (this.field19 != null) {
            AutoReadyModule.method0(this.field19, Color.RED);
         }

      }
   }

   public void lambda$null$1() {
      this.method1(true);
   }

   public boolean method14() {
      ArrayList var1 = new ArrayList();

      for(int var2 = -5; var2 < 6; ++var2) {
         for(int var3 = -5; var3 < 6; ++var3) {
            for(int var4 = -5; var4 < 6; ++var4) {
               var1.add(new BlockPos(field58.thePlayer.posX + (double)var2, field58.thePlayer.posY + (double)var3, field58.thePlayer.posZ + (double)var4));
            }
         }
      }

      BlockPos var5 = this.ba_ != null ? this.ba_ : field58.thePlayer.getPosition();
      Optional var6 = var1.stream().filter(this::lambda$findTarget$13).filter(this::method4).filter(MithrilMacroModule::lambda$findTarget$14).filter(this::method2).min(Comparator.comparingDouble(this::lambda$findTarget$15));
      if (var6.isPresent()) {
         this.ba_ = (BlockPos)var6.get();
         this.field19 = null;
         this.field13 = this.method3((BlockPos)var6.get());
         this.method0(true);
      } else {
         var6 = var1.stream().filter(this::lambda$findTarget$16).filter(this::method7).filter(MithrilMacroModule::lambda$findTarget$17).filter(this::method2).min(Comparator.comparingDouble(this::lambda$findTarget$18));
         if (var6.isPresent()) {
            this.ba_ = (BlockPos)var6.get();
            this.field19 = null;
            this.field13 = this.method3((BlockPos)var6.get());
            this.method0(true);
         }
      }

      this.field22 = 0;
      return var6.isPresent();
   }

   public boolean method7(BlockPos var1) {
      IBlockState var2 = field58.theWorld.getBlockState(var1);
      return var2.getBlock().equals(Blocks.wool) && var2.getProperties().entrySet().stream().anyMatch(MithrilMacroModule::lambda$matchesAny$19) || var2.getBlock().equals(Blocks.prismarine) || var2.getBlock().equals(Blocks.stained_hardened_clay) || var2.getBlock().equals(Blocks.wool) && var2.getProperties().entrySet().stream().anyMatch(MithrilMacroModule::lambda$matchesAny$20) || this.method6(var1);
   }

   public static boolean lambda$findTarget$17(BlockPos var0) {
      return field58.thePlayer.getDistance((double)var0.getX(), (double)((float)var0.getY() - field58.thePlayer.getEyeHeight()), (double)var0.getZ()) < 5.5D;
   }
}
