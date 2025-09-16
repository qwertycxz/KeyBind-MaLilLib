package top.qwertycxz.keybind.hotkey.custom;

import static com.google.common.collect.Lists.transform;
import static java.util.Collections.emptyList;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import java.util.List;

public class CustomKeybind implements IKeybindProvider {
	private static List<IKeybind> hotkeyKeybind = emptyList();
	private static List<ConfigHotkey> hotkeyList = emptyList();

	public static void setHotkeyList(List<ConfigHotkey> hotkeyList) {
		CustomKeybind.hotkeyList = hotkeyList;
		hotkeyKeybind = transform(hotkeyList, ConfigHotkey::getKeybind);
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
