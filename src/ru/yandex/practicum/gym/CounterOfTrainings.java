package ru.yandex.practicum.gym;

public class CounterOfTrainings {
    private Coach coach;
    private int count;

    CounterOfTrainings(Coach coach, int count) {
        this.coach = coach;
        this.count = count;
    }

    public Coach getCoach() {
        return coach;
    }

    public Integer getCount() {
        return count;
    }
}
