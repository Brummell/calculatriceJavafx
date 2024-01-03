package com.test;



import javafx.scene.control.Button;

public class CalculatorController {
	private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        wireUpEvents();
    }

    private void wireUpEvents() {
    	for (Button digitButton : view.getDigitButtons()) {
            digitButton.setOnAction(e -> handleDigitButtonClick(digitButton));
        }	
    	view.getEqualButton().setOnAction(e -> handleEgalButtonClick());
    	
        view.updateDisplay();
    }
    private void handleDigitButtonClick(Button digitButton) {
        String buttonText = digitButton.getText();

        model.appendDigit(Integer.parseInt(buttonText));

        view.updateDisplay();;
    }
    private void handleEgalButtonClick() {
        String expression = view.getDisplay().getText();

        try {
            String[] tokens = expression.split("\\+|\\-|\\*|\\/");

            if (tokens.length == 2) {
                int operand1 = Integer.parseInt(tokens[0]);
                int operand2 = Integer.parseInt(tokens[1]);
                char operator = expression.charAt(tokens[0].length());

                int result = calculateResult(operand1, operand2, operator);

                updateDisplay(Integer.toString(result));
            } else {
                updateDisplay("Erreur");
            }

        } catch (NumberFormatException e) {
            updateDisplay("Erreur");
        }
    }

    private int calculateResult(int operand1, int operand2, char operator) {
        switch (operator) {
            case '+':
                return (int)operand1 + operand2;
            case '-':
                return (int)operand1 - operand2;
            case '*':
                return (int)operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return (int)operand1 / operand2;
                } else {
                    updateDisplay("Erreur");
                }
            default:
                throw new IllegalArgumentException("Opérateur non pris en charge: " + operator);
        }
    }

    private void updateDisplay(String result) {
    	view.getDisplay().setText(result);
    }
    

}
