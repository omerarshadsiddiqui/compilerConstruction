package Compiler;



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MMC
 */
public class Lexer {

    BufferedReader reader;
    char character;
    List<Tokens> tokenList = new ArrayList<>();
   //protected ArrayList<String> keyWords = new ArrayList<>(Arrays.asList("int", "char", "string", "if", "else", "do", "while"));
   protected String intDigits = "^[0-9]+$";
   protected String Stringliteral = "(\\\"([^\\\\\\\"]|\\\\.)*\\\")";
   protected String singlequote = "(\\'([^\\\\\\']|\\\\.)*\\')";
   protected String floatDigits = "[0-9]+[.][0-9]+";
   protected String special = "[-.!\"`'#%&,:;<>=@{}~\\$\\(\\)\\*\\+\\/\\\\\\?\\[\\]\\^\\|]+";
   protected String unacceptable = "^([-.!\"`'#%&,:;<>=@{}~\\$\\(\\)\\*\\+\\/\\\\\\?\\[\\]\\^\\|]+|[0-9]+)+[a-zA-Z_0-9]*$";
    int value = 6;
    int count=1; //line count
    public static ArrayList<String> AttributeValue = new ArrayList<String>();
    public static ArrayList<String> TokenName = new ArrayList<>();
    public static ArrayList<String> Value = new ArrayList<>();
    public static ArrayList<String> Parser = new ArrayList<>(); // Adding Values for the Parser
    public Lexer(File file) throws IOException {
        AttributeValue.add("0");
        AttributeValue.add("1");
        AttributeValue.add("2");
        AttributeValue.add("3");        //Entering values for int- while for Symbol Table
        AttributeValue.add("4");
        AttributeValue.add("5");
        AttributeValue.add("6");
        TokenName.add("int");
        TokenName.add("char");
        TokenName.add("string");
        TokenName.add("if");
        TokenName.add("else");
        TokenName.add("do");
        TokenName.add("while");
        Value.add("-");
        Value.add("-");
        Value.add("-");
        Value.add("-");
        Value.add("-");
        Value.add("-");
        Value.add("-");
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        character = readNextCharacter();

    }

    List<Tokens> generateTokens() throws IOException {
        Tokens token = readNextToken();
        while (token != null) {
            tokenList.add(token);
            token = readNextToken();
        }
        return tokenList;
    }

