package rikkei.academy.controller;

import rikkei.academy.model.Admin;
import rikkei.academy.service.admin.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//name = "AdminServlet", value = "/AdminServlet"
@WebServlet("/")
public class AdminServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private UserDAO userDAO;

        public void init() {
            userDAO = new UserDAO();
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String action = request.getServletPath();

            try {
                switch (action) {
                    case "/new":
                        showNewForm(request, response);
                        break;
                    case "/insert":
                        insertUser(request, response);
                        break;
                    case "/delete":
                        deleteUser(request, response);
                        break;
                    case "/edit":
                        showEditForm(request, response);
                        break;
                    case "/update":
                        updateUser(request, response);
                        break;
                    default:
                        listUser(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }


    private void listUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
            List<Admin> listUser = userDAO.selectAllUsers();
            request.setAttribute("listAdmin", listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewadmin/user-lisst.jsp");
            dispatcher.forward(request, response);
        }

        private void showNewForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewadmin/user-form.jsp");
            dispatcher.forward(request, response);
        }

        private void showEditForm(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            Admin existingUser = userDAO.selectAdmin(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewadmin/user-form.jsp");
            request.setAttribute("admin", existingUser);
            dispatcher.forward(request, response);

        }

        private void insertUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
            String name = request.getParameter("name");
            Admin newAdmin = new Admin(name);
            userDAO.insertAdmin(newAdmin);
            listUser(request,response);
        }

        private void updateUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Admin list = new Admin(id, name);
            userDAO.updateAdmin(list);
            listUser(request,response);
        }

        private void deleteUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.deleteAdmin(id);
            response.sendRedirect("list");

        }
    }
