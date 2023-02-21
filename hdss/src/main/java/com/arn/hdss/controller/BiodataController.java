package com.arn.hdss.controller;

import java.util.List;

import com.arn.hdss.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arn.hdss.entity.Biodata;
import com.arn.hdss.repository.BiodataRepository;

@RestController
@RequestMapping("/api/biodata")
public class BiodataController {

	@Autowired
	private BiodataRepository repo;

	@GetMapping("")
	public DataWrapper<Biodata> findAll() {

		List<Biodata> data = repo.findAll();

		DataWrapper<Biodata> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}

	@PostMapping("")
	public DataWrapper<Biodata> saveAll(@RequestBody DataWrapper<Biodata> data) {

		List<Biodata> saved =  repo.saveAll(data.getData());

		DataWrapper<Biodata> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}

	@PostMapping("/save")
	public Biodata save(@RequestBody Biodata biodata) {
		return repo.save(biodata);
	}

	@GetMapping("/{id}")
	public Biodata find(@PathVariable("id") String mRecordid) {

		return repo.findById(mRecordid).orElseThrow(() -> new DataNotFoundException(Biodata.class.getSimpleName(), mRecordid));
	}

	@PostMapping("/{id}")
	public Biodata replace(@RequestBody Biodata newBiodata, @PathVariable("id") String mRecordid) {

		return repo.save(newBiodata);

	}

	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String mRecordid) {
		repo.deleteById(mRecordid);
	}
}
