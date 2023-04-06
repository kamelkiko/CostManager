package com.kiko.costmanager.logic.interactors

import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.CityEntity

class Interacts(private val dataManager: DataManager) {
    fun getAverageSalaryCityEntry() =
        dataManager.getAllCitiesData()
            .filter(::excludeNullSalariesAndLowQualityData)
            .sortedByDescending { it.averageMonthlyNetSalaryAfterTax }
            .take(10)

    fun getAverageSalaryCityName() =
        dataManager.getAllCitiesData()
            .asSequence()
            .filter(::excludeNullSalariesAndLowQualityData)
            .sortedByDescending { it.averageMonthlyNetSalaryAfterTax }
            .take(10)
            .map { it.cityName }.toList()

    fun getAverageSalaryNumber(city: String) =
        dataManager.getAllCitiesData()
            .first { it.cityName.lowercase() == city.lowercase() }
            .averageMonthlyNetSalaryAfterTax ?: 0f

    fun getCitiesHasDataQuality() =
        dataManager.getAllCitiesData().filter { it.dataQuality }

    fun getCitiesInternetName() =
        dataManager.getAllCitiesData().asSequence()
            .filter(::excludeNullSalariesAndNullInternetPrices)
            .sortedBy(::calculateThePercentageBetweenSalaryAndInternetPrice)
            .take(10)
            .map { it.cityName }.toList()

    fun getCitiesInternetCityEntry() =
        dataManager.getAllCitiesData()
            .filter(::excludeNullSalariesAndNullInternetPrices)
            .sortedBy(::calculateThePercentageBetweenSalaryAndInternetPrice)
            .take(10)


    fun getCitiesInternetNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter(::excludeNullSalariesAndNullInternetPrices)
            .sortedBy(::calculateThePercentageBetweenSalaryAndInternetPrice)
            .first { it.cityName.lowercase() == city.lowercase() }
            .servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl ?: 0f


