<%-- 
    Document   : fooddetail
    Created on : Jun 7, 2023, 11:30:27 AM
    Author     : msi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
</html>
<link rel="stylesheet" href="./css/fooddetail.css" />
<link rel="stylesheet" href="./css/homepage.css" />
<link
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
    rel="stylesheet"
    />
<!-- Google Fonts -->
<link
    href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
    rel="stylesheet"
    />
<!-- MDB -->
<link
    href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.1/mdb.min.css"
    rel="stylesheet"
    />
</head>
<body>
    <div class="wrapper">
        <!-- Navbar -->
        <div id="header-cover">
            <div id="header">
                <div id="header-left">
                    <a href="index.html" class="logo">
                        <img
                            class="img-logo"
                            src="./images/Foodshop-removebg.png"
                            alt=""
                            />
                    </a>
                    <div id="menu">
                        <div class="item">
                            <a class="item-menu-content" href="home">Trang chủ</a>
                        </div>
                        <div class="item">
                            <a class="item-menu-content" href="foodmenu.html">Sản phẩm</a>
                        </div>
                        <div class="item">
                            <a class="item-menu-content" href="orderlookup.html"
                               >Tra cứu đơn hàng</a
                            >
                        </div>
                        <div class="item">
                            <a class="item-menu-content" href="support.html">Hỗ trợ</a>
                        </div>
                    </div>
                </div>
                <div id="header-actions">
                    <div class="item">
                        <form style="position: relative" action="search" method="post">      
                            <input
                                class="search-engine"
                                type="text"
                                placeholder="Tìm kiếm..."
                                style="height: 30px"
                                name="find"
                                />
                            <input class="fa-solid fa-magnifying-glass"
                                   style="position: absolute; right: 5px; top: 6px"
                                   type="submit" value="?">  
                            </input>
                        </form>
                    </div>
                    <div class="item">
                        <a href="login"><i class="fa-solid fa-user"></i></a>
                    </div>
                    <div class="item">
                        <a href="cart.html"><i class="fa-solid fa-cart-shopping"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Main Content -->

        <c:set var="account" value="${sessionScope.account}"/>

        <c:forEach items="${requestScope.infofood}" var="c">
            <div class="product-detail">
                <!-- Picture -->
                <div class="col-sm-5">
                    <img
                        class="img-product"
                        src="${c.img}"
                        alt="mon an"
                        />
                </div>
                <div class="col-sm-1"></div>
                <!-- Information -->
                <form action="" class="product-information col-sm-6" >
                    <!-- Header -->
                    <h1>${c.name_food}</h1>
                    <!-- Description -->
                    <p class="description">Mô tả: ${c.describe_food}</p>
                    <!-- Price -->
                    <h3 class="price">Giá bán: ${c.price_final}  VNĐ</h3>

                    <h3 class="price">Đánh giá sản phẩm: ${requestScope.rate} ✮</h3>


                    <!-- Unit -->
                    <div class="form-outline">
                        <input type="number" id="quantity" class="form-control" min="0" value="1" step="1"/>
                    </div>          
                    <!-- Button Add -->
                    <br />
                    <button class="btn">Đặt mua</button>
                    <br />
                    <button class="btn">Thêm vào giỏ hàng</button>
                </form>
            </div>
        </c:forEach>


        <div id="comment">
            <h2>NHẬN XÉT CỦA KHÁCH HÀNG</h2>
            <div id="comment-body">

                <div class="prev">
                    <a href="#">
                        <img src="./images/prev.png" alt="" />
                    </a>
                </div>
                <c:forEach items="${requestScope.commentfood}" var="c">
                    <ul id="list-comment">
                        <li class="item">
                            <div class="name">${c.full_name}</div>

                            <div class="stars">
                                <h1>${c.rate}</h1>
                                <img src="./images/star.png" alt="" />
                            </div>

                            <div class="name">${c.time_comment}</div>
                            <div class="text">
                                <p>
                                    ${c.comment_use}
                                </p>
                            </div>
                        </li>  

                    </ul>
                </c:forEach>

                <div class="next">
                    <a href="#">
                        <img src="./images/next.png" alt="" />
                    </a>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <div id="footer">
            <div class="box">
                <div class="logo">
                    <img
                        class="footer-logo"
                        src="./images/Foodshop-removebg.png"
                        alt=""
                        />
                </div>
                <!-- <p>Cung cấp sản phẩm với chất lượng an toàn cho quý khách</p> -->
            </div>
            <div class="box">
                <h3>NỘI DUNG</h3>
                <ul class="quick-menu">
                    <div class="item">
                        <a href="home">Trang chủ</a>
                    </div>
                    <div class="item">
                        <a href="">Sản phẩm</a>
                    </div>
                    <div class="item">
                        <a href="">Blog</a>
                    </div>
                    <div class="item">
                        <a href="">Liên hệ</a>
                    </div>
                </ul>
            </div>
            <div class="box">
                <h3>LIÊN HỆ</h3>
                <form action="">
                    <input type="text" placeholder="Địa chỉ email" />
                    <button>Nhận tin</button>
                </form>
            </div>
        </div>
    </div>
    <!-- MDB -->
    <script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.1/mdb.min.js"
    ></script>
</body>
</html>
