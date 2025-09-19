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
import java.util.List;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Style;
import top.qwertycxz.keybind.gui.hotkey.Dialog;
import top.qwertycxz.keybind.gui.hotkey.Entries;

public class HotkeyScreen extends GuiConfigsBase {
	public static final Style DUPLICATE = EMPTY.withColor(RED);
	public static final String ID = "id";
	public boolean dialog = false;
	public Style idStyle = EMPTY;
	public final int index;
	private final ConfigHotkey hotkey;
	private final ConfigString id;
	private final ConfigInteger scancode;

	public HotkeyScreen(int index, Screen parent) {
		super(PADDING_LEFT, PADDING_TOP, "$name", null, "$capital.HotkeyScreen.Title");
		hotkey = hotkeysOptions.get(index);
		id = new ConfigString("$capital.HotkeyScreen.Id", HOTKEY_LIST.get(index), "$capital.HotkeyScreen.Comment");
		scancode = scancodesOptions.get(index);

		this.index = index;
		setParent(parent);
	}

	@Override
	public List<ConfigOptionWrapper> getConfigs() {
		return ConfigOptionWrapper.createFor(asList(id, hotkey, scancode));
	}

	@Override
	public Dialog getDialogHandler() {
		return new Dialog(this);
	}

	@Override
	public boolean onKeyTyped(int key, int scancode, int modifiers) {
		if (idStyle == DUPLICATE && key == 256) return true;
		return super.onKeyTyped(key, scancode, modifiers);
	}

	@Override
	protected void buildConfigSwitcher() {}

	@Override
	protected Entries createListWidget(int listX, int listY) {
		return new Entries(listX, listY, getBrowserWidth(), getBrowserHeight(), getConfigWidth(), 0, useKeybindSearch(), this);
	}

	@Override
	protected void onSettingsChanged() {
		if (dialog || idStyle == DUPLICATE) return;
		String newId = id.getStringValue();
		HOTKEY_LIST.set(index, newId);

		ConfigHotkey newHotkey = new ConfigHotkey(newId, hotkey.getStringValue(), "");
		newHotkey.getKeybind().setSettings(hotkey.getKeybind().getSettings());
		hotkeysOptions.set(index, newHotkey);

		scancodesOptions.set(index, new ConfigInteger(newId, scancode.getIntegerValue(), ""));
		super.onSettingsChanged();
	}
}
