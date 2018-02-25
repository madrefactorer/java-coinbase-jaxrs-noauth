import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.lang.Double.parseDouble;

/**
    Java 8
    Dependency jars needed:
        hk2-api-2.4.0.jar
        hk2-locator-2.4.0.jar
        hk2-utils-2.4.0.jar
        javax.ws.rs-api-2.0.jar
        jersey-all-2.22.2.jar
        jersey-guava-2.7.jar
        json-20171018.jar
 */

public class CoinbaseJaxRS {

    public double retrievePrice(String coinSymbol) {

        String coinURL = Coin.URLHASH.get(coinSymbol);
        Client client = ThisClient.getInstance().getClient();
        Response response;

        response = client.target(coinURL)
                        .request(MediaType.APPLICATION_JSON)
                        .get();

        if (response.getStatusInfo().getStatusCode() != Response.Status.OK.getStatusCode()) {
            // Get custom error message from Coinbase.
            System.out.println( response.readEntity(String.class));
            return -1;
        } else {

            String responseAsString = response.readEntity(String.class);

            JSONObject jsonObj = new JSONObject(responseAsString);

            JSONObject data = jsonObj.getJSONObject("data");

            String price = data.getString("amount");

            return parseDouble(price);
        }
    }
}


