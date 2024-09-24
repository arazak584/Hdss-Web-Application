package org.arn.hdsscapture.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomDateDeserializer extends JsonDeserializer<Date> {
    private static final SimpleDateFormat fullDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
    private static final SimpleDateFormat partialDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText();
        try {
            // First, try to parse the date with the full format
            Date parsedDate;
            if (date.length() > 10) {
                parsedDate = fullDateFormat.parse(date);
            } else {
                // If the date is in the 'yyyy-MM-dd' format
                parsedDate = partialDateFormat.parse(date);
            }
            // Optionally format the date to 'yyyy-MM-dd HH:mm:ss'
            return parsedDate;
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }
}


