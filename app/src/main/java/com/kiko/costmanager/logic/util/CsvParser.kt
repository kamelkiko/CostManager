package com.kiko.costmanager.logic.util

import com.kiko.costmanager.logic.data.models.*

class CsvParser {

    fun parseLine(id: Int, line: String): CityEntity {
        val tokens = line.split(",")
        return CityEntity(
            id = id,
            cityName = tokens[Constants.ColumnIndex.CITY],
            image = getRandomImage(),
            country = tokens[Constants.ColumnIndex.COUNTRY],
            mealsPrices = constructMealsPricesFromTokens(tokens),
            drinksPrices = constructDrinksPricesFromTokens(tokens),
            fruitAndVegetablesPrices = constructFruitAndVegetablesPricesFromTokens(tokens),
            foodPrices = constructFoodPricesFromTokens(tokens),
            servicesPrices = constructServicesPricesFromTokens(tokens),
            clothesPrices = constructClothesPricesFromTokens(tokens),
            transportationsPrices = constructTransportationsPricesFromTokens(tokens),
            carsPrices = constructCarsPricesFromTokens(tokens),
            realEstatesPrices = constructRealStatesPricesFromTokens(tokens),
            averageMonthlyNetSalaryAfterTax = tokens[Constants.ColumnIndex.AVERAGE_MONTHLY_NET_SALARY_AFTER_TAX].toFloatOrNull(),
            dataQuality = tokens[Constants.ColumnIndex.DATA_QUALITY].toIntOrNull() == 1,
            isFavourite = false,
        )
    }

    private fun constructMealsPricesFromTokens(tokenizedList: List<String>): MealsPrices {
        return MealsPrices(
            mealAtMcDonaldSOrEquivalent = tokenizedList.getFloat(Constants.ColumnIndex.MEAL_AT_MC_DONALD_S_OR_EQUIVALENT),
            mealInexpensiveRestaurant = tokenizedList.getFloat(Constants.ColumnIndex.MEAL_INEXPENSIVE_RESTAURANT),
            mealFor2PeopleMidRangeRestaurant = tokenizedList.getFloat(Constants.ColumnIndex.MEAL_FOR_2_PEOPLE_MID_RANGE_RESTAURANT),
        )
    }

    private fun constructDrinksPricesFromTokens(tokenizedList: List<String>): DrinksPrices {
        return DrinksPrices(
            cappuccinoRegularInRestaurants = tokenizedList.getFloat(Constants.ColumnIndex.CAPPUCCINO_REGULAR_IN_RESTAURANTS),
            cokePepsiAThirdOfLiterBottleInRestaurants = tokenizedList.getFloat(Constants.ColumnIndex.COKE_PEPSI_A_THIRD_OF_LITER_BOTTLE_IN_RESTAURANTS),
            milkRegularOneLiter = tokenizedList.getFloat(Constants.ColumnIndex.MILK_REGULAR_ONE_LITER),
            waterAThirdOfLiterBottleInRestaurants = tokenizedList.getFloat(Constants.ColumnIndex.WATER_A_THIRD_OF_LITER_BOTTLE_IN_RESTAURANTS),
            waterOneAndHalfLiterBottleAtTheMarket = tokenizedList.getFloat(Constants.ColumnIndex.WATER_ONE_AND_HALF_LITER_BOTTLE_AT_THE_MARKET),
        )
    }

    private fun constructFruitAndVegetablesPricesFromTokens(tokenizedList: List<String>): FruitAndVegetablesPrices {
        return FruitAndVegetablesPrices(
            apples1kg = tokenizedList.getFloat(Constants.ColumnIndex.APPLES_1KG),
            banana1kg = tokenizedList.getFloat(Constants.ColumnIndex.BANANA_1KG),
            oranges1kg = tokenizedList.getFloat(Constants.ColumnIndex.ORANGES_1KG),
            tomato1kg = tokenizedList.getFloat(Constants.ColumnIndex.TOMATO_1KG),
            onion1kg = tokenizedList.getFloat(Constants.ColumnIndex.ONION_1KG),
            potato1kg = tokenizedList.getFloat(Constants.ColumnIndex.POTATO_1KG),
            lettuceOneHead = tokenizedList.getFloat(Constants.ColumnIndex.LETTUCE_ONE_HEAD)
        )
    }

