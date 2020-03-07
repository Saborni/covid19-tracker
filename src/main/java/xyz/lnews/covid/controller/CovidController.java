package xyz.lnews.covid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import xyz.lnews.covid.model.LocStats;
import xyz.lnews.covid.service.CovidServiceImpl;

import java.util.List;

@Controller
public class CovidController {

    @Autowired
    CovidServiceImpl covidService;

    @GetMapping("/")
    public String homePage(Model model){
        List<LocStats> allStats = covidService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(s->s.getLatestTotalCases()).sum();
        int totalLatestCases = allStats.stream().mapToInt(s->s.getDiffFromPrevDay()).sum();
        model.addAttribute("title","Covid-19 Tracker Tool");
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalLatestCases",totalLatestCases);
        model.addAttribute("locStats",allStats);

        return "homepage";
    }

}
