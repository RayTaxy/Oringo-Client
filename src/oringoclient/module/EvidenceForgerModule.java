package oringo.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class447;
import map.Class7;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import oringo.event.Class189;
import oringo.mixin.S3EPacketTeamsAccessor;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class EvidenceForgerModule extends Module {
   public final Class447 field0 = new Class447();
   public final Pattern field1 = Pattern.compile("[0-9][0-9]/[0-9][0-9]/[0-9][0-9]");
   public final EnumSetting field2 = new EnumSetting("Mode", "Rotation", new String[]{"Rotation", "Clone"});
   public final StringSetting field3 = new StringSetting("Time", "");
   public final StringSetting field4 = new StringSetting("Top SB", "");
   public final StringSetting field5 = new StringSetting("Ban ID", "");
   public final String field6 = "§cYou are temporarily banned for §f%s §cfrom this server!\n\n§7Reason: §f%s.\n§7Find out more: §b§nhttps://www.hypixel.net/appeal\n\n§7Ban ID: §f%s\n§7Sharing your Ban ID may affect the processing of your appeal!";
   public boolean field7 = true;
   public final StringSetting field8 = new StringSetting("Reason", "");
   public final DoubleSetting field9 = new DoubleSetting("Delay (s)", 5.0D, 0.0D, 20.0D, 1.0D);

   public static int method5() {
      int var0 = Class7.field2.getScaledHeight();
      return var0 - Mouse.getY() * var0 / Class7.field1.displayHeight - 1;
   }

   public EvidenceForgerModule() {
      super("Evidence Forger", Category.skyblock, SubCategory.other);
      this.method0(new Setting[]{this.field2, this.field9, this.field3, this.field8, this.field5, this.field4});
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S3EPacketTeams) {
         S3EPacketTeams var2 = (S3EPacketTeams)var1.field0;
         if (var2.getPrefix().length() != 0 && var2.getColor() == 15) {
            Matcher var3 = this.field1.matcher(var2.getPrefix());
            if (var3.find()) {
               ((S3EPacketTeamsAccessor)var2).setScoreName("§7" + this.field4.method1());
               ((S3EPacketTeamsAccessor)var2).setScoreName2("");
            }
         }
      }

   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START) {
         if (this.field7 && Keyboard.isKeyDown(41)) {
            if (this.field2.method4().equals("Rotation")) {
               EntityPlayerSP var10000 = field58.thePlayer;
               var10000.rotationYaw -= 20.0F;
               var10000 = field58.thePlayer;
               var10000.rotationPitch -= 20.0F;
            }

            this.field7 = false;
            this.field0.o_();
         }

         if (!this.field7 && this.field0.method0(this.field9.method0() * 1000.0D)) {
            field58.getNetHandler().handleDisconnect(new S40PacketDisconnect(new ChatComponentText(String.format("§cYou are temporarily banned for §f%s §cfrom this server!\n\n§7Reason: §f%s.\n§7Find out more: §b§nhttps://www.hypixel.net/appeal\n\n§7Ban ID: §f%s\n§7Sharing your Ban ID may affect the processing of your appeal!", this.field3.method1(), this.field8.method1(), this.field5.method1()))));
            this.field7 = true;
         }

      }
   }
}
