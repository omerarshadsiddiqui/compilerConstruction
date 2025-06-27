package Compiler;

import java.util.Stack;
import java.util.StringTokenizer;

public class Parser{
    public static StringTokenizer string_token;
    public static String Remaining;
    public static String StringAt;
    //public static
    public static Stack<String> symbol = new Stack<String>();
    public static Stack<Integer> state = new Stack<Integer>();
    public static String checkString = "^[a-zA-Z]+(0-9|_)*[\\w]+$";
    //public static Stack<String> stateSymbol = new Stack<String>();
    //public static String Stringcheck = "^[a-zA-Z]+(0-9|_)*$";


    public static String StringInsertion()
    {
//        int cnt = 0;
        Remaining = "";
        while(StringAt != "$") {
            Remaining += StringAt;
            StringAt = string_token.nextToken().intern();
            if(StringAt.matches(checkString)) {
                System.out.println("HEREEE");
                StringAt = "i";
            }
//            System.out.println(cnt);
//            System.out.println(StringAt);
        }
//        System.out.println(Remaining);
        Remaining += "$";
        String s = Remaining.replaceAll("i","id");
        CompilerGUI.jTextArea2.append("\nThe String is converted into form: \n"+s+"\n");

        return Remaining;
    }

    public static void Parser() {
        symbol.push("-");
        state.push(0);
        int Continueloop = 1;
        while(Continueloop == 1) {
            int Stack_State = state.peek();
            String Expression = String.valueOf(Remaining.charAt(0));
            System.out.println(Expression);
                if (Expression.equals("i")) {
                    if (Stack_State == 0 || Stack_State == 4 || Stack_State == 6 || Stack_State == 7) {
                        symbol.push("" + Remaining.charAt(0));
                        state.push(5);
                        Remaining = Remaining.substring(1);
                    }
                    else {
//                        System.out.println("Expression is invalid!!! Cannot be Compiled!");
                        CompilerGUI.jTextArea2.append("\nExpression is invalid!!! Cannot be Compiled!");
                        String str = Remaining;
                        String s = str.replaceAll("i","id");
                        CompilerGUI.jTextArea2.append("\nRemaining Expression Cannot be Parsed: "+s+"\nError is caused by: "+ Remaining.charAt(0));
                        Continueloop = 0;
//                        System.exit(-1);
                    }
//                    Continueloop = 2;
                }
                else if (Expression.equals("("))
                {
                    if(Stack_State == 0 || Stack_State == 4 || Stack_State == 6 || Stack_State == 7) {
                        symbol.push("" + Remaining.charAt(0));
                        state.push(4);
                        Remaining = Remaining.substring(1);
                    }
                    else {
                        CompilerGUI.jTextArea2.append("\nExpression is invalid!!! Cannot be Compiled!");
                        String str = Remaining;
                        String s = str.replaceAll("i", "id");
                        CompilerGUI.jTextArea2.append("\nRemaining Expression Cannot be Parsed: "+s+"\nError is caused by: "+ Remaining.charAt(0));
                        Continueloop = 0;
//                        System.exit(-1);
                    }
                }
                else if (Expression.equals("+")) {
                    if(Stack_State == 1 || Stack_State == 8) {
                        symbol.push("" + Remaining.charAt(0));
                        state.push(6);
                        Remaining = Remaining.substring(1);
                    }
                    else if(Stack_State == 2) {
                        Reduction(2);
                    }
                    else if(Stack_State == 3) {
                        Reduction(4);
                    }
                    else if(Stack_State == 5) {
                        Reduction(6);
                    }
                    else if(Stack_State == 9) {
                        Reduction(1);
                    }
                    else if(Stack_State == 10) {
                        Reduction(3);
                    }
                    else if(Stack_State == 11) {
                        Reduction(5);
                    }
                    else {
                        CompilerGUI.jTextArea2.append("\nExpression is invalid!!! Cannot be Compiled!");
                        String str = Remaining;
                        String s = str.replaceAll("i","id");
                        CompilerGUI.jTextArea2.append("\nRemaining Expression Cannot be Parsed: "+s+"\nError is caused by: "+ Remaining.charAt(0));
//                        System.exit(-1);
                        Continueloop = 0;
                    }
                }
                else if (Expression.equals("*")) {
                    if(Stack_State == 2 || Stack_State == 9) {
                        symbol.push("" + Remaining.charAt(0));
                        state.push(7);
                        Remaining = Remaining.substring(1);
                    }
                    else if(Stack_State == 3) {
                        Reduction(4);
                    }
                    else if(Stack_State == 5) {
                        Reduction(6);
                    }
                    else if(Stack_State == 10) {
                        Reduction(3);
                    }
                    else if(Stack_State == 11) {
                        Reduction(5);
                    }
                    else {
                        CompilerGUI.jTextArea2.append("\nExpression is invalid!!! Cannot be Compiled!");
                        String str = Remaining;
                        String s = str.replaceAll("i","id");
                        CompilerGUI.jTextArea2.append("\nRemaining Expression Cannot be Parsed: "+s+"\nError is caused by: "+ Remaining.charAt(0));
//                        System.exit(-1);
                        Continueloop = 0;
                    }
                }

                else if (Expression.equals(")"))
                {
                    if(Stack_State == 8) {
                        symbol.push("" + Remaining.charAt(0));
                        state.push(11);
                        Remaining = Remaining.substring(1);
                    }
                    else if(Stack_State == 2) {
                        Reduction(2);
                    }
                    else if(Stack_State == 3) {
                        Reduction(4);
                    }
                    else if(Stack_State == 5) {
                        Reduction(6);
                    }
                    else if(Stack_State == 9) {
                        Reduction(1);
                    }
                    else if(Stack_State == 10) {
                        Reduction(3);
                    }
                    else if(Stack_State == 11) {
                        Reduction(5);
                    }
                    else {
                        CompilerGUI.jTextArea2.append("\nExpression is invalid!!! Cannot be Compiled!");
                        String str = Remaining;
                        String s = str.replaceAll("i","id");
                        CompilerGUI.jTextArea2.append("\nRemaining Expression Cannot be Parsed: "+s+"\nError is caused by: "+ Remaining.charAt(0));
//                        System.exit(-1);
                        Continueloop = 0;
                    }
                }
                else if (Expression.equals("$")) {
                    if(Stack_State == 1) {
                        CompilerGUI.jTextArea2.append("\nCompiled Succesfully!!!");
                        Continueloop = 0;
                    }
                    else if(Stack_State == 2) {
                        Reduction(2);
                    }
                    else if(Stack_State == 3) {
                        Reduction(4);
                    }
                    else if(Stack_State == 5) {
                        Reduction(6);
                    }
                    else if(Stack_State == 9) {
                        Reduction(1);
                    }
                    else if(Stack_State == 10) {
                        Reduction(3);
                    }
                    else if(Stack_State == 11) {
                        Reduction(5);
                    }
                    else {
                        CompilerGUI.jTextArea2.append("\nExpression is invalid!!! Cannot be Compiled!");
                        String str = Remaining;
                        String s = str.replaceAll("i","id");
                        CompilerGUI.jTextArea2.append("\nRemaining Expression Cannot be Parsed: "+s+"\nError is caused by: "+ Remaining.charAt(0));
//                        System.exit(-1);
                        Continueloop = 0;
                    }
                }
                else {
                    CompilerGUI.jTextArea2.append("\nExpression is  not a part of grammar");
                    Continueloop = 0;
                }

//            if(Continueloop != 0)
//            {
//                for(int i = 1; i < symbol.size(); i++)
//                {
//                    System.out.print("["+symbol.elementAt(i)+":"+state.elementAt(i)+"]");
//                }
//                System.out.print("  " + Expression + "\n");
////                //Continueloop = 0;
//            }
        }
    }


