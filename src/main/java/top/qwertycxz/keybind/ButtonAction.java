package top.qwertycxz.keybind;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;

class ButtonAction implements IButtonActionListener {
	final String category;
	final GuiConfigs gui;

	ButtonAction(String category, GuiConfigs gui) {
		this.category = category;
		this.gui = gui;
	}

	@Override
	public void actionPerformedWithButton(ButtonBase button, int mouse) {
		gui.category = category;
		gui.initGui();
	}
}
