package oringo.module;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class408;
import map.Class496;
import map.Class95;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.ArmorStandsCommand;
import oringo.command.BindCommand;
import oringo.command.BlockCommand;
import oringo.command.BloodSkipCommand;
import oringo.command.BlueprintCommand;
import oringo.command.BrushCommand;
import oringo.command.BuyAuctionCommand;
import oringo.command.ChecknameCommand;
import oringo.command.Clip3DCommand;
import oringo.command.ClipCommand;
import oringo.command.Command;
import oringo.command.ConfigCommand;
import oringo.command.DebugCommand;
import oringo.command.ESPCommand;
import oringo.command.FakeBanCommand;
import oringo.command.FireworkCommand;
import oringo.command.FriendsCommand;
import oringo.command.GenInfoCommand;
import oringo.command.GetPosCommand;
import oringo.command.HClipCommand;
import oringo.command.HelpCommand;
import oringo.command.ICCommand;
import oringo.command.IRCCommand;
import oringo.command.IRCNameCommand;
import oringo.command.JerryBoxCommand;
import oringo.command.LoginCommand;
import oringo.command.LogsCommand;
import oringo.command.MessageCommand;
import oringo.command.MoveItemCommand;
import oringo.command.OringoCommand;
import oringo.command.PetCommand;
import oringo.command.PlayerCommand;
import oringo.command.PlusCommand;
import oringo.command.ReplyCommand;
import oringo.command.RoomClearCommand;
import oringo.command.RussianRouletteCommand;
import oringo.command.SayCommand;
import oringo.command.SelfBanCommand;
import oringo.command.StalkCommand;
import oringo.command.TestCommand;
import oringo.command.ToggleCommand;
import oringo.command.ToggleIRCCommand;
import oringo.command.UHCTPCommand;
import oringo.command.UseCommand;
import oringo.command.WardrobeCommand;
import oringo.command.XRayCommand;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoMaskModule extends Module {
   public final DoubleSetting field0 = (DoubleSetting)(new DoubleSetting("Swap health", 30.0D, 0.0D, 100.0D, 1.0D, this::lambda$new$1)).method2("The health percentage");
   public int al_ = -1;
   public final List field2 = Lists.newArrayList(new AutoMaskModule.Class0[]{new AutoMaskModule.Class0(new String[]{"SPIRIT_MASK"}), new AutoMaskModule.Class0(new String[]{"STARRED_BONZO_MASK", "BONZO_MASK"})});
   public final BooleanSetting field3 = new BooleanSetting("Swap back", false, this::lambda$new$0, "Swaps back the helmet if health is above the health threshold");
   public final BooleanSetting field4 = new BooleanSetting("Swap on health", false);

   public static double lambda$onMessage$2(AutoMaskModule.Class0 var0) {
      return (double)var0.field0;
   }

   public Boolean lambda$new$1() {
      return !this.field4.method1();
   }

   public static boolean lambda$onMessage$3(AutoMaskModule.Class0 var0, ItemStack var1) {
      return var0.method0(BlockHitModule.method0(var1));
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.field4.method1() && field58.thePlayer.ticksExisted >= 100 && RenderChunkBoundsModule.method5()) {
         float var2 = field58.thePlayer.getHealth() / field58.thePlayer.getMaxHealth();
         if (var2 > this.field0.method1() / 100.0F) {
            if (this.al_ != -1) {
               if (this.field3.method1()) {
                  ServerRotationsModule.method0(this.al_, 0);
                  ServerRotationsModule.method0(5, 0);
                  ServerRotationsModule.method0(this.al_, 0);
               }

               this.al_ = -1;
            }

         } else {
            ItemStack var3 = field58.thePlayer.getCurrentArmor(3);
            if (var3 != null) {
               String var4 = BlockHitModule.method0(var3);
               if (var4 != null) {
                  Iterator var5 = this.field2.iterator();

                  while(var5.hasNext()) {
                     AutoMaskModule.Class0 var6 = (AutoMaskModule.Class0)var5.next();
                     if (var6.method0(var4)) {
                        return;
                     }
                  }
               }
            }

            this.field2.sort(Comparator.comparingDouble(AutoMaskModule::lambda$onMotionUpdate$4));
            Iterator var7 = this.field2.iterator();

            while(var7.hasNext()) {
               AutoMaskModule.Class0 var8 = (AutoMaskModule.Class0)var7.next();
               if (var8.field0 > System.currentTimeMillis()) {
                  return;
               }

               this.al_ = Class95.method0(AutoMaskModule::lambda$onMotionUpdate$5);
               if (this.al_ != -1) {
                  ServerRotationsModule.method0(this.al_, 0);
                  ServerRotationsModule.method0(5, 0);
                  ServerRotationsModule.method0(this.al_, 0);
                  break;
               }
            }

         }
      }
   }

   public Boolean lambda$new$0() {
      return !this.field4.method1();
   }

   public static void access$000(Object var0) {
      method2(var0);
   }

   public static boolean lambda$onMotionUpdate$5(AutoMaskModule.Class0 var0, ItemStack var1) {
      return var0.method0(BlockHitModule.method0(var1));
   }

   public AutoMaskModule() {
      super("Auto Mask", Category.dungeon, SubCategory.main, "Swaps bonzo and spirit masks");
      this.method0((Setting[])(new Setting[]{this.field4, this.field0, this.field3}));
   }

   public static void access$100(Object var0) {
      method2(var0);
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void method0(ClientChatReceivedEvent var1) {
      if (field58.thePlayer != null && Class496.field5 && var1.type != 2 && RenderChunkBoundsModule.method5()) {
         String var2 = var1.message.getUnformattedText().trim();
         if ((var2.startsWith("Your") || var2.startsWith("Second Wind Activated!")) && var2.endsWith("saved your life!")) {
            ItemStack var3 = field58.thePlayer.getCurrentArmor(3);
            if (var3 == null) {
               return;
            }

            String var4 = BlockHitModule.method0(var3);
            if (var4 == null) {
               return;
            }

            Iterator var5 = this.field2.iterator();

            AutoMaskModule.Class0 var6;
            while(var5.hasNext()) {
               var6 = (AutoMaskModule.Class0)var5.next();
               if (var6.method0(var4)) {
                  var6.method0(var3);
                  return;
               }
            }

            this.field2.sort(Comparator.comparingDouble(AutoMaskModule::lambda$onMessage$2));
            var5 = this.field2.iterator();

            while(var5.hasNext()) {
               var6 = (AutoMaskModule.Class0)var5.next();
               if (var6.method0(var4)) {
                  return;
               }

               int var7 = Class95.method0(AutoMaskModule::lambda$onMessage$3);
               if (var7 != -1) {
                  ServerRotationsModule.method0(var7, 0);
                  ServerRotationsModule.method0(5, 0);
                  ServerRotationsModule.method0(var7, 0);
                  break;
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      Iterator var2 = this.field2.iterator();

      while(var2.hasNext()) {
         AutoMaskModule.Class0 var3 = (AutoMaskModule.Class0)var2.next();
         var3.o_();
      }

   }

   public static double lambda$onMotionUpdate$4(AutoMaskModule.Class0 var0) {
      return (double)var0.field0;
   }

   public static boolean g_(String var0) {
      return Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap().stream().anyMatch(Class496::lambda$anyTab$4);
   }

   public static void method0(BlockPos var0, Color var1) {
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableBlend();
      GL11.glLineWidth(2.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.disableDepth();
      GlStateManager.disableLighting();
      AutoBlazeModule.method0(var1);
      RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB((double)var0.getX() - Oringo.field9.getRenderManager().viewerPosX, (double)var0.getY() - Oringo.field9.getRenderManager().viewerPosY, (double)var0.getZ() - Oringo.field9.getRenderManager().viewerPosZ, (double)(var0.getX() + 1) - Oringo.field9.getRenderManager().viewerPosX, (double)(var0.getY() + 1) - Oringo.field9.getRenderManager().viewerPosY, (double)(var0.getZ() + 1) - Oringo.field9.getRenderManager().viewerPosZ));
      AutoCloseModule.method5();
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.disableBlend();
   }

   public static void z_() {
      AutoMiddleModule.method0((Command)(new JerryBoxCommand()));
      AutoMiddleModule.method0((Command)(new StalkCommand()));
      AutoMiddleModule.method0((Command)(new WardrobeCommand()));
      AutoMiddleModule.method0((Command)(new HelpCommand()));
      AutoMiddleModule.method0((Command)(new ArmorStandsCommand()));
      AutoMiddleModule.method0((Command)(new ToggleCommand()));
      AutoMiddleModule.method0((Command)(new ChecknameCommand()));
      AutoMiddleModule.method0((Command)(new ClipCommand()));
      AutoMiddleModule.method0((Command)(new ConfigCommand()));
      AutoMiddleModule.method0((Command)(new LogsCommand()));
      AutoMiddleModule.method0((Command)(new MoveItemCommand()));
      AutoMiddleModule.method0((Command)(new FireworkCommand()));
      AutoMiddleModule.method0((Command)(new OringoCommand()));
      AutoMiddleModule.method0((Command)(new UseCommand()));
      AutoMiddleModule.method0((Command)(new BindCommand()));
      AutoMiddleModule.method0((Command)(new BlueprintCommand()));
      AutoMiddleModule.method0((Command)(new BuyAuctionCommand()));
      AutoMiddleModule.method0((Command)(new SayCommand()));
      AutoMiddleModule.method0((Command)(new BloodSkipCommand()));
      AutoMiddleModule.method0((Command)(new ESPCommand()));
      AutoMiddleModule.method0((Command)(new FakeBanCommand()));
      if (Oringo.cV_) {
         AutoMiddleModule.method0((Command)(new DebugCommand()));
         AutoMiddleModule.method0((Command)(new LoginCommand()));
      }

      AutoMiddleModule.method0((Command)(new XRayCommand()));
      AutoMiddleModule.method0((Command)(new HClipCommand()));
      AutoMiddleModule.method0((Command)(new FriendsCommand()));
      AutoMiddleModule.method0((Command)(new PlayerCommand()));
      AutoMiddleModule.method0((Command)(new UHCTPCommand()));
      AutoMiddleModule.method0((Command)(new Clip3DCommand()));
      AutoMiddleModule.method0((Command)(new TestCommand()));
      AutoMiddleModule.method0((Command)(new ToggleIRCCommand()));
      AutoMiddleModule.method0((Command)(new MessageCommand()));
      AutoMiddleModule.method0((Command)(new ReplyCommand()));
      AutoMiddleModule.method0((Command)(new BlockCommand()));
      AutoMiddleModule.method0((Command)IRCCommand.field1);
      AutoMiddleModule.method0((Command)(new IRCNameCommand()));
      if (Oringo.cV_) {
         AutoMiddleModule.method0((Command)(new ICCommand()));
         AutoMiddleModule.method0((Command)(new PlusCommand()));
      }

      AutoMiddleModule.method0((Command)(new SelfBanCommand()));
      AutoMiddleModule.method0((Command)(new BrushCommand()));
      AutoMiddleModule.method0((Command)(new GenInfoCommand()));
      AutoMiddleModule.method0((Command)(new RoomClearCommand()));
      AutoMiddleModule.method0((Command)(new RussianRouletteCommand()));
      AutoMiddleModule.method0((Command)(new GetPosCommand()));
      AutoMiddleModule.method0((Command)(new PetCommand()));
   }

   private static class Class0 {
      private static final Pattern field2 = Pattern.compile("^§8Cooldown: §a(\\d+)s$");
      public long field0;
      public String[] field1;

      public void method0(ItemStack var1) {
         List var2 = Class408.method1(var1);
         Iterator var3 = var2.iterator();

         Matcher var5;
         do {
            if (!var3.hasNext()) {
               AutoMaskModule.access$100("Unable to find item cooldown. Please report this!");
               System.out.println(var2);
               this.field0 = System.currentTimeMillis() + 350000L;
               return;
            }

            String var4 = (String)var3.next();
            var5 = field2.matcher(var4);
         } while(!var5.find());

         try {
            this.field0 = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(Long.parseLong(var5.group(1)));
         } catch (NumberFormatException var7) {
            this.field0 = System.currentTimeMillis() + 350000L;
            AutoMaskModule.access$000("Unable to parse item cooldown. Please report this!");
            System.out.println(var2);
         }

      }

      public Class0(String... var1) {
         this.field1 = var1;
      }

      public boolean method0(String var1) {
         if (var1 == null) {
            return false;
         } else {
            String[] var2 = this.field1;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               String var5 = var2[var4];
               if (var5.equals(var1)) {
                  return true;
               }
            }

            return false;
         }
      }

      public void o_() {
         this.field0 = System.currentTimeMillis();
      }
   }
}
