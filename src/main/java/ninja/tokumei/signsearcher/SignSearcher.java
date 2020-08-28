package ninja.tokumei.signsearcher;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import ninja.tokumei.signsearcher.event.SignUpdateCallback;
import ninja.tokumei.signsearcher.ext.BlockEntityExt;
import ninja.tokumei.signsearcher.ext.SignBlockEntityExt;

import java.util.HashSet;
import java.util.Optional;
import java.util.PrimitiveIterator;

public class SignSearcher implements ClientModInitializer, ClientCommandPlugin {
	private static final HashSet<SignBlockEntity> signs = new HashSet<>();
	private static String searchText = "";

	@Override
	public void onInitializeClient() {
		System.out.println("Client init");
		SignUpdateCallback.EVENT.register(SignSearcher::addSign);
		ClientBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register((blockEntity, world) -> {
			if (blockEntity instanceof SignBlockEntity) {
				removeSign((SignBlockEntity) blockEntity);
			}
		});
	}

	@Override
	public void registerCommands(CommandDispatcher<CottonClientCommandSource> commandDispatcher) {
		commandDispatcher.register(
			ArgumentBuilders.literal("signsearch")
				.then(
					ArgumentBuilders.argument("query", StringArgumentType.greedyString())
						.executes(context -> {
							String searchText = StringArgumentType.getString(context, "query");
							setSearchText(searchText);
							context.getSource().sendFeedback(new LiteralText(String.format("Searching for `%s`...", searchText)));
							return 1;
						})
				)
				.executes(context -> {
					setSearchText("");
					context.getSource().sendFeedback(new LiteralText("Cleared search"));
					return 1;
				})
		);
	}

	public static void updateSign(SignBlockEntity sign) {
		((BlockEntityExt) sign).setGlowing(matchesSign(sign));
	}

	public static boolean matchesSign(SignBlockEntity sign) {
		if (searchText.equals("")) {
			return false;
		}
		StringBuilder signText = new StringBuilder();
		for (Text line : ((SignBlockEntityExt) sign).getText()) {
			line.visit((part) -> {
				signText.append(part);
				return Optional.empty();
			});
			signText.append(" ");
		}
		return fuzzyContains(signText.toString(), searchText);
	}

	public static void addSign(SignBlockEntity sign) {
		signs.add(sign);
		updateSign(sign);
	}

	public static void removeSign(SignBlockEntity sign) {
		signs.remove(sign);
	}

	public static void setSearchText(String searchText) {
		if (!SignSearcher.searchText.equals(searchText)) {
			SignSearcher.searchText = searchText;
			for (SignBlockEntity sign : signs) {
				updateSign(sign);
			}
		}
	}

	private static boolean fuzzyContains(String a, String b) {
		for (int i = 0; i < a.length(); i++) {
			if (fuzzyStartsWith(a.substring(i), b)) {
				return true;
			}
		}
		return false;
	}

	private static boolean fuzzyStartsWith(String a, String b) {
		PrimitiveIterator.OfInt aChars = a.trim().chars().iterator();
		PrimitiveIterator.OfInt bChars = b.trim().chars().iterator();

		while (aChars.hasNext() && bChars.hasNext()) {
			int aChar = aChars.nextInt();
			int bChar = bChars.nextInt();

			while (Character.isWhitespace(aChar) && Character.isWhitespace(bChar) && aChars.hasNext() && bChars.hasNext()) {
				aChar = aChars.nextInt();
				bChar = bChars.nextInt();
			}

			if (Character.toLowerCase(aChar) != Character.toLowerCase(bChar)) {
				return false;
			}
		}

		return !bChars.hasNext();
	}
}
