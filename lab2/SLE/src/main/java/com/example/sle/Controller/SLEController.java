package com.example.sle.Controller;

import com.example.sle.Model.InitialParameters;
import com.example.sle.Service.SLEService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/sle")
public class SLEController {

    private double[] solutions;

    @PostMapping("/gauss")
    @Operation(description = "Find roots by gauss's method" , method = "gaussMethod")
    public Map<String, Double> gaussMethod(@RequestBody InitialParameters initialParameters) throws Exception {

        log.info(initialParameters.toString());
        double[] solutions = SLEService.gaussMethod(initialParameters.getA(), initialParameters.getB());
        return fromDoubleToMap(solutions);
    }

    @PostMapping("/runout")
    @Operation(description = "Find roots by runout method", method = "runoutMethod")
    public Map<String, Double> runoutMethod(@RequestBody InitialParameters initialParameters) throws Exception {
        log.info(initialParameters.toString());
        double[] solutions = SLEService.runoutMethod(initialParameters.getA(), initialParameters.getB());
        return fromDoubleToMap(solutions);
    }

    private Map<String, Double> fromDoubleToMap(double[] solutions) throws Exception {
        Map<String, Double> result = new HashMap<>();

        if (Objects.isNull(solutions)) {
            throw new Exception("Solutions weren't found");
        }

        for (int i = 0; i < solutions.length; i++) {
            result.put("x" + (i + 1), solutions[i]);
        }
        return result;
    }
}
