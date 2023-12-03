# DSA REAL INTERVIEW

1. Function takes the str parameter being passed and determine if there is some substring k that can be repeated n>1 times to produce the input string exactly it appears your code should return the longest substring k and if there is none return -1. **[CorEdge - Parserlabs]**


   ```java
   public class SubstringMatcher {
       public static String matchChallenge(String str) {
           int length = str.length();
   
           for (int i = length / 2; i > 0; i--) {
               String substring = str.substring(0, i);
               if (str.equals(substring.repeat(length / i))) { // repeat supports in Java 11
                   return substring;
               }
           }
   
           return "-1";
       }
   
       public static void main(String[] args) {
           String result = matchChallenge("abcdabcdabcd");
           System.out.println(result);
       }
   }
   ```

   

For Java 11 or less

```java
public class SubstringMatcher {
    public static String matchChallenge(String str) {
        int length = str.length();

        for (int i = length / 2; i > 0; i--) {
            String substring = str.substring(0, i);

            // Repeat the substring manually to create a candidate pattern
            StringBuilder repeatedSubstring = new StringBuilder();
          
            for (int j = 0; j < length / i; j++) {
                repeatedSubstring.append(substring);
            }

            // Check if the repeated substring matches the original string
            if (str.equals(repeatedSubstring.toString())) {
                return substring;
            }
        }

        return "-1";
    }

    public static void main(String[] args) {
        String result = matchChallenge("abcdabcdabcd");
        System.out.println(result);
    }
}
```

2. Function takes the `num` parameter being passed and return the next number greater than num using the same digits. **[CorEdge - Parserlabs]**

```markdown
Input:  n = "218765"
Output: "251678"

Input:  n = "1234"
Output: "1243"

Input: n = "4321"
Output: "Not Possible"

Input: n = "534976"
Output: "536479"
```

Following are few observations about the next greater number. 

1. If all digits sorted in `descending order`, then output is always “Not Possible”. For example, 4321. 
2. If all digits are sorted in `ascending order`, then we need to swap last two digits. For example, 1234. 
3. For other cases, we need to process the number from rightmost side (why? because we need to find the smallest of all greater numbers)



Following is the algorithm for finding the next greater number. 

1. Traverse the given number from rightmost digit, keep traversing till you find a digit which is smaller than the previously traversed digit. For example, if the input number is “534976”, we stop at **4** because 4 is smaller than next digit 9. If we do not find such a digit, then output is “Not Possible”.
2. Now search the right side of above found digit ‘d’ for the smallest digit greater than ‘d’. For “53**4**976″, the right side of 4 contains “976”. The smallest digit greater than 4 is **6**.
3. Swap the above found two digits, we get 53**6**97**4** in above example.
4. Now `sort / reverse` all digits from position next to ‘d’ to the end of number. The number that we get after sorting is the output. For above example, we sort digits in bold 536**974**. We get “536**479**” which is the next greater number for input 534976.

```java
public class Main {
    public static int nextGreaterNumber(int num) {
        char[] numDigits = Integer.toString(num).toCharArray();

        // Find the first digit from the right that is smaller than the digit to its right
        for (int i = numDigits.length - 2; i >= 0; i--) {
            if (numDigits[i] < numDigits[i + 1]) {
                // Find the smallest digit to the right of numDigits[i] that is greater than numDigits[i]
                for (int j = numDigits.length - 1; j > i; j--) {
                    if (numDigits[j] > numDigits[i]) {
                        // Swap numDigits[i] and numDigits[j]
                        char temp = numDigits[i];
                        numDigits[i] = numDigits[j];
                        numDigits[j] = temp;
                      
                        // Reverse the substring to the right of numDigits[i]
                        reverse(numDigits, i + 1, numDigits.length - 1);

                        // Convert the char array back to an integer and return
                        return Integer.parseInt(new String(numDigits));
                    }
                }
            }
        }

        // If no such digit is found, then the number is the largest possible with these digits
        return -1;
    }

    private static void reverse(char[] array, int start, int end) {
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int result = nextGreaterNumber(4321);
        System.out.println(result);
    }
}
```

3. Function array challenege(arr) take the array of numbers stored in arr and return the string true if any two numbers can be multiplied so that the answer is greater than double the sum of all elements in the array if not return `false` **[CorEdge - Parserlabs]**

```java
public class ArrayChallenge {
    public static String arrayChallenge(int[] arr) {
        int sumOfElements = 0;

        for (int num : arr) {
            sumOfElements += num;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] * arr[j] > 2 * sumOfElements) {
                    return "true";
                }
            }
        }

        return "false";
    }

    public static void main(String[] args) {
        int[] exampleArray = {1, 2, 3, 4};
        String result = arrayChallenge(exampleArray);
        System.out.println(result);
    }
}
```

4. Function takes num1 and num2 parameters and return 1 if there is a straight triple of a number at any place in num1 and also a straight double of the same number in num2.

