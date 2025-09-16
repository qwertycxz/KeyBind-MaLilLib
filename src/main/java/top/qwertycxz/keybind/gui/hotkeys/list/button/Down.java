package top.qwertycxz.keybind.gui.hotkeys.list.button;

import static java.util.Collections.swap;
import static top.qwertycxz.keybind.ConfigHandler.HOTKEY_LIST;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import top.qwertycxz.keybind.gui.hotkeys.list.Entries;

public class Down implements IButtonActionListener {
	private final Entries entries;
	private final int entry;

	public Down(Entries entries, int entry) {
		this.entries = entries;
		this.entry = entry;
	}

	@Override
	public void actionPerformedWithButton(ButtonBase button, int mouse) {
		swap(HOTKEY_LIST, entry, entry + 1);
		entries.dirty = true;
		entries.refreshEntries();
	}
}
