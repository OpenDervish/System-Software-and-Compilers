//Banker's Algorithm
import java.util.Scanner;
import java.util.Arrays;
public class SS8
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int p,r,i,j;
        int process,count=0;
        int max[][]=new int[10][10];
        int alloc[][]=new int[10][10];
        int need[][]=new int[10][10];
        int avail[]=new int[10];
        int completed[]=new int[10];
        int safe[]=new int[10];

        System.out.println("Enter the number of processes");
        p=sc.nextInt();
        System.out.println("Enter the number of resources");
        r=sc.nextInt();
        Arrays.fill(completed,0);
        System.out.println("Enter Max Matrix");
        for(i =0;i<p;i++)
        {
            for(j=0;j<r;j++)
            {
                max[i][j]=sc.nextInt();
            }
        }

        System.out.println("Enter Alloc Matrix");
        for(i =0;i<p;i++)
        {
            for(j=0;j<r;j++)
            {
                alloc[i][j]=sc.nextInt();
                need[i][j]=max[i][j]-alloc[i][j];
            }
        }
        System.out.println("Enter Avail Matrix");
        for(i =0;i<r;i++)
        {
            avail[i]=sc.nextInt();
        }

        Arrays.fill(completed,0);
        do
        {
           /* System.out.println("Max Matrix");
            System.out.println("Alloc Matrix");
            for(i =0;i<p;i++)
            {
                for(j=0;j<r;j++)
                {
                    System.out.print(max[i][j]+" ");
                }
                System.out.print("\t\t");
                for(j=0;j<r;j++)
                {
                    System.out.print(alloc[i][j]+" ");
                }
                System.out.println();
            }*/
            process=-1;
            for(i=0;i<p;i++)
            {
                if(completed[i]==0)
                {
                    process=i;
                    for(j=0;j<r;j++)
                    {
                        if(need[i][j]> avail[j])
                        {
                            process=-1;
                            break;
                        }
                    }
                }
                if(process!=-1)
                   break;
            }
            if(process!=-1)
            {
                safe[count]=process+1;
                count++;
                for(j=0;j<r;j++)
                {
                    avail[j]=avail[j]+alloc[process][j];
                    max[process][j]=0;
                    alloc[process][j]=0;
                    completed[process]=1;
                }
            }
        }while(count!=p && process!=-1);

        if(count==p)
        {
            System.out.println("System is in Safe State");
            System.out.print("Safe Sequence is:[");
            for(i=0;i<p;i++)
            {
                System.out.print(safe[i]+",");
            }
            System.out.print("]\n");
        }

        else
            System.out.println("System is not in Safe State");
    }
}
