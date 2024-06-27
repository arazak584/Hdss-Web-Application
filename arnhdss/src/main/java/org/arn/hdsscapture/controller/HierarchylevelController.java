package org.arn.hdsscapture.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.arn.hdsscapture.CacheConfig;
import org.arn.hdsscapture.entity.Locationhierarchy;
import org.arn.hdsscapture.repository.FieldworkerRepository;
import org.arn.hdsscapture.repository.LocationhierarchyRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.arn.hdsscapture.utils.HierarchyUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/hierarchy")
public class HierarchylevelController {
	
	LocationhierarchyRepository repo;
	
	@Autowired
	FieldworkerRepository frepo;
	
	@Autowired
    public void setLevelRepo(LocationhierarchyRepository repo) {
        this.repo = repo;
    }
	
	@Autowired
	CacheConfig cacheManager;
	
	@GetMapping("/all")
	//@Cacheable(value = "hierarchy", key = "'hierarchy'", cacheManager = "cacheManager")
	public DataWrapper<Locationhierarchy> findAll() {

		List<Locationhierarchy> data = repo.findAll();

		DataWrapper<Locationhierarchy> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@GetMapping
    public List<Locationhierarchy> getAll() {
        return repo.subvillage();
    }

	
	@PostMapping(consumes = "application/json", produces = "application/json")
    public List<Locationhierarchy> updateHierarchy(final @RequestBody List<HierarchyUpdate> list) {
        List<Locationhierarchy> toDelete = list.stream().filter(o -> o.getAction() == HierarchyUpdate.Action.DELETE)
                .map(HierarchyUpdate::getData).collect(Collectors.toList());
        List<Locationhierarchy> toUpdate = list.stream().filter(o -> o.getAction() == HierarchyUpdate.Action.UPDATE)
                .map(HierarchyUpdate::getData).collect(Collectors.toList());

        List<Locationhierarchy> result = new ArrayList<>();

        if(!toDelete.isEmpty()){
        	repo.deleteInBatch(toDelete);
        }
        if(!toUpdate.isEmpty()){
            result = repo.saveAll(toUpdate);
        }

        return result;
    }
	


}
