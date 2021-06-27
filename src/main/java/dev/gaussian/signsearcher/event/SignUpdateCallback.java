package dev.gaussian.signsearcher.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.entity.SignBlockEntity;

@FunctionalInterface
public interface SignUpdateCallback {
    Event<SignUpdateCallback> EVENT = EventFactory.createArrayBacked(SignUpdateCallback.class,
        (listeners) -> (blockEntity) -> {
            for (SignUpdateCallback listener : listeners) {
                listener.update(blockEntity);
            }
        });

    void update(SignBlockEntity blockEntity);
}
