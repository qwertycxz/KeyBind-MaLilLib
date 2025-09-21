package top.qwertycxz.keybind.gui.navigate;

import static top.qwertycxz.keybind.gui.GenericScreen.CATEGORY_HOTKEYS;
import static top.qwertycxz.keybind.gui.GenericScreen.category;
import static top.qwertycxz.keybind.hotkey.custom.CustomCallback.CLIENT;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import net.minecraft.client.gui.screens.Screen;
import top.qwertycxz.keybind.gui.HotkeysScreen;

public class HotkeysButton implements IButtonActionListener {
	private final Screen parent;

	public HotkeysButton(final Screen parent) {
		this.parent = parent;
	}

	@Override
	public void actionPerformedWithButton(final ButtonBase button, final int mouse) {
		category = CATEGORY_HOTKEYS;
		CLIENT.setScreen(new HotkeysScreen(parent));
	}
}
