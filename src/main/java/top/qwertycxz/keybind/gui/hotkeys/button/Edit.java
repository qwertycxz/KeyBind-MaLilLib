package top.qwertycxz.keybind.gui.hotkeys.button;

import static top.qwertycxz.keybind.hotkey.custom.CustomCallback.CLIENT;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import top.qwertycxz.keybind.gui.HotkeyScreen;
import top.qwertycxz.keybind.gui.hotkeys.Entries;

public class Edit implements IButtonActionListener {
	private final Entries entries;
	private final int entry;

	public Edit(final Entries entries, final int entry) {
		this.entries = entries;
		this.entry = entry;
	}

	@Override
	public void actionPerformedWithButton(final ButtonBase button, final int mouse) {
		entries.removed();
		CLIENT.setScreen(new HotkeyScreen(entry, CLIENT.screen, false));
	}
}
