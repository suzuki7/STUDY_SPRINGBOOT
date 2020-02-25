package com.tuyano.springboot;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HelloController {

	@Autowired
	MyDataRepository repository;

	@RequestMapping(value="/", method= RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav){
		mav.setViewName("index");
		mav.addObject("msg", "test");
		Iterable<MyData> list =repository.findAll();
		mav.addObject("datalist",list);
		return mav;
	}
	
	@RequestMapping(value="/", method= RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView form(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	
	
	

}
	
//	@RequestMapping(value="/{num}")
//	public ModelAndView index(@PathVariable int num,ModelAndView mav) {
//		mav.setViewName("index");
//		mav.addObject("num", num);
//		if(num >=0) {
//			mav.addObject("check","num >= data.size() ? 0 : num");
//		}else {
//			mav.addObject("check","num <= data.size() * -1 ? 0 : num * -1");
//		}
//		ArrayList<DataObject> data = new ArrayList<>();
//		data.add(new DataObject (1,"@aaa","123"));
//		data.add(new DataObject (2,"@tta","444"));
//		data.add(new DataObject (3,"@yya","666"));
//		mav.addObject("data", data);		
//		
//		return mav; 
//	}
//}
//
//class DataObject{
//	private int id;
//	private String name;
//	private String value;
//	
//	
//	public DataObject(int id,String name,String value) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.value = value;
//		
//	}
//		public int getId() {
//			return id;
//		}
//		public void setId(int id) {
//			this.id = id;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getValue() {
//			return value;
//		}
//		public void setValue(String value) {
//			this.value = value;
//		}
//}
//	
//@RequestMapping("/")
//	public ModelAndView index(ModelAndView mav) {
//		mav.setViewName("index");
//		ArrayList<String[]> data = new ArrayList<>();
//		data.add(new String[] {"tara","@aaa","123"});
//		data.add(new String[] {"baba","@tta","444"});
//		data.add(new String[] {"yyya","@yya","666"});
//		mav.addObject("data", data);
//		return mav;
//	}
//}

	
	
//@RequestMapping(value="/")
//public ModelAndView index(ModelAndView mav) {
//	mav.setViewName("index");
//	mav.addObject("msg", "current data.");
//	DataObject obj = new DataObject(123,"hanako","hanako@flower");
//	mav.addObject("object", obj);
//	
//	mav.addObject("test","message 1 <hr/> message 2 <br/> message 3");
//	return mav;
//}
//
//

//}
//
//	
	
	
	
	
	
//	@RequestMapping(value="/",method=RequestMethod.GET)
//	public ModelAndView index(ModelAndView mav) {
//		mav.addObject("msg", "フォームを送信してください");
//		mav.setViewName("index");
//		return mav;
//	}
//	
//	@RequestMapping(value="/",method=RequestMethod.POST)
//	public ModelAndView send(
//		@RequestParam(value="check1",required=false) boolean check1,
//		@RequestParam(value="radio1",required=false) String redio1,
//		@RequestParam(value="select1",required=false) String select1,
//		@RequestParam(value="select2",required=false) String[] select2,
//		ModelAndView mav) {
//		
//		String res="";
//		
//		try {
//		res = "check:"+check1+" radio:"+redio1+" select:"+select1+
//				"\nselect2";
//		
//		for(int i =0 ;i<select2.length;i++) {
//			res +=","+select2[i];
//		}
//		}catch (Exception e) {
//			res +=null;
//		}
//				
//				
//		mav.addObject("msg",res);
//		mav.setViewName("index");
//		return mav;
//	}

	
//	@RequestMapping("/{num}")
//	public ModelAndView index(@PathVariable int num,ModelAndView mav) {
//		int res = 0;
//		for(int i = 1;i<=num;i++) {
//			res +=i;}
//		mav.addObject("msg", "total:" + res);
//		mav.setViewName("index");
//		return mav;
//	  }
//	}
	
//	@RequestMapping("/{num}")
//	public String index(@PathVariable int num, Model model) {
//		int res = 0;
//		for(int i = 1;i<=num;i++) {
//			res +=i;
//			model.addAttribute("msg","total:" + res);
//		}
//		return "index";
//	}
	





