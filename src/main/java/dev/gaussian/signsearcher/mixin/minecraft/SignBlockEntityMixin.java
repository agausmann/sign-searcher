package dev.gaussian.signsearcher.mixin.minecraft;

import dev.gaussian.signsearcher.event.SignUpdateCallback;
import dev.gaussian.signsearcher.ext.SignBlockEntityExt;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignBlockEntity.class)
public abstract class SignBlockEntityMixin implements SignBlockEntityExt {
    @Accessor
    public abstract Text[] getTexts();

    //public void fromTag(BlockState state, CompoundTag tag)
    @Inject(method = "readNbt", at = @At("RETURN"))
    void handleUpdate(NbtCompound nbt, CallbackInfo ci) {
        SignUpdateCallback.EVENT.invoker().update((SignBlockEntity) (Object) this);
    }
}
