package top.qwertycxz.keybind.gui;

import static top.qwertycxz.keybind.gui.GenericScreen.BUTTON_HEIGHT;
import static top.qwertycxz.keybind.gui.GenericScreen.BUTTON_SPAN;
import static top.qwertycxz.keybind.gui.GenericScreen.LIST_TOP;
import static top.qwertycxz.keybind.gui.GenericScreen.PADDING_LEFT;
import static top.qwertycxz.keybind.gui.GenericScreen.PADDING_TOP;

import fi.dy.masa.malilib.gui.GuiListBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import net.minecraft.client.gui.screens.Screen;
import top.qwertycxz.keybind.gui.hotkeys.Entries;
import top.qwertycxz.keybind.gui.hotkeys.Entry;
import top.qwertycxz.keybind.gui.navigate.GenericButton;

public class HotkeysScreen extends GuiListBase<Integer, Entry, Entries> {
	private final Screen parent;

	public HotkeysScreen(final Screen parent) {
		super(PADDING_LEFT, LIST_TOP);
		title = "$capital.Gui.Title";
		this.parent = parent;
		setParent(parent);
	}

	@Override
	public void initGui() {
		super.initGui();
		final ButtonGeneric generic = new ButtonGeneric(PADDING_LEFT, PADDING_TOP, -1, BUTTON_HEIGHT, "$capital.Gui.Generic");
		addButton(generic, new GenericButton(parent));
		final ButtonGeneric hotkeys = new ButtonGeneric(generic.getWidth() + PADDING_LEFT + BUTTON_SPAN, PADDING_TOP, -1, BUTTON_HEIGHT, "$capital.Gui.Hotkeys");
		hotkeys.setEnabled(false);
		addButton(hotkeys, null);
	}

	@Override
	protected Entries createListWidget(final int x, final int y) {
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
