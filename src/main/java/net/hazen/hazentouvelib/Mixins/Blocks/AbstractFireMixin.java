package net.hazen.hazentouvelib.Mixins.Blocks;

import net.hazen.hazentouvelib.Blocks.SoulFire.SoulFireBlock;
import net.hazen.hazentouvelib.Datagen.HLTags;
import net.hazen.hazentouvelib.Registries.HLBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
    BaseFireBlock.class
)
public abstract class AbstractFireMixin {

    @Inject(
        at = @At(
            "HEAD"
        ), method = "getState", cancellable = true
    )
    private static void getFireState(BlockGetter world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate
            .is(
                HLTags.Blocks.SOUL_FIRE_BASE_BLOCK
            ) || SoulFireBlock.EXPLOSION_CAUSES_SOUL_FIRE_FLAG) {
            SoulFireBlock.EXPLOSION_CAUSES_SOUL_FIRE_FLAG = false;
            cir
                .setReturnValue(
                    ((SoulFireBlock) HLBlockRegistry.SOUL_FIRE.get()).getStateForPosition(world, pos)
                );
        }
    }

}