package top.qwertycxz.keybind;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import top.qwertycxz.keybind.gui.GenericScreen;

public class ModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<GenericScreen> getModConfigScreenFactory() {
		return GenericScreen::new;
	}
}
