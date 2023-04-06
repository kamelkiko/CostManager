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
            .averageMonthlyNetSalaryAfterTax

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
        dataManager.getAllCitiesData().first { it.cityName.lowercase() == city.lowercase() }
            .servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl


    fun getCitiesMealName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullMeals)
            .sortedBy(::calculateThePercentageMeals)
            .take(10)
            .map { it.cityName }.toList()


    fun getCitiesMealNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.mealsPrices.mealInexpensiveRestaurant!! + it.mealsPrices.mealFor2PeopleMidRangeRestaurant!! +
                        it.mealsPrices.mealAtMcDonaldSOrEquivalent!!)
            }.first()

    fun getCitiesDrinkNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.drinksPrices.cappuccinoRegularInRestaurants!! +
                        it.drinksPrices.milkRegularOneLiter!! +
                        it.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants!! +
                        it.drinksPrices.waterAThirdOfLiterBottleInRestaurants!! +
                        it.drinksPrices.waterOneAndHalfLiterBottleAtTheMarket!!)
            }.first()


    fun getCitiesDrinkName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullDrinks)
            .sortedBy(::calculateThePercentageDrinks)
            .take(10)
            .map { it.cityName }.toList()

    fun getCitiesFruitNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.fruitAndVegetablesPrices.apples1kg!! +
                        it.fruitAndVegetablesPrices.banana1kg!! +
                        it.fruitAndVegetablesPrices.lettuceOneHead!! +
                        it.fruitAndVegetablesPrices.onion1kg!! +
                        it.fruitAndVegetablesPrices.oranges1kg!! +
                        it.fruitAndVegetablesPrices.potato1kg!! +
                        it.fruitAndVegetablesPrices.tomato1kg!!)
            }.first()


    fun getCitiesFruitName() =
        dataManager.getAllCitiesData().asSequence().filter(::excludeNullFruit)
            .sortedBy(::calculateThePercentageFruit)
            .take(10)
            .map { it.cityName }.toList()

    fun getCitiesFoodNumber(city: String) =
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.foodPrices.chickenFillets1kg!! +
                        it.foodPrices.eggsRegular12!! +
                        it.foodPrices.localCheese1kg!! +
                        it.foodPrices.riceWhite1kg!! +
                        it.foodPrices.loafOfFreshWhiteBread500g!! +
                        it.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat!!)
            }.first()


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
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl!! +
                        it.servicesPrices.basicElectricityHeatingCoolingWaterGarbageFor85m2Apartment!! +
                        it.servicesPrices.cinemaInternationalReleaseOneSeat!! +
                        it.servicesPrices.fitnessClubMonthlyFeeForOneAdult!! +
                        it.servicesPrices.internationalPrimarySchoolYearlyForOneChild!! +
                        it.servicesPrices.oneMinOfPrepaidMobileTariffLocalNoDiscountsOrPlans!! +
                        it.servicesPrices.preschoolOrKindergartenFullDayPrivateMonthlyForOneChild!! +
                        it.servicesPrices.tennisCourtRentOneHourOnWeekend!!
                        )
            }.first()


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
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.clothesPrices.onePairOfJeansLevis50oneOrSimilar!! +
                        it.clothesPrices.onePairOfMenLeatherBusinessShoes!! +
                        it.clothesPrices.onePairOfNikeRunningShoesMidRange!! +
                        it.clothesPrices.oneSummerDressInAChainStoreZaraHAndM!!
                        )
            }.first()


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
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.transportationsPrices.gasolineOneLiter!! +
                        it.transportationsPrices.monthlyPassRegularPrice!! +
                        it.transportationsPrices.oneWayTicketLocalTransport!! +
                        it.transportationsPrices.taxi1kmNormalTariff!! +
                        it.transportationsPrices.taxiStartNormalTariff!! +
                        it.transportationsPrices.taxi1hourWaitingNormalTariff!!
                        )
            }.first()

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
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.carsPrices.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar!! +
                        it.carsPrices.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar!!
                        )
            }.first()

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
        dataManager.getAllCitiesData()
            .filter { it.cityName.lowercase() == city.lowercase() }
            .map {
                (it.realEstatesPrices.apartment3BedroomsInCityCentre!! +
                        it.realEstatesPrices.apartment3BedroomsOutsideOfCentre!! +
                        it.realEstatesPrices.apartmentOneBedroomInCityCentre!! +
                        it.realEstatesPrices.pricePerSquareMeterToBuyApartmentInCityCentre!! +
                        it.realEstatesPrices.apartmentOneBedroomOutsideOfCentre!! +
                        it.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!!
                        )
            }.first()

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