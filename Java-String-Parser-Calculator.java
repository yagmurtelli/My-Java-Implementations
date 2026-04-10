import java.util.*;

public class Calculator {


	    List<String> functiondata;
	    char[] number1 = new char[2];
	    char[] number2 = new char[2];
	    char[] operator = new char[1];

	    public Calculator(List<String> functiondata) {
	        this.functiondata = functiondata;
	    }

	    public void process() {
	        ListIterator<String> datanew = functiondata.listIterator();
	        while (datanew.hasNext()) {
	            int index = datanew.nextIndex() + 1;
	            String temp = datanew.next();

	            try {
	                if (temp.endsWith("!")) {
	                    temp.getChars(0, 2, number1, 0);
	                    int n1 = ((number1[0] - '0') * 10) + (number1[1] - '0');
	                    int result = factorial(n1);
	                    System.out.println(index + ". function is:" + temp + "= " + result);

	                } else if (temp.endsWith("s")) {
	                    temp.getChars(0, 2, number1, 0);
	                    int n1 = ((number1[0] - '0') * 10) + (number1[1] - '0');
	                    double result = sfunction(n1);
	                    System.out.println(index + ". function is:" + temp + "= " + result);

	                } else {
	                    temp.getChars(0, 2, number1, 0);
	                    temp.getChars(2, 3, operator, 0);
	                    temp.getChars(3, 5, number2, 0);

	                    int n1 = ((number1[0] - '0') * 10) + (number1[1] - '0');
	                    int n2 = ((number2[0] - '0') * 10) + (number2[1] - '0');
	                    char op = operator[0];

	                    switch (op) {
	                        case '+':
	                            System.out.println(index + ". function is:" + temp + "= " + (n1 + n2));
	                            break;
	                        case '-':
	                            System.out.println(index + ". function is:" + temp + "= " + (n1 - n2));
	                            break;
	                        case '/':
	                            if (n2 == 0) {
	                                System.out.println(index + ". function is:" + temp + "= Error zero division");
	                            } else {
	                                System.out.println(index + ". function is:" + temp + "= " + ((double) n1 / n2));
	                            }
	                            break;
	                        default:
	                            System.out.println(index + ". function is:" + temp + "= Error operator not defined");
	                    }
	                }
	            } catch (Exception e) {
	                System.out.println(index + ". function is:" + temp + "= Error invalid format");
	            }
	        }
	    }

	    public int factorial(int n) {
	        if (n <= 1) return 1;
	        return n * factorial(n - 1);
	    }

	    public double powersfunction(double number, int power) {
	        if (power == 0) return 1;
	        return number * powersfunction(number, power - 1);
	    }

	    public double sfunction(double number) {
	        double num1 = number * 3.14 / 100;
	        double result = num1
	                - (powersfunction(num1, 3) / factorial(3))
	                + (powersfunction(num1, 5) / factorial(5))
	                - (powersfunction(num1, 7) / factorial(7))
	                + (powersfunction(num1, 9) / factorial(9));
	        return result;
	    }
	}

	   


