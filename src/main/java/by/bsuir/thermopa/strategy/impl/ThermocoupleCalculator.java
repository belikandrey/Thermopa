package by.bsuir.thermopa.strategy.impl;

import by.bsuir.thermopa.entity.CalculatorResult;
import by.bsuir.thermopa.entity.thermocouple.Type;
import by.bsuir.thermopa.strategy.ThermopaCalculator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("thermocoupleCalculator")
public class ThermocoupleCalculator implements ThermopaCalculator {

    @Override
    public CalculatorResult calculate(Map<String, Object> params) {
        final Type type = (Type) params.get("type");
        final Double fromEds = (Double) params.get("fromEds");
        final Double fromT = (Double) params.get("fromT");

        double a, b, c, d, a1, b1, c1, d1;

        switch (type) {
            case TMK_M:
                a = 0.46739;
                b = 22.65393;
                c = -0.78186;
                d = 0.11786;
                a1 = 2.02568E-6;
                b1 = 0.04264;
                c1 = 5.03527E-5;
                d1 = -4.9441E-8;
                break;
            case TJK_J:
                a = -9.00789;
                b = 20.70519;
                c = -0.09652;
                d = 6.9624E-4;
                a1 = 0.34739;
                b1 = 0.04808;
                c1 = 1.52514E-5;
                d1 = -5.93024E-9;
                break;

            case TPP13_R:
                a = 15.24549;
                b = 123.03099;
                c = -3.64165;
                d = 0.08343;
                a1 = -0.02656;
                b1 = 0.00674;
                c1 = 4.91809E-6;
                d1 = -1.10375E-9;
                break;

            case TPP10_S:
                a = 10.9408;
                b = 129.71771;
                c = -3.66195;
                d = 0.09393;
                a1 = -0.0265;
                b1 = 0.00684;
                c1 = 3.64777E-6;
                d1 = -8.54455E-10;
                break;

            case TPR_B:
                a = 146.44839;
                b = 272.34942;
                c = -22.77337;
                d = 0.87502;
                a1 = 0.03346;
                b1 = -5.75046E-4;
                c1 = 6.4871E-6;
                d1 = -1.09397E-9;
                break;

            case TMK_T:
                a = 0.69421;
                b = 29.25358;
                c = -1.11665;
                d = 0.03168;
                a1 = -0.02123;
                b1 = 0.03847;
                c1 = 4.70201E-5;
                d1 = -3.22231E-8;
                break;

            case THH_N:
                a = -18.90302;
                b = 39.20541;
                c = -0.57067;
                d = 0.0071;
                a1 = 0.15603;
                b1 = 0.02522;
                c1 = 1.96738E-5;
                d1 = -8.63967E-9;
                break;

            case TXA_K:
                a = -15.78005;
                b = 28.48037;
                c = -0.22017;
                d = 0.003;
                a1 = 0.45636;
                b1 = 0.0353;
                c1 = 1.31371E-5;
                d1 = -7.41683E-9;
                break;

            case TXKH_E:
                a = -16.15782;
                b = 18.35188;
                c = -0.1646;
                d = 0.00133;
                a1 = 0.28466;
                b1 = 0.0564;
                c1 = 4.80952E-5;
                d1 = -2.90413E-8;
                break;

            case TXK_L:
                a = -8.05693;
                b = 16.33524;
                c = -0.1432;
                d = 0.00124;
                a1 = 0.13355;
                b1 = 0.0623;
                c1 = 5.42674E-5;
                d1 = -3.6148E-8;
                break;

            case A1_A2_A3:
                a = 5.9579;
                b = 68.84126;
                c = -1.00607;
                d = 0.03415;
                a1 = -0.40884;
                b1 = 0.01686;
                c1 = 2.17244E-7;
                d1 = -6.14901E-10;
                break;

            default:
                a = b = c = d = a1 = b1 = c1 = d1 = 0;
        }

        double y = a + b * fromEds + c * fromEds * fromEds + d * fromEds * fromEds * fromEds;
        double x = a1 + b1 * fromT + c1 * fromT * fromT + d1 * fromT * fromT * fromT;

        return new CalculatorResult(y, x);
    }
}
