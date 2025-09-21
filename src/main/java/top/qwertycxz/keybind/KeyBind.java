package top.qwertycxz.keybind;

import static fi.dy.masa.malilib.config.ConfigManager.getInstance;
import static fi.dy.masa.malilib.event.InputEventHandler.getKeybindManager;

import fi.dy.masa.malilib.config.IConfigManager;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import net.fabricmc.api.ClientModInitializer;
import top.qwertycxz.keybind.hotkey.add.AddHotkeyKeybind;

public class KeyBind implements ClientModInitializer {
	public static final IConfigManager CONFIG = getInstance();
	public static final IKeybindManager KEYBIND = getKeybindManager();

	@Override
	public void onInitializeClient() {
		CONFIG.registerConfigHandler("$name", new ConfigHandler());
		KEYBIND.registerKeybindProvider(new AddHotkeyKeybind());
	}
}
