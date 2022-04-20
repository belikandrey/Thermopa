package by.bsuir.thermopa.strategy;

import by.bsuir.thermopa.entity.CalculatorResult;

import java.util.Map;

public interface ThermopaCalculator {
    CalculatorResult calculate(Map<String, Object> params);
}
