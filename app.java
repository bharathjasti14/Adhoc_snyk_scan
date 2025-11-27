package com.example;

import org.apache.commons.text.StringSubstitutor;

public class App {
    public static void main(String[] args) {
        String template = "Hello, ${name}!";
        StringSubstitutor sub = new StringSubstitutor(java.util.Collections.singletonMap("name", "World"));
        String result = sub.replace(template);
        System.out.println(result);
    }
}
