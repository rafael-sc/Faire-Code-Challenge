package com.orafaelsc.fairetest.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.orafaelsc.fairetest.commom.AdapterItemClickListener
import com.orafaelsc.fairetest.databinding.WeatherItemBinding

class ForecastAdapter(
    private val itemClickListener: AdapterItemClickListener = {}
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = listOf<ConsolidatedWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        WeatherViewHolder(
            WeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        (holder as? WeatherViewHolder)?.onBind(item, position)
    }

    override fun getItemCount(): Int = items.size

    class WeatherViewHolder(
        private val binding: WeatherItemBinding,
        private val itemClickListener: AdapterItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ConsolidatedWeather, position: Int) {
            binding.run {
                root.setOnClickListener {
                    itemClickListener(position)
                }
                textViewActualTemp.text = item.actualTemp
                textViewHigherTemp.text = item.higherTemp
                textViewLowerTemp.text = item.lowerTemp

                imageWeather.run {
                    load(item.imageUrl) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                    contentDescription = item.stateName
                }
            }
        }
    }

    fun setItems(list: List<ConsolidatedWeather>) {
        this.items = list
    }
}
