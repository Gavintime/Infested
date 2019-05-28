package net.keckle.infested.mixin;

import net.keckle.infested.InfestedMain;

import java.util.Random;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.IWorld;

@Mixin(StructurePiece.class)
public abstract class MixinStructurePiece {

    //shadow variables
    @Shadow
    private BlockMirror mirror;
    @Shadow
    private BlockRotation rotation;
    @Shadow
    private static Set<Block> BLOCKS_NEEDING_POST_PROCESSING;


    //shadow methods
    @Shadow
    abstract protected int applyXTransform(int int_1, int int_2);
    @Shadow
    abstract protected int applyYTransform(int int_1);
    @Shadow
    abstract protected int applyZTransform(int int_1, int int_2);


    //override method to add chance to replace mossy cobble with infested mossy cobble
    public void addBlock(IWorld iWorld_1, BlockState blockState_1, int int_1, int int_2, int int_3, MutableIntBoundingBox mutableIntBoundingBox_1) {

        //EDITED 
        //20% chance for mossy cobblestone from world gen to become infected
        if(blockState_1.equals(Blocks.MOSSY_COBBLESTONE.getDefaultState())) {
            if(new Random().nextInt(12) == 0) blockState_1 = InfestedMain.infMossCobble.getDefaultState();
        }


        BlockPos blockPos_1 = new BlockPos(this.applyXTransform(int_1, int_3), this.applyYTransform(int_2), this.applyZTransform(int_1, int_3));

        if (mutableIntBoundingBox_1.contains(blockPos_1)) {

           if (this.mirror != BlockMirror.NONE) {
              blockState_1 = blockState_1.mirror(this.mirror);
           }
  
           if (this.rotation != BlockRotation.NONE) {
              blockState_1 = blockState_1.rotate(this.rotation);
           }
  
           iWorld_1.setBlockState(blockPos_1, blockState_1, 2);
           FluidState fluidState_1 = iWorld_1.getFluidState(blockPos_1);

           if (!fluidState_1.isEmpty()) {
              iWorld_1.getFluidTickScheduler().schedule(blockPos_1, fluidState_1.getFluid(), 0);
           }
  
           if (BLOCKS_NEEDING_POST_PROCESSING.contains(blockState_1.getBlock())) {
              iWorld_1.getChunk(blockPos_1).markBlockForPostProcessing(blockPos_1);
           }
        }
     }
}