/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j_peg;

/**
 *
 * @author Mohamed
 */
public class Node {
    public String tag; 
    public int freq;
    public String nodeCode;
    public Node left;
    public Node right;
    public Node parent;
    public String catgory;
    boolean flag;
    public  Node(){
        tag="";
        freq = 0; 
        nodeCode = "";
        catgory="";
        flag=false;
    }
}
