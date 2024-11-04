package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import map.Class253;
import map.Class265;
import map.Class362;
import map.Class374;
import map.Class376;
import map.Class506;
import map.Class528;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Specials.Pre;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class PlayerESPModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Tracers", false);
   public final BooleanSetting aR_ = new BooleanSetting("Chams", false);
   public final BooleanSetting field2 = new BooleanSetting("Box", false);
   public final BooleanSetting field3 = new BooleanSetting("2D", false);
   public final BooleanSetting field4 = (BooleanSetting)(new BooleanSetting("HP", false)).method2("Changes the color depending on the player's health");
   public final BooleanSetting field5 = new BooleanSetting("Outline", true);

   public PlayerESPModule() {
      super("PlayerESP", Category.render, SubCategory.world, "Shows players through walls");
      this.method0((Setting[])(new Setting[]{this.field5, this.field3, this.aR_, this.field2, this.field0, this.field4}));
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field3.method1() || this.field2.method1() || this.field0.method1() || this.field5.method1()) {
         Iterator var2 = field58.theWorld.playerEntities.iterator();

         while(var2.hasNext()) {
            EntityPlayer var3 = (EntityPlayer)var2.next();
            if (this.method0(var3)) {
               int var4 = (int)((double)Math.max((1.0F - var3.getHealth() / var3.getMaxHealth()) * 255.0F, 0.0F) * 1.5D);
               int var5 = (int)Math.max(var3.getHealth() / var3.getMaxHealth() * 255.0F, 0.0F);
               Color var6 = !this.field4.method1() ? Class362.field7.method0((float)var3.getEntityId()) : new Color(Math.min(var4, 255), Math.min(var5, 255), 0);
               boolean var7 = Class210.method0((Entity)var3);
               if (this.field5.method1()) {
                  ScaffoldModule.method0(var3, var6);
               }

               if (this.field3.method1() && var7) {
                  Class374.method0(var3, var1.partialTicks, 1.0F, var6);
               }

               if (this.field2.method1() && var7) {
                  AutoIceFillModule.method0(var3, var1.partialTicks, var6);
               }

               if (this.field0.method1()) {
                  Class528.method0(var3, var1.partialTicks, 1.0F, var6);
               }
            }
         }

      }
   }

   public boolean method0(EntityPlayer var1) {
      return Class506.method0((Entity)var1) && var1.getHealth() > 0.0F && !var1.isDead && var1 instanceof EntityOtherPlayerMP;
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST,
      receiveCanceled = true
   )
   public void method0(Pre var1) {
      if (var1.entity instanceof EntityOtherPlayerMP && this.aR_.method1()) {
         Class265.method0();
      }
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(net.minecraftforge.client.event.RenderLivingEvent.Pre var1) {
      if (var1.entity instanceof EntityOtherPlayerMP && this.aR_.method1()) {
         Class376.method0();
      }
   }

   public static void method0(String var0, int var1) {
      if (Oringo.field9.thePlayer != null) {
         Oringo.field9.thePlayer.addChatMessage(new Class253.Class0(var0, var1));
      }

   }
}
