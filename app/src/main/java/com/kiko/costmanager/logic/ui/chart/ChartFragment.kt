package com.kiko.costmanager.logic.ui.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentChartBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment


class ChartFragment : BaseFragment<FragmentChartBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentChartBinding
        get() = FragmentChartBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        val list: ArrayList<BarEntry> = ArrayList()
        list.add(BarEntry(100f, 100f, "kamel"))
        val list2 = listOf<String>(
            "kamel"
        )
        val barDataSet = BarDataSet(list, "list")
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        barDataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.dark_black)
        binding.barChart.xAxis.textColor =
            ContextCompat.getColor(requireContext(), R.color.dark_black)
        binding.barChart.axisLeft.textColor =
            ContextCompat.getColor(requireContext(), R.color.dark_black)
        binding.barChart.axisRight.textColor =
            ContextCompat.getColor(requireContext(), R.color.dark_black)
        binding.barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.barChart.xAxis.valueFormatter = IndexAxisValueFormatter(list2)
        binding.barChart.xAxis.granularity = 1f
        binding.barChart.xAxis.isGranularityEnabled = true
        val barData = BarData(barDataSet)
        binding.barChart.setFitBars(true)
        binding.barChart.data = barData
    }
}