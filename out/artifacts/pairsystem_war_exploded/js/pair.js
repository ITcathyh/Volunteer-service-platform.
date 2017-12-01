var type = 1;

function showsuccess() {
    $("#msgerror").hide();
    $("#msgsuccess").show();
    $('html,body').animate({scrollTop: 0}, 'slow');
}

function showsuccess1(msgtext) {
    $("#ss-msg-success-p").text(msgtext);
    $("#msgerror").hide();
    $("#msgsuccess").show();
    $('html,body').animate({scrollTop: 0}, 'slow');
}

function showerror(msgtext) {
    $("#msg-error-p").text(msgtext);
    $("#msgerror").show();
    $("#msgsuccess").hide();
    $('html,body').animate({scrollTop: 0}, 'slow');
}

function check(name, studentid, qq, email, phone) {
    if (!new RegExp("^[\u4e00-\u9fa5]{2,10}$", "").test(name)) {
        showerror("请输入正确的中文名(如名字内带符号请去掉符号)");
        return false;
    } else if (!new RegExp("^201[0-9]{5,5}$", "").test(studentid)) {
        showerror("请输入正确的学号");
        return false;
    } else if (!new RegExp("[1-9][0-9]{4,}", "").test(qq)) {
        showerror("请输入正确的QQ号码");
        return false;
    } else if (!new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$", "").test(email)) {
        showerror("请输入正确的邮箱");
        return false;
    } else if (!(/^1(3|4|5|7|8)\d{9}$/.test(phone))) {
        showerror("请输入正确的手机号");
        return false;
    }

    return true;
}

function addselfpair(name, studentid, qq, email, phone, anothername, anotherstudentid, anotherqq, anotheremail, anotherphone) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone +
        "&anothername=" + anothername + "&selfsex=" + $("#selfsex").val() +
        "&anotherstudentid=" + anotherstudentid + "&anotherqq=" + anotherqq +
        "&anotheremail=" + anotheremail + "&anotherphone=" + anotherphone + "&type=2&id=" + breurl,
        type: "post",
        headers: headers,
        url: "/checkaddbreuser",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "notfound") {
                showerror("提交失败，请确认需要匹配的人的个人信息");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "full") {
                showerror("提交失败，本次活动人数已满，请明天再报名吧~");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}

function addselfpair1(name, studentid, qq, email, phone, anothername, anotherstudentid, anotherqq, anotheremail, anotherphone) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&anothername=" + anothername +
        "&selfsex=" + $("#selfsex").val() + "&anotherstudentid=" + anotherstudentid +
        "&anotherqq=" + anotherqq + "&anotheremail=" + anotheremail + "&anotherphone=" + anotherphone +
        "&college=" + $("#college").val() + "&time=" + $("#time").val() +
        "&selfcollege=" + $("#selfcollege").val() + "&sex=" + $("#sex").val() + "&type=3&id=" + breurl,
        type: "post",
        headers: headers,
        url: "/checkaddbreuser",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "notfound") {
                showerror("提交失败，请确认需要匹配的人的个人信息");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "full") {
                showerror("提交失败，本次活动人数已满，请明天再报名吧~");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}

function addsystempair(name, studentid, qq, email, phone) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&college=" + $("#college").val() +
        "&selfsex=" + $("#selfsex").val() + "&time=" + $("#time").val() +
        "&selfcollege=" + $("#selfcollege").val() + "&sex=" + $("#sex").val() + "&type=1&id=" + breurl,
        type: "post",
        headers: headers,
        url: "/checkaddbreuser",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "full") {
                showerror("提交失败，本次活动人数已满，请明天再报名吧~");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#submitpair", function (e) {
    var name = $("#name").val();
    var studentid = $("#studentid").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var phone = $("#phone").val();

    if (check(name, studentid, qq, email, phone)) {
        if (type == 1) {
            la.start();
            addsystempair(name, studentid, qq, email, phone);
        } else {
            var anothername = $("#anothername").val();
            var anotherstudentid = $("#anotherstudentid").val();
            var anotherqq = $("#anotherqq").val();
            var anotheremail = $("#anotheremail").val();
            var anotherphone = $("#anotherphone").val();

            if (check(name, anotherstudentid, anotherqq, email, anotherphone)) {
                if (anotherstudentid == studentid || anotherqq == qq || anotheremail == email || anotherphone == phone) {
                    showerror("请输入匹配人正确的信息");
                } else {
                    la.start();
                    if (type == 2) {
                        addselfpair(name, studentid, qq, email, phone, anothername, anotherstudentid, anotherqq, anotheremail, anotherphone);
                    } else {
                        addselfpair1(name, studentid, qq, email, phone, anothername, anotherstudentid, anotherqq, anotheremail, anotherphone);
                    }
                }
            }
        }
    }
});

