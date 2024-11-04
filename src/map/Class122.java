package map;

import java.net.URL;
import java.net.URLConnection;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.scoreboard.Team.EnumVisible;
import net.minecraft.util.BlockPos;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import oringo.Oringo;
import oringo.module.AntiObsidianModule;
import oringo.setting.Setting;

public abstract class Class122 extends Class422 {
   public final Setting field1;

   public static boolean method0(EntityOtherPlayerMP var0) {
      return var0.getTeam() == null || var0.getTeam().getNameTagVisibility() == EnumVisible.NEVER || var0.getTeam().getNameTagVisibility() == EnumVisible.HIDE_FOR_OTHER_TEAMS && Oringo.field9.thePlayer.getTeam() != var0.getTeam();
   }

   public static URLConnection method0(URL var0) {
      return var0.openConnection();
   }

   public boolean method2() {
      return !this.field1.g_();
   }

   public static boolean method0(boolean var0) {
      BlockPos var1 = (new BlockPos(AntiObsidianModule.field58.thePlayer.posX, AntiObsidianModule.field58.thePlayer.posY, AntiObsidianModule.field58.thePlayer.posZ)).up();
      Block var2 = AntiObsidianModule.field58.theWorld.getBlockState(var1).getBlock();
      if (var0 && var2 instanceof BlockFalling) {
         return true;
      } else {
         Block[] var3 = AntiObsidianModule.G_;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Block var6 = var3[var5];
            if (var6 == var2) {
               return true;
            }
         }

         return false;
      }
   }

   public Class122(Setting var1) {
      this.field1 = var1;
   }

   public static Vector2f method0(Vector3f var0, int var1, int var2) {
      return Class6.method0(var0, Class368.method2(2982), Class368.method2(2983), var1, var2);
   }
}
