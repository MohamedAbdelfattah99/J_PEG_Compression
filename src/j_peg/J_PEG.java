/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j_peg;

import static java.lang.Math.abs;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import javax.print.DocFlavor;

/**
 *
 * @author Mohamed
 */
public class J_PEG {
public static  String text="-2,0,0,2,0,0,3,2,0,1,0,0,-2,0,-1,0,0,1,0,0,-1,0,0,0,0,0,0,0,0,0";
 public static Vector<Node>Tags=new Vector<>();
  public static Vector<Node>decompresd=new Vector<>();

    public static String Convert_binary(int x) {
        int num=x;
        String temp="";
       String number= Integer.toBinaryString(abs(num));
        if(x<0){
            for(int i=0;i<number.length();i++){
                if(number.charAt(i)=='0'){
                    temp+="1";
                   
                }
                else {
                   temp+="0";
                }
            }
        }
        else{
            temp=number;
        }
        return temp;
        
    }
public  static  void Create_tages(){
    int counter=0;
    String eof=""; 
    String []arr=text.split(",");   
    for(int i=0;i<arr.length;i++){
        if(arr[i].equals("0")){
            counter++;
            eof+=arr[i];
         }
        else if(arr[i]!="0"){
            String zeros=Integer.toString(counter);
            String temp=Convert_binary(Integer.valueOf(arr[i]));
            Node obj=new Node();
            obj.catgory=temp;
            int size=temp.length();
            String catagory=Integer.toString(size);
            
            String Tag=zeros+"/"+catagory;
          
            obj.tag=Tag;
            
            Tags.add(obj);
           Tag="";
           counter=0;
           eof="";
        }         
    }
   Node obj=new Node();
   obj.tag=eof;
   Tags.add(obj);
    
}

    public static void Codes()
    {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(StandardHuffman.root);
        while (!queue.isEmpty()) 
        {
            Node tempNode = queue.poll();
            for(int i = 0; i < Tags.size(); i++)
            {
                if(tempNode.tag.equals(Tags.get(i).tag))
                    Tags.get(i).nodeCode = tempNode.nodeCode;
            }
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
    public static String dec;
   public static void Compres_jegp(){
        Create_tages();
       Vector <Node>vec = StandardHuffman.calc_freq(Tags);
       StandardHuffman.Compress(vec);
       StandardHuffman.Code();
       Codes();
       String output="";
       for(int i=0;i<Tags.size();i++){
           output+=Tags.get(i).nodeCode+","+Tags.get(i).catgory+" ";
       }
       dec=output;
      // System.out.println(output);
   }
   
    public static void get_catagories() {
        
     String []arr=dec.split(" ");
     String codes="";
     String zeros="";
     for(int i=0;i<arr.length;i++){
         boolean flag=false;
        
         String x=arr[i]; 
         for(int j=0;j<x.length();j++){
             if(x.charAt(j)==','){
                 flag=true;
             }
             if(x.charAt(j)!=','&&flag==false){
                 codes+=x.charAt(j);
             }
               if(x.charAt(j)!=','&&flag==true){
                 zeros+=x.charAt(j);
             }
             
         }        
             for(int b=0;b<Tags.size();b++){
                 if(codes.equals(Tags.get(b).nodeCode)&&Tags.get(b).flag==false){
                     Node obj=new Node();
                     obj.tag=Tags.get(b).tag;
                     obj.catgory=Tags.get(b).catgory;
                     Tags.get(b).flag=true;
                     obj.flag=true;
                     decompresd.add(obj);
                     break;
                 }
             }
       
         codes="";
         zeros="";
        
     }                              
    }
    public static int binary_int(String x) {
        String temp="";
        int decimal=0;
    
       String []spilt=x.split("");
        if(spilt[0].equals("0")){
            for(int i=0;i<spilt.length;i++){
                if(spilt[i].equals("0")){
                    spilt[i]="1";
                    
                }
                else
                {
                    spilt[i]="0";
                }
            }
            for(int i=0;i<spilt.length;i++){
                temp+=spilt[i];
            }
            decimal=Integer.parseInt(temp,2);
            decimal=decimal*-1;

        }
        else {
            decimal=Integer.parseInt(x,2);

        }
        return decimal;
        
    }
    public static String split_tag() {
        String temp="";
        int num=0;
         for(int i=0;i<decompresd.size();i++){
             
             String y=decompresd.get(i).catgory;
    if(y!=""){         
          num=binary_int(y);
          String z=Integer.toString(num);
         char c=decompresd.get(i).tag.charAt(0);
          int size=Character.getNumericValue(c);
             
          for(int j=0;j<size;j++){
              temp+="0";
              temp+=",";
          }
          temp+=z;
          temp+=" / ";
    }
    else{
        temp+="eof";
    }
             
          
            
     }
    return temp;
        
    }
    

    public static void Decompress() {
     get_catagories();
     String answer=split_tag(); 
     System.out.println(answer);
        
    }
    public static void main(String[] args) {

Compres_jegp(); 
Decompress();
}
}