/* apply teacher begin */
function addteacher(name, studentid, qq, email, basecourse, professional, phone, la) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&selfsex=" + $("#selfsex").val() +
        "&basecourse=" + basecourse + "&professional=" + professional + "&college=" + $("#selfcollege").val(),
        type: "post",
        headers: headers,
        url: "/checkaddteacher",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#submitaddteacher", function (e) {
    var name = $("#name").val();
    var studentid = $("#studentid").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var basecourse = $("#basecourse").val();
    var professional = $("#professional").val();

    if (check(name, studentid, qq, email, phone)) {
        if (basecourse == "无" && professional == "无") {
            showerror("请选择至少一项擅长课程");
        } else {
            var la = Ladda.create(document.querySelector("#submitaddteacher"));
            la.start();

            addteacher(name, studentid, qq, email, basecourse, professional, phone, la);
        }
    }
});

function alterChoice(choice) {
    $("#professional").empty();

    if (choice == "工商管理学院") {
        $("#professional").append("<option>工商管理</option>");
        $("#professional").append("<option>中央银行业务</option>");
        $("#professional").append("<option>国际结算</option>");
        $("#professional").append("<option>计算机辅助CAD/CAM</option>");
        $("#professional").append("<option>金融学</option>");
        $("#professional").append("<option>金融时间序列分析(2)</option>");
        $("#professional").append("<option>行为金融与有效市场</option>");
        $("#professional").append("<option>生产系统建模与仿真</option>");
        $("#professional").append("<option>当代中国经济热点问题</option>");
        $("#professional").append("<option>社会保障学</option>");
        $("#professional").append("<option>土地管理学</option>");
        $("#professional").append("<option>资本论</option>");
        $("#professional").append("<option>基础工业工程</option>");
        $("#professional").append("<option>金融工程</option>");
        $("#professional").append("<option>技术经济学</option>");
        $("#professional").append("<option>计算机安全与保密</option>");
        $("#professional").append("<option>财务报表阅读与分析(双语)</option>");
        $("#professional").append("<option>现代制造系统</option>");
        $("#professional").append("<option>企业文化</option>");
        $("#professional").append("<option>管理信息系统(二)</option>");
        $("#professional").append("<option>网站设计</option>");
        $("#professional").append("<option>计量经济学导论</option>");
        $("#professional").append("<option>金融法规</option>");
        $("#professional").append("<option>工业应用数理统计</option>");
        $("#professional").append("<option>市场研究方法</option>");
        $("#professional").append("<option>固定收益证券分析(双语)</option>");
        $("#professional").append("<option>投资经济学</option>");
        $("#professional").append("<option>当代世界经济与政治</option>");
        $("#professional").append("<option>企业战略管理</option>");
        $("#professional").append("<option>投资管理</option>");
        $("#professional").append("<option>营销策划</option>");
        $("#professional").append("<option>服务计算概论</option>");
        $("#professional").append("<option>先进制造技术及应用</option>");
        $("#professional").append("<option>多媒体技术</option>");
        $("#professional").append("<option>管理信息系统(3)双语</option>");
        $("#professional").append("<option>运筹学(一)</option>");
        $("#professional").append("<option>服务管理</option>");
        $("#professional").append("<option>人力资源管理</option>");
        $("#professional").append("<option>物流工程进展</option>");
        $("#professional").append("<option>管理统计学</option>");
        $("#professional").append("<option>管理运筹学</option>");
        $("#professional").append("<option>公共关系学</option>");
        $("#professional").append("<option>我国的货币政策</option>");
        $("#professional").append("<option>成本会计学(二)</option>");
        $("#professional").append("<option>高级财务会计</option>");
        $("#professional").append("<option>税务会计与纳税筹划(2)</option>");
    } else if (choice == "软件学院") {
        $("#professional").append("<option>通信概论</option>");
        $("#professional").append("<option>算法设计与分析</option>");
        $("#professional").append("<option>网络安全</option>");
        $("#professional").append("<option>图像与多媒体新技术</option>");
        $("#professional").append("<option>企业级服务器系统导论</option>");
        $("#professional").append("<option>云计算及其安全技术</option>");
        $("#professional").append("<option>人机交互技术</option>");
        $("#professional").append("<option>Linux程序设计</option>");
        $("#professional").append("<option>计算机游戏程序设计</option>");
        $("#professional").append("<option>虚拟现实设计基础</option>");
        $("#professional").append("<option>信息安全创业基础</option>");
        $("#professional").append("<option>多核与并行程序设计</option>");
        $("#professional").append("<option>数媒创业基础</option>");
        $("#professional").append("<option>在线互动媒体技术</option>");
        $("#professional").append("<option>数据可视化技术</option>");
        $("#professional").append("<option>传感技术与应用</option>");
        $("#professional").append("<option>后期制作</option>");
        $("#professional").append("<option>社会计算</option>");
        $("#professional").append("<option>动画插件编程</option>");
        $("#professional").append("<option>金融法规</option>");
        $("#professional").append("<option>金融时间序列分析(2)</option>");
        $("#professional").append("<option>运筹学(一)</option>");
        $("#professional").append("<option>现代制造系统</option>");
        $("#professional").append("<option>行为金融与有效市场</option>");
        $("#professional").append("<option>固定收益证券分析(双语)</option>");
        $("#professional").append("<option>服务计算概论</option>");
        $("#professional").append("<option>软件工程</option>");
        $("#professional").append("<option>算法分析与设计</option>");
        $("#professional").append("<option>软件体系结构与设计模式</option>");
        $("#professional").append("<option>Linux操作系统</option>");
        $("#professional").append("<option>分布式系统导论</option>");
        $("#professional").append("<option>编译方法</option>");
        $("#professional").append("<option>新一代互联网技术</option>");
        $("#professional").append("<option>Java框架与组件技术</option>");
        $("#professional").append("<option>移动互联导论</option>");
        $("#professional").append("<option>主机系统导论</option>");
        $("#professional").append("<option>Web技术与应用</option>");
        $("#professional").append("<option>计算机系统安全 </option>");
    } else if (choice == "生命科学和健康学院") {
        $("#professional").append("<option>肿瘤学</option>");
        $("#professional").append("<option>神经生物学</option>");
        $("#professional").append("<option>干细胞与组织工程</option>");
        $("#professional").append("<option>细胞工程</option>");
        $("#professional").append("<option>专业外语</option>");
        $("#professional").append("<option>免疫学</option>");
        $("#professional").append("<option>细胞生物学</option>");
        $("#professional").append("<option>基因工程</option>");
        $("#professional").append("<option>微生物工程</option>");
        $("#professional").append("<option>实验动物学</option>");
        $("#professional").append("<option>细胞工程</option>");
        $("#professional").append("<option>免疫学</option>");
        $("#professional").append("<option>细胞生物学</option>");
        $("#professional").append("<option>基因工程</option>");
        $("#professional").append("<option>微生物工程</option>");
        $("#professional").append("<option>实验动物学</option>");
        $("#professional").append("<option>药用植物</option>");
        $("#professional").append("<option>发育生物学</option>");
        $("#professional").append("<option>毛细管电泳 </option>");
    } else if (choice == "江河建筑学院") {
        $("#professional").append("<option>建筑结构选型(建)</option>");
        $("#professional").append("<option>城市设计概论(建)</option>");
        $("#professional").append("<option>建筑施工(建)</option>");
        $("#professional").append("<option>建筑经济与业务管理(建)</option>");
        $("#professional").append("<option>建筑物理(一)</option>");
        $("#professional").append("<option>建筑力学</option>");
        $("#professional").append("<option>建筑构造(二)</option>");
        $("#professional").append("<option>居住区规划原理</option>");
        $("#professional").append("<option>学科前沿知识讲座</option>");
    } else if (choice == "文法学院") {
        $("#professional").append("<option>中国政治制度史</option>");
        $("#professional").append("<option>国家公务员法</option>");
        $("#professional").append("<option>商法学</option>");
        $("#professional").append("<option>税法学</option>");
        $("#professional").append("<option>环境法学</option>");
        $("#professional").append("<option>经济法学</option>");
        $("#professional").append("<option>社会保障学</option>");
        $("#professional").append("<option>中国政治思想史</option>");
        $("#professional").append("<option>当代世界经济与政治</option>");
        $("#professional").append("<option>公共关系学</option>");
        $("#professional").append("<option>社会政策概论</option>");
        $("#professional").append("<option>行政职业能力</option>");
        $("#professional").append("<option>土地管理学</option>");
        $("#professional").append("<option>企业文化</option>");
    } else if (choice == "马克思主义学院") {
        $("#professional").append("<option>当代世界经济与政治</option>");
    } else if (choice == "计算机科学与工程学院") {
        $("#professional").append("<option>Java语言及程序设计</option>");
        $("#professional").append("<option>编译原理</option>");
        $("#professional").append("<option>信息处理与机器翻译</option>");
        $("#professional").append("<option>多核并行程序设计(双语)</option>");
        $("#professional").append("<option>微机原理与程序设计</option>");
        $("#professional").append("<option>过程控制系统</option>");
        $("#professional").append("<option>自动控制原理4</option>");
        $("#professional").append("<option>计算机仿真技术基础</option>");
        $("#professional").append("<option>管理信息系统(二)</option>");
        $("#professional").append("<option>自动控制原理2)</option>");
        $("#professional").append("<option>人工智能技术基础</option>");
        $("#professional").append("<option>面向对象技术与JAVA程序设计</option>");
        $("#professional").append("<option>数据库管理系统实现技术</option>");
        $("#professional").append("<option>超大规模集成电路(VLSI)设计与EDA工程概论</option>");
        $("#professional").append("<option>微机原理及应用</option>");
        $("#professional").append("<option>可编程逻辑器件与EDA技术</option>");
        $("#professional").append("<option>可编程逻辑器件及应用</option>");
        $("#professional").append("<option>过程控制仪表及装置</option>");
        $("#professional").append("<option>单片机原理及应用</option>");
        $("#professional").append("<option>单片机及接口技术</option>");
        $("#professional").append("<option>专用芯片(ASIC)设计导论</option>");
        $("#professional").append("<option>电器控制基础与可编程控制器</option>");
        $("#professional").append("<option>微控制器原理</option>");
        $("#professional").append("<option>计算机组成原理</option>");
        $("#professional").append("<option>接口技术</option>");
        $("#professional").append("<option>电机原理及电机拖动(=)</option>");
        $("#professional").append("<option>电机原理及拖动(=)双语</option>");
        $("#professional").append("<option>电力电子电路</option>");
        $("#professional").append("<option>现代汽车电子技术</option>");
        $("#professional").append("<option>网站设计</option>");
        $("#professional").append("<option>基于FPGA的SOPC设计</option>");
        $("#professional").append("<option>M2M技术概论(双语)</option>");
        $("#professional").append("<option>计算机图形学基础</option>");
        $("#professional").append("<option>最优控制</option>");
        $("#professional").append("<option>自动控制原理</option>");
        $("#professional").append("<option>控制系统仿真与CAD</option>");
        $("#professional").append("<option>生物信息学导论</option>");
        $("#professional").append("<option>建筑智能化概论</option>");
        $("#professional").append("<option>数学类：数学建模技术</option>");
        $("#professional").append("<option>普适计算导论</option>");
        $("#professional").append("<option>误差理论与数据处理</option>");
        $("#professional").append("<option>组合数学</option>");
        $("#professional").append("<option>现代优化计算方法</option>");
        $("#professional").append("<option>智能优化方法</option>");
        $("#professional").append("<option>通信原理</option>");
        $("#professional").append("<option>多媒体图像通信</option>");
        $("#professional").append("<option>无线局域网</option>");
        $("#professional").append("<option>卫星通信</option>");
        $("#professional").append("<option>通信系统仿真技术基础</option>");
        $("#professional").append("<option>多媒体技术</option>");
        $("#professional").append("<option>计算机网络原理</option>");
        $("#professional").append("<option>计算机网络技术</option>");
        $("#professional").append("<option>信息安全基础</option>");
        $("#professional").append("<option>计算机安全与保密</option>");
        $("#professional").append("<option>通信电子线路</option>");
        $("#professional").append("<option>移动网络及其技术</option>");
        $("#professional").append("<option>数字信号处理2双语</option>");
        $("#professional").append("<option>实时信号处理技术</option>");
        $("#professional").append("<option>计算机网络</option>");
        $("#professional").append("<option>数字信号处理双语</option>");
        $("#professional").append("<option>数字信号处理</option>");
        $("#professional").append("<option>数字图像处理与机器视觉</option>");
        $("#professional").append("<option>激光原理</option>");
        $("#professional").append("<option>数字图像处理(双语)</option>");
        $("#professional").append("<option>电磁场与电磁波</option>");
        $("#professional").append("<option>计算机系统专题</option>");
        $("#professional").append("<option>现代检测技术及系统</option>");
        $("#professional").append("<option>数字系统设计</option>");
        $("#professional").append("<option>操作系统基础</option>");
        $("#professional").append("<option>电力系统自动化</option>");
        $("#professional").append("<option>数据库系统实践</option>");
    } else {
        $("#professional").append("<option>医学成像技术及系统(1)</option>");
        $("#professional").append("<option>微机原理与程序设计</option>");
        $("#professional").append("<option>电力电子电路</option>");
        $("#professional").append("<option>自动控制原理</option>");
        $("#professional").append("<option>大学日语</option>");
        $("#professional").append("<option>电机原理及电机拖动(=)</option>");
        $("#professional").append("<option>电器控制基础与可编程控制器</option>");
        $("#professional").append("<option>单片机及接口技术</option>");
        $("#professional").append("<option>医学信息学</option>");
        $("#professional").append("<option>数字电子技术基础口</option>");
        $("#professional").append("<option>数字信号处理</option>");
        $("#professional").append("<option>分子生物学</option>");
        $("#professional").append("<option>数据库原理</option>");
    }

    $("#professional").append("<option>无</option>");
}

