/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import entity.UserRole;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class UserRoleFacade extends AbstractFacade<UserRole> {

    @PersistenceContext(unitName = "WebShoeShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRoleFacade() {
        super(UserRole.class);
    }
    
    public boolean isRole (String roleName, User user) {
        List<String> userRoleNameList = em.createQuery("SELECT ur.roles.roleName FROM UserRole ur WHERE ur.user = :user")
                .setParameter("user", user)
                .getResultList();
        return userRoleNameList.contains(roleName);
    }
    
    public String getTheRole(User user) {
        List<String> listRoleNames = em.createQuery("SELECT ur.roles.roleName FROM UserRoles ur WHERE ur.user = :user")
                .setParameter("user", user)
                .getResultList();
        if(listRoleNames.contains("ADMINISTRATOR"))return "ADMINISTRATOR";
        if(listRoleNames.contains("MANAGER"))return "MANAGER";
        if(listRoleNames.contains("BUYER"))return "BUYER";
        return null;
    }
}
