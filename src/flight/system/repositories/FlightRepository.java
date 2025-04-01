package flight.system.repositories;

import flight.system.data.Flight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FlightRepository {

    private static final int LENGTH_OF_COLUMN = 3;

    public static List<Flight> loadFlights(String path) {
        try {
            return Files.readAllLines(Path.of(path))
                .stream()
                .map(line -> line.split(","))
                .filter(parts -> parts.length == LENGTH_OF_COLUMN)
                .map(parts -> new Flight(parts[0].trim(), parts[1].trim(), Double.parseDouble(parts[2].trim())))
                .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error loading flights file: " + e.getMessage());
        }
        return null;
    }

}