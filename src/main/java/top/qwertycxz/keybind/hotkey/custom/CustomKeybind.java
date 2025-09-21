package top.qwertycxz.keybind.hotkey.custom;

import static com.google.common.collect.Lists.transform;

import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import java.util.List;

public class CustomKeybind implements IKeybindProvider {
	private final List<? extends IHotkey> pressHotkeys;
	private final List<IKeybind> pressKeybind;
	private final List<? extends IHotkey> releaseHotkeys;
	private final List<IKeybind> releaseKeybind;

	public CustomKeybind(final List<? extends IHotkey> pressHotkeys, final List<? extends IHotkey> releaseHotkeys) {
		this.pressHotkeys = pressHotkeys;
		this.pressKeybind = transform(pressHotkeys, IHotkey::getKeybind);

		this.releaseHotkeys = releaseHotkeys;
		this.releaseKeybind = transform(releaseHotkeys, IHotkey::getKeybind);
	}

	@Override
	public void addHotkeys(final IKeybindManager manager) {
		manager.addHotkeysForCategory("$name", "$capital.CustomKeybind.Press", pressHotkeys);
		manager.addHotkeysForCategory("$name", "$capital.CustomKeybind.Release", releaseHotkeys);
	}

	@Override
	public void addKeysToMap(final IKeybindManager manager) {
		for (final IKeybind keybind : pressKeybind) {
			manager.addKeybindToMap(keybind);
		}
		for (final IKeybind keybind : releaseKeybind) {
			manager.addKeybindToMap(keybind);
		}
	}
}
