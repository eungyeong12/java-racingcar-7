package racingcar.domain.race;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private final int round;
    private final List<Record> records;

    public Result(int round) {
        this.round = round;
        this.records = new ArrayList<>();
    }

    public void addRecord(racingcar.domain.race.Record record) {
        this.records.add(record);
    }

    public int getRound() {
        return round;
    }

    public List<Record> getRecords() {
        return records;
    }
}
