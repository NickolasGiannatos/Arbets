<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>

        <!DOCTYPE html>
        <html>

        <head>
            <%@ include file="header.jsp" %>
                <title>Error</title>
        </head>

        <body>

            <%@ include file="navbar.jsp" %>

                <div class="container">
                    <div class="page-header">
                        <br>
                        <h1>Ooops something went wrong</h1>
                    </div>

                    <div class="row">

                        <div class="col-xs-12">

                            <br>
                           

                            <% if(exception != null) { %>
                                <p> <b><%=exception.getMessage() %></b></p>
                                <% } %>

                        </div>

                    </div>

                </div>
                <!-- /.container -->

                <%@ include file="footer.jsp" %>
        </body>

        </html>