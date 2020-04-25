package com.luis.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDemo {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoula", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 310),
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));

        //找出2011年所有交易并按照交易额排序，从低到高
        List<Transaction> result = transactions.stream().filter(a -> a.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        result.forEach(System.out::println);

        //交易员都在那些城市待过
//        List<String> cities = transactions.stream().map(Transaction::getTrader)
//                .map(Trader::getCity).distinct().collect(Collectors.toList());
        List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(Collectors.toList());
        cities.stream().forEach(System.out::println);
        //查找所有来自剑桥的交易，并按姓名排序
        List<Transaction> transactions1OfCambridge = transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(transaction -> transaction.getTrader().getName())).collect(Collectors.toList());
        transactions1OfCambridge.stream().forEach(System.out::println);

        //返回所有交易员的姓名字符串，按字母排序
//        String names = transactions.stream().map(transaction -> transaction.getTrader().getName())
//                .distinct().sorted(String::compareTo).reduce("",(a,b)->a+b);
        String names = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct().sorted(String::compareTo).collect(Collectors.joining());
        System.out.println(names);
        //是否在米兰工作的
        boolean milanBased = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        //生活在剑桥的交易员的所有交易
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue).forEach(System.out::println);

        //最高的交易额
        transactions.stream().reduce((transaction1, transaction2) -> (transaction1.getValue() > transaction2.getValue() ? transaction1 : transaction2))
                .ifPresent(transaction -> System.out.println("Max Transaction Value: " + transaction.getValue()));
        transactions.stream().min(Comparator.comparing(Transaction::getValue))
                .ifPresent(transaction -> System.out.println("Min Transaction Value: " + transaction.getValue()));

        //測試 mapToInt
        OptionalInt min = transactions.stream().mapToInt(Transaction::getValue).max();
        System.out.println(min.orElse(-1));

        //Collector
        Map<Integer, List<Transaction>> transactionsByValues = transactions.stream().collect(Collectors.groupingBy(Transaction::getValue));

        System.out.println(transactions.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Transaction::getValue)))
                .orElse(new Transaction(new Trader("default", "default"), 1970, 1)).toString());

        Integer totalValue = transactions.stream().collect(Collectors.summingInt(Transaction::getValue));
        System.out.println("Total value is " + totalValue);
        Double average = transactions.stream().collect(Collectors.averagingInt(Transaction::getValue));
        System.out.println("Average value is " + average);
        System.out.println("===========statistic===========");
        IntSummaryStatistics statistic = transactions.stream().collect(Collectors.summarizingInt(Transaction::getValue));
        System.out.println(statistic);

        System.out.println("===========reduce==============");
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        stream.reduce(new ArrayList<Integer>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                },
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                }).forEach(System.out::println);
    }
}