package Tree;

public class maxString {
    public static void main(String[] args) {

            String str ="AABABBA";
            int k =2;
        int value= characterReplacement(str,k);
        System.out.println(value);
    }

    public static int characterReplacement(String s, int k) {
        int[] a = new int[26];
        int max=0;
        int res=0;
        int left =0;
        for (int right =0; right<s.length();right++){
            a[s.charAt(right)-'A']++;


        }
        return res;


    }
}
