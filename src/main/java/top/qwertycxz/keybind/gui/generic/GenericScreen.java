package top.qwertycxz.keybind.gui.generic;

import static top.qwertycxz.keybind.ConfigHandler.CATEGORY_GENERIC;
import static top.qwertycxz.keybind.ConfigHandler.CATEGORY_HOTKEYS;
import static top.qwertycxz.keybind.ConfigHandler.GENERIC_OPTIONS;

import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import net.minecraft.client.gui.screens.Screen;
import java.util.List;
import top.qwertycxz.keybind.gui.hotkeys.HotkeysScreen;

public class GenericScreen extends GuiConfigsBase {
	public static String category = CATEGORY_GENERIC;
	public static final int BUTTON_HEIGHT = 20;
	public static final int BUTTON_SPAN = 2;
	public static final int LIST_TOP = 50;
	public static final int PADDING_LEFT = 10;
	public static final int PADDING_TOP = 26;
	public final Screen parent;

	public GenericScreen(Screen parent) {
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
			openGui(new HotkeysScreen(parent));
			return;
		}
		super.initGui();

		ButtonGeneric generic = new ButtonGeneric(PADDING_LEFT, PADDING_TOP, -1, BUTTON_HEIGHT, "$capital.Gui.Generic");
		generic.setEnabled(false);
		addButton(generic, null);
		addButton(new ButtonGeneric(generic.getWidth() + PADDING_LEFT + BUTTON_SPAN, PADDING_TOP, -1, BUTTON_HEIGHT, "$capital.Gui.Hotkeys"), new HotkeysButton(parent));

		clearOptions();
		getListWidget().resetScrollbarPosition();
	}

	@Override
	protected void buildConfigSwitcher() {}
}
