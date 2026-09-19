import  java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class WeatherApp {
    public static void main(String[] args) throws Exception{
        String Api= "";

        Scanner sc = new Scanner(System.in);
        System.out.print("city- ");
        String city = sc.nextLine().trim();

        String url="http://api.weatherstack.com/current"+ "?access_key="+ Api+ "&query="+city;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(response.body());

//        Location
        JSONObject location = (JSONObject) data.get("location");
        System.out.println("Location- "+location.get("country")+","+location.get("region")+","+location.get("name"));
        System.out.println("Coordinates- "+ location.get("lat")+"/"+ location.get("lon"));

//       current data (Temp,humidity,weather condition,feels like, wind speed)
        JSONObject current = (JSONObject) data.get("current");
        System.out.print("Temperature- "+current.get("temperature")+"C   ");
        System.out.println("Feels like- "+ current.get("feelslike")+"C");
        System.out.println("Humidity- "+ current.get("humidity")+"%");
        System.out.println("Condition- "+ current.get("weather_descriptions"));
        System.out.println("Wind speed- "+current.get("wind_speed")+"Kmph");
    }
}
