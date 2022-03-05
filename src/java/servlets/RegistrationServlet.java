package servlets;

import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.UserFacade;


@WebServlet(name = "RegistrationServlet",loadOnStartup = 1, urlPatterns = {
    "/showSignUp",
    "/signUp",
})
public class RegistrationServlet extends HttpServlet {
    @EJB UserFacade userFacade; 

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         request.setCharacterEncoding("UTF-8");
         String path = request.getServletPath();
        switch (path) {
            case "/showSignUp":
                request.getRequestDispatcher("/signUp").forward(request, response);
                break;
            case "/signUp":
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String phone = request.getParameter("phone");
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                
                User user = new User();
                if(firstName == null){
                    request.setAttribute("info", "Одно или несколько полей не заполнены");
                    request.getRequestDispatcher("/signUp").forward(request, response);
                    break;
                }
                if(lastName == null){
                    request.setAttribute("info", "Одно или несколько полей не заполнены");
                    request.getRequestDispatcher("/signUp").forward(request, response);
                    break;
                }
                if(phone == null){
                    request.setAttribute("info", "Одно или несколько полей не заполнены");
                    request.getRequestDispatcher("/signUp").forward(request, response);
                    break;
                }
                if(login == null){
                    request.setAttribute("info", "Одно или несколько полей не заполнены");
                    request.getRequestDispatcher("/signUp").forward(request, response);
                    break;
                }
                if(password == null){
                    request.setAttribute("info", "Одно или несколько полей не заполнены");
                    request.getRequestDispatcher("/signUp").forward(request, response);
                    break;
                }

                try {
                    userFacade.create(user);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPhone(phone);
                    user.setLogin(login);
                    user.setPassword(password);
                } catch (Exception e) {
                    request.setAttribute("info", "Не удалось зарегистрироваться");
                }
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}