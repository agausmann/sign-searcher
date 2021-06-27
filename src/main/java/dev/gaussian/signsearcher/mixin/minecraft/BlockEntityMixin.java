package dev.gaussian.signsearcher.mixin.minecraft;

import dev.gaussian.signsearcher.ext.BlockEntityExt;
import net.minecraft.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements BlockEntityExt {
    @Unique
    private boolean glowing = false;
    @Unique
    private int glowColor = 0xffffff;

    public boolean isGlowing() {
        return this.glowing;
    }

    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
    }

    public int getGlowColor() {
        return this.glowColor;
    }

    public void setGlowColor(int glowColor) {
        this.glowColor = glowColor;
    }
}
