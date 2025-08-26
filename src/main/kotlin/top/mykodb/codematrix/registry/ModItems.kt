package top.mykodb.codematrix.registry

import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredItem
import top.mykodb.codematrix.registry.Registration.ITEMS

object ModItems {

    val EXAMPLE_ITEM: DeferredItem<Item> = ITEMS.registerSimpleItem(
        "example_item", Item.Properties().food(
            FoodProperties.Builder()
                .alwaysEdible().nutrition(1).saturationModifier(2f).build()
        )
    )

    init {
        InitManager.registerModule {
            // 这里可以放置ModItems模块复杂的初始化逻辑。
            // 例如：注册燃料、配置合成表等需要在其他模块之后进行的操作。
            // 如果不需要复杂的初始化，这个函数体可以是空的，但注册行为本身是必须的。
        }
    }
}