    // Function that handles reductions
   public static void Reduction(int method) {
        switch(method) {
            case 1:
            {
                //reduce E -> E+T
                for (int i=0; i <2 ; i++){
                    symbol.pop();
                    state.pop();
                }
                if(state.peek() == 0)
                {
                    symbol.push("E");
                    state.push(1);
                }
                else if(state.peek() == 4)
                {
                    symbol.push("E");
                    state.push(8);
                }
                break;
            }
            case 2:
            {
                //reduce E -> T
                symbol.pop();
                state.pop();
                if(state.peek() == 0)
                {
                    symbol.push("E");
                    state.push(1);
                }
                else if(state.peek() == 4)
                {
                    symbol.push("E");
                    state.push(8);
                }
                break;
            }
            case 3:
            {
                //reduce T -> T*F
                for (int i=0; i <2 ; i++){
                    symbol.pop();                             
                    state.pop();
                }
                if(state.peek() == 0 || state.peek() == 4)
                {
                    symbol.push("T");
                    state.push(2);
                }
                else if(state.peek() == 6)
                {
                    symbol.push("T");
                    state.push(9);
                }
                break;
            }
            case 4:
            {
                //reduce T -> F
                symbol.pop();
                state.pop();
                if(state.peek() == 0 || state.peek() == 4)
                {
                    symbol.push("T");
                    state.push(2);
                }
                else if(state.peek() == 6)
                {
                    symbol.push("T");
                    state.push(9);
                }
                break;
            }
            case 5:
            {
                //reduce F -> (E)
                for (int i=0; i <3 ; i++){
                    symbol.pop();
                    state.pop();
                }
                if(state.peek() == 0 || state.peek() == 4 || state.peek() == 6)
                {
                    symbol.push("F");
                    state.push(3);
                }
                else if(state.peek() == 7)
                {
                    symbol.push("F");
                    state.push(10);
                }
                break;
            }
            case 6:
            {
                //reduce F -> id
                symbol.pop();
                state.pop();
                if(state.peek() == 0 || state.peek() == 4 || state.peek() == 6) {
                    symbol.push("F");
                    state.push(3);
                }
                else if(state.peek() == 7) {
                    symbol.push("F");
                    state.push(10);
                }
                break;
            }
        }
    }
}
