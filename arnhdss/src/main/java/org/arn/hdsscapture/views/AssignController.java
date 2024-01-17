package org.arn.hdsscapture.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.arn.hdsscapture.entity.Fieldworker;
import org.arn.hdsscapture.entity.Locationhierarchy;
import org.arn.hdsscapture.repository.FieldworkerRepository;
import org.arn.hdsscapture.repository.LocationhierarchyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/assign")
public class AssignController {

	
	LocationhierarchyRepository  loc;
	
	@Autowired
    public AssignController(LocationhierarchyRepository loc) {
        this.loc = loc;
    }
	
	@Autowired
	FieldworkerRepository fwrepo;
	
	@GetMapping("")
	public String activehoh(Model model) {

		List<Locationhierarchy> items = loc.villages();
		//System.out.println("Villages: " + villages);
		model.addAttribute("items", items);

	    return "report/assign";
	}
	
	@Transactional
	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
	    if (file.isEmpty()) {
	        model.addAttribute("message", "Please select a file to upload.");
	        return "redirect:/file/assign";
	    }

	    try (InputStream inputStream = file.getInputStream()) {
	        // Use Apache POI to read the Excel file
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

	        List<Locationhierarchy> updatedItems = new ArrayList<>(); // Create a list to hold updated items

	        // Assuming column names are in the first row
	        Row headerRow = sheet.getRow(0);

	        // Find column indexes based on column names
	        int extIdColumnIndex = findColumnIndex(headerRow, "extId");
	        int fwNameColumnIndex = findColumnIndex(headerRow, "fw_name");

	        if (extIdColumnIndex != -1 && fwNameColumnIndex != -1) {
	        	for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	        	    Row row = sheet.getRow(rowIndex);

	        	    Cell extIdCell = row.getCell(extIdColumnIndex);
	        	    Cell fwNameCell = row.getCell(fwNameColumnIndex);

	        	    String extId = extIdCell != null ? extIdCell.getStringCellValue() : "";
	        	    String newFwname = fwNameCell != null ? fwNameCell.getStringCellValue() : "";

	        	    // Check if the fw_name exists in the related entity
	        	    List<Fieldworker> relatedEntity = fwrepo.findFieldworkerByUsername(newFwname);

	        	    if (!relatedEntity.isEmpty()) {
	        	        Optional<Locationhierarchy> optionalItem = loc.findByExtId(extId);

	        	        optionalItem.ifPresent(item -> {
	        	            // Update the fw_name field
	        	            item.setFw_name(newFwname);
	        	            updatedItems.add(item); // Add the updated item to the list
	        	        });
	        	    } else {
	        	        // Handle the case where fw_name is not found in the related entity
	        	    	System.out.println("Fwname-Error: " + newFwname);
	        	        model.addAttribute("error", "Error: '" + newFwname + "' not a Registered Fieldworker.");
	        	        //return "redirect:/file/assign"; 
	        	        return "report/assign";
	        	    }
	        	}

	            // Save the updated Locationhierarchy items
	            loc.saveAll(updatedItems);

	            model.addAttribute("message", "File uploaded and fw_names updated successfully.");
	        } else {
	            model.addAttribute("message", "Column names 'extId' and 'fw_name' not found in the Excel file.");
	        }
	    } catch (IOException e) {
	        model.addAttribute("message", "Error occurred while processing the file.");
	        e.printStackTrace();
	    }

	    return "redirect:/file/assign";
	}

	private int findColumnIndex(Row headerRow, String columnName) {
	    for (int i = 0; i < headerRow.getLastCellNum(); i++) {
	        Cell cell = headerRow.getCell(i);
	        if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
	            return i;
	        }
	    }
	    return -1;
	}
}
