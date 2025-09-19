package top.qwertycxz.keybind.gui.hotkey;

import static net.minecraft.network.chat.Style.EMPTY;
import static net.minecraft.util.FormattedCharSequence.forward;
import static top.qwertycxz.keybind.ConfigHandler.HOTKEY_LIST;
import static top.qwertycxz.keybind.gui.HotkeyScreen.DUPLICATE;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.gui.GuiConfigsBase.ConfigOptionWrapper;
import fi.dy.masa.malilib.gui.GuiTextFieldGeneric;
import fi.dy.masa.malilib.gui.widgets.WidgetConfigOption;
import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptionsBase;
import top.qwertycxz.keybind.gui.HotkeyScreen;

class Entry extends WidgetConfigOption {
	Entry(int x, int y, int width, int height, int labelWidth, int configWidth, ConfigOptionWrapper wrapper, int listIndex, HotkeyScreen host, WidgetListConfigOptionsBase<?, ?> parent) {
		super(x, y, width, height, labelWidth, configWidth, wrapper, listIndex, host, parent);
		if (wrapper.getConfig() instanceof ConfigString) {
			GuiTextFieldGeneric text = textField.getTextField();
			text.setFormatter((string, position) -> forward(string, host.idStyle));
			text.setResponder(string -> {
				int index = HOTKEY_LIST.indexOf(string);
				if (index == -1 || index == host.index) {
					host.idStyle = EMPTY;
				}
				else {
					host.idStyle = DUPLICATE;
				}
			});
		}
	}

	@Override
	protected void addLabel(int x, int y, int width, int height, int color, String... lines) {
		if (wrapper.getConfig() instanceof ConfigHotkey) {
			super.addLabel(x, y, width, height, color, "$capital.Entry.Hotkey");
		}
		else if (wrapper.getConfig() instanceof ConfigInteger) {
			super.addLabel(x, y, width, height, color, "$capital.Entry.Scancode");
		}
		else {
			super.addLabel(x, y, width, height, color, lines);
		}
	}
}
