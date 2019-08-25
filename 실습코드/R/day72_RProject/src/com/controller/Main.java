package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;

@Controller
public class Main {

	@RequestMapping("/main.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv; 
	}
	
	@RequestMapping("/showChartS.mc")
	public ModelAndView showChartS() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		mv.addObject("center", "s-chart");
		return mv; 
	}
	
	@RequestMapping("/showChartB.mc")
	public ModelAndView showChartB() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		mv.addObject("center", "b-chart");
		return mv; 
	}
	
	@RequestMapping("/showChartSB.mc")
	public ModelAndView showChartSB() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		mv.addObject("center", "SB");
		return mv; 
	}
	
	@RequestMapping("/showChartTS.mc")
	public ModelAndView showChartTS() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		mv.addObject("center", "TS");
		return mv; 
	}
	
	@RequestMapping("/schart.mc")
	@ResponseBody
	public void schart(HttpServletResponse rep) throws Exception  {		
		RConnection rc = new RConnection("70.12.114.215");
		rc.eval("source('/home/centos/rminipjt/r01.R')");
		REXP rx = rc.eval("rhive_bum()");
		RList rlist = rx.asList();


		int c[] = rlist.at("bum.seoul").asIntegers();		
		JSONArray jb = new JSONArray();
		for (int i = 0; i < c.length; i++) {			
			JSONObject jo = new JSONObject();
			jb.add(c[i]);
		}

		PrintWriter out = null;
		try {
			rep.setCharacterEncoding("EUC-KR");
			rep.setContentType("text/json;charset=UTF-8");
			out = rep.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		out.print(jb.toJSONString());
		rc.close(); 
	}
	
	@RequestMapping("/bchart.mc")
	@ResponseBody
	public void bchart(HttpServletResponse rep) throws Exception  {		
		RConnection rc = new RConnection("70.12.114.215");		
		rc.eval("source('/home/centos/rminipjt/r01.R')");
		REXP rx = rc.eval("rhive_bum()");
		RList rlist = rx.asList();
		int c[] = rlist.at("bum.busan").asIntegers();		
		JSONArray jb = new JSONArray();
		for (int i = 0; i < c.length; i++) {			
			JSONObject jo = new JSONObject();
			jb.add(c[i]);	
		}
		PrintWriter out = null;
		try {
			rep.setCharacterEncoding("EUC-KR");
			rep.setContentType("text/json;charset=UTF-8");
			out = rep.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(jb);
		out.print(jb.toJSONString());
		rc.close(); 
	}
	
	@RequestMapping("/SBchart.mc")
	@ResponseBody
	public void SBchart(HttpServletResponse rep) throws Exception  {		
		RConnection rc = new RConnection("70.12.114.215");		
		rc.eval("source('/home/centos/rminipjt/r01.R')");
		rc.eval("graph1()");
		rc.close(); 
	}
	
	@RequestMapping("/TSchart.mc")
	@ResponseBody
	public void TSchart(HttpServletResponse rep) throws Exception  {		
		RConnection rc = new RConnection("70.12.114.215");		
		rc.eval("source('/home/centos/rminipjt/r01.R')");
		REXP rx = rc.eval("rhive_bum()");
		rc.eval("graph2()");
		rc.close(); 
	}
}