package net.runelite.client.menus.menuentries;

import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.menus.DirectMenuEntryElement;
import net.runelite.api.menus.MenuEntries;
import net.runelite.client.menus.AbstractComparableEntry;
import net.runelite.client.util.Text;

@Slf4j
public class DirectMenuEntries extends MenuEntries
{
	@Getter
	@Setter
	static int entryCount;

	@Getter
	private static final DirectMenuEntryElement ELEMENT = new DirectMenuEntryElement();



	//public static int tryFindMatch(AbstractComparableEntry a)
	{

	}

}
