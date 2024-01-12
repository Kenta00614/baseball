<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="header.jsp" %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
    <style>
        /* Add your custom styles here */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h2 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        select {
            margin-bottom: 10px;
        }

        a {
            text-decoration: none;
            color: #007BFF;
            margin-bottom: 10px;
            display: inline-block;
        }

        .unregistered {
            color: #999;
        }
    </style>
</head>
<body>
    <a href="MatchDisplay" type="submit">戻る</a>
    <c:choose>
        <%-- 試合情報がないとき --%>
        <c:when test="${fn:length(matchList)== 0 }">
            <p>試合情報が登録されていません</p>
        </c:when>
        <%-- 試合情報があるとき --%>
        <c:when test="${fn:length(matchList)> 0}">
            <h2>試合情報</h2>
            <div id="app">
                <%-- ドロップダウンリスト・日付の変更 --%>
                <select v-model="selectedEventDate" @change="updateSelectedEvent">
                    <c:forEach var="match" items="${matchList }">
                        <option value="${match.eventDate }">${match.eventDateStr }</option>
                    </c:forEach>
                </select>

                <%-- 日付のテーブル --%>
                <table v-for="(match, index) in filteredMatchList" :key="index">
                    <thead>
                        <tr>
                            <th>開催日</th>
                            <th>試合順</th>
                            <th>高校名</th>
                            <th>高校名</th>
                            <th>試合状況</th>
                            <th>第何回戦</th>
                            <th>販売日時</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(duel, j) in duelList[selectIndex]" :key="j">
                            <td rowspan="4" v-if="j == 0">
                                <a :href="'MatchUpdateInput?date=' + match.eventDate">{{ match.eventDateStr }}</a>
                            </td>
                            <td>{{ j+1 }}試合目</td>
                            <%-- 値があるときは表示して、ないときは未登録と表示する --%>
                            <td v-if="duel.schoolNameA">{{ duel.schoolNameA }}</td>
                            <td v-else class="unregistered">未登録</td>
                            <td v-if="duel.schoolNameB">{{ duel.schoolNameB }}</td>
                            <td v-else class="unregistered">未登録</td>
                            <td v-if="duel.statusStr">{{ duel.statusStr }}</td>
                            <td v-else class="unregistered">未登録</td>
                            <td v-if="duel.roundStr">{{ duel.roundStr }}</td>
                            <td v-else class="unregistered">未登録</td>
                            <td rowspan="4" v-if="j == 0">{{ match.saleStartAt }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <script>
                new Vue({
                    el: '#app',
                    data: {
                        matchList: [
                            <c:forEach var="match" items="${matchList}">
                                {eventDate:"${match.eventDate }",eventDateStr:"${match.eventDateStr }",saleStartAt:"${match.saleStartAtStr }",flug:false,},
                            </c:forEach>
                        ],
                        duelList: [
                            <c:forEach begin="0" end="${fn:length(duelList)-1}" step="1" var="i">[
                                <c:forEach begin="0" end="${fn:length(duelList[i])-1}" step="1" var="j">
                                    {schoolNameA:"${duelList[i][j].schoolNameA }",schoolNameB:"${duelList[i][j].schoolNameB }",statusStr:"${duelList[i][j].statusStr }",roundStr:"${duelList[i][j].roundStr }",},
                                </c:forEach>
                            ],
                            </c:forEach>
                        ],
                        selectedEventDate: "",
                        nowDate: "${nowDate}",
                        selectIndex: 0,
                    },
                    created: function() {
                        this.initializeEventDate();
                    },
                    computed: {
                        filteredMatchList() {
                            return this.matchList.filter(match => match.flug === true);
                        }
                    },
                    methods: {
                        updateSelectedEvent() {
                            for (let i = 0; i < this.matchList.length; i++) {
                                if (this.matchList[i].eventDate === this.selectedEventDate) {
                                    this.matchList[i].flug = true;
                                    this.selectIndex = i;
                                } else {
                                    this.matchList[i].flug = false;
                                }
                            }
                        },
                        initializeEventDate() {
                            let found = false;

                            for (let i = 0; i < this.matchList.length; i++) {
                                if (this.matchList[i].eventDate === this.nowDate) {
                                    this.selectedEventDate = this.nowDate;
                                    found = true;
                                    this.matchList[i].flug = true;
                                    this.selectIndex = i;
                                    break;
                                }
                            }

                            if (!found && this.matchList.length > 0) {
                                this.selectedEventDate = this.matchList[0].eventDate;
                                this.matchList[0].flug = true;
                                this.selectIndex = 0;
                            }
                        }
                    },
                });
            </script>
        </c:when>
    </c:choose>
</body>
</html>
