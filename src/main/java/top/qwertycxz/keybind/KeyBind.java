package top.qwertycxz.keybind;

import static fi.dy.masa.malilib.config.ConfigManager.getInstance;
import static fi.dy.masa.malilib.event.InputEventHandler.getKeybindManager;

import net.fabricmc.api.ClientModInitializer;
import top.qwertycxz.keybind.hotkey.add.AddHotkeyKeybind;
import top.qwertycxz.keybind.hotkey.custom.CustomKeybind;

public class KeyBind implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		getInstance().registerConfigHandler("$name", new ConfigHandler());
		getKeybindManager().registerKeybindProvider(new AddHotkeyKeybind());
		getKeybindManager().registerKeybindProvider(new CustomKeybind());
	}
}
