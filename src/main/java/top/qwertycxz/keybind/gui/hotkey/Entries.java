package top.qwertycxz.keybind.gui.hotkey;

import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.widgets.WidgetConfigOption;
import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptions;
import top.qwertycxz.keybind.gui.HotkeyScreen;

public class Entries extends WidgetListConfigOptions {
	private final HotkeyScreen parent;

	public Entries(final int x, final int y, final int width, final int height, final int configWidth, final float zLevel, final boolean useKeybindSearch, final HotkeyScreen parent) {
		super(x, y, width, height, configWidth, zLevel, useKeybindSearch, parent);
		this.parent = parent;
	}

	@Override
	protected WidgetConfigOption createListEntryWidget(final int x, final int y, final int listIndex, final boolean isOdd, final GuiConfigsBase.ConfigOptionWrapper wrapper) {
		return new Entry(x, y, browserEntryWidth, browserEntryHeight, maxLabelWidth, configWidth, wrapper, listIndex, parent, this);
	}
}
