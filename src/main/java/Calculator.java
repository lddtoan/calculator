import java.util.ArrayList;

class Calculator {
    private static String trim(String str) {
        return str.replaceAll("\\s", "");
    }

    private static ArrayList<String> split(String str) {
        if(str.charAt(0) == '-') {
            str = '0' + str;
        }
        ArrayList<String> operators = new ArrayList<String>();

        for (int i = 0; i < str.length(); i++) {
            String number = new String();

            while(i < str.length() && Character.isDigit(str.charAt(i))) {
                number = number + str.charAt(i);
                i++;
            }

            if(number.length() > 0) {
                operators.add(number);
                i--;
            }
            else {
                operators.add(String.valueOf(str.charAt(i)));
            }
        }

        ArrayList<String> stack = new ArrayList<String>();

        for (int i = 0; i < operators.size(); i++) {
            if(stack.size() > 0 && operators.get(i).equals("-")) {
                switch (stack.get(stack.size() - 1)) {
                    case "(":
                        stack.add("0");
                        stack.add("-");
                        break;
                    case "+":
                        stack.remove(stack.size() - 1);
                        stack.add("-");
                        break;
                    case "-":
                        stack.remove(stack.size() - 1);
                        stack.add("+");
                        break;
                    case "*":
                        stack.add("-1");
                        stack.add("*");
                        break;
                    case "/":
                        stack.add("-1");
                        stack.add("/");
                        break;
                    default:
                        stack.add("-");
                        break;
                }
            }
            else {
                stack.add(operators.get(i));
            }
        }

        return stack;
    }

    private static String addSub(ArrayList<String> operators) {
        ArrayList<String> stack = new ArrayList<String>();
        for (int i = 0; i < operators.size(); i++) {
            if(stack.size() > 0 && stack.get(stack.size() - 1).equals("+")) {
                stack.remove(stack.size() - 1);
                int currentNum = Integer.parseInt(operators.get(i));
                int prevNum = Integer.parseInt(stack.remove(stack.size() - 1));
                stack.add(String.valueOf(prevNum + currentNum));
            }
            else if(stack.size() > 0 && stack.get(stack.size() - 1).equals("-")) {
                stack.remove(stack.size() - 1);
                int currentNum = Integer.parseInt(operators.get(i));
                int prevNum = Integer.parseInt(stack.remove(stack.size() - 1));
                stack.add(String.valueOf(prevNum - currentNum));
            }
            else {
                stack.add(operators.get(i));
            }
        }
        return stack.get(0);
    }

    private static String mulDiv(ArrayList<String> operators) {
        ArrayList<String> stack = new ArrayList<String>();
        for (int i = 0; i < operators.size(); i++) {
            if(stack.size() > 0 && stack.get(stack.size() - 1).equals("*")) {
                stack.remove(stack.size() - 1);
                int currentNum = Integer.parseInt(operators.get(i));
                int prevNum = Integer.parseInt(stack.remove(stack.size() - 1));
                stack.add(String.valueOf(prevNum * currentNum));
            }
            else if(stack.size() > 0 && stack.get(stack.size() - 1).equals("/")) {
                stack.remove(stack.size() - 1);
                int currentNum = Integer.parseInt(operators.get(i));
                int prevNum = Integer.parseInt(stack.remove(stack.size() - 1));
                stack.add(String.valueOf(prevNum / currentNum));
            }
            else {
                stack.add(operators.get(i));
            }
        }
        return Calculator.addSub(stack);
    }

    private static String bracket(ArrayList<String> operators) {
        ArrayList<String> stack = new ArrayList<String>();
        for (int i = 0; i < operators.size(); i++) {
            if(operators.get(i).equals(")")) {
                ArrayList<String> substack = new ArrayList<String>();
                while(!stack.get(stack.size() - 1).equals("(")) {
                    substack.add(0, stack.remove(stack.size() - 1));
                }
                stack.remove(stack.size() - 1);
                stack.add(mulDiv(substack));
            }
            else {
                stack.add(operators.get(i));
            }
        }
        return Calculator.mulDiv(stack);
    }

    public static String calculate(String str) {
        str = Calculator.trim(str);
        ArrayList<String> operators = Calculator.split(str);

        return Calculator.bracket(operators);
    }
}