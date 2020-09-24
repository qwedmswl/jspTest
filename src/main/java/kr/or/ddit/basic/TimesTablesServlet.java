package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet_gugudan
 */
@WebServlet("/TimesTablesServlet")
public class TimesTablesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimesTablesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		PrintWriter writer = resp.getWriter();
		writer.println("<table boder='1'>");
		
		for(int i = 1; i <= 9; i++) {
			writer.println("	<tr>");
		
			for (int j = 1; j <=9; j++) {
				writer.println("		<td>" + j + " * " + i + " = " + i*j + "</td>");
			}
			writer.println("	</tr>");
		
		writer.println(		"</script>");
		writer.println(		"<body>");
		writer.println(		"</body>");
		
		writer.println(		"");
		writer.println();
		writer.println();
		writer.println("</html>");
	}


}
}