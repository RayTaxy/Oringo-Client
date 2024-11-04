package oringo.module;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import map.Class34;
import map.Class362;
import map.Class392;
import me.oringo.Key;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0BPacketEntityAction.Action;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.input.Mouse;
import oringo.Oringo;
import oringo.command.ReplyCommand;
import oringo.mixin.MinecraftAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class AOTVReturnModule extends Module {
   public final StringSetting o_ = new StringSetting("Warp command", "/warp forge");
   public final BooleanSetting p_ = new BooleanSetting("Open chat", true);
   public boolean q_;
   public boolean field2;
   public final DoubleSetting field4 = new DoubleSetting("Delay", 2000.0D, 500.0D, 5000.0D, 1.0D);
   public Thread field5 = null;
   public boolean field6 = false;
   public final StringSetting field7 = new StringSetting("TP Coords", "0.5,167,-10.5;-23.5,180,-26.5;-64.5,212,-15.5;-33.5,244,-32.5");
   public Vec3 field0 = null;
   public final EnumSetting field9 = new EnumSetting("mode", "walk", new String[]{"jump", "walk"});
   public final BooleanSetting field10 = new BooleanSetting("Middle click", false);

   public void lambda$null$3(Minecraft var1, Runnable var2) {
      var1.thePlayer.sendChatMessage("/is");
      this.method0(var2, false);
   }

   public void b_() {
      this.q_ = false;
      if (this.field5 != null) {
         this.field5.stop();
      }

   }

   public static void method1() {
      if (!Class392.field6) {
         Class392.field6 = true;
         Class392.field5.mkdirs();
         AutoSumoModule.method5();
         String var0 = AutoFishModule.method0(Class392.field0);

         try {
            HttpURLConnection var1 = (HttpURLConnection)(new URL(String.format("https://shiyu.moe/assets/?checksum=%s&key=%s", var0, Key.getKey()))).openConnection();
            switch(var1.getResponseCode()) {
            case 200:
               Files.copy(var1.getInputStream(), Class392.field0.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            case 204:
               break;
            default:
               if (!Oringo.cV_) {
                  throw new IllegalStateException("Failed to load resources! " + var1.getResponseCode());
               }
            }

            ((MinecraftAccessor)Minecraft.getMinecraft()).getDefaultResourcePacks().add(new FileResourcePack(Class392.field0));
         } catch (IOException var2) {
            throw new IllegalStateException("Failed to load resources!", var2);
         }
      }
   }

   public static boolean lambda$null$1(Entity var0) {
      return var0 instanceof EntityArmorStand;
   }

   public void method0(Runnable var1, boolean var2) {
      if (this.field5 != null) {
         this.field5.stop();
      }

      this.q_ = true;
      Minecraft var3 = Minecraft.getMinecraft();
      (this.field5 = new Thread(this::lambda$start$4)).start();
   }

   public AOTVReturnModule() {
      super("AOTV Return", Category.other, SubCategory.skills, (String)null);
      this.method0(new Setting[]{this.o_, this.field9, this.field7, this.p_, this.field10, this.field4});
   }

   public void lambda$start$4(Minecraft var1, Runnable var2, boolean var3) {
      try {
         KeyBinding.setKeyBindState(var1.gameSettings.keyBindAttack.getKeyCode(), false);
         var1.thePlayer.sendChatMessage("/l");
         Thread.sleep(5000L);
         var1.thePlayer.sendChatMessage("/skyblock");
         Thread.sleep(5000L);
         var1.thePlayer.sendChatMessage("/is");
         Thread.sleep((long)this.field4.method0() * 3L);

         for(int var4 = 0; var4 < 9; ++var4) {
            if (var1.thePlayer.inventory.getStackInSlot(var4) != null && var1.thePlayer.inventory.getStackInSlot(var4).getDisplayName().contains("Void")) {
               var1.thePlayer.inventory.currentItem = var4;
               break;
            }
         }

         var1.thePlayer.sendChatMessage(this.o_.method1());
         Thread.sleep((long)this.field4.method0() * 2L);
         if (Class362.field54.field28 == null) {
            Iterator var11 = ((List)var1.theWorld.getLoadedEntityList().stream().filter(AOTVReturnModule::lambda$null$0).collect(Collectors.toList())).iterator();

            while(var11.hasNext()) {
               Entity var5 = (Entity)var11.next();
               if (var5.getDisplayName().getFormattedText().contains("§e§lDRILL MECHANIC§r")) {
                  Class362.field54.field28 = (EntityArmorStand)var5;
                  PopupAnimationModule.method2("Mechanic");
                  break;
               }
            }
         }

         String[] var12 = this.field7.method1().split(";");
         int var13 = var12.length;

         for(int var6 = 0; var6 < var13; ++var6) {
            String var7 = var12[var6];
            if (Class362.field54.field28 == null) {
               Iterator var8 = ((List)var1.theWorld.getLoadedEntityList().stream().filter(AOTVReturnModule::lambda$null$1).collect(Collectors.toList())).iterator();

               while(var8.hasNext()) {
                  Entity var9 = (Entity)var8.next();
                  if (var9.getDisplayName().getFormattedText().contains("§e§lDRILL MECHANIC§r")) {
                     Class362.field54.field28 = (EntityArmorStand)var9;
                     PopupAnimationModule.method2("Mechanic");
                     break;
                  }
               }
            }

            Thread.sleep((long)this.field4.method0());
            String var15 = this.field9.method4();
            byte var17 = -1;
            switch(var15.hashCode()) {
            case 3273774:
               if (var15.equals("jump")) {
                  var17 = 0;
               }
               break;
            case 3641801:
               if (var15.equals("walk")) {
                  var17 = 1;
               }
            }

            label91:
            switch(var17) {
            case 0:
               if (var1.thePlayer.onGround) {
                  var1.thePlayer.jump();
               }

               while(true) {
                  if (var1.thePlayer.onGround) {
                     break label91;
                  }

                  Thread.sleep(1L);
               }
            case 1:
               KeyBinding.setKeyBindState(var1.gameSettings.keyBindRight.getKeyCode(), true);
               Thread.sleep(50L);
               KeyBinding.setKeyBindState(var1.gameSettings.keyBindRight.getKeyCode(), false);
            }

            Thread.sleep((long)this.field4.method0());
            this.field0 = new Vec3(Double.parseDouble(var7.split(",")[0]), Double.parseDouble(var7.split(",")[1]), Double.parseDouble(var7.split(",")[2]));
            Class34 var16 = ReplyCommand.method0(this.field0);
            var1.thePlayer.rotationYaw = var16.method5();
            var1.thePlayer.rotationPitch = var16.method2();
            Thread.sleep((long)this.field4.method0());
            var1.getNetHandler().getNetworkManager().sendPacket(new C0BPacketEntityAction(var1.thePlayer, Action.START_SNEAKING));
            var1.playerController.sendUseItem(var1.thePlayer, var1.theWorld, var1.thePlayer.getHeldItem());
            var1.getNetHandler().getNetworkManager().sendPacket(new C0BPacketEntityAction(var1.thePlayer, Action.STOP_SNEAKING));
            this.field0 = null;
         }

         Thread.sleep((long)this.field4.method0());
         String var14 = this.field7.method1().split(";")[this.field7.method1().split(";").length - 1];

         for(var13 = 0; var13 < 9; ++var13) {
            if (var1.thePlayer.inventory.getStackInSlot(var13) != null && var1.thePlayer.inventory.getStackInSlot(var13).getTooltip(var1.thePlayer, false).stream().anyMatch(AOTVReturnModule::lambda$null$2)) {
               var1.thePlayer.inventory.currentItem = var13;
               break;
            }
         }

         Thread.sleep((long)this.field4.method0());
         if (var1.thePlayer.getDistance(Double.parseDouble(var14.split(",")[0]), Double.parseDouble(var14.split(",")[1]), Double.parseDouble(var14.split(",")[2])) < 3.0D) {
            if (this.p_.method1()) {
               this.field6 = true;
            }

            if (var2 != null) {
               var2.run();
            }
         } else if (!var3) {
            (new Thread(this::lambda$null$3)).start();
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

      this.q_ = false;
   }

   public static boolean lambda$null$2(String var0) {
      return var0.toLowerCase().contains("pickaxe") || var0.toLowerCase().contains("gauntlet") || var0.toLowerCase().contains("drill");
   }

   public boolean method2() {
      return this.q_;
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (this.method44()) {
         if (this.field6) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
            this.field6 = false;
         }

         if (field58.thePlayer != null && field58.theWorld != null && this.field10.method1()) {
            if (Mouse.isButtonDown(2) && field58.currentScreen == null) {
               if (!this.field2 && field58.objectMouseOver != null && field58.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                  BlockPos var2 = field58.objectMouseOver.getBlockPos();
                  if (field58.theWorld.getBlockState(var2).getBlock().getMaterial() != Material.air) {
                     this.field7.method1(this.field7.method1() + (this.field7.method1().length() > 0 ? ";" : "") + ((double)var2.getX() + 0.5D) + "," + ((double)var2.getY() + 0.5D) + "," + ((double)var2.getZ() + 0.5D));
                     ShortbowTriggerbotModule.method0("Oringo Client", "Added " + var2.getX() + " " + var2.getY() + " " + var2.getZ() + " to coords!", 2500);
                  }
               }

               this.field2 = true;
            } else {
               this.field2 = false;
            }

         }
      }
   }

   public static boolean lambda$null$0(Entity var0) {
      return var0 instanceof EntityArmorStand;
   }
}