```java
public class NumberTripleDouble {

    public static void main(String[] args) {
        int num1 = 123456789;
        int num2 = 112233445;

        int result = checkTripleDouble(num1, num2);
        System.out.println(result);
    }

    static int checkTripleDouble(int num1, int num2) {
        String strNum1 = String.valueOf(num1);
        String strNum2 = String.valueOf(num2);

        for (int i = 0; i <= strNum1.length() - 3; i++) {
            char digit = strNum1.charAt(i);
            
          	if (strNum1.charAt(i + 1) == digit && strNum1.charAt(i + 2) == digit) {
                if (strNum2.contains("" + digit + digit)) {
                    return 1;
                }
            }
        }

        return 0;
    }
}
```

5. Function take the array of characters stored in strArr which will contain characters ranging from A to Z in some arbitrary order and determine what elements still remain in a virtual cache that can hold upto `5 elements` with an LRU cache algorithm implemented.

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    public static void main(String[] args) {
        String[] strArr = {"ABC", "ABD", "CBA"};

        String result = lruCache(strArr);
        System.out.println(result);
    }

    static String lruCache(String[] strArr) {
        int capacity = 5;
      
        Map<String, Integer> cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > capacity;
            }
        };

        for (String str : strArr) {
          	// If an element is present its replaced
          	cache.put(str, 0); // populated with unique elements
        }

        return String.join("-", cache.keySet());
    }
}
```

**Using Array List**

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayChallenge {

    public static void main(String[] args) {
        List<String> strArr = new ArrayList<>();
        strArr.add("J");
        strArr.add("M");
        strArr.add("R");
        strArr.add("A");
        strArr.add("B");
        strArr.add("C");
        strArr.add("R");
        strArr.add("M");
        strArr.add("F");
        strArr.add("C");

        System.out.println(ArrayChallenge(strArr));
    }

    static String ArrayChallenge(List<String> strArr) {
        List<String> newArr = new ArrayList<>();
      	
        for (String x : strArr) {
          	// Capacity is 5 
            if (newArr.size() < 5) {
                if (!newArr.contains(x)) {
                    newArr.add(x);
                } else {
                    newArr.remove(x);
                    newArr.add(x);
                }
            } else {
                newArr.remove(0); // Remove First Element ... Like Queue
                newArr.add(x); // 
            }
        }

        return String.join("-", newArr);
    }
}
```

6. Function takes array of integers stored in arr and find the fourth largest elements and return their sum.

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {1, 1, 1, -5};
        System.out.println(findFourthLargestSum(arr1));  // Output: -2
    }

    public static int findFourthLargestSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
      
      	int n = arr.length;

        if (n < 4) {
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }
            return sum;
        }
      
        Arrays.sort(arr);
        return arr[n - 1] + arr[n - 2] + arr[n - 3] + arr[n - 4];
    }
}
```

7. Have the function StringChallenge(str) read str which will contain two strings separated by a space. The first string will consist of the following sets of characters: +, *, $, and {N} which is optional. The plus (+) character represents a single alphabetic character, the ($) character represents a number between 1-9, and the asterisk (*) represents a sequence of the same character of length 3 unless it is followed by {N} which represents how many characters should appear in the sequence where N will be at least 1. Your goal is to determine if the second string exactly matches the pattern of the first string in the input. For example: if str is "++*{5} jtggggg" then the second string in this case does match the pattern, so your program should return the string true. If the second string does not match the pattern your program should return the string false.

   Examples 

   Input: "+++++* abcdehhhhhh" 

   Output: false 

   Input: "$**+*{2} 9mmmrrrkbb" 

   Output: true

```java
import java.util.regex.*;

public class WordChecker {
    public static void main(String[] args) {
        System.out.println(wc("++*{5} jtggggg"));  // Output: true
        System.out.println(wc("+++++* abcdehhhhhh"));  // Output: false
        System.out.println(wc("$**+*{2} 9mmmrrrkbb"));  // Output: true
    }

    public static boolean wc(String str) {
        String[] arr = str.split(" ");
        String pattern = arr[0];
        String word = arr[1];
        String regex = "";

        for (int i = 0; i < pattern.length(); ++i) {
            if (pattern.charAt(i) == '+') {
                regex += "[a-zA-Z]";
            } else if (pattern.charAt(i) == '$') {
                regex += "[1-9]";
            } else if (pattern.charAt(i) == '*') {
                if (i + 1 < pattern.length() && pattern.charAt(i + 1) == '{') {
                    regex += ".{" + pattern.charAt(i + 2) + "}";
                    i += 2; // Skip the next two characters '{' and the digit
                } else {
                    regex += ".{3}";
                }
            }
        }

        // Use Pattern and Matcher to test the word against the regex
        Pattern patternRegex = Pattern.compile("^" + regex + "$");
        Matcher matcher = patternRegex.matcher(word);
        
        // System.out.print(patternRegex);
        return matcher.matches();
    }
}
```

