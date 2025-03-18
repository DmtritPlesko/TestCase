package api.controller;

import api.dto.MealDto;
import api.dto.ReportDto;
import api.service.meal.MealService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/api/v1/meals")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MealController {

    final MealService service;

    @PostMapping
    public MealDto addMeal(@RequestBody MealDto mealDto) {
        return service.addMeal(mealDto);
    }

    @GetMapping(path = "/report/{userId}")
    public ReportDto createReport(@PathVariable("userId") Long id,
                                  @RequestParam("date")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return service.createReportByDay(id,date);
    }

    @GetMapping(path = "/{userId}")
    public ReportDto history(@PathVariable("userId") Long id) {

    }

}
