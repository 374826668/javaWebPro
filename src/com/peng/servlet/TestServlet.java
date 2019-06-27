package com.peng.servlet;

import com.kingdee.bos.util.CryptoTean;
import com.kingdee.eas.base.permission.app.util.PermissionUtil;
import com.kingdee.util.StringUtils;

import java.io.IOException;

public class TestServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //EAS用户密码账号测试案例
        boolean isMatch = false;
        try {
            String strOldPwd = CryptoTean.encrypt("007728", "123");//V5tKRmzV2K4=
            String oldPW = PermissionUtil.decrypt("007728", strOldPwd);//123

            String passwordINDB = "C5ZqQVo4icw+RSxrgxlNVA==";//123的加密密码


            if (StringUtils.isEmpty(oldPW)) {
                isMatch = StringUtils.isEmpty(passwordINDB);
            }else {
                //j6XjSPtJTvaGduzN1p5FuxO33n8 USERID
                isMatch = PermissionUtil.encrypt("j6XjSPtJTvaGduzN1p5FuxO33n8=", oldPW).equals(passwordINDB);
            }
            System.out.println(isMatch+"");
        }
        catch(Exception e) {

        }
        response.getWriter().write(isMatch+"");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    doPost(request,response);
    }
}
