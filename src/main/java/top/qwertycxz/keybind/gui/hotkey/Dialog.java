package top.qwertycxz.keybind.gui.hotkey;

import static fi.dy.masa.malilib.gui.GuiBase.openGui;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.gui.interfaces.IDialogHandler;
import top.qwertycxz.keybind.gui.HotkeyScreen;

public class Dialog implements IDialogHandler {
	final HotkeyScreen parent;

	public Dialog(HotkeyScreen parent) {
		this.parent = parent;
	}

	@Override
	public void closeDialog() {
		openGui(parent);
		parent.dialog = false;
	}

	@Override
	public void openDialog(GuiBase gui) {
		parent.dialog = true;
		openGui(gui);
	}
}
