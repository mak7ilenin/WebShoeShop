package servlets;

import entity.Roles;
import entity.User;
import entity.UserRole;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.RolesFacade;
import session.UserFacade;
import session.UserRoleFacade;
import tools.PasswordProtected;

/**
 *
 * @author makso
 */
@WebServlet(name = "LoginServlet",loadOnStartup = 1, urlPatterns = {
    "/showIndex",
    "/index",
    "/logout",
    "/showSignUp",
    "/signUp",
})
public class LoginServlet extends HttpServlet {
    @EJB UserFacade userFacade;
    @EJB RolesFacade roleFacade;
    @EJB UserRoleFacade userRoleFacade;
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        if(userFacade.count()>0) return;    
        User user = new User();
        user.setFirstName("Maksim");
        user.setLastName("Dzjubenko");
        user.setPhone("53334005");
        user.setMoney(122.2);
        user.setLogin("admin");
        PasswordProtected passwordProtected = new PasswordProtected();
        String salt = passwordProtected.getSalt();
        user.setSalt(salt);
        String adminPassword = passwordProtected.getProtectedPassword("12345", salt);
        user.setPassword(adminPassword);
        userFacade.create(user);
        Roles role = new Roles();
        role.setRoleName("BUYER");
        roleFacade.create(role);
        UserRole userRole = new UserRole();
        userRole.setRoles(role);
        userRole.setUser(user);
        userRoleFacade.create(userRole);
    }
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
            case "/showIndex":
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/index":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User authUser = userFacade.findByLogin(login);
                //Authentification
                if(authUser == null){
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.getRequestDispatcher("/showIndex").forward(request, response);
                    break;
                }
                //Authorization
                String salt = authUser.getSalt();
                PasswordProtected passwordProtected = new PasswordProtected();
                password = passwordProtected.getProtectedPassword(password, salt);
                if(!password.equals(authUser.getPassword())){
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.getRequestDispatcher("/showIndex").forward(request, response);
                    break;
                }

                HttpSession session = request.getSession(true);
                session.setAttribute("authUser", authUser);
                request.setAttribute("info", "Приветствуем вас , "+authUser.getFirstName());
                request.getRequestDispatcher("/showIndex").forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                    request.setAttribute("info", "Вы вышли");
                }
                request.getRequestDispatcher("/showIndex").forward(request, response);
                break;
            case "/showSignUp":
                request.getRequestDispatcher("/WEB-INF/showSignUp.jsp").forward(request, response);
                break;
            case "/signUp":
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String phone = request.getParameter("phone");
                String moneyStr = request.getParameter("money");
                double money = 0;
                if (moneyStr != null && moneyStr.length() > 0) {
                    money = Double.parseDouble(moneyStr);
                }
                login = request.getParameter("login");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if(!password1.equals(password2)){
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                    request.setAttribute("phone", phone);
                    request.setAttribute("money", money);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Пароли не совпадают");
                    request.getRequestDispatcher("/showSignUp").forward(request, response);
                    break;
                }
                if(firstName.isEmpty()
                        || lastName.isEmpty()
                        || phone.isEmpty()
                        || money <= 1
                        || login.isEmpty()
                        || password1.isEmpty()
                        || password2.isEmpty()
                        ){
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                    request.setAttribute("phone", phone);
                    request.setAttribute("money", money);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Одно или несколько полей не заполнены!");
                    request.getRequestDispatcher("/showSignUp").forward(request, response);
                    break;
                }
                User newUser = new User();
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setPhone(phone);
                newUser.setMoney(money);
                newUser.setLogin(login);
                passwordProtected = new PasswordProtected();
                salt = passwordProtected.getSalt();
                newUser.setSalt(salt);
                password1 = passwordProtected.getProtectedPassword(password1, salt);
                newUser.setPassword(password1);
                userFacade.create(newUser);
                request.setAttribute("info", "Приветсвуем вас, "+newUser.getFirstName()+"! Авторизуйтесь");
                request.getRequestDispatcher("/showIndex").forward(request, response);
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