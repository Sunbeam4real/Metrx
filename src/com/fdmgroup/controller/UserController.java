package com.fdmgroup.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.UserDAOI;
import com.fdmgroup.daoJpa.TargetDAOImpl;
import com.fdmgroup.daoJpa.UserDAOImpl;
import com.fdmgroup.model.ActivityType;
import com.fdmgroup.model.Privilege;
import com.fdmgroup.model.T_User;
import com.fdmgroup.model.Target;
import com.fdmgroup.tool.InputChecking;
import com.fdmgroup.tool.PasswordUtils;

@Controller

public class UserController {

       @RequestMapping("/goToEditProfileOp")
       public ModelAndView goToEdiProfileOp(HttpServletRequest req, HttpServletResponse resp) {
              String userid = req.getParameter("userId");

              long userid2 = Long.valueOf(userid);

              UserDAOI udao = new UserDAOImpl();
              T_User user = udao.readById(userid2);
              ModelAndView mv = new ModelAndView();
              mv.addObject("user", user);
              mv.setViewName("manageUser-operator.jsp");

              Map<Privilege, Integer> privileges = user.getPrivileges();
              Integer ViewDoc = privileges.get(Privilege.valueOf("ViewDoc"));
              mv.addObject("ViewDoc", ViewDoc);
              Integer ExportDoc = privileges.get(Privilege.valueOf("ExportDoc"));
              mv.addObject("ExportDoc", ExportDoc);
              Integer UploadDoc = privileges.get(Privilege.valueOf("UploadDoc"));
              mv.addObject("UploadDoc", UploadDoc);

              Integer ViewSelf = privileges.get(Privilege.valueOf("ViewSelf"));
              mv.addObject("ViewSelf", ViewSelf);
              Integer ViewProfiles = privileges.get(Privilege.valueOf("ViewProfiles"));
              mv.addObject("ViewProfiles", ViewProfiles);

              Integer CreateUser = privileges.get(Privilege.valueOf("CreateUser"));
              mv.addObject("CreateUser", CreateUser);

              Integer EditUser = privileges.get(Privilege.valueOf("EditUser"));
              mv.addObject("EditUser", EditUser);

              Integer EditUserAdmin = privileges.get(Privilege.valueOf("EditUserAdmin"));
              mv.addObject("EditUserAdmin", EditUserAdmin);

              Integer ViewAllActivities = privileges.get(Privilege.valueOf("ViewAllActivities"));
              mv.addObject("ViewAllActivities", ViewAllActivities);

              Integer createBusinessCase = privileges.get(Privilege.valueOf("CreateBusinessCase"));
              mv.addObject("createBusinessCase", createBusinessCase);

              return mv;

       }

       @RequestMapping("/listAllUser")
       public ModelAndView listAllUser(HttpServletRequest req) {
              UserDAOI udao = new UserDAOImpl();
              List<T_User> users = udao.readAll();
              ModelAndView mv = new ModelAndView();
              //fixed: delete own account
              users.remove(req.getSession().getAttribute("loggedinuser"));
              //end
              mv.setViewName("manageUser-all.jsp");
              mv.addObject("users", users);
              return mv;
       }

       @RequestMapping("/deleteUser")
       public ModelAndView deleteUser(HttpServletRequest req, HttpServletResponse resp) {
              String userid = req.getParameter("deleteUser");

              long userid2 = Long.valueOf(userid);
              UserDAOI udao = new UserDAOImpl();
              T_User user = udao.readById(userid2);
              user.setVisible(false);
              udao.update(user);
              List<T_User> users = udao.readAll();
              ModelAndView mv = new ModelAndView();
              //fixed: delete own account
              users.remove(req.getSession().getAttribute("loggedinuser"));
              //end
              mv.setViewName("manageUser-all.jsp");
              mv.addObject("users", users);

              return mv;
       }

       
       @RequestMapping("/altActivateUser")
       public String deactivateUser(HttpServletRequest req, HttpServletResponse resp) {
              String userid = req.getParameter("deleteUser");

              long userid2 = Long.valueOf(userid);
              UserDAOI udao = new UserDAOImpl();
              T_User user = udao.readById(userid2);
              user.setActive(!(user.getIsActive()));
              udao.update(user);
              ModelAndView mv = new ModelAndView();
              mv.setViewName("manageUser-all.jsp");
              List<T_User> users = udao.readAll();
              mv.addObject("message", "Altered User successfully");
              mv.addObject("users", users);
              return "redirect:listAllUser";
       }

