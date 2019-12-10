package shopping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Agent {

	ConnMang con=new ConnMang();
	
	public void agentData() throws ClassNotFoundException, SQLException {
		Scanner s=new Scanner (System.in);
		System.out.println("user name");     
		String name=s.next();
		System.out.println("password");
		String pswrd=s.next();
		Statement s1=con.getConnection().createStatement();
		ResultSet r=s1.executeQuery("select * from agentlogin");
		int f=0;
		
		 //checking the login
		
		while(r.next())
		{
			 
			String n=r.getString(1);
			String h=r.getString(2);
			
			if(n.equals(name)&&h.equals(pswrd))
			{
				f=1;
				break;
			}
			else
			{
				f=0;
			}
		}
	if(f==1)
		{
			System.out.println("success login");
		int n=0;
		do
		{
		System.out.println("1.buy/sell\n2.view product\n3.logout");  //enter  into agent login
		 n=s.nextInt();
		 	switch(n)
		 		{
		 		case 1:  //buy/sell
		 			Statement stmt=con.getConnection().createStatement();
					ResultSet re=stmt.executeQuery("select*from addproduct");
					while(re.next())
						{
							System.out.println("##########*********#########");
							System.out.println("Product ID   ->"+re.getString(1));		//display products
							System.out.println("Product Name ->"+re.getString(2));
							System.out.println("##########*********#########");
						}
					System.out.println("enter the id u wnt buy");						//ask what user need
					int buy=s.nextInt();
					int price,total,product;
					ResultSet resu=stmt.executeQuery("select *from addproduct ");		//select from the table
					while(resu.next())
						{
					int	pri=resu.getInt(1);  // product id
							if(pri==buy)
							{
							price=	resu.getInt(4); //get the price
							product=resu.getInt(3); //get the quantity
							System.out.println("quantity");
							int quan=s.nextInt();
							total=price*quan;
							System.out.println("total cost= "+total); //total price
							System.out.println("enter 1 u want proceed");
							int enter=s.nextInt();
								if(enter ==1)
								{
									System.out.println("booking cofirmed\nThankyou!!!!!!!!");
									int produc=product-quan;
									
									//update the quantity 
									
									PreparedStatement st=con.getConnection().prepareStatement("update addproduct set minsellquantity=? where minsellquantity=?");
									st.setInt(1,produc);  //quantity updated
									st.setInt(2,product);  //previous quantity
									st.executeUpdate();
								}
								break;
							}
							else
							{
								System.out.println("wrong id");
							}
						
						}
		 			break;
				case 2:  // display
					Statement stm=con.getConnection().createStatement();
					ResultSet res=stm.executeQuery("select*from addproduct");
					while(res.next())
						{
							System.out.println("##########*********#########");
							System.out.println("Product ID   ->"+res.getString(1));
							System.out.println("Product Name ->"+res.getString(2));
							System.out.println("Quantity     ->"+res.getString(3));
							System.out.println("Price        ->"+res.getString(4));
							System.out.println("##########*********#########");
						}
					break;
				case 3:  // go to home page
					First f1=new First();
					f1.choice();
					break;
				default:System.out.println("not in the menu");
		 		}
		}
		while(n==1||n==2||n==3);
		}
	else
	{
		System.out.println("login faild");
		First f1=new First();
		f1.choice();
	}
		
	}

}
