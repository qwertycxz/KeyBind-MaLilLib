package top.qwertycxz.keybind;

import fi.dy.masa.malilib.gui.GuiConfigsBase;
import java.util.List;

class GuiConfigs extends GuiConfigsBase {
	GuiConfigs() {
		super(10, 26, "$lowercase", null, "$capital.GuiConfigs");
	}

	@Override
	public List<ConfigOptionWrapper> getConfigs() {
		return ConfigOptionWrapper.createFor(ConfigHandler.OPTIONS_COMMON);
	}
}
