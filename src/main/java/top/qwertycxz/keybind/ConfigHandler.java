package top.qwertycxz.keybind;

import static com.google.common.collect.ImmutableList.of;
import static com.google.common.collect.Lists.transform;
import static fi.dy.masa.malilib.config.ConfigUtils.readConfigBase;
import static fi.dy.masa.malilib.config.ConfigUtils.writeConfigBase;
import static fi.dy.masa.malilib.config.ConfigUtils.writeHotkeys;
import static fi.dy.masa.malilib.hotkeys.KeybindSettings.GUI;
import static fi.dy.masa.malilib.util.JsonUtils.parseJsonFile;
import static fi.dy.masa.malilib.util.JsonUtils.writeJsonToFile;
import static java.nio.file.Files.createDirectories;
import static net.fabricmc.loader.api.FabricLoader.getInstance;
import static net.minecraft.client.resources.language.I18n.get;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static top.qwertycxz.keybind.KeyBind.KEYBIND;

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
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import top.qwertycxz.keybind.hotkey.add.AddHotkeyCallback;
import top.qwertycxz.keybind.hotkey.custom.CustomCallback;
import top.qwertycxz.keybind.hotkey.custom.CustomKeybind;

public class ConfigHandler implements IConfigHandler {
	public static final ConfigHotkey ADD_HOTKEY_CONFIG = new ConfigHotkey("$capital.ConfigHandler.AddHotkey.Name", "", GUI, "$capital.ConfigHandler.AddHotkey.Comment");
	public static final IKeybind ADD_HOTKEY_KEYBIND = ADD_HOTKEY_CONFIG.getKeybind();
	private static final ConfigStringList HOTKEYS_CONFIG = new ConfigStringList("$capital.ConfigHandler.Hotkeys.Name", of(), "$capital.ConfigHandler.Hotkeys.Comment");
	public static final List<String> HOTKEY_LIST = HOTKEYS_CONFIG.getStrings();
	public static final ConfigInteger NEXT_SCANCODE_CONFIG = new ConfigInteger("$capital.ConfigHandler.NextScancode.Name", 1000, "$capital.ConfigHandler.NextScancode.Comment");
	public static final List<ConfigBase<?>> GENERIC_OPTIONS = of(ADD_HOTKEY_CONFIG, HOTKEYS_CONFIG, NEXT_SCANCODE_CONFIG);
	public static ArrayList<ConfigHotkey> pressOptions = new ArrayList<>();
	public static ArrayList<ConfigHotkey> releaseOptions = new ArrayList<>();
	public static ArrayList<ConfigInteger> scancodesOptions = new ArrayList<>();
	public static final String CATEGORY_GENERIC = "Generic";
	private static final String CATEGORY_PRESS = "Press";
	private static final String CATEGORY_RELEASE = "Release";
	private static final String CATEGORY_SCANCODES = "Scancodes";
	private static final Path CONFIG_DIR = getInstance().getConfigDir().resolve("$capital");
	private static final File CONFIG_FILE = CONFIG_DIR.resolve("config.json").toFile();
	private static final Logger LOGGER = getLogger(ConfigHandler.class);
	private static CustomKeybind hotkeysKeybind;

	static {
		ADD_HOTKEY_KEYBIND.setCallback(new AddHotkeyCallback());
	}

	@Override
	public void load() {
		try {
			KEYBIND.unregisterKeybindProvider(hotkeysKeybind);
			final JsonObject config = parseJsonFile(CONFIG_FILE).getAsJsonObject();
			readConfigBase(config, CATEGORY_GENERIC, GENERIC_OPTIONS);

			scancodesOptions = new ArrayList<>(transform(HOTKEY_LIST, hotkey -> new ConfigInteger(hotkey, 0, "")));
			readConfigBase(config, CATEGORY_SCANCODES, scancodesOptions);

			List<ConfigHotkey> hotkeys = transform(HOTKEY_LIST, hotkey -> new ConfigHotkey(hotkey, "", ""));
			pressOptions = new ArrayList<>(hotkeys);
			releaseOptions = new ArrayList<>(hotkeys);
			readConfigBase(config, CATEGORY_PRESS, pressOptions);
			readConfigBase(config, CATEGORY_RELEASE, releaseOptions);

			for (int i = 0; i < HOTKEY_LIST.size(); i++) {
				final String id = HOTKEY_LIST.get(i);
				int scancode = scancodesOptions.get(i).getIntegerValue();
				scancodesOptions.set(i, new ConfigInteger(id, scancode, "$capital.ConfigHandler.Scancode"));

				final ConfigHotkey pressRaw = pressOptions.get(i);
				final ConfigHotkey pressNew = new ConfigHotkey(id, pressRaw.getStringValue(), "$capital.ConfigHandler.Press");
				final IKeybind pressKeybind = pressNew.getKeybind();
				pressKeybind.setCallback(new CustomCallback(GLFW_PRESS, scancode));
				pressKeybind.setSettings(pressRaw.getKeybind().getSettings());
				pressOptions.set(i, pressNew);

				final ConfigHotkey releaseRaw = releaseOptions.get(i);
				final ConfigHotkey releaseNew = new ConfigHotkey(id, releaseRaw.getStringValue(), "$capital.ConfigHandler.Release");
				final IKeybind releaseKeybind = releaseNew.getKeybind();
				releaseKeybind.setCallback(new CustomCallback(GLFW_RELEASE, scancode));
				releaseKeybind.setSettings(releaseRaw.getKeybind().getSettings());
				releaseOptions.set(i, releaseNew);
			}

			hotkeysKeybind = new CustomKeybind(pressOptions, releaseOptions);
			KEYBIND.registerKeybindProvider(hotkeysKeybind);
		}
		catch (final Throwable e) {
			LOGGER.warn(get("$capital.ConfigHandler.LoadError"), e);
		}
	}

	@Override
	public void save() {
		try {
			createDirectories(CONFIG_DIR);
			final JsonObject json = new JsonObject();

			writeConfigBase(json, CATEGORY_GENERIC, GENERIC_OPTIONS);
			writeHotkeys(json, CATEGORY_PRESS, pressOptions);
			writeHotkeys(json, CATEGORY_RELEASE, releaseOptions);
			writeConfigBase(json, CATEGORY_SCANCODES, scancodesOptions);
			writeJsonToFile(json, CONFIG_FILE);
		}
		catch (final IOException e) {
			throw new RuntimeException(get("$capital.ConfigHandler.SaveError"), e);
		}
	}
}
