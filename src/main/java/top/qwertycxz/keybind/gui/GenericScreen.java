package top.qwertycxz.keybind.gui;

import static java.util.Arrays.asList;
import static top.qwertycxz.keybind.ConfigHandler.ADD_HOTKEY_CONFIG;
import static top.qwertycxz.keybind.ConfigHandler.GENERIC_CATEGORY;
import static top.qwertycxz.keybind.ConfigHandler.NEXT_SCANCODE_CONFIG;
import static top.qwertycxz.keybind.hotkey.custom.CustomCallback.CLIENT;

import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import java.util.List;
import net.minecraft.client.gui.screens.Screen;
import top.qwertycxz.keybind.gui.navigate.HotkeysButton;

public class GenericScreen extends GuiConfigsBase {
	public static final int BUTTON_SPAN = 2;
	public static final String HOTKEY_CATEGORY = "Hotkeys";
	public static String category = GENERIC_CATEGORY;
	private static final List<ConfigBase<?>> OPTIONS = asList(ADD_HOTKEY_CONFIG, NEXT_SCANCODE_CONFIG);
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
		return ConfigOptionWrapper.createFor(OPTIONS);
	}

	@Override
	public void initGui() {
		if (HOTKEY_CATEGORY.equals(category)) {
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
