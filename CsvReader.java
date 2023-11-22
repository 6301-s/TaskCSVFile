import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvReader {
    public static void main(String[] args) {
        String csvFile = "books.csv";
        String outputFile = "results.txt";

        try {
            Map<String, Double> genreRatingSum = new HashMap<>();
            Map<String, Integer> genreRatingCount = new HashMap<>();

            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;

            // Read the CSV file line by line
            while ((line = br.readLine()) != null) {
                // Split the line into values using comma as a separator
                String[] values = line.split(",");

                // Extract genre and rating
                String genre = values[2].trim();
                double rating = Double.parseDouble(values[3].trim());

                // Update the sum and count for the genre
                genreRatingSum.put(genre, genreRatingSum.getOrDefault(genre, 0.0) + rating);
                genreRatingCount.put(genre, genreRatingCount.getOrDefault(genre, 0) + 1);
            }

            br.close();

            // Write the results to the output file
            FileWriter writer = new FileWriter(outputFile);
            for (Map.Entry<String, Double> entry : genreRatingSum.entrySet()) {
                String genre = entry.getKey();
                double totalRating = entry.getValue();
                int count = genreRatingCount.get(genre);

                double averageRating = totalRating / count;

                // Write to the output file
                writer.write(genre + ": " + averageRating + "\n");
            }

            writer.close();

            System.out.println("Results written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
