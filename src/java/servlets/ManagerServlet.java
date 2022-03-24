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
import session.RolesFacade;
import session.UserFacade;
import tools.PasswordProtected;

/**
 *
 * @author makso
 */
@WebServlet(name = "ManagerServlet",urlPatterns = {
    "/showAddModel",
    "/addModel",

    "/addUser",
    
    "/editModel",
    "/showEditModel",
    
    "/deleteModel",
    "/showDeleteModel",
    
    "/deleteUser",
    "/showDeleteUser",
    
    "/showEditUserInfo",
    "/editUserInfo",
    "/showEditUserLogin",
    "/editUserLogin",
    
    "/showBuyModel",
    "/buyModel",
    
})
public class ManagerServlet extends HttpServlet {
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
        //Authentification
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "Авторизуйтесь");
            request.getRequestDispatcher("/showIndex").forward(request, response);
            return;
        }
        List<Model> modelsList = modelFacade.findAll();
        List<User> usersList = userFacade.findAll();
        
//        if(!userRoleFacade.isRole("MANAGER", authUser)) {
//            request.setAttribute("info", "У вас недостаточно прав!");
//            request.getRequestDispatcher("/showIndex").forward(request, response);
//        }
        String path = request.getServletPath();
//        session.setAttribute("currentRole", userRoleFacade.getTheRole(authUser));
        switch (path) {
            case "/showAddModel":
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
            case "/showEditUserLogin":
                request.setAttribute("users", usersList);
                request.getRequestDispatcher("/WEB-INF/editUserLogin.jsp").forward(request, response);
                break;
            case "/editUserLogin":
                String editUserLog = request.getParameter("editUserLog");
                String editUserPassword1 = request.getParameter("editUserPassword1");
                String editUserPassword2 = request.getParameter("editUserPassword2");
                User chooseEditUserSignIn = userFacade.find(Long.parseLong(request.getParameter("theEditLogin")));                  
      
                if(editUserLog.isEmpty() || editUserPassword1.isEmpty() || editUserPassword2.isEmpty()) {
                    request.setAttribute("info", "Заполните все поля!");
                    request.setAttribute("editUserLog", editUserLog);
                    request.setAttribute("editUserPassword1", editUserPassword1);
                    request.setAttribute("editUserPassword2", editUserPassword2);
                    request.getRequestDispatcher("showEditUserLogin").forward(request, response);
                }
                else {
                    request.setAttribute("info", "Выберите пользователя!");
                }

                if(editUserPassword1.equals(editUserPassword2)) {
                    chooseEditUserSignIn.setLogin(editUserLog); 
                    PasswordProtected passwordProtected = new PasswordProtected();
                    String salt = passwordProtected.getSalt();
                    chooseEditUserSignIn.setSalt(salt);
                    String protectedEditUserPassword = passwordProtected.getProtectedPassword(editUserPassword1, salt);
                    chooseEditUserSignIn.setPassword(protectedEditUserPassword);
                    userFacade.edit(chooseEditUserSignIn);
                    request.setAttribute("info", "Данные успешно изменены!");
                    request.getRequestDispatcher("showEditUserLogin").forward(request, response);
                }
                else {
                    request.setAttribute("info", "Пароли не совпадают");
                }
                request.getRequestDispatcher("showEditUserLogin").forward(request, response);
                break;
            case "/addUser":
                request.getRequestDispatcher("showSignUp").forward(request, response);
                break;
            case "/showEditModel":
                request.setAttribute("models", modelsList);
                request.getRequestDispatcher("/WEB-INF/editModel.jsp").forward(request, response);
                break;
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
//            case "/showDeleteUser":
//                request.setAttribute("users", usersList);
//                request.getRequestDispatcher("/WEB-INF/deleteUser.jsp").forward(request, response);
//                break;
//            case "/deleteUser":
//                User deleteUser = userFacade.find(Long.parseLong(request.getParameter("TheUsers")));
//                String userName = deleteUser.getFirstName();
//                String userLastName = deleteUser.getLastName();
//                
//                try {
//                    List<History> histories = historyFacade.findAll();
//                    for (History history : histories) {
//                        if (Objects.equals(deleteUser.getId(), history.getUser().getId())) {
//                            for(History history1 : histories) {
//                                historyFacade.remove(history1);
//                            }
//                            userFacade.remove(deleteUser);
//                        }
//                    }   
//                } catch (Exception e) {
//                    request.setAttribute("info", "Не удалось удалить пользователя!");
//                }
//                request.setAttribute("info", "Пользователь " + userName + " " + userLastName + " успешно удален!");
//                request.getRequestDispatcher("showDeleteUser").forward(request, response);
//                break;
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
            case "/showBuyModel":
                request.setAttribute("users", usersList);
                request.setAttribute("models", modelsList);
                request.getRequestDispatcher("/WEB-INF/buyModel.jsp").forward(request, response);
                break;
            case "/buyModel":
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