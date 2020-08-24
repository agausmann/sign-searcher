package ninja.tokumei.signsearcher;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.minecraft.block.SignBlock;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.chunk.WorldChunk;
import org.lwjgl.glfw.GLFW;

public class SignSearcherMod implements ModInitializer {
	private KeyBinding searchBinding;
	private SearchGui searchGui;

	@Override
	public void onInitialize() {
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
				System.out.println("C LOADED at " + blockEntity.getPos().toShortString());
			}
		});
		ServerBlockEntityEvents.BLOCK_ENTITY_LOAD.register((blockEntity, clientWorld) -> {
			if (blockEntity instanceof SignBlockEntity) {
				System.out.println("S LOADED at " + blockEntity.getPos().toShortString());
			}
		});
		ClientBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register((blockEntity, clientWorld) -> {
			if (blockEntity instanceof SignBlockEntity) {
				System.out.println("C UNLOAD at " + blockEntity.getPos().toShortString());
			}
		});
		ServerBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register((blockEntity, clientWorld) -> {
			if (blockEntity instanceof SignBlockEntity) {
				System.out.println("S UNLOAD at " + blockEntity.getPos().toShortString());
			}
		});
		ClientChunkEvents.CHUNK_LOAD.register((clientWorld, worldChunk) -> {
			System.out.println("C CHUNK LOADED " + worldChunk.getPos());
		});
		ServerChunkEvents.CHUNK_LOAD.register((clientWorld, worldChunk) -> {
			System.out.println("S CHUNK LOADED " + worldChunk.getPos());
		});
		ClientChunkEvents.CHUNK_UNLOAD.register((clientWorld, worldChunk) -> {
			System.out.println("C CHUNK UNLOAD " + worldChunk.getPos());
		});
		ServerChunkEvents.CHUNK_UNLOAD.register((clientWorld, worldChunk) -> {
			System.out.println("S CHUNK UNLOAD " + worldChunk.getPos());
		});
	}
}
