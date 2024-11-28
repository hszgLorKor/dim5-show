package com.education.hszg.bmi;

public class BMICalculator {

    public double calculateBMI(double weightInKG, double heightInMetres) {
        if ((weightInKG < 30) || (weightInKG > 150)) return 0.0;
        if ((heightInMetres < 1.20) || (heightInMetres > 2.50)) return 0.0;
        return
                weightInKG
                /
                (heightInMetres * heightInMetres);
    }

    public String getBMIRecommendation(double weightInKG, double heightInMetres) {
        if (calculateBMI(weightInKG, heightInMetres) < 21)
            return "Please eat more often at McDonalds.";
        if (calculateBMI(weightInKG, heightInMetres) > 24)
            return "Please do join a gym sports club.";
        return "You are having a healthy body.";
    }

    public static void main(String[] args) {
        BMICalculator bmiCalculator = new BMICalculator();
        System.out.println("Test des BMI Calculator:");
        System.out.println("Values: 70.0kg und 1.78m");
        System.out.println("BMI: " + bmiCalculator.calculateBMI(70.0, 1.78));

    }

}
