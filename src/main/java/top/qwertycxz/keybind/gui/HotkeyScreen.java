package top.qwertycxz.keybind.gui;

import static java.util.Arrays.asList;
import static java.util.Collections.frequency;
import static net.minecraft.ChatFormatting.RED;
import static net.minecraft.network.chat.Style.EMPTY;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static top.qwertycxz.keybind.ConfigHandler.HOTKEY_LIST;
import static top.qwertycxz.keybind.ConfigHandler.pressOptions;
import static top.qwertycxz.keybind.ConfigHandler.releaseOptions;
import static top.qwertycxz.keybind.ConfigHandler.scancodesOptions;
import static top.qwertycxz.keybind.gui.GenericScreen.PADDING_LEFT;
import static top.qwertycxz.keybind.gui.GenericScreen.PADDING_TOP;
import static top.qwertycxz.keybind.hotkey.custom.CustomCallback.CLIENT;
import static top.qwertycxz.keybind.hotkey.custom.CustomCallback.WINDOW;

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
	private final ConfigString id;
	private final ConfigHotkey press;
	private final boolean pressOnClose;
	private final ConfigHotkey release;
	private final ConfigInteger scancode;

	public HotkeyScreen(final int index, final Screen parent, final boolean pressOnClose) {
		super(PADDING_LEFT, PADDING_TOP, "$name", parent, "$capital.HotkeyScreen.Title");
		press = pressOptions.get(index);
		release = releaseOptions.get(index);
		scancode = scancodesOptions.get(index);
		this.index = index;
		this.pressOnClose = pressOnClose;
		setParent(parent);

		final String idString = HOTKEY_LIST.get(index);
		id = new ConfigString("$capital.HotkeyScreen.Id", idString, "$capital.HotkeyScreen.Comment");
		if (frequency(HOTKEY_LIST, idString) > 1) {
			idStyle = DUPLICATE;
		}
	}

	@Override
	public List<ConfigOptionWrapper> getConfigs() {
		return ConfigOptionWrapper.createFor(asList(id, press, release, scancode));
	}

	@Override
	public Dialog getDialogHandler() {
		return new Dialog(this);
	}

	@Override
	public boolean onKeyTyped(final int key, final int scancode, final int modifiers) {
		if (idStyle == DUPLICATE && key == 256) return true;
		final boolean success = super.onKeyTyped(key, scancode, modifiers);
		if (pressOnClose && key == 256) {
			int newScancode = this.scancode.getIntegerValue();
			CLIENT.keyboardHandler.keyPress(WINDOW, newScancode, newScancode, GLFW_PRESS, 0);
		}
		return success;
	}

	@Override
	protected void buildConfigSwitcher() {}

	@Override
	protected Entries createListWidget(final int listX, final int listY) {
		return new Entries(listX, listY, getBrowserWidth(), getBrowserHeight(), getConfigWidth(), 0, useKeybindSearch(), this);
	}

	@Override
	protected void onSettingsChanged() {
		if (dialog || idStyle == DUPLICATE) return;
		final String newId = id.getStringValue();
		HOTKEY_LIST.set(index, newId);

		final ConfigHotkey newPress = new ConfigHotkey(newId, press.getStringValue(), "");
		newPress.getKeybind().setSettings(press.getKeybind().getSettings());
		pressOptions.set(index, newPress);

		final ConfigHotkey newRelease = new ConfigHotkey(newId, release.getStringValue(), "");
		newRelease.getKeybind().setSettings(release.getKeybind().getSettings());
		releaseOptions.set(index, newRelease);

		scancodesOptions.set(index, new ConfigInteger(newId, scancode.getIntegerValue(), ""));
		super.onSettingsChanged();
	}
}
