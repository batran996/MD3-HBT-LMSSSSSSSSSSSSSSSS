package rikkei.academy.controller;

import rikkei.academy.service.lotrinh.LoTrinh;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//name = "AdminServlet", value = "/AdminServlet"
@WebServlet("/")
public class LoTrinhServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private LoTrinh loTrinh;

        public void init() {
            loTrinh = new LoTrinh();
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
            List<rikkei.academy.model.LoTrinh> listUser = loTrinh.selectAllUsers();
            request.setAttribute("listAdmin", listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewadmin/user-lisst.jsp");
            dispatcher.forward(request, response);
        }

        private void showNewForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp/viewadmin/user-form.jsp");
            dispatcher.forward(request, response);
        }

        private void showEditForm(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            rikkei.academy.model.LoTrinh existingUser = loTrinh.selectAdmin(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewadmin/user-form.jsp");
            request.setAttribute("admin", existingUser);
            dispatcher.forward(request, response);

        }

        private void insertUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
            String name = request.getParameter("name");
            rikkei.academy.model.LoTrinh newLoTrinh = new rikkei.academy.model.LoTrinh(name);
            loTrinh.insertAdmin(newLoTrinh);
            listUser(request,response);
        }

        private void updateUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            rikkei.academy.model.LoTrinh list = new rikkei.academy.model.LoTrinh(id, name);
            loTrinh.updateAdmin(list);
            listUser(request,response);
        }

        private void deleteUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            loTrinh.deleteAdmin(id);
            response.sendRedirect("list");

        }
    }
