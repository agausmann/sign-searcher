package ninja.tokumei.signsearcher;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import ninja.tokumei.signsearcher.event.SignUpdateCallback;
import ninja.tokumei.signsearcher.ext.BlockEntityExt;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class SignSearcherMod implements ClientModInitializer {
	private KeyBinding searchBinding;
	private HashSet<SignBlockEntity> signCache;
	private ArrayList<SignBlockEntity> signList;
	private boolean dirty;
	private SearchGui searchGui;

	@Override
	public void onInitializeClient() {
		this.searchBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.signsearcher.search",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_SEMICOLON,
				"category.signsearcher"
		));
		this.signCache = new HashSet<>();
		this.signList = new ArrayList<>();
		this.dirty = false;
		this.searchGui = new SearchGui(this.signList);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (this.searchBinding.wasPressed()) {
				this.updateList();
				this.signList.sort(Comparator.comparing((blockEntity) -> blockEntity.getPos().getSquaredDistance(client.player.getBlockPos())));
				MinecraftClient.getInstance().openScreen(new SearchScreen(this.searchGui));
			}
		});
		SignUpdateCallback.EVENT.register((blockEntity) -> {
			this.dirty |= this.signCache.add(blockEntity);
			((BlockEntityExt) blockEntity).setGlowing(true);
		});
		ClientBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register((blockEntity, world) -> {
			if (blockEntity instanceof SignBlockEntity) {
				this.dirty |= this.signCache.remove(blockEntity);
			}
		});
	}

	public void updateList() {
		if (this.dirty) {
			this.signList.clear();
			this.signList.addAll(signCache);
			this.dirty = false;
		}
	}
}
