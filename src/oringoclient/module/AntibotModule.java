package oringo.module;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import oringo.Oringo;
import oringo.mixin.RenderAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class AntibotModule extends Module {
   public static final EnumSetting field0 = new EnumSetting("Mode", "Hypixel", new String[]{"Hypixel", "Custom"});
   public static final BooleanSetting field1 = (BooleanSetting)(new BooleanSetting("Invis ticks check", true, AntibotModule::lambda$static$1)).method2("Checks if the entity was visible");
   public static final BooleanSetting field2 = (BooleanSetting)(new BooleanSetting("Tab ticks check", false, AntibotModule::lambda$static$2)).method2("Checks if the entity appeared on the tablist");
   public static final BooleanSetting field3 = new BooleanSetting("NPC check", true, AntibotModule::lambda$static$3);
   public static AntibotModule field4;
   public static final BooleanSetting field5 = new BooleanSetting("Name check", false, AntibotModule::lambda$static$0);

   public static Boolean lambda$static$3() {
      return !field0.method0(1);
   }

   public static void method0(int var0, int var1, float var2, float var3, int var4, int var5, int var6, int var7, float var8, float var9, EntityLivingBase var10) {
      ResourceLocation var11;
      if (var10 instanceof AbstractClientPlayer) {
         var11 = ((AbstractClientPlayer)var10).getLocationSkin();
      } else {
         var11 = ((RenderAccessor)Oringo.field9.getRenderManager().getEntityClassRenderObject(var10.getClass())).getEntityTextureInvoker(var10);
      }

      if (var11 != null) {
         GlStateManager.enableTexture2D();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         Oringo.field9.getTextureManager().bindTexture(var11);
         Gui.drawScaledCustomSizeModalRect(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9);
         GlStateManager.disableBlend();
      }

   }

   public static boolean method0(Packet var0) {
      if (var0 instanceof S32PacketConfirmTransaction) {
         return false;
      } else if (var0 instanceof S20PacketEntityProperties) {
         return false;
      } else if (var0 instanceof S3BPacketScoreboardObjective) {
         return false;
      } else if (var0 instanceof S0BPacketAnimation) {
         return false;
      } else if (var0 instanceof S02PacketChat) {
         return false;
      } else if (var0 instanceof S37PacketStatistics) {
         return false;
      } else if (var0 instanceof C0FPacketConfirmTransaction) {
         return false;
      } else if (var0 instanceof S04PacketEntityEquipment) {
         return false;
      } else if (var0 instanceof S14PacketEntity) {
         return false;
      } else if (var0 instanceof S19PacketEntityHeadLook) {
         return false;
      } else if (var0 instanceof S3EPacketTeams) {
         return false;
      } else if (var0 instanceof S12PacketEntityVelocity) {
         return false;
      } else if (var0 instanceof S18PacketEntityTeleport) {
         return false;
      } else if (var0 instanceof S47PacketPlayerListHeaderFooter) {
         return false;
      } else if (var0 instanceof S29PacketSoundEffect) {
         return false;
      } else if (var0 instanceof S00PacketKeepAlive) {
         return false;
      } else if (var0 instanceof S03PacketTimeUpdate) {
         return false;
      } else if (var0 instanceof S3CPacketUpdateScore) {
         return false;
      } else if (var0 instanceof S2APacketParticles) {
         return false;
      } else if (var0 instanceof S1CPacketEntityMetadata) {
         return false;
      } else if (var0 instanceof S24PacketBlockAction) {
         return false;
      } else if (var0 instanceof C03PacketPlayer) {
         return false;
      } else if (var0 instanceof C00PacketKeepAlive) {
         return false;
      } else if (var0 instanceof S38PacketPlayerListItem) {
         return false;
      } else if (var0 instanceof S3FPacketCustomPayload) {
         return false;
      } else if (var0 instanceof FMLProxyPacket) {
         return false;
      } else if (var0 instanceof S21PacketChunkData) {
         return false;
      } else {
         return !(var0 instanceof S26PacketMapChunkBulk);
      }
   }

   public static Boolean lambda$static$0() {
      return !field0.method0(-1);
   }

   public static Boolean lambda$static$2() {
      return !field0.method0(1);
   }

   public AntibotModule() {
      super("Antibot", Category.combat, SubCategory.combat, "Prevents you from attacking bots and npcs");
      this.method0(new Setting[]{field0, field1, field2, field3, field5});
      EnumSetting var10001 = field0;
      this.method0(var10001::method4);
   }

   public static Boolean lambda$static$1() {
      return !field0.method0(1);
   }
}
