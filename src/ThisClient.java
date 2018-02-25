import javax.ws.rs.client.ClientBuilder;

public class ThisClient {
    private static ThisClient ourInstance = new ThisClient();

    javax.ws.rs.client.Client client;

    public static ThisClient getInstance() {
        if (ourInstance == null)
            ourInstance = new ThisClient();
        return ourInstance;
    }

    private ThisClient() {
        client = ClientBuilder.newClient();
    }

    public javax.ws.rs.client.Client getClient() {
        return this.client;
    }
}
