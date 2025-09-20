package top.qwertycxz.keybind.gui.hotkeys;

import static com.google.common.collect.ContiguousSet.create;
import static com.google.common.collect.DiscreteDomain.integers;
import static com.google.common.collect.Range.closedOpen;
import static fi.dy.masa.malilib.config.ConfigManager.getInstance;
import static fi.dy.masa.malilib.gui.MaLiLibIcons.MINUS;
import static fi.dy.masa.malilib.gui.MaLiLibIcons.SEARCH;
import static java.util.Collections.singletonList;
import static top.qwertycxz.keybind.ConfigHandler.HOTKEY_LIST;

import fi.dy.masa.malilib.config.IConfigManager;
import fi.dy.masa.malilib.gui.LeftRight;
import fi.dy.masa.malilib.gui.widgets.WidgetListBase;
import fi.dy.masa.malilib.gui.widgets.WidgetSearchBar;
import java.util.List;
import java.util.Set;

public class Entries extends WidgetListBase<Integer, Entry> {
	private static final int HEIGHT = MINUS.getHeight();
	public boolean dirty = false;
	private final IConfigManager configManager;

	public Entries(final int x, final int y, final int width, final int height) {
		super(x, y, width, height, null);
		browserEntriesOffsetY = 17;
		browserEntryHeight = HEIGHT;
		configManager = getInstance();
		widgetSearchBar = new WidgetSearchBar(x + 2, y + 4, width - 14, 14, 0, SEARCH, LeftRight.LEFT);
	}

	@Override
	public void removed() {
		if (!dirty) return;
		configManager.onConfigsChanged("$name");
		dirty = false;
	}

	@Override
	protected Entry createListEntryWidget(final int x, final int y, final int listIndex, final boolean odd, final Integer entry) {
		return new Entry(x, y, browserEntryWidth, browserEntryHeight, entry, listIndex, this, HOTKEY_LIST.parallelStream().map(textRenderer::width).unordered().max(Integer::compareTo).orElse(0));
	}

	@Override
	protected Set<Integer> getAllEntries() {
		return create(closedOpen(0, HOTKEY_LIST.size()), integers());
	}

	@Override
	protected List<String> getEntryStringsForFilter(final Integer filter) {
		return singletonList(HOTKEY_LIST.get(filter).toLowerCase());
	}
}