    fun getCitiesMealName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullMeals)
            .sortedBy(::calculateThePercentageMeals)
            .take(10)
            .map { it.cityName }.toList()


    fun getCitiesMealNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.mealsPrices.mealFor2PeopleMidRangeRestaurant?.let { it1 ->
                    it.mealsPrices.mealAtMcDonaldSOrEquivalent?.let { it2 ->
                        it.mealsPrices.mealInexpensiveRestaurant?.plus(
                            it1
                        )?.plus(it2)
                    }
                })?.div(3f)
            }.first() ?: 0f

    fun getCitiesDrinkNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter(::excludeNullDrinks)
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.drinksPrices.waterOneAndHalfLiterBottleAtTheMarket?.let { it1 ->
                    it.drinksPrices.waterAThirdOfLiterBottleInRestaurants?.plus(
                        it1
                    )?.let { it2 ->
                        it.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants?.plus(
                            it2
                        )?.let { it3 ->
                            it.drinksPrices.milkRegularOneLiter?.plus(
                                it3
                            )?.let { it1 ->
                                it.drinksPrices.cappuccinoRegularInRestaurants?.plus(
                                    it1
                                )
                            }
                        }
                    }
                }


                        )?.div(5f)
            }.first() ?: 0f


    fun getCitiesDrinkName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullDrinks)
            .sortedBy(::calculateThePercentageDrinks)
            .take(10)
            .map { it.cityName }.toList()

    fun getCitiesFruitNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.fruitAndVegetablesPrices.banana1kg?.let { it1 ->
                    it.fruitAndVegetablesPrices.lettuceOneHead?.let { it2 ->
                        it.fruitAndVegetablesPrices.onion1kg?.let { it3 ->
                            it.fruitAndVegetablesPrices.oranges1kg?.let { it4 ->
                                it.fruitAndVegetablesPrices.potato1kg?.let { it5 ->
                                    it.fruitAndVegetablesPrices.tomato1kg?.let { it6 ->
                                        it.fruitAndVegetablesPrices.apples1kg?.plus(
                                            it1
                                        )?.plus(it2)?.plus(it3)?.plus(it4)?.plus(it5)?.plus(it6)
                                    }
                                }
                            }
                        }
                    }
                })?.div(7f)
            }.first() ?: 0f


    fun getCitiesFruitName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullFruit)
            .sortedBy(::calculateThePercentageFruit)
            .take(10)
            .map { it.cityName }.toList()

    fun getCitiesFoodNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter(::excludeNullFood)
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.foodPrices.eggsRegular12?.let { it1 ->
                    it.foodPrices.localCheese1kg?.let { it2 ->
                        it.foodPrices.riceWhite1kg?.let { it3 ->
                            it.foodPrices.loafOfFreshWhiteBread500g?.let { it4 ->
                                it.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat?.let { it5 ->
                                    it.foodPrices.chickenFillets1kg?.plus(it1)?.plus(
                                        it2
                                    )?.plus(it3)?.plus(it4)?.plus(
                                        it5
                                    )
                                }
                            }
                        }
                    }
                })?.div(6f)
            }.first() ?: 0f


    fun getCitiesFoodName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullFood)
            .sortedBy(::calculateThePercentageFood)
            .take(10)
            .map { it.cityName }.toList()

    private fun excludeNullFood(city: CityEntity) =
        city.foodPrices.chickenFillets1kg != null
                && city.foodPrices.eggsRegular12 != null
                && city.foodPrices.localCheese1kg != null
                && city.foodPrices.riceWhite1kg != null
                && city.foodPrices.loafOfFreshWhiteBread500g != null
                && city.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat != null

    private fun calculateThePercentageFood(city: CityEntity) =
        ((city.foodPrices.chickenFillets1kg!! +
                city.foodPrices.eggsRegular12!! +
                city.foodPrices.localCheese1kg!! +
                city.foodPrices.riceWhite1kg!! +
                city.foodPrices.loafOfFreshWhiteBread500g!! +
                city.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat!!)) / 6f


    fun getCitiesServicesNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter(::excludeNullServices)
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.servicesPrices.basicElectricityHeatingCoolingWaterGarbageFor85m2Apartment?.let { it1 ->
                    it.servicesPrices.cinemaInternationalReleaseOneSeat?.let { it2 ->
                        it.servicesPrices.fitnessClubMonthlyFeeForOneAdult?.let { it3 ->
                            it.servicesPrices.internationalPrimarySchoolYearlyForOneChild?.let { it4 ->
                                it.servicesPrices.oneMinOfPrepaidMobileTariffLocalNoDiscountsOrPlans?.let { it5 ->
                                    it.servicesPrices.preschoolOrKindergartenFullDayPrivateMonthlyForOneChild?.let { it6 ->
                                        it.servicesPrices.tennisCourtRentOneHourOnWeekend?.let { it7 ->
                                            it.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl?.plus(
                                                it1
                                            )?.plus(
                                                it2
                                            )?.plus(
                                                it3
                                            )?.plus(
                                                it4
                                            )?.plus(
                                                it5
                                            )?.plus(
                                                it6
                                            )?.plus(
                                                it7
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                        )?.div(8)
            }.first() ?: 0f


    fun getCitiesServicesName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullServices)
            .sortedBy(::calculateThePercentageServices)
            .take(10)
            .map { it.cityName }.toList()

    private fun excludeNullServices(city: CityEntity) =
        city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl != null
                && city.servicesPrices.basicElectricityHeatingCoolingWaterGarbageFor85m2Apartment != null
                && city.servicesPrices.cinemaInternationalReleaseOneSeat != null
                && city.servicesPrices.fitnessClubMonthlyFeeForOneAdult != null
                && city.servicesPrices.internationalPrimarySchoolYearlyForOneChild != null
                && city.servicesPrices.oneMinOfPrepaidMobileTariffLocalNoDiscountsOrPlans != null
                && city.servicesPrices.preschoolOrKindergartenFullDayPrivateMonthlyForOneChild != null
                && city.servicesPrices.tennisCourtRentOneHourOnWeekend != null

    private fun calculateThePercentageServices(city: CityEntity) =
        ((city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl!! +
                city.servicesPrices.basicElectricityHeatingCoolingWaterGarbageFor85m2Apartment!! +
                city.servicesPrices.cinemaInternationalReleaseOneSeat!! +
                city.servicesPrices.fitnessClubMonthlyFeeForOneAdult!! +
                city.servicesPrices.internationalPrimarySchoolYearlyForOneChild!! +
                city.servicesPrices.oneMinOfPrepaidMobileTariffLocalNoDiscountsOrPlans!! +
                city.servicesPrices.preschoolOrKindergartenFullDayPrivateMonthlyForOneChild!! +
                city.servicesPrices.tennisCourtRentOneHourOnWeekend!!)) / 8f


    fun getCitiesClothesNumber(city: String) =
        dataManager.getAllCitiesData().filter(::excludeNullClothes)
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.clothesPrices.onePairOfMenLeatherBusinessShoes?.let { it1 ->
                    it.clothesPrices.onePairOfNikeRunningShoesMidRange?.let { it2 ->
                        it.clothesPrices.oneSummerDressInAChainStoreZaraHAndM?.let { it3 ->
                            it.clothesPrices.onePairOfJeansLevis50oneOrSimilar?.plus(
                                it1
                            )?.plus(
                                it2
                            )?.plus(
                                it3
                            )
                        }
                    }
                }
                        )?.div(4)
            }.first() ?: 0f


    fun getCitiesClothesName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullClothes)
            .sortedBy(::calculateThePercentageClothes)
            .take(10)
            .map { it.cityName }.toList()

    private fun excludeNullClothes(city: CityEntity) =
        city.clothesPrices.onePairOfJeansLevis50oneOrSimilar != null
                && city.clothesPrices.onePairOfMenLeatherBusinessShoes != null
                && city.clothesPrices.onePairOfNikeRunningShoesMidRange != null
                && city.clothesPrices.oneSummerDressInAChainStoreZaraHAndM != null

    private fun calculateThePercentageClothes(city: CityEntity) =
        ((city.clothesPrices.onePairOfJeansLevis50oneOrSimilar!! +
                city.clothesPrices.onePairOfMenLeatherBusinessShoes!! +
                city.clothesPrices.onePairOfNikeRunningShoesMidRange!! +
                city.clothesPrices.oneSummerDressInAChainStoreZaraHAndM!!
                )) / 4f

    private fun excludeNullFruit(city: CityEntity) =
        city.fruitAndVegetablesPrices.apples1kg != null
                && city.fruitAndVegetablesPrices.banana1kg != null
                && city.fruitAndVegetablesPrices.lettuceOneHead != null
                && city.fruitAndVegetablesPrices.onion1kg != null
                && city.fruitAndVegetablesPrices.oranges1kg != null
                && city.fruitAndVegetablesPrices.potato1kg != null
                && city.fruitAndVegetablesPrices.tomato1kg != null

    fun getCitiesTransNumber(city: String) =
        dataManager.getAllCitiesData().filter(::excludeNullTrans)
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.transportationsPrices.monthlyPassRegularPrice?.let { it1 ->
                    it.transportationsPrices.oneWayTicketLocalTransport?.let { it2 ->
                        it.transportationsPrices.taxi1kmNormalTariff?.let { it3 ->
                            it.transportationsPrices.taxiStartNormalTariff?.let { it4 ->
                                it.transportationsPrices.taxi1hourWaitingNormalTariff?.let { it5 ->
                                    it.transportationsPrices.gasolineOneLiter?.plus(
                                        it1
                                    )?.plus(
                                        it2
                                    )?.plus(
                                        it3
                                    )?.plus(
                                        it4
                                    )?.plus(
                                        it5
                                    )
                                }
                            }
                        }
                    }
                }
                        )?.div(6f)
            }.first() ?: 0f

    fun getCitiesTransName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullTrans)
            .sortedBy(::calculateThePercentageTrans)
            .take(10)
            .map { it.cityName }.toList()

    private fun calculateThePercentageTrans(city: CityEntity) =
        ((city.transportationsPrices.gasolineOneLiter!! +
                city.transportationsPrices.monthlyPassRegularPrice!! +
                city.transportationsPrices.oneWayTicketLocalTransport!! +
                city.transportationsPrices.taxi1kmNormalTariff!! +
                city.transportationsPrices.taxiStartNormalTariff!! +
                city.transportationsPrices.taxi1hourWaitingNormalTariff!!
                )) / 6f

    private fun excludeNullTrans(city: CityEntity) =
        city.transportationsPrices.gasolineOneLiter != null
                && city.transportationsPrices.monthlyPassRegularPrice != null
                && city.transportationsPrices.oneWayTicketLocalTransport != null
                && city.transportationsPrices.taxi1kmNormalTariff != null
                && city.transportationsPrices.taxiStartNormalTariff != null
                && city.transportationsPrices.taxi1hourWaitingNormalTariff != null

    fun getCitiesCarNumber(city: String) =
        dataManager.getAllCitiesData().filter(::excludeNullCars)
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.carsPrices.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar?.let { it1 ->
                    it.carsPrices.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar?.plus(
                        it1
                    )
                })?.div(2f)
            }.first() ?: 0f

    fun getCitiesCarName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullCars)
            .sortedBy(::calculateThePercentageCars)
            .take(10)
            .map { it.cityName }.toList()

    private fun calculateThePercentageCars(city: CityEntity) =
        ((city.carsPrices.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar!! +
                city.carsPrices.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar!!
                )) / 2f

    private fun excludeNullCars(city: CityEntity) =
        city.carsPrices.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar != null
                && city.carsPrices.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar != null

    fun getCitiesRealStateNumber(city: String) =
        dataManager.getAllCitiesData().filter(::excludeNullStates)
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre?.let { it1 ->
                    it.realEstatesPrices.apartmentOneBedroomOutsideOfCentre?.let { it2 ->
                        it.realEstatesPrices.pricePerSquareMeterToBuyApartmentInCityCentre?.let { it3 ->
                            it.realEstatesPrices.apartmentOneBedroomInCityCentre?.let { it4 ->
                                it.realEstatesPrices.apartment3BedroomsOutsideOfCentre?.let { it5 ->
                                    it.realEstatesPrices.apartment3BedroomsInCityCentre?.plus(
                                        it5
                                    )?.plus(
                                        it4
                                    )?.plus(
                                        it3
                                    )?.plus(
                                        it2
                                    )?.plus(
                                        it1
                                    )
                                }
                            }
                        }
                    }
                }
                        )?.div(6f)
            }.first() ?: 0f

    fun getCitiesRealStateName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullStates)
            .sortedBy(::calculateThePercentageStates)
            .take(10)
            .map { it.cityName }.toList()

    private fun calculateThePercentageStates(city: CityEntity) =
        ((city.realEstatesPrices.apartment3BedroomsInCityCentre!! +
                city.realEstatesPrices.apartment3BedroomsOutsideOfCentre!! +
                city.realEstatesPrices.apartmentOneBedroomInCityCentre!! +
                city.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!! +
                city.realEstatesPrices.apartmentOneBedroomOutsideOfCentre!! +
                city.realEstatesPrices.pricePerSquareMeterToBuyApartmentInCityCentre!!
                )) / 6f

    private fun excludeNullStates(city: CityEntity) =
        city.realEstatesPrices.apartment3BedroomsInCityCentre != null
                && city.realEstatesPrices.apartment3BedroomsOutsideOfCentre != null
                && city.realEstatesPrices.apartmentOneBedroomInCityCentre != null
                && city.realEstatesPrices.pricePerSquareMeterToBuyApartmentInCityCentre != null
                && city.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre != null
                && city.realEstatesPrices.apartmentOneBedroomOutsideOfCentre != null

    private fun calculateThePercentageFruit(city: CityEntity) =
        ((city.fruitAndVegetablesPrices.apples1kg!! +
                city.fruitAndVegetablesPrices.banana1kg!! +
                city.fruitAndVegetablesPrices.lettuceOneHead!! +
                city.fruitAndVegetablesPrices.onion1kg!! +
                city.fruitAndVegetablesPrices.oranges1kg!! +
                city.fruitAndVegetablesPrices.potato1kg!! +
                city.fruitAndVegetablesPrices.tomato1kg!!)) / 7f

    private fun excludeNullSalariesAndLowQualityData(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null && city.dataQuality
    }

    private fun calculateThePercentageBetweenSalaryAndInternetPrice(city: CityEntity) =
        city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl!! /
                city.averageMonthlyNetSalaryAfterTax!!

    private fun excludeNullSalariesAndNullInternetPrices(city: CityEntity) =
        city.averageMonthlyNetSalaryAfterTax != null
                && city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl != null

    private fun excludeNullMeals(city: CityEntity) =
        city.mealsPrices.mealAtMcDonaldSOrEquivalent != null
                && city.mealsPrices.mealFor2PeopleMidRangeRestaurant != null
                && city.mealsPrices.mealInexpensiveRestaurant != null

    private fun calculateThePercentageMeals(city: CityEntity) =
        (city.mealsPrices.mealAtMcDonaldSOrEquivalent!! +
                city.mealsPrices.mealInexpensiveRestaurant!! +
                city.mealsPrices.mealFor2PeopleMidRangeRestaurant!!) / 3f

    private fun excludeNullDrinks(city: CityEntity) =
        city.drinksPrices.cappuccinoRegularInRestaurants != null
                && city.drinksPrices.milkRegularOneLiter != null
                && city.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants != null
                && city.drinksPrices.waterAThirdOfLiterBottleInRestaurants != null
                && city.drinksPrices.waterOneAndHalfLiterBottleAtTheMarket != null

    private fun calculateThePercentageDrinks(city: CityEntity) =
        (city.drinksPrices.cappuccinoRegularInRestaurants!! +
                city.drinksPrices.milkRegularOneLiter!! +
                city.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants!! +
                city.drinksPrices.waterAThirdOfLiterBottleInRestaurants!! +
                city.drinksPrices.waterOneAndHalfLiterBottleAtTheMarket!!) / 5f
}