package oringo.module;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import map.Class321;
import map.Class34;
import map.Class350;
import map.Class361;
import map.Class417;
import map.Class469;
import map.Class496;
import map.Class501;
import map.Class506;
import map.Class524;
import map.Class6;
import map.Class94;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.util.vector.Vector3f;
import oringo.command.IRCNameCommand;
import oringo.command.LogsCommand;
import oringo.command.MessageCommand;
import oringo.command.MoveItemCommand;
import oringo.command.ReplyCommand;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class TerminatorAuraModule extends Module {
   public final EnumSetting field0 = new EnumSetting("Rotation", "Bow", new String[]{"Bow", "Middle"});
   public final BooleanSetting field1 = new BooleanSetting("Disable in boss", false);
   public final StringSetting field2 = new StringSetting("Custom Item");
   public final EnumSetting field3 = new EnumSetting("Mouse", "Right", new String[]{"Left", "Right"});
   public final DoubleSetting field4 = new DoubleSetting("Range", 15.0D, 5.0D, 50.0D, 1.0D);
   public final BooleanSetting field5 = new BooleanSetting("Boss Lock", true);
   public final BooleanSetting field6 = new BooleanSetting("Disable on world change", false);
   public final BooleanSetting field7 = new BooleanSetting("Only Dungeon", true);
   public static EntityLivingBase field24;
   public final DoubleSetting field9 = new DoubleSetting("Use delay", 3.0D, 1.0D, 10.0D, 1.0D);
   public final EnumSetting field10 = new EnumSetting("Mode", "Swap", new String[]{"Swap", "Held"});
   public static final HashMap field11 = new HashMap();
   public final DoubleSetting field12 = new DoubleSetting("Fov", 360.0D, 1.0D, 360.0D, 1.0D);
   public final BooleanSetting field13 = new BooleanSetting("Team Check", true);

   public boolean a_(EntityLivingBase var1) {
      if (LividFinderModule.field2.method1() && LividFinderModule.field0 != null) {
         return LividFinderModule.field0.equals(var1);
      } else if (var1 != field58.thePlayer && var1.deathTime == 0 && Class506.method0((Entity)var1) && !field11.containsKey(var1) && !(var1 instanceof EntityBlaze) && (!Class6.method0(var1) || !this.field13.method1()) && !(var1 instanceof EntityArmorStand) && field58.thePlayer.canEntityBeSeen(var1) && var1.getHealth() > 0.0F && (double)var1.getDistanceToEntity(field58.thePlayer) <= this.field4.method0() && (!(var1 instanceof EntityPlayer) && !(var1 instanceof EntityBat) && !(var1 instanceof EntityZombie) && !(var1 instanceof EntityGiantZombie) && !(var1 instanceof EntityEnderman) || !var1.isInvisible()) && !var1.getName().equals("Dummy") && !var1.getName().startsWith("Decoy")) {
         if (!Class417.method0(MessageCommand.method0((Entity)var1), (float)this.field12.method0())) {
            return false;
         } else if (var1 instanceof EntityMagmaCube && !field58.theWorld.getEntitiesWithinAABB(EntityArmorStand.class, var1.getEntityBoundingBox().expand(0.3D, 2.0D, 0.3D), TerminatorAuraModule::lambda$isValid$3).isEmpty()) {
            return false;
         } else {
            if (LogsCommand.method2().method44()) {
               boolean var2 = MoveItemCommand.method0((Entity)var1);
               if (NamesOnlyModule.bs_.method0(1) || var2) {
                  return NamesOnlyModule.bs_.method0(1) && var2;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static Double lambda$getTarget$2(EntityLivingBase var0, Object var1) {
      return IRCNameCommand.method0(MessageCommand.method0((Entity)(var0 != null ? var0 : (EntityLivingBase)var1)), MessageCommand.method0((Entity)((EntityLivingBase)var1)));
   }

   public static boolean lambda$isValid$3(EntityArmorStand var0) {
      return var0.getDisplayName().getFormattedText().contains("█");
   }

   public static boolean method0(BlockPos var0) {
      return Class469.field0.theWorld.isBlockLoaded(var0, false);
   }

   public TerminatorAuraModule() {
      super("Terminator Aura", 0, Category.dungeon, SubCategory.main, "Shoots at enemies with a shortbow");
      this.method0((Setting[])(new Setting[]{this.field0, this.field9, this.field4, this.field3, this.field12, this.field10, this.field2, this.field5, this.field7, this.field1, this.field6, this.field13}));
   }

   public boolean lambda$getTarget$1(Entity var1) {
      return var1 instanceof EntityLivingBase && this.a_((EntityLivingBase)var1);
   }

   @SubscribeEvent(
      priority = EventPriority.LOW
   )
   public void method0(Class210.Class1 var1) {
      if (KillAuraModule.field24 == null && (double)field58.thePlayer.ticksExisted % this.field9.method0() == 0.0D && (Class496.field5 || !this.field7.method1()) && (!this.field1.method1() || !Class496.field13)) {
         boolean var2 = field58.thePlayer.getHeldItem() != null && (field58.thePlayer.getHeldItem().getDisplayName().contains("Juju") || field58.thePlayer.getHeldItem().getDisplayName().contains("Terminator") || field58.thePlayer.getHeldItem().getDisplayName().contains("Spirit Bow") || !this.field2.method1().equals("") && field58.thePlayer.getHeldItem().getDisplayName().contains(this.field2.method1()));
         int var3 = field58.thePlayer.inventory.currentItem;
         if (this.field10.method4().equals("Swap")) {
            for(int var4 = 0; var4 < 9; ++var4) {
               if (field58.thePlayer.inventory.getStackInSlot(var4) != null && (field58.thePlayer.inventory.getStackInSlot(var4).getDisplayName().contains("Juju") || field58.thePlayer.inventory.getStackInSlot(var4).getDisplayName().contains("Terminator") || field58.thePlayer.inventory.getStackInSlot(var4).getDisplayName().contains("Spirit Bow") || !this.field2.F_() && field58.thePlayer.inventory.getStackInSlot(var4).getDisplayName().contains(this.field2.method1()))) {
                  var2 = true;
                  var3 = var4;
                  break;
               }
            }
         }

         if (var2) {
            field24 = this.method1(field24);
            if (field24 != null) {
               if (!Class361.method0((Class94)(this.field3.method0(0) ? new Class350(var3) : new Class501(var3)))) {
                  return;
               }

               Class34 var5 = this.field0.method0(0) ? Class321.method0((Entity)field24) : ReplyCommand.method0(new Vec3(field24.posX, field24.posY + (double)field24.getEyeHeight() / 2.0D, field24.posZ));
               var5.method4();
               var1.t_ = var5.method5();
               var1.bF_ = var5.method2();
            }

         }
      }
   }

   public EntityLivingBase method1(EntityLivingBase var1) {
      if (this.field5.method1() && var1 != null && AutoIceFillModule.method0((Entity)var1) && var1.getHealth() > 0.0F && !var1.isDead && var1.canEntityBeSeen(field58.thePlayer) && (double)var1.getDistanceToEntity(field58.thePlayer) < this.field4.method0()) {
         return var1;
      } else {
         field11.entrySet().removeIf(TerminatorAuraModule::lambda$getTarget$0);
         Stream var10000 = field58.theWorld.getLoadedEntityList().stream().filter(this::lambda$getTarget$1);
         EntityPlayerSP var10001 = field58.thePlayer;
         var10001.getClass();
         List var2 = (List)var10000.sorted(Comparator.comparingDouble(var10001::func_70032_d)).sorted(Comparator.comparing(TerminatorAuraModule::lambda$getTarget$2).reversed()).collect(Collectors.toList());
         Iterator var3 = var2.iterator();
         if (var3.hasNext()) {
            Entity var4 = (Entity)var3.next();
            field11.put((EntityLivingBase)var4, System.currentTimeMillis());
            return (EntityLivingBase)var4;
         } else {
            return null;
         }
      }
   }

   @SubscribeEvent
   public void a_(Class270 var1) {
      if (this.field6.method1()) {
         this.method1(false);
      }

   }

   public static boolean lambda$getTarget$0(Entry var0) {
      return System.currentTimeMillis() - (Long)var0.getValue() > 350L;
   }

   public static void method0(float var0, float var1, float var2, float var3) {
      Class524.method0(var0, var1, var2, var3, new Vector3f(0.0F, 1.0F, 1.0F), new Vector3f(0.0F, 1.0F, 1.0F), new Vector3f(1.0F, 1.0F, 1.0F), new Vector3f(1.0F, 1.0F, 1.0F));
   }
}
