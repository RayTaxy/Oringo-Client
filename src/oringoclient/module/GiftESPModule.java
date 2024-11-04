package oringo.module;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import map.Class475;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.DebugCommand;
import oringo.command.PlayerCommand;
import oringo.event.Class270;
import oringo.event.Class307;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class GiftESPModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Simulator ESP", true);
   public static final BooleanSetting field1 = new BooleanSetting("Main Lobby ESP", true);
   public final HashSet field2 = new HashSet();

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      Iterator var2 = field58.theWorld.loadedTileEntityList.iterator();

      while(var2.hasNext()) {
         TileEntity var3 = (TileEntity)var2.next();
         if (var3 instanceof TileEntitySkull && ExtraFeaturesModule.method0((TileEntitySkull)var3) && !this.field2.contains(var3.getPos())) {
            if (!DebugCommand.method0(GiftESPModule::lambda$onWorld$0)) {
               AutoArrowModule.method0((double)var3.getPos().getX() + 0.5D, (double)var3.getPos().getY() + 0.25D, (double)var3.getPos().getZ() + 0.5D, Color.GREEN);
            }

            if (Class307.method0(var3.getPos())) {
               AutoBlazeModule.method0(PlayerCommand.method0(Color.GREEN, 60));
               Class475.method0(var3.getPos());
               AutoCloseModule.method5();
            }
         }
      }

   }

   public static boolean lambda$isGift$1(String var0) {
      return var0.endsWith("SIMULATOR");
   }

   @SubscribeEvent
   public void a_(Class270 var1) {
      this.field2.clear();
   }

   public static boolean method0(double var0, double var2, double var4, double var6) {
      int var8 = DisablerModule.method5();
      int var9 = EvidenceForgerModule.method5();
      return (double)var8 > var0 && (double)var8 < var0 + var4 && (double)var9 > var2 && (double)var9 < var2 + var6;
   }

   public static boolean method0(ItemStack var0, String var1) {
      var1 = var1.toLowerCase();
      String var2 = var0.getDisplayName().toLowerCase();
      String var3 = BlockHitModule.method0(var0);
      return var2.contains(var1) || var3 != null && var3.toLowerCase().contains(var1);
   }

   @SubscribeEvent
   public void field0(Class332.Class0 var1) {
      if (var1.field0 instanceof C08PacketPlayerBlockPlacement) {
         this.field2.add(((C08PacketPlayerBlockPlacement)var1.field0).getPosition());
      }

   }

   public static boolean lambda$onWorld$0(String var0) {
      return var0.endsWith("SIMULATOR");
   }

   public GiftESPModule() {
      super("Gift ESP", Category.other, SubCategory.other, "Helps you find all of the main lobby gifts");
      this.method0(new Setting[]{field1, field0});
   }
}
