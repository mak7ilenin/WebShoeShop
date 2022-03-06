/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.ModelBox;
import entity.Model;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ModelBoxFacade;
import session.ModelFacade;
import session.UserFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "MyServlet",urlPatterns = {
    "/addModelBox",
    "/createModelBox",
    "/listModels",
    "/showModel",
    "/removeModel",
        
})
public class MyServlet extends HttpServlet {
    @EJB ModelBoxFacade modelBoxFacade;
    @EJB ModelFacade modelFacade;
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
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Авторизуйтесь");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        //Authentification
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "Авторизуйтесь");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/addModelBox":
                List<Model> models = modelFacade.findAll();
                request.setAttribute("models", models);
                request.getRequestDispatcher("/WEB-INF/addModelBox.jsp").forward(request, response);
                break;
            case "/createModelBox":
                String modelName = request.getParameter("modelName");
                String modelSize = request.getParameter("modelSize");
                String model = request.getParameter("model");
                String priceStr = request.getParameter("price");
                    double price = 0;
                    if (priceStr != null && priceStr.length() > 0) {
                        price = Double.parseDouble(priceStr);
                    }
                String modelFirm = request.getParameter("modelFirm");
                String url = request.getParameter("url");
                if(modelName.isEmpty() || modelSize.isEmpty() || modelFirm.isEmpty() || url.isEmpty()){
                    request.setAttribute("info", "Заполните все поля");
                    request.setAttribute("modelName", modelName);
                    request.setAttribute("modelSize", modelSize);
                    request.setAttribute("price", price);
                    request.setAttribute("modelFirm", modelFirm);
                    request.setAttribute("url", url);
                    request.getRequestDispatcher("/addModelBox").forward(request, response);
                    break;
                }
                Model mod = null;
                try {
                    mod = modelFacade.find(Long.parseLong(model));
                    ModelBox modelBox = new ModelBox();
                    modelBox.setModelName(modelName);
                    modelBox.setModelSize(modelSize);
                    modelBox.setModel(mod);
                    modelBox.setPrice(price);
                    modelBox.setModelFirm(modelFirm);
                    modelBox.setUrl(url);
                    modelBoxFacade.create(modelBox);
                    authUser = userFacade.find(authUser.getId());
                    authUser.getListModelBox().add(modelBox);
                    userFacade.edit(authUser);
                    session.setAttribute("authUser", authUser);
                    request.setAttribute("info", "Данные записаны успешно");
                    request.getRequestDispatcher("/addModelBox").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("info", "Заполните все поля");
                    request.setAttribute("modelName", modelName);
                    request.setAttribute("modelSize", modelSize);
                    request.setAttribute("modelFirm", modelFirm);
                    request.setAttribute("price", price);
                    request.setAttribute("url", url);
                    request.getRequestDispatcher("/addModelBox").forward(request, response);
                    break;
                }
                break;
            case "/listModels":
                authUser=(User) session.getAttribute("authUser");
                request.setAttribute("listModels", authUser.getListModelBox());
                request.getRequestDispatcher("/WEB-INF/listModels.jsp").forward(request, response);
                break;
            case "/showModel":
                String modelId = request.getParameter("modelId");
                if(modelId != null && modelId.isEmpty()){
                    request.setAttribute("info", "Неверный запрос");
                    request.getRequestDispatcher("/listModels").forward(request, response);
                    break;
                }
                try {
                    ModelBox ab = modelBoxFacade.find(Long.parseLong(modelId));
                    request.setAttribute("modelBox", ab);
                } catch (Exception e) {
                    request.setAttribute("info", "Неверный запрос");
                    request.getRequestDispatcher("/listModels").forward(request, response);
                    break;
                }
                request.getRequestDispatcher("/WEB-INF/showModel.jsp").forward(request, response);
                break;
            case "/removeModel":
                String id = request.getParameter("id");
                try {
                    for(ModelBox modelBox : authUser.getListModelBox()){
                        if(modelBox.getId().equals(Long.parseLong(id))){
                            authUser.getListModelBox().remove(modelBox);
                            userFacade.edit(authUser);
                            modelBoxFacade.remove(modelBox);
                            session.setAttribute("authUser", authUser);
                            File file = new File(modelBox.getModel().getPathToFile());
                            file.delete();
                            request.setAttribute("info", "Удален аккаунт: "+modelBox.getName());
                            break;
                        }
                    }
                    
                } catch (Exception e) {
                    request.setAttribute("info", "Удаление не удалось");
                }
                request.getRequestDispatcher("/listModels").forward(request, response);
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