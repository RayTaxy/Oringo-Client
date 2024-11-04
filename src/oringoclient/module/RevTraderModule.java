package oringo.module;

import map.Class175;
import map.Class237;
import map.Class29;
import map.Class398;
import map.Class417;
import map.Class447;
import map.Class503;
import map.Class56;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.MessageCommand;
import oringo.event.Class210;
import oringo.event.Class468;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class RevTraderModule extends Module {
   public static final DoubleSetting field0 = new DoubleSetting("CPS", 10.0D, 1.0D, 20.0D, 1.0D);
   public int Y_ = 9;
   public static final DoubleSetting field2 = new DoubleSetting("Fov", 90.0D, 30.0D, 360.0D, 1.0D);
   public static final BooleanSetting field3 = new BooleanSetting("Sneak", true);
   public static final StringSetting field4 = new StringSetting("Weapon", "Axe of the Shredded");
   public static final StringSetting field5 = new StringSetting("Magic find weapon");
   public static final DoubleSetting field6 = new DoubleSetting("Range", 4.0D, 1.0D, 6.0D, 0.1D);
   public static final BooleanSetting field7 = new BooleanSetting("Move", true);
   public Vec3 field8;
   public boolean field9 = false;
   public static final Class447 field10 = new Class447();
   public static EntityZombie field11;

   @SubscribeEvent
   public void method0(Class468 var1) {
      if (this.method44() && !(field58.currentScreen instanceof GuiContainer)) {
         var1.method0(field3.method1());
         if (field7.method1()) {
            if (this.Y_ != 0 || !this.field9) {
               ++this.Y_;
               this.Y_ %= 12;
               var1.method0(0.0F).method1(this.Y_ >= 3 && this.Y_ <= 8 ? -1.0F : 1.0F);
            }
         }
      }
   }

   public static boolean lambda$findTarget$0(EntityZombie var0) {
      return (double)var0.getDistanceToEntity(field58.thePlayer) < field6.method0() && !var0.isInvisible() && var0.getHealth() > 0.0F && Class417.method0(MessageCommand.method0((Entity)var0), (float)field2.method0()) && Class237.method0(var0);
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void method0(ClientChatReceivedEvent var1) {
      if (this.method44()) {
         String var2 = var1.message.getUnformattedText().trim();
         if (var2.startsWith("LOOT SHARE")) {
            this.field9 = true;
            this.field8 = field58.thePlayer.getPositionVector();
            this.Y_ = 0;
            if (!field5.F_()) {
               int var3 = GrottoNotificationModule.o_(field5.method1());
               if (var3 != -1) {
                  RenderBarriersModule.method0(var3);
               }
            }
         } else if (var2.equalsIgnoreCase("slayer quest complete!")) {
            this.field9 = false;
         }

      }
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.method44() && !this.field9) {
         field11 = Class56.method0();
         if (field11 != null) {
            int var2 = GrottoNotificationModule.o_(field4.method1());
            if (var2 != -1) {
               RenderBarriersModule.method0(var2);
            }

            var1.method0(Class29.method0(Class175.method0(), Class398.method0(field11, 0.05F), 60.0F));
         }
      } else {
         field11 = null;
      }
   }

   public RevTraderModule() {
      super("Rev Trader", Category.skyblock, SubCategory.slayer, "Trades bosses during Derpy");
      this.method0((Setting[])(new Setting[]{field6, field0, field2, field4, field5, field3, field7}));
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      Vec3 var2 = field58.thePlayer.getPositionEyes(1.0F);
      Vec3 var3 = AutoFrozilleModule.method0(Class503.field0, Class503.field1);
      Vec3 var4 = var2.addVector(var3.xCoord * 6.0D, var3.yCoord * 6.0D, var3.zCoord * 6.0D);
      if (field10.method0(1000.0D / field0.method0(), true) && field11.getEntityBoundingBox().calculateIntercept(var2, var4) != null) {
         field58.thePlayer.swingItem();
         field58.playerController.attackEntity(field58.thePlayer, field11);
      }

   }
}
