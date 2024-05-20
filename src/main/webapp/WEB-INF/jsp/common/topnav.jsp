
<!-- Navbar -->
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <div class="collapse navbar-collapse order-3" id="navbarCollapse">

        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="./" role="button">
                    <img src="${staticMinUrl}/images/jm.svg" width="32"/>
                </a>
            </li>
            <li class="nav-item">
                <a href="${appPath}/products.html" class="nav-link">Products</a>
            </li>

        </ul>

    </div>

    <!-- Right navbar links[START] -->
    <ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
        <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="#" aria-expanded="false">
                <i class="fas fa-cog"></i>
            </a>
            <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow" style="left: 0px; right: inherit;">
                <li>
                    <table cellpadding="5" cellspacing="5">
                        <tr>
                            <td><a href="${appPath}/login" class="dropdown-item"> Login </a></td>
                        </tr>
                        <tr>
                            <td><a href="javascript:Common.logout()" class="dropdown-item"> Logout </a></td>
                        </tr>
                    </table>
                </li>

            </ul>
        </li>
    </ul><!-- Right navbar links[END] -->

</nav>
<!-- /.navbar -->