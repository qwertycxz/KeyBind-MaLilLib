package top.qwertycxz.keybind.hotkey.custom;

import static com.google.common.collect.Lists.transform;

import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import java.util.List;

public class CustomKeybind implements IKeybindProvider {
	private final List<? extends IHotkey> hotkey;
	private final List<IKeybind> keybind;

	public CustomKeybind(List<? extends IHotkey> hotkey) {
		this.hotkey = hotkey;
		this.keybind = transform(hotkey, IHotkey::getKeybind);
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
