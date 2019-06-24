
package javaapplication26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *for output cases--
 //take root1 array--> for all roots
//take cost1 array-->for cost to those roots;
//take toller array--> to Insert toll between roots
 * @author Atul Kumar Dey
 */
public class DijkstraRootAndCostFinder {

    public static void main(String[] args) {
        //1st input--> no. of node # no. of edge
        //rest input--> From Node # To Node # Cost
        //Output --> All Path from Initial node to Final node and it's respective cost.
        
        String[] input1={"6#8","1#2#8","1#4#7","1#5#12","2#3#4","2#4#2","3#6#6","4#6#8","5#6#10"};
       
        String[] output=(applyTolls(input1));
      
        for(int i=0;i<output.length;i++){
            System.out.println(output[i]);
       }
       
    }
     public static String[] applyTolls(String[] input1)
    {
       

        int[] cost=new int[input1.length-1];
        int[] init=new int[input1.length-1];
        int[] end=new int[input1.length-1];
        //for Cost
        for(int i=0;i<cost.length;i++){
            int j=input1[i+1].length();
            int check=1;
            for(int k=j-1;k>0;k--){
                if(input1[i+1].charAt(k)=='#'){
                break;
            }else{ 
                    int du=Character.getNumericValue(input1[i+1].charAt(k))*check;
                    cost[i]=cost[i]+du;
                    check=check*10;                   
                    }
            }
        }
          //for Initial junction
        for(int i=0;i<init.length;i++){
            int j=input1[i+1].length();
            int check=1;
               int h1=0;       
            for(int k=0;k<j-1;k++)
            {                
               if(input1[i+1].charAt(k)=='#'){
                    break;
                }else{
                    h1=h1+1;
                }
            }
            for(int z=h1-1;z>=0;z--){             
                    int du=Character.getNumericValue(input1[i+1].charAt(z))*check;
                    init[i]=init[i]+du;
                    check=check*10;                                     
            }
    }
        //for End junction
     for(int i=0;i<end.length;i++){
            int j=input1[i+1].length();
            int check=1;
               int h1=j-1;
                int h2=0;       
            for(int k=0;k<j-1;k++)
            {                
               if(input1[i+1].charAt(k)=='#'){
                    break;
                }else{
                    h2=h2+1;
                }
            }
            for(int k=j;k>0;k--)
            {                
               if(input1[i+1].charAt(k-1)=='#'){
                    break;
                }else{
                    h1=h1-1;
                }
            }            
            for(int z=h1-1;z>h2;z--){      
                    int du=Character.getNumericValue(input1[i+1].charAt(z))*check;
                    end[i]=end[i]+du;
                    check=check*10;                                     
        }
     
    }
     //init==1 and final position==max
     int max=0;
     for(int i=0;i<end.length;i++){
         if(end[i]>max){
             max=end[i];
         }
      }int b=1;
      int f=max;
     
     
    //Making of array root & cost-take 1
     ArrayList root1=new ArrayList();
     ArrayList cost1=new ArrayList();
     ArrayList eqi=new ArrayList();
     //in totatl array
     int[][] total=new int[input1.length-1][3];       
     for(int i=0;i<input1.length-1;i++){
            for(int j=0;j<3;j++){
                if(j==0){
                total [i][j]=init[i];
                }else if(j==1){
                    total [i][j]=end[i];
                }else{
                    total [i][j]=cost[i];
                }
            }
        }
for(int i=0;i<input1.length-1;i++){
    for(int j=0;j<2;j++){
        if(eqi.contains(total[i][j])==false){
            eqi.add(total[i][j]);
        }
    }
}

ArrayList count=new ArrayList();
for(int i=0;i<eqi.size();i++){
    int co=1;
    count.add(i, 0);
   for(int j=0;j<input1.length-1;j++){
       for(int k=0;k<2;k++){           
           if((int)eqi.get(i)==(total[j][k])){
               count.set(i, co);
               co++;
           }
       }
   }
}int max1=0;
for(int j=0;j<count.size();j++){
    if((int)count.get(j)>max1){
        max1=(int) count.get(j);
    }
}ArrayList toller=new ArrayList();
int io=eqi.indexOf(b);
int of=eqi.indexOf(f);
int su=0;
begin:
while(su<=max1){
    root1.add(su, Integer.parseInt(eqi.get(io).toString()));
   cost1.add(su,0);
    int sw=b;
            for(int i=0;i<init.length;i++){
                    if(init[i]==sw && (Integer.parseInt(count.get(eqi.indexOf(end[i])).toString())>0 || end[i]==f)){
                        root1.set(su, root1.get(su)+"->"+end[i]);
                        cost1.add(su, (int)cost1.get(su)+cost[i]);
                        count.set(eqi.indexOf(end[i]),(Integer.parseInt(count.get(eqi.indexOf(end[i])).toString())-1));
                          if(end[i]==f){
                              if(end[i]==f && toller.isEmpty()==true){
                                  toller.add(su,i+1);
                              }
                              su=su+1;
                              continue begin;
                          }else{
                              if(toller.isEmpty()==false && toller.contains(i+1)==false){
                                  toller.add(su,i+1);
                              }
                              count.set(eqi.indexOf(end[i]),(Integer.parseInt(count.get(eqi.indexOf(end[i])).toString())-1));
                              sw=end[i];
                              i=0;
                          }
 
                    }else{
                       // System.out.println("no sol");
                    }
                
            }

}toller.remove(toller.indexOf(1));
int swap=0,swap1=0;
for(int i=0;i<toller.size();i++){
    for(int j=i+1;j<toller.size();j++){
        if((int)toller.get(i)>(int)toller.get(j)){
            swap=(int)toller.get(i);
            swap1=(int)cost1.get(i);
            toller.set(i,toller.get(j));
            cost1.set(i,cost1.get(j));
            toller.set(j,swap);
            cost1.set(j,swap1);
        }
    }
} 

int max2=0,max2loc=0;
for(int j=0;j<cost1.size();j++){
    if((int)cost1.get(j)>max2){
        max2=(int)cost1.get(j);
        max2loc=j;        
    }
}int mcou=0;for(int j=0;j<root1.size();j++){
    if((int)cost1.get(j)!=max2){
        mcou=mcou+1;
    }
}
ArrayList result=new ArrayList();
result.add(mcou+"#"+max2);

for(int i=0;i<toller.size();i++){    
    if((int)cost1.get(i)!=max2){
        result.add(toller.get(i)+"#"+(max2-(int)cost1.get(i)));
    }
}

//take root1--> for all roots
//take cost1-->for cost to those roots;
//take toller--> to Insert toll between roots
       String[] fres=new String[root1.size()];
       for(int i=0;i<fres.length;i++){
           fres[i]=(String) root1.get(i);
       }
       
return fres;
        
    }
    
}
