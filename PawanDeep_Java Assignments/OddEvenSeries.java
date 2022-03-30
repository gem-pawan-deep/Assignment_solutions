public class OddEvenSeries // program for series.
{
public static void main(String[] args) {
for(int i=1;i<=50;i++)
{
if(i%2==0)
{
System.out.print(i + " "); //series for even number.
}
}
System.out.println("\n");
for(int i=1;i<=50;i++)
{
if(i%2!=0)
{
System.out.print(i + " "); //series for odd number.
}
}
}
}