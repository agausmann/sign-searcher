package ninja.tokumei.signsearcher;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class OpenCallback implements UseItemCallback {
    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity playerEntity, World world, Hand hand) {
        if (playerEntity.getStackInHand(hand).getItem() == Items.STICK && world.isClient()) {
            System.out.println("Open Menu");
            MinecraftClient.getInstance().openScreen(new SearchScreen(new SearchGui()));
            return TypedActionResult.fail(ItemStack.EMPTY);
        }
        return TypedActionResult.pass(ItemStack.EMPTY);
    }
}
