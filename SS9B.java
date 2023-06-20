//LRU
import java.util.Scanner;

class Frame
{
    int page_num=-1;
    int lastAccessTime=-1;

    void refreshFrame(int la)
    {
        lastAccessTime=la;
    }

    void replaceFrame(int pnum,int la)
    {
        page_num=pnum;
        lastAccessTime=la;
    }
}
public class SS9B
{
    static Frame cache[];
    static int pages[];
    static int nP,nF;
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int index;
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
        cache=new Frame[nF];
        for(i=0;i<nF;i++)
        {
            cache[i]=new Frame();
        }

        for(i=0;i<nP;i++)
        {
            index=findIndex(pages[i]);
            if(index!=-1)
            {
                cache[index].refreshFrame(i);
            }

            else
            {
                miss++;
                int temp=lru();
                cache[temp].replaceFrame(pages[i],i);
            }

            printCurrent();
        }
        sc.close();
        System.out.println("Total Misses:"+miss);
    }
    
    public static int findIndex(int a)
    {
        int i;
        for(i=0;i<nF;i++)
        {
            if(cache[i].page_num==a)
                return i;
        }
        return -1;
    }

    public static void printCurrent()
    {
        int i;
        for(i=0;i<nF;i++)
        {
            System.out.print(cache[i].page_num+" ");
        }
        System.out.println();
    }

    public static int lru()
    {
        int min=cache[0].lastAccessTime;
        int index=0;
        int i;
        for(i=1;i<nF;i++)
        {
            if(min>cache[i].lastAccessTime)
            {
                min=cache[i].lastAccessTime;
                index=i;
            }
        }

        return index;
    }
}
