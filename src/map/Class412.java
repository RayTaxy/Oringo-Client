package map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import net.minecraft.client.Minecraft;
import oringo.module.AutoDailyModule;

public class Class412 {
   public static final Minecraft field0 = Minecraft.getMinecraft();

   public static String[] method0(String var0) throws Exception {
      HttpsURLConnection var1 = (HttpsURLConnection)(new URL("https://hypixel.net/claim-reward/" + var0)).openConnection();
      var1.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 OPR/100.0.0.0");
      String var2 = (String)(new BufferedReader(new InputStreamReader(var1.getInputStream(), StandardCharsets.UTF_8))).lines().collect(Collectors.joining("\n"));
      Matcher var3 = AutoDailyModule.field0.matcher(var2);
      Matcher var4 = AutoDailyModule.ci_.matcher(var2);
      return var3.find() && var4.find() ? new String[]{var3.group(1), var4.group(1), ((String)((List)var1.getHeaderFields().get("set-cookie")).get(0)).split(" ")[0]} : new String[0];
   }
}
