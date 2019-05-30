package net.keckle.infested.mixin;

import net.keckle.infested.InfestedMain;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePiece;


@Mixin(StructurePiece.class)
public abstract class MixinStructurePiece {

   @ModifyVariable(method = "addBlock", at = @At("HEAD"))
   private BlockState onaddBlock(BlockState blockState_1) {

      // 1/12th chance for mossy cobble to become infested
      if (blockState_1.equals(Blocks.MOSSY_COBBLESTONE.getDefaultState())) {
         if (new Random().nextInt(12) == 0)
            blockState_1 = InfestedMain.infMossCobble.getDefaultState();

         // 1/12th chance for bookshelf to become infested
      } else if (blockState_1.equals(Blocks.BOOKSHELF.getDefaultState())) {
         if (new Random().nextInt(12) == 0)
            blockState_1 = InfestedMain.infBookShelf.getDefaultState();
      }
      
      return blockState_1;
   }
}
