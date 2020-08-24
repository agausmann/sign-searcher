package ninja.tokumei.signsearcher;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SignSearcherMod implements ClientModInitializer {
	private KeyBinding searchBinding;
	private SearchGui searchGui;

	@Override
	public void onInitializeClient() {
		this.searchGui = new SearchGui();

		this.searchBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.signsearcher.search",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_SEMICOLON,
				"category.signsearcher"
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (this.searchBinding.wasPressed()) {
				MinecraftClient.getInstance().openScreen(new SearchScreen(this.searchGui));
			}
		});
		ClientBlockEntityEvents.BLOCK_ENTITY_LOAD.register((blockEntity, clientWorld) -> {
			if (blockEntity instanceof SignBlockEntity) {
				searchGui.resultsList.add((SignBlockEntity) blockEntity);
			}
		});
		ClientBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register((blockEntity, clientWorld) -> {
			if (blockEntity instanceof SignBlockEntity) {

			}
		});
	}
}
