package top.qwertycxz.keybind.gui.hotkey;

import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.widgets.WidgetConfigOption;
import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptions;
import top.qwertycxz.keybind.gui.HotkeyScreen;

public class Entries extends WidgetListConfigOptions {
	private final HotkeyScreen parent;

	public Entries(int x, int y, int width, int height, int configWidth, float zLevel, boolean useKeybindSearch, HotkeyScreen parent) {
		super(x, y, width, height, configWidth, zLevel, useKeybindSearch, parent);
		this.parent = parent;
	}

	@Override
	protected WidgetConfigOption createListEntryWidget(int x, int y, int listIndex, boolean isOdd, GuiConfigsBase.ConfigOptionWrapper wrapper) {
		return new Entry(x, y, browserEntryWidth, browserEntryHeight, maxLabelWidth, configWidth, wrapper, listIndex, parent, this);
	}
}
