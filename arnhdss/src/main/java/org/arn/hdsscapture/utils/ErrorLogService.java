package org.arn.hdsscapture.utils;

import java.time.LocalDateTime;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ErrorLogService {

	private final ErrorLogRepository errorLogRepository;

    public ErrorLogService(ErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW) // Independent transaction for logging errors
    public void logError(String errorMessage, String stackTrace, String recordUuid) {
        try {
            ErrorLog errorLog = new ErrorLog();
            errorLog.setErrorMessage(errorMessage);
            errorLog.setTimestamp(LocalDateTime.now());
            errorLog.setStackTrace(stackTrace);
            errorLog.setRecordUuid(recordUuid);
            errorLogRepository.save(errorLog); // Save the error log to the database
        } catch (Exception logException) {
            // Log the failure of error logging (this should not fail often)
            System.err.println("Failed to log error: " + logException.getMessage());
        }
    }
}
