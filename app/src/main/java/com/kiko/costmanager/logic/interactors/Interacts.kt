package com.kiko.costmanager.logic.interactors

import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.CityEntity

class Interacts(private val dataManager: DataManager) {
    fun getAverageSalaryCityEntry() =
        dataManager.getAllCitiesData()
            .filter(::exclude0fSalariesAndLowQualityData)
            .sortedByDescending { it.averageMonthlyNetSalaryAfterTax }
            .take(10)

    fun getAverageSalaryCityName() =
        dataManager.getAllCitiesData()
            .asSequence()
            .filter(::exclude0fSalariesAndLowQualityData)
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
            .filter(::exclude0fSalariesAnd0fInternetPrices)
            .sortedBy(::calculateThePercentageBetweenSalaryAndInternetPrice)
            .take(10)
            .map { it.cityName }.toList()

    fun getCitiesInternetCityEntry() =
        dataManager.getAllCitiesData()
            .filter(::exclude0fSalariesAnd0fInternetPrices)
            .sortedBy(::calculateThePercentageBetweenSalaryAndInternetPrice)
            .take(10)


    fun getCitiesInternetNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter(::exclude0fSalariesAnd0fInternetPrices)
            .sortedBy(::calculateThePercentageBetweenSalaryAndInternetPrice)
            .first { it.cityName.lowercase() == city.lowercase() }
            .servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl ?: 0f


    fun getCitiesMealName() =
        dataManager.getAllCitiesData().asSequence().filter(::exclude0fMeals)
            .sortedBy(::calculateThePercentageMeals)
            .take(10)
            .map { it.cityName }.toList()