/* apply teacher end */

/*self-studying begin */
function addstudying(name, studentid, qq, email, phone, la) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&college=" + $("#college").val() +
        "&selfsex=" + $("#selfsex").val() + "&time=" + $("#time").val() +
        "&selfcollege=" + $("#selfcollege").val() + "&sex=" + $("#sex").val(),
        type: "post",
        headers: headers,
        url: "/checkaddstudying",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}


$(document).on("click", "#submitaddstudying", function (e) {
    var name = $("#name").val();
    var studentid = $("#studentid").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var phone = $("#phone").val();

    if (check(name, studentid, qq, email, phone)) {
        var la = Ladda.create(document.querySelector("#submitaddstudying"));
        la.start();

        addstudying(name, studentid, qq, email, phone, la);
    }
});
/*self-studying end */

/*addstudent begin */
function addstudent(name, studentid, qq, email, college, phone, la) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&college=" + college +
        "&pairid=" + teastudentid,
        type: "post",
        headers: headers,
        url: "/checkaddstudent",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "booked") {
                showerror("别人已经抢先一步啦，换个辅学人试试吧");
            } else if (response == "emailerror") {
                showerror("匹配邮件发送失败，请稍后重试或联系负责人");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else if (response == "toomanttimes"){
                showerror("请选择新的一位辅学者吧！");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#submitaddstudent", function (e) {
    var name = $("#name").val();
    var studentid = $("#studentid").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var college = $("#selfcollege").val();

    if (check(name, studentid, qq, email, phone)) {
        if (college != $("#teachercollege").val()) {
            showerror("目前只支持在同学院间进行辅学");
        } else if (studentid == $("#teacherstudentid")) {
            showerror("请输入正确的学号");
        } else {
            var la = Ladda.create(document.querySelector("#submitaddstudent"));

            la.start();
            addstudent(name, studentid, qq, email, college, phone, la);
        }
    }
});
/*addstudent end */

