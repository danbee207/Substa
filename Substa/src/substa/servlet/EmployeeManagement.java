package substa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import substa.beans.Employer;
import substa.beans.User;
import substa.model.DBManagers;

/**
 * Servlet implementation class EmployeeManagement
 */

@WebServlet(urlPatterns = { "/EmployeeManagement" }, initParams = {
		@WebInitParam(name = "jdbcDriver", value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://mysql2.cs.stonybrook.edu/danpark"),
		@WebInitParam(name = "dbUser", value = "danpark"), @WebInitParam(name = "dbPass", value = "110142214") })
public class EmployeeManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManagers db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();

		db = new DBManagers();
		db.setDbURL(config.getInitParameter("dbUrl"));
		db.setDbuser(config.getInitParameter("dbUser"));
		db.setDbpass(config.getInitParameter("dbPass"));

		try {
			Class.forName(config.getInitParameter("jdbcDriver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRuqest(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRuqest(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void processRuqest(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);

		response.setHeader("Cache-Control", "no-cache");

		response.setHeader("Pragma", "no-cache"); // no cache for HTTP 1.0
		response.setDateHeader("Expires", 0); // always expires
		
		int type = Integer.parseInt(request.getParameter("ShowDetail"));
		
		User user = new User();
		user.setSsn(Long.parseLong(request.getParameter("ssn")));
		user.setFirst(request.getParameter("FirstName"));
		user.setLast(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setPw(request.getParameter("password"));
		
		String address = request.getParameter("street")+","+request.getParameter("city")+","+request.getParameter("state");
		user.setAddress(address);
		
		user.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
		
		if(type ==-1){ //add
			
			
			db.addUser(user);
			int level = Integer.parseInt(request.getParameter("level"));
			float money = Float.parseFloat(request.getParameter("hourR"));
			
			String preStart = request.getParameter("startDate");
			SimpleDateFormat bfomratter = new SimpleDateFormat("yyyy-m-d h:m");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			Date d = bfomratter.parse(preStart);
			
			String endDateFixed = formatter.format(d);
			System.out.println(endDateFixed);
			Timestamp start = (Timestamp.valueOf(endDateFixed));
			
			db.addEmployer(user, level, start, money);
			
			
		}else if(type==-2){  //delete
		
			db.deleteEmployer(Long.parseLong(request.getParameter("ssn")));
		
		}else{				//edit
			Employer employee = new Employer(user);
			employee.setLevel(Integer.parseInt(request.getParameter("level")));
			employee.setHourlyRate(Float.parseFloat(request.getParameter("hourR")));
			employee.setStartDate(Timestamp.valueOf(request.getParameter("startDate")));
			
			
			db.changeEmployer(employee);
		}

		session.setAttribute("employerList", db.getEmployees());
		gotoEmployee(response);
	}
	protected void gotoEmployee(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		out.println("<script type = 'text/javascript'>");
		out.println("location.href='EmployeeManagement.jsp';");
		out.println("</script>");
	}

}
