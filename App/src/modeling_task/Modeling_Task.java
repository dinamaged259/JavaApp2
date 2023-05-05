
package modeling_task;

import javax.swing.*;
public class Modeling_Task {

    
    public static void main(String[] args) {
        
        
        String num=JOptionPane.showInputDialog("What is the number of demands?");
        int n=Integer.parseInt(num);
        int demand[]=new int[n];
        float frequency[]=new float[n];
        float probability[]=new float[n];
        float comulative[]=new float[n];
        float interval[][]=new float[n][2];

        float sum=0;


        for(int i=0; i<n; i++)
        {
            String s=JOptionPane.showInputDialog("Enter demand value");
            demand[i]=Integer.parseInt(s);
            String d=JOptionPane.showInputDialog("Enter frequency value of this demand");
            frequency[i]=Float.parseFloat(d);
            sum+=frequency[i];
        }
        


        for(int j=0; j<n; j++)
        {
            probability[j]=frequency[j]/sum;
            if(j>0)
                comulative[j]=probability[j]+comulative[j-1];
            else
                comulative[0]=probability[0];
        }



        String l=JOptionPane.showInputDialog("Enter the biggest random number you want:");
        int big=Integer.parseInt(l);

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<2; j++)
            {
                interval[0][0]=1;
                if(j==1)
                    interval[i][j]=interval[i][j-1]+((probability[i]*big)-1);
                else if(j==0&&i>0)
                    interval[i][j]=(interval[i-1][j+1]+1);
            }
        }



        String t=JOptionPane.showInputDialog("Hom many random numbers you want to enter?");
        int nums=Integer.parseInt(t);
        int random[]=new int[nums];
        for(int m=0; m<nums; m++)
        {
            String o=JOptionPane.showInputDialog("Enter random number");
            random[m]=Integer.parseInt(o);
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<2; j+=2)
                {
                    if(random[m] >= interval[i][j] && random[m] <= interval[i][j+1])
                        JOptionPane.showMessageDialog(null, "The Demand of your random number ("+
                                        random[m]+") is : "+demand[i],
                               "Output" , JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        
        String output="";
        output+=String.format("%s", "Demand");
        output+=String.format("%15s", "Frequency");
        output+=String.format("%15s", "Probability");
        output+=String.format("%15s", "Comulative");
        output+=String.format("%15s", "Intervals");
        
        
        output+="\n";
        for(int i=0; i<n; i++)
        {
            output+=String.format("%,7d", demand[i]);
            output+=String.format("%,20.2f", frequency[i]);
            output+=String.format("%,18.2f", probability[i]);
            output+=String.format("%,20.2f", comulative[i]);
            for(int j=0; j<2; j++)
            {
                if(j==1)
                    output+=String.format("-%,2.2f", interval[i][j]);
                else
                    output+=String.format("%,20.2f", interval[i][j]);
            }
            
            output+="\n";
            
        }
        
        JOptionPane.showMessageDialog(null, output);
        
        
    }
    
}
