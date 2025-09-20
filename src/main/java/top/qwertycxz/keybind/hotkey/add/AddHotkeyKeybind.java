package top.qwertycxz.keybind.hotkey.add;

import static java.util.Collections.singletonList;
import static top.qwertycxz.keybind.ConfigHandler.ADD_HOTKEY_CONFIG;
import static top.qwertycxz.keybind.ConfigHandler.ADD_HOTKEY_KEYBIND;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import java.util.List;

public class AddHotkeyKeybind implements IKeybindProvider {
	private static final List<ConfigHotkey> HOTKEY = singletonList(ADD_HOTKEY_CONFIG);

	@Override
	public void addHotkeys(final IKeybindManager manager) {
		manager.addHotkeysForCategory("$name", "$capital.AddHotkeyKeybind", HOTKEY);
	}

	@Override
	public void addKeysToMap(final IKeybindManager manager) {
		manager.addKeybindToMap(ADD_HOTKEY_KEYBIND);
	}
}
