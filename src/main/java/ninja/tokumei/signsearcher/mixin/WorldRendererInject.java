package ninja.tokumei.signsearcher.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public class WorldRendererInject {
    @ModifyVariable(
        //public void render(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f)
        method = "render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V",

        //VertexConsumerProvider.Immediate immediate = this.bufferBuilders.getEntityVertexConsumers();
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/render/BufferBuilderStorage.getEntityVertexConsumers()Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;"
        ),
        name = "bl3",
        ordinal = 2,
        index = 37
    )
    public boolean forceOutline(boolean orig) {
        return true;
    }

    @Redirect(
        //public void render(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f)
        method = "render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V",

        //BlockEntityRenderDispatcher.INSTANCE.render(blockEntity, tickDelta, matrices, vertexConsumerProvider3);
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/render/block/entity/BlockEntityRenderDispatcher.render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V",
            ordinal = 0
        )
    )
    public void renderWithOutline1(BlockEntityRenderDispatcher dispatcher, BlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider originalVcp) {
        OutlineVertexConsumerProvider outlineVcp = ((WorldRendererMixin) this).getBufferBuilders().getOutlineVertexConsumers();
        dispatcher.render(blockEntity, tickDelta, matrices, outlineVcp);
    }

    @Redirect(
        //public void render(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f)
        method = "render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V",

        //BlockEntityRenderDispatcher.INSTANCE.render(blockEntity, tickDelta, matrices, vertexConsumerProvider3);
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/render/block/entity/BlockEntityRenderDispatcher.render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V",
            ordinal = 1
        )
    )
    public void renderWithOutline2(BlockEntityRenderDispatcher dispatcher, BlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider originalVcp) {
        OutlineVertexConsumerProvider outlineVcp = ((WorldRendererMixin) this).getBufferBuilders().getOutlineVertexConsumers();
        dispatcher.render(blockEntity, tickDelta, matrices, outlineVcp);
    }

    /*
    @Inject(
        //public void render(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f)
        method = "render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V",

        //BlockEntityRenderDispatcher.INSTANCE.render(blockEntity, tickDelta, matrices, vertexConsumerProvider3);
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/render/block/entity/BlockEntityRenderDispatcher.render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V",
            ordinal = 0
        )
    )
    public void setupBlockEntityOutline(CallbackInfo ci) {
    }

    @ModifyVariable(
        //public void render(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f)
        method = "render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V",

        //BlockEntityRenderDispatcher.INSTANCE.render(blockEntity, tickDelta, matrices, vertexConsumerProvider3);
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/client/render/block/entity/BlockEntityRenderDispatcher.render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V",
            ordinal = 0
        ),
        name = "vertexConsumerProvider3"
    )
    public VertexConsumerProvider setBlockEntityOutlineVertexConsumerProvider(VertexConsumerProvider orig) {
        OutlineVertexConsumerProvider outlineVcp = ((WorldRendererMixin) this).getBufferBuilders().getOutlineVertexConsumers();
        outlineVcp.setColor(255, 127, 0, 255);
        return outlineVcp;
    }
    */
}
