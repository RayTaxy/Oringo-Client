package map;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedTitle;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.Oringo;
import oringo.event.Class255;
import oringo.event.Class468;
import oringo.module.AutoFarmModule;
import oringo.module.NoRenderModule;
import oringo.module.PeltESPModule;

public abstract class Class83 {
   public int Y_;
   public final AutoFarmModule field1 = Class229.method1();
   public final String field2;
   public final Minecraft field3 = Minecraft.getMinecraft();

   public void lambda$null$0(WebhookMessageBuilder var1, WebhookEmbedBuilder var2, BufferedImage var3) {
      try {
         ByteArrayOutputStream var4 = new ByteArrayOutputStream();
         ImageIO.write(var3, "png", var4);
         byte[] var5 = var4.toByteArray();
         var1.addFile("screenshot.png", var5);
         var2.setImageUrl("attachment://screenshot.png");
      } catch (IOException var6) {
         if (Oringo.cV_) {
            var6.printStackTrace();
         }
      }

      var1.addEmbeds(new WebhookEmbed[]{var2.build()});
      this.field1.method0(var1.build());
   }

   public void method0(ClientTickEvent var1) {
   }

   public void method0(boolean var1) {
      WebhookMessageBuilder var2 = (new WebhookMessageBuilder()).setUsername("Oringo Client").setAvatarUrl("https://cdn.discordapp.com/attachments/1078688899397853276/1132769112184074350/logo.png");
      WebhookEmbedBuilder var3 = (new WebhookEmbedBuilder()).setTitle(new EmbedTitle(this.field2 + String.format(" failsafe triggered! (%s)", this.field3.getSession().getUsername()), (String)null)).setColor(Color.RED.getRGB());
      if (var1) {
         Runnable var4 = this::lambda$sendWebhook$1;
         if (Class362.field47.method44()) {
            NoRenderModule.field6.add(var4);
         } else {
            var4.run();
         }

      } else {
         var2.addEmbeds(new WebhookEmbed[]{var3.build()});
         this.field1.method0(var2.build());
      }
   }

   public void method0(int var1) {
      this.Y_ = var1;
   }

   public void method0(Class468 var1) {
   }

   public void method0() {
      PeltESPModule.A_(this.field2 + String.format(" failsafe triggered! (%s)", this.field3.getSession().getUsername()));
      Toolkit.getDefaultToolkit().beep();
   }

   public void lambda$sendWebhook$1(WebhookMessageBuilder var1, WebhookEmbedBuilder var2) {
      Class255.method0(this.field3.displayWidth, this.field3.displayHeight, this.field3.getFramebuffer(), this::lambda$null$0);
   }

   public void o_() {
      this.Y_ = 0;
   }

   public void method2() {
   }

   public abstract boolean method3();

   public abstract boolean method4();

   public static double method0(double var0, double var2) {
      return var2 + (var0 - var2) * Class84.field0.nextDouble();
   }

   public Class83(String var1) {
      this.field2 = var1;
   }
}
