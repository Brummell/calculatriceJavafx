package com.test;

public class CalculatorModel {
	private StringBuilder currentInput = new StringBuilder();

    public void appendDigit(int digit) {
        currentInput.append(digit);
    }

    public void clear() {
        currentInput.setLength(0);
    }
    
    public void appendOperator(String operator) {
    	currentInput.append(operator);
    }
    
    public String getCurrentInput() {
        return currentInput.toString();
    }
    
    
}
