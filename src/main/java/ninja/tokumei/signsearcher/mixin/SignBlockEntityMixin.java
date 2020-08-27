package ninja.tokumei.signsearcher.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import ninja.tokumei.signsearcher.event.SignUpdateCallback;
import ninja.tokumei.signsearcher.ext.SignBlockEntityExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignBlockEntity.class)
public abstract class SignBlockEntityMixin implements SignBlockEntityExt {
    @Accessor
    public abstract Text[] getText();

    //public void fromTag(BlockState state, CompoundTag tag)
    @Inject(method = "fromTag(Lnet/minecraft/block/BlockState;Lnet/minecraft/nbt/CompoundTag;)V", at = @At("RETURN"))
    void handleUpdate(BlockState state, CompoundTag tag, CallbackInfo info) {
        SignUpdateCallback.EVENT.invoker().update((SignBlockEntity) (Object) this);
    }
}
