package oringo.mixin;

import java.io.File;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import net.minecraft.crash.CrashReport;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({CrashReport.class})
public abstract class CrashReportMixin {
   @Shadow
   private File crashReportFile;
   @Shadow
   @Final
   private static Logger logger;

   @Shadow
   public abstract String getCompleteReport();

   @Overwrite
   public boolean saveToFile(File var1) {
      if (this.crashReportFile != null) {
         return false;
      } else {
         if (var1.getParentFile() != null) {
            var1.getParentFile().mkdirs();
         }

         try {
            OutputStreamWriter var2 = new OutputStreamWriter(Files.newOutputStream(var1.toPath()), StandardCharsets.UTF_8);
            var2.write(this.getCompleteReport());
            var2.close();
            this.crashReportFile = var1;
            return true;
         } catch (Throwable var3) {
            logger.error("Could not save crash report to " + var1, var3);
            return false;
         }
      }
   }
}
