package com.luis.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamDishDemo {
    public static void main(String[] args) {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("pork", 100));
        dishes.add(new Dish("beef", 200));
        dishes.add(new Dish("chicken", 300));
        dishes.add(new Dish("french", 400));

        System.out.println("=============dishes==============");
        int totalCalories = dishes.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories);
        System.out.println("============reduce max==============");
        Optional<Dish> maxDish = dishes.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(maxDish.get().getName());
        System.out.println("============collectors==============");
        List<Dish> dishes2 = dishes.stream().reduce(new ArrayList<Dish>(),
                (List<Dish> l, Dish e) -> {
                    l.add(e);
                    return l;
                },
                (List<Dish> l1, List<Dish> l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
        dishes2.stream().map(Dish::getName).forEach(System.out::println);
        int totalCalories2 = dishes.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println("Total is " + totalCalories2);

    }

    private static class Dish {
        String name;
        int calories;

        public Dish(String name, int calories) {
            this.name = name;
            this.calories = calories;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }
    }
}
