package Compiler;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MMC
 */
public class Tokens {
    String token;
    String lexeme;
    String value;
    String line;

    public Tokens(String token, String lexeme, String value, String  line) {
        this.token = token;
        this.lexeme = lexeme;
        this.value = value;
        this.line = line;
    }

    public String toString() {

        return Print(lexeme,token,value,line);
    }
    String Print(String l, String t, String v, String li){
        String Lexemes=l;
        String tokentype = t;
        String lin = li;
        for(int i=l.length() ; i<16 ; i++){
            Lexemes+="  ";
        }
        for(int i=v.length();i<16;i++){
            tokentype+="  ";
        }
        for (int i=t.length();i<16;i++){
            lin+="   ";
        }

        return Lexemes+tokentype+value+lin;
    }
}