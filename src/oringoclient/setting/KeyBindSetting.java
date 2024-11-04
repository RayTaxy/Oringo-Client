package oringo.setting;

import com.google.common.base.Predicates;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Iterator;
import java.util.List;
import map.Class1;
import map.Class376;
import map.Class500;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import oringo.Oringo;
import oringo.module.AutoBlazeModule;
import oringo.module.PestESPModule;

public class KeyBindSetting extends Setting {
   public int field0;
   public boolean field13;
   @Expose
   @SerializedName("keycode")
   public int field57;
   public final Class500 field3;

   public int method46() {
      return this.field57;
   }

   public KeyBindSetting(String var1, Class500 var2, Class500 var3) {
      super(var1, var3);
      this.field3 = var2;
   }

   public static void method0(BlockPos var0, Block var1, int var2) {
      Class1 var3 = Setting.method1(var0);
      var3.field3 = var1;
      var3.field0 = var2;
      if (Oringo.field9.theWorld != null && var1 == null) {
         Oringo.field9.theWorld.setBlockState(var0, Blocks.air.getDefaultState());
      }

      PestESPModule.n_();
   }

   public boolean method8() {
      return this.field0-- > 0;
   }

   public static boolean method0(Entity var0, Vec3 var1) {
      if (!AutoBlazeModule.field58.thePlayer.canEntityBeSeen(var0)) {
         return false;
      } else {
         float var2 = 1.0F;
         double var3 = AutoBlazeModule.field58.thePlayer.getDistance(var0.posX, var0.posY + (double)var0.getEyeHeight() / 2.0D - (double)AutoBlazeModule.field58.thePlayer.getEyeHeight(), var0.posZ);
         List var5 = AutoBlazeModule.field58.theWorld.getEntitiesInAABBexcluding(AutoBlazeModule.field58.thePlayer, AutoBlazeModule.field58.thePlayer.getEntityBoundingBox().addCoord(var1.xCoord * var3, var1.yCoord * var3, var1.zCoord * var3).expand((double)var2, (double)var2, (double)var2), Predicates.and(EntitySelectors.NOT_SPECTATING, Entity::func_70067_L));
         Vec3 var6 = AutoBlazeModule.field58.thePlayer.getPositionEyes(1.0F);
         Vec3 var7 = var6.addVector(var1.xCoord * var3, var1.yCoord * var3, var1.zCoord * var3);
         Iterator var8 = var5.iterator();

         AxisAlignedBB var11;
         MovingObjectPosition var12;
         do {
            Entity var9;
            do {
               if (!var8.hasNext()) {
                  return true;
               }

               var9 = (Entity)var8.next();
            } while(var9 == var0);

            float var10 = var9.getCollisionBorderSize() + 0.3F;
            var11 = var9.getEntityBoundingBox().expand((double)var10, (double)var10, (double)var10);
            var12 = var11.calculateIntercept(var6, var7);
         } while(!var11.isVecInside(var6) && var12 == null);

         return false;
      }
   }

   public boolean method1() {
      boolean var10000;
      label27: {
         if ((Boolean)this.field3.get() && this.field57 != 0) {
            if (this.field57 > 0) {
               if (Keyboard.isKeyDown(this.field57)) {
                  break label27;
               }
            } else if (Mouse.isButtonDown(this.field57 - 100)) {
               break label27;
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean method44() {
      return this.field13;
   }

   public static boolean method0(File var0) {
      try {
         Files.write(var0.toPath(), EnumSetting.method0(), new OpenOption[0]);
      } catch (Exception var2) {
         Class376.field3.error("Exception thrown while saving config {}!", new Object[]{var0.getAbsolutePath(), var2});
         return false;
      }

      Class376.field3.info("Config saved successfully!");
      return true;
   }

   public void method3(int var1) {
      this.field57 = var1;
   }

   public KeyBindSetting(String var1, Class500 var2) {
      this(var1, var2, (Class500)null);
   }

   public void method4() {
      if ((Boolean)this.field3.get()) {
         ++this.field0;
         this.field13 = !this.field13;
      }
   }

   public void method1(boolean var1) {
      this.field13 = var1;
   }
}
