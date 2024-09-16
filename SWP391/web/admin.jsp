<%-- 
    Document   : home
    Created on : Jun 4, 2023, 7:31:47 PM
    Author     : Trịnh Minh Tân
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Foodshop</title>
        <link rel="stylesheet" href="./css/homepage.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
            />
    </head>

    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header-left">
                    <a href="" class="logo">
                        <img class="img-logo" src="./images/Foodshop-removebg.png" alt="" />
                    </a>
                    <div id="menu">
                        <div class="item">
                            <a class="item-menu-content" href="home">Trang chủ</a>
                        </div>
                        <div class="item">
                            <a class="item-menu-content" href="">Sản phẩm</a>
                        </div>
                        <div class="item">
                            <a class="item-menu-content" href="">Blog</a>
                        </div>
                        <div class="item">
                            <a class="item-menu-content" href="">Liên hệ</a>
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
            <div id="banner">
                <div class="box-left">
                    <h2>
                        <span>THỨC ĂN</span>
                        <br />
                        <span>THƯỢNG HẠNG</span>
                    </h2>
                    <p>
                        Chuyên cung cấp các món ăn đảm bảo dinh dưỡng hợp vệ sinh đến người
                        dùng,phục vụ người dùng 1 cái hoàn hảo nhất
                    </p>
                    <button>Mua ngay</button>
                </div>
                <div class="box-right">
                    <img src="./images/img_1.png" alt="" />
                    <img src="./images/img_2.png" alt="" />
                    <img src="./images/img_3.png" alt="" />
                </div>
                <div class="to-bottom">
                    <a href="">
                        <img src="./images/to_bottom.png" alt="" />
                    </a>
                </div>
            </div>


            <c:set var="num1" value="${requestScope.num1}"/>
            <c:set var="num2" value="${requestScope.num2}"/>
            <c:set var="type" value="${requestScope.type}"/>
            <c:set var="account" value="${sessionScope.account}"/>

            <h2>${account.username}</h2>
            
            <c:if test="${type==2}">
                <div id="wp-products">
                    <h2>${requestScope.error}</h2>
                    <ul id="list-products">
                        <c:forEach items="${requestScope.search}" var="c">
                            <div class="item">
                                <img src="${c.img}" alt="" />  
                                <a href="detail?num=${c.id}">
                                    <div class="name">${c.name_food}</div>
                                </a>
                            </div>
                        </c:forEach>
                </div>
            </c:if>


            <c:if test="${type==1}">
                <div id="wp-products" style="margin: 120px auto 0px auto !important;">
                    <h2>NHỮNG MÓN ĂN SIÊU SALE</h2>
                    <ul id="list-products">
                        <c:forEach items="${requestScope.foodsale}" var="c">
                            <div class="item">
                                <img src="${c.img}" alt="" />  
                                <a href="detail?num=${c.id}">
                                    <div class="name">${c.name_food}</div>
                                </a>
                                <div class="price">Giảm giá lên đến: ${c.price_final} %</div>
                            </div>
                        </c:forEach>
                </div>

                <div class="list-page">
                    <div class="item">
                        <a href="homeshow?saledisplay=${(num1+1)}&amp;cheapdisplay=${num2}">+</a>
                    </div>
                </div>

                <div id="wp-products" style="margin: 120px auto 0px auto !important;">
                    <h2>NHỮNG MÓN ĂN SIÊU RẺ</h2>
                    <br>
                    <ul id="list-products">
                        <c:forEach items="${requestScope.foodcheap}" var="c">
                            <div class="item">
                                <img src="${c.img}" alt="" />  
                                <a href="detail?num=${c.id}">
                                    <div class="name">${c.name_food}</div>
                                </a>
                                <div class="price">Giá chỉ còn: ${c.price_final} VNĐ</div>
                            </div>
                        </c:forEach>
                </div>

                <div class="list-page">
                    <div class="item">
                        <a href="homeshow?saledisplay=${num1}&amp;cheapdisplay=${(num2+1)}">+</a>
                    </div>
                </div>

                <br>
                </ul>


                <br>
                </ul>
                <div id="saleoff">
                    <div class="box-left">
                        <h1>
                            <span>GIẢM GIÁ LÊN ĐẾN</span>
                            <span>45%</span>
                        </h1>
                    </div>
                    <div class="box-right"></div>
                </div>

                <div id="comment">
                    <h2>NHẬN XÉT CỦA KHÁCH HÀNG</h2>
                    <div id="comment-body">
                        <div class="prev">
                            <a href="#">
                                <img src="./images/prev.png" alt="" />
                            </a>
                        </div>
                        <ul id="list-comment">
                            <li class="item">
                                <div class="avatar">
                                    <img src="./images/avatar_1.png" alt="" />
                                </div>
                                <div class="stars">
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                </div>
                                <div class="name">Nguyễn Đình Vũ</div>

                                <div class="text">
                                    <p>
                                        Lorem Ipsum is simply dummy text of the printing and
                                        typesetting industry. Lorem Ipsum has been the industry's
                                        standard dummy text ever since the 1500s, when an unknown
                                        printer took a galley of type and scrambled it to make a type
                                        specimen book.
                                    </p>
                                </div>
                            </li>
                            
                            <li class="item">
                                <div class="avatar">
                                    <img src="./images/avatar_1.png" alt="" />
                                </div>
                                <div class="stars">
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                </div>
                                <div class="name">Trần Ngọc Sơn</div>

                                <div class="text">
                                    <p>
                                        Lorem Ipsum is simply dummy text of the printing and
                                        typesetting industry. Lorem Ipsum has been the industry's
                                        standard dummy text ever since the 1500s, when an unknown
                                        printer took a galley of type and scrambled it to make a type
                                        specimen book.
                                    </p>
                                </div>
                            </li>
                            <li class="item">
                                <div class="avatar">
                                    <img src="./images/avatar_1.png" alt="" />
                                </div>
                                <div class="stars">
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                    <span>
                                        <img src="./images/star.png" alt="" />
                                    </span>
                                </div>
                                <div class="name">Nguyễn Trần Vi</div>

                                <div class="text">
                                    <p>
                                        Lorem Ipsum is simply dummy text of the printing and
                                        typesetting industry. Lorem Ipsum has been the industry's
                                        standard dummy text ever since the 1500s, when an unknown
                                        printer took a galley of type and scrambled it to make a type
                                        specimen book.
                                    </p>
                                </div>
                            </li>
                        </ul>
                        <div class="next">
                            <a href="#">
                                <img src="./images/next.png" alt="" />
                            </a>
                        </div>
                    </div>
                </div>
            </c:if>


            <div id="footer">
                <div class="box">
                    <div class="logo">
                        <img class="footer-logo" src="./images/Foodshop-removebg.png" alt="" />
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
                        <input type="text" placeholder="Địa chỉ email"/>
                        <button>Nhận tin</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="./js/script.js"></script>
    </body>
</html>
