

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ITReturns
 */
public class ITReturns extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ITReturns() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String salary=request.getParameter("salary");
		String tax=request.getParameter("tax");
		PrintWriter out=response.getWriter();
		out.println("<h1> Hi,"+name);
		out.println("<h1> UR SALARY=,"+salary);
		out.println("<h1> Sorry tax deducted=,"+tax);
		File fd=new File("/Users/abhishek.s.deshatty/eclipse-workspace/Q11_Servle/FirstFile.txt");
		fd.createNewFile();
		FileOutputStream fout= new FileOutputStream(fd);
		fout.write(("hello"+name+salary+tax).getBytes());
		fout.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
