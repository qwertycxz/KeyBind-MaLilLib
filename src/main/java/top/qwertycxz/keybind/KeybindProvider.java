package top.qwertycxz.keybind;

import static top.qwertycxz.keybind.ConfigHandler.KRYBIND_ADD_HOTKEY;
import static top.qwertycxz.keybind.ConfigHandler.OPTIONS_COMMON;

import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;

class KeybindProvider implements IKeybindProvider {
	@Override
	public void addHotkeys(IKeybindManager keybind) {
		keybind.addHotkeysForCategory("$name", "$capital.KeybindProvider", OPTIONS_COMMON);
	}

	@Override
	public void addKeysToMap(IKeybindManager keybind) {
		keybind.addKeybindToMap(KRYBIND_ADD_HOTKEY);
	}
}
