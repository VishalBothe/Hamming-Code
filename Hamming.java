//java program to implement Hamming code
import java.util.Scanner;

class Sender
{
	static int m,r,tb;
	static int r1[],r2[],r3[],r4[],r5[];//arrays to store position of redundant bits.
	
	static void addParity(int a[],int no)
	{
		m=no;r=0;tb=0;
		
		while(Math.pow(2,r)<(m+r+1)) //this loop calculates number of redundant bits.
			r++;
		
		System.out.println("No. of parity bits : "+r);
		tb=m+r;
		System.out.println("No. of bits in a sequence : "+tb);
		int datastream[]=new int[tb];
		
		int i=0,j=1,k=0;
		for(j=1;j<=tb;j++)
		{
			if(j==Math.pow(2,i))
			{
				datastream[j-1]=-1;
				i++;
			}
			else
			{
				datastream[j-1]=a[k];	//inserts data bits in their position. 
				k++;
			}
		}	
		
		int n=tb,pos=0,cnt=0,counter;
		boolean flag;
		
		for(k=0;k<n;k++)
		{
			switch(k+1)
			{
				case 1:
				counter=0;
				i=0;j=0;cnt=0;
				r1=new int[10];
				while(i<n && j<10)
				{
					r1[j]=i+1;
					j++;
					i=i+2;
				}
				for(i=0;i<r1.length;i++)
				{
					pos=r1[i];
					if(pos==0)
						break;
					if(datastream[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
					datastream[k]=0;
				else
					datastream[k]=1;
				break;
				
				case 2:
				counter=0;
				r2=new int[10];
				i=1;j=0;cnt=0;
				flag=true;
				while(i<n && j<10)
				{
					if(flag)
					{
						r2[j]=i+1;
						j++;
						cnt++;
						if(cnt==2)
						{
							flag=false;
							cnt=0;
						}
					}
					else
					{
						cnt++;
						if(cnt==2)
						{
							flag=true;
							cnt=0;
						}
					}
					i++;
				}
				for(i=0;i<r2.length;i++)
				{
					pos=r2[i];
					if(pos==0)
						break;
					if(datastream[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
					datastream[k]=0;
				else
					datastream[k]=1;
				break;
				
				case 3: break;
				
				case 4:
				counter=0;
				i=3;j=0;cnt=0;flag=true;
				r3=new int[10];
				while(i<n && j<10)
				{
					if(flag)
					{
						r3[j]=i+1;
						j++;
						cnt++;
						if(cnt==4)
						{
							flag=false;
							cnt=0;
						}
					}
					else
					{
						cnt++;
						if(cnt==4)
						{
							flag=true;
							cnt=0;
						}
					}
					i++;
				}
				for(i=0;i<r3.length;i++)
				{
					pos=r3[i];
					if(pos==0)
						break;
					if(datastream[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
					datastream[k]=0;
				else
					datastream[k]=1;
				break;
				
				case 5:
				case 6:
				case 7: break;
				
				case 8:
				counter=0;
				i=7;j=0;cnt=0;flag=true;
				r4=new int[10];
				while(i<n && j<10)
				{
					if(flag)
					{
						r4[j]=i+1;
						j++;
						cnt++;
						if(cnt==8){
							flag=false;
							cnt=0;
						}
					}
					else
					{
						cnt++;
						if(cnt==8)
						{
							flag=true;
							cnt=0;
						}
					}
					i++;
				}
				for(i=0;i<r4.length;i++)
				{
					pos=r4[i];
					if(pos==0)
						break;
					if(datastream[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
					datastream[k]=0;
				else
					datastream[k]=1;
				break;
				
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
				case 15: break;
				
				case 16:
				counter=0;
				i=15;j=0;cnt=0;flag=true;
				r5=new int[10];
				while(i<n && j<10)
				{
					if(flag)
					{
						r5[j]=i+1;
						j++;
						cnt++;
						if(cnt==16){
							flag=false;
							cnt=0;
						}
					}
					else
					{
						cnt++;
						if(cnt==16)
						{
							flag=true;
							cnt=0;
						}
					}
					i++;
				}
				for(i=0;i<r5.length;i++)
				{
					pos=r5[i];
					if(pos==0)
						break;
					if(datastream[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
					datastream[k]=0;
				else
					datastream[k]=1;
				break;
			}
		}
		System.out.println("data sequence :");
		for(i=0;i<tb;i++)
			System.out.print(datastream[i]+" ");
		System.out.println();
		Reciver.checkParity(r1,r2,r3,r4,r5,tb,m);
	}
}

class Reciver
{
	static int rcnt=0,counter=0;
	static void checkParity(int r1[],int r2[],int r3[],int r4[],int r5[],int tb,int m)
	{
		int pos=0;
		int i=0,j=0,k=0;
		System.out.println("Enter received data");
		int rdat[]=new int[tb];
		Scanner in=new Scanner(System.in);
		for(i=0;i<tb;i++)
			rdat[i]=in.nextInt();
		for(k=0;k<tb;k++)
		{
			switch(k+1)
			{
				case 1:
				counter=0;
				for(i=0;i<r1.length;i++)
				{
					pos=r1[i];
					if(pos==0)
						break;
					if(rdat[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
						break;
		
				else
					rcnt=rcnt+k+1;
				System.out.println();
				
				break;
				
				case 2:
				counter=0;
				for(i=0;i<r2.length;i++)
				{
					pos=r2[i];
					if(pos==0)
						break;
					if(rdat[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
						break;
		
				else
					rcnt=rcnt+k+1;
				System.out.println();
				
				break;
				
				case 3: break;
				
				case 4:
				counter=0;
				for(i=0;i<r3.length;i++)
				{
					pos=r3[i];
					if(pos==0)
						break;
					if(rdat[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
						break;
		
				else
					rcnt=rcnt+k+1;
				System.out.println();
				
				break;
				
				case 5:
				case 6:
				case 7:break;
				
				case 8:
				counter=0;
				for(i=0;i<r4.length;i++)
				{
					pos=r4[i];
					if(pos==0)
						break;
					if(rdat[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
						break;
		
				else
					rcnt=rcnt+k+1;
				System.out.println();
				
				break;	
	
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
				case 15: break;
				
				case 16:
				counter=0;
				for(i=0;i<r5.length;i++)
				{
					pos=r5[i];
					if(pos==0)
						break;
					if(rdat[pos-1]==1)
						counter++;
				}
				if(counter%2==0)
						break;
		
				else
					rcnt=rcnt+k+1;
				System.out.println();
				
				break;
			}
		}
	
		if(rcnt==0)
			System.out.println("No error");
		else
		{
			System.out.println("Error occurred at : "+rcnt);
			if(rdat[rcnt-1]==0)
				rdat[rcnt-1]=1;
			else
				rdat[rcnt-1]=0;
			System.out.println("corrected sequence");
			for(i=0;i<tb;i++)
				System.out.print(rdat[i]+" ");
			System.out.println();
		}
		int a1[]=new int[m];
		i=0;j=1;k=0;
		while(j<=tb)
		{
			if(j==Math.pow(2,i))
			{
				i++;
				j++;
			}
			else
			{
				a1[k]=rdat[j-1];
				j++;
				k++;
			}
			
		
		}
		System.out.println("data recived: ");
			for(i=0;i<a1.length;i++)
				System.out.print(a1[i]+" ");
			System.out.println();	
	}
}

class Hamming
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("How many bits (max: 24 bits):???");
		int n=sc.nextInt();
		int arr[]=new int[n];int i=0;
		System.out.println("Enter bits");
		for(i=0;i<n;i++)
			arr[i]=sc.nextInt();
		
		System.out.print("data to be sent: ");
		for(i=0;i<n;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
		System.out.println("No. of bits : "+n);
		
		Sender.addParity(arr,n);
	}
}
