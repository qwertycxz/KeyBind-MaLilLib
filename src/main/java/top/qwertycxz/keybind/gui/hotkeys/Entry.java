package top.qwertycxz.keybind.gui.hotkeys;

import static fi.dy.masa.malilib.gui.MaLiLibIcons.ARROW_DOWN;
import static fi.dy.masa.malilib.gui.MaLiLibIcons.ARROW_UP;
import static fi.dy.masa.malilib.gui.MaLiLibIcons.BTN_PLUSMINUS_16;
import static fi.dy.masa.malilib.gui.MaLiLibIcons.MINUS;
import static top.qwertycxz.keybind.ConfigHandler.HOTKEY_LIST;
import static top.qwertycxz.keybind.gui.GenericScreen.BUTTON_SPAN;

import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.widgets.WidgetLabel;
import fi.dy.masa.malilib.gui.widgets.WidgetListEntryBase;
import top.qwertycxz.keybind.gui.hotkeys.button.Delete;
import top.qwertycxz.keybind.gui.hotkeys.button.Down;
import top.qwertycxz.keybind.gui.hotkeys.button.Edit;
import top.qwertycxz.keybind.gui.hotkeys.button.Up;

public class Entry extends WidgetListEntryBase<Integer> {
	private static final int DOWN_WIDTH = ARROW_DOWN.getWidth();
	private static final int LABEL_SPAN = 10;
	private static final int PLUSMINUS_WIDTH = BTN_PLUSMINUS_16.getWidth();
	private static final int UP_WIDTH = ARROW_UP.getWidth();

	Entry(int x, int y, int width, int height, Integer entry, int index, Entries entries, int labelWidth) {
		super(x, y, width, height, entry, index);
		WidgetLabel background = new WidgetLabel(x, y, width, height, 0);
		if (index % 2 == 1) {
			background.setBackgroundProperties(0, 0x20FFFFFF, 0, 0);
		}
		else {
			background.setBackgroundProperties(0, 0x50FFFFFF, 0, 0);
		}
		addWidget(background);

		ButtonGeneric edit = new ButtonGeneric(x, y, BTN_PLUSMINUS_16, "$capital.Entry.Edit");
		edit.setHeight(height);
		addButton(edit, new Edit(entry));

		addLabel(x + PLUSMINUS_WIDTH + BUTTON_SPAN, y + 5, labelWidth, 8, 0xFFFFFFFF, HOTKEY_LIST.get(entry));
		if (entry > 0) {
			addButton(new ButtonGeneric(x + PLUSMINUS_WIDTH + labelWidth + BUTTON_SPAN + LABEL_SPAN, y, ARROW_UP, "$capital.Entry.Up"), new Up(entries, entry));
		}
		if (entry < HOTKEY_LIST.size() - 1) {
			addButton(new ButtonGeneric(x + PLUSMINUS_WIDTH + labelWidth + UP_WIDTH + BUTTON_SPAN * 2 + LABEL_SPAN, y, ARROW_DOWN, "$capital.Entry.Down"), new Down(entries, entry));
		}
		addButton(new ButtonGeneric(x + PLUSMINUS_WIDTH + labelWidth + UP_WIDTH + DOWN_WIDTH + BUTTON_SPAN * 3 + LABEL_SPAN, y, MINUS, "$capital.Entry.Delete"), new Delete(entries, entry));
	}
}
