import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Ejercicio2 {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String url;
        List<CompletableFuture<Void>> descargas = new ArrayList<>();
        Path path = Paths.get("C:\\Users\\carol\\Documents\\DAM\\PSP\\T4\\contenidoUrls.txt");

        while (true) {
            System.out.println("Introduce una URL:");
            url = reader.nextLine();

            String finalUrl = url;
            CompletableFuture<Void> descargaFutura = CompletableFuture.supplyAsync(() -> {
                try {
                    return descargarContenido(finalUrl);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).thenAccept(contenido -> {
                try {
                    Files.write(path, contenido.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            descargas.add(descargaFutura);

            // Esperar a que todas las descargas se completen
            for (CompletableFuture<Void> descarga : descargas) {
                try {
                    descarga.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static String descargarContenido(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> respuesta = client.send(solicitud, HttpResponse.BodyHandlers.ofString());

        return respuesta.body();
    }
}