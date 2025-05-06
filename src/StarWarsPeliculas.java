import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class StarWarsPeliculas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Ingresa el número de la película que deseas consultar (0 para salir): ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de leer el entero

            switch (opcion) {
                case 1:
                    consultarPelicula(1);
                    break;
                case 2:
                    consultarPelicula(2);
                    break;
                case 3:
                    consultarPelicula(3);
                    break;
                case 4:
                    consultarPelicula(4);
                    break;
                case 5:
                    consultarPelicula(5);
                    break;
                case 6:
                    consultarPelicula(6);
                    break;
                case 0:
                    System.out.println("¡Que la Fuerza te acompañe!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intenta de nuevo.");
            }
            System.out.println(); // Línea en blanco para separar las consultas
        } while (opcion != 0);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("--- Menú de Películas de Star Wars ---");
        System.out.println("1. La amenaza fantasma");
        System.out.println("2. El ataque de los clones");
        System.out.println("3. La venganza de los Sith");
        System.out.println("4. Una nueva esperanza");
        System.out.println("5. El imperio contraataca");
        System.out.println("6. El retorno del Jedi");
        System.out.println("0. Salir");
    }

    public static void consultarPelicula(int episodio) {
        String apiUrl = "https://swapi.py4e.com/api/films/" + episodio + "/";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Aquí llamaremos a un método para procesar la respuesta JSON
                procesarRespuesta(response.toString());

            } else {
                System.out.println("Error al consultar la API. Código de respuesta: " + connection.getResponseCode());
            }

            connection.disconnect();

        } catch (IOException e) {
            System.err.println("Ocurrió un error al realizar la petición: " + e.getMessage());
        }
    }

    public static void procesarRespuesta(String jsonRespuesta) {
        try {
            JSONObject jsonObject = new JSONObject(jsonRespuesta);

            String titulo = jsonObject.getString("title");
            int episodio = jsonObject.getInt("episode_id");
            String director = jsonObject.getString("director");
            String productor = jsonObject.getString("producer");
            String fechaLanzamiento = jsonObject.getString("release_date");
            String textoIntroductorio = jsonObject.getString("opening_crawl");

            System.out.println("--- Información de la Película ---");
            System.out.println("Título: " + titulo);
            System.out.println("Episodio: " + episodio);
            System.out.println("Director: " + director);
            System.out.println("Productor(es): " + productor);
            System.out.println("Fecha de Lanzamiento: " + fechaLanzamiento);
            System.out.println("\nTexto Introductorio:");
            System.out.println(textoIntroductorio);

            // Llamar al método para grabar la información en un archivo
            GrabadorPelicula.grabarPelicula(jsonObject);

        } catch (org.json.JSONException e) {
            System.err.println("Error al procesar la respuesta JSON: " + e.getMessage());
        }
        // Aquí implementaremos la lógica para analizar el JSON y mostrar la información
        //System.out.println("Respuesta JSON de la API:");
        //System.out.println(jsonRespuesta);
        // Por ahora, solo imprimimos la respuesta JSON sin procesar
    }
}