/* admin beigin */
$(document).on("click", "#changebreurl", function (e) {
    $.ajax({
        type: "post",
        url: "/adminchangeurl",
        dataType: "json",
        error: function (data) {
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("出现异常，请稍后重试");
            } else {
                $("#urlvalue").text(response);
            }
        }
    });
})

function beginpair(la) {
    $.ajax({
        type: "post",
        url: "/admincheckpairbre",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("配对失败，请查看错误日志");
        },
        success: function (response) {
            la.stop();

            if (response == -1 || response == 0) {
                showerror("配对失败，请查看错误日志");
            } else {
                showsuccess1("配对完成,共配对" + response + "对");
                brenotpairednum -= response << 1;
                $("#brenotpairednum").text(brenotpairednum);
            }
        }
    });
}


$(document).on("click", "#beginpair", function (e) {
    if (window.confirm('确定开始匹配早餐叫醒用户吗？')) {
        var la = Ladda.create(document.querySelector("#beginpair"));

        la.start();
        beginpair(la);
    }
})


function sendpairinfo(la) {
    $.ajax({
        type: "post",
        url: "/adminsendbrepairinfo",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("暂无需要发送邮件的用户");
            } else {
                showsuccess1("发送邮件成功,已发送" + response + "%");
            }
        }
    });
}

