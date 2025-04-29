package org.arn.hdsscapture.hdssapk;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Component
public class ApkDownloader {

	private static final String GITHUB_API_URL = "https://api.github.com/repos/arn-techsystem/openAB/releases/latest";
	private static final String APK_DIR = "apk_folder";
	private static final String APK_FILE_NAME = "app-debug.apk";

	// @Scheduled(fixedRate = 60 * 60 * 1000) // Every hour
	//@Scheduled(fixedRate = 60 * 10 * 1000) // Every 10 minutes
	//@Scheduled(cron = "0 0/10 * * * ?") // Every 10 minutes
	// Schedule the task to run daily at 12:00 AM and 12:00 PM
    @Scheduled(cron = "0 0 0,12 * * ?")
	@Transactional
	public void downloadLatestApk() {
		System.out.println("[APK Downloader] Task started...");

		try {
			// Step 1: Ensure apk_folder exists
			Path apkDirPath = Paths.get(APK_DIR);
			if (!Files.exists(apkDirPath)) {
				Files.createDirectories(apkDirPath);
				System.out.println("[APK Downloader] Created directory: " + apkDirPath.toAbsolutePath());
			} else {
				System.out.println("[APK Downloader] Directory exists: " + apkDirPath.toAbsolutePath());
			}

			// Step 2: Connect to GitHub API
			System.out.println("[APK Downloader] Connecting to GitHub API...");
			HttpURLConnection connection = (HttpURLConnection) new URL(GITHUB_API_URL).openConnection();
			connection.setRequestProperty("Accept", "application/vnd.github+json");
			connection.connect();

			int responseCode = connection.getResponseCode();
			if (responseCode != 200) {
				System.err.println("[APK Downloader] GitHub API responded with code: " + responseCode);
				return;
			}

			// Step 3: Parse JSON response
			System.out.println("[APK Downloader] Reading response...");
			String response = new BufferedReader(new InputStreamReader(connection.getInputStream())).lines()
					.collect(Collectors.joining("\n"));
			JSONObject release = new JSONObject(response);
			JSONArray assets = release.getJSONArray("assets");

			// Step 4: Find and download APK
			boolean apkFound = false;
			for (int i = 0; i < assets.length(); i++) {
				JSONObject asset = assets.getJSONObject(i);
				if (asset.getString("name").endsWith(".apk")) {
					apkFound = true;
					String downloadUrl = asset.getString("browser_download_url");

					System.out.println("[APK Downloader] Found APK: " + asset.getString("name"));
					System.out.println("[APK Downloader] Downloading from: " + downloadUrl);

					try (InputStream in = new URL(downloadUrl).openStream()) {
						Path apkPath = apkDirPath.resolve(APK_FILE_NAME);
						Files.copy(in, apkPath, StandardCopyOption.REPLACE_EXISTING);
						System.out.println("[APK Downloader] APK downloaded to: " + apkPath.toAbsolutePath());
					}

					break;
				}
			}

			if (!apkFound) {
				System.out.println("[APK Downloader] No APK file found in latest release.");
			}

		} catch (Exception e) {
			System.err.println("[APK Downloader] Error: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("[APK Downloader] Task finished.\n");
	}
}
