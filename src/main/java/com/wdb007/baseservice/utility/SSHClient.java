package com.wdb007.baseservice.utility;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.wdb007.baseservice.model.SSHInfo;

/**
 * ssh操作类
 * @author tianzhi
 *
 */
public class SSHClient {
	private JSch jsch = new JSch();
	private Session session;
    private SSHInfo sshInfo;
	public SSHClient(SSHInfo sshInfo) {
		this.sshInfo=sshInfo;
	}
    public void startSSH() {
		try {
			session = jsch.getSession(sshInfo.getPublish_usr(), sshInfo.getPublish_ip(), 22);
			session.setPassword(sshInfo.getPublish_pws());
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			System.out.println(session.getServerVersion());// 这里打印SSH服务器版本信息
			int assinged_port = session.setPortForwardingL("127.0.0.1", 3306, "127.0.0.1", 3306);// 端口映射 转发
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void closeSSH() {
		if (session!=null&&session.isConnected()) {
			session.disconnect();	
		}
	}
}