$(document).on("click", "#sendpairinfo", function (e) {
    if (window.confirm('确定开始发送邮件吗？')) {
        var la = Ladda.create(document.querySelector("#sendpairinfo"));

        la.start();
        sendpairinfo(la);
    }
})

$(document).on("click", "#donwloadexcel", function (e) {
    var la = Ladda.create(document.querySelector("#donwloadexcel"));

    la.start();
    $.ajax({
        type: "post",
        url: "/adminmakeexcel",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("生成excel失败");
            } else {
                window.location.href = "/admindownload?type=userinfoexcel&filename=userinfo.zip";
            }
        }
    });
})

$(document).on("click", "#donwloadauthdata", function (e) {
    var la = Ladda.create(document.querySelector("#donwloadauthdata"));

    la.start();
    $.ajax({
        type: "post",
        url: "/adminmakeauthdatazip",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("生成压缩包失败,可能尚未有用户上传资料");
            } else {
                window.location.href = "/admindownload?type=authdatazip&filename=authdata.zip";
            }
        }
    });
})

function submitinfo(bremaxnum, la) {
    $.ajax({
        data: "bremaxnum=" + bremaxnum + "&title=" + $("#title").val() + "&logo=" + $("#logo").val(),
        type: "post",
        url: "/adminalterinfo",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("修改失败");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#submitinfo", function (e) {
    var bremaxnum = $("#bremaxnum").val();

    if (/^[1-9]\d*$/.test(bremaxnum)) {
        var la = Ladda.create(document.querySelector("#submitinfo"));
        la.start();

        submitinfo(bremaxnum, la);
    } else {
        showerror("请输入正确的数量");
    }
})

$(document).on("click", "#downloaderrormsag", function (e) {
    window.location.href = "/admindownload?type=errormsg&filename=my.log";
})
/* admin end */

/* queryuser begin */

function updateuser(name, studentid, qq, email, phone, time) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&time=" + time +
        "&selfcollege=" + $("#selfcollege").val() + "&college=" + $("#college").val() +
        "&sex=" + $("#sex").val() + "&type=" + type,
        type: "post",
        url: "/admincheckupdateuser",
        dataType: "json",
        error: function (data) {
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("修改失败，请稍后重试");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#updateuser", function (e) {
    var name = $("#name").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var phone = $("#phone").val();

    if (check(name, studentid, qq, email, phone)) {
        if (type == 1) {
            updateuser(name, studentid, qq, email, phone, $("#time1").val());
        } else {
            updateuser(name, studentid, qq, email, phone, $("#time2").val());
        }
    }
})


