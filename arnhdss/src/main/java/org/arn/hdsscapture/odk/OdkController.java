package org.arn.hdsscapture.odk;

import java.util.List;

import org.arn.hdsscapture.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/odk")
public class OdkController {
	
	OdkRepository repo;
	
	
	@Autowired
    public void setLevelRepo(OdkRepository repo) {
        this.repo = repo;
    }
	
	@GetMapping("")
	public DataWrapper<ODK> findAll() {

		List<ODK> data = repo.findEnabled();

		DataWrapper<ODK> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}


	
//	@PostMapping(consumes = "application/json", produces = "application/json")
//    public List<ODK> updateOdk(final @RequestBody List<OdkUpdate> list) {
//        List<ODK> toDelete = list.stream().filter(o -> o.getAction() == OdkUpdate.Action.DELETE)
//                .map(OdkUpdate::getData).collect(Collectors.toList());
//        List<ODK> toUpdate = list.stream().filter(o -> o.getAction() == OdkUpdate.Action.UPDATE)
//                .map(OdkUpdate::getData).collect(Collectors.toList());
//
//        List<ODK> result = new ArrayList<>();
//
//        if(!toDelete.isEmpty()){
//        	repo.deleteInBatch(toDelete);
//        }
//        if(!toUpdate.isEmpty()){
//        	 for (ODK odk : toUpdate) {
////                 if (odk.getInsertDate() == null) {
////                     odk.setInsertDate(new Date());
////                 }
//             }
//            result = repo.saveAll(toUpdate);
//        }
//
//        return result;
//    }
	


}
