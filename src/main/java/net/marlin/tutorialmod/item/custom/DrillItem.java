package net.marlin.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class DrillItem extends Item {
    public DrillItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        if (!world.isClient()){
            world.breakBlock(context.getBlockPos(), true);
            world.breakBlock(context.getBlockPos().north(), true);
            world.breakBlock(context.getBlockPos().north().east(), true);
            world.breakBlock(context.getBlockPos().north().west(), true);
            world.breakBlock(context.getBlockPos().east(), true);
            world.breakBlock(context.getBlockPos().west(), true);
            world.breakBlock(context.getBlockPos().south(), true);
            world.breakBlock(context.getBlockPos().south().east(), true);
            world.breakBlock(context.getBlockPos().south().west(), true);

            context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                    item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

            world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS);
        }

        return ActionResult.SUCCESS;
    }

}
