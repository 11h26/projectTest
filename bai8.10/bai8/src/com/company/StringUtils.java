package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtils {
    public static List<String> tokenize(String source, String separator) {
        List<String> result = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(source, separator);

        while (tokenizer.hasMoreTokens()) {
            result.add(tokenizer.nextToken());
        }

        return result;
    }
}
