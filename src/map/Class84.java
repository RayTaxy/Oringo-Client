package map;

import java.net.URL;
import java.util.Random;
import javax.net.ssl.HttpsURLConnection;

public class Class84 {
   public static final Random field0 = new Random();

   public boolean method0(double var1) {
      return !this.method1(var1);
   }

   public boolean method1(double var1) {
      return !this.method0(var1);
   }

   public static boolean method0(int var0, String var1, String var2, String var3) throws Exception {
      HttpsURLConnection var4 = (HttpsURLConnection)(new URL("https://rewards.hypixel.net/claim-reward/claim?option=" + var0 + "&id=" + var1 + "&activeAd=1&_csrf=" + var2 + "&watchedFallback=false&skipped=0")).openConnection();
      var4.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 OPR/100.0.0.0");
      var4.setRequestProperty("Cookie", var3);
      var4.setRequestProperty("Host", var4.getURL().getHost());
      var4.setRequestProperty("Content-Length", "0");
      var4.setRequestProperty("Accept", "*/*");
      var4.setRequestMethod("POST");
      var4.setDoOutput(true);
      return var4.getResponseCode() == 200;
   }
}
