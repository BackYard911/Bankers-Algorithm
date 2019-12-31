package com.BackYard;

import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    int n; // = 5; //number of processes
    int m;  //= 3; //number of resource types
    int []available;
    int [][]max;
    int [][]allocation;
    int [][]need; //= new int[n][m];
    int []safeSeq; //=new int[n];

    void initialization(){
        System.out.println("Please enter number of processes");
        n = scanner.nextInt();
        System.out.println("Please enter number of resource types");
        m = scanner.nextInt();
        allocation = new int[n][m];
       /*    {     {0,1,0}, //P0
                {2,0,0}, //P1
                {3,0,2}, //P2
                {2,1,1}, //P3
                {0,0,2} //P4 } */
        System.out.println("Please enter the allocation map respectively");
       for (int i = 0 ; i<n; i++){
           for (int j = 0; j<m; j++){
                allocation[i][j]= scanner.nextInt();
           }
       }

        max = new int[n][m];
             /*   {
                {7,5,3}, //P0
                {3,2,2}, //P1
                {9,0,2}, //P2
                {2,2,2}, //P3
                {4,3,3} //P4
        } */
        System.out.println("Please enter the max map respectively");
        for (int i = 0 ; i<n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = scanner.nextInt();
            }
        }
        available = new int[m]; //{3,3,2};
        System.out.println("Please enter the number of available instance of each resource respectively");
        for (int i = 0 ;i < m;i++){
            available[i]=scanner.nextInt();
        }
        for (int i =0 ; i<m; i++){
            for (int j = 0; j<n ;j++){
                    available[i]-=allocation[j][i];
            }
        }
    }

    void isSafe(){
        safeSeq = new int[n];
        int counter = 0;
        boolean finish[] = new boolean[n];
        for(int i = 0; i<n;i++){
            finish[i]=false;
        }
        int work[] = new int [m];
        for(int i = 0;i < m;i++){
            work[i] = available[i];
        }
        while(counter<n){
            boolean flag = false;
            for (int i =0;i<n;i++){
                if(!finish[i]){
                    int j;
                    for(j = 0;j < m;j++){
                        if(need[i][j]>work[j])
                            break;
                    }
                   if (j == m){
                       safeSeq[counter]=i;
                       counter++;
                       finish[i]=true;
                       flag=true;
                       for (j = 0;j < m;j++){
                           work[j] = work[j]+allocation[i][j];
                       }
                   }
                }
            }
            if (!flag)
                break;
        }
        if(counter < n)
            System.out.println("System is not safe.");
        else{
            System.out.println("Safe sequence is:");
            for (int i = 0;i < n;i++){
                System.out.print("P" + safeSeq[i]);
                if (i != n-1)
                    System.out.print(" -> ");
            }
        }
    }

    void calcNeed(){
        need = new int[n][m];
        for (int i = 0; i < n;i++){
            for (int j = 0; j < m;j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public static void main(String[] args) {
	Main banker = new Main();
	banker.initialization();
	banker.calcNeed();
	banker.isSafe();
    }
}
