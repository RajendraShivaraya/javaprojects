package remoteproxydesigndemo.service;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import remoteproxydesigndemo.model.MenuItems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantManagerImplProxy implements RestaurantManager
{
    @Override
    public List<MenuItems> getMenu(int restaurantNumber)
    {
        if (validateRestaurantNum(restaurantNumber))
        {
            return getMenuFromServer(restaurantNumber);
        }
        return null;
    }

    @Override
    public String getAddress(int restaurantNumber)
    {
        if (validateRestaurantNum(restaurantNumber))
        {
            return getAddressFromServer(restaurantNumber);
        }
        return null;
    }

    @Override
    public Long getContactNumber(int restaurantNumber)
    {
        if (validateRestaurantNum(restaurantNumber))
        {
            return getContactFromServer(restaurantNumber);
        }
        return null;
    }

    private boolean validateRestaurantNum(int restaurantNumber)
    {
        boolean result = true;
        try
        {
            if (restaurantNumber > 2 || restaurantNumber == 0)
            {
                result = false;
                System.out.println("Invalid Restaurant Number");
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return result;
    }

    private List<MenuItems> getMenuFromServer(int restaurantNumber)
    {
        String url = "http://localhost:8081/menu/" + restaurantNumber;
        String response = sendGetRequest(url);
        // Convert the JSON response to a List<Object>
        List<MenuItems> responseList = new ArrayList<>();
        Gson gson = new Gson();
        responseList = gson.fromJson(response.toString(), List.class);

        return responseList;
    }

    private String getAddressFromServer(int restaurantNumber) {
        String url = "http://localhost:8081/address/" + restaurantNumber;
        return sendGetRequest(url);
    }

    private Long getContactFromServer(int restaurantNumber) {
        String url = "http://localhost:8081/contact/" + restaurantNumber;
        String response = sendGetRequest(url);

        return Long.parseLong(response);
    }

    private String sendGetRequest(String url)
    {
        try {
            // Create an HttpURLConnection object to send the HTTP request
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

            // Set the HTTP method to GET
            con.setRequestMethod("GET");

            // Set the headers, if necessary
            con.setRequestProperty("Content-Type", "application/json");

            // Send the HTTP request
            int responseCode = con.getResponseCode();

            // Check the HTTP response code
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the API endpoint
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Return the response from the API endpoint
                return response.toString();
            } else
            {
                System.out.println("API call failed with HTTP status code: " + responseCode);
                return null;
            }
        } catch (Exception e)
        {
            System.out.println("Exception caught: " + e.getMessage());
            return null;
        }
    }
}
