package top.qwertycxz.keybind;

import static fi.dy.masa.malilib.config.ConfigUtils.readConfigBase;
import static fi.dy.masa.malilib.config.ConfigUtils.writeConfigBase;
import static fi.dy.masa.malilib.hotkeys.KeybindSettings.GUI;
import static fi.dy.masa.malilib.util.JsonUtils.parseJsonFile;
import static fi.dy.masa.malilib.util.JsonUtils.writeJsonToFile;
import static java.nio.file.Files.createDirectories;
import static java.util.Collections.singletonList;
import static net.fabricmc.loader.api.FabricLoader.getInstance;
import static net.minecraft.client.resources.language.I18n.get;
import static org.slf4j.LoggerFactory.getLogger;

import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;

class ConfigHandler implements IConfigHandler {
	static final String CATEGORY_COMMON = "Common";
	static final ConfigHotkey CONFIG_ADD_HOTKEY = new ConfigHotkey("$capital.ConfigHandler.AddHotkey.Name", "", GUI, "$capital.ConfigHandler.AddHotkey.Comment");
	static final Path CONFIG_DIR = getInstance().getConfigDir().resolve("$capital");
	static final File CONFIG_FILE = CONFIG_DIR.resolve("config.json").toFile();
	static final IKeybind KRYBIND_ADD_HOTKEY = CONFIG_ADD_HOTKEY.getKeybind();
	static final Logger LOGGER = getLogger(ConfigHandler.class);
	static final List<ConfigHotkey> OPTIONS_COMMON = singletonList(CONFIG_ADD_HOTKEY);

	static {
		KRYBIND_ADD_HOTKEY.setCallback(new AddHotkey());
	}

	@Override
	public void load() {
		try {
			readConfigBase(parseJsonFile(CONFIG_FILE).getAsJsonObject(), CATEGORY_COMMON, OPTIONS_COMMON);
		}
		catch (Throwable e) {
			LOGGER.warn(get("$capital.ConfigHandler.LoadError"), e);
		}
	}

	@Override
	public void save() {
		try {
			createDirectories(CONFIG_DIR);
			final var json = new JsonObject();
			writeConfigBase(json, CATEGORY_COMMON, OPTIONS_COMMON);
			writeJsonToFile(json, CONFIG_FILE);
		}
		catch (IOException e) {
			throw new RuntimeException(get("$capital.ConfigHandler.SaveError"), e);
		}
	}
}
