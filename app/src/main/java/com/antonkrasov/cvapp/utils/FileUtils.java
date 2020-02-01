package com.antonkrasov.cvapp.utils;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import timber.log.Timber;

public class FileUtils {

    public static @Nullable String inputStreamIntoString(@Nullable InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }

        try {
            StringBuilder sb = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8 ));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();

            return sb.toString();
        } catch (Exception ex) {
            Timber.e(ex);
        }

        return null;
    }

}
