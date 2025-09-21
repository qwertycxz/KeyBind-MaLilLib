package top.qwertycxz.keybind.hotkey.custom;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static top.qwertycxz.keybind.hotkey.custom.Press.CLIENT;
import static top.qwertycxz.keybind.hotkey.custom.Press.WINDOW;

import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

public class Release implements IHotkeyCallback {
	private final int scancode;

	public Release(int scancode) {
		this.scancode = scancode;
	}

	@Override
	public boolean onKeyAction(KeyAction action, IKeybind keybind) {
		System.out.println("Release" + scancode);
		CLIENT.keyboardHandler.keyPress(WINDOW, scancode, scancode, GLFW_RELEASE, 0);
		return true;
	}

}
