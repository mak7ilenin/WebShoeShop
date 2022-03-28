package servlets;

import entity.History;
import java.io.IOException;
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

/**
 *
 * @author makso
 */
@WebServlet(name = "GainServlet",urlPatterns = {
    "/showGain",
    "/gainForJanuary",
    "/gainForFebruary",
    "/gainForMarch",
    "/gainForApril",
    "/gainForMay",
    "/gainForJune",
    "/gainForJuly",
    "/gainForAugust",
    "/gainForSeptember",
    "/gainForOctober",
    "/gainForNovember",
    "/gainForDecember",
})
public class GainServlet extends HttpServlet {
    @EJB HistoryFacade historyFacade;
    
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

        String path = request.getServletPath();
        List<History> historiesList = historyFacade.findAll();
        switch (path) {
            case "/showGain":
                Double sumGain = 0.0;
                for (History history : historiesList) {
                    sumGain = sumGain + history.getGain();
                }
                request.setAttribute("allGain", "Прибыль за всё время работы магазаниа: " + sumGain + "$");
                request.getRequestDispatcher("/WEB-INF/gain.jsp").forward(request, response);
                break;
            }
        double gainForAMonth = 0.0;
        for (History history : historiesList) {
            switch (path) {
                case "/gainForJanuary":
                    if (history.getBuy().getMonth() == 0) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForFebruary":
                    if (history.getBuy().getMonth() == 1) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForMarch":
                    if (history.getBuy().getMonth() == 2) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForApril":
                    if (history.getBuy().getMonth() == 3) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForMay":
                    if (history.getBuy().getMonth() == 4) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForJune":
                    if (history.getBuy().getMonth() == 5) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForJuly":
                    if (history.getBuy().getMonth() == 6) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForAugust":
                    if (history.getBuy().getMonth() == 7) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForSeptember":
                    if (history.getBuy().getMonth() == 8) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForOctober":
                    if (history.getBuy().getMonth() == 9) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForNovember":
                    if (history.getBuy().getMonth() == 10) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
                case "/gainForDecember":
                    if (history.getBuy().getMonth() == 11) {
                        gainForAMonth = gainForAMonth + history.getGain();
                    }
            }
        }

        request.setAttribute("gainForAMonth", gainForAMonth + "$");
        request.getRequestDispatcher("/showGain").forward(request, response); 
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