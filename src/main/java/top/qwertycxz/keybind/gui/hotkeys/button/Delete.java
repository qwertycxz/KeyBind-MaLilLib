package top.qwertycxz.keybind.gui.hotkeys.button;

import static top.qwertycxz.keybind.ConfigHandler.HOTKEY_LIST;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import top.qwertycxz.keybind.gui.hotkeys.Entries;

public class Delete implements IButtonActionListener {
	private final Entries entries;
	private final int entry;

	public Delete(final Entries entries, final int entry) {
		this.entries = entries;
		this.entry = entry;
	}

	@Override
	public void actionPerformedWithButton(final ButtonBase button, final int mouse) {
		HOTKEY_LIST.remove(entry);
		entries.dirty = true;
		entries.refreshEntries();
	}
}
