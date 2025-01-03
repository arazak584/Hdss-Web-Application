package org.arn.hdsscapture.projection;

import java.util.List;

import org.arn.hdsscapture.repository.PopulationRepository;
import org.arn.hdsscapture.views.Population;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pyramid")
public class PopController {
	
PopulationRepository repo;
	
	@Autowired
    public void setPopRepo(PopulationRepository repo) {
        this.repo = repo;
    }

	@GetMapping
    public List<Population> getPop() {
        return repo.pyramid();
    }
	
	
}
