package by.bsuir.thermopa.strategy.impl;

import by.bsuir.thermopa.entity.CalculatorResult;
import by.bsuir.thermopa.entity.resistancetermometers.Type;
import by.bsuir.thermopa.exception.InvalidDataException;
import by.bsuir.thermopa.strategy.ThermopaCalculator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("resistanceThermometersCalculator")
public class ResistanceThermometersCalculator implements ThermopaCalculator {
    @Override
    public CalculatorResult calculate(Map<String, Object> params) {

        final Type type = (Type) params.get("type");
        final Double zeroResist = (Double) params.get("zeroResist");
        final Double fromT = (Double) params.get("fromT");
        final Double fromRt = (Double) params.get("fromRt");

        switch (type) {
            case PLATINUM_385:
                return calculate_platinum385(zeroResist, fromT, fromRt);

            case PLATINUM_391:
                return calculate_platinum391(zeroResist, fromT, fromRt);

            case COPPER:
                return calculate_copper(zeroResist, fromT, fromRt);

            case NICKEL:
                return calculate_nickel(zeroResist, fromT, fromRt);

            default:
                throw new RuntimeException();
        }
    }

    private CalculatorResult calculate_platinum385(double zeroResist, double fromT, double fromRt) {
        double resultSOP = 0, resultTem = 0;
        double a = 3.9083 * 10e-3, b = -5.775 * 10e-7, c = -4.183 * 10e-12;
        double d1 = 255.819, d2 = 9.1455, d3 = -2.92363, d4 = 1.7909;
        double[] arr = new double[]{d1, d2, d3, d4};
        if (fromT >= -200 && fromT < 0) {
            resultSOP = zeroResist * (1 + a * fromT + b * Math.pow(fromT, 2) + c * (fromT - 100) * Math.pow(fromT, 3));

            for (int i = 0; i <= 3; ++i) {
                resultTem += arr[i] * Math.pow(fromRt / zeroResist - 1, (double) (i + 1));
            }
        } else if (fromT >= 0 && fromT <= 850) {
            resultSOP = zeroResist * (1 + a * fromT + b * Math.pow(fromT, 2));
        } else {
            throw new InvalidDataException("Вы ввели ошибочную температуру");
        }

        if (fromRt <= zeroResist) {
            for (int i = 0; i <= 3; ++i) {
                resultTem += arr[i] * Math.pow(fromRt / zeroResist - 1, (double) (i + 1));
            }
        } else {
            resultTem = (Math.sqrt(Math.pow(a, 2) - 4 * b * (1 - fromRt / zeroResist)) - a) / (2 * b);
        }

        return new CalculatorResult(resultTem, resultSOP);

    }

    private CalculatorResult calculate_platinum391(double zeroResist, double fromT, double fromRt) {
        double resultSOP = 0, resultTem = 0;
        double a = 3.9690 * 10e-3, b = -5.841 * 10e-7, c = -4.330 * 10e-12;
        double d1 = 255.903, d2 = 8.80035, d3 = -2.91506, d4 = 1.67611;
        double[] arr = new double[]{d1, d2, d3, d4};

        if (fromT >= -200 && fromT < 0) {
            resultSOP = zeroResist * (1 + a * fromT + b * Math.pow(fromT, 2) + c * (fromT - 100) * Math.pow(fromT, 3));
        } else if (fromT >= 0 && fromT <= 850) {
            resultSOP = zeroResist * (1 + a * fromT + b * Math.pow(fromT, 2));
        } else {
            throw new InvalidDataException("Вы ввели ошибочную температуру");
        }

        if (fromRt <= zeroResist) {
            for (int i = 0; i <= 3; ++i) {
                resultTem += arr[i] * Math.pow(fromRt / zeroResist - 1, (double) (i + 1));
            }
        } else {
            resultTem = (Math.sqrt(Math.pow(a, 2) - 4 * b * (1 - fromRt / zeroResist)) - a) / (2 * b);
        }

        return new CalculatorResult(resultTem, resultSOP);
    }

    private CalculatorResult calculate_copper(double zeroResist, double fromT, double fromRt) {
        double a = 4.28 * 10e-3, b = -6.2032 * 10e-8, c = 8.5154 * 10e-12;
        double resultSOP = 0, resultTem = 0;

        double d1 = 233.87, d2 = -2.0062, d3 = 7.937, d4 = -0.3953;
        double[] arr = new double[]{d1, d2, d3, d4};

        if (fromT >= -200 && fromT < 0) {
            resultSOP = zeroResist * (1 + a * fromT + b * Math.pow(fromT, 2) + c * (fromT - 100) * Math.pow(fromT, 3));
        } else if (fromT >= 0 && fromT <= 850) {
            resultSOP = zeroResist * (1 + a * fromT);
        } else {
            throw new InvalidDataException("Вы ввели ошибочную температуру");
        }

        if (fromRt <= zeroResist) {
            for (int i = 0; i <= 3; ++i) {
                resultTem += arr[i] * Math.pow(fromRt / zeroResist - 1, (double) (i + 1));
            }
        } else {
            resultTem = (fromRt / zeroResist - 1) / a;
        }

        return new CalculatorResult(resultTem, resultSOP);

    }

    private CalculatorResult calculate_nickel(double zeroResist, double fromT, double fromRt) {
        double a = 5.4963 * 10e-3, b = 6.7556 * 10e-7, c = -9.2004 * 10e-10;

        double resultSOP = 0, resultTem = 0;

        double d1 = 144.096, d2 = -25.502, d3 = 4.4876;
        double[] arr = new double[]{d1, d2, d3};

        if (fromT >= -60 && fromT < 100) {
            resultSOP = zeroResist * (1 + a * fromT + b * fromT * Math.pow(fromT, 2));
        } else if (fromT >= 100 && fromT <= 180) {
            resultSOP = zeroResist * (1 + a * fromT + b * Math.pow(fromT, 2) + c * (fromT - 100) * Math.pow(fromT, 2));
        } else {
            throw new InvalidDataException("Вы ввели ошибочную температуру");
        }

        if (fromRt <= zeroResist) {
            resultTem = (Math.sqrt(Math.pow(a, 2) - 4 * b * (1 - fromRt / zeroResist)) - a) / (2 * b);
        } else {
            for (int i = 0; i <= 2; ++i) {
                resultTem += arr[i] * Math.pow(fromRt / zeroResist - 1.6172, (double) (i + 1));
            }

            resultTem += 100;
        }

        return new CalculatorResult(resultTem, resultSOP);
    }


}
