package oringo.module;

import com.google.common.collect.Sets;
import java.awt.Color;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import map.Class163;
import map.Class291;
import map.Class361;
import map.Class447;
import map.Class496;
import map.Class518;
import map.Class527;
import map.Class94;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.SelfBanCommand;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoIcePathModule extends Module {
   public final Class447 field0 = new Class447();
   public EntitySilverfish cT_;
   public BlockPos field2;
   public EnumFacing field3;
   public final DoubleSetting field4 = new DoubleSetting("Distance", 5.0D, 2.0D, 6.0D, 0.1D);

   public static void method5() {
      try {
         Method var0;
         try {
            var0 = Minecraft.class.getDeclaredMethod("func_147116_af");
         } catch (NoSuchMethodException var2) {
            var0 = Minecraft.class.getDeclaredMethod("clickMouse");
         }

         var0.setAccessible(true);
         var0.invoke(Minecraft.getMinecraft());
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public static boolean lambda$onUpdate$1(TileEntity var0) {
      return var0 instanceof TileEntityChest && ShortbowTriggerbotModule.method0(var0.getPos(), "Ice Path");
   }

   public AutoIcePathModule() {
      super("Auto Ice Path", Category.dungeon, SubCategory.puzzle);
      this.method0((Setting[])(new Setting[]{this.field4}));
   }

   public static boolean lambda$onUpdate$0(EntitySilverfish var0) {
      return var0.posY == 67.0D;
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (Class496.field5 && SecretAuraModule.w_("Ice Path")) {
         List var2 = field58.theWorld.getEntities(EntitySilverfish.class, AutoIcePathModule::lambda$onUpdate$0);
         if (var2.isEmpty()) {
            this.cT_ = null;
         } else {
            this.cT_ = (EntitySilverfish)var2.get(0);
            BlockPos var3 = new BlockPos(this.cT_);
            if (this.cT_.posX == this.cT_.prevPosX && this.cT_.posZ == this.cT_.prevPosZ) {
               if (!var3.equals(this.field2)) {
                  Optional var4 = field58.theWorld.loadedTileEntityList.stream().filter(AutoIcePathModule::lambda$onUpdate$1).findFirst();
                  if (!var4.isPresent()) {
                     return;
                  }

                  TileEntityChest var5 = (TileEntityChest)var4.get();
                  IBlockState var6 = field58.theWorld.getBlockState(var5.getPos());
                  EnumFacing var7 = (EnumFacing)var6.getValue(BlockChest.FACING);
                  this.field2 = var3;
                  var7 = SelfBanCommand.method0(var3, Sets.newHashSet(), var5.getPos().offset(var7.rotateYCCW()));
                  if (var7 == null) {
                     method2("Unable to find a path. Try using the roomclear command!");
                     this.field2 = null;
                  }
               }

               if (this.field3 != null && (double)this.cT_.getDistanceToEntity(field58.thePlayer) < this.field4.method0()) {
                  var1.method1((float)this.field3.getHorizontalIndex() * 90.0F);
                  if (this.field0.a_(150L) && Class361.method0((Class94)(new Class291(this.cT_)))) {
                     this.field0.o_();
                  }

               }
            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field2 = null;
      this.field3 = null;
      this.cT_ = null;
   }

   public static void method0(TileEntity var0, Color var1) {
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableBlend();
      GL11.glLineWidth(2.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      AutoBlazeModule.method0(var1);
      RenderGlobal.drawSelectionBoundingBox(var0.getRenderBoundingBox().offset(-Oringo.field9.getRenderManager().viewerPosX, -Oringo.field9.getRenderManager().viewerPosY, -Oringo.field9.getRenderManager().viewerPosZ));
      AutoCloseModule.method5();
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
   }

   public static float method6() {
      return Class163.method0(Class527.field6.getHealth() / Class518.method0(Class527.field6), 1.0F, 0.0F);
   }
}
