package top.mykodb.codematrix.registry

import net.minecraft.core.component.DataComponentType
import top.mykodb.codematrix.core.data.EnergyData
import top.mykodb.codematrix.registry.Registration.DATA_COMPONENTS_TYPE
import java.util.function.Supplier

object ModDataComponent {
    val ENERGY = DATA_COMPONENTS_TYPE.register("energy", Supplier {
        DataComponentType.builder<EnergyData>()
            .persistent(EnergyData.CODEC)
            .networkSynchronized(EnergyData.STREAM_CODEC)
            .build()
    })
}