package oringo.command;

import java.util.Iterator;
import java.util.List;
import map.Class168;
import map.Class82;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.Vec3i;
import oringo.module.ClickGuiModule;
import oringo.module.PopupAnimationModule;
import oringo.module.XRayModule;

public class XRayCommand extends Command {
   public static void method0(List var0) {
      Iterator var1 = var0.iterator();

      while(var1.hasNext()) {
         BakedQuad var2 = (BakedQuad)var1.next();
         Class168.field0.addVertexData(var2.getVertexData());
         Class168.field0.putColorRGB_F4(1.0F, 1.0F, 1.0F);
         Vec3i var3 = var2.getFace().getDirectionVec();
         Class168.field0.putNormal((float)var3.getX(), (float)var3.getY(), (float)var3.getZ());
      }

   }

   public void method0(String[] var1) {
      if (var1.length == 0) {
         Iterator var2 = XRayModule.field0.iterator();

         while(var2.hasNext()) {
            Block var3 = (Block)var2.next();
            PopupAnimationModule.method2(var3.getRegistryName());
         }

         PopupAnimationModule.method2(String.format("%s%s add/remove", ClickGuiModule.field25.method1(), this.method5()[0]));
      } else {
         String var6 = var1[0];
         byte var7 = -1;
         switch(var6.hashCode()) {
         case -934610812:
            if (var6.equals("remove")) {
               var7 = 1;
            }
            break;
         case 96417:
            if (var6.equals("add")) {
               var7 = 0;
            }
         }

         Block var4;
         switch(var7) {
         case 0:
            if (var1.length == 2) {
               var4 = Block.getBlockFromName(var1[1]);
               if (var4 != null) {
                  XRayModule.field0.add(var4);
                  method2("Successfully added " + var1[1] + " to list");
               } else {
                  method2("Couldn't find that block");
               }
            } else {
               PopupAnimationModule.method2(String.format("Usage: %s%s add name", ClickGuiModule.field25.method1(), this.method5()[0]));
            }
            break;
         case 1:
            if (var1.length == 2) {
               var4 = Block.getBlockFromName(var1[1]);
               if (var4 != null) {
                  XRayModule.field0.remove(var4);
               }
            }
            break;
         default:
            Iterator var8 = XRayModule.field0.iterator();

            while(var8.hasNext()) {
               Block var5 = (Block)var8.next();
               PopupAnimationModule.method2(var5.getRegistryName());
            }
         }

         if (field9.renderGlobal != null) {
            field9.renderGlobal.loadRenderers();
         }
      }

   }

   public XRayCommand() {
      super("xray");
   }

   public static String method2() {
      return Class82.field0;
   }

   public String method1() {
      return null;
   }
}
