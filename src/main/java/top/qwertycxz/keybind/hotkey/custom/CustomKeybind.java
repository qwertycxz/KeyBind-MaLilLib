package top.qwertycxz.keybind.hotkey.custom;

import static com.google.common.collect.Lists.transform;
import static java.util.Collections.emptyList;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import java.util.List;

public class CustomKeybind implements IKeybindProvider {
	private static List<IKeybind> keybind = emptyList();
	private static List<ConfigHotkey> hotkey = emptyList();

	public static void setHotkey(List<ConfigHotkey> hotkey) {
		CustomKeybind.hotkey = hotkey;
		keybind = transform(hotkey, ConfigHotkey::getKeybind);
	}

	@Override
	public void addHotkeys(IKeybindManager manager) {
		manager.addHotkeysForCategory("$name", "$capital.CustomKeybind", hotkey);
	}

	@Override
	public void addKeysToMap(IKeybindManager manager) {
		for (IKeybind keybind : keybind) {
			manager.addKeybindToMap(keybind);
		}
	}
}
