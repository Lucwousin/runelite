import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("bx")
@Implements("InterfaceParent")
public class InterfaceParent extends Node {
   @ObfuscatedName("px")
   @ObfuscatedSignature(
      signature = "Lln;"
   )
   @Export("sceneMinimapSprite")
   static Sprite sceneMinimapSprite;
   @ObfuscatedName("o")
   @ObfuscatedGetter(
      intValue = -992379173
   )
   static int field986;
   @ObfuscatedName("m")
   @ObfuscatedGetter(
      intValue = -707461797
   )
   @Export("group")
   int group;
   @ObfuscatedName("f")
   @ObfuscatedGetter(
      intValue = 1712037041
   )
   @Export("type")
   int type;
   @ObfuscatedName("q")
   @Export("keep")
   boolean keep;

   InterfaceParent() {
      this.keep = false;
   }

   @ObfuscatedName("q")
   @ObfuscatedSignature(
      signature = "(IIB)Lcg;",
      garbageValue = "1"
   )
   @Export("getWorldMapScript")
   static Script getWorldMapScript(int scriptID, int type) {
      Script var2 = (Script)Script.Script_cached.get((long)(scriptID << 16));
      if (var2 != null) {
         return var2;
      } else {
         String var3 = String.valueOf(scriptID);
         int var4 = Formatting.archive12.getGroupId(var3);
         if (var4 == -1) {
            return null;
         } else {
            byte[] var5 = Formatting.archive12.takeFileFlat(var4);
            if (var5 != null) {
               if (var5.length <= 1) {
                  return null;
               }

               var2 = Occluder.newScript(var5);
               if (var2 != null) {
                  Script.Script_cached.put(var2, (long)(scriptID << 16));
                  return var2;
               }
            }

            return null;
         }
      }
   }

   @ObfuscatedName("o")
   @ObfuscatedSignature(
      signature = "([BIII)Ljava/lang/String;",
      garbageValue = "619422509"
   )
   @Export("decodeStringCp1252")
   public static String decodeStringCp1252(byte[] src, int srcStart, int length) {
      char[] var3 = new char[length];
      int var4 = 0;

      for (int var5 = 0; var5 < length; ++var5) {
         int var6 = src[var5 + srcStart] & 255;
         if (var6 != 0) {
            if (var6 >= 128 && var6 < 160) {
               char var7 = class304.cp1252AsciiExtension[var6 - 128];
               if (var7 == 0) {
                  var7 = '?';
               }

               var6 = var7;
            }

            var3[var4++] = (char)var6;
         }
      }

      return new String(var3, 0, var4);
   }

   @ObfuscatedName("o")
   @ObfuscatedSignature(
      signature = "(IB)I",
      garbageValue = "32"
   )
   static int method1174(int var0) {
      ChatChannel var1 = (ChatChannel)Messages.Messages_channels.get(var0);
      return var1 == null ? 0 : var1.size();
   }
}
