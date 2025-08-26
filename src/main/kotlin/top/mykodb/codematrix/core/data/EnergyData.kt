package top.mykodb.codematrix.core.data

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec


/**
 *  能量组件
 */
data class EnergyData(
    val energy: Int,
    val capacity: Int,
    val maxReceive: Int,
    val maxExtract: Int
){

    companion object {
        val CODEC: Codec<EnergyData> = RecordCodecBuilder.create { builder ->
            builder.group(
                Codec.INT.fieldOf("energy").forGetter(EnergyData::energy),
                Codec.INT.fieldOf("capacity").forGetter(EnergyData::capacity),
                Codec.INT.fieldOf("maxReceive").forGetter(EnergyData::maxReceive),
                Codec.INT.fieldOf("maxExtract").forGetter(EnergyData::maxExtract)
            ).apply(builder, ::EnergyData)
        }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, EnergyData> =
            StreamCodec.of(
                { buf, data ->
                    buf.writeInt(data.energy)
                    buf.writeInt(data.capacity)
                    buf.writeInt(data.maxReceive)
                    buf.writeInt(data.maxExtract)
                },
                { buf ->
                    EnergyData(
                        energy = buf.readInt(),
                        capacity = buf.readInt(),
                        maxReceive = buf.readInt(),
                        maxExtract = buf.readInt()
                    )
                }
            )

        val EMPTY = EnergyData(0, 0, 0, 0)
    }
}
