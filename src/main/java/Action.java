import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class Action {

    private static final String URI = "http://jsonplaceholder.typicode.com/posts/1";

    public static void main(String[] args) {

        final HttpClient httpClient = HttpClientBuilder.create().build();

        try {

            String response = request(httpClient, new URI(URI));
            System.out.println("Response: " + response);

        } catch (IOException e) {
            System.out.println("Error:" + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error:" + e.toString());
        }

    }

    public static String request(HttpClient httpClient, URI uri) throws IOException {

        HttpUriRequest request = RequestBuilder.get().setUri(uri)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json").build();

        HttpResponse httpResponse = httpClient.execute(request);

        return read(httpResponse.getEntity().getContent());

    }

    public static String read(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();

    }
}
