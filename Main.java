//package Compiler;
//
//import java.io.*;
//import java.util.*;
//import java.io.File;
//
//
//public class Main {
//        public static String str = null;
//
//    public static void main(String[] args) throws IOException {
//
//            while (true) {
//                System.out.println("Press '1' for Lexical Analysis");
//                System.out.println("Press '2' to Exit");
//                Scanner key = new Scanner(System.in);
//                System.out.printf("Enter Key: ");
//                String userName = key.nextLine();
//
//                if (userName.equalsIgnoreCase("1")) {
//
//                    try {
//                        File file = new File("C:\\Users\\MMC\\Documents\\NetBeansProjects\\compilerConstruction\\src\\main\\java\\Compiler\\input.txt");
//                        Lexer lexerClass = new Lexer(file);
//                        List<Tokens> tokenList = lexerClass.generateTokens();
//                        System.out.println("\t\tLEXICAL ANALYSIS");
//                        System.out.println("--------------------------------------------");
//                        System.out.println("Lexeme\t\tToken Name\t\tAttribute Value");
//                        System.out.println("---------------------------------------------");
//                       /* for (int i = 0; i < Llexeme.size(); i++) {
//                            System.out.printf("%s\t\t\t\t\t%s\t\t\t\t%s", Llexeme.get(i), LToken.get(i), LAttribute.get(i));
//                            System.out.printf("\n");
//                        }*/
//                       // System.out.println("\n");
//                        for (int i = 0; i < tokenList.size(); i++) {
//                            System.out.println(tokenList.get(i).toString());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//
//                    }
//                    //String[] array = new String[Lexer.SymbolTable];
//                    System.out.println("\n------------------------------------------\n");
//                    System.out.println("\t\t\tSYMBOL TABLE\n");
//                    System.out.println("--------------------------------------------\n");
//                    System.out.println("Attribute Value\t\tToken Name\t\t Value\n");
//                    System.out.println("---------------------------------------------\n");
//
//                    //System.out.println(SymbolTable.length);
//                    Formatter fmt = new Formatter();
//                    
//                    for (int i = 0; i < Lexer.Value.size(); i++) {
//                        fmt.format("%6s  %16s  %15s\n", Lexer.AttributeValue.get(i), Lexer.TokenName.get(i), Lexer.Value.get(i));
//                       // System.out.printf("%s\t\t\t|\t\t%s", AttributeValue.get(i), TokenName.get(i), Value.get(i));
//                        //System.out.printf("\n");
//                    }
//                    System.out.println(fmt);
//                    break;
//                } 
//                else if (userName.equalsIgnoreCase("2")) {
//                    System.out.println("Lexical Analysis & Syntax Analysis\n");
//                    try {
//                        File file = new File("src/com/company/input2.txt");
//                        Lexer lexerClass = new Lexer(file);
//                        List<Tokens> tokenList = lexerClass.generateTokens();
//                        System.out.println("\t\tLEXICAL ANALYSIS");
//                        System.out.println("--------------------------------------------");
//                        System.out.println("Lexeme\t\tToken Name\t\tAttribute Value");
//                        System.out.println("---------------------------------------------");
//                        for (int i = 0; i < tokenList.size(); i++) {
//                            System.out.println(tokenList.get(i).toString());
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//
//                    }
//
//                    System.out.println("\n------------------------------------------");
//                    System.out.println("\t\t\tSYMBOL TABLE");
//                    System.out.println("--------------------------------------------");
//                    System.out.println("Attribute Value\t\tToken Name\t\t Value");
//                    System.out.println("---------------------------------------------");
//
//                    //System.out.println(SymbolTable.length);
//                    Formatter fmt = new Formatter();
//                    for (int i = 0; i < Value.size(); i++) {
//                        fmt.format("%6s  %16s  %15s\n", AttributeValue.get(i), TokenName.get(i), Value.get(i));
//                    }
//                    System.out.println(fmt);
//                    System.out.println("-------------------------------------------");
//                    System.out.println("\t\tSyntax Analysis");
//                    System.out.println("-------------------------------------------");
//
//                    BufferedReader br = new BufferedReader(new FileReader("src/com/company/input2.txt"));
//                    String st;
//                    while ((st = br.readLine()) != null) {
//                        System.out.println("String Provided: "+st);
//                    }
//                    //  String str = String.valueOf(Parser);
//                    // System.out.println(str);
//                    str = Parser.get(0);
//                    for (int i = 1; i < Parser.size(); i++) {
//                        str += String.valueOf(Parser.get(i));//Parser.get(i);
//                    }
//                    String finalString = "";
//                    // separate the string in tokens
//                    int index = 0;
//                    while (index < str.length()) {
//                        if (str.charAt(index) == '(' || str.charAt(index) == '*' || str.charAt(index) == '+' || str.charAt(index) == ')')
//                            finalString += " " + str.charAt(index) + " ";
//                        else
//                            finalString += str.charAt(index);
//                        index++;
//                    }
//                   // System.out.println("String Provided: " + finalString);
//                   // System.out.println(finalString);
//                    // tokenizing the input string
//                    Parser.string_token = new Parser.StringTokenizer(finalString + " $");
//                    Parser.StringAt = Parser.string_token.nextToken().intern();
//                    if (Parser.StringAt.matches(Parser.checkString)) {
//
//                    Parser.StringAt = "i";
//                }
//                    Parser.StringInsertion();
//                    Parser.Parser();
//                  break;
//                }
//                else if (userName.equalsIgnoreCase("2")) {
//                    System.exit(0);
//                } else {
//                    System.out.println("WRONG KEY ENTERED. TRY AGAIN\n");
//                }
//            }
//
//
//       /*
//
//                    try {
//
//                FileReader f = new FileReader("src/com/company/input.txt");
//                        System.out.println("Lexeme\t\tToken Name\t\tAttribute Value");
//                Scanner s = new Scanner(f);
//                String source = " ";
//                while (s.hasNext()) {
//                    source += s.nextLine() + "\n";
//                }
//                Lexical l = new Lexical(source);
//                l.printTokens();
//            } catch(FileNotFoundException e) {
//                error(-1, "Exception: " + e.getMessage());
//            }*/
//
//
//    }
//}