    private fun constructFoodPricesFromTokens(tokenizedList: List<String>): FoodPrices {
        return FoodPrices(
            loafOfFreshWhiteBread500g = tokenizedList.getFloat(Constants.ColumnIndex.LOAF_OF_FRESH_WHITE_BREAD_500G),
            riceWhite1kg = tokenizedList.getFloat(Constants.ColumnIndex.RICE_WHITE_1KG),
            eggsRegular12 = tokenizedList.getFloat(Constants.ColumnIndex.EGGS_REGULAR_12),
            localCheese1kg = tokenizedList.getFloat(Constants.ColumnIndex.LOCAL_CHEESE_1KG),
            chickenFillets1kg = tokenizedList.getFloat(Constants.ColumnIndex.CHICKEN_FILLETS_1KG),
            beefRound1kgOrEquivalentBackLegRedMeat = tokenizedList.getFloat(Constants.ColumnIndex.BEEF_ROUND_1KG_OR_EQUIVALENT_BACK_LEG_RED_MEAT),
        )
    }

    private fun constructServicesPricesFromTokens(tokenizedList: List<String>): ServicesPrices {
        return ServicesPrices(
            basicElectricityHeatingCoolingWaterGarbageFor85m2Apartment = tokenizedList.getFloat(
                Constants.ColumnIndex.BASIC_ELECTRICITY_HEATING_COOLING_WATER_GARBAGE_FOR_85M2_APARTMENT
            ),
            cinemaInternationalReleaseOneSeat = tokenizedList.getFloat(Constants.ColumnIndex.CINEMA_INTERNATIONAL_RELEASE_ONE_SEAT),
            internet60MbpsOrMoreUnlimitedDataCableAdsl = tokenizedList.getFloat(Constants.ColumnIndex.INTERNET_60_MBPS_OR_MORE_UNLIMITED_DATA_CABLE_ADSL),
            fitnessClubMonthlyFeeForOneAdult = tokenizedList.getFloat(Constants.ColumnIndex.FITNESS_CLUB_MONTHLY_FEE_FOR_ONE_ADULT),
            tennisCourtRentOneHourOnWeekend = tokenizedList.getFloat(Constants.ColumnIndex.TENNIS_COURT_RENT_ONE_HOUR_ON_WEEKEND),
            oneMinOfPrepaidMobileTariffLocalNoDiscountsOrPlans = tokenizedList.getFloat(Constants.ColumnIndex.ONE_MIN_OF_PREPAID_MOBILE_TARIFF_LOCAL_NO_DISCOUNTS_OR_PLANS),
            internationalPrimarySchoolYearlyForOneChild = tokenizedList.getFloat(Constants.ColumnIndex.INTERNATIONAL_PRIMARY_SCHOOL_YEARLY_FOR_ONE_CHILD),
            preschoolOrKindergartenFullDayPrivateMonthlyForOneChild = tokenizedList.getFloat(
                Constants.ColumnIndex.PRESCHOOL_OR_KINDERGARTEN_FULL_DAY_PRIVATE_MONTHLY_FOR_ONE_CHILD
            ),
        )
    }

    private fun constructClothesPricesFromTokens(tokenizedList: List<String>): ClothesPrices {
        return ClothesPrices(
            onePairOfJeansLevis50oneOrSimilar = tokenizedList.getFloat(Constants.ColumnIndex.ONE_PAIR_OF_JEANS_LEVIS_50ONE_OR_SIMILAR),
            oneSummerDressInAChainStoreZaraHAndM = tokenizedList.getFloat(Constants.ColumnIndex.ONE_SUMMER_DRESS_IN_A_CHAIN_STORE_ZARA_H_AND_M),
            onePairOfNikeRunningShoesMidRange = tokenizedList.getFloat(Constants.ColumnIndex.ONE_PAIR_OF_NIKE_RUNNING_SHOES_MID_RANGE),
            onePairOfMenLeatherBusinessShoes = tokenizedList.getFloat(Constants.ColumnIndex.ONE_PAIR_OF_MEN_LEATHER_BUSINESS_SHOES),
        )
    }

    private fun constructTransportationsPricesFromTokens(tokenizedList: List<String>): TransportationsPrices {
        return TransportationsPrices(
            gasolineOneLiter = tokenizedList.getFloat(Constants.ColumnIndex.GASOLINE_ONE_LITER),
            taxi1hourWaitingNormalTariff = tokenizedList.getFloat(Constants.ColumnIndex.TAXI_1HOUR_WAITING_NORMAL_TARIFF),
            taxi1kmNormalTariff = tokenizedList.getFloat(Constants.ColumnIndex.TAXI_1KM_NORMAL_TARIFF),
            monthlyPassRegularPrice = tokenizedList.getFloat(Constants.ColumnIndex.MONTHLY_PASS_REGULAR_PRICE),
            oneWayTicketLocalTransport = tokenizedList.getFloat(Constants.ColumnIndex.ONE_WAY_TICKET_LOCAL_TRANSPORT),
            taxiStartNormalTariff = tokenizedList.getFloat(Constants.ColumnIndex.TAXI_START_NORMAL_TARIFF)
        )
    }

