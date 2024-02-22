import javafx.collections.ListChangeListener;

/*Implementa una aplicación por consola que vaya pidiendo diferentes URL indefinidamente y
las añada a un listado. Este listado será observado por la clase Downloader siguiendo el
patrón observer. Cada vez que se añade una nueva URL al listado se mostrará por pantalla
el texto: “Se ha iniciado la descarga del archivo URL”. Donde la URL es la introducida por el
usuario.*/
public class Downloader implements ListChangeListener<String> {
    @Override
    public void onChanged(Change<? extends String> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                System.out.println("Se ha iniciado la descarga del archivo " + change.getAddedSubList().get(0));
            }
        }
    }
}
