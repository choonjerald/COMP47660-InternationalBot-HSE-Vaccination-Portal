<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="fusioncharts.FusionCharts" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="../Styles/ChartSampleStyleSheet.css" rel="stylesheet" />
<script type="text/javascript" src="//cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
<script type="text/javascript" src="//cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
<style>
    <%@include file="../css/welcome.css"%>
    .scrolled-down {
        transform: translateY(0);
        transition: all 0.3s ease-in-out;
    }

    .scrolled-up {
        transform: translateY(0);
        transition: all 0.3s ease-in-out;
    }
    table {
        margin: 0 auto;
        border-collapse: collapse;
        overflow: hidden;
        box-shadow: 0 0 20px rgba(0,0,0,0.1);
    }

    th, td {
        padding: 15px;
        color: white;
        text-align: center;
        font-size: large;
        font-weight: bold;
    }
    td {
        background-color: rgba(255,255,255,0.2);
    }
    th {
        background-color: #55608f;
        text-align: left;
    }
    body{
        background: linear-gradient(45deg, #49a09d, #5f2c82);
    }
</style>
<html>
<head>
    <title>Statistics</title>
</head>
<div class="autohide header-blue">
    <nav class="navbar navbar-light navbar-expand-md navigation-clean-search">
        <div class="container-fluid"><a class="navbar-brand" href="/">HSE Vaccination</a></div>
        <div id="navbarSupportedContent" class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <c:choose>
                    <c:when test="${role == '[USER]' || role == '[ADMIN]'}">
                        <li class="nav-item"><a href="/logout"
                                                class="nav-link text-uppercase font-weight-bold">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a href="/login"
                                                class="nav-link text-uppercase font-weight-bold text-white">Login</a>
                        </li>
                        <li class="nav-item"><a href="/registration"
                                                class="nav-link text-uppercase font-weight-bold text-white">Register</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </nav>
    <div class="container hero">
        <div class="row">
            <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
                <h1>HSE Vaccination Statistics</h1>
                <p>Data visualisation for vaccination. The statistics and graphs in this section are built
                    using confirmed figures provided by the Health Service Executive (HSE),
                    and are updated following official release of new data.<br>
                </p>
            </div>
            <div class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block syringe-holder">
                <div class="syringe-mockup"><img class="syringe"
                                                 src="https://www.pinclipart.com/picdir/big/80-803622_hypodermic-needle-medicine-syringe-clip-art-wheel-heraldry.png">
                    <!-- <div class="screen">	</div>	-->
                </div>
            </div>
        </div>

        <div class="mt-5 text-center">
            <h1 class="text-white mb-5">Aggregated Statistics</h1>
            <h1>${firstVaccinePfizerCount}</h1>
            <h1>${secondVaccinePfizerCount}</h1>
            <h1>${firstVaccineModernaCount}</h1>
            <h1>${secondVaccineModernaCount}</h1>


            <div class="container">
                <table>
                    <thead>
                    <tr>
                        <th>Highest Vaccinated Nationality</th>
                        <th>Highest Vaccinated Age Group</th>
                        <th>Registered Males</th>
                        <th>Registered Females</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${mostCommonNationality}</td>
                        <td>${mostCommonAgeGroup}</td>
                        <td>${registeredMales}</td>
                        <td>${registeredFemales}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div id="chart"></div>
        <div><span><a href="../Index.jsp">Go Back</a></span></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig = new HashMap<String, String>();
            chartConfig.put("caption", "Countries With Most Oil Reserves [2017-18]");
            chartConfig.put("subCaption", "In MMbbl = One Million barrels");
            chartConfig.put("xAxisName", "Country");
            chartConfig.put("yAxisName", "Reserves (MMbbl)");
            chartConfig.put("numberSuffix", "k");
            chartConfig.put("theme", "fusion");

            //store label-value pair
            Map<String, Integer> dataValuePair = new HashMap<String, Integer>();
            dataValuePair.put("Venezuela", 290);
            dataValuePair.put("Saudi", 260);
            dataValuePair.put("Canada", 180);
            dataValuePair.put("Iran", 140);
            dataValuePair.put("Russia", 115);
            dataValuePair.put("UAE", 100);
            dataValuePair.put("US", 30);
            dataValuePair.put("China", 30);

            StringBuilder jsonData = new StringBuilder();
            StringBuilder data = new StringBuilder();
            // json data to use as chart data source
            jsonData.append("{'chart':{");
            for(Map.Entry conf:chartConfig.entrySet())
            {
                jsonData.append("'" + conf.getKey()+"':'"+conf.getValue() + "',");
            }

            jsonData.replace(jsonData.length() - 1, jsonData.length() ,"},");

            // build  data object from label-value pair
            data.append("'data':[");

            for(Map.Entry pair:dataValuePair.entrySet())
            {
                data.append("{'label':'" + pair.getKey() + "','value':'" + pair.getValue() +"'},");
            }
            data.replace(data.length() - 1, data.length(),"]");

            jsonData.append(data.toString());
            jsonData.append("}");


//Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart = new FusionCharts(
                    "column2d",
                    "first_chart",
                    "800",
                    "550",
                    "chart",
                    "json",
                    jsonData.toString()
            );
        %>
        <%= firstChart.render() %>

    </div>
</div>

</html>

<script>
    document.addEventListener("DOMContentLoaded", function () {

        el_autohide = document.querySelector('.autohide');

        // add padding-top to bady (if necessary)
        navbar_height = document.querySelector('.navbar').offsetHeight;
        document.body.style.paddingTop = 0;

        if (el_autohide) {
            var last_scroll_top = 0;
            window.addEventListener('scroll', function () {
                let scroll_top = window.scrollY;
                if (scroll_top < last_scroll_top) {
                    el_autohide.classList.remove('scrolled-down');
                    el_autohide.classList.add('scrolled-up');
                } else {
                    el_autohide.classList.remove('scrolled-up');
                    el_autohide.classList.add('scrolled-down');
                }
                last_scroll_top = scroll_top;
            });
            // window.addEventListener
        }
        // if

    });
    window.onload = function() {

        var chart = new CanvasJS.Chart("chartContainer", {
            theme: "light2",
            title: {
                text: "Facebook for Android - App Rating"
            },
            subtitles: [{
                text: "December 2017"
            }],
            axisY: {
                title: "Number of Users",
                labelFormatter: addSymbols
            },
            data: [{
                type: "bar",
                indexLabel: "{y}",
                indexLabelFontColor: "#444",
                indexLabelPlacement: "inside",
                dataPoints: <%out.print(dataPoints);%>
            }]
        });
        chart.render();

        function addSymbols(e) {
            var suffixes = ["", "K", "M", "B"];

            var order = Math.max(Math.floor(Math.log(e.value) / Math.log(1000)), 0);
            if(order > suffixes.length - 1)
                order = suffixes.length - 1;

            var suffix = suffixes[order];
            return CanvasJS.formatNumber(e.value / Math.pow(1000, order)) + suffix;
        }

    }
</script>
