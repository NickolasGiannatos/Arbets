<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="check_authentication.jsp" %>
        <%@ page import="arbets.*, java.util.*"%>
        <%@ page errorPage="error_page.jsp"%>
            <%
            User curUser = (User)session.getAttribute("authentication");
            UserService us= new UserService();
            DB db = (DB) session.getAttribute("db");
            %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <%@ include file="header.jsp" %>
                        <title>My profile</title>

                </head>

                <body>
                    <%@ include file="navbar.jsp" %>


                        <!-- ======= profile Section ======= -->
                        <section id="profile" class="profile">
                            <div class="container">

                                <div class="row">
                                    <center>
                                        <div class="col-md-12">
                                            <div class="col-lg-6 col-md-6 d-flex align-items-center" data-aos="zoom-in">
                                                <div class="member">
                                                    <%
                                                        if (request.getAttribute("success_edit") != null) {
                                    %>
                                                        <div class="alert alert-success">

                                                            <%=(String)request.getAttribute("success_edit") %>

                                                        </div>
                                                        <%
                                                        }
                                                        %>
                                                            <img src="../Arbets/assets/img/team/<%=us.findImage(curUser.getId(),db)%>" alt="prifile_picture">



                                                            <h4>
                                                                <%=curUser.getFirstname()%>
                                                                    <%=curUser.getSurename()%>
                                                            </h4>
                                                            <span>My account</span>
                                                            <b>Points: </b>
                                                            <%=curUser.getPoints()%>
                                                                <br>



                                                                <b>E-mail: </b>
                                                                <%=curUser.getEmail()%>
                                                                    <br>
                                                                    <b>Username: </b>
                                                                    <%=curUser.getUsername()%>
                                                                        <br>
                                                                        <b>Date of Birth: </b>
                                                                        <%
                                                                        Calendar cal = Calendar.getInstance();
cal.setTime(curUser.getBirthdate());
String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
out.print(formatedDate);
                                                                        %>

                                                                            <br>

                                                                            <b>Bank account: </b>
                                                                            <%=curUser.getBankAccount()%>
                                                                                <br>
                                                                                <br>

                                                                                <button type="button" class="btn btn-dark"><a href="../Arbets/edit_profile.jsp">Edit</a></button>
                                                </div>

                                            </div>
                                        </div>
                                    </center>

                                </div>
                            </div>
                        </section>
                        <!-- End profile Section -->
                        <br>
                        <br>
                        <br>
                        <%@ include file="footer.jsp" %>

                </body>

                </html>