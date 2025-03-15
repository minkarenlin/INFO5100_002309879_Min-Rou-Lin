import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) throws Exception {
        testRegex("Email Validation", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", 
            new String[]{"test@example.com", "user.name@domain.co"}, 
            new String[]{"invalid-email", "@missingusername.com"});
        
        testRegex("Phone Number Validation (US format)", "^\\(\\d{3}\\) \\d{3}-\\d{4}$", 
            new String[]{"(123) 456-7890"}, 
            new String[]{"123-456-7890", "(123)456-7890"});
        
        testRegex("Credit Card Number Validation", "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", 
            new String[]{"1234-5678-9876-5432", "1111-2222-3333-4444"}, 
            new String[]{"12345-6789-9876-5432", "1234 5678 9876 5432"});
        
        testRegex("Password Strength Check (Minimum 8 chars, at least 1 letter, 1 number)", 
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", 
            new String[]{"Password1", "secure123"}, 
            new String[]{"short", "allletters", "12345678"});
        
        testRegex("Date Format (YYYY-MM-DD)", "^\\d{4}-\\d{2}-\\d{2}$", 
            new String[]{"2025-03-14", "1998-12-31"}, 
            new String[]{"14-03-2025", "2025/03/14"});
    }

     private static void testRegex(String description, String pattern, String[] validCases, String[] invalidCases) {
        System.out.println("\n" + description);
        Pattern regexPattern = Pattern.compile(pattern);
        
        for (String testCase : validCases) {
            System.out.println("Testing: " + testCase + " => " + regexPattern.matcher(testCase).matches());
        }
        
        for (String testCase : invalidCases) {
            System.out.println("Testing: " + testCase + " => " + regexPattern.matcher(testCase).matches());
        }
    }
}
