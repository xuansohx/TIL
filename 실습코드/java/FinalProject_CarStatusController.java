package Final.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import Final.frame.Biz;
import Final.vo.Car;
import Final.vo.CarStatus;
import Final.vo.Reservation;
import Final.vo.User;

/* Final Project Code Review */

@Controller
public class CarStatusController {
	@Resource(name = "ubiz")
	Biz<String, User> ubiz;

	@Resource(name = "carbiz") 
	Biz<Integer,Car> carbiz; 
	 
	@Resource(name = "csbiz")
	Biz<Integer, CarStatus> csbiz;

	@Resource(name = "Ureserbiz") // id's reservation
	Biz<String, Reservation> urbiz;
	
	@Resource(name = "reserbiz") // All reservation
	Biz<Integer, Reservation> rbiz;

	@RequestMapping("/ctatedetail.mc")
	public ModelAndView ctatedetail() {
		ModelAndView mv = new ModelAndView();
		ArrayList<Reservation> reservationList = null;
		try {
			reservationList = rbiz.getAll(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("rl", reservationList);
		mv.setViewName("admin/cardetail");
		return mv;
	}
	
	@RequestMapping("/statusCenter.mc")
	public ModelAndView getStatusFromAndroid(String carid , String carstatus) {
		ModelAndView mv = new ModelAndView();
		int car_id = Integer.parseInt(carid);
		CarStatus cs = new CarStatus(car_id,carstatus);
		System.out.println(carstatus);
		try {
			csbiz.modify(cs);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("admin/cardetail");
		return mv;
	}
	
	@RequestMapping("/allocation.mc")
	public void allocation(HttpServletResponse response, HttpSession session) {
		// reservation을 session에 담아서 가져옴
		Reservation reserve =
				(Reservation) session.getAttribute("sch");
	
		// for My Reservation List
		String uid = reserve.getUserid(); 
				
		// Reservation
		int rcarid = reserve.getCarid(); 
		int rcalid = reserve.getCalid(); 
			
	
		// List
		ArrayList<Car> clist = null; 
		 try { 
			 rbiz.register(reserve);
			 reserve = urbiz.get(uid);
			 rcalid = reserve.getCalid();
			 clist = carbiz.getAll(1); 
			 } catch (Exception e) 
		 { 
				 e.printStackTrace(); 
		 }
		 
		// Array
		 Car[] CarArray = new Car[clist.size()];
		 int size=0;
		 // Array -> list
		 for(Car temp : clist) {
			 CarArray[size++]=temp;
		 }
				
		// Allocation
		// reservation table -> carid, car table -> calid
		 Car car = null;
			for(int i=0; i<CarArray.length; i++) {
				car = CarArray[i];
				if(rcarid == 0) {
					if(car.getCalid()==0) {
						reserve.setCarid(car.getCarid());
						car.setCalid(rcalid);
						System.out.println(rcalid);
						break; // for문 끝내주기
					}
				}			
			}
			
		// insert DataBase
		try {
			carbiz.modify(car);
			rbiz.modify(reserve);
			System.out.println(car);
			System.out.println(reserve);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// End Action and Move to other page
		try {
			sendPush(reserve);
			response.sendRedirect("schelist.mc?userid="+uid);
		}catch (IOException e) {
				e.printStackTrace();
			}
	
	}
	
	public void sendPush(Reservation reserve) {
		String uid = reserve.getUserid();
		User u = null;
		try {
			u = ubiz.get(uid);
			String token = u.getUserdevice();
			int pin = reserve.getPinNum();
			FcmUtil fcm = new FcmUtil();
			fcm.send_FCM(token, "decoca", pin + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
	@RequestMapping("/getStatus.mc")
	public ModelAndView getStatus(String carid) {
		ModelAndView mv = new ModelAndView();
		CarStatus cs = null;
		int caridd = Integer.parseInt(carid);
		System.out.println(caridd);
		try {
			cs = csbiz.get(caridd);
			System.out.println(cs);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("admin/cardetail");
		return mv;
	}
	

}
