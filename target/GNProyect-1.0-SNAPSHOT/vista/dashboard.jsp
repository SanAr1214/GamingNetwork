<%-- 
    Document   : dashboard
    Created on : 5/09/2025, 11:22:05 p. m.
    Author     : santi
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Gaming Network</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Iconos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <style>
        body {
            background-color: #212121;
            color: #f5f5f5;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        /* Navbar */
        .navbar {
            background: linear-gradient(90deg, #212121, #e53935);
            padding: 0.8rem 1.5rem;
        }
        .navbar-brand {
            color: #fff !important;
            font-size: 1.5rem;
            font-weight: bold;
            letter-spacing: 1px;
        }
        .navbar-text a {
            color: #ff5252;
            font-weight: bold;
            text-decoration: none;
        }
        .navbar-text a:hover {
            text-decoration: underline;
        }
        .btn-logout {
            border: 1px solid #fff;
            color: #fff;
            transition: all 0.3s ease-in-out;
        }
        .btn-logout:hover {
            background-color: #fff;
            color: #212121;
        }

        /* Panel */
        .title-panel {
            color: #ff5252;
            font-weight: bold;
            text-align: center;
            margin-bottom: 2rem;
        }

        /* Cards */
        .card-custom {
            background: #2c2c2c;
            border-radius: 15px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            padding: 2rem 1rem;
        }
        .card-custom:hover {
            transform: translateY(-8px);
            box-shadow: 0px 10px 20px rgba(0,0,0,0.5);
        }
        .card-custom i {
            font-size: 2.5rem;
            color: #e53935;
            margin-bottom: 1rem;
        }
        .card-title {
            font-size: 1.3rem;
            color: #fff;
            margin-bottom: 0.5rem;
        }
        .card-text {
            font-size: 0.95rem;
            color: #cfcfcf;
            margin-bottom: 1.2rem;
        }
        .btn-custom {
            background-color: #e53935;
            color: #fff;
            border-radius: 25px;
            padding: 0.5rem 1.2rem;
            transition: background 0.3s ease;
        }
        .btn-custom:hover {
            background-color: #c62828;
        }
    </style>
</head>
<body>


<%
    usuario usuario = (usuario) session.getAttribute("usuarioIn");
    if (usuario == null) {
        response.sendRedirect("inicioUsuario.jsp");
        return;
    }
%>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Gaming Network</a>
        <div class="d-flex align-items-center">
            <span class="navbar-text me-3">
                Bienvenido, 
                <a href="<%= request.getContextPath() %>/perfil?idUsuario=<%= usuario.getIdUsuario() %>">
                <%= usuario.getNickname() %>
                </a>

            </span>
            <a href="inicioUsuario.jsp" class="btn btn-logout btn-sm">Cerrar Sesión</a>
        </div>
    </div>
</nav>


<!-- Contenido -->
<div class="container mt-5">
    <h2 class="title-panel">Panel de Control</h2>

    <div class="row g-4">
        <!-- Estaciones -->
        <div class="col-md-6">
            <div class="card-custom text-center">
                <i class="fas fa-desktop"></i>
                <h5 class="card-title">Estaciones</h5>
                <p class="card-text">Visualiza el estado de las estaciones y su disponibilidad.</p>
                <a href="estaciones.jsp" class="btn btn-custom">Gestionar</a>
            </div>
        </div>

        <!-- Reservas -->
        <div class="col-md-6">
            <div class="card-custom text-center">
                <i class="fas fa-calendar-alt"></i>
                <h5 class="card-title">Reservas</h5>
                <p class="card-text">Administra y consulta las reservas de los usuarios.</p>
                <a href="reservas.jsp" class="btn btn-custom">Gestionar</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