       public Target checkTarget(String input, T_User user, LocalDate localDate, ActivityType type) {
           Target inputTarget = null;
           // input is not empty or null or non numeric
           if (!(InputChecking.emptyCheck(input) || !input.matches(InputChecking.numbersRegex))) {
                  int value = Integer.parseInt(input);
                  // value must be greater than 0
                  if (value > 0) {
                        inputTarget = new Target(user, type, localDate, value, true);
                  }
           }
           return inputTarget;
    }

       
       public void addNonNullTargetToDatabase(Target checkTarget) {
              TargetDAOImpl tdao = new TargetDAOImpl();
              if (checkTarget != null) {
                     tdao.create(checkTarget);
              }
       }
       
       // NOTE: String name = [Enum class name].[Enum value name].name(); Returns the
       // enum converted to String
       // NOTE: Privilege priv = [Enum Class name].valueOf([Enum value name]);
       @RequestMapping("/updateUserOp")
       public String updateUserOp(HttpServletRequest req, HttpServletResponse resp,
                     @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
                     @RequestParam("position") String position, @RequestParam("call") String call,
                     @RequestParam("email") String email, @RequestParam("meeting") String meeting,
                     @RequestParam("interview") String interview, @RequestParam("clientVisited") String clientVisited,
                     @RequestParam("dateOfMessage") String dateOfMessage) {

              UserDAOI udao = new UserDAOImpl();
              String userid = req.getParameter("userid");

              long userid2 = Long.valueOf(userid);
              T_User user = udao.readById(userid2);
              
              if (!InputChecking.emptyCheck(dateOfMessage)) {
                  LocalDate localDate = LocalDate.parse(dateOfMessage);
                  addNonNullTargetToDatabase(checkTarget(call, user, localDate, ActivityType.Call));
                  addNonNullTargetToDatabase(checkTarget(email, user, localDate, ActivityType.Email));
                  addNonNullTargetToDatabase(checkTarget(meeting, user, localDate, ActivityType.Meeting));
                  addNonNullTargetToDatabase(checkTarget(interview, user, localDate, ActivityType.Interview));
                  addNonNullTargetToDatabase(checkTarget(clientVisited, user, localDate, ActivityType.ClientVisit));
           }



              String[] enumNames = req.getParameterValues("enumIndexes"); // parameter value = jsp parameter name of enums
              HashMap<Privilege, Integer> privileges = new HashMap<Privilege, Integer>();
              user.setPrivileges(privileges);
              user.setFirstName(firstname);
              user.setLastName(lastname);
              user.setPosition(position);

              if (enumNames != null) {
                     for (String e : enumNames) {
                           Privilege priv = Privilege.valueOf(e);
                           user.addPrivilege(priv);
                     }
              }
              udao.update(user);
              
              T_User loggedInUser = (T_User) req.getSession().getAttribute("loggedinuser");
              if (user.getUserId() == loggedInUser.getUserId()) {            	  
            	  req.getSession().setAttribute("loggedinuser", user);
              }
              ModelAndView mv = new ModelAndView();
              mv.setViewName("manageUser-all.jsp");
              mv.addObject("message", "Updated");
              List<T_User> users = udao.readAll();
              mv.addObject("users", users);
              return "redirect:listAllUser";
       }

       
       @RequestMapping("/updateUserNorm")
       public ModelAndView updateUserNorm(HttpServletRequest req, HttpServletResponse resp,

                     @RequestParam("currentPW") String currentPass, @RequestParam("newPW") String newPass,
                     @RequestParam("confirmPW") String confirmPass) {
    	   	  
    	   
    	   	  currentPass = PasswordUtils.encrypt(currentPass);
    	   	  
    	   	  
    	   
              UserDAOI udao = new UserDAOImpl();
              T_User user = (T_User) req.getSession().getAttribute("loggedinuser");

              ModelAndView mv = new ModelAndView();

              // Check if currentPass and current User Password Match
              if (!(user.getPassword().equals(currentPass))) {
                     mv.setViewName("manageUser-regular.jsp");
                     mv.addObject("message", "Current Password does not match user's old password");
                     return mv;
              }
              if (!(InputChecking.checkPassword(newPass) || InputChecking.checkPassword(confirmPass))) {
                     mv.setViewName("manageUser-regular.jsp");
                     mv.addObject("message", "Invalid new password or confirm password.");
                     return mv;
              }
              if (!(newPass.equals(confirmPass))) {
                     mv.setViewName("manageUser-regular.jsp");
                     mv.addObject("message", "New password and Confirm password don't match.");
                     return mv;
              }
              newPass = PasswordUtils.encrypt(newPass);
              user.setPassword(newPass);
              udao.update(user);
              mv.setViewName("manageUser-regular.jsp");
              mv.addObject("message", "Updated");
              return mv;

       }

}

