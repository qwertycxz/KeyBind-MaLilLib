package top.qwertycxz.keybind;

import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

class CustomCallback implements IHotkeyCallback {
	final int scancode;

	CustomCallback(int scancode) {
		this.scancode = scancode;
	}

	@Override
	public boolean onKeyAction(KeyAction action, IKeybind keybind) {
		System.out.println(scancode + action.getDisplayName() + keybind.getKeysDisplayString());
		return true;
	}
}
