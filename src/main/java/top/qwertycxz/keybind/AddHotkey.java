package top.qwertycxz.keybind;

import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

class AddHotkey implements IHotkeyCallback {
	@Override
	public boolean onKeyAction(KeyAction action, IKeybind keybind) {
		System.out.println(action.getDisplayName() + keybind.getKeysDisplayString());
		return true;
	}
}
