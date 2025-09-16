package top.qwertycxz.keybind.gui.hotkeys.button;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;

public class Edit implements IButtonActionListener {
	private final int entry;

	public Edit(int entry) {
		this.entry = entry;
	}

	@Override
	public void actionPerformedWithButton(ButtonBase button, int mouse) {
		System.out.println("Hotkey modified: " + entry);
	}
}
