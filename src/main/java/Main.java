import calculator.Calculator;

public class Main {
    public static void main(String[] args) {

        runCalculator();
    }

    public static void runCalculator() {
        Calculator.of(10)
                .consume(System.out::println)
                .eval(value -> value * 10 / 5)
                .eval(value -> value + 5)
                .consume(System.out::println)
                .eval(value -> value / 0)
                .consume(System.out::println);

        Calculator.of((Integer) null)
                .eval(value -> value * 10)
                .eval(value -> value + 5)
                .consume(System.out::println);

        Calculator.of(10)
                .eval(value -> value + 5)
                .consume(System.out::println)
                .eval(value -> null)
                .consume(System.out::println);
    }
}
