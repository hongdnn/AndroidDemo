package com.example.weekfiveproject

class FruitManager {
     var fruitList = mutableListOf<Fruit>()

    init {
        fruitList.add(
            Fruit(
                1,
                "Cherry",
                "Price per kg",
                "https://upload.wikimedia.org/wikipedia/commons/b/bb/Cherry_Stella444.jpg",
                5.6F,
                7.2F,
                "Free Ship"
            )
        )
        fruitList.add(
            Fruit(
                2,
                "Orange",
                "Price per kg",
                "https://www.collinsdictionary.com/images/full/orange_342874121.jpg",
                6.2F,
                6.8F,
                null
            )
        )
        fruitList.add(
            Fruit(
                3,
                "Banana",
                "Price per kg",
                "https://cdn.mos.cms.futurecdn.net/42E9as7NaTaAi4A6JcuFwG-1200-80.jpg",
                3.2F,
                4.6F,
                null
            )
        )
        fruitList.add(
            Fruit(
                4,
                "Apple",
                "Price per kg",
                "https://i2.wp.com/ceklog.kindel.com/wp-content/uploads/2013/02/firefox_2018-07-10_07-50-11.png",
                6.6F,
                7.0F,
                null
            )
        )
        fruitList.add(
            Fruit(
                5,
                "Watermelon",
                "Price per kg",
                "https://cdn.mos.cms.futurecdn.net/QCRt2ButzyC6FJi8pb6hNi.jpg",
                8.0F,
                9.0F,
                "Free Ship"
            )
        )
        fruitList.add(
            Fruit(
                6,
                "Avocado",
                "Price per kg",
                "https://www.washingtonian.com/wp-content/uploads/2020/02/iStock-1027572462-scaled-2048x1695.jpg",
                5.0F,
                6.0F,
                "Sale 12%"
            )
        )
        fruitList.add(
            Fruit(
                7,
                "Lemon",
                "Price per kg",
                "https://i.ndtvimg.com/mt/cooks/2014-11/lemon.jpg",
                3.3F,
                4.2F,
                null
            )
        )
        fruitList.add(
            Fruit(
                8,
                "Tangerine",
                "Price per kg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ-c78c6mNDcNwHsO3uRknoWPAVehIlUmqqmg&usqp=CAU",
                2.3F,
                3.6F,
                "Sale 20%"
            )
        )
    }
}