<%-- 
    Document   : login
    Created on : Jun 5, 2023, 3:28:00 AM
    Author     : msi
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
        <title>Document</title>
        <link rel="stylesheet" href="./css/login_register.css">
        <!-- Font Awesome -->
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
        <div class="container">
            <div class="form-content">
                <div class="form-content-cover">
                    <!-- Pills navs -->
                    <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                        <li class="nav-item" role="presentation">
                            <a
                                class="nav-link active"
                                id="tab-login"
                                data-mdb-toggle="pill"
                                href="#pills-login"
                                role="tab"
                                aria-controls="pills-login"
                                aria-selected="true"
                                >Login</a
                            >
                        </li>
                        <li class="nav-item" role="presentation">
                            <a
                                class="nav-link"
                                id="tab-register"
                                data-mdb-toggle="pill"
                                href="#pills-register"
                                role="tab"
                                aria-controls="pills-register"
                                aria-selected="false"
                                >Register</a
                            >
                        </li>
                    </ul>
                    <!-- Pills navs -->

                    <!-- Pills content -->
                    <div class="tab-content">
                        <div
                            class="tab-pane fade show active"
                            id="pills-login"
                            role="tabpanel"
                            aria-labelledby="tab-login"
                            >
                            <form action="login" method="post">
                                <div class="text-center mb-3">
                                    <p>Sign in with:</p>
                                    <button type="button" class="btn btn-link btn-floating mx-1">
                                        <i class="fab fa-facebook-f"></i>
                                    </button>

                                    <button type="button" class="btn btn-link btn-floating mx-1">
                                        <i class="fab fa-google"></i>
                                    </button>

                                    <button type="button" class="btn btn-link btn-floating mx-1">
                                        <i class="fab fa-twitter"></i>
                                    </button>

                                    <button type="button" class="btn btn-link btn-floating mx-1">
                                        <i class="fab fa-github"></i>
                                    </button>
                                </div>

                                <p class="text-center">or:</p>

                                <!-- Input -->


                                <div class="form-outline mb-4">
                                    <input type="text" id="loginName" class="form-control" name="user"/>
                                    <label class="form-label" for="loginName">Username</label
                                    >
                                    <!-- display when Wrong format -->
                                    <!-- <div class="form-helper">Example Helper</div> -->
                                </div>

                                <!-- Password input -->
                                <div class="form-outline mb-4">
                                    <input
                                        type="password" id="loginPassword" class="form-control" name="pass"/>
                                    <label class="form-label" for="loginPassword">Password</label>
                                </div>


                                <center>
                                    <h1 style="color: red">${requestScope.error}</h1>
                                </center>


                                <!-- 2 column grid layout -->
                                <div class="row mb-4">
                                    <div class="col-md-6 d-flex justify-content-center">
                                        <!-- Checkbox -->
                                        <div class="form-check mb-3 mb-md-0">
                                            <input
                                                class="form-check-input"
                                                type="checkbox"
                                                value=""
                                                id="loginCheck"
                                                checked
                                                />
                                            <label class="form-check-label" for="loginCheck">
                                                Remember me
                                            </label>
                                        </div>
                                    </div>

                                    <div class="col-md-6 d-flex justify-content-center">
                                        <!-- Simple link -->
                                        <a href="#!">Forgot password?</a>
                                    </div>
                                </div>

                                <!-- Submit button -->
                                <input type="submit" class="btn btn-warning btn-block mb-4" value="Sign in">

                                <!-- Register buttons -->
                                <div class="text-center">
                                    <p>Not a member? <a href="#!">Register</a></p>
                                </div>
                            </form>
                        </div>
                        <div
                            class="tab-pane fade"
                            id="pills-register"
                            role="tabpanel"
                            aria-labelledby="tab-register"
                            >
                            <form action="register" method="post">
                                <div class="text-center mb-3">
                                    <p>Sign up with:</p>
                                    <button type="button" class="btn btn-link btn-floating mx-1">
                                        <i class="fab fa-facebook-f"></i>
                                    </button>

                                    <button type="button" class="btn btn-link btn-floating mx-1">
                                        <i class="fab fa-google"></i>
                                    </button>

                                    <button type="button" class="btn btn-link btn-floating mx-1">
                                        <i class="fab fa-twitter"></i>
                                    </button>

                                    <button type="button" class="btn btn-link btn-floating mx-1">
                                        <i class="fab fa-github"></i>
                                    </button>
                                </div>

                                <p class="text-center">or:</p>


                                <!-- Username input -->
                                <div class="form-outline mb-4">
                                    <input
                                        type="text"
                                        id="registerUsername"
                                        class="form-control"
                                        name="user"
                                        />
                                    <label class="form-label" for="registerUsername"
                                           >Username</label
                                    >
                                </div>

                                <!-- Password input -->
                                <div class="form-outline mb-4">
                                    <input
                                        type="password"
                                        id="registerPassword"
                                        class="form-control"
                                        name="pass"
                                        />
                                    <label class="form-label" for="registerPassword"
                                           >Password</label
                                    >
                                </div>

                                <!-- Repeat Password input -->
                                <div class="form-outline mb-4">
                                    <input
                                        type="password"
                                        id="registerRepeatPassword"
                                        class="form-control"
                                        name="repeat"
                                        />
                                    <label class="form-label" for="registerRepeatPassword"
                                           >Repeat password</label
                                    >
                                </div>

                                <center>
                                    <h1 style="color: red">${requestScope.error}</h1>
                                </center>

                                <!-- Checkbox -->
                                <div class="form-check d-flex justify-content-center mb-4">
                                    <input
                                        class="form-check-input me-2"
                                        type="checkbox"
                                        value=""
                                        id="registerCheck"
                                        checked
                                        aria-describedby="registerCheckHelpText"
                                        />
                                    <label class="form-check-label" for="registerCheck">
                                        I have read and agree to the terms
                                    </label>
                                </div>

                                <!-- Submit button -->                                
                                <input type="submit" class="btn btn-warning btn-block mb-4" value="Register">
                            </form>


                        </div>
                    </div>
                    <!-- Pills content -->
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

