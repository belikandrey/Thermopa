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
        Double coldJunctionT = (Double) params.get("coldJunctionT");
        return new CalculatorResult(multiplyDependsOnColdJunction(coldJunctionT, getEds(type, fromT)), multiplyDependsOnColdJunction(coldJunctionT, getTemperature(type, fromEds)));
    }

    private double multiplyDependsOnColdJunction(double coldJunctionT, double valueToMultiply) {
        if (coldJunctionT < 5) {
            return valueToMultiply;
        }
        if (coldJunctionT >= 5 && coldJunctionT < 10) {
            return valueToMultiply * 1.02;
        }
        if (coldJunctionT >= 10 && coldJunctionT < 15) {
            return valueToMultiply * 1.04;
        }
        if (coldJunctionT >= 15 && coldJunctionT < 20) {
            return valueToMultiply * 1.06;
        }
        if (coldJunctionT >= 20 && coldJunctionT < 30) {
            return valueToMultiply;
        }
        if (coldJunctionT >= 30 && coldJunctionT < 40) {
            return valueToMultiply * 1.04;
        }
        if (coldJunctionT >= 40 && coldJunctionT < 45) {
            return valueToMultiply * 1.02;
        }
        if (coldJunctionT >= 45 && coldJunctionT < 50) {
            return valueToMultiply;
        }
        if (coldJunctionT >= 50 && coldJunctionT < 55) {
            return valueToMultiply * 0.96;
        }
        if (coldJunctionT >= 55 && coldJunctionT < 60) {
            return valueToMultiply * 0.9;
        }
        return valueToMultiply;
    }

    private double getEds(Type type, Double fromT) {
        List<Double> variables = new ArrayList<>();
        switch (type) {
            case TMK_M:
                if (fromT >= -200 && fromT < 100) {
                    variables.add(2.4455560 * Math.pow(10.0, -6));
                    variables.add(4.2638917 * Math.pow(10.0, -2));
                    variables.add(5.0348392 * Math.pow(10.0, -5));
                    variables.add(-4.4974485 * Math.pow(10.0, -8));
                }
                break;
            case TJK_J:
                if (fromT >= -210 && fromT < 760) {
                    variables.add(0.0);
                    variables.add(5.0381187815 * Math.pow(10.0, -2));
                    variables.add(3.0475836930 * Math.pow(10.0, -5));
                    variables.add(-8.5681065720 * Math.pow(10.0, -8));
                    variables.add(1.3228195295 * Math.pow(10.0, -10));
                    variables.add(-1.7052958337 * Math.pow(10.0, -13));
                    variables.add(2.0948090697 * Math.pow(10.0, -16));
                    variables.add(-1.2538395336 * Math.pow(10.0, -19));
                    variables.add(1.5631725697 * Math.pow(10.0, -23));
                } else if (fromT >= 760 && fromT < 1200) {
                    variables.add(2.9645625681 * Math.pow(10.0, 2));
                    variables.add(-1.4976127786);
                    variables.add(3.1787103924 * Math.pow(10.0, -3));
                    variables.add(-3.1847686701 * Math.pow(10.0, -6));
                    variables.add(1.5720819004 * Math.pow(10.0, -9));
                    variables.add(-3.0691369056 * Math.pow(10.0, -13));
                }
                break;

            case TPP13_R:
                if (fromT >= -50.0 && fromT < 1064.18) {
                    variables.add(0.0);
                    variables.add(5.28961729765 * Math.pow(10.0, -3));
                    variables.add(1.39166589782 * Math.pow(10.0, -5));
                    variables.add(-2.38855693017 * Math.pow(10.0, -8));
                    variables.add(3.56916001063 * Math.pow(10.0, -11));
                    variables.add(-4.62347666298 * Math.pow(10.0, -14));
                    variables.add(5.00777441034 * Math.pow(10.0, -17));
                    variables.add(-3.73105886191 * Math.pow(10.0, -20));
                    variables.add(1.57716482367 * Math.pow(10.0, -23));
                    variables.add(-2.81038625251 * Math.pow(10.0, -27));
                } else if (fromT >= 1064.18 && fromT < 1664.5) {
                    variables.add(2.95157925316);
                    variables.add(2.52061251332 * Math.pow(10.0, -3));
                    variables.add(1.59564501865 * Math.pow(10.0, -5));
                    variables.add(-7.64085947576 * Math.pow(10.0, -9));
                    variables.add(2.05305291024 * Math.pow(10.0, -12));
                    variables.add(-2.93359668173 * Math.pow(10.0, -16));
                } else if (fromT >= 1664.5 && fromT < 1768.1) {
                    variables.add(1.52232118209 * Math.pow(10.0, 2));
                    variables.add(-2.68819888545 * Math.pow(10.0, -1));
                    variables.add(1.71280280471 * Math.pow(10.0, -4));
                    variables.add(-3.45895706453 * Math.pow(10.0, -8));
                    variables.add(-9.34633971046 * Math.pow(10.0, -15));
                }
                break;

            case TPP10_S:
                if (fromT >= -50.0 && fromT < 1064.18) {
                    variables.add(0.0);
                    variables.add(5.40313308631 * Math.pow(10.0, -3));
                    variables.add(1.25934289740 * Math.pow(10.0, -5));
                    variables.add(-2.32477968689 * Math.pow(10.0, -8));
                    variables.add(3.22028823036 * Math.pow(10.0, -11));
                    variables.add(-3.31465196389 * Math.pow(10.0, -14));
                    variables.add(2.55744251786 * Math.pow(10.0, -17));
                    variables.add(-1.25068871393 * Math.pow(10.0, -20));
                    variables.add(2.71443176145 * Math.pow(10.0, -24));
                } else if (fromT >= 1064.18 && fromT < 1664.5) {
                    variables.add(1.32900444085);
                    variables.add(3.34509311344 * Math.pow(10.0, -3));
                    variables.add(6.54805192818 * Math.pow(10.0, -6));
                    variables.add(-1.64856259209 * Math.pow(10.0, -9));
                    variables.add(1.29989605174 * Math.pow(10.0, -14));
                } else if (fromT >= 1664.5 && fromT < 1768.1) {
                    variables.add(1.46628232636 * Math.pow(10.0, 2));
                    variables.add(-2.58430516752 * Math.pow(10.0, -1));
                    variables.add(1.63693574641 * Math.pow(10.0, -4));
                    variables.add(-3.30439046987 * Math.pow(10.0, -8));
                    variables.add(-9.43223690612 * Math.pow(10.0, -15));
                }
                break;

            case TPR_B:
                if (fromT >= 0.0 && fromT < 630.615) {
                    variables.add(0.0);
                    variables.add(-2.4650818346 * Math.pow(10.0, -4));
                    variables.add(5.9040421171 * Math.pow(10.0, -6));
                    variables.add(-1.3257931636 * Math.pow(10.0, -9));
                    variables.add(1.5668291901 * Math.pow(10.0, -12));
                    variables.add(-1.6944529240 * Math.pow(10.0, -15));
                    variables.add(6.2990347094 * Math.pow(10.0, -19));
                } else if (fromT >= 630.615 && fromT < 1820) {
                    variables.add(-3.8938168621);
                    variables.add(2.8571747470 * Math.pow(10.0, -2));
                    variables.add(-8.4885104785 * Math.pow(10.0, -5));
                    variables.add(1.5785280164 * Math.pow(10.0, -7));
                    variables.add(-1.6835344864 * Math.pow(10.0, -10));
                    variables.add(1.1109794013 * Math.pow(10.0, -13));
                    variables.add(-4.4515431033 * Math.pow(10.0, -17));
                    variables.add(9.8975640821 * Math.pow(10.0, -21));
                    variables.add(-9.3791330289 * Math.pow(10.0, -25));
                }
                break;

            case TMK_T:
                if (fromT >= -270 && fromT < 0.0) {
                    variables.add(0.0);
                    variables.add(3.8748106364 * Math.pow(10.0, -2));
                    variables.add(4.4194434347 * Math.pow(10.0, -5));
                    variables.add(1.1844323105 * Math.pow(10.0, -7));
                    variables.add(2.0032973554 * Math.pow(10.0, -8));
                    variables.add(9.0138019559 * Math.pow(10.0, -10));
                    variables.add(2.2651156593 * Math.pow(10.0, -11));
                    variables.add(3.6071154205 * Math.pow(10.0, -13));
                    variables.add(3.8493939883 * Math.pow(10.0, -15));
                    variables.add(2.8213521925 * Math.pow(10.0, -17));
                    variables.add(1.4251594779 * Math.pow(10.0, -19));
                    variables.add(4.8768662286 * Math.pow(10.0, -22));
                    variables.add(1.0795539270 * Math.pow(10.0, -24));
                    variables.add(1.3945027062 * Math.pow(10.0, -27));
                    variables.add(7.9795153927 * Math.pow(10.0, -31));
                } else if (fromT >= 0.0 && fromT < 400) {
                    variables.add(0.0);
                    variables.add(3.8748106364 * Math.pow(10.0, -2));
                    variables.add(3.3292227880 * Math.pow(10.0, -5));
                    variables.add(2.0618243404 * Math.pow(10.0, -7));
                    variables.add(-2.1882256846 * Math.pow(10.0, -9));
                    variables.add(1.0996880928 * Math.pow(10.0, -11));
                    variables.add(-3.0815758772 * Math.pow(10.0, -14));
                    variables.add(4.5479135290 * Math.pow(10.0, -17));
                    variables.add(-2.7512901673 * Math.pow(10.0, -20));
                }
                break;

            case THH_N:
                if (fromT >= -270 && fromT < 0.0) {
                    variables.add(0.0);
                    variables.add(2.6159105962 * Math.pow(10.0, -2));
                    variables.add(1.0957484228 * Math.pow(10.0, -5));
                    variables.add(-9.3841111554 * Math.pow(10.0, -8));
                    variables.add(-4.6412039759 * Math.pow(10.0, -11));
                    variables.add(-2.6303357716 * Math.pow(10.0, -12));
                    variables.add(-2.2653438003 * Math.pow(10.0, -14));
                    variables.add(-7.6089300791 * Math.pow(10.0, -17));
                    variables.add(-9.3419667835 * Math.pow(10.0, -20));
                } else if (fromT >= 0.0 && fromT < 1300) {
                    variables.add(0.0);
                    variables.add(2.5929394601 * Math.pow(10.0, -2));
                    variables.add(1.5710141880 * Math.pow(10.0, -5));
                    variables.add(4.3825627237 * Math.pow(10.0, -8));
                    variables.add(-2.5261169794 * Math.pow(10.0, -10));
                    variables.add(6.4311819339 * Math.pow(10.0, -13));
                    variables.add(-1.0063471519 * Math.pow(10.0, -15));
                    variables.add(9.9745338992 * Math.pow(10.0, -19));
                    variables.add(-6.0863245607 * Math.pow(10.0, -22));
                    variables.add(2.0849229339 * Math.pow(10.0, -25));
                    variables.add(-3.0682196151 * Math.pow(10.0, -29));
                }
                break;

            case TXA_K:
                if (fromT >= -270 && fromT < 0.0) {
                    variables.add(0.0);
                    variables.add(3.9450128025 * Math.pow(10.0, -2));
                    variables.add(2.3622373598 * Math.pow(10.0, -5));
                    variables.add(-3.2858906784 * Math.pow(10.0, -7));
                    variables.add(-4.9904828777 * Math.pow(10.0, -9));
                    variables.add(-6.7509059173 * Math.pow(10.0, -11));
                    variables.add(-5.7410327428 * Math.pow(10.0, -13));
                    variables.add(-3.1088872894 * Math.pow(10.0, -15));
                    variables.add(-1.0451609365 * Math.pow(10.0, -17));
                    variables.add(-1.9889266878 * Math.pow(10.0, -20));
                    variables.add(-1.6322697486 * Math.pow(10.0, -23));
                } else if (fromT >= 0.0 && fromT < 1372) {
                    variables.add(-1.7600413686 * Math.pow(10.0, -2));
                    variables.add(3.8921204975 * Math.pow(10.0, -2));
                    variables.add(1.8558770032 * Math.pow(10.0, -5));
                    variables.add(-9.9457592874 * Math.pow(10.0, -8));
                    variables.add(3.1840945719 * Math.pow(10.0, -10));
                    variables.add(-5.6072844889 * Math.pow(10.0, -13));
                    variables.add(5.6075059059 * Math.pow(10.0, -16));
                    variables.add(-3.2020720003 * Math.pow(10.0, -19));
                    variables.add(9.7151147152 * Math.pow(10.0, -23));
                    variables.add(-1.2104721275 * Math.pow(10.0, -26));
                }
                break;

            case TXKH_E:
                if (fromT >= -270 && fromT < 0.0) {
                    variables.add(0.0);
                    variables.add(5.8665508708 * Math.pow(10.0, -2));
                    variables.add(4.5410977124 * Math.pow(10.0, -5));
                    variables.add(-7.7998048686 * Math.pow(10.0, -7));
                    variables.add(-2.5800160843 * Math.pow(10.0, -8));
                    variables.add(-5.9452583057 * Math.pow(10.0, -10));
                    variables.add(-9.3214058667 * Math.pow(10.0, -12));
                    variables.add(-1.0287605534 * Math.pow(10.0, -13));
                    variables.add(-8.0370123621 * Math.pow(10.0, -16));
                    variables.add(-4.3979497391 * Math.pow(10.0, -18));
                    variables.add(-1.6414776355 * Math.pow(10.0, -20));
                    variables.add(-3.9673619516 * Math.pow(10.0, -23));
                    variables.add(-5.5827328721 * Math.pow(10.0, -26));
                    variables.add(-3.4657842013 * Math.pow(10.0, -29));
                } else if (fromT >= 0.0 && fromT < 1000) {
                    variables.add(0.0);
                    variables.add(5.8665508710 * Math.pow(10.0, -2));
                    variables.add(4.5032275582 * Math.pow(10.0, -5));
                    variables.add(2.8908407212 * Math.pow(10.0, -8));
                    variables.add(-3.3056896652 * Math.pow(10.0, -10));
                    variables.add(6.5024403270 * Math.pow(10.0, -13));
                    variables.add(-1.9197495504 * Math.pow(10.0, -16));
                    variables.add(-1.2536600497 * Math.pow(10.0, -18));
                    variables.add(2.1489217569 * Math.pow(10.0, -21));
                    variables.add(-1.4388041782 * Math.pow(10.0, -24));
                    variables.add(3.5960899481 * Math.pow(10.0, -28));
                }
                break;

            case TXK_L:
                if (fromT >= -200 && fromT < 800) {
                    variables.add(-4.1626930 * Math.pow(10.0, -6));
                    variables.add(6.3310880 * Math.pow(10.0, -2));
                    variables.add(6.0118088 * Math.pow(10.0, -5));
                    variables.add(-7.9469796 * Math.pow(10.0, -8));
                    variables.add(9.3101891 * Math.pow(10.0, -11));
                    variables.add(-2.4299630 * Math.pow(10.0, -14));
                    variables.add(-2.6547176 * Math.pow(10.0, -16));
                    variables.add(4.4332477 * Math.pow(10.0, -19));
                    variables.add(-2.1172626 * Math.pow(10.0, -22));
                }
                break;

            case A1:
                if (fromT >= 0 && fromT < 2500) {
                    variables.add(7.1564735 * Math.pow(10.0, -4));
                    variables.add(1.1951905 * Math.pow(10.0, -2));
                    variables.add(1.6672625 * Math.pow(10.0, -5));
                    variables.add(-2.8287807 * Math.pow(10.0, -8));
                    variables.add(2.8397839 * Math.pow(10.0, -11));
                    variables.add(-1.8505007 * Math.pow(10.0, -14));
                    variables.add(7.3632123 * Math.pow(10.0, -18));
                    variables.add(-1.6148878 * Math.pow(10.0, -21));
                    variables.add(1.4901679 * Math.pow(10.0, -25));
                }
                break;

            case A2:
                if (fromT >= 0 && fromT < 1800) {
                    variables.add(-1.0850558 * Math.pow(10.0, -4));
                    variables.add(1.1642292 * Math.pow(10.0, -2));
                    variables.add(2.1280289 * Math.pow(10.0, -5));
                    variables.add(-4.4258402 * Math.pow(10.0, -8));
                    variables.add(5.5652058 * Math.pow(10.0, -11));
                    variables.add(-4.3801310 * Math.pow(10.0, -14));
                    variables.add(2.0228390 * Math.pow(10.0, -17));
                    variables.add(-4.9354041 * Math.pow(10.0, -21));
                    variables.add(4.8119846 * Math.pow(10.0, -25));
                }
                break;

            case A3:
                if (fromT >= 0 && fromT < 1800) {
                    variables.add(-1.0649133 * Math.pow(10.0, -4));
                    variables.add(1.1686475 * Math.pow(10.0, -2));
                    variables.add(1.8022157 * Math.pow(10.0, -5));
                    variables.add(-3.3436998 * Math.pow(10.0, -8));
                    variables.add(3.7081688 * Math.pow(10.0, -11));
                    variables.add(-2.5748444 * Math.pow(10.0, -14));
                    variables.add(1.0301893 * Math.pow(10.0, -17));
                    variables.add(-2.0735944 * Math.pow(10.0, -21));
                    variables.add(1.4678450 * Math.pow(10.0, -25));
                }
                break;
        }
        int power = 0;
        double result = 0;
        for (Double var : variables) {
            result += var * Math.pow(fromT, power++);
        }
        return result;//HERE WE FOUND "E"
    }

    private double getTemperature(Type type, Double fromE) {
        List<Double> variables = new ArrayList<>();
        switch (type) {
            case TMK_M:
                if (fromE >= -6.154 && fromE < 4.722) {
                    variables.add(0.4548090);
                    variables.add(2.2657698 * Math.pow(10.0, -2));
                    variables.add(-7.7935652 * Math.pow(10.0, -7));
                    variables.add(1.1786931 * Math.pow(10.0, -10));
                }
                break;
            case TJK_J:
                if (fromE >= -8.095 && fromE < 0) {
                    variables.add(0.0);
                    variables.add(1.9528268 * Math.pow(10.0, 1));
                    variables.add(-1.2286185);
                    variables.add(-1.0752178);
                    variables.add(-5.9086933 * Math.pow(10.0, -1));
                    variables.add(-1.7256713 * Math.pow(10.0, -1));
                    variables.add(-2.8131513 * Math.pow(10.0, -2));
                    variables.add(-2.3963370 * Math.pow(10.0, -3));
                    variables.add(-8.3823321 * Math.pow(10.0, -5));
                } else if (fromE >= 0 && fromE < 42.919) {
                    variables.add(0.0);
                    variables.add(1.978425 * Math.pow(10.0, 1));
                    variables.add(-2.001204 * Math.pow(10.0, -1));
                    variables.add(1.036969 * Math.pow(10.0, -2));
                    variables.add(-2.549687 * Math.pow(10.0, -4));
                    variables.add(3.585153 * Math.pow(10.0, -6));
                    variables.add(-5.344285 * Math.pow(10.0, -8));
                    variables.add(5.099890 * Math.pow(10.0, -10));
                } else if (fromE >= 42.919 && fromE < 69.553) {
                    variables.add(-3.11358187 * Math.pow(10.0, 3));
                    variables.add(3.00543684 * Math.pow(10.0, 2));
                    variables.add(-9.94773230);
                    variables.add(1.70276630 * Math.pow(10.0, -1));
                    variables.add(-1.43033468 * Math.pow(10.0, -3));
                    variables.add(4.73886084 * Math.pow(10.0, -6));
                }
                break;

            case TPP13_R:
                if (fromE >= -0.226 && fromE < 1.923) {
                    variables.add(0.0);
                    variables.add(1.8891380 * Math.pow(10.0, 2));
                    variables.add(-9.3835290 * Math.pow(10.0, 1));
                    variables.add(1.3068619 * Math.pow(10.0, 2));
                    variables.add(-2.2703580 * Math.pow(10.0, 2));
                    variables.add(3.5145659 * Math.pow(10.0, 2));
                    variables.add(-3.8953900 * Math.pow(10.0, 2));
                    variables.add(2.8239471 * Math.pow(10.0, 2));
                    variables.add(-1.2607281 * Math.pow(10.0, 2));
                    variables.add(3.1353611 * Math.pow(10.0, 1));
                    variables.add(-3.3187769);
                } else if (fromE >= 1.923 && fromE < 11.361) {
                    variables.add(1.334584505 * 10);
                    variables.add(1.472644573 * Math.pow(10.0, 2));
                    variables.add(-1.844024844 * Math.pow(10.0, 1));
                    variables.add(4.031129726);
                    variables.add(-6.249428360 * Math.pow(10.0, -1));
                    variables.add(6.468412046 * Math.pow(10.0, -2));
                    variables.add(-4.458750426 * Math.pow(10.0, -3));
                    variables.add(1.994710149 * Math.pow(10.0, -4));
                    variables.add(-5.313401790 * Math.pow(10.0, -6));
                    variables.add(6.481976217 * Math.pow(10.0, -8));
                } else if (fromE >= 11.361 && fromE < 19.739) {
                    variables.add(-8.199599416 * Math.pow(10.0, 1));
                    variables.add(1.553962042 * Math.pow(10.0, 2));
                    variables.add(-8.342197663);
                    variables.add(4.279433549 * Math.pow(10.0, -1));
                    variables.add(-1.191577910 * Math.pow(10.0, -2));
                    variables.add(1.492290091 * Math.pow(10.0, -4));
                } else if (fromE >= 19.739 && fromE < 21.103) {
                    variables.add(3.406177836 * Math.pow(10.0, 4));
                    variables.add(-7.023729171 * Math.pow(10.0, 3));
                    variables.add(5.582903813 * Math.pow(10.0, 2));
                    variables.add(-1.952394635 * 10);
                    variables.add(2.560740231 * Math.pow(10.0, -1));
                }
                break;

            case TPP10_S:
                if (fromE >= -0.235 && fromE < 1.874) {
                    variables.add(0.0);
                    variables.add(1.84949460 * Math.pow(10.0, 2));
                    variables.add(-8.00504062 * Math.pow(10.0, 1));
                    variables.add(1.02237430 * Math.pow(10.0, 2));
                    variables.add(-1.52248592 * Math.pow(10.0, 2));
                    variables.add(1.88821343 * Math.pow(10.0, 2));
                    variables.add(-1.59085941 * Math.pow(10.0, 2));
                    variables.add(8.23027880 * Math.pow(10.0, 1));
                    variables.add(-2.34181944 * Math.pow(10.0, 1));
                    variables.add(2.79786260);
                } else if (fromE >= 1.874 && fromE < 10.332) {
                    variables.add(1.291507177 * 10);
                    variables.add(1.466298863 * Math.pow(10.0, 2));
                    variables.add(-1.534713402 * Math.pow(10.0, 1));
                    variables.add(3.145945973);
                    variables.add(-4.163257839 * Math.pow(10.0, -1));
                    variables.add(3.187963771 * Math.pow(10.0, -2));
                    variables.add(-1.291637500 * Math.pow(10.0, -3));
                    variables.add(2.183475087 * Math.pow(10.0, -5));
                    variables.add(-1.447379511 * Math.pow(10.0, -7));
                    variables.add(8.211272125 * Math.pow(10.0, -9));
                } else if (fromE >= 10.332 && fromE < 17.536) {
                    variables.add(-8.087801117 * Math.pow(10.0, 1));
                    variables.add(1.621573104 * Math.pow(10.0, 2));
                    variables.add(-8.536869453);
                    variables.add(4.719686976 * Math.pow(10.0, -1));
                    variables.add(-1.441693666 * Math.pow(10.0, -2));
                    variables.add(2.081618890 * Math.pow(10.0, -4));
                } else if (fromE >= 17.536 && fromE < 18.694) {
                    variables.add(5.333875126 * Math.pow(10.0, 4));
                    variables.add(-1.235892298 * Math.pow(10.0, 4));
                    variables.add(1.092657613 * Math.pow(10.0, 3));
                    variables.add(-4.265693686 * Math.pow(10.0, 1));
                    variables.add(6.247205420 * Math.pow(10.0, -1));
                }
                break;

            case TPR_B:
                if (fromE >= 0.291 && fromE < 2.431) {
                    variables.add(9.8423321 * 10);
                    variables.add(6.9971500 * Math.pow(10.0, 2));
                    variables.add(-8.4765304 * Math.pow(10.0, 2));
                    variables.add(1.0052644 * Math.pow(10.0, 3));
                    variables.add(-8.3345952 * Math.pow(10.0, 2));
                    variables.add(4.5508542 * Math.pow(10.0, 2));
                    variables.add(-1.5523037 * Math.pow(10.0, 2));
                    variables.add(2.9886750 * Math.pow(10.0, 1));
                    variables.add(-2.4742860);
                } else if (fromE >= 2.431 && fromE < 13.820) {
                    variables.add(2.1315071 * 10 * 10);
                    variables.add(2.8510504 * Math.pow(10.0, 2));
                    variables.add(-5.2742887 * Math.pow(10.0, 1));
                    variables.add(9.9160804);
                    variables.add(-1.2965303);
                    variables.add(1.1195870 * Math.pow(10.0, -1));
                    variables.add(-6.0625199 * Math.pow(10.0, -3));
                    variables.add(1.8661696 * Math.pow(10.0, -4));
                    variables.add(-2.4878585 * Math.pow(10.0, -6));
                }
                break;

            case TMK_T:
                if (fromE >= -5.603 && fromE < 0.0) {
                    variables.add(0.0);
                    variables.add(2.5949192 * Math.pow(10.0, 1));
                    variables.add(-2.1316967 * Math.pow(10.0, -1));
                    variables.add(7.9018692 * Math.pow(10.0, -1));
                    variables.add(4.2527777 * Math.pow(10.0, -1));
                    variables.add(1.3304473 * Math.pow(10.0, -1));
                    variables.add(2.0241446 * Math.pow(10.0, -2));
                    variables.add(1.2668171 * Math.pow(10.0, -3));
                } else if (fromE >= 0.0 && fromE < 20.872) {
                    variables.add(0.0);
                    variables.add(2.592800 * Math.pow(10.0, 1));
                    variables.add(-7.602961 * Math.pow(10.0, -1));
                    variables.add(4.637791 * Math.pow(10.0, -2));
                    variables.add(-2.165394 * Math.pow(10.0, -3));
                    variables.add(6.048144 * Math.pow(10.0, -5));
                    variables.add(-7.293422 * Math.pow(10.0, -7));
                }
                break;

            case THH_N:
                if (fromE >= -3.990 && fromE < 0) {
                    variables.add(0.0);
                    variables.add(3.8436847 * Math.pow(10.0, 1));
                    variables.add(1.1010485);
                    variables.add(5.2229312);
                    variables.add(7.2060525);
                    variables.add(5.8488586);
                    variables.add(2.7754916);
                    variables.add(7.7075166 * Math.pow(10.0, -1));
                    variables.add(1.1582665 * Math.pow(10.0, -1));
                    variables.add(7.3138868 * Math.pow(10.0, -3));
                } else if (fromE >= 0 && fromE < 20.613) {
                    variables.add(0.0);
                    variables.add(3.86896 * 10);
                    variables.add(-1.08267);
                    variables.add(4.70205 * Math.pow(10.0, -2));
                    variables.add(-2.12169 * Math.pow(10.0, -6));
                    variables.add(-1.17272 * Math.pow(10.0, -4));
                    variables.add(5.39280 * Math.pow(10.0, -6));
                    variables.add(-7.98156 * Math.pow(10.0, -8));
                } else if (fromE >= 20.613 && fromE < 47.513) {
                    variables.add(1.972485 * Math.pow(10.0, 1));
                    variables.add(3.300943 * Math.pow(10.0, 1));
                    variables.add(-3.915159 * Math.pow(10.0, -1));
                    variables.add(9.855391 * Math.pow(10.0, -3));
                    variables.add(-1.274371 * Math.pow(10.0, -4));
                    variables.add(7.767022 * Math.pow(10.0, -7));
                }
                break;

            case TXA_K:
                if (fromE >= -5.891 && fromE < 0) {
                    variables.add(0.0);
                    variables.add(2.5173462 * Math.pow(10.0, 1));
                    variables.add(-1.1662878);
                    variables.add(-1.0833638);
                    variables.add(-8.9773540 * Math.pow(10.0, -1));
                    variables.add(-3.7342377 * Math.pow(10.0, -1));
                    variables.add(-8.6632643 * Math.pow(10.0, -2));
                    variables.add(-1.0450598 * Math.pow(10.0, -2));
                    variables.add(-5.1920577 * Math.pow(10.0, -4));
                } else if (fromE >= 0 && fromE < 20.644) {
                    variables.add(0.0);
                    variables.add(2.508355 * 10);
                    variables.add(7.860106 * Math.pow(10.0, -2));
                    variables.add(-2.503131 * Math.pow(10.0, -1));
                    variables.add(8.315270 * Math.pow(10.0, -2));
                    variables.add(-1.228034 * Math.pow(10.0, -2));
                    variables.add(9.804036 * Math.pow(10.0, -4));
                    variables.add(-4.413030 * Math.pow(10.0, -5));
                    variables.add(1.057734 * Math.pow(10.0, -6));
                    variables.add(-1.052755 * Math.pow(10.0, -8));
                } else if (fromE >= 20.644 && fromE < 54.886) {
                    variables.add(-1.318058 * Math.pow(10.0, 2));
                    variables.add(4.830222 * Math.pow(10.0, 1));
                    variables.add(-1.646031);
                    variables.add(5.464731 * Math.pow(10.0, -2));
                    variables.add(-9.650715 * Math.pow(10.0, -4));
                    variables.add(8.802193 * Math.pow(10.0, -6));
                    variables.add(-3.110810 * Math.pow(10.0, -8));
                }
                break;

            case TXKH_E:
                if (fromE >= -8.825 && fromE < 0.0) {
                    variables.add(0.0);
                    variables.add(1.6977288 * Math.pow(10.0, 1));
                    variables.add(-4.3514970 * Math.pow(10.0, -1));
                    variables.add(-1.5859697 * Math.pow(10.0, -1));
                    variables.add(-9.2502871 * Math.pow(10.0, -2));
                    variables.add(-2.6084314 * Math.pow(10.0, -2));
                    variables.add(-4.1360199 * Math.pow(10.0, -3));
                    variables.add(-3.4034030 * Math.pow(10.0, -4));
                    variables.add(-1.1564890 * Math.pow(10.0, -5));
                } else if (fromE >= 0.0 && fromE < 76.373) {
                    variables.add(0.0);
                    variables.add(1.7057035 * Math.pow(10.0, 1));
                    variables.add(-2.3301759 * Math.pow(10.0, -1));
                    variables.add(6.5435585 * Math.pow(10.0, -3));
                    variables.add(-7.3562749 * Math.pow(10.0, -5));
                    variables.add(-1.7896001 * Math.pow(10.0, -6));
                    variables.add(8.4036165 * Math.pow(10.0, -8));
                    variables.add(-1.3735879 * Math.pow(10.0, -9));
                    variables.add(1.0629823 * Math.pow(10.0, -11));
                    variables.add(-3.2447087 * Math.pow(10.0, -14));

                }
                break;

            case TXK_L:
                if (fromE >= -9.488 && fromE < 66.466) {
                    variables.add(3.1116085 * Math.pow(10.0, -2));
                    variables.add(1.5632542 * Math.pow(10.0, 1));
                    variables.add(-0.2281310);
                    variables.add(1.6061658 * Math.pow(10.0, -2));
                    variables.add(-1.2036818 * Math.pow(10.0, -3));
                    variables.add(5.7602230 * Math.pow(10.0, -5));
                    variables.add(-1.6144584 * Math.pow(10.0, -6));
                    variables.add(2.5988757 * Math.pow(10.0, -8));
                    variables.add(-2.2286755 * Math.pow(10.0, -10));
                    variables.add(7.8910747 * Math.pow(10.0, -13));
                }
                break;

            case A1:
                if (fromE >= 0 && fromE < 33.640) {
                    variables.add(0.9643027);
                    variables.add(7.9495086 * Math.pow(10.0, 1));
                    variables.add(-4.9990310);
                    variables.add(0.6341776);
                    variables.add(-4.7440967 * Math.pow(10.0, -2));
                    variables.add(2.1811337 * Math.pow(10.0, -3));
                    variables.add(-5.8324228 * Math.pow(10.0, -5));
                    variables.add(8.2433725 * Math.pow(10.0, -7));
                    variables.add(-4.5928480 * Math.pow(10.0, -9));
                }
                break;

            case A2:
                if (fromE >= 0 && fromE < 27.232) {
                    variables.add(1.1196428);
                    variables.add(8.0569397 * Math.pow(10.0, 1));
                    variables.add(-6.2279122);
                    variables.add(0.9337015);
                    variables.add(-8.2608051 * Math.pow(10.0, -2));
                    variables.add(4.4110979 * Math.pow(10.0, -3));
                    variables.add(-1.3610551 * Math.pow(10.0, -4));
                    variables.add(2.2183851 * Math.pow(10.0, -6));
                    variables.add(-1.4527698 * Math.pow(10.0, -8));
                }
                break;

            case A3:
                if (fromE >= 0 && fromE < 26.773) {
                    variables.add(0.8769216);
                    variables.add(8.1483231 * Math.pow(10.0, 1));
                    variables.add(-5.9344173);
                    variables.add(0.8699340);
                    variables.add(-7.6797687 * Math.pow(10.0, -2));
                    variables.add(4.1814387 * Math.pow(10.0, -3));
                    variables.add(-1.3439670 * Math.pow(10.0, -4));
                    variables.add(2.342409 * Math.pow(10.0, -6));
                    variables.add(-1.6988727 * Math.pow(10.0, -8));
                }
                break;
        }
        int power = 0;
        double result = 0;
        for (Double var : variables) {
            result += var * Math.pow(fromE, power++);
        }
        return result;//HERE WE FOUND "T"
    }
}
