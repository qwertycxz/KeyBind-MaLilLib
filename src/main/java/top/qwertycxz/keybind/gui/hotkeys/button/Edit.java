package top.qwertycxz.keybind.gui.hotkeys.button;

import static net.minecraft.client.Minecraft.getInstance;

import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import net.minecraft.client.Minecraft;
import top.qwertycxz.keybind.gui.HotkeyScreen;

public class Edit implements IButtonActionListener {
	private final Minecraft client;
	private final int entry;

	public Edit(final int entry) {
		client = getInstance();
		this.entry = entry;
	}

	@Override
	public void actionPerformedWithButton(final ButtonBase button, final int mouse) {
		client.setScreen(new HotkeyScreen(entry, client.screen));
	}
}
