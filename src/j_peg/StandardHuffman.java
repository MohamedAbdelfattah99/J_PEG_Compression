/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j_peg;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author ta7a
 */
public class StandardHuffman {

    public static Scanner input = new Scanner(System.in);
    public static Node root = new Node();
    public static String output = null;
    public static Vector<String> binary = new Vector<>();

    public static Vector calc_freq(Vector<Node> tags) {
        Vector<Node> myNodes = new Vector<>();
        Vector<String> myTags = new Vector<>();
        int counter = 0;
        boolean bool = false;
        for (int i = 0; i < tags.size(); i++) {
            String STR = tags.get(i).tag;
            for (int j = 0; j < myTags.size(); j++) {
                if (myTags.get(j).equals(STR)) {
                    bool = true;
                }
            }

            if (!bool) {
                myTags.add(STR);
                for (int k = 0; k < tags.size(); k++) {
                    if (STR.equals(tags.get(k).tag)) {
                        counter++;
                    }
                }
                Node temp = new Node();
                temp.freq = counter;
                temp.tag = STR;
                myNodes.add(temp);
            }

            counter = 0;
            bool = false;
        }
        return myNodes;
    }

    public static Vector Sort(Vector<Node> myNodes) {
        for (int i = 0; i < myNodes.size() - 1; i++) {
            for (int j = 0; j < myNodes.size() - i - 1; j++) {
                if (myNodes.get(j).freq < myNodes.get(j + 1).freq) {
                    String tag = myNodes.get(j).tag;
                    myNodes.get(j).tag = myNodes.get(j + 1).tag;
                    myNodes.get(j + 1).tag = tag;

                    int freq = myNodes.get(j).freq;
                    myNodes.get(j).freq = myNodes.get(j + 1).freq;
                    myNodes.get(j + 1).freq = freq;

                    String nodeCode = myNodes.get(j).nodeCode;
                    myNodes.get(j).nodeCode = myNodes.get(j + 1).nodeCode;
                    myNodes.get(j + 1).nodeCode = nodeCode;

                    Node left = myNodes.get(j).left;
                    myNodes.get(j).left = myNodes.get(j + 1).left;
                    myNodes.get(j + 1).left = left;

                    Node right = myNodes.get(j).right;
                    myNodes.get(j).right = myNodes.get(j + 1).right;
                    myNodes.get(j + 1).right = right;

                    Node parent = myNodes.get(j).parent;
                    myNodes.get(j).parent = myNodes.get(j + 1).parent;
                    myNodes.get(j + 1).parent = parent;

                }
            }
        }
        return myNodes;
    }

    public static void Compress(Vector<Node> myNodes) {
        while (myNodes.size() > 1) 
        {
            Sort(myNodes);
            root.right = myNodes.get(myNodes.size() - 1);
            root.left = myNodes.get(myNodes.size() - 2);
            root.right.parent = root;
            root.left.parent = root;

            Node temp = new Node();
            temp.freq = myNodes.get(myNodes.size() - 1).freq + myNodes.get(myNodes.size() - 2).freq;
            temp.tag = myNodes.get(myNodes.size() - 2).tag + "+" + myNodes.get(myNodes.size() - 1).tag;
            temp.left = myNodes.get(myNodes.size() - 2);
            temp.right = myNodes.get(myNodes.size() - 1);

            myNodes.remove(myNodes.size() - 1);
            myNodes.remove(myNodes.size() - 1);
            myNodes.add(temp);
        }
        root = myNodes.get(0);
    }

    public static void Code() 
    {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        root.nodeCode = "";
        while (!queue.isEmpty()) {

            Node tempNode = queue.poll();

            if (tempNode.left != null) {
                tempNode.left.nodeCode = tempNode.nodeCode + "0";
                queue.add(tempNode.left);

            }

            if (tempNode.right != null) {
                tempNode.right.nodeCode = tempNode.nodeCode + "1";
                queue.add(tempNode.right);

            }
        }

    }

    public static void Tree() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {

            Node tempNode = queue.poll();
            System.out.println("tag : " + tempNode.tag + "   freq : " + tempNode.freq + "  nodeCode : " + tempNode.nodeCode);
            output += tempNode.nodeCode;

            if (tempNode.left != null) {
                queue.add(tempNode.left);

            }

            if (tempNode.right != null) {
                queue.add(tempNode.right);

            }
        }
    }

}
