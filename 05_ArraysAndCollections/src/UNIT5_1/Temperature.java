package UNIT5_1;

import java.text.DecimalFormat;

public class Temperature {
    public static void main(String[] args) {
        final int PATIENTS = 30;
        final double MAX_TEMP = 40;
        final double MIN_TEMP = 32;
        final double MIN_TEMP_HEALTHY_HUMAN = 36.2;
        final double MAX_TEMP_HEALTHY_HUMAN = 36.9;
        int countNormalTemperature = 0;

        DecimalFormat decimalFormat = new DecimalFormat("#0.00°C");
        double [] temperature = new double[PATIENTS];
        for (int i = 0; i <  temperature.length; i++) {
            temperature[i]= Math.random()*(MAX_TEMP-MIN_TEMP)+MIN_TEMP;
        }
        double minTemperature = temperature[0];
        double maxTemperature = temperature[temperature.length-1];

        for (int i =0;i<temperature.length;i++){
            if (temperature[i]>maxTemperature) {
                maxTemperature = temperature[i];
            }
            if (temperature[i]<minTemperature){
                minTemperature=temperature[i];
            }
            if (temperature[i] >=MIN_TEMP_HEALTHY_HUMAN && temperature[i]<MAX_TEMP_HEALTHY_HUMAN){
                countNormalTemperature++;
            }
        }

        for (double a:temperature) {
            System.out.print(decimalFormat.format(a) + " ");
        }
        System.out.println();

        System.out.println("Min temp: " + decimalFormat.format(minTemperature) + "\nMax temp: " + decimalFormat.format(maxTemperature));
        System.out.println("Average = " +decimalFormat.format ((maxTemperature+minTemperature)/2));
        System.out.println("People with normal temperature are : " + countNormalTemperature);
    }
}
