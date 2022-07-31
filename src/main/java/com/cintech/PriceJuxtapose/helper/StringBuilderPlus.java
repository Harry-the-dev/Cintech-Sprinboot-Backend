package com.cintech.PriceJuxtapose.helper;

public class StringBuilderPlus {


        private StringBuilder sb;

        public StringBuilderPlus(){
            sb = new StringBuilder();
        }

        public void append(String str) {
            if (str != null) {
                sb.append(str);
            }
        }

        public void appendLine(String str) {
            if (str != null) {
                sb.append(str).append(System.getProperty("line.separator"));
            }
        }

        public String toString() {
            return sb.toString();
        }

}
