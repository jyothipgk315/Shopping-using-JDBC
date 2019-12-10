package shopping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {

	ConnMang con =new ConnMang();
	
	public void adminData() throws ClassNotFoundException, SQLException{
		
		Scanner s=new Scanner (System.in);
		System.out.println("user name");
		String name=s.next();
		System.out.println("password");
		String pswrd=s.next();
		Statement s1=con.getConnection().createStatement();
		ResultSet r=s1.executeQuery("select * from adminlogin");
		
		//checking login details
		
		
		int f=0;
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
		System.out.println("1.add product\n2.display\n3.remove\n4.update\n5.exit");
		 n=s.nextInt();
			switch(n)
			{
			case 1:		//add product
				System.out.println("product id");
				int id=s.nextInt();
				System.out.println("product name");
				String proname=s.next();
				System.out.println("minsell quantity");
				int qty=s.nextInt();
				System.out.println("price");
				int price=s.nextInt();
				
				PreparedStatement st=con.getConnection().prepareStatement("insert into addproduct(productid,name,minsellquantity,price) values(?,?,?,?);");
				st.setInt(1,id);
				st.setString(2, proname);
				st.setInt(3, qty);
				st.setInt(4,price);
				st.executeUpdate();
				System.out.println("Product added successfully");
				break;
			case 2:
				//display
				
				Statement stmt=con.getConnection().createStatement();
				ResultSet re=stmt.executeQuery("select*from addproduct");
				while(re.next())
					{
						System.out.println("##########*********#########");
						System.out.println("Product ID   ->"+re.getString(1));
						System.out.println("Product Name ->"+re.getString(2));
						System.out.println("Quantity     ->"+re.getString(3));
						System.out.println("Price        ->"+re.getString(4));
						System.out.println("##########*********#########");
					}
				break;
			case 3:
				// remove data from table
				System.out.println("product to be removed(ID)");
				int reid=s.nextInt();
				PreparedStatement stm=con.getConnection().prepareStatement("DELETE FROM addproduct WHERE productid=?");
				stm.setInt(1,reid);
				stm.execute();
				System.out.println("item removed");
				break;
			case 4:
				//update table details
				Statement state=con.getConnection().createStatement();
				ResultSet result=state.executeQuery("select*from addproduct");
				while(result.next())
					{
						System.out.println("##########*********#########");
						System.out.println("Product ID   ->"+result.getString(1));
						System.out.println("Product Name ->"+result.getString(2));
						System.out.println("##########*********#########");
					}
				System.out.println("item to be updated (id)");
				int item=s.nextInt();
				ResultSet resu=state.executeQuery("select *from addproduct ");
				int quantity;
				while(resu.next())
					{
					int itemid=resu.getInt(1);
						if(item==itemid)
							{
							quantity=resu.getInt(3);
							System.out.println("quantiy to  be added");
							int quantity1=s.nextInt();
							int last=quantity+quantity1;
							PreparedStatement sts=con.getConnection().prepareStatement("update addproduct set minsellquantity=? where minsellquantity=?");
							sts.setInt(1,last);
							sts.setInt(2,quantity);
							sts.executeUpdate();
							}
					}
				
				break;
			case 5:// got to main
				First f1=new First();
				f1.choice();
				break;
			default:System.out.println("not in the menu");
			}
		}
		while(n==1||n==2||n==3||n==4);
		}
		else
		{
			System.out.println("login faild");
			First f1=new First();
			f1.choice();
		}
		
	}

}
