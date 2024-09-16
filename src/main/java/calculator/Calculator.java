package calculator;

import java.util.function.Consumer;
import java.util.function.Function;

public class Calculator<T extends Number> {
    /**
     * Калькулятор в котором произошла ошибка
     */
    private static final Calculator<?> BROKEN_CALCULATOR = new Calculator<>(true);

    /**
     * Значение, хранящееся внутри калькулятора
     */
    private final T value;

    /**
     * Определяет, есть ли в калькуляторе ошибка
     */
    private final boolean hasError;

    private Calculator(T value) {
        this.value = value;
        this.hasError = false;
    }

    private Calculator(boolean hasError) {
        this.value = null;
        this.hasError = hasError;
    }

    /**
     * Он возвращает неработающий калькулятор с явным приведением типов.
     */
    @SuppressWarnings("unchecked")
    private static <T extends Number> Calculator<T> getBrokenCalculator() {
        return (Calculator<T>) BROKEN_CALCULATOR;
    }

    /**
     * Метод создает новый экземпляр калькулятора с указанным начальным значением.
     */
    public static <T extends Number> Calculator<T> of(T value) {
        if (value == null) return getBrokenCalculator();
        return new Calculator<>(value);
    }

    /**
     * Метод применяет заданную функцию к значению, хранящемуся в калькуляторе.
     * Он никогда не выдает ArithmeticException или NullPointerException.
     */
    public <U extends Number> Calculator<U> eval(Function<? super T, ? extends U> mapper) {
        try {
            return new Calculator<>(mapper.apply(value));
        } catch (ArithmeticException | NullPointerException e) {
            return getBrokenCalculator();
        }
    }

    /**
     * Метод передает сохраненное значение {@consumer} только в том случае,
     * если в калькуляторе не произошло ошибок.
     */
    public Calculator<T> consume(Consumer<T> consumer) {
        if (value == null /*|| consumer == null*/) return getBrokenCalculator();
        consumer.accept(value);
        return this;
    }
}