#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char prod[3][10]={"A->aBa","B->bB","B->@"};
char first[3][10]={"a","b","@"};
char follow[3][10]={"$","a","a"};
char table[3][4][10];
char input[10];
int top=-1;
char stack[25];
char curp[25];

void push(char c)
{
    stack[++top]=c;
}

void pop()
{
    top--;
}

void display()
{
    for(int i=top;i>=0;i--)
    {
        printf("%c",stack[i]);
    }
}

int numr(char c)
{
    switch(c)
    {
        case 'a':
        case 'A':return 1;
        case 'b':
        case 'B':return 2;
        case '$':return 3;
    }
    return 1;
}

void main()
{
    printf("Grammar\n");
    int i,j,k,n;
    for(i=0;i<3;i++)
    {
        printf("%s\n",prod[i]);
    }

    printf("First={%s,%s,%s}\n",first[0],first[1],first[2]);
    printf("Follow={%s,%s,%s}\n",follow[0],follow[1],follow[2]);

    printf("Predictive Parse Table LL1\n");
    for(i=0;i<3;i++)
    {
        for(j=0;j<4;j++)
        {
            strcpy(table[i][j],"e");
        }
    }
    strcpy(table[0][0]," ");
    strcpy(table[0][1],"a");
    strcpy(table[0][2],"b");
    strcpy(table[0][3],"$");
    strcpy(table[1][0],"A");
    strcpy(table[2][0],"B");

    for(i=0;i<3;i++)
    {
        k=strlen(first[i]);
        for(j=0;j<k;j++)
        {
            if(first[i][j]!='@')
            {
                strcpy(table[numr(prod[i][0])][numr(first[i][j])],prod[i]);
            }

            else
            {
                strcpy(table[numr(prod[i][0])][numr(follow[i][j])],prod[i]);
            }
        }
    }

    printf("\n----------------------------------------------------------\n");
    for(i=0;i<3;i++)
    {
        for(j=0;j<4;j++)
        {
            printf("%-10s",table[i][j]);
            if(j==3)
                printf("\n----------------------------------------------------------\n");
        }
    }

    printf("Enter input string\n");
    scanf("%s",input);

    for(i=0;i<strlen(input);i++)
    {
        if(input[i]!='a' && input[i]!='b' && input[i]!='$')
        {
            printf("Invalid String\n");
            exit(0);
        }
    }

    if(input[i-1]!='$')
    {
        printf("Invalid String without end marker\n");
        exit(0);
    }

    printf("Stack\t\tInput\t\tAction\n");
    printf("\n----------------------------------------------------------\n");
    push('$');
    push('A');
    i=0;
    while(input[i]!='$' && stack[top]!='$')
    {
        display();
        printf("\t\t%s",(input+i));

        if(stack[top]==input[i])
        {
            printf("\t\tMatched %c\n",input[i]);// %c only not %s
            pop();
            i++;
        }

        else
        {
            if(stack[top]>=65 && stack[top]<=91)
            {
                strcpy(curp,table[numr(stack[top])][numr(input[i])]);
                if(strcmp(curp,"e")==0)
                {
                    printf("\nInvalid String-Rejected\n");
                    exit(0);
                }

                else
                {
                    printf("\t\tApply Production %s\n",curp);
                    if(curp[3]=='@')
                    {
                        pop();
                    }

                    else
                    {
                        pop();
                        n=strlen(curp);
                        for(j=n-1;j>=3;j--)
                        {
                            push(curp[j]);
                        }
                    }
                }
            }
        }   
    }

    display();
    printf("\t\t%s",(input+i));
    printf("\n----------------------------------------------------------\n");
    if(stack[top]=='$' &&input[i]=='$')
    {
        printf("\nValid String-Accepted\n");
    }

    else
    {
        printf("Invalid String-Rejected\n");
    }
}

/*
Output
  gcc three.c
  ./a.out
Grammar
A->aBa
B->bB
B->@
First={a,b,@}
Follow={$,a,a}
Predictive Parse Table LL1

----------------------------------------------------------
          a         b         $
----------------------------------------------------------
A         A->aBa    e         e
----------------------------------------------------------
B         B->@      B->bB     e
----------------------------------------------------------
Enter input string
abba$
Stack           Input           Action

----------------------------------------------------------
A$              abba$           Apply Production A->aBa
aBa$            abba$           Matched a
Ba$             bba$            Apply Production B->bB
bBa$            bba$            Matched b
Ba$             ba$             Apply Production B->bB
bBa$            ba$             Matched b
Ba$             a$              Apply Production B->@
a$              a$              Matched a
$               $
----------------------------------------------------------
Valid String-Accepted
  */
