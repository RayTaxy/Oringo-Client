package map;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import org.apache.commons.lang3.reflect.FieldUtils;
import oringo.Oringo;
import oringo.command.LivingMetalMinerModule;
import oringo.module.AOTVReturnModule;
import oringo.module.AimAssistModule;
import oringo.module.AnimationsModule;
import oringo.module.AntiNickerModule;
import oringo.module.AntiNukekubiModule;
import oringo.module.AntiObsidianModule;
import oringo.module.AntiStuckModule;
import oringo.module.AntiTentacleModule;
import oringo.module.ArcadeESPModule;
import oringo.module.AutoAlignModule;
import oringo.module.AutoArrowModule;
import oringo.module.AutoBeamsModule;
import oringo.module.AutoBlazeModule;
import oringo.module.AutoCloakModule;
import oringo.module.AutoCloseModule;
import oringo.module.AutoCombineModule;
import oringo.module.AutoConversationModule;
import oringo.module.AutoCraftModule;
import oringo.module.AutoCrystalModule;
import oringo.module.AutoDailyModule;
import oringo.module.AutoEchoModule;
import oringo.module.AutoEnchantingModule;
import oringo.module.AutoFishModule;
import oringo.module.AutoFrozilleModule;
import oringo.module.AutoHackModule;
import oringo.module.AutoHarpModule;
import oringo.module.AutoHealModule;
import oringo.module.AutoIceFillModule;
import oringo.module.AutoIcePathModule;
import oringo.module.AutoIceSprayModule;
import oringo.module.AutoJaxRangeModule;
import oringo.module.AutoJoeyModule;
import oringo.module.AutoJoinSkyblockModule;
import oringo.module.AutoMaddoxModule;
import oringo.module.AutoMaskModule;
import oringo.module.AutoMiddleModule;
import oringo.module.AutoPhobiaModule;
import oringo.module.AutoPowderChestModule;
import oringo.module.AutoQuizModule;
import oringo.module.AutoRabbitModule;
import oringo.module.AutoReconnectModule;
import oringo.module.AutoRelayModule;
import oringo.module.AutoRequeueModule;
import oringo.module.AutoRogueModule;
import oringo.module.AutoSalvageModule;
import oringo.module.AutoScribeModule;
import oringo.module.AutoSimonModule;
import oringo.module.AutoSoulcryModule;
import oringo.module.AutoStunSnakeModule;
import oringo.module.AutoTerminalsModule;
import oringo.module.AutoToolModule;
import oringo.module.AutoVisitorsModule;
import oringo.module.AutoWaterModule;
import oringo.module.AutoWeirdosModule;
import oringo.module.BacteNotificationsModule;
import oringo.module.BarPhaseModule;
import oringo.module.BearAuraModule;
import oringo.module.BlazeSwapperModule;
import oringo.module.BlockHitModule;
import oringo.module.BlockNukerModule;
import oringo.module.BloodAimbotModule;
import oringo.module.BossBarModule;
import oringo.module.BridgeHelperModule;
import oringo.module.BrushModule;
import oringo.module.BuildGuesserModule;
import oringo.module.CameraModule;
import oringo.module.Category;
import oringo.module.ChatBypassModule;
import oringo.module.ChestESPModule;
import oringo.module.ChestStealerModule;
import oringo.module.ChinaHatModule;
import oringo.module.CinderbatESPModule;
import oringo.module.ClickGuiModule;
import oringo.module.ColorCodesModule;
import oringo.module.CommandBindingModule;
import oringo.module.CookieClickerModule;
import oringo.module.CreateNewkeybindModule;
import oringo.module.CreeperNametagsModule;
import oringo.module.CropNukerModule;
import oringo.module.CustomESPModule;
import oringo.module.CustomHubMap;
import oringo.module.Dadudze2Module;
import oringo.module.DadudzeModule;
import oringo.module.DelaysModule;
import oringo.module.DerpModule;
import oringo.module.DilloFinderModule;
import oringo.module.DisablerModule;
import oringo.module.DiscordRPCModule;
import oringo.module.DivanChestFinderModule;
import oringo.module.DojoHelperModule;
import oringo.module.DoorESPModule;
import oringo.module.DragonHitboxesModule;
import oringo.module.DungeonESPModule;
import oringo.module.DungeonMapModule;
import oringo.module.EagleModule;
import oringo.module.EnigmaSoulESPModule;
import oringo.module.EvidenceForgerModule;
import oringo.module.ExtraFeaturesModule;
import oringo.module.FDSwapperModule;
import oringo.module.FairySoulESPModule;
import oringo.module.FastBreakModule;
import oringo.module.FlightModule;
import oringo.module.FragHelperModule;
import oringo.module.FreeCamModule;
import oringo.module.FrozenTreasureESPModule;
import oringo.module.FullBrightModule;
import oringo.module.GardenHelperModule;
import oringo.module.GemstoneESPModule;
import oringo.module.GhostBlocksModule;
import oringo.module.GiantsModule;
import oringo.module.GiftESPModule;
import oringo.module.GlintModule;
import oringo.module.GrottoNotificationModule;
import oringo.module.GuiMoveModule;
import oringo.module.HClipModule;
import oringo.module.HidePlayersModule;
import oringo.module.HideSummonsModule;
import oringo.module.HostageESPModule;
import oringo.module.IRCModule;
import oringo.module.IceFillHelperModule;
import oringo.module.InterfacesModule;
import oringo.module.InventoryHUDModule;
import oringo.module.InventoryManagerModule;
import oringo.module.KillAuraModule;
import oringo.module.KillInsultsModule;
import oringo.module.KuudraHelperModule;
import oringo.module.LightningDetectModule;
import oringo.module.LividFinderModule;
import oringo.module.MacroHelperModule;
import oringo.module.MinigameAimbotModule;
import oringo.module.MirrorverseHelperModule;
import oringo.module.MithrilMacroModule;
import oringo.module.ModHiderModule;
import oringo.module.Module;
import oringo.module.ModuleListModule;
import oringo.module.MotionBlurModule;
import oringo.module.MurderMysteryModule;
import oringo.module.MushroomESPModule;
import oringo.module.MythologicalHelperModule;
import oringo.module.NBTCopyModule;
import oringo.module.NamesOnlyModule;
import oringo.module.NametagsModule;
import oringo.module.NickHiderModule;
import oringo.module.NoBlockModule;
import oringo.module.NoBreakResetModule;
import oringo.module.NoCarpetModule;
import oringo.module.NoDebuffModule;
import oringo.module.NoFoliageModule;
import oringo.module.NoJumpBoostModule;
import oringo.module.NoRenderModule;
import oringo.module.NoRotateModule;
import oringo.module.NoSlowModule;
import oringo.module.NoVoidModule;
import oringo.module.NucleusHelperModule;
import oringo.module.OdonataESPModule;
import oringo.module.OptimizationsModule;
import oringo.module.PVPInfoModule;
import oringo.module.PacketLoggerModule;
import oringo.module.PeltESPModule;
import oringo.module.PestESPModule;
import oringo.module.PinglessHardstoneModule;
import oringo.module.PlayerESPModule;
import oringo.module.PlusColorChangerModule;
import oringo.module.PopupAnimationModule;
import oringo.module.ProfilerModule;
import oringo.module.RancherSpooferModule;
import oringo.module.RatProtectionModule;
import oringo.module.ReachModule;
import oringo.module.RenderBarriersModule;
import oringo.module.RenderChunkBoundsModule;
import oringo.module.ResetVLModule;
import oringo.module.RiftFarmingHelperModule;
import oringo.module.RodStackerModule;
import oringo.module.ScoreboardModule;
import oringo.module.ScreenShotModule;
import oringo.module.SecretAuraModule;
import oringo.module.SecretHitboxesModule;
import oringo.module.ShinyBlocksModule;
import oringo.module.ShortbowTriggerbotModule;
import oringo.module.SimulatorAuraModule;
import oringo.module.SpeedModule;
import oringo.module.SprintModule;
import oringo.module.StaffAnalyserModule;
import oringo.module.SumoFencesModule;
import oringo.module.TNTRunPingModule;
import oringo.module.TerminalAuraModule;
import oringo.module.TerminalESPModule;
import oringo.module.TerminatorAuraModule;
import oringo.module.TestModule;
import oringo.module.ThornAimbotModule;
import oringo.module.ThystHiderModule;
import oringo.module.TicTacToeModule;
import oringo.module.TimeChangerModule;
import oringo.module.TrailModule;
import oringo.module.TrajectoryModule;
import oringo.module.VClipModule;
import oringo.module.VampireHelperModule;
import oringo.module.VanquisherESPModule;
import oringo.module.VelocityModule;
import oringo.module.WTapModule;
import oringo.module.XRayModule;

