package oringo.module;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.util.Comparator;
import java.util.List;
import map.Class361;
import map.Class402;
import map.Class426;
import map.Class447;
import map.Class496;
import map.Class527;
import map.Class94;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.Clip3DCommand;
import oringo.command.MessageCommand;
import oringo.command.ToggleCommand;
import oringo.event.Class210;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoIceSprayModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Bats", true);
   public static final DoubleSetting L_ = new DoubleSetting("Spray delay", 5.0D, 1.0D, 5.0D, 0.1D);
   public static final BooleanSetting field2 = new BooleanSetting("Fels", true);
   public static final DoubleSetting field3 = new DoubleSetting("Fov", 360.0D, 30.0D, 360.0D, 5.0D);
   public static final BooleanSetting field4 = new BooleanSetting("Minis", true);
   public static final BooleanSetting field5 = new BooleanSetting("Wither Lords", true);
   public static final DoubleSetting field6 = new DoubleSetting("Distance", 6.0D, 1.0D, 7.0D, 0.1D);
   public static final BooleanSetting field7 = new BooleanSetting("From inventory", true);
   public static final BooleanSetting field8 = new BooleanSetting("Through walls", false);
   public final Class447 field9 = new Class447();
   public static final BooleanSetting field10 = new BooleanSetting("Mimic", true);
   public static final BooleanSetting field11 = new BooleanSetting("Blood Minis", true);

   @SubscribeEvent(
      priority = EventPriority.LOW
   )
   public void method0(Class210.Class1 var1) {
      if (Class496.field5 && !Class496.field3 && this.field9.a_((long)L_.method3() * 1000L)) {
         int var2 = Clip3DCommand.method2();
         if (var2 != -1 && (var2 >= 36 || field7.method1())) {
            List var3 = field58.theWorld.getEntities(EntityLivingBase.class, ToggleCommand::a_);
            if (!var3.isEmpty()) {
               if (field4.method1() && field0.method1()) {
                  var3.sort(Comparator.comparingInt(AutoIceSprayModule::lambda$onMotion$0));
               }

               EntityLivingBase var4 = (EntityLivingBase)var3.get(0);
               if (Class361.method0((Class94)(new Class402(var2, AutoIceSprayModule::lambda$onMotion$1)))) {
                  var1.method0(MessageCommand.method0((Entity)var4));
                  this.field9.o_();
               }

            }
         }
      }
   }

   public static void b_(String var0) {
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(var0), (ClipboardOwner)null);
   }

   public static void method0(float var0, float var1) {
      AutoQuizModule.method0(var0, var1, Class527.field2.method0());
   }

   public AutoIceSprayModule() {
      super("Auto Ice Spray", Category.dungeon, SubCategory.main);
      this.method0((Setting[])(new Setting[]{field7, field8, L_, field6, field3, field4, field2, field11, field0, field10, field5}));
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.type == ElementType.TEXT && field58.thePlayer != null && !this.field9.method0(L_.method0() * 1000.0D)) {
         int var2 = Clip3DCommand.method2();
         if (var2 != -1) {
            ThornAimbotModule.method0(field58.thePlayer.inventoryContainer.getSlot(var2).getStack(), (float)this.field9.method2() / (L_.method1() * 1000.0F));
         }
      }
   }

   public static void lambda$onMotion$1(ItemStack var0) {
      if (var0 != null) {
         Class426.method10();
         method3(new C08PacketPlayerBlockPlacement(var0));
      }
   }

   public static int lambda$onMotion$0(EntityLivingBase var0) {
      return AutoIceFillModule.method0((Entity)var0) ? 1 : 0;
   }

   public static boolean lambda$getIceSpray$2(ItemStack var0) {
      return "ICE_SPRAY_WAND".equals(BlockHitModule.method0(var0));
   }

   public static void method0(AxisAlignedBB var0, Color var1) {
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableBlend();
      GL11.glLineWidth(2.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      AutoBlazeModule.method0(var1);
      RenderGlobal.drawSelectionBoundingBox(var0.offset(-Oringo.field9.getRenderManager().viewerPosX, -Oringo.field9.getRenderManager().viewerPosY, -Oringo.field9.getRenderManager().viewerPosZ));
      AutoCloseModule.method5();
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
   }
}
