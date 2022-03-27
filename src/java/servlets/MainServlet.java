package servlets;

import entity.History;
import entity.Model;
import entity.User;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import tools.PasswordProtected;

/**
 *
 * @author makso
 */
@WebServlet(name = "MainServlet",urlPatterns = {
    "/showAdminPanel",
    "/setRole",
    
    "/showGain",
    "/gain",
    
    "/showListModels",
        
    "/showAddModel",
    "/addModel",

    "/addUser",
    
    "/editModel",
    "/chooseModelToEdit",
    "/showEditModel",
    
    "/deleteModel",
    "/showDeleteModel",
    
    "/deleteUser",
    "/showDeleteUser",
    
    "/showEditUserInfo",
    "/editUserInfo",
    
    "/showEditMyInfo",
    "/editMyInfo",
    "/showEditMyLogin",
    "/editMyLogin",
    
    
    "/showBuyModel",
    "/buyModel",
    
})
public class MainServlet extends HttpServlet {
    @EJB ModelFacade modelFacade;
    @EJB UserFacade userFacade;
    @EJB HistoryFacade historyFacade;
    
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
        User authUser = (User) session.getAttribute("authUser");
        
        List<Model> modelsList = modelFacade.findAll();
        List<User> usersList = userFacade.findAll();
//        Long myselfInList = authUser.getId();
//        int intID = myselfInList.intValue();
        usersList.remove(0);
//        usersList.remove(intID);
        String path = request.getServletPath();
        switch (path) {
            case "/showListModels":
                request.setAttribute("models", modelsList);
                request.setAttribute("info", "Авторизуйтесь, чтобы купить модель");
                request.getRequestDispatcher("/WEB-INF/listModels.jsp").forward(request, response);
                break;
            case "/showAdminPanel":
                request.setAttribute("users", usersList);
                request.getRequestDispatcher("/WEB-INF/adminPanel.jsp").forward(request, response);
                break;
            case "/setRole":
                User chosenUser = userFacade.find(Long.parseLong(request.getParameter("сhooseUser")));
                String chosenRole = request.getParameter("chooseRole");

                try {
                    chosenUser.setRole(chosenRole);
                    userFacade.edit(chosenUser);         
                } catch (Exception e) {
                    request.setAttribute("info", "Не удалось изменить роль!");
                    request.getRequestDispatcher("/showAdminPanel").forward(request, response);
                }

                request.setAttribute("info", "Теперь " + chosenUser.getFirstName() + " имеет роль " + chosenRole);
                request.getRequestDispatcher("/showAdminPanel").forward(request, response);
                break;
            case "/showGain":
                request.getRequestDispatcher("/WEB-INF/gain.jsp").forward(request, response);
                break;
            case "/gain":
                
            case "/showBuyModel":
                request.setAttribute("users", usersList);
                request.setAttribute("models", modelsList);
                request.getRequestDispatcher("/WEB-INF/buyModel.jsp").forward(request, response);
                break;
            case "/buyModel":
                Model buyModel = modelFacade.find(Long.parseLong(request.getParameter("buyModels")));

                if(authUser.getMoney() >= buyModel.getPrice()) {
                    History history = new History();
                    history.setModel(buyModel);
                    history.setUser(authUser);
                    authUser.setMoney(authUser.getMoney() - buyModel.getPrice());
                    history.setBuy(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    history.setGain(history.getGain() + buyModel.getPrice());
                    userFacade.edit(authUser);
                    historyFacade.create(history);   
                    request.setAttribute("info", "Покупка успешно совершена!");
                }
                else {
                    request.setAttribute("info", "Недостаточно средств!");
                }
                request.getRequestDispatcher("showBuyModel").forward(request, response);
                break;
            case "/showAddModel":
                request.setAttribute("amount", 1);
                request.getRequestDispatcher("/WEB-INF/addModel.jsp").forward(request, response);
                break;
            case "/addModel":
                String modelName = request.getParameter("modelName");
                String modelSize = request.getParameter("modelSize");
                String modelFirm = request.getParameter("modelFirm");   
                
                if(modelName.isEmpty() || modelSize.isEmpty() || modelFirm.isEmpty()){
                    request.setAttribute("info", "Заполните все поля!");
                    request.setAttribute("modelName", modelName);
                    request.setAttribute("modelSize", modelSize);
                    request.setAttribute("modelFirm", modelFirm);
                    request.getRequestDispatcher("/showAddModel").forward(request, response);
                    break;
                }
                try{
                    Model addModel = new Model();
                    addModel.setModelName(modelName);
                    addModel.setModelSize(modelSize);
                    addModel.setModelFirm(modelFirm);
                    addModel.setPrice(Double.parseDouble(request.getParameter("price")));
                    addModel.setAmount(Integer.parseInt(request.getParameter("amount")));
                    modelFacade.create(addModel);
                    request.setAttribute("info", "Обувь добавлена!");
                    request.getRequestDispatcher("/showAddModel").forward(request, response);
                }
                catch(Exception e){
                    request.setAttribute("info", "Не удалось добавить модель!");
                    request.setAttribute("modelName", modelName);
                    request.setAttribute("modelSize", modelSize);
                    request.setAttribute("modelFirm", modelFirm);
                    request.setAttribute("money", null);
                    request.getRequestDispatcher("/showAddModel").forward(request, response);
                    break;
                }
                break;
            case "/addUser":
                request.getRequestDispatcher("showSignUp").forward(request, response);
                break;
            case "/showEditModel":    
                request.setAttribute("models", modelsList);
                request.getRequestDispatcher("/WEB-INF/editModel.jsp").forward(request, response);
                break;
//            case "/chooseModelToEdit":
//                String editModelName = editModel.getModelName();
//                request.setAttribute("editModelName", editModelName);
//                request.getRequestDispatcher("/showEditModel").forward(request, response);
            case "/editModel":      
                Model editModel = modelFacade.find(Long.parseLong(request.getParameter("theModels")));
                String editModelName = request.getParameter("editModelName");
                String editModelSize = request.getParameter("editModelSize");
                String editModelFirm = request.getParameter("editModelFirm");
                
                if(editModelName.isEmpty() || editModelSize.isEmpty() || editModelFirm.isEmpty()) {
                    request.setAttribute("info", "Заполните все поля!");
                    request.setAttribute("editModelName", editModelName);
                    request.setAttribute("editModelSize", editModelSize);
                    request.setAttribute("editModelFirm", editModelFirm);
                }
                
                try {
                    editModel.setModelName(editModelName);
                    editModel.setModelSize(editModelSize);
                    editModel.setModelFirm(editModelFirm);
                    editModel.setPrice(Double.parseDouble(request.getParameter("editPrice")));
                    modelFacade.edit(editModel);
                    request.setAttribute("info", "Данные успешно сохранены!");
                    request.getRequestDispatcher("/showEditModel").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("info", "Изменение не удалось!");
                }
                request.getRequestDispatcher("/showEditModel").forward(request, response);
                break;
            case "/showDeleteModel":
                request.setAttribute("models", modelsList);
                request.getRequestDispatcher("/WEB-INF/deleteModel.jsp").forward(request, response);
                break;
            case "/deleteModel":
                Model deleteModel = modelFacade.find(Long.parseLong(request.getParameter("TheModels")));
                modelName = deleteModel.getModelName();
                try {
                    List<History> histories = historyFacade.findAll();
                    
                    for (History history : histories) {
                        if(Objects.equals(deleteModel.getId(), history.getModel().getId())) {
                            for (History history1 : histories) {
                                historyFacade.remove(history1);
                            }
                            modelFacade.remove(deleteModel);                              
                        }
                    }
                    request.setAttribute("info", "Обувь " + modelName + " успешно удалена!");
                } catch (Exception e) {
                    request.setAttribute("info", "Не удалось удалить модель!");
                }
                request.getRequestDispatcher("/showDeleteModel").forward(request, response);
                break;
            case "/showDeleteUser":
                request.setAttribute("users", usersList);
                request.getRequestDispatcher("/WEB-INF/deleteUser.jsp").forward(request, response);
                break;
            case "/deleteUser":
                User deleteUser = userFacade.find(Long.parseLong(request.getParameter("TheUsers")));
                String userName = deleteUser.getFirstName();
                String userLastName = deleteUser.getLastName();
                
                try {
                    List<History> histories = historyFacade.findAll();
                    for (History history : histories) {
                        if (Objects.equals(deleteUser.getId(), history.getUser().getId())) {
                            for(History history1 : histories) {
                                historyFacade.remove(history1);
                            }
                            userFacade.remove(deleteUser);
                        }
                    }   
                } catch (Exception e) {
                    request.setAttribute("info", "Не удалось удалить пользователя!");
                }
                request.setAttribute("info", "Пользователь " + userName + " " + userLastName + " успешно удален!");
                request.getRequestDispatcher("showDeleteUser").forward(request, response);
                break;
            case "/showEditUserInfo":
                request.setAttribute("users", usersList);
                request.getRequestDispatcher("/WEB-INF/editUserInfo.jsp").forward(request, response);
                break;
            case "/editUserInfo":
                User editUser = userFacade.find(Long.parseLong(request.getParameter("theUsers")));           
                
                String editUserFirstName = request.getParameter("editUserFirstName");
                String editUserLastName = request.getParameter("editUserLastName");
                String editUserPhone = request.getParameter("editUserPhone");
                
                if(editUserFirstName.isEmpty() || editUserLastName.isEmpty() || editUserPhone.isEmpty()) {
                    request.setAttribute("info", "Заполните все поля!");
                    request.setAttribute("editUserFirstName", editUserFirstName);
                    request.setAttribute("editUserLastName", editUserLastName);
                    request.setAttribute("editUserPhone", editUserPhone);
                }
                
                try {
                    editUser.setFirstName(editUserFirstName);
                    editUser.setLastName(editUserLastName);
                    editUser.setPhone(editUserPhone);
                    editUser.setMoney(Double.parseDouble(request.getParameter("editUserMoney")));
                    userFacade.edit(editUser);
                    request.setAttribute("info", "Данные успешно сохранены!");
                    request.getRequestDispatcher("/showEditUserInfo").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("info", "Изменение не удалось!");
                }
                request.getRequestDispatcher("/showEditUserInfo").forward(request, response);
                break;
            case "/showEditMyLogin":
                request.getRequestDispatcher("/WEB-INF/editMyLogin.jsp").forward(request, response);
                break;
            case "/editMyLogin":
                String editLogin = request.getParameter("editLogin");
                String editPassword1 = request.getParameter("editPassword1");
                String editPassword2 = request.getParameter("editPassword2");             
      
                if(editLogin.isEmpty() || editPassword1.isEmpty() || editPassword2.isEmpty()) {
                    request.setAttribute("info", "Заполните все поля!");
                    request.setAttribute("editLogin", editLogin);
                    request.setAttribute("editPassword1", editPassword1);
                    request.setAttribute("editPassword2", editPassword2);
                    request.getRequestDispatcher("/showEditMyLogin").forward(request, response);
                }
                else {
                    request.setAttribute("info", "Выберите пользователя!");
                }

                if(editPassword1.equals(editPassword2)) {
                    authUser.setLogin(editLogin); 
                    PasswordProtected passwordProtected = new PasswordProtected();
                    String salt = passwordProtected.getSalt();
                    authUser.setSalt(salt);
                    String protectedEditUserPassword = passwordProtected.getProtectedPassword(editPassword1, salt);
                    authUser.setPassword(protectedEditUserPassword);
                    userFacade.edit(authUser);
                    request.setAttribute("info", "Данные успешно изменены!");
                    request.getRequestDispatcher("/showEditMyLogin").forward(request, response);
                }
                else {
                    request.setAttribute("info", "Пароли не совпадают");
                }
                request.getRequestDispatcher("/showEditMyLogin").forward(request, response);
                break;
            case "/showEditMyInfo":
                request.setAttribute("users", usersList);
                request.getRequestDispatcher("/WEB-INF/editMyInfo.jsp").forward(request, response);
                break;
            case "/editMyInfo":
                if (request.getParameter("editFirstName").isEmpty() || 
                        request.getParameter("editLastName").isEmpty() || 
                        request.getParameter("editPhone").isEmpty() ||
                        request.getParameter("editMoney").isEmpty()){
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("showEditMyInfo.jsp").forward(request, response);
                    break;
                }
                try {
                    authUser.setFirstName(request.getParameter("editFirstName"));
                    authUser.setLastName(request.getParameter("editLastName"));
                    authUser.setPhone(request.getParameter("editPhone"));
                    authUser.setMoney(Double.parseDouble(request.getParameter("editMoney")));
                    userFacade.edit(authUser);
                    request.setAttribute("info", "Данные успешно сохранены!");
                    break;
                } catch (Exception e) {
                    request.setAttribute("info", "Не удалось изменить данные!");
                }
                request.getRequestDispatcher("/showEditMyInfo").forward(request, response);
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