package com.peng.servlet;

import java.lang.reflect.Method;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;


public class wstest{
	public static void main(String[] args) 
	{
		
		try {
			/*EASLoginProxyService loginLocator=new EASLoginProxyServiceLocator();
			EASLoginProxy loginProxy=loginLocator.getEASLogin();
			System.out.println("----开始登录-------");
			WSContext context;
			context = loginProxy.login("010712", "111", "eas", "FYCS", "L2", 2);
			System.out.println("--------登录成功SessionID:"+context.getSessionId());
			
			WSupdatePwdSrvProxyServiceLocator updatePwdlocation= new WSupdatePwdSrvProxyServiceLocator();
			updatePwdlocation.setMaintainSession(true);
			WSupdatePwdSrvProxy updatePwdProxy=updatePwdlocation.getWSupdatePwd();
			((WSupdatePwdSoapBindingStub)updatePwdProxy).setHeader(new SOAPHeaderElement("http://login.webservice.bos.kingdee.com","SessionId",context.getSessionId()));
//			((WSupdatePwdSoapBindingStub)updatePwdProxy).setHeader(new SOAPHeaderElement("http://39.98.46.61:6888/portal","SessionId",context.getSessionId()));
			String a=updatePwdProxy.updatePwd("010712", "111", "");*/
//			System.out.println(a);
			
			//调用登陆接口
	           Service s=new Service();
	           Call call=(Call)s.createCall();
	           call.setOperationName("login");
	           call.setTargetEndpointAddress("http://39.98.46.61:6888/ormrpc/services/EASLogin?wsdl");
	           call.setReturnType(new QName("urn:client","WSContext"));
	           call.setReturnClass(WSContext.class);
	           call.setReturnQName(new QName("","loginReturn"));
	           //超时
	           call.setTimeout(Integer.valueOf(1000*600000*60));
	           call.setMaintainSession(true);
	           //登陆接口参数
	           WSContext rs=(WSContext)call.invoke(new Object[]{"010712", "333", "eas", "FYCS", "L2", Integer.valueOf(0)});
	           System.out.println(rs.getSessionId());
	           //清理
	           call.clearOperation();
	           //调用业务接口
	           call.setOperationName("updatePwd");
	           call.setTargetEndpointAddress("http://39.98.46.61:6888/ormrpc/services/WSupdatePwd");
	           //call.setReturnType(new QName("urn:lang.java","String"));
	           //call.setReturnClass(String.class);
	           call.setReturnQName(new QName("","updatePwdReturn"));
	           call.setTimeout(Integer.valueOf(1000*600000*60));
	           call.setMaintainSession(true);
	           SOAPHeaderElement header=new SOAPHeaderElement("http://login.webservice.bos.kingdee.com","SessionId", rs.getSessionId());
	           call.addHeader(header);
	           //接口参数
	           String aa=(String)call.invoke(new Object[]{"010712", "333", ""} );
	           System.out.println(aa);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		
	}
}







































