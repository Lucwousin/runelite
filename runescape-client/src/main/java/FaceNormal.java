import java.applet.Applet;
import java.net.URL;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;
import netscape.javascript.JSObject;

@ObfuscatedName("el")
@Implements("FaceNormal")
public class FaceNormal {
   @ObfuscatedName("x")
   @ObfuscatedGetter(
      intValue = 422473713
   )
   public static int field344;
   @ObfuscatedName("gm")
   @ObfuscatedSignature(
      signature = "Lln;"
   )
   @Export("redHintArrowSprite")
   static Sprite redHintArrowSprite;
   @ObfuscatedName("m")
   @ObfuscatedGetter(
      intValue = -2074333261
   )
   int field346;
   @ObfuscatedName("f")
   @ObfuscatedGetter(
      intValue = -1376860893
   )
   int field347;
   @ObfuscatedName("q")
   @ObfuscatedGetter(
      intValue = 2619977
   )
   int field348;

   @ObfuscatedName("f")
   @ObfuscatedSignature(
      signature = "(IIII)Lcg;",
      garbageValue = "-1788849120"
   )
   @Export("getWorldMapScript")
   static Script getWorldMapScript(int type, int objectId, int elementCategory) {
      int var3 = GrandExchangeOfferUnitPriceComparator.getWorldMapScriptId(objectId, type);
      Script var4 = InterfaceParent.getWorldMapScript(var3, type);
      if (var4 != null) {
         return var4;
      } else {
         var3 = PacketBuffer.getWorldMapScriptIdCategory(elementCategory, type);
         var4 = InterfaceParent.getWorldMapScript(var3, type);
         return var4 != null ? var4 : null;
      }
   }

   @ObfuscatedName("q")
   @ObfuscatedSignature(
      signature = "(Ljava/lang/String;ILjava/lang/String;I)Z",
      garbageValue = "-1495992318"
   )
   static boolean method3237(String var0, int var1, String var2) {
      if (var1 == 0) {
         try {
            if (!class50.field1160.startsWith("win")) {
               throw new Exception();
            } else if (!var0.startsWith("http://") && !var0.startsWith("https://")) {
               throw new Exception();
            } else {
               String var11 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?&=,.%+-_#:/*";

               for (int var12 = 0; var12 < var0.length(); ++var12) {
                  if (var11.indexOf(var0.charAt(var12)) == -1) {
                     throw new Exception();
                  }
               }

               Runtime.getRuntime().exec("cmd /c start \"j\" \"" + var0 + "\"");
               return true;
            }
         } catch (Throwable var6) {
            return false;
         }
      } else if (var1 == 1) {
         try {
            Applet var3 = class50.applet;
            Object[] var4 = new Object[]{(new URL(class50.applet.getCodeBase(), var0)).toString()};
            Object var5 = JSObject.getWindow(var3).call(var2, var4);
            return var5 != null;
         } catch (Throwable var7) {
            return false;
         }
      } else if (var1 == 2) {
         try {
            class50.applet.getAppletContext().showDocument(new URL(class50.applet.getCodeBase(), var0), "_blank");
            return true;
         } catch (Exception var8) {
            return false;
         }
      } else if (var1 == 3) {
         try {
            class46.method851(class50.applet, "loggedout");
         } catch (Throwable var10) {
         }

         try {
            class50.applet.getAppletContext().showDocument(new URL(class50.applet.getCodeBase(), var0), "_top");
            return true;
         } catch (Exception var9) {
            return false;
         }
      } else {
         throw new IllegalArgumentException();
      }
   }
}