public class Class362 {
   public static ScreenShotModule field0;
   public static NoSlowModule field1;
   public static AutoPowderChestModule field2;
   public static NucleusHelperModule field3;
   public static GrottoNotificationModule field4;
   public static FlightModule field5;
   public static DisablerModule field6;
   public static ClickGuiModule field7;
   public static ReachModule field8;
   public static GemstoneESPModule field9;
   public static TerminatorAuraModule field10;
   public static ArcadeESPModule field11;
   public static PeltESPModule field12;
   public static MurderMysteryModule field13;
   public static DerpModule field14;
   public static AutoRelayModule field15;
   public static GlintModule field16;
   public static SprintModule field17;
   public static AnimationsModule field18;
   public static ModHiderModule field19;
   public static SpeedModule field20;
   public static XRayModule field21;
   public static NametagsModule field22;
   public static Class514 field23;
   public static SecretAuraModule field24;
   public static KillAuraModule field25;
   public static ExtraFeaturesModule field26;
   public static FullBrightModule field27;
   private static final List field63 = new CopyOnWriteArrayList();
   public static BloodAimbotModule field28;
   public static PVPInfoModule field29;
   public static AutoVisitorsModule field30;
   public static BlockHitModule field31;
   public static NickHiderModule field32;
   public static NoFoliageModule field33;
   public static ColorCodesModule field34;
   public static FastBreakModule field35;
   public static NoDebuffModule field36;
   public static NoBreakResetModule field37;
   public static AutoSalvageModule field38;
   public static AutoEnchantingModule field39;
   public static PopupAnimationModule field40;
   public static CustomESPModule field41;
   public static BrushModule field42;
   public static FreeCamModule field43;
   public static InventoryHUDModule field44;
   public static SecretHitboxesModule field45;
   public static BossBarModule field46;
   public static NoRenderModule field47;
   public static InterfacesModule field48;
   public static DilloFinderModule field49;
   public static GardenHelperModule field50;
   public static AOTVReturnModule field51;
   public static VelocityModule field52;
   private static boolean field6;
   private static final HashMap field65 = new HashMap();
   public static GiantsModule field53;
   public static MithrilMacroModule field54;
   public static IRCModule field55;
   public static AutoEchoModule field56;
   public static GuiMoveModule field57;
   public static FragHelperModule field58;
   public static NoRotateModule field59;
   public static BuildGuesserModule field60;
   public static CameraModule field61;
   public static CustomHubMap field62;

