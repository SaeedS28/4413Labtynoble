//Begin Lab 2

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;

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
	private Boolean flag1 = true ,flag2 = true;
	private Double principalOld=null;
	private Double periodOld=null;
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
		
//Output and parsing	
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
		
		//String foo = request.getParameter("foo");
		//resOut.write("Query Param foo= "+ foo + "\n");
	
		String url = this.getServletContext().getContextPath()+"/Start";
		resOut.write("Request URI: "+url+" \n");
		
		String servletPath = request.getServletPath();
		resOut.write("Request Servlet Path :"+servletPath+"\n");
//Calculations
		Double principalVal = 0.0;
		Double periodVal = 0.0;
		Double interestVal = 0.0;
		
		String principalText = request.getParameter("principal");
		String periodText = request.getParameter("period");
		String interestText = request.getParameter("interest");
		
		if(principalText != null)
		{
			principalVal = Double.parseDouble(principalText);
			principalOld = principalVal;
			flag1 = false;
		}
		else if(flag1)
		{
			principalVal = Double.parseDouble(this.getServletContext().getInitParameter("principal"));
			principalOld = principalVal;
		}
		else
		{
			principalVal = principalOld;
		}
		
		
		if(periodText != null)
		{
			periodVal = Double.parseDouble(periodText);
			periodOld = periodVal;
			flag2 = false;
		}
		else if(flag2)
		{
			periodVal = Double.parseDouble(this.getServletContext().getInitParameter("period"));
			periodOld = periodVal;
		}
		else
		{
			periodVal = periodOld;
		}
		
		
	
		if(interestText != null){
			interestVal=Double.parseDouble(interestText);
		}else{
			interestVal=Double.parseDouble(this.getServletContext().getInitParameter("interest"));
		}
		
		
		//Output		
		resOut.write("---- Monthly Payments ----\n");
		resOut.write("Based on Principal="+principalVal+" Period="+periodVal+" Interest="+interestVal+"\n");
		//Computation for payments
		interestVal = interestVal * 0.01;
		Double mpaymentsVal = ((interestVal)/12)*principalVal/(1-Math.pow(1+((interestVal)/12), (-1)*periodVal));;		
		DecimalFormat df = new DecimalFormat("#.####");
		df.setMaximumFractionDigits(2);
		resOut.write("Monthly Payments: $"+df.format(mpaymentsVal)+"\n");	
		//Double principal=Double.parseDouble(this.getServletContext().getInitParameter("principal"));
		if(request.getRequestURI().contains("Startup/YorkBank"))
		response.sendRedirect(url+"/Start");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
