package com.target.practice;

public class MagyFirstPR {
    public static void main(String[] args) {

        String name = "i love my dogs";
        String reversed = "";
        for (int i = name.length()-1; i > 0; i--) {
            reversed += name.charAt(i);
        }
        System.out.println(reversed);
    }
}
