package substa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import substa.beans.Auction;
import substa.beans.Customer;
import substa.beans.Employer;
import substa.beans.User;
import substa.beans.Item;
import substa.beans.Post;
import substa.beans.SalesRecord;
import substa.beans.BidHistory;
import substa.beans.AuctionDetailInfo;

public class DBManagers {

	private String dbURL = "";
	private String dbuser = "";
	private String dbpass = "";

	public DBManagers(){
		super();
	}
	
	public DBManagers(String url, String user, String pw) {
		dbURL = url;
		dbuser = user;
		dbpass = pw;
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpass() {
		return dbpass;
	}

	public void setDbpass(String dbpass) {
		this.dbpass = dbpass;
	}

	/*
	 * Func to open connection to the db
	 * 
	 * @author Danbee Park
	 */
	public Connection getConnection() {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(getDbURL(), getDbuser(), getDbpass());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * Func to close open connection to db
	 * 
	 * @author Danbee Park
	 */
	public void closeConnection(Connection conn) {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	/*
	 * Fuct to get User info from db
	 * 
	 * @author Danbee Park
	 * 
	 */
	public User getUser(String email, String pw) {
		User user = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT * FROM Person WHERE Email=? AND pw=? ";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, email);
				ps.setString(2, pw);
				rs = ps.executeQuery();
				while (rs.next()) {
					user = new User();
					user.setSsn(rs.getLong("SSN"));
					user.setFirst(rs.getString("FirstName"));
					user.setLast(rs.getString("LastName"));
					user.setAddress(rs.getString("Address"));
					user.setZipcode(rs.getInt("ZipCode"));
					user.setTelephone(rs.getLong("Telephone"));
					user.setEmail(rs.getString("Email"));
					user.setPw(rs.getString("pw"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		return user;
	}

	public Customer getCustomer(User user) {
		Customer customer = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT * FROM Customer WHERE CustomerID=?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setLong(1, user.getSsn());
				rs = ps.executeQuery();
				while (rs.next()) {
					customer = new Customer(user);
					customer.setCreditCardNum(rs.getLong("CreditCardNum"));
					customer.setRating(rs.getFloat("Rating"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		return customer;
	}
	public Customer getCustomer(long user) {
		Customer customer = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT * FROM Customer WHERE CustomerID=?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setLong(1, user);
				rs = ps.executeQuery();
				while (rs.next()) {
					customer = new Customer();
					customer.setSsn(rs.getLong("SSN"));
					customer.setFirst(rs.getString("FirstName"));
					customer.setLast(rs.getString("LastName"));
					customer.setAddress(rs.getString("Address"));
					customer.setZipcode(rs.getInt("ZipCode"));
					customer.setTelephone(rs.getLong("Telephone"));
					customer.setEmail(rs.getString("Email"));
					customer.setPw(rs.getString("pw"));
					customer.setCreditCardNum(rs.getLong("CreditCardNum"));
					customer.setRating(rs.getFloat("Rating"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		return customer;
	}
	public Employer getEmployer(User user) {
		Employer employee = null;
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sqlQuery = "SELECT * FROM Employee WHERE EmployeeID=?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setLong(1, user.getSsn());
				rs = ps.executeQuery();
				while (rs.next()) {
					employee = new Employer(user);
					employee.setLevel(rs.getInt("Level"));
					employee.setStartDate((rs.getTimestamp("StartDate")));
					employee.setHourlyRate(rs.getFloat("HourlyRate"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		return employee;
	}
	
	public boolean addUser(User user){
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try{
				String sql = "INSERT INTO Person(SSN, LastName, FirstName, Address, ZipCode, Telephone, Email, pw)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
					ps = conn.prepareStatement(sql);
					ps.setLong(1, user.getSsn());
					ps.setString(2, user.getLast());
					ps.setString(3, user.getFirst());
					ps.setString(4, user.getAddress());
					ps.setInt(5, user.getZipcode());
					ps.setLong(6, user.getTelephone());
					ps.setString(7, user.getEmail());
					ps.setString(8, user.getPw());
					return ps.execute();
					
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						ps.close();
						
					}catch(SQLException ex){
						ex.printStackTrace();
					}
					closeConnection(conn);
				}
			
		}
	
		return true;
	}
	
	public boolean addCustomer(User customer, long credit){
	
		addUser(customer);
		
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Customer (CustomerID, Rating, CreditCardNum)" + " VALUES (?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, customer.getSsn());
				ps.setInt(2, 0);
				ps.setLong(3, credit);
				
				return ps.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public boolean addEmployer(User employer,int level, Timestamp start, float rate){
		
		System.out.println("adUser : " + addUser(employer));
		
		Connection conn = getConnection();
		if (conn != null) {
			PreparedStatement ps = null;
			String sql = "INSERT INTO Employee (EmployeeID, Level, StartDate, HourlyRate) " + "VALUES (?, ?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, employer.getSsn());
				ps.setInt(2, level);
				ps.setTimestamp(3, start);
				ps.setFloat(4, rate);
				
				return ps.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		return true;
	}
	
	public boolean changeCustomer(Customer customer) {
		
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			try {
				String sql1 = "UPDATE Customer"
						+ "SET Rating=?, CreditCardNum=? "
						+ "WHERE CustomerID=?";
				ps1 = conn.prepareStatement(sql1);
				ps1.setFloat(1, customer.getRating());
				ps1.setLong(2, customer.getCreditCardNum());
				ps1.setLong(3, customer.getSsn());
				
				String sql2 = "UPDATE Person "
						+ "SET SSN=?, LastName=?, FirstName=?, Address=?, ZipCode=?, Telephone=?, Email=?, pw=? "
						+ "WHERE SSN=?";
				ps2 = conn.prepareStatement(sql2);
				ps2.setLong(1, customer.getSsn());
				ps2.setString(2, customer.getLast());
				ps2.setString(3, customer.getFirst());
				ps2.setString(4, customer.getAddress());
				ps2.setInt(5, customer.getZipcode());
				ps2.setLong(6, customer.getTelephone());
				ps2.setString(7, customer.getEmail());
				ps2.setString(8, customer.getPw());
				ps2.setLong(1, customer.getSsn());
				
				return ps1.execute()&&ps2.execute();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					ps2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public boolean changeEmployer(Employer employer) {
		
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			try {
				String sql1 = "UPDATE Employee "
						+ "SET Level=?, StartDate=?, HourlyRate=? "
						+ "WHERE EmployeeID=? ";
				ps1 = conn.prepareStatement(sql1);
				ps1.setLong(1, employer.getLevel());
				ps1.setTimestamp(2, employer.getStartDate());
				ps1.setFloat(3, employer.getHourlyRate());
				ps1.setLong(4, employer.getSsn());
				
				String sql2 = "UPDATE Person "
						+ "SET SSN=?, LastName=?, FirstName=?, Address=?, ZipCode=?, Telephone=?, Email=?, pw=? "
						+ "WHERE Person=? ";
				ps2 = conn.prepareStatement(sql2);
				ps2.setLong(1, employer.getSsn());
				ps2.setString(2, employer.getLast());
				ps2.setString(3, employer.getFirst());
				ps2.setString(4, employer.getAddress());
				ps2.setInt(5, employer.getZipcode());
				ps2.setLong(6, employer.getTelephone());
				ps2.setString(7, employer.getEmail());
				ps2.setString(8, employer.getPw());
				ps2.setLong(1, employer.getSsn());
				
				return ps1.execute()&&ps2.execute();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					ps2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public boolean deleteCustomer(long customerId) {
		
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			
			try {
				String sql = "DELETE FROM Person "
						+ "WHERE SSN=? ";
				ps = conn.prepareStatement(sql);
				ps.setLong(1, customerId);
				
				return ps.execute();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public boolean deleteEmployer(long employerId) {
		
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			
			try {
				String sql = "DELETE FROM Person "
						+ "WHERE SSN=? ";
				ps = conn.prepareStatement(sql);
				ps.setLong(1, employerId);
				
				return ps.execute();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public ArrayList<Item> getAllItems() {
		Connection conn = getConnection();
		ArrayList<Item> allItems = new ArrayList<Item>();
		Item item = null;
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT * FROM Item ";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					item = new Item();
					item.setItemID(rs.getInt("ItemID"));
					item.setItemName(rs.getString("ItemName"));
					item.setItemType(rs.getString("ItemType"));
					item.setNumCopies(rs.getInt("NumCopies"));
					item.setDescription(rs.getString("Description"));
					item.setImgsrc(rs.getString("img"));
					allItems.add(item);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return allItems;
	}

	public ArrayList<SalesRecord> getSalesByItemName(String itemName) {
		
		ArrayList<SalesRecord> salesRecordByItemName = new ArrayList<SalesRecord>();
		SalesRecord salesRecord = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT S.BuyerID, S.SellerID, S.Price, S.Date, S.AuctionID "
						+ "FROM Sales S, Auction A, Item I "
						+ "WHERE S.AuctionID = A.AuctionID AND A.ItemID = I.ItemID AND I.ItemName = ? ";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, itemName);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					salesRecord = new SalesRecord();
					salesRecord.setBuyerID(rs.getInt("BuyerID"));
					salesRecord.setSellerID(rs.getLong("SellerID"));
					salesRecord.setPrice(rs.getFloat("Price"));
					salesRecord.setDate(rs.getTimestamp("Date"));
					salesRecord.setAuctionID(rs.getInt("AuctionID"));
					salesRecordByItemName.add(salesRecord);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return salesRecordByItemName;
	}
	
	public ArrayList<SalesRecord> getSalesByCustomerName(String firstName, String lastName) {
		
		ArrayList<SalesRecord> salesRecordByCustomerName = new ArrayList<SalesRecord>();
		SalesRecord salesRecord = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT S.BuyerID, S.SellerID, S.Price, S.Date, S.AuctionID "
						+ "FROM Sales S, Auction A "
						+ "WHERE (A.BuyerID = P.SSN OR A.SellerID = P.SSN) AND P.FirstName =? AND P.LastName = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					salesRecord = new SalesRecord();
					salesRecord.setBuyerID(rs.getInt("BuyerID"));
					salesRecord.setSellerID(rs.getLong("SellerID"));
					salesRecord.setPrice(rs.getFloat("Price"));
					salesRecord.setDate(rs.getTimestamp("Date"));
					salesRecord.setAuctionID(rs.getInt("AuctionID"));
					salesRecordByCustomerName.add(salesRecord);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return salesRecordByCustomerName;
	}

	public ArrayList<SalesRecord> getSalesByMonth(int month) {
		
		ArrayList<SalesRecord> salesRecordByMonth = new ArrayList<SalesRecord>();
		SalesRecord salesRecord = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT * FROM Sales WHERE MONTH(Date) = ? ORDER BY Date DESC ";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, month);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					salesRecord = new SalesRecord();
					salesRecord.setBuyerID(rs.getInt("BuyerID"));
					salesRecord.setSellerID(rs.getLong("SellerID"));
					salesRecord.setPrice(rs.getFloat("Price"));
					salesRecord.setDate(rs.getTimestamp("Date"));
					salesRecord.setAuctionID(rs.getInt("AuctionID"));
					salesRecordByMonth.add(salesRecord);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return salesRecordByMonth;
	}
	
	public float getRevenueByItemName(String itemName) {
		
		float revenue = 0;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT SUM(S.Price) AS Revenue "
						+ "FROM Sales A, Auction A, Item I "
						+ "WHERE S.AuctionID = A.AuctionID AND A.ItemID = I.ItemID AND I.ItemName = ? ";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, itemName);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					revenue = rs.getFloat("Revenue");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return revenue;
	}
	
	public float getRevenueByItemType(String itemType) {
		
		float revenue = 0;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT SUM(S.Price) AS Revenue "
						+ "FROM Sales A, Auction A "
						+ "WHERE S.AuctionID = A.AuctionID AND A.ItemID IN ( "
						+ "	SELECT I.ItemID "
						+ "	FROM Item I "
						+ "	WHERE I.ItemType = ?)";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, itemType);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					revenue = rs.getFloat("Revenue");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return revenue;
	}
	
	public float getRevenueByBuyerName(String firstName, String lastName) {
		
		float revenue = 0;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT SUM(S.Price) AS Revenue "
						+ "FROM Sales A "
						+ "WHERE S.BuyerID = ( "
						+ "	SELECT P.SSN "
						+ "	FROM Person P "
						+ "	WHERE P.FirstName = ? AND P.LastName = ?)";
				
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					revenue = rs.getFloat("Revenue");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return revenue;
	}
	
	public ArrayList<Employer> getTopRevenueEmployee() {
		ArrayList<Employer> topRevenueEmployer = new ArrayList<Employer>();
		int topEmployerID = 0;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			try {
				String sqlQuery1 = "SELECT R.EmployeeID "
						+ "FROM RepTotalRev R "
						+ "WHERE R.TotalRev = ( "
						+ "	SELECT MAX(R2.TotalRev) "
						+ "	FROM RepTotalRev R2)" ;
				ps1 = conn.prepareStatement(sqlQuery1);
				rs1 = ps1.executeQuery();
				
				while(rs1.next()) {
					topEmployerID = rs1.getInt("EmployeeID");
				}
				
				rs1.close();
				
				if(topEmployerID > 0) {
					String sqlQuery2 = "SELECT * "
							+ "FROM Person, Employee "
							+ "WHERE EmployeeID = SSN AND SSN = ?";
					
					ps2 = conn.prepareStatement(sqlQuery2);
					ps2.setInt(1, topEmployerID);
					rs2 = ps2.executeQuery();
					
					User user = null;
					Employer employer = null;
					
					while(rs2.next()) {
						user = new User();
						user.setFirst(rs2.getString("FirstName"));
						user.setLast(rs2.getString("LastName"));
						user.setAddress(rs2.getString("Address"));
						user.setZipcode(rs2.getInt("ZipCode"));
						user.setTelephone(rs2.getLong("Telephone"));
						user.setSsn(rs2.getLong("SSN"));
						user.setEmail(rs2.getString("Email"));
						user.setPw(rs2.getString("pw"));
						employer = new Employer(user);
						employer.setLevel(rs2.getInt("Level"));
						employer.setStartDate(rs2.getTimestamp("StartDate"));
						employer.setHourlyRate(rs2.getFloat("HourlyRate"));
						topRevenueEmployer.add(employer);
					}
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					ps2.close();
					rs1.close();
					rs2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return topRevenueEmployer;
	}
	
	public ArrayList<Customer> getTopRevenueCustomer() {
		ArrayList<Customer> topRevenueCustomer = new ArrayList<Customer>();
		long topCustomerID = 0;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			try {
				String sqlQuery1 = "SELECT S.SellerID "
						+ "FROM SellerTotalRev S "
						+ "WHERE S.TotalRev = ( "
						+ "	SELECT MAX(S2.TotalRev) "
						+ "	FROM SellerTotalRev S2)";
				ps1 = conn.prepareStatement(sqlQuery1);
				rs1 = ps1.executeQuery();
				
				while(rs1.next()) {
					topCustomerID = rs1.getLong("SellerID");
				}
				
				rs1.close();
				
				if(topCustomerID > 0) {
					String sqlQuery2 = "SELECT * "
							+ "FROM Person, Customer "
							+ "WHERE CustomerID = SSN AND SSN = ?";
					
					ps2 = conn.prepareStatement(sqlQuery2);
					ps2.setLong(1, topCustomerID);
					rs2 = ps2.executeQuery();
					
					User user = null;
					Customer customer = null;
					
					while(rs2.next()) {
						user = new User();
						user.setFirst(rs2.getString("FirstName"));
						user.setLast(rs2.getString("LastName"));
						user.setAddress(rs2.getString("Address"));
						user.setZipcode(rs2.getInt("ZipCode"));
						user.setTelephone(rs2.getLong("Telephone"));
						user.setSsn(rs2.getLong("SSN"));
						user.setEmail(rs2.getString("Email"));
						user.setPw(rs2.getString("pw"));
						customer = new Customer(user);
						customer.setRating(rs2.getFloat("Rating"));
						customer.setCreditCardNum(rs2.getLong("CreditCardNum"));
						topRevenueCustomer.add(customer);
					}
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					ps2.close();
					rs1.close();
					rs2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return topRevenueCustomer;
	}
	
	public boolean addSalesRecords() {
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try {
				String sqlQuery = "INSERT INTO Sales(BuyerID, SellerID, Price, Date, AuctionID) "
						+ "SELECT B.CustomerID, P.CustomerID, MAX(B.BidPrice), P.ExpireDate, A.AuctionID "
						+ "FROM Post P, Bid B, Auction A "
						+ "WHERE P.ExpireDate < NOW() AND P.AuctionID = B.AuctionID AND "
						+ "	P.AuctionID = A.AuctionID AND B.BidPrice >= P.ReservedPrice AND "
						+ "	P.AuctionID NOT IN (SELECT AuctionID FROM Sales) "
						+ "GROUP BY B.AuctionID";
				ps = conn.prepareStatement(sqlQuery);
				return ps.execute();
					
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
			
		}
		
		return true;
	}
	
	public ArrayList<Customer> getMailingList() {
	
		Connection conn = getConnection();
		ArrayList<Customer> mailingList = new ArrayList<Customer>();
		Customer customer = null;
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT * FROM Person, Customer WHERE Customer.CustomerID = Person.SSN";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				while(rs.next()) {
					customer = new Customer();
					customer.setSsn(rs.getLong("SSN"));
					customer.setLast(rs.getString("LastName"));
					customer.setFirst(rs.getString("FirstName"));
					customer.setAddress(rs.getString("Address"));
					customer.setZipcode(rs.getInt("ZipCode"));
					customer.setTelephone(rs.getLong("Telephone"));
					customer.setEmail(rs.getString("Email"));
					customer.setPw(rs.getString("pw"));
					customer.setRating(rs.getFloat("Rating"));
					customer.setCreditCardNum(rs.getLong("CreditCardNum"));
					mailingList.add(customer);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return mailingList;
	}
	
	public ArrayList<AuctionDetailInfo> getPersonalSuggestion(long customerID) {
		
		Connection conn = getConnection();
		ArrayList<AuctionDetailInfo> suggestion = new ArrayList<AuctionDetailInfo>();
		AuctionDetailInfo auction = null;
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT I.ItemName, I.ItemType, I.Description, I.img, A.AuctionID, A.BidIncrement, "
						+ "A.MinimumBid, A.Copies_Sold, P.CustomerID, P.ExpireDate "
						+ "FROM Sales S, Auction A, Item I, Post P "
						+ "WHERE NOW() < P.ExpireDate AND S.AuctionID = A.AuctionID AND P.AuctionID = A.AuctionID AND I.NumCopies > 0 "
						+ "AND A.ItemID = I.ItemID AND S.BuyerID IN ("
						+ "	SELECT S.BuyerID "
						+ "	FROM Sales S, Auction A "
						+ "	WHERE S.AuctionID = A.AuctionID AND A.ItemID IN ("
						+ "	SELECT A.ItemID "
						+ "	FROM Auction A, Sales S "
						+ "	WHERE S.AuctionID = A.AuctionID AND S.BuyerID = ?) "
						+ "	) "
						+ "GROUP BY A.ItemID "
						+ "ORDER BY COUNT(A.ItemID) "
						+ "LIMIT 20";
				
				ps = conn.prepareStatement(sqlQuery);
				ps.setLong(1, customerID);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					auction = new AuctionDetailInfo();
					auction.setItemName(rs.getString("ItemName"));
					auction.setItemType(rs.getString("ItemType"));
					auction.setDescription(rs.getString("Description"));
					auction.setImgSrc(rs.getString("img"));
					auction.setAuctionID(rs.getInt("AuctionID"));
					auction.setBidInc(rs.getFloat("BidIncrement"));
					auction.setMinBid(rs.getFloat("MinimumBid"));
					auction.setCopy(rs.getInt("Copies_Sold"));
					auction.setSellerID(rs.getLong("CustomerID"));
					auction.setEndDate(rs.getTimestamp("ExpireDate"));
					suggestion.add(auction);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return suggestion;
	}
	
	public float getOwnBidMax(int auctionID, long customerID) {
		float ownBidMax = 0;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT MaximumBid"
						+ "FROM Bid "
						+ "WHERE CustomerID = ? AND AuctionID = ? "
						+ "ORDER BY MaximumBid DECS"
						+ "LIMIT 1";
				ps = conn.prepareStatement(sqlQuery);
				ps.setLong(1, customerID);
				ps.setInt(2, auctionID);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					ownBidMax = rs.getFloat("MaximumBid");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return ownBidMax;
	}
	
	public BidHistory getWinnersBid(int auctionID) {
		
		BidHistory winnersBid = new BidHistory();
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			try {
				String sqlQuery1 = "SELECT * "
						+ "FROM Bid "
						+ "WHERE AuctionID = ? "
						+ "ORDER BY BidTime DESC "
						+ "LIMIT 1 ";
				ps1 = conn.prepareStatement(sqlQuery1);
				ps1.setInt(1, auctionID);
				rs1 = ps1.executeQuery();
				
				while(rs1.next()) {
					winnersBid.setAuctionID(rs1.getInt("AuctionID"));
					winnersBid.setCustomerID(rs1.getLong("CustomerID"));
					winnersBid.setItemID(rs1.getInt("ItemID"));
					winnersBid.setBidTime(rs1.getTimestamp("BidTime"));
					winnersBid.setMaxBid(rs1.getFloat("MaximumBid"));
					winnersBid.setWinnerID(rs1.getLong("WinnerID"));
				}
				
				ps1.close(); rs1.close();
				
				String sqlQuery2 = "SELECT BidPrice "
						+ "FROM Bid "
						+ "WHERE AuctionID = ? "
						+ "ORDER BY BidTime DESC "
						+ "LIMIT 1 ";
				ps2 = conn.prepareStatement(sqlQuery2);
				ps2.setInt(1, auctionID);
				rs2 = ps2.executeQuery();
				
				while(rs2.next()) {
					winnersBid.setBidPrice(rs2.getFloat("BidPrice"));
				}
				
				ps2.close(); ps2.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					rs1.close();
					ps2.close();
					rs2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return winnersBid;
	}
	
	public ArrayList<BidHistory> getBidHistoryByAuction(int auctionID) {
		
		ArrayList<BidHistory> bidHistoryByAuction = new ArrayList<BidHistory>();
		Connection conn = getConnection();
		BidHistory bidHistory = null;
		
		if(conn != null){
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try{
				String sql = "SELECT * "
						+ "FROM Bid "
						+ "WHERE AuctionID = ? "
						+ "ORDER BY B.BidTime DESC ";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, auctionID);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					bidHistory = new BidHistory();
					bidHistory.setAuctionID(rs.getInt("AuctionID"));
					bidHistory.setCustomerID(rs.getLong("CustomerID"));
					bidHistory.setItemID(rs.getInt("ItemID"));
					bidHistory.setBidPrice(rs.getFloat("BidPrice"));
					bidHistory.setBidTime(rs.getTimestamp("BidTime"));
					bidHistory.setMaxBid(rs.getFloat("MaximumBid"));
					bidHistory.setWinnerID(rs.getLong("WinnerID"));
					bidHistoryByAuction.add(bidHistory);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return bidHistoryByAuction;
	}
	
	public boolean changeBidInc(int auctionID, float bidInc) {
		
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			
			try {
				String sql1 = "UPDATE Auction"
						+ "SET BidIncrement=? "
						+ "WHERE AuctionID=? ";
				ps1 = conn.prepareStatement(sql1);
				ps1.setFloat(1, bidInc);
				ps1.setInt(2, auctionID);
				
				return ps1.execute();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public boolean isWinner(long customerID, int auctionID) {
		
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT * "
						+ "FROM Bid"
						+ "WHERE AuctionID = ? "
						+ "ORDER BY BidTime DESC"
						+ "LIMIT 1";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, auctionID);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					long winnerID = rs.getLong("WinnerID");
					return winnerID == customerID;
				}
				
				return ps.execute();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public boolean addBid(BidHistory bid) {
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try{
				String sql = "INSERT INTO Bid(CustomerID, AuctionID, ItemID, BidTiem, MaximumBid, BidPrice, WinnerID)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
				
				ps = conn.prepareStatement(sql);
				ps.setLong(1, bid.getCustomerID());
				ps.setInt(2, bid.getAuctionID());
				ps.setInt(3, bid.getItemID());
				ps.setTimestamp(4, bid.getBidTime());
				ps.setFloat(5, bid.getMaxBid());
				ps.setFloat(6, bid.getBidPrice());
				ps.setLong(7, bid.getWinnerID());
				return ps.execute();
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public ArrayList<AuctionDetailInfo> getTakenAuctions(Customer customer) {
		ArrayList<AuctionDetailInfo> takenAuctions = new ArrayList<AuctionDetailInfo>();
		Connection conn = getConnection();
		AuctionDetailInfo takenAuction = null;
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT I.ItemName, I.ItemType, I.Description, I.img, A.AuctionID, "
						+ "A.BidIncrement, A.MinimumBid, A.Copies_Sold, P.CustomerID, P.ExpireDate, P.ReservedPrice "
						+ "FROM Item I, Auction A, Post P "
						+ "WHERE I.ItemID = A.ItemID AND P.AuctionID = A.AuctionID AND A.AuctionID IN ( "
						+ "	SELECT B.AuctionID "
						+ "	FROM Bid B "
						+ "	WHERE B.CustomerID = ? "
						+ "	GROUP BY B.AuctionID "
						+ " ORDER BY AuctionID DESC) ";

				ps = conn.prepareStatement(sqlQuery);
				ps.setLong(1, customer.getSsn());
				rs = ps.executeQuery();
				
				while(rs.next()) {
					takenAuction = new AuctionDetailInfo();
					takenAuction.setItemName(rs.getString("ItemName"));
					takenAuction.setItemType(rs.getString("ItemType"));
					takenAuction.setDescription(rs.getString("Description"));
					takenAuction.setImgSrc(rs.getString("img"));
					takenAuction.setAuctionID(rs.getInt("AuctionID"));
					takenAuction.setBidInc(rs.getFloat("BidIncrement"));
					takenAuction.setMinBid(rs.getFloat("MinimumBid"));
					takenAuction.setCopy(rs.getInt("Copies_Sold"));
					takenAuction.setSellerID(rs.getLong("CustomerID"));
					takenAuction.setEndDate(rs.getTimestamp("ExpireDate"));
					takenAuction.setPrice(rs.getFloat("ReservedPrice"));
					takenAuctions.add(takenAuction);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return takenAuctions;
	}
	
	public ArrayList<BidHistory> getTakenBids(Customer customer) {
		ArrayList<BidHistory> takenBids = new ArrayList<BidHistory>();
		Connection conn = getConnection();
		BidHistory bidHistory = null;
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT B.AuctionID, B.CustomerID, B.ItemID, I.ItemName, B.BidPrice, B.BidTime, B.MaximumBid "
						+ "FROM Bid B, Item I "
						+ "WHERE B.CustomerID = ? AND B.ItemID = I.ItemID "
						+ "GROUP BY AuctionID ASC, BidTime DESC";
				ps = conn.prepareStatement(sqlQuery);
				ps.setLong(1, customer.getSsn());
				rs = ps.executeQuery();
				
				while(rs.next()) {
					bidHistory = new BidHistory();
					bidHistory.setAuctionID(rs.getInt("AuctionID"));
					bidHistory.setCustomerID(rs.getLong("CustomerID"));
					bidHistory.setItemID(rs.getInt("ItemID"));
					bidHistory.setItemName(rs.getString("ItemName"));
					bidHistory.setBidPrice(rs.getFloat("BidPrice"));
					bidHistory.setBidTime(rs.getTimestamp("BidTime"));
					bidHistory.setMaxBid(rs.getFloat("MaximumBid"));
					takenBids.add(bidHistory);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return takenBids;
	}
	
	public boolean changeAuctionInfo(AuctionDetailInfo auctionInfo) {
		
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			
			try {
				String sqlQuery = "UPDATE Auction "
						+ "SET BidIncrement = ? "
						+ "WHERE AuctionID = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setFloat(1, auctionInfo.getBidInc());
				ps.setInt(2, auctionInfo.getAuctionID());
				return ps.execute();
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}

	public AuctionDetailInfo getAuctionInfoByAuctionID(int auctionID) {
		
		AuctionDetailInfo auctionInfo = new AuctionDetailInfo();
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT I.ItemName, I.ItemType, I.Description, I.img, "
						+ "A.AuctionID, A.BidIncrement, A.MinimumBid, A.Copies_Sold, P.CustomerID, P.ExpireDate, P.ReservedPrice "
						+ "FROM Item I, Auction A, Post P "
						+ "WHERE P.AuctionID = A.AuctionID AND I.ItemID = A.ItemID AND A.AuctionID = ? ";
				ps = conn.prepareStatement(sqlQuery);
				ps.setInt(1, auctionID);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					auctionInfo.setItemName(rs.getString("ItemName"));
					auctionInfo.setItemType(rs.getString("ItemType"));
					auctionInfo.setDescription(rs.getString("Description"));
					auctionInfo.setImgSrc(rs.getString("img"));
					auctionInfo.setAuctionID(rs.getInt("AuctionID"));
					auctionInfo.setBidInc(rs.getFloat("BidIncrement"));
					auctionInfo.setMinBid(rs.getFloat("MinimumBid"));
					auctionInfo.setCopy(rs.getInt("Copies_Sold"));
					auctionInfo.setSellerID(rs.getLong("CustomerID"));
					auctionInfo.setEndDate(rs.getTimestamp("ExpireDate"));
					auctionInfo.setPrice(rs.getFloat("ReservedPrice"));
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return auctionInfo;
	}
	
	public ArrayList<AuctionDetailInfo> getAuctionInfoBySellerName(String firstName, String lastName) {
		
		ArrayList<AuctionDetailInfo> auctionInfoBySellerName = new ArrayList<AuctionDetailInfo>();
		AuctionDetailInfo auctionInfo = null;
		Connection conn = getConnection();
		long sellerId = 0;
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			try {
				String sqlQuery1 = "SELECT SSN "
						+ "FROM Person "
						+ "WHERE FirstName = ? AND LastName = ? ";
				ps1 = conn.prepareStatement(sqlQuery1);
				ps1.setString(1, firstName);
				ps1.setString(2, lastName);
				rs1 = ps1.executeQuery();
				while(rs1.next()) {
					sellerId = rs1.getLong("SSN");
				}
				rs1.close();
				
				if(sellerId > 0) {
					String sqlQuery2 = "SELECT I.ItemName, I.ItemType, I.Description, I.img, "
							+ "A.AuctionID, A.BidIncrement, A.MinimumBid, A.Copies_Sold, P.CustomerID, P.ExpireDate, P.ReservedPrice "
							+ "FROM Item I, Auction A, Post P "
							+ "WHERE P.ExpireDate > NOW() AND A.AuctionID = P.AuctionID AND I.ItemID = A.ItemID AND P.CustomerID = ? ";
					ps2 = conn.prepareStatement(sqlQuery2);
					ps2.setLong(1, sellerId);
					rs2 = ps2.executeQuery();
					
					while(rs2.next()) {
						auctionInfo = new AuctionDetailInfo();
						auctionInfo.setItemName(rs2.getString("ItemName"));
						auctionInfo.setItemType(rs2.getString("ItemType"));
						auctionInfo.setDescription(rs2.getString("Description"));
						auctionInfo.setImgSrc(rs2.getString("img"));
						auctionInfo.setAuctionID(rs2.getInt("AuctionID"));
						auctionInfo.setBidInc(rs2.getFloat("BidIncrement"));
						auctionInfo.setMinBid(rs2.getFloat("MinimumBid"));
						auctionInfo.setCopy(rs2.getInt("Copies_Sold"));
						auctionInfo.setSellerID(rs2.getLong("CustomerID"));
						auctionInfo.setEndDate(rs2.getTimestamp("ExpireDate"));
						auctionInfo.setPrice(rs2.getFloat("ReservedPrice"));
						auctionInfoBySellerName.add(auctionInfo);
					}
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					ps2.close();
					rs1.close();
					rs2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return auctionInfoBySellerName;
	}
	
	public ArrayList<AuctionDetailInfo> getAuctionInfoByItemType(String itemType) {
		
		ArrayList<AuctionDetailInfo> auctionInfoByItemType = new ArrayList<AuctionDetailInfo>();
		AuctionDetailInfo auctionInfo = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT I.ItemName, I.ItemType, I.Description, I.img, "
						+ "A.AuctionID, A.BidIncrement, A.MinimumBid, A.Copies_Sold, P.CustomerID, P.ExpireDate, P.ReservedPrice "
						+ "FROM Item I, Auction A, Post P "
						+ "WHERE NOW() < P.ExpireDate AND P.AuctionID = A.AuctionID AND I.ItemID = A.ItemID AND I.ItemType = ? ";
				ps = conn.prepareStatement(sqlQuery);
				if(itemType.equals("Men's Clothing")) {
					ps.setString(1, "Men\'s Clothing");
				} else if(itemType.equals("Women's Clothing")) {
					ps.setString(1, "Women\'s Clothing");
				} else if(itemType.equals("Kids' Clothing")) {
					ps.setString(1, "Kids\' Clothing");
				} else {
					ps.setString(1, itemType);
				}
				rs = ps.executeQuery();
				
				while(rs.next()) {
					auctionInfo = new AuctionDetailInfo();
					auctionInfo.setItemName(rs.getString("ItemName"));
					auctionInfo.setItemType(rs.getString("ItemType"));
					auctionInfo.setDescription(rs.getString("Description"));
					auctionInfo.setImgSrc(rs.getString("img"));
					auctionInfo.setAuctionID(rs.getInt("AuctionID"));
					auctionInfo.setBidInc(rs.getFloat("BidIncrement"));
					auctionInfo.setMinBid(rs.getFloat("MinimumBid"));
					auctionInfo.setCopy(rs.getInt("Copies_Sold"));
					auctionInfo.setSellerID(rs.getLong("CustomerID"));
					auctionInfo.setEndDate(rs.getTimestamp("ExpireDate"));
					auctionInfo.setPrice(rs.getFloat("ReservedPrice"));
					auctionInfoByItemType.add(auctionInfo);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return auctionInfoByItemType;
	}
	 
	public ArrayList<AuctionDetailInfo> getAuctionInfoByItemName(ArrayList<String> keywords) {
		
		ArrayList<AuctionDetailInfo> auctionInfoByItemName = new ArrayList<AuctionDetailInfo>();
		AuctionDetailInfo auctionInfo = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "";
				for(int i = 0; i < keywords.size(); i++) {
					sqlQuery = "SELECT I.ItemName, I.ItemType, I.Description, I.img, "
							+ "A.AuctionID, A.BidIncrement, A.MinimumBid, A.Copies_Sold, P.CustomerID, P.ExpireDate, P.ReservedPrice "
							+ "FROM Item I, Auction A, Post P "
							+ "WHERE P.ExpireDate > NOW() AND P.AuctionID = A.AuctionID "
							+ "AND I.ItemID = A.ItemID AND I.ItemName LIKE ? ";
					ps = conn.prepareStatement(sqlQuery);
					ps.setString(1, "%" + keywords.get(i) + "%");
					rs = ps.executeQuery();
					
					while(rs.next()) {
						auctionInfo = new AuctionDetailInfo();
						auctionInfo.setItemName(rs.getString("ItemName"));
						auctionInfo.setItemType(rs.getString("ItemType"));
						auctionInfo.setDescription(rs.getString("Description"));
						auctionInfo.setImgSrc(rs.getString("img"));
						auctionInfo.setAuctionID(rs.getInt("AuctionID"));
						auctionInfo.setBidInc(rs.getFloat("BidIncrement"));
						auctionInfo.setMinBid(rs.getFloat("MinimumBid"));
						auctionInfo.setCopy(rs.getInt("Copies_Sold"));
						auctionInfo.setSellerID(rs.getLong("CustomerID"));
						auctionInfo.setEndDate(rs.getTimestamp("ExpireDate"));
						auctionInfo.setPrice(rs.getFloat("ReservedPrice"));
						auctionInfoByItemName.add(auctionInfo);
					}
					ps.close();
					rs.close();
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return auctionInfoByItemName;
	}
	
	public boolean addItem(Item item) {
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try{
				String sql = "INSERT INTO Item(ItemName, ItemType, NumCopies, Description, img)"
						+ " VALUES (?, ?, ?, ?, ?)";
			
				ps = conn.prepareStatement(sql);
				ps.setString(1, item.getItemName());
				ps.setString(2, item.getItemType());
				ps.setInt(3, item.getNumCopies());
				ps.setString(4, item.getDescription());
				ps.setString(5, item.getImgsrc());
				
				return ps.execute();
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public int getLatestItemID() {
		
		int latestItemID = 0;
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				String sql = "SELECT MAX(ItemID) AS ItemID "
						+ "FROM Item";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					latestItemID = rs.getInt("ItemID");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return latestItemID;
	}
	
	public int getManagerID() {
		
		int ManagerID = 0;
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				String sql = "SELECT EmployeeID "
						+ "FROM Employee "
						+ "WHERE Level = 2";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					ManagerID = rs.getInt("EmployeeID");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return ManagerID;
	}
	
	public boolean addAuction(Auction auction) {
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try{
				String sql = "INSERT INTO Auction(BidIncrement, MinimumBid, Copies_Sold, Monitor, ItemId)"
						+ " VALUES (?, ?, ?, ?, ?)";
			
				ps = conn.prepareStatement(sql);
				ps.setFloat(1, auction.getBidInc());
				ps.setFloat(2, auction.getMinBid());
				ps.setInt(3, auction.getCopy());
				ps.setInt(4, auction.getMornitor());
				ps.setInt(5, auction.getItemId());
				
				return ps.execute();
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}
	
	public int getLatesetAuctionID() {
		
		int latestAuctionID = 0;
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
				String sql = "SELECT MAX(AuctionID) AS AuctionID "
						+ "FROM Auction";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					latestAuctionID = rs.getInt("AuctionID");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return latestAuctionID;
	}
	
	public boolean addPost(Post post) {
		
		Connection conn = getConnection();
		
		if(conn != null){
			PreparedStatement ps = null;
			
			try{
				String sql = "INSERT INTO Post(CustomerID, AuctionID, ExpireDate, PostDate, ReservedPrice)"
						+ " VALUES (?, ?, ?, ?, ?)";
			
				ps = conn.prepareStatement(sql);
				ps.setLong(1, post.getCusId());
				ps.setInt(2, post.getAucId());
				ps.setTimestamp(3, post.getEndDate());
				ps.setTimestamp(4, post.getStartDate());
				ps.setFloat(5, post.getPrice());
				
				return ps.execute();
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					ps.close();
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return true;
	}

	public ArrayList<Item> getItemByCategory(String category) {
		ArrayList<Item> itemInCategory = new ArrayList<Item>();
		Item item = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT * "
						+ "FROM Post P, Auction A, Item I "
						+ "WHERE NOW() < P.ExpireDate AND P.AuctionID = A.AuctionID AND A.ItemID = I.ItemID AND I.ItemType = ?";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, category);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					item = new Item();
					item.setItemID(rs.getInt("ItemID"));
					item.setItemName(rs.getString("ItemName"));
					item.setItemType(rs.getString("ItemType"));
					item.setNumCopies(rs.getInt("NumCopies"));
					item.setDescription(rs.getString("Description"));
					item.setImgsrc(rs.getString("img"));
					itemInCategory.add(item);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return itemInCategory;
	}
	
	public ArrayList<AuctionDetailInfo> getDeadlineItems() {
		ArrayList<AuctionDetailInfo> deadlineItems = new ArrayList<AuctionDetailInfo>();
		AuctionDetailInfo auctionDetailInfo = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT * "
						+ "FROM Post P, Auction A, Item I "
						+ "WHERE NOW() < P.ExpireDate AND P.AuctionID = A.AuctionID AND A.ItemID = I.ItemID "
						+ "ORDER BY P.ExpireDate ASC "
						+ "LIMIT 4";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					auctionDetailInfo = new AuctionDetailInfo();
					auctionDetailInfo.setItemName(rs.getString("ItemName"));
					auctionDetailInfo.setItemType(rs.getString("ItemType"));
					auctionDetailInfo.setDescription(rs.getString("Description"));
					auctionDetailInfo.setImgSrc(rs.getString("img"));
					auctionDetailInfo.setAuctionID(rs.getInt("AuctionID"));
					auctionDetailInfo.setBidInc(rs.getFloat("BidIncrement"));
					auctionDetailInfo.setMinBid(rs.getFloat("MinimumBid"));
					auctionDetailInfo.setCopy(rs.getInt("Copies_Sold"));
					auctionDetailInfo.setSellerID(rs.getLong("CustomerID"));
					auctionDetailInfo.setEndDate(rs.getTimestamp("ExpireDate"));
					deadlineItems.add(auctionDetailInfo);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		}
		
		return deadlineItems;
	}
	
	public ArrayList<Item> getBestSellers() {
		ArrayList<Item> bestSellers = new ArrayList<Item>();
		Item item = null;
		ArrayList<Integer> bestSelledItem = new ArrayList<Integer>();
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			try {
				String sqlQuery1 = "SELECT A.ItemID "
						+ "FROM Sales S, Auction A "
						+ "WHERE A.AuctionID = S.AuctionID "
						+ " GROUP BY A.ItemID "
						+ " ORDER BY COUNT(*) DESC "
						+ " LIMIT 4";
				ps1 = conn.prepareStatement(sqlQuery1);
				rs1 = ps1.executeQuery();
				while(rs1.next()) {
					bestSelledItem.add(rs1.getInt("ItemID"));
				}
				ps1.close(); rs1.close();
				
				for(int i = 0; i < bestSelledItem.size(); i++) {
					String sqlQuery2 = "SELECT * FROM Item WHERE ItemID = ?";
					ps2 = conn.prepareStatement(sqlQuery2);
					ps2.setInt(1, bestSelledItem.get(i));
					rs2 = ps2.executeQuery();
					
					while(rs2.next()) {
						item = new Item();
						item.setItemID(rs2.getInt("ItemID"));
						item.setItemName(rs2.getString("ItemName"));
						item.setItemType(rs2.getString("ItemType"));
						item.setNumCopies(rs2.getInt("NumCopies"));
						item.setDescription(rs2.getString("Description"));
						item.setImgsrc(rs2.getString("img"));
						bestSellers.add(item);
					}
					ps2.close(); rs2.close();
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					rs1.close();
					ps2.close();
					rs2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return bestSellers;
	}
	
	public ArrayList<Employer> getEmployees() {
		
		ArrayList<Employer> employees = new ArrayList<Employer>();
		Employer employee = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT * FROM Person, Employee WHERE Person.SSN = Employee.EmployeeID";
				ps = conn.prepareStatement(sqlQuery);
				rs = ps.executeQuery();
				while(rs.next()) {
					employee = new Employer();
					employee.setSsn(rs.getLong("SSN"));
					employee.setLast(rs.getString("LastName"));
					employee.setFirst(rs.getString("FirstName"));
					employee.setAddress(rs.getString("Address"));
					employee.setZipcode(rs.getInt("ZipCode"));
					employee.setTelephone(rs.getLong("Telephone"));
					employee.setEmail(rs.getString("Email"));
					employee.setPw(rs.getString("pw"));
					employee.setLevel(rs.getInt("Level"));
					employee.setStartDate(rs.getTimestamp("StartDate"));
					employee.setHourlyRate(rs.getFloat("HourlyRate"));
					employees.add(employee);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return employees;
	}
	
	public ArrayList<Customer> getMostSellCustomers() {
		ArrayList<Customer> mostSellCustomers = new ArrayList<Customer>();
		ArrayList<Long> mostSellCustomerIds = new ArrayList<Long>();
		Customer customer = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			try {
				String sqlQuery1 = "SELECT S.SellerID "
						+ "FROM Sales S, Auction A "
						+ "WHERE A.AuctionID = S.AuctionID "
						+ " GROUP BY S.SellerID"
						+ " ORDER BY COUNT(*) DESC"
						+ " LIMIT 3";
				ps1 = conn.prepareStatement(sqlQuery1);
				rs1 = ps1.executeQuery();
				while(rs1.next()) {
					mostSellCustomerIds.add(rs1.getLong("SellerID"));
				}
				ps1.close(); rs1.close();
				
				for(int i = 0; i < mostSellCustomerIds.size(); i++) {
					String sqlQuery2 = "SELECT * FROM Person, Customer WHERE Customer.CustomerID = Person.SSN AND Person.SSN = ?";
					ps2 = conn.prepareStatement(sqlQuery2);
					ps2.setLong(1, mostSellCustomerIds.get(i));
					rs2 = ps2.executeQuery();
					
					while(rs2.next()) {
						customer = new Customer();
						customer.setSsn(rs2.getLong("SSN"));
						customer.setLast(rs2.getString("LastName"));
						customer.setFirst(rs2.getString("FirstName"));
						customer.setAddress(rs2.getString("Address"));
						customer.setZipcode(rs2.getInt("ZipCode"));
						customer.setTelephone(rs2.getLong("Telephone"));
						customer.setEmail(rs2.getString("Email"));
						customer.setPw(rs2.getString("pw"));
						customer.setRating(rs2.getFloat("Rating"));
						customer.setCreditCardNum(rs2.getLong("CreditCardNum"));
						mostSellCustomers.add(customer);
					}
					ps2.close(); rs2.close();
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					rs1.close();
					ps2.close();
					rs2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return mostSellCustomers;
	}
	
	public ArrayList<AuctionDetailInfo> getAuctionsBySellerID(long sellerId) {
		ArrayList<AuctionDetailInfo> auctionBySeller = new ArrayList<AuctionDetailInfo>();
		AuctionDetailInfo auction = null;
		Connection conn = getConnection();
		
		if(conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sqlQuery = "SELECT I.ItemName, I.ItemType, I.Description, I.img, "
							+ "A.AuctionID, A.BidIncrement, A.MinimumBid, A.Copies_Sold, P.CustomerID, P.ExpireDate, P.ReservedPrice "
							+ "FROM Item I, Auction A, Post P "
							+ "WHERE P.CustomerID = ? AND P.AuctionID = A.AuctionID AND A.ItemID = I.ItemID";
				ps = conn.prepareStatement(sqlQuery);
				ps.setLong(1, sellerId);
				rs = ps.executeQuery();
				while(rs.next()) {
					auction = new AuctionDetailInfo();
					auction.setItemName(rs.getString("ItemName"));
					auction.setItemType(rs.getString("ItemType"));
					auction.setDescription(rs.getString("Description"));
					auction.setImgSrc(rs.getString("img"));
					auction.setAuctionID(rs.getInt("AuctionID"));
					auction.setBidInc(rs.getFloat("BidIncrement"));
					auction.setMinBid(rs.getFloat("MinimumBid"));
					auction.setCopy(rs.getInt("Copies_Sold"));
					auction.setSellerID(rs.getLong("CustomerID"));
					auction.setEndDate(rs.getTimestamp("ExpireDate"));
					auction.setPrice(rs.getFloat("ReservedPrice"));
					auctionBySeller.add(auction);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				closeConnection(conn);
			}
		} 
		
		return auctionBySeller;
	}
	
}