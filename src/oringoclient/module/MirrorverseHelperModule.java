package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import map.Class207;
import map.Class361;
import map.Class447;
import map.Class496;
import map.Class510;
import map.Class94;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.S45PacketTitle.Type;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.event.Class217;
import oringo.event.Class468;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class MirrorverseHelperModule extends Module {
   public static final BlockPos field0;
   public final BooleanSetting cQ_ = new BooleanSetting("Auto Dance", true);
   public boolean field2;
   public static final Iterable cR_;
   public static final BlockPos field4;
   public static final BlockPos field5;
   public final BooleanSetting field6 = new BooleanSetting("Parkour", true);
   public final DoubleSetting field7 = new DoubleSetting("Ping", 150.0D, 0.0D, 1000.0D, 10.0D);
   public int field8 = 0;
   public final BooleanSetting field9 = new BooleanSetting("Lava Maze", true);
   public boolean field10;
   public static final BlockPos field11;
   public static final Iterable field12;
   public static final Iterable field13;
   public static final BlockPos field14;
   public static final BlockPos field15 = new BlockPos(-82, 51, -95);
   public BlockPos ba_;
   public final Class447 field17 = new Class447();
   public static final BlockPos field18 = new BlockPos(-105, 51, -105);
   public final DoubleSetting field19 = (DoubleSetting)(new DoubleSetting("Jump delay ticks", 0.0D, 0.0D, 10.0D, 1.0D)).method2("Modify if jumps too early");
   public boolean field20;
   public int field21;
   public boolean field3;

   public MirrorverseHelperModule() {
      super("Mirrorverse Helper", Category.skyblock, SubCategory.rift, "Useful features for the mirrorverse minigame\nUse the Render Barriers module for turbulator");
      this.method0((Setting[])(new Setting[]{this.field9, this.field6, this.cQ_, this.field7, this.field19}));
   }

   @SubscribeEvent
   public void b_(ClientTickEvent var1) {
      if (Class496.field21 && field58.thePlayer != null && this.field6.method1() && var1.phase == Phase.START) {
         Iterator var2 = field13.iterator();

         while(var2.hasNext()) {
            BlockPos var3 = (BlockPos)var2.next();
            BlockPos var4 = var3.subtract(field5);
            IBlockState var5 = field58.theWorld.getBlockState(var3);
            BlockPos var6 = new BlockPos(-123 + var4.getX(), 45 - var4.getY(), -92 + var4.getZ());
            IBlockState var7 = field58.theWorld.getBlockState(var6);
            if (var7.getBlock() != Blocks.air) {
               field58.theWorld.setBlockState(var6, var5);
            }
         }

      }
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (field58.thePlayer != null) {
         String var3;
         if (var1.field0 instanceof S29PacketSoundEffect) {
            S29PacketSoundEffect var5 = (S29PacketSoundEffect)var1.field0;
            var3 = var5.getSoundName();
            byte var4 = -1;
            switch(var3.hashCode()) {
            case 884831587:
               if (var3.equals("note.bassattack")) {
                  var4 = 0;
               }
               break;
            case 1579999903:
               if (var3.equals("note.hat")) {
                  var4 = 2;
               }
               break;
            case 2129177534:
               if (var3.equals("note.bd")) {
                  var4 = 1;
               }
            }

            switch(var4) {
            case 2:
               this.field17.o_();
            case 0:
            case 1:
            default:
            }
         } else if (var1.field0 instanceof S45PacketTitle) {
            S45PacketTitle var2 = (S45PacketTitle)var1.field0;
            if (var2.getType() == Type.SUBTITLE && var2.getMessage() != null) {
               var3 = ChatFormatting.stripFormatting(var2.getMessage().getUnformattedText());
               if (var3.endsWith("!")) {
                  this.field10 = var3.contains("Sneak");
                  this.field3 = var3.contains("Jump");
                  this.field2 = var3.contains("Punch!") && !var3.equals("Punch!");
                  this.field20 = false;
                  this.field8 = 5;
                  this.field21 = this.field19.method3();
               }

            }
         }
      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (Class496.field21 && field58.thePlayer != null && this.field9.method1() && var1.phase == Phase.START) {
         Iterator var2 = field12.iterator();

         while(var2.hasNext()) {
            BlockPos var3 = (BlockPos)var2.next();
            BlockPos var4 = var3.subtract(field15);
            field58.theWorld.setBlockState(field11.add(var4.getX(), var4.getY(), -var4.getZ()), field58.theWorld.getBlockState(var3));
         }

      }
   }

   static {
      field12 = BlockPos.getAllInBox(field15, field18);
      field11 = new BlockPos(-82, 51, -117);
      field5 = new BlockPos(-123, 47, -92);
      field0 = new BlockPos(-222, 56, -125);
      field13 = BlockPos.getAllInBox(field5, field0);
      field4 = new BlockPos(-263, 32, -108);
      field14 = new BlockPos(-265, 32, -106);
      cR_ = BlockPos.getAllInBox(field14, field4);
   }

   @SubscribeEvent
   public void method0(Class468 var1) {
      if (this.cQ_.method1() && Class496.field21 && this.ba_ != null && this.field8-- <= 0) {
         if (field58.thePlayer.posY < 36.0D && field58.thePlayer.posZ >= -108.0D && field58.thePlayer.posZ <= -105.0D && field58.thePlayer.posX >= -265.0D && field58.thePlayer.posX <= -262.0D) {
            if (this.field20) {
               if (this.field10) {
                  var1.method0(true);
               }

               if (this.field3 && this.field21-- < 0 && field58.thePlayer.onGround) {
                  field58.thePlayer.motionY = 0.41999998688697815D;
                  this.field3 = false;
               }
            }

            if (this.field2 && this.field17.a_((long)(930 - this.field7.method3()))) {
               Class361.method0((Class94)(new Class510(XRayModule.method0(ThystHiderModule.method3()), EnumFacing.UP)));
               this.field2 = false;
            }

         }
      }
   }

   public static float method0(boolean var0) {
      float var1 = 0.91F;
      if (var0) {
         var1 = Oringo.field9.thePlayer.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(Oringo.field9.thePlayer.posX), MathHelper.floor_double(Oringo.field9.thePlayer.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(Oringo.field9.thePlayer.posZ))).getBlock().slipperiness * 0.91F;
      }

      float var2 = 0.16277136F / (var1 * var1 * var1);
      float var3;
      if (var0) {
         var3 = Oringo.field9.thePlayer.getAIMoveSpeed() * var2;
      } else {
         var3 = Oringo.field9.thePlayer.jumpMovementFactor;
      }

      return var3;
   }

   public double method5() {
      double var1 = field58.thePlayer.isSneaking() ? 0.3D : 1.0D;
      return Math.min(1.0D, var1 * 0.2873D * (double)field58.thePlayer.capabilities.getWalkSpeed() * 10.0D);
   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      if (this.cQ_.method1() && Class496.field21) {
         if (field58.thePlayer.posY < 36.0D && field58.thePlayer.posZ >= -108.0D && field58.thePlayer.posZ <= -105.0D && field58.thePlayer.posX >= -265.0D && field58.thePlayer.posX <= -262.0D) {
            this.ba_ = null;
            Iterator var2 = cR_.iterator();

            while(var2.hasNext()) {
               BlockPos var3 = (BlockPos)var2.next();
               Block var4 = field58.theWorld.getBlockState(var3).getBlock();
               if (var4 == Blocks.stained_glass) {
                  this.ba_ = var3;
                  break;
               }
            }

            if (this.ba_ != null) {
               Vec3 var6 = (new Vec3(this.ba_)).addVector(0.5D, 0.0D, 0.5D);
               Vec3 var7 = var6.subtract(field58.thePlayer.getPositionVector());
               double var8 = this.method5();
               if (field58.thePlayer.onGround) {
                  var1.method2(Class207.method0(var7.xCoord, var8, -var8));
                  var8 -= Math.abs(var1.w_());
                  var1.method1(Class207.method0(var7.zCoord, var8, -var8));
               }

               this.field20 = var7.xCoord == 0.0D && var7.zCoord == 0.0D;
            }
         }
      }
   }
}
