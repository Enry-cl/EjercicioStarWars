import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GrabadorPeliculaReiniciaElArchivo {

    public static void grabarPelicula(JSONObject peliculaInfo) {
        // Generar un nombre de archivo único con la fecha y hora
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = now.format(formatter);
        String nombreArchivo = "pelicula_" + peliculaInfo.getString("title").replaceAll("[^a-zA-Z0-9]", "") + "_" + timestamp + ".txt";

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("--- Información de la Película ---\n");
            writer.write("Título: " + peliculaInfo.getString("title") + "\n");
            writer.write("Episodio: " + peliculaInfo.getInt("episode_id") + "\n");
            writer.write("Director: " + peliculaInfo.getString("director") + "\n");
            writer.write("Productor(es): " + peliculaInfo.getString("producer") + "\n");
            writer.write("Fecha de Lanzamiento: " + peliculaInfo.getString("release_date") + "\n");
            writer.write("\nTexto Introductorio:\n");
            // Reemplazar saltos de línea en el texto introductorio para una mejor visualización en el archivo
            writer.write(peliculaInfo.getString("opening_crawl").replace("\r\n", "\n") + "\n");
            System.out.println("La información de la película '" + peliculaInfo.getString("title") + "' ha sido grabada en el archivo '" + nombreArchivo + "'.");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo '" + nombreArchivo + "': " + e.getMessage());
        }
    }
}