function updateteacher(name, studentid, qq, email, skill, phone) {
    $.ajax({
        data: "teachername=" + name + "&teacherstudentid=" + studentid +
        "&teacherqq=" + qq + "&teacheremail=" + email + "&teacherphone=" + phone +
        "&teacherskill=" + skill + "&teachercollege=" + $("#teachercollege").val(),
        type: "post",
        url: "/admincheckupdateassist",
        dataType: "json",
        error: function (data) {

            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("修改失败，请稍后重试");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#updateassist", function (e) {
    var name = $("#teachername").val();
    var qq = $("#teacherqq").val();
    var email = $("#teacheremail").val();
    var skill = $("#teacherskill").val();
    var phone = $("#teacherphone").val();

    if (check(name, studentid, qq, email, phone)) {
        if (skill.length <= 1 || skill.length >= 11) {
            showerror("请输入擅长领域(2-10字符)");
        } else {
            updateteacher(name, studentid, qq, email, skill, phone);
        }
    }
});

function checkdelete(genre, studentid) {
    $.ajax({
        data: "genre=" + genre + "&studentid=" + studentid,
        type: "post",
        url: "/admincheckdelete",
        dataType: "json",
        error: function (data) {
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("删除失败，请稍后重试");
            } else {
                showsuccess();
                setTimeout(function () {
                    window.location.href = "/adminqueryuser";
                }, 1000);
            }
        }
    });
}

/* queryuser end */

/* login begin */

function login(username, password, la) {
    $.ajax({
        data: {
            "username": username,
            "password": password
        },
        type: "post",
        url: "/checklogin",
        dataType: "json",
        error: function (data) {
            la.stop();
            $("#msg-success").hide(10);
            $("#msg-error").show(100);
            $("#msg-error-p").html("出现异常");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                $("#msg-success").hide(10);
                $("#msg-error").show(100);
                $("#msg-error-p").html("用户名或密码错误");
            } else if (response == "loginlock") {
                $("#msg-success").hide(10);
                $("#msg-error").show(100);
                $("#msg-error-p").html("输入错误次数过多，禁止登录");
                $("#login").addClass("disabled");
            } else {
                $("#msg-error").hide(10);
                $("#msg-success").show(100);
                $("#msg-success-p").html("登录成功");
                window.setTimeout("location.href='/admin'", 1000);
            }
        }
    });
}

