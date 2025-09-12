package top.qwertycxz.keybind;

import static java.util.Collections.emptyList;
import static top.qwertycxz.keybind.ConfigHandler.CATEGORY_GENERIC;
import static top.qwertycxz.keybind.ConfigHandler.CATEGORY_HOTKEYS;
import static top.qwertycxz.keybind.ConfigHandler.CATEGORY_SCANCODES;
import static top.qwertycxz.keybind.ConfigHandler.GENERIC_OPTIONS;
import static top.qwertycxz.keybind.ConfigHandler.hotkeysOptions;
import static top.qwertycxz.keybind.ConfigHandler.scancodesOptions;

import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import java.util.List;

class GuiConfigs extends GuiConfigsBase {
	static final int PADDING_LEFT = 10;
	static final int PADDING_TOP = 26;
	String category = CATEGORY_GENERIC;

	GuiConfigs() {
		super(PADDING_LEFT, 50, "$lowercase", null, "$capital.GuiConfigs.Title");
	}

	@Override
	public List<ConfigOptionWrapper> getConfigs() {
		switch (category) {
			case CATEGORY_GENERIC :
				return ConfigOptionWrapper.createFor(GENERIC_OPTIONS);
			case CATEGORY_HOTKEYS :
				return ConfigOptionWrapper.createFor(hotkeysOptions);
			case CATEGORY_SCANCODES :
				return ConfigOptionWrapper.createFor(scancodesOptions);
		}
		return emptyList();
	}

	@Override
	public void initGui() {
		super.initGui();
		int x = PADDING_LEFT;
		x += createButton(x, CATEGORY_GENERIC);
		x += createButton(x, CATEGORY_HOTKEYS);
		createButton(x, CATEGORY_SCANCODES);
		clearOptions();
		getListWidget().resetScrollbarPosition();
	}

	int createButton(int x, String name) {
		ButtonGeneric button = new ButtonGeneric(x, PADDING_TOP, -1, 20, "$capital.GuiConfigs." + name);
		addButton(button, new ButtonAction(name, this));
		if (category.equals(name)) {
			button.setEnabled(false);
		}
		return button.getWidth() + 2;
	}
}
