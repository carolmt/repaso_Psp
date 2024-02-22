import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Scanner;
/*Implementa una aplicación por consola que vaya pidiendo diferentes URL indefinidamente y
las añada a un listado. Este listado será observado por la clase Downloader siguiendo el
patrón observer. Cada vez que se añade una nueva URL al listado se mostrará por pantalla
el texto: “Se ha iniciado la descarga del archivo URL”. Donde la URL es la introducida por el
usuario.*/

public class main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String url;
        Downloader downloader = new Downloader();
        ObservableList<String> urls = FXCollections.observableArrayList();
        urls.addListener(downloader);

        while (true) {
            System.out.println("Introduce una URL:");
            url = reader.nextLine();
            urls.add(url);
        }
    }
}
