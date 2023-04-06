package com.kiko.costmanager.logic.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentDetailsBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.Prices
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.util.Constants
import com.kiko.costmanager.logic.util.setImageUrl


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate
    private lateinit var adapter: DetailsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    private fun setup() {
        val id = arguments?.getInt(Constants.KEY)
        val city = DataManager.getCityById(id!!)
        val list = listOfNotNull(
            DataManager.getAllInteracts().getCitiesMealNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.MEALS, "Average of meals", it) },
            DataManager.getAllInteracts().getCitiesDrinkNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.DRINKS, "Average of drinks", it) },
            DataManager.getAllInteracts().getCitiesFruitNumber(city.cityName)
            .let { Prices(Constants.ImageUrl.FRUITS, "Average of fruits", it) },
            DataManager.getAllInteracts().getCitiesFoodNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.FOODS, "Average of foods", it) },
            DataManager.getAllInteracts().getCitiesServicesNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.SERVICES, "Average of services", it) },
            DataManager.getAllInteracts().getCitiesClothesNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.CLOTHES, "Average of clothes", it) },
            DataManager.getAllInteracts().getCitiesTransNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.TRANSPORTATIONS, "Average of trans", it) },
            DataManager.getAllInteracts().getCitiesCarNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.CARS, "Average of cars", it) },
            DataManager.getAllInteracts().getCitiesRealStateNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.REAL_STATES, "Average of states", it) },
            DataManager.getAllInteracts().getAverageSalaryNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.SALARIES, "Average of salaries", it) },
            DataManager.getAllInteracts().getCitiesInternetNumber(city.cityName)
                .let { Prices(Constants.ImageUrl.INTERNET, "Average of internet", it) },
        )

        adapter = DetailsAdapter(list)
        binding.recycleDetails.adapter = adapter
        binding.imageDetails.setImageUrl(city.image)
        binding.toolbarDetails.title = city.cityName + ",${city.country}"
    }

    private fun addCallBack() {
        binding.toolbarDetails.setNavigationOnClickListener {
            back()
        }
    }

    private fun back() {
        requireActivity().onBackPressed()
    }

    companion object {
        fun newInstance(id: Int): DetailsFragment =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.KEY, id)
                }
            }

    }
}