    fun getCitiesMealNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                ((it.mealsPrices.mealInexpensiveRestaurant
                    ?: 0f) + it.mealsPrices.mealFor2PeopleMidRangeRestaurant!! +
                        it.mealsPrices.mealAtMcDonaldSOrEquivalent!!)
            }.first()

    fun getCitiesDrinkNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter(::exclude0fDrinks)
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
        dataManager.getAllCitiesData().asSequence().filter(::exclude0fDrinks)
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
        dataManager.getAllCitiesData().asSequence().filter(::exclude0fFruit)
            .sortedBy(::calculateThePercentageFruit)
            .take(10)
            .map { it.cityName }.toList()

    fun getCitiesFoodNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter(::exclude0fFood)
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
        dataManager.getAllCitiesData().asSequence().filter(::exclude0fFood)
            .sortedBy(::calculateThePercentageFood)
            .take(10)
            .map { it.cityName }.toList()

    private fun exclude0fFood(city: CityEntity) =
        city.foodPrices.chickenFillets1kg != 0f
                && city.foodPrices.eggsRegular12 != 0f
                && city.foodPrices.localCheese1kg != 0f
                && city.foodPrices.riceWhite1kg != 0f
                && city.foodPrices.loafOfFreshWhiteBread500g != 0f
                && city.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat != 0f

    private fun calculateThePercentageFood(city: CityEntity) =
        ((city.foodPrices.chickenFillets1kg!! +
                city.foodPrices.eggsRegular12!! +
                city.foodPrices.localCheese1kg!! +
                city.foodPrices.riceWhite1kg!! +
                city.foodPrices.loafOfFreshWhiteBread500g!! +
                city.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat!!)) / 6f


    fun getCitiesServicesNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter(::exclude0fServices)
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
        dataManager.getAllCitiesData().asSequence().filter(::exclude0fServices)
            .sortedBy(::calculateThePercentageServices)
            .take(10)
            .map { it.cityName }.toList()

    private fun exclude0fServices(city: CityEntity) =
        city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl != 0f
                && city.servicesPrices.basicElectricityHeatingCoolingWaterGarbageFor85m2Apartment != 0f
                && city.servicesPrices.cinemaInternationalReleaseOneSeat != 0f
                && city.servicesPrices.fitnessClubMonthlyFeeForOneAdult != 0f
                && city.servicesPrices.internationalPrimarySchoolYearlyForOneChild != 0f
                && city.servicesPrices.oneMinOfPrepaidMobileTariffLocalNoDiscountsOrPlans != 0f
                && city.servicesPrices.preschoolOrKindergartenFullDayPrivateMonthlyForOneChild != 0f
                && city.servicesPrices.tennisCourtRentOneHourOnWeekend != 0f

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
        dataManager.getAllCitiesData().filter(::exclude0fClothes)
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
        dataManager.getAllCitiesData().asSequence().filter(::exclude0fClothes)
            .sortedBy(::calculateThePercentageClothes)
            .take(10)
            .map { it.cityName }.toList()

    private fun exclude0fClothes(city: CityEntity) =
        city.clothesPrices.onePairOfJeansLevis50oneOrSimilar != 0f
                && city.clothesPrices.onePairOfMenLeatherBusinessShoes != 0f
                && city.clothesPrices.onePairOfNikeRunningShoesMidRange != 0f
                && city.clothesPrices.oneSummerDressInAChainStoreZaraHAndM != 0f

    private fun calculateThePercentageClothes(city: CityEntity) =
        ((city.clothesPrices.onePairOfJeansLevis50oneOrSimilar!! +
                city.clothesPrices.onePairOfMenLeatherBusinessShoes!! +
                city.clothesPrices.onePairOfNikeRunningShoesMidRange!! +
                city.clothesPrices.oneSummerDressInAChainStoreZaraHAndM!!
                )) / 4f

    private fun exclude0fFruit(city: CityEntity) =
        city.fruitAndVegetablesPrices.apples1kg != 0f
                && city.fruitAndVegetablesPrices.banana1kg != 0f
                && city.fruitAndVegetablesPrices.lettuceOneHead != 0f
                && city.fruitAndVegetablesPrices.onion1kg != 0f
                && city.fruitAndVegetablesPrices.oranges1kg != 0f
                && city.fruitAndVegetablesPrices.potato1kg != 0f
                && city.fruitAndVegetablesPrices.tomato1kg != 0f

    fun getCitiesTransNumber(city: String) =
        dataManager.getAllCitiesData().filter(::exclude0fTrans)
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
        dataManager.getAllCitiesData().asSequence().filter(::exclude0fTrans)
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

    private fun exclude0fTrans(city: CityEntity) =
        city.transportationsPrices.gasolineOneLiter != 0f
                && city.transportationsPrices.monthlyPassRegularPrice != 0f
                && city.transportationsPrices.oneWayTicketLocalTransport != 0f
                && city.transportationsPrices.taxi1kmNormalTariff != 0f
                && city.transportationsPrices.taxiStartNormalTariff != 0f
                && city.transportationsPrices.taxi1hourWaitingNormalTariff != 0f

    fun getCitiesCarNumber(city: String) =
        dataManager.getAllCitiesData().filter(::exclude0fCars)
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.carsPrices.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar?.let { it1 ->
                    it.carsPrices.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar?.plus(
                        it1
                    )
                })?.div(2f)
            }.first() ?: 0f

    fun getCitiesCarName() =
        dataManager.getAllCitiesData().asSequence().filter(::exclude0fCars)
            .sortedBy(::calculateThePercentageCars)
            .take(10)
            .map { it.cityName }.toList()

    private fun calculateThePercentageCars(city: CityEntity) =
        ((city.carsPrices.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar!! +
                city.carsPrices.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar!!
                )) / 2f

    private fun exclude0fCars(city: CityEntity) =
        city.carsPrices.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar != 0f
                && city.carsPrices.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar != 0f

    fun getCitiesRealStateNumber(city: String) =
        dataManager.getAllCitiesData().filter(::exclude0fStates)
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
        dataManager.getAllCitiesData().asSequence().filter(::exclude0fStates)
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

    private fun exclude0fStates(city: CityEntity) =
        city.realEstatesPrices.apartment3BedroomsInCityCentre != 0f
                && city.realEstatesPrices.apartment3BedroomsOutsideOfCentre != 0f
                && city.realEstatesPrices.apartmentOneBedroomInCityCentre != 0f
                && city.realEstatesPrices.pricePerSquareMeterToBuyApartmentInCityCentre != 0f
                && city.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre != 0f
                && city.realEstatesPrices.apartmentOneBedroomOutsideOfCentre != 0f

    private fun calculateThePercentageFruit(city: CityEntity) =
        ((city.fruitAndVegetablesPrices.apples1kg!! +
                city.fruitAndVegetablesPrices.banana1kg!! +
                city.fruitAndVegetablesPrices.lettuceOneHead!! +
                city.fruitAndVegetablesPrices.onion1kg!! +
                city.fruitAndVegetablesPrices.oranges1kg!! +
                city.fruitAndVegetablesPrices.potato1kg!! +
                city.fruitAndVegetablesPrices.tomato1kg!!)) / 7f

    private fun exclude0fSalariesAndLowQualityData(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != 0f && city.dataQuality
    }

    private fun calculateThePercentageBetweenSalaryAndInternetPrice(city: CityEntity) =
        city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl!! /
                city.averageMonthlyNetSalaryAfterTax!!

    private fun exclude0fSalariesAnd0fInternetPrices(city: CityEntity) =
        city.averageMonthlyNetSalaryAfterTax != 0f
                && city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl != 0f

    private fun exclude0fMeals(city: CityEntity) =
        city.mealsPrices.mealAtMcDonaldSOrEquivalent != 0f
                && city.mealsPrices.mealFor2PeopleMidRangeRestaurant != 0f
                && city.mealsPrices.mealInexpensiveRestaurant != 0f

    private fun calculateThePercentageMeals(city: CityEntity) =
        (city.mealsPrices.mealAtMcDonaldSOrEquivalent!! +
                city.mealsPrices.mealInexpensiveRestaurant!! +
                city.mealsPrices.mealFor2PeopleMidRangeRestaurant!!) / 3f

    private fun exclude0fDrinks(city: CityEntity) =
        city.drinksPrices.cappuccinoRegularInRestaurants != 0f
                && city.drinksPrices.milkRegularOneLiter != 0f
                && city.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants != 0f
                && city.drinksPrices.waterAThirdOfLiterBottleInRestaurants != 0f
                && city.drinksPrices.waterOneAndHalfLiterBottleAtTheMarket != 0f

    private fun calculateThePercentageDrinks(city: CityEntity) =
        (city.drinksPrices.cappuccinoRegularInRestaurants!! +
                city.drinksPrices.milkRegularOneLiter!! +
                city.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants!! +
                city.drinksPrices.waterAThirdOfLiterBottleInRestaurants!! +
                city.drinksPrices.waterOneAndHalfLiterBottleAtTheMarket!!) / 5f
}