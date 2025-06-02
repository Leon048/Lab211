/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.BMI;
import entity.BMICalculator;
import entity.NormalCalculator;
import entity.Operator;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NormalCalculator normalCalculator;
        BMICalculator bmiCalculator;
        while (true) {
            int choice = Validator.getInt("========= Calculator Program =========\n"
                    + "1. Normal Calculator\n"
                    + "2. BMI Calculator\n"
                    + "3. Exit\n"
                    + "Please choice one option:", "Just be 1->3", "Invalid!", 1, 3);
            switch (choice) {
                case 1:
                    System.out.println("----- Normal Calculator -----");
                    double a = Validator.getDouble("Enter number: ", "Error range!", "Invalid!",
                            -Double.MAX_VALUE, Double.MAX_VALUE);
                    while (true) {
                        String stringOperator = Validator.getString("Enter Operator: ",
                                "Please input (+, -, *, /, ^)", "^[\\+\\-\\*/\\^=]$");
                        Operator operator = Operator.checkOperator(stringOperator.charAt(0));
                        if (operator == Operator.EQUALS) {
                            System.out.println("Result: " + a);
                            break;
                        } else {
                            double b = Validator.getDouble("Enter number: ", "Error range!", "Invalid!",
                                    -Double.MAX_VALUE, Double.MAX_VALUE);
                            normalCalculator = new NormalCalculator(a, b, operator);
                            try {
                                a = normalCalculator.calculate();
                                System.out.println("Memory: " + a);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("----- BMI Calculator -----");
                    bmiCalculator = new BMICalculator();
                    bmiCalculator.input();
                    BMI result = bmiCalculator.calculateBMI();
                    System.out.printf("BMI Number: %.2f\n" , bmiCalculator.getBmi());
                    System.out.println("BMI Status: " + result.getValue());
                case 3:
                    System.exit(0);
            }
        }

    }
}
