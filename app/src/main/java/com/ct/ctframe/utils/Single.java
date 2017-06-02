package com.ct.ctframe.utils;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/30.
 * GitHub:https://github.com/CNHTT
 */

public class Single {
    public static void main(String[] args) {
        int a[] = {3,1,5,7,2,4,9,6,10,8};
        print(a);

        for (int i = 1; i <a.length ; i++) {
            if (a[i]<a[i-1])
            {
                int j;
                int x = a[i];
                a[i]=a[i-1];
                System.out.print("\n");
                System.out.print(a[i]);
                for (j=i-1;j>0&& x<a[j];j--)
                {
                    a[j+1] = a[j];

                }
                a[j+1] =x;

            }
        }
        print(a);

    }
    public static void print(int a[]){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
    }
}
