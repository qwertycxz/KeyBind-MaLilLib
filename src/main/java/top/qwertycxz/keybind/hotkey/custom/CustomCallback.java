package top.qwertycxz.keybind.hotkey.custom;

import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

public class CustomCallback implements IHotkeyCallback {
	private final int scancode;

	public CustomCallback(int scancode) {
		this.scancode = scancode;
	}

	@Override
	public boolean onKeyAction(KeyAction action, IKeybind keybind) {
		System.out.println(scancode + action.getDisplayName() + keybind.getKeysDisplayString());
		return true;
	}
}
