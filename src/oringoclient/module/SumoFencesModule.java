package oringo.module;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import map.Class376;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class194;

public class SumoFencesModule extends Module {
   public boolean field0 = false;
   public final Set M_ = new HashSet();

   public SumoFencesModule() {
      super("Sumo Fences", Category.combat, SubCategory.combat, "Prevents you from falling off during sumo duels");
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START && field58.theWorld != null && field58.thePlayer != null) {
         if (Class376.method0("Mode: Sumo") && Class376.method0("Opponent:")) {
            this.field0 = true;
         } else {
            this.M_.clear();
            this.field0 = false;
         }
      }
   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      if (this.field0 && var1.field1 == null) {
         if (field58.theWorld.getBlockState(var1.field0.down(9)).getBlock() == Blocks.air) {
            var1.field1 = (new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 5.0D, 1.0D)).offset((double)var1.field0.getX(), (double)var1.field0.getY(), (double)var1.field0.getZ());
         }
      }
   }

   public static Map method0(ItemStack var0) {
      HashMap var1 = new HashMap();
      if (var0 == null) {
         return var1;
      } else {
         NBTTagCompound var2 = TargetStrafeModule.method0(var0);
         if (var2 != null && var2.hasKey("attributes")) {
            NBTTagCompound var3 = var2.getCompoundTag("attributes");
            Iterator var4 = var3.getKeySet().iterator();

            while(var4.hasNext()) {
               String var5 = (String)var4.next();
               var1.put(var5, var3.getInteger(var5));
            }

            return var1;
         } else {
            return var1;
         }
      }
   }

   public static void method0(ResourceLocation var0, float var1, float var2, float var3, float var4) {
      GlStateManager.enableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.blendFunc(770, 771);
      Oringo.field9.getTextureManager().bindTexture(var0);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      TargetStrafeModule.method0(var1, var2, 0.0F, 0.0F, var3, var4, var3, var4);
      GlStateManager.disableTexture2D();
      GlStateManager.disableBlend();
   }
}
