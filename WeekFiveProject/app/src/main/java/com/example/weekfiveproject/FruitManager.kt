package com.example.weekfiveproject

class FruitManager {
    var fruitList = mutableListOf<Fruit>()

    init {
        fruitList.addAll(
            listOf(
                Fruit(
                    1,
                    "Cherry",
                    "Price per kg",
                    "https://upload.wikimedia.org/wikipedia/commons/b/bb/Cherry_Stella444.jpg",
                    5.6F,
                    7.2F,
                    "Free Ship",
                    false
                ),
                Fruit(
                    2,
                    "Orange",
                    "Price per kg",
                    "https://www.collinsdictionary.com/images/full/orange_342874121.jpg",
                    6.2F,
                    6.8F,
                    null,
                    false
                ),
                Fruit(
                    3,
                    "Banana",
                    "Price per kg",
                    "https://cdn.mos.cms.futurecdn.net/42E9as7NaTaAi4A6JcuFwG-1200-80.jpg",
                    3.2F,
                    4.6F,
                    null,
                    false
                ),
                Fruit(
                    4,
                    "Apple",
                    "Price per kg",
                    "https://i2.wp.com/ceklog.kindel.com/wp-content/uploads/2013/02/firefox_2018-07-10_07-50-11.png",
                    6.6F,
                    7.0F,
                    null,
                    false
                ),
                Fruit(
                    5,
                    "Watermelon",
                    "Price per kg",
                    "https://cdn.mos.cms.futurecdn.net/QCRt2ButzyC6FJi8pb6hNi.jpg",
                    8.0F,
                    9.0F,
                    "Free Ship",
                    false
                ),
                Fruit(
                    6,
                    "Avocado",
                    "Price per kg",
                    "https://www.washingtonian.com/wp-content/uploads/2020/02/iStock-1027572462-scaled-2048x1695.jpg",
                    5.0F,
                    6.0F,
                    "Sale 12%",
                    false
                ),
                Fruit(
                    7,
                    "Lemon",
                    "Price per kg",
                    "https://i.ndtvimg.com/mt/cooks/2014-11/lemon.jpg",
                    3.3F,
                    4.2F,
                    null,
                    false
                ),
                Fruit(
                    8,
                    "Tangerine",
                    "Price per kg",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ-c78c6mNDcNwHsO3uRknoWPAVehIlUmqqmg&usqp=CAU",
                    2.3F,
                    3.6F,
                    "Sale 20%",
                    false
                ),
                Fruit(
                    9,
                    "Kiwi",
                    "Price per kg",
                    "https://product.hstatic.net/1000282430/product/trai-kiwi-xanh_large.jpg",
                    5.6F,
                    7.2F,
                    "Free Ship",
                    false
                ),
                Fruit(
                    10,
                    "Grapes",
                    "Price per kg",
                    "https://images.homedepot-static.com/productImages/a530c427-8297-464c-90f8-6607c9d1479b/svn/van-zyverden-fruit-plants-83723-64_1000.jpg",
                    6.2F,
                    6.8F,
                    null,
                    false
                ),
                Fruit(
                    11,
                    "Mango",
                    "Price per kg",
                    "https://img1.exportersindia.com/product_images/bc-full/2020/1/5534411/green-mango-1579762441-5266354.jpeg",
                    3.2F,
                    4.6F,
                    null,
                    false
                ),
                Fruit(
                    12,
                    "Pomegranate",
                    "Price per kg",
                    "https://i.pinimg.com/originals/a6/8e/48/a68e4896249e1787502d37677d490f19.jpg",
                    6.6F,
                    7.0F,
                    null,
                    false
                ),
                Fruit(
                    13,
                    "Custard-apple",
                    "Price per kg",
                    "https://4.imimg.com/data4/EE/ND/MY-10184357/custard-apple-500x500.jpg",
                    8.0F,
                    9.0F,
                    "Free Ship",
                    false
                ),
                Fruit(
                    14,
                    "Pineapple",
                    "Price per kg",
                    "https://5.imimg.com/data5/WT/GN/ZH/SELLER-3722283/pineapple-500x500.jpg",
                    5.0F,
                    6.0F,
                    "Sale 12%",
                    false
                ),
                Fruit(
                    15,
                    "Plum",
                    "Price per kg",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQQA64oFsrLLCB3b16t5ADNWvtszw4i5VHcgg&usqp=CAU",
                    3.3F,
                    4.2F,
                    null,
                    false
                ),
                Fruit(
                    16,
                    "Sapodilla",
                    "Price per kg",
                    "https://cdn.shopify.com/s/files/1/0017/9234/4153/products/kalpatti-250x250_600x.jpg?v=1562951606",
                    2.3F,
                    3.6F,
                    "Sale 20%",
                    false
                ),
                Fruit(
                    17,
                    "Peach",
                    "Price per kg",
                    "https://images.eatthismuch.com/site_media/img/1484_hannahgothelf_9b6411ed-b690-46f6-b4db-48470bcb695c.jpg",
                    5.6F,
                    7.2F,
                    "Free Ship",
                    false
                ),
                Fruit(
                    18,
                    "Starfruit",
                    "Price per kg",
                    "https://dictionary.cambridge.org/vi/images/thumb/starfr_noun_002_35656.jpg?version=5.0.122",
                    6.2F,
                    6.8F,
                    null,
                    false
                ),
                Fruit(
                    19,
                    "Papaya",
                    "Price per kg",
                    "https://images-na.ssl-images-amazon.com/images/I/41JntFR5VJL._AC_SY400_.jpg",
                    3.2F,
                    4.6F,
                    null,
                    false
                ),
                Fruit(
                    20,
                    "Apricot",
                    "Price per kg",
                    "https://www.gardeningknowhow.com/wp-content/uploads/2015/05/apricot-pit.jpg",
                    6.6F,
                    7.0F,
                    null,
                    false
                ),
                Fruit(
                    21,
                    "Durian",
                    "Price per kg",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSagKxuCF11kxoYWyo5FmmYVo8gIVQ0-DKhDQ&usqp=CAU",
                    8.0F,
                    9.0F,
                    "Free Ship",
                    false
                ),
                Fruit(
                    22,
                    "Mangosteen",
                    "Price per kg",
                    "https://images-na.ssl-images-amazon.com/images/I/41nDqKsRGHL._SY300_QL70_ML2_.jpg",
                    5.0F,
                    6.0F,
                    "Sale 12%",
                    false
                )
            )
        )

    }
}