package oringo.module;

import java.util.Iterator;
import map.Class362;
import map.Class376;
import map.Class518;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.opengl.GL11;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class BossBarModule extends Module {
   public IBossDisplayData field0 = null;
   public BooleanSetting field1 = new BooleanSetting("Slayer Boss Bar", true);

   public static boolean method0(MapData var0, boolean var1) {
      if (!Class362.field62.method44()) {
         return false;
      } else if (!var1) {
         return false;
      } else {
         return CustomHubMap.field3.contains(var0.mapName) ? true : ArcadeESPModule.method3();
      }
   }

   public static void method0(float var0, float var1, float var2, float var3) {
      GL11.glBegin(7);
      GL11.glTexCoord2f(0.0F, 0.0F);
      GL11.glVertex2f(var0, var1);
      GL11.glTexCoord2f(0.0F, 1.0F);
      GL11.glVertex2f(var0, var1 + var3);
      GL11.glTexCoord2f(1.0F, 1.0F);
      GL11.glVertex2f(var0 + var2, var1 + var3);
      GL11.glTexCoord2f(1.0F, 0.0F);
      GL11.glVertex2f(var0 + var2, var1);
      GL11.glEnd();
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase != Phase.START && field58.theWorld != null) {
         this.field0 = null;
         if (this.field1.method1() && Class376.method0("Slay the boss!")) {
            String[] var11 = field58.thePlayer.getDisplayName().getFormattedText().split(" ");
            if (var11.length >= 2) {
               String var12 = "§eSpawned by: " + var11[1];
               Iterator var4 = field58.theWorld.loadedEntityList.iterator();

               while(var4.hasNext()) {
                  Entity var5 = (Entity)var4.next();
                  if (var5 instanceof EntityArmorStand) {
                     String var6 = var5.getDisplayName().getFormattedText();
                     if (var6.equals(var12)) {
                        Entity var7 = field58.theWorld.getEntityByID(var5.getEntityId() - 3);
                        Entity var8 = field58.theWorld.getEntityByID(var5.getEntityId() - 2);
                        if (var8 != null && var7 instanceof EntityLivingBase) {
                           float var9 = ((EntityLivingBase)var7).getHealth();
                           float var10 = Class518.method0((EntityLivingBase)var7);
                           this.field0 = new BossBarModule.Class0(var8.getDisplayName().getFormattedText(), (double)(var9 / var10));
                        }
                     }
                  }
               }

            }
         } else {
            Iterator var2 = field58.theWorld.loadedEntityList.iterator();

            while(var2.hasNext()) {
               Entity var3 = (Entity)var2.next();
               if (var3 instanceof EntityWither && !var3.getDisplayName().getUnformattedText().equals("Wither")) {
                  var3.posY = 10000.0D;
                  this.field0 = (IBossDisplayData)var3;
               }
            }

         }
      }
   }

   public BossBarModule() {
      super("Boss Bar", Category.render, SubCategory.ui);
      this.method0((Setting[])(new Setting[]{this.field1}));
   }

   @SubscribeEvent
   public void method0(Pre var1) {
      if (var1.type == ElementType.BOSSHEALTH && this.field0 != null) {
         BossStatus.setBossStatus(this.field0, true);
      }

   }

   private static class Class0 implements IBossDisplayData {
      private final double field0;
      private final ChatComponentText field1;

      public IChatComponent getDisplayName() {
         return this.field1;
      }

      public float getHealth() {
         return (float)this.field0;
      }

      public float getMaxHealth() {
         return 1.0F;
      }

      public Class0(String var1, double var2) {
         this.field0 = var2;
         this.field1 = new ChatComponentText(var1);
      }
   }
}
