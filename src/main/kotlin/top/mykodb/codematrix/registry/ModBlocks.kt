package top.mykodb.codematrix.registry

import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.neoforge.registries.DeferredItem
import top.mykodb.codematrix.registry.Registration.BLOCKS

object ModBlocks {

    val EXAMPLE_BLOCK: DeferredItem<BlockItem> = registerBlockItem("example_block", BlockBehaviour.Properties.of())

    private fun registerBlockItem(
        name: String,
        blockProps: BlockBehaviour.Properties = BlockBehaviour.Properties.of(),
        itemProps: Item.Properties = Item.Properties()
    ): DeferredItem<BlockItem> {
        return Registration.ITEMS.registerSimpleBlockItem(
            BLOCKS.registerSimpleBlock(name, blockProps),
            itemProps
        )
    }
}