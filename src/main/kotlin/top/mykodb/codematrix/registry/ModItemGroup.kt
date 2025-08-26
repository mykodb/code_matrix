package top.mykodb.codematrix.registry

import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTabs
import net.neoforged.neoforge.registries.DeferredHolder
import top.mykodb.codematrix.Mod.MODID
import top.mykodb.codematrix.registry.Registration.CREATIVE_MODE_TABS
import java.util.function.Supplier

object ModItemGroup {
    val EXAMPLE_TAB: DeferredHolder<CreativeModeTab, CreativeModeTab> =
        CREATIVE_MODE_TABS.register("example_tab", Supplier {
            CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.$MODID"))
                .withTabsBefore(CreativeModeTabs.COMBAT)
                .icon { ModBlocks.EXAMPLE_BLOCK.get().defaultInstance }
                .displayItems { _: CreativeModeTab.ItemDisplayParameters, output: CreativeModeTab.Output ->
                    output.accept(ModBlocks.EXAMPLE_BLOCK)
                    output.accept(ModItems.EXAMPLE_ITEM)
                }.build()
        })
}