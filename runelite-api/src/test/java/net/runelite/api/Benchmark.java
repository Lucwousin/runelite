package net.runelite.api;

import java.util.Random;
import static junit.framework.TestCase.assertEquals;
import net.runelite.api.util.Text;
import net.runelite.api.widgets.WidgetInfo;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Benchmark // add -XX:+PrintCompilation to see when jit is done
{
	private static final int times = 1000 * 1000;
	private final String[] strings = new String[5];

	private String colTag(int val)
	{
		return "<col=" + Integer.toHexString(val) + '>';
	}

	@Before
	public void before()
	{
		Random random = new Random();
		strings[0] = "";
		strings[1] = colTag(random.nextInt(0xFFFFFF)) + Skill.values()[random.nextInt(22)] + colTag(random.nextInt(0xFFFFFF)) + " -> " + colTag(random.nextInt(0xFFFFFF)) + Prayer.values()[random.nextInt(28)];
		strings[2] = colTag(random.nextInt(0xFFFFFF)) + Quest.values()[random.nextInt(150)].getName();
		strings[3] = ChatMessageType.values()[random.nextInt(25)].name() + ClanMemberRank.values()[random.nextInt(10)] + " " + colTag(random.nextInt(0xFFFFFF)) + " (level-" + random.nextInt(126) + ')';
		strings[4] = WidgetInfo.values()[random.nextInt(255)].toString();

	}

	@Test
	public void testNormal()
	{
		assertEquals("goblin", Text.fullStandardize("<col=ABCDEF>Goblin  <col=FEDCBA>(level-2)"));
		assertEquals("", Text.fullStandardize(""));
		assertEquals("", Text.fullStandardize(" <img=6> "));
		assertEquals("iron ore -> furnace", Text.fullStandardize("<col=ABCDEF>    irON  ORE<col=FFFFFF>  -> <col=BEBEBE>furnacE"));
		//assertEquals("goblin", Text.newStandardize("<col=ABCDEF>Goblin  <col=FEDCBA>(level-2)"));
//<col=XXXXXX>{item/spell name}<col=FFFFFF> -> <col=XXXXXX>{other name}
	}

	public static void main(String... args)
	{
		Benchmark bm = new Benchmark();
		bm.before();

		long nanoTime = System.nanoTime();
		bm.test();
		bm.other1();
		bm.other2();
		System.out.println(System.nanoTime() - nanoTime / 1_000_000d + "ms for init phase");

		System.gc();

		nanoTime = System.nanoTime();
		bm.test();
		bm.other1();
		bm.other2();
		System.out.println(System.nanoTime() - nanoTime / 1_000_000d + "ms for benchmark phase");
	}

	@Test
	@Ignore
	public void test()
	{
		time = System.nanoTime();
		int f;
		for (f = 0; f < times; f++)
		{
			setA(Text.fullStandardize(strings[RandomUtils.nextInt(0, 4)]));
		}

		long here = System.nanoTime() - time;
		System.out.println("full loops done in " + here + "ns (" + here / 1_000_000 + "ms) " + here / 1_000_000 / f / (double) times + " ms/loop\nFinal string: " + a);

		//other1();
	}

	private void other1()
	{
		time = System.nanoTime();
		int f;
		for (f = 0; f < times; f++)
		{
			setA(Text.oldStandardize(strings[RandomUtils.nextInt(0, 4)]));
		}

		long here = System.nanoTime() - time;
		System.out.println("old loops done in " + here + "ns (" + here / 1_000_000 + "ms) " + here / 1_000_000 / (double) times + " ms/loop\nFinal string: " + a);

		//other2();
	}

	private void other2()
	{
		time = System.nanoTime();
		int f;
		for (f = 0; f < times; f++)
		{
			setA(Text.newStandardize(strings[RandomUtils.nextInt(0, 4)]));
		}

		long here = System.nanoTime() - time;
		System.out.println("new loops done in " + here + "ns (" + here / 1_000_000 + "ms) " + here / 1_000_000 / (double) times + " ms/loop\nFinal string: " + a);
	}

	private long time;
	private static String a;
	private static void setA(String a)
	{
		Benchmark.a = a;
	}
}
