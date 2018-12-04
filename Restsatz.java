import java.util.Scanner;
public class Restsatz{
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            System.out.println("number of congruences:");
            int g=sc.nextInt();

            System.out.println("type in b_j an m_j (x mod m_i = b_i)");
            int a[]=new int[g];
            int b[]=new int[g];
            int m[]=new int[g];
           
            int dual[]=new int[g];
            int dual2[]=new int[g];

            for(int i=0; i<g;i++){
                a[i]=1;
                b[i]=sc.nextInt();
                m[i]=sc.nextInt();
                int ggt=ggT(a[i],m[i]);            
                if(b[i]%ggt!=0){System.out.println("No solution"); return;}
                else{
                    a[i]=a[i]/ggt; b[i]=b[i]/ggt; m[i]=m[i]/ggt;
                    System.out.println(a[i]+"x = "+b[i]+" mod "+m[i]);
                }
            }
            int k=multiply(m);
            for(int i=0; i< g; i++){
                dual2[i]=k/m[i];
                dual[i]=dual2[i]%m[i];
                System.out.println(dual[i]+"x_"+i+" "+b[i]+" mod "+m[i]);
            }
            
            System.out.println();
            int x[]= new int[g];
            for(int i=0; i<g; i++){
                x[i]=mod(invInB(dual[i],m[i]),m[i])*b[i];
                System.out.println("x_"+i+"="+x[i]);
            }
            System.out.println("\nx="+multiply(dual2, x)+"+k*"+k);
        }

        public static int ggT(int a, int b){
            while(b!=0){
                int r= a%b;
                a=b;
                b=r;
            }
            return a;

        }
        public static int multiply(int[] m){//Multiply vector entries
            int mul=1;
            for(int i=0; i< m.length; i++){
                mul=mul*m[i];
            }
            return mul;
        }
        public static int multiply(int[] m, int[] x){ //Multiply two vectors
            int sum=0;
            for(int i=0; i< m.length; i++){
                sum=sum+x[i]*m[i];
            }
            return sum;
        }
        public static int invInB(int a, int b){ //extended Euklidean algorithm, return inv(a) in Z_b
            int x0=1;int y1=1;
            int y0=0; int x1=0;
            int q=a/b;
            int temp=0;
            while(b!=0){
                System.out.println("q="+q+"\ta="+a+"\tb="+b+"\t"+x0+"\t"+y0+"\t"+x1+"\t"+y1);
                temp=x0-q*x1;
                x0=x1;
                x1=temp;
                temp=y0-q*y1;
                y0=y1;
                y1=temp;
                temp=a%b;
                a=b;
                b=temp;
                if(b!=0)q=a/b;
            }
            System.out.println("q="+q+"\ta="+a+"\tb="+b+"\t"+x0+"\t"+y0+"\t"+x1+"\t"+y1);
            return x0;
        }  
        public static int mod(int s, int m){ //mathematical mod, not java mod (problems for s<0), not needed jet
            int erg=s%m;
            if(s<0){erg=(-1)*((s)/m)*m+m+(s);}
            return erg;
        }
}
