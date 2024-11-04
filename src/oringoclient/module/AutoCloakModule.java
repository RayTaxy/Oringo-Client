package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class361;
import map.Class376;
import map.Class386;
import map.Class402;
import map.Class426;
import map.Class447;
import map.Class94;
import map.Class95;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.EXTFramebufferObject;
import oringo.Oringo;
import oringo.command.ClipCommand;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class AutoCloakModule extends Module {
   public static final Pattern field0 = Pattern.compile("(.*)s [1-8] hits", 2);
   public boolean bp_;
   public final DoubleSetting field2 = new DoubleSetting("Distance", 30.0D, 1.0D, 64.0D, 1.0D, this::lambda$new$2);
   public final DoubleSetting field3 = new DoubleSetting("Health", 30.0D, 1.0D, 100.0D, 1.0D, this::lambda$new$0);
   public final BooleanSetting field4 = new BooleanSetting("Yang Glyph", true);
   public final DoubleSetting field5 = new DoubleSetting("Time", 1.0D, 1.0D, 8.0D, 1.0D, this::lambda$new$1);
   public final Class447 field6 = new Class447();
   public final BooleanSetting field7 = new BooleanSetting("Pillar", true);
   public final EnumSetting field8 = new EnumSetting("Yang Mode", this::lambda$new$3, "Cloak", new String[]{"Cloak", "Coat"});
   public final BooleanSetting field9 = new BooleanSetting("From inventory", false);
   public final BooleanSetting field10 = new BooleanSetting("On health", false);
   public boolean field11;
   public int bq_;

   public AutoCloakModule() {
      super("Auto Cloak", Category.skyblock, SubCategory.slayer, "Cloaks on blaze pillar or yang glyph");
      this.method0((Setting[])(new Setting[]{this.field4, this.field8, this.field7, this.field5, this.field2, this.field10, this.field3, this.field9}));
   }

   public Boolean lambda$new$0() {
      return !this.field10.method1();
   }

   public Boolean lambda$new$1() {
      return !this.field7.method1();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      boolean var2 = false;
      int var3 = Class95.method0(AutoCloakModule::lambda$onTick$4);
      if (var3 != -1 && (this.field9.method1() || var3 >= 36)) {
         if (this.field7.method1() && Class376.method0("Slay the boss!") && Class376.method0("Inferno Demonlord")) {
            Iterator var4 = field58.theWorld.getEntities(EntityArmorStand.class, AutoCloakModule::lambda$onTick$5).iterator();

            while(var4.hasNext()) {
               EntityArmorStand var5 = (EntityArmorStand)var4.next();
               if (var5.getDistanceSqToEntity(field58.thePlayer) < this.field2.method0() * this.field2.method0()) {
                  Matcher var6 = field0.matcher(ChatFormatting.stripFormatting(var5.getDisplayName().getUnformattedText()));
                  if (var6.find()) {
                     String var7 = var6.group(1);
                     int var8 = Integer.parseInt(var7);
                     if ((double)var8 <= this.field5.method0()) {
                        var2 = true;
                        break;
                     }
                  }
               }
            }
         }

         if (this.field10.method1() && field58.thePlayer.getHealth() / field58.thePlayer.getMaxHealth() < this.field3.method1() / 100.0F) {
            var2 = true;
         }

         if (this.field4.method1() && !this.field6.a_(500L)) {
            if (this.field8.method0(0)) {
               var2 = true;
            } else if (RenderChunkBoundsModule.method5() && !this.field11) {
               this.bq_ = Class95.method0(AutoCloakModule::lambda$onTick$6);
               if (this.bq_ != -1 && this.bq_ != 6) {
                  this.field11 = true;
                  ServerRotationsModule.method0(this.bq_, 0);
                  ServerRotationsModule.method0(6, 0);
                  ServerRotationsModule.method0(this.bq_, 0);
               }
            }
         } else if (this.field11 && RenderChunkBoundsModule.method5()) {
            this.field11 = false;
            if ("MITHRIL_COAT".equals(BlockHitModule.method0(field58.thePlayer.inventoryContainer.getSlot(6).getStack()))) {
               ServerRotationsModule.method0(this.bq_, 0);
               ServerRotationsModule.method0(6, 0);
               ServerRotationsModule.method0(this.bq_, 0);
            }
         }

         if (this.bp_ != var2 && Class361.method0((Class94)(new Class402(var3, AutoCloakModule::lambda$onTick$7)))) {
            this.bp_ = var2;
         }

      }
   }

   public static boolean lambda$onTick$5(EntityArmorStand var0) {
      return true;
   }

   public static boolean lambda$onTick$6(ItemStack var0) {
      return "MITHRIL_COAT".equals(BlockHitModule.method0(var0));
   }

   public static boolean lambda$onTick$4(ItemStack var0) {
      return "WITHER_CLOAK".equals(BlockHitModule.method0(var0));
   }

   public Boolean lambda$new$3() {
      return !this.field4.method1();
   }

   public static boolean method0(int var0) {
      while(var0 > 0) {
         BlockPos var1 = new BlockPos(ClipCommand.field9.thePlayer.posX, (double)var0, ClipCommand.field9.thePlayer.posZ);
         IBlockState var2 = ClipCommand.field9.theWorld.getBlockState(var1);
         if (!var2.getBlock().isPassable(ClipCommand.field9.theWorld, var1)) {
            return false;
         }

         --var0;
      }

      return true;
   }

   public static void lambda$onTick$7(ItemStack var0) {
      if (var0 != null) {
         Class426.method10();
         method3(new C08PacketPlayerBlockPlacement(var0));
      }
   }

   public static void method0(Framebuffer var0) {
      EXTFramebufferObject.glDeleteRenderbuffersEXT(var0.depthBuffer);
      int var1 = EXTFramebufferObject.glGenRenderbuffersEXT();
      EXTFramebufferObject.glBindRenderbufferEXT(36161, var1);
      EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, Class386.field0.displayWidth, Class386.field0.displayHeight);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, var1);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, var1);
   }

   public Boolean lambda$new$2() {
      return !this.field7.method1();
   }

   public static MovingObjectPosition method0(BlockPos var0) {
      IBlockState var1 = Oringo.field9.theWorld.getBlockState(var0);
      Block var2 = var1.getBlock();
      var2.setBlockBoundsBasedOnState(Oringo.field9.theWorld, var0);
      AxisAlignedBB var3 = new AxisAlignedBB((double)var0.getX() + var2.getBlockBoundsMinX(), (double)var0.getY() + var2.getBlockBoundsMinY(), (double)var0.getZ() + var2.getBlockBoundsMinZ(), (double)var0.getX() + var2.getBlockBoundsMaxX(), (double)var0.getY() + var2.getBlockBoundsMaxY(), (double)var0.getZ() + var2.getBlockBoundsMaxZ());
      Vec3 var4 = BlazeSwapperModule.method0(var3);
      EnumFacing var5 = FDSwapperModule.method0(var0, var4);
      return var5 == null ? null : new MovingObjectPosition(var4, var5, var0);
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.field4.method1() && var1.field0 instanceof S29PacketSoundEffect) {
         S29PacketSoundEffect var2 = (S29PacketSoundEffect)var1.field0;
         if (var2.getSoundName().equals("mob.guardian.curse") && Class376.method0("Voidgloom Seraph") && Class376.method0("Slay the boss!") && var2.getVolume() == 0.3F && var2.getPitch() > 1.2F) {
            this.field6.o_();
         }

      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.bp_ = false;
   }
}
