package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import map.Class98;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.LivingMetalMinerModule;
import oringo.setting.ColorSetting;
import oringo.setting.Setting;

public class OdonataESPModule extends Module {
   public static final String field0 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZkODA2ZGVmZGZkZjU5YjFmMjYwOWM4ZWUzNjQ2NjZkZTY2MTI3YTYyMzQxNWI1NDMwYzkzNThjNjAxZWY3YyJ9fX0=";
   public final ColorSetting cF_;

   public static float method0(float var0) {
      return LivingMetalMinerModule.method0(var0, 0.1F);
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      Iterator var2 = field58.theWorld.loadedEntityList.iterator();

      while(var2.hasNext()) {
         Entity var3 = (Entity)var2.next();
         if (var3 instanceof EntityArmorStand) {
            EntityArmorStand var4 = (EntityArmorStand)var3;
            if (var4.isInvisible() && var4.getHealth() == 20.0F && var4.getHeldItem() != null) {
               ItemStack var5 = var4.getHeldItem();
               if (var5.getItem() instanceof ItemSkull && "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZkODA2ZGVmZGZkZjU5YjFmMjYwOWM4ZWUzNjQ2NjZkZTY2MTI3YTYyMzQxNWI1NDMwYzkzNThjNjAxZWY3YyJ9fX0=".equals(Class98.method0(var5))) {
                  AutoIceSprayModule.method0((new AxisAlignedBB(-0.15D, 0.85D, -0.15D, 0.15D, 1.15D, 0.15D)).offset(var4.prevPosX + (var4.posX - var4.prevPosX) * (double)var1.partialTicks, var4.prevPosY + (var4.posY - var4.prevPosY) * (double)var1.partialTicks, var4.prevPosZ + (var4.posZ - var4.prevPosZ) * (double)var1.partialTicks), this.cF_.method17());
               }
            }
         }
      }

   }

   public OdonataESPModule() {
      super("Odonata ESP", Category.render, SubCategory.rift);
      this.cF_ = new ColorSetting("Color", Color.CYAN, false);
      this.method0(new Setting[]{this.cF_});
   }
}
