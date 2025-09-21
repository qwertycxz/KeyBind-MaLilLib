package top.qwertycxz.keybind.hotkey.add;

import static java.util.Arrays.sort;
import static top.qwertycxz.keybind.ConfigHandler.HOTKEY_LIST;
import static top.qwertycxz.keybind.ConfigHandler.NEXT_SCANCODE_CONFIG;
import static top.qwertycxz.keybind.ConfigHandler.pressOptions;
import static top.qwertycxz.keybind.ConfigHandler.releaseOptions;
import static top.qwertycxz.keybind.ConfigHandler.scancodesOptions;
import static top.qwertycxz.keybind.hotkey.custom.Press.CLIENT;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import top.qwertycxz.keybind.gui.HotkeyScreen;

public class AddHotkeyCallback implements IHotkeyCallback {
	private static final String NEW_ID = "New Hotkey";

	@Override
	public boolean onKeyAction(final KeyAction action, final IKeybind keybind) {
		final int index = HOTKEY_LIST.size();
		HOTKEY_LIST.add(NEW_ID);
		pressOptions.add(new ConfigHotkey(NEW_ID, "", "$capital.AddHotkeyCallback.Press"));
		releaseOptions.add(new ConfigHotkey(NEW_ID, "", "$capital.AddHotkeyCallback.Release"));

		final int nextScancode = NEXT_SCANCODE_CONFIG.getIntegerValue();
		final int[] usedScancodes = scancodesOptions.parallelStream().mapToInt(scancode -> scancode.getIntegerValue()).filter(scancode -> scancode >= nextScancode).unordered().toArray();
		sort(usedScancodes);

		int newScancode = nextScancode;
		for (final int i : usedScancodes) {
			if (i != newScancode) break;
			newScancode++;
		}
		scancodesOptions.add(new ConfigInteger(NEW_ID, newScancode, "$capital.AddHotkeyCallback.Scancode"));

		CLIENT.setScreen(new HotkeyScreen(index, CLIENT.screen, true));
		return true;
	}
}
