package com.hibernate.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextField;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hibernate.gui.*;

class TestParseTextFieldToInt {

    private App app;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        app = new App();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testParseTextFieldToIntWithValidNumber() {
        JTextField textField = new JTextField("123");
        int result = app.parseTextFieldToInt(textField);
        assertEquals(123, result);
    }

    @Test
    void testParseTextFieldToIntWithEmptyString() {
        JTextField textField = new JTextField("");
        int result = app.parseTextFieldToInt(textField);
        assertEquals(0, result);
    }

    @Test
    void testParseTextFieldToIntWithNonNumericString() {
        JTextField textField = new JTextField("abc");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.parseTextFieldToInt(textField);
        });
        assertEquals("Only numbers supported", exception.getMessage());
    }

    @Test
    void testParseTextFieldToIntWithMixedString() {
        JTextField textField = new JTextField("123abc");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.parseTextFieldToInt(textField);
        });
        assertEquals("Only numbers supported", exception.getMessage());
    }

    @Test
    void testParseTextFieldToIntWithLeadingZeros() {
        JTextField textField = new JTextField("00123");
        int result = app.parseTextFieldToInt(textField);
        assertEquals(123, result);
    }
}
