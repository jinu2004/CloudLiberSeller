package networking

object ApiRouter {
    private const val BASE_URL = "https://clwebapp-ry1s.onrender.com"
    const val GET = "$BASE_URL/api/android/fetchHalfData/fiction"
    const val POST = "$BASE_URL/api/sellers/storeBookData"
    const val APIKEY ="Gwg8QOzf0W6kRQl9sov72zVo2mwaAWe5JyO5iY9jrdcF"
    const val POST_APIKEY ="WptwXpw7OeDS1GoPkloKpQjJ8tdxwiCh7384Irqe9ZZtTvlygZ32k0q5z"



    const val APPID = "1:204239095350:android:af5391c0aecd170782fa97"
    const val BUCKETNAME = "cloudlibre-database.appspot.com"
    const val FIREBASE_STORAGE = "https://firebasestorage.googleapis.com/v0/b/$BUCKETNAME/o?uploadType=media&name="

//    https://firebasestorage.googleapis.com/v0/b/project-clouliber.appspot.com/o/coverimages%2F8aadee2eafece86269e20d025bab490e.jpg?alt=media&token=b86f091e-9375-4892-9231-012d6dfa6529
}