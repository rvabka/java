import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DeathCauseStatisticList {
    private List<DeathCauseStatistic> deathList = new ArrayList<>();
    public void repopulate(String path) {
        try(Stream<String> fileLines = Files.lines(Path.of(path))) {
            deathList = fileLines
                    .skip(2)
                    .map(DeathCauseStatistic::fromCsvLine)
                    .toList();
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n){
        return deathList.stream()
                .sorted((o1, o2) -> Integer.compare(
                        o2.getAgeBracketDeaths(age).getDeathCount(),
                        o1.getAgeBracketDeaths(age).getDeathCount()))
                .limit(n)
                .toList();
    }
}
}
