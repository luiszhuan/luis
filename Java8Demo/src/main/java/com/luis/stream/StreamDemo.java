package com.luis.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Acion");
        List<Integer> wordLength = words.stream().map(String::length).collect(Collectors.toList());
        wordLength.stream().forEach(System.out::println);
        System.out.println("====================================");
        List<String> chars = words.stream().map(word -> word.split("")).flatMap(Arrays::stream).map(String::toLowerCase).distinct().collect(Collectors.toList());
        chars.forEach(System.out::println);
        System.out.println("====================================");

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        int sum = nums.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        nums.stream().map(a -> (a + ",")).forEach(System.out::print);
        System.out.println(nums.stream().reduce((a, b) -> a + b));

        System.out.println("============int stream demo=========");
        IntStream intStream = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(intStream.count());

        //勾股數
        System.out.println("==========pythagoreanTriples=========");
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        //生成流
        Stream<String> stringStream = Stream.of("Luis", "Mara", "Qiqi", "Niannian");
        stringStream.map(String::toUpperCase).forEach(a -> System.out.print(a + ","));
        stringStream = Stream.of("Luis", "Mara", "Qiqi", "Niannian");
        System.out.println(String.join(",", stringStream.collect(Collectors.toList())));
        stringStream = Stream.of("Luis", "Mara", "Qiqi", "Niannian");
        System.out.println(stringStream.collect(Collectors.joining(",", "[", "]")));

        System.out.println("==========stream iterator=========");
        //Stream.iterator
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(10).map(t -> t[0]).forEach(System.out::println);
        //Stream.generator
        System.out.println("==========random limit 5=========");
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        System.out.println("===============fib====================");
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
        System.out.println("==========stream 2===============");
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);
        List<int[]> pairs = number1.stream().flatMap(i -> number2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        System.out.println(pairs);

    }
}
