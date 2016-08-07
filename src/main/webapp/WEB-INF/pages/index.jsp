<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: de1mos
  Date: 2.08.16
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Uptime Test Task - Amazon's Product Advertising API</title>

    <spring:url value="/resources/css/common.css" var="commonCss" />
    <spring:url value="/resources/css/index.css" var="indexCss" />
    <spring:url value="/resources/js/plugins/require.js" var="requireJs" />
    <spring:url value="/resources/js/includes/index.js" var="indexJs" />

    <link href="${commonCss}" rel="stylesheet" type="text/css"/>
    <link href="${indexCss}" rel="stylesheet" type="text/css"/>
</head>
<body>
    <header class="page-header">
        <div class="logo-wrapper">
            <img class="header-logo" src="<c:url value="resources/img/amazon-logo.png"/>"/>
            <div class="logo-text">Product Adertising API Test</div>
        </div>
    </header>
    <section class="page-content">
        <div class="search-wrapper">
            <select class="search-category" id="search-category">
                <option value="All">All</option>
                <option value="Books">Books</option>
                <option value="DVD">DVD</option>
                <option value="Music">Music</option>
                <option value="Apparel">Apparel</option>
                <option value="Video">Video</option>
                <option value="Automotive">Automotive</option>
                <option value="Watch">Watch</option>
                <option value="Electronics">Electronics</option>
            </select>
            <input type="text" placeholder="..." class="search-input-text" id="keywords">
            <button type="button" class="search-button" id="search-products-btn">Search</button>
        </div>
        <div class="products-catalog-wrapper">
            <div class="products-catalog">
            </div>
            <div class="products-catalog-pagintaion"></div>
        </div>
        <div class="clear"></div>
    </section>
    <footer class="page-footer"></footer>
    <script data-main="${indexJs}" src="${requireJs}"></script>
</body>
</html>
