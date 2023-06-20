//Round Robin
import java.util.Scanner;

public class SS7_6Ath 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int a[]=new int[10];
        int b[]=new int[10];
        int x[]=new int[10];
        int i;
        int count=0,n,time,tq;
        double awt=0,att=0;
        boolean flag=false;

        System.out.println("Enter the Number of processes");   //!=10
        n=sc.nextInt();

        System.out.println("Enter arrival and burst time ");
        for(i=0;i<n;i++)
        {
            System.out.println("Process "+i);
            a[i]=sc.nextInt();
            b[i]=sc.nextInt();
            x[i]=b[i];
        }
        System.out.println("Enter the Time Quantum");
        tq=sc.nextInt();

        System.out.println("Process\tArrival\tBurst\tTurnaround\tWaiting");
        for(time=0,i=0;count!=n;)
        {
            if(x[i]<=tq && x[i]>0)
            {
                time+=x[i];
                x[i]=0;
                flag=true;
            }

            else if(x[i]>0)
            {
                time+=tq;
                x[i]=x[i]-tq;
            }

            if(x[i]==0 && flag==true)           //flag condition to check process is considered for calculation only once
            {
                count++;
                System.out.println((i+1) +"\t"+ a[i] +"\t"+ b[i] +"\t"+ (time-a[i])+"\t"+(time-a[i]-b[i]));
                flag=false;
                awt+=time-a[i]-b[i];
                att+=time-a[i];
            }

            if(i==(n-1))
                i=0;
            else if(a[i+1]<=time)
                i++;

            else
                i=0;
        }
        System.out.println("Average Waiting Time:"+(awt)/n);
        System.out.println("Average Turnaround Time:"+(att)/n);
        sc.close();
    }
}
