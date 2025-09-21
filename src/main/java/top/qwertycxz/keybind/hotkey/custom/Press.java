package top.qwertycxz.keybind.hotkey.custom;

import static net.minecraft.client.Minecraft.getInstance;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import net.minecraft.client.Minecraft;

public class Press implements IHotkeyCallback {
	public static final Minecraft CLIENT = getInstance();
	public static final long WINDOW = CLIENT.getWindow().getWindow();
	private final int scancode;

	public Press(int scancode) {
		this.scancode = scancode;
	}

	@Override
	public boolean onKeyAction(KeyAction action, IKeybind keybind) {
		System.out.println("Press" + scancode);
		CLIENT.keyboardHandler.keyPress(WINDOW, scancode, scancode, GLFW_PRESS, 0);
		return true;
	}
}
