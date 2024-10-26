package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import racingcar.constant.Constant;
import racingcar.domain.Message;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.race.Result;
import racingcar.domain.race.Results;
import racingcar.domain.race.RoundRecord;

public class MessageService {

    public Message generateResultMessage(Results results) {
        StringBuilder sb = new StringBuilder();
        List<Result> raceResults = results.getResults();

        for (Result result : raceResults) {
            addResult(sb, result);
            sb.append(System.lineSeparator());
        }

        return new Message(sb.toString());
    }

    private void addResult(StringBuilder sb, Result result) {
        for (RoundRecord record : result.getRecords()) {
            sb.append(record.getCarName()).append(" : ");
            sb.append(convertToDash(record.getDistance()));
            sb.append(System.lineSeparator());
        }
    }

    private String convertToDash(int distance) {
        return "-".repeat(distance);
    }

    public Message generateWinnerMessage(Cars cars) {
        List<String> winners = getWinners(cars);
        return new Message(String.join(Constant.DELIMITER, winners));
    }

    private List<String> getWinners(Cars cars) {
        List<String> winners = new ArrayList<>();
        List<Car> raceCars = cars.getCars();

        sortByDistanceHigh(raceCars);
        Car firstCar = raceCars.getFirst();
        int maxDistance = firstCar.getDistance();
        for (Car car : raceCars) {
            addWinners(winners, car, maxDistance);
        }

        return winners;
    }

    private void sortByDistanceHigh(List<Car> cars) {
        cars.sort((o1, o2) -> o2.getDistance() - o1.getDistance());
    }

    private void addWinners(List<String> winners, Car car, int maxDistance) {
        if (car.getDistance() == maxDistance) {
            winners.add(car.getName());
        }
    }
}