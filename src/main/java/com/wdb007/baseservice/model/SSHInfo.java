package com.wdb007.baseservice.model;

/**
 * ssh连接信息
 * @author tianzhi
 *
 */
public class SSHInfo {
	private static SSHInfo instance = null;

	private SSHInfo() {
	}

	public static SSHInfo getSSHInfo() {
		if (instance == null) {
			synchronized (SSHInfo.class) {
				if (instance == null) {
					instance = new SSHInfo();
				}
			}
		}
		return instance;
	}
    public static void setSSHInfo(SSHInfo sSHInfo) {
    	instance=sSHInfo;
    }
	public String publish_ip;
	public String publish_port;
	public String publish_usr;
	public String publish_pws;
	public String local_ip;
	public String local_port;
	public String local_usr;
	public String local_pws;

	public String getPublish_ip() {
		return publish_ip;
	}

	public void setPublish_ip(String publish_ip) {
		this.publish_ip = publish_ip;
	}

	public String getPublish_port() {
		if (publish_port == null || publish_port == "")
			return "22";
		else
			return publish_port;
	}

	public void setPublish_port(String publish_port) {
		this.publish_port = publish_port;
	}

	public String getPublish_usr() {
		return publish_usr;
	}

	public void setPublish_usr(String publish_usr) {
		this.publish_usr = publish_usr;
	}

	public String getPublish_pws() {
		return publish_pws;
	}

	public void setPublish_pws(String publish_pws) {
		this.publish_pws = publish_pws;
	}

	public String getLocal_ip() {
		return local_ip;
	}

	public void setLocal_ip(String local_ip) {
		this.local_ip = local_ip;
	}

	public String getLocal_port() {
		if (local_port==null||local_port=="") {
			return "3306"; 
		}
		return local_port;
	}

	public void setLocal_port(String local_port) {
		this.local_port = local_port;
	}

	public String getLocal_usr() {
		return local_usr;
	}

	public void setLocal_usr(String local_usr) {
		this.local_usr = local_usr;
	}

	public String getLocal_pws() {
		return local_pws;
	}

	public void setLocal_pws(String local_pws) {
		this.local_pws = local_pws;
	}
}