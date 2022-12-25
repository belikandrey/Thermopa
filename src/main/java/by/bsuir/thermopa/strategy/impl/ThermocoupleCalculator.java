package by.bsuir.thermopa.strategy.impl;

import by.bsuir.thermopa.entity.CalculatorResult;
import by.bsuir.thermopa.entity.thermocouple.Type;
import by.bsuir.thermopa.strategy.ThermopaCalculator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("thermocoupleCalculator")
public class ThermocoupleCalculator implements ThermopaCalculator {

    @Override
    public CalculatorResult calculate(Map<String, Object> params) {
        final Double fromEds = (Double) params.get("fromEds");
        Type type = (Type) params.get("type");
        Double fromT = (Double) params.get("fromT");

        return new CalculatorResult(getEds(type, fromT), getTemperature(type, fromT));
    }

    private double getEds(Type type, Double fromT){
        List<Double> variables = new ArrayList<>();
        switch (type) {
            case TMK_M:
                if(fromT >= -200 && fromT < 100){
                    variables.add(2.4455560 * Math.pow(10.0, -6));
                    variables.add(4.2638917 * Math.pow(10.0, -2));
                    variables.add(5.0348392  * Math.pow(10.0, -5));
                    variables.add(-4.4974485   * Math.pow(10.0, -8));
                }
                break;
            case TJK_J:
                if(fromT >= -210 && fromT < 760){
                    variables.add(0.0);
                    variables.add(5.0381187815 * Math.pow(10.0, -2));
                    variables.add(3.0475836930  * Math.pow(10.0, -5));
                    variables.add(-8.5681065720  * Math.pow(10.0, -8));
                    variables.add(1.3228195295  * Math.pow(10.0, -10));
                    variables.add(-1.7052958337  * Math.pow(10.0, -13));
                    variables.add(2.0948090697   * Math.pow(10.0, -16));
                    variables.add(-1.2538395336  * Math.pow(10.0, -19));
                    variables.add(1.5631725697  * Math.pow(10.0, -23));
                } else if (fromT >= 760 && fromT < 1200){
                    variables.add(2.9645625681* Math.pow(10.0, 2));
                    variables.add(-1.4976127786);
                    variables.add(3.1787103924  * Math.pow(10.0, -3));
                    variables.add(-3.1847686701  * Math.pow(10.0, -6));
                    variables.add(1.5720819004  * Math.pow(10.0, -9));
                    variables.add(-3.0691369056  * Math.pow(10.0, -13));
                }
                break;

            case TPP13_R:
                if(fromT >= -50.0 && fromT < 1064.18){
                    variables.add(0.0);
                    variables.add(5.28961729765 * Math.pow(10.0, -3));
                    variables.add(1.39166589782 * Math.pow(10.0, -5));
                    variables.add(-2.38855693017 * Math.pow(10.0, -8));
                    variables.add(3.56916001063 * Math.pow(10.0, -11));
                    variables.add(-4.62347666298 * Math.pow(10.0, -14));
                    variables.add(5.00777441034 * Math.pow(10.0, -17));
                    variables.add(-3.73105886191 * Math.pow(10.0, -20));
                    variables.add(1.57716482367  * Math.pow(10.0, -23));
                    variables.add(-2.81038625251 * Math.pow(10.0, -27));
                } else if (fromT >= 1064.18 && fromT < 1664.5){
                    variables.add(2.95157925316);
                    variables.add(2.52061251332  * Math.pow(10.0, -3));
                    variables.add(1.59564501865 * Math.pow(10.0, -5));
                    variables.add(-7.64085947576 * Math.pow(10.0, -9));
                    variables.add(2.05305291024 * Math.pow(10.0, -12));
                    variables.add(-2.93359668173 * Math.pow(10.0, -16));
                } else if (fromT >= 1664.5 && fromT < 1768.1){
                    variables.add(1.52232118209 * Math.pow(10.0, 2));
                    variables.add(-2.68819888545  * Math.pow(10.0, -1));
                    variables.add(1.71280280471 * Math.pow(10.0, -4));
                    variables.add(-3.45895706453 * Math.pow(10.0, -8));
                    variables.add(-9.34633971046 * Math.pow(10.0, -15));
                }
                break;

            case TPP10_S:
                if(fromT >= -50.0 && fromT < 1064.18){
                    variables.add(0.0);
                    variables.add(5.40313308631 * Math.pow(10.0, -3));
                    variables.add(1.25934289740 * Math.pow(10.0, -5));
                    variables.add(-2.32477968689 * Math.pow(10.0, -8));
                    variables.add(3.22028823036  * Math.pow(10.0, -11));
                    variables.add(-3.31465196389 * Math.pow(10.0, -14));
                    variables.add(2.55744251786  * Math.pow(10.0, -17));
                    variables.add(-1.25068871393 * Math.pow(10.0, -20));
                    variables.add(2.71443176145  * Math.pow(10.0, -24));
                } else if (fromT >= 1064.18 && fromT < 1664.5){
                    variables.add(1.32900444085);
                    variables.add(3.34509311344  * Math.pow(10.0, -3));
                    variables.add(6.54805192818 * Math.pow(10.0, -6));
                    variables.add(-1.64856259209 * Math.pow(10.0, -9));
                    variables.add(1.29989605174 * Math.pow(10.0, -14));
                } else if (fromT >= 1664.5 && fromT < 1768.1){
                    variables.add(1.46628232636 * Math.pow(10.0, 2));
                    variables.add(-2.58430516752  * Math.pow(10.0, -1));
                    variables.add(1.63693574641 * Math.pow(10.0, -4));
                    variables.add(-3.30439046987 * Math.pow(10.0, -8));
                    variables.add(-9.43223690612 * Math.pow(10.0, -15));
                }
                break;

            case TPR_B:
                if(fromT >= 0.0 && fromT < 630.615){
                    variables.add(0.0);
                    variables.add(-2.4650818346 * Math.pow(10.0, -4));
                    variables.add(5.9040421171 * Math.pow(10.0, -6));
                    variables.add(-1.3257931636  * Math.pow(10.0, -9));
                    variables.add(1.5668291901  * Math.pow(10.0, -12));
                    variables.add(-1.6944529240 * Math.pow(10.0, -15));
                    variables.add(6.2990347094  * Math.pow(10.0, -19));
                } else if (fromT >= 630.615 && fromT < 1820){
                    variables.add(-3.8938168621);
                    variables.add(2.8571747470 * Math.pow(10.0, -2));
                    variables.add(-8.4885104785  * Math.pow(10.0, -5));
                    variables.add(1.5785280164  * Math.pow(10.0, -7));
                    variables.add(-1.6835344864  * Math.pow(10.0, -10));
                    variables.add(1.1109794013  * Math.pow(10.0, -13));
                    variables.add(-4.4515431033  * Math.pow(10.0, -17));
                    variables.add(9.8975640821  * Math.pow(10.0, -21));
                    variables.add(-9.3791330289  * Math.pow(10.0, -25));
                }
                break;

            case TMK_T:
                if(fromT >= -270 && fromT < 0.0){
                    variables.add(0.0);
                    variables.add(3.8748106364 * Math.pow(10.0, -2));
                    variables.add(4.4194434347 * Math.pow(10.0, -5));
                    variables.add(1.1844323105  * Math.pow(10.0, -7));
                    variables.add(2.0032973554  * Math.pow(10.0, -8));
                    variables.add(9.0138019559 * Math.pow(10.0, -10));
                    variables.add(2.2651156593  * Math.pow(10.0, -11));
                    variables.add(3.6071154205 * Math.pow(10.0, -13));
                    variables.add(3.8493939883 * Math.pow(10.0, -15));
                    variables.add(2.8213521925 * Math.pow(10.0, -17));
                    variables.add(1.4251594779   * Math.pow(10.0, -19));
                    variables.add(4.8768662286  * Math.pow(10.0, -22));
                    variables.add(1.0795539270  * Math.pow(10.0, -24));
                    variables.add(1.3945027062  * Math.pow(10.0, -27));
                    variables.add(7.9795153927  * Math.pow(10.0, -31));
                } else if (fromT >= 0.0 && fromT < 400){
                    variables.add(0.0);
                    variables.add(3.8748106364 * Math.pow(10.0, -2));
                    variables.add(3.3292227880  * Math.pow(10.0, -5));
                    variables.add(2.0618243404  * Math.pow(10.0, -7));
                    variables.add(-2.1882256846  * Math.pow(10.0, -9));
                    variables.add(1.0996880928  * Math.pow(10.0, -11));
                    variables.add(-3.0815758772  * Math.pow(10.0, -14));
                    variables.add(4.5479135290  * Math.pow(10.0, -17));
                    variables.add(-2.7512901673  * Math.pow(10.0, -20));
                }
                break;

            case THH_N:
                if(fromT >= -270 && fromT < 0.0){
                    variables.add(0.0);
                    variables.add(2.6159105962  * Math.pow(10.0, -2));
                    variables.add(1.0957484228 * Math.pow(10.0, -5));
                    variables.add(-9.3841111554   * Math.pow(10.0, -8));
                    variables.add(-4.6412039759  * Math.pow(10.0, -11));
                    variables.add(-2.6303357716    * Math.pow(10.0, -12));
                    variables.add(-2.2653438003  * Math.pow(10.0, -14));
                    variables.add(-7.6089300791 * Math.pow(10.0, -17));
                    variables.add(-9.3419667835 * Math.pow(10.0, -20));
                } else if (fromT >= 0.0 && fromT < 1300){
                    variables.add(0.0);
                    variables.add(2.5929394601 * Math.pow(10.0, -2));
                    variables.add(1.5710141880  * Math.pow(10.0, -5));
                    variables.add(4.3825627237 * Math.pow(10.0, -8));
                    variables.add(-2.5261169794  * Math.pow(10.0, -10));
                    variables.add(6.4311819339  * Math.pow(10.0, -13));
                    variables.add(-1.0063471519  * Math.pow(10.0, -15));
                    variables.add(9.9745338992  * Math.pow(10.0, -19));
                    variables.add(-6.0863245607  * Math.pow(10.0, -22));
                    variables.add(2.0849229339  * Math.pow(10.0, -25));
                    variables.add(-3.0682196151  * Math.pow(10.0, -29));
                }
                break;

            case TXA_K:
                if(fromT >= -270 && fromT < 0.0){
                    variables.add(0.0);
                    variables.add(3.9450128025 * Math.pow(10.0, -2));
                    variables.add(2.3622373598 * Math.pow(10.0, -5));
                    variables.add(-3.2858906784   * Math.pow(10.0, -7));
                    variables.add(-4.9904828777  * Math.pow(10.0, -9));
                    variables.add(-6.7509059173   * Math.pow(10.0, -11));
                    variables.add(-5.7410327428  * Math.pow(10.0, -13));
                    variables.add(-3.1088872894 * Math.pow(10.0, -15));
                    variables.add(-1.0451609365 * Math.pow(10.0, -17));
                    variables.add(-1.9889266878 * Math.pow(10.0, -20));
                    variables.add(-1.6322697486   * Math.pow(10.0, -23));
                } else if (fromT >= 0.0 && fromT < 1372){
                    variables.add(-1.7600413686 * Math.pow(10.0, -2));
                    variables.add(3.8921204975 * Math.pow(10.0, -2));
                    variables.add(1.8558770032  * Math.pow(10.0, -5));
                    variables.add(-9.9457592874 * Math.pow(10.0, -8));
                    variables.add(3.1840945719  * Math.pow(10.0, -10));
                    variables.add(-5.6072844889  * Math.pow(10.0, -13));
                    variables.add(5.6075059059  * Math.pow(10.0, -16));
                    variables.add(-3.2020720003  * Math.pow(10.0, -19));
                    variables.add(9.7151147152  * Math.pow(10.0, -23));
                    variables.add(-1.2104721275  * Math.pow(10.0, -26));
                }
                break;

            case TXKH_E:
                if(fromT >= -270 && fromT < 0.0){
                    variables.add(0.0);
                    variables.add(5.8665508708 * Math.pow(10.0, -2));
                    variables.add(4.5410977124 * Math.pow(10.0, -5));
                    variables.add(-7.7998048686  * Math.pow(10.0, -7));
                    variables.add(-2.5800160843  * Math.pow(10.0, -8));
                    variables.add(-5.9452583057  * Math.pow(10.0, -10));
                    variables.add(-9.3214058667  * Math.pow(10.0, -12));
                    variables.add(-1.0287605534 * Math.pow(10.0, -13));
                    variables.add(-8.0370123621 * Math.pow(10.0, -16));
                    variables.add(-4.3979497391 * Math.pow(10.0, -18));
                    variables.add(-1.6414776355   * Math.pow(10.0, -20));
                    variables.add(-3.9673619516  * Math.pow(10.0, -23));
                    variables.add(-5.5827328721  * Math.pow(10.0, -26));
                    variables.add(-3.4657842013  * Math.pow(10.0, -29));
                } else if (fromT >= 0.0 && fromT < 1000){
                    variables.add(0.0);
                    variables.add(5.8665508710 * Math.pow(10.0, -2));
                    variables.add(4.5032275582  * Math.pow(10.0, -5));
                    variables.add(2.8908407212  * Math.pow(10.0, -8));
                    variables.add(-3.3056896652  * Math.pow(10.0, -10));
                    variables.add(6.5024403270  * Math.pow(10.0, -13));
                    variables.add(-1.9197495504  * Math.pow(10.0, -16));
                    variables.add(-1.2536600497  * Math.pow(10.0, -18));
                    variables.add(2.1489217569  * Math.pow(10.0, -21));
                    variables.add(-1.4388041782  * Math.pow(10.0, -24));
                    variables.add(3.5960899481  * Math.pow(10.0, -28));
                }
                break;

            case TXK_L:
                if(fromT >= -200 && fromT < 800){
                    variables.add(-4.1626930 * Math.pow(10.0, -6));
                    variables.add(6.3310880 * Math.pow(10.0, -2));
                    variables.add(6.0118088  * Math.pow(10.0, -5));
                    variables.add(-7.9469796  * Math.pow(10.0, -8));
                    variables.add(9.3101891  * Math.pow(10.0, -11));
                    variables.add(-2.4299630   * Math.pow(10.0, -14));
                    variables.add(-2.6547176  * Math.pow(10.0, -16));
                    variables.add(4.4332477  * Math.pow(10.0, -19));
                    variables.add(-2.1172626 * Math.pow(10.0, -22));
                }
                break;

            case A1:
                if(fromT >= 0 && fromT < 2500){
                    variables.add(7.1564735  * Math.pow(10.0, -4));
                    variables.add(1.1951905 * Math.pow(10.0, -2));
                    variables.add(1.6672625   * Math.pow(10.0, -5));
                    variables.add(-2.8287807  * Math.pow(10.0, -8));
                    variables.add(2.8397839  * Math.pow(10.0, -11));
                    variables.add(-1.8505007  * Math.pow(10.0, -14));
                    variables.add(7.3632123   * Math.pow(10.0, -18));
                    variables.add(-1.6148878  * Math.pow(10.0, -21));
                    variables.add(1.4901679 * Math.pow(10.0, -25));
                }
                break;

            case A2:
                if(fromT >= 0 && fromT < 1800){
                    variables.add(-1.0850558  * Math.pow(10.0, -4));
                    variables.add(1.1642292 * Math.pow(10.0, -2));
                    variables.add(2.1280289   * Math.pow(10.0, -5));
                    variables.add(-4.4258402 * Math.pow(10.0, -8));
                    variables.add(5.5652058  * Math.pow(10.0, -11));
                    variables.add(-4.3801310  * Math.pow(10.0, -14));
                    variables.add(2.0228390   * Math.pow(10.0, -17));
                    variables.add(-4.9354041  * Math.pow(10.0, -21));
                    variables.add(4.8119846 * Math.pow(10.0, -25));
                }
                break;

            case A3:
                if(fromT >= 0 && fromT < 1800){
                    variables.add(-1.0649133  * Math.pow(10.0, -4));
                    variables.add(1.1686475 * Math.pow(10.0, -2));
                    variables.add(1.8022157  * Math.pow(10.0, -5));
                    variables.add(-3.3436998  * Math.pow(10.0, -8));
                    variables.add(3.7081688  * Math.pow(10.0, -11));
                    variables.add(-2.5748444  * Math.pow(10.0, -14));
                    variables.add(1.0301893   * Math.pow(10.0, -17));
                    variables.add(-2.0735944  * Math.pow(10.0, -21));
                    variables.add(1.4678450 * Math.pow(10.0, -25));
                }
                break;
        }
        int power = 0;
        double result = 0;
        for (Double var : variables){
            result += var * Math.pow(fromT, power++);
        }
        return result;
    }

