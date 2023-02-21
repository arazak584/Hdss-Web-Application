package com.arn.hdss.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arn.hdss.entity.Codebook;
import com.arn.hdss.repository.CodebookRepository;
import com.arn.hdss.service.CodebookService;

@Service
public class CodebookServiceImpl implements CodebookService {
	
	private CodebookRepository codebookRepository;
	
	@Autowired
	public CodebookServiceImpl(CodebookRepository thecodebookRepository) {
		codebookRepository = thecodebookRepository;
	}

	@Override
	public List<Codebook> findAll() {
		return codebookRepository.findAll();
	}

	@Override
	public Codebook findById(Integer theId) {
		Optional<Codebook> result = codebookRepository.findById(theId);
		Codebook theCodebook = null;
        if(result.isPresent())
        	theCodebook = result.get();
        else
            throw new RuntimeException("Codebook id not found - " + theId);
        return theCodebook;
	}

	@Override
	public void save(Codebook theCodebook) {
		codebookRepository.save(theCodebook);

	}

	@Override
	public void deleteById(Integer theId) {
		codebookRepository.deleteById(theId);
		
	}

	

}
