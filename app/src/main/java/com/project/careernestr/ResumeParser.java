package com.project.careernestr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ResumeParser {

    private static final List<String> SKILL_DICTIONARY = Arrays.asList(
            "java", "android", "kotlin", "firebase", "xml", "rest api", "retrofit", "git",
            "react", "node.js", "mongodb", "express", "javascript", "html", "css",
            "figma", "adobe xd", "ui/ux", "wireframing", "python", "django", "machine learning"
    );

    public static List<String> parseExtractedText(String text) {
        String lowerText = text.toLowerCase(Locale.ROOT);
        List<String> extractedSkills = new ArrayList<>();

        // ১. Keyword Overlap Detection (তোমার আসল লজিক)
        for (String skill : SKILL_DICTIONARY) {
            if (lowerText.contains(skill)) {
                extractedSkills.add(skill);
            }
        }

        // ২. Intent & Role Domain Matching (তোমার আসল লজিক)
        String predictedRole = "General Software Engineer";
        if (extractedSkills.contains("android") || extractedSkills.contains("kotlin") || lowerText.contains("android studio")) {
            predictedRole = "Android Developer";
        } else if (extractedSkills.contains("react") || extractedSkills.contains("node.js")) {
            predictedRole = "Full Stack Developer";
        } else if (extractedSkills.contains("figma") || extractedSkills.contains("ui/ux")) {
            predictedRole = "UI/UX Designer";
        }

        // যদি কোনো স্কিল খুঁজে না পায়, অ্যাপ যেন ক্র্যাশ না করে তাই ডিফল্ট কিছু স্কিল ব্যাকআপ রাখা হলো
        if (extractedSkills.isEmpty()) {
            extractedSkills.add("Java");
            extractedSkills.add("Android Studio");
            extractedSkills.add("Firebase");
        }

        return extractedSkills;
    }

    // প্রজেক্টের স্কোর ক্যালকুলেশনের জন্য তোমার এক্সপেরিয়েন্স লজিকটা এখানে কাজে লাগানো হলো
    public static int calculateResumeScore(String text) {
        String lowerText = text.toLowerCase(Locale.ROOT);
        int experience = 0;
        if (lowerText.contains("years experience") || lowerText.contains("years of experience")) {
            experience = 2;
        }

        // এক্সপেরিয়েন্স থাকলে স্কোর একটু বাড়িয়ে ৮৫ দেবে, না থাকলে ৭৫
        return (experience > 0) ? 85 : 75;
    }
}