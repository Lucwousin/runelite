import java.util.Arrays;
import org.junit.Test;

public class RandomTests
{
	@Test
	public void test()
	{
		final int[] ints = new int[100];
		Arrays.fill(ints, 69);
		class301.clearIntArray(ints, 0, 100);
		for (int i : ints)
		{
			assert i == 0;
		}
	}
}
