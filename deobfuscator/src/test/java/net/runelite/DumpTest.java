package net.runelite;

import com.google.common.io.ByteStreams;
import io.sigpipe.jbsdiff.Patch;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import net.runelite.deob.DeobTestProperties;
import org.junit.Rule;
import org.junit.Test;

public class DumpTest
{
	@Rule
	public DeobTestProperties properties = new DeobTestProperties();

	@Test
	public void dump() throws Exception
	{
		Map<String, byte[]> jar = new HashMap<>();
		loadJar(jar, new File(properties.getVanillaClient()));
		patch(jar);
		saveJar(jar, new File("./injected-dump.jar"));
	}

	private static void saveJar(Map<String, byte[]> fileMap, File toFile) throws IOException
	{
		try (JarOutputStream jout = new JarOutputStream(new FileOutputStream(toFile), new Manifest()))
		{
			for (Map.Entry<String, byte[]> entry : fileMap.entrySet())
			{
				JarEntry e = new JarEntry(entry.getKey());
				jout.putNextEntry(e);

				byte[] data = entry.getValue();

				jout.write(data);
				jout.closeEntry();
			}
		}
	}

	private void patch(Map<String, byte[]> zipFile) throws Exception
	{
		ByteArrayOutputStream patchOs = new ByteArrayOutputStream(756 * 1024);
		int patchCount = 0;

		for (Map.Entry<String, byte[]> file : zipFile.entrySet())
		{
			byte[] bytes;
			try (InputStream is = getClass().getResourceAsStream("/patch/" + file.getKey() + ".bs"))
			{
				if (is == null)
				{
					continue;
				}

				bytes = ByteStreams.toByteArray(is);
			}

			patchOs.reset();
			Patch.patch(file.getValue(), bytes, patchOs);
			file.setValue(patchOs.toByteArray());

			++patchCount;
		}
	}

	private static void loadJar(Map<String, byte[]> toMap, File fromFile) throws IOException
	{
		JarInputStream fis = new JarInputStream(new FileInputStream(fromFile));
		byte[] tmp = new byte[4096];
		ByteArrayOutputStream buffer = new ByteArrayOutputStream(756 * 1024);
		for (; ; )
		{
			JarEntry metadata = fis.getNextJarEntry();
			if (metadata == null)
			{
				break;
			}

			buffer.reset();
			for (; ; )
			{
				int n = fis.read(tmp);
				if (n <= -1)
				{
					break;
				}
				buffer.write(tmp, 0, n);
			}
			toMap.put(metadata.getName(), buffer.toByteArray());
		}
	}
}
