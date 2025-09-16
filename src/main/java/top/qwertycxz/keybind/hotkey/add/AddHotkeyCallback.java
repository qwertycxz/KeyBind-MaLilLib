package top.qwertycxz.keybind.hotkey.add;

import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

public class AddHotkeyCallback implements IHotkeyCallback {
	@Override
	public boolean onKeyAction(KeyAction action, IKeybind keybind) {
		System.out.println(action.getDisplayName() + keybind.getKeysDisplayString());
		return true;
	}
}
