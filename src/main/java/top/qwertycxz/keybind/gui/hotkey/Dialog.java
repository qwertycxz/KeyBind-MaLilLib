package top.qwertycxz.keybind.gui.hotkey;

import static top.qwertycxz.keybind.hotkey.custom.CustomCallback.CLIENT;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.gui.interfaces.IDialogHandler;
import top.qwertycxz.keybind.gui.HotkeyScreen;

public class Dialog implements IDialogHandler {
	final HotkeyScreen parent;

	public Dialog(final HotkeyScreen parent) {
		this.parent = parent;
	}

	@Override
	public void closeDialog() {
		CLIENT.setScreen(parent);
		parent.dialog = false;
	}

	@Override
	public void openDialog(final GuiBase gui) {
		parent.dialog = true;
		CLIENT.setScreen(gui);
	}
}
