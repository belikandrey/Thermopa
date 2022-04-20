package by.bsuir.thermopa.controller;

import by.bsuir.thermopa.entity.CalculatorResult;
import by.bsuir.thermopa.entity.thermocouple.Type;
import by.bsuir.thermopa.exception.InvalidDataException;
import by.bsuir.thermopa.strategy.ThermopaCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class ApplicationController {

    private ThermopaCalculator thermocoupleCalculator;
    private ThermopaCalculator resistanceThermometersCalculator;

    @Autowired
    public ApplicationController(@Qualifier("thermocoupleCalculator") ThermopaCalculator thermocoupleCalculator, @Qualifier("resistanceThermometersCalculator") ThermopaCalculator resistanceThermometersCalculator) {
        this.thermocoupleCalculator = thermocoupleCalculator;
        this.resistanceThermometersCalculator = resistanceThermometersCalculator;
    }

    @GetMapping
    public String getIndex() {
        return "home";
    }

    @GetMapping("/resistance-termometers")
    public String toResistanceTermometers() {
        return "resistance_termometers";
    }

    @GetMapping("/thermocouple")
    public String toThermocouple() {
        return "thermocouple";
    }

    @PostMapping("/calculate-thermocouple")
    public String calculateThermocouple(@RequestBody MultiValueMap<String, String> params, Model model) {
        try {
            final int type = Integer.parseInt(params.getFirst("type") != null ? params.getFirst("type") : "0");
            final double fromEds = Double.parseDouble(params.getFirst("fromEds") != null ? params.getFirst("fromEds") : "0");
            final double fromT = Double.parseDouble(params.getFirst("fromT") != null ? params.getFirst("fromT") : "0");
            final Type thermocoupleType = Type.values()[type];
            Map<String, Object> paramsToCalculator = new HashMap<>();
            paramsToCalculator.put("type", thermocoupleType);
            paramsToCalculator.put("fromEds", fromEds);
            paramsToCalculator.put("fromT", fromT);
            final CalculatorResult calculatorResult = thermocoupleCalculator.calculate(paramsToCalculator);
            model.addAttribute("toEds", String.format("%.5f", calculatorResult.getY()));
            model.addAttribute("toT", String.format("%.5f", calculatorResult.getX()));
            model.addAttribute("fromEds", fromEds);
            model.addAttribute("fromT", fromT);
            model.addAttribute("type", type);
        } catch (InvalidDataException e) {
            model.addAttribute("message", e.getMessage());
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Вводите только числа!");
        }
        return "thermocouple";
    }

    @PostMapping("/calculate-resistance-thermometers")
    public String calculateResistanceThermometers(@RequestBody MultiValueMap<String, String> params, Model model) {
        try {
            final int type = Integer.parseInt(params.getFirst("type") != null ? params.getFirst("type") : "0");
            final double fromT = Double.parseDouble(params.getFirst("fromT") != null ? params.getFirst("fromT") : "0");
            final double r0 = Double.parseDouble(params.getFirst("r0") != null ? params.getFirst("r0") : "0");
            final double fromRt = Double.parseDouble(params.getFirst("fromRt") != null ? params.getFirst("fromRt") : "0");

            final by.bsuir.thermopa.entity.resistancetermometers.Type resistanceType = by.bsuir.thermopa.entity.resistancetermometers.Type.values()[type];

            Map<String, Object> paramsToCalculator = new HashMap<>();

            paramsToCalculator.put("type", resistanceType);
            paramsToCalculator.put("zeroResist", r0);
            paramsToCalculator.put("fromT", fromT);
            paramsToCalculator.put("fromRt", fromRt);


            final CalculatorResult result = resistanceThermometersCalculator.calculate(paramsToCalculator);

            model.addAttribute("toT", String.format("%.5f", result.getX()));//SOP
            model.addAttribute("toRt", String.format("%.5f", result.getY()));//Tem
            model.addAttribute("type", type);
            model.addAttribute("fromT", fromT);
            model.addAttribute("r0", r0);
            model.addAttribute("fromRt", fromRt);
        } catch (InvalidDataException e) {
            model.addAttribute("message", e.getMessage());
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Вводите только числа!");
        }
        return "resistance_termometers";
    }

}