    private fun constructCarsPricesFromTokens(tokenizedList: List<String>): CarsPrices {
        return CarsPrices(
            volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar = tokenizedList.getFloat(Constants.ColumnIndex.VOLKSWAGEN_GOLF_1_4_90KW_TREND_LINE_OR_EQUIVALENT_NEW_CAR),
            toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar = tokenizedList.getFloat(Constants.ColumnIndex.TOYOTA_COROLLA_SEDAN_1_6L_97KW_COMFORT_OR_EQUIVALENT_NEW_CAR),
        )
    }

    private fun constructRealStatesPricesFromTokens(tokenizedList: List<String>): RealEstatesPrices {
        return RealEstatesPrices(
            apartmentOneBedroomInCityCentre = tokenizedList.getFloat(Constants.ColumnIndex.APARTMENT_ONE_BEDROOM_IN_CITY_CENTRE),
            apartmentOneBedroomOutsideOfCentre = tokenizedList.getFloat(Constants.ColumnIndex.APARTMENT_ONE_BEDROOM_OUTSIDE_OF_CENTRE),
            apartment3BedroomsInCityCentre = tokenizedList.getFloat(Constants.ColumnIndex.APARTMENT_3_BEDROOMS_IN_CITY_CENTRE),
            apartment3BedroomsOutsideOfCentre = tokenizedList.getFloat(Constants.ColumnIndex.APARTMENT_3_BEDROOMS_OUTSIDE_OF_CENTRE),
            pricePerSquareMeterToBuyApartmentInCityCentre = tokenizedList.getFloat(Constants.ColumnIndex.PRICE_PER_SQUARE_METER_TO_BUY_APARTMENT_IN_CITY_CENTRE),
            pricePerSquareMeterToBuyApartmentOutsideOfCentre = tokenizedList.getFloat(Constants.ColumnIndex.PRICE_PER_SQUARE_METER_TO_BUY_APARTMENT_OUTSIDE_OF_CENTRE),
        )
    }


    private fun getRandomImage(): String {
        return listOf(
            "https://images.unsplash.com/photo-1608925086961-dbcd276a0e71?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
            "https://images.unsplash.com/photo-1523906834658-6e24ef2386f9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=383&q=80",
            "https://images.unsplash.com/photo-1515542622106-78bda8ba0e5b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
            "https://images.unsplash.com/photo-1492571350019-22de08371fd3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=753&q=80",
            "https://images.unsplash.com/photo-1558642084-fd07fae5282e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=436&q=80",
            "https://images.unsplash.com/photo-1602941889598-3e7780b9a602?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=436&q=80",
            "https://images.unsplash.com/photo-1553913861-c0fddf2619ee?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
            "https://images.unsplash.com/photo-1572704956971-74e41e37d1b8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80",
            "https://images.unsplash.com/photo-1485738422979-f5c462d49f74?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1199&q=80",
            "https://plus.unsplash.com/premium_photo-1677934308187-26e8103c292b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80",
            "https://images.unsplash.com/photo-1530276371031-2511efff9d5a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8U3lkbmV5JTIwSGFyYm91ciUyMEJyaWRnZSUyQyUyMFN5ZG5leSUyQyUyMEF1c3RyYWxpYXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1523482580672-f109ba8cb9be?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1830&q=80",
            "https://images.unsplash.com/photo-1596484552834-6a58f850e0a1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
            "https://images.unsplash.com/photo-1595277914958-7f384e3d3347?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80",
            "https://images.unsplash.com/photo-1626215039499-2d0e58588665?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1174&q=80",
            "https://images.unsplash.com/photo-1630491732980-318d54825436?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1917&q=80",
            "https://images.unsplash.com/photo-1573529034457-3a81a2db5576?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80",
            "https://media.istockphoto.com/id/1455303240/photo/london-big-ben-tower-westminster-bridge-over-thames-river-england-uk.jpg?s=612x612&w=0&k=20&c=pIRtFOJoT6OcN5pAgwq38TzqJ8bNcFKd0rD7Mk5aOps=",
            "https://images.unsplash.com/photo-1616169610185-62da73d4f889?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80",
            "https://images.unsplash.com/photo-1520175480921-4edfa2983e0f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=867&q=80",
            "https://media.istockphoto.com/id/927714156/photo/spain-square-is-a-square-in-the-maria-luisa-park-in-seville.jpg?s=612x612&w=0&k=20&c=Wv-RCpTKlmubYgLibbbfxsjgJIYUDlU-_hJ65-eDALM=",

            ).shuffled().first()
    }


}