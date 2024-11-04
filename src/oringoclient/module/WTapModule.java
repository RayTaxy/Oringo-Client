package oringo.module;

import com.google.common.collect.Iterables;
import map.Class500;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class WTapModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Bow", true, this::lambda$new$0);
   public boolean am_ = false;
   public final EnumSetting field2 = new EnumSetting("Mode", "Legit", new String[]{"Legit"});

   public Boolean lambda$new$0() {
      return !this.field2.method0(-1);
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.am_) {
         var1.method0(false);
      }

      this.am_ = false;
   }

   public static Object method0(Iterable var0) {
      return Iterables.getFirst(var0, (Object)null);
   }

   public WTapModule() {
      super("W Tap", Category.combat, SubCategory.combat, "Makes enemies take more knockback");
      this.method0((Setting[])(new Setting[]{this.field2, this.field0}));
      EnumSetting var10001 = this.field2;
      this.method0((Class500)(var10001::method4));
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      EntityPlayerSP var2 = field58.thePlayer;
      if (var1.field0 instanceof C02PacketUseEntity && ((C02PacketUseEntity)var1.field0).getAction() == Action.ATTACK) {
         boolean var3 = var2.fallDistance > 0.0F && !var2.onGround && !var2.isOnLadder() && !var2.isInWater() && !var2.isPotionActive(Potion.blindness) && var2.ridingEntity == null && ((C02PacketUseEntity)var1.field0).getEntityFromWorld(field58.theWorld) instanceof EntityLivingBase;
         if (this.field2.method0(0) && var3) {
            this.am_ = true;
         }
      }

   }

   public static void method3() {
      GlStateManager.disableDepth();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.depthMask(true);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
   }
}
