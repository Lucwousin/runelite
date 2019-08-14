package net.runelite.api.menus;

import lombok.Setter;

/**
 * A class holding references to all the menu entry related arrays, making
 * interacting with them from anywhere as easy as possible
 */
public class MenuEntries
{
	/**
	 * These arrays are the exact (!) same ones
	 * as in the actual client. Handle them with
	 * an appropriate amount of care. Keep in mind
	 * to only do this on the client thread and that
	 * menu entries are handled a lot! Small changes
	 * can mean big memory hogs.
	 */
	@Setter
	public static int menuEntryCount;

	@Setter
	public static int[] menuArguments1;

	@Setter
	public static int[] menuArguments2;

	@Setter
	public static int[] menuOpcodes;

	@Setter
	public static int[] menuIdentifiers;

	@Setter
	public static String[] menuActions;

	@Setter
	public static String[] menuTargets;

	@Setter
	public static boolean[] menuShiftClick;

	/**
	 * Sorts the current menu entries in the same way the client does this.
	 *
	 * After this, the highest indexed entry is the left click entry.
	 *
	 * The client does this by itself every time someone clicks, and if
	 * we want to know which entry is gonna end up on top we're better off
	 * using this.sortNeeded()
	 */
	public static void sortMenuEntries()
	{
		int count = menuEntryCount - 1;

		for (int tmp, i = 0; i < count; ++i)
		{
			if (menuOpcodes[i] < 1000 && menuOpcodes[i + 1] > 1000)
			{
				String var3 = menuTargets[i];
				menuTargets[i] = menuTargets[i + 1];
				menuTargets[i + 1] = var3;
				String var4 = menuActions[i];
				menuActions[i] = menuActions[i + 1];
				menuActions[i + 1] = var4;
				tmp = menuOpcodes[i];
				menuOpcodes[i] = menuOpcodes[i + 1];
				menuOpcodes[i + 1] = tmp;
				tmp = menuArguments1[i];
				menuArguments1[i] = menuArguments1[i + 1];
				menuArguments1[i + 1] = tmp;
				tmp = menuArguments2[i];
				menuArguments2[i] = menuArguments2[i + 1];
				menuArguments2[i + 1] = tmp;
				tmp = menuIdentifiers[i];
				menuIdentifiers[i] = menuIdentifiers[i + 1];
				menuIdentifiers[i + 1] = tmp;
				boolean var6 = menuShiftClick[i];
				menuShiftClick[i] = menuShiftClick[i + 1];
				menuShiftClick[i + 1] = var6;
			}
		}
	}

	public static void sortNeeded()
	{

	}
}
