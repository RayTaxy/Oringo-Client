package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import map.Class24;
import map.Class496;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C03PacketPlayer.C06PacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.Command;
import oringo.command.HelpCommand;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AutoMiddleModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Reload Chunks", false);
   public static boolean aJ_;
   public static int field2 = -1;

   public void b_() {
      aJ_ = false;
   }

   public static void method0(double var0, double var2, double var4) {
      GL11.glTranslated(var0, var2, 0.0D);
      GL11.glScaled(var4, var4, var4);
      GL11.glTranslated(-var0, -var2, 0.0D);
   }

   public static void method0(Command var0) {
      String[] var1 = var0.method5();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         String var4 = var1[var3];
         if (!Class24.field0.containsKey(var4.toLowerCase())) {
            Class24.field0.put(var4.toLowerCase(), var0);
         }
      }

      HelpCommand.field0.put(var0.method5()[0].toLowerCase(), var0);
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (field58.thePlayer != null && (Class496.field13 || Oringo.cV_)) {
         if ((var1.field0 instanceof C03PacketPlayer || var1.field0 instanceof C0CPacketInput) && Class496.field14 && (aJ_ || field58.thePlayer.isRiding())) {
            var1.method9();
            if (!aJ_) {
               method2(new C06PacketPlayerPosLook(54.0D, 65.0D, 76.0D, 0.0F, 0.0F, false));
               aJ_ = true;
            }
         }

      }
   }

   public AutoMiddleModule() {
      super("Auto Middle", Category.dungeon, SubCategory.floor7, "Teleports you to the necron platform");
      this.method0((Setting[])(new Setting[]{this.field0}));
   }

   public static boolean method0(Entity var0) {
      if (!(var0 instanceof EntityOtherPlayerMP)) {
         return false;
      } else {
         EntityLivingBase var1 = (EntityLivingBase)var0;
         return ChatFormatting.stripFormatting(var0.getDisplayName().getUnformattedText()).startsWith("[NPC]") || var0.getUniqueID().version() == 2 && var1.getHealth() == 20.0F && var1.getMaxHealth() == 20.0F;
      }
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      --field2;
      if (field2 == 0 && this.field0.method1()) {
         field58.renderGlobal.loadRenderers();
      }

      if (aJ_) {
         var1.method0(2137.0D, 2137.0D, 2137.0D);
         field2 = 20;
         aJ_ = false;
      }

   }
}
