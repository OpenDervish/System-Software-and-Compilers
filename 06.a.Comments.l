/*
6.a. Write a LEX program to eliminate comment lines in a C program and copy the
resulting program into a separate file.
*/

%{
#include<stdio.h>
%}
%%
\/\/(.*) {};
\/\*(.*\n)*.*\*\/ {};
%%
int yywrap()
{
 return 1;
}
 
int main(){
yyin=fopen("1.c","r");
yyout=fopen("2.c","w");
yylex();
return 0;
}
