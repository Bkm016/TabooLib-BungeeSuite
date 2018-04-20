/** 
 * @author Caiyong 
 * @version 1.0 
 */  
package me.skymc.taboolib.bungeesuite.util;  
  
public class TextUtils {    

    public static double similarDegree(String strA, String strB){     
        String newStrA = removeSign(max(strA, strB));      
        String newStrB = removeSign(min(strA, strB));  
        int temp = Math.max(newStrA.length(), newStrB.length());      
        int temp2 = longestCommonSubstring(newStrA, newStrB).length();     
        return temp2 * 1.0 / temp;      
    }  
    
    private static String max(String strA, String strB) {
    	return strA.length() >= strB.length() ? strA : strB;
    }
    
    private static String min(String strA, String strB) {
    	return strA.length() < strB.length() ? strA : strB;
    }
    
    private static String removeSign(String str) {     
        StringBuffer sb = new StringBuffer();   
        for (char item : str.toCharArray()) { 
            if (charReg(item)){      
                sb.append(item);    
            }
        }
        return sb.toString();
    }    
    
    private static boolean charReg(char charValue) {      
        return (charValue >= 0x4E00 && charValue <= 0X9FA5) || (charValue >= 'a' && charValue <= 'z') || (charValue >= 'A' && charValue <= 'Z')  || (charValue >= '0' && charValue <= '9');      
    }      
    
    private static String longestCommonSubstring(String strA, String strB) {     
        char[] chars_strA = strA.toCharArray();  
        char[] chars_strB = strB.toCharArray();   
        int m = chars_strA.length;     
        int n = chars_strB.length;   
          
        int[][] matrix = new int[m + 1][n + 1];     
        for (int i = 1; i <= m; i++) {    
            for (int j = 1; j <= n; j++) {      
                if (chars_strA[i - 1] == chars_strB[j - 1])     
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;      
                else     
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);     
            }     
        }  
        
        char[] result = new char[matrix[m][n]];      
        int currentIndex = result.length - 1;     
        while (matrix[m][n] != 0) {     
            if (matrix[n] == matrix[n - 1])    
                n--;     
            else if (matrix[m][n] == matrix[m - 1][n])      
                m--;     
            else {     
                result[currentIndex] = chars_strA[m - 1];     
                currentIndex--;    
                n--;     
                m--;    
            }    
        }      
       return new String(result);     
    }
}   