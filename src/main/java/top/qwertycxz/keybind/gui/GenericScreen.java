package top.qwertycxz.keybind.gui;

import static top.qwertycxz.keybind.ConfigHandler.CATEGORY_GENERIC;
import static top.qwertycxz.keybind.ConfigHandler.GENERIC_OPTIONS;
import static top.qwertycxz.keybind.hotkey.custom.Press.CLIENT;

import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import java.util.List;
import net.minecraft.client.gui.screens.Screen;
import top.qwertycxz.keybind.gui.navigate.HotkeysButton;

public class GenericScreen extends GuiConfigsBase {
	public static final int BUTTON_SPAN = 2;
	public static final String CATEGORY_HOTKEYS = "Hotkeys";
	public static String category = CATEGORY_GENERIC;
	static final int BUTTON_HEIGHT = 20;
	static final int LIST_TOP = 50;
	static final int PADDING_LEFT = 10;
	static final int PADDING_TOP = 26;
	private final Screen parent;

	public GenericScreen(final Screen parent) {
		super(PADDING_LEFT, LIST_TOP, "$name", null, "$capital.Gui.Title");
		this.parent = parent;
		setParent(parent);
	}

	@Override
	public List<ConfigOptionWrapper> getConfigs() {
		return ConfigOptionWrapper.createFor(GENERIC_OPTIONS);
	}

	@Override
	public void initGui() {
		if (CATEGORY_HOTKEYS.equals(category)) {
			CLIENT.setScreen(new HotkeysScreen(parent));
			return;
		}
		super.initGui();

		final ButtonGeneric generic = new ButtonGeneric(PADDING_LEFT, PADDING_TOP, -1, BUTTON_HEIGHT, "$capital.Gui.Generic");
		generic.setEnabled(false);
		addButton(generic, null);
		addButton(new ButtonGeneric(generic.getWidth() + PADDING_LEFT + BUTTON_SPAN, PADDING_TOP, -1, BUTTON_HEIGHT, "$capital.Gui.Hotkeys"), new HotkeysButton(parent));

		clearOptions();
		getListWidget().resetScrollbarPosition();
	}

	@Override
	protected void buildConfigSwitcher() {}
}
