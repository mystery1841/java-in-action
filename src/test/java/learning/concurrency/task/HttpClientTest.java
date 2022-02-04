package learning.concurrency.task;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HttpClientTest {
    public static void main(String[] args) throws InterruptedException {
        String urlString = "https://cn.bing.com/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(urlString)).GET().build();
        CompletableFuture<HttpResponse<String>> f = client.sendAsync(
                request, HttpResponse.BodyHandlers.ofString());
        Thread.sleep(10000L);
    }


    public CompletableFuture<String> readPage(URL url) {
        var executor = Executors.newCachedThreadPool();
        return CompletableFuture.supplyAsync(() ->
        {
            try {
                return new String(url.openStream().readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }
}
