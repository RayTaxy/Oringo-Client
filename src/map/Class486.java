package map;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class210;
import oringo.module.Category;
import oringo.module.GiftESPModule;
import oringo.module.RenderChunkBoundsModule;
import oringo.module.SubCategory;
import oringo.module.ThornAimbotModule;
import oringo.module.TrailModule;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class Class486 extends Class408 {
   public final BooleanSetting field0 = new BooleanSetting("Rotation", false);
   public final ButtonSetting field1 = new ButtonSetting("Remove keybinding", this::lambda$new$5);
   public final DoubleSetting field2 = new DoubleSetting("Yaw", 0.0D, -180.0D, 180.0D, 0.1D, this::lambda$new$1);
   public final StringSetting field3 = (StringSetting)(new StringSetting("Item")).method2("The item that is used, skyblock ID can be used");
   public final DoubleSetting field4 = new DoubleSetting("Pitch", 0.0D, -90.0D, 90.0D, 0.1D, this::lambda$new$3);
   public final BooleanSetting field5 = new BooleanSetting("Add to current yaw", false, this::lambda$new$2);
   public final BooleanSetting field6 = (BooleanSetting)(new BooleanSetting("Interact with blocks", false)).method2("Allows keybinds to interact with blocks.\nUse this option for items like Superboom TNT");
   public final BooleanSetting field7 = new BooleanSetting("Add to current pitch", false, this::lambda$new$4);
   public final Class447 field8 = new Class447();
   public final StringSetting field9 = (StringSetting)(new StringSetting("When item held")).method2("Only activates when specified item is held");
   public final EnumSetting field10 = (EnumSetting)(new EnumSetting("Action", "Right", new String[]{"Right", "Left", "Swing"})).method2("Should be changed depending on how the item ability is activated.\nRight - Right click\nLeft - Swings and attacks entities\nSwing - Swings, but doesn't attack entities");
   public final EnumSetting field11 = (EnumSetting)(new EnumSetting("Mode", "Press", new String[]{"Press", "Held", "Toggle"})).method2("When the keybind is activate\nPress - Activates when the key is pressed\nHeld - Activates while the key is held\nToggle - Activates until the key is pressed again");
   public final DoubleSetting field14 = (DoubleSetting)(new DoubleSetting("Delay", 50.0D, 0.0D, 5000.0D, 50.0D, this::lambda$new$0)).method2("Delay in milliseconds");

   public static void method0(ItemStack var0, Class296 var1, int var2) {
      ThornAimbotModule.method0(var0, (float)(var1.method0() / (long)var2));
   }

   public Boolean lambda$new$4() {
      return !this.field0.method1();
   }

   public static int method0(BlockPos var0, BlockPos var1) {
      int var2 = var0.getX() - var1.getX();
      int var3 = var0.getZ() - var1.getZ();
      return var2 * var2 + var3 * var3;
   }

   public Boolean lambda$new$1() {
      return !this.field0.method1();
   }

   public String d_() {
      return this.field3.F_() ? "Keybind " + (Class362.E_().indexOf(this) + 1) : this.field3.method1();
   }

   public boolean method0(int var1) {
      // $FF: Couldn't be decompiled
   }

   public boolean lambda$onMotionUpdate$6(ItemStack var1) {
      return GiftESPModule.method0(var1, this.field3.method1());
   }

   public Class486(String var1) {
      super(var1, Category.keybinds, SubCategory.keybinds, "Ability keybinds");
      this.method0((Setting[])(new Setting[]{this.field3, this.field9, this.field11, this.field6, this.field14, this.field10, this.field0, this.field2, this.field5, this.field4, this.field7, this.field1}));
      this.field59.method0(true);
   }

   public Boolean lambda$new$0() {
      return this.field11.method0(0);
   }

   public Boolean lambda$new$3() {
      return !this.field0.method1();
   }

   public void lambda$new$5() {
      Class362.method1(this);
   }

   public static Vec3 method0(Vec3 var0) {
      return var0.addVector(-TrailModule.field58.getRenderManager().viewerPosX, -TrailModule.field58.getRenderManager().viewerPosY, -TrailModule.field58.getRenderManager().viewerPosZ);
   }

   public Boolean lambda$new$2() {
      return !this.field0.method1();
   }

   public boolean method15() {
      return this.field11.method0(2) && this.method7() || this.field11.method0(1) && this.method1() || this.field11.method0(0) && this.method8();
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.method15()) {
         if (this.field8.a_((long)this.field14.method3()) || this.field11.method0(0)) {
            if (!this.field3.F_() && RenderChunkBoundsModule.method5()) {
               ItemStack var2 = field58.thePlayer.getHeldItem();
               if (this.field9.F_() || var2 != null && GiftESPModule.method0(var2, this.field9.method1())) {
                  int var3 = TrailModule.method0(this::lambda$onMotionUpdate$6);
                  if (var3 != -1 && this.method0(var3)) {
                     this.field8.o_();
                     if (this.field0.method1()) {
                        float var4 = this.field5.method1() ? var1.t_ + this.field2.method1() : this.field2.method1();

                        float var5;
                        for(var5 = this.field7.method1() ? var1.bF_ + this.field4.method1() : this.field4.method1(); var5 > 90.0F; var4 += 180.0F) {
                           var5 = 180.0F - var5;
                        }

                        while(var5 < -90.0F) {
                           var4 += 180.0F;
                           var5 += 180.0F;
                        }

                        var1.method0((new Class34(var4, var5)).method4());
                     }

                  }
               }
            }
         }
      }
   }
}
