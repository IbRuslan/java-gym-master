package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable;

    Timetable() {
        timetable = new HashMap<>();
    }

    public void addNewTrainingSession(TrainingSession trainingSession) {
        TreeMap<TimeOfDay, List<TrainingSession>> daySession =
                timetable.computeIfAbsent(trainingSession.getDayOfWeek(), k -> new TreeMap<>());

        List<TrainingSession> sessionsAtTime =
                daySession.computeIfAbsent(trainingSession.getTimeOfDay(), k -> new ArrayList<>());

        sessionsAtTime.add(trainingSession);

    }

    public TreeMap<TimeOfDay, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return timetable.getOrDefault(dayOfWeek, new TreeMap<>());
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        return timetable
                .getOrDefault(dayOfWeek, new TreeMap<>())
                .getOrDefault(timeOfDay, new ArrayList<>());
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        HashMap<Coach, Integer> result = new HashMap<>();

        if (!timetable.isEmpty()) {
            for (DayOfWeek dayOfWeek : timetable.keySet()) {
                for (TimeOfDay timeOfDay : timetable.get(dayOfWeek).keySet()) {
                    for (TrainingSession session : timetable.get(dayOfWeek).get(timeOfDay)) {
                        result.put(session.getCoach(), result.getOrDefault(session.getCoach(), 0) + 1);
                    }
                }
            }
        }

        List<CounterOfTrainings> list  = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : result.entrySet()) {
            list.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }

        list.sort((a,b) -> b.getCount() - a.getCount());

        return list;

    }
}
