package ninja.tokumei.signsearcher;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SignSearcherMod implements ClientModInitializer {
	private KeyBinding searchBinding;

	@Override
	public void onInitializeClient() {
		searchBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.signsearcher.search",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_SEMICOLON,
				"category.signsearcher"
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (searchBinding.wasPressed()) {
				MinecraftClient.getInstance().openScreen(new SearchScreen(new SearchGui()));
			}
		});
	}
}
