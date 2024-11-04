package oringo.module;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.mixin.ItemSwordAccessor;
import oringo.mixin.ItemToolAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AutoPhobiaModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Auto sign", true);
   public final BooleanSetting bM_ = new BooleanSetting("No blind", true);
   public final BooleanSetting field2 = new BooleanSetting("Auto math", true);

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (this.field2.method1()) {
         String var2 = var1.message.getUnformattedText();
         if (var2.startsWith("QUICK MATHS! Solve: ")) {
            String var3 = var2.replaceAll("QUICK MATHS! Solve: ", "");

            try {
               AutoPhobiaModule.Class0 var4 = new AutoPhobiaModule.Class0(var3, (AutoPhobiaModule$1)null);
               double var5 = var4.method0();
               field58.thePlayer.sendChatMessage(String.valueOf((int)var5));
            } catch (RuntimeException var7) {
               method2("Parsing expression failed due to " + var7.getMessage() + " for string '" + var3 + "'!");
            }

         }
      }
   }

   @SubscribeEvent
   public void method1(ClientChatReceivedEvent var1) {
      if (this.field0.method1()) {
         ClickEvent var2 = var1.message.getChatStyle().getChatClickEvent();
         if (var2 != null && var2.getAction() == Action.RUN_COMMAND) {
            if (var2.getValue().startsWith("/spookysignpaper ")) {
               field58.thePlayer.sendChatMessage(var2.getValue());
            }

         }
      }
   }

   public static float method0(Entity var0, ItemStack var1, boolean var2) {
      float var3 = 1.0F;
      if (var1 == null) {
         return var3;
      } else {
         if (var1.getItem() instanceof ItemTool) {
            var3 += ((ItemToolAccessor)var1.getItem()).getDamageVsEntity();
         } else if (var1.getItem() instanceof ItemSword) {
            var3 += ((ItemSwordAccessor)var1.getItem()).getAttackDamage();
            var3 += 2.0F;
         }

         if (var0 instanceof EntityLivingBase) {
            var3 += EnchantmentHelper.getModifierForCreature(var1, ((EntityLivingBase)var0).getCreatureAttribute());
         } else {
            var3 += EnchantmentHelper.getModifierForCreature(var1, EnumCreatureAttribute.UNDEFINED);
         }

         if (var2 && var0 != null && !var0.isBurning()) {
            int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, var1);
            var3 += (float)(25 * var4);
         }

         return var3;
      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START && field58.thePlayer != null && this.bM_.method1()) {
         field58.thePlayer.removePotionEffect(Potion.blindness.getId());
      }
   }

   public AutoPhobiaModule() {
      super("Auto Phobia", Category.skyblock, SubCategory.qol);
      this.method0((Setting[])(new Setting[]{this.field0, this.field2, this.bM_}));
   }

   public static class Class0 {
      private final String field0;
      private int field1;
      private int field2;

      private double method1() {
         if (this.method0(43)) {
            return this.method1();
         } else if (this.method0(45)) {
            return -this.method1();
         } else {
            int var3 = this.field1;
            double var1;
            if (this.method0(40)) {
               var1 = this.method4();
               if (!this.method0(41)) {
                  throw new RuntimeException("Missing ')'");
               }
            } else {
               if ((this.field2 < 48 || this.field2 > 57) && this.field2 != 46) {
                  throw new RuntimeException("Unexpected: " + (char)this.field2);
               }

               while(this.field2 >= 48 && this.field2 <= 57 || this.field2 == 46) {
                  this.method2();
               }

               var1 = Double.parseDouble(this.field0.substring(var3, this.field1));
            }

            if (this.method0(94)) {
               var1 = Math.pow(var1, this.method1());
            }

            return var1;
         }
      }

      private boolean method0(int var1) {
         while(this.field2 == 32) {
            this.method2();
         }

         if (this.field2 == var1) {
            this.method2();
            return true;
         } else {
            return false;
         }
      }

      public double method0() {
         this.method2();
         double var1 = this.method4();
         if (this.field1 < this.field0.length()) {
            throw new RuntimeException("Unexpected: " + (char)this.field2);
         } else {
            return var1;
         }
      }

      private void method2() {
         this.field2 = ++this.field1 < this.field0.length() ? this.field0.charAt(this.field1) : -1;
      }

      private double method3() {
         double var1 = this.method1();

         while(true) {
            while(!this.method0(120)) {
               if (!this.method0(47)) {
                  return var1;
               }

               var1 /= this.method1();
            }

            var1 *= this.method1();
         }
      }

      Class0(String var1, AutoPhobiaModule$1 var2) {
         this(var1);
      }

      private double method4() {
         double var1 = this.method3();

         while(true) {
            while(!this.method0(43)) {
               if (!this.method0(45)) {
                  return var1;
               }

               var1 -= this.method3();
            }

            var1 += this.method3();
         }
      }

      private Class0(String var1) {
         this.field1 = -1;
         this.field0 = var1;
      }
   }
}
