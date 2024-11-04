package oringo.mixin;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import map.Class262;
import map.Class265;
import map.Class362;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.command.ArmorStandsCommand;
import oringo.event.Class210;
import oringo.event.Class217;
import oringo.module.OptimizationsModule;

@Mixin(
   value = {FontRenderer.class},
   priority = 1001
)
public abstract class FontRendererMixin {
   @Unique
   private final List field0 = new ArrayList(0);
   private int field1;
   private float field2;
   private float field3;
   private float field4;
   private float field5;

   @Inject(
      method = {"renderStringAtPos"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/FontRenderer;renderChar(CZ)F",
   ordinal = 0
)}
   )
   public void method0(String var1, boolean var2, CallbackInfo var3) {
      if (!this.field0.isEmpty()) {
         Iterator var4 = this.field0.iterator();

         while(var4.hasNext()) {
            int[] var5 = (int[])var4.next();
            if (this.field1 >= var5[0] && this.field1 < var5[1]) {
               Color var6 = new Color(var5[2]);
               GlStateManager.color((float)var6.getRed() / 255.0F / (float)(var2 ? 4 : 1), (float)var6.getGreen() / 255.0F / (float)(var2 ? 4 : 1), (float)var6.getBlue() / 255.0F / (float)(var2 ? 4 : 1), this.field5);
            }

            if (this.field1 == var5[1]) {
               GlStateManager.color(this.field3, this.field2, this.field4, this.field5);
            }
         }

         ++this.field1;
      }
   }

   @ModifyVariable(
      method = {"renderStringAtPos"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private String text(String var1) {
      if (var1 == null) {
         return null;
      } else if (!Class362.method2()) {
         return var1;
      } else {
         String var2 = Class210.method2();
         if (var2 != null) {
            var1 = Class217.method3().matcher(var1).replaceAll("$1" + var2 + "$2");
         }

         return ArmorStandsCommand.q_(var1);
      }
   }

   @Inject(
      method = {"setColor"},
      at = {@At("HEAD")},
      remap = false
   )
   public void method0(float var1, float var2, float var3, float var4, CallbackInfo var5) {
      this.field3 = var1;
      this.field2 = var2;
      this.field4 = var3;
      this.field5 = var4;
   }

   @Redirect(
      method = {"renderStringAtPos"},
      at = @At(
   value = "INVOKE",
   target = "Ljava/lang/String;indexOf(I)I",
   ordinal = 0
)
   )
   public int indexOf(String var1, int var2) {
      if (OptimizationsModule.field0.method44() && var2 >= 65 && var2 <= 90) {
         var2 += 32;
      }

      return var1.indexOf(var2);
   }

   @ModifyVariable(
      method = {"getStringWidth"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private String text1(String var1) {
      if (var1 == null) {
         return null;
      } else {
         return !Class362.method2() ? var1 : ArmorStandsCommand.q_(var1);
      }
   }

   @Redirect(
      method = {"renderStringAtPos"},
      at = @At(
   value = "INVOKE",
   target = "Ljava/lang/String;toLowerCase(Ljava/util/Locale;)Ljava/lang/String;"
)
   )
   public String onRender(String var1, Locale var2) {
      return var1;
   }

   @Inject(
      method = {"renderStringAtPos"},
      at = {@At("HEAD")}
   )
   public void method1(String var1, boolean var2, CallbackInfo var3) {
      if (var1 != null) {
         this.field1 = 0;
         this.field0.clear();
         Iterator var4 = Class265.field1.values().iterator();

         while(true) {
            Class262 var5;
            do {
               if (!var4.hasNext()) {
                  return;
               }

               var5 = (Class262)var4.next();
            } while(var5.method3() == -1);

            Matcher var6 = var5.y_().matcher(var1);

            while(var6.find()) {
               this.field0.add(new int[]{var6.start(), var6.start() + var5.method45().length(), var5.method3()});
            }
         }
      }
   }

   @Inject(
      method = {"renderStringAtPos"},
      at = {@At(
   value = "INVOKE",
   target = "Ljava/lang/String;indexOf(I)I",
   ordinal = 0
)}
   )
   public void method2(String var1, boolean var2, CallbackInfo var3) {
      this.field1 += 2;
   }

   @Redirect(
      method = {"getCharWidth"},
      at = @At(
   value = "INVOKE",
   target = "Ljava/lang/String;indexOf(I)I",
   ordinal = 0
)
   )
   public int indexOfWidth(String var1, int var2) {
      return OptimizationsModule.field0.method44() && var2 >= 33 && var2 <= 126 ? var2 : var1.indexOf(var2);
   }
}
