
package huffman;

/**
 *
 * @author yashbee313839
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import javax.swing.JOptionPane;



public class HuffmanCode {
	 static ArrayList<Node> string = new ArrayList<Node>() ;
    static ArrayList<Node> characters = new ArrayList<Node> ();
    static TreeMap<Character,String> tree = new TreeMap<>();
    static String input ;
    static char[] temp ;
    static String Encoded;
    static String Decoded;

    static ArrayList<Node> string2 = new ArrayList<Node>() ;
    static ArrayList<Node> characters2 = new ArrayList<Node> ();
    static String input2 ;
    static char[] temp2 ;
    static String Encoded2;
    static String Decoded2;


    public static void main(String[] args) {


        settingFrequency();
        System.out.println("The Characters with their Initial Frequencies");
        for(int i = 0 ; i <string.size();i++ ){
            System.out.println("'"+string.get(i).data + "'"+ "  :  " + string.get(i).freq);
            System.out.println("---------------");
        }
        makingTree();
        String tString ="";
        Node nFinal = string.get(0);
        Node n =  nFinal ;
        System.out.println(string.size());
        traverse1(nFinal , tString);
        traverse(nFinal);
        //PRINT THE CHARACTERS OF THE STRING

        System.out.println("======================");
        System.out.println("Characters after Encoding : ");
        System.out.println();
        for(int j=0;j<characters.size();j++){
            System.out.println(tree.get(characters.get(j).data.charAt(0)) + " : " + characters.get(j).data);
         }
        System.out.println("The Message before Encoding is : " + input);

        Encoded = "";
        for(int j = 0 ; j < input.length() ; j++){
            Encoded = Encoded + tree.get(input.charAt(j));
        }

        System.out.println("The Message after Encoding : "  + Encoded);
        Decoded = Encoded ;
        decode(Decoded,n);

        settingFrequency2();
        System.out.println("===============================");
        System.out.println("Second Input Section");
        if(checkTheSame() == true){
            System.out.println("The Characters of the Second Input with their Initial Frequencies");
            for(int i = 0 ; i <string2.size();i++ ){
            System.out.println("'"+string2.get(i).data + "'"+ "  :  " + string2.get(i).freq);
            System.out.println("---------------");
            }
             Encoded2 = "";
        for(int j = 0 ; j < input2.length() ; j++){
            Encoded2 = Encoded2 + tree.get(input2.charAt(j));
        }
            System.out.println("Encoded Input 2 is : " + Encoded2);

            Decoded2 = "";
            Decoded2 = Encoded2 ;
            decode(Decoded2,n);
        }

        else {
            System.out.println("ERROR: \nThere are characters in the second input that doesn't match the Characters in the First One ");
        }

    }
    public static void settingFrequency(){
        input = JOptionPane.showInputDialog("Enter String to be encoded");
        temp = input.toCharArray();
        for(int i=0;i<temp.length;i++){
            String t = temp[i] + "";
            Node n = new Node (t);
            if(!contains(string,t)){
            string.add(n);
            characters.add(n);
            }
            else if(contains(string,t)) {
                int index = getIndex(string,t);
                string.get(index).freq++;

            }
        }
    }
    public static void settingFrequency2(){
        input2 = JOptionPane.showInputDialog("Enter String to be encoded");
        temp2 = input2.toCharArray();
        for(int i=0;i<temp2.length;i++){
            String t = temp2[i] + "";
            Node n = new Node (t);
            if(!contains(string2,t)){
            string2.add(n);
            characters2.add(n);
            }
            else if(contains(string2,t)) {
                int index = getIndex(string2,t);
                string2.get(index).freq++;

            }
        }
    }
    public static boolean checkTheSame(){
        boolean same = true ;
        for(int i=0;i<temp2.length;i++){
            if(!contains(characters,temp2[i]+"")){
                same = false;
                break;
            }
        }
        return same ;
    }
    public static void makingTree(){
        Collections.sort(string);
        while(string.size() > 1){

            Node n = (Node) string.get(0) ;
            Node m = (Node) string.get(1);
            int tempFreq = n.freq + m.freq ;
            Node nw = new Node (n.data+m.data);
            nw.addChild(n, m);
            string.remove(0);
            string.remove(0);
            string.add(nw);
            nw.freq = tempFreq;
            Collections.sort(string);
                    }
    }
    public static void traverse1(Node n ,String s){
        if(n.left != null){
            traverse1(n.left,s+"0");
        }
        if (n.right!=null){
            traverse1(n.right,s+"1");
        }
        else if ( n.right == null  && n.left==null)
        {
            tree.put(n.data.charAt(0), s);
            }
        }

        public static void traverse(Node n){
        if(n.left != null){
            traverse(n.left);
        }
         if ( n.right == null  && n.left==null)
        {
            System.out.println(n.data);
            }
        if (n.right!=null){
            traverse(n.right);
        }

        }
    public static boolean contains(ArrayList<Node> list, String name) {
    for (Node item : list) {
        if (item.getData().equals(name)) {
            return true;
        }
    }
    return false;
}
     public static int getIndex(ArrayList<Node> list, String name) {
    int c=0;
         for(int i =0 ; i <list.size();i++){
             if(list.get(i).data.equals(name)){
                 break;
             }
             c++;
    }
    return c;
    }
     public static void decode(String s,Node l){
         Decoded="";
          Node n = l;
         for(int i = 0;i<s.length();){
            Node node = n ;
            while(node.left!=null && node.right !=null && i < s.length()){
                if((s.charAt(i)) == '1')
                    node = node.right;
                else
                    node = node.left;
                i++;

            }
            Decoded += node.data;
         }
         System.out.println("Decoded Text is = " + Decoded);
     }

}