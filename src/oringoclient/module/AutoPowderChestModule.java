package oringo.module;

import java.util.HashMap;
import java.util.Iterator;
import map.Class228;
import map.Class282;
import map.Class296;
import map.Class361;
import map.Class447;
import map.Class496;
import map.Class94;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.ReplyCommand;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class AutoPowderChestModule extends Module {
   public final EnumSetting field0 = (EnumSetting)(new EnumSetting("Mode", "Right Click", new String[]{"Legit", "Blatant", "Right Click"})).method2("Legit mode requires you to look at the chest");
   public Class228 bO_ = null;
   public Class228 field2 = null;
   public final HashMap field3 = new HashMap();
   public final Class447 field4 = new Class447();
   public final EnumSetting field5 = new EnumSetting("Swap", this::lambda$new$0, "None", new String[]{"None", "Mithril", "Gemstone"});

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (Class496.field1 && !this.field0.method0(2)) {
         if (var1.field0 instanceof S2APacketParticles && ((S2APacketParticles)var1.field0).getParticleType().equals(EnumParticleTypes.CRIT)) {
            Class228 var2 = new Class228(((S2APacketParticles)var1.field0).getXCoordinate(), ((S2APacketParticles)var1.field0).getYCoordinate(), ((S2APacketParticles)var1.field0).getZCoordinate());
            if (field58.theWorld.loadedTileEntityList.stream().noneMatch(this::lambda$onPacket$3)) {
               return;
            }

            if (this.field2 == null || this.field2.method1().equals(var2.method1()) || this.field4.a_(4000L)) {
               this.bO_ = var2;
               this.field4.o_();
            }
         }

         if (var1.field0 instanceof S02PacketChat && ((S02PacketChat)var1.field0).getChatComponent().getUnformattedText().equals("You have successfully picked the lock on this chest!")) {
            this.bO_ = null;
            this.field2 = null;
         }

      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.b_();
   }

   public AutoPowderChestModule() {
      super("Auto Powder Chest", Category.skyblock, SubCategory.mining, "Automatically opens powder chests");
      this.method0((Setting[])(new Setting[]{this.field0, this.field5}));
   }

   public void b_() {
      this.bO_ = null;
      this.field2 = null;
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (!this.field0.method0(2)) {
         if (this.field4.a_(4000L) || this.field0.method0("Legit") && field58.objectMouseOver != null && field58.objectMouseOver.typeOfHit == MovingObjectType.BLOCK && field58.theWorld.getBlockState(field58.objectMouseOver.getBlockPos()).getBlock() != Blocks.chest) {
            this.bO_ = null;
         }

         if (this.bO_ != null) {
            var1.method0(ReplyCommand.method0(this.bO_.method6()));
         }

      }
   }

   public boolean lambda$onUpdate$2(ItemStack var1) {
      return CreateNewkeybindModule.method0(var1, this.field5.method4() + " Powder", false);
   }

   public static boolean lambda$onUpdate$1(Class296 var0) {
      return var0.a_(10L);
   }

   public Boolean lambda$new$0() {
      return !this.field0.method0(2);
   }

   public static BlockPos method0(BlockPos var0) {
      int var1 = 100;

      while(Oringo.field9.theWorld.getBlockState(var0).getBlock() == Blocks.air) {
         var0 = var0.down();
         if (var1-- == 0) {
            break;
         }
      }

      return var0;
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (Class496.field1 && this.field0.method0(2) && !field58.playerController.getIsHittingBlock()) {
         this.field3.values().removeIf(AutoPowderChestModule::lambda$onUpdate$1);
         Iterator var2 = field58.theWorld.loadedTileEntityList.iterator();

         while(var2.hasNext()) {
            TileEntity var3 = (TileEntity)var2.next();
            if (var3 instanceof TileEntityChest && !this.field3.containsKey(var3.getPos()) && field58.thePlayer.getDistanceSq(var3.getPos()) < 30.0D && ((TileEntityChest)var3).lidAngle == 0.0F) {
               this.field3.put(var3.getPos(), new Class296());
               int var4 = this.field5.method0(0) ? field58.thePlayer.inventory.currentItem : TrailModule.method0(this::lambda$onUpdate$2);
               if (var4 == -1) {
                  return;
               }

               Class361.method0((Class94)(new Class282(var4, var3.getPos(), new Vec3(var3.getPos()), EnumFacing.DOWN)));
            }
         }

      }
   }

   public boolean lambda$onPacket$3(Class228 var1, TileEntity var2) {
      return (this.field0.method0(1) || field58.objectMouseOver != null && var2.getPos().equals(field58.objectMouseOver.getBlockPos())) && var2 instanceof TileEntityChest && var1.method0(var2.getPos()) < 1.0D;
   }
}
