package org.arn.hdsscapture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rp")
public class ReportController {
	
//	private final VisitRepository visitRepository;
//
//    public ReportController(VisitRepository visitRepository) {
//        this.visitRepository = visitRepository;
//    }
//
//    @GetMapping("/visit")
//    public String generateReport(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//                                 @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
//                                 Model model) {
//
//        List<Object[]> reportData = visitRepository.generateAggregateReport(startDate, endDate);
//        
//        System.err.println("Size of list " + reportData.size());
//        model.addAttribute("reportData", reportData);
//
//        return "report/visit"; // Return the Thymeleaf template for rendering the report
//    }

}

