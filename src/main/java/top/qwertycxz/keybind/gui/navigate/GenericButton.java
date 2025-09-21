package top.qwertycxz.keybind.gui.navigate;

import static top.qwertycxz.keybind.ConfigHandler.CATEGORY_GENERIC;
import static top.qwertycxz.keybind.gui.GenericScreen.category;
import static top.qwertycxz.keybind.hotkey.custom.Press.CLIENT;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import net.minecraft.client.gui.screens.Screen;
import top.qwertycxz.keybind.gui.GenericScreen;

public class GenericButton implements IButtonActionListener {
	private final Screen parent;

	public GenericButton(final Screen parent) {
		this.parent = parent;
	}

	@Override
	public void actionPerformedWithButton(final ButtonBase button, final int mouse) {
		category = CATEGORY_GENERIC;
		CLIENT.setScreen(new GenericScreen(parent));
	}
}
