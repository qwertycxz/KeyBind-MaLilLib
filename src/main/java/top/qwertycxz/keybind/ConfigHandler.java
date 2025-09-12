package top.qwertycxz.keybind;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.ImmutableList.of;
import static fi.dy.masa.malilib.config.ConfigUtils.readConfigBase;
import static fi.dy.masa.malilib.config.ConfigUtils.writeConfigBase;
import static fi.dy.masa.malilib.event.InputEventHandler.getKeybindManager;
import static fi.dy.masa.malilib.hotkeys.KeybindSettings.GUI;
import static fi.dy.masa.malilib.util.JsonUtils.parseJsonFile;
import static fi.dy.masa.malilib.util.JsonUtils.writeJsonToFile;
import static java.nio.file.Files.createDirectories;
import static net.fabricmc.loader.api.FabricLoader.getInstance;
import static net.minecraft.client.resources.language.I18n.get;
import static org.slf4j.LoggerFactory.getLogger;
import static top.qwertycxz.keybind.CustomKeybind.setHotkeyList;

import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;

class ConfigHandler implements IConfigHandler {
	static final String CATEGORY_GENERIC = "Generic";
	static final String CATEGORY_HOTKEYS = "Hotkeys";
	static final String CATEGORY_SCANCODES = "Scancodes";
	static final Path CONFIG_DIR = getInstance().getConfigDir().resolve("$capital");
	static final File CONFIG_FILE = CONFIG_DIR.resolve("config.json").toFile();
	static final ConfigHotkey GENERIC_ADD_HOTKEY = new ConfigHotkey("$capital.ConfigHandler.AddHotkey.Name", "", GUI, "$capital.ConfigHandler.AddHotkey.Comment");
	static final IKeybind GENERIC_ADD_HOTKEY_KEYBIND = GENERIC_ADD_HOTKEY.getKeybind();
	static final ConfigStringList GENERIC_HOTKEYS = new ConfigStringList("$capital.ConfigHandler.Hotkeys.Name", of(), "$capital.ConfigHandler.Hotkeys.Comment");
	static final ConfigInteger GENERIC_NEXT_SCANCODE = new ConfigInteger("$capital.ConfigHandler.NextScancode.Name", 0, "$capital.ConfigHandler.NextScancode.Comment");
	static final List<ConfigBase<?>> GENERIC_OPTIONS = of(GENERIC_ADD_HOTKEY, GENERIC_HOTKEYS, GENERIC_NEXT_SCANCODE);
	static final Logger LOGGER = getLogger(ConfigHandler.class);
	static List<ConfigHotkey> hotkeysOptions = of();
	static List<ConfigInteger> scancodesOptions = of();

	static {
		GENERIC_ADD_HOTKEY_KEYBIND.setCallback(new AddHotkeyCallback());
	}

	@Override
	public void load() {
		try {
			final JsonObject config = parseJsonFile(CONFIG_FILE).getAsJsonObject();
			readConfigBase(config, CATEGORY_GENERIC, GENERIC_OPTIONS);
			scancodesOptions = from(GENERIC_HOTKEYS.getStrings()).transform(hotkey -> new ConfigInteger(hotkey, 0, "$capital.ConfigHandler.Scancode")).toList();
			readConfigBase(config, CATEGORY_SCANCODES, scancodesOptions);
			hotkeysOptions = from(scancodesOptions).transform(scancode -> {
				ConfigHotkey hotkey = new ConfigHotkey(scancode.getName(), "", scancode.getStringValue());
				hotkey.getKeybind().setCallback(new CustomCallback(scancode.getIntegerValue()));
				return hotkey;
			}).toList();
			readConfigBase(config, CATEGORY_HOTKEYS, hotkeysOptions);
			setHotkeyList(hotkeysOptions);
			getKeybindManager().updateUsedKeys();
		}
		catch (Throwable e) {
			LOGGER.warn(get("$capital.ConfigHandler.LoadError"), e);
		}
	}

	@Override
	public void save() {
		try {
			createDirectories(CONFIG_DIR);
			final JsonObject json = new JsonObject();
			writeConfigBase(json, CATEGORY_GENERIC, GENERIC_OPTIONS);
			writeConfigBase(json, CATEGORY_HOTKEYS, hotkeysOptions);
			writeJsonToFile(json, CONFIG_FILE);
		}
		catch (IOException e) {
			throw new RuntimeException(get("$capital.ConfigHandler.SaveError"), e);
		}
	}
}
