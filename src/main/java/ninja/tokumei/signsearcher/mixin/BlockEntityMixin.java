package ninja.tokumei.signsearcher.mixin;

import net.minecraft.block.entity.BlockEntity;
import ninja.tokumei.signsearcher.ext.BlockEntityExt;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements BlockEntityExt {
    private boolean glowing = false;
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
