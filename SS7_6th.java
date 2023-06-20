//SRTF
import java.util.Scanner;
public class SS7_6th
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int i;
        int smallest,time,n,count=0,end;
        double awt=0,att=0;
        int a[]=new int[10];
        int b[]=new int[10];
        int x[]=new int[10];
        int waiting[]=new int[10];
        int turn[]=new int[10];
        int completion[]=new int[10];

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

        b[9]=999;
        for(time=0;count!=n;time++)
        {
            smallest=9;
            for(i=0;i<n;i++)
            {
                if(a[i]<=time && b[i]<b[smallest] && b[i]!=0)
                    smallest=i;
            }
            b[smallest]--;

            if(b[smallest]==0)
            {
                count++;
                end=time+1;
                completion[smallest]=end;
                turn[smallest]=completion[smallest]-a[smallest];
                waiting[smallest]=turn[smallest]-x[smallest];
            }
        }

        System.out.println("Process\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        for(i=0;i<n;i++)
        {
            System.out.println((i+1)+"\t"+a[i]+"\t"+b[i]+"\t"+completion[i]+"\t"+turn[i]+"\t"+waiting[i]);
            awt+=waiting[i];
            att+=turn[i];
        }

        System.out.println("Average Waiting Time:"+(awt/n));
        System.out.println("Average Turnaround Time:"+(att/n));
        sc.close();
    }
}