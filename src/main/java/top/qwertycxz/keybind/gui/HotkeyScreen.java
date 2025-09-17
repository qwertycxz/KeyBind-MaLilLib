package top.qwertycxz.keybind.gui;

import static java.util.Arrays.asList;
import static net.minecraft.ChatFormatting.RED;
import static net.minecraft.network.chat.Style.EMPTY;
import static top.qwertycxz.keybind.ConfigHandler.HOTKEY_LIST;
import static top.qwertycxz.keybind.ConfigHandler.hotkeysOptions;
import static top.qwertycxz.keybind.ConfigHandler.scancodesOptions;
import static top.qwertycxz.keybind.gui.GenericScreen.PADDING_LEFT;
import static top.qwertycxz.keybind.gui.GenericScreen.PADDING_TOP;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptions;
import java.util.List;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Style;
import top.qwertycxz.keybind.gui.hotkey.Entries;

public class HotkeyScreen extends GuiConfigsBase {
	public static final Style DUPLICATE = EMPTY.withColor(RED);
	public static final String ID = "id";
	public Style idStyle = EMPTY;
	public final int index;
	private final ConfigHotkey hotkey;
	private final ConfigString id;
	private final ConfigInteger scancode;

	public HotkeyScreen(int index, Screen parent) {
		super(PADDING_LEFT, PADDING_TOP, "$name", null, "$capital.HotkeyScreen.Title");
		id = new ConfigString(ID, HOTKEY_LIST.get(index), "$capital.HotkeyScreen.Id");

		ConfigHotkey oldHotkey = hotkeysOptions.get(index);
		hotkey = new ConfigHotkey("hotkey", oldHotkey.getStringValue(), oldHotkey.getKeybind().getSettings(), "$capital.HotkeyScreen.Hotkey");
		scancode = new ConfigInteger("scancode", scancodesOptions.get(index).getIntegerValue(), "$capital.HotkeyScreen.Scancode");

		this.index = index;
		setParent(parent);
	}

	@Override
	public List<ConfigOptionWrapper> getConfigs() {
		return ConfigOptionWrapper.createFor(asList(id, hotkey, scancode));
	}

	@Override
	public boolean onKeyTyped(int key, int scancode, int modifiers) {
		if (idStyle == DUPLICATE && key == 256) return true;
		return super.onKeyTyped(key, scancode, modifiers);
	}

	@Override
	protected void buildConfigSwitcher() {}

	@Override
	protected WidgetListConfigOptions createListWidget(int listX, int listY) {
		return new Entries(listX, listY, getBrowserWidth(), getBrowserHeight(), getConfigWidth(), 0, useKeybindSearch(), this);
	}

	@Override
	protected void onSettingsChanged() {
		String newId = id.getStringValue();
		HOTKEY_LIST.set(index, newId);

		ConfigHotkey newHotkey = new ConfigHotkey(newId, hotkey.getStringValue(), "");
		newHotkey.getKeybind().setSettings(hotkey.getKeybind().getSettings());
		hotkeysOptions.set(index, newHotkey);

		scancodesOptions.set(index, new ConfigInteger(newId, scancode.getIntegerValue(), ""));
		super.onSettingsChanged();
	}
}
