package top.qwertycxz.keybind;

import static java.util.Collections.emptyList;

import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import java.util.List;

class CustomKeybind implements IKeybindProvider {
	static IKeybind[] hotkeyKeybind = {};
	static List<? extends IHotkey> hotkeyList = emptyList();

	static void setHotkeyList(List<? extends IHotkey> hotkeyList) {
		CustomKeybind.hotkeyList = hotkeyList;
		hotkeyKeybind = hotkeyList.parallelStream().map(IHotkey::getKeybind).unordered().toArray(IKeybind[]::new);
	}

	@Override
	public void addHotkeys(IKeybindManager manager) {
		manager.addHotkeysForCategory("$name", "$capital.KeybindProvider", hotkeyList);
	}

	@Override
	public void addKeysToMap(IKeybindManager manager) {
		for (IKeybind keybind : hotkeyKeybind) {
			manager.addKeybindToMap(keybind);
		}
	}
}
