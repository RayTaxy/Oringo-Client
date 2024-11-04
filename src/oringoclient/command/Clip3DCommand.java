package oringo.command;

import map.Class95;
import oringo.module.AutoIceSprayModule;

public class Clip3DCommand extends Command {
   public void method0(String[] var1) {
      double var2 = Double.parseDouble(var1[0]);
      double var4 = Math.toRadians((double)field9.thePlayer.rotationYaw);
      double var6 = Math.toRadians((double)field9.thePlayer.rotationPitch);
      field9.thePlayer.setPosition(field9.thePlayer.posX + -Math.sin(var4) * var2 * Math.cos(var6), field9.thePlayer.posY + var2 * -Math.sin(var6), field9.thePlayer.posZ + Math.cos(var4) * var2 * Math.cos(var6));
      field9.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
   }

   public static int method2() {
      return Class95.method0(AutoIceSprayModule::lambda$getIceSpray$2);
   }

   public Clip3DCommand() {
      super("3dclip");
   }

   public String method1() {
      return "Yeah 3d clip";
   }
}
