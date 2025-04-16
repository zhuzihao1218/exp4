
package com.zzh.test;
import org.apache.commons.lang3.StringUtils;
/**
 * StringUtils类的单元测试
 * 不依赖测试框架,通过main方法直接运行测试
 */
public class StringUtilsTest {

    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        testReverse();
        testIsBlank();
        testCapitalize();
        testSubstring();
        testTrim();
        testIsEmpty();

        System.out.println("\nTest Results:");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed tests: " + passedTests);
        System.out.println("Pass rate: " + (passedTests * 100.0 / totalTests) + "%");
    }

    /**
     * 测试 reverse 方法
     */
    private static void testReverse() {
        System.out.println("\nTesting reverse method:");

        assertTest("reverse-null",
                StringUtils.reverse(null) == null,
                "Null string should return null");

        assertTest("reverse-empty",
                "".equals(StringUtils.reverse("")),
                "Empty string should return empty string");

        assertTest("reverse-single",
                "a".equals(StringUtils.reverse("a")),
                "Single character should remain unchanged");

        assertTest("reverse-normal",
                "dcba".equals(StringUtils.reverse("abcd")),
                "String should be reversed");

        assertTest("reverse-withSpace",
                "cba   321".equals(StringUtils.reverse("123   abc")),
                "String with spaces should be reversed correctly");
    }

    /**
     * 测试 isBlank 方法
     */
    private static void testIsBlank() {
        System.out.println("\nTesting isBlank method:");

        assertTest("isBlank-null",
                StringUtils.isBlank(null) == true,
                "Null string should return true");

        assertTest("isBlank-empty",
                StringUtils.isBlank("") == true,
                "Empty string should return true");

        assertTest("isBlank-whitespace",
                StringUtils.isBlank("   ") == true,
                "Whitespace string should return true");

        assertTest("isBlank-normal",
                StringUtils.isBlank("test") == false,
                "Normal string should return false");

        assertTest("isBlank-withSpace",
                StringUtils.isBlank("  test  ") == false,
                "String with spaces should return false");
    }

    /**
     * 测试 capitalize 方法
     */
    private static void testCapitalize() {
        System.out.println("\nTesting capitalize method:");

        assertTest("capitalize-null",
                StringUtils.capitalize(null) == null,
                "Null string should return null");

        assertTest("capitalize-empty",
                "".equals(StringUtils.capitalize("")),
                "Empty string should return empty string");

        assertTest("capitalize-lowercase",
                "Hello".equals(StringUtils.capitalize("hello")),
                "First letter should be capitalized");

        assertTest("capitalize-uppercase",
                "Hello".equals(StringUtils.capitalize("Hello")),
                "Already capitalized string should remain unchanged");
    }

    /**
     * 测试 substring 方法 (包含注入的缺陷)
     * 注意: 这个方法中包含了一个注入的缺陷,在处理负数索引时会直接返回空字符串
     */
    private static void testSubstring() {
        System.out.println("\nTesting substring method (with injected defect):");

        assertTest("substring-null",
                StringUtils.substring(null, 0) == null,
                "Null string should return null");

        assertTest("substring-normal",
                "ello".equals(StringUtils.substring("Hello", 1)),
                "Substring from index 1 should return 'ello'");

        assertTest("substring-outOfBounds",
                "".equals(StringUtils.substring("Hello", 10)),
                "Out-of-bounds index should return empty string");

        assertTest("substring-negative",
                "Hello".equals(StringUtils.substring("Hello", -5)),
                "Negative index should count from end (but defect returns empty string)");
    }

    /**
     * 测试 trim 方法
     */
    private static void testTrim() {
        System.out.println("\nTesting trim method:");

        assertTest("trim-null",
                StringUtils.trim(null) == null,
                "Null string should return null");

        assertTest("trim-empty",
                "".equals(StringUtils.trim("")),
                "Empty string should return empty string");

        assertTest("trim-whitespace",
                "".equals(StringUtils.trim("   ")),
                "Whitespace string should return empty string");

        assertTest("trim-bothEnds",
                "test".equals(StringUtils.trim("  test  ")),
                "String with leading/trailing spaces should be trimmed");

        assertTest("trim-middle",
                "test test".equals(StringUtils.trim("test test")),
                "Inner spaces should be preserved");
    }

    /**
     * 测试 isEmpty 方法
     */
    private static void testIsEmpty() {
        System.out.println("\nTesting isEmpty method:");

        assertTest("isEmpty-null",
                StringUtils.isEmpty(null) == true,
                "Null string should return true");

        assertTest("isEmpty-empty",
                StringUtils.isEmpty("") == true,
                "Empty string should return true");

        assertTest("isEmpty-blank",
                StringUtils.isEmpty(" ") == false,
                "Whitespace string should return false");

        assertTest("isEmpty-normal",
                StringUtils.isEmpty("test") == false,
                "Normal string should return false");
    }

    private static void assertTest(String testName, boolean condition, String message) {
        totalTests++;
        if (condition) {
            passedTests++;
            System.out.println(testName + " PASSED");
        } else {
            System.out.println(testName + " FAILED: " + message);
        }
    }
}