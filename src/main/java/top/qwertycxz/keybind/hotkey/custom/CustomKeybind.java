package top.qwertycxz.keybind.hotkey.custom;

import static java.util.Collections.emptyList;

import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import java.util.List;

public class CustomKeybind implements IKeybindProvider {
	private static IKeybind[] hotkeyKeybind = {};
	private static List<? extends IHotkey> hotkeyList = emptyList();

	public static void setHotkeyList(List<? extends IHotkey> hotkeyList) {
		CustomKeybind.hotkeyList = hotkeyList;
		hotkeyKeybind = hotkeyList.parallelStream().map(IHotkey::getKeybind).unordered().toArray(IKeybind[]::new);
	}

	@Override
	public void addHotkeys(IKeybindManager manager) {
		manager.addHotkeysForCategory("$name", "$capital.CustomKeybind", hotkeyList);
	}

	@Override
	public void addKeysToMap(IKeybindManager manager) {
		for (IKeybind keybind : hotkeyKeybind) {
			manager.addKeybindToMap(keybind);
		}
	}
}
