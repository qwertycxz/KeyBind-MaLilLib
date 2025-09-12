package top.qwertycxz.keybind;

import static java.util.Collections.singletonList;
import static top.qwertycxz.keybind.ConfigHandler.GENERIC_ADD_HOTKEY;
import static top.qwertycxz.keybind.ConfigHandler.GENERIC_ADD_HOTKEY_KEYBIND;

import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import java.util.List;

class AddHotkeyKeybind implements IKeybindProvider {
	static final List<IHotkey> HOTKEY = singletonList(GENERIC_ADD_HOTKEY);

	@Override
	public void addHotkeys(IKeybindManager manager) {
		manager.addHotkeysForCategory("$name", "$capital.KeybindProvider", HOTKEY);
	}

	@Override
	public void addKeysToMap(IKeybindManager manager) {
		manager.addKeybindToMap(GENERIC_ADD_HOTKEY_KEYBIND);
	}
}
