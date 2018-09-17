package com.cgv.ticket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgv.controller.action.Action;
import com.cgv.controller.action.ActionForward;

public class TicketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//영화 리스트, 극장 리스트, 날짜리스트, 영화시간 리스트 넘겨줘야함
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./ticket/ticket_list.jsp");
		
		return forward;
	}

}
