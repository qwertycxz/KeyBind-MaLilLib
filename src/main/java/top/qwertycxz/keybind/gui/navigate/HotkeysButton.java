package top.qwertycxz.keybind.gui.navigate;

import static fi.dy.masa.malilib.gui.GuiBase.openGui;
import static top.qwertycxz.keybind.ConfigHandler.CATEGORY_HOTKEYS;
import static top.qwertycxz.keybind.gui.GenericScreen.category;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import net.minecraft.client.gui.screens.Screen;
import top.qwertycxz.keybind.gui.HotkeysScreen;

public class HotkeysButton implements IButtonActionListener {
	private final Screen parent;

	public HotkeysButton(Screen parent) {
		this.parent = parent;
	}

	@Override
	public void actionPerformedWithButton(ButtonBase button, int mouse) {
		category = CATEGORY_HOTKEYS;
		openGui(new HotkeysScreen(parent));
	}
}
