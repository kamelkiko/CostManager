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
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.util.Constants


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
        val name = arguments?.getString(Constants.KEY)
        val list: ArrayList<PieEntry> = ArrayList()
        when (name) {
            Constants.MEALS -> {
                DataManager.getAllInteracts().getCitiesMealName().forEach {
                    list.add(PieEntry(DataManager.getAllInteracts().getCitiesMealNumber(it), it))
                }
                setTitle(Constants.MEALS)
            }
            Constants.DRINKS -> {
                DataManager.getAllInteracts().getCitiesDrinkName().forEach {
                    list.add(PieEntry(DataManager.getAllInteracts().getCitiesDrinkNumber(it), it))
                }
                setTitle(Constants.DRINKS)
            }

            Constants.FRUITS -> {
                DataManager.getAllInteracts().getCitiesFruitName().forEach {
                    list.add(PieEntry(DataManager.getAllInteracts().getCitiesFruitNumber(it), it))
                }
                setTitle(Constants.FRUITS)
            }
            Constants.FOODS -> {
                DataManager.getAllInteracts().getCitiesFoodName().forEach {
                    list.add(PieEntry(DataManager.getAllInteracts().getCitiesFoodNumber(it), it))
                }
                setTitle(Constants.FOODS)
            }

            Constants.SERVICES -> {
                DataManager.getAllInteracts().getCitiesServicesName().forEach {
                    list.add(
                        PieEntry(
                            DataManager.getAllInteracts().getCitiesServicesNumber(it),
                            it
                        )
                    )
                }
                setTitle(Constants.SERVICES)
            }
            Constants.CLOTHES -> {
                DataManager.getAllInteracts().getCitiesClothesName().forEach {
                    list.add(PieEntry(DataManager.getAllInteracts().getCitiesClothesNumber(it), it))
                }
                setTitle(Constants.CLOTHES)
            }

            Constants.TRANS -> {
                DataManager.getAllInteracts().getCitiesTransName().forEach {
                    list.add(PieEntry(DataManager.getAllInteracts().getCitiesTransNumber(it), it))
                }
                setTitle(Constants.TRANS)
            }
            Constants.CARS -> {
                DataManager.getAllInteracts().getCitiesCarName().forEach {
                    list.add(PieEntry(DataManager.getAllInteracts().getCitiesCarNumber(it), it))
                }
                setTitle(Constants.CARS)
            }

            Constants.SALARIES -> {
                DataManager.getAllInteracts().getAverageSalaryCityName().forEach {
                    list.add(
                        PieEntry(
                            DataManager.getAllInteracts().getAverageSalaryNumber(it),
                            it
                        )
                    )
                }
                setTitle(Constants.SALARIES)
            }

            Constants.STATES -> {
                DataManager.getAllInteracts().getCitiesRealStateName().forEach {
                    list.add(
                        PieEntry(
                            DataManager.getAllInteracts().getCitiesRealStateNumber(it),
                            it
                        )
                    )
                }
                setTitle(Constants.STATES)
            }
            Constants.INTERNET -> {
                DataManager.getAllInteracts().getCitiesInternetName().forEach {
                    list.add(
                        PieEntry(
                            DataManager.getAllInteracts().getCitiesInternetNumber(it),
                            it
                        )
                    )
                }
                setTitle(Constants.INTERNET)
            }
        }

        val pieDataSet = PieDataSet(list, "Meals")
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        pieDataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.dark_white)
        pieDataSet.valueTextSize = 16f
        val pieData = PieData(pieDataSet)
        binding.barChart.data = pieData
        binding.barChart.description.text = "Meals"
        binding.barChart.animateY(1000)
    }

    private fun setTitle(title: String) {
        binding.toolbarChart.title = "Average Of $title"
    }

    companion object {
        fun newInstance(key: String): ChartFragment =
            ChartFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.KEY, key)
                }
            }
    }
}