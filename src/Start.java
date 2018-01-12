

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns={"/Start","/Startup","/Startup/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at 4413 at this location: ").append(request.getContextPath());
		response.setContentType("text/plain");
		Writer resOut = response.getWriter();
		resOut.write("Hello World!\n");
	
		String clientIP = request.getRemoteAddr();
		resOut.write("Client IP:"+ clientIP+"\n");
		
		int clientPort = request.getRemotePort();
		resOut.write("Client Port: "+clientPort+"\n");
		
		
		boolean flagged = true;
		if(flagged)
			resOut.write("This IP has been flagged! \n");
		else
			resOut.write("This Ip has not been flagged! \n");
		
		
		String clientProtocol = request.getProtocol();
		resOut.write("Client Protocal: "+clientProtocol+"\n");
		
		String action = request.getMethod();
		resOut.write("Client Method "+action+". \n");
		
		String queryString = request.getQueryString();
		resOut.write("Query String: "+queryString+"\n");
		
		String foo = request.getParameter("foo");
		resOut.write("Query Param foo= "+ foo + "\n");
	
		String url = this.getServletContext().getContextPath()+"/Start";
		resOut.write("Request URI: "+url+" \n");
		
		String servletPath = request.getServletPath();
		resOut.write("Request Servlet Path :"+servletPath+"\n");

		Double principal = 0.0;
		Double period = 0.0;
		Double interest = 0.0;
		Double mpayments = (interest/12)*principal/(Math.pow(period,1-(1+(interest/12))));
		resOut.write("---- Monthly Payments ----\n");
		resOut.write("Based on Principal="+principal+" Period="+period+" Interest="+interest+"\n");
		resOut.write("Monthly Payments: "+mpayments);
		//Double principal=Double.parseDouble(this.getServletContext().getInitParameter("principal"));
		if(request.getRequestURI().contains("YorkBank"))
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