    private double getTemperature(Type type, Double fromT){
        double a, b, c, d, a1, b1, c1, d1;

        switch (type) {
            case TMK_M:
                a1 = 2.02568E-6;
                b1 = 0.04264;
                c1 = 5.03527E-5;
                d1 = -4.9441E-8;
                break;
            case TJK_J:
                a1 = 0.34739;
                b1 = 0.04808;
                c1 = 1.52514E-5;
                d1 = -5.93024E-9;
                break;

            case TPP13_R:
                a1 = -0.02656;
                b1 = 0.00674;
                c1 = 4.91809E-6;
                d1 = -1.10375E-9;
                break;

            case TPP10_S:
                a1 = -0.0265;
                b1 = 0.00684;
                c1 = 3.64777E-6;
                d1 = -8.54455E-10;
                break;

            case TPR_B:
                a1 = 0.03346;
                b1 = -5.75046E-4;
                c1 = 6.4871E-6;
                d1 = -1.09397E-9;
                break;

            case TMK_T:
                a1 = -0.02123;
                b1 = 0.03847;
                c1 = 4.70201E-5;
                d1 = -3.22231E-8;
                break;

            case THH_N:
                a1 = 0.15603;
                b1 = 0.02522;
                c1 = 1.96738E-5;
                d1 = -8.63967E-9;
                break;

            case TXA_K:
                a1 = 0.45636;
                b1 = 0.0353;
                c1 = 1.31371E-5;
                d1 = -7.41683E-9;
                break;

            case TXKH_E:
                a1 = 0.28466;
                b1 = 0.0564;
                c1 = 4.80952E-5;
                d1 = -2.90413E-8;
                break;

            case TXK_L:
                a1 = 0.13355;
                b1 = 0.0623;
                c1 = 5.42674E-5;
                d1 = -3.6148E-8;
                break;

            case A1:
            case A2:
            case A3:
                a1 = -0.40884;
                b1 = 0.01686;
                c1 = 2.17244E-7;
                d1 = -6.14901E-10;
                break;

            default:
                a1 = b1 = c1 = d1 = 0;
        }

        return a1 + b1 * fromT + c1 * fromT * fromT + d1 * fromT * fromT * fromT;
    }
}