   public static void method0(Class486 var0) {
      field63.add(var0);
   }

   private static void field0(Module var0) {
      if (field65.containsKey(var0.getClass())) {
         throw new IllegalStateException(String.format("Tried to register %s twice!", var0.method45()));
      } else {
         field65.put(var0.getClass(), var0);
      }
   }

   private static boolean field0(Category var0, Module var1) {
      return var1.method37() == var0;
   }

   public static List E_() {
      return field63;
   }

   public static List method1() {
      return method0(Module::method44, false);
   }

   public static void method0(int var0) {
      if (var0 != 0) {
         Iterator var1 = method0().iterator();

         while(var1.hasNext()) {
            Module var2 = (Module)var1.next();
            if (var2.method46() == var0) {
               if (var2 instanceof Class408) {
                  ((Class408)var2).method9();
               } else {
                  var2.method40();
                  if (var2.cW_.equals("Click Gui")) {
                     return;
                  }

                  if (ClickGuiModule.field23.method1()) {
                     SecretHitboxesModule.method0(var2.method45() + (var2.method44() ? " enabled!" : " disabled!"), 2500);
                  }
               }
            }

            if (var2.method44()) {
               var2.method4(var0);
            }
         }

      }
   }

   public static boolean method2() {
      return field6;
   }

   public static void method1(Class486 var0) {
      var0.method1(false);
      field63.remove(var0);
   }

   private static void lambda$getModules$2(Predicate var0, List var1, Class486 var2) {
      if (var0 == null || var0.test(var2)) {
         var1.add(var2);
      }

   }

   private static void field0(Predicate var0, List var1, Class var2, Module var3) {
      if (var0 == null || var0.test(var3)) {
         var1.add(var3);
      }

   }

