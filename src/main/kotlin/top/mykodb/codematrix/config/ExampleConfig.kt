package top.mykodb.codematrix.config

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.common.ModConfigSpec

class ExampleConfig(builder: ModConfigSpec.Builder){
    val BOOLEAN: ModConfigSpec.BooleanValue = builder
        .comment("布尔值")
        .define("Boolean", true)

    val INT : ModConfigSpec.IntValue = builder
        .comment("Int值")
        .defineInRange("Int", 42, 0, Int.MAX_VALUE)

    val STRING: ModConfigSpec.ConfigValue<String> = builder
        .comment("字符串")
        .define("String", "字符串内容")

    val ITEM_LIST: ModConfigSpec.ConfigValue<MutableList<out String>> = builder
        .comment("可变列表内容为物品id")
        .defineListAllowEmpty(
            "ListString",
            mutableListOf("minecraft:iron_ingot"),
            { "" },
            { obj: Any ->obj is String && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(obj)) }
        )
}