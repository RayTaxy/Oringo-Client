package oringo.mixin;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import map.Class516;
import map.Class61;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ItemStack.class})
public class ItemStackMixin2 implements Class516 {
   private final AtomicInteger field0 = new AtomicInteger(-1);
   private boolean field1 = true;

   private boolean compile(Tessellator var1, WorldRenderer var2) {
      IBakedModel var3 = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel((ItemStack)this);
      if (var3 == null) {
         var2.finishDrawing();
         var2.reset();
         return false;
      } else {
         var2.begin(7, DefaultVertexFormats.ITEM);
         EnumFacing[] var4 = EnumFacing.values();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            EnumFacing var7 = var4[var6];
            List var8 = var3.getFaceQuads(var7);
            if (var8 == null) {
               var2.finishDrawing();
               var2.reset();
               return false;
            }

            Iterator var9 = var8.iterator();

            while(var9.hasNext()) {
               BakedQuad var10 = (BakedQuad)var9.next();
               var2.addVertexData(var10.getVertexData());
               var2.putColor4(-1);
               Vec3i var11 = var10.getFace().getDirectionVec();
               var2.putNormal((float)var11.getX(), (float)var11.getY(), (float)var11.getZ());
            }
         }

         List var12 = var3.getGeneralQuads();
         Iterator var13 = var12.iterator();

         while(var13.hasNext()) {
            BakedQuad var14 = (BakedQuad)var13.next();
            var2.addVertexData(var14.getVertexData());
            var2.putColor4(-1);
            Vec3i var15 = var14.getFace().getDirectionVec();
            var2.putNormal((float)var15.getX(), (float)var15.getY(), (float)var15.getZ());
         }

         this.field0.set(GLAllocation.generateDisplayLists(1));
         Class61.method0(this.field0);
         GL11.glNewList(this.field0.get(), 4864);
         var1.draw();
         GL11.glEndList();
         return true;
      }
   }

   public boolean method0(TransformType var1) {
      if (var1 != TransformType.THIRD_PERSON) {
         return false;
      } else {
         Tessellator var2 = Tessellator.getInstance();
         WorldRenderer var3 = var2.getWorldRenderer();
         if (this.field1 && this.field0.get() == -1) {
            this.field1 = this.compile(var2, var3);
            return false;
         } else {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            GlStateManager.enableCull();
            GlStateManager.callList(this.field0.get());
            GlStateManager.cullFace(1029);
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            TextureManager var4 = Minecraft.getMinecraft().getTextureManager();
            var4.bindTexture(TextureMap.locationBlocksTexture);
            var4.getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
            GlStateManager.popMatrix();
            return true;
         }
      }
   }
}
