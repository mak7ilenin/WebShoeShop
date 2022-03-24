/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.History;
import entity.Model;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.HistoryFacade;
import session.ModelFacade;
import session.UserFacade;
import session.UserRoleFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "BuyerServlet", urlPatterns = {
    "/showTheBuyModel",
    "/buyTheModel",
    
    "/showEditMyInfo",
    "/editMyInfo",
    "/showEditMyLogin",
    "/editMyLogin",
})
public class BuyerServlet extends HttpServlet {
    @EJB ModelFacade modelFacade;
    @EJB UserFacade userFacade;
    @EJB HistoryFacade historyFacade;
    @EJB UserRoleFacade userRoleFacade;
    
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();

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
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Авторизуйтесь");
            request.getRequestDispatcher("/showIndex").forward(request, response);
            return;
        }
        //Authentification
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "Авторизуйтесь");
            request.getRequestDispatcher("/showIndex").forward(request, response);
            return;
        }
        List<Model> modelsList = modelFacade.findAll();
        List<User> usersList = userFacade.findAll();
        
        if(!userRoleFacade.isRole("BUYER", authUser)) {
            request.setAttribute("info", "У вас недостаточно прав!");
            request.getRequestDispatcher("/showIndex").forward(request, response);
        }
        String path = request.getServletPath();
//        session.setAttribute("currentRole", session.getAttribute("currentRole"));
        session.setAttribute("currentRole", userRoleFacade.getTheRole(authUser));
        switch (path) {
            case "/showTheBuyModel":
                request.getRequestDispatcher("/WEB-INF/buyModel.jsp").forward(request, response);
                break;
            case "/buyTheModel":
                Model buyModel = modelFacade.find(Long.parseLong(request.getParameter("buyModels")));
                User buyUser = userFacade.find(Long.parseLong(request.getParameter("buyUsers")));
                    
                if(buyUser.getMoney() >= buyModel.getPrice()) {
                    History history = new History();
                    history.setModel(buyModel);
                    history.setUser(buyUser);
                    buyUser.setMoney(buyUser.getMoney() - buyModel.getPrice());
                    history.setBuy(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    history.setGain(history.getGain() + buyModel.getPrice());
                    userFacade.edit(buyUser);
                    historyFacade.create(history);   
                    request.setAttribute("info", "Покупка успешно совершена!");
                }
                else {
                    request.setAttribute("info", "Недостаточно средств!");
                }
                request.getRequestDispatcher("showBuyModel").forward(request, response);
                break;
            case "/showEditMyInfo":
                request.setAttribute("activeShowEditUserInfo", true);
                request.getRequestDispatcher("/WEB-INF/editUserInfo.jsp").forward(request, response);
                break;
            case "/editMyInfo":
                authUser.setFirstName(request.getParameter("editFirstName"));
                authUser.setLastName(request.getParameter("editLastName"));
                authUser.setPhone(request.getParameter("editPhone"));
                authUser.setMoney(Double.parseDouble(request.getParameter("editMoney")));
                userFacade.create(authUser);  
        }}


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
