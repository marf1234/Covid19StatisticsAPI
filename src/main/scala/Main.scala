import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.Scanner
object Main {
  def main(args: Array[String]): Unit = {
    val scanner = new Scanner(System.in)
    print("Введите название страны: ")
    val country = scanner.nextLine()
    val apiUrl = s"https://api.covid19api.com/dayone/country/$country/status/deaths"

    val client = HttpClientBuilder.create().build()
    val request = new HttpGet(apiUrl)
    val response = client.execute(request)

    val responseJson = EntityUtils.toString(response.getEntity())
    val dataArray = new JSONArray(responseJson)

    var maxDeaths = 0
    var maxDeathsDate = ""
    for (i <- 0 until dataArray.length()) {
      val dataObject = dataArray.getJSONObject(i)
      val deaths = dataObject.getInt("Cases")
      val date = dataObject.getString("Date").substring(0, 10)

      if (deaths > maxDeaths) {
        maxDeaths = deaths
        maxDeathsDate = date
      }
    }

    println(s"Максимальное количество умерших: $maxDeaths")
    println(s"Дата: $maxDeathsDate")

    scanner.close()
  }
  }
