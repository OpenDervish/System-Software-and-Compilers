//FIFO
import java.util.Scanner;
import java.util.Arrays;
public class SS9A 
{
    static int nP,nF;
    static int pages[],cache[];
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int nextFrameIndex=0,index;
        int i;
        int miss=0;
        System.out.println("Enter the Number of Pages");
        nP=sc.nextInt();
        pages=new int[nP];
        System.out.println("Enter the Pages");
        for(i=0;i<nP;i++)
        {
            pages[i]=sc.nextInt();
        }
        System.out.println("Enter the Number of Frames");
        nF=sc.nextInt();
        cache=new int[nF];
        Arrays.fill(cache,-1);
        for(i=0;i<nP;i++)
        {
            index=findIndex(pages[i]);
            if(index!=-1)
            {
                continue;
            }

            else
            {
                miss+=1;
                cache[nextFrameIndex]=pages[i];
                nextFrameIndex=(nextFrameIndex+1) % nF;
            }

            printCurrent();
        }
        System.out.println("Total misses="+miss);
    }   

    public static int findIndex(int a)
    {
        int i;
        for(i=0;i<nF;i++)
        {
            if(cache[i]==a)
                return i;
        }
        return -1;
    }

    public static void printCurrent()
    {
        int i;
        for(i=0;i<nF;i++)
        {
            System.out.print(cache[i]+" ");
        }
        System.out.println();
    }
}
