package oringo.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import map.Class119;
import map.Class196;
import map.Class198;
import map.Class229;
import map.Class500;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0BPacketEntityAction.Action;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.event.Class468;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class SneakModule extends Module {
   public static final EnumSetting field0 = new EnumSetting("Mode", "NCP", new String[]{"NCP", "Vanilla", "Fake"});
   public static final BooleanSetting O_ = new BooleanSetting("Always", false);
   public static final DoubleSetting field2 = new DoubleSetting("Speed", 1.0D, 0.0D, 1.0D, 0.1D);

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (this.method44() && field58.thePlayer != null && field58.thePlayer.isSneaking()) {
         if (field0.method0(2) && var1.field0 instanceof C0BPacketEntityAction && (((C0BPacketEntityAction)var1.field0).getAction() == Action.START_SNEAKING || ((C0BPacketEntityAction)var1.field0).getAction() == Action.STOP_SNEAKING)) {
            var1.setCanceled(true);
         }

      }
   }

   public SneakModule() {
      super("Sneak", Category.movement, SubCategory.movement, "Allows you to modify the sneaking speed");
      this.method0((Setting[])(new Setting[]{field0, field2, O_}));
      EnumSetting var10001 = field0;
      this.method0((Class500)(var10001::method4));
   }

   @SubscribeEvent
   public void method0(Class468 var1) {
      if (this.method44()) {
         if (O_.method1()) {
            var1.method0(!field58.gameSettings.keyBindSneak.isKeyDown());
         }

         if (var1.I_()) {
            var1.method0((float)((double)var1.method2() / 0.3D * field2.method0())).method1((float)((double)var1.G_() / 0.3D * field2.method0()));
         }
      }

   }

   public static void method1() {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableTexture2D();
      SpeedModule.method0(0.7F, 3.0F);
      GlStateManager.translate(0.0F, 0.0F, -999.0F);
      GlStateManager.bindTexture(Class196.field6.framebufferTexture);
      CameraModule.method5();
      GlStateManager.translate(0.0F, 0.0F, 999.0F);
      SprintModule.method8();
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (field58.thePlayer.isSneaking()) {
         if (field0.method0(0)) {
            method3(new C0BPacketEntityAction(field58.thePlayer, Action.STOP_SNEAKING));
         }

      }
   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
      if (field58.thePlayer.isSneaking()) {
         if (field0.method0(0)) {
            method3(new C0BPacketEntityAction(field58.thePlayer, Action.STOP_SNEAKING));
         }

      }
   }

   public static void method2() {
      MapData var0 = Class198.method0();
      if (Class119.field1 && var0 != null && !var0.mapDecorations.isEmpty()) {
         Class229.field5.o_();
         List var1 = SpeedModule.method3();
         ArrayList var2 = new ArrayList();
         if (var1 != null) {
            var1 = Class119.field6.sortedCopy(SpeedModule.method3());
            int var3 = 0;

            Iterator var4;
            for(var4 = var1.iterator(); var4.hasNext(); ++var3) {
               NetworkPlayerInfo var5 = (NetworkPlayerInfo)var4.next();
               var0.mapDecorations.forEach(Class119::lambda$updatePlayers$0);
            }

            var4 = var2.iterator();

            while(var4.hasNext()) {
               Class229 var8 = (Class229)var4.next();
               Iterator var6 = Class119.field5.iterator();

               while(var6.hasNext()) {
                  Class229 var7 = (Class229)var6.next();
                  if (var8.method45().equals(var7.method45())) {
                     var8.method0(var7.method7());
                  }
               }
            }

            Class119.field5.clear();
            Class119.field5.addAll(var2);
         }
      }
   }

   public static boolean method0(String var0, String var1) {
      return var0.startsWith(var1);
   }
}
