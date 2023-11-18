package org.arn.hdsscapture.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.arn.hdsscapture.entity.Settings;
import org.arn.hdsscapture.repository.SettingsRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.arn.hdsscapture.utils.ParametersUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/settings")
public class SettingsController {
	
	SettingsRepository repo;
	
	@Autowired
    public void setSettingsRepo(SettingsRepository repo) {
        this.repo = repo;
    }
	
	@GetMapping("/parameter")
	public DataWrapper<Settings> findAll() {

		List<Settings> data = repo.findAll();

		DataWrapper<Settings> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	
	@GetMapping
    public List<Settings> getAll() {
        return repo.findAll();
    }
	
	@PostMapping(consumes = "application/json", produces = "application/json")
    public List<Settings> updateSettings(final @RequestBody List<ParametersUpdate> list) {
        List<Settings> toDelete = list.stream().filter(o -> o.getAction() == ParametersUpdate.Action.DELETE)
                .map(ParametersUpdate::getData).collect(Collectors.toList());
        List<Settings> toUpdate = list.stream().filter(o -> o.getAction() == ParametersUpdate.Action.UPDATE)
                .map(ParametersUpdate::getData).collect(Collectors.toList());

        List<Settings> result = new ArrayList<>();

        if(!toDelete.isEmpty()){
        	repo.deleteInBatch(toDelete);
        }
        if(!toUpdate.isEmpty()){
            result = repo.saveAll(toUpdate);
        }

        return result;
    }
	



}


