/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        BigInteger [] arr = getPandQ();
        System.out.println("Please enter a number less than 100");
        BigInteger m = new BigInteger(in.nextLine());
        BigInteger p = arr[0];
        BigInteger q = arr[1];
        BigInteger n = p.multiply(q);
        BigInteger fn = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
        BigInteger e = gcd(fn);// this is the public key
        BigInteger d = getD(e, fn);// this is the private key
        BigInteger c = signing(m, d, n);
        BigInteger M = verification(c, e, n);
        
        System.out.println("Message = "+m.toString());
        System.out.println("p = "+p.toString());
        System.out.println("q = "+q.toString());
        System.out.println("n = "+n.toString());
        System.out.println("fn = "+fn.toString());
        System.out.println("e = "+e.toString());
        System.out.println("d = "+d.toString());
        System.out.println("Signed Text = "+c.toString());
        System.out.println("Unsigned Text = "+M.toString());
        
        
    }
    
    public static BigInteger signing(BigInteger m, BigInteger d, BigInteger n){
        // we sing the message using private key
        BigInteger pow = m.pow(Integer.parseInt(d.toString()));
        return pow.mod(n);
    }
    
    public static BigInteger verification(BigInteger c, BigInteger e, BigInteger n){
        // we verify the message using public key
        BigInteger pow = c.pow(Integer.parseInt(e.toString()));
        return pow.mod(n);
    }
    
    public static BigInteger getD (BigInteger e, BigInteger fn){
        return e.modInverse(fn);
    }
    
    
    public static BigInteger gcd(BigInteger fn){
        BigInteger e = new BigInteger("10");
        BigInteger gcd;
        while (true){
            gcd = e.gcd(fn);
            if (e.compareTo(fn) == -1 && gcd.equals(BigInteger.ONE)) {
                break;
            }
            e = e.add(BigInteger.ONE);
        }
        return e;
    }
    public static BigInteger[] getPandQ(){
        BigInteger [] arr = new BigInteger [2];
        Random r = new Random();
        
        int p = r.nextInt(100)+10;
        int q = r.nextInt(100)+10;
        
        while(true){
            if (!isPrime(p)){
                p = r.nextInt(100)+10;
            }
            if(!isPrime(q)){
                q = r.nextInt(100)+10;
            }
            if(p == q){
                q = r.nextInt(100)+10;
            }
            if(isPrime(p) && isPrime(q) && p!=q){
                break;
            }
        }
        
        arr[0] = BigInteger.valueOf(p);
        arr[1] = BigInteger.valueOf(q);
        return arr;
    }
    
    public static boolean isPrime (int x){
        if (x == 1 || x == 2){
            return true;
        }
        for (int i = 2;i<=(x/2);i++){
            
            if(x % i == 0){
                return false;
            }
            
        }
        return true;
    }
}
