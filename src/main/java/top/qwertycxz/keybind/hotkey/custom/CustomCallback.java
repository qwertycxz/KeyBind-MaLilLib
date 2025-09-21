package top.qwertycxz.keybind.hotkey.custom;

import static net.minecraft.client.Minecraft.getInstance;

import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import net.minecraft.client.Minecraft;

public class CustomCallback implements IHotkeyCallback {
	public static final Minecraft CLIENT = getInstance();
	public static final long WINDOW = CLIENT.getWindow().getWindow();
	private final int action;
	private final int scancode;

	public CustomCallback(final int action, final int scancode) {
		this.action = action;
		this.scancode = scancode;
	}

	@Override
	public boolean onKeyAction(final KeyAction action, final IKeybind keybind) {
		CLIENT.keyboardHandler.keyPress(WINDOW, scancode, scancode, this.action, 0);
		return true;
	}
}
