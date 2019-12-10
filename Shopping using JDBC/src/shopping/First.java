package shopping;

import java.sql.SQLException;
import java.util.Scanner;


public class First {

	public static void main(String[] args)throws SQLException,ClassNotFoundException
	{
		First f=new First();
		f.choice();
	}
	
		void choice() throws ClassNotFoundException, SQLException {
			Scanner s= new Scanner (System.in);
			System.out.println("1.admin\n2.agent\n3.exit");
			int n=s.nextInt();
			switch (n)
			{
			case 1:
				Admin ad=new Admin();
				ad.adminData();
				break;
			case 2:
				Agent ag=new Agent();
				ag.agentData();
				break;
			case 3:
				System.exit(0);
				break;
				default:System.out.println("not in the menu");
			}
		
	

	}

}
