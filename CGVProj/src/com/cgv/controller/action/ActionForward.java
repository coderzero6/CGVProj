package com.cgv.controller.action;

public class ActionForward {
	private boolean isRedirect = false; //redirect 여부 체크
	private String path = null; //이동할 경로 설정
	
	public boolean isRedirect() {return isRedirect;}
	public void setRedirect(boolean isRedirect) {this.isRedirect = isRedirect;}
	public String getPath() {return path;}
	public void setPath(String path) {this.path = path;}
	
}
