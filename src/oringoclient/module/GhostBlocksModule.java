package oringo.module;

import com.mojang.authlib.properties.Property;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import map.Class12;
import map.Class208;
import map.Class25;
import map.Class361;
import map.Class408;
import map.Class447;
import map.Class469;
import map.Class496;
import map.Class501;
import map.Class94;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.io.IOUtils;
import oringo.event.Class16;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.event.Class274;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class GhostBlocksModule extends Class408 {
   public boolean field0;
   public final Class447 aF_ = new Class447();
   public static final HashMap aG_ = new HashMap();
   public final DoubleSetting field3 = new DoubleSetting("Delay", 200.0D, 0.0D, 1000.0D, 50.0D);
   public final BooleanSetting field4 = new BooleanSetting("Right click with pick", false);
   public final DoubleSetting field5 = new DoubleSetting("Range", 10.0D, 1.0D, 100.0D, 1.0D);
   public boolean field2 = false;
   public final BooleanSetting field7 = (BooleanSetting)(new BooleanSetting("Swing", true)).method2("Should the player swing to simulate a ghost pickaxe");
   public boolean field8 = false;
   public static final Set field9 = new HashSet();
   public final BooleanSetting field10 = (BooleanSetting)(new BooleanSetting("Cord blocks", true)).method2("Places ghost blocks so you can go to p3 early");
   public static final int[][] field11 = new int[][]{{275, 220, 231}, {275, 220, 232}, {299, 168, 243}, {299, 168, 244}, {299, 168, 246}, {299, 168, 247}, {299, 168, 247}, {300, 168, 247}, {300, 168, 246}, {300, 168, 244}, {300, 168, 243}, {298, 168, 247}, {298, 168, 246}, {298, 168, 244}, {298, 168, 243}, {287, 167, 240}, {288, 167, 240}, {289, 167, 240}, {290, 167, 240}, {291, 167, 240}, {292, 167, 240}, {293, 167, 240}, {294, 167, 240}, {295, 167, 240}, {290, 167, 239}, {291, 167, 239}, {292, 167, 239}, {293, 167, 239}, {294, 167, 239}, {295, 167, 239}, {290, 166, 239}, {291, 166, 239}, {292, 166, 239}, {293, 166, 239}, {294, 166, 239}, {295, 166, 239}, {290, 166, 240}, {291, 166, 240}, {292, 166, 240}, {293, 166, 240}, {294, 166, 240}, {295, 166, 240}};

   public boolean method0(BlockPos var1) {
      Block var2 = field58.theWorld.getBlockState(var1).getBlock();
      if (var2 == Blocks.skull) {
         TileEntitySkull var3 = (TileEntitySkull)field58.theWorld.getTileEntity(var1);
         if (var3.getSkullType() == 3 && var3.getPlayerProfile() != null && var3.getPlayerProfile().getProperties() != null) {
            Property var4 = (Property)WTapModule.method0((Iterable)var3.getPlayerProfile().getProperties().get("textures"));
            return var4 != null && var4.getValue().equals("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkYjRhZGZhOWJmNDhmZjVkNDE3MDdhZTM0ZWE3OGJkMjM3MTY1OWZjZDhjZDg5MzQ3NDlhZjRjY2U5YiJ9fX0=");
         }
      }

      return var2 == Blocks.lever || var2 == Blocks.chest || var2 == Blocks.trapped_chest || var2 == Blocks.air;
   }

   public GhostBlocksModule() {
      super("Ghost Blocks", 0, Category.dungeon, SubCategory.main, "Creates ghost blocks when pressed");
      this.method0((Setting[])(new Setting[]{this.field5, this.field3, this.field7, this.field10, this.field4}));
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Class16 var1) {
      if (var1.field2 != null && !this.field0 && var1.field2.getBlock() != Blocks.air) {
         if (field9.contains(var1.cB_)) {
            var1.method9();
            aG_.put(System.currentTimeMillis(), var1);
         }

      }
   }

   @SubscribeEvent
   public void method0(Class274 var1) {
      if (this.field4.method1() && field58.thePlayer.getHeldItem() != null && field58.thePlayer.getHeldItem().getItem() instanceof ItemPickaxe) {
         if (field58.objectMouseOver.typeOfHit == MovingObjectType.BLOCK && this.method0(field58.objectMouseOver.getBlockPos())) {
            return;
         }

         this.field8 = true;
         var1.method9();
      }

   }

   public static boolean lambda$onKey$0(Entry var0) {
      if (System.currentTimeMillis() - (Long)var0.getKey() > 250L) {
         field58.theWorld.setBlockState(((Class16)var0.getValue()).cB_, ((Class16)var0.getValue()).field2);
         return true;
      } else {
         return false;
      }
   }

   public static void method0(AxisAlignedBB var0, boolean var1) {
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      if (!var1) {
         GlStateManager.disableDepth();
         GlStateManager.depthMask(false);
      }

      GlStateManager.disableLighting();
      IceFillHelperModule.method0(var0);
      GlStateManager.enableTexture2D();
      if (!var1) {
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
      }

      GlStateManager.disableBlend();
   }

   public boolean c_() {
      return true;
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      aG_.clear();
      field9.clear();
   }

   public static float method0(ItemStack var0) {
      return var0.getItem() instanceof ItemTool ? (float)((double)((ItemTool)var0.getItem()).getToolMaterial().getHarvestLevel() + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, var0) * 0.75D) : 0.0F;
   }

   public static void method3() throws Exception {
      PrintWriter var0 = new PrintWriter(Files.newOutputStream((new File("textPackets.txt")).toPath()));
      ByteBuf var1 = Unpooled.wrappedBuffer(IOUtils.toByteArray(new GZIPInputStream(Files.newInputStream((new File("packetLog.log")).toPath()))));
      PacketBuffer var2 = new PacketBuffer(var1);

      for(int var3 = 0; var1.readableBytes() != 0; ++var3) {
         int var4 = var2.readVarIntFromBuffer();
         Packet var5 = EnumConnectionState.PLAY.getPacket(EnumPacketDirection.CLIENTBOUND, var4);
         var5.readPacketData(var2);
         var0.println(var3 + " " + AutoToolModule.method0(var5));
      }

      var0.close();
   }

   public static Class208 method5() {
      int var0 = (int)Class469.field0.thePlayer.posX;
      int var1 = (int)Class469.field0.thePlayer.posZ;
      Class12 var2 = FragHelperModule.method0(var0, var1);
      return Class25.method0((var2.method1() - -185) / 32, (var2.method0() - -185) / 32);
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.field10.method1() && Class496.field13) {
         int[][] var2 = field11;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            int[] var5 = var2[var4];
            if (field58.theWorld.getBlockState(new BlockPos(var5[0] - 199, var5[1], var5[2] - 199)).getBlock() != Blocks.air) {
               field58.theWorld.setBlockToAir(new BlockPos(var5[0] - 199, var5[1], var5[2] - 199));
            }
         }
      }

      this.field0 = true;
      aG_.entrySet().removeIf(GhostBlocksModule::lambda$onKey$0);
      this.field0 = false;
      if ((this.method1() || this.field8) && (this.aF_.method0(this.field3.method0()) || !this.field2)) {
         this.field8 = false;
         this.aF_.o_();
         Vec3 var6 = field58.thePlayer.getPositionEyes(0.0F);
         Vec3 var7 = field58.thePlayer.getLook(0.0F);
         Vec3 var8 = var6.addVector(var7.xCoord * this.field5.method0(), var7.yCoord * this.field5.method0(), var7.zCoord * this.field5.method0());
         BlockPos var9 = field58.theWorld.rayTraceBlocks(var6, var8, true, false, true).getBlockPos();
         if (this.method0(var9)) {
            return;
         }

         field58.theWorld.setBlockToAir(var9);
         if (this.field7.method1()) {
            Class361.method0((Class94)(new Class501()));
         }

         field9.add(var9);
      }

      this.field2 = this.method1();
   }

   @SubscribeEvent
   public void method0(PlayerInteractEvent var1) {
      if (var1.action == Action.RIGHT_CLICK_BLOCK && var1.pos != null && var1.face != null) {
         field9.remove(var1.pos.offset(var1.face));
      }
   }

   public void b_() {
      aG_.clear();
      field9.clear();
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook) {
         aG_.clear();
      }

   }
}
