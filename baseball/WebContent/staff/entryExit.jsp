<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Your existing styles here */

        /* Add new styles for the custom button */
        .custom-btn {
            background: #000;
            color: #fff;
            line-height: 42px;
            padding: 0;
            border: none;
            position: relative;
            overflow: hidden;
            cursor: pointer;
        }

        .custom-btn span {
            position: relative;
            display: block;
            width: 100%;
            height: 100%;
            z-index: 1;
        }

        .custom-btn:before,
        .custom-btn:after {
            position: absolute;
            content: "";
            height: 0%;
            width: 2px;
            background: #000;
            transition: all 500ms ease;
        }

        .custom-btn:before {
            right: 0;
            top: 0;
        }

        .custom-btn:after {
            left: 0;
            bottom: 0;
        }

        .custom-btn:hover {
            color: #000;
            background: transparent;
        }

        .custom-btn:hover:before {
            height: 100%;
        }

        .custom-btn:hover:after {
            height: 100%;
        }

        .custom-btn span:before,
        .custom-btn span:after {
            position: absolute;
            content: "";
            background: #000;
            transition: all 500ms ease;
        }

        .custom-btn span:before {
            left: 0;
            top: 0;
            width: 0%;
            height: 2px;
        }

        .custom-btn span:after {
            right: 0;
            bottom: 0;
            width: 0%;
            height: 2px;
        }

        .custom-btn span:hover:before {
            width: 100%;
        }

        .custom-btn span:hover:after {
            width: 100%;
        }
    </style>
    <title>入退場画面</title>
</head>

<body>
    <h1>入場・退場</h1>
    <div class="header">
        <form action="Entry" method="get">
            <button class="custom-btn btn-6" type="submit"><span>入場</span></button>
        </form>
        <form action="Exit" method="get">
            <button class="custom-btn btn-6" type="submit"><span>退場</span></button>
        </form>
    </div>
</body>

</html>
