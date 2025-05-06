import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GrabadorPelicula {

    private static final String NOMBRE_ARCHIVO_GLOBAL = "peliculas_starwars.txt";

    public static void grabarPelicula(JSONObject peliculaInfo) {
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO_GLOBAL, true)) { // El 'true' indica modo de anexar
            writer.write("--- Información de la Película ---\n");
            writer.write("Título: " + peliculaInfo.getString("title") + "\n");
            writer.write("Episodio: " + peliculaInfo.getInt("episode_id") + "\n");
            writer.write("Director: " + peliculaInfo.getString("director") + "\n");
            writer.write("Productor(es): " + peliculaInfo.getString("producer") + "\n");
            writer.write("Fecha de Lanzamiento: " + peliculaInfo.getString("release_date") + "\n");
            writer.write("Fecha y Hora de Consulta: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("\nTexto Introductorio:\n");
            writer.write(peliculaInfo.getString("opening_crawl").replace("\r\n", "\n") + "\n");
            writer.write("------------------------------------\n\n"); // Separador entre películas
            System.out.println("La información de la película '" + peliculaInfo.getString("title") + "' ha sido agregada al archivo '" + NOMBRE_ARCHIVO_GLOBAL + "'.");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo '" + NOMBRE_ARCHIVO_GLOBAL + "': " + e.getMessage());
        }
    }
}
