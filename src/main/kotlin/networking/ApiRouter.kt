package networking

object ApiRouter {
    private const val BASE_URL = "https://clwebapp-ry1s.onrender.com"
    const val GET = "$BASE_URL/api/android/fetchHalfData/nonfiction"
    const val POST = "$BASE_URL/api/sellers/storeBookData"
    const val APIKEY ="Gwg8QOzf0W6kRQl9sov72zVo2mwaAWe5JyO5iY9jrdcF"
    const val POST_APIKEY ="WptwXpw7OeDS1GoPkloKpQjJ8tdxwiCh7384Irqe9ZZtTvlygZ32k0q5z"



    const val APPID = "1:204239095350:android:af5391c0aecd170782fa97"
    const val BUCKETNAME = "cloudlibre-database.appspot.com"
    const val FIREBASE_STORAGE = "https://firebasestorage.googleapis.com/v0/b/$BUCKETNAME/o?uploadType=media&name="

//    https://firebasestorage.googleapis.com/v0/b/cloudlibre-database.appspot.com/o/books%2Fimage_test_2.jpg?alt=media&token=a634287e-7f61-4b22-9227-3f235af2bfb3
}