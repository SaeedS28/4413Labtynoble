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
@WebServlet(urlPatterns={"/Start","/Startup","/Startup/*","/Start/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Boolean flag1 = true ,flag2 = true;
	private Double principalOld=null;
	private Double periodOld=null;
	private static final String PRINCIPAL = "principal";
	private static final String INTEREST = "interest";
//	private static final String PERIOD = "period";
	
	String startPage="/UI.jspx";
	String resultPage="/Result.jspx";
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
		Writer resOut = response.getWriter();
		Double principalVal = 0.0;
		Double periodVal = 0.0;
		Double interestVal = 0.0;
		Double graceInterestVal=0.0;
		Double gracePeriod=0.0;
		Double mpaymentsVal= 0.0;
		Double totalPrincipalVal=0.0;
		Double totalInterestVal = 0.0;
		String principalText = request.getParameter("principal");
		String periodText = request.getParameter("period");
		String interestText = request.getParameter("interest");
	
	
// if there is nothing in the parameters
	if (request.getParameter("Submit")==null) 
				   request.getRequestDispatcher(startPage).forward(request,response);
	else {
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
		
			if (interestText != null) {
				interestVal = Double.parseDouble(interestText);
			} else {
				interestVal = Double.parseDouble(this.getServletContext().getInitParameter("interest"));
			}
//This needs to be calculated anyways^
			
//now if grace is checked we do this
			if(request.getParameter("grace")!=null)
			{
				gracePeriod = Double.parseDouble(this.getServletContext().getInitParameter("gracePeriod"));
				totalInterestVal = interestVal + Double.parseDouble(this.getServletContext().getInitParameter("fixedInterest"));
				mpaymentsVal = ((0.01 * totalInterestVal) / 12) * principalVal	/ (1 - Math.pow(1 + ((0.01 * totalInterestVal) / 12), (-1) * periodVal)); 
			    graceInterestVal =principalVal+((totalInterestVal)/12)*gracePeriod; //one of these is 0		
				System.out.println(graceInterestVal+"yoo its grace interest");
				totalPrincipalVal= mpaymentsVal+(graceInterestVal/gracePeriod);
				totalInterestVal = graceInterestVal;
			}
			else if(request.getParameter("grace")==null)
			{
				System.out.println("so graceperiod is always null");
				totalInterestVal = interestVal	+ Double.parseDouble(this.getServletContext().getInitParameter("fixedInterest"));
				totalPrincipalVal = ((0.01 * totalInterestVal) / 12) * principalVal	/ (1 - Math.pow(1 + ((0.01 * totalInterestVal) / 12), (-1) * periodVal));
			}
			
			
			
			//Output		
			DecimalFormat df = new DecimalFormat("#.####");
			df.setMaximumFractionDigits(2);
			System.out.println("total interest"+ totalInterestVal + "Principal total is "+ totalPrincipalVal + "Interest value"+ interestVal);

			request.setAttribute(PRINCIPAL,df.format(totalPrincipalVal));
			request.setAttribute(INTEREST,df.format(totalInterestVal));
			request.getRequestDispatcher(resultPage).forward(request,response);
	}
	
			
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
