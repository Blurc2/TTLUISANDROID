package peach.princess.my.net.ttluis.data.network.services

import okhttp3.Credentials
import retrofit2.Retrofit
import java.util.HashMap

class ApiService(var retrofit : Retrofit) {

    fun <T> getApiClient(type: Class<T>): T {
        return retrofit.create(type)
    }

    companion object {
        fun getHeaders(): Map<String, String> {
            val headers = HashMap<String, String>()
            headers["Content-Type"] = "application/x-www-form-urlencoded"
            headers["charset"] = "UTF-8"
            headers["X-Requested-By"] = "rwsa"
            headers["Authorization"] = Credentials.basic(
                "Crbeumk6FyRfZLpPTex5Ak3aQe6i3pgh",
                "V8Luy6WuarJoCyWTyDqGHcBV6DXha5J5Z5Y5XWg2geLEc6N9sBaPqM25Gyjjqai8")
            return headers
        }

        fun getHeaders(token: String): Map<String, String> {
            val headers = HashMap<String, String>()
            val stringBuilder = StringBuilder("Bearer").append(" ").append(token)
            headers["Content-Type"] = "application/json"
            headers["Authorization"] = stringBuilder.toString()
            return headers
        }
    }


}