   public static void z_() {
      if (!Loader.instance().hasReachedState(LoaderState.PREINITIALIZATION)) {
         throw new IllegalStateException("Modules initialized too early");
      } else {
         field0(new AutoCloseModule());
         field0(new AutoArrowModule());
         field0(new NoRenderModule());
         field0(new NoVoidModule());
         field0(OptimizationsModule.field0);
         field0(new ClickGuiModule());
         field0(new GrottoNotificationModule());
         field0(new KillAuraModule());
         field0(new NoRotateModule());
         field0(AntiStuckModule.field0);
         field0(new NoFoliageModule());
         field0(new VelocityModule());
         field0(new BloodAimbotModule());
         field0(new MinigameAimbotModule());
         field0(new AutoJoinSkyblockModule());
         field0(new PVPInfoModule());
         field0(new GlintModule());
         field0(new AntiNickerModule());
         field0(new EnigmaSoulESPModule());
         field0(RenderBarriersModule.field0);
         field0(new TerminalAuraModule());
         field0(new ChatBypassModule());
         field0(new NBTCopyModule());
         field0(new TerminatorAuraModule());
         field0(new AutoEchoModule());
         field0(new DivanChestFinderModule());
         field0(new SecretAuraModule());
         field0(new AutoBeamsModule());
         field0(new BridgeHelperModule());
         field0(new AutoIceFillModule());
         field0(new GiftESPModule());
         field0(new Class514());
         field0(new DungeonESPModule());
         field0(new ColorCodesModule());
         field0(new FragHelperModule());
         field0(new LividFinderModule());
         field0(new IRCModule());
         field0(new EagleModule());
         field0(new HideSummonsModule());
         field0(new ExtraFeaturesModule());
         field0(new AutoFishModule());
         field0(new FairySoulESPModule());
         field0(new ScreenShotModule());
         field0(new GhostBlocksModule());
         field0(new DoorESPModule());
         field0(new AutoCombineModule());
         field0(new SumoFencesModule());
         field0(new MacroHelperModule());
         field0(new DojoHelperModule());
         field0(new RatProtectionModule());
         field0(new AutoDailyModule());
         field0(PlusColorChangerModule.field2);
         if ((new File("C:\\Users\\Dadoodze")).exists()) {
            field0(new DadudzeModule());
            field0(new Dadudze2Module());
            field0(new PacketLoggerModule());
            field0(new BacteNotificationsModule());
            field0(new EvidenceForgerModule());
         }

         field0(new AutoCloakModule());
         field0(new CinderbatESPModule());
         field0(new ModHiderModule());
         field0(TimeChangerModule.cO_);
         field0(new TNTRunPingModule());
         field0(new NoSlowModule());
         field0(new SprintModule());
         field0(new ReachModule());
         field0(new CropNukerModule());
         field0(new HostageESPModule());
         field0(new BlockNukerModule());
         field0(new AutoSimonModule());
         field0(new InventoryManagerModule());
         field0(new ChestStealerModule());
         field0(new PlayerESPModule());
         field0(new GemstoneESPModule());
         field0(new VClipModule());
         field0(new FastBreakModule());
         field0(new NickHiderModule());
         field0(new ChinaHatModule());
         field0(new AutoJaxRangeModule());
         field0(new AOTVReturnModule());
         field0(new MithrilMacroModule());
         field0(new DiscordRPCModule());
         field0(new InventoryHUDModule());
         field0(new CustomESPModule());
         field0(new ArcadeESPModule());
         field0(new FlightModule());
         field0(new AutoRogueModule());
         field0(new BuildGuesserModule());
         field0(new IceFillHelperModule());
         field0(new PeltESPModule());
         field0(new BossBarModule());
         field0(new NoBlockModule());
         field0(new MythologicalHelperModule());
         field0(new AnimationsModule());
         field0(LividFinderModule.method5());
         field0(DungeonMapModule.method7());
         field0(new WTapModule());
         field0(new AutoToolModule());
         field0(new CameraModule());
         field0(new InterfacesModule());
         field0(new DragonHitboxesModule());
         field0(new MotionBlurModule());
         field0(new FreeCamModule());
         field0(new MurderMysteryModule());
         field0(new ChestESPModule());
         field0(new AutoSalvageModule());
         field0(new ShinyBlocksModule());
         field0(new FrozenTreasureESPModule());
         field0(new DilloFinderModule());
         field0(new CreeperNametagsModule());
         field0(new CommandBindingModule());
         field0(new MushroomESPModule());
         field0(new CookieClickerModule());
         field0(new GiantsModule());
         field0(new DisablerModule());
         field0(new GuiMoveModule());
         field0(new BlockHitModule());
         field0(new SpeedModule());
         field0(new PopupAnimationModule());
         field0(new AntiNukekubiModule());
         field0(new TrailModule());
         field0(new Class47());
         field0(new StaffAnalyserModule());
         field0(new Class302());
         field0(new FDSwapperModule());
         field0(new AimAssistModule());
         field0(Class106.method0());
         field0(new AutoHealModule());
         field0(new VanquisherESPModule());
         field0(new PinglessHardstoneModule());
         field0(new AutoPowderChestModule());
         field0(new NametagsModule());
         field0(new NoDebuffModule());
         field0(new BlazeSwapperModule());
         field0(new BarPhaseModule());
         field0(new Class95());
         field0(new KillInsultsModule());
         field0(DelaysModule.field2);
         field0(new XRayModule());
         field0(new HidePlayersModule());
         field0(new SimulatorAuraModule());
         field0(new ResetVLModule());
         field0(new RodStackerModule());
         field0(new NamesOnlyModule());
         field0(new NoCarpetModule());
         field0(new AutoCrystalModule());
         field0(new CustomHubMap());
         field0(new HClipModule());
         field0(new AutoQuizModule());
         field0(new AutoWeirdosModule());
         field0(new TicTacToeModule());
         field0(new AutoAlignModule());
         field0(new AutoReconnectModule());
         field0(new LightningDetectModule());
         field0(new FullBrightModule());
         field0(new AutoMaskModule());
         field0(new AntiObsidianModule());
         field0(new AutoCraftModule());
         field0(new AutoTerminalsModule());
         field0(new AutoMaddoxModule());
         field0(new AutoHackModule());
         field0(new AutoJoeyModule());
         field0(new AutoHarpModule());
         field0(new AutoVisitorsModule());
         field0(new AutoConversationModule());
         field0(new AutoFrozilleModule());
         field0(new AutoStunSnakeModule());
         field0(new LivingMetalMinerModule());
         field0(new AutoRelayModule());
         field0(new AutoEnchantingModule());
         field0(new DungeonMapModule());
         field0(new AutoMiddleModule());
         field0(new AntiTentacleModule());
         field0(new AutoIceSprayModule());
         field0(new RiftFarmingHelperModule());
         field0(new AutoRabbitModule());
         field0(new AutoBlazeModule());
         field0(new CreateNewkeybindModule());
         field0(new NucleusHelperModule());
         field0(new AutoIcePathModule());
         field0(new Class475());
         field0(new BearAuraModule());
         field0(new NoJumpBoostModule());
         field0(new ThornAimbotModule());
         field0(new NoBreakResetModule());
         field0(new GardenHelperModule());
         field0(new KuudraHelperModule());
         field0(new BrushModule());
         field0(new AutoSoulcryModule());
         field0(new ModuleListModule());
         field0(new ScoreboardModule());
         field0(new Class481());
         field0(new TrajectoryModule());
         field0(new ShortbowTriggerbotModule());
         field0(new TerminalESPModule());
         field0(new AutoWaterModule());
         field0(new SecretHitboxesModule());
         field0(new VampireHelperModule());
         field0(new MirrorverseHelperModule());
         field0(new OdonataESPModule());
         field0(new AutoScribeModule());
         field0(new AutoRequeueModule());
         field0(new AutoPhobiaModule());
         field0(new Class426());
         field0(new ThystHiderModule());
         field0(new Class393());
         field0(new Class490());
         field0(new RancherSpooferModule());
         field0(new PestESPModule());
         if (Oringo.cV_) {
            field0(new RenderChunkBoundsModule());
            field0(new TestModule());
            field0(ProfilerModule.field10 = new ProfilerModule());
         }

         Field[] var0 = Class362.class.getDeclaredFields();
         int var1 = var0.length;

         for(int var2 = 0; var2 < var1; ++var2) {
            Field var3 = var0[var2];
            if (Modifier.isStatic(var3.getModifiers()) && Modifier.isPublic(var3.getModifiers())) {
               try {
                  if (Module.class.isAssignableFrom(var3.getType()) && var3.get((Object)null) == null) {
                     if (Modifier.isFinal(var3.getModifiers())) {
                        FieldUtils.removeFinalModifier(var3);
                     }

                     var3.set((Object)null, method0(var3.getType()));
                  }
               } catch (IllegalAccessException | IllegalStateException var5) {
               }
            }
         }

         field6 = true;
      }
   }

   public static List method0(Category var0) {
      return method0(Class362::field0, true);
   }

   public static List method0() {
      return method0((Predicate)null, true);
   }

   public static void method5() {
      Iterator var0 = field63.iterator();

      while(var0.hasNext()) {
         Class486 var1 = (Class486)var0.next();
         var1.method1(false);
      }

      field63.clear();
   }

   public static Module method0(Class var0) {
      Module var1 = (Module)field65.get(var0);
      if (var1 == null) {
         throw new IllegalStateException(String.format("Unregistered module %s!", var0.getSimpleName()));
      } else {
         return var1;
      }
   }

   public static List method0(Predicate var0, boolean var1) {
      ArrayList var2 = new ArrayList();
      field65.forEach(Class362::field0);
      if (var1) {
         field63.forEach(Class362::lambda$getModules$2);
      }

      return var2;
   }
}
