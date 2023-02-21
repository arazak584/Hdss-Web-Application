package com.arn.hdss.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arn.hdss.entity.Demographic;
import com.arn.hdss.repository.DemographicRepository;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@RestController
@RequestMapping("/api/zip/demographic")
public class DemographicZipController {
	
	@Autowired
	DemographicRepository repo;
	
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<byte[]> downloadData() throws IOException {
	  // Retrieve data from database
	  List<Demographic> data = repo.findAll();

	  // Convert data to CSV
	  CsvSchema schema = CsvSchema.builder().addColumn("extId").addColumn("education").addColumn("fw").addColumn("insertDate")
			  .addColumn("marital").addColumn("occupation").addColumn("phone1").addColumn("phone2")
			  .addColumn("religion").addColumn("tribe").build();
	  CsvMapper csvMapper = new CsvMapper();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  StringWriter writer = new StringWriter();
	  csvMapper.writer(schema).with(formatter).writeValues(writer).writeAll(data);
	  String csv = writer.toString();

	  // Zip the CSV file
	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  ZipOutputStream zos = new ZipOutputStream(baos);
	  zos.putNextEntry(new ZipEntry("demographic.csv"));
	  zos.write(csv.getBytes());
	  zos.closeEntry();
	  zos.close();
	  
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	  headers.setContentDispositionFormData("attachment", "demographic.zip");

	  return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);

	}


}