function checkInfo() {
    var username = $("#username").val();
    var password = $("#password").val();

    if (username.length < 1 || password.length < 1) {
        $("#msg-success").hide(10);
        $("#msg-error").show(100);
        $("#msg-error-p").html("用户名或密码错误");
    } else {
        var la = Ladda.create(document.querySelector("#login"));

        la.start();
        login(username, password, la);
    }
}

$(document).on("click", "#login", function (e) {
    checkInfo();
});

$("#password").keydown(function (event) {
    if (event.keyCode == 13) {
        checkInfo();
    }
});
/* login end */

/* authenticate begin */

$("#submitauthentication").click(function () {
    var content = $("#content").val();

    if (!new RegExp("^201[0-9]{5,5}$", "").test($("#studentid").val())) {
        showerror("请填写正确的学号");
        return false;
    } else if (content.length < 1 || content.length > 150) {
        showerror("请填写不多于150字的辅学内容");
        return false;
    }

    la.start();
    $("#myform").ajaxSubmit(options);
    return false;
});

var options = {
    type: 'POST',
    url: "/uploadfile",
    dataType: 'json',
    success: function (response) {
        la.stop();

        if (response == "error") {
            showerror("出现异常，请稍候重试");
        } else if (response == "notenough") {
            showerror("请上传至少三张图片");
        } else if (response == "notfound") {
            showerror("未找到匹配学号，请确认后再提交");
        } else if (response == "lockupload") {
            showerror("请勿频繁上传，上传功能将锁定一段时间");
            $("#submitauthentication").addClass("disabled");
        } else {
            showsuccess1("提交成功，请注意提醒对方也要来认证喔");
        }
    }, error: function (data) {
        la.stop();
        showerror("出现异常，请稍候重试。（注意需上传至少三张照片）");
    }
};

$(".file").fileinput({
    showPreview: false,
    showUpload: false,
    showRemove: false,
    allowedFileTypes: ['image'],
    allowedPreviewMimeTypes: ['jpg', 'png', 'gif'],
    language: 'zh',
    maxFileSize: 13312
})
/* authenticate end */

/* editor begin */
function updateemail(add1, add2, add3, pwd1, pwd2, pwd3, host1, host2, host3) {
    $.ajax({
        data: {
            "add1": add1,
            "add2": add2,
            "add3": add3,
            "pwd1": pwd1,
            "pwd2": pwd2,
            "pwd3": pwd3,
            "host1": host1,
            "host2": host2,
            "host3": host3
        },
        type: "post",
        url: "/admincheckupdateemail",
        dataType: "json",
        error: function (data) {
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("修改失败，请稍后重试");
            } else {
                showsuccess1("修改成功");
            }
        }
    });
}

/* editor end */