    Tokens readNextToken() throws IOException {
        int state = 1;
        while (true) {
            if (character == (char) -1) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            if (character == '\n') {
                count++;
            }

            switch (state) {
                case 1: {
                    if (character == ' ' || character == '\n' || character == '\t' ||           //Ignoring the whitspaces
                            character == '\f' || character == '\b' || character == '\r') {
                        character = readNextCharacter();
                        continue;
                    } else if (character == '/') {          //Removing single line comments
                        character = readNextCharacter();
                        if (character == '/') {
                            character = readNextCharacter();
                            while (character != '\n') {
                                character = readNextCharacter();
                            }
                            character = readNextCharacter();
                            count++;
                             return new Tokens("", "Single line comment Found", "", "At line: "+(count-1));
                        } else if (character == '*') {          //Removing
                            character = readNextCharacter();
                            while (character != '*' && (character = readNextCharacter()) != '/') {
                                character = readNextCharacter();
                            }

                            do {
                                character = readNextCharacter();
                            } while (character != '\n');
                            {
                                character = readNextCharacter();
                            }
                            count++;
                            return new Tokens("","Multiline Comment Found","","At Line: "+(count-1));
                        }else{
                           // character = readNextCharacter();
                            Parser.add("/");
                            return new Tokens("ao", "/", "dv", "");
                        }

                    } else if (character == '<') {              //Reading Operators
                        character = readNextCharacter();
                        if (character == '=') {
                            character = readNextCharacter();
                            Parser.add("<=");
                            return new Tokens("ro ", "<=", "le", "");
                        } else if (character == '>') {
                            character = readNextCharacter();
                            Parser.add("<>");
                            return new Tokens("ro", "<>", "ne", "");
                        } else {
                            Parser.add("<");
                            return new Tokens("ro", "<", "lt", "");
                        }
                    } else if (character == '>') {
                        character = readNextCharacter();
                        if (character == '=') {
                            Parser.add(">=");
                            character = readNextCharacter();
                            return new Tokens("ro", ">=", "ge", "");
                        } else{
                            Parser.add(">");
                        return new Tokens("ro", ">", "gt", "");
                    }
                    }else if (character == '=') {
                        character = readNextCharacter();
                        if (character == '=') {
                            Parser.add("==");
                            character = readNextCharacter();
                            return new Tokens("ro", "==", "eq", "");
                        } else {
                            Parser.add("=");
                           return new Tokens("oo", "=", "as", "");
                        }
                    } else if (character == '+') {
                        character = readNextCharacter();
                       // System.out.println("+\tao\tad");
                        Parser.add("+");
                        return new Tokens("ao", "+", "ad", "");
                    } else if (character == '-') {
                        character = readNextCharacter();
                        //System.out.println("-\tao\tsb");
                        Parser.add("-");
                        return new Tokens("ao", "-", "sb", "");
                    } else if (character == '*') {
                        character = readNextCharacter();
                        //System.out.println("*\tao\tml");
                        Parser.add("*");
                        return new Tokens("ao", "*", "ml", "");
                    } else if (character == '/') {
                        character = readNextCharacter();
                        //System.out.println("/\tao\tdv");
                        Parser.add("/");
                        return new Tokens("ao", "/", "dv", "");
                    } else if (character == ';') {
                        character = readNextCharacter();
                        //System.out.println(";\too\ttr");
                        Parser.add(";");
                        return new Tokens("oo", ";", "tr", "");
                    } else if (character == '{') {
                        character = readNextCharacter();
                        Parser.add("{");
                        //System.out.println("{\too\tob");
                        return new Tokens("oo", "{", "ob", "");
                    } else if (character == '}') {
                        character = readNextCharacter();
                        //System.out.println("}\too\tcb");
                        Parser.add("}");
                        return new Tokens("oo", "}", "cb", "");
                    } else if (character == '(') {
                        character = readNextCharacter();
                        //System.out.println("(\too\top");
                        Parser.add("(");
                        return new Tokens("oo", "(", "op", "");
                    } else if (character == ')') {
                        character = readNextCharacter();
                        //System.out.println(")\too\tcp");
                        Parser.add(")");
                        return new Tokens("oo", ")", "cp", "");
                    } else {
                        state = 2;
                        continue;
                    }

                }
                case 2: {
                    if (isAlphabet(character) || character == '_') {    //Check for Keywords & identifiers
                        String word = String.valueOf(character);
                        for (; ; ) {
                            character = readNextCharacter();
                            if (isAlphabet(character) || character == '_' || isDigit(character)) { //[\w]+
                                word += String.valueOf(character); //text = text+a;
                            } else {

                                if (word.equals("int")) {
                                    //System.out.println("int\tint\t0");
                                        return new Tokens("int",word,"0","");

                              //  return new Tokens("int",word,"0","");
                                }else if (word.equalsIgnoreCase("int")){
                                    return new Tokens("Does not belong to the language",word,"","Error in line: "+count);
                                }
                                if (word.equals("char")) {
                                  //  System.out.println("char\t"+word"\t0");
                                    return new Tokens("char", word, "1", "");
                                }else if (word.equalsIgnoreCase("char")){
                                    return new Tokens("Does not belong to the language",word,"","Error in line: "+count);
                                }
                                if (word.equals("String")) {
                                        return new Tokens("String", word, "3", "");
                                }else if (word.equalsIgnoreCase("String")){
                                    return new Tokens("Does not belong to the language",word,"","Error in line: "+count);
                                }
                                if (word.equals("if")) {
                                        return new Tokens("if", word, "3", "");
                                    }else if (word.equalsIgnoreCase("if")){
                                    return new Tokens("Does not belong to the language",word,"","Error in line: "+count);
                                }
                                if (word.equals("else")) {

                                        return new Tokens("else", word, "4", "");

                                }else if (word.equalsIgnoreCase("else")){
                                    return new Tokens("Does not belong to the language",word,"","Error in line: "+count);
                                }
                                if (word.equals("do")) {
                                    return new Tokens("do", word, "5", "");
                                }else if (word.equalsIgnoreCase("do")){
                                    return new Tokens("Does not belong to the language",word,"","Error in line: "+count);
                                }
                                if (word.equals("while")) {
                                    return new Tokens("while", word, "6", "");
                                }else if (word.equalsIgnoreCase("while")){
                                    return new Tokens("Does not belong to the language",word,"","Error in line: "+count);
                                }
                                /*if (word.equalsIgnoreCase("while")) {
                                    //SymbolTable = new String[]{"5", "while", "-"};
                                    return new Tokens("while", word, "6", "");
                                }*/else {
                                    if(!Value.contains(word)){
                                        int a = ++value;
                                        AttributeValue.add(String.valueOf(a));
                                        TokenName.add("id");
                                        Value.add(word);
                                        Parser.add(word);
                                        //System.out.println("id\t "+word+"\t"+a);
                                      return new Tokens("id", word, " " +a, "");
                                    }
                                    else{
                                        int s = Value.indexOf(word);
                                        String val = AttributeValue.get(s);
                                        Parser.add(word);
                                       // word.delete(0, word.length());
                                       // current = readNextCharacter();
                                       // System.out.println("");
                                        //word="";
                                        return new Tokens("id", word, " " +val, "");
                                        // return new Tokens("", "", "","");
                                    }
                                     //   continue;
                                       // System.out.printf("\n");
                                      //  return ;
                                        //generateTokens();
                                        //current = readNextChar();
                                   // int a = ++value;

                                        //SymbolTable.get(Integer.parseInt(String.valueOf(pos-2)));
                                        //SymbolTable = new String[]{String.valueOf(a), "id", word};
                                        //SymbolTable = new String[]{String.valueOf(a), "id", word};
//                                        return new Tokens("id", word, " " + a, "");
                                }
                            }
                        }
                    } else if(isDigit(character) || special.matches(String.valueOf(character))){
                        String text = String.valueOf(character);
                        for (; ; ) {
                            character = readNextCharacter();
                            if (isAlphabet(character) || character == '_' || isDigit(character)) {
                                text += String.valueOf(character);
                            } else if(text.matches(this.intDigits)){
                                if(!Value.contains(text)){
                                    int v = ++value;
                                    AttributeValue.add(String.valueOf(v));          //Check for integer values
                                    TokenName.add("in");
                                    Value.add(text);
                                    Parser.add(text);
                                   // System.out.println("in\t "+text+"\t"+v);
                                    return new Tokens("in", text, " " + v, "");
                                }else {
                                    Parser.add(text);
                                    int s = Value.indexOf(text);
                                    String val = AttributeValue.get(s);
                                    //System.out.println("");
                                    return new Tokens("in", text, " " +val, "");
                                    //  return new Tokens("","", "", "");
                                    //generateTokens();
                                    //  current = readNextChar();
                                }
//                                    int v = ++value;
//                                    SymbolTable.add(String.valueOf(v));
//                                    SymbolTable.add("in");
//                                    SymbolTable.add(text);
//                                    // SymbolTable = new String[]{String.valueOf(n), "in", num};
//                                    return new Tokens("in", text, " " + v, "");
                            }
                            else {
                                if (text.matches(unacceptable)) { //Check for values that are unacceptable and may cause error
                                   // System.out.println("Token does not belong to the language "+ current+"\tError in Line: " + count);
                                    return new Tokens("Token does not belong to the language", text, " ", "Error in Line: " + count);
                                }
                            }
                        }
                    }
                     else {
                        state = 3;
                        continue;
                    }
                }
                case 3: {
                    if (isDigit(character)) {
                        String num = String.valueOf(character);
                        for (; ; ) {
                            character = readNextCharacter();
                            if (isDigit(character)) {
                                num += String.valueOf(character);

                            } else {
                                if (num.matches(this.intDigits)) {      //Check for Integer
                                    if(!Value.contains(num)){
                                        int n = ++value;
                                        Parser.add(num);
                                        // int a = ++value;
                                        AttributeValue.add(String.valueOf(n));
                                        TokenName.add("in");
                                        Value.add(num);
                                     //   System.out.println("in\t "+num+"\t"+n);
                                        return new Tokens("in", num, " " +n, "");
                                    }else {
                                        Parser.add(num);
                                        int s = Value.indexOf(num);
                                        String val = AttributeValue.get(s);
                                        //     System.out.println("");
                                        //return null;
                                        return new Tokens("in", num, " " +val, "");
                                    }   //generateTokens();
                                        //current = readNextChar();
                                        // return null;
                                    // int n = ++value;

                                    //SymbolTable.add(String.valueOf(n));
                                    //SymbolTable.add("in");
                                    //SymbolTable.add(num);
                                    // SymbolTable = new String[]{String.valueOf(n), "in", num};
                                    //return new Tokens("in", num, " " + n, "");
                                } else if (num.matches(this.floatDigits)) {
                                    //System.out.println("Token does not belong to the language "+ current+"\tError in Line: " + count);
                                    return new Tokens("Token does not belong to the language", num, " ", "Error in Line: " + count);
                                } else
                                    //System.out.println("Token does not belong to the language "+ current+"\tError in Line: " + count);
                                    return new Tokens("Token does not belong to the language", num, " ", "Error in Line: " + count);
                            }
                        }
                    } else if (character == '"') {
                        String word = String.valueOf(character);        //Check for String literals
                        // current = readNextChar();
                        do {
                            character = readNextCharacter();
                            if (isStringLiteral(character)) {
                                word += String.valueOf(character);
                            } else {
                                if (word.matches(this.Stringliteral)) {
                                    //int s = ++value;
                                    if(!Value.contains(word)){
                                        int s = ++value;
                                        Parser.add(word);
                                        AttributeValue.add(String.valueOf(s));
                                        TokenName.add("sl");
                                        Value.add(word);
                                    //    System.out.println("sl\t "+word+"\t"+s);
                                        return new Tokens("sl", word, " " +s, "");
                                    }else{
                                        Parser.add(word);
                                        int sl = Value.indexOf(word);
                                        String val = AttributeValue.get(sl);
                                    //    System.out.println("");
                                        //return null;
                                        return new Tokens("sl", word, " " +sl, "");
                                        }
                                }else
                                    //System.out.println("Token does not belong to the language "+ current+"\tError in Line: " + count);
                                    return new Tokens("Token does not belong to the language", word, " ", "Error in Line: " + count);
                            }
                            //current = readNextChar();
                        } while (character != '\n');
                    }
                    else if (character == '\'') {
                        String word = String.valueOf(character);
                        // current = readNextChar();
                        do {
                            character = readNextCharacter();        //Check for single quotes , error detected.
                            if (isSingleQuote(character)) {
                                word += String.valueOf(character);
                            } else {
                                if (word.matches(this.singlequote)) {
                                    //int s = ++value;
                                    return new Tokens("Token does not belong to the language", word, " ", "Error in Line: " + count);

                                }else
                                    //System.out.println("Token does not belong to the language "+ current+"\tError in Line: " + count);
                                    return new Tokens("Token does not belong to the language", word, " ", "Error in Line: " + count);
                            }
                            //current = readNextChar();
                        } while (character != '\n');
                    }
                     else {
                        character = readNextCharacter();
                        return new Tokens("Token does not belong to the language", " " + character, " ", "Error in Line: " + count);
                        //System.out.println("Token does not belong to the language "+ current+"\tError in Line: " + count);
                     }
                }

            }
        }

    }

    char readNextCharacter() {      //For reading next character from file
        try {
            return (char) reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (char) -1;
    }

    boolean isDigit(char c) {       //Check if current character is a Digit or not
        if (c >= '0' && c <= '9'|| c == '.')
            return true;

        return false;
    }
    boolean isStringLiteral (char c){ //Check if current character is a String literal or not
       if (c >= '"' && c <= '"')
           return true;
        if (c >= 'a' && c <= 'z')
            return true;
        if (c >= 'A' && c <= 'Z')
            return true;
        return false;
    }

    boolean isAlphabet(char c) {       //Check if current character is a Alphabet or not
        if (c >= 'a' && c <= 'z')
            return true;
        if (c >= 'A' && c <= 'Z')
            return true;

        return false;
    }
    boolean isSingleQuote (char c){     //Check if current character is a Single Quote or not
        if (c >= '\'' && c <= '\'')
            return true;
        if (c >= 'a' && c <= 'z')
            return true;
        if (c >= 'A' && c <= 'Z')
            return true;
        return false;
    }
}

