package ninja.tokumei.signsearcher.mixin;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignBlockEntity.class)
public class SignBlockEntityInject {
    //public void setTextOnRow(int row, Text text)
    @Inject(method = "setTextOnRow(ILnet/minecraft/text/Text;)V", at = @At("HEAD"))
    void handleSetText(int row, Text text, CallbackInfo info) {
        System.out.println(String.format("SETTEXT %d %s", row, text));
    }

    //public SignBlockEntity()
    @Inject(method = "<init>()V", at = @At("RETURN"))
    void handleInit(CallbackInfo info) {
        System.out.println("CONS");
    }
}
