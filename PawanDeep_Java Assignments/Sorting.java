import java.util.*; 

public class Sorting 

{ 

public static void main(String[] args) { 

    

   int swap; 

    int n;   

        Scanner sc=new Scanner(System.in);  

        n=sc.nextInt();   

        int[] arr = new int[200]; 

        for(int i=0; i<n; i++)  

        { 

            arr[i]=sc.nextInt();  

        }  

        for(int i=0;i<n;i++) 

        { 

            for(int j=i+1;j<n;j++) 

            { 

                if(arr[i]>arr[j]) 

                { 

                    swap=arr[i]; 

                    arr[i]=arr[j]; 

                    arr[j]=swap; 

                } 

            } 

        } 

        System.out.print("{"); 

for(int i=0;i<n;i++) 

{ 

    System.out.print(arr[i]+","); 

} 

System.out.print("}"); 

} 

} 