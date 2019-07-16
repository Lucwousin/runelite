import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("gi")
@Implements("ByteArrayPool")
public class ByteArrayPool {
	@ObfuscatedName("m")
	@ObfuscatedGetter(
		intValue = -791125441
	)
	@Export("ByteArrayPool_smallCount")
	static int ByteArrayPool_smallCount;
	@ObfuscatedName("f")
	@ObfuscatedGetter(
		intValue = 2121641763
	)
	@Export("ByteArrayPool_mediumCount")
	static int ByteArrayPool_mediumCount;
	@ObfuscatedName("q")
	@ObfuscatedGetter(
		intValue = -971800905
	)
	@Export("ByteArrayPool_largeCount")
	static int ByteArrayPool_largeCount;
	@ObfuscatedName("w")
	@Export("ByteArrayPool_small")
	static byte[][] ByteArrayPool_small;
	@ObfuscatedName("o")
	@Export("ByteArrayPool_medium")
	static byte[][] ByteArrayPool_medium;
	@ObfuscatedName("u")
	@Export("ByteArrayPool_large")
	static byte[][] ByteArrayPool_large;
	@ObfuscatedName("g")
	@Export("ByteArrayPool_alternativeSizes")
	static int[] ByteArrayPool_alternativeSizes;
	@ObfuscatedName("dz")
	@ObfuscatedSignature(
		signature = "Lit;"
	)
	@Export("archive13")
	static Archive archive13;

	@ObfuscatedName("f")
	@ObfuscatedSignature(
		signature = "(IZI)[B",
		garbageValue = "198163011"
	)
	@Export("ByteArrayPool_get")
	static synchronized byte[] ByteArrayPool_get(int var0, boolean var1) {
		byte[] var2;
		if (var0 != 100) {
			if (var0 < 100) {
			}
		} else if (ByteArrayPool_smallCount > 0) {
			var2 = ByteArrayPool_small[--ByteArrayPool_smallCount];
			ByteArrayPool_small[ByteArrayPool_smallCount] = null;
			return var2;
		}

		if (var0 != 5000) {
			if (var0 < 5000) {
			}
		} else if (ByteArrayPool_mediumCount > 0) {
			var2 = ByteArrayPool_medium[--ByteArrayPool_mediumCount];
			ByteArrayPool_medium[ByteArrayPool_mediumCount] = null;
			return var2;
		}

		if (var0 != 30000) {
			if (var0 < 30000) {
			}
		} else if (ByteArrayPool_largeCount > 0) {
			var2 = ByteArrayPool_large[--ByteArrayPool_largeCount];
			ByteArrayPool_large[ByteArrayPool_largeCount] = null;
			return var2;
		}

		if (RouteStrategy.ByteArrayPool_arrays != null) {
			for (int var3 = 0; var3 < ByteArrayPool_alternativeSizes.length; ++var3) {
				if (ByteArrayPool_alternativeSizes[var3] != var0) {
					if (var0 < ByteArrayPool_alternativeSizes[var3]) {
					}
				} else if (WorldMapSection1.ByteArrayPool_altSizeArrayCounts[var3] > 0) {
					byte[] var4 = RouteStrategy.ByteArrayPool_arrays[var3][--WorldMapSection1.ByteArrayPool_altSizeArrayCounts[var3]];
					RouteStrategy.ByteArrayPool_arrays[var3][WorldMapSection1.ByteArrayPool_altSizeArrayCounts[var3]] = null;
					return var4;
				}
			}
		}

		return new byte[var0];
	}

	@ObfuscatedName("g")
	@ObfuscatedSignature(
		signature = "(IZI)Ljava/lang/String;",
		garbageValue = "-399583759"
	)
	@Export("intToString")
	public static String intToString(int value, boolean addPlus) {
		if (!addPlus || value < 0) {
			return Integer.toString(value);
		}

		int var2 = value;
		String var3;
		int var4 = 2;

		for (int var5 = value / 10; var5 != 0; ++var4) {
			var5 /= 10;
		}

		char[] var9 = new char[var4];
		var9[0] = '+';

		for (int var6 = var4 - 1; var6 > 0; --var6) {
			int var7 = var2;
			var2 /= 10;
			int var8 = var7 - var2 * 10;
			if (var8 >= 10) {
				var9[var6] = (char)(var8 + 87);
			} else {
				var9[var6] = (char)(var8 + 48);
			}
		}

		var3 = new String(var9);

		return var3;
	}

	static {
		ByteArrayPool_smallCount = 0;
		ByteArrayPool_mediumCount = 0;
		ByteArrayPool_largeCount = 0;
		ByteArrayPool_small = new byte[1000][];
		ByteArrayPool_medium = new byte[250][];
		ByteArrayPool_large = new byte[50][];
	}
}
