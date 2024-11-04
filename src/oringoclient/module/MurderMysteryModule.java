package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import map.Class374;
import map.Class376;
import map.Class388;
import map.Class528;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team.EnumVisible;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.command.WardrobeCommand;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class MurderMysteryModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Tracers", true);
   public static final BooleanSetting N_ = new BooleanSetting("Say murderer", false);
   public boolean field2;
   public static final BooleanSetting field3 = new BooleanSetting("Ingot ESP", true);
   public static final Set field4 = new HashSet();
   public static final BooleanSetting field5 = new BooleanSetting("Reveal names", true);
   public static final Set field6 = new HashSet();
   public static final BooleanSetting field7 = new BooleanSetting("Bow esp", true);

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field2) {
         Iterator var2 = field58.theWorld.loadedEntityList.iterator();

         while(true) {
            while(var2.hasNext()) {
               Entity var3 = (Entity)var2.next();
               if (var3 instanceof EntityPlayer) {
                  if (!((EntityPlayer)var3).isPlayerSleeping() && var3 != field58.thePlayer) {
                     ScorePlayerTeam var4;
                     if (field6.contains(var3)) {
                        Class374.method0(var3, var1.partialTicks, 1.0F, Color.red);
                        if (field5.method1() && ((EntityPlayer)var3).getTeam() instanceof ScorePlayerTeam) {
                           var4 = (ScorePlayerTeam)((EntityPlayer)var3).getTeam();
                           var4.setNameTagVisibility(EnumVisible.ALWAYS);
                           var4.setNamePrefix("§c");
                        }

                        if (field0.method1()) {
                           Class528.method0(var3, var1.partialTicks, 1.0F, Color.RED);
                        }
                     } else if (field4.contains(var3)) {
                        Class374.method0(var3, var1.partialTicks, 1.0F, Color.blue);
                        if (field5.method1() && ((EntityPlayer)var3).getTeam() instanceof ScorePlayerTeam) {
                           var4 = (ScorePlayerTeam)((EntityPlayer)var3).getTeam();
                           var4.setNameTagVisibility(EnumVisible.ALWAYS);
                           var4.setNamePrefix("§b");
                        }

                        if (field0.method1()) {
                           Class528.method0(var3, var1.partialTicks, 1.0F, Color.blue);
                        }
                     } else {
                        Class374.method0(var3, var1.partialTicks, 1.0F, Color.gray);
                     }
                  }
               } else if (var3 instanceof EntityItem && ((EntityItem)var3).getEntityItem().getItem() == Items.gold_ingot && field3.method1()) {
                  Class374.method0(var3, var1.partialTicks, 1.0F, Color.yellow);
               } else if (field7.method1() && var3 instanceof EntityArmorStand && ((EntityArmorStand)var3).getEquipmentInSlot(0) != null && ((EntityArmorStand)var3).getEquipmentInSlot(0).getItem() == Items.bow) {
                  Class528.method0(var3, var1.partialTicks, 1.0F, Color.CYAN);
               }
            }

            return;
         }
      }
   }

   public static void method0(float var0, float var1, float var2, float var3, float var4, float var5, Color var6) {
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.blendFunc(770, 771);
      Class388.field1.method5();
      PrinterModule.method0(var0, var1, var2, var3, var4, false, var6);
      BossBarModule.method0(var0, var1, var2, var3);
      Class388.field1.method2();
      NoCarpetModule.method0(var0 + var5, var1 + var5, var2 - var5 * 2.0F, var3 - var5 * 2.0F, var4 - var5);
      GlStateManager.enableAlpha();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
   }

   public MurderMysteryModule() {
      super("Murder Mystery", Category.other, SubCategory.other, "Tells you who is the murderer");
      this.method0((Setting[])(new Setting[]{N_, field3, field7, field0, field5}));
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!WardrobeCommand.i_("MURDER MYSTERY")) {
         this.field2 = false;
      } else {
         boolean var2 = Class376.method0("Survivors:") && Class376.method0("Infected:");
         if (!Class376.method0("Innocents Left:") && !var2) {
            this.field2 = false;
            field6.clear();
            field4.clear();
         } else {
            this.field2 = true;
            Iterator var3 = field58.theWorld.playerEntities.iterator();

            while(var3.hasNext()) {
               EntityPlayer var4 = (EntityPlayer)var3.next();
               if (!field6.contains(var4) && !field4.contains(var4) && var4.getHeldItem() != null) {
                  if (field4.size() < 2 && var4.getHeldItem().getItem().equals(Items.bow) && !var2) {
                     field4.add(var4);
                     ShortbowTriggerbotModule.method0("Oringo Client", String.format("§b%s §fis the detective!", var4.getName()), 2500);
                  }

                  NBTTagCompound var5 = TargetStrafeModule.method0(var4.getHeldItem());
                  if (var5 != null && var5.hasKey("KNIFE") && var5.getBoolean("KNIFE")) {
                     field6.add(var4);
                     if (!var2) {
                        ShortbowTriggerbotModule.method0("Oringo Client", String.format("§c%s §fis the murderer!", var4.getName()), 2500);
                        if (N_.method1() && var4 != field58.thePlayer) {
                           field58.thePlayer.sendChatMessage(String.format("%s is the murderer!", ChatFormatting.stripFormatting(var4.getName())));
                        }
                     }
                  }
               }
            }

         }
      }
   }

   public static void method1() {
      GL11.glStencilFunc(512, 0, 15);
      GL11.glStencilOp(7681, 7681, 7681);
      GL11.glPolygonMode(1032, 6914);
   }
}
