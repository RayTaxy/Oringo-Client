package map;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.DataWatcher.WatchableObject;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.mixin.C02PacketUseEntityAccessor;
import oringo.mixin.EntityPlayerSPAccessor;
import oringo.module.AutoToolModule;
import oringo.module.NoRenderModule2;
import oringo.module.PopupAnimationModule;

public class Class101 {
   public final HashSet field0 = new HashSet();
   public final Pattern at_ = Pattern.compile("(.+?)'s? Profile");
   public final HashSet field2 = new HashSet();
   public static final Minecraft field3 = Minecraft.getMinecraft();
   public int field4 = 0;
   public final Class447 field5 = new Class447();
   public static long field6 = -1L;
   public boolean cs_;

   public static void lambda$onPacket$0(S1CPacketEntityMetadata var0) {
      C02PacketUseEntity var1 = new C02PacketUseEntity();
      ((C02PacketUseEntityAccessor)var1).setEntityId(var0.getEntityId());
      ((C02PacketUseEntityAccessor)var1).setAction(Action.INTERACT);
      Class302.method2(var1);
   }

   public static boolean lambda$onPacket$1() {
      return !field3.playerController.getIsHittingBlock();
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S01PacketJoinGame) {
         this.field0.clear();
         this.field2.clear();
         this.cs_ = false;
         this.field4 = ((S01PacketJoinGame)var1.field0).getEntityId();
      } else if (var1.field0 instanceof S0CPacketSpawnPlayer) {
         int var2 = ((S0CPacketSpawnPlayer)var1.field0).getEntityID();
         this.field0.add(var2);
         if (this.field2.contains(var2)) {
            this.field2.remove(var2);
            NoRenderModule2.method0("False flag " + var2 + " " + String.format(" (%s remaining)", this.field2.size()));
            System.out.println(AutoToolModule.method0(var1.field0));
         }
      }

      if (field3.thePlayer != null && field3.thePlayer.ticksExisted >= 50) {
         if (Class496.field7) {
            if (var1.field0 instanceof S1CPacketEntityMetadata) {
               if (!this.field5.a_(750L)) {
                  return;
               }

               S1CPacketEntityMetadata var5 = (S1CPacketEntityMetadata)var1.field0;
               if (var5.func_149376_c() == null || var5.getEntityId() == field3.thePlayer.getEntityId() || var5.getEntityId() == this.field4 || this.field0.contains(var5.getEntityId())) {
                  return;
               }

               Iterator var3 = ((S1CPacketEntityMetadata)var1.field0).func_149376_c().iterator();

               while(var3.hasNext()) {
                  WatchableObject var4 = (WatchableObject)var3.next();
                  if (var4.getObjectType() == 3 && var4.getDataValueId() == 17) {
                     field6 = System.currentTimeMillis();
                     this.field5.o_();
                     if (Oringo.cV_) {
                        NoRenderModule2.method0("Spectate " + ((S1CPacketEntityMetadata)var1.field0).getEntityId());
                        this.field2.add(((S1CPacketEntityMetadata)var1.field0).getEntityId());
                        if (!((EntityPlayerSPAccessor)field3.thePlayer).getServerSneakState()) {
                           Class361.method0((Class94)(new Class395(Class101::lambda$onPacket$0, true, Class101::lambda$onPacket$1)));
                        }
                     }
                     break;
                  }
               }
            } else if (var1.field0 instanceof S2DPacketOpenWindow) {
               if (!this.cs_) {
                  return;
               }

               String var6 = ChatFormatting.stripFormatting(((S2DPacketOpenWindow)var1.field0).getWindowTitle().getFormattedText());
               Matcher var8 = this.at_.matcher(var6);
               if (var8.find() && Oringo.cV_) {
                  PopupAnimationModule.method2("The staff's name is " + var8.group(1));
                  Class302.method2(new C0DPacketCloseWindow(((S2DPacketOpenWindow)var1.field0).getWindowId()));
                  var1.method9();
               }
            } else if (var1.field0 instanceof S29PacketSoundEffect) {
               if (!this.cs_) {
                  return;
               }

               S29PacketSoundEffect var7 = (S29PacketSoundEffect)var1.field0;
               if (var7.getSoundName().equals("random.click") && var7.getVolume() == 0.5F && var7.getPitch() == 1.0F) {
                  var1.method9();
               }
            }

         }
      }
   }
}
