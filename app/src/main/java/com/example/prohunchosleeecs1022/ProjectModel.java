package com.example.prohunchosleeecs1022;

/*
 * Author : Alex Valdez, Huy Anh Vu Tran , Khandker Hasan
 * Team Name : ProHunchos
 * LE EECS 1022 Project = "What number am I thinking of"?
 */

public class ProjectModel {
    //Generate a random number from 1 and the user's upper limit
    public static double getRandom(double upperValue) {
        double value;
        value = Math.floor((Math.random() * upperValue) + 1);
        return value;
    }//end getRandom()

    //Turn string inputs and return them as double
    public static double toDouble(String s) {
        double result;
        result = Double.parseDouble(s);
        return result;
    }//end toDouble()

    //Turns double inputs and returns them as string
    public static String format(double value) {
        String answer;
        answer = Double.toString(value);
        return answer;
    }//format()
}//end ProjectModel()
