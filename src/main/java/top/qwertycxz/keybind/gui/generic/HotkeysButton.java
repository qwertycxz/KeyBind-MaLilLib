package top.qwertycxz.keybind.gui.generic;

import static fi.dy.masa.malilib.gui.GuiBase.openGui;
import static top.qwertycxz.keybind.ConfigHandler.CATEGORY_HOTKEYS;
import static top.qwertycxz.keybind.gui.generic.GenericScreen.category;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import net.minecraft.client.gui.screens.Screen;
import top.qwertycxz.keybind.gui.hotkeys.HotkeysScreen;

class HotkeysButton implements IButtonActionListener {
	private final Screen parent;

	HotkeysButton(Screen parent) {
		this.parent = parent;
	}

	@Override
	public void actionPerformedWithButton(ButtonBase button, int mouse) {
		category = CATEGORY_HOTKEYS;
		openGui(new HotkeysScreen(parent));
	}
}
