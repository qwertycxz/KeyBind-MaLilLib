package top.qwertycxz.keybind.gui.hotkeys;

import static top.qwertycxz.keybind.gui.generic.GenericScreen.BUTTON_HEIGHT;
import static top.qwertycxz.keybind.gui.generic.GenericScreen.BUTTON_SPAN;
import static top.qwertycxz.keybind.gui.generic.GenericScreen.LIST_TOP;
import static top.qwertycxz.keybind.gui.generic.GenericScreen.PADDING_LEFT;
import static top.qwertycxz.keybind.gui.generic.GenericScreen.PADDING_TOP;

import fi.dy.masa.malilib.gui.GuiListBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import net.minecraft.client.gui.screens.Screen;
import top.qwertycxz.keybind.gui.hotkeys.list.Entry;
import top.qwertycxz.keybind.gui.hotkeys.list.Entries;

public class HotkeysScreen extends GuiListBase<Integer, Entry, Entries> {
	private final Screen parent;

	public HotkeysScreen(Screen parent) {
		super(PADDING_LEFT, LIST_TOP);
		title = "$capital.Gui.Title";
		this.parent = parent;
		setParent(parent);
	}

	@Override
	public void initGui() {
		super.initGui();
		ButtonGeneric generic = new ButtonGeneric(PADDING_LEFT, PADDING_TOP, -1, BUTTON_HEIGHT, "$capital.Gui.Generic");
		addButton(generic, new GenericButton(parent));
		ButtonGeneric hotkeys = new ButtonGeneric(generic.getWidth() + PADDING_LEFT + BUTTON_SPAN, PADDING_TOP, -1, BUTTON_HEIGHT, "$capital.Gui.Hotkeys");
		hotkeys.setEnabled(false);
		addButton(hotkeys, null);
	}

	@Override
	protected Entries createListWidget(int x, int y) {
		return new Entries(x, y, getBrowserWidth(), getBrowserHeight());
	}

	@Override
	protected int getBrowserHeight() {
		return height - 80;
	}

	@Override
	protected int getBrowserWidth() {
		return width - 20;
	}
}
