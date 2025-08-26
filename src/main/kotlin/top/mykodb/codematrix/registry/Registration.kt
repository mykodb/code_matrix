package top.mykodb.codematrix.registry

import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.CreativeModeTab
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import top.mykodb.codematrix.Mod.MODID

object Registration {
    /**
     * 延迟注册: 物品
     */
    val ITEMS: DeferredRegister.Items =  DeferredRegister.createItems(MODID)

    /**
     * 延迟注册: 方块
     */
    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(MODID)

    /**
     * 延迟注册: 创造模式选项卡
     */
    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID)

    /**
     * 延迟注册: 物品堆叠组件
     */
    val DATA_COMPONENTS_TYPE: DeferredRegister<DataComponentType<*>> = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MODID)

    fun registerAll(bus: IEventBus) {
        ITEMS.register(bus)
        BLOCKS.register(bus)
        CREATIVE_MODE_TABS.register(bus)
        DATA_COMPONENTS_TYPE.register(bus)
    }
}