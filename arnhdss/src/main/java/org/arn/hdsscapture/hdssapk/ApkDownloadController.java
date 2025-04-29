package org.arn.hdsscapture.hdssapk;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ApkDownloadController {

	private static final Path APK_PATH = Paths.get("apk_folder/app-debug.apk");

	@GetMapping("/download/apk")
	public ResponseEntity<Resource> downloadApk() throws MalformedURLException {
		if (!APK_PATH.toFile().exists()) {
			return ResponseEntity.notFound().build();
		}

		Resource resource = new UrlResource(APK_PATH.toUri());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app-debug.apk")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
}
