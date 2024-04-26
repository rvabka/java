import java.util.Arrays;

public class DeathCauseStatistic {
    private String icd10;
    private int[] deaths;

    public DeathCauseStatistic(String icd10, int[] deaths) {
        this.icd10 = icd10;
        this.deaths = deaths;
    }

    public String getIcd10() {
        return icd10;
    }

    @Override
    public String toString() {
        return "DeathCauseStatistic{" +
                "icd10='" + icd10 + '\'' +
                ", deaths=" + Arrays.toString(deaths) +
                '}';
    }

    public static DeathCauseStatistic fromCsvLine(String csvLine) {
        String tab[] = csvLine.split(",");
        String icd10 = tab[0].trim();
        int[] deaths = Arrays.stream(tab)
                .skip(2)
                .mapToInt(s -> s.equals("-") ? 0 : Integer.parseInt(s))
                .toArray();
        return new DeathCauseStatistic(icd10, deaths);
    }

    public AgeBracketDeaths getAgeBracketDeaths(int age) {
        String ages = "0 – 4,5 - 9,10 - 14,15 - 19,20 - 24,25 - 29,30 - 34,35 - 39,40 - 44,45 - 49,50 - 54,55 - 59,60 - 64,65 - 69,70 - 74,75 - 79,80 - 84,85 - 89,90 - 94,95 lat i więcej";
        int agesIndex = age / 5;
        int young = agesIndex * 5;
        int old = young + 4;
        if (age >= 95) {
            agesIndex = deaths.length - 1;
            old = Integer.MAX_VALUE;
        }
        return new AgeBracketDeaths(young, old, deaths[agesIndex]);
    }

    public class AgeBracketDeaths {
        public final int young;
        public final int old;

        public int getDeathCount() {
            return deathCount;
        }

        public final int deathCount;

        public AgeBracketDeaths(int young, int old, int deathCount) {
            this.young = young;
            this.old = old;
            this.deathCount = deathCount;
        }

        @Override
        public String toString() {
            return "AgeBracketDeaths{" +
                    "young=" + young +
                    ", old=" + old +
                    ", deathCount=" + deathCount +
                    '}';
        }
    }


}
