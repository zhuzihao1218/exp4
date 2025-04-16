package org.apache.commons.lang3;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {

    // 测试trim方法对null和空字符串的处理
    @Test
    public void verifyTrimBehavior() {
        assertNull("Null input should return null", StringUtils.trim(null));
        assertEquals("Empty string should stay empty", "", StringUtils.trim(""));
        assertEquals("Whitespace should become empty", "", StringUtils.trim("   "));
    }

    // 验证capitalize对大小写的处理
    @Test
    public void checkCapitalization() {
        assertEquals("Already capitalized remains same", "Hello", StringUtils.capitalize("Hello"));
        assertEquals("Lowercase should be capitalized", "Test", StringUtils.capitalize("test"));
    }

    // 测试isEmpty的边界条件
    @Test
    public void emptyStringTests() {
        assertTrue("Null should be empty", StringUtils.isEmpty(null));
        assertFalse("Space is not empty", StringUtils.isEmpty(" "));
    }

    // 检查substring的负数索引特性
    @Test
    public void negativeIndexCases() {
        assertEquals("Negative start index", "Hello",
                StringUtils.substring("Hello", -5));
        assertEquals("Normal substring", "lo",
                StringUtils.substring("Hello", 3));
    }

    // 验证reverse对特殊字符的处理
    @Test
    public void specialCharacterReversal() {
        assertEquals("Single char reversal", "a",
                StringUtils.reverse("a"));
        assertEquals("Space preservation", "cba   321",
                StringUtils.reverse("123   abc"));
    }

    // 测试isBlank对空白字符串的识别
    @Test
    public void whitespaceDetection() {
        assertTrue("Tab should be blank",
                StringUtils.isBlank("\t"));
        assertFalse("Text with spaces",
                StringUtils.isBlank("  test  "));
    }
}