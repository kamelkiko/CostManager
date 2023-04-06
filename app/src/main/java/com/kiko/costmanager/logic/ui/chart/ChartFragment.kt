package com.kiko.costmanager.logic.ui.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
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
        addCallBack()
    }

    private fun addCallBack() {
        binding.toolbarChart.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setup() {
        val list: ArrayList<PieEntry> = ArrayList()
        list.add(PieEntry(100f, "kamel"))
        list.add(PieEntry(101f, "ahmed"))
        list.add(PieEntry(102f, "ali"))
        list.add(PieEntry(103f, "eman"))
        val pieDataSet = PieDataSet(list, "")
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        pieDataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.dark_white)
        pieDataSet.valueTextSize = 16f
        val pieData = PieData(pieDataSet)
        binding.barChart.data = pieData
        binding.barChart.description.text = ""
        binding.barChart.animateY(1000)
    }
}