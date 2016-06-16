/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchprosys;

import javax.swing.JOptionPane;

/**
 *
 * @author prathibha
 */
public class BatchFunction {

    DBoperationsFunction DBOF = new DBoperationsFunction();
    //calculate avalable time
    int AvailableTime(int min, int opPlusemchin) {
        return min * opPlusemchin;
    }

    //int AvailableTime= 700*15;
    //just a method to show the thing that what did in it
    double rai(int orderArticle, int availabletime) {
        int qunatity = DBOF.getOrderQunatity(orderArticle);
        return qunatity / availabletime;

    }
    //get setup times on relevant order article
    double setupTime(int orderArticle) {
        return DBOF.getsetuptime(orderArticle);

    }
    //get avarage of all setup times on all sections
    double avarageSetupTime(int[] orderArticleArray) {
        double sum = 0;
        for (int i = 0; i < orderArticleArray.length; i++) {
            sum += setupTime(orderArticleArray[i]);
            //System.out.println("sum--"+sum);

        }
        return sum/orderArticleArray.length;
    }
    
    
    //calculate the L function using av time allrasisti and utilization
    double L(double aveargrSetupTime, double allraisiti,double x) {
        double L = ((allraisiti /x) + aveargrSetupTime);
        return L;
        
    }
    //get batch quantity of relevent order article
    int[] batchQuantity(double L, int[] orderArticleArray) {
        //int quantity = 0;
        int array[]=new int[orderArticleArray.length];
        for (int i = 0; i < orderArticleArray.length; i++) {
            if (DBOF.batchQuantity(L, orderArticleArray[i])>0){
                array[i]= DBOF.batchQuantity(L, orderArticleArray[i]);
            }
            else{
                array[i]= 1;
            }

        }
        return array;
    }
    
    //divide order q from batch q
    int[] noofbatches(int[] orderq,int[] batchq){
        int NOB[]=new int[orderq.length];
        for(int i=0;i<orderq.length;i++){
            if(batchq[i]>0){
                NOB[i]=orderq[i]/batchq[i];
            }
            else{
                NOB[i]=1;
            }
            
        }
        return NOB;
    }
    
    int[] FinalFunction(int[] orderArticleArray,int ATmin,int opPLUSmchine){
        int AT=AvailableTime(ATmin,opPLUSmchine);
        //System.out.println("1");
        
        double GAraisiti=DBOF.getAllraisiti(orderArticleArray, AT);//get the multification of setup time section wise ime and avarage time
        //System.out.println("2-raisiti-"+GAraisiti);
        double AST=avarageSetupTime(orderArticleArray);
        //System.out.println("3-ast-"+AST);
        double AllUtiliz=Math.sqrt(DBOF.getAllUtisum(orderArticleArray, AT))-DBOF.getAllUtisum(orderArticleArray, AT);//get utilizatin difirrence
        double L=L(AST, GAraisiti,AllUtiliz);//calculate L function
        System.out.println("utilaization 22 -"+DBOF.getAllUtisum(orderArticleArray, AT));
        System.out.println("utilaization -"+AllUtiliz);
        int[] BQ=batchQuantity(L, orderArticleArray);// return batch q array
        //System.out.println("5");
        return BQ;
    
    }
